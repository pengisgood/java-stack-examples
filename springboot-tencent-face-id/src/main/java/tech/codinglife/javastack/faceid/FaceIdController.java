package tech.codinglife.javastack.faceid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tech.codinglife.javastack.faceid.model.AccessToken;
import tech.codinglife.javastack.faceid.model.H5FaceId;
import tech.codinglife.javastack.faceid.model.TicketResult;
import tech.codinglife.javastack.faceid.model.TicketType;
import tech.codinglife.javastack.faceid.utils.FaceIdUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019-09-18
 * Time: 17:16
 */
@Controller
public class FaceIdController {
    private final RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${tencent.app.id}")
    private String appId;

    @Value("${tencent.app.secret}")
    private String secret;

    @Value("${tencent.app.version}")
    private String version;

    public FaceIdController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/callback")
    public String callbackPage(Model model, @RequestParam Map<String, String> queryParams) {
        model.addAttribute("queryParams", queryParams);
        return "callback";
    }


    @GetMapping("/access_token")
    public ResponseEntity<AccessToken> getAccessToken() {
        String url = "https://idasc.webank.com/api/oauth2/access_token";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("app_id", appId)
            .queryParam("secret", secret)
            .queryParam("grant_type", "client_credential")
            .queryParam("version", version);

        return restTemplate.getForEntity(builder.toUriString(), AccessToken.class);
    }

    @GetMapping("/ticket")
    public ResponseEntity<TicketResult> getSignTicket(@RequestParam String accessToken,
                                                      @RequestParam TicketType type,
                                                      @RequestParam String userId) {
        String url = "https://idasc.webank.com/api/oauth2/api_ticket";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("app_id", appId)
            .queryParam("access_token", accessToken)
            .queryParam("type", type)
            .queryParam("version", version);

        if (TicketType.NONCE == type) {
            builder.queryParam("user_id", userId);
        }

        return restTemplate.getForEntity(builder.toUriString(), TicketResult.class);
    }

    @GetMapping("/faceid")
    public ResponseEntity<H5FaceId> getH5FaceId(@RequestParam String name,
                                                @RequestParam String idNo,
                                                @RequestParam String userId,
                                                @RequestParam String signTicket) {
        String url = "https://idasc.webank.com/api/server/h5/geth5faceid";
        String orderNo = FaceIdUtil.calculateNonce(5);
        String[] args = new String[]{appId, orderNo, name, idNo, userId, version, signTicket};
        String sign = FaceIdUtil.calculateSign(args);

        HashMap<String, String> body = new HashMap<>();
        body.put("webankAppId", appId);
        body.put("orderNo", orderNo);
        body.put("name", name);
        body.put("idNo", idNo);
        body.put("userId", userId);
        body.put("sourcePhotoType", "1");
        body.put("version", version);
        body.put("sign", sign);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<?> request = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(url, request, H5FaceId.class);
    }

    /**
     * PC端H5接入
     * 参考文档: https://cloud.tencent.com/document/product/1007/35894
     *
     * @param orderNo
     * @param faceId
     * @param nonceTicket
     * @param userId
     * @return
     */
    @GetMapping("/login/pc")
    public ResponseEntity<String> pcLogin(@RequestParam String orderNo,
                                          @RequestParam String faceId,
                                          @RequestParam String nonceTicket,
                                          @RequestParam String userId) {
        String url = "https://ida.webank.com/api/pc/login";
        String nonce = FaceIdUtil.calculateNonce(32);
        String[] args = new String[]{appId, userId, nonce, version, faceId, orderNo, nonceTicket};
        String sign = FaceIdUtil.calculateSign(args);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("webankAppId", appId)
            .queryParam("version", version)
            .queryParam("nonce", nonce)
            .queryParam("orderNo", orderNo)
            .queryParam("h5faceId", faceId)
            .queryParam("url", FaceIdUtil.encodeURIComponent("http://localhost:8081/callback"))
            .queryParam("userId", userId)
            .queryParam("sign", sign).encode();

        return ResponseEntity.ok(builder.build(true).toUriString());
    }

    /**
     * 独立H5接入
     * 参考文档: https://cloud.tencent.com/document/product/1007/35884
     *
     * @param orderNo
     * @param faceId
     * @param nonceTicket
     * @param userId
     * @return
     */
    @GetMapping("/login/web")
    public ResponseEntity<String> webLogin(@RequestParam String orderNo,
                                           @RequestParam String faceId,
                                           @RequestParam String nonceTicket,
                                           @RequestParam String userId) {
        String url = "https://ida.webank.com/api/web/login";
        String nonce = FaceIdUtil.calculateNonce(32);
        String[] args = new String[]{appId, userId, nonce, version, faceId, orderNo, nonceTicket};
        String sign = FaceIdUtil.calculateSign(args);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("webankAppId", appId)
            .queryParam("version", version)
            .queryParam("nonce", nonce)
            .queryParam("orderNo", orderNo)
            .queryParam("h5faceId", faceId)
            .queryParam("url", FaceIdUtil.encodeURIComponent("http://localhost:8081/callback"))
            .queryParam("resultType", "1")
            .queryParam("userId", userId)
            .queryParam("sign", sign)
            .queryParam("from", "browser")
            .queryParam("redirectType", "1")
            .encode();

        return ResponseEntity.ok(builder.build(true).toUriString());
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String orderNo,
                                         @RequestParam String signTicket) {
        String url = "https://idasc.webank.com/api/server/sync";
        String nonce = FaceIdUtil.calculateNonce(32);
        String[] args = new String[]{appId, orderNo, version, signTicket, nonce};
        String sign = FaceIdUtil.calculateSign(args);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("app_id", appId)
            .queryParam("version", version)
            .queryParam("nonce", nonce)
            .queryParam("order_no", orderNo)
            .queryParam("get_file", "1")
            .queryParam("sign", sign).encode();

        return restTemplate.getForEntity(builder.toUriString(), String.class);
    }

}

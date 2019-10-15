function getAccessToken() {
  localStorage.setItem('userId', String(Date.now()));
  $.get({
    url: "/access_token",
    success: function (data) {
      localStorage.setItem('accessToken', data["access_token"]);
      $("pre#getAccessToken").html(JSON.stringify(data, null, 2));
    }
  })
}

function getTicket(type) {
  $.get({
    url: "/ticket",
    data: {
      accessToken: localStorage.getItem('accessToken'),
      type: type,
      userId: localStorage.getItem('userId')
    },
    success: function (data) {
      switch (type) {
        case 'SIGN':
          localStorage.setItem('signTicket', data["tickets"][0]["value"]);
          $("pre#getSignTicket").html(JSON.stringify(data, null, 2));
          break;
        case 'NONCE':
          localStorage.setItem('nonceTicket', data["tickets"][0]["value"]);
          $("pre#getNonceTicket").html(JSON.stringify(data, null, 2));
          break;
      }
    }
  })
}

function getFaceId() {
  $.get({
    url: "/faceid",
    data: {
      name: $("input[name='name']").val(),
      idNo: $("input[name='idNo']").val(),
      userId: localStorage.getItem('userId'),
      signTicket: localStorage.getItem('signTicket')
    },
    success: function (data) {
      localStorage.setItem('bizSeqNo', data["result"]["bizSeqNo"]);
      localStorage.setItem('orderNo', data["result"]["orderNo"]);
      localStorage.setItem('faceId', data["result"]["h5faceId"]);
      $("pre#getFaceId").html(JSON.stringify(data, null, 2));
    }
  })
}

function pcLogin() {
  $.get({
    url: "/login/pc",
    data: {
      orderNo: localStorage.getItem('orderNo'),
      faceId: localStorage.getItem('faceId'),
      userId: localStorage.getItem('userId'),
      nonceTicket: localStorage.getItem('nonceTicket')
    },
    success: function (data) {
      $("pre#login").html(JSON.stringify(data, null, 2));

      $("body").append('<a id="login" href="' + data + '" target="_blank">&nbsp;</a>');
      $('a#login')[0].click();
    }
  })
}

function webLogin() {
  $.get({
    url: "/login/web",
    data: {
      orderNo: localStorage.getItem('orderNo'),
      faceId: localStorage.getItem('faceId'),
      userId: localStorage.getItem('userId'),
      nonceTicket: localStorage.getItem('nonceTicket')
    },
    success: function (data) {
      var qrCode = new QRCode("qrCode", {
        colorDark : "#000000",
        colorLight : "#ffffff",
        correctLevel : QRCode.CorrectLevel.H
      });
      qrCode.makeCode(data);

      $("pre#login").html(JSON.stringify(data, null, 2));
    }
  })
}

function verify() {
  $.get({
    url: "/verify",
    data: {
      orderNo: localStorage.getItem("orderNo"),
      signTicket: localStorage.getItem("signTicket"),
    },
    success: function (data) {
      var img = "data:image/png;base64," + data["result"]["photo"];
      var video = "data:video/mp4;base64," + data["result"]["video"];
      $("img").attr("src", img);
      $("video").attr("src", video);
      $("pre#verify").html(JSON.stringify(data, null, 2));
    }
  })
}

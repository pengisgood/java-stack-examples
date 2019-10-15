package tech.codinglife.javastack.faceid.model;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019-09-18
 * Time: 17:52
 */
public class H5FaceId extends Base {
    private Result result;

    public H5FaceId() {
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private static class Result {
        private String bizSeqNo;
        private String orderNo;
        private String h5faceId;

        public Result() {
        }

        public String getBizSeqNo() {
            return bizSeqNo;
        }

        public void setBizSeqNo(String bizSeqNo) {
            this.bizSeqNo = bizSeqNo;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getH5faceId() {
            return h5faceId;
        }

        public void setH5faceId(String h5faceId) {
            this.h5faceId = h5faceId;
        }
    }
}

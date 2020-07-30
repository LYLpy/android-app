package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/13------更新------
 * 意见反馈
 */

public class FeedbackModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"feedBack":{"token":"e724877f9ffea392fa6254b36b4b0c47","message":"啊啊啊111","page":"2","id":"17"},"addSuccess":true}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private Databean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * feedBack : {"token":"e724877f9ffea392fa6254b36b4b0c47","message":"啊啊啊111","page":"2","id":"17"}
         * addSuccess : true
         */

        private FeedBackbean feedBack;
        private boolean addSuccess;

        public FeedBackbean getFeedBack() {
            return feedBack;
        }

        public void setFeedBack(FeedBackbean feedBack) {
            this.feedBack = feedBack;
        }

        public boolean isAddSuccess() {
            return addSuccess;
        }

        public void setAddSuccess(boolean addSuccess) {
            this.addSuccess = addSuccess;
        }

        public static class FeedBackbean {
            /**
             * token : e724877f9ffea392fa6254b36b4b0c47
             * message : 啊啊啊111
             * page : 2
             * id : 17
             */

            private String token;
            private String message;
            private String page;
            private String id;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}

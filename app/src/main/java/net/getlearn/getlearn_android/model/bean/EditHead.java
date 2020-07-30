package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/25------更新------
 * 修改头像
 */

public class EditHead {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"result":"success","message":"头像修改成功","headimgurl":"http:xxxx.img"}
     */

    private String error;
    private String success;
    private String status;
    private String msg;
    private Databean data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
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
         * result : success
         * message : 头像修改成功
         * headimgurl : http:xxxx.img
         */

        private String result;
        private String message;
        private String headimgurl;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }
    }
}

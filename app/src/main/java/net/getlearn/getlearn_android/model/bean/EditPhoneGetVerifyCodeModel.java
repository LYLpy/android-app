package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/20------更新------
 * 修改用户手机获取验证码接口
 */

public class EditPhoneGetVerifyCodeModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 验证码发送成功!
     * data : {"verify_id":"9ump3ht8cj5safolsejnmhauuq"}
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
         * verify_id : 9ump3ht8cj5safolsejnmhauuq
         */

        private String verify_id;

        public String getVerify_id() {
            return verify_id;
        }

        public void setVerify_id(String verify_id) {
            this.verify_id = verify_id;
        }
    }
}

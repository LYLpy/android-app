package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/5------更新------
 */

public class GetVerificationCodeModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"verify_id":"sr08mu8i2egdddhf2nq9pe6mo3","is_old":0,"phone":"17702000301"}
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
         * verify_id : sr08mu8i2egdddhf2nq9pe6mo3
         * is_old : 0
         * phone : 17702000301
         */
        private String verify_id;//验证码ID,提交验证码
        private int is_old;//新用户为0，已注册为1
        private String phone;

        public String getVerify_id() {
            return verify_id;
        }

        public void setVerify_id(String verify_id) {
            this.verify_id = verify_id;
        }

        public int getIs_old() {
            return is_old;
        }

        public void setIs_old(int is_old) {
            this.is_old = is_old;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}

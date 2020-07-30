package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/19------更新------
 * 编辑地址
 */

public class EditAddressModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"result":1}
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
         * result : 1
         */

        private int result;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
}

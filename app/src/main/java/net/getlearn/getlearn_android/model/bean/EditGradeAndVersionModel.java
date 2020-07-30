package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/28------更新------
 */

public class EditGradeAndVersionModel {

    /**
     * error : 10000
     * success : fail
     * status : 200
     * msg : 修改失败
     * data : {"result":0}
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
         * result : 0
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

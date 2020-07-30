package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/15------更新------
 */

public class GradeModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"id":8,"option":"1年级"},{"id":9,"option":"2年级"},{"id":10,"option":"3年级"},{"id":11,"option":"4年级"},{"id":12,"option":"5年级"},{"id":13,"option":"6年级"},{"id":14,"option":"7年级"}]
     */

    private String error;
    private String success;
    private String status;
    private String msg;
    private List<Databean> data;

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

    public List<Databean> getData() {
        return data;
    }

    public void setData(List<Databean> data) {
        this.data = data;
    }

    public static class Databean{
        /**
         * id : 8
         * option : 1年级
         */

        private int id;
        private String option;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }
    }
}

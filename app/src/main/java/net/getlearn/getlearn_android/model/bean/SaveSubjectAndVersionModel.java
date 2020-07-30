package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/15------更新------
 * 保存用户选择科目和版本
 */

public class SaveSubjectAndVersionModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"grade_id":"9","select_course":"23@38,24@38,25@38,26@38,27@38,28@38,29@38,30@38,31@38,33@38,109@38,0@38","add_time":1563259201,"uid":1}
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
         * grade_id : 9
         * select_course : 23@38,24@38,25@38,26@38,27@38,28@38,29@38,30@38,31@38,33@38,109@38,0@38
         * add_time : 1563259201
         * uid : 1
         */

        private String grade_id;
        private String select_course;
        private int add_time;
        private int uid;

        public String getGrade_id() {
            return grade_id;
        }

        public void setGrade_id(String grade_id) {
            this.grade_id = grade_id;
        }

        public String getSelect_course() {
            return select_course;
        }

        public void setSelect_course(String select_course) {
            this.select_course = select_course;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}

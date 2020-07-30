package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/24------更新------
 * 选课界面获取选课科目
 */

public class SubjectModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"subject_id":23,"subject":"语文"},{"subject_id":24,"subject":"数学"},{"subject_id":25,"subject":"英语"},{"subject_id":26,"subject":"物理"},{"subject_id":0,"subject":"无学科"}]
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

    public static class Databean {
        /**
         * subject_id : 23
         * subject : 语文
         */

        private int subject_id;
        private String subject;

        public int getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(int subject_id) {
            this.subject_id = subject_id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }
}

package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/1------更新------
 * 提交评论
 */

public class SubmitComment {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"id":"4","m_content":"课程不错试试"}
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
         * id : 4
         * m_content : 课程不错试试
         */

        private String id;
        private String m_content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getM_content() {
            return m_content;
        }

        public void setM_content(String m_content) {
            this.m_content = m_content;
        }
    }
}

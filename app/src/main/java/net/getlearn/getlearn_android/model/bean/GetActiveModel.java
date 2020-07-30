package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/9/6------更新------
 * 获取活动（链接）
 */

public class GetActiveModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"active_id":1,"active_link":"http://gl.app.com/app/share/active?token=130f90b0a7ca51cdfe9d68748fe60cd1&active_id=1"}
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
         * active_id : 1
         * active_link : http://gl.app.com/app/share/active?token=130f90b0a7ca51cdfe9d68748fe60cd1&active_id=1
         */

        private int active_id;
        private String active_link;

        public int getActive_id() {
            return active_id;
        }

        public void setActive_id(int active_id) {
            this.active_id = active_id;
        }

        public String getActive_link() {
            return active_link;
        }

        public void setActive_link(String active_link) {
            this.active_link = active_link;
        }
    }
}

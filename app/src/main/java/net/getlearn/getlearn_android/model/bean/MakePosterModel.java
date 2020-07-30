package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/9/6------更新------
 * 获取活动海报
 */

public class MakePosterModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"poster_link":"http://gl.app.com/uploads/poster/e1d428c1923eafb904b1e9d812a70776.jpg"}
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
         * poster_link : http://gl.app.com/uploads/poster/e1d428c1923eafb904b1e9d812a70776.jpg
         */

        private String poster_link;

        public String getPoster_link() {
            return poster_link;
        }

        public void setPoster_link(String poster_link) {
            this.poster_link = poster_link;
        }
    }
}

package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/16------更新------
 * 学习干货点赞
 */

public class StudyKeyLikeModel {

    /**
     * error : 200
     * status : 200
     * data : {"id":"1","success":"success","fabulous":"800","status":"click"}
     */

    private int error;
    private int status;
    private Databean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * id : 1
         * success : success
         * fabulous : 800
         * status : click
         */

        private String id;
        private String success;
        private String fabulous;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getFabulous() {
            return fabulous;
        }

        public void setFabulous(String fabulous) {
            this.fabulous = fabulous;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

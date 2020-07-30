package net.getlearn.getlearn_android.model.bean;

public class GetVersion {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"id":1,"version_code":9,"version_name":"1.1.3","add_time":"2020-01-17 16:24:20","download_link":"","comment":"当前版本","version_status":0}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * version_code : 9
         * version_name : 1.1.3
         * add_time : 2020-01-17 16:24:20
         * download_link :
         * comment : 当前版本
         * version_status : 0
         */

        private int id;
        private int version_code;
        private String version_name;
        private String add_time;
        private String download_link;
        private String comment;
        private int version_status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion_code() {
            return version_code;
        }

        public void setVersion_code(int version_code) {
            this.version_code = version_code;
        }

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getDownload_link() {
            return download_link;
        }

        public void setDownload_link(String download_link) {
            this.download_link = download_link;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getVersion_status() {
            return version_status;
        }

        public void setVersion_status(int version_status) {
            this.version_status = version_status;
        }
    }
}

package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/22------更新------
 * 视频播放地址
 */

public class VideoUrlModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"url":"http://gzgl.gelearning.net/zjywb/uploadfile/tongbushipin_xiaoxue/rjbxxsx1njxc201203.ts.mp4?auth_key=1562687617-NzoW4ZeO00000000000000000000000000000002-00000000000000000000000000000000-7b0d9ded17002fb26c31ec9a0b812a83","timestamp":1562687617}
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
         * url : http://gzgl.gelearning.net/zjywb/uploadfile/tongbushipin_xiaoxue/rjbxxsx1njxc201203.ts.mp4?auth_key=1562687617-NzoW4ZeO00000000000000000000000000000002-00000000000000000000000000000000-7b0d9ded17002fb26c31ec9a0b812a83
         * timestamp : 1562687617
         */

        private String url;
        private int timestamp;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }
}

package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/13------更新------
 * 上传单张图片
 */

public class UploadSingleImgModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"images":"http://glapp.x1298.com/uploads/20190813/b1bd367161c228ef5efb2438b05671ce.jpg"}
     */

    private int error;
    private String success;
    private int status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
         * images : http://glapp.x1298.com/uploads/20190813/b1bd367161c228ef5efb2438b05671ce.jpg
         */

        private String images;

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }
}

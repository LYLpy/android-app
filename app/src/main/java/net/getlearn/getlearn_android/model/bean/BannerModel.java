package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/1------更新------
 */

public class BannerModel {
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"type":0,"img":"http://glapp.x1298.com/uploads/20190903/731a076672c2052a72f46ec1dce22b0a.jpg","link":"6023","title":"部编版1年级上册"},{"type":1,"img":"http://glapp.x1298.com/uploads/20190903/58e239d77ec6790939291ec81c6e8061.jpg","link":"1022","title":"奥数"},{"type":3,"img":"http://glapp.x1298.com/uploads/20190919/817681bbd6de7d04255770b15c0483de.jpg","link":"1008","title":"小学作文"}]
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : 0
         * img : http://glapp.x1298.com/uploads/20190903/731a076672c2052a72f46ec1dce22b0a.jpg
         * link : 6023
         * title : 部编版1年级上册
         */

        private int type;
        private String img;
        private String link;
        private String title;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"img":"http://xxtbpy.getlearn.cn/uploads/20191021/c55871f0148806e2961745207caa8e02.jpg","link":"6023","title":"部编版1年级上册"},{"img":"http://xxtbpy.getlearn.cn/uploads/20190903/58e239d77ec6790939291ec81c6e8061.jpg","link":"1022","title":"奥数"},{"img":"http://xxtbpy.getlearn.cn/uploads/20190919/817681bbd6de7d04255770b15c0483de.jpg","link":"1008","title":"小学作文"}]
     */

//    private int error;
//    private String success;
//    private String status;
//    private String msg;
//    private List<DataBean> data;
//
//    public int getError() {
//        return error;
//    }
//
//    public void setError(int error) {
//        this.error = error;
//    }
//
//    public String getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(String success) {
//        this.success = success;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * img : http://xxtbpy.getlearn.cn/uploads/20191021/c55871f0148806e2961745207caa8e02.jpg
//         * link : 6023
//         * title : 部编版1年级上册
//         */
//
//        private String img;
//        private String link;
//        private String title;
//
//        public String getImg() {
//            return img;
//        }
//
//        public void setImg(String img) {
//            this.img = img;
//        }
//
//        public String getLink() {
//            return link;
//        }
//
//        public void setLink(String link) {
//            this.link = link;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//    }
//
//

}

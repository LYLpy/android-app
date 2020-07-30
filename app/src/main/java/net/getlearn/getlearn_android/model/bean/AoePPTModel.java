package net.getlearn.getlearn_android.model.bean;

import java.util.List;

public class AoePPTModel {
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":3,"per_page":"20","current_page":1,"last_page":1,"data":[{"special_title":"小明教科书","level_title":"小明教科书上册（12）","background":"http://gl.app.com/upload/20191025/7c5a0cfd22a54f00698faa67668d3448.png","course_id":44},{"special_title":"小学语文","level_title":"测试（上册）","background":"http://gl.app.com/upload/","course_id":20},{"special_title":"测试小明","level_title":"小明18课（测试）","background":"http://gl.app.com/upload/20191025/6e1b67cb75710834a843b67d278b9ba3.png","course_id":20}]}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 3
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"special_title":"小明教科书","level_title":"小明教科书上册（12）","background":"http://gl.app.com/upload/20191025/7c5a0cfd22a54f00698faa67668d3448.png","course_id":44},{"special_title":"小学语文","level_title":"测试（上册）","background":"http://gl.app.com/upload/","course_id":20},{"special_title":"测试小明","level_title":"小明18课（测试）","background":"http://gl.app.com/upload/20191025/6e1b67cb75710834a843b67d278b9ba3.png","course_id":20}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * special_title : 小明教科书
             * level_title : 小明教科书上册（12）
             * background : http://gl.app.com/upload/20191025/7c5a0cfd22a54f00698faa67668d3448.png
             * course_id : 44
             */

            private String special_title;
            private String level_title;
            private String background;
            private int course_id;

            public String getSpecial_title() {
                return special_title;
            }

            public void setSpecial_title(String special_title) {
                this.special_title = special_title;
            }

            public String getLevel_title() {
                return level_title;
            }

            public void setLevel_title(String level_title) {
                this.level_title = level_title;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public int getCourse_id() {
                return course_id;
            }

            public void setCourse_id(int course_id) {
                this.course_id = course_id;
            }
        }
    }


//    private int Image;
//    private String Title;
//    private String Titletow;
//
//    public AoePPTModel(int Image,String Title,String Titletow){
//        this.Image = Image;
//        this.Title = Title;
//        this.Titletow = Titletow;
//    }
//
//    public int getImage() {
//        return Image;
//    }
//
//    public void setImage(int image) {
//        Image = image;
//    }
//
//    public String getTitle() {
//        return Title;
//    }
//
//    public void setTitle(String title) {
//        Title = title;
//    }
//
//    public String getTitletow() {
//        return Titletow;
//    }
//
//    public void setTitletow(String titletow) {
//        Titletow = titletow;
//    }
}

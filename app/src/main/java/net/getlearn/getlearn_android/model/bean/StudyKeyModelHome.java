package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/7------更新------
 * 首页学习干货bean
 */

public class StudyKeyModelHome {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":1,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":1,"title":"测试","author":"作者我","icon":"/uploads/20190722/d752e66e38496a4df64e82cc7e2d2ba6.jpg","read_volume":22}]}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DatabeanX data;

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

    public DatabeanX getData() {
        return data;
    }

    public void setData(DatabeanX data) {
        this.data = data;
    }

    public static class DatabeanX {
        /**
         * total : 1
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":1,"title":"测试","author":"作者我","icon":"/uploads/20190722/d752e66e38496a4df64e82cc7e2d2ba6.jpg","read_volume":22}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<Databean> data;

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

        public List<Databean> getData() {
            return data;
        }

        public void setData(List<Databean> data) {
            this.data = data;
        }

        public static class Databean {
            /**
             * id : 1
             * title : 测试
             * author : 作者我
             * icon : /uploads/20190722/d752e66e38496a4df64e82cc7e2d2ba6.jpg
             * read_volume : 22
             */

            private int id;
            private String title;
            private String author;
            private String icon;
            private int read_volume;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getRead_volume() {
                return read_volume;
            }

            public void setRead_volume(int read_volume) {
                this.read_volume = read_volume;
            }
        }
    }
}

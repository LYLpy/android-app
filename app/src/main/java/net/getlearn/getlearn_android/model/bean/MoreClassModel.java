package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/26------更新------
 * 同步课获取更多课程列表
 */

public class MoreClassModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":9,"per_page":8,"current_page":1,"last_page":2,"data":[{"id":52,"name":"人教版小学数学1年级","icon":"http://gl.app.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png","okResCount":34,"click":9000},{"id":1312,"name":"苏教版小学数学一年级","icon":"http://gl.app.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-shujiaoban1shuxue.png","okResCount":36,"click":9000},{"id":1322,"name":"北师大版小学数学一年级","icon":"http://gl.app.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-beishida1shuxue.png","okResCount":42,"click":9000},{"id":2784,"name":"浙教版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":51,"click":9000},{"id":2822,"name":"青岛版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":20,"click":9000},{"id":2831,"name":"少儿版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":50,"click":9000},{"id":2882,"name":"冀教版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":23,"click":9000},{"id":2934,"name":"科学版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":16,"click":9000}]}
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
         * total : 9
         * per_page : 8
         * current_page : 1
         * last_page : 2
         * data : [{"id":52,"name":"人教版小学数学1年级","icon":"http://gl.app.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png","okResCount":34,"click":9000},{"id":1312,"name":"苏教版小学数学一年级","icon":"http://gl.app.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-shujiaoban1shuxue.png","okResCount":36,"click":9000},{"id":1322,"name":"北师大版小学数学一年级","icon":"http://gl.app.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-beishida1shuxue.png","okResCount":42,"click":9000},{"id":2784,"name":"浙教版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":51,"click":9000},{"id":2822,"name":"青岛版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":20,"click":9000},{"id":2831,"name":"少儿版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":50,"click":9000},{"id":2882,"name":"冀教版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":23,"click":9000},{"id":2934,"name":"科学版小学数学1年级","icon":"http://gl.app.com/uploads/","okResCount":16,"click":9000}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<Databean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
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
             * id : 52
             * name : 人教版小学数学1年级
             * icon : http://gl.app.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png
             * okResCount : 34
             * click : 9000
             */

            private int id;
            private String name;
            private String icon;
            private int okResCount;
            private int click;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getOkResCount() {
                return okResCount;
            }

            public void setOkResCount(int okResCount) {
                this.okResCount = okResCount;
            }

            public int getClick() {
                return click;
            }

            public void setClick(int click) {
                this.click = click;
            }
        }
    }
}

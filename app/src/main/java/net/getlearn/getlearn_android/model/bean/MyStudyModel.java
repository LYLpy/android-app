package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/11------更新------
 * 我的学习
 */

public class MyStudyModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":11,"per_page":"20","current_page":1,"last_page":1,"data":[{"course_id":43,"class_name":"人教版小学语文6年级上册","click":290,"icon":"http://glapp.x1298.com/uploads/","rate":7},{"course_id":40,"class_name":"人教版小学语文3年级下册","click":246,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yuwen.png","rate":6},{"course_id":6705,"class_name":"微课堂3+1小学语文6年级","click":56,"icon":"http://glapp.x1298.com/uploads/","rate":1},{"course_id":71,"class_name":"人教版小学英语6年级上册","click":86,"icon":"http://glapp.x1298.com/uploads/","rate":2},{"course_id":57,"class_name":"人教版小学数学6年级上册","click":75,"icon":"http://glapp.x1298.com/uploads/","rate":11},{"course_id":6715,"class_name":"微课堂3+1小学英语6年级","click":256,"icon":"http://glapp.x1298.com/uploads/","rate":0},{"course_id":6560,"class_name":"教科版EEC小学英语三起6年级下册","click":12,"icon":"http://glapp.x1298.com/uploads/","rate":7},{"course_id":6611,"class_name":"川教版小学英语6年级下册","click":10,"icon":"http://glapp.x1298.com/uploads/","rate":11},{"course_id":1033,"class_name":"小学奥数六年级上册","click":2,"icon":"http://glapp.x1298.com/uploads/","rate":7},{"course_id":6535,"class_name":"冀教版小学英语6年级下册","click":55,"icon":"http://glapp.x1298.com/uploads/","rate":0},{"course_id":6710,"class_name":"微课堂3+1小学数学6年级","click":21,"icon":"http://glapp.x1298.com/uploads/","rate":0}]}
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
         * total : 11
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"course_id":43,"class_name":"人教版小学语文6年级上册","click":290,"icon":"http://glapp.x1298.com/uploads/","rate":7},{"course_id":40,"class_name":"人教版小学语文3年级下册","click":246,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yuwen.png","rate":6},{"course_id":6705,"class_name":"微课堂3+1小学语文6年级","click":56,"icon":"http://glapp.x1298.com/uploads/","rate":1},{"course_id":71,"class_name":"人教版小学英语6年级上册","click":86,"icon":"http://glapp.x1298.com/uploads/","rate":2},{"course_id":57,"class_name":"人教版小学数学6年级上册","click":75,"icon":"http://glapp.x1298.com/uploads/","rate":11},{"course_id":6715,"class_name":"微课堂3+1小学英语6年级","click":256,"icon":"http://glapp.x1298.com/uploads/","rate":0},{"course_id":6560,"class_name":"教科版EEC小学英语三起6年级下册","click":12,"icon":"http://glapp.x1298.com/uploads/","rate":7},{"course_id":6611,"class_name":"川教版小学英语6年级下册","click":10,"icon":"http://glapp.x1298.com/uploads/","rate":11},{"course_id":1033,"class_name":"小学奥数六年级上册","click":2,"icon":"http://glapp.x1298.com/uploads/","rate":7},{"course_id":6535,"class_name":"冀教版小学英语6年级下册","click":55,"icon":"http://glapp.x1298.com/uploads/","rate":0},{"course_id":6710,"class_name":"微课堂3+1小学数学6年级","click":21,"icon":"http://glapp.x1298.com/uploads/","rate":0}]
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
             * course_id : 43
             * class_name : 人教版小学语文6年级上册
             * click : 290
             * icon : http://glapp.x1298.com/uploads/
             * rate : 7
             */

            private int course_id;
            private String class_name;
            private int click;
            private String icon;
            private int rate;

            public int getCourse_id() {
                return course_id;
            }

            public void setCourse_id(int course_id) {
                this.course_id = course_id;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public int getClick() {
                return click;
            }

            public void setClick(int click) {
                this.click = click;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }
        }
    }
}

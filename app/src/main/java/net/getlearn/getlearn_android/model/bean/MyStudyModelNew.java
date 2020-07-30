package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/7------更新------
 * UI改动之后，新的我的学习Model
 */

public class MyStudyModelNew {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":7,"per_page":"20","current_page":1,"last_page":1,"data":[{"course_id":54,"class_name":"人教版小学数学3年级","click":5,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3shuxue.png"},{"course_id":68,"class_name":"人教版小学英语3年级","click":4,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yingyu.png"},{"course_id":43,"class_name":"人教版小学语文6年级","click":80,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yuwen.png"},{"course_id":71,"class_name":"人教版小学英语6年级","click":23,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yingyu.png"},{"course_id":57,"class_name":"人教版小学数学6年级","click":9,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6shuxue.png"},{"course_id":42,"class_name":"人教版小学语文5年级","click":3,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban5yuwen.png"},{"course_id":2781,"class_name":"西师大版小学数学6年级上册2014版","click":8,"icon":"http://glapp.x1298.com/uploads/course/images/2019-07-15/2781.png"}]}
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
         * total : 7
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"course_id":54,"class_name":"人教版小学数学3年级","click":5,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3shuxue.png"},{"course_id":68,"class_name":"人教版小学英语3年级","click":4,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yingyu.png"},{"course_id":43,"class_name":"人教版小学语文6年级","click":80,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yuwen.png"},{"course_id":71,"class_name":"人教版小学英语6年级","click":23,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yingyu.png"},{"course_id":57,"class_name":"人教版小学数学6年级","click":9,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6shuxue.png"},{"course_id":42,"class_name":"人教版小学语文5年级","click":3,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban5yuwen.png"},{"course_id":2781,"class_name":"西师大版小学数学6年级上册2014版","click":8,"icon":"http://glapp.x1298.com/uploads/course/images/2019-07-15/2781.png"}]
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
             * course_id : 54
             * class_name : 人教版小学数学3年级
             * click : 5
             * icon : http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3shuxue.png
             */

            private int course_id;
            private String class_name;
            private int click;
            private String icon;

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
        }
    }
}

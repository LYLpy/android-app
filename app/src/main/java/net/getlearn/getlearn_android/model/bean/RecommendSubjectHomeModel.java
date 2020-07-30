package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/27------更新------
 * 首页推荐课程Model
 */

public class RecommendSubjectHomeModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":48,"per_page":6,"current_page":1,"last_page":8,"data":[{"id":1210,"name":"北师大版高中数学必修4","okResCount":38,"icon":"http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiushuxue4.png","click":100000},{"id":1302,"name":"苏教版高中语文必修三","okResCount":25,"icon":"http://gl.app.com/uploads/course/images/2017-04-26/zhongxuetongbujingjiang-shujiaobangaozhongbixiu3yuwen.png","click":100000},{"id":1214,"name":"北师大版高中英语必修3","okResCount":30,"icon":"http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiuyingyu3.png","click":100000},{"id":1209,"name":"北师大版高中数学必修3","okResCount":40,"icon":"http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiushuxue3.png","click":100000},{"id":2880,"name":"华东师大版高中历史第4分册","okResCount":19,"icon":"http://gl.app.com/uploads/","click":100000},{"id":1215,"name":"北师大版高中英语必修4","okResCount":12,"icon":"http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiuyingyu4.png","click":100000}]}
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
         * total : 48
         * per_page : 6
         * current_page : 1
         * last_page : 8
         * data : [{"id":1210,"name":"北师大版高中数学必修4","okResCount":38,"icon":"http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiushuxue4.png","click":100000},{"id":1302,"name":"苏教版高中语文必修三","okResCount":25,"icon":"http://gl.app.com/uploads/course/images/2017-04-26/zhongxuetongbujingjiang-shujiaobangaozhongbixiu3yuwen.png","click":100000},{"id":1214,"name":"北师大版高中英语必修3","okResCount":30,"icon":"http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiuyingyu3.png","click":100000},{"id":1209,"name":"北师大版高中数学必修3","okResCount":40,"icon":"http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiushuxue3.png","click":100000},{"id":2880,"name":"华东师大版高中历史第4分册","okResCount":19,"icon":"http://gl.app.com/uploads/","click":100000},{"id":1215,"name":"北师大版高中英语必修4","okResCount":12,"icon":"http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiuyingyu4.png","click":100000}]
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
             * id : 1210
             * name : 北师大版高中数学必修4
             * okResCount : 38
             * icon : http://gl.app.com/uploads/course/images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiushuxue4.png
             * click : 100000
             */

            private int id;
            private String name;
            private int okResCount;
            private String icon;
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

            public int getOkResCount() {
                return okResCount;
            }

            public void setOkResCount(int okResCount) {
                this.okResCount = okResCount;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
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

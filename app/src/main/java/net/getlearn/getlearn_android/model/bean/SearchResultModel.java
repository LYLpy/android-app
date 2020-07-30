package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 * 搜索结果
 */

public class SearchResultModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":32,"per_page":20,"current_page":1,"last_page":2,"data":[{"id":38,"name":"人教版小学语文1年级","okResCount":0,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","click":19999},{"id":52,"name":"人教版小学数学1年级","okResCount":34,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png","click":20000},{"id":66,"name":"人教版小学英语1年级","okResCount":12,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yingyu.png","click":20001},{"id":2301,"name":"1年级动漫英语","okResCount":116,"icon":"http://glapp.x1298.com/uploads/course/images/2018-08-16/5b75797993933.jpg","click":20002},{"id":2728,"name":"新世纪版小学英语1年级","okResCount":28,"icon":"http://glapp.x1298.com/uploads/","click":20003},{"id":2737,"name":"新世纪版高中英语1年级","okResCount":16,"icon":"http://glapp.x1298.com/uploads/","click":20004},{"id":2740,"name":"译林版小学英语1年级","okResCount":16,"icon":"http://glapp.x1298.com/uploads/","click":20005},{"id":2749,"name":"语文A版1年级","okResCount":30,"icon":"http://glapp.x1298.com/uploads/","click":20006},{"id":2759,"name":"语文版小学语文1年级","okResCount":71,"icon":"http://glapp.x1298.com/uploads/","click":20007},{"id":2783,"name":"长春版小学语文1年级","okResCount":40,"icon":"http://glapp.x1298.com/uploads/","click":20008},{"id":2784,"name":"浙教版小学数学1年级","okResCount":51,"icon":"http://glapp.x1298.com/uploads/","click":20009},{"id":2803,"name":"粤人版小学英语1年级","okResCount":31,"icon":"http://glapp.x1298.com/uploads/","click":20010},{"id":2810,"name":"牛津上海版小学英语1年级","okResCount":24,"icon":"http://glapp.x1298.com/uploads/","click":20011},{"id":2819,"name":"牛津上海版高中英语1年级","okResCount":47,"icon":"http://glapp.x1298.com/uploads/","click":20012},{"id":2822,"name":"青岛版小学数学1年级","okResCount":20,"icon":"http://glapp.x1298.com/uploads/","click":20013},{"id":2831,"name":"少儿版小学数学1年级","okResCount":50,"icon":"http://glapp.x1298.com/uploads/","click":20014},{"id":2838,"name":"外研版小学英语1年级","okResCount":40,"icon":"http://glapp.x1298.com/uploads/","click":20015},{"id":2860,"name":"沪科版高中物理1年级","okResCount":31,"icon":"http://glapp.x1298.com/uploads/","click":20016},{"id":2862,"name":"沪科版高中化学1年级","okResCount":24,"icon":"http://glapp.x1298.com/uploads/","click":20017},{"id":2875,"name":"华东师大版高中语文1年级","okResCount":59,"icon":"http://glapp.x1298.com/uploads/","click":20018}]}
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
         * total : 32
         * per_page : 20
         * current_page : 1
         * last_page : 2
         * data : [{"id":38,"name":"人教版小学语文1年级","okResCount":0,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","click":19999},{"id":52,"name":"人教版小学数学1年级","okResCount":34,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png","click":20000},{"id":66,"name":"人教版小学英语1年级","okResCount":12,"icon":"http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yingyu.png","click":20001},{"id":2301,"name":"1年级动漫英语","okResCount":116,"icon":"http://glapp.x1298.com/uploads/course/images/2018-08-16/5b75797993933.jpg","click":20002},{"id":2728,"name":"新世纪版小学英语1年级","okResCount":28,"icon":"http://glapp.x1298.com/uploads/","click":20003},{"id":2737,"name":"新世纪版高中英语1年级","okResCount":16,"icon":"http://glapp.x1298.com/uploads/","click":20004},{"id":2740,"name":"译林版小学英语1年级","okResCount":16,"icon":"http://glapp.x1298.com/uploads/","click":20005},{"id":2749,"name":"语文A版1年级","okResCount":30,"icon":"http://glapp.x1298.com/uploads/","click":20006},{"id":2759,"name":"语文版小学语文1年级","okResCount":71,"icon":"http://glapp.x1298.com/uploads/","click":20007},{"id":2783,"name":"长春版小学语文1年级","okResCount":40,"icon":"http://glapp.x1298.com/uploads/","click":20008},{"id":2784,"name":"浙教版小学数学1年级","okResCount":51,"icon":"http://glapp.x1298.com/uploads/","click":20009},{"id":2803,"name":"粤人版小学英语1年级","okResCount":31,"icon":"http://glapp.x1298.com/uploads/","click":20010},{"id":2810,"name":"牛津上海版小学英语1年级","okResCount":24,"icon":"http://glapp.x1298.com/uploads/","click":20011},{"id":2819,"name":"牛津上海版高中英语1年级","okResCount":47,"icon":"http://glapp.x1298.com/uploads/","click":20012},{"id":2822,"name":"青岛版小学数学1年级","okResCount":20,"icon":"http://glapp.x1298.com/uploads/","click":20013},{"id":2831,"name":"少儿版小学数学1年级","okResCount":50,"icon":"http://glapp.x1298.com/uploads/","click":20014},{"id":2838,"name":"外研版小学英语1年级","okResCount":40,"icon":"http://glapp.x1298.com/uploads/","click":20015},{"id":2860,"name":"沪科版高中物理1年级","okResCount":31,"icon":"http://glapp.x1298.com/uploads/","click":20016},{"id":2862,"name":"沪科版高中化学1年级","okResCount":24,"icon":"http://glapp.x1298.com/uploads/","click":20017},{"id":2875,"name":"华东师大版高中语文1年级","okResCount":59,"icon":"http://glapp.x1298.com/uploads/","click":20018}]
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
             * id : 38
             * name : 人教版小学语文1年级
             * okResCount : 0
             * icon : http://glapp.x1298.com/uploads/course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png
             * click : 19999
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

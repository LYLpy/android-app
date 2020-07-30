package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/2------更新------
 * 课程列表
 */

public class SubjectListModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"grade_info":{"id":40,"name":"人教版小学语文3年级下册","collection":0},"class_list":{"total":32,"per_page":"20","current_page":1,"last_page":2,"data":[{"id":161,"name":"人教版小学语文3年级下册第1课 燕子","history":0,"is_free":0},{"id":162,"name":"人教版小学语文3年级下册第2课 古诗两首（咏柳&春日）","history":0,"is_free":0},{"id":163,"name":"人教版小学语文3年级下册第3课 荷花","history":0,"is_free":1},{"id":164,"name":"人教版小学语文3年级下册第4课 珍珠泉","history":0,"is_free":1},{"id":165,"name":"人教版小学语文3年级下册第5课 翠鸟","history":0,"is_free":1},{"id":166,"name":"人教版小学语文3年级下册第6课 燕子专列","history":0,"is_free":1},{"id":167,"name":"人教版小学语文3年级下册第7课 一个小村庄的故事","history":0,"is_free":1},{"id":168,"name":"人教版小学语文3年级下册第8课 路旁的橡树","history":0,"is_free":1},{"id":169,"name":"人教版小学语文3年级下册第9课 寓言两则（亡羊补牢&南辕北辙）","history":0,"is_free":1},{"id":170,"name":"人教版小学语文3年级下册第10课 惊弓之鸟","history":0,"is_free":1},{"id":171,"name":"人教版小学语文3年级下册第11课 画杨桃","history":0,"is_free":1},{"id":172,"name":"人教版小学语文3年级下册第12课 想别人没想到的","history":0,"is_free":1},{"id":173,"name":"人教版小学语文3年级下册第13课 和时间赛跑","history":0,"is_free":1},{"id":174,"name":"人教版小学语文3年级下册第14课 检阅","history":0,"is_free":1},{"id":175,"name":"人教版小学语文3年级下册第15课 争吵","history":0,"is_free":1},{"id":176,"name":"人教版小学语文3年级下册第16课 绝招","history":0,"is_free":1},{"id":177,"name":"人教版小学语文3年级下册第17课 可贵的沉默","history":0,"is_free":1},{"id":178,"name":"人教版小学语文3年级下册第18课 她是我的朋友","history":0,"is_free":1},{"id":179,"name":"人教版小学语文3年级下册第19课 七颗钻石","history":0,"is_free":1},{"id":180,"name":"人教版小学语文3年级下册第20课 妈妈的账单","history":0,"is_free":1}]}}
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
         * grade_info : {"id":40,"name":"人教版小学语文3年级下册","collection":0}
         * class_list : {"total":32,"per_page":"20","current_page":1,"last_page":2,"data":[{"id":161,"name":"人教版小学语文3年级下册第1课 燕子","history":0,"is_free":0},{"id":162,"name":"人教版小学语文3年级下册第2课 古诗两首（咏柳&春日）","history":0,"is_free":0},{"id":163,"name":"人教版小学语文3年级下册第3课 荷花","history":0,"is_free":1},{"id":164,"name":"人教版小学语文3年级下册第4课 珍珠泉","history":0,"is_free":1},{"id":165,"name":"人教版小学语文3年级下册第5课 翠鸟","history":0,"is_free":1},{"id":166,"name":"人教版小学语文3年级下册第6课 燕子专列","history":0,"is_free":1},{"id":167,"name":"人教版小学语文3年级下册第7课 一个小村庄的故事","history":0,"is_free":1},{"id":168,"name":"人教版小学语文3年级下册第8课 路旁的橡树","history":0,"is_free":1},{"id":169,"name":"人教版小学语文3年级下册第9课 寓言两则（亡羊补牢&南辕北辙）","history":0,"is_free":1},{"id":170,"name":"人教版小学语文3年级下册第10课 惊弓之鸟","history":0,"is_free":1},{"id":171,"name":"人教版小学语文3年级下册第11课 画杨桃","history":0,"is_free":1},{"id":172,"name":"人教版小学语文3年级下册第12课 想别人没想到的","history":0,"is_free":1},{"id":173,"name":"人教版小学语文3年级下册第13课 和时间赛跑","history":0,"is_free":1},{"id":174,"name":"人教版小学语文3年级下册第14课 检阅","history":0,"is_free":1},{"id":175,"name":"人教版小学语文3年级下册第15课 争吵","history":0,"is_free":1},{"id":176,"name":"人教版小学语文3年级下册第16课 绝招","history":0,"is_free":1},{"id":177,"name":"人教版小学语文3年级下册第17课 可贵的沉默","history":0,"is_free":1},{"id":178,"name":"人教版小学语文3年级下册第18课 她是我的朋友","history":0,"is_free":1},{"id":179,"name":"人教版小学语文3年级下册第19课 七颗钻石","history":0,"is_free":1},{"id":180,"name":"人教版小学语文3年级下册第20课 妈妈的账单","history":0,"is_free":1}]}
         */

        private GradeInfobean grade_info;
        private ClassListbean class_list;

        public GradeInfobean getGrade_info() {
            return grade_info;
        }

        public void setGrade_info(GradeInfobean grade_info) {
            this.grade_info = grade_info;
        }

        public ClassListbean getClass_list() {
            return class_list;
        }

        public void setClass_list(ClassListbean class_list) {
            this.class_list = class_list;
        }

        public static class GradeInfobean {
            /**
             * id : 40
             * name : 人教版小学语文3年级下册
             * collection : 0
             */

            private int id;
            private String name;
            private int collection;//0为不收藏，1为收藏

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

            public int getCollection() {
                return collection;
            }

            public void setCollection(int collection) {
                this.collection = collection;
            }
        }

        public static class ClassListbean {
            /**
             * total : 32
             * per_page : 20
             * current_page : 1
             * last_page : 2
             * data : [{"id":161,"name":"人教版小学语文3年级下册第1课 燕子","history":0,"is_free":0},{"id":162,"name":"人教版小学语文3年级下册第2课 古诗两首（咏柳&春日）","history":0,"is_free":0},{"id":163,"name":"人教版小学语文3年级下册第3课 荷花","history":0,"is_free":1},{"id":164,"name":"人教版小学语文3年级下册第4课 珍珠泉","history":0,"is_free":1},{"id":165,"name":"人教版小学语文3年级下册第5课 翠鸟","history":0,"is_free":1},{"id":166,"name":"人教版小学语文3年级下册第6课 燕子专列","history":0,"is_free":1},{"id":167,"name":"人教版小学语文3年级下册第7课 一个小村庄的故事","history":0,"is_free":1},{"id":168,"name":"人教版小学语文3年级下册第8课 路旁的橡树","history":0,"is_free":1},{"id":169,"name":"人教版小学语文3年级下册第9课 寓言两则（亡羊补牢&南辕北辙）","history":0,"is_free":1},{"id":170,"name":"人教版小学语文3年级下册第10课 惊弓之鸟","history":0,"is_free":1},{"id":171,"name":"人教版小学语文3年级下册第11课 画杨桃","history":0,"is_free":1},{"id":172,"name":"人教版小学语文3年级下册第12课 想别人没想到的","history":0,"is_free":1},{"id":173,"name":"人教版小学语文3年级下册第13课 和时间赛跑","history":0,"is_free":1},{"id":174,"name":"人教版小学语文3年级下册第14课 检阅","history":0,"is_free":1},{"id":175,"name":"人教版小学语文3年级下册第15课 争吵","history":0,"is_free":1},{"id":176,"name":"人教版小学语文3年级下册第16课 绝招","history":0,"is_free":1},{"id":177,"name":"人教版小学语文3年级下册第17课 可贵的沉默","history":0,"is_free":1},{"id":178,"name":"人教版小学语文3年级下册第18课 她是我的朋友","history":0,"is_free":1},{"id":179,"name":"人教版小学语文3年级下册第19课 七颗钻石","history":0,"is_free":1},{"id":180,"name":"人教版小学语文3年级下册第20课 妈妈的账单","history":0,"is_free":1}]
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
                 * id : 161
                 * name : 人教版小学语文3年级下册第1课 燕子
                 * history : 0
                 * is_free : 0
                 */

                private int id;
                private String name;
                private int history;//0正常状态，1上次观看到这
                private int is_free;

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

                public int getHistory() {
                    return history;
                }

                public void setHistory(int history) {
                    this.history = history;
                }

                public int getIs_free() {
                    return is_free;
                }

                public void setIs_free(int is_free) {
                    this.is_free = is_free;
                }
            }
        }
    }
}

package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/5------更新------
 * 我的收藏
 */

public class MyColletcModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appUserCollectionList":{"total":2,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":43,"name":"人教版小学语文6年级","icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yuwen.png","countUser":1,"countClassHour":49},{"id":40,"name":"人教版小学语文3年级下册","icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yuwen.png","countUser":1,"countClassHour":32}]}}
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
         * appUserCollectionList : {"total":2,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":43,"name":"人教版小学语文6年级","icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yuwen.png","countUser":1,"countClassHour":49},{"id":40,"name":"人教版小学语文3年级下册","icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yuwen.png","countUser":1,"countClassHour":32}]}
         */

        private AppUserCollectionListbean appUserCollectionList;

        public AppUserCollectionListbean getAppUserCollectionList() {
            return appUserCollectionList;
        }

        public void setAppUserCollectionList(AppUserCollectionListbean appUserCollectionList) {
            this.appUserCollectionList = appUserCollectionList;
        }

        public static class AppUserCollectionListbean {
            /**
             * total : 2
             * per_page : 20
             * current_page : 1
             * last_page : 1
             * data : [{"id":43,"name":"人教版小学语文6年级","icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yuwen.png","countUser":1,"countClassHour":49},{"id":40,"name":"人教版小学语文3年级下册","icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yuwen.png","countUser":1,"countClassHour":32}]
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
                 * id : 43
                 * name : 人教版小学语文6年级
                 * icon : course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6yuwen.png
                 * countUser : 1
                 * countClassHour : 49
                 */

                private int id;
                private String name;
                private String icon;
                private int countUser;
                private int countClassHour;

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

                public int getCountUser() {
                    return countUser;
                }

                public void setCountUser(int countUser) {
                    this.countUser = countUser;
                }

                public int getCountClassHour() {
                    return countClassHour;
                }

                public void setCountClassHour(int countClassHour) {
                    this.countClassHour = countClassHour;
                }
            }
        }
    }
}

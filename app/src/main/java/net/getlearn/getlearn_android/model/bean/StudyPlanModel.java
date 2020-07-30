package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/9------更新------
 * 学习计划bean
 */

public class StudyPlanModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appStudyPlanList":{"total":1,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":15,"userId":4,"taskContent":"试试任务添加","closeDateTime":"2019-08-08 22:00","tipsDateTime":"2019-08-08 21:45:00","weekFirstDate":"2019-08-05","weekLastDate":"2019-08-11","isFinish":1,"tipsMode":1,"isTips":1,"createDateTime":"2019-08-08 13:58","updateDateTime":"2019-08-08 13:58:58","createDate":"2019-08-08"}]},"schedule":"0%","date":[{"params":0,"day":"2019-08-05"},{"params":0,"day":"2019-08-06"},{"params":0,"day":"2019-08-07"},{"params":1,"day":"2019-08-08"},{"params":0,"day":"2019-08-09"},{"params":0,"day":"2019-08-10"},{"params":0,"day":"2019-08-11"}]}
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
         * appStudyPlanList : {"total":1,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":15,"userId":4,"taskContent":"试试任务添加","closeDateTime":"2019-08-08 22:00","tipsDateTime":"2019-08-08 21:45:00","weekFirstDate":"2019-08-05","weekLastDate":"2019-08-11","isFinish":1,"tipsMode":1,"isTips":1,"createDateTime":"2019-08-08 13:58","updateDateTime":"2019-08-08 13:58:58","createDate":"2019-08-08"}]}
         * schedule : 0%
         * date : [{"params":0,"day":"2019-08-05"},{"params":0,"day":"2019-08-06"},{"params":0,"day":"2019-08-07"},{"params":1,"day":"2019-08-08"},{"params":0,"day":"2019-08-09"},{"params":0,"day":"2019-08-10"},{"params":0,"day":"2019-08-11"}]
         */

        private AppStudyPlanListbean appStudyPlanList;
        private String schedule;
        private List<Datebean> date;

        public AppStudyPlanListbean getAppStudyPlanList() {
            return appStudyPlanList;
        }

        public void setAppStudyPlanList(AppStudyPlanListbean appStudyPlanList) {
            this.appStudyPlanList = appStudyPlanList;
        }

        public String getSchedule() {
            return schedule;
        }

        public void setSchedule(String schedule) {
            this.schedule = schedule;
        }

        public List<Datebean> getDate() {
            return date;
        }

        public void setDate(List<Datebean> date) {
            this.date = date;
        }

        public static class AppStudyPlanListbean {
            /**
             * total : 1
             * per_page : 20
             * current_page : 1
             * last_page : 1
             * data : [{"id":15,"userId":4,"taskContent":"试试任务添加","closeDateTime":"2019-08-08 22:00","tipsDateTime":"2019-08-08 21:45:00","weekFirstDate":"2019-08-05","weekLastDate":"2019-08-11","isFinish":1,"tipsMode":1,"isTips":1,"createDateTime":"2019-08-08 13:58","updateDateTime":"2019-08-08 13:58:58","createDate":"2019-08-08"}]
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
                 * id : 15
                 * userId : 4
                 * taskContent : 试试任务添加
                 * closeDateTime : 2019-08-08 22:00
                 * tipsDateTime : 2019-08-08 21:45:00
                 * weekFirstDate : 2019-08-05
                 * weekLastDate : 2019-08-11
                 * isFinish : 1
                 * tipsMode : 1
                 * isTips : 1
                 * createDateTime : 2019-08-08 13:58
                 * updateDateTime : 2019-08-08 13:58:58
                 * createDate : 2019-08-08
                 */

                private int id;
                private int userId;
                private String taskContent;
                private String closeDateTime;
                private String tipsDateTime;
                private String weekFirstDate;
                private String weekLastDate;
                private int isFinish;
                private int tipsMode;
                private int isTips;
                private String createDateTime;
                private String updateDateTime;
                private String createDate;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getTaskContent() {
                    return taskContent;
                }

                public void setTaskContent(String taskContent) {
                    this.taskContent = taskContent;
                }

                public String getCloseDateTime() {
                    return closeDateTime;
                }

                public void setCloseDateTime(String closeDateTime) {
                    this.closeDateTime = closeDateTime;
                }

                public String getTipsDateTime() {
                    return tipsDateTime;
                }

                public void setTipsDateTime(String tipsDateTime) {
                    this.tipsDateTime = tipsDateTime;
                }

                public String getWeekFirstDate() {
                    return weekFirstDate;
                }

                public void setWeekFirstDate(String weekFirstDate) {
                    this.weekFirstDate = weekFirstDate;
                }

                public String getWeekLastDate() {
                    return weekLastDate;
                }

                public void setWeekLastDate(String weekLastDate) {
                    this.weekLastDate = weekLastDate;
                }

                public int getIsFinish() {
                    return isFinish;
                }

                public void setIsFinish(int isFinish) {
                    this.isFinish = isFinish;
                }

                public int getTipsMode() {
                    return tipsMode;
                }

                public void setTipsMode(int tipsMode) {
                    this.tipsMode = tipsMode;
                }

                public int getIsTips() {
                    return isTips;
                }

                public void setIsTips(int isTips) {
                    this.isTips = isTips;
                }

                public String getCreateDateTime() {
                    return createDateTime;
                }

                public void setCreateDateTime(String createDateTime) {
                    this.createDateTime = createDateTime;
                }

                public String getUpdateDateTime() {
                    return updateDateTime;
                }

                public void setUpdateDateTime(String updateDateTime) {
                    this.updateDateTime = updateDateTime;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }
            }
        }

        public static class Datebean {
            /**
             * params : 0
             * day : 2019-08-05
             */

            private int params;
            private String day;

            public int getParams() {
                return params;
            }

            public void setParams(int params) {
                this.params = params;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }
}

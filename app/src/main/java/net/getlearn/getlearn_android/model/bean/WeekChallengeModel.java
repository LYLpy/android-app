package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/16------更新------
 * 本周挑战
 */

public class WeekChallengeModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appWeekChallengesList":{"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"id":3,"name":"本周挑战","integral":15,"firstWeekDate":"2019-08-19","lastWeekDate":"2019-08-25","createDate":"2019-08-21 22:02:11","updateDate":"2019-08-21 22:02:11","flagUrl":"/api/sign/signThreeDayUp"},{"id":4,"name":"完成任务五节课","integral":20,"firstWeekDate":"2019-08-19","lastWeekDate":"2019-08-25","createDate":"2019-08-21 22:02:30","updateDate":"2019-08-21 22:02:30","flagUrl":"/api/sign/courseFinish"}]}}
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
         * appWeekChallengesList : {"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"id":3,"name":"本周挑战","integral":15,"firstWeekDate":"2019-08-19","lastWeekDate":"2019-08-25","createDate":"2019-08-21 22:02:11","updateDate":"2019-08-21 22:02:11","flagUrl":"/api/sign/signThreeDayUp"},{"id":4,"name":"完成任务五节课","integral":20,"firstWeekDate":"2019-08-19","lastWeekDate":"2019-08-25","createDate":"2019-08-21 22:02:30","updateDate":"2019-08-21 22:02:30","flagUrl":"/api/sign/courseFinish"}]}
         */

        private AppWeekChallengesListbean appWeekChallengesList;

        public AppWeekChallengesListbean getAppWeekChallengesList() {
            return appWeekChallengesList;
        }

        public void setAppWeekChallengesList(AppWeekChallengesListbean appWeekChallengesList) {
            this.appWeekChallengesList = appWeekChallengesList;
        }

        public static class AppWeekChallengesListbean {
            /**
             * total : 2
             * per_page : 10
             * current_page : 1
             * last_page : 1
             * data : [{"id":3,"name":"本周挑战","integral":15,"firstWeekDate":"2019-08-19","lastWeekDate":"2019-08-25","createDate":"2019-08-21 22:02:11","updateDate":"2019-08-21 22:02:11","flagUrl":"/api/sign/signThreeDayUp"},{"id":4,"name":"完成任务五节课","integral":20,"firstWeekDate":"2019-08-19","lastWeekDate":"2019-08-25","createDate":"2019-08-21 22:02:30","updateDate":"2019-08-21 22:02:30","flagUrl":"/api/sign/courseFinish"}]
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
                 * id : 3
                 * name : 本周挑战
                 * integral : 15
                 * firstWeekDate : 2019-08-19
                 * lastWeekDate : 2019-08-25
                 * createDate : 2019-08-21 22:02:11
                 * updateDate : 2019-08-21 22:02:11
                 * flagUrl : /api/sign/signThreeDayUp
                 */

                private int id;
                private String name;
                private int integral;
                private String firstWeekDate;
                private String lastWeekDate;
                private String createDate;
                private String updateDate;
                private String flagUrl;

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

                public int getIntegral() {
                    return integral;
                }

                public void setIntegral(int integral) {
                    this.integral = integral;
                }

                public String getFirstWeekDate() {
                    return firstWeekDate;
                }

                public void setFirstWeekDate(String firstWeekDate) {
                    this.firstWeekDate = firstWeekDate;
                }

                public String getLastWeekDate() {
                    return lastWeekDate;
                }

                public void setLastWeekDate(String lastWeekDate) {
                    this.lastWeekDate = lastWeekDate;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public String getUpdateDate() {
                    return updateDate;
                }

                public void setUpdateDate(String updateDate) {
                    this.updateDate = updateDate;
                }

                public String getFlagUrl() {
                    return flagUrl;
                }

                public void setFlagUrl(String flagUrl) {
                    this.flagUrl = flagUrl;
                }
            }
        }
    }
}

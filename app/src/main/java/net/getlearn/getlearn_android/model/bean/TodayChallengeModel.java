package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/16------更新------
 * 今日挑战
 */

public class TodayChallengeModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 查询成功
     * data : {"appWeekChallengesList":{"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"id":4,"name":"分享一次APP","integral":5,"challengeDate":"2019-08-21","flagUrl":"/api/sign/shareEveryday","createDate":"2019-08-21 22:01:15","updateDate":"2019-08-21 22:01:15"},{"id":5,"name":"完成一节课","integral":10,"challengeDate":"2019-08-21","flagUrl":"/api/sign/payCourse","createDate":"2019-08-21 22:01:41","updateDate":"2019-08-21 22:01:41"}]}}
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
         * appWeekChallengesList : {"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"id":4,"name":"分享一次APP","integral":5,"challengeDate":"2019-08-21","flagUrl":"/api/sign/shareEveryday","createDate":"2019-08-21 22:01:15","updateDate":"2019-08-21 22:01:15"},{"id":5,"name":"完成一节课","integral":10,"challengeDate":"2019-08-21","flagUrl":"/api/sign/payCourse","createDate":"2019-08-21 22:01:41","updateDate":"2019-08-21 22:01:41"}]}
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
             * data : [{"id":4,"name":"分享一次APP","integral":5,"challengeDate":"2019-08-21","flagUrl":"/api/sign/shareEveryday","createDate":"2019-08-21 22:01:15","updateDate":"2019-08-21 22:01:15"},{"id":5,"name":"完成一节课","integral":10,"challengeDate":"2019-08-21","flagUrl":"/api/sign/payCourse","createDate":"2019-08-21 22:01:41","updateDate":"2019-08-21 22:01:41"}]
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
                 * id : 4
                 * name : 分享一次APP
                 * integral : 5
                 * challengeDate : 2019-08-21
                 * flagUrl : /api/sign/shareEveryday
                 * createDate : 2019-08-21 22:01:15
                 * updateDate : 2019-08-21 22:01:15
                 */

                private int id;
                private String name;
                private int integral;
                private String challengeDate;
                private String flagUrl;
                private String createDate;
                private String updateDate;

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

                public String getChallengeDate() {
                    return challengeDate;
                }

                public void setChallengeDate(String challengeDate) {
                    this.challengeDate = challengeDate;
                }

                public String getFlagUrl() {
                    return flagUrl;
                }

                public void setFlagUrl(String flagUrl) {
                    this.flagUrl = flagUrl;
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
            }
        }
    }
}

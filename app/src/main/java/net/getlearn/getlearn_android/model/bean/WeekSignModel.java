package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/16------更新------
 * 本周签到情况
 */

public class WeekSignModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"week":[{"params":5,"day":"2019-08-26","integral":5},{"params":5,"day":"2019-08-27","integral":5},{"params":0,"day":"2019-08-28","integral":10},{"params":0,"day":"2019-08-29","integral":10},{"params":0,"day":"2019-08-30","integral":10},{"params":0,"day":"2019-08-31","integral":15},{"integral":20}],"userIntegral":9829,"currentDaySignIntegral":5,"isSigned":true,"signContinueDay":2}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private Databean data;

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

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * week : [{"params":5,"day":"2019-08-26","integral":5},{"params":5,"day":"2019-08-27","integral":5},{"params":0,"day":"2019-08-28","integral":10},{"params":0,"day":"2019-08-29","integral":10},{"params":0,"day":"2019-08-30","integral":10},{"params":0,"day":"2019-08-31","integral":15},{"integral":20}]
         * userIntegral : 9829
         * currentDaySignIntegral : 5
         * isSigned : true
         * signContinueDay : 2
         */

        private int userIntegral;
        private int currentDaySignIntegral;
        private boolean isSigned;
        private int signContinueDay;
        private List<Weekbean> week;

        public int getUserIntegral() {
            return userIntegral;
        }

        public void setUserIntegral(int userIntegral) {
            this.userIntegral = userIntegral;
        }

        public int getCurrentDaySignIntegral() {
            return currentDaySignIntegral;
        }

        public void setCurrentDaySignIntegral(int currentDaySignIntegral) {
            this.currentDaySignIntegral = currentDaySignIntegral;
        }

        public boolean isIsSigned() {
            return isSigned;
        }

        public void setIsSigned(boolean isSigned) {
            this.isSigned = isSigned;
        }

        public int getSignContinueDay() {
            return signContinueDay;
        }

        public void setSignContinueDay(int signContinueDay) {
            this.signContinueDay = signContinueDay;
        }

        public List<Weekbean> getWeek() {
            return week;
        }

        public void setWeek(List<Weekbean> week) {
            this.week = week;
        }

        public static class Weekbean {
            /**
             * params : 5
             * day : 2019-08-26
             * integral : 5
             */

            private int params;
            private String day;
            private int integral;

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

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }
        }
    }
}

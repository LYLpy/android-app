package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/18------更新------
 * 签到
 */

public class UserSignModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 签到成功!
     * data : {"getIntegral":5,"signContinueDay":1,"userIntegral":2076}
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
         * getIntegral : 5
         * signContinueDay : 1
         * userIntegral : 2076
         */

        private int getIntegral;
        private int signContinueDay;
        private int userIntegral;

        public int getGetIntegral() {
            return getIntegral;
        }

        public void setGetIntegral(int getIntegral) {
            this.getIntegral = getIntegral;
        }

        public int getSignContinueDay() {
            return signContinueDay;
        }

        public void setSignContinueDay(int signContinueDay) {
            this.signContinueDay = signContinueDay;
        }

        public int getUserIntegral() {
            return userIntegral;
        }

        public void setUserIntegral(int userIntegral) {
            this.userIntegral = userIntegral;
        }
    }
}

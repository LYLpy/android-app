package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/6------更新------
 * 首页--我的
 */

public class PersionalHomeModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"uid":72,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132","wechatnickname":"yang_test","isVip":1,"integral":9999,"signature":null,"study":0,"challenges":0,"sign":false}
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
         * uid : 72
         * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132
         * wechatnickname : yang_test
         * isVip : 1
         * integral : 9999
         * signature : null
         * study : 0
         * challenges : 0
         * sign : false
         */

        private int uid;
        private String headimgurl;
        private String wechatnickname;
        private int isVip;
        private int integral;
        private Object signature;
        private int study;
        private int challenges;
        private boolean sign;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getWechatnickname() {
            return wechatnickname;
        }

        public void setWechatnickname(String wechatnickname) {
            this.wechatnickname = wechatnickname;
        }

        public int getIsVip() {
            return isVip;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }

        public int getStudy() {
            return study;
        }

        public void setStudy(int study) {
            this.study = study;
        }

        public int getChallenges() {
            return challenges;
        }

        public void setChallenges(int challenges) {
            this.challenges = challenges;
        }

        public boolean isSign() {
            return sign;
        }

        public void setSign(boolean sign) {
            this.sign = sign;
        }
    }
}

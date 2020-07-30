package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/20------更新------
 * 修改手机号码
 */

public class EditPhoneModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 手机号码更改成功!
     * data : {"user":{"uid":1,"password":null,"phone":"18529434634","wechatnickname":"adc哈哈","openid":null,"sex":0,"province":null,"city":null,"country":null,"headingurl":null,"privileges":null,"unionid":null,"qqopenid":null,"figureurl":null,"token":"e724877f9ffea392fa6254b36b4b0c47","tokenTime":1564733838,"integral":1873,"invitationCode":null,"isVip":0,"reg_time":1562055244,"update_time":"2019-07-23 14:43:14"}}
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
         * user : {"uid":1,"password":null,"phone":"18529434634","wechatnickname":"adc哈哈","openid":null,"sex":0,"province":null,"city":null,"country":null,"headingurl":null,"privileges":null,"unionid":null,"qqopenid":null,"figureurl":null,"token":"e724877f9ffea392fa6254b36b4b0c47","tokenTime":1564733838,"integral":1873,"invitationCode":null,"isVip":0,"reg_time":1562055244,"update_time":"2019-07-23 14:43:14"}
         */

        private Userbean user;

        public Userbean getUser() {
            return user;
        }

        public void setUser(Userbean user) {
            this.user = user;
        }

        public static class Userbean {
            /**
             * uid : 1
             * password : null
             * phone : 18529434634
             * wechatnickname : adc哈哈
             * openid : null
             * sex : 0
             * province : null
             * city : null
             * country : null
             * headingurl : null
             * privileges : null
             * unionid : null
             * qqopenid : null
             * figureurl : null
             * token : e724877f9ffea392fa6254b36b4b0c47
             * tokenTime : 1564733838
             * integral : 1873
             * invitationCode : null
             * isVip : 0
             * reg_time : 1562055244
             * update_time : 2019-07-23 14:43:14
             */

            private int uid;
            private Object password;
            private String phone;
            private String wechatnickname;
            private Object openid;
            private int sex;
            private Object province;
            private Object city;
            private Object country;
            private Object headingurl;
            private Object privileges;
            private Object unionid;
            private Object qqopenid;
            private Object figureurl;
            private String token;
            private int tokenTime;
            private int integral;
            private Object invitationCode;
            private int isVip;
            private int reg_time;
            private String update_time;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getWechatnickname() {
                return wechatnickname;
            }

            public void setWechatnickname(String wechatnickname) {
                this.wechatnickname = wechatnickname;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getCountry() {
                return country;
            }

            public void setCountry(Object country) {
                this.country = country;
            }

            public Object getHeadingurl() {
                return headingurl;
            }

            public void setHeadingurl(Object headingurl) {
                this.headingurl = headingurl;
            }

            public Object getPrivileges() {
                return privileges;
            }

            public void setPrivileges(Object privileges) {
                this.privileges = privileges;
            }

            public Object getUnionid() {
                return unionid;
            }

            public void setUnionid(Object unionid) {
                this.unionid = unionid;
            }

            public Object getQqopenid() {
                return qqopenid;
            }

            public void setQqopenid(Object qqopenid) {
                this.qqopenid = qqopenid;
            }

            public Object getFigureurl() {
                return figureurl;
            }

            public void setFigureurl(Object figureurl) {
                this.figureurl = figureurl;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getTokenTime() {
                return tokenTime;
            }

            public void setTokenTime(int tokenTime) {
                this.tokenTime = tokenTime;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public Object getInvitationCode() {
                return invitationCode;
            }

            public void setInvitationCode(Object invitationCode) {
                this.invitationCode = invitationCode;
            }

            public int getIsVip() {
                return isVip;
            }

            public void setIsVip(int isVip) {
                this.isVip = isVip;
            }

            public int getReg_time() {
                return reg_time;
            }

            public void setReg_time(int reg_time) {
                this.reg_time = reg_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}

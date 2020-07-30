package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 * 微信登录
 */

public class WXLoginModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"uid":1,"password":null,"phone":"15913107564","wechatnickname":"yang_test","openid":"ofWWpwAzjApkfQgACl2ZKkGNBThI","sex":0,"province":"","city":"","country":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/GFRSP7jaAibx1vGvpY2we6cEqTiaKnaZ7xTGYnzQu81TOZOjfdZwectX3CfGNibmm3VBR8GhpXbn9YdJib9ibzc0rxg/132","privileges":"a:0:{}","unionid":"o9Rjh53KVyXGx84cgMj1UWvQqcrE","qqopenid":null,"figureurl":null,"token":"99308356ac74ce4c781b5008ae5e21ac","tokenTime":1567049980,"integral":0,"invitationCode":null,"isVip":0,"reg_time":1564371498,"update_time":"2019-07-29 11:39:40","select_course":{"grade_id":13,"select_course":"23@38,24@38,25@38,0@38"}}
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
         * uid : 1
         * password : null
         * phone : 15913107564
         * wechatnickname : yang_test
         * openid : ofWWpwAzjApkfQgACl2ZKkGNBThI
         * sex : 0
         * province :
         * city :
         * country :
         * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/GFRSP7jaAibx1vGvpY2we6cEqTiaKnaZ7xTGYnzQu81TOZOjfdZwectX3CfGNibmm3VBR8GhpXbn9YdJib9ibzc0rxg/132
         * privileges : a:0:{}
         * unionid : o9Rjh53KVyXGx84cgMj1UWvQqcrE
         * qqopenid : null
         * figureurl : null
         * token : 99308356ac74ce4c781b5008ae5e21ac
         * tokenTime : 1567049980
         * integral : 0
         * invitationCode : null
         * isVip : 0
         * reg_time : 1564371498
         * update_time : 2019-07-29 11:39:40
         * select_course : {"grade_id":13,"select_course":"23@38,24@38,25@38,0@38"}
         */

        private int uid;
        private Object password;
        private String phone;
        private String wechatnickname;
        private String openid;
        private int sex;
        private String province;
        private String city;
        private String country;
        private String headimgurl;
        private String privileges;
        private String unionid;
        private Object qqopenid;
        private Object figureurl;
        private String token;
        private int tokenTime;
        private int integral;
        private Object invitationCode;
        private int isVip;
        private int reg_time;
        private String update_time;
        private SelectCoursebean select_course;

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

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getPrivileges() {
            return privileges;
        }

        public void setPrivileges(String privileges) {
            this.privileges = privileges;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
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

        public SelectCoursebean getSelect_course() {
            return select_course;
        }

        public void setSelect_course(SelectCoursebean select_course) {
            this.select_course = select_course;
        }

        public static class SelectCoursebean {
            /**
             * grade_id : 13
             * select_course : 23@38,24@38,25@38,0@38
             */

            private int grade_id;
            private String select_course;

            public int getGrade_id() {
                return grade_id;
            }

            public void setGrade_id(int grade_id) {
                this.grade_id = grade_id;
            }

            public String getSelect_course() {
                return select_course;
            }

            public void setSelect_course(String select_course) {
                this.select_course = select_course;
            }
        }
    }
}

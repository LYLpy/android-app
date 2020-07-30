package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/5------更新------
 * 登录
 */

public class LoginModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"uid":1,"phone":"15913107564","wechatnickname":null,"openid":null,"sex":0,"province":null,"city":null,"country":null,"headimgurl":null,"privileges":null,"unionid":null,"token":"224f3506c83ea0a543abb6613a03bb35","integral":0,"invitationCode":null,"isVip":0,"reg_time":1562381354,"select_course":{"id":1,"uid":1,"grade_id":9,"select_course":"23@129,24@129,25@38,26@38,27@38,28@38,29@38,30@38,31@38,33@38,109@38,0@38","add_time":1563257616}}
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
         * phone : 15913107564
         * wechatnickname : null
         * openid : null
         * sex : 0
         * province : null
         * city : null
         * country : null
         * headimgurl : null
         * privileges : null
         * unionid : null
         * token : 224f3506c83ea0a543abb6613a03bb35
         * integral : 0
         * invitationCode : null
         * isVip : 0
         * reg_time : 1562381354
         * select_course : {"id":1,"uid":1,"grade_id":9,"select_course":"23@129,24@129,25@38,26@38,27@38,28@38,29@38,30@38,31@38,33@38,109@38,0@38","add_time":1563257616}
         */

        private int uid;
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
        private String token;
        private int integral;
        private String invitationCode;
        private int isVip;
        private int reg_time;
        private SelectCoursebean select_course;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
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

        public SelectCoursebean getSelect_course() {
            return select_course;
        }

        public void setSelect_course(SelectCoursebean select_course) {
            this.select_course = select_course;
        }

        public static class SelectCoursebean {
            /**
             * id : 1
             * uid : 1
             * grade_id : 9
             * select_course : 23@129,24@129,25@38,26@38,27@38,28@38,29@38,30@38,31@38,33@38,109@38,0@38
             * add_time : 1563257616
             */

            private int id;
            private int uid;
            private int grade_id;
            private String select_course;
            private int add_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

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

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }
        }
    }
}

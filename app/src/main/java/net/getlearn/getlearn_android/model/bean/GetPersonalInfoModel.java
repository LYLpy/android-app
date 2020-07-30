package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/7------更新------
 * 获取个人信息
 */

public class GetPersonalInfoModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"headimgurl":null,"wechatnickname":"小明","signature":null,"phone":"17702000302","city":"","select_course":[{"subject_id":23,"subject":"语文","version":{"id":38,"version":"人教版"}},{"subject_id":24,"subject":"数学","version":{"id":38,"version":"人教版"}},{"subject_id":25,"subject":"英语","version":{"id":38,"version":"人教版"}},{"subject_id":0,"subject":"无学科","version":{"id":38,"version":"人教版"}}],"grade":{"id":13,"option":"6年级"}}
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
         * headimgurl : null
         * wechatnickname : 小明
         * signature : null
         * phone : 17702000302
         * city :
         * select_course : [{"subject_id":23,"subject":"语文","version":{"id":38,"version":"人教版"}},{"subject_id":24,"subject":"数学","version":{"id":38,"version":"人教版"}},{"subject_id":25,"subject":"英语","version":{"id":38,"version":"人教版"}},{"subject_id":0,"subject":"无学科","version":{"id":38,"version":"人教版"}}]
         * grade : {"id":13,"option":"6年级"}
         */

        private Object headimgurl;
        private String wechatnickname;
        private String signature;
        private String phone;
        private String city;
        private Gradebean grade;
        private List<SelectCoursebean> select_course;

        public Object getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(Object headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getWechatnickname() {
            return wechatnickname;
        }

        public void setWechatnickname(String wechatnickname) {
            this.wechatnickname = wechatnickname;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Gradebean getGrade() {
            return grade;
        }

        public void setGrade(Gradebean grade) {
            this.grade = grade;
        }

        public List<SelectCoursebean> getSelect_course() {
            return select_course;
        }

        public void setSelect_course(List<SelectCoursebean> select_course) {
            this.select_course = select_course;
        }

        public static class Gradebean {
            /**
             * id : 13
             * option : 6年级
             */

            private int id;
            private String option;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }
        }

        public static class SelectCoursebean {
            /**
             * subject_id : 23
             * subject : 语文
             * version : {"id":38,"version":"人教版"}
             */

            private int subject_id;
            private String subject;
            private Versionbean version;

            public int getSubject_id() {
                return subject_id;
            }

            public void setSubject_id(int subject_id) {
                this.subject_id = subject_id;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public Versionbean getVersion() {
                return version;
            }

            public void setVersion(Versionbean version) {
                this.version = version;
            }

            public static class Versionbean {
                /**
                 * id : 38
                 * version : 人教版
                 */

                private int id;
                private String version;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }
            }
        }
    }
}

package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/29------更新------
 * 名师主页bean
 */

public class FamousTeacherIntroductionModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"id":717,"name":"彭青青","content":"许昌学院毕业，优秀青年教师，曾任职于许昌本源文化培训中心。表达能力强，上课经验丰富，能够尽快了解和深度研究自己的教学内容；热爱教学工作，喜欢讲课，喜欢和孩子相处，用心工作。","grade":"一年级","subject":"语文","image":"","inaugural_school":"许昌本源文化培训中心","graduation_school":"许昌学院","title":"优秀教师","tag":[""],"course":[{"okResCount":29,"name":"人教部编版小学语文1年级下册","course_id":6024,"icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","click":null},{"okResCount":60,"name":"人教部编版小学语文2年级上册","course_id":6025,"icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2yuwen.png","click":null}]}
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
         * id : 717
         * name : 彭青青
         * content : 许昌学院毕业，优秀青年教师，曾任职于许昌本源文化培训中心。表达能力强，上课经验丰富，能够尽快了解和深度研究自己的教学内容；热爱教学工作，喜欢讲课，喜欢和孩子相处，用心工作。
         * grade : 一年级
         * subject : 语文
         * image :
         * inaugural_school : 许昌本源文化培训中心
         * graduation_school : 许昌学院
         * title : 优秀教师
         * tag : [""]
         * course : [{"okResCount":29,"name":"人教部编版小学语文1年级下册","course_id":6024,"icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","click":null},{"okResCount":60,"name":"人教部编版小学语文2年级上册","course_id":6025,"icon":"course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2yuwen.png","click":null}]
         */

        private int id;
        private String name;
        private String content;
        private String grade;
        private String subject;
        private String image;
        private String inaugural_school;
        private String graduation_school;
        private String title;
        private List<String> tag;
        private List<Coursebean> course;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getInaugural_school() {
            return inaugural_school;
        }

        public void setInaugural_school(String inaugural_school) {
            this.inaugural_school = inaugural_school;
        }

        public String getGraduation_school() {
            return graduation_school;
        }

        public void setGraduation_school(String graduation_school) {
            this.graduation_school = graduation_school;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public List<Coursebean> getCourse() {
            return course;
        }

        public void setCourse(List<Coursebean> course) {
            this.course = course;
        }

        public static class Coursebean {
            /**
             * okResCount : 29
             * name : 人教部编版小学语文1年级下册
             * course_id : 6024
             * icon : course/images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png
             * click : null
             */

            private int okResCount;
            private String name;
            private int course_id;
            private String icon;
            private Object click;

            public int getOkResCount() {
                return okResCount;
            }

            public void setOkResCount(int okResCount) {
                this.okResCount = okResCount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCourse_id() {
                return course_id;
            }

            public void setCourse_id(int course_id) {
                this.course_id = course_id;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getClick() {
                return click;
            }

            public void setClick(Object click) {
                this.click = click;
            }
        }
    }
}

package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/2------更新------
 * 课程介绍
 */

public class SubjectIntroduceModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"teacher":[{"head_img_url":"https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=a9a7509a06f3d7ca18fb37249376d56c/d439b6003af33a87d8d517becc5c10385243b5dd.jpg","teacher":"小明","teacher_id":110,"teacher_introduce":"小明是一位高级小学教师，。。。。","position":"小学高级教师","lable":["高级小学教师","讲师","叫兽"]},{"head_img_url":"https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=a9a7509a06f3d7ca18fb37249376d56c/d439b6003af33a87d8d517becc5c10385243b5dd.jpg","teacher":"小明","teacher_id":110,"teacher_introduce":"小明是一位高级小学教师，。。。。","position":"小学高级教师","lable":["高级小学教师","讲师","叫兽"]}],"course_introduce":{"id":43,"name":"人教版小学语文6年级","content":"小学六年级同步课程借助词典阅读，让学生懂得理解词语在语言环境中的辨别词语的感情色彩。在阅读中根据课文内容及文体特点，利用导语、课后题或其他方法，学习抓住关键词句、重点句段理解课文内容，体会课文表达的思想感情，在阅读中能抓住关键词句，揣摩文章的表达顺序，初步领悟文章基本的表达方法，并培养能在交流和讨论中，敢于提出自己的看法。","okResCount":49},"free_list":[{"id":317,"orderNo":1,"name":"人教版小学语文6年级上册第1课 山中访友","is_free":0},{"id":318,"orderNo":2,"name":"人教版小学语文6年级上册第2课 山雨","is_free":0}]}
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
         * teacher : [{"head_img_url":"https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=a9a7509a06f3d7ca18fb37249376d56c/d439b6003af33a87d8d517becc5c10385243b5dd.jpg","teacher":"小明","teacher_id":110,"teacher_introduce":"小明是一位高级小学教师，。。。。","position":"小学高级教师","lable":["高级小学教师","讲师","叫兽"]},{"head_img_url":"https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=a9a7509a06f3d7ca18fb37249376d56c/d439b6003af33a87d8d517becc5c10385243b5dd.jpg","teacher":"小明","teacher_id":110,"teacher_introduce":"小明是一位高级小学教师，。。。。","position":"小学高级教师","lable":["高级小学教师","讲师","叫兽"]}]
         * course_introduce : {"id":43,"name":"人教版小学语文6年级","content":"小学六年级同步课程借助词典阅读，让学生懂得理解词语在语言环境中的辨别词语的感情色彩。在阅读中根据课文内容及文体特点，利用导语、课后题或其他方法，学习抓住关键词句、重点句段理解课文内容，体会课文表达的思想感情，在阅读中能抓住关键词句，揣摩文章的表达顺序，初步领悟文章基本的表达方法，并培养能在交流和讨论中，敢于提出自己的看法。","okResCount":49}
         * free_list : [{"id":317,"orderNo":1,"name":"人教版小学语文6年级上册第1课 山中访友","is_free":0},{"id":318,"orderNo":2,"name":"人教版小学语文6年级上册第2课 山雨","is_free":0}]
         */

        private CourseIntroducebean course_introduce;
        private List<Teacherbean> teacher;
        private List<FreeListbean> free_list;

        public CourseIntroducebean getCourse_introduce() {
            return course_introduce;
        }

        public void setCourse_introduce(CourseIntroducebean course_introduce) {
            this.course_introduce = course_introduce;
        }

        public List<Teacherbean> getTeacher() {
            return teacher;
        }

        public void setTeacher(List<Teacherbean> teacher) {
            this.teacher = teacher;
        }

        public List<FreeListbean> getFree_list() {
            return free_list;
        }

        public void setFree_list(List<FreeListbean> free_list) {
            this.free_list = free_list;
        }

        public static class CourseIntroducebean {
            /**
             * id : 43
             * name : 人教版小学语文6年级
             * content : 小学六年级同步课程借助词典阅读，让学生懂得理解词语在语言环境中的辨别词语的感情色彩。在阅读中根据课文内容及文体特点，利用导语、课后题或其他方法，学习抓住关键词句、重点句段理解课文内容，体会课文表达的思想感情，在阅读中能抓住关键词句，揣摩文章的表达顺序，初步领悟文章基本的表达方法，并培养能在交流和讨论中，敢于提出自己的看法。
             * okResCount : 49
             */

            private int id;
            private String name;
            private String content;
            private int okResCount;

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

            public int getOkResCount() {
                return okResCount;
            }

            public void setOkResCount(int okResCount) {
                this.okResCount = okResCount;
            }
        }

        public static class Teacherbean {
            /**
             * head_img_url : https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=a9a7509a06f3d7ca18fb37249376d56c/d439b6003af33a87d8d517becc5c10385243b5dd.jpg
             * teacher : 小明
             * teacher_id : 110
             * teacher_introduce : 小明是一位高级小学教师，。。。。
             * position : 小学高级教师
             * lable : ["高级小学教师","讲师","叫兽"]
             */

            private String head_img_url;
            private String teacher;
            private int teacher_id;
            private String teacher_introduce;
            private String position;
            private List<String> lable;

            public String getHead_img_url() {
                return head_img_url;
            }

            public void setHead_img_url(String head_img_url) {
                this.head_img_url = head_img_url;
            }

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }

            public int getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(int teacher_id) {
                this.teacher_id = teacher_id;
            }

            public String getTeacher_introduce() {
                return teacher_introduce;
            }

            public void setTeacher_introduce(String teacher_introduce) {
                this.teacher_introduce = teacher_introduce;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public List<String> getLable() {
                return lable;
            }

            public void setLable(List<String> lable) {
                this.lable = lable;
            }
        }

        public static class FreeListbean {
            /**
             * id : 317
             * orderNo : 1
             * name : 人教版小学语文6年级上册第1课 山中访友
             * is_free : 0
             */

            private int id;
            private int orderNo;
            private String name;
            private int is_free;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(int orderNo) {
                this.orderNo = orderNo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIs_free() {
                return is_free;
            }

            public void setIs_free(int is_free) {
                this.is_free = is_free;
            }
        }
    }
}

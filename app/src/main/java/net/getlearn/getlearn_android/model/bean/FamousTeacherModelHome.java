package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/7------更新------
 * 首页名师专栏
 */

public class FamousTeacherModelHome {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"per_page":"20","current_page":1,"last_page":1,"data":[{"id":1,"name":"高名","position":"小学高级英语教师","grade":"一年级","honor":"优秀青年教师","head":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563793129493&di=1b8a27bed61263ed8738835897e568b7&imgtype=0&src=http%3A%2F%2Fwww.ghost64.com%2Fqqtupian%2FzixunImg%2Flocal%2F2017%2F10%2F13%2F15078710168856.jpg"},{"id":2,"name":"高小名","position":"小学高级数学教师","grade":"二年级","honor":"优秀中年教师","head":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563793275288&di=6b02800d453f4c9e2ea310efc1c4b1a4&imgtype=0&src=http%3A%2F%2Fimg.tukexw.com%2Fimg%2F08edc5ee9aecd1d1.jpg"},{"id":3,"name":"小明","position":"小学高级语文教师","grade":"六年级","honor":"","head":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563793275288&di=6b02800d453f4c9e2ea310efc1c4b1a4&imgtype=0&src=http%3A%2F%2Fimg.tukexw.com%2Fimg%2F08edc5ee9aecd1d1.jpg"}]}
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
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":1,"name":"高名","position":"小学高级英语教师","grade":"一年级","honor":"优秀青年教师","head":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563793129493&di=1b8a27bed61263ed8738835897e568b7&imgtype=0&src=http%3A%2F%2Fwww.ghost64.com%2Fqqtupian%2FzixunImg%2Flocal%2F2017%2F10%2F13%2F15078710168856.jpg"},{"id":2,"name":"高小名","position":"小学高级数学教师","grade":"二年级","honor":"优秀中年教师","head":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563793275288&di=6b02800d453f4c9e2ea310efc1c4b1a4&imgtype=0&src=http%3A%2F%2Fimg.tukexw.com%2Fimg%2F08edc5ee9aecd1d1.jpg"},{"id":3,"name":"小明","position":"小学高级语文教师","grade":"六年级","honor":"","head":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563793275288&di=6b02800d453f4c9e2ea310efc1c4b1a4&imgtype=0&src=http%3A%2F%2Fimg.tukexw.com%2Fimg%2F08edc5ee9aecd1d1.jpg"}]
         */

        private String per_page;
        private int current_page;
        private int last_page;
        private List<Databean> data;

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
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
             * id : 1
             * name : 高名
             * position : 小学高级英语教师
             * grade : 一年级
             * honor : 优秀青年教师
             * head : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563793129493&di=1b8a27bed61263ed8738835897e568b7&imgtype=0&src=http%3A%2F%2Fwww.ghost64.com%2Fqqtupian%2FzixunImg%2Flocal%2F2017%2F10%2F13%2F15078710168856.jpg
             */

            private int id;
            private String name;
            private String position;
            private String grade;
            private String honor;
            private String head;

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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getHonor() {
                return honor;
            }

            public void setHonor(String honor) {
                this.honor = honor;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }
        }
    }
}

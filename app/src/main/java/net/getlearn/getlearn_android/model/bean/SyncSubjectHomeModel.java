package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/17------更新------
 * 同步课
 */

public class SyncSubjectHomeModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"classify":{"resCategoryId":96,"categName":"同步精讲"},"subject":[{"id":23,"option":"语文"},{"id":24,"option":"数学"},{"id":25,"option":"英语"}]}
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
         * classify : {"resCategoryId":96,"categName":"同步精讲"}
         * subject : [{"id":23,"option":"语文"},{"id":24,"option":"数学"},{"id":25,"option":"英语"}]
         */

        private Classifybean classify;
        private List<Subjectbean> subject;

        public Classifybean getClassify() {
            return classify;
        }

        public void setClassify(Classifybean classify) {
            this.classify = classify;
        }

        public List<Subjectbean> getSubject() {
            return subject;
        }

        public void setSubject(List<Subjectbean> subject) {
            this.subject = subject;
        }

        public static class Classifybean {
            /**
             * resCategoryId : 96
             * categName : 同步精讲
             */

            private int resCategoryId;
            private String categName;

            public int getResCategoryId() {
                return resCategoryId;
            }

            public void setResCategoryId(int resCategoryId) {
                this.resCategoryId = resCategoryId;
            }

            public String getCategName() {
                return categName;
            }

            public void setCategName(String categName) {
                this.categName = categName;
            }
        }

        public static class Subjectbean {
            /**
             * id : 23
             * option : 语文
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
    }
}

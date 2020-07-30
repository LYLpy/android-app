package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/19------更新------
 * 获取用户选择科目的版本Model
 */

public class UserSelectedVersionModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"subject_id":23,"subject":"语文","version":{"id":38,"version":"人教版"}},{"subject_id":24,"subject":"数学","version":{"id":38,"version":"人教版"}},{"subject_id":25,"subject":"英语","version":{"id":38,"version":"人教版"}},{"subject_id":0,"subject":"无学科","version":{"id":38,"version":"人教版"}}]
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private List<Databean> data;

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

    public List<Databean> getData() {
        return data;
    }

    public void setData(List<Databean> data) {
        this.data = data;
    }

    public static class Databean {
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

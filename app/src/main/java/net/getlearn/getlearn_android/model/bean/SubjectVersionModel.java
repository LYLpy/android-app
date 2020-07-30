package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/15------更新------
 * 选择版本Model
 */

public class SubjectVersionModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"id":23,"subject":"语文","version":[{"version_id":38,"version_name":"人教版"},{"version_id":129,"version_name":"人教部编版"},{"version_id":132,"version_name":"闽教版"}]},{"id":23,"subject":"语文","version":[{"version_id":38,"version_name":"人教版"},{"version_id":129,"version_name":"人教部编版"},{"version_id":132,"version_name":"闽教版"}]},{"id":23,"subject":"语文","version":[{"version_id":38,"version_name":"人教版"},{"version_id":129,"version_name":"人教部编版"},{"version_id":132,"version_name":"闽教版"}]}]
     */

    private String error;
    private String success;
    private String status;
    private String msg;
    private List<Databean> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
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
         * id : 23
         * subject : 语文
         * version : [{"version_id":38,"version_name":"人教版"},{"version_id":129,"version_name":"人教部编版"},{"version_id":132,"version_name":"闽教版"}]
         */

        private int id;
        private String subject;
        private int selectedPosition;

        public int getSelectedPosition() {
            return selectedPosition;
        }

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        private List<Versionbean> version;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public List<Versionbean> getVersion() {
            return version;
        }

        public void setVersion(List<Versionbean> version) {
            this.version = version;
        }

        public static class Versionbean {
            /**
             * version_id : 38
             * version_name : 人教版
             */

            private int version_id;
            private String version_name;

            public int getVersion_id() {
                return version_id;
            }

            public void setVersion_id(int version_id) {
                this.version_id = version_id;
            }

            public String getVersion_name() {
                return version_name;
            }

            public void setVersion_name(String version_name) {
                this.version_name = version_name;
            }
        }
    }
}

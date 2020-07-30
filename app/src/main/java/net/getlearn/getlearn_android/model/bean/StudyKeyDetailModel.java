package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/9------更新------
 */

public class StudyKeyDetailModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"id":4,"title":"学习干货11111111","author":"行行行","icon":"http://glapp.x1298.com//uploads/20190821/cb63037a6a642ec13cc1538564f283a7.png","fabulous":1,"addtime":"2019-08-21 16:29:42","content":"测试内容","read_volume":140,"status":true}
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
         * id : 4
         * title : 学习干货11111111
         * author : 行行行
         * icon : http://glapp.x1298.com//uploads/20190821/cb63037a6a642ec13cc1538564f283a7.png
         * fabulous : 1
         * addtime : 2019-08-21 16:29:42
         * content : 测试内容
         * read_volume : 140
         * status : true
         */

        private int id;
        private String title;
        private String author;
        private String icon;
        private int fabulous;
        private String addtime;
        private String content;
        private int read_volume;
        private boolean status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getFabulous() {
            return fabulous;
        }

        public void setFabulous(int fabulous) {
            this.fabulous = fabulous;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRead_volume() {
            return read_volume;
        }

        public void setRead_volume(int read_volume) {
            this.read_volume = read_volume;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}

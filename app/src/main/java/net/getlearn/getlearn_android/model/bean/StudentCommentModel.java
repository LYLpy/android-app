package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/4------更新------
 * 学员评论
 */

public class StudentCommentModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":6,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":6,"uid":4,"course_id":2781,"pid":0,"content":"哈哈哈哈哈哈哈哈哈","status":0,"manager_id":0,"type":0,"add_time":"11:55","wechatnickname":"yang_test","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132","replay":[{"id":7,"content":"额","status":0,"add_time":"15:05","wechatnickname":"yang_test","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132","manager":"系统管理员"}]}]}
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
         * total : 6
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":6,"uid":4,"course_id":2781,"pid":0,"content":"哈哈哈哈哈哈哈哈哈","status":0,"manager_id":0,"type":0,"add_time":"11:55","wechatnickname":"yang_test","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132","replay":[{"id":7,"content":"额","status":0,"add_time":"15:05","wechatnickname":"yang_test","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132","manager":"系统管理员"}]}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<Databean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

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
             * id : 6
             * uid : 4
             * course_id : 2781
             * pid : 0
             * content : 哈哈哈哈哈哈哈哈哈
             * status : 0
             * manager_id : 0
             * type : 0
             * add_time : 11:55
             * wechatnickname : yang_test
             * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132
             * replay : [{"id":7,"content":"额","status":0,"add_time":"15:05","wechatnickname":"yang_test","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132","manager":"系统管理员"}]
             */

            private int id;
            private int uid;
            private int course_id;
            private int pid;
            private String content;
            private int status;
            private int manager_id;
            private int type;
            private String add_time;
            private String wechatnickname;
            private String headimgurl;
            private List<Replaybean> replay;

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

            public int getCourse_id() {
                return course_id;
            }

            public void setCourse_id(int course_id) {
                this.course_id = course_id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getManager_id() {
                return manager_id;
            }

            public void setManager_id(int manager_id) {
                this.manager_id = manager_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getWechatnickname() {
                return wechatnickname;
            }

            public void setWechatnickname(String wechatnickname) {
                this.wechatnickname = wechatnickname;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public List<Replaybean> getReplay() {
                return replay;
            }

            public void setReplay(List<Replaybean> replay) {
                this.replay = replay;
            }

            public static class Replaybean {
                /**
                 * id : 7
                 * content : 额
                 * status : 0
                 * add_time : 15:05
                 * wechatnickname : yang_test
                 * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132
                 * manager : 系统管理员
                 */

                private int id;
                private String content;
                private int status;
                private String add_time;
                private String wechatnickname;
                private String headimgurl;
                private String manager;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }

                public String getWechatnickname() {
                    return wechatnickname;
                }

                public void setWechatnickname(String wechatnickname) {
                    this.wechatnickname = wechatnickname;
                }

                public String getHeadimgurl() {
                    return headimgurl;
                }

                public void setHeadimgurl(String headimgurl) {
                    this.headimgurl = headimgurl;
                }

                public String getManager() {
                    return manager;
                }

                public void setManager(String manager) {
                    this.manager = manager;
                }
            }
        }
    }
}

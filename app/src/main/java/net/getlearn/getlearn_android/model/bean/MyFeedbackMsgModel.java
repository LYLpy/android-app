package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/18------更新------
 * 我的反馈(对话框里面)
 */

public class MyFeedbackMsgModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"feedBackList":[{"id":294,"userId":72,"message":null,"image":"http://glapp.x1298.com/uploads/20190822/30701ec5a1b28de906bf0c4cc5daa680.jpeg","createDateTime":"2019-08-22 11:14:36","parentId":293,"phone":15913107564,"managerId":null,"userImage":"http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132"},{"id":293,"userId":72,"message":"饿得得得得","image":null,"createDateTime":"2019-08-22 11:14:32","parentId":0,"phone":15913107564,"managerId":null,"userImage":"http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132"}]}
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
        private List<FeedBackListbean> feedBackList;

        public List<FeedBackListbean> getFeedBackList() {
            return feedBackList;
        }

        public void setFeedBackList(List<FeedBackListbean> feedBackList) {
            this.feedBackList = feedBackList;
        }

        public static class FeedBackListbean {
            /**
             * id : 294
             * userId : 72
             * message : null
             * image : http://glapp.x1298.com/uploads/20190822/30701ec5a1b28de906bf0c4cc5daa680.jpeg
             * createDateTime : 2019-08-22 11:14:36
             * parentId : 293
             * phone : 15913107564
             * managerId : null
             * userImage : http://thirdwx.qlogo.cn/mmopen/vi_32/ylkcMtNWSicpRgbpnzTjO7eK232wMkzOUI0Bza1wOUeLDNUtfTVLDJQ4sVukNdzEakic32d1m0OqpWxfHyPbHguw/132
             */

            private int id;
            private int userId;
            private String message;
            private String image;
            private String createDateTime;
            private int parentId;
            private long phone;
            private String managerId;
            private String userImage;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCreateDateTime() {
                return createDateTime;
            }

            public void setCreateDateTime(String createDateTime) {
                this.createDateTime = createDateTime;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public long getPhone() {
                return phone;
            }

            public void setPhone(long phone) {
                this.phone = phone;
            }

            public String getManagerId() {
                return managerId;
            }

            public void setManagerId(String managerId) {
                this.managerId = managerId;
            }

            public String getUserImage() {
                return userImage;
            }

            public void setUserImage(String userImage) {
                this.userImage = userImage;
            }
        }
    }
}

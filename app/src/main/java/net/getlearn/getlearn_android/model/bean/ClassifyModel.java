package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/24------更新------
 * 选课界面获取分类
 */

public class ClassifyModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"resCategoryId":96,"categName":"同步精讲","orderNo":2,"icon":""},{"resCategoryId":2,"categName":"专题课堂","orderNo":2,"icon":""},{"resCategoryId":95,"categName":"微课","orderNo":4,"icon":""}]
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
         * resCategoryId : 96
         * categName : 同步精讲
         * orderNo : 2
         * icon :
         */
        private int resCategoryId;
        private String categName;
        private int orderNo;
        private String icon;

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

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}

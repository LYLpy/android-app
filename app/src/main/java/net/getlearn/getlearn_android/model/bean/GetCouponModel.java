package net.getlearn.getlearn_android.model.bean;

public class GetCouponModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 推荐优惠券
     * data : {"giftName":"39元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png","couponCurrency":39,"couponCondition":0,"id":73,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":61,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * giftName : 39元优惠券
         * icon : http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png
         * couponCurrency : 39
         * couponCondition : 0
         * id : 73
         * startCouponDateTime : 2020.01.09 00:00
         * u_gift_id : 61
         * endCouponDateTime : 2020.01.31 00:00
         * couponStatus : 1
         */

        private String giftName;
        private String icon;
        private int couponCurrency;
        private int couponCondition;
        private int id;
        private String startCouponDateTime;
        private int u_gift_id;
        private String endCouponDateTime;
        private int couponStatus;

        public String getGiftName() {
            return giftName;
        }

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getCouponCurrency() {
            return couponCurrency;
        }

        public void setCouponCurrency(int couponCurrency) {
            this.couponCurrency = couponCurrency;
        }

        public int getCouponCondition() {
            return couponCondition;
        }

        public void setCouponCondition(int couponCondition) {
            this.couponCondition = couponCondition;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStartCouponDateTime() {
            return startCouponDateTime;
        }

        public void setStartCouponDateTime(String startCouponDateTime) {
            this.startCouponDateTime = startCouponDateTime;
        }

        public int getU_gift_id() {
            return u_gift_id;
        }

        public void setU_gift_id(int u_gift_id) {
            this.u_gift_id = u_gift_id;
        }

        public String getEndCouponDateTime() {
            return endCouponDateTime;
        }

        public void setEndCouponDateTime(String endCouponDateTime) {
            this.endCouponDateTime = endCouponDateTime;
        }

        public int getCouponStatus() {
            return couponStatus;
        }

        public void setCouponStatus(int couponStatus) {
            this.couponStatus = couponStatus;
        }
    }
}

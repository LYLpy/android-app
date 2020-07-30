package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/30------更新------
 * 订单详情
 */

public class OrderDetailModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"order_id":218,"order_no":"GL201908201530291838291026","order_amount":"35.00","pay_amount":"34.00","pay_type":0,"create_time":"2019-08-20 15:30:29","pay_time":"1970-01-01 08:00:00","order_status":0,"preferential_amount":"1.00","buyer_pay_amount":"0.00","coupon_id":58,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":1}
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
         * order_id : 218
         * order_no : GL201908201530291838291026
         * order_amount : 35.00
         * pay_amount : 34.00
         * pay_type : 0
         * create_time : 2019-08-20 15:30:29
         * pay_time : 1970-01-01 08:00:00
         * order_status : 0
         * preferential_amount : 1.00
         * buyer_pay_amount : 0.00
         * coupon_id : 58
         * set_meal_name : 三个月会员套餐
         * icon : http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png
         * coupon : 1
         */

        private int order_id;
        private String order_no;
        private String order_amount;
        private String pay_amount;
        private int pay_type;
        private String create_time;
        private String pay_time;
        private int order_status;
        private String preferential_amount;
        private String buyer_pay_amount;
        private int coupon_id;
        private String set_meal_name;
        private String icon;
        private int coupon;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public String getPreferential_amount() {
            return preferential_amount;
        }

        public void setPreferential_amount(String preferential_amount) {
            this.preferential_amount = preferential_amount;
        }

        public String getBuyer_pay_amount() {
            return buyer_pay_amount;
        }

        public void setBuyer_pay_amount(String buyer_pay_amount) {
            this.buyer_pay_amount = buyer_pay_amount;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getSet_meal_name() {
            return set_meal_name;
        }

        public void setSet_meal_name(String set_meal_name) {
            this.set_meal_name = set_meal_name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }
    }
}

package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 我的订单
 */

public class MyOrderModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":36,"per_page":"20","current_page":1,"last_page":2,"data":[{"order_id":218,"order_no":"GL201908201530291838291026","order_amount":"35.00","pay_amount":"34.00","pay_type":0,"create_time":"2019-08-20 15:30:29","pay_time":0,"order_status":0,"preferential_amount":"1.00","buyer_pay_amount":"0.00","coupon_id":58,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":1},{"order_id":217,"order_no":"GL201908201504510671542932","order_amount":"34.00","pay_amount":"0.01","pay_type":1,"create_time":"2019-08-20 15:04:51","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":0},{"order_id":205,"order_no":"GL201908200939056645670658","order_amount":"0.01","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-20 09:39:05","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0},{"order_id":198,"order_no":"GL201908191712435760556492","order_amount":"34.00","pay_amount":"0.01","pay_type":0,"create_time":"2019-08-19 17:12:43","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":0},{"order_id":197,"order_no":"GL201908191712270698195092","order_amount":"35.00","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-19 17:12:27","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":0},{"order_id":196,"order_no":"GL201908191711355475912698","order_amount":"0.01","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-19 17:11:35","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0},{"order_id":195,"order_no":"GL201908191706532570360108","order_amount":"0.01","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-19 17:06:53","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0},{"order_id":194,"order_no":"GL201908191706063607525649","order_amount":"0.01","pay_amount":"0.00","pay_type":1,"create_time":"2019-08-19 17:06:06","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0},{"order_id":193,"order_no":"GL201908191703000334781086","order_amount":"0.01","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-19 17:03:00","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0}]}
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
         * total : 36
         * per_page : 20
         * current_page : 1
         * last_page : 2
         * data : [{"order_id":218,"order_no":"GL201908201530291838291026","order_amount":"35.00","pay_amount":"34.00","pay_type":0,"create_time":"2019-08-20 15:30:29","pay_time":0,"order_status":0,"preferential_amount":"1.00","buyer_pay_amount":"0.00","coupon_id":58,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":1},{"order_id":217,"order_no":"GL201908201504510671542932","order_amount":"34.00","pay_amount":"0.01","pay_type":1,"create_time":"2019-08-20 15:04:51","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":0},{"order_id":205,"order_no":"GL201908200939056645670658","order_amount":"0.01","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-20 09:39:05","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0},{"order_id":198,"order_no":"GL201908191712435760556492","order_amount":"34.00","pay_amount":"0.01","pay_type":0,"create_time":"2019-08-19 17:12:43","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":0},{"order_id":197,"order_no":"GL201908191712270698195092","order_amount":"35.00","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-19 17:12:27","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"三个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190729/9d19085351bbb8e595e10aa00999ac56.png","coupon":0},{"order_id":196,"order_no":"GL201908191711355475912698","order_amount":"0.01","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-19 17:11:35","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0},{"order_id":195,"order_no":"GL201908191706532570360108","order_amount":"0.01","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-19 17:06:53","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0},{"order_id":194,"order_no":"GL201908191706063607525649","order_amount":"0.01","pay_amount":"0.00","pay_type":1,"create_time":"2019-08-19 17:06:06","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0},{"order_id":193,"order_no":"GL201908191703000334781086","order_amount":"0.01","pay_amount":"0.00","pay_type":0,"create_time":"2019-08-19 17:03:00","pay_time":0,"order_status":0,"preferential_amount":"0.00","buyer_pay_amount":"0.00","coupon_id":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190812/550db806c6fce06809926b033d940adc.jpg","coupon":0}]
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
             * order_id : 218
             * order_no : GL201908201530291838291026
             * order_amount : 35.00
             * pay_amount : 34.00
             * pay_type : 0
             * create_time : 2019-08-20 15:30:29
             * pay_time : 0
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
}

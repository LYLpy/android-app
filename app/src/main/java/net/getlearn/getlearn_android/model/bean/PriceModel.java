package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/12------更新------
 * 售价bean
 */

public class PriceModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":5,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":184,"set_meal_name":"测试季卡","price":"69.01","vm_price":"79.00","length_time":3,"icon":"20200506/d32f46aa9462ca133de7f9db8efc6bc6.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"discount_text":"限时特惠","duration":"3个月"},{"id":183,"set_meal_name":"测试月卡","price":"0.01","vm_price":"0.02","length_time":1,"icon":"20200506/97aee621f96804528659113e5fc6c13b.png","discount_price":"0.01","begin_time":1588736517,"end_time":1620230400,"discount":1,"discount_text":"限时特惠","duration":"1个月"},{"id":6,"set_meal_name":"月卡","price":"39.00","vm_price":"69.00","length_time":1,"icon":"20190903/ee03bf354fd3b9d5dcde18658e0fe93f.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"discount_text":"限时特惠","duration":"1个月"},{"id":7,"set_meal_name":"季卡","price":"69.00","vm_price":"99.00","length_time":3,"icon":"20190903/6f96c89d1ff9b4b98a9fdc0dcce36f65.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"discount_text":"限时特惠","duration":"3个月"},{"id":8,"set_meal_name":"年卡","price":"99.01","vm_price":"169.00","length_time":24,"icon":"20190903/de828f7cbc7fb81035142085d3ef698d.png","discount_price":"99.01","begin_time":1590048507,"end_time":1590076800,"discount":1,"discount_text":"买一送一","duration":"24个月"}]}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 5
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":184,"set_meal_name":"测试季卡","price":"69.01","vm_price":"79.00","length_time":3,"icon":"20200506/d32f46aa9462ca133de7f9db8efc6bc6.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"discount_text":"限时特惠","duration":"3个月"},{"id":183,"set_meal_name":"测试月卡","price":"0.01","vm_price":"0.02","length_time":1,"icon":"20200506/97aee621f96804528659113e5fc6c13b.png","discount_price":"0.01","begin_time":1588736517,"end_time":1620230400,"discount":1,"discount_text":"限时特惠","duration":"1个月"},{"id":6,"set_meal_name":"月卡","price":"39.00","vm_price":"69.00","length_time":1,"icon":"20190903/ee03bf354fd3b9d5dcde18658e0fe93f.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"discount_text":"限时特惠","duration":"1个月"},{"id":7,"set_meal_name":"季卡","price":"69.00","vm_price":"99.00","length_time":3,"icon":"20190903/6f96c89d1ff9b4b98a9fdc0dcce36f65.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"discount_text":"限时特惠","duration":"3个月"},{"id":8,"set_meal_name":"年卡","price":"99.01","vm_price":"169.00","length_time":24,"icon":"20190903/de828f7cbc7fb81035142085d3ef698d.png","discount_price":"99.01","begin_time":1590048507,"end_time":1590076800,"discount":1,"discount_text":"买一送一","duration":"24个月"}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 184
             * set_meal_name : 测试季卡
             * price : 69.01
             * vm_price : 79.00
             * length_time : 3
             * icon : 20200506/d32f46aa9462ca133de7f9db8efc6bc6.png
             * discount_price : 0.00
             * begin_time : 0
             * end_time : 0
             * discount : 0
             * discount_text : 限时特惠
             * duration : 3个月
             */

            private int id;
            private String set_meal_name;
            private String price;
            private String vm_price;
            private int length_time;
            private String icon;
            private String discount_price;
            private int begin_time;
            private int end_time;
            private int discount;
            private String discount_text;
            private String duration;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSet_meal_name() {
                return set_meal_name;
            }

            public void setSet_meal_name(String set_meal_name) {
                this.set_meal_name = set_meal_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getVm_price() {
                return vm_price;
            }

            public void setVm_price(String vm_price) {
                this.vm_price = vm_price;
            }

            public int getLength_time() {
                return length_time;
            }

            public void setLength_time(int length_time) {
                this.length_time = length_time;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(String discount_price) {
                this.discount_price = discount_price;
            }

            public int getBegin_time() {
                return begin_time;
            }

            public void setBegin_time(int begin_time) {
                this.begin_time = begin_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getDiscount_text() {
                return discount_text;
            }

            public void setDiscount_text(String discount_text) {
                this.discount_text = discount_text;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }
        }
    }
}

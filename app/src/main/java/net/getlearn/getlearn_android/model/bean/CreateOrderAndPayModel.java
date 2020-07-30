package net.getlearn.getlearn_android.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/29------更新------
 * 下单并支付
 */

public class CreateOrderAndPayModel {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"order_id":32,"order_no":"GL201907311410233382062557","order_amount":"12.00","pay_amount":"0.00","pay_type":0,"create_time":"2019-07-31 14:10:23","pay_time":0,"order_status":0,"preferential_amount":0,"set_meal_name":"一个月会员套餐","icon":"http://glapp.x1298.com/uploads/20190724/8f79a0a461bda98141b97704d09b7db0.png","pay_info":{"return_code":"FAIL","return_msg":"appid和mch_id不匹配","success":"fail","msg":"FAIL:appid和mch_id不匹配","alipay":"alipay_sdk=alipay-sdk-php-20180705&app_id=2019073066063134&biz_content=%7B%22body%22%3A%22%5Cu4e00%5Cu4e2a%5Cu6708%5Cu4f1a%5Cu5458%5Cu5957%5Cu9910%22%2C%22subject%22%3A%22%5Cu4e00%5Cu4e2a%5Cu6708%5Cu4f1a%5Cu5458%5Cu5957%5Cu9910%22%2C%22out_trade_no%22%3A%22GL201907311631402005360258%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%2212.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fglapp.x1298.com%2Fapi%2Fpay_notify_url%2Falipay&sign_type=RSA2&timestamp=2019-07-31+16%3A31%3A41&version=1.0&sign=XhpDWLbq3rKaAaf1NvBQgbtuzl88bDj2Y9FN6KDEVDY6CN385pg71dnOZABstpUp55LLEtOE6PmiyoyVrAR0VCGxHqa2MwrDSon5SyA%2FGWY4V81pRut99uh7F8FIQ7X7OPAMYw6wLXtuzuP7UPiqROii0HZzeUB%2BYR8QFN3bi5Y1k1bKtRwAR8UhHhAWbnOSapQnDCcn3a%2B6t5B5jT3YlhWEdRZStRSLIVkqsv0o1kK6C5VR56WT66Tx%2FycYHd5%2F9LGIqKUIHDdZ8YZXxJuA35Fwp9PlxQFs%2BlfT%2B8XMIXZAqJLX1prUybsCw40oumO92L6WY098H3%2F9uLP8lbS4Zg%3D%3D"}}
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
         * order_id : 32
         * order_no : GL201907311410233382062557
         * order_amount : 12.00
         * pay_amount : 0.00
         * pay_type : 0
         * create_time : 2019-07-31 14:10:23
         * pay_time : 0
         * order_status : 0
         * preferential_amount : 0
         * set_meal_name : 一个月会员套餐
         * icon : http://glapp.x1298.com/uploads/20190724/8f79a0a461bda98141b97704d09b7db0.png
         * pay_info : {"return_code":"FAIL","return_msg":"appid和mch_id不匹配","success":"fail","msg":"FAIL:appid和mch_id不匹配","alipay":"aaa"}
         */

        private int order_id;
        private String order_no;
        private String order_amount;
        private String pay_amount;
        private int pay_type;
        private String create_time;
        private int pay_time;
        private int order_status;
        private int preferential_amount;
        private String set_meal_name;
        private String icon;
        private PayInfobean pay_info;




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

        public int getPay_time() {
            return pay_time;
        }

        public void setPay_time(int pay_time) {
            this.pay_time = pay_time;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public int getPreferential_amount() {
            return preferential_amount;
        }

        public void setPreferential_amount(int preferential_amount) {
            this.preferential_amount = preferential_amount;
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

        public PayInfobean getPay_info() {
            return pay_info;
        }

        public void setPay_info(PayInfobean pay_info) {
            this.pay_info = pay_info;
        }

        public static class PayInfobean {
            /**
             * return_code : FAIL
             * return_msg : appid和mch_id不匹配
             * success : fail
             * msg : FAIL:appid和mch_id不匹配
             * alipay : alipay_sdk=alipay-sdk-php-20180705&app_id=2019073066063134&biz_content=%7B%22body%22%3A%22%5Cu4e00%5Cu4e2a%5Cu6708%5Cu4f1a%5Cu5458%5Cu5957%5Cu9910%22%2C%22subject%22%3A%22%5Cu4e00%5Cu4e2a%5Cu6708%5Cu4f1a%5Cu5458%5Cu5957%5Cu9910%22%2C%22out_trade_no%22%3A%22GL201907311631402005360258%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%2212.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fglapp.x1298.com%2Fapi%2Fpay_notify_url%2Falipay&sign_type=RSA2&timestamp=2019-07-31+16%3A31%3A41&version=1.0&sign=XhpDWLbq3rKaAaf1NvBQgbtuzl88bDj2Y9FN6KDEVDY6CN385pg71dnOZABstpUp55LLEtOE6PmiyoyVrAR0VCGxHqa2MwrDSon5SyA%2FGWY4V81pRut99uh7F8FIQ7X7OPAMYw6wLXtuzuP7UPiqROii0HZzeUB%2BYR8QFN3bi5Y1k1bKtRwAR8UhHhAWbnOSapQnDCcn3a%2B6t5B5jT3YlhWEdRZStRSLIVkqsv0o1kK6C5VR56WT66Tx%2FycYHd5%2F9LGIqKUIHDdZ8YZXxJuA35Fwp9PlxQFs%2BlfT%2B8XMIXZAqJLX1prUybsCw40oumO92L6WY098H3%2F9uLP8lbS4Zg%3D%3D
             */

            private String return_code;
            private String return_msg;
            private String success;
            private String msg;
            private String alipay;

            private String appid;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String partnerid;
            private String prepayid;
            private int timestamp;
            private String sign;

            public String getReturn_code() {
                return return_code;
            }

            public void setReturn_code(String return_code) {
                this.return_code = return_code;
            }

            public String getReturn_msg() {
                return return_msg;
            }

            public void setReturn_msg(String return_msg) {
                this.return_msg = return_msg;
            }

            public String getSuccess() {
                return success;
            }

            public void setSuccess(String success) {
                this.success = success;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getAlipay() {
                return alipay;
            }

            public void setAlipay(String alipay) {
                this.alipay = alipay;
            }
            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }

    }
}

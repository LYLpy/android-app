package net.getlearn.getlearn_android.model.bean;

import java.util.List;

public class ExchangeCodeRecordsModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appExchangeList":{"total":4,"per_page":10,"current_page":1,"last_page":1,"data":[{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 20:29:42","type":0,"code":"s2C4712E578C","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 20:22:17","type":0,"code":"s192C21D5BD9","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 17:19:48","type":0,"code":"ss21N8FN98FF","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-15 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 16:56:06","type":0,"code":"dlAB1341A3A2","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"}]}}
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
         * appExchangeList : {"total":4,"per_page":10,"current_page":1,"last_page":1,"data":[{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 20:29:42","type":0,"code":"s2C4712E578C","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 20:22:17","type":0,"code":"s192C21D5BD9","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 17:19:48","type":0,"code":"ss21N8FN98FF","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-15 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 16:56:06","type":0,"code":"dlAB1341A3A2","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"}]}
         */

        private AppExchangeListBean appExchangeList;

        public AppExchangeListBean getAppExchangeList() {
            return appExchangeList;
        }

        public void setAppExchangeList(AppExchangeListBean appExchangeList) {
            this.appExchangeList = appExchangeList;
        }

        public static class AppExchangeListBean {
            /**
             * total : 4
             * per_page : 10
             * current_page : 1
             * last_page : 1
             * data : [{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 20:29:42","type":0,"code":"s2C4712E578C","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 20:22:17","type":0,"code":"s192C21D5BD9","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 17:19:48","type":0,"code":"ss21N8FN98FF","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-15 00:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/","use_time":"2020-01-14 16:56:06","type":0,"code":"dlAB1341A3A2","viptime":"","gift_id":76,"name":"50元优惠券","validity":"2020-01-17 00:00:00"}]
             */

            private int total;
            private int per_page;
            private int current_page;
            private int last_page;
            private List<DataBean> data;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
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
                 * icon : http://test-xxtbpy.getlearn.cn/uploads/
                 * use_time : 2020-01-14 20:29:42
                 * type : 0
                 * code : s2C4712E578C
                 * viptime :
                 * gift_id : 76
                 * name : 50元优惠券
                 * validity : 2020-01-17 00:00:00
                 */

                private String icon;
                private String use_time;
                private int type;
                private String code;
                private String viptime;
                private int gift_id;
                private String name;
                private String validity;

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getUse_time() {
                    return use_time;
                }

                public void setUse_time(String use_time) {
                    this.use_time = use_time;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getViptime() {
                    return viptime;
                }

                public void setViptime(String viptime) {
                    this.viptime = viptime;
                }

                public int getGift_id() {
                    return gift_id;
                }

                public void setGift_id(int gift_id) {
                    this.gift_id = gift_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValidity() {
                    return validity;
                }

                public void setValidity(String validity) {
                    this.validity = validity;
                }
            }
        }
    }
}

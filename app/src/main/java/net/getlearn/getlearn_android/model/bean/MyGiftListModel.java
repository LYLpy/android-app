package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/17------更新------
 * 我的礼品
 */

public class MyGiftListModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appGiftList":{"total":14,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":86,"giftName":"满39减39","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/302baa4003382dd2a3c527c36b291e7e.png","getDateTime":"2020-01-17 10:15:18","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:59","netOrEntity":0},{"id":86,"giftName":"满39减39","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/302baa4003382dd2a3c527c36b291e7e.png","getDateTime":"2020-01-17 09:59:54","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:59","netOrEntity":0},{"id":90,"giftName":"满9减9","price":2,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200117/d309d12cef1e401861165d5f43e4c337.png","getDateTime":"2020-01-17 09:52:38","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:00","netOrEntity":0},{"id":74,"giftName":"满9减1","price":10,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200110/c380692e968ef956ed0019539bcc3a31.png","getDateTime":"2020-01-13 10:15:09","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":64,"giftName":"10元优惠券","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","getDateTime":"2020-01-09 15:12:25","startCouponDateTime":"2019-09-24 09:10:55","endCouponDateTime":"2020-05-30 00:00:00","netOrEntity":0},{"id":72,"giftName":"满10减1","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/56ff0e6656fbf5d48d9e20374d986a74.png","getDateTime":"2020-01-09 15:12:20","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":73,"giftName":"39元优惠券","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png","getDateTime":"2020-01-09 15:12:18","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 23:00:00","netOrEntity":0},{"id":77,"giftName":"满200减50","price":150,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/3aa1469308dfdf4e1207dc86e2e72e97.png","getDateTime":"2020-01-09 15:12:16","startCouponDateTime":"2020-01-11 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":64,"giftName":"10元优惠券","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","getDateTime":null,"startCouponDateTime":"2019-09-24 09:10:55","endCouponDateTime":"2020-05-30 00:00:00","netOrEntity":0}]}}
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
         * appGiftList : {"total":14,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":86,"giftName":"满39减39","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/302baa4003382dd2a3c527c36b291e7e.png","getDateTime":"2020-01-17 10:15:18","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:59","netOrEntity":0},{"id":86,"giftName":"满39减39","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/302baa4003382dd2a3c527c36b291e7e.png","getDateTime":"2020-01-17 09:59:54","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:59","netOrEntity":0},{"id":90,"giftName":"满9减9","price":2,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200117/d309d12cef1e401861165d5f43e4c337.png","getDateTime":"2020-01-17 09:52:38","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:00","netOrEntity":0},{"id":74,"giftName":"满9减1","price":10,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200110/c380692e968ef956ed0019539bcc3a31.png","getDateTime":"2020-01-13 10:15:09","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":64,"giftName":"10元优惠券","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","getDateTime":"2020-01-09 15:12:25","startCouponDateTime":"2019-09-24 09:10:55","endCouponDateTime":"2020-05-30 00:00:00","netOrEntity":0},{"id":72,"giftName":"满10减1","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/56ff0e6656fbf5d48d9e20374d986a74.png","getDateTime":"2020-01-09 15:12:20","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":73,"giftName":"39元优惠券","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png","getDateTime":"2020-01-09 15:12:18","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 23:00:00","netOrEntity":0},{"id":77,"giftName":"满200减50","price":150,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/3aa1469308dfdf4e1207dc86e2e72e97.png","getDateTime":"2020-01-09 15:12:16","startCouponDateTime":"2020-01-11 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":64,"giftName":"10元优惠券","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","getDateTime":null,"startCouponDateTime":"2019-09-24 09:10:55","endCouponDateTime":"2020-05-30 00:00:00","netOrEntity":0}]}
         */

        private AppGiftListBean appGiftList;

        public AppGiftListBean getAppGiftList() {
            return appGiftList;
        }

        public void setAppGiftList(AppGiftListBean appGiftList) {
            this.appGiftList = appGiftList;
        }

        public static class AppGiftListBean {
            /**
             * total : 14
             * per_page : 20
             * current_page : 1
             * last_page : 1
             * data : [{"id":86,"giftName":"满39减39","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/302baa4003382dd2a3c527c36b291e7e.png","getDateTime":"2020-01-17 10:15:18","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:59","netOrEntity":0},{"id":86,"giftName":"满39减39","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/302baa4003382dd2a3c527c36b291e7e.png","getDateTime":"2020-01-17 09:59:54","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:59","netOrEntity":0},{"id":90,"giftName":"满9减9","price":2,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200117/d309d12cef1e401861165d5f43e4c337.png","getDateTime":"2020-01-17 09:52:38","startCouponDateTime":"2020-01-16 00:00:00","endCouponDateTime":"2020-01-17 23:59:00","netOrEntity":0},{"id":74,"giftName":"满9减1","price":10,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200110/c380692e968ef956ed0019539bcc3a31.png","getDateTime":"2020-01-13 10:15:09","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":64,"giftName":"10元优惠券","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","getDateTime":"2020-01-09 15:12:25","startCouponDateTime":"2019-09-24 09:10:55","endCouponDateTime":"2020-05-30 00:00:00","netOrEntity":0},{"id":72,"giftName":"满10减1","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/56ff0e6656fbf5d48d9e20374d986a74.png","getDateTime":"2020-01-09 15:12:20","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":73,"giftName":"39元优惠券","price":1,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png","getDateTime":"2020-01-09 15:12:18","startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-31 23:00:00","netOrEntity":0},{"id":77,"giftName":"满200减50","price":150,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/3aa1469308dfdf4e1207dc86e2e72e97.png","getDateTime":"2020-01-09 15:12:16","startCouponDateTime":"2020-01-11 00:00:00","endCouponDateTime":"2020-01-31 00:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":76,"giftName":"50元优惠券","price":200,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/39d2eaab76a62ed3a0e8561518923612.png","getDateTime":null,"startCouponDateTime":"2020-01-09 00:00:00","endCouponDateTime":"2020-01-18 11:00:00","netOrEntity":0},{"id":64,"giftName":"10元优惠券","price":5,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","getDateTime":null,"startCouponDateTime":"2019-09-24 09:10:55","endCouponDateTime":"2020-05-30 00:00:00","netOrEntity":0}]
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
                 * id : 86
                 * giftName : 满39减39
                 * price : 1
                 * icon : http://test-xxtbpy.getlearn.cn/uploads/20200116/302baa4003382dd2a3c527c36b291e7e.png
                 * getDateTime : 2020-01-17 10:15:18
                 * startCouponDateTime : 2020-01-16 00:00:00
                 * endCouponDateTime : 2020-01-17 23:59:59
                 * netOrEntity : 0
                 */

                private int id;
                private String giftName;
                private int price;
                private String icon;
                private String getDateTime;
                private String startCouponDateTime;
                private String endCouponDateTime;
                private int netOrEntity;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getGiftName() {
                    return giftName;
                }

                public void setGiftName(String giftName) {
                    this.giftName = giftName;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getGetDateTime() {
                    return getDateTime;
                }

                public void setGetDateTime(String getDateTime) {
                    this.getDateTime = getDateTime;
                }

                public String getStartCouponDateTime() {
                    return startCouponDateTime;
                }

                public void setStartCouponDateTime(String startCouponDateTime) {
                    this.startCouponDateTime = startCouponDateTime;
                }

                public String getEndCouponDateTime() {
                    return endCouponDateTime;
                }

                public void setEndCouponDateTime(String endCouponDateTime) {
                    this.endCouponDateTime = endCouponDateTime;
                }

                public int getNetOrEntity() {
                    return netOrEntity;
                }

                public void setNetOrEntity(int netOrEntity) {
                    this.netOrEntity = netOrEntity;
                }
            }
        }
    }
}

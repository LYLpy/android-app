package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/5------更新------
 * 我的优惠券
 */

public class MyCouponModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appGiftList":{"total":6,"per_page":"20","current_page":1,"last_page":1,"data":[{"giftName":"39元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png","couponCurrency":39,"couponCondition":0,"id":73,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":61,"endCouponDateTime":"2020.01.31 23:00","couponStatus":1,"status":1},{"giftName":"满200减50","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/3aa1469308dfdf4e1207dc86e2e72e97.png","couponCurrency":50,"couponCondition":200,"id":77,"startCouponDateTime":"2020.01.11 00:00","u_gift_id":60,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0},{"giftName":"10元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","couponCurrency":10,"couponCondition":20,"id":64,"startCouponDateTime":"2019.09.24 09:10","u_gift_id":63,"endCouponDateTime":"2020.05.30 00:00","couponStatus":1,"status":0},{"giftName":"10元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","couponCurrency":10,"couponCondition":20,"id":64,"startCouponDateTime":"2019.09.24 09:10","u_gift_id":79,"endCouponDateTime":"2020.05.30 00:00","couponStatus":1,"status":0},{"giftName":"满10减1","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/56ff0e6656fbf5d48d9e20374d986a74.png","couponCurrency":1,"couponCondition":10,"id":72,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":62,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0},{"giftName":"满9减1","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200110/c380692e968ef956ed0019539bcc3a31.png","couponCurrency":1,"couponCondition":9,"id":74,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":71,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0}]}}
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
         * appGiftList : {"total":6,"per_page":"20","current_page":1,"last_page":1,"data":[{"giftName":"39元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png","couponCurrency":39,"couponCondition":0,"id":73,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":61,"endCouponDateTime":"2020.01.31 23:00","couponStatus":1,"status":1},{"giftName":"满200减50","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/3aa1469308dfdf4e1207dc86e2e72e97.png","couponCurrency":50,"couponCondition":200,"id":77,"startCouponDateTime":"2020.01.11 00:00","u_gift_id":60,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0},{"giftName":"10元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","couponCurrency":10,"couponCondition":20,"id":64,"startCouponDateTime":"2019.09.24 09:10","u_gift_id":63,"endCouponDateTime":"2020.05.30 00:00","couponStatus":1,"status":0},{"giftName":"10元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","couponCurrency":10,"couponCondition":20,"id":64,"startCouponDateTime":"2019.09.24 09:10","u_gift_id":79,"endCouponDateTime":"2020.05.30 00:00","couponStatus":1,"status":0},{"giftName":"满10减1","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/56ff0e6656fbf5d48d9e20374d986a74.png","couponCurrency":1,"couponCondition":10,"id":72,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":62,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0},{"giftName":"满9减1","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200110/c380692e968ef956ed0019539bcc3a31.png","couponCurrency":1,"couponCondition":9,"id":74,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":71,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0}]}
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
             * total : 6
             * per_page : 20
             * current_page : 1
             * last_page : 1
             * data : [{"giftName":"39元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png","couponCurrency":39,"couponCondition":0,"id":73,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":61,"endCouponDateTime":"2020.01.31 23:00","couponStatus":1,"status":1},{"giftName":"满200减50","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/3aa1469308dfdf4e1207dc86e2e72e97.png","couponCurrency":50,"couponCondition":200,"id":77,"startCouponDateTime":"2020.01.11 00:00","u_gift_id":60,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0},{"giftName":"10元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","couponCurrency":10,"couponCondition":20,"id":64,"startCouponDateTime":"2019.09.24 09:10","u_gift_id":63,"endCouponDateTime":"2020.05.30 00:00","couponStatus":1,"status":0},{"giftName":"10元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/f4d83d223720b78aa9f508c02748fe6d.png","couponCurrency":10,"couponCondition":20,"id":64,"startCouponDateTime":"2019.09.24 09:10","u_gift_id":79,"endCouponDateTime":"2020.05.30 00:00","couponStatus":1,"status":0},{"giftName":"满10减1","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200109/56ff0e6656fbf5d48d9e20374d986a74.png","couponCurrency":1,"couponCondition":10,"id":72,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":62,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0},{"giftName":"满9减1","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200110/c380692e968ef956ed0019539bcc3a31.png","couponCurrency":1,"couponCondition":9,"id":74,"startCouponDateTime":"2020.01.09 00:00","u_gift_id":71,"endCouponDateTime":"2020.01.31 00:00","couponStatus":1,"status":0}]
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
                 * giftName : 39元优惠券
                 * icon : http://test-xxtbpy.getlearn.cn/uploads/20200115/b4a29bd4b6cce5c4b481da84dd7e00a4.png
                 * couponCurrency : 39
                 * couponCondition : 0
                 * id : 73
                 * startCouponDateTime : 2020.01.09 00:00
                 * u_gift_id : 61
                 * endCouponDateTime : 2020.01.31 23:00
                 * couponStatus : 1
                 * status : 1
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
                private int status;

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

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}

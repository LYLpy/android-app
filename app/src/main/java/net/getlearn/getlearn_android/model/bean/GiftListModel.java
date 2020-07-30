package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/8/6------更新------
 * 礼品列表
 */

public class GiftListModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appGiftList":{"total":1,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":57,"giftName":"1","price":1,"icon":"20190726/996186759b843b5a4f90240bcec45171.png","count":1}]},"userIntegral":999999}
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
         * appGiftList : {"total":1,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":57,"giftName":"1","price":1,"icon":"20190726/996186759b843b5a4f90240bcec45171.png","count":1}]}
         * userIntegral : 999999
         */

        private AppGiftListbean appGiftList;
        private int userIntegral;

        public AppGiftListbean getAppGiftList() {
            return appGiftList;
        }

        public void setAppGiftList(AppGiftListbean appGiftList) {
            this.appGiftList = appGiftList;
        }

        public int getUserIntegral() {
            return userIntegral;
        }

        public void setUserIntegral(int userIntegral) {
            this.userIntegral = userIntegral;
        }

        public static class AppGiftListbean {
            /**
             * total : 1
             * per_page : 20
             * current_page : 1
             * last_page : 1
             * data : [{"id":57,"giftName":"1","price":1,"icon":"20190726/996186759b843b5a4f90240bcec45171.png","count":1}]
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
                 * id : 57
                 * giftName : 1
                 * price : 1
                 * icon : 20190726/996186759b843b5a4f90240bcec45171.png
                 * count : 1
                 */

                private int id;
                private String giftName;
                private int price;
                private String icon;
                private int count;

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

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }
            }
        }
    }
}

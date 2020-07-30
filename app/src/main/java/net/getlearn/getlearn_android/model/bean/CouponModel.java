package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/24------更新------
 */

public class CouponModel {
    private String describe;//优惠券描述
    private String termOfValidity;//有效期
    private String par;//面值

    public CouponModel(){
    }

    public CouponModel(String describe, String termOfValidity, String par) {
        this.describe = describe;
        this.termOfValidity = termOfValidity;
        this.par = par;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTermOfValidity() {
        return termOfValidity;
    }

    public void setTermOfValidity(String termOfValidity) {
        this.termOfValidity = termOfValidity;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }
}

package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/14------更新------
 */

public class NewTaskModel {
    private int imgId;
    private String day;
    private String time;

    public NewTaskModel(int imgId, String day, String time) {
        this.imgId = imgId;
        this.day = day;
        this.time = time;
    }
    public NewTaskModel() {
    }
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

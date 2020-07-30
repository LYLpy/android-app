package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 */

public class StudyKeyModel {

    private String imageUrl;//图片url
    private String title;//标题，课程名称
    private String views;//已有多少人学习
    public StudyKeyModel() {
    }

    public StudyKeyModel(String imageUrl, String title,String views) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.views = views;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}

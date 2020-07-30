package net.getlearn.getlearn_android.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/21------更新------
 * 常见问题Lv的数据模型
 */

public class CommonQuestionModel {

    private String mTitle;
    private String mContent;
    public CommonQuestionModel() {
    }
    public CommonQuestionModel(String title, String content) {
        mTitle = title;
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}

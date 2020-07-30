package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/19------更新------
 */

public class SelectVersionModel {

    //第一级版本，如语文教材版本、数学教材版本等
    private String firstVersion;
    //第二级版本，如人教版、苏教版等
    private List<String> secondVersions;
    //已经选择了哪个版本
    private String selectVersion;
    //二级版本当前位置
    private int seconcVersionPosition = 0;
    public SelectVersionModel(){
    }
    public SelectVersionModel(String firstVersion,String selectVersion,List<String> list){
        this.firstVersion = firstVersion;
        this.selectVersion = selectVersion;
        this.secondVersions = list;
        this.selectVersion = list.get(0);
    }
    public int getSeconcVersionPosition() {
        return seconcVersionPosition;
    }

    public void setSeconcVersionPosition(int seconcVersionPosition) {
        this.seconcVersionPosition = seconcVersionPosition;
    }


    public String getFirstVersion() {
        return firstVersion;
    }

    public void setFirstVersion(String firstVersion) {
        this.firstVersion = firstVersion;
    }

    public List<String> getSecondVersions() {
        return secondVersions;
    }

    public void setSecondVersions(List<String> secondVersions) {
        this.secondVersions = secondVersions;
    }

    public String getSelectVersion() {
        return selectVersion;
    }

    public void setSelectVersion(String selectVersion) {
        this.selectVersion = selectVersion;
    }
}

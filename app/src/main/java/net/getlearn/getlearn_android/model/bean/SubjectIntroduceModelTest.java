package net.getlearn.getlearn_android.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/2------更新------
 */

public class SubjectIntroduceModelTest {
    private String subjectName;//课程名称
    private String subjectAmount;//课时
    private String teacher;//主讲人，老师
    private String teacherIntroduce;//教师介绍
    private String subjectIntroduce;//课程介绍
    private List<SubjectListModel> subjectList;

//    ===================================================================================================================
    public SubjectIntroduceModelTest() {
    }
    public SubjectIntroduceModelTest(String subjectName,
                                     String subjectAmount,
                                     String teacher,
                                     String teacherIntroduce,
                                     String subjectIntroduce,
                                     List<SubjectListModel> subjectList) {
        this.subjectName = subjectName;
        this.subjectAmount = subjectAmount;
        this.teacher = teacher;
        this.teacherIntroduce = teacherIntroduce;
        this.subjectIntroduce = subjectIntroduce;
        this.subjectList = subjectList;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectAmount() {
        return subjectAmount;
    }

    public void setSubjectAmount(String subjectAmount) {
        this.subjectAmount = subjectAmount;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherIntroduce() {
        return teacherIntroduce;
    }

    public void setTeacherIntroduce(String teacherIntroduce) {
        this.teacherIntroduce = teacherIntroduce;
    }

    public String getSubjectIntroduce() {
        return subjectIntroduce;
    }

    public void setSubjectIntroduce(String subjectIntroduce) {
        this.subjectIntroduce = subjectIntroduce;
    }

    public List<SubjectListModel> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectListModel> subjectList) {
        this.subjectList = subjectList;
    }
}

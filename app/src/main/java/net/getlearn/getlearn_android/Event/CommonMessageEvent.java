package net.getlearn.getlearn_android.Event;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/7/6------更新------
 * EventBus通用消息载体
 */

public class CommonMessageEvent {
    private int type;//类型
    private String content;//内容

    public CommonMessageEvent(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

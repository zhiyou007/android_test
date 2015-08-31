package com.wzm.abc.bean;

import java.util.List;

/**
 * Created by zhiyou007 on 2015/8/31.
 */
public class WxHotList {
    private String status;
    private String mesg;
    private List<WxHotList> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public List<WxHotList> getList() {
        return list;
    }

    public void setList(List<WxHotList> list) {
        this.list = list;
    }
}

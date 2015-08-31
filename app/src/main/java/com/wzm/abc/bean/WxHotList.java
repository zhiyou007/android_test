package com.wzm.abc.bean;

import java.util.ArrayList;

/**
 * Created by zhiyou007 on 2015/8/31.
 */
public class WxHotList {
    private String status;
    private String mesg;
    private ArrayList<WxHotItem> list;

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

    public ArrayList<WxHotItem> getList() {
        return list;
    }

    public void setList(ArrayList<WxHotItem> list) {
        this.list = list;
    }
}

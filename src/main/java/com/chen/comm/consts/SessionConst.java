package com.chen.comm.consts;

public enum SessionConst{
    UID("uid");

    SessionConst(String s) {
        this.s=s;
    }
    private String s;
    public String getValue(){
        return s;
    }
}
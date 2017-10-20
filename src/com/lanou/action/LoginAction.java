package com.lanou.action;

import com.lanou.domain.Staff;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by dllo on 17/10/20.
 */
public class LoginAction extends ActionSupport implements ModelDriven<Staff>{

    private Staff staff = new Staff();
    private String code;


    public String login(){
        return SUCCESS;
    }

    public String validateLogin(){
        String code1 = (String) ActionContext.getContext().getApplication().get("code");
        if (!code1.equalsIgnoreCase(code)){
            addActionError("验证码不正确!");
        }
        return SUCCESS;
    }

    @Override
    public Staff getModel() {
        return staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package com.lanou.util;

import com.lanou.vcode.utils.VerifyCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import org.apache.struts2.ServletActionContext;

import java.awt.image.BufferedImage;

/**
 * Created by dllo on 17/10/20.
 */
public class CodeImgResult implements Result {

    @Override
    public void execute(ActionInvocation actionInvocation) throws Exception {
        VerifyCode code = new VerifyCode();
        BufferedImage img = code.getImage();
        ActionContext.getContext().getApplication().put("code", code.getText());
        System.out.println("code " + code.getText());
        VerifyCode.output(img, ServletActionContext.getResponse().getOutputStream());
    }
}

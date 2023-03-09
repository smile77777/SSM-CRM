package com.ljc.crm.settings.web.controller;

import com.ljc.crm.commons.contants.Contants;
import com.ljc.crm.commons.pojo.ReturnObject;
import com.ljc.crm.commons.utils.DateUtils;
import com.ljc.crm.settings.pojo.User;
import com.ljc.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //封装成为map
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service方法查询用户
        User user = userService.queryUserByLoginActAndPwd(map);
        //根据查询结果，生成相应信息
        ReturnObject returnObject=new ReturnObject();
        if(user==null){
            //登陆失败，id or pwd 出错
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("id or pwd 出错");
        }else {
            //判断是否合法
            if(DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //身份过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("身份过期");
            }else if("0".equals(user.getLockState())){
                //状态被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登陆失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，ip受限");
            }else {
                //登陆成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

                //把对象保存到session中
                session.setAttribute(Contants.SESSION_USER,user);

                //判断是否记住密码
                if("true".equals(isRemPwd)){
                    Cookie c1 = new Cookie("loginAct", user.getLoginAct());
                    c1.setMaxAge(10*24*60*60);//10天的秒数
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd", user.getLoginPwd());
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                }else {
                    Cookie c1 = new Cookie("loginAct", "1");
                    c1.setMaxAge(0);//10天的秒数
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd", "1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }
            }
        }
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        Cookie c1 = new Cookie("loginAct", "1");
        c1.setMaxAge(0);//10天的秒数
        response.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd", "1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        session.invalidate();
        return "redirect:/";
    }
}

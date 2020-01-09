/**
 * 公司：DLUT
 * 文件名：UserController
 * 作者：haibing
 * 时间：2020/1/4 12:39
 * 描述：
 */

package com.hundsun.xone.ssm.controller;

import com.hundsun.xone.ssm.dao.support.ResultInfo;
import com.hundsun.xone.ssm.entity.User;
import com.hundsun.xone.ssm.service.impl.UserServiceImpl;
import com.hundsun.xone.ssm.util.Md5Util;
import com.hundsun.xone.ssm.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Controller()
@RequestMapping("/user")
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String hello(HttpServletRequest request, HttpServletResponse response){

        ApplicationContext context = SpringContextUtil.getApplicationContext();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanDefinitionNames.length);
        for (String beanName : beanDefinitionNames) {
            System.out.println("beanName: " + beanName);
        }
        return "addUser";
    }

    @RequestMapping(value = "/queryUserById", method = RequestMethod.POST)
    public String queryUserById(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId){
        User user = userService.queryUserById(userId);

        request.getSession().setAttribute("user", user);
        System.out.println(user.toString());
        return "listUserInfo";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        String encode = request.getServletContext().getInitParameter("encode");
        request.setCharacterEncoding(encode);
        response.setContentType("text/html;charset=" + encode);
        User user = new User();

        user.setUserId(request.getParameter("userId"));
        user.setUserName(request.getParameter("userName"));
        user.setUserPwd(Md5Util.getEncryptedPwd(request.getParameter("userPwd")));
        user.setUserStatus(request.getParameter("userStatus").charAt(0));
        user.setUserType(request.getParameter("userType").charAt(0));
        user.setRemark(request.getParameter("remark"));
        user.setLoginFlag(request.getParameter("loginFlag").charAt(0));
        request.getSession().setAttribute("user",user);

        if (!(StringUtils.isNotEmpty(user.getUserId()) && StringUtils.isNotEmpty(user.getUserName()))){
            response.getWriter().write("用户名或用户id不能为空");
        }
        if (userService.existingUser(user.getUserId())){
            response.getWriter().write("当前用户已注册。。");
        }
        ResultInfo resultInfo = userService.addUser(user);
        System.out.println("添加新用户："+user.toString());

        return "menu";
//        response.getWriter().write(resultInfo.getOpRemark());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException {
        String encode = request.getServletContext().getInitParameter("encode");
        request.setCharacterEncoding(encode);
        response.setContentType("text/html;charset=" + encode);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.queryUserByName(username);

        if (user == null) {
            response.getWriter().write("不存在该用户,请注册");
        } else{
            System.out.println("存在登录用户：" + user.toString());
            String passwordDb = user.getUserPwd();
            if ( (passwordDb.length()!=56 && passwordDb.equals(password)) || Md5Util.validPassword(password,passwordDb)) {
                if (user.getUserPwd().length()!= 56){
                    user.setUserPwd(Md5Util.getEncryptedPwd(password));
                    user.setLoginFlag('1');
                    userService.updateUser(user);
                    System.out.println("密码加密成功");
                }
                request.getSession().setAttribute("user",user);
                    /*request.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(request, response);*/
                    return "menu";
              /*  String contextPath = req.getServletContext().getContextPath();
                resp.sendRedirect(contextPath + "/user/queryUserById");*/
            } else {
                response.getWriter().write("用户密码错误，请重新输入");
               /* request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);*/

            }
        }
        return "error";
    }
}

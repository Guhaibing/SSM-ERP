/**
 * 公司：DLUT
 * 文件名：UserController
 * 作者：haibing
 * 时间：2020/1/4 12:39
 * 描述：
 */

package com.hundsun.xone.ssm.controller;

import com.hundsun.xone.ssm.entity.User;
import com.hundsun.xone.ssm.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/queryUserById", method = RequestMethod.POST)
    public String queryUserById(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId){
        User user = userService.queryUserById(userId);
        ServletContext context = request.getServletContext();
        context.setAttribute("user", user);
        System.out.println(user.toString());
        return "listUserInfo";
    }

    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String encode = request.getServletContext().getInitParameter("encode");
        request.setCharacterEncoding(encode);
        response.setContentType("text/html;charset=" + encode);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.queryUserByName(username);
        /*    User user = userDAO.queryUserByName(username);*/

        if (user == null) {
            response.getWriter().write("不存在该用户,请注册");
        } else{
            System.out.println("存在登录用户：" + user.toString());
            if (user.getUserPwd().equals(password)) {
                    request.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(request, response);
                    /*return "menu";*/
              /*  String contextPath = req.getServletContext().getContextPath();
                resp.sendRedirect(contextPath + "/user/queryUserById");*/
            } else {
                response.getWriter().write("用户密码错误，请重新输入");
               /* request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);*/
            }
        }
    }
}

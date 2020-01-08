/**
 * 公司：DLUT
 * 文件名：RegisterServlet
 * 作者：haibing
 * 时间：2020/1/5 12:58
 * 描述：
 */

package com.hundsun.xone.ssm.servlet;

import com.hundsun.xone.ssm.dao.UserDAO;
import com.hundsun.xone.ssm.dao.impl.UserDAOImpl;
import com.hundsun.xone.ssm.entity.User;
import com.hundsun.xone.ssm.service.impl.UserServiceImpl;
import com.hundsun.xone.ssm.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web.xml中已配置，不需要注解
 */
/*@WebServlet(urlPatterns = "/login")*/
public class LoginServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

//    @Autowired
   /* private UserDAOImpl userDAO;*/
   /* private UserServiceImpl userDAO;*/
    @Autowired
    private UserDAOImpl userDAO;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String encode = req.getServletContext().getInitParameter("encode");
        req.setCharacterEncoding(encode);
        resp.setContentType("text/html;charset=" + encode);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        userDAO = userDAO == null ? SpringContextUtil.getApplicationContext().getBean(UserDAOImpl.class) : userDAO;

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-mybatis.xml");

        userDAO = userDAO == null ? context.getBean(UserDAOImpl.class) : userDAO;
        if (userDAO == null){
            resp.getWriter().write("userDAO 报空");
        }
        /*User user = userDAO.selectUserByUserName(username);
    *//*    User user = userDAO.queryUserByName(username);*//*

        if (user == null) {
            resp.getWriter().write("不存在该用户,请注册");
        } else{
            System.out.println("存在登录用户：" + user.toString());
            if (user.getUserPwd().equals(password)) {
                req.getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req, resp);
              *//*  String contextPath = req.getServletContext().getContextPath();
                resp.sendRedirect(contextPath + "/user/queryUserById");*//*
            } else {
                resp.getWriter().write("用户密码错误，请重新输入");
            }
        }*/
    }
}

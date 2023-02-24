package lsh.disk.controller;

import com.alibaba.fastjson.JSON;
import lsh.disk.pojo.User;
import lsh.disk.service.UserService;
import lsh.disk.utils.HadoopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public String login(String userName, String password, Model model, HttpSession session,HttpServletRequest request){
         User user = null;
         if(request.getParameter("name")!=null){
             userName = request.getParameter("name");
         }
         if(request.getParameter("pwd")!=null){
             password = request.getParameter("pwd");
        }

        try {
            user = userService.login(userName,password);
            if(user!=null){
                session.setAttribute("uname",userName);
                model.addAttribute("msg","登陆成功！");
                //HadoopUtils.mkdir(userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg","登陆失败，出现异常:"+e.getMessage());
        }
        return JSON.toJSONString(user);
    }

    @RequestMapping("/register")
    @ResponseBody
    public String add(String userName, String password, Model model,HttpServletRequest request, HttpSession session){
        Integer n = null;
        if(request.getParameter("userName")!=null){
            userName = request.getParameter("userName");
            System.out.println(userName);
        }
        if(request.getParameter("password")!=null){
            password = request.getParameter("password");
            System.out.println(password);
        }
        try {
            n =userService.add(userName,password);
            System.out.println(n);
            boolean f =HadoopUtils.mkdirAll(userName);
            session.setAttribute("uname",userName);
            //user = userService.login(name,pwd);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return JSON.toJSONString(n);
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        try {
            HadoopUtils.closeFS(); //关闭集群
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }
}

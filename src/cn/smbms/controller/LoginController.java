package cn.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

@Controller
public class LoginController {
	@Resource(name="userService")
	private UserService service;
	
	@RequestMapping(value="/login.html")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="/dologin.html")
	public String dologin(HttpServletRequest request,@RequestParam String userCode,@RequestParam String userPassword){
		HttpSession session = request.getSession();
		User user = service.userCodeExist(userCode);
		if(user!=null){
			if(user.getUserPassword().equals(userPassword)){
				session.setAttribute(Constants.USER_SESSION, user);
				return "redirect:/sys/main.html";	
			}else{
				throw new RuntimeException("密码不正确！");
			}
		}else{
			throw new RuntimeException("用户名不存在！");
		}
	}
	
	@RequestMapping(value="/sys/main.html")
	public String main(HttpSession session){
		return "frame";
	}
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session){
		if(session.getAttribute(Constants.USER_SESSION)!=null){
			session.removeAttribute(Constants.USER_SESSION);
		}
		return "login";
	}
}

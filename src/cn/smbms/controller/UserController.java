package cn.smbms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/sys/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "roleService")
	private RoleService roleService;

	@RequestMapping(value = "/userlist.html")
	public String getUseList(Model model, @RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole) {
		List<User> uselist = new ArrayList<User>();
		List<Role> rolelist = new ArrayList<Role>();
		Integer _roleid = null;
		int pageSize = Constants.pageSize;
		int currentPageNo = 1;
		
		if (pageIndex != null) {
			currentPageNo = Integer.parseInt(pageIndex);
		}
		if (queryUserName == null) {
			queryUserName = "";
		}
		
		if (queryUserRole != null && !queryUserRole.equals("")&&!queryUserRole.equals("0")) {
			_roleid = Integer.parseInt(queryUserRole);
		}
		int count = userService.getUseCount(queryUserName, _roleid);
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(currentPageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		int pageCount = page.getTotalPageCount();
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > pageCount){
			currentPageNo = pageCount;
		}
		uselist = userService.getUseList(queryUserName, _roleid, currentPageNo, pageSize);
		rolelist = roleService.getRoleList();
		model.addAttribute("userList", uselist);
		model.addAttribute("roleList", rolelist);
		model.addAttribute("queryUserName", queryUserName);
		//model.addAttribute("", queryUserRole);
		model.addAttribute("currentPageNo", currentPageNo);
		model.addAttribute("totalPageCount", pageCount);
		model.addAttribute("totalCount", count);
		
		return "/user/userlist";
	}

	@RequestMapping(value = "/useradd.html")
	public String jumpUseAdd(Model model) {
		return "/user/useradd";
	}
	
	
	@RequestMapping(value="/rolelist.json",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public List<Role> getRoleList(){
		return roleService.getRoleList();
	}

}

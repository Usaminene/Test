package cn.smbms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.smbms.service.role.RoleService;

@Controller
@RequestMapping("/sys/role")
public class RoleController {
	
	@Resource(name="roleService")
	private RoleService service;
	

}

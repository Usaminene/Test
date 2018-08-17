package cn.smbms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.smbms.service.bill.BillService;

@Controller
@RequestMapping("/sys/bill")
public class BillController {
	
	@Resource(name="billService")
	private BillService service;
	
	

}

package cn.smbms.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.smbms.pojo.Provider;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/sys/provider")
public class ProviderController {
	
	@Resource(name="providerService")
	private ProviderService service;
	
	@RequestMapping("/providerlist.html")
	public String getProList(Model model,
			@RequestParam(value = "queryProCode", required = false) String queryProCode,
			@RequestParam(value = "queryProName", required = false) String queryProName,
			@RequestParam(value = "pageIndex", required = false) String pageIndex){
		int pageSize = Constants.pageSize;
		int currentPageNo = 1;
		if(queryProCode==null){
			queryProCode ="";
		}
		if(queryProName==null){
			queryProName="";
		}
		if(pageIndex!=null){
			currentPageNo = Integer.parseInt(pageIndex);
		}
		
		List<Provider> list = new ArrayList<Provider>();
		int count = service.getProCount(queryProCode, queryProName);
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(currentPageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		int pageCount = page.getTotalPageCount();
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > pageCount) {
			currentPageNo = pageCount;
		}
		list = service.getProList(queryProCode,queryProName,currentPageNo, pageSize);
		model.addAttribute("providerList", list);
		model.addAttribute("queryProCode", queryProCode);
		model.addAttribute("queryProName", queryProName);
		model.addAttribute("totalPageCount", pageCount);
		model.addAttribute("totalCount", count);
		model.addAttribute("currentPageNo", currentPageNo);
		return "provider/providerlist";
	}
	
	@RequestMapping("provideradd.html")
	public String jumpProAdd(){
		return "provider/provideradd";
	}
	@RequestMapping(value="/procodetest",method=RequestMethod.GET)
	@ResponseBody
	public Object getProCode(@RequestParam String proCode){
		HashMap<String, String> map = new HashMap<String, String>();
		int result = service.getProCodeExist(proCode);
		if(result<=0){
			map.put("result","true");
		}else if(result>0){
			map.put("result","false");
		}
		return JSONArray.toJSONString(map);
	}
}

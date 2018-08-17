package cn.smbms.service.user;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;

public interface UserService {
	
	public int getUseCount(String userName,Integer userRole);
	
	public User userCodeExist(String userCode);
	
	public List<User> getUseList(String userName,Integer userRole,int currentPageNo,int pageSize);
}

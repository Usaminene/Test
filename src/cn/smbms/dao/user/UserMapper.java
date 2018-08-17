package cn.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;

public interface UserMapper {

	public User userCodeExist(@Param("userCode") String userCode);
	
	public int getUseCount(@Param("userName")String userName, @Param("userRole")Integer userRole);

	public List<User> getUseList(@Param("userName")String userName, @Param("userRole")Integer userRole,
			@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
}

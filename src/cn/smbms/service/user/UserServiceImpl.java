package cn.smbms.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userMapper")
	private UserMapper mapper;
	


	@Override
	public User userCodeExist(String userCode) {
		return mapper.userCodeExist(userCode);
	}

	@Override
	public List<User> getUseList(String userName, Integer userRole, int currentPageNo, int pageSize) {
		currentPageNo = (currentPageNo-1) * pageSize;
		return mapper.getUseList(userName, userRole, currentPageNo, pageSize);
	}

	@Override
	public int getUseCount(String userName, Integer userRole) {
		return mapper.getUseCount(userName, userRole);
	}

}

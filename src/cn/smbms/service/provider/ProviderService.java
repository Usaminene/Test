package cn.smbms.service.provider;

import java.util.List;


import cn.smbms.pojo.Provider;

public interface ProviderService {

	public Integer getProCodeExist(String proCode);
	
	public int getProCount(String proCode,String proName);
	
	public List<Provider> getProList(String proCode,String proName,int currentPageNo, int pageSize);
}

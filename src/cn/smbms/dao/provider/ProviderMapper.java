package cn.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;

public interface ProviderMapper {

	public int getProCodeExist(@Param("proCode") String proCode);
	
	public int getProCount(@Param("proCode") String proCode, @Param("proName") String proName);

	public List<Provider> getProList(@Param("proCode") String proCode, @Param("proName") String proName,
			@Param("currentPageNo") int currentPageNo, @Param("pageSize") int pageSize);
	
	
}

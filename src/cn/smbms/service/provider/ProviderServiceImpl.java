package cn.smbms.service.provider;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

	@Resource(name="providerMapper")
	private ProviderMapper mapper;
	
	
	@Override
	public Integer getProCodeExist(String proCode) {
		return mapper.getProCodeExist(proCode);
	}


	@Override 
	public List<Provider> getProList(String proCode, String proName,int currentPageNo, int pageSize) {
		currentPageNo = (currentPageNo-1)*pageSize;
		return mapper.getProList(proCode, proName, currentPageNo, pageSize);
	}


	@Override
	public int getProCount(String proCode, String proName) {
		return mapper.getProCount(proCode, proName);
	}

}

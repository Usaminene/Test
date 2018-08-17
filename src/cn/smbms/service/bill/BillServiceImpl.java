package cn.smbms.service.bill;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillMapper;

@Service("billService")
public class BillServiceImpl implements BillService {
	
	@Resource(name="billMapper")
	private BillMapper mapper;

}

package org.ljw.rest.service.impl;

import java.util.List;

import org.ljw.rest.mapper.TbContentMapper;
import org.ljw.rest.pojo.TbContent;
import org.ljw.rest.pojo.TbContentExample;
import org.ljw.rest.service.TbContentService;
import org.ljw.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbContentServiceImpl implements TbContentService {

	@Autowired
	private TbContentMapper tbContentMapper; 
	
	@Override
	public FjnyResult getTbContentList(Long cid) {
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(cid);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		
		return FjnyResult.ok(list);
	}

}

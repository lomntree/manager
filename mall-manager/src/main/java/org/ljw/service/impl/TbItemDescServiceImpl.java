package org.ljw.service.impl;

import org.ljw.mapper.TbItemDescMapper;
import org.ljw.pojo.TbItemDesc;
import org.ljw.service.TbItemDescService;
import org.ljw.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TbItemDescServiceImpl implements TbItemDescService {

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Override
	public FjnyResult getTbItemDesc(Long itemId) {
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);

		return FjnyResult.ok(itemDesc);
	}


	 
}

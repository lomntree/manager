package org.ljw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ljw.mapper.TbItemCatMapper;
import org.ljw.pojo.TbItemCat;
import org.ljw.pojo.TbItemCatExample;
import org.ljw.pojo.TbItemCatExample.Criteria;
/*import org.ljw.mapper.TbItemCatMapper;
import org.ljw.pojo.TbItemCat;
import org.ljw.pojo.TbItemCatExample;
import org.ljw.pojo.TbItemCatExample.Criteria;*/
import org.ljw.service.TbItemCatService;
import org.ljw.utils.EasyUITRreeNodeBean;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {

	@Autowired
	TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EasyUITRreeNodeBean> getTbItemCatList(Long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCatList=tbItemCatMapper.selectByExample(example);
		List<EasyUITRreeNodeBean> list=new ArrayList<EasyUITRreeNodeBean>();
		for (TbItemCat tbItemCat : tbItemCatList) {
			EasyUITRreeNodeBean node=new EasyUITRreeNodeBean();
			node.setId(tbItemCat.getId());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			node.setText(tbItemCat.getName());
			list.add(node);
		}
		return list;
	}

}
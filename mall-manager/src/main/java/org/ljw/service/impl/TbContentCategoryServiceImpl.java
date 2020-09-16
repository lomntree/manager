package org.ljw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ljw.mapper.TbContentCategoryMapper;
import org.ljw.pojo.TbContentCategory;
import org.ljw.pojo.TbContentCategoryExample;
import org.ljw.pojo.TbContentCategoryExample.Criteria;
import org.ljw.service.TbContentCategoryService;
import org.ljw.utils.EasyUITRreeNodeBean;
import org.ljw.utils.ExceptionUtil;
import org.ljw.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
		
		
	
	@Override
	public List<EasyUITRreeNodeBean> getTbContentCategoryList(long parentId) {
		TbContentCategoryExample example =new TbContentCategoryExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> selectByExample = tbContentCategoryMapper.selectByExample(example);
		List<EasyUITRreeNodeBean> lists =new ArrayList<EasyUITRreeNodeBean>();
		for (TbContentCategory tbContentCategory : selectByExample) {
			EasyUITRreeNodeBean e =new EasyUITRreeNodeBean(tbContentCategory.getId(),
					tbContentCategory.getName(),
					tbContentCategory.getIsParent() ? "closed":"open");
					
			lists.add(e);
		}
		return lists;
	
	}
   @Override
   public FjnyResult createNode(Long parentId,String name) {
	   try {
		TbContentCategory node =new TbContentCategory();
		node.setParentId(parentId);
		node.setName(name);
		node.setIsParent(false);
		node.setSortOrder(1);
		node.setStatus(1);
		node.setCreated(new Date());
		node.setUpdated(new Date());
		tbContentCategoryMapper.insert(node);
		TbContentCategory parentNode =tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		return FjnyResult.ok(node);
	} catch (Exception e) {
		return FjnyResult.build(500, ExceptionUtil.getStackTrace(e));
	}
	
   }
}

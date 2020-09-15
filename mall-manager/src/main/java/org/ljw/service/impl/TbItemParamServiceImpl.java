package org.ljw.service.impl;

import java.util.Date;
import java.util.List;

import org.ljw.mapper.TbItemCatMapper;

import org.ljw.mapper.TbItemMapper;
import org.ljw.mapper.TbItemParamMapper;
import org.ljw.pojo.TbItem;
import org.ljw.pojo.TbItemCat;

import org.ljw.pojo.TbItemExample;
import org.ljw.pojo.TbItemParam;
import org.ljw.pojo.TbItemParamExample;
import org.ljw.pojo.TbItemParamExample.Criteria;
import org.ljw.service.TbItemParamService;
import org.ljw.service.TbItemService;
import org.ljw.utils.EasyUIDataGridResult;
import org.ljw.utils.ExceptionUtil;
import org.ljw.utils.FjnyResult;
import org.ljw.utils.IdRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class TbItemParamServiceImpl implements TbItemParamService {
    
	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	
	@Autowired 
	private TbItemCatMapper tbItemCatMapper;
    @Override
	public EasyUIDataGridResult getTbItemParamList(Integer page,Integer rows) {
    	TbItemParamExample example =new TbItemParamExample();  	
		PageHelper.startPage(page,rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example );
		for(TbItemParam tbItemParam :list) {
				String tbItemCatName=getItemCatName(tbItemParam.getItemCatId());
				tbItemParam.setItemCatName(tbItemCatName);
			
		}
		PageInfo<TbItemParam> pageInfo= new PageInfo<>(list);
		long total=pageInfo.getTotal();
		EasyUIDataGridResult dataGrid = new EasyUIDataGridResult(total,list);
		return dataGrid;
	}
     public String getItemCatName(Long cid) {
    	 
    	 TbItemCat tbItemcat=tbItemCatMapper.selectByPrimaryKey(cid);
    	 
    	 return tbItemcat.getName();
     }
	@Override
	public FjnyResult checkParam(Long itemCatId) {
		try {
			
			TbItemParamExample example =new TbItemParamExample();
			Criteria createCriteria =example.createCriteria();
			createCriteria.andItemCatIdEqualTo(itemCatId);
			List<TbItemParam> withBLOBs =tbItemParamMapper.selectByExampleWithBLOBs(example);
			if(null==withBLOBs ||withBLOBs.isEmpty()) {
				return FjnyResult.ok();
			}
			return FjnyResult.ok(withBLOBs.get(0));
		} catch (Exception e) {
			return FjnyResult.build(500,ExceptionUtil.getStackTrace(e)); 
		}
	}
	@Override
	public FjnyResult addItemParam(Long cid, String paramData) {
		try {
			TbItemParam record =new TbItemParam();
			   record.setItemCatId(cid);
			   record.setParamData(paramData);
			   record.setUpdated(new Date());
			   record.setCreated(new Date());
			   tbItemParamMapper.insert(record);
			return FjnyResult.ok();	
		} catch (Exception e) {
			return FjnyResult.build(500,ExceptionUtil.getStackTrace(e)); 
		}
	}	
		
	
	

}

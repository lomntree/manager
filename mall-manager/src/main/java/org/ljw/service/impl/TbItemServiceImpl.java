package org.ljw.service.impl;

import java.util.Date;
import java.util.List;
import org.ljw.mapper.TbItemDescMapper;
import org.ljw.mapper.TbItemMapper;
import org.ljw.mapper.TbItemParamItemMapper;
import org.ljw.pojo.TbItem;
import org.ljw.pojo.TbItemDesc;
import org.ljw.pojo.TbItemExample;
import org.ljw.pojo.TbItemExample.Criteria;
import org.ljw.pojo.TbItemParamItem;
import org.ljw.service.TbItemService;
import org.ljw.utils.EasyUIDataGridResult;
import org.ljw.utils.FjnyResult;
import org.ljw.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class TbItemServiceImpl implements TbItemService {
    
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
    @Override
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows) {
		
		PageHelper.startPage(page,rows);
		TbItemExample example =new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andStatusNotEqualTo((byte)3);
		List<TbItem> list=tbItemMapper.selectByExample(example);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		PageInfo<TbItem> pageInfo= new PageInfo<>(list);
		long total=pageInfo.getTotal();
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult(total,list);
		return easyUIDataGridResult;
	}
    
    
    
   @Override
   public FjnyResult saveItem(TbItem tbItem,String desc,String itemparams) {
	   Date date=new Date();
	   long genItemId =IDUtils.genItemId();
	   tbItem.setId(genItemId);
	   tbItem.setStatus((byte)1);
	   tbItem.setCreated(date);
	   tbItem.setUpdated(date);
	   int a=tbItemMapper.insertSelective(tbItem);
       
	   if(a<0) {
		   return FjnyResult.build(500,"添加商品失败");
	   }
	   //商品描述添加
	   TbItemDesc tbItemDesc=new TbItemDesc();
	   tbItemDesc.setItemId(tbItem.getId());
	   tbItemDesc.setItemDesc(desc);
	   tbItemDesc.setCreated(date);
	   tbItemDesc.setUpdated(date);
	   tbItemDescMapper.insertSelective(tbItemDesc);
	   
	   
	   //商品规格数据添加
	   TbItemParamItem record=new TbItemParamItem();
	   record.setItemId(genItemId);
	   record.setParamData(itemparams);
	   record.setCreated(date);
	   record.setUpdated(date);
	   tbItemParamItemMapper.insert(record);
	   return FjnyResult.ok();
   }
   @Override
   public FjnyResult updateTbItem(TbItem tbItem,String desc) {
	   tbItem.setUpdated(new Date());
	   tbItemMapper.updateByPrimaryKeySelective(tbItem);
	   TbItemDesc record =new TbItemDesc();
	   record.setItemId(tbItem.getId());
	   record.setUpdated(new Date());
	   record.setItemDesc(desc);
	  tbItemDescMapper.updateByPrimaryKeySelective(record);
	  return FjnyResult.ok();
   }
   @Override
   public FjnyResult deleteItem(List<Long> ids) {
	
	   try {
		   TbItem record =new TbItem();   
		   record.setStatus((byte)3);
		   TbItemExample example =new TbItemExample();
		   example.createCriteria().andIdIn(ids);
		   tbItemMapper.updateByExampleSelective(record, example);
	} catch (Exception e) {
		return FjnyResult.build(500,"删除失败");
	}
	   
	   return FjnyResult.ok();

   }



@Override
public FjnyResult statusItem(List<Long> ids) {
	   try {
		   TbItem record =new TbItem();   
		   record.setStatus((byte)1);
		   TbItemExample example =new TbItemExample();
		   example.createCriteria().andIdIn(ids);
		   tbItemMapper.updateByExampleSelective(record, example);
	} catch (Exception e) {
		return FjnyResult.build(500,"上架失败");
	}
	   
	   return FjnyResult.ok();

	
}
@Override
public FjnyResult soldTbItem(List<Long> ids) {
	   try {
		   TbItem record =new TbItem();   
		   record.setStatus((byte)2);
		   TbItemExample example =new TbItemExample();
		   example.createCriteria().andIdIn(ids);
		   tbItemMapper.updateByExampleSelective(record, example);
	} catch (Exception e) {
		return FjnyResult.build(500,"下架失败");
	}
	   
	   return FjnyResult.ok();

	
}


}

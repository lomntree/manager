package org.ljw.service;

import java.util.List;

import org.ljw.pojo.TbItem;
import org.ljw.utils.EasyUIDataGridResult;
import org.ljw.utils.FjnyResult;

public interface TbItemService {
	// 获取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page, Integer rows);

	// 添加商品列表
	public FjnyResult saveItem(TbItem tbItem, String desc, String itemparams);

     //更新商品
	public FjnyResult updateTbItem(TbItem tbItem, String desc);
     //删除商品
	public FjnyResult deleteItem(List<Long> ids);
      //上架架商品
	public FjnyResult statusItem(List<Long> ids);
       //下架商品
	public FjnyResult soldTbItem(List<Long> ids);

	

}

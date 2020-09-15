package org.ljw.controller;

import java.util.List;


import org.ljw.pojo.TbItem;
import org.ljw.service.TbItemCatService;
import org.ljw.service.TbItemDescService;
import org.ljw.service.TbItemService;
import org.ljw.utils.EasyUIDataGridResult;
import org.ljw.utils.EasyUITRreeNodeBean;
import org.ljw.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class TbItemController {

	@Autowired
	public TbItemService tbItemService;
	@Autowired
	public TbItemCatService tbItemCatService;
	@Autowired
	public TbItemDescService tbItemDescService;
	
	@RequestMapping("/getItem")
	@ResponseBody
	public EasyUIDataGridResult getTbItemList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer rows) {
		return tbItemService.getTbItemList(page, rows);
 
	}
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ResponseBody
	public FjnyResult saveTbItem(TbItem tbItem,String desc,String itemParams) {
		System.out.println("========saveTbItem=======" +itemParams);
		return tbItemService.saveItem(tbItem,desc,itemParams);
		
	}
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUITRreeNodeBean> getItemCatList(@RequestParam(value = "id",defaultValue = "0") long parentId) {
		System.out.println("parentId:" + parentId);
		return tbItemCatService.getTbItemCatList(parentId);
	}
	
	
	
	@RequestMapping("/query/item-desc/{id}")
	@ResponseBody
	public FjnyResult getTbItemDesc(@PathVariable Long id) {
		return tbItemDescService.getTbItemDesc(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public FjnyResult updateTbItem(TbItem tbItem,String desc) {
		return tbItemService.updateTbItem(tbItem,desc);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public FjnyResult deleteItem(@RequestParam("ids")List<Long>ids) {
	System.out.println("ids:"+ids);
	return tbItemService.deleteItem(ids);
	}
	@RequestMapping("/status")
	@ResponseBody
	public FjnyResult statusItem(@RequestParam("ids")List<Long>ids) {
		return tbItemService.statusItem(ids);
	}
	@RequestMapping("/sold")
	@ResponseBody
	public FjnyResult soldTbItem(@RequestParam("ids")List<Long>ids) {
		return tbItemService.soldTbItem(ids);
	}
	
	
}

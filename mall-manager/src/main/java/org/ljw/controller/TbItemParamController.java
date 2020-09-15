package org.ljw.controller;

import org.ljw.mapper.TbItemCatMapper;
import org.ljw.service.TbItemParamService;
import org.ljw.utils.EasyUIDataGridResult;
import org.ljw.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class TbItemParamController {

	@Autowired
	private TbItemParamService tbItemParamService;
	
	
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(@RequestParam(defaultValue ="1")
    Integer page
    ,@RequestParam(defaultValue="10")Integer rows) {
    return tbItemParamService.getTbItemParamList(page,rows);
    }

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public FjnyResult checkParam(@PathVariable Long itemCatId) {
    	System.out.println("====lll");
    	return tbItemParamService.checkParam(itemCatId);
    }
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public FjnyResult addItemParam(@PathVariable Long cid,String paramData) {
    return tbItemParamService.addItemParam(cid,paramData);
  }
}
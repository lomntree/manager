package org.ljw.rest.controller;

import org.ljw.rest.service.TbContentService;
import org.ljw.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class TbContentController {
	@Autowired
	private TbContentService tbContentService;

	@RequestMapping("/cid/{cid}")
	@ResponseBody
	 public FjnyResult getTbContentList(@PathVariable("cid") Long cid) {
		System.out.println("cid:" + cid);
		return tbContentService.getTbContentList(cid);
	}
}

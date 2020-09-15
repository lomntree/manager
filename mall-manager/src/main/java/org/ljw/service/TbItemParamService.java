package org.ljw.service;

import org.ljw.utils.EasyUIDataGridResult;
import org.ljw.utils.FjnyResult;

public interface TbItemParamService{

	EasyUIDataGridResult getTbItemParamList(Integer page,Integer rows);

	FjnyResult checkParam(Long itemCatId);

	FjnyResult addItemParam(Long cid, String paramData);
}

package org.ljw.service;

import java.util.List;

import org.ljw.utils.EasyUITRreeNodeBean;

public interface TbItemCatService {


	List<EasyUITRreeNodeBean> getTbItemCatList(Long parentId);
}


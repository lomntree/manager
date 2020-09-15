package org.ljw.service;

import java.util.List;

import org.ljw.utils.EasyUITRreeNodeBean;

public interface TbContentCategoryService {
    List<EasyUITRreeNodeBean> getTbContentCategoryList(long parentId);
}

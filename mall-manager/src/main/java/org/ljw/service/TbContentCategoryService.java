package org.ljw.service;

import java.util.List;

import org.ljw.utils.EasyUITRreeNodeBean;
import org.ljw.utils.FjnyResult;

public interface TbContentCategoryService {
    List<EasyUITRreeNodeBean> getTbContentCategoryList(long parentId);
    FjnyResult createNode(Long parentId,String name);
    FjnyResult updateNode(Long id,String name);
    FjnyResult deleteNode(Long id);
}

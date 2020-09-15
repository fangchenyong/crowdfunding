package com.fangchy.service.api;

import com.fangchy.entity.vo.DetailProjectVO;
import com.fangchy.entity.vo.PortalTypeVO;
import com.fangchy.entity.vo.ProjectVO;

import java.util.List;

public interface ProjectService {

    void saveProject(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeVO();

    DetailProjectVO getDetailProjectVO(Integer projectId);
}

package com.fangchy.service.api;

import com.fangchy.entity.vo.ProjectVO;

public interface ProjectService {

    void saveProject(ProjectVO projectVO, Integer memberId);

}

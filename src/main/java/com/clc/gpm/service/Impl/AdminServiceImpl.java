package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.FacultyMapper;
import com.clc.gpm.dao.mapper.LevelMapper;
import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.search.SearchFacultyDTO;
import com.clc.gpm.entity.Level;
import com.clc.gpm.service.AdminService;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.utils.PageUtil;
import com.clc.gpm.vo.LevelVO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Admin service.
 */
@Service
public class AdminServiceImpl implements AdminService {
    /** default sort*/
    private static final String DEFAULT_SORT = "faculty.FACULTY_ID";

    /** LST_COLUMN_ID*/
    private static final String[] HEADER_SORT = { DEFAULT_SORT, "faculty.NAME" };

    @Autowired
    private LevelMapper levelMapper;

    @Autowired
    private FacultyMapper facultyMapper;

    @Override
    public List<FacultyDTO> getAllListFaculty(SearchFacultyDTO facultyDTO) {
        PageUtil.initSearchDTO(facultyDTO, HEADER_SORT, DEFAULT_SORT);
        return facultyMapper.getAllFaculty(facultyDTO);
    }

    @Override
    public int countAllFaculty() {
        return facultyMapper.countAllFaculty();
    }

    @Override
    public LevelVO getAllLevel() {
        LevelVO levelVO = new LevelVO();
        levelVO.setLstLevel(levelMapper.getAllLevel());

        return levelVO;
    }

    @Override
    public Integer addLevel(String obj) {
        Gson gson = new Gson();
        Level level = gson.fromJson(obj, Level.class);
        level.setStatus("1");
        CommonService.copyCreateInfo(level);
        return levelMapper.insert(level);
    }
}

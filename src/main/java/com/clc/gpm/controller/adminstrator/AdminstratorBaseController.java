package com.clc.gpm.controller.adminstrator;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.controller.PaginationController;
import com.clc.gpm.dao.mapper.FacultyMapper;
import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.search.SearchFacultyDTO;
import com.clc.gpm.entity.Faculty;
import com.clc.gpm.form.search.SearchFacultyForm;
import com.clc.gpm.service.AdminService;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.vo.LevelVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * The type Adminstrator controller.
 */
@Controller
@RequestMapping("/admin")
public class AdminstratorBaseController extends PaginationController {

    /**
     * The Faculty mapper.
     */
    @Autowired
    FacultyMapper facultyMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CommonService commonService;

    /**
     * Init index view model and view.
     *
     * @return the model and view
     */
    @GetMapping("/")
    public ModelAndView initIndexView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sites/admin/admin-index");

        return modelAndView;
    }

    /**
     * Init list faculty view model and view.
     *
     * @param searchFacultyForm the search faculty form
     * @return the model and view
     */
    @GetMapping("/list-faculty")
    public ModelAndView initListFacultyView(SearchFacultyForm searchFacultyForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sites/admin/admin-list-faculty");

        if (searchFacultyForm == null)
            searchFacultyForm = new SearchFacultyForm();

        SearchFacultyDTO facultyDTO = new SearchFacultyDTO();
        commonService.map(searchFacultyForm, facultyDTO);

        List<FacultyDTO> lstFaculty = adminService.getAllListFaculty(facultyDTO);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(lstFaculty)) {
            totalRow = adminService.countAllFaculty();
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchFacultyForm.getPage(), totalRow);
        } else {
            totalRow = adminService.countAllFaculty();
            modelAndView.addObject("totalRowCount", totalRow);
            //Get current page
            int currentPage = totalRow / CommonConstants.GRID_ROW_COUNT;
            facultyDTO.setPage(currentPage);
            setPaginationData(modelAndView, currentPage, totalRow);
            lstFaculty = adminService.getAllListFaculty(facultyDTO);
        }

        modelAndView.addObject("lstFaculty", lstFaculty);
        modelAndView.addObject("searchFacultyForm", searchFacultyForm);
        return modelAndView;
    }


    /**
     * Add faculty string.
     *
     * @param obj the obj
     * @return the string
     */
    @PostMapping("addFaculty")
    @ResponseBody
    public String addFaculty(@RequestBody String obj) {
        Gson gson = new Gson();
        Faculty faculty = gson.fromJson(obj, Faculty.class);
        faculty.setDeleteFlg("0");
        facultyMapper.insert(faculty);
        return gson.toJson(faculty);
    }

    /**
     * Edit faculty string.
     *
     * @param obj the obj
     * @return the string
     */
    @PostMapping("editFaculty")
    @ResponseBody
    public String editFaculty(@RequestBody String obj) {
        Gson gson = new Gson();
        Faculty faculty = gson.fromJson(obj, Faculty.class);
        faculty.setDeleteFlg("0");
                facultyMapper.updateNotNullByPK(faculty);
        return gson.toJson(faculty);
    }

    /**
     * Delete faculty boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    @PostMapping("deleteFaculty")
    @ResponseBody
    public boolean deleteFaculty(@RequestBody String obj) {
        Gson gson = new Gson();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Faculty> lstFaculty = objectMapper.readValue(obj, objectMapper.getTypeFactory().constructCollectionType(List.class, Faculty.class));
            for (Faculty faculty :
                    lstFaculty) {
                facultyMapper.deleteFaculty(faculty.getId());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Quan ly he dao tao
     * Init level manager model and view.
     *
     * @return the model and view
     */
    @GetMapping("level-manager")
    public ModelAndView initLevelManager() {
        ModelAndView modelAndView = new ModelAndView("sites/admin/admin-level");
        LevelVO levelVO = adminService.getAllLevel();

        modelAndView.addObject("levelVO", levelVO);
        return modelAndView;
    }

    /**
     * Add level string.
     *
     * @param obj the obj
     * @return the string
     */
    @PostMapping("addLevel")
    @ResponseBody
    public String addLevel(@RequestBody String obj){
        Gson gson = new Gson();

        return gson.toJson(adminService.addLevel(obj));
    }
}
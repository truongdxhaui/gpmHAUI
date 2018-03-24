package com.clc.gpm.dto.handler;

import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.JQGridDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class JQGridHandler {

    public JQGridDTO<FacultyDTO> loadFaculty(final HttpServletRequest req){

        /**
         * The page and rows are sent from the JQGrid component with the Ajax
         * query.
         *
         */
        int page = Integer.valueOf(req.getParameter("page")).intValue();
        int pageSize = Integer.valueOf(req.getParameter("rows")).intValue();

        /**
         * I am not using the star index and end index in this case, but in an
         * ideal situation, you will be passing the start and end index to your
         * pagination SQL query.
         *
         */
        int startIndex = page == 1 ? 0 : (pageSize * (page - 1));
        int endIndex = page == 1 ? pageSize : pageSize * page;
        int total = -1;

        JQGridDTO<FacultyDTO> jqGridDTO = new JQGridDTO<>();
        List<FacultyDTO> lstFacultyDTO = new ArrayList<>();

        FacultyDTO faculty = new FacultyDTO();
        faculty.setFacultyId("TestID");
        faculty.setName("CNTT");

        lstFacultyDTO.add(faculty);

        /**
         * The total in the ideal situation would be the count of the records of
         * your SQL query from the table you want to fetch data from.
         */
        total = lstFacultyDTO.size();
        jqGridDTO.setPage(page);
        jqGridDTO.setTotal(String.valueOf(Math.ceil((double) total / pageSize)));
        jqGridDTO.setRecords(String.valueOf(total));
        jqGridDTO.setRows(lstFacultyDTO);

        return jqGridDTO;
    }
}

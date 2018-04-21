package com.clc.gpm.rest;

import com.clc.gpm.service.LecturerService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LecturerAPI {

    @Autowired
    LecturerService lecturerService;

    @GetMapping("activeProject")
    @ResponseBody
    public String activeProject(Integer projectId){
        Gson gson = new Gson();
        if(lecturerService.activeProject(projectId))
            return gson.toJson(HttpStatus.OK);
        return "";
    }

    @GetMapping("disableProject")
    @ResponseBody
    public String disableProject(Integer projectId){
        Gson gson = new Gson();
        if(lecturerService.disableProject(projectId))
            return gson.toJson(HttpStatus.OK);
        return "";
    }
}

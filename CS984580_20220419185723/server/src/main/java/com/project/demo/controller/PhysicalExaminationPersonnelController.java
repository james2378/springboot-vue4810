package com.project.demo.controller;

import com.project.demo.entity.PhysicalExaminationPersonnel;
import com.project.demo.service.PhysicalExaminationPersonnelService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *体检人员：(PhysicalExaminationPersonnel)表控制层
 *
 */
@RestController
@RequestMapping("/physical_examination_personnel")
public class PhysicalExaminationPersonnelController extends BaseController<PhysicalExaminationPersonnel,PhysicalExaminationPersonnelService> {

    /**
     *体检人员对象
     */
    @Autowired
    public PhysicalExaminationPersonnelController(PhysicalExaminationPersonnelService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}

package com.project.demo.controller;

import com.project.demo.entity.PhysicalExaminationPackage;
import com.project.demo.service.PhysicalExaminationPackageService;
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
 *体检套餐：(PhysicalExaminationPackage)表控制层
 *
 */
@RestController
@RequestMapping("/physical_examination_package")
public class PhysicalExaminationPackageController extends BaseController<PhysicalExaminationPackage,PhysicalExaminationPackageService> {

    /**
     *体检套餐对象
     */
    @Autowired
    public PhysicalExaminationPackageController(PhysicalExaminationPackageService service) {
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

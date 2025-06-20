package com.project.demo.controller;

import com.project.demo.entity.PhysicalExaminationPlan;
import com.project.demo.service.PhysicalExaminationPlanService;
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
 *体检计划：(PhysicalExaminationPlan)表控制层
 *
 */
@RestController
@RequestMapping("/physical_examination_plan")
public class PhysicalExaminationPlanController extends BaseController<PhysicalExaminationPlan,PhysicalExaminationPlanService> {

    /**
     *体检计划对象
     */
    @Autowired
    public PhysicalExaminationPlanController(PhysicalExaminationPlanService service) {
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

package Zim.controller;

import Zim.model.Area;
import Zim.model.modelview.AreaSimple;
import Zim.model.modelview.AreaView;
import Zim.model.modelview.SysResult;
import Zim.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laxton-Joe on 2017/8/9.
 */
@RestController
public class AreaController {

    @Autowired
    AreaService areaService;

    @CrossOrigin
    @RequestMapping(value = "/api/area", method = RequestMethod.POST)
    @ResponseBody
    public SysResult<AreaView> get(@RequestParam(value = "pid", required = true, defaultValue = "0") int pid,
                                   @RequestParam(value = "type", required = true, defaultValue = "1") int type) {
        SysResult<AreaView> result = new SysResult<>();
        try {
            List<Area> areas = areaService.getAreas(pid, type);
            AreaView areaView = new AreaView();
            areaView.setParentId(pid);
            areaView.setType(type);
            List<AreaSimple> listAreaSimple = new ArrayList<>();
            for (Area area : areas) {
                AreaSimple simple = new AreaSimple();
                simple.setAreaId(area.getAreaId());
                simple.setName(area.getName());
                listAreaSimple.add(simple);
            }
            areaView.setAreas(listAreaSimple);
            result.setResult(true);
            result.setContent(areaView);
        } catch (Exception ex) {
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }

        return result;
    }
}

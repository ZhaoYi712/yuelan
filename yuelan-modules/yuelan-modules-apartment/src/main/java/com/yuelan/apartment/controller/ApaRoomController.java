package com.yuelan.apartment.controller;

import com.yuelan.apartment.domain.ApaRoomInfo;
import com.yuelan.apartment.service.ApaRoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 房间信息
 * @Author: ZhaoYi
 * @date: 24-5-23 9:23
 */
@RestController
@RequestMapping(value = "/room")
public class ApaRoomController {

    @Resource
    private ApaRoomService apaRoomInfoService;

    /**
     * 新增房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/insert")
    public Object insert(ApaRoomInfo apaRoomInfo){
        return apaRoomInfoService.insert(apaRoomInfo);
    }

    /**
     * 刪除房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/delete")
    public Object delete(int id){
        return apaRoomInfoService.delete(id);
    }

    /**
     * 更新房间
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/update")
    public Object update(ApaRoomInfo apaRoomInfo){
        return apaRoomInfoService.update(apaRoomInfo);
    }

    /**
     * 查询房间信息
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/load")
    public Object load(int id){
        return apaRoomInfoService.load(id);
    }

    /**
     * 查询房间 分页查询
     * @author ZhaoYi
     * @date 2024/05/23
     **/
    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return apaRoomInfoService.pageList(offset, pagesize);
    }
}

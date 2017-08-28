package io.renren.modules.wechat.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.qiniu.util.Json;
import com.riversoft.weixin.common.menu.Menu;
import com.riversoft.weixin.common.menu.MenuItem;
import com.riversoft.weixin.mp.menu.Menus;
import io.renren.common.utils.JacksonUtil;
import io.renren.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/7/31.
 */
@Controller
@RequestMapping("/wx")
public class WxMenuCallbackController {

    private static Logger logger = LoggerFactory.getLogger(WxCallbackController.class);

    /**
     * 自定义菜单创建
     * @param
     * @return
     */
    @RequestMapping(value = "/menu/create",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public R create(String menus){

        JavaType javaType = JacksonUtil.getCollectionType(ArrayList.class, MenuItem.class);


        try {
            List<MenuItem> list = JacksonUtil.mapper.readValue(menus, javaType);
            Menu menu = new Menu();
            menu.setMenus(list);
            logger.debug("menu={}", Json.encode(menu));
            Menus.defaultMenus().create(menu);
            return R.ok("菜单创建成功");
        } catch (IOException e) {
            e.printStackTrace();
            return R.ok("菜单创建失败");
        }
    }
    /**
     * 获取菜单
     * @return
     */
    @RequestMapping(value = "/menu/get",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public R get(){
       Menu menu= Menus.defaultMenus().get();
       logger.debug("signature={}",menu);
       return R.ok("获取菜单成功").put("menu",menu);
    }
}

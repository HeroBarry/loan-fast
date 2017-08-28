package io.renren.modules.wechat.controller;

import com.riversoft.weixin.mp.user.BlackLists;
import com.riversoft.weixin.mp.user.Groups;
import com.riversoft.weixin.mp.user.Users;
import com.riversoft.weixin.mp.user.bean.Group;
import com.riversoft.weixin.mp.user.bean.User;
import com.riversoft.weixin.mp.user.bean.UserPagination;
import io.renren.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by John on 2017/8/2.
 */
@Controller
@RequestMapping("/wx")
public class WxUserController {
    private static Logger logger = LoggerFactory.getLogger(WxCallbackController.class);
    /**
     * 获取用户列表
     * @param nextOpenId 最后一个用户OpenId
     */
    @RequestMapping("/user/getUserList")
    @ResponseBody
    public R getUserOpenIdList(String nextOpenId){

       UserPagination userPagination= Users.defaultUsers().list(nextOpenId);
        logger.info("userPagination={}",userPagination);

        List<String> userOpenIdList =userPagination.getUsers();
        List<Map<String, String>> openIds=new ArrayList<>();
        //组装请求参数集合
        for (String openId:userOpenIdList){
            Map<String, String> map = new HashMap<>();
            map.put("openid", openId);
            map.put("lang", "zh_CN");
            openIds.add(map);
        }
        //批量获取用户信息
        List<User> wxUsers=Users.defaultUsers().batchGet(openIds);
        return R.ok("获取用户列表").put("userPagination",wxUsers);
    }

    /**
     * 查询所有分组
     * @return
     */
    @RequestMapping("/user/getGroupList")
    @ResponseBody
    public R getGroupList(){
        List<Group> groups=Groups.defaultGroups().list();
        return R.ok("查询所有分组成功").put("groups",groups);
    }
    /**
     * 创建分组
     * @param name
     */
    @RequestMapping("/user/createGroups")
    @ResponseBody
    public R createGroups(String name){
        Group group= Groups.defaultGroups().create(name);
        return R.ok("分组创建成功").put("group",group);
    }

    /**
     * 修改分组
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/user/updateGroup")
    @ResponseBody
    public R updateGroup(int id, String name){
        Groups.defaultGroups().update(id,name);
        return R.ok("修改分组成功");
    }

    /**
     * 拉黑
     * @param openids
     * @return
     */
    @RequestMapping("/user/blackUsers")
    @ResponseBody
    public R blackUsers(List<String> openids){
        BlackLists.defaultBlackLists().black(openids);
        return R.ok("拉黑成功");
    }
    /**
     * 取消拉黑
     * @param openids
     * @return
     */
    @RequestMapping("/user/unBlackUsers")
    @ResponseBody
    public R unBlackUsers(List<String> openids){
        BlackLists.defaultBlackLists().unblack(openids);
        return R.ok("取消拉黑成功");
    }
}

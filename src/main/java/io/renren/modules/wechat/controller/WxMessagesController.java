package io.renren.modules.wechat.controller;

import com.riversoft.weixin.mp.message.MpMessages;
import com.riversoft.weixin.mp.user.Groups;
import io.renren.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by John on 2017/7/31.
 */
@Controller
@RequestMapping("/wx")
public class WxMessagesController {
    private static Logger logger = LoggerFactory.getLogger(WxCallbackController.class);
    @RequestMapping("/message/text")
    @ResponseBody
    public R textMessage(){

        ArrayList<String> openIds=new ArrayList<>();
        openIds.add("oGCZp0sRV0oKpd-JazcYwVML4w6I");
        openIds.add("oGCZp0nTP9f6hQXDBaW2lzO8okIk");
        int groupId=Groups.defaultGroups().getUserGroup("oGCZp0nTP9f6hQXDBaW2lzO8okIk");
        long ok=MpMessages.defaultMpMessages().text(groupId,"多米多科技");

        logger.info("ok={}",ok);
        return R.ok("文本消息发送成功");
    }

    @RequestMapping("/message/mpNews")
    @ResponseBody
    public R mpNewstMessage(){


        ArrayList<String> openIds=new ArrayList<>();
        openIds.add("oGCZp0sRV0oKpd-JazcYwVML4w6I");
        openIds.add("oGCZp0nTP9f6hQXDBaW2lzO8okIk");
        int groupId=Groups.defaultGroups().getUserGroup("oGCZp0nTP9f6hQXDBaW2lzO8okIk");
        long ok=MpMessages.defaultMpMessages().mpNews(groupId,"多米多科技");

        logger.info("ok={}",ok);
        return R.ok("文本消息发送成功");
    }
}

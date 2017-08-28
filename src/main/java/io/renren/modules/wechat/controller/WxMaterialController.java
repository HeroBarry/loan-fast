package io.renren.modules.wechat.controller;

import com.riversoft.weixin.common.media.MpArticle;
import com.riversoft.weixin.common.media.MpNews;
import com.riversoft.weixin.mp.media.Materials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;

/**
 * Created by John on 2017/8/2.
 */
@Controller
@RequestMapping("/wx")
public class WxMaterialController {


    private static Logger logger = LoggerFactory.getLogger(WxCallbackController.class);
    /**
     * 图文消息的content里面如果有图片，该图片需要使用本方法上传，图片仅支持jpg/png格式，大小必须在1MB以下
     *
     * @param inputStream 图片流
     * @param fileName    文件名
     * @return 图片的url, 可以在图文消息的content里面使用
     */
    @RequestMapping("/materials/addMpNewsImage")
    @ResponseBody
    public String addMpNewsImage(InputStream inputStream, String fileName) {
       String url= Materials.defaultMaterials().addMpNewsImage(inputStream,fileName);
        //TODO  可以将url存在自己服务器中
       return url;
    }
    /**
     * 上传一个图文素材
     *
     * @param mpNews 图文素材
     * @return 返回media id
     */
    @RequestMapping("/materials/addMpNews")
    @ResponseBody
    public String addMpNews(MpNews mpNews) {
        String url=Materials.defaultMaterials().addMpNews(mpNews);
        return url;
    }
    /**
     * 获取图文素材
     *
     * @param mediaId media id
     * @return 图文素材
     */
    @RequestMapping("/materials/getMpNews")
    @ResponseBody
    public MpNews getMpNews(String mediaId) {
        MpNews mpNews=Materials.defaultMaterials().getMpNews(mediaId);
        return mpNews;
    }
    /**
     * 修改图文素材
     *
     * @param mediaId 图文素材 media id
     * @param index   要更新的文章在图文素材中的位置
     * @param article 文章
     */
    @RequestMapping("/materials/updateMpNews")
    @ResponseBody
    public void updateMpNews(String mediaId, int index, MpArticle article) {
        Materials.defaultMaterials().updateMpNews(mediaId,index,article);
    }
}

package io.renren.common.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Alan on 2017/8/11.
 */
public class JacksonUtil {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception{
        //JavaType javaType = getCollectionType(ArrayList.class, YourBean.class);
       // List<YourBean> lst =  (List<YourBean>)mapper.readValue(jsonString, javaType);
    }
    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


}

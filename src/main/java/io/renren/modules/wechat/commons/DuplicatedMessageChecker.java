package io.renren.modules.wechat.commons;

/**
 * Created by exizhai on 11/15/2015.
 */
public interface DuplicatedMessageChecker {

    boolean isDuplicated(String msgKey);

}

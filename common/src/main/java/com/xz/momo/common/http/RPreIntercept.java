package com.xz.momo.common.http;

import java.util.Map;

/**
 * Created by cisco_luo on 2017/3/25.
 */

public interface RPreIntercept {

    boolean process(Map<String, String> headers, Map<String, String> body);

}

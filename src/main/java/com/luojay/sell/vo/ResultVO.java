package com.luojay.sell.vo;

import lombok.Data;

/**http返回的最外层对象
 * 返回给前端的视图对象ViewOcject
 */
@Data
public class ResultVO<T> {
    //状态码
    private Integer code;
    //提示消息
    private String message;
    //data里面是一个对象，这里定义为泛型
    private T data;

}

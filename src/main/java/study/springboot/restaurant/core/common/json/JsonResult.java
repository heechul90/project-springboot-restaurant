package study.springboot.restaurant.core.common.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonResult<T> {

    private String message = "";
    private T data;
}

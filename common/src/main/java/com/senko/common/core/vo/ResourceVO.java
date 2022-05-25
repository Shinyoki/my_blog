package com.senko.common.core.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 资源、表单资源VO
 *
 * @author senko
 * @date 2022/5/25 22:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("资源、表单资源VO")
public class ResourceVO implements Serializable {
    private static final long serialVersionUID = -8246578776825271731L;
    //TODO 资源VO
}

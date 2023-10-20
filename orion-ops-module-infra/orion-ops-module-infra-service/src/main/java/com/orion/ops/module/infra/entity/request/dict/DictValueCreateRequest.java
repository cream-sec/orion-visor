package com.orion.ops.module.infra.entity.request.dict;

import com.orion.ops.framework.common.constant.ValidConst;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 字典配置值 创建请求对象
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023-10-16 16:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "DictValueCreateRequest", description = "字典配置值 创建请求对象")
public class DictValueCreateRequest implements Serializable {

    @NotNull
    @Schema(description = "配置项id")
    private Long keyId;

    @NotBlank
    @Size(max = 32)
    @Pattern(regexp = ValidConst.CHAR_NUMBER_1_32_PATTERN, message = ValidConst.CHAR_NUMBER_1_32_MESSAGE)
    @Schema(description = "配置名称")
    private String name;

    @NotBlank
    @Size(max = 512)
    @Schema(description = "配置值")
    private String value;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "配置描述")
    private String label;

    @NotBlank
    @Schema(description = "额外参数")
    private String extra;

    @NotNull
    @Schema(description = "排序")
    private Integer sort;

}

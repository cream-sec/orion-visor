package com.orion.ops.module.infra.controller;

import com.orion.ops.framework.log.core.annotation.IgnoreLog;
import com.orion.ops.framework.log.core.enums.IgnoreLogMode;
import com.orion.ops.framework.web.core.annotation.RestWrapper;
import com.orion.ops.module.infra.entity.request.menu.*;
import com.orion.ops.module.infra.entity.vo.SystemMenuVO;
import com.orion.ops.module.infra.service.SystemMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单 api
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023-7-17 11:39
 */
@Tag(name = "infra - 菜单服务")
@Slf4j
@Validated
@RestWrapper
@RestController
@RequestMapping("/infra/system-menu")
@SuppressWarnings({"ELValidationInJSP", "SpringElInspection"})
public class SystemMenuController {

    @Resource
    private SystemMenuService systemMenuService;

    @PostMapping("/create")
    @Operation(summary = "创建菜单")
    @PreAuthorize("@ss.hasPermission('infra:system-menu:create')")
    public Long createSystemMenu(@RequestBody SystemMenuCreateRequest request) {
        return systemMenuService.createSystemMenu(request);
    }

    @PutMapping("/update")
    @Operation(summary = "通过 id 更新菜单")
    @PreAuthorize("@ss.hasPermission('infra:system-menu:update')")
    public Integer updateSystemMenu(@RequestBody SystemMenuUpdateRequest request) {
        return systemMenuService.updateSystemMenuById(request);
    }

    @PutMapping("/update-status")
    @Operation(summary = "通过 id 级联更新菜单状态")
    @PreAuthorize("@ss.hasPermission('infra:system-menu:update-status')")
    public Integer updateSystemMenuStatus(@RequestBody SystemMenuUpdateStatusRequest request) {
        return systemMenuService.updateSystemMenuStatus(request);
    }

    @IgnoreLog(IgnoreLogMode.RET)
    @GetMapping("/get")
    @Operation(summary = "通过 id 查询菜单")
    @Parameter(name = "id", description = "id", required = true)
    @PreAuthorize("@ss.hasPermission('infra:system-menu:query')")
    public SystemMenuVO getSystemMenu(@RequestParam("id") Long id) {
        return systemMenuService.getSystemMenuById(id);
    }

    @IgnoreLog(IgnoreLogMode.RET)
    @PostMapping("/list")
    @Operation(summary = "查询菜单")
    @PreAuthorize("@ss.hasPermission('infra:system-menu:query')")
    public List<SystemMenuVO> getSystemMenuList(@Validated @RequestBody SystemMenuQueryRequest request) {
        return systemMenuService.getSystemMenuByIdList(request);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "通过 id 级联删除菜单")
    @Parameter(name = "id", description = "id", required = true)
    @PreAuthorize("@ss.hasPermission('infra:system-menu:delete')")
    public Integer deleteSystemMenu(@RequestParam("id") Long id) {
        return systemMenuService.deleteSystemMenu(id);
    }

}


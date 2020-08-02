package com.clw.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author clw
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_menu")
@ApiModel(value="Menu对象", description="菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "菜单/按钮ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "上级菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "菜单/按钮名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "菜单URL")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "类型 0菜单 1按钮")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "排序")
    @TableField("order_num")
    private Long orderNum;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("modified_time")
    private Date modifiedTime;

    @ApiModelProperty(value = "0：不可用，1：可用")
    @TableField("available")
    private Integer available;

    @ApiModelProperty(value = "0:不展开，1：展开")
    @TableField("open")
    private Integer open;


}

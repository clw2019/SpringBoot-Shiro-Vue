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
 * 用户表
 * </p>
 *
 * @author clw
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "联系电话")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty(value = "状态 0锁定 1有效")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("modified_time")
    private Date modifiedTime;

    @ApiModelProperty(value = "性别 0男 1女 2保密")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "0:超级管理员，1：系统用户")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @TableField("birth")
    private Date birth;

    @ApiModelProperty(value = "部门id")
    @TableField("department_id")
    private Long departmentId;


}

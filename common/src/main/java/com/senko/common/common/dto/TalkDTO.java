package com.senko.common.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 后台说说DTO
 *
 * @author senko
 * @date 2022/5/29 8:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("说说DTO")
public class TalkDTO implements Serializable {
    private static final long serialVersionUID = -84598989898L;

    /**
     * 说说id
     */
    @ApiModelProperty("说说id")
    private Integer id;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 说说内容
     */
    @ApiModelProperty("说说内容")
    private String content;

    /**
     * 说说图片
     * JSON字符串：["url1", "url2", ...]
     */
    @ApiModelProperty("说说图片")
    private String images;

    /**
     * 图片集合
     * 需从数据库获取images自行通过JSON.parseObject(images, List.class)转换
     */
    @ApiModelProperty("图片集合")
    @TableField(exist = false)
    private List<String> imgList;

    /**
     * 是否置顶 1-是 0-否
     */
    @ApiModelProperty("是否置顶 1-是 0-否")
    private Integer isTop;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;

    /**
     * 点赞量
     */
    @ApiModelProperty("点赞量")
    private Integer likeCount;

    /**
     * 评论量
     */
    @ApiModelProperty("评论量")
    private Integer commentCount;

}

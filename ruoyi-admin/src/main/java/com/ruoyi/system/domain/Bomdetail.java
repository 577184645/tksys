package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * bom详细清单对象 bomdetail
 * 
 * @author ruoyi
 * @date 2020-09-28
 */
@Data
public class Bomdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long bomid;
    private String code;
    private String comment;
    private Double price;
    private String link;
    private String footprint;
    private String description;
    private String parttype;
    private String designator;
    private Long quantity;




}

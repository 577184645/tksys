package com.ruoyi.system.domain;

import java.util.Date;

import com.ruoyi.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料列表对象 material
 *
 * @author ruoyi
 * @date 2020-06-01
 */
public class Material extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;


    /** 部门 */
    @Excel(type = Excel.Type.IMPORT,name = "部门",readConverterExp = "00=研发部,01=生产部,02=工程部,03=办公室,04=销售部,05=其他")
    private String deptIdExcel;

    /** 类型id */
    @Excel(type = Excel.Type.IMPORT,name = "类型",readConverterExp = "01=元器件,02=半成品,03=成品,04=办公用品,05=设备设施")
    private String typeIdExcel;

    private Long typeId;

    private Long deptId;


    /** 编码 */
    @Excel(type=Excel.Type.EXPORT,name = "编码")
    private String materialcode;

    /** 型号 */
    @Excel(name = "型号")
    private String partnumber;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 封装 */
    @Excel(name = "封装")
    private String footprint;

    /** 品牌 */
    @Excel(name = "品牌")
    private String manufacture;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;



    /** 采购员 */
    @Excel(name = "采购员")
    private String buyer;

    /** 物料信息注册者 */
    @Excel(type = Excel.Type.EXPORT,name = "物料信息注册者")
    private String inputoperator;





    /** 物料信息注册时间 */
    @Excel(type = Excel.Type.EXPORT,name = "物料信息注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputdate;



    /** 安全库存 */
    @Excel(name = "安全库存")
    private Long safestock;


    /** 批准者 */
    @Excel(name = "批准者")
    private String approvedby;

    /** 批准时间 */
    @Excel(name = "批准时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveddate;

    /** 批准状态 */
    @Excel(name = "批准状态")
    private String approvedstatus;

    /** 最少包装量 */
    @Excel(name = "最少包装量")
    private Integer productmpq;

    /** 最少起订量 */
    @Excel(name = "最少起订量")
    private Integer productmoq;

   private  String   delFlag;


    /** 交期 */
    @Excel(name = "交期")
    private Integer leadtime;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;




    /** 供方料号 */
    @Excel(name = "供方料号")
    private String ordernumber;



    /** 创建时间 */
    @Excel(type = Excel.Type.EXPORT,name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(type = Excel.Type.EXPORT,name = "修改时间",width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setMaterialcode(String materialcode) 
    {
        this.materialcode = materialcode;
    }

    public String getMaterialcode() 
    {
        return materialcode;
    }
    public void setPartnumber(String partnumber) 
    {
        this.partnumber = partnumber;
    }

    public String getPartnumber() 
    {
        return partnumber;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setFootprint(String footprint) 
    {
        this.footprint = footprint;
    }

    public String getFootprint() 
    {
        return footprint;
    }
    public void setManufacture(String manufacture) 
    {
        this.manufacture = manufacture;
    }

    public String getManufacture() 
    {
        return manufacture;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setInputoperator(String inputoperator) 
    {
        this.inputoperator = inputoperator;
    }

    public String getInputoperator() 
    {
        return inputoperator;
    }
    public void setInputdate(Date inputdate) 
    {
        this.inputdate = inputdate;
    }

    public Date getInputdate() 
    {
        return inputdate;
    }
    public void setApprovedby(String approvedby) 
    {
        this.approvedby = approvedby;
    }

    public String getApprovedby() 
    {
        return approvedby;
    }
    public void setApproveddate(Date approveddate) 
    {
        this.approveddate = approveddate;
    }

    public Date getApproveddate() 
    {
        return approveddate;
    }
    public void setApprovedstatus(String approvedstatus) 
    {
        this.approvedstatus = approvedstatus;
    }

    public String getApprovedstatus() 
    {
        return approvedstatus;
    }
    public void setProductmpq(Integer productmpq) 
    {
        this.productmpq = productmpq;
    }

    public Integer getProductmpq() 
    {
        return productmpq;
    }
    public void setProductmoq(Integer productmoq) 
    {
        this.productmoq = productmoq;
    }

    public Integer getProductmoq() 
    {
        return productmoq;
    }
    public void setLeadtime(Integer leadtime) 
    {
        this.leadtime = leadtime;
    }

    public Integer getLeadtime() 
    {
        return leadtime;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }


   private Materialtype  materialtype;

    public Materialtype getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(Materialtype materialtype) {
        this.materialtype = materialtype;
    }

    public void setSafestock(Long safestock)
    {
        this.safestock = safestock;
    }

    public Long getSafestock() 
    {
        return safestock;
    }
    public void setOrdernumber(String ordernumber) 
    {
        this.ordernumber = ordernumber;
    }

    public String getOrdernumber() 
    {
        return ordernumber;
    }
    public void setBuyer(String buyer) 
    {
        this.buyer = buyer;
    }

    public String getDeptIdExcel() {
        return deptIdExcel;
    }

    public void setDeptIdExcel(String deptIdExcel) {
        this.deptIdExcel = deptIdExcel;
    }

    public String getTypeIdExcel() {
        return typeIdExcel;
    }

    public void setTypeIdExcel(String typeIdExcel) {
        this.typeIdExcel = typeIdExcel;
    }

    public String getBuyer()
    {
        return buyer;
    }
    public void setcTime(Date cTime) 
    {
        this.cTime = cTime;
    }

    public Date getcTime() 
    {
        return cTime;
    }
    public void setuTime(Date uTime) 
    {
        this.uTime = uTime;
    }

    public Date getuTime() 
    {
        return uTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("materialcode", getMaterialcode())
            .append("partnumber", getPartnumber())
            .append("description", getDescription())
            .append("footprint", getFootprint())
            .append("manufacture", getManufacture())
            .append("unit", getUnit())
            .append("inputoperator", getInputoperator())
            .append("inputdate", getInputdate())
            .append("approvedby", getApprovedby())
            .append("approveddate", getApproveddate())
            .append("approvedstatus", getApprovedstatus())
            .append("productmpq", getProductmpq())
            .append("productmoq", getProductmoq())
            .append("leadtime", getLeadtime())
            .append("comments", getComments())
            .append("tId", getDeptId())
            .append("dept", getDeptId())
            .append("safestock", getSafestock())
            .append("ordernumber", getOrdernumber())
            .append("buyer", getBuyer())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}

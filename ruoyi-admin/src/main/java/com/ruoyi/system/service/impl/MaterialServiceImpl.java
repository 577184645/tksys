package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.Material;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 物料列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Service
public class MaterialServiceImpl implements IMaterialService {
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private MaterialtypeMapper materialtypeMapper;
    @Autowired
    private MaterialdeptMapper materialdeptMapper;
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private StorageoutdetailMapper storageoutdetailMapper;
    @Autowired
    private StorageindetailMapper storageindetailMapper;
    @Autowired
    private StoragequitdetailMapper storagequitdetailMapper;
    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;
    /**
     * 查询物料列表
     *
     * @param id 物料列表ID
     * @return 物料列表
     */
    @Override
    public Material selectMaterialById(Long id) {
        return materialMapper.selectMaterialById(id);
    }

    @Override
    public String getMaterialcode(Long typeId, Long deptId) {
        String materialCode = "";
        //获得类型 部门 相对应code
        String code = materialtypeMapper.selectMaterialtypeById(typeId).getCode();
        String code1 = materialdeptMapper.selectMaterialdeptById(deptId).getCode();
        //根据类型 部门 查找最大物料编码
        String materialcode = materialMapper.selectMaterialByMaterialcode(code + code1);
        if(materialcode==null){
            materialCode = code + code1 + "0001";
        }else{
            int count;
            //判断是否有后缀名
           if(materialcode.indexOf("-")==-1){
                count=Integer.valueOf(materialcode.substring(4));
           }else {
               count=Integer.valueOf(materialcode.substring(4,materialcode.indexOf("-")));
           }

            if (count+1 < 10) {
                materialCode = code + code1 + "000" + String.valueOf(count+1);
            } else if (count+1 < 100) {
                materialCode = code + code1 + "00" + String.valueOf(count+1);
            } else if (count+1 < 1000) {
                materialCode = code + code1 + "0" + String.valueOf(count+1);
            }else {
                materialCode=code+code1+String.valueOf(count+1);
            }
        }






        return materialCode;

    }

    /**
     * 查询物料列表列表
     *
     * @param material 物料列表
     * @return 物料列表
     */
    @Override
    public List<Material> selectMaterialList(Material material) {
        return materialMapper.selectMaterialList(material);
    }

    /**
     * 新增物料列表
     *
     * @param material 物料列表
     * @return 结果
     */
    @Override
    public int insertMaterial(Material material) {
        int i = materialMapper.selectMaterialRepetition(material.getName(), material.getPartnumber(), material.getFootprint(), material.getManufacture(), material.getDeptId());
      if (i>0){
          return 0;
      }
        return materialMapper.insertMaterial(material);
    }

    @Override
    public int addSuffix(Material material) {
        int index = materialMapper.selectMaterialByMaxMaterialcodeSuffix(material.getMaterialcode()).indexOf("-");
        if(index!=-1){
            String maxMaterialcode = materialMapper.selectMaterialByMaxMaterialcodeSuffix(material.getMaterialcode());
            char c = maxMaterialcode.substring(index + 1).charAt(0);
            c= (char) (c+1);
            material.setMaterialcode(material.getMaterialcode()+"-"+c);
        }else{
            material.setMaterialcode(material.getMaterialcode()+"-A");
        }
        return materialMapper.insertMaterial(material);
    }

    /**
     * 修改物料列表
     *
     * @param material 物料列表
     * @return 结果
     */
    @Override
    public AjaxResult updateMaterial(Material material) {
        if(storageMapper.selectStorageByMaterialcode(material.getMaterialcode())!=null){

            storagequitdetailMapper.updateMaterial(material.getName(),material.getPartnumber(),material.getFootprint(),material.getUnit(),material.getManufacture(),material.getMaterialcode());
            storageMapper.updateMaterial(material.getName(),material.getPartnumber(),material.getFootprint(),material.getUnit(),material.getManufacture(),material.getMaterialcode());
            storageindetailMapper.updateMaterial(material.getName(),material.getPartnumber(),material.getFootprint(),material.getUnit(),material.getManufacture(),material.getMaterialcode());
            storageoutdetailMapper.updateMaterial(material.getName(),material.getPartnumber(),material.getFootprint(),material.getUnit(),material.getManufacture(),material.getMaterialcode());
            materialMapper.updateMaterial(material);
        }else{
             materialMapper.updateMaterial(material);

        }
        return AjaxResult.success("修改成功!");
    }

    /**
     * 删除物料列表对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteMaterialByIds(String ids) {
        String[] strings = Convert.toStrArray(ids);
        int sueecessscount=0;
        int errorcount=0;
        for (int i = 0; i < strings.length; i++) {
            if(storageMapper.selectStorageByMaterialcode(materialMapper.selectMaterialById(Long.valueOf(strings[i])).getMaterialcode()).size()>0){
                errorcount++;
            }else{
                materialMapper.deleteMaterialById(Long.valueOf(strings[i]));
                sueecessscount++;
            }
        }




        return AjaxResult.success("操作成功,删除数据成功"+sueecessscount+"条"+"删除数据失败"+errorcount+"条,因为该物料在库存已存在！请联系管理员!");
    }



    @Override
    @Transactional
    public String importMaterial(List<Material> materialList) {
        if (StringUtils.isNull(materialList) || materialList.size() == 0) {
            throw new BusinessException("导入数据不能为空！");
        }
        SysUser user = ShiroUtils.getSysUser();
        int successNum = 0;
        int failureNum = 0;
        int repetitionNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Material material : materialList) {
                material.setInputdate(new Date());
                material.setInputoperator(user.getUserName());
            String code = material.getTypeIdExcel();
            String code1 =material.getDeptIdExcel();
            String materialcode = materialMapper.selectMaterialByMaterialcode(code + code1);
            if(materialcode==null){
                material.setMaterialcode(code + code1 + "0001");
            }else{
                Integer count=Integer.valueOf(materialcode.substring(4));

                if (count+1 < 10) {
                    material.setMaterialcode(code + code1 + "000" + String.valueOf(count+1));
                } else if (count+1 < 100) {
                    material.setMaterialcode( code + code1 + "00" + String.valueOf(count+1));
                } else if (count+1 < 1000) {
                    material.setMaterialcode(  code + code1 + "0" + String.valueOf(count+1));
                }else {
                    material.setMaterialcode(String.valueOf(count+1));
                }
            }
            material.setDeptId(materialdeptMapper.selectMaterialdeptByCode(material.getDeptIdExcel()).getId());
            material.setTypeId(materialtypeMapper.selectMaterialtypeByCode(material.getTypeIdExcel()).getDeptId());
            if(materialMapper.selectMaterialRepetition(material.getName(),material.getPartnumber(),material.getFootprint(),material.getManufacture(),material.getDeptId())>0){
                       repetitionNum++;
                          continue;
            }



                insertMaterial(material);
                successNum++;
            }
                failureNum++;

            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，其中重复数据"+repetitionNum+"条,数据如下：");

        return successMsg.toString();

    }


}

package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.Material;
import com.ruoyi.system.domain.MaterialChild;
import com.ruoyi.system.domain.Storage;
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
@Transactional
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
    @Autowired
    private MaterialChildMapper materialChildMapper;
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
        String materialcode = materialMapper.selectMaterialMaxMaterialcode(code + code1);
        if(materialcode==null){
            materialCode = code + code1 + "0001";
        }else{
            int  count=Integer.valueOf(materialcode.substring(4))+1;
            if (count < 10) {
                materialCode = code + code1 + "000" + String.valueOf(count);
            } else if (count < 100) {
                materialCode = code + code1 + "00" + String.valueOf(count);
            } else if (count < 1000) {
                materialCode = code + code1 + "0" + String.valueOf(count);
            }else {
                materialCode=code+code1+String.valueOf(count);
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
        return materialMapper.insertMaterial(material);
    }





    /**
     * 删除物料列表对象
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    @Override
    public AjaxResult deleteMaterial(Long id) {
       Storage storage=storageMapper.selectStorageByMaterialId(id);
       if(storage!=null){
           return AjaxResult.error("该物料有库存无法删除!");
       }
        materialMapper.deleteMaterialById(id);
        materialChildMapper.deleteMaterialChildByMaterialId(id);
        storageMapper.deleteStorageByMaterialId(id);
        return AjaxResult.success("操作成功!");
    }



    @Override

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
            String code = material.getTypeIdExcel();
            String code1 =material.getDeptIdExcel();
            String materialcode = materialMapper.selectMaterialMaxMaterialcode(code + code1);
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
                insertMaterial(material);
                successNum++;
            }
                failureNum++;
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，其中重复数据"+repetitionNum+"条,数据如下：");
        return successMsg.toString();

    }

    @Override
    public AjaxResult abandoned(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            materialMapper.updateStatus(2,Long.valueOf(s));
        }
      return  AjaxResult.success("操作成功!");
    }

    @Override
    public AjaxResult recovery(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            materialMapper.updateStatus(1,Long.valueOf(s));
        }
        return  AjaxResult.success("操作成功!");
    }


}

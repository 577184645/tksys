package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IMaterialService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

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
    public Material selectMaterialById(Integer id) {
        return materialMapper.selectMaterialById(id);
    }

    @Override
    public String getMaterialcode(Long typeId, Long deptId) {
        String materialCode = "";
        String code = materialtypeMapper.selectMaterialtypeById(typeId).getCode();
        String code1 = materialdeptMapper.selectMaterialdeptById(deptId).getCode();
        String materialcode = materialMapper.selectMaterialByMaterialcode(code + code1);
        if(materialcode==null){
            materialCode = code + code1 + "0001";
        }else{

            Integer count=Integer.valueOf(materialcode.substring(4));

            if (count+1 < 10) {
                materialCode = code + code1 + "000" + String.valueOf(count+1);
            } else if (count+1 < 100) {
                materialCode = code + code1 + "00" + String.valueOf(count+1);
            } else if (count+1 < 1000) {
                materialCode = code + code1 + "0" + String.valueOf(count+1);
            }else {
                materialCode=String.valueOf(count+1);
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
     * 修改物料列表
     *
     * @param material 物料列表
     * @return 结果
     */
    @Override
    @Transactional
    public int updateMaterial(Material material) {

        String oldmaterialcode = materialMapper.selectMaterialById(material.getId()).getMaterialcode();
        String name=material.getName();
        String materialcode=material.getMaterialcode();
        String partnumber=material.getPartnumber();
        String footprint=material.getFootprint();
        String unit=material.getUnit();
        String manufacture=material.getManufacture();
        storagequitdetailMapper.updateMaterial(name,materialcode,partnumber,footprint,unit,manufacture,oldmaterialcode);
        storageMapper.updateMaterial(name,materialcode,partnumber,footprint,unit,manufacture,oldmaterialcode);
       storageindetailMapper.updateMaterial(name,materialcode,partnumber,footprint,unit,manufacture,oldmaterialcode);
        storageoutdetailMapper.updateMaterial(name,materialcode,partnumber,footprint,unit,manufacture,oldmaterialcode);
        warehouseRecordMapper.updateMaterial(materialcode,name,oldmaterialcode);
        return materialMapper.updateMaterial(material);
    }

    /**
     * 删除物料列表对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterialByIds(String ids) {

        return materialMapper.deleteMaterialByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物料列表信息
     *
     * @param id 物料列表ID
     * @return 结果
     */
    @Override
    public int deleteMaterialById(Integer id) {
        return materialMapper.deleteMaterialById(id);
    }

    @Override
    @Transactional
    public String importMaterial(List<Material> materialList, HttpServletRequest request) {
        if (StringUtils.isNull(materialList) || materialList.size() == 0) {
            throw new BusinessException("导入数据不能为空！");
        }
        SysUser user = ShiroUtils.getSysUser();
        int successNum = 0;
        int failureNum = 0;
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

                insertMaterial(material);
                successNum++;
            }
                failureNum++;

            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");

        return successMsg.toString();

    }


}

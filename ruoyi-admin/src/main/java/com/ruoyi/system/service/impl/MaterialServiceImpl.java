package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.MaterialdeptMapper;
import com.ruoyi.system.mapper.MaterialtypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MaterialMapper;
import com.ruoyi.system.service.IMaterialService;
import com.ruoyi.common.core.text.Convert;

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
        Integer count = materialMapper.selectMaterialByMaterialcode(code + code1);
        if (count == null || count == 0) {
            materialCode = code + code1 + "0001";
        } else {
            if (count < 10) {
                materialCode = code + code1 + "000" + String.valueOf(++count);
            } else if (count < 100) {
                materialCode = code + code1 + "00" + String.valueOf(++count);
            } else if (count < 1000) {
                materialCode = code + code1 + "0" + String.valueOf(++count);
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
        String code = materialtypeMapper.selectMaterialtypeById(material.getTypeId()).getCode();
        String code1 = materialdeptMapper.selectMaterialdeptById(material.getDeptId()).getCode();
        Integer count = materialMapper.selectMaterialByMaterialcode(code + code1);
        if (count == null || count == 0) {
            material.setMaterialcode(code + code1 + "0001");
        } else {
            if (count < 10) {
                material.setMaterialcode(code + code1 + "000" + String.valueOf(++count));
            } else if (count < 100) {
                material.setMaterialcode(code + code1 + "00" + String.valueOf(++count));
            } else if (count < 1000) {
                material.setMaterialcode(code + code1 + "0" + String.valueOf(++count));
            }

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
    public int updateMaterial(Material material) {
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
    public String importMaterial(List<Material> materialList, HttpServletRequest request) {
        if (StringUtils.isNull(materialList) || materialList.size() == 0) {
            throw new BusinessException("导入数据不能为空！");
        }
        SysUser user =(SysUser) request.getSession().getAttribute("sessionuser");
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Material material : materialList) {
            try {

                material.setInputdate(new Date());
                material.setInputoperator(user.getUserName());
                Integer count = materialMapper.selectMaterialByMaterialcode(material.getDeptIdExcel() + material.getTypeIdExcel());
                if (count == null || count == 0) {
                    material.setMaterialcode(material.getDeptIdExcel() + material.getTypeIdExcel() + "0001");
                } else {
                    if (count < 10) {
                        material.setMaterialcode(material.getDeptIdExcel() + material.getTypeIdExcel() + "000" + String.valueOf(++count));
                    } else if (count < 100) {
                        material.setMaterialcode(material.getDeptIdExcel() + material.getTypeIdExcel() + "00" + String.valueOf(++count));
                    } else if (count < 1000) {
                        material.setMaterialcode(material.getDeptIdExcel() + material.getTypeIdExcel() + "0" + String.valueOf(++count));
                    }

                }
                material.setDeptId(materialdeptMapper.selectMaterialdeptByCode(material.getDeptIdExcel()).getId());
                material.setTypeId(materialtypeMapper.selectMaterialtypeByCode(material.getTypeIdExcel()).getDeptId());

                insertMaterial(material);
                successNum++;
            } catch (Exception e) {
                failureNum++;
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
                throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();

    }


}

package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.Materialdept;
import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.system.mapper.MaterialdeptMapper;
import com.ruoyi.system.mapper.MaterialtypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MaterialMapper;
import com.ruoyi.system.domain.Material;
import com.ruoyi.system.service.IMaterialService;
import com.ruoyi.common.core.text.Convert;

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
    public List<Material> selectMaterialList(Material material)
    {
        return materialMapper.selectMaterialList(material);
    }

    /**
     * 新增物料列表
     * 
     * @param material 物料列表
     * @return 结果
     */
    @Override
    public int insertMaterial(Material material)
    {
        String code = materialtypeMapper.selectMaterialtypeById(material.getTypeId()).getCode();
        String code1 =   materialdeptMapper.selectMaterialdeptById(material.getDeptId()).getCode();
        Integer count = materialMapper.selectMaterialByMaterialcode(code + code1);
        if (count==null||count==0){
            material.setMaterialcode(code+code1+"0001");
        }else{
            if(count<10){
                material.setMaterialcode(code+code1+"000"+String.valueOf(++count));
            }
            else if(count<100){
                material.setMaterialcode(code+code1+"00"+String.valueOf(++count));
            }
            else if(count<1000){
                material.setMaterialcode(code+code1+"0"+String.valueOf(++count));
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
    public int updateMaterial(Material material)
    {
        return materialMapper.updateMaterial(material);
    }

    /**
     * 删除物料列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterialByIds(String ids)
    {
        return materialMapper.deleteMaterialByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物料列表信息
     * 
     * @param id 物料列表ID
     * @return 结果
     */
    @Override
    public int deleteMaterialById(Integer id)
    {
        return materialMapper.deleteMaterialById(id);
    }
}

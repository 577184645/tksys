package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StoragequitdetailMapper;
import com.ruoyi.system.domain.Storagequitdetail;
import com.ruoyi.system.service.IStoragequitdetailService;
import com.ruoyi.common.core.text.Convert;

/**
 * 退料Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-19
 */
@Service
public class StoragequitdetailServiceImpl implements IStoragequitdetailService 
{
    @Autowired
    private StoragequitdetailMapper storagequitdetailMapper;

    /**
     * 查询退料
     * 
     * @param id 退料ID
     * @return 退料
     */
    @Override
    public Storagequitdetail selectStoragequitdetailById(Long id)
    {
        return storagequitdetailMapper.selectStoragequitdetailById(id);
    }

    /**
     * 查询退料列表
     * 
     * @param storagequitdetail 退料
     * @return 退料
     */
    @Override
    public List<Storagequitdetail> selectStoragequitdetailList(Storagequitdetail storagequitdetail)
    {
        return storagequitdetailMapper.selectStoragequitdetailList(storagequitdetail);
    }

    /**
     * 新增退料
     * 
     * @param storagequitdetail 退料
     * @return 结果
     */
    @Override
    public int insertStoragequitdetail(Storagequitdetail storagequitdetail)
    {
        return storagequitdetailMapper.insertStoragequitdetail(storagequitdetail);
    }

    /**
     * 修改退料
     * 
     * @param storagequitdetail 退料
     * @return 结果
     */
    @Override
    public int updateStoragequitdetail(Storagequitdetail storagequitdetail)
    {
        return storagequitdetailMapper.updateStoragequitdetail(storagequitdetail);
    }

    /**
     * 删除退料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoragequitdetailByIds(String ids)
    {
        return storagequitdetailMapper.deleteStoragequitdetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退料信息
     * 
     * @param id 退料ID
     * @return 结果
     */
    @Override
    public int deleteStoragequitdetailById(Long id)
    {
        return storagequitdetailMapper.deleteStoragequitdetailById(id);
    }

    @Override
    public List<Storagequitdetail> selectStorageindetailByStoragequitbillId(Long storagequitbillid) {
        return storagequitdetailMapper.selectStorageindetailByStoragequitbillId(storagequitbillid);
    }
}

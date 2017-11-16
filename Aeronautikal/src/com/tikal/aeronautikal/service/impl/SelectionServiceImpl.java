package com.tikal.aeronautikal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikal.aeronautikal.dao.SelectionDao;
import com.tikal.aeronautikal.entity.BaseEntity;
import com.tikal.aeronautikal.service.SelectionService;

import java.util.List;
import java.util.Map;
/**
 * @author 
 */
@Service("selectionService")
public class SelectionServiceImpl implements SelectionService {

    @Autowired
    protected SelectionDao selectionDao;

    public <T extends BaseEntity> void save(T object) {
        selectionDao.save(object);
    }

    public <T extends BaseEntity> void delete(T object) {
        selectionDao.delete(object);
    }

    public <T extends BaseEntity> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize, String order) {
        return selectionDao.listObjectByPage(clazz, page, pageSize, order);
    }

    public <T extends BaseEntity> long countAll(Class<T> clazz) {
        return selectionDao.countAll(clazz);
    }

    public <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions) {
        return selectionDao.getUniqueEntity(clazz, conditions);
    }
}

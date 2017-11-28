package com.tikal.aeronautikal.service;

import java.util.List;
import java.util.Map;

import com.tikal.aeronautikal.entity.AeronaveEntity;
import com.tikal.aeronautikal.entity.BaseEntity;


/**
 * Business service for data access
 *
 * @author tikal
 */
public interface AeronaveService {

    /**
     * saves given object in database
     * @param object object to save
     */
    <T extends BaseEntity> void save(T object);

    /**
     * deletes given object from database
     * @param object object to save
     */
    <T extends BaseEntity> void delete(T object);
    
    <T extends BaseEntity> void update(T object);
    
    public void update(AeronaveEntity ae);
    
    public AeronaveEntity consult(long numeroSerie);

    /**
     * gets objects from db for pagination
     * @param clazz     class of object
     * @param page      number of page
     * @param pageSize  size of single page
     * @param order     sorting for query
     * @return          list of objects for given page
     */
 //   <T extends BaseEntity> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize, String order);

    /**
     * counts total number of records in database
     * @param clazz class of object to count
     * @return      amount of records
     */
  //  <T extends BaseEntity> long countAll(Class<T> clazz);

    /**
     * gets entity by given conditions
     * @param clazz         class of object
     * @param conditions    Map with key as a field name and value as a field value
     * @return              Entity by given criteria
     */
    
    <T extends BaseEntity> T getUniqueEntity(Class<T> clazz, Map<String, Object> conditions);

}
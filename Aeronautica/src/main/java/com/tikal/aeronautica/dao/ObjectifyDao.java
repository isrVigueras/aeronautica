package com.tikal.aeronautica.dao;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
//import com.googlecode.objectify.Query;
//import com.googlecode.objectify.helper.DAOBase;
//import com.googlecode.objectify.helper.DAOBase;
//import com.tikal.aeronautica.model.Aeronave;
//import com.googlecode.objectify.ObjectifyService.ofy;


public class ObjectifyDao<Aeronave> 
{
 protected Class<Aeronave> clazz;

 /**
  * We've got to get the associated domain class somehow
  *
  * @param clazz
  */
 protected ObjectifyDao(Class<Aeronave> clazz)
 {
  this.clazz = clazz;
 }

 /*public Key<Aeronave> add(Aeronave entity)

 {
  //Key<Aeronave> key = ofy().load(entity);
  Key<Aeronave> key = ofy().save().entity(entity).now();
  return key;
 }

 public void delete(Aeronave entity)
 {
  //ofy().delete(entity);
	 ofy().delete().entity(entity).now();
 }

 public void delete(Key<Aeronave> entityKey)
 {
  //y().delete(entityKey);
	 ofy().delete().key(entityKey).now();
 }

 public Aeronave get(Long id) throws EntityNotFoundException
 {
	 //Aeronave obj = ofy().load().get(this.clazz, id);
	 Aeronave obj = ofy().load().type(this.clazz).id(id).now();
	
  return obj;
 }*/

 /**
  * Convenience method to get an object matching a single property
  *
  * @param propName
  * @param propValue
  * @return T matching Object
  */
 /*public Aeronave getByProperty(String propName, Object propValue)
 {
  //Query<Aeronave> q = ofy().query(clazz);
	 Aeronave obj = ofy().load().type(this.clazz).filter(propName, propValue).first().now();
  //Aeronave obj = q.filter(propName, propValue).get();
  return obj;
 }

 /*public List<Aeronave> listByProperty(String propName, Object propValue)
 {
  //Query<Aeronave> q = ofy().query(clazz);
 // q.filter(propName, propValue);
	// List<Aeronave> entities = new ArrayList<Aeronave>();
	 Aeronave obj = ofy().load().type(this.clazz).filter(propName, propValue).first().now();
	 
	 
  List<Aeronave> entities = new ArrayList<Aeronave>();
  for (Aeronave entity : obj){
   entities.add(entity);
  }
  
  return entities;
 }

 public T getByExample(T u, String... matchProperties)
 {
  Query<T> q = ofy().query(clazz);
  // Find non-null properties and add to query
  for (String propName : matchProperties)
  {
   Object propValue = getPropertyValue(u, propName);
   q.filter(propName, propValue);
  }
  T obj = q.get();
  return obj;
 }

 public Query<T> listByExample(T u, String... matchProperties)
 {
  Query<T> q = ofy().query(clazz);
  // Find non-null properties and add to query
  for (String propName : matchProperties)
  {
   Object propValue = getPropertyValue(u, propName);
   q.filter(propName, propValue);
  }
  return q;
 }*/

 private Object getPropertyValue(Object obj, String propertyName)
 {
  BeanInfo beanInfo;
  try
  {
   beanInfo = Introspector.getBeanInfo(obj.getClass());
  }
  catch (IntrospectionException e)
  {
   throw new RuntimeException(e);
  }
  PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
  for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
  {
   String propName = propertyDescriptor.getName();
   if (propName.equals(propertyName))
   {
    Method readMethod = propertyDescriptor.getReadMethod();
    try
    {
     Object value = readMethod.invoke(obj, new Object[] {});
     return value;
    }
    catch (IllegalArgumentException e)
    {
     throw new RuntimeException(e);
    }
    catch (IllegalAccessException e)
    {
     throw new RuntimeException(e);
    }
    catch (InvocationTargetException e)
    {
     throw new RuntimeException(e);
    }
   }
  }
  return null;
 }

}
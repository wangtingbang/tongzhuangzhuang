package me.tongzhuangzhuang.support.biz;

import me.tongzhuangzhuang.support.dao.BaseDao;
import me.tongzhuangzhuang.support.dao.BaseEntity;
import me.tongzhuangzhuang.support.dao.DeleteFlag;
import me.tongzhuangzhuang.support.model.BaseModel;
import me.tongzhuangzhuang.support.mybatis.Example;
import me.tongzhuangzhuang.support.mybatis.Pagination;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author dave
 * 
 * @param <M>
 * @param <E>
 * @param <K>
 */
public abstract class CommonBusiness<M extends BaseModel<K>, E extends BaseEntity<K>, K> {

  /**
   * 
   * @param model
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int create(M model) {
    E entity = toEntity(model);
    int i = getBaseDao().insert(entity);
    model.setId(entity.getId());
    getLogger().info("create [{}] row(s) success! [{}]", i, model.getId());
    return i;
  }

  /**
   * 
   * @param model
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int createSelective(M model) {
    E entity = toEntity(model);
    int i = getBaseDao().insertSelective(entity);
    model.setId(entity.getId());
    getLogger().info("createSelective [{}] row(s) success! [{}]", i, model.getId());
    return i;
  }

  /**
   * 
   * @param model
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int delete(M model) {
    int i = getBaseDao().deleteByPrimaryKey(toEntity(model));
    model.setDeleteFlag(DeleteFlag.DELETED);
    getLogger().info("delete [{}] row(s) success! [{}]", i, model.getId());
    return i;
  }

  /**
   * 
   * @param id
   * @param clazz
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int delete(K id, Class<M> clazz) {
    int i = 0;
    try {
      M model = clazz.newInstance();
      model.setId(id);
     i =  delete(model);
    } catch (Exception e) {
      getLogger().error("newInstance fail !");
    }
    return i;
  }

  /**
   * 
   * @param model
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int update(M model) {
    int i = getBaseDao().updateByPrimaryKey(toEntity(model));
    getLogger().info("update [{}] row(s) success! [{}]", i, model.getId());
    return i;
  }

  /**
   * 
   * @param model
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int updateSelective(M model) {
    int i = getBaseDao().updateByPrimaryKeySelective(toEntity(model));
    getLogger().info("updateSelective [{}] row(s) success! [{}]", i, model.getId());
    return i;
  }

  /**
   * 
   * @param model
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public M find(M model) {
    E entity = getBaseDao().selectByPrimaryKey(toEntity(model));
    if(null == entity){
      return null;
    }
    return fromEntity(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public M find(K id, Class<M> clazz) {
    M model = null;
    try {
      model = clazz.newInstance();
      model.setId(id);
      model = find(model);
    } catch (Exception e) {
    }
    return model;
  }

  /**
   * 
   * @param example
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<M> list(Example<E> example) {
    List<E> list = getBaseDao().selectByExample(example);
    List<M> result = new LinkedList<M>();
    for (E e : list) {
      result.add(fromEntity(e));
    }
    return result;
  }

  /**
   * 
   * @param example
   * @param page
   * @param limit
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Pagination<M> listByPage(Example<E> example, int page, int limit) {
    Pagination<M> pagination = new Pagination<M>(page, limit);
    return listByPage(example, pagination);
  }

  /**
   * 
   * @param example
   * @param pagination
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Pagination<M> listByPage(Example<E> example, Pagination<M> pagination) {
    int cnt = count(example);
    pagination.setTotal(cnt);
    List<E> list = getBaseDao().selectByExample(example, pagination.getRowBounds());
    List<M> result = new LinkedList<M>();
    for (E e : list) {
      result.add(fromEntity(e));
    }
    pagination.setResult(result);
    return pagination;
  }

  /**
   * 
   * @param example
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public int count(Example<E> example) {
    return getBaseDao().countByExample(example);
  }

  protected abstract E toEntity(M model);

  protected abstract M fromEntity(E entity);

  protected abstract BaseDao<E> getBaseDao();

  protected abstract Logger getLogger();

}

package me.tongzhuangzhuang.support.dao;

import me.tongzhuangzhuang.support.mybatis.Example;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 
 * @author dave
 *
 * @param <T>
 */
public interface BaseDao<T> {

  /**
   * @param example
   * @return
   */
  int countByExample(Example<T> example);

  /**
   * @return
   */
  int deleteByPrimaryKeyHard(T record);

  /**
   * @return
   */
  int deleteByPrimaryKey(T record);
  
  /**
   * @param record
   * @return
   */
  int insert(T record);

  /**
   * @param record
   * @return
   */
  int insertSelective(T record);

  /**
   * @param example
   * @return
   */
  List<T> selectByExample(Example<T> example);

  /**
   * @param rowBounds
   * @return
   */
  List<T> selectByExample(Example<T> example, RowBounds rowBounds);

  /**
   * @return
   */
  T selectByPrimaryKey(T record);

  /**
   * @param record
   * @return
   */
  int updateByPrimaryKeySelective(T record);

  /**
   * @param record
   * @return
   */
  int updateByPrimaryKey(T record);

}

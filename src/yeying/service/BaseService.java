package yeying.service;

import org.springframework.transaction.annotation.Transactional;
import yeying.Interf.BaseDataInterFace;
import yeying.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import yeying.utils.EntityWrapper;
import yeying.utils.Page;

import java.util.List;

/**
 * Author maple
 *
 * @date 2018/2/3 0003 21:19
 */
/**
 * 方法说明看这里
 * 太长了，基本都是调用baseDao的方法，所以说明去BaseDao里看吧emm
 *
 *
 */
public class BaseService<M extends BaseDao<T>,T> implements BaseDataInterFace<T> {

    @Autowired
    protected M dao;


    /**
     * 用法: String sql="select obj from User as obj where obj.id=:id"
     * getQueryByHql(hql,id);
     *
     * @param hql    需要查询的hql语句
     * @param values 通过setParameter注入的语句
     * @return
     */
    @Override
    public List<T> queryByHql(String hql, Object... values) {
       return dao.queryByHql(hql,values);
    }


    /**
     * 根据class类和Id查询数据
     *
     * @param id
     * @return
     */
    @Override
    public T queryById(long id) {
        return dao.queryById(id);
    }


    /**
     * 根据class类和Id查询数据
     *
     * @param id
     * @return
     */
    @Override
    public T queryById(int id) {
        return dao.queryById(id);
    }

    /**
     * 根据类对象与ID删除数据
     */
    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    /**
     * 根据条件类封装的条件查询
     * @param wrapper 自己封装的条件查询Wrapper
     * @return
     */
    @Override
    public List<T> query(EntityWrapper wrapper) {
        return dao.query(wrapper);
    }

    /**'
     * 根据条件删除数据库对象（慎用）
     * @param wrapper 自己封装的条件查询Wrapper
     */
    @Override
    public void delete(EntityWrapper wrapper) {
dao.delete(wrapper);
    }



    @Override
    public void update(T entity) {
dao.update(entity);
    }


    /**'
     * 删除当前对象
     */
    @Override
    public void delete(T entity) {
dao.delete(entity);
    }

    /**'
     * 保存
     */
    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    /**
     * 根据当前条件封装类查找分页类
     * @param entityWrapper
     * @param currentPage 要查询第几页
     * @param limit  每页显示几条记录
     * @return
     */
    @Override
    public Page<T> queryPage(EntityWrapper entityWrapper, int currentPage, int limit) {
        return dao.queryPage(entityWrapper,currentPage,limit);
    }

    /**
     * 根据当前条件封装类查找分页类
     * @param entityWrapper
     * @param currentPage 要查询第几页,每页20条
     * @param
     * @return
     */
    @Override
    public Page<T> queryPage(EntityWrapper entityWrapper, int currentPage) {
        return dao.queryPage(entityWrapper,currentPage);
    }


    /**
     *
      * @param sql
     * @param offset 从第几条开始查询
     * @param limit  每页显示几条记录
     * @return
     */
    @Override
    public List<T> queryList(String sql, int offset, int limit) {
        return dao.queryList(sql,offset,limit);
    }

    /**
     *
     * @param sql
     * @param offset 从第几条开始查询,默认每页20条
     * @return
     */
    @Override
    public List<T> queryList(String sql, int offset) {
        return dao.queryList(sql,offset);
    }

    /**
     * 执行自定义hql语句
     * @param hql
     */
    @Override
    public void executeUpdateOrDeleteByHql(String hql) {
        dao.executeUpdateOrDeleteByHql(hql);
    }
}

package yeying.service;

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



    @Override
    public List<T> queryByHql(String hql, String... values) {
       return dao.queryByHql(hql,values);
    }

    @Override
    public T queryById(long id) {
        return dao.queryById(id);
    }

    @Override
    public T queryById(int id) {
        return dao.queryById(id);
    }

    @Override
    public List<T> query(EntityWrapper wrapper) {
        return dao.query(wrapper);
    }

    @Override
    public void delete(EntityWrapper wrapper) {
dao.delete(wrapper);
    }



    @Override
    public void update(T entity) {
dao.update(entity);
    }

    @Override
    public void delete(T entity) {
dao.delete(entity);
    }

    @Override
    public Page<T> queryPage(EntityWrapper entityWrapper, int currentPage, int limit) {
        return dao.queryPage(entityWrapper,currentPage,limit);
    }

    @Override
    public Page<T> queryPage(EntityWrapper entityWrapper, int currentPage) {
        return dao.queryPage(entityWrapper,currentPage);
    }

    @Override
    public List<T> queryList(String sql, int offset, int limit) {
        return dao.queryList(sql,offset,limit);
    }

    @Override
    public List<T> queryList(String sql, int offset) {
        return dao.queryList(sql,offset);
    }
}

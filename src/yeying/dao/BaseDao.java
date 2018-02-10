package yeying.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import yeying.Interf.BaseDataInterFace;
import yeying.utils.EntityWrapper;
import yeying.utils.Page;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Author maple
 *
 * @date 2018/2/2 0002 20:24
 * @T 实体类
 */
public class BaseDao<T> implements BaseDataInterFace<T>{
    @Autowired
    private HibernateTemplate hibernateTemplate;

    private Class<T> clz;

    /**
     * 获取T类的class
     */
    private Class<T> getClz() {
        if (clz == null) {
            System.out.println(this.getClass().getGenericSuperclass());
            //获取泛型的Class对象
            clz = ((Class<T>)
                    (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }

    /**
     * 用法: String sql="select obj from User as obj where obj.id=:id"
     * getQueryByHql(hql,id);
     *
     * @param hql    需要查询的hql语句
     * @param values 通过setParameter注入的语句
     * @return
     */
    public List<T> queryByHql(String hql, String... values) {
        return (List<T>) hibernateTemplate.find(hql, values);
    }

    /**
     * 根据class类和Id查询数据
     *
     * @param id
     * @return
     */
    public T queryById(long id) {

        return hibernateTemplate.get(getClz(), id);
    }

    public T queryById(int id) {
        return hibernateTemplate.get(getClz(), id);
    }

    @Override
    public List<T> query(EntityWrapper wrapper) {
        return (List<T>) hibernateTemplate.find(wrapper.getHql());
    }

    /**'
     * 根据条件删除数据库对象（慎用）
     * @param wrapper 自己封装的条件查询Wrapper
     */
    @Override
    public void delete(EntityWrapper wrapper) {
 Session session=hibernateTemplate.getSessionFactory().openSession();
Query query=session.createQuery(wrapper.getDeleteHql());
query.executeUpdate();
    }

    /**
     * 更新数据
     *
     * @param entity
     */
    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    /**
     * 删除数据
     */
    public void delete(T entity) {
        hibernateTemplate.delete(entity);
    }

    @Override
    public Page<T> queryPage(EntityWrapper entityWrapper, int currentPage, int limit) {
//查询总记录数
        Session session=hibernateTemplate.getSessionFactory().openSession();
        Query query=session.createQuery(entityWrapper.getCountHql());
        long count= (long) query.uniqueResult();
        //创建通用Page类
        Page<T> page=new Page<T>((int) count,currentPage,limit);
        //设置第一条查询记录
        List<T> list=wrapperPage(entityWrapper.getHql(),session,page.getOffset(),page.getLimit());
        page.setList(list);
        return page;
    }

    @Override
    public Page<T> queryPage(EntityWrapper entityWrapper, int currentPage) {
        Session session=hibernateTemplate.getSessionFactory().openSession();
        Query query=session.createQuery(entityWrapper.getCountHql());
        long count= (long) query.uniqueResult();
        //创建通用Page类
        Page<T> page=new Page<T>((int) count,currentPage);
        //设置第一条查询记录
        List<T> list=wrapperPage(entityWrapper.getHql(),session,page.getOffset(),page.getLimit());
        page.setList(list);
        return page;
    }

    @Override
    public List<T> queryList(String hql, int offset, int limit) {
        Session session=hibernateTemplate.getSessionFactory().openSession();
        List<T> list=wrapperPage(hql,session,offset,limit);
        return list;
    }

    @Override
    public List<T> queryList(String hql, int offset) {
        Session session=hibernateTemplate.getSessionFactory().openSession();
        List<T> list=wrapperPage(hql,session,offset,20);
        return list;
    }

    /**
     * 根据类对象与ID删除数据
     */
    public void deleteById( long id) {
        T m = queryById(id);
        hibernateTemplate.delete(m);
    }

    /**
     * 因为hibernatetemplate不支持分页，故使用query进行分页
     * @param hql
     * @param session
     * @param offset
     * @param limit
     */
    private List<T> wrapperPage(String hql,Session session,int offset,int limit){
        Query query = session.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<T> list = query.list();
        return list;
    }



}

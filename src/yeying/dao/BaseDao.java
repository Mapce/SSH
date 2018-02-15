package yeying.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import yeying.Interf.BaseDataInterFace;
import yeying.utils.EntityWrapper;
import yeying.utils.Page;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.sql.Wrapper;
import java.util.List;

/**
 * Author maple
 *
 * @date 2018/2/2 0002 20:24
 * @T 实体类
 */

public class BaseDao<T> extends HibernateDaoSupport implements BaseDataInterFace<T>{

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
    public List<T> queryByHql(String hql, Object... values) {
        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();

        return (List<T>) getHibernateTemplate().find(hql,values);
    }

    /**
     * 根据class类和Id查询数据
     *
     * @param id
     * @return
     */
    public T queryById(long id) {
Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
        return session.get(getClz(),id);
    }

    public T queryById(int id) {
        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
        return session.get(getClz(),(long)id);
    }


    /**
     * 根据条件类封装的条件查询
     * @param wrapper 自己封装的条件查询Wrapper
     * @return
     */


    @Override
    public List<T> query(EntityWrapper wrapper) {
        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
        org.hibernate.query.Query queryObject = session.createQuery(wrapper.getHql());
        List<T> list=queryObject.list();
        return list;
    }

    /**'
     * 根据条件删除数据库对象（慎用）
     * @param wrapper 自己封装的条件查询Wrapper
     */
    @Override
    public void delete(EntityWrapper wrapper) {
        List<T> list=query(wrapper);
        getHibernateTemplate().deleteAll(list);
    }

    /**
     * 更新数据
     *
     * @param entity
     */
    public void update(T entity) {
        getHibernateTemplate().getSessionFactory().getCurrentSession().update(entity);
    }

    /**
     * 删除数据
     */
    public void delete(T entity) {
        getHibernateTemplate().getSessionFactory().getCurrentSession().delete(entity);
    }

    @Override
@Transactional
    public void save(T entity) {
//        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
////      Transaction transactional=  session.beginTransaction();
////        session.save(entity);
////        session.flush();
////        transactional.commit();
        getHibernateTemplate().save(entity);
    }

    /**
     *根据条件查询类查询分页类
     * @param currentPage 要查询第几页
     * @param limit  每页显示几条记录
     * @return
     */
    @Override
    public Page<T> queryPage(EntityWrapper entityWrapper, int currentPage, int limit) {
//查询总记录数
        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query=session.createQuery(entityWrapper.getCountHql());
        long count= (long) query.uniqueResult();
        //创建通用Page类
        Page<T> page=new Page<T>((int) count,currentPage,limit);
        //设置第一条查询记录
        List<T> list=wrapperPage(entityWrapper.getHql(),session,page.getOffset(),page.getLimit());
        page.setList(list);
        return page;
    }

    /**
     *根据条件查询类查询分页类
     * @param currentPage 要查询第几页,默认每页20条
     * @param
     * @return
     */
    @Override
    public Page<T> queryPage(EntityWrapper entityWrapper, int currentPage) {
        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query=session.createQuery(entityWrapper.getCountHql());
        long count= (long) query.uniqueResult();
        //创建通用Page类
        Page<T> page=new Page<T>((int) count,currentPage);
        //设置第一条查询记录
        List<T> list=wrapperPage(entityWrapper.getHql(),session,page.getOffset(),page.getLimit());
        page.setList(list);
        return page;
    }
    /**
     *根据自己拼接的hQL查询
     * @param offset 从第几条开始查询
     * @param limit  每页显示几条记录
     * @return
     */
    @Override
    public List<T> queryList(String hql, int offset, int limit) {
        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
        List<T> list=wrapperPage(hql,session,offset,limit);
        return list;
    }

    /**
     *根据自己拼接的hql查询
     * @param offset 从第几条开始查询,默认每页20条
     * @param
     * @return
     */
    @Override
    public List<T> queryList(String hql, int offset) {
        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
        List<T> list=wrapperPage(hql,session,offset,20);
        return list;
    }

    @Override
    public void executeUpdateOrDeleteByHql(String hql) {
Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
Query query=session.createQuery(hql);
query.executeUpdate();

    }



    /**
     * 根据类对象与ID删除数据
     */
    @Override
    public void deleteById(long id) {
        T m = queryById(id);
        getHibernateTemplate().getSessionFactory().getCurrentSession().delete(m);
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

    @Resource(name="sessionFactory")
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }


}

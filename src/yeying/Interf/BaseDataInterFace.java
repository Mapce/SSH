package yeying.Interf;

import yeying.utils.EntityWrapper;
import yeying.utils.Page;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * Author maple
 *
 * @date 2018/2/3 0003 21:19
 */
public interface BaseDataInterFace<T> {

    public List<T> queryByHql(String hql, Object... values);
    /**
     * 根据class类和Id查询数据
     *
     * @param id
     * @return
     */
    public T queryById(long id) ;

    public T queryById(int id);

    public void deleteById(long id);

    /**
     * 根据条件查询
     * @param wrapper 自己封装的条件查询Wrapper
     * @return
     */
    public List<T> query(EntityWrapper wrapper);

    /**
     * 根据条件删除
     * @param wrapper 自己封装的条件查询Wrapper
     * @return
     */
    public void delete(EntityWrapper wrapper);


    /**
     * 更新数据
     *
     * @param entity
     */
    public void update(T entity);

    /**
     * 删除数据
     */
    public void delete(T entity);

    /**
     * 保存到数据库
     */
    public void save(T entity);

    /**
     * 根据类对象与ID删除数据
     */


    /**
     *根据条件查询类查询分页类
     * @param currentPage 要查询第几页
     * @param limit  每页显示几条记录
     * @return
     */

    public Page<T> queryPage(EntityWrapper entityWrapper,int currentPage,int limit);

    /**
     *根据条件查询类查询分页类
     * @param currentPage 要查询第几页,默认每页20条
     * @param
     * @return
     */

    public Page<T> queryPage(EntityWrapper entityWrapper,int currentPage);

    /**
     *根据自己拼接的hQL查询
     * @param offset 从第几条开始查询
     * @param limit  每页显示几条记录
     * @return
     */

    public List<T> queryList(String sql,int offset,int limit);

    /**
     *根据自己拼接的hql查询
     * @param offset 从第几条开始查询,默认每页20条
     * @param
     * @return
     */
    public List<T> queryList(String hql,int offset);

    /**
     *  根据hql执行插入或者删除
     * @param hql
     * @return
     */
    public void executeUpdateOrDeleteByHql(String hql);

}

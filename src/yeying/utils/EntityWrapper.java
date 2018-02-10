package yeying.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;

/**
 * Author maple
 *
 * @date 2018/2/3 0003 23:22
 */
public class EntityWrapper {
    private Class clz;
    StringBuffer where=new StringBuffer(" where 1=1");


    public EntityWrapper(Class clz){
        this.clz=clz;
    }


private String orderType="desc";
    private String orderBy="";
  public void addAnd(String attribute,String value,String experience){
      where.append(" and obj."+attribute +" " +experience+" "+value);
  }
  public void addOr(String attribute,String value,String experience){
      where.append(" or obj."+attribute +" " +experience+" "+value);
  }
    /**
     *
     * @param type 排序的顺序 只能取 "desc" 降序 或者"asc" 升序
     */
  public void setOrderType(String type){
   if(!StringUtils.isEmpty(type))
      orderType=type;
  }

    /**
     * 按照什么属性排序,用法: setOrderBy("id","addTime","name")
     * 则优先按照id排序，如id一样 再按照addTime排序，以此类推
     * @param orderBy
     */
  public void setOrderBy(String... orderBy){
       for(String s:orderBy){
           if(!StringUtils.isEmpty(s)){
               this.orderBy+="obj."+s+" ";
           }
       }
  }

    /**
     * 返回组装后的sql,这是baseDao要用的方法.
     * @return
     */
  public String getHql(){
       if(!StringUtils.isEmpty(orderBy)){
           return "select obj from "+clz.getSimpleName()+" as obj "+where+" order by "+orderBy+" "+orderType;
       }
      return "select obj from "+clz.getSimpleName()+" as obj "+where;
  }

    /**
     * 返回组装条件后删除符合条件的hql
     * @return
     */
  public String getDeleteHql(){


        return "delete obj from "+clz.getSimpleName()+where;
    }

    /**
     * 返回查询符合条件实体数目的hql
     * @return
     */
    public String getCountHql(){
        return "select count(obj) from "+clz.getSimpleName()+" as obj "+where;
    }

}

package yeying.utils;

import java.util.List;

/**
 * Author maple
 * 通用分页类
 *
 * @date 2018/2/9 0009 19:37
 */
public class Page<T> {
  private static int PAGE_SIZE=20;


    private List<T> list; //对象记录结果集

    private int total = 0; // 总记录数

    private int limit = PAGE_SIZE; // 每页显示记录数

    private int totalPage = 1; // 总页数

    private int currentPage = 1; // 当前页



    private boolean isFirstPage=false;        //是否为第一页

    private boolean isLastPage=false;         //是否为最后一页

    private boolean hasPreviousPage=false;   //是否有前一页

    private boolean hasNextPage=false;       //是否有下一页



    private int navigatePages=8; //导航页码数

    private int[] navigatePageNumbers;  //所有导航页号



    public Page(int total, int currentPage) {

        init(total, currentPage, limit);

    }



    public Page(int total, int currentPage, int limit) {

        init(total, currentPage, limit);

    }



    private void init(int total, int currentPage, int limit){

        //设置基本参数

        this.total=total;

        this.limit=limit;

        this.totalPage=(this.total-1)/this.limit+1;



        //根据输入可能错误的当前号码进行自动纠正

        if(currentPage<1){

            this.currentPage=1;

        }else if(currentPage>this.totalPage){

            this.currentPage=this.totalPage;

        }else{

            this.currentPage=currentPage;

        }



        //基本参数设定之后进行导航页面的计算

        calcNavigatePageNumbers();



        //以及页面边界的判定

        judgePageBoudary();

    }



    /**

     * 计算导航页

     */

    private void calcNavigatePageNumbers(){

        //当总页数小于或等于导航页码数时

        if(totalPage<=navigatePages){

            navigatePageNumbers=new int[totalPage];

            for(int i=0;i<totalPage;i++){

                navigatePageNumbers[i]=i+1;

            }

        }else{ //当总页数大于导航页码数时

            navigatePageNumbers=new int[navigatePages];

            int startNum=currentPage-navigatePages/2;

            int endNum=currentPage+navigatePages/2;



            if(startNum<1){

                startNum=1;

                //(最前navPageCount页

                 for(int i=0;i<navigatePages;i++){

                    navigatePageNumbers[i]=startNum++;

                }

            }else if(endNum>totalPage){

                endNum=totalPage;

                //最后navPageCount页

                for(int i=navigatePages-1;i>=0;i--){

                    navigatePageNumbers[i]=endNum--;

                }

            }else{

                //所有中间页

                for(int i=0;i<navigatePages;i++){

                    navigatePageNumbers[i]=startNum++;

                }

            }

        }

    }



    /**

     * 判定页面边界

     */

    private void judgePageBoudary(){

        isFirstPage = currentPage == 1;

        isLastPage = currentPage == totalPage && currentPage!=1;

        hasPreviousPage = currentPage!=1;

        hasNextPage = currentPage!=totalPage;

    }





    public void setList(List<T> list) {

        this.list = list;

    }



    /**

     * 得到当前页的内容

     * @return {List}

     */

    public List<T> getList() {

        return list;

    }



    /**

     * 得到记录总数

     * @return {int}

     */

    public int getTotal() {

        return total;

    }



    /**

     * 得到每页显示多少条记录

     * @return {int}

     */

    public int getLimit() {

        return limit;

    }



    /**

     * 得到页面总数

     * @return {int}

     */

    public int getPages() {

        return totalPage;

    }



    /**

     * 得到当前页号

     * @return {int}

     */

    public int getCurrentPage() {

        return currentPage;

    }

    /**
     * 根据当前页和limit得到应该从第几条记录开始
     * @return
     */
    public int getOffset(){
        return (this.currentPage-1)*limit;
    }



    /**

     * 得到所有导航页号

     * @return {int[]}

     */

    public int[] getNavigatePageNumbers() {

        return navigatePageNumbers;

    }



    public boolean isFirstPage() {

        return isFirstPage;

    }



    public boolean isLastPage() {

        return isLastPage;

    }



    public boolean hasPreviousPage() {

        return hasPreviousPage;

    }



    public boolean hasNextPage() {

        return hasNextPage;

    }





    public String toString(){

        String str=new String();

        str= "[" +

                "total="+total+

                ",totalPage="+totalPage+

                ",currentPage="+currentPage+

                ",limit="+limit+

                //",navigatePages="+navigatePages+

                ",isFirstPage="+isFirstPage+

                ",isLastPage="+isLastPage+

                ",hasPreviousPage="+hasPreviousPage+

                ",hasNextPage="+hasNextPage+

                ",navigatePageNumbers=";

        int len=navigatePageNumbers.length;

        if(len>0)str+=(navigatePageNumbers[0]);

        for(int i=1;i<len;i++){

            str+=(" "+navigatePageNumbers[i]);

        }

        //sb+=",list="+list;

        str+="]";

        return str;

    }



}

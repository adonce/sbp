/*
 *
 * This file is generated under this project, "com.lguplus.ipasms.web".
 *
 * Date  : 2016. 9. 6. 오후 4:21:27
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package kr.co.adonce.pagination;

/**
 * 
 * @since 2016. 9. 6.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface IPaginationResult<T> {

    /**
     * 한 페이지당 보여질 아이템 개수를 반환한다.
     * 
     * @return
     *
     * @since 2016. 9. 6.
     */
    public int getItemCountPerPage();

    /**
     * 아이템을 반환한다.
     * 
     * @return
     *
     * @since 2016. 9. 6.
     */
    public T getItems();

    /**
     * 현재 페이지 번호를 반환한다.
     * 
     * @return
     *
     * @since 2016. 9. 6.
     */
    public int getPageNum();

    /**
     * 전체 아이템 개수를 반환한다.
     * 
     * @return
     *
     * @since 2016. 9. 6.
     */
    public int getTotalCount();

}
package moweifeng.repository;

import moweifeng.entities.Book;

import java.util.List;

/**
 * 图书数据访问层
 */
public interface BookRepository {
    /*
     * 添加图书信息
     */
    void save(Book book);
   /*
    *  根据图书编号删除图书信息
    */
    void deleteById(int id);
    /*
     *  修改图书信息
     */
    void update(Book book);
    /*
     *  根据图书编号查询图书信息
     */
    Book selectById(int id);
    /*
     * 查询所有的图书信息
     */
    List<Book> selectAll(int start,int limit);
    /*
     *获取图书的数量
     */
    int selectBooksCount();
    /*
     * 定义获取所有图书的数量的方法  用于导出数据使用
     */
    List<Book> selectAllBooksForPoi();
}

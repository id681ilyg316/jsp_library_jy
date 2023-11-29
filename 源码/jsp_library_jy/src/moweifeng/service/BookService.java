package moweifeng.service;

import moweifeng.entities.Book;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * 图书逻辑控制层
 */
public interface BookService {
    /*
     * 查询所有的图书信息
     */
    List<Book> findAllBooks(int page,int limit);
    int findBookCount();
    Book findBookById(int bookId);
    void addBook(Book book);
    void modifyBook(Book book);
    void deleteBookById(int bookId);
    HSSFWorkbook findWorkBook();
}

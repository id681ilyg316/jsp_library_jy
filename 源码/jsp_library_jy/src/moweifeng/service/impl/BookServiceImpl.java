package moweifeng.service.impl;

import moweifeng.entities.Book;
import moweifeng.entities.BookCase;
import moweifeng.repository.BookAdminRepository;
import moweifeng.repository.BookRepository;
import moweifeng.repository.impl.BookAdminRepositoryImpl;
import moweifeng.repository.impl.BookRepositoryImpl;
import moweifeng.service.BookService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 *：图书逻辑控制层实现类
 */
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookRepositoryImpl();
    @Override
    public List<Book> findAllBooks(int page,int limit) {
        int start = (page - 1)*limit;
        return this.bookRepository.selectAll(start,limit);
    }

    @Override
    public int findBookCount() {
        return this.bookRepository.selectBooksCount();
    }

    @Override
    public Book findBookById(int bookId) {
        return bookRepository.selectById(bookId);
    }

    @Override
    public void addBook(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void modifyBook(Book book) {
        this.bookRepository.update(book);
    }

    @Override
    public void deleteBookById(int bookId) {
        this.bookRepository.deleteById(bookId);
    }

    @Override
    public HSSFWorkbook findWorkBook() {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("图书信息表");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("图书名称");
        cell = row.createCell(1);
        cell.setCellValue("图书作者");
        cell = row.createCell(2);
        cell.setCellValue("图书页码");
        cell = row.createCell(3);
        cell.setCellValue("图书价格");
        cell = row.createCell(4);
        cell.setCellValue("出版社");
        cell = row.createCell(5);
        cell.setCellValue("图书类别");
        List<Book> bookList = this.bookRepository.selectAllBooksForPoi();
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellValue(book.getName());
            cell = row.createCell(1);
            cell.setCellValue(book.getAuthor());
            cell = row.createCell(2);
            cell.setCellValue(book.getPages());
            cell = row.createCell(3);
            cell.setCellValue(book.getPrice());
            cell = row.createCell(4);
            cell.setCellValue(book.getPublish());
            cell = row.createCell(5);
            cell.setCellValue(book.getBookCase() == null ? null : book.getBookCase().getName());
        }
        return hssfWorkbook;
    }
}

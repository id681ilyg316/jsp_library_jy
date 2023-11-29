package moweifeng.service;

import moweifeng.entities.Borrow;
import moweifeng.entities.Reader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface ReaderService {
    List<Borrow> findBorrowsByReaderId(int readerId,int page,int limit);

    /**
     * 获取所有未审核的借阅图书的总数
     */
    int findBorrowsNum();
    int findBorrowsCountByReaderId(int readerId);
    void addBorrow(Borrow borrow);
    Borrow findBorrowById(int borrowId);




    List<Reader> findAllReaders(int page,int limit);
    int findReadersCount();


    Reader findReaderById(int readerId);
    void modifyReader(Reader reader);
    void removeReaderById(int readerId);
    void addReader(Reader reader);

    List<Borrow> findAllBorrows();

    Borrow findBorrowByIds(int bookId, int readerId, int state);
    HSSFWorkbook findReadersRecords(int readerId);
}

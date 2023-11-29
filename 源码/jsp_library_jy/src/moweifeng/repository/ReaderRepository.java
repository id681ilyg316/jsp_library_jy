package moweifeng.repository;

import moweifeng.entities.Borrow;
import moweifeng.entities.Reader;

import java.util.List;

public interface ReaderRepository {
    List<Borrow> selectBorrowsByReaderId(int readerId,int start,int limit);
    List<Borrow> selectAllBorrows();
    int selectBorrowsCountByReaderId(int readerId);
    int selectBorrowCountsByState();
    void saveBorrow(Borrow borrow);
    Borrow selectBorrowById(int borrowId);

    List<Reader> selectReaders(int start,int limit);
    int selectReaderCount();

    Reader selectReaderById(int readerId);
    void updateReader(Reader reader);
    void deleteReaderById(int readerId);
    void saveReader(Reader reader);

    Borrow selectBorrowByIds(int bookId, int readerId, int state);
    List<Borrow> selectAllBorrowByReaderId(int readerId);
}

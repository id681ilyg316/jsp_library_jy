package moweifeng.service;

import moweifeng.entities.BookCase;
import moweifeng.entities.Borrow;
import moweifeng.entities.ReturnBook;
import moweifeng.vo.BookBarData;
import moweifeng.vo.BookPieData;

import java.util.List;

public interface BookAdminService {
    void addBookCase(BookCase bookCase);
    void removeBookCaseById(int bookCaseId);
    void modifyBookCase(BookCase bookCase);
    BookCase findBookCaseById(int bookCaseId);
    List<BookCase> findAllBookCase();
    List<BookCase> findAllBookCaseByPage(int page,int limit);

    int findAllCount();

    void modifyBorrow(Borrow borrow);
    void addReturnBook(ReturnBook returnBook);

    List<ReturnBook> findAllReturns();

    int findReturnCount();

    ReturnBook findReturnById(int returnId);

    void modifyReturn(ReturnBook returnBook);
    List<BookPieData> findBookDataForPie();

    BookBarData findBookDataForBar();
}

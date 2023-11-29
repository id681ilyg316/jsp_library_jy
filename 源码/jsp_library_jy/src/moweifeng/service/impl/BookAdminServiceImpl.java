package moweifeng.service.impl;

import moweifeng.entities.BookCase;
import moweifeng.entities.Borrow;
import moweifeng.entities.ReturnBook;
import moweifeng.repository.BookAdminRepository;
import moweifeng.repository.impl.BookAdminRepositoryImpl;
import moweifeng.service.BookAdminService;
import moweifeng.vo.BookBarData;
import moweifeng.vo.BookPieData;

import java.util.List;

public class BookAdminServiceImpl implements BookAdminService {
    private BookAdminRepository bookAdminRepository = new BookAdminRepositoryImpl();

    @Override
    public void addBookCase(BookCase bookCase) {
        this.bookAdminRepository.saveBookCase(bookCase);
    }

    @Override
    public void removeBookCaseById(int bookCaseId) {
        this.bookAdminRepository.deleteBookCaseById(bookCaseId);
    }

    @Override
    public void modifyBookCase(BookCase bookCase) {
        this.bookAdminRepository.updateBookCase(bookCase);
    }

    @Override
    public BookCase findBookCaseById(int bookCaseId) {
        return this.bookAdminRepository.selectBookCaseById(bookCaseId);
    }

    @Override
    public List<BookCase> findAllBookCase() {
        return this.bookAdminRepository.selectAllBookCases();
    }

    @Override
    public List<BookCase> findAllBookCaseByPage(int page, int limit) {
        int start = (page - 1)*limit;
        return this.bookAdminRepository.selectAllBookCasesByPage(start,limit);
    }

    @Override
    public int findAllCount() {
        return this.bookAdminRepository.selectAllCount();
    }


    @Override
    public void modifyBorrow(Borrow borrow) {
        this.bookAdminRepository.updateBorrow(borrow);
    }

    @Override
    public void addReturnBook(ReturnBook returnBook) {
        this.bookAdminRepository.saveReturnBook(returnBook);
    }


    @Override
    public List<ReturnBook> findAllReturns() {
        return this.bookAdminRepository.selectAllReturns();
    }

    @Override
    public int findReturnCount() {
        return this.bookAdminRepository.selectReturnCount();
    }

    @Override
    public ReturnBook findReturnById(int returnId) {
        return this.bookAdminRepository.selectReturnById(returnId);
    }

    @Override
    public void modifyReturn(ReturnBook returnBook) {
        this.bookAdminRepository.updateReturn(returnBook);
    }

    @Override
    public List<BookPieData> findBookDataForPie() {
        return this.bookAdminRepository.selectBookPieData();
    }

    @Override
    public BookBarData findBookDataForBar() {
        return this.bookAdminRepository.selectBookBarData();
    }
}

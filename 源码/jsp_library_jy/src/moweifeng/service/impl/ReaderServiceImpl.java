package moweifeng.service.impl;

import moweifeng.entities.Borrow;
import moweifeng.entities.Reader;
import moweifeng.repository.ReaderRepository;
import moweifeng.repository.impl.ReaderRepositoryImpl;
import moweifeng.service.ReaderService;
import moweifeng.vo.BorrowDTO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    @Override
    public List<Borrow> findBorrowsByReaderId(int readerId,int page,int limit) {
        int start = (page - 1)*limit;
        return this.readerRepository.selectBorrowsByReaderId(readerId,start,limit);
    }

    @Override
    public int findBorrowsCountByReaderId(int readerId) {
        return this.readerRepository.selectBorrowsCountByReaderId(readerId);
    }
    @Override
    public List<Borrow> findAllBorrows() {
        return this.readerRepository.selectAllBorrows();
    }

    @Override
    public int findBorrowsNum() {
        return this.readerRepository.selectBorrowCountsByState();
    }

    @Override
    public void addBorrow(Borrow borrow) {
        this.readerRepository.saveBorrow(borrow);
    }

    @Override
    public Borrow findBorrowById(int borrowId) {
        return this.readerRepository.selectBorrowById(borrowId);
    }



    @Override
    public List<Reader> findAllReaders(int page, int limit) {
        int start = (page - 1)*limit;
        return this.readerRepository.selectReaders(start,limit);
    }

    @Override
    public int findReadersCount() {
        return this.readerRepository.selectReaderCount();
    }

    @Override
    public Reader findReaderById(int readerId) {
        return this.readerRepository.selectReaderById(readerId);
    }

    @Override
    public void modifyReader(Reader reader) {
        this.readerRepository.updateReader(reader);
    }

    @Override
    public void removeReaderById(int readerId) {
        this.readerRepository.deleteReaderById(readerId);
    }

    @Override
    public void addReader(Reader reader) {
        this.readerRepository.saveReader(reader);
    }

    @Override
    public Borrow findBorrowByIds(int bookId, int readerId, int state) {
    	Borrow brBorrow = null;
    	if(this.readerRepository.selectBorrowByIds(bookId,readerId,0) != null) {
    		brBorrow = this.readerRepository.selectBorrowByIds(bookId,readerId,0);
    	}else if(this.readerRepository.selectBorrowByIds(bookId,readerId,4) != null){
    		brBorrow = this.readerRepository.selectBorrowByIds(bookId,readerId,4);
    	}else if(this.readerRepository.selectBorrowByIds(bookId,readerId,5) != null){
    		brBorrow = this.readerRepository.selectBorrowByIds(bookId,readerId,5);
    	}
        return brBorrow;
    }

    @Override
    public HSSFWorkbook findReadersRecords(int readerId) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("借阅图书记录表");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("图书名称");
        cell = row.createCell(1);
        cell.setCellValue("借阅人");
        cell = row.createCell(2);
        cell.setCellValue("借阅时间");
        cell = row.createCell(3);
        cell.setCellValue("还书时间");
        cell = row.createCell(4);
        cell.setCellValue("状态");
        List<Borrow> borrowList = this.readerRepository.selectAllBorrowByReaderId(readerId);
        BorrowDTO borrowDTO = null;
        for (int i = 0; i < borrowList.size(); i++) {
            borrowDTO = new BorrowDTO();
            borrowDTO.setBook(borrowList.get(i).getBook());
            borrowDTO.setBookAdmin(borrowList.get(i).getBookAdmin());
            borrowDTO.setBorrowtime(borrowList.get(i).getBorrowtime());
            borrowDTO.setReader(borrowList.get(i).getReader());
            borrowDTO.setReturntime(borrowList.get(i).getReturntime());
            int state = borrowList.get(i).getState();
            switch (state){
                case 0:
                    borrowDTO.setState("未审核");
                    break;
                case 1:
                    borrowDTO.setState("审核通过");
                    break;
                case 2:
                    borrowDTO.setState("审核未通过");
                    break;
                case 3:
                    borrowDTO.setState("已归还");
                    break;
            }
            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellValue(borrowDTO.getBook().getName());
            cell = row.createCell(1);
            cell.setCellValue(borrowDTO.getReader().getName());
            cell = row.createCell(2);
            cell.setCellValue(borrowDTO.getBorrowtime());
            cell = row.createCell(3);
            cell.setCellValue(borrowDTO.getReturntime());
            cell = row.createCell(4);
            cell.setCellValue(borrowDTO.getState());
        }
        return hssfWorkbook;
    }
}

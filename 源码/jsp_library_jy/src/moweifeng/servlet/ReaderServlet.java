package moweifeng.servlet;

import moweifeng.constants.Constants;
import moweifeng.entities.Book;
import moweifeng.entities.Borrow;
import moweifeng.entities.Reader;
import moweifeng.entities.ReturnBook;
import moweifeng.service.BookAdminService;
import moweifeng.service.BookService;
import moweifeng.service.ReaderService;
import moweifeng.service.impl.BookAdminServiceImpl;
import moweifeng.service.impl.BookServiceImpl;
import moweifeng.service.impl.ReaderServiceImpl;
import moweifeng.utils.DateUtils;
import moweifeng.vo.BorrowVO;
import moweifeng.vo.ReaderVO;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
/**
 * 读者操作功能类
 */
public class ReaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        ReaderService readerService = new ReaderServiceImpl();
        BookService bookService = new BookServiceImpl();
        BookAdminService bookAdminService = new BookAdminServiceImpl();
        String method = req.getParameter("method");
        int count = 0;
        Reader reader;
        String bookIdStr;
        String borrowId;
        //查询所有读者借阅图书信息
        if (method == null){
            reader = (Reader) session.getAttribute("reader");
            Constants.pageStr  = req.getParameter("page");
            Constants.limitStr = req.getParameter("limit");
            List<Borrow> borrowList = readerService.findBorrowsByReaderId(reader.getId(),Integer.valueOf(Constants.pageStr),Integer.valueOf(Constants.limitStr));
            count = readerService.findBorrowsCountByReaderId(reader.getId());
            BorrowVO borrowVO = new BorrowVO();
            borrowVO.setCode(0);
            borrowVO.setMsg(null);
            borrowVO.setCount(count);
            borrowVO.setData(borrowList);
            JSONObject jsonObject = JSONObject.fromObject(borrowVO);
            resp.getWriter().write(jsonObject.toString());
        } else switch (method) {
            //查询所有读者信息
            case "findAll":
                List<Reader> readerList;
                Constants.pageStr = req.getParameter("page");
                Constants.limitStr = req.getParameter("limit");
                readerList = readerService.findAllReaders(Integer.parseInt(Constants.pageStr), Integer.parseInt(Constants.limitStr));
                count = readerService.findReadersCount();
                ReaderVO readerVO = new ReaderVO();
                readerVO.setCode(0);
                readerVO.setCount(count);
                readerVO.setData(readerList);
                readerVO.setMsg(null);
                JSONObject jsonObject = JSONObject.fromObject(readerVO);
                resp.getWriter().write(jsonObject.toString());
                break;
            //编辑读者信息
            case "preEdit":
                Constants.readerId = req.getParameter("readerId");
                reader = readerService.findReaderById(Integer.parseInt(Constants.readerId));
                req.setAttribute("reader", reader);
                req.getRequestDispatcher("/reader/reader-edit.jsp").forward(req, resp);
                break;
            case "edit":
                Constants.readerId = req.getParameter("id");
                Constants.readerName = req.getParameter("name");
                Constants.username = req.getParameter("username");
                Constants.password = req.getParameter("password");
                Constants.tel = req.getParameter("tel");
                Constants.cardId = req.getParameter("cardid");
                Constants.gender = req.getParameter("gender");
                reader = new Reader(Integer.parseInt(Constants.readerId), Constants.readerName, Constants.username, Constants.cardId, Constants.tel, Constants.password, Constants.gender);
                readerService.modifyReader(reader);
                resp.sendRedirect(req.getContextPath()+"/reader/reader-list.jsp");
                break;
            //删除读者信息
            case "delete":
                Constants.readerId = req.getParameter("readerId");
                readerService.removeReaderById(Integer.parseInt(Constants.readerId));
                resp.sendRedirect(req.getContextPath()+"/reader/reader-list.jsp");
                break;
            //添加读者信息
            case "add":
                Constants.readerName = req.getParameter("name");
                Constants.username = req.getParameter("username");
                Constants.tel = req.getParameter("tel");
                Constants.cardId = req.getParameter("cardid");
                Constants.password = req.getParameter("password");
                Constants.gender = req.getParameter("gender");
                reader = new Reader(Constants.readerName, Constants.username, Constants.cardId, Constants.tel, Constants.password, Constants.gender);
                readerService.addReader(reader);
                resp.sendRedirect(req.getContextPath()+"/reader/reader-list.jsp");
                break;
            //读者借阅图书操作
            case "borrow":
                bookIdStr = req.getParameter("bookId");
                Book book = bookService.findBookById(Integer.parseInt(bookIdStr));
                reader = (Reader) session.getAttribute("reader");
                Borrow borrow = new Borrow();
                borrow.setReader(reader);
                borrow.setBook(book);
                borrow.setBookAdmin(null);
                Date date = new Date();
                borrow.setBorrowtime(DateUtils.dateToString(date));
                borrow.setReturntime(null);
                borrow.setState(0);
                readerService.addBorrow(borrow);
                resp.sendRedirect(req.getContextPath()+"/reader/reader.jsp");
                break;
            //读者归还图书操作
            case "return":
                borrowId = req.getParameter("borrowId");
                borrow = readerService.findBorrowById(Integer.parseInt(borrowId));
                borrow.setState(4);
                bookAdminService.modifyBorrow(borrow);
                reader = (Reader) session.getAttribute("reader");
                ReturnBook returnBook = new ReturnBook();
                returnBook.setBook(borrow.getBook());
                returnBook.setReader(reader);
                Date returnDate = new Date();
                returnBook.setReturntime(DateUtils.dateToString(returnDate));
                returnBook.setBookAdmin(null);
                returnBook.setState(4);
                bookAdminService.addReturnBook(returnBook);
                resp.sendRedirect(req.getContextPath()+"/reader/reader.jsp");
                break;
        }

    }
}

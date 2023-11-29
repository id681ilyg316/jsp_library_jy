package moweifeng.servlet;

import moweifeng.entities.*;
import moweifeng.service.BookAdminService;
import moweifeng.service.BookService;
import moweifeng.service.ReaderService;
import moweifeng.service.impl.BookAdminServiceImpl;
import moweifeng.service.impl.BookServiceImpl;
import moweifeng.service.impl.ReaderServiceImpl;
import moweifeng.utils.DateUtils;
import moweifeng.vo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 管理员操作功能类
 */
public class BookAdminServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	this.doPost(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setCharacterEncoding("utf-8");
	resp.setContentType("text/json;charset=UTF-8");
	ReaderService readerService = new ReaderServiceImpl();
	BookService bookService = new BookServiceImpl();
	BookAdminService bookAdminService = new BookAdminServiceImpl();
	String method = req.getParameter("method");
	String type = req.getParameter("type");
	String attitude = req.getParameter("result");
	String borrowIdStr;
	String returnIdStr;
	String bookCaseIdStr;
	String bookCaseName;
	Borrow borrow;
	BookAdmin bookAdmin;
	Book book;
	BookCase bookCase;
	JSONObject jsonObject;
	HttpSession session = req.getSession();
	String suffix = ".xls";
	String userAgent = req.getHeader("User-Agent");
	//根据方法名称判断
	if (method != null) {
		switch (method) {
			case "findAll":
				//根据类型判断  borrow为借阅操作   return为还书操作
				switch (type) {
				case "borrow":
					List<Borrow> borrowList = readerService.findAllBorrows();
					int count = readerService.findBorrowsNum();
					BorrowVO borrowVO = new BorrowVO();
					borrowVO.setData(borrowList);
					borrowVO.setCount(count);
					borrowVO.setCode(0);
					borrowVO.setMsg(null);
					jsonObject = JSONObject.fromObject(borrowVO);
					resp.getWriter().write(jsonObject.toString());
					break;
					case "return":
						List<ReturnBook> returnBookList = bookAdminService.findAllReturns();
						ReturnBookVO returnBookVO = new ReturnBookVO();
						count = bookAdminService.findReturnCount();
						returnBookVO.setCode(0);
						returnBookVO.setCount(count);
						returnBookVO.setMsg(null);
						returnBookVO.setData(returnBookList);
						jsonObject = JSONObject.fromObject(returnBookVO);
						resp.getWriter().write(jsonObject.toString());
						break;
				}
				break;
			//借阅和归还的审核操作
			case "auditing":
				switch (type) {
					case "borrow":
						switch (attitude) {
							//同意借阅
							case "agree":
								borrowIdStr = req.getParameter("borrowId");
								bookAdmin = (BookAdmin) session.getAttribute("bookAdmin");
								borrow = readerService.findBorrowById(Integer.parseInt(borrowIdStr));
								borrow.setBookAdmin(bookAdmin);
								borrow.setState(1);
								book = bookService.findBookById(borrow.getBook().getId());
								book.setAbled(0);
								bookService.modifyBook(book);
								bookAdminService.modifyBorrow(borrow);
								resp.sendRedirect(req.getContextPath() + "/admin/borrow-manage.jsp");
								break;
							//拒绝借阅
							case "disagree":
								borrowIdStr = req.getParameter("borrowId");
								bookAdmin = (BookAdmin) session.getAttribute("bookAdmin");
								borrow = readerService.findBorrowById(Integer.parseInt(borrowIdStr));
								borrow.setBookAdmin(bookAdmin);
								borrow.setState(2);
								book = bookService.findBookById(borrow.getBook().getId());
								book.setAbled(1);
								bookService.modifyBook(book);
								bookAdminService.modifyBorrow(borrow);
								resp.sendRedirect(req.getContextPath() + "/admin/borrow-manage.jsp");
								break;
						}
						break;
					//归还图书操作
					case "return":
						switch (attitude) {
							//同意归还
							case "agree":
								returnIdStr = req.getParameter("returnId");
								bookAdmin = (BookAdmin) session.getAttribute("bookAdmin");
								ReturnBook returnBook = bookAdminService.findReturnById(Integer.parseInt(returnIdStr));
								returnBook.setBookAdmin(bookAdmin);
								returnBook.setReturntime(DateUtils.dateToString(new Date()));
								returnBook.setState(3);
								bookAdminService.modifyReturn(returnBook);
								int bookId = returnBook.getBook().getId();
								book = bookService.findBookById(bookId);
								book.setAbled(1);
								bookService.modifyBook(book);
								borrow = readerService.findBorrowByIds(book.getId(), returnBook.getReader().getId(), 4);
								if (borrow != null) {
									borrow.setState(3);
									borrow.setReturntime(DateUtils.dateToString(new Date()));
									bookAdminService.modifyBorrow(borrow);
									resp.sendRedirect(req.getContextPath() + "/admin/return-manage.jsp");
								} else {
									resp.sendRedirect(req.getContextPath() + "/admin/return-manage.jsp");
								}
								break;
							//拒绝归还
							case "disagree":
								returnIdStr = req.getParameter("returnId");
								bookAdmin = (BookAdmin) session.getAttribute("bookAdmin");
								returnBook = bookAdminService.findReturnById(Integer.parseInt(returnIdStr));
								returnBook.setBookAdmin(bookAdmin);
								returnBook.setReturntime(DateUtils.dateToString(new Date()));
								returnBook.setState(5);
								bookAdminService.modifyReturn(returnBook);
								bookId = returnBook.getBook().getId();
								book = bookService.findBookById(bookId);
								book.setAbled(0);
								bookService.modifyBook(book);
								borrow = readerService.findBorrowByIds(book.getId(), returnBook.getReader().getId(), 0);
								borrow.setState(5);
								borrow.setBookAdmin(bookAdmin);
								borrow.setReturntime(DateUtils.dateToString(new Date()));
								bookAdminService.modifyBorrow(borrow);
								resp.sendRedirect(req.getContextPath() + "/admin/return-manage.jsp");
								break;
						}
						break;
				}
				break;
			//显示图书数据操作
			case "getBookData":
				switch (type) {
					//显示成饼图
					case "pie":
						List<BookPieData> bookPieDataList = bookAdminService.findBookDataForPie();
						JSONArray jsonArray = JSONArray.fromObject(bookPieDataList);
						resp.getWriter().write(jsonArray.toString());
						break;
					//显示成柱状图
					case "bar":
						BookBarData bookBarDataList = bookAdminService.findBookDataForBar();
						jsonObject = JSONObject.fromObject(bookBarDataList);
						resp.getWriter().write(jsonObject.toString());
						break;
				}
				//导出数据操作
			case "exportDataInfo":
				switch (type) {
					//导出图书信息操作
					case "book":
						HSSFWorkbook hssfWorkbook = bookService.findWorkBook();
						resp.setContentType("application/x-msdownload");
						
						String fileName = "图书信息";
						fileName = URLEncoder.encode(fileName, "UTF-8");
						resp.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName + suffix);
						OutputStream outputStream = resp.getOutputStream();
						hssfWorkbook.write(outputStream);
						outputStream.close();
						break;
					//导出图书借阅量数据
					case "reader":
						Reader reader = (Reader) session.getAttribute("reader");
						hssfWorkbook = readerService.findReadersRecords(reader.getId());
						resp.setContentType("application/x-msdownload");
						fileName = "图书借阅信息表";
						fileName = URLEncoder.encode(fileName, "UTF-8");
						
						//Content-Disposition 的作用
						//当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
						resp.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName + suffix);
						outputStream = resp.getOutputStream();
						hssfWorkbook.write(outputStream);
						outputStream.close();
						break;
				}
			case "bookCase":
				switch (type) {
					case "find":
						String pageStr = req.getParameter("page");
						String limitStr = req.getParameter("limit");
						List<BookCase> bookCaseList = bookAdminService.findAllBookCaseByPage(Integer.parseInt(pageStr), Integer.parseInt(limitStr));
						int count = bookAdminService.findAllCount();
						BookCaseVO bookCaseVO = new BookCaseVO();
						bookCaseVO.setCode(0);
						bookCaseVO.setMsg(null);
						bookCaseVO.setCount(count);
						bookCaseVO.setData(bookCaseList);
						jsonObject = JSONObject.fromObject(bookCaseVO);
						resp.getWriter().write(jsonObject.toString());
						break;
					case "add":
						bookCaseName = req.getParameter("name");
						bookCase = new BookCase();
						bookCase.setName(bookCaseName);
						bookAdminService.addBookCase(bookCase);
						resp.sendRedirect(req.getContextPath() + "/admin/bookcase-list.jsp");
						break;
					case "preEdit":
						bookCaseIdStr = req.getParameter("bookCaseId");
						bookCase = bookAdminService.findBookCaseById(Integer.parseInt(bookCaseIdStr));
						req.setAttribute("bookCase", bookCase);
						req.getRequestDispatcher("/admin/bookcase-edit.jsp").forward(req, resp);
						break;
					case "edit":
						bookCaseIdStr = req.getParameter("id");
						bookCaseName = req.getParameter("name");
						bookCase = bookAdminService.findBookCaseById(Integer.parseInt(bookCaseIdStr));
						bookCase.setName(bookCaseName);
						bookAdminService.modifyBookCase(bookCase);
						resp.sendRedirect(req.getContextPath() + "/admin/bookcase-list.jsp");
						break;
					case "delete":
						bookCaseIdStr = req.getParameter("bookCaseId");
						bookAdminService.removeBookCaseById(Integer.parseInt(bookCaseIdStr));
						resp.sendRedirect(req.getContextPath() + "/admin/bookcase-list.jsp");
						break;
				}
				break;
		}
	}
}
}

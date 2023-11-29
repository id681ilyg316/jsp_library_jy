package moweifeng.servlet;

import moweifeng.entities.Book;
import moweifeng.entities.BookCase;
import moweifeng.service.BookAdminService;
import moweifeng.service.BookService;
import moweifeng.service.impl.BookAdminServiceImpl;
import moweifeng.service.impl.BookServiceImpl;
import moweifeng.vo.BookVO;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *图书控制层
 */
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    
	static List<Book> getList(List<Book> list,int page,int size){
		int end = 0;
		int pages = list.size()/size + 1;
		if(page>pages) {
			page = pages - 1;
		}
		if(page < 1) {
			page = 1;
		}
		if(page*size>list.size()) {
			end = list.size();
		}else {
			end = page*size;
		}
		
		return list.subList((page-1)*size, end);
	}
	
	static int getPageSize(List<Book> list,int size) {
		if(list.size() % size == 0) {
			return list.size()/size;
		}else {
			return list.size()/size + 1;
		}
	 
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String method = req.getParameter("method");
    	String query = req.getParameter("query");
    	if(query == null) {
    		query = "";
    	}
        BookService bookService = new BookServiceImpl();
        BookAdminService bookAdminService = new BookAdminServiceImpl();
        Book book;
        String bookIdStr;
        List<BookCase> bookCaseList;
        String name;
        String author;
        String publish;
        String pageStr;
        String priceStr;
        String bookCaseId;
        BookCase bookCase;
        //查询图书
        if (method == null){
            resp.setCharacterEncoding("utf-8");
            pageStr = req.getParameter("page");
            String limitStr = req.getParameter("limit");
            List<Book> bookList = bookService.findAllBooks(Integer.valueOf(pageStr),Integer.valueOf(limitStr));
            
            BookVO bookVO = new BookVO();
            bookVO.setCode(0);
            bookVO.setMsg(null);
            bookVO.setCount(bookService.findBookCount());
            bookVO.setData(bookList);
            List<Book> list = new ArrayList<Book>();
            int count = 0;
            for(Book book2:bookList) {
            	if(book2.getName().contains(query)) {
            		list.add(book2);
            		count++;
            	}
            }
            bookVO.setData(list);
            bookVO.setCount(bookService.findBookCount());
            JSONObject jsonObject = JSONObject.fromObject(bookVO);
            resp.getWriter().write(jsonObject.toString());
        } else {
            switch (method) {
                //编辑图书
                case "preEdit":
                    bookIdStr = req.getParameter("bookId");
                    book = bookService.findBookById(Integer.valueOf(bookIdStr));
                    req.setAttribute("book",book);
                    bookCaseList = bookAdminService.findAllBookCase();
                    req.setAttribute("bookCaseList",bookCaseList);
                    req.getRequestDispatcher("/book/book-edit.jsp").forward(req,resp);
                    break;
                case "edit":
                    bookIdStr = req.getParameter("id");
                    name = req.getParameter("name");
                    author = req.getParameter("author");
                    publish = req.getParameter("publish");
                    pageStr = req.getParameter("pages");
                    priceStr = req.getParameter("price");
                    bookCaseId = req.getParameter("bookCaseId");
                    bookCase = bookAdminService.findBookCaseById(Integer.valueOf(bookCaseId));
                    book = new Book();
                    book.setId(Integer.valueOf(bookIdStr));
                    book.setName(name);
                    book.setAuthor(author);
                    book.setPublish(publish);
                    book.setPages(Integer.valueOf(pageStr));
                    book.setPrice(Float.valueOf(priceStr));
                    book.setBookCase(bookCase);
                    book.setAbled(1);
                    bookService.modifyBook(book);
                    resp.sendRedirect(req.getContextPath()+"/book/book-list.jsp");
                    break;
                //添加图书
                case "preAdd":
                    bookCaseList = bookAdminService.findAllBookCase();
                    req.setAttribute("list",bookCaseList);
                    req.getRequestDispatcher("/book/book-add.jsp").forward(req,resp);
                    break;
                case "add":
                    name = req.getParameter("name");
                    author = req.getParameter("author");
                    publish = req.getParameter("publish");
                    pageStr = req.getParameter("pages");
                    priceStr = req.getParameter("price");
                    bookCaseId = req.getParameter("bookCaseId");
                    bookCase = bookAdminService.findBookCaseById(Integer.parseInt(bookCaseId));
                    book = new Book(name,Integer.parseInt(pageStr),Float.parseFloat(priceStr),publish,author,bookCase,1);
                    bookService.addBook(book);
                    resp.sendRedirect(req.getContextPath()+"/book/book-list.jsp");
                    break;
                //删除图书
                case "delete":
                    bookIdStr = req.getParameter("bookId");
                    book = bookService.findBookById(Integer.parseInt(bookIdStr));
                    book.setBookCase(null);
                    bookService.deleteBookById(Integer.parseInt(bookIdStr));
                    resp.sendRedirect(req.getContextPath()+"/book/book-list.jsp");
                    break;

            }
        }
    }
}

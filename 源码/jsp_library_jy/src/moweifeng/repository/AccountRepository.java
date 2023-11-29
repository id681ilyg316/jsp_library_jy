package moweifeng.repository;

import moweifeng.entities.BookAdmin;
import moweifeng.entities.Reader;
/**
 *账户数据接口
 */
public interface AccountRepository {
	BookAdmin adminLogin(String username, String password);
	Reader readerLogin(String username, String password);
	int readerRegister(String username, String password);
}

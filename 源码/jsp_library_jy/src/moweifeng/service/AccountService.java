package moweifeng.service;


public interface AccountService {
    public Object login(String username, String password, String type);

	int register(String username, String password);
}

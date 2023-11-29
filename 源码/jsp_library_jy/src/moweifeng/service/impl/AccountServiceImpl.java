package moweifeng.service.impl;

import moweifeng.repository.AccountRepository;
import moweifeng.repository.impl.AccountRepositoryImpl;
import moweifeng.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository = new AccountRepositoryImpl();
    @Override
    public Object login(String username,String password, String type) {
        Object object = null;
        switch (type){
            case "reader":
                object = accountRepository.readerLogin(username,password);
                break;
            case "bookadmin":
                object = accountRepository.adminLogin(username,password);
                break;
        }
        return object;
    }
    
    @Override
    public int register(String username,String password) {
    
        return accountRepository.readerRegister(username,password);
    }
}

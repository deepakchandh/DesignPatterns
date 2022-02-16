package com.java.designpatterns.creational.factory;

interface Account{

    Account createAccount(Account account);

    void resetPwd(String userId, String pwd);
}
class UserRepository implements Account {

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public void resetPwd(String userId, String pwd) {

    }
}

class DriverRepository implements Account {

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public void resetPwd(String userId, String pwd) {

    }
}

class AdminRepository implements Account {

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public void resetPwd(String userId, String pwd) {

    }
}

enum AccountType{
    USER,
    ADMIN,
    DRIVER
}
class AccountFactory{

    public static Account getAccountRepo(AccountType accountType){
        switch (accountType){
            case USER:
                return new UserRepository();
            case ADMIN:
                return new AdminRepository();
            case DRIVER:
                return new DriverRepository();
            default:
                return null;
        }
    }
}

public class FactoryPatternExample {

    public static void main(String[] args) {
        UserRepository userRepository = (UserRepository) AccountFactory.getAccountRepo(AccountType.USER);
        AdminRepository adminRepository = (AdminRepository) AccountFactory.getAccountRepo(AccountType.ADMIN);

    }
}

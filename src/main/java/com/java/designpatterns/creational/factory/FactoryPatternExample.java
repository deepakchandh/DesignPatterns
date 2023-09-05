package com.java.designpatterns.creational.factory;

class Account{
    String id;
    String name;

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

interface AccountRepository{

    Account createAccount(Account account);

    void resetPwd(String userId, String pwd);
}
class UserRepository implements AccountRepository {

    @Override
    public Account createAccount(Account account) {
        return account;
    }

    @Override
    public void resetPwd(String userId, String pwd) {

    }
}

class DriverRepository implements AccountRepository {

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public void resetPwd(String userId, String pwd) {

    }
}

class AdminRepository implements AccountRepository {

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

    public static AccountRepository getAccountRepo(AccountType accountType){
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
        Account account = userRepository.createAccount(new Account("88", "deepak"));
        System.out.println(account.getId());


    }
}

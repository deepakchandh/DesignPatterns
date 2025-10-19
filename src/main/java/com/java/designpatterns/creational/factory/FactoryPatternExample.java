package com.java.designpatterns.creational.factory;

class Account{
    private final String id;
    private final String name;

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}

interface AccountRepository{

    Account createAccount(Account account);

    void resetPwd(String userId, String pwd);
}
class UserRepository implements AccountRepository {

    @Override
    public Account createAccount(Account account) {
        System.out.println("User account created: " + account.getName());
        return account;
    }

    @Override
    public void resetPwd(String userId, String newPassword) {
        System.out.println("User password reset for ID: " + userId);
    }
}

class DriverRepository implements AccountRepository {

    @Override
    public Account createAccount(Account account) {
        System.out.println("Admin account created: " + account.getName());
        return account;
    }

    @Override
    public void resetPwd(String userId, String pwd) {
        System.out.println("Admin password reset for ID: " + userId);
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
        AccountRepository userRepo = AccountFactory.getAccountRepo(AccountType.USER);
        Account user = userRepo.createAccount(new Account("88", "Deepak"));
        userRepo.resetPwd(user.getId(), "newPass123");

        System.out.println("Account created with ID: " + user.getId());


    }
}

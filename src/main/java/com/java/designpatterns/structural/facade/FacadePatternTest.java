package com.java.designpatterns.structural.facade;

import java.sql.Connection;

class MysqlHelper{
    public static Connection getMySqlDBConnection(){
        return null;
    }

    public void generateMySqlPDFReport(String tableName, Connection con){
    }

    public void generateMySqlHTMLReport(String tableName, Connection con){
    }
}

class OracleHelper{
    public static Connection getOracleDBConnection(){
        //get Oracle DB connection using connection parameters
        return null;
    }

    public void generateOraclePDFReport(String tableName, Connection con){
        //get data from table and generate pdf report
    }

    public void generateOracleHTMLReport(String tableName, Connection con){
        //get data from table and generate pdf report
    }
}

class HelperFacade{
    public static enum DBTypes{
        MYSQL,ORACLE;
    }

    public static enum ReportTypes{
        HTML,PDF;
    }

    public static void generateReport(DBTypes dbType, ReportTypes reportType, String tableName){
        Connection con = null;
        switch (dbType){
            case MYSQL:
                con = MysqlHelper.getMySqlDBConnection();
                MysqlHelper mySqlHelper = new MysqlHelper();
                switch(reportType){
                    case HTML:
                        mySqlHelper.generateMySqlHTMLReport(tableName, con);
                        break;
                    case PDF:
                        mySqlHelper.generateMySqlPDFReport(tableName, con);
                        break;
                }
                break;
            case ORACLE:
                con = OracleHelper.getOracleDBConnection();
                OracleHelper oracleHelper = new OracleHelper();
                switch(reportType){
                    case HTML:
                        oracleHelper.generateOracleHTMLReport(tableName, con);
                        break;
                    case PDF:
                        oracleHelper.generateOraclePDFReport(tableName, con);
                        break;
                }
                break;
        }
    }
}


public class FacadePatternTest {

    public static void main(String[] args){

        String tableName = "Employee";
        // without using Facade
        Connection connection = MysqlHelper.getMySqlDBConnection();
        MysqlHelper mySqlHelper = new MysqlHelper();
        mySqlHelper.generateMySqlHTMLReport(tableName, connection);

        Connection oracleDBConnection = OracleHelper.getOracleDBConnection();
        OracleHelper oracleHelper = new OracleHelper();
        oracleHelper.generateOraclePDFReport(tableName, oracleDBConnection);

        // using Facade
        HelperFacade.generateReport(HelperFacade.DBTypes.MYSQL, HelperFacade.ReportTypes.HTML, tableName);
        HelperFacade.generateReport(HelperFacade.DBTypes.ORACLE, HelperFacade.ReportTypes.PDF, tableName);

    }
}

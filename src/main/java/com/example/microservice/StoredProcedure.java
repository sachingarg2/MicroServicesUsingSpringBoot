package com.example.microservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StoredProcedure {
    public static void createStoredProcedure() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/Assignment?useSSL=false",
                        "postgres", "Sachin@123");

        Statement stmt = c.createStatement();
        String sql = "create or replace procedure placeOrder(\n" +
                "   _id int,\n" +
                "   _unitPrice dec, \n" +
                "   _quantity int\n" +
                ")\n" +
                "language plpgsql    \n" +
                "as $$\n" +
                "begin\n" +
                "INSERT INTO public.\"order\"(\n" +
                "\tquantity, unit_price, customer_id)\n" +
                "\tVALUES (_quantity, _unitPrice, _id);" +
                "\n" +
                "    commit;\n" +
                "end;$$";
        stmt.executeQuery(sql);

        stmt.close();
        c.close();

    }

    public static void updateOrders(int id,double unitPrice,int quantity) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/Assignment?useSSL=false",
                        "postgres", "Sachin@123");

        Statement stmt = c.createStatement();
        String sql = "call placeOrder("+id+","+unitPrice+","+quantity+");";
        stmt.executeQuery(sql);

        stmt.close();
        c.close();

    }


}

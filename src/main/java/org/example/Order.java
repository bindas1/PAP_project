package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private List<Object> arguments;
    public void updateArguments()
    {
        arguments = new ArrayList();
        arguments.add(this.order_id);
        arguments.add(this.product_id);
        arguments.add(this.email_client);
        arguments.add(this.quantity);
        arguments.add(this.order_date);
        arguments.add(this.shipping_status);
    }

    public List<Object> getArguments()
    {
        return arguments;
    }

    private int order_id;
    public int getorder_id()
    {
        return this.order_id;
    }
    public void setorder_id(int value)
    {
        this.order_id = value;
    }

    private int product_id;
    public int getproduct_id()
    {
        return this.product_id;
    }
    public void setproduct_id(int value)
    {
        this.product_id = value;
    }

    private String email_client;
    public String getemail_client()
    {
        return this.email_client;
    }
    public void setemail_client(String value)
    {
        this.email_client = value;
    }

    private int quantity;
    public int getquantity()
    {
        return this.quantity;
    }
    public void setquantity(int value)
    {
        this.quantity = value;
    }

    private java.sql.Date order_date;
    public java.sql.Date getorder_date()
    {
        return this.order_date;
    }
    public void setorder_date(java.sql.Date value)
    {
        this.order_date = value;
    }

    private int shipping_status;
    public int getshipping_status()
    {
        return this.shipping_status;
    }
    public void setshipping_status(int value)
    {
        this.shipping_status = value;
    }

    public Order(int order_id_, int product_id_, String email_client_, int quantity_, java.sql.Date order_date_, int shipping_status_)
    {
        this.order_id = order_id_;
        this.product_id = product_id_;
        this.email_client = email_client_;
        this.quantity = quantity_;
        this.order_date = order_date_;
        this.shipping_status = shipping_status_;
        updateArguments();
    }
}

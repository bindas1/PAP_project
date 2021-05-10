package org.example;

import java.util.ArrayList;
import java.util.List;

public class Product
{
    private List<Object> arguments;
    public void updateArguments()
    {
        arguments = new ArrayList();
        arguments.add(this.product_name);
        arguments.add(this.price);
        arguments.add(this.ean);
    }

    public List<Object> getArguments()
    {
        return arguments;
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

    private String product_name;
    public String getproduct_name()
    {
        return this.product_name;
    }
    public void setproduct_name(String value)
    {
        this.product_name = value;
    }

    private float price;
    public float getprice()
    {
        return this.price;
    }
    public void setprice(float value)
    {
        this.price = value;
    }

    private int ean;
    public int getean()
    {
        return this.ean;
    }
    public void setean(int value)
    {
        this.ean = value;
    }


    public Product(int product_id_,String product_name_,float price_,int ean_)
    {
        this.product_id = product_id_;
        this.product_name = product_name_;
        this.price = price_;
        this.ean = ean_;
    }

    public Product(String product_name_,float price_,int ean_)
    {
        this.product_name = product_name_;
        this.price = price_;
        this.ean = ean_;
        updateArguments();
    }
}
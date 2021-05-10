package org.example;

import java.util.*;

public class Client
{
    private List<Object> arguments;
    public void updateArguments()
    {
        arguments = new ArrayList();
        arguments.add(this.email);
        arguments.add(this.first_name);
        arguments.add(this.last_name);
        arguments.add(this.zip_code);
        arguments.add(this.city);
        arguments.add(this.street_address);
    }

    public List<Object> getArguments()
    {
        return arguments;
    }

    private String email;
    public String getemail()
    {
        return this.email;
    }
    public void setemail(String value)
    {
        this.email = value;
    }

    private String first_name;
    public String getfirst_name()
    {
        return this.first_name;
    }
    public void setfirst_name(String value)
    {
        this.first_name = value;
    }

    private String last_name;
    public String getlast_name()
    {
        return this.last_name;
    }
    public void setlast_name(String value)
    {
        this.last_name = value;
    }

    private String zip_code;
    public String getzip_code()
    {
        return this.zip_code;
    }
    public void setzip_code(String value)
    {
        this.zip_code = value;
    }

    private String city;
    public String getcity()
    {
        return this.city;
    }
    public void setcity(String value)
    {
        this.city = value;
    }

    private String street_address;
    public String getstreet_address()
    {
        return this.street_address;
    }
    public void setstreet_address(String value)
    {
        this.street_address = value;
    }

    public Client(String email_,String first_name_,String last_name_,String zip_code_,String city_,String street_address_)
    {
        this.email = email_;
        this.first_name = first_name_;
        this.last_name = last_name_;
        this.zip_code = zip_code_;
        this.city = city_;
        this.street_address = street_address_;
        updateArguments();
    }
}
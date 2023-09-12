package com.android.foodorderapp;

public class User
{

    private int id;
    private String uname;
    private String email_id;
    private String password;


    public User(int id, String uname, String email_id, String password) {
        this.id = id;
        this.uname = uname;
        this.email_id = email_id;
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", email_id='" + email_id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
    }

    public String getEmail_id()
    {
        return email_id;
    }

    public void setEmail_id(String email_id)
    {
        this.email_id = email_id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }







}

package domain;

/**
 * 用户的实体类
 */
public class user
{
    private String password;
    private String ID;
    private String status;

    public String getID()
    {
        return ID;
    }

    public void setID(String id)
    {
        this.ID = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String Password)
    {
        this.password = Password;
    }


    public String getStatus()
    {
        return status;
    }

    public void setStatus(String Status)
    {
        this.status = Status;
    }
}

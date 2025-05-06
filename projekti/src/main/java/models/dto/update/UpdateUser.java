package models.dto.update;

public class UpdateUser {

    private int id;
    private String password;
    private String email;
    private String emer;
    private String mbiemer;
    private String role;   // ADMIN ose MESUES

    public int getId()                     { return id; }
    public void setId(int id)              { this.id = id; }

    public String getPassword()            { return password; }
    public void setPassword(String p)      { this.password = p; }

    public String getEmail()               { return email; }
    public void setEmail(String e)         { this.email = e; }

    public String getEmer()                { return emer; }
    public void setEmer(String em)         { this.emer = em; }

    public String getMbiemer()             { return mbiemer; }
    public void setMbiemer(String mb)      { this.mbiemer = mb; }

    public String getRole()                { return role; }
    public void setRole(String r)          { this.role = r; }

    @Override
    public String toString() {
        return "UpdateUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", emer='" + emer + '\'' +
                ", mbiemer='" + mbiemer + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}


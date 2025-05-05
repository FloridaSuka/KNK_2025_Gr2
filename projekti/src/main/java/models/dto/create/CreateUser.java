package models.dto.create;

public class CreateUser {

    private String username;
    private String password;
    private String email;
    private String emer;
    private String mbiemer;
    private String role = "MESUES";

    // ===== GETTERS / SETTERS =====
    public String getUsername()             { return username; }
    public void   setUsername(String u)     { this.username = u; }

    public String getPassword()             { return password; }
    public void   setPassword(String p)     { this.password = p; }

    public String getEmail()                { return email; }
    public void   setEmail(String e)        { this.email = e; }

    public String getEmer()                 { return emer; }
    public void   setEmer(String em)        { this.emer = em; }

    public String getMbiemer()              { return mbiemer; }
    public void   setMbiemer(String mb)     { this.mbiemer = mb; }

    public String getRole()                 { return role; }
    public void   setRole(String r)         { this.role = r; }

    @Override
    public String toString() {
        return "CreateUserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", emer='" + emer + '\'' +
                ", mbiemer='" + mbiemer + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

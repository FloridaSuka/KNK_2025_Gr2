package models;

public class User {

    public enum Role {ADMIN, MESUES}
    private int id;
    private String username;
    private String passwordHash;
    private String email;
    private String emer;
    private String mbiemer;
    private Role role = Role.MESUES;

    public User(int id, String username, String passwordHash, String email, String emer, String mbiemer, Role role) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.emer = emer;
        this.mbiemer = mbiemer;
        this.role = role;
    }

    public User(String username, String passwordHash, Role role) {
        this(0, username, passwordHash, null, null, null, role);
    }

    // ===== GETTERS =====
    public int getId() {
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    public String getEmail(){
        return email;
    }
    public String getEmer() {
        return emer;
    }
    public String getMbiemer() {
        return mbiemer;
    }
    public Role getRole(){
        return role;
    }

    // ===== SETTERS =====
    public void setEmail(String email) {
        this.email = email;
    }
    public void setEmer(String emer) {
        this.emer = emer;
    }
    public void setMbiemer(String mbiemer) {
        this.mbiemer = mbiemer;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, username='%s', role=%s}", id, username, role);
    }
}

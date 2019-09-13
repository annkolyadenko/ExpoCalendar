package ua.com.expo.entity;

import java.util.Arrays;
import java.util.Objects;

public class User extends Entity {

    private Role role;
    private String name;
    private String email;
    private byte[] password;
    private byte[] salt;

    public User() {
    }

    public User(Long id, Role role, String name, String email, byte[] password, byte[] salt) {
        super(id);
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public User(Builder builder) {
        super(builder.id);
        this.role = builder.role;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.salt = builder.salt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public static class Builder {
        private Long id;
        private Role role;
        private String name;
        private String email;
        private byte[] password;
        private byte[] salt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(byte[] password) {
            this.password = password;
            return this;
        }

        public Builder salt(byte[] salt) {
            this.salt = salt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!Objects.equals(role, user.role)) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Arrays.equals(password, user.password)) return false;
        return Arrays.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + Arrays.hashCode(salt);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //TO DO!!!
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: " + name);
        sb.append(", id: " + this.getId());
        sb.append(", role: " + role);
        sb.append(", login: " + email);
        return sb.toString();
    }
}

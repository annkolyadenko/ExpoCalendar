package ua.com.expo.dto;

public class UserDto extends AbstractDto {

    private RoleDto role;
    private String name;
    private String email;

    public UserDto() {
    }

    public UserDto(Builder builder) {
        super(builder.id);
        this.role = builder.role;
        this.name = builder.name;
        this.email = builder.email;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
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

    public static class Builder {
        private Long id;
        private RoleDto role;
        private String name;
        private String email;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder role(RoleDto role) {
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

        public UserDto build() {
            return new UserDto(this);
        }
    }

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

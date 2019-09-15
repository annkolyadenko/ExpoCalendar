package ua.com.expo.entity;

import java.util.Objects;

public class Role extends Entity {

    private String type;

    public Role() {
    }

    public Role(Long id, String type) {
        super(id);
        this.type = type;
    }

    public Role(Builder builder) {
        super(builder.id);
        this.type = builder.type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Builder {
        private Long id;
        private String type;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder role(String type) {
            this.type = type;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        return Objects.equals(type, role.type);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return type;
        /*StringBuilder sb = new StringBuilder();
        sb.append("type: " + type);
        sb.append(", id: " + this.getId());
        return sb.toString();*/
    }
}

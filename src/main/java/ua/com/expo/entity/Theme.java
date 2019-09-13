package ua.com.expo.entity;

import java.util.Objects;

public class Theme extends Entity {

    private String name;

    public Theme() {
    }

    public Theme(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Theme(Builder builder) {
        super(builder.id);
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Theme build() {
            return new Theme(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Theme)) return false;

        Theme theme = (Theme) o;

        return Objects.equals(name, theme.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Theme: " + name);
        sb.append(", id: " + this.getId());
        return sb.toString();
    }
}

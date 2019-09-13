package ua.com.expo.entity;

import java.util.Objects;

public class Showroom extends Entity {

    private String name;
    private String info;

    public Showroom() {
    }

    public Showroom(Long id, String name, String info) {
        super(id);
        this.name = name;
        this.info = info;
    }

    public Showroom(Builder builder) {
        super(builder.id);
        this.name = builder.name;
        this.info = builder.info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String info;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Showroom build() {
            return new Showroom(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Showroom)) return false;

        Showroom showroom = (Showroom) o;

        if (!Objects.equals(name, showroom.name)) return false;
        return Objects.equals(info, showroom.info);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Showroom: " + name);
        sb.append(", id: " + this.getId());
        sb.append(", info: " + info);
        return sb.toString();
    }
}

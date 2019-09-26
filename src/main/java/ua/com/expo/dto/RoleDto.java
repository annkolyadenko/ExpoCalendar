package ua.com.expo.dto;

public class RoleDto extends AbstractDto {

    private String type;

    public RoleDto() {
    }

    public RoleDto(Builder builder) {
        super(builder.id);
        this.type = builder.type;
    }

    public RoleDto(Long id, String type) {
        super(id);
        this.type = type;
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

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public RoleDto build() {
            return new RoleDto(this);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return type;
    }
}

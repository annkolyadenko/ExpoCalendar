package ua.com.expo.entity.enums;

public enum RoleEnum {
    VISITOR(1L){
        @Override
        public String toString() {
            return "visitor";
        }
    }, ADMINISTRATOR(2L) {
        @Override
        public String toString() {
            return "administrator";
        }
    };

    private Long id;

    RoleEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public abstract String toString();
}

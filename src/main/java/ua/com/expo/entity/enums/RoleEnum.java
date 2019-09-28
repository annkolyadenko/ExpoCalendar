package ua.com.expo.entity.enums;

public enum RoleEnum {

    VISITOR(){
        @Override
        public String toString() {
            return "visitor";
        }
    }, ADMINISTRATOR() {
        @Override
        public String toString() {
            return "administrator";
        }
    };

    public abstract String toString();
}

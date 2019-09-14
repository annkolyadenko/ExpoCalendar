package ua.com.expo.entity.enums;

public enum ShowroomEnum {

    BLACK {
        @Override
        public String toString() {
            return "black";
        }
    }, WHITE {
        @Override
        public String toString() {
            return "white";
        }
    }, RED {
        @Override
        public String toString() {
            return "red";
        }
    }, TURQUOISE {
        @Override
        public String toString() {
            return "turquoise";
        }
    };

    public abstract String toString();
}

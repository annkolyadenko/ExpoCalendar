package ua.com.expo.entity.enums;

public enum ShowroomEnum {

    BLACK {
        @Override
        public String getName() {
            return "black";
        }
    }, WHITE {
        @Override
        public String getName() {
            return "white";
        }
    }, RED {
        @Override
        public String getName() {
            return "red";
        }
    }, TURQUOISE {
        @Override
        public String getName() {
            return "turquoise";
        }
    };

    public abstract String getName();
}

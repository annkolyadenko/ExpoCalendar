package ua.com.expo.entity.enums;

public enum PaymentType {
    MASTER_CARD{
        @Override
        public String toString() {
            return "MASTER CARD";
        }
    }, VISA {
        @Override
        public String toString() {
            return "VISA";
        }
    }, PAY_PAL {
        @Override
        public String toString() {
            return "PAY PAL";
        }
    }, MOBILE_PAYMENTS {
        @Override
        public String toString() {
            return "MOBILE PAYMENTS";
        }
    };

    public abstract String toString();
}

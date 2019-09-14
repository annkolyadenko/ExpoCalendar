package ua.com.expo.entity.enums;

public enum PaymentStatus {
    AUTHORISED {
        @Override
        public String toString() {
            return "authorized";
        }
    }, EXPIRED {
        @Override
        public String toString() {
            return "expired";
        }
    }, CANCELLED {
        @Override
        public String toString() {
            return "cancelled";
        }
    }, REFUSED {
        @Override
        public String toString() {
            return "refused";
        }
    }, ERROR {
        @Override
        public String toString() {
            return "error";
        }
    }, REFUNDED {
        @Override
        public String toString() {
            return "refunded";
        }
    };

    public abstract String toString();


}

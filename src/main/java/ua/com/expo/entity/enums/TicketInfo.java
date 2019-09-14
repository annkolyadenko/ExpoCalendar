package ua.com.expo.entity.enums;

public enum TicketInfo {
    INFO {
        @Override
        public String toString() {
            return "ПРАВИЛА ВІДВІДУВАННЯ: Купуючи квитки на виставку, Відвідувачі погоджуються з даними Правилами і зобов’язуються їх дотримуватись." +
                    "RULES OF VISITING EXPO: When purchasing tickets for expo performances, visitors agree with these Rules and commit themselves to comply with them.";
        }
    };

    public abstract String toString();
}

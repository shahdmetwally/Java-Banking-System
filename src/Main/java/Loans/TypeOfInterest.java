package Loans;

public enum TypeOfInterest {
    FIX_RATE{
        @Override
        public String toString() {
            return "Fix rate";
        }
    },
    VARIABLE_RATE{
        @Override
        public String toString() {
            return "Varible ";
        }
    }

}

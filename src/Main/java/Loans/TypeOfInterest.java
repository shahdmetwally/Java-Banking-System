package Loans;

public enum TypeOfInterest {
    FIX_RATE{
        @Override
        public String toString() {
            return "Fix";
        }
    },
    VARIABLE_RATE{
        @Override
        public String toString() {
            return "Variable ";
        }
    }

}

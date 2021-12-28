package Loans;

import Utilities.Utilities;

public enum TypesOfLoan {
    PERSONAL_LOAN{
        @Override
        public String toString() {
            return "Personal Loan";
        }

    },
    HOUSE_LOAN{
        @Override
        public String toString() {
            return "House loan";
        }
    },
    CAR_LOAN{
        @Override
        public String toString() {
            return "Car loan";
        }
    },
    UNSECURED_LOAN{
        @Override
        public String toString() {
            return "Unsecured loan";
        }
    }

}

package Bank;

import Utilities.Utilities;

public enum TypesOfLoan {
    PERSONAL_LOAN{
        @Override
        public String toString() {
            return "Personal Loan";
        }
        public String getMortgagePercentage(double loan, double houseWorth, double cashContribution){
           double loanSize = loan -cashContribution;
           double loanSizePercentage = loanSize / houseWorth;
           if(loanSizePercentage > 0.7){
               return "The mortgage will be at least 2% per year"+ Utilities.EOL +
                       " This is apart of the interest of the loan.";
            }else if(0.5 < loanSizePercentage|| loanSizePercentage < 0.7){
               return "The mortgage will be at least 1 % per year"+ Utilities.EOL +
                       " This is apart of the interest of the loan.";
           }else{
               return "There is not a mortgage requirement for this loan";
           }

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

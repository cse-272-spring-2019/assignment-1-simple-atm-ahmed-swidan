import java.util.ArrayList;
import java.util.List;

public class Account {
    private String cardNumber;
    private double balance;
    List<String> history= new ArrayList<>();
    public Account(String cardNumber,double balance){
        this.cardNumber=cardNumber;
        if(this.balance<0.0)
            this.balance=0.0;
        this.balance=balance;
    }

    public String getBalance() {
        String stringBalance=Double.toString(balance);
        if(history.size() == 5){
            history.remove(0);
        }
        history.add("Balance is: "+stringBalance);
        return stringBalance;
    }

    public void deposit(double dbalance){
        if(dbalance<0)
            dbalance=0;
        balance+=dbalance;
        if(history.size() == 5){
            history.remove(0);
        }
        history.add(String.valueOf(dbalance)+" added on balance");
    }
    public  void withdraw(double wbalance){
        if(wbalance<0)
            wbalance=0;
        balance-=wbalance;
        if(history.size() == 5){
            history.remove(0);
        }
        if(balance<0.0){
            balance+=wbalance;
            history.add(" Transaction Denied.");
        }
        else
            history.add(String.valueOf(wbalance)+" removed from balance");

    }
    public void showHistory(int counter){
        System.out.println(history);
    }

    public boolean verifyAccount(String cardNumber){
        return this.cardNumber.equals(cardNumber);
    }

}

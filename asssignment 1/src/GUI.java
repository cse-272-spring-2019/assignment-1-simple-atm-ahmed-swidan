import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {
   private Account swidan= new Account("youssef",0);//the account object

    private int historyCounter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("ATM machine");
//creating all the widgets
        Label cardNumberLabel = new Label("please put your card number:");
        Label verifyMessege = new Label();
        Label welcomeText=new Label("welcome to your bank account, what do you wanna do?");
        Label history=new Label("HISTORY");
        Label showBalance= new Label();

        TextField cardNumberFeild = new TextField();
        TextField depositFeild = new TextField("0");
        TextField withdrawFeild = new TextField("0");


        Button submitButton = new Button("submit");
        Button depositButton = new Button("diposit");
        Button withdrawButton = new Button("wihdraw");
        Button nextButton = new Button("next");
        Button previousButton = new Button("previous");
        Button showBalanceButton= new Button("balance inquiry");

        GridPane loginGrid = new GridPane();
        loginGrid.add(cardNumberLabel, 0, 0);
        loginGrid.add(cardNumberFeild, 0, 1);
        loginGrid.add(submitButton, 0, 2);
        loginGrid.add(verifyMessege,0,3);
//arranging the wodgets in a grid
        GridPane accountGrid=new GridPane();
        accountGrid.add(welcomeText,0,0);
        accountGrid.add(depositButton,0,1);
        accountGrid.add(depositFeild,1,1);
        accountGrid.add(withdrawButton,0,2);
        accountGrid.add(withdrawFeild,1,2);
        accountGrid.add(previousButton,0,4);
        accountGrid.add(history,1,4);
        accountGrid.add(nextButton,2,4);
        accountGrid.add(showBalance,1,3);
        accountGrid.add(showBalanceButton,0,3);
        loginGrid.setVgap(5);
        accountGrid.setVgap(5);
        accountGrid.setHgap(10);

        Scene loginScene = new Scene(loginGrid, 400, 200);
        Scene accountScene= new Scene(accountGrid, 700,200);

        stage.setScene(loginScene);

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!swidan.verifyAccount(cardNumberFeild.getText())){
                    verifyMessege.setText("wrong card number");
                }
                else{
                    stage.setScene(accountScene);
                }
            }
        });

        showBalanceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            showBalance.setText(swidan.getBalance());
                history.setText(swidan.history.get(swidan.history.size()-1));
                historyCounter=swidan.history.size()-1;
            }
        });

        depositButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                swidan.deposit(Double.parseDouble(depositFeild.getText()));
                history.setText(swidan.history.get(swidan.history.size()-1));
                historyCounter=swidan.history.size()-1;
                if(swidan.history.get(swidan.history.size()-1).equals("0.0 added on balance"))
                    swidan.history.remove(swidan.history.size()-1);

            }
        });
        withdrawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                swidan.withdraw(Double.parseDouble(withdrawFeild.getText()));
                history.setText(swidan.history.get(swidan.history.size()-1));
                historyCounter=swidan.history.size()-1;
                if(swidan.history.get(swidan.history.size()-1).equals(" Transaction Denied."))
                    swidan.history.remove(swidan.history.size()-1);
                if(swidan.history.get(swidan.history.size()-1).equals("0.0 removed from balance"))
                    swidan.history.remove(swidan.history.size()-1);
            }
        });
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(historyCounter>0) {
                    historyCounter-=1;
                    history.setText(swidan.history.get(historyCounter));
                }
                else {
                    history.setText("no more history to show");
                    if(historyCounter!=-1)
                    historyCounter--;
                }
            }
        });
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(historyCounter<swidan.history.size()-1)
                history.setText(swidan.history.get(++historyCounter));
                else {
                    history.setText("no more history to show");
                    if(historyCounter>=swidan.history.size()-1)
                    historyCounter=swidan.history.size();
                }
            }
        });
        stage.show();
    }
}


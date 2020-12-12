
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {
    public static void main(String[] args) { launch(args); }

    private Label firstNumberLbl;
    private TextField firstNumberTf;
    private Label secondNumberLbl;
    private TextField secondNumberTf;
    private Label operationLbl;
    private Button addBtn;
    private Button subBtn;
    private Button mulBtn;
    private Button divBtn;
    private Label resultLbl;
    private TextField resultTf;

    @Override
    public void start(Stage stage) throws Exception {
        // 1. create the leaf nodes
        firstNumberLbl = new Label("First number:");
        firstNumberTf = new TextField();
        secondNumberLbl = new Label("Second number:");
        secondNumberTf = new TextField();
        operationLbl = new Label("Operation:");
        addBtn = new Button("+");
        subBtn = new Button("-");
        mulBtn = new Button("*");
        divBtn = new Button("/");
        resultLbl = new Label("Result:");
        resultTf = new TextField();
        addBtn.setOnAction(new AddHandler());
        subBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setResult(getFirstNumber() - getSecondNumber());
            }
        });
        mulBtn.setOnAction(event -> setResult(getFirstNumber() * getSecondNumber()));
        divBtn.setOnAction(event -> setResult(getFirstNumber() / getSecondNumber()));
        // 2. create the branch node
        /**VBox box = new VBox(10, firstNumberLbl, firstNumberTf, secondNumberLbl, 
            secondNumberTf, operationLbl, addBtn, subBtn, mulBtn, divBtn, 
            resultLbl, resultTf);**/
        HBox box = new HBox(10, addBtn, subBtn, mulBtn, divBtn);
        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        grid.add(firstNumberLbl, 0, 0);
        grid.add(firstNumberTf, 1, 0);
        grid.add(secondNumberLbl, 0, 1);
        grid.add(secondNumberTf, 1, 1);
        grid.add(operationLbl, 0, 2);
        /**grid2.add(addBtn, 0, 0);
        grid2.add(subBtn, 1, 0);
        grid2.add(mulBtn, 2, 0);
        grid2.add(divBtn, 3, 0);**/
        grid.add(box, 1, 2);
        grid.add(resultLbl, 0, 3);
        grid.add(resultTf, 1, 3);
        // 3. set the scene, show the stage
        stage.setScene(new Scene(grid));
        stage.setTitle("Calculator");
        stage.show();
    }
    private int getFirstNumber() {
        return Integer.parseInt(firstNumberTf.getText());
    }
    private int getSecondNumber() {
        return Integer.parseInt(secondNumberTf.getText());
    }
    private void setResult(int value) {
        resultTf.setText("" + value);
    }
    private class AddHandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent event) {
            setResult(getFirstNumber() + getSecondNumber());
        }
    }
}
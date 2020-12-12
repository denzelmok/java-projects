
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginForm extends Application {
    public static void main(String[] args) {launch(args);}
    
    private String usrName = "sam";
    private String psWord = "mypassword";
    private Label usrNameLbl;
    private TextField usrNameTf;
    private Label psWordLbl;
    private PasswordField psWordPf;
    private Button loginBtn;
    private Text resultTxt;
    
    @Override
    public void start(Stage stage) throws Exception {
        usrNameLbl = new Label("Username:");
        usrNameTf = new TextField();
        psWordLbl = new Label("Password:");
        psWordPf = new PasswordField();
        loginBtn = new Button("Login");
        resultTxt = new Text();
        loginBtn.setOnAction(event -> {
            if ((usrNameTf.getText().equals(usrName)) && (psWordPf.getText().equals(psWord)))
                resultTxt.setText("Password correct!");
            else
                resultTxt.setText("Password incorrect!");
        });
        
        GridPane grid = new GridPane();
        grid.add(usrNameLbl, 0, 0);
        grid.add(usrNameTf, 1, 0);
        grid.add(psWordLbl, 0, 1);
        grid.add(psWordPf, 1, 1);
        grid.add(loginBtn, 1, 2);
        grid.add(resultTxt, 1, 3);
        
        stage.setScene(new Scene(grid));
        stage.setTitle("Login");
        stage.show();
    }
}
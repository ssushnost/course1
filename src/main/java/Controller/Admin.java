package Controller;

import Other.Person;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    private final Model.Admin model = new Model.Admin();
    public Label firstnameEmptyLabel;
    public Label surnameEmptyLabel;
    public TextField firstnameField;
    public TextField surnameField;
    public Label lastnameEmptyLabel;
    public TextField lastnameField;
    public Label usernameEmptyLabel;
    public TextField usernameField;
    public Label passwordEmptyLabel;
    public PasswordField passwordField;
    public Label welcomeLabel;
    public ComboBox<String> roleComboBox;
    public Label roleEmptyLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstnameField.textProperty().addListener((observable, oldValue, newValue) -> model.setFirstname(newValue));
        surnameField.textProperty().addListener((observable, oldValue, newValue) -> model.setSurname(newValue));
        lastnameField.textProperty().addListener((observable, oldValue, newValue) -> model.setLastname(newValue));
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> model.setUsername(newValue));
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> model.setPassword(newValue));
        roleComboBox.valueProperty().addListener((observable, oldValue, newValue) -> model.setRole(newValue));

        firstnameField.styleProperty().bindBidirectional(model.firstnameFieldStyle);
        surnameField.styleProperty().bindBidirectional(model.surnameFieldStyle);
        lastnameField.styleProperty().bindBidirectional(model.lastnameFieldStyle);
        usernameField.styleProperty().bindBidirectional(model.usernameFieldStyle);
        passwordField.styleProperty().bindBidirectional(model.passwordFieldStyle);
        roleComboBox.styleProperty().bindBidirectional(model.roleComboBoxStyle);

        firstnameEmptyLabel.visibleProperty().bindBidirectional(model.firstnameEmpty);
        surnameEmptyLabel.visibleProperty().bindBidirectional(model.surnameEmpty);
        lastnameEmptyLabel.visibleProperty().bindBidirectional(model.lastnameEmpty);
        usernameEmptyLabel.visibleProperty().bindBidirectional(model.usernameEmpty);
        passwordEmptyLabel.visibleProperty().bindBidirectional(model.passwordEmpty);
        roleEmptyLabel.visibleProperty().bindBidirectional(model.roleEmpty);

        welcomeLabel.textProperty().bindBidirectional(model.welcomeText);
        roleComboBox.setItems(model.roles);
    }

    public void addEmployee() {
        model.setFirstname(firstnameField.getText());
        model.setSurname(surnameField.getText());
        model.setLastname(lastnameField.getText());
        model.setUsername(usernameField.getText());
        model.setPassword(passwordField.getText());
        model.setRole(roleComboBox.getValue());
        model.addUser();
    }

    public void logOut() {
        model.logOut();
    }
}
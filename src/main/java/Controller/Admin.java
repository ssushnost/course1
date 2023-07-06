package Controller;

import Other.Person;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    private final Model.Registration model = new Model.Registration();
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
    private Person person;

    public void passPerson(Person person) {
        this.person = person;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> roles = FXCollections.observableArrayList("Employee", "Doctor");
        roleComboBox.setItems(roles);
        Platform.runLater(() -> {
            welcomeLabel.setText("Welcome, " + person.getWelcomeName());
        });
    }

    public void addEmployee(ActionEvent actionEvent) {
        String firstname = firstnameField.getText();
        String surname = surnameField.getText();
        String lastname = lastnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();
        boolean firstnameIsBlank = firstname.isBlank();
        boolean surnameIsBlank = surname.isBlank();
        boolean lastnameIsBlank = lastname.isBlank();
        boolean usernameIsBlank = username.isBlank();
        boolean passwordIsBlank = password.isBlank();
        boolean roleIsNull = role == null;
        firstnameField.setStyle(firstnameIsBlank ? "-fx-border-color: red;" : "");
        surnameField.setStyle(surnameIsBlank ? "-fx-border-color: red;" : "");
        lastnameField.setStyle(lastnameIsBlank ? "-fx-border-color: red;" : "");
        usernameField.setStyle(usernameIsBlank ? "-fx-border-color: red;" : "");
        passwordField.setStyle(passwordIsBlank ? "-fx-border-color: red;" : "");
        roleComboBox.setStyle(roleIsNull ? "-fx-border-color: red;" : "");
        firstnameEmptyLabel.setVisible(firstnameIsBlank);
        surnameEmptyLabel.setVisible(surnameIsBlank);
        lastnameEmptyLabel.setVisible(lastnameIsBlank);
        usernameEmptyLabel.setVisible(usernameIsBlank);
        passwordEmptyLabel.setVisible(passwordIsBlank);
        if (firstnameIsBlank || surnameIsBlank || lastnameIsBlank || usernameIsBlank || passwordIsBlank || roleIsNull)
            return;
        model.addUser(firstname, surname, lastname, username, password, role);
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Adding employee");
        dialog.setHeaderText("Successfully added employee");
        dialog.setContentText(surname + " " + firstname + " " + lastname);
        dialog.showAndWait();
    }

    public void logOut(MouseEvent mouseEvent) {
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
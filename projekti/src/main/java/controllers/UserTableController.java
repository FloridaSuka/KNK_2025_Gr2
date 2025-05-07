package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.User;
import services.UserService;

public class UserTableController {

    @FXML private TableView<User> tableUsers;
    @FXML private TableColumn<User,Integer> colId;
    @FXML private TableColumn<User,String>  colUsername;
    @FXML private TableColumn<User,String>  colEmail;
    @FXML private TableColumn<User,String>  colRole;

    @FXML private TextField txtUsername;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<User.Role> cmbRole;
    @FXML private Label lblMsg;

    private final UserService userService = new UserService();
    private ObservableList<User> data;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        cmbRole.setItems(FXCollections.observableArrayList(User.Role.values()));

        refresh();
        tableUsers.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSel, newSel) -> fillForm(newSel));
    }

    @FXML
    private void addUser() {
        if (inputsInvalid()) return;
        User u = new User(txtUsername.getText(), txtPassword.getText(),
                cmbRole.getValue());
        u.setEmail(txtEmail.getText());
        userService.register(u);
        refresh();
        clearForm();
        lblMsg.setText("U shtua me sukses");
    }

    @FXML
    private void updateUser() {
        User sel = tableUsers.getSelectionModel().getSelectedItem();
        if (sel == null) { lblMsg.setText("Zgjidh një rresht"); return; }

        sel.setEmail(txtEmail.getText());
        sel.setRole(cmbRole.getValue());
        if (!txtPassword.getText().isBlank())
            userService.register(sel, txtPassword.getText()); // rifresko pass
        else
            userService.register(sel, sel.getPasswordHash()); // hash ekzistues

        refresh();
        clearForm();
        lblMsg.setText("U përditësua");
    }

    @FXML
    private void deleteUser() {
        User sel = tableUsers.getSelectionModel().getSelectedItem();
        if (sel == null) { lblMsg.setText("Zgjidh një rresht"); return; }
        userService.delete(sel.getUsername());
        refresh();
        clearForm();
        lblMsg.setText("U fshi");
    }

    private void refresh() {
        data = FXCollections.observableArrayList(userService.getAll());
        tableUsers.setItems(data);
    }

    private void fillForm(User u) {
        if (u == null) return;
        txtUsername.setText(u.getUsername());
        txtEmail.setText(u.getEmail());
        cmbRole.setValue(u.getRole());
        txtPassword.clear();
    }

    private void clearForm() {
        txtUsername.clear();
        txtEmail.clear();
        txtPassword.clear();
        cmbRole.getSelectionModel().clearSelection();
    }

    private boolean inputsInvalid() {
        if (txtUsername.getText().isBlank() || txtPassword.getText().isBlank()
                || cmbRole.getValue() == null) {
            lblMsg.setText("Ploteso fushat kryesore");
            return true;
        }
        return false;
    }
}

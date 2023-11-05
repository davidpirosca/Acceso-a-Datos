/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxformulariologin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author 3rWaZzZa
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label lblWelcome;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSignin;
    
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ejemplo1personas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 3rWaZzZa
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;
    @FXML
    private TableView<Persona> tblPersonas;
    @FXML
    private TableColumn nombreColumn;
    @FXML
    private TableColumn apellidosColumn;
    @FXML
    private TableColumn edadColumn;

    private ObservableList<Persona> personData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personData = FXCollections.observableArrayList();
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory("apellidos"));
        edadColumn.setCellValueFactory(new PropertyValueFactory("edad"));
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Persona p = new Persona(txtNombre.getText(), txtApellidos.getText(), txtEdad.getText());
        personData.add(p);
        tblPersonas.setItems(personData);
    }

}

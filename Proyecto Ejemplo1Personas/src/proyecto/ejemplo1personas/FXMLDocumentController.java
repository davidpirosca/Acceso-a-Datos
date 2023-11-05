/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package proyecto.ejemplo1personas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author 3rWaZzZa
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<Persona> tablePersona;
    @FXML
    private TableColumn columnNombre;
    @FXML
    private TableColumn columnApellidos;
    @FXML
    private TableColumn columnEdad;

    private ObservableList<Persona> personData;

    private void handleButtonAction(ActionEvent event) {
        Persona p = new Persona(txtNombre.getText(), txtApellidos.getText(), txtEdad.getText());
        tablePersona.setItems(personData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        personData = FXCollections.observableArrayList();
        columnNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        columnEdad.setCellValueFactory(new PropertyValueFactory("edad"));
    }

}

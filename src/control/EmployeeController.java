/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Employee;
import managment.EmployeeManagment;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author juanc
 */
public class EmployeeController implements Initializable {

    @FXML
    private ImageView imgEmployee;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<Employee> tblEmployee;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colIdentification;
    @FXML
    private TableColumn colGender;
    @FXML
    private TableColumn colBirthDate;
    @FXML
    private TableColumn colCharge;
    @FXML
    private TableColumn colWage;
    @FXML
    private Button btnBringEmp;
    
    //Without  @FXML
    
    private ObservableList<Employee> myEmployees;
    private EmployeeManagment Emag;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.modelTable();
        this.Emag = new EmployeeManagment();
    }    

    @FXML
    private void doAdd(ActionEvent event) {
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/AddEmployee.fxml"));
            
            Parent root = loader.load();
            
            AddEmployeeController controlador = loader.getController();
            controlador.initAttributtes(myEmployees);
            
            Scene scene=new Scene(root);    
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); 
            
            Employee employee = controlador.getEmploy();
            if(employee != null){
                this.myEmployees.add(employee);
                this.tblEmployee.refresh(); 
            }
        }
        catch (IOException ex) {
           String mesg;
           mesg="An Error had ocurred with the Loader";
           this.showMessages(mesg, 1);
        }
    }

    @FXML
    private void doModify(ActionEvent event) {
        String mesg;
        Employee employee = this.tblEmployee. getSelectionModel().getSelectedItem();
        if (employee == null){
            mesg="You must to select a employee";
            this.showMessages(mesg, 1);
        }
        else{
            try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/AddEmployee.fxml"));
            
            Parent root = loader.load();
            
            AddEmployeeController controlador = loader.getController();
            controlador.initAttributtes(myEmployees, employee);
            
            Scene scene=new Scene(root);    
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); 
            
            Employee aux = controlador.getEmploy();
            if(aux != null){
                this.tblEmployee.refresh();
            }
        } 
        catch (IOException ex) {
           mesg="An Error had ocurred with the Loader";
           this.showMessages(mesg, 1);
            }
        }
    }

    @FXML
    private void doDelete(ActionEvent event) {
        String mesg;
        Employee employee = this.tblEmployee. getSelectionModel().getSelectedItem();
        if (employee == null){
            mesg="You must to select a employee";
            this.showMessages(mesg, 1);
        }
        else{
            this.myEmployees.remove(employee);
            this.tblEmployee.refresh();
            this.Emag.eliminarEmpleados();
            mesg="Employee deleted";
            this.showMessages(mesg, 2);
        }
    }

    @FXML
    private void doBack(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/FView.fxml"));
            
            Parent root = loader.load();
            
            FViewController controlador = loader.getController();
            
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.setScene(scene);
            stage.showAndWait();
        } 
        catch (IOException ex) {
           String mesg;
           mesg="An Error had ocurred with the Loader";
           System.out.println(mesg);
        }
    }
    
        @FXML
    private void doBringEmp(ActionEvent event) {
         ArrayList <Employee> employees= Emag.getEmpleados();
        for(Employee run: employees){
            this.myEmployees.add(run);
        }
        this.tblEmployee.setItems(this.myEmployees);
    }
    
     //Aditional
    private void modelTable(){
         //Init of "ArrayList" of FX 
        this.myEmployees=FXCollections.observableArrayList();    
        this.tblEmployee.setItems(myEmployees);
        //Let's asociate the Columns and start the Table
        this.colIdentification.setCellValueFactory(new PropertyValueFactory("cedula"));
        this.colName.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colGender.setCellValueFactory(new PropertyValueFactory("genero")); 
        this.colBirthDate.setCellValueFactory(new PropertyValueFactory("fechanacimiento")); 
        this.colCharge.setCellValueFactory(new PropertyValueFactory("Cargo"));
        this.colWage.setCellValueFactory(new PropertyValueFactory("Salario"));
    }
    
    //Confirmation messages
    
        private boolean showMessages(String mesg, int caso){
        Alert msg;
        boolean ok=false;
        
        if(caso==1)//ERROR
        {
            msg=new Alert(Alert.AlertType.ERROR);
            msg.setTitle("ERROR");
            
            msg.setHeaderText(null);
            msg.setContentText(mesg);
            msg.showAndWait();
            
        }
        if (caso==2)//Notificacion
        {
            msg=new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("READY");
            
            msg.setHeaderText(null);
            msg.setContentText(mesg);
            msg.showAndWait();
            
        }
        
        if (caso==3)//Confirmación
        {
            msg=new Alert(Alert.AlertType.CONFIRMATION);
            msg.setTitle("DELETE REQUEST");
            
            msg.setHeaderText(null);
            msg.setContentText(mesg);
            msg.initStyle(StageStyle.UTILITY);
            
            Optional<ButtonType> result = msg.showAndWait();
            if (result.get()==ButtonType.OK)
                ok=true;
            
        }
        return ok;
    }   
}

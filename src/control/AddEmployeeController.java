/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import managment.EmployeeManagment;
import model.Employee;

/**
 * FXML Controller class
 *
 * @author juanc
 */
public class AddEmployeeController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtIdentification;
    @FXML
    private ComboBox txtGender;
    @FXML
    private TextField txtBirthDay;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtWage;
    @FXML
    private ComboBox txtCharge;
    
    //Without @FXML
    private EmployeeManagment emManag;
    private ObservableList<Employee> myEmployees;
    private Employee employ;
    

    /**
     * Initializes the controller class.
     */
    //================== Init ========================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fill_combox();
        this.emManag =new EmployeeManagment ();
    }    
    
    //Init of Attributtes
    public void initAttributtes(ObservableList<Employee>myEmployees){
        this.myEmployees=myEmployees;
    }
    
    public void initAttributtes(ObservableList<Employee>myEmployees, Employee employee){
        String x,y;
        this.myEmployees=myEmployees;
        this.employ=employee;
        //Attributtes
        this.txtName.setText(employee.getNombre());
        this.txtIdentification.setText(employee.getCedula()+"");
        x= (String) this.txtGender.getSelectionModel().getSelectedItem();
        this.txtBirthDay.setText(employee.getFechanacimiento());
        this.txtWage.setText(employee.getSalario());
        y= (String) this.txtCharge.getSelectionModel().getSelectedItem();
        
    }

    @FXML
    private void doSave(ActionEvent event) {
         //Take data
        String mesg;
        String name = this.txtName.getText();
        int ide =  Integer.parseInt(this.txtIdentification.getText());
        String gender = (String) this.txtGender.getSelectionModel().getSelectedItem();
        String dateBirth = this.txtBirthDay.getText();
        String Charge = (String) this.txtCharge.getSelectionModel().getSelectedItem();
        String wage=this.txtWage.getText();
        
        //Object created
        Employee employee = new Employee(ide, name, gender, dateBirth, Charge, wage);
        
        //Conditionals
        if(!this.myEmployees.contains(employee)){
            //Modify
            if(this.employ != null){
                
                this.employ.setNombre(name);
                this.employ.setCedula(ide);
                this.employ.setGenero(gender);
                this.employ.setFechanacimiento(dateBirth);
                this.employ.setCargo(Charge);
                this.employ.setSalario(wage);

                mesg="Modify Succesfully";
                this.showMessages(mesg, 2);
            
            }else{
                //Insert
                
                this.employ = employee;  
                mesg="Added succesfully";
                this.showMessages(mesg, 2); 
            }
            Stage stage = (Stage) this.btnSave.getScene().getWindow();
            stage.close();
        }else{
            mesg="Employee already exist";
            this.showMessages(mesg, 1);
            }
        String files;
        files = this.txtIdentification.getText()+", "+this.txtName.getText()+","+this.txtGender.getSelectionModel().getSelectedItem()+", "+this.txtBirthDay.getText()+", "+this.txtCharge.getSelectionModel().getSelectedItem()+", "+this.txtWage.getText();
        this.emManag.guardarEmepleados(files);
    }

    @FXML
    private void doExit(ActionEvent event) {
        this.myEmployees = null;
        Stage stage = (Stage) this.btnSave.getScene().getWindow();
        stage.close();
    }
    
    //Get Employee for return data

    public Employee getEmploy() {
        return employ;
    }
    
    //Private Method to Fill Combo Box on the Interface
    private void fill_combox(){
        //Gender
        this.txtGender.getItems().add("Femenino");
        this.txtGender.getItems().add("Masculino");
        this.txtGender.getItems().add("Helicóptero Apache");
        this.txtGender.getItems().add("Prefiero no decirlo");
        //Charge
        this.txtCharge.getItems().add("Recepcionista");
        this.txtCharge.getItems().add("Aseos Generales");
        this.txtCharge.getItems().add("Departamento técnico");
        this.txtCharge.getItems().add("Administrador(a) de empresas");
        this.txtCharge.getItems().add("Auxiliar de consultorio");

    } 
    
     //Confirmation Messages
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

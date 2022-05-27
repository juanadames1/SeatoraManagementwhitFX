/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import managment.SeatoraManagment;
import model.Patient;


/**
 * FXML Controller class
 *
 * @author juanc
 */
public class AddPatientController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtIdentification;
    @FXML
    private TextField txtDateOfBirth;
    @FXML
    private ComboBox txtGender;
    @FXML
    private TextField txtStartDate;
    @FXML
    private ComboBox txtTypeOfTreatment;
    @FXML
    private TextField txtEndDate;
    @FXML
    private ComboBox txtDentistInCharge;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnExit;
    
    //Variables without @FXML
    private SeatoraManagment paManag;
    private ObservableList<Patient> myPatients;
    private Patient patien;

    /**
     * Initializes the controller class.
     */
    
    //============ Init ===========================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fill_combox();
        this.paManag = new SeatoraManagment();
    }  
    
    //Init of Attributes
    public void initAttributtes(ObservableList<Patient> myPatients){
        this.myPatients = myPatients;
    }
    
    public void initAttributtes(ObservableList<Patient> myPatients, Patient patient){
        String x,y,z;
        this.myPatients = myPatients;
        this.patien=patient; 
        //Attributtes 
        this.txtName.setText(patient.getNombre());
        this.txtDateOfBirth.setText(patient.getFechanacimiento());
        this.txtEndDate.setText(patient.getFechaftratamiento());
        this.txtIdentification.setText(patient.getCedula()+ "");
        this.txtStartDate.setText(patient.getFechaitratamiento());
        x= (String) this.txtDentistInCharge.getSelectionModel().getSelectedItem();
        y= (String) this.txtGender.getSelectionModel().getSelectedItem(); 
        z= (String) this.txtTypeOfTreatment.getSelectionModel().getSelectedItem(); 
    }

    @FXML
    private void doSave(ActionEvent event) {
        //Take data
        String mesg;
        String name = this.txtName.getText();
        int ide =  Integer.parseInt(this.txtIdentification.getText());
        String gender = (String) this.txtGender.getSelectionModel().getSelectedItem();
        String dateBirth = this.txtDateOfBirth.getText();
        String startDate = this.txtStartDate.getText();
        String typeofTeart = (String) this.txtTypeOfTreatment.getSelectionModel().getSelectedItem();
        String endDate = this.txtEndDate.getText();
        String dentistInCharge = (String) this.txtDentistInCharge.getSelectionModel().getSelectedItem();
        
        //Object created
        Patient patient = new Patient(ide, name, gender, dateBirth, startDate, typeofTeart, endDate, dentistInCharge);
        
        //Conditionals
        if(!this.myPatients.contains(patient)){
            //Modify
            if(this.patien != null){
                
                this.patien.setNombre(name);
                this.patien.setCedula(ide);
                this.patien.setGenero(gender);
                this.patien.setFechaftratamiento(startDate);
                this.patien.setFechaitratamiento(startDate);
                this.patien.setOdontologo(endDate);
                this.patien.setFechanacimiento(dateBirth);
                this.patien.setTipotratamiento(typeofTeart);

                mesg="Modify Succesfully";
                this.showMessages(mesg, 2);
            
            }else{
                //Insert
                
                this.patien = patient;  
                mesg="Added succesfully";
                this.showMessages(mesg, 2); 
                
                Stage stage = (Stage) this.btnSave.getScene().getWindow();
                stage.close();
            }
        }else{
            mesg="Patient already exist";
            this.showMessages(mesg, 1);
            }
        String files;
        files = this.txtIdentification.getText()+", "+this.txtName.getText()+","+this.txtGender.getSelectionModel().getSelectedItem()+", "+this.txtDateOfBirth.getText()+", "+this.txtStartDate.getText()+", "+this.txtTypeOfTreatment.getSelectionModel().getSelectedItem()+", "+this.txtEndDate.getText()+", "+this.txtDentistInCharge.getSelectionModel().getSelectedItem();
        this.paManag.guardarPacientes(files);
    }

    @FXML
    private void doExit(ActionEvent event) {
            this.myPatients = null;
            Stage stage = (Stage) this.btnSave.getScene().getWindow();
            stage.close();
    }
    //Get Patient for return data

    public Patient getPatien() {
        return patien;
    }
    
    //Private Method to Fill Combo Box on the Interface
    private void fill_combox(){
        //Gender
        this.txtGender.getItems().add("Femenino");
        this.txtGender.getItems().add("Masculino");
        this.txtGender.getItems().add("Helicóptero Apache");
        this.txtGender.getItems().add("Prefiero no decirlo");
        //Tipos de tratamientos
        this.txtTypeOfTreatment.getItems().add("Ortodoncia");
        this.txtTypeOfTreatment.getItems().add("Cirugía maxilofacial");
        this.txtTypeOfTreatment.getItems().add("Emergencia");
        this.txtTypeOfTreatment.getItems().add("Limpieza dental");
        this.txtTypeOfTreatment.getItems().add("Corona sobre implante");
        this.txtTypeOfTreatment.getItems().add("Carillas dentales");
        this.txtTypeOfTreatment.getItems().add("Blanqueamiento dental");
        //Dentist in charge
        this.txtDentistInCharge.getItems().add("Camilo Sanchez");
        this.txtDentistInCharge.getItems().add("Carlos Calvache");
        this.txtDentistInCharge.getItems().add("Camilo Pardo");
        this.txtDentistInCharge.getItems().add("Felipe Tocho");
        this.txtDentistInCharge.getItems().add("Santiago Silvercraft");
        this.txtDentistInCharge.getItems().add("Sergio Marcado");
        this.txtDentistInCharge.getItems().add("Silvana Cortés");
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

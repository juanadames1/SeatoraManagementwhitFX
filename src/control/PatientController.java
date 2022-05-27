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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Patient;
import managment.SeatoraManagment;

/**
 * FXML Controller class
 *
 * @author Adames
 */
public class PatientController implements Initializable {

    @FXML
     ImageView imgPatients;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colIde;
    @FXML
    private TableColumn colGender;
    @FXML
    private TableColumn colBirth;
    @FXML
    private TableColumn colStartDate;
    @FXML
    private TableColumn colType;
    @FXML
    private TableColumn colEndDate;
    @FXML
    private TableColumn colDentist;
    @FXML
    private TableView<Patient> tblPatients;
    @FXML
    private Button btnBringPatient;
    
   //Variables without @FXML
    private ObservableList<Patient> myPatients;
    private SeatoraManagment Cmag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.modelTable();
        this.Cmag=new SeatoraManagment();
    }    

    @FXML
    private void doAdd(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/AddPatient.fxml"));
            
            Parent root = loader.load();
            
            AddPatientController controlador = loader.getController();
            controlador.initAttributtes(myPatients);
            
            Scene scene=new Scene(root);    
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); 
            
            Patient patient = controlador.getPatien();
            if(patient != null){
                this.myPatients.add(patient);
                this.tblPatients.refresh(); 
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
        Patient patient = this.tblPatients. getSelectionModel().getSelectedItem();
        if (patient == null){
            mesg="You must to select a patient";
            this.showMessages(mesg, 1);
        }
        else{
            try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/AddPatient.fxml"));
            
            Parent root = loader.load();
            
            AddPatientController controlador = loader.getController();
            controlador.initAttributtes(myPatients, patient);
            
            Scene scene=new Scene(root);    
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); 
            
            Patient aux = controlador.getPatien();
            if(aux != null){
                this.tblPatients.refresh();
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
        Patient patient = this.tblPatients. getSelectionModel().getSelectedItem();
        if (patient == null){
            mesg="You must to select a patient";
            this.showMessages(mesg, 1);
        }
        else{
            this.myPatients.remove(patient);
            this.tblPatients.refresh();
            this.Cmag.eliminarPatients();
            mesg="Patient deleted";
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
    private void doBringP(ActionEvent event) {
        ArrayList <Patient> patients= Cmag.getPacientes();
        for(Patient run: patients){
            this.myPatients.add(run);
        }
        this.tblPatients.setItems(this.myPatients);
    }

    
    //Aditional
    private void modelTable(){
         //Init of "ArrayList" of FX 
        this.myPatients=FXCollections.observableArrayList();    
        this.tblPatients.setItems(myPatients);
        //Let's asociate the Columns and start the Table
        this.colIde.setCellValueFactory(new PropertyValueFactory("cedula"));
        this.colName.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colGender.setCellValueFactory(new PropertyValueFactory("genero")); 
        this.colBirth.setCellValueFactory(new PropertyValueFactory("fechanacimiento")); 
        this.colStartDate.setCellValueFactory(new PropertyValueFactory("fechaitratamiento"));
        this.colType.setCellValueFactory(new PropertyValueFactory("tipotratamiento"));
        this.colEndDate.setCellValueFactory(new PropertyValueFactory("fechaftratamiento")); 
        this.colDentist.setCellValueFactory(new PropertyValueFactory("odontologo")); 
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


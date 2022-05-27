/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control;

import com.sun.javafx.logging.Logger;
import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author juanc
 */
public class FViewController implements Initializable {

    @FXML
    private Button Go_Patients;
    @FXML
    private Button Go_Employees;
    @FXML
    private Button btn_Exit;
    @FXML
    private Button Go_Dentist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void do_Patients(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Patient.fxml"));
            
            Parent root = loader.load();
            
            PatientController controlador = loader.getController();
            
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (IOException ex) {
           String mesg;
           mesg="An Error had ocurred with the Loader";
           System.out.println(mesg);
        }
    }

    @FXML
    private void do_Employees(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Employee.fxml"));
            
            Parent root = loader.load();
            
            EmployeeController controlador = loader.getController();
            
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (IOException ex) {
           String mesg;
           mesg="An Error had ocurred with the Loader";
           System.out.println(mesg);
        }
    }

    @FXML
    private void do_Exit(ActionEvent event) {
         String mesg;
        mesg = "Are you sure you want to leave?";
        if(this.showMessages(mesg, 3) == true){
            Stage stage = (Stage) this.btn_Exit.getScene().getWindow();
            stage.close();
            System.exit(0);
        }
    }
    
    @FXML
    private void do_Dentist(ActionEvent event) {
            try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Dentist.fxml"));
            
            Parent root = loader.load();
            
            DentistController controlador = loader.getController();
            
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); 
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (IOException ex) {
           String mesg;
           mesg="An Error had ocurred with the Loader";
           System.out.println(mesg);
        }
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

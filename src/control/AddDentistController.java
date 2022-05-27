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
import managment.GestionOdontologo;
import model.Odontologo;

/**
 * FXML Controller class
 *
 * @author juanc
 */
public class AddDentistController implements Initializable {

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
    private TextField txtProfessional;
    @FXML
    private ComboBox txtSpecialty;
    
    //Without @FXML
    private GestionOdontologo odMan;
    private ObservableList<Odontologo> myOdons;
    private Odontologo odonto;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fill_combox();
        this.odMan = new GestionOdontologo();
    }    

    //Init of Attributtes
    public void initAttributtes(ObservableList<Odontologo>myOdons){
        this.myOdons=myOdons;
    }
    
    public void initAttributtes(ObservableList<Odontologo>myOdons, Odontologo odontologo){
        String x,y;
        this.myOdons=myOdons;
        this.odonto=odontologo;
        //Attributtes
        this.txtName.setText(odontologo.getNombre());
        this.txtIdentification.setText(odontologo.getCedula()+"");
        x= (String) this.txtGender.getSelectionModel().getSelectedItem();
        this.txtBirthDay.setText(odontologo.getFechanacimiento());
        this.txtWage.setText(odontologo.getSalario());
        this.txtProfessional.setText(odontologo.getTarjeta()+"");
        y= (String) this.txtSpecialty.getSelectionModel().getSelectedItem();
        
    }
    @FXML
    private void doSave(ActionEvent event) {
       //Take data
        String mesg;
        String name = this.txtName.getText();
        int ide =  Integer.parseInt(this.txtIdentification.getText());
        String gender = (String) this.txtGender.getSelectionModel().getSelectedItem();
        String dateBirth = this.txtBirthDay.getText();
        String specialty = (String) this.txtSpecialty.getSelectionModel().getSelectedItem();
        String wage=this.txtWage.getText();
        int profess = Integer.parseInt(this.txtProfessional.getText());
        //Object created
        Odontologo odontologo = new Odontologo(ide, name, gender, dateBirth, profess, specialty ,wage);
        
        //Conditionals
        if(!this.myOdons.contains(odontologo)){
            //Modify
            if(this.odonto != null){
                
                this.odonto.setNombre(name);
                this.odonto.setCedula(ide);
                this.odonto.setGenero(gender);
                this.odonto.setFechanacimiento(dateBirth);
                this.odonto.setTarjeta(profess);
                this.odonto.setEspecialidad(specialty);
                this.odonto.setSalario(wage);

                mesg="Modify Succesfully";
                this.showMessages(mesg, 2);
            
            }else{
                //Insert
                
                this.odonto = odontologo;  
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
        files = this.txtIdentification.getText()+", "+this.txtName.getText()+","+this.txtGender.getSelectionModel().getSelectedItem()+", "+this.txtBirthDay.getText()+", "+this.txtProfessional.getText()+", "+this.txtSpecialty.getSelectionModel().getSelectedItem()+", "+this.txtWage.getText();
        this.odMan.guardarOdontologo(files);
    }  

    @FXML
    private void doExit(ActionEvent event) {
        this.myOdons = null;
        Stage stage = (Stage) this.btnSave.getScene().getWindow();
        stage.close();
    }
    
    //Get

    public Odontologo getOdonto() {
        return odonto;
    }
    
    //Private Method to Fill Combo Box on the Interface
    private void fill_combox(){
        //Gender
        this.txtGender.getItems().add("Femenino");
        this.txtGender.getItems().add("Masculino");
        this.txtGender.getItems().add("Helicóptero Apache");
        this.txtGender.getItems().add("Prefiero no decirlo");
        //Charge
        this.txtSpecialty.getItems().add("Dentista");
        this.txtSpecialty.getItems().add("Ortodoncista");
        this.txtSpecialty.getItems().add("Maxilofacial");
        this.txtSpecialty.getItems().add("Láser");
        this.txtSpecialty.getItems().add("Cirujano plástico");

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

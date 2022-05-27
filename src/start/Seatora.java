package start;

//Code made by Adames Pardo Juan Camilo and Camilo Sanches for the final of POO 2022-1

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Seatora extends Application{
    public static void main (String[]args){
        launch(args);
    }
    

    @Override
    public void start(Stage window) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/view/FView.fxml"));
       Scene scene = new Scene(root);
       window.setScene(scene);
       
       window.setTitle("Seatora Managment");
       window.setResizable(false);//User don't allowed to change window's resolution
       window.setOnCloseRequest(event->{
           event.consume();});
       window.show();//Show the window
       
     }
    
}

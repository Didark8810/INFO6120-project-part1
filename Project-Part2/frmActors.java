import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.stage.*;
import java.sql.*;
import java.util.List; 
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

public class frmActors extends Application
{
    Connection conn = null;
    TextField txtFirstName, txtLastName;
    Label lblListTitle, lblFirstName, lblLastName;
    ListView<String> lstActorsData;
    Button btnShowActors, btnAddActor, btnRemoveActor;
    database dataMng;
    Alert alrMessage;
    
    public void start(Stage myStage) throws Exception 
    {
        try
        {
            dataMng = new database();
            
            myStage.setTitle("Actors");
            GridPane grid = new GridPane();
            GridPane grdButtons = new GridPane();
            grid.setAlignment(Pos.CENTER);
            
            Scene scene = new Scene(grid, 500, 500);
            scene.getStylesheets().add("style.css");
            
            myStage.setScene(scene);
            myStage.show();
            
            Text scenetitle = new Text("Actors");
            scenetitle.setId("title");
            
            lblListTitle = new Label("Actors");
            
            lstActorsData = new ListView<>();
            
            btnShowActors = new Button("List Actors");
            BShowActorsDataListener listenerShowActorData = new BShowActorsDataListener();
            btnShowActors.setOnAction(listenerShowActorData);
            
            btnAddActor = new Button("Add Actor");
            BAddActorDataListener listenerAddActor = new BAddActorDataListener();
            btnAddActor.setOnAction(listenerAddActor);
            
            btnRemoveActor = new Button("Remove Actor");
            BRemoveActorListener listenerRemoveActor = new BRemoveActorListener();
            btnRemoveActor.setOnAction(listenerRemoveActor);
            
            lblFirstName = new Label("First Name: ");
            lblLastName = new Label("Last Name: ");
            txtFirstName = new TextField();
            txtLastName = new TextField();
            
            grid.add(lblListTitle, 0, 0);
            grid.add(lstActorsData, 0, 1);
            grid.add(btnShowActors, 0, 2);
            grid.add(lblFirstName, 0, 3);
            grid.add(txtFirstName, 0, 4);
            grid.add(lblLastName, 0, 5);
            grid.add(txtLastName, 0, 6);
            
            grdButtons.add(btnAddActor, 0, 0);
            grdButtons.add(btnRemoveActor, 1, 0);
            
            grid.add(grdButtons, 0, 7);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    public void cleanForm()
    {
        txtFirstName.setText("");
        txtLastName.setText("");
        lstActorsData.getItems().clear();
    }
    
    private class BShowActorsDataListener implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            ObservableList<String> data = dataMng.selectAll();
            lstActorsData.setItems(data);
        }
        
    }
    
    private class BAddActorDataListener implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            String firstName = txtFirstName.getText().trim();
            String lastName = txtLastName.getText().trim();
            
            if (firstName.isEmpty() || lastName.isEmpty())
            {
                alrMessage = new Alert(AlertType.WARNING);
                alrMessage.setContentText("First name and last name of the actor is required");
                alrMessage.show();
            }
            else
            {
                dataMng.insertRow(firstName, lastName);
                cleanForm();
                
                alrMessage = new Alert(AlertType.INFORMATION);
                alrMessage.setContentText("Actor Inserted!");
                alrMessage.show();
            }
        }
    }
    
    private class BRemoveActorListener implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent event)
        {
            String firstName = txtFirstName.getText().trim();
            String lastName = txtLastName.getText().trim();
            
            if (firstName.isEmpty() || lastName.isEmpty())
            {
                alrMessage = new Alert(AlertType.WARNING);
                alrMessage.setContentText("First name and last name of the actor is required");
                alrMessage.show();
            }
            else
            {
                dataMng.deleteRow(firstName, lastName);
                cleanForm();
                
                alrMessage = new Alert(AlertType.INFORMATION);
                alrMessage.setContentText("Actor Removed!");
                alrMessage.show();
            }
        }
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class database {

    private Connection connect() {
        String url = "jdbc:sqlite:sakila.db"; 
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    //list all actors
    public ObservableList selectAll(){
        String sql = "SELECT first_name,last_name FROM actor";
        //String data = "";
        ObservableList<String> data = FXCollections.observableArrayList();
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                //System.out.printf("%-10s %-10s%n", rs.getString("first_name"),rs.getString("last_name"));
                //data += rs.getString("first_name") + rs.getString("last_name") + "\n";
                data.add(rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return data;
    }

    //Insert new actors
    public void insertRow(String firstName, String lastName)
    {
        String SQL = "INSERT INTO actor (first_name,last_name) VALUES (?, ?)";          
        try
        {
            Connection conn = this.connect();    
            PreparedStatement stmt = conn.prepareStatement(SQL);     
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    //Delete actors by first name and last name
    public void deleteRow(String firstName, String lastName)
    {
        String SQL = "DELETE FROM actor WHERE first_name = '" +firstName+"' and last_name='"+lastName+"';" ;
        try
        {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
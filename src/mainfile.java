import java.util.Scanner;

public class mainfile {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        String userCommand_firstName="";
	        String userCommand_lastName ="";
	        database data = new database();
	        boolean exitProgram = false;
	        while(!exitProgram) {
		        System.out.print("Enter a command: ");
		        String userCommand = scanner.nextLine();
		        if(userCommand.toLowerCase().equals("add")) {
		        	
		        	System.out.print("Enter a first name: ");
			        userCommand_firstName = scanner.nextLine();
			        
		        	System.out.print("Enter a last name: ");
			        userCommand_lastName = scanner.nextLine();
			        //add actor to the data base
			        data.insertRow(userCommand_firstName,userCommand_lastName);
		        }else if(userCommand.toLowerCase().equals("list")) {
		        	//show all actors
		        	data.selectAll();
		        }
		        else if(userCommand.toLowerCase().equals("remove") ) {
		        	System.out.print("Enter a first name: ");
			        userCommand_firstName = scanner.nextLine();

		        	System.out.print("Enter a last name: ");
			        userCommand_lastName = scanner.nextLine();
			        //remove actor from the data base
			        data.deleteRow(userCommand_firstName,userCommand_lastName);
		        }
		        else if(userCommand.toLowerCase().equals("exit")) {
		        	//finish the program
		        	exitProgram = true;
		        	System.out.println("bye =)");		
		        }
		        else {
		        	System.out.println("Invalid command *_*");
		        }
	        }
	        scanner.close();   
	    }
}

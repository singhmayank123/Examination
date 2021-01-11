import java.util.Scanner;  //Importing scanner class for reading the user inputs
import java.util.ArrayList;// for dynamic arrays

class Admin{
/*These are private fields which can't be accessed outside the class directly*/
	private String username;
	private String password;

	public Admin(){
		//Do nothing
	}

	public Admin(String username, String password){
//Sets the admin name and password
		this.username = username;
		this.password = password;
	}
//Accessors for private fields 
	public String getUser(){
		return username;
	}

	public String getPass(){
		return password;
	}
/* Returns the admin information to the caller */
	public String toString(){

		String res = "\nUsernamw: "+username+
					 " password: "+password;

		return res;
	}
}

/*********************************************************************/
public class Quesbank{

	ArrayList<Admin>admins = new ArrayList<Admin>(); //stores the admin records
	ArrayList<String>questions = new ArrayList<String>(); //stores the questions
	ArrayList<String>answers = new ArrayList<String>(); //Stores  the answer of ech question

//main driver
	public static void main(String[] args) {

		int score = 0; //It will count your total scores

		//create object of scanner class for user input
		Scanner input = new Scanner(System.in);
//create instance of Quesbank class 
		Quesbank q = new Quesbank();
//Pattern printing 		
		for(int i = 0; i<20; i++)
			System.out.print("-|---");

		System.out.println("\n\n\t\t\t\tWelcome to test maker !\n");

		for(int i = 0; i<20; i++)
			System.out.print("-|---");
//Stores the user result
		String res;
/* Use labelling for loops so that you can get back from the inner interfaces */
		outer:do{

/* printing values */
			System.out.println("\n\nYou are ?");
			System.out.println("a. Admin");
			System.out.println("b. User");
			System.out.print("Enter: ");
			 res = input.next();
/* equals matches two same objects as string is treated 
object so always use equals() method for comparison*/
			 if(res.equals("a")){
/* Adds the initial admin to the record */
			q.addAdmin();

			System.out.println("\n");
//print pattern
			for(int i = 0; i<20; i++)
				System.out.print("-|---");
//print pattern
			System.out.println("\n\n\t\t\t\t\tHi! Admin.\n");
//print pattern
			for(int i = 0; i<20; i++)
				System.out.print("-|---");
//Stores user choice for admin interface 
			String choice;

			do{
//Display() shows the admin interface to the user
				display();
/* Read the next characters from the console before the whitespaces */
				choice = input.next();
/* If user wishh to go back then continue the outer loop, which skips
 the further execution and redirects to the start */
				if(choice.equals("q")) continue outer;
/* Matches each cases and calls the methods for teh selected choice */

				switch(choice){

					case "a":
						q.addAdmin();
						break;
					case "b":
						q.delAdmin();
						break;
					case "c":
						q.searchAdmin();
						break;
					case "d":
						q.showAdmins();
						break;
					case "e":
						q.addQues();
						break;
					case "f":
						q.delQues();
						break;
					case "g":
						q.showQues();
						break;

				}
//Iterates until the user hits ctrl+exit 
			}while(true);
		}

/* If the users chooses to give exam */
		else if(res.equals("b")){
//Interface 			
			System.out.println("a. Math Exam");
			System.out.println("b.Java Exam");
			System.out.println("\nChoose Exam");

			res = input.next(); 

			if(res.equals("a")){
				System.out.printf("\nThere are %d questions! All the best.\n",q.questions.size());

/* Adds the question and answer using parallel arrays */

				for(int i = 0; i<q.questions.size(); i++){
					System.out.println(q.questions.get(i));
					System.out.print("Enter you answer: ");
					String answer = input.next();
					System.out.println("\nYour entered answer: "+answer);
					if(q.answers.get(i).equals(answer)){
						System.out.println("\nCorrect answer.");
						score++;
					}
					else{

						System.out.println("\nWrong answer.");

					}
				}

				System.out.printf("\n\nYour Score is %d out %d ", score,q.questions.size());
			}
			else if(res.equals("b")){
				System.out.println("\nSorry! no questions available for this test.");
			}
		}

	} while(true);
			
} // End of main method 

/***************************************************************************************/

/* Interface for admin */
		public static void display(){

			System.out.println("\n\na. Add an admin");
			System.out.println("b. Delete an admin");
			System.out.println("c. Search for an admin");
			System.out.println("d. Show all admins");
			System.out.println("e. Add a question");
			System.out.println("f. Delete a question");
			System.out.println("g. Show all questions");
			System.out.println("Press q to go back..");
			//int choice = input.nextInt();

		}

/************************************************************************************/

/* Method for adding the records of teh admin */

		public void addAdmin(){

			Scanner input = new Scanner(System.in);
			System.out.print("\nEnter username: ");
			String username = input.next();
			System.out.print("Enter password: ");
			String password = input.next();
	 		admins.add(new Admin(username,password));
		}

/***********************************************************************************/

/* Method for adding the question */

		public void addQues(){

			Scanner input = new Scanner(System.in);
			System.out.print("\nWrite your question: ");
			String question = input.nextLine();
	 		questions.add(question);
	 		System.out.print("\nAdd the answer: ");
			String answer = input.nextLine();
	 		answers.add(answer);
		}

/***********************************************************************************/

/* Method for deleting the admins */

		public void delAdmin(){
/* setting to  indicate that no record is deleted till now  */
			boolean isdel = false;

//validates the user credentials 
			Scanner input = new Scanner(System.in);
			System.out.print("\nEnter username: ");
			String username = input.next();
			System.out.print("Enter password: ");
			String password = input.next();

			if(admins.isEmpty()){
				System.out.println("You don't have any admin records to Delete! ");
			}
//validates the user credentials 
			for(int i = 0; i<admins.size(); i++){

					if(admins.get(i).getUser().equals(username) && 
						admins.get(i).getPass().equals(password)){

						admins.remove(i);
						System.out.println("\nData deleted successfully!");
//If records deleted then set isdel true
						isdel =  true;
						
				    }
			}
// If records not deleted means login credentials didn't match
			if(!isdel) System.out.println("\nInvalid login credentials!");
	
		}

/***********************************************************************************/

/* Method for adding the question */
		public void delQues(){

			Scanner input = new Scanner(System.in);
			System.out.print("\nEnter question Number: ");
			int number= input.nextInt();
/* Removes the question from array as index starts from 0 */
			questions.remove(number-1);
			System.out.print("\nQuestion deleted successfully!");

		}

/***********************************************************************************/

/* search the admin records using his username */
		public void searchAdmin(){

			Scanner input = new Scanner(System.in);

			System.out.print("\nEnter username: ");
			String username = input.next();
			
			if(admins.isEmpty()){
				System.out.println("You don't have any records to Search! ");
			}

			for(int i = 0; i<admins.size(); i++){

					if(admins.get(i).getUser().equals(username)){

						System.out.println("\nUser exists in the records!");
				    }
			}

		}

/*****************************************************************************/

		public void showQues(){
			
				System.out.println(String.valueOf(questions));			

		}

/*****************************************************************************/

		public void showAdmins(){

			for(int i = 0; i<admins.size(); i++)
				System.out.println(admins.get(i).toString());
			
		}

}

/************************ Ends here ***********************/
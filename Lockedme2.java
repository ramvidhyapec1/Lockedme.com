package com.Lockedme;
//input like - 2 - 4 - 1 - Goes out of the application

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lockedme2 {
	//When it is empty - another message to be displayed - ie the folder
	
	static int first_menu_choice;
	static int second_menu_choice;
	static Scanner sc;
	
	public static void main(String[] args) throws IOException 
	{		
	//Initiating the scanner object
		sc =new Scanner(System.in);
		
	//Displaying the welcome screen
		welcomeScreen();	
		
	//Displaying the first menu choices and capturing users input
		first_menu_choice=showMainMenu(); 
	//Calling the appropriate methods based on users choice
		switching_first_menu_choice(first_menu_choice);
		
	//Exiting out of the application
		exitWithThankYouMessage();	
		
	//Closing the Scanner object
		sc.close();
	}
	
	//Welcome Screen
	private static void welcomeScreen() 
	{	ClearConsole();
		System.out.println("*****************************************Welcome to Lockedme.com*****************************************");
		System.out.println("*************************************Devloped by : Vidya Ramanathan**************************************");
		System.out.println("*********************************************************************************************************");
	}
	
	//Clearing the screen before the execution of the program
		public static void ClearConsole(){
		try {
			String operatingSystem = System.getProperty("os.name"); //Check the current operating system
	              
			if(operatingSystem.contains("Windows")){        
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			} else {
				ProcessBuilder pb = new ProcessBuilder("clear");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			} 
		}catch(Exception e){
	            System.out.println(e);
	    	}
	    }
	
	//Main Menu
	private static int showMainMenu() throws IOException
	{
		//Displaying the menu choices
		System.out.println("What would you like to do today?");
		System.out.println("Enter 1 : List all the files");
		System.out.println("Enter 2 : Add / Delete / Search for a file");
		System.out.println("Enter 3 : Close the application");
		
		//Getting the input from the user
		first_menu_choice=sc.nextInt();    //Storing the users choice as first_menu_choice
		if(first_menu_choice != 1 && first_menu_choice !=2 && first_menu_choice !=3)
		{
			System.out.println("Please enter a valid choice");  //Displaying error message if user does not enter a valid choice
			showMainMenu();
		};		
		return first_menu_choice; //returning the first_menu_choice
	}
	
	//Switching first_menu_choice
	private static void switching_first_menu_choice(int first_menu_choice) throws IOException, InputMismatchException
	{try {
		switch (first_menu_choice) 
		{		
			case 1: 
			{
				listFiles(); 
				return;					
			}
			case 2: 
			{
				addDeleteSearchMenu();
				return;
			}
			case 3: 
			{
				break;			
			}		
			default: 
				System.out.println("Please enter a valid choice");
				showMainMenu();
				switching_first_menu_choice(first_menu_choice);
		}
	}//
		catch(Exception e){
			if(e.getMessage().contains("InputMismatchException")) {
			System.out.println("Please enter a valid choice");
			showMainMenu();
			switching_first_menu_choice(first_menu_choice);	
			}
		}
	}
	
	//Method to list the files 
	private static void listFiles() throws NullPointerException,IOException {
		try {
		ClearConsole();
		System.out.println("Enter the directory path to list");
		String directory;
		directory=sc.next();
		File dir=new File(directory);
		if(!dir.exists()) {
			System.out.println("No such file / folder exists.");
			listFiles();
		}
		if(dir.list().length<=0)
		{
			System.out.println("There are no files to list in this directory");
		}
		
		System.out.println("\n");
		System.out.println("*********************List of files in the current directory*********************");
		System.out.println("\n");
		System.out.println("Main Directory: "+dir.getName());
		listFilesAndFolder(dir);
		}catch(Exception e) {
			System.out.println("There are no files to list.");
			
		}
		System.out.println("\n\n\n");
		System.out.println("*******************End of list of files*******************");
		showMainMenu();
		switching_first_menu_choice(first_menu_choice);
	}
	
	private static void listFilesAndFolder(File file) {
		File[] files=file.listFiles();
		Arrays.sort(files);
		
		if (files != null && files.length>0) {
			for( File f:files) {
				if(f.isFile()) {
					System.out.println("\t File: "+f.getName() + "(size in bytes: "+f.length()+")");
				}
			}
			for(File d:files) {
				if(d.isDirectory()) {
					System.out.println("Folder : "+d.getName());
					listFilesAndFolder(d);
				}
			}
		}		
	}
		
	private static void addDeleteSearchMenu() throws IOException 
	{
		System.out.println("Please select your choice");
		System.out.println("Press 1: Add a file");
		System.out.println("Press 2: Delete a file");
		System.out.println("Press 3: Search for a file");
		System.out.println("Press 4: Go back to Main/Previous menu");
		System.out.println("Press 5: Exit the application");
	
		second_menu_choice =sc.nextInt();
		
		switch (second_menu_choice) 
		{
			case 1: 
			{
				addFile();
				return;
			}
			case 2: 
			{
				deleteFile();
				return;
			}
			case 3: 
			{
				searchFile();
				return;
			}
			case 4: 
			{
				showMainMenu();
				switching_first_menu_choice(first_menu_choice);
			}	
			case 5:
			{
				exitWithThankYouMessage();
				sc.close();				
			}
			default:
			{
				System.out.println("Please enter a valid choice");
				addDeleteSearchMenu();
			}
		}	
	}
	
	private static void addFile() throws IOException 
	{	
		System.out.println("Please enter the name of the new file to be added along with its extension(Eg: Testing.png)");
		String new_file_name=sc.next();
		File newfile=new File("C:\\Users\\gnana\\eclipse-workspace\\com.Lockedme\\src\\com\\Lockedme",new_file_name);
		boolean result=newfile.createNewFile();
		if (result) {
			System.out.println("Your file has been added successfully!");
			System.out.println("\n");
			showMainMenu();
			switching_first_menu_choice(first_menu_choice);
			return;
		}else
		{
			System.out.println("File with that name already exists.");
			addFile();
		}
	}
	
	private static void searchFile() throws IOException
	{
		System.out.println("Please enter the name of the file to be searched");
		String searchedFileName=sc.next();
		File directory=new File("C:\\Users\\gnana\\eclipse-workspace\\com.Lockedme\\src\\com\\Lockedme");
		searchinFiles(directory,searchedFileName);
	}
	private static void searchinFiles(File directory, String searchedFileName) throws IOException
	{  int found=0;
		File[] files=directory.listFiles();
		String fileName;
		if (files != null && files.length>0) {
			for( File f:files) {
				if(f.isFile()) {
					//System.out.println(f.toString());
					fileName=f.getName();
					//System.out.println(fileName);
					if(fileName.equalsIgnoreCase(searchedFileName)) {
						System.out.println("************************************************************************************************");
						System.out.println("File "+fileName+" has been found in "+directory.toString());
						System.out.println("Size in bytes: "+f.length());
						System.out.println("************************************************************************************************");
						found=1;
						return;
					}
					
				}
			}
				if(found !=1) {
					for(File f:files) {
						if(found !=1) {
							if(f.isDirectory()) {
								directory=f;
								searchinFiles(directory,searchedFileName);
							
							}
						}else return;
					}				
				}				
		}if(found==0) {
			System.out.println("Sorry! No file with the name \""+searchedFileName+"\" was found");
		}
		
	}
	
	
		/*System.out.println("Please enter the name of the file to be searched");
		String searchedFileName=sc.next();
		File directory=new File("C:\\Users\\gnana\\eclipse-workspace\\com.Lockedme\\src\\com\\Lockedme");
		int found=0;
		File[] files=directory.listFiles();
		if (files != null && files.length>0) {
			for( File f:files) {
				if(f.isFile()) {
					searchFiles(directory,f,searchedFileName);
					/*if(f.toString().equalsIgnoreCase(searchedFileName)) {
					System.out.println("************************************************************************************************");
					System.out.println("File "+f.toString()+" has been found in "+directory.toString());
					System.out.println("\t File: "+f.getName() + "(size in bytes: "+f.length()+")");
					System.out.println("************************************************************************************************");
					found=1;
					
					}
				if(f.isDirectory()) {
					searchFolder(File f, String searchedFileName);
					/*directory=new File(f.getAbsolutePath());
					files=directory.listFiles();
					
					System.out.println("Searching in folder "+directory.toString());
					if(f.isFile()) {
						if(f.toString().equalsIgnoreCase(searchedFileName)) {
						System.out.println("************************************************************************************************");
						System.out.println("File "+f.toString()+" has been found in "+directory.toString());
						System.out.println("\t File: "+f.getName() + "(size in bytes: "+f.length()+")");
						System.out.println("************************************************************************************************");
						found=1;
						
						}
					}
				}
			}
		}
		}
				
		/*String[] files=directory.list();
		int found=0;
		if (files != null && files.length>0) {
		for(String fil:files) {
			if(fil.equalsIgnoreCase(searchedFileName)) {
				System.out.println("************************************************************************************************");
				System.out.println("File "+fil+" has been found in "+directory.toString());
				System.out.println("************************************************************************************************");
				found=1;
			}			
		}
		if(found ==0 ) {
			System.out.println("Sorry! No file with the name \""+searchedFileName+"\" was found");
		}
		showMainMenu();
		switching_first_menu_choice(first_menu_choice);
		}
	private static int searchFiles(File directory,File f,String searchedFileName) {
	if(f.toString().equalsIgnoreCase(searchedFileName)) {
		System.out.println("************************************************************************************************");
		System.out.println("File "+f.toString()+" has been found in "+directory.toString());
		System.out.println("\t File: "+f.getName() + "(size in bytes: "+f.length()+")");
		System.out.println("************************************************************************************************");
		int found=1;
		return found;
		}
	
	}	*/
	

	private static void deleteFile() throws IOException
	{
		String nameOfFile;
		System.out.println("Please enter the name of the file to be deleted along with the extension");
		String fileToBeDeleted=sc.next();
		File currentDirectory=new File("C:\\Users\\gnana\\eclipse-workspace\\com.Lockedme\\src\\com\\Lockedme");
		File[] files=currentDirectory.listFiles();		
		int found=0;
		for(File fil:files) 
		{
			nameOfFile=fil.getName();
			if(nameOfFile.equalsIgnoreCase(fileToBeDeleted))
			{
				System.out.println("File \""+fileToBeDeleted+"\" is found. Are you sure you want to delete it? Y / N");
				String choice=sc.next();
				if(choice.equalsIgnoreCase("Y")) {
					fil.delete();
					System.out.println("************************************************************************************************");
					System.out.println("File has been deleted successfully.");
					System.out.println("************************************************************************************************");
					found =1;			
				}else if (choice.equalsIgnoreCase("N")) {
					System.out.println("************************************************************************************************");
					System.out.println("File is not deleted.");
					System.out.println("************************************************************************************************");
					found=1;
				}
			}			
		}
		if(found==0) {
		System.out.println("************************************************************************************************");
		System.out.println("Sorry! No file with the name \""+fileToBeDeleted+"\" was found.");
		System.out.println("************************************************************************************************");
		System.out.println("\n");
		}
		showMainMenu();
		switching_first_menu_choice(first_menu_choice);
	}	

//Thank you and Exit out of application
private static void exitWithThankYouMessage() {
	System.out.println("\n");
	System.out.println("************************************************************************************************");
	System.out.println("\n");
	System.out.println("                       Thank you for using Lockedme.com Bye!");
	System.out.println("\n");
	System.out.println("************************************************************************************************");
	
}
}

package com.Lockers;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.naming.AuthenticationException;

public class Main {
//Checking git
	//connecting git
	private static int choiceBusiness = 0;
	private static int choiceMain=0;
	private static int mainMenuUpperBound=3;
	private static int businessMenuUpperBound=4;

	public static void main (String[] args) throws Exception{
		// TODO Auto-generated method stub

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		@SuppressWarnings("resource")
		Scanner choiceScanner=new Scanner(System.in);
		do {

			showMainMenu();
			System.out.println("\nWhats your choice?");

			try {
				
				choiceMain= Integer.parseInt(choiceScanner.nextLine());
				boolean repeatMenu= menuChoiceValidation(mainMenuUpperBound,choiceMain);
				if (repeatMenu) {
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				
				choiceMain=4;
				boolean repeatMenu= menuChoiceValidation(mainMenuUpperBound,choiceMain);
				if (repeatMenu) {		
					continue;
				}

			}
			finally {
				//				choiceScanner.close();
			}
			
			switch (choiceMain) { //switch main
			case 1 : 
				sortFiles();
				displayFiles();
				break;

			case 2 :
				boolean breaking=false;
				boolean redirect=false;

				do{ //loop for business operation menu
					showBusinessMenu();

					try {
						choiceBusiness=Integer.parseInt(scanner.nextLine());
						boolean repeatBusinessMenu= menuChoiceValidation(businessMenuUpperBound,choiceBusiness);
						if (repeatBusinessMenu) {
							continue;
						}

					} catch (Exception e) {
						// TODO: handle exception
						choiceBusiness=5;
						boolean repeatMenu= menuChoiceValidation(mainMenuUpperBound,choiceBusiness);
						if (repeatMenu) {
							continue;
						}
					}

					finally {
						//						scanner.close();
					}
					
					
					switch (choiceBusiness) { //switch statement for the business operation menu


					case 1:
						addFile();		
						break;

					case 2:
						deleteFile();
						break;
					case 3:
						searchFile();
						break;

					case 4:
						System.out.println("Going back to the Main Menu");
						breaking=true;
						break;

					default:
						System.out.println("Not a valid input. Try from the options above");

					}//Close Switch for business operation menu
					if (breaking==true) {
						redirect=true;
						break;
					}

				}while(choiceBusiness !=0); //Business Menu Loop Close

				if (redirect) {
					continue;
				}
		
			case 3:
				System.out.println("Aborting program");
				System.exit(0);
			default :
				System.out.println("Not a valid choice");
			}//switch main close
		}while (choiceMain !=3); //Main Menu loop close

	}//main method close

	static void showMainMenu() {
		System.out.println("\n\t\t\tMAIN MENU");
		System.out.println("Please select the following options and press enter key for your choice");
		System.out.println("1. Display the files present in the directory");
		System.out.println("2. More options");
		System.out.println("3. Exit the program");

	}

	static void showBusinessMenu() {
		System.out.println("\n\t\t\t BUSINESS OPERATIONS MENU");

		System.out.println("Please select the following options and press enter key for your choice");
		System.out.println("1. Add a file in the current directory ");
		System.out.println("2. Delete a file from the current directory");
		System.out.println("3. Search for a specific file");
		System.out.println("4. Return to the main menu");
	}
	
	static void displayFiles()  {
		System.out.println("Files in current directoru will show just in a minute");
		System.out.println("Press 1 if want to view the files in current directory");
		System.out.println("Press 2 if you want to view files in custom directory");
		Scanner scanner=new Scanner(System.in);
		String directoryPath = null;
		
		int choice = Integer.parseInt(scanner.nextLine());
		
		
			try {
				directoryPath=new File(".").getCanonicalPath();
				System.out.println("Current directory is " +directoryPath);
			}catch (NullPointerException e) {
				// TODO: handle exceptions
				System.out.println("The path you entered is invalid. Please check");
				System.exit(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		File directory = null;
		
		if (choice==1) {
			 directory = new File(directoryPath);
		}
		
		else if (choice==2) {
			try {
				 directory = new File(scanner.nextLine());
				System.out.println("The directory you entered is "+directory);
			} 
			catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println("The path you entered is invalid. Please check");
				System.exit(0);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		}
		
		else {
			System.out.println("Wrong choice. Please try again");
		}
		
		System.out.println("Absolute path is " +directory.getAbsolutePath());
		File[] filesInDirectory = directory.listFiles();
		
		String allFiles=null;
		String allTypes=null;
		
		System.out.println("\n\n**********************************************************************************************************************************");
		System.out.printf("%-4s %-70s %-40s %-50s","Sno.", "File name", "File Tyepe", "File Size");
		System.out.println("\n\n**********************************************************************************************************************************");

		
		double size=0;
		int count=0;
		String unit="bytes";
		
		for (File f : filesInDirectory) {
			size=(double)f.length();
			
			
			if (size>1024 && size<1024*1024) {
				unit="KB";
				size=size/1024;
				size = Math.round(size*10.0)/10.0;	
			
			}
			
			if (size>1024*1024 && size<1024*1024*1024) {
				unit="MB";
				size=size/1024/1024;
				size = Math.round(size*100.0)/100.0;
			}
			
			if (size>1024*1024*1024) {
				unit="GB";
				size=size/1024/1024/1024;
				size = Math.round(size*100.0)/100.0;

			}
			
			System.out.printf("%-4s %-70s %-40s %-50s\n",++count, " "+f.getName(),"type",size+" "+unit );

		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	static void sortFiles() {
		System.out.println("Files are being sorted");
	}

	static void addFile() {
		System.out.println("File is being added, just a minute");

	}

	static void deleteFile() {
		System.out.println("File is being deleted");
	}

	static void searchFile() {
		System.out.println("File is being searched");
	}

	static boolean menuChoiceValidation(int upperBound, int choice){
		while (choice<1||choice>upperBound) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\nInvalid Choice !!");
			System.out.println("Please enter a number between 1 and "+upperBound);
			System.out.println("Press M to see the menu again or press any other key to exit the program");
			char ch = sc.nextLine().charAt(0);
			System.out.println("ch is"+ch);
			System.out.println("");

			try {
				if (ch=='m'|| ch =='M') {
					boolean menuValidation=true;
					return menuValidation;	
				}

				else {
					System.out.println("Aborting!!");
					System.exit(0);
				}

			} catch (NoSuchElementException e) {
				// TODO: handle exception
				System.out.println("Showing the menu");
				System.exit(ch);

			}
		}
		return false;
	}// menu validation close
}//Class Main closed
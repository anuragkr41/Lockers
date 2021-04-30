package com.Lockers;
import java.io.File;

import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

import javax.naming.AuthenticationException;
public class Main {
	private static int choiceBusiness = 0;
	private static int choiceMain=0;
	private static int mainMenuUpperBound=4;
	private static int businessMenuUpperBound=4;
	private static int menuType;
	private static File currentDirectory = new File("");
	private static String currentDirectoryPath = currentDirectory.getAbsolutePath();

	public static void main (String[] args) throws Exception{
		// TODO Auto-generated method stub

		showWelcomeMessage();
		do {
			System.out.println("You are currently in the following directory");
			System.out.println(currentDirectoryPath);
			showMainMenu();
			menuType=1;
			System.out.println("\nWhats your choice?");
			System.out.println(currentDirectoryPath);
			choiceMain=inputNumber(mainMenuUpperBound);

			switch (choiceMain) { //switch main
			case 1 : 
				displayFiles();
				currentDirectoryPath=currentDirectory.getAbsolutePath();
				//				System.out.println("Current Directory is now set to "+ currentDirectory.getAbsolutePath());
				break;

			case 2:


				int sortCh=directoryChoice("sort");
				//				WorkingDirectory(sortCh);
				currentDirectory=WorkingDirectory(sortCh);
				currentDirectoryPath=currentDirectory.getAbsolutePath();

				File[] sortedDirecotry=	sortFiles(currentDirectory);
				displayFiles(sortedDirecotry);
				break;

			case 3 :
				boolean breaking=false;
				boolean redirect=false;

				do{ //loop for business operation menu
					showBusinessMenu();
					menuType=2;
					choiceBusiness=inputNumber(businessMenuUpperBound);

					switch (choiceBusiness) { //switch statement for the business operation menu
					case 1:
						addFile();		
						break;

					case 2:
						deleteFile();
						break;
					case 3:

						System.out.println("\nEnter the name of the file that you want to search");
						File fs=searchFile(currentDirectory, inputFile());

						displayFiles(fs);
						//						System.out.println("File is found in "+fs.getPath());
						break;
					case 4:
						System.out.println("Going back to the Main Menu");
						breaking=true;
						break;
					default:
						System.out.println("Not a valid input. Try from the options above");
					}//Close Switch for business operation menu
					if (breaking==true) { //setting the redirect variable to true so user 
						//											can go back to the main menu. 

						redirect=true;
						break;
					}

				}while(choiceBusiness !=0); //Business Menu Loop Close

				if (redirect) { 
					continue;//for running the loop again from the start after redirection.
				}

			case 4:
				System.out.println("Aborting program");
				System.exit(0);
			default :
				System.out.println("Not a valid choice");
			}//switch main close
		}while (choiceMain !=4); //Main Menu loop close

	}//main method close

	private static void showWelcomeMessage() {
		// TODO Auto-generated method stub
		
		
		
		

		System.out.println("\n\n");
		System.out.println("\t\t\t█░░ █▀█ █▀▀ █▄▀ █▀▀ █▀█ █▀   █▀█ █░█ ▀█▀   █░░ ▀█▀ █▀▄");
		System.out.println("\t\t\t█▄▄ █▄█ █▄▄ █░█ ██▄ █▀▄ ▄█   █▀▀ ▀▄▀ ░█░   █▄▄ ░█░ █▄▀");
		
		
		
		
		
		System.out.println("\n\n\t\t\t\t\t***Welcome to Lockers Pvt Ltd***");
		System.out.println("\t\t\t\t\t\t[By Anurag Kumar]\n\n");

	}

	static void addFile() {
		System.out.println("File is being added, just a minute");

	}

	static void deleteFile() {
		System.out.println("File is being deleted");
	}



	public static int directoryChoice(String word) {
		System.out.println("Press 1 if want to "+word+" the files in current directory");
		System.out.println("Press 2 if you want to "+word+" view files in custom directory");

		return inputNumber(2);

	}

	static void displayFiles(File dir) {
		File[] filesInDirectory = dir.listFiles();
		System.out.println("\n\n**********************************************************************************************************************************");
		System.out.printf("%-4s %-70s %-40s %-50s","Sno.", "File name", "File Type", "File Size");
		System.out.println("\n\n**********************************************************************************************************************************");

		double size=0;
		int count=0;
		String unit="bytes";

		String fileType="file";
		for (File f : filesInDirectory) {

			//			

			if (f.isDirectory()) { //if else block for fetching directory size
				size=getDirectorySize(f);
				fileType="Directory";

			}

			else {
				size=(double)f.length();
				String type = f.getName();
				String[] filesType=type.split("[.]");
				fileType= "."+filesType[filesType.length-1];

			}		//if else block for fetching directory size


			if (size<1024) {
				size = (int) size/1;	
				unit="bytes";
			}


			else if (size>1024 && size<1024*1024) {
				unit="KB";
				size=size/1024;
				size = Math.round(size*10.0)/10.0;	

			}

			else if (size>1024*1024 && size<1024*1024*1024) {
				unit="MB";
				size=size/1024/1024;
				size = Math.round(size*100.0)/100.0;
			}

			else if (size>1024*1024*1024) {
				unit="GB";
				size=size/1024/1024/1024;
				size = Math.round(size*100.0)/100.0;

			}
			System.out.printf("%-4s %-70s %-40s %-50s\n",++count, " "+f.getName(),fileType,size+" "+unit );
		}//end of for each loop
	} //End of display files method


	static void displayFiles(File[] dir) {
		//		File[] filesInDirectory = dir.listFiles();
		System.out.println("\n\n**********************************************************************************************************************************");
		System.out.printf("%-4s %-70s %-40s %-50s","Sno.", "File name", "File Type", "File Size");
		System.out.println("\n\n**********************************************************************************************************************************");

		double size=0;
		int count=0;
		String unit="bytes";

		String fileType="file";
		for (File f : dir) {

			//			

			if (f.isDirectory()) { //if else block for fetching directory size
				size=getDirectorySize(f);
				fileType="Directory";

			}

			else {
				size=(double)f.length();
				String type = f.getName();
				String[] filesType=type.split("[.]");
				fileType= "."+filesType[filesType.length-1];

			}		//if else block for fetching directory size


			if (size<1024) {
				size = (int) size/1;	
				unit="bytes";
			}


			else if (size>1024 && size<1024*1024) {
				unit="KB";
				size=size/1024;
				size = Math.round(size*10.0)/10.0;	

			}

			else if (size>1024*1024 && size<1024*1024*1024) {
				unit="MB";
				size=size/1024/1024;
				size = Math.round(size*100.0)/100.0;
			}

			else if (size>1024*1024*1024) {
				unit="GB";
				size=size/1024/1024/1024;
				size = Math.round(size*100.0)/100.0;

			}
			System.out.printf("%-4s %-70s %-40s %-50s\n",++count, " "+f.getName(),fileType,size+" "+unit );
		}//end of for each loop
	} //End of display files method



	static void displayFiles()  {

		int ch=directoryChoice("view");

		currentDirectory= WorkingDirectory(ch);
		currentDirectoryPath=currentDirectory.getAbsolutePath();

		//		System.out.println("Absolute path is " +wd.getAbsolutePath());
		File[] filesInDirectory = currentDirectory.listFiles();

		System.out.println("\n\n**********************************************************************************************************************************");
		System.out.printf("%-4s %-70s %-40s %-50s","Sno.", "File name", "File Type", "File Size");
		System.out.println("\n\n**********************************************************************************************************************************");

		double size=0;
		int count=0;
		String unit="bytes";

		String fileType="file";
		for (File f : filesInDirectory) {

			//			

			if (f.isDirectory()) { //if else block for fetching directory size
				size=getDirectorySize(f);
				fileType="Directory";

			}

			else {
				size=(double)f.length();
				String type = f.getName();
				String[] filesType=type.split("[.]");
				fileType= "."+filesType[filesType.length-1];

			}		//if else block for fetching directory size


			if (size<1024) {
				size = (int) size/1;	
				unit="bytes";
			}


			else if (size>1024 && size<1024*1024) {
				unit="KB";
				size=size/1024;
				size = Math.round(size*10.0)/10.0;	

			}

			else if (size>1024*1024 && size<1024*1024*1024) {
				unit="MB";
				size=size/1024/1024;
				size = Math.round(size*100.0)/100.0;
			}

			else if (size>1024*1024*1024) {
				unit="GB";
				size=size/1024/1024/1024;
				size = Math.round(size*100.0)/100.0;

			}
			System.out.printf("%-4s %-70s %-40s %-50s\n",++count, " "+f.getName(),fileType,size+" "+unit );
		}//end of for each loop
	} //End of display files method

	public static double getDirectorySize(File dir) { // recursive function to get the size of directory

		double sz = 0;
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile())
					sz += file.length();
				else
					sz += getDirectorySize(file);
			}
		}
		return sz;

	}

	public static int inputNumber(int upperBound) { // input number method ok!!
		Scanner sc = new Scanner(System.in);
		int choice=0;
		while (choice<=0||choice>upperBound) {
			try {
				System.out.println(" ** NOTE**- Please enter a number between 1 and "+upperBound+" or press 9 to quit");
				choice=sc.nextInt();
				if (choice==9) {
					System.out.println("Shutting down application");
					System.exit(0);
				}

			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("Invalid choice!! Please try again with a number only");
				sc.next();
				choice=0;

				if (menuType==1) {
					showMainMenu();
				}

				else if(menuType==2){
					showBusinessMenu();
				}

			}	
		}

		return choice;
	} // input number close

	public static File searchFile(File dir ,String fileName) {
		File[] files = dir.listFiles();
		boolean isCheckingSubdirectories=false;
		File updatedFolder=dir;
		boolean found=false;
		if (files!=null) {
			for (File file : files) {
				if (file.isFile()) {
					if (file.getName().equals(fileName)) {
						System.out.println("File found sucessfully");
						found=true;	
						System.out.println(file.getName());
						return updatedFolder;
					}

					else {
						isCheckingSubdirectories=true;
						updatedFolder=file;
						searchFile(file, fileName);		
					}

					if(found == false) {

						System.out.println("\n Sorry!! File not found in the directory and other .");
						return file;
					}
				}
			}


		}
		return updatedFolder;
	}

	static void showBusinessMenu() {
		System.out.println("\n\t\t\t BUSINESS OPERATIONS MENU");

		System.out.println("Please select the following options and press enter key for your choice");
		System.out.println("1. Add a file in the current directory ");
		System.out.println("2. Delete a file from the current directory");
		System.out.println("3. Search for a specific file");
		System.out.println("4. Return to the main menu");
	}

	static void showMainMenu() {
		System.out.println("\n\t\t\tMAIN MENU");
		System.out.println("Please select the following options and press ente r key for your choice");
		System.out.println("1. Display the files present in a directory");
		System.out.println("2. Sort files in curret directory");
		System.out.println("3. More options");
		System.out.println("4. Exit the program");

	}

	static File[] sortFiles(File dir) {
		File[] filesInDirectory = dir.listFiles();
		System.out.println("File are being sorted");

		Arrays.sort(filesInDirectory, new FileSorter());

		return filesInDirectory;
	}

	public static File WorkingDirectory(int choice) {

		Scanner scanner=new Scanner(System.in);
		String directoryPath = currentDirectoryPath;
		File directory = null;

		if (choice==1) {
			directory = new File(directoryPath);
		}

		else if (choice==2) {			

			try {
				System.out.println("Please enter an existing directory path");
				directory = new File(scanner.nextLine());
				System.out.println("The directory you entered is "+directory);
			} 
			catch (NullPointerException e) {
				// TODO: handle exception

				return currentDirectory;
				//				System.out.println("The path you entered is invalid. Please check");
			}
			catch (Exception e) {
				// TODO: handle exception
				return currentDirectory;
				//				directory=currentDirectory;

				//				e.printStackTrace();
			}
		}

		else {
			System.out.println("Wrong choice. Please try again");
		}

		return directory;


	}

	public static String inputFile() {
		Scanner scanner = new Scanner(System.in);
		String name=null; 

		try {
			name = scanner.nextLine();
		} catch (Exception e) {
			// TODO: handle exception
			name = "NoName";
		}
		return name;
	}

}//Class Main closed

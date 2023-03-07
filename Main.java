// Omar Alshafei
// Human Resource Managment System

import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class Project2 {
	
	private static int readMenu() {
		Scanner sc = new Scanner(System.in);
		char input;
		
		System.out.println("1-  Enter the information a faculty");
		System.out.println("2-  Enter the information of a student");
		System.out.println("3-  Print tuition invoice for a student");
		System.out.println("4-  Print faculty information");
		System.out.println("5-  Enter the information of a staff member");
		System.out.println("6-  Print the information of a staff member");
		System.out.println("7-  Exit Program");
		System.out.print("\tEnter your selection: ");
		input = sc.next().charAt(0);
		Character.toLowerCase(input);
		
		while(input < 49 || input > 55) {
			System.out.println("\nInvalid entry- please try again\n"); 
			
			System.out.println("");
			System.out.println("1-  Enter the information a faculty");
			System.out.println("2-  Enter the information of a student");
			System.out.println("3-  Print tuition invoice for a student");
			System.out.println("4-  Print faculty information");
			System.out.println("5-  Enter the information of a staff member");
			System.out.println("6-  Print the information of a staff member");
			System.out.println("7-  Exit Program");
			System.out.print("\tEnter your selection: ");
			input = sc.next().charAt(0);
			Character.toLowerCase(input);
		}
		
		int returnVal = Character.getNumericValue(input);
		return returnVal;
	}
	
	private static String readDepartment() {
		String str, strCopy;
		Scanner sc = new Scanner(System.in);
		str = sc.nextLine();
		strCopy = str;
		str = str.toLowerCase();
		
		while(!str.equals("mathematics") && !str.equals("engineering") && !str.equals("sciences")) {
			System.out.println("\t\t\"" + strCopy + "\" is invalid");
			System.out.print("\tDepartment: ");
			str = sc.nextLine();
			strCopy = str;
			str = str.toLowerCase();
		}
		
		if(str.equals("mathematics"))
			return "Mathematics"; 
		
		if(str.equals("engineering"))
			return "Engineering"; 
		
		if(str.equals("sciences"))
			return "Sciences"; 
		
		return str;
	}
	
	private static String readRank() {
		String str, strCopy;
		Scanner sc = new Scanner(System.in);
		str = sc.nextLine();
		strCopy = str;
		str = str.toLowerCase();
		
		while(!str.equals("professor") && !str.equals("adjunct")) {
			System.out.println("\t\t\"" + strCopy + "\" is invalid");
			System.out.print("\tRank: ");
			str = sc.nextLine();
			strCopy = str;
			str = str.toLowerCase();
		}
		
		if(str.equals("professor"))
			return "Professor"; 
		
		if(str.equals("adjunct"))
			return "Adjunct"; 
		
		return str;
	}
	
	private static String readStatus() {
		String str, strCopy;
		Scanner sc = new Scanner(System.in);
		str = sc.nextLine();
		strCopy = str;
		str = str.toLowerCase();
		
		while(!str.equals("f") && !str.equals("p")) {
			System.out.println("\t\"" + strCopy + "\" is invalid");
			System.out.print("Status, Enter P for Part Time, or Enter F for Full Time: ");
			str = sc.nextLine();
			strCopy = str;
			str = str.toLowerCase();
		}
		
		if(str.equals("f"))
			return "Full Time"; 
		
		if(str.equals("p"))
			return "Part Time"; 
		
		return str;
	}
	
	// return false of ID format have NO error, else return true
	private static boolean IDFormat(String newID){
			
		if(newID.length() != 6)
			return true;
			
		for(int i = 0; i < 2; i++) {
			char c = newID.charAt(i);
				
			if(!Character.isLetter(c))
				return true;
		}
			
		for(int i = 2; i < 6; i++) {
			char c = newID.charAt(i);
				
			if(!Character.isDigit(c))
				return true;
		}
			
		return false;
	}
	
	private static String checkID(String id) {
		Scanner sc = new Scanner(System.in);
		
		while(IDFormat(id)) {
			try {
				throw new IdException();
			}
			
			catch(IdException ie) {
				System.out.println(ie.getMessage());
				System.out.println("");
				System.out.print("\tID: ");
				id = sc.nextLine();
			}
		}
		
		return id;
	}
	
	private static Student readStudent() {
		Scanner sc = new Scanner(System.in);
		String name, id, str; 
		double gpa; 
		int credit; 
		
		System.out.println("Enter the student info: ");
		System.out.print("\tName of Student: ");
		name = sc.nextLine();
		System.out.print("\tID: ");
		id = sc.nextLine();
		id = checkID(id);
		
		try{
			System.out.print("\tGpa: ");
			str = sc.nextLine();
			gpa = Double.parseDouble(str);
		}
		catch(Exception e) {
			System.out.println("\tUsing defaul GPA, 0.0");
			gpa = 0.0;
		}
		
		try{
			System.out.print("\tCredit hours: ");
			str = sc.nextLine();
			credit = Integer.parseInt(str);
		}
		catch(Exception e) {
			System.out.println("\tUsing defaul Credit hours, 0");
			credit = 0;
		}
		

		Student s = new Student(name, id, gpa, credit);
		
		return s; 
	}
	
	private static Faculty readFaculty() {
		Scanner sc = new Scanner(System.in);
		String name, id, rank, department; 
		
		System.out.println("Enter the faculty info: ");
		System.out.print("\tName of the faculty: ");
		name = sc.nextLine();
		System.out.print("\tID: ");
		id = sc.nextLine();
		id = checkID(id);
		System.out.print("\tRank: ");
		rank = readRank();
		System.out.print("\tDepartment: ");
		department = readDepartment();

		Faculty f = new Faculty(name, id, department, rank);
		
		return f; 
	}
	
	private static Staff readStaff() {
		Scanner sc = new Scanner(System.in);
		String name, id, status, department; 
		
		System.out.print("Name of the staff member: ");
		name = sc.nextLine();
		System.out.print("Enter the id: ");
		id = sc.nextLine();
		id = checkID(id);
		System.out.print("Department: ");
		department = readDepartment();
		System.out.print("Status, Enter P for Part Time, or Enter F for Full Time: ");
		status = readStatus();

		Staff sf = new Staff(name, id, department, status);
		
		return sf; 
	}
	
	// return true for y and false for n
	private static boolean readStatusReport() {
		Scanner sc = new Scanner(System.in);
		String str; 
		
		System.out.print("Would you like to create the report? (Y/N): ");
		str = sc.nextLine();
		str = str.toLowerCase();
		
		while(!str.equals("y") && !str.equals("n")){
			System.out.print("Invalid input! Would you like to create the report? (Y/N): ");
			str = sc.nextLine();
			str = str.toLowerCase();
		}
		
		if(str.equals("y"))
			return true;
		return false;
	}
	
	private static int readReportType() {
		Scanner sc = new Scanner(System.in);
		String str; 
		
		System.out.print("Would you like to sort your students by (1) gpa or (2) credit hours: ");
		str = sc.nextLine();
		str = str.toLowerCase();
		
		while(!str.equals("1") && !str.equals("2")){
			System.out.print("Invalid input! Would you like to sort your students by (1) gpa or (2) credit hours: ");
			str = sc.nextLine();
			str = str.toLowerCase();
		}
		
		if(str.equals("1"))
			return 1;
		return 2;
	}
	

	public static void main(String[] args) {
		
		String id; 
		int input;
		
		Personnel personnel = new Personnel();
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("\t\t\tWelcome to my Personnel Management Program\n");
		System.out.println("Choose one of the options:");
		
		while(true){
			input = readMenu();
			
			if(input == 1) {
				Faculty temp = readFaculty();
				
				while(personnel.repeatID(temp)) {
					try {
						throw new IdException();
					}
					catch(IdException ie) {
						System.out.println("\nRepeated ID, enter infomation again\n");
						temp = readFaculty();
					}
					
				}
				
				personnel.addFaculty(temp);
				System.out.println("Faculty added!\n");
			}
			
			else if(input == 2) {
				Student temp = readStudent();
				
				while(personnel.repeatID(temp)) {
					try {
						throw new IdException();
					}
					catch (IdException ie) {
						System.out.println("\nRepeated ID, enter infomation again\n");
						temp = readStudent();
					}
					
				}
				
				personnel.addStudent(temp);
				System.out.println("Student added!\n");
			}
			
			else if(input == 3) {
				System.out.print("Enter the student’s is: ");
				id = sc.nextLine();
				personnel.printInfo(id, input);
				
			}
			
			else if(input == 4) {
				System.out.print("Enter the Faculty’s id: ");
				id = sc.nextLine();
				personnel.printInfo(id, input);
			}
			
			else if(input == 5) {
				Staff temp = readStaff();
				
				while(personnel.repeatID(temp)) {
					try {
						throw new IdException();
					}
					catch (IdException ie) {
						System.out.println("\nRepeated ID, enter infomation again\n");
						temp = readStaff();
					}
					
				}
				
				personnel.addStaff(temp);
				System.out.println("Staff member added!\n");
			}
			
			else if(input == 6) {
				System.out.print("Enter the Staff’s id: ");
				id = sc.nextLine();
				personnel.printInfo(id, input);
			}
			
			else if(input == 7) {
				boolean report = readStatusReport();
				
				if(!report) 
					break;
				
				int reportType = readReportType();
				
				personnel.printReport(reportType);
				
				break;
			}
			
		}
		
		System.out.println("\n\nGoodbye! ");
		

	}

}



abstract class Person{
	private String fullName, ID; 
	
	public Person() {
		fullName = "No Name"; 
		ID = "No ID";
	}
	
	public Person(String fullName, String ID) {
		this.fullName = fullName; 
		this.ID = ID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	@Override
	public String toString() {
		return fullName + ", " + ID; 
	}
	
	public abstract void print();
	
}



class Student extends Person{
	private double GPA;
	private int numCredit;
	private final double COST_PER_CREADIT = 236.45;
	private final int FEE = 52;
	
	public Student() {
		super();
		GPA = -1.0; 
		numCredit = -1; 
	}
	
	public Student(String fullName, String ID, double GPA, int numCredit) {
		super(fullName, ID);
		this.GPA = GPA; 
		this.numCredit = numCredit; 
	}
	
	public double getGPA() {
		return GPA;
	}
	
	public void setGPA(double gPA) {
		GPA = gPA;
	}
	
	public int getNumCredit() {
		return numCredit;
	}
	
	public void setNumCredit(int numCredit) {
		this.numCredit = numCredit;
	} 
	
	public double cost() {
		double cost = (numCredit * COST_PER_CREADIT) + FEE;
		
		if(GPA >= 3.85) {
			cost *= 0.75;
		}
		
		return cost;
		
	}
	
	@Override
	public String toString() {
		return super.toString() + ", " + GPA + ", " + numCredit; 
	}
	
	@Override
	public void print() {
		System.out.println("\nHere is the tuition invoice for " + getFullName() + " : \n");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println(getFullName() + "\t\t" + getID());
		System.out.println("Credit Hours:" + numCredit + " ($236.45/credit hour)");
		System.out.println("Fees: $52\n");
		
		double cost = cost();
		double discount = ((numCredit * COST_PER_CREADIT) + FEE) - cost;
		System.out.print("Total payment: $" + String.format("%.2f", cost) + "\t\t");
		System.out.println("($" + String.format("%.2f", discount) + " discount applied)");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("");
	}
}



abstract class Employee extends Person{
	private String department; 

	public Employee() {
		super();
		department = "No Department";
	}

	public Employee(String fullName, String ID, String department) {
		super(fullName, ID);
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	} 
	
	@Override
	public String toString(){
		return super.toString() + ", " + department; 
	}
	
	@Override
	public void print() {
		System.out.println("");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println(getFullName() + "\t\t" + getID());
		System.out.print(getDepartment() + " Department, " );
	}
	
}



class Faculty extends Employee{
	private String rank;

	public Faculty() {
		super();
		rank = "No Rank";
	}

	public Faculty(String fullName, String ID, String department, String rank) {
		super(fullName, ID, department);
		this.rank = rank;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	} 
	
	@Override
	public String toString(){
		return super.toString() + ", " + rank; 
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println(rank);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("");
	}
	
}



class Staff extends Employee{
	private String status;

	public Staff() {
		super();
		status = "No Status";
	}

	public Staff(String fullName, String ID, String department, String status) {
		super(fullName, ID, department);
		this.status = status; 
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", " + status; 
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println(status);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("");
	}
	
}



class Personnel {
	private Person[] list; 
	private static int count = 0; 
	
	public Personnel() {
		list = new Person[100];
		for(int i = 0; i < 100; i++)
			list[i] = null;
	}
	
	public Personnel(Person[] someList) {
		list = someList;
	}

	public Person[] getList() {
		return list;
	}

	public void setList(Person[] list) {
		this.list = list;
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		Personnel.count = count;
	}
	
	public void addStudent(Student s) {
		list[count] = s; 
		count++;
	}
	
	public void addFaculty(Faculty f) {
		list[count] = f; 
		count++;
	}
	
	public void addStaff(Staff sf) {
		list[count] = sf; 
		count++;
	}
	
	public void printInfo(String id, int flag) {
		int index = -1;
		for(int i=0; i < count; i++) {
			if(list[i].getID().equals(id)) {
				index = i;
				break;
			}
		}
		
		if(index == -1) {
			if(flag == 3)
				System.out.println("No student matched! \n");
			
			if(flag == 4)
				System.out.println("No faculty matched! \n");
			
			if(flag == 6)
				System.out.println("No Staff member matched! \n");
		}
		
		else 
			list[index].print();
	}
	
	public boolean repeatID(Person p) {
		String str = p.getID();
		int i = 0;
		while(list[i] != null) {
			if(str.equals(list[i].getID()))
				return true;
			
			i++;
		}
		
		return false;
	}
	
	
	public void printReport(int type) {
		PrintWriter writer = null;
		
		try {
		writer = new PrintWriter("report.txt");
		}
		catch(Exception e) {
			System.out.println("Cannot create a report file");
			System.out.println("Sorry!");
		}
		
		// for current date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		
		writer.print("\t\tReport created on ");
		writer.println(dtf.format(now));
		writer.print("\t\t***********************\n\n\n");
		
		
		ArrayList<Student> studentList = new ArrayList<>();
		int printCounter = 1;
		
		writer.println("Faculty Members");
		writer.println("-------------------------");
		for(int i = 0; i < count; i++) {
			
			if(list[i] instanceof Faculty) {
				writer.println("\t" + printCounter + ". " + list[i].getFullName());
				writer.println("\t" + "ID: " + list[i].getID());
				writer.println("\t" + ((Faculty)list[i]).getRank() + "," + ((Faculty)list[i]).getDepartment() );
				writer.println("");
				printCounter++;
			}
			
			if(list[i] instanceof Student)
				studentList.add((Student)list[i]);
		}
		
		printCounter = 1;
		writer.println("\n");
		
		writer.println("Staff Members");
		writer.println("-------------------------");
		for(int i = 0; i < count; i++) {
			
			if(list[i] instanceof Staff) {
				writer.println("\t" + printCounter + ". " + list[i].getFullName());
				writer.println("\t" + "ID: " + list[i].getID());
				writer.println("\t" + ((Staff)list[i]).getDepartment() + ((Staff)list[i]).getStatus() );
				writer.println("");
				printCounter++;
			}
			
		}
		
		writer.println("\n\n");
		
		if(type == 1) {
			writer.println("Students (Sorted by gpa) ");
			Collections.sort(studentList, new SortByGPA());
		}
		else {
			writer.println("Students (Sorted by credit hours) ");
			Collections.sort(studentList, new SortByCreditHours());
		}
		
		writer.println("-----------");
		printCounter = 1;
		
		for(Student s : studentList) {
			writer.println("\t" + printCounter + ". " + s.getFullName() );
			writer.println("\t" + "ID: " + s.getID() );
			writer.println("\t" + "Gpa: " + s.getGPA() );
			writer.println("\t" + "Credit hours: " + s.getNumCredit() );
			writer.println("");
			printCounter++;
		}
		
		writer.close();
		System.out.println("\nReport created and saved on your hard drive! ");
	}
	
}

class IdException extends Exception{
	private String id; 
	
	public IdException() {}
	
	public IdException(String newID) {
		id = newID;
	}
	
	@Override
	public String getMessage() {
		return "Invalid ID format. Must be LetterLetterDigitDigitDigitDigit";
	}
}


class SortByGPA implements Comparator<Student> {

    public int compare(Student a, Student b)
    {
        if( a.equals(b)) return 0;
        if( a.getGPA() > b.getGPA()) return 1;
        return -1;
    }
}


class SortByCreditHours implements Comparator<Student> {

    public int compare(Student a, Student b)
    {
        if( a.equals(b)) return 0;
        if( a.getNumCredit() > b.getNumCredit()) return 1;
        return -1;
    }
}

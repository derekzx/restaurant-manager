package Restaurant;
import java.io.IOException;
import java.util.ArrayList;


public class RestaurantLayoutDaily {
	//private ArrayList<Table> freeTables;
	private ArrayList<Table> freeTablesAM;
	private ArrayList<Table> freeTablesPM;

	private TableType initialiseTables;
	private ArrayList<Table> noOfBookedTablesAM; //Add into class diagram
	private ArrayList<Table> noOfBookedTablesPM; //Add into class diagram

	private ArrayList<Booking> allDayBookings; //Add into class diagram
	private int Date; //Add this to class diagram!
	
	public RestaurantLayoutDaily(int date) throws IOException {
		Date=date;
		freeTablesAM=new ArrayList<Table>();
		freeTablesPM=new ArrayList<Table>();
		//freeTables=new ArrayList<Table>();

		initialiseTables=new TableType();

		//TableType initialiseTables=new TableType();
		//freeTables=initialiseTables.getTables();
		noOfBookedTablesAM=new ArrayList<Table>();
		noOfBookedTablesPM=new ArrayList<Table>();

		allDayBookings=new ArrayList<Booking>();
		
	}
	
	public int getNoOfTables(int seats) {  
		return initialiseTables.getTables(seats);
	}


	
	protected int getFreeTables(boolean time, int seats) {
		int allFreeTables=getNoOfTables(seats); //Total - booked

		if (time==true) {
			 //for (int i=0; i<noOfBookedTables.size(); i++) {
				//if (noOfBookedTables.get(i).getBooked() == true) //Change this part!!! It won't work coz all true 
				 //freeTables.add(noOfBookedTables.get(i));
			//} 
			
			for(int i=0; i<noOfBookedTablesAM.size(); i++) {
				if(noOfBookedTablesAM.get(i).getSeats() == seats)
					allFreeTables--;  
			}
		}
		else {
			
			for(int i=0; i<noOfBookedTablesPM.size(); i++) {
				if(noOfBookedTablesPM.get(i).getSeats() == seats)
					allFreeTables--;  
			}
		}
		return allFreeTables;
	}
	
	protected int getDate() {
		return Date;
	}
	
	public void addReservation(boolean time, int type) throws IOException { //What is this throw IOException
		//Don't have to check for table availability as already done in the checkTable method
		Table newTable = new Table();  //Change class diagram arrow to black			
			newTable.setBooked(true);
			newTable.setTableId(1234567); //Will change this later;
			newTable.setSeats(type);
			newTable.setTime(time);  //Add in a time method
		
		if (time=true)
			noOfBookedTablesAM.add(newTable);
		else 
			noOfBookedTablesPM.add(newTable);
	}
	
	public void removeReservation(boolean time, int type) { 
		if (time=true) {
			for(int i=0; i<noOfBookedTablesAM.size(); i++) {
				if (noOfBookedTablesAM.get(i).getSeats()==type)
					noOfBookedTablesAM.remove(i);
				else
					System.out.println("Error");	
			}
		}
		else 
			for(int i=0; i<noOfBookedTablesPM.size(); i++) {
				if (noOfBookedTablesPM.get(i).getSeats()==type)
					noOfBookedTablesPM.remove(i);
				else
					System.out.println("Error");	
			}
			
		
	}
	
	public void passBookingtoAdd(Booking reservation) {
		allDayBookings.add(reservation);
	}
	
	public void passBookingtoRemove(Booking reservation) {
		for(int i=0; i<allDayBookings.size(); i++) {
			if(allDayBookings.get(i).getBookingID()==reservation.getBookingID())
				allDayBookings.remove(i);
			else
				System.out.println("Invalid booking");
		}
	}
	
	public ArrayList<Booking> returnDayBookings() {
		return allDayBookings;
	}
	
	public ArrayList<Booking> returnBooking(boolean time) {
		ArrayList<Booking> tablesAtTime=new ArrayList<Booking>();
		for(int i=0; i<allDayBookings.size(); i++) {
			if(allDayBookings.get(i).getTime()==time) 
				tablesAtTime.add(allDayBookings.get(i));
		}
		return tablesAtTime;
	}
	
	public Booking returnSpecificBooking(boolean time, int phone) {
		Booking tempbooking=new Booking();
		ArrayList<Booking> temp=returnBooking(time);
		for(int i=0; i<temp.size(); i++) {
			if(temp.get(i).getPhone()==phone)
				tempbooking = temp.get(i);
		} return tempbooking;
	}
	

}

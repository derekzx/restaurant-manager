package Restaurant;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReservationCal {
	private ArrayList<RestaurantLayoutDaily> dailyLayout;
	private int monthofyear;  //YYYYMM
	
	public void setMonth(int month) {
		int year = Booking.getCurrentDate()/100;
		monthofyear=(year*100)+month;
	}
	
	public int getMonth() {
		return monthofyear;
	}
	
	public ReservationCal() throws IOException {   //Write a method for getBooking (returns a Booking object)
		//if no layout on that date, create a new layout. If it exists, add relevant info inside
		//Create new system clock to create a new day in ReservationCal (not so feasible)
		dailyLayout = new ArrayList<RestaurantLayoutDaily>();
		for(int i=0; i<31; i++) {
			RestaurantLayoutDaily daily= new RestaurantLayoutDaily((monthofyear*100)+(i+1)); //modifieddate+1 appends the day to YYYYMM
		}
	}	 
	
	public void addReservation(Booking reservation) throws IOException {  //throws IOException????
		for (int i=0; i<dailyLayout.size(); i++) {
			if (dailyLayout.get(i).getDate()==reservation.getDate()) {
				if(checkTableAvailability(reservation.getDate(), reservation.getTime(), reservation.getType())==true) { //Add in the type of table to Booking class!!!
					//Write method for check table availability first
					//Can simplify checkTableAvailability method to remove date from arguments passed
					dailyLayout.get(i).addReservation(reservation.getTime(), reservation.getType());
					//Write method for addReservation
					System.out.println("Reservation has been succesfully added!");
					dailyLayout.get(i).passBookingtoAdd(reservation);
				}
				else 
					System.out.println("Sorry, there are no available tables");
			}
		}
		
	}
	
	
	public boolean checkTableAvailability(int date, boolean time, int seats) {
		boolean availability = false;
		for (int i=0; i<dailyLayout.size(); i++) {
			if (dailyLayout.get(i).getDate()==date) {
				if(dailyLayout.get(i).getFreeTables(time, seats) != 0)
					availability = true;
				else
					availability = false;
			}
		} return availability;
	}
	
	
	public void removeReservation(Booking reservation) {
		for (int i=0; i<dailyLayout.size(); i++) {
			if (dailyLayout.get(i).getDate()==reservation.getDate()) {
				if(checkTableAvailability(reservation.getDate(), reservation.getTime(), reservation.getType())==false) { //Add in the type of table to Booking class!!!
					for(int j=0; j<dailyLayout.get(i).returnDayBookings().size(); j++) {
						if(dailyLayout.get(i).returnDayBookings().get(j).getPhone()==reservation.getPhone()) {
							dailyLayout.get(i).removeReservation(reservation.getTime(), reservation.getType());
							System.out.println("Reservation has been succesfully removed!");
							dailyLayout.get(i).passBookingtoRemove(reservation);
						}
						else 
							System.out.println("Sorry, the phone number does not match");
					}
				}
				else
					System.out.println("Sorry, there is no reservation on that date and time");
			}
		}
	}
	
	public void checkFreeTables(long date, int seats, boolean time) { //check for the free tables on a date
		for (int i=0; i<dailyLayout.size(); i++) {
			if (dailyLayout.get(i).getDate()==date) {
				int availableTables=dailyLayout.get(i).getFreeTables(time, seats);
				System.out.println("There are " +availableTables+ " tables available to reserve");
			}
			else
				System.out.println("Incorrect date");
		}
	}
	
	public Booking checkSpecificReservation(int date, boolean time, int phone) { //Change name in Class Diagram
		Booking temp=new Booking();
		for (int i=0; i<dailyLayout.size(); i++) {
			if (dailyLayout.get(i).getDate()==date) {
				temp= dailyLayout.get(i).returnSpecificBooking(time, phone);
			}
		}return temp;
	}
	
	public ArrayList<Booking> checkUpcomingReservation(int date, boolean time) {  //Add this method into class
		ArrayList<Booking> temp=new ArrayList<Booking>();
		for (int i=0; i<dailyLayout.size(); i++) {
			if (dailyLayout.get(i).getDate()==date) {
				temp= dailyLayout.get(i).returnBooking(time);
			}
		}return temp;
	
	}
}

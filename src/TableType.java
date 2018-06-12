package Restaurant;
import java.io.IOException;
import java.util.ArrayList;

/*public class TableType {
	private ArrayList typeOfSeats;
	//Allows users to add the type of tables that they want
	
	public TableType() throws IOException {
		Table[] tenseater=new Table[5]; //Change this to allow restaurant staff to edit the type and number of tables
		for (int i=0; i<5; i++) {
			tenseater[i]= new Table();
			tenseater[i].setBooked(false);
		}
		
		int[] eightseater=new int[5];
		int[] fourseater=new int[10];
		int[] twoseater=new int[10];
		
		typeOfSeats=new ArrayList();
		typeOfSeats.add(tenseater);
		typeOfSeats.add(eightseater);
		typeOfSeats.add(fourseater);
		typeOfSeats.add(twoseater);
		
	}
	
	public ArrayList getTables() {
		return typeOfSeats;
	}
	
} */


public class TableType {
	private int tenseater, eightseater, fourseater, twoseater;
	
	public TableType() {
		tenseater=5;
		eightseater=5;
		fourseater=10;
		twoseater=10;
	}
	
	public int getTables(int seats) {
		int noOfTables=0;
		switch(seats) {
		case 10:
			noOfTables=5;
			break;
		case 8: 
			noOfTables=5;
			break;
		case 4:
			noOfTables=10;
			break;
		case 2: 
			noOfTables=10;
			break;
		} return noOfTables;
	}
	
	
	
	
	
}

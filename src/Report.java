import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Report {

	private static ArrayList<MenuItem> histMenuItems;
	private static ArrayList<PromoItem> histPromoItems;
	private static int[] lastpos;
	private double totalRevenue;
	private String salesBreakdown; //detailed description of how much revenue each item on the menu got
	
	private static ArrayList<Order2> todayOrderList; //list of the orders for a particular day
	private static ArrayList<ArrayList<Order2>> orderHistory; //list of all the todayOrderList
															// ie. database of every order in every day
	
	public Report() throws IOException{
		histMenuItems = new ArrayList<MenuItem>();
		histPromoItems = new ArrayList<PromoItem>();
		loadMenuItem();
		
		todayOrderList = new ArrayList<Order2>(); //ArrayList of orders for a particular day MAKE WHEN DAY STARTS
		
		for (int i = 0; i < histMenuItems.size(); i++){
			histMenuItems.get(i).setSales(0);
		}
		
		for (int i = 0; i < histPromoItems.size(); i++){
			histPromoItems.get(i).setSales(0);
		}
	}
	
	
	public void calculateTotalSales(int periodStart, int periodEnd){
		for(int k = 0; k < periodStart - periodEnd + 1; k++){ //for each todayOrderList (each day)
			 int n = orderHistory.size() - k - 2;
			 for(int j = 0; j < orderHistory.get(n).size(); j++){ // for each Order in todayOrderList
				 for (int i = 0; i < orderHistory.get(n).get(j).getMenuArray().size(); i++){ //for each MenuItem in Order
					 double menuItemOrderSales = orderHistory.get(n).get(j).getMenuArray().get(i).getQuantityPrice();
					 orderHistory.get(n).get(j).getMenuArray().get(i).setSales(
							 orderHistory.get(n).get(j).getMenuArray().get(i).getSales() + menuItemOrderSales);
				 }
				 
				 for (int i = 0; i < orderHistory.get(n).get(j).getPromoArray().size(); i++){ //for each PromoItem in Order
					 double promoItemOrderSales = orderHistory.get(n).get(j).getPromoArray().get(i).getQuantityPrice();
					 orderHistory.get(n).get(j).getPromoArray().get(i).setSales(
							 orderHistory.get(n).get(j).getPromoArray().get(i).getSales() + promoItemOrderSales);
				 }
			 }
		 }
	 }
	
	public double getTotalRevenue(int periodStart, int periodEnd){
		 double totalRevenue = 0.0;
		 calculateTotalSales(periodStart, periodEnd);
		 for(int i = 0; i < histMenuItems.size(); i++){
			 totalRevenue += histMenuItems.get(i).getSales();
		 }
		 for(int i = 0; i < histPromoItems.size(); i++){
			 totalRevenue += histPromoItems.get(i).getSales();
		 }
		 return totalRevenue;
	 }
	
	public static void updateOrderHistory(Order2 order){
		//this method takes in a new order and adds it to the database orderHistory
		orderHistory.get(orderHistory.size() - 1).add(order);
	}
	
	public String getSalesBreakdown(int periodStart, int periodEnd){
		 //report will detail the period, individual sale items (either menu items or promotional items) and total revenue.
	
		 salesBreakdown = "Time period of report: From " + periodStart + "days ago to " + periodEnd + " days ago.";
 
		 for(int i = 0; i < histMenuItems.size(); i++){
			 if(histMenuItems.get(i).getSales() != 0){
				 salesBreakdown += ("\nItem name: " + histMenuItems.get(i).getName() + "\nUnit price: " + histMenuItems.get(i).getPrice() + "\nTotal sales: " + histMenuItems.get(i).getSales());
			 }
		 }
		 
		 for(int i = 0; i < histPromoItems.size(); i++){
			 if(histPromoItems.get(i).getSales() != 0){
				 salesBreakdown += ("\nPromo name: " + histPromoItems.get(i).getName() + "\nUnit price: " + histPromoItems.get(i).getPrice() + "\nTotal sales: " + histPromoItems.get(i).getSales());
			 }
		 }
		 salesBreakdown += ("\n\nTotal revenue of all products during time period: " + getTotalRevenue(periodStart, periodEnd));
		 
		 return salesBreakdown;
	 }
	
	public void viewMenu() {
		System.out.println("=====List of Menu Items=====");
		//initialise i to search through arraylist
		int i = 0;
		System.out.println("\n=====Main Courses=====");
		
		//Prints out the main courses in the list
		while (i <= lastpos[0]) {
			MenuItem temp = histMenuItems.get(i);
			System.out.println(i+1 + " : " + temp.getName() + " : $" + temp.getPrice());
			System.out.println(temp.getDescription());
			i++;
		}
		
		//Prints out the drinks in the list
		System.out.println("\n=====Drinks=====");
		while (i <= lastpos[1]) {
			MenuItem temp = histMenuItems.get(i);
			System.out.println(i+1 + " : " + temp.getName() + " : $" + temp.getPrice());
			System.out.println(temp.getDescription());
			i++;
		}
		
		//Prints out the desserts in the list
		System.out.println("\n=====Desserts=====");
		while (i <= lastpos[2]) {
			MenuItem temp = histMenuItems.get(i);
			System.out.println(i+1 + " : " + temp.getName() + " : $" + temp.getPrice());
			System.out.println(temp.getDescription());
			i++;
		}
		System.out.println("");
		
		//Prints out the promotional items in the list
		System.out.println("=====List of Promotional Items=====");
		for (int j = 0; j < histPromoItems.size(); j++) {
			PromoItem temp2 = histPromoItems.get(j);
			System.out.println(j+1 + " : " +temp2.getName() + " : $" + temp2.getPrice());
			temp2.printPromoItems();
			System.out.println("");
		} 
	}

	
	public void printReport(int periodStart, int periodEnd) throws IOException{		
		FileReaderWriter.printReport(getSalesBreakdown(periodStart, periodEnd));
	}
	
	public static void addPromoItem(PromoItem item) throws FileNotFoundException, IOException {
		histPromoItems.add(item);
		saveItem();
	}
	
	public static void addMenuItem(int choice, MenuItem item) throws FileNotFoundException, IOException {
		histMenuItems.add(lastpos[choice-1]+1, item);
		for (int j = choice-1; j < lastpos.length; j++) {
			// increase the last position of the array behind
			lastpos[j]++;
		}
		saveItem();
	}
	
	public static void loadMenuItem() throws FileNotFoundException, IOException {
		// loads presaved files
		histMenuItems = FileReaderWriter.readMenuItemFile("histMenuItem.txt");
		lastpos = FileReaderWriter.readLastPos("histlastpos.txt");
		histPromoItems = FileReaderWriter.readPromoItemFile("histPromoItem.txt");
	}
	
	public static void saveItem() throws FileNotFoundException, IOException {
		//saves current data onto a txt file
		FileReaderWriter.createFile("histMenuItem.txt");
		for (int i = 0; i < histMenuItems.size(); i++) {
			FileReaderWriter.addMenuItem("histMenuItem.txt", histMenuItems.get(i));
			}
		FileReaderWriter.createFile("histPromoItem.txt");
		for (int j = 0; j < histPromoItems.size(); j++) {
			FileReaderWriter.addPromoItem("histPromoItem.txt", histPromoItems.get(j));
		}
		FileReaderWriter.createFile("histlastpos.txt");
		FileReaderWriter.saveLastPos("histlastpos.txt", lastpos);
		
	}
		
}
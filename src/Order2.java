import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Order2 {
	public int orderId;
	private ArrayList<MenuItem> menuArray;
	private ArrayList<PromoItem> promoArray;
	private Menu myMenu;

	public Order2() throws IOException{
		myMenu = new Menu();
		menuArray = new ArrayList<MenuItem>();
		promoArray = new ArrayList<PromoItem>();
		orderId=0;
	}
	
	public double getTotalPrice() {
		double price = 0;
		for (int i = 0; i < menuArray.size(); i++) {
			price += menuArray.get(i).getQuantityPrice();
		}
		for (int j = 0; j < promoArray.size(); j++) {
			price += promoArray.get(j).getQuantityPrice();
		}
		return price;
	}
	
	public void viewOrder() {
		System.out.println("Table: " + orderId);
		System.out.println("Menu items ordered: ");
		for (int i = 0; i < menuArray.size(); i++) {
			System.out.println(i + 1 + " : " + menuArray.get(i).getName() + " : Quantity " + menuArray.get(i).getQuantity());
		}
		System.out.println("Promotional items ordered: ");
		for (int j = 0; j < promoArray.size(); j++) {
			System.out.println(j + 1 + " : " + promoArray.get(j).getName() + " : Quantity " + promoArray.get(j).getQuantity());
			}
		System.out.println("Subtotal Price: " + getTotalPrice());
	}
	
	public void addMenuOrder(int k, int quantity) {
		MenuItem temp = myMenu.getMenuItem(k);
		for (int i = 0; i < menuArray.size(); i++) {
			if (temp.getName().equals(menuArray.get(i).getName())) {
				menuArray.get(i).addQuantity(quantity);
				return;
			}
		}
		temp.addQuantity(quantity);
		menuArray.add(temp);
	}
	
	public void addPromoOrder(int k, int quantity) {
		PromoItem temp = myMenu.getPromoItem(k);
		for (int i = 0; i < promoArray.size(); i++) {
			if (temp.getName().equals(promoArray.get(i).getName())) {
				promoArray.get(i).addQuantity(quantity);
				return;
			}
		}
		temp.addQuantity(quantity);
		promoArray.add(temp);
	}

	public void deletePromoOrder(int k) {
		PromoItem temp = myMenu.getPromoItem(k);
		for (int i = 0; i < promoArray.size(); i++) {
			if (temp.getName().equals(promoArray.get(i).getName())) {
				promoArray.remove(i);
				return;
			}
		}
		System.out.println("Promotional item is not found!");
	}
	
	public void deleteMenuOrder(int k) {
		MenuItem temp = myMenu.getMenuItem(k);
		for (int j = 0; j < menuArray.size(); j++) {
			if (temp.getName().equals(menuArray.get(j).getName())) {
				promoArray.remove(j);
				return;
			}
		}
		System.out.println("Menu item is not found!");
	}
	
	public void deleteOrder() {
		
	}
	
	public void printInvoice() throws IOException {

		//FileWriter fwStream = new FileWriter( "invoice.txt" );
		//BufferedWriter bwStream = new BufferedWriter( fwStream );
		//PrintWriter pwStream = new PrintWriter( bwStream );
		System.out.println("   HOTBOX BAR & GRILL   ");
		System.out.println("    Block S3.1-B4");
		System.out.println("*************************");
		System.out.println("Table = " + orderId);
		System.out.println("-------------------------");
		viewOrder();
		System.out.println("-------------------------");
		System.out.println("Price before GST = " + getTotalPrice());
		System.out.println("-------------------------");
		System.out.println("Nett Price = " + getTotalPrice()*1.177);
	}

	public void setOrderId(int table_id) {
		orderId = table_id;		
	}
	
	public ArrayList<MenuItem> getMenuArray(){
		return menuArray;
	}
	
	public ArrayList<PromoItem> getPromoArray(){
		return promoArray;
	}
}

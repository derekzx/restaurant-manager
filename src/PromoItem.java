import java.util.ArrayList;

public class PromoItem {
	private ArrayList<MenuItem> promoItem;
	private double price;
	private String name;
	private int quantity;
	private double sales;
	
	public PromoItem(String name, double price) {
		setPrice(price);
		setName(name);
		quantity = 0;
		promoItem = new ArrayList<MenuItem>();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public double getQuantityPrice() {
		return quantity * price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return promoItem.size();
	}
	
	public MenuItem getItem(int index) {
		return promoItem.get(index);
	}
	
	public double getSales(){
		return sales;
	}
	
	public void setSales(double sales){
		this.sales = sales;
	}
	
	public void addItem(MenuItem item) {
		promoItem.add(item);
	}
	
	public void removeItem(String name) {
		//initialise a boolean to mark success of search for item
		boolean complete = false;
		
		//for loop to search for the item and break when item is found
		for (int i = 0; i < promoItem.size(); i++) {
			if (promoItem.get(i).getName().equals(name)) {
				System.out.println("Item removed!");
				promoItem.remove(i);
				complete = true;
				break;
			}
		}
		
		//prints error message when item not found
		if (!complete) {
			System.out.println(name + " is not found in promotional item");
		}
	}
	
	public void printPromoItems() {
		
		//prints out list of its items
		for (int i = 0; i < promoItem.size(); i++) {
			System.out.println(promoItem.get(i).getName() + " : " + promoItem.get(i).getDescription());
		}
	}

}


public class MenuItem {
	private double price;
	private String descrption;
	private String name;
	private int menuId;
	private int quantity;
	private double sales;
	
	public MenuItem (String name, String description, double price, int menuId) {
		this.name = name;
		this.menuId = menuId;
		quantity = 0;
		setDescription(description);
		setPrice(price);
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
	
	public void setDescription(String description) {
		this.descrption = description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getMenuId() {
		return menuId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return descrption;
	}
	
	public double getSales(){
		return sales;
	}
	
	public void setSales(double sales){
		this.sales = sales;
	}

	
	
}

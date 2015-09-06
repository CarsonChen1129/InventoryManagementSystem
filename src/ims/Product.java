package ims;

/**
 * An object that contains all the information about a product, currently only contains inventory level
 * @author CarsonChen
 *
 */
public class Product {
	
	private int inventory_level;					/* The inventory level of the product */
	
	/**
	 * get the inventory level of a product
	 * @return inventory_level
	 */
	synchronized public int getInventory_level() { return this.inventory_level; }
	
	/**
	 * set the inventory level of a product
	 * @param inventory_level
	 */
	synchronized public void setInventory_level(int inventory_level) { this.inventory_level = inventory_level; }
	

}

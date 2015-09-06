package ims;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author CarsonChen
 *
 */
public class Warehouse {
	
		/*In real life, the data may come from external sources, here I use concurrentHashMap just for indication*/
		private ConcurrentHashMap<String,Product> warehouse = new ConcurrentHashMap<String,Product>();	
		
		private int inventory_level;				/* The inventory level of a product */
		private boolean resourceAvailable = true;	/* A boolean variable to ensure the concurrency */
		
		/**
		 * Warehouse constructor
		 */
		Warehouse() {}
		

		/**
		 * Browse the warehouse and restock a product into the warehouse
		 * 1.If a product is not found in the warehouse, store the productId and the amount to be restocked into the warehouse
		 * 2.If a product can be found in the warehouse, update the inventory level of the product
		 * @param productId
		 * @param amountToRestock
		 */
		public synchronized void restock(String productId, int amountToRestock ) {
			
			
			/* If other thread is using the resource, keep the thread waiting until it got notified*/
			if (!resourceAvailable) {
				try {
					wait();
				} catch(InterruptedException e) {
					System.out.println("InterruptedException caught");
				}
			}
			
			/* Change the boolean value to prevent the conflicts when other threads are trying to use the resouces at the same time */
			resourceAvailable = false;
			Product theProduct;
			
			
			if (!warehouse.containsKey(productId)) {				/* 1.If a product is not found in the warehouse, store the productId and the amount to be restocked into the warehouse*/
				System.out.println("[The product does not exist, adding into the warehouse...]");
				theProduct 		= new Product();
				inventory_level = amountToRestock;
				theProduct.setInventory_level(inventory_level);
				warehouse.put(productId, theProduct);
			} else {												/* 2.If a product can be found in the warehouse, update the inventory level of the product */
				theProduct 		= warehouse.get(productId);
				inventory_level = theProduct.getInventory_level();
				inventory_level += amountToRestock;
				theProduct.setInventory_level(inventory_level);
				warehouse.put(productId,theProduct);
			}
			
			System.out.println("[Successfully restock "+amountToRestock+" products, current inventory level of "+productId+" is: "+inventory_level+"]");
			
			/* Change the boolean variable and notify other threads */
			resourceAvailable = true;
			notify();
			
		}
		
		/**
		 * Browse the warehouse and pick a product from the warehouse
		 * 1.If the product is not found, tell the user that the product is not in stock
		 * 2.If the product can be found but with inventory level 0, tell the user that the product is out of stock
		 * 3.If the product can be found but does not have enough amount to be picked, tell the user that the request cannot be fulfilled
		 * 4.If the product can be found and has enough inventory level,pick up the product and update the warehouse
		 * @param productId
		 * @param amountToPick
		 * 
		 * @return Product
		 */
		public synchronized Product pick(String productId, int amountToPick) {
			
			/* If other thread is using the resource, keep the thread waiting until it got notified*/
			if (!resourceAvailable) {
				try{
					wait();
				}catch(InterruptedException e){
					System.out.println("InterruptedException caught");
				}	
			}
			
			/* Change the boolean value to prevent the conflicts when other threads are trying to use the resouces at the same time */
			resourceAvailable = false;
			Product theProduct;
			
			
			if (!warehouse.containsKey(productId)) {		/* 1.If the product is not found, tell the user that the product is not in stock */
				System.out.println("[The product "+productId+" is not in stock]");
				resourceAvailable = true;
				notify();
				return null;
			} else {	
				
				theProduct = warehouse.get(productId);
				
				if (theProduct.getInventory_level() == 0 ) { /* 2.If the product can be found but with inventory level 0, tell the user that the product is out of stock*/
					System.out.println("[The product "+productId+" is out of stock]");
					resourceAvailable = true;
					notify();
					return null;
				} else if ((theProduct.getInventory_level() - amountToPick)<0) { /* 3.If the product can be found but does not have enough amount to be picked, tell the user that the request cannot be fulfilled*/
					System.out.println("[The product "+productId+" does not have enough inventory level in stock, available: "+theProduct.getInventory_level()+"]");
					resourceAvailable = true;
					notify();
					return null;
				} else {	/* 4.If the product can be found and has enough inventory level,pick up the product and update the warehouse */
					inventory_level = theProduct.getInventory_level();
					inventory_level -= amountToPick;
					theProduct.setInventory_level(inventory_level);
					warehouse.put(productId,theProduct);
					System.out.println("[Successfully pick "+amountToPick+" products,current inventory level of "+productId+" is:"+inventory_level+"]");
					resourceAvailable = true;
					notify();
					return theProduct;
				}
				
			} 
			
		}

}

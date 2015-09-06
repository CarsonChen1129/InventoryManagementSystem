package ims;

/**
 * Create threads to handle "Add 'amountToRestock' of the given productId to inventory" request 
 * @author CarsonChen
 *
 */
public class RestockingResult implements Runnable {
	  
	private int amountToRestock;		/* Amount of product that need to be restocked */
	private String productId;			/* The id of the product that need to be restocked */
	private Warehouse warehouse;		/* The warehouse object */
	private Thread thread;				/* Current thread */
	
	/**
	 * Initialize the variables and the thread
	 * @param productId
	 * @param amountToRestock
	 * @param warehouse
	 */
	RestockingResult(String productId,int amountToRestock, Warehouse warehouse) {
		
		this.amountToRestock 	= amountToRestock;
		this.productId 			= productId;
		this.warehouse 			= warehouse;
		
		try {
			thread = new Thread(this);
			thread.start();
		} catch(Exception e) {
			System.out.println("Unable to create the thread, currently active threads number: "+Thread.activeCount());
		}
	
		
	}

	/**
	 * The method to run the thread
	 * @from Runnable
	 */
	public void run() {
		
		try {
			warehouse.restock(productId, amountToRestock);
			thread.interrupt();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	    
	}

}

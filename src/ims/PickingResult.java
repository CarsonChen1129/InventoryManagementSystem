package ims;

/**
 * Create threads to handle "Deduct 'amountToPick' of the given 'productId' from inventory" request 
 * @author CarsonChen
 *
 */
public class PickingResult implements Runnable {
       
    
	private int amountToPick;		/* Amount of product that need to be picked */
	private String productId;		/* The id of the product that need to be picked */
	private Warehouse warehouse;	/* The warehouse object */
	private Thread thread;			/* Current thread */
	
	/**
	 * Initialize the variables and the thread
	 * @param productId
	 * @param amountToPick
	 * @param warehouse
	 */
	PickingResult(String productId,int amountToPick, Warehouse warehouse) {
		
		this.amountToPick 	= amountToPick;
		this.productId 		= productId;
		this.warehouse 		= warehouse;
		
		try {
			thread 	= new Thread(this);
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
			warehouse.pick(productId, amountToPick);
			thread.interrupt();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}

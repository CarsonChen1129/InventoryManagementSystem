package ims;

import java.util.Random;

/**
 * 
 * @author CarsonChen
 *
 */
public class Operations implements InventoryManagementSystem{
	
	
	
	/**
	 * run test to do 100 restock requests and 100 pick requests
	 */
	public void runTest(Warehouse warehouse)  {
		
		System.out.println();
		System.out.println("====================Restock Tests====================");
		System.out.println();
		
		/* Do 100 restock requests */
		for(int i=0;i<100;i++){
				restockProduct(Integer.toString(i+1), (1+new Random().nextInt(20)), warehouse);
		}
		
		System.out.println();
		System.out.println("====================Pick Tests====================");
		System.out.println();
		
		/* Do 100 pick requests */
		for(int i=0;i<100;i++){
				pickProduct(Integer.toString((new Random().nextInt(100))),(1+new Random().nextInt(20)), warehouse);
		}	

	}
	

	/**
     * Deduct 'amountToPick' of the given 'productId' from inventory.
     * @from InventoryManagementSystem
     *
     * @param productId
     * @param amountToPick
     *
     * @return PickingResult
     */
	synchronized public PickingResult pickProduct(String productId, int amountToPick, Warehouse warehouse) {
		
			return new PickingResult(productId,amountToPick,warehouse);
		
	}

	/**
     * Add 'amountToRestock' of the given productId to inventory.
     * @from InventoryManagementSystem
     *
     * @param productId
     * @param amountToRestock
     *
     * @return RestockingResult
     */
	synchronized public RestockingResult restockProduct(String productId, int amountToRestock, Warehouse warehouse) {
		
		
			return new RestockingResult(productId,amountToRestock,warehouse);
		
		
	}

}

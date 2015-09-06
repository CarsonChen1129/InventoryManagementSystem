package ims;

/**
 * Implementations of this interface must be thread-safe. Access to shared data must be
synchronized as methods of this
 * interface may be executed from multiple threads.
 * */
public interface InventoryManagementSystem {
    /**
     * Deduct 'amountToPick' of the given 'productId' from inventory.
     *
     * @param productId
     * @param amountToPick
     * @param warehouse
     *
     * @return PickingResult
     */
    PickingResult pickProduct(String productId, int amountToPick, Warehouse warehouse);
    /**
     * Add 'amountToRestock' of the given productId to inventory.
     *
     * @param productId
     * @param amountToRestock
     * @param warehouse
     *
     * @return RestockingResult
     */
    RestockingResult restockProduct(String productId, int amountToRestock, Warehouse warehouse);
}

package seedu.address.model.person;

// class not implemented yet
public class Order {

    // feel free to change - tx
    private boolean isDone = false;

    // function to be removed
    public Name getName() {
        // not implemented yet
        return null;
    }

    /**
     * Returns true if both orders of the same id have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        // to be edited
        return otherOrder != null
                && otherOrder.getName().equals(getName());
    }

    /**
     * Marks this {@code Order} as done
     */
    public void markAsDone() {
        // feel free to change - tx
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }
}

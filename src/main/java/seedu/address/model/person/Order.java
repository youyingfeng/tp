package seedu.address.model.person;

// class not implemented yet
public class Order {

    private final String description;
    private boolean isDone = false;

    public Order(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
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

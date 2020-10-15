package seedu.address.model.person;

import seedu.address.commons.core.index.Index;

// class not implemented yet
public class Order {

    private final String description;
    private boolean isDone = false;
    private final Index clientId;

    public Order(String description, Index clientId) {
        this.description = description;
        this.clientId = clientId;
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
        return false;
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

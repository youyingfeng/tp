package seedu.address.model.person;

// class not implemented yet
public class Order {

    private final String DESCRIPTION;
    private boolean isDone = false;

    public Order(String description) {
        this.DESCRIPTION = description;
    }

    public String getDescription() {
        return this.DESCRIPTION;
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

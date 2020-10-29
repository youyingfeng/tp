package seedu.address.model.person;

import java.time.LocalDate;
import java.util.function.Predicate;

public class OrderDatePredicate implements Predicate<Order> {

    private LocalDate date;

    public OrderDatePredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Order order) {
        LocalDate orderDeliveryDate = order.getDeliveryDateTime().toLocalDate();
        return date.isEqual(orderDeliveryDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof OrderDatePredicate // instanceof handles nulls
                           && date.equals(((OrderDatePredicate) other).date));
    }
}

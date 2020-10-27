package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Client;
import seedu.address.model.person.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    // Manually added
    public static final Order SHOES = new OrderBuilder().withDescription("shoes")
            .withAddress("123, Jurong West Ave 6, #08-111").withClientId("1").withDate("2020-12-12 2359")
            .withTags("important").build();
    public static final Order DRESS = new OrderBuilder().withDescription("dress")
            .withAddress("311, Clementi Ave 2, #02-25").withClientId("2").withDate("2020-10-12 2359")
            .withTags("important").build();
    public static final Order SKIRT = new OrderBuilder().withDescription("skirt")
            .withAddress("wall street").withClientId("2").withDate("2020-8-12 2359")
            .withTags("important").build();
    public static final Order HAT = new OrderBuilder().withDescription("hat")
            .withAddress("10th street").withClientId("3").withDate("2020-6-12 2359")
            .withTags("important").build();
    public static final Order BRACELET = new OrderBuilder().withDescription("bracelet")
            .withAddress("michegan ave").withClientId("4").withDate("2020-4-12 2359")
            .withTags("important").build();
    public static final Order HELMET = new OrderBuilder().withDescription("helmet")
            .withAddress("little tokyo").withClientId("5").withDate("2020-2-12 2359")
            .withTags("important").build();
    public static final Order GLASSES = new OrderBuilder().withDescription("glasses")
            .withAddress("4th street").withClientId("6").withDate("2020-2-30 2359")
            .withTags("important").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Client AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalOrders() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(SHOES, DRESS, SKIRT, HAT, BRACELET, HELMET, GLASSES));
    }
}

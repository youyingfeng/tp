package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Order;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {
    public static final String DEFAULT_CLIENTID = "2";
    public static final String DEFAULT_ORDERID = "1";
    public static final String DEFAULT_DESCRIPTION = "shoes";
    public static final String DEFAULT_DATE = "2020-12-12 2359";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final boolean DEFAULT_COMPLETION_STATUS = false;

    private Index clientId;
    private Index orderId;
    private String description;
    private LocalDateTime deliveryDate;
    private LocalDateTime creationDate;
    private Address address;
    private boolean isDone;
    private Set<Tag> tags;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        try {
            orderId = ParserUtil.parseOrderIndex(DEFAULT_ORDERID);
            clientId = ParserUtil.parseClientIndex(DEFAULT_CLIENTID);
            description = DEFAULT_DESCRIPTION;
            address = new Address(DEFAULT_ADDRESS);
            tags = new HashSet<>();
            deliveryDate = ParserUtil.parseDate(DEFAULT_DATE);
            creationDate = ParserUtil.parseDate(DEFAULT_DATE);
            isDone = DEFAULT_COMPLETION_STATUS;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     * */
    public OrderBuilder(Order orderToCopy) {
        orderId = orderToCopy.getOrderId();
        clientId = orderToCopy.getClientId();
        description = orderToCopy.getDescription();
        deliveryDate = orderToCopy.getDeliveryDateTime();
        creationDate = orderToCopy.getCreationDateTime();
        address = orderToCopy.getAddress();
    }

    /**
     * Sets the {@code orderId} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderId(String orderId) {
        try {
            this.orderId = ParserUtil.parseOrderIndex(orderId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code clientId} of the {@code Order} that we are building.
     */
    public OrderBuilder withClientId(String clientId) {
        try {
            this.clientId = ParserUtil.parseClientIndex(clientId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Order} that we are building.
     */
    public OrderBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public OrderBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Order} that we are building.
     */
    public OrderBuilder withDeliveryDate(String date) {
        try {
            this.deliveryDate = ParserUtil.parseDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Order} that we are building.
     */
    public OrderBuilder withCreationDate(String date) {
        try {
            this.creationDate = ParserUtil.parseDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Order} that we are building.
     */
    public OrderBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Order} that we are building.
     */
    public OrderBuilder withCompletionStatus(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Creates {@code Order} using the order fields.
     */
    public Order build() {
        LocalDateTime creationDateTime = LocalDateTime.now();
        return new Order(orderId, clientId, description, address, deliveryDate, creationDate, creationDate, isDone);
    }

}

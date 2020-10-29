package seedu.address.logic.parser;

import java.time.format.DateTimeFormatter;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Original Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* New Prefix definitions */
    public static final Prefix CLIENT_PREFIX_NAME = new Prefix("--name");
    public static final Prefix CLIENT_PREFIX_ADDRESS = new Prefix("--address");
    public static final Prefix CLIENT_PREFIX_EMAIL = new Prefix("--email");
    public static final Prefix CLIENT_PREFIX_PHONE = new Prefix("--phone");

    /* Prefix definitions for DELETE */
    public static final Prefix DELETE_PREFIX_CLIENT = new Prefix("--client");
    public static final Prefix DELETE_PREFIX_ORDER = new Prefix("--order");

    /* Prefix definitions for DONE */
    public static final Prefix DONE_PREFIX_ORDER = new Prefix("--order");

    /* Prefix definitions for ORDER */
    public static final Prefix ORDER_PREFIX_DESCRIPTION = new Prefix("--description");
    public static final Prefix ORDER_PREFIX_CLIENT = new Prefix("--client");
    public static final Prefix ORDER_PREFIX_ADDRESS = new Prefix("--address");
    public static final Prefix ORDER_PREFIX_DATE = new Prefix("--date");

    /* Prefix definitions for UPDATE CLIENT */
    public static final Prefix UPDATE_CLIENT_PREFIX_CLIENTID = new Prefix("--clientid");
    public static final Prefix UPDATE_CLIENT_PREFIX_NAME = new Prefix("--name");
    public static final Prefix UPDATE_CLIENT_PREFIX_ADDRESS = new Prefix("--address");
    public static final Prefix UPDATE_CLIENT_PREFIX_EMAIL = new Prefix("--email");
    public static final Prefix UPDATE_CLIENT_PREFIX_PHONE = new Prefix("--phone");

    /* Prefix definitions for UPDATE ORDER */
    public static final Prefix UPDATE_ORDER_PREFIX_ORDERID = new Prefix("--orderid");
    public static final Prefix UPDATE_ORDER_PREFIX_CLIENTID = new Prefix("--clientid");
    public static final Prefix UPDATE_ORDER_PREFIX_DESCRIPTION = new Prefix("--description");
    public static final Prefix UPDATE_ORDER_PREFIX_ADDRESS = new Prefix("--address");
    public static final Prefix UPDATE_ORDER_PREFIX_DATE = new Prefix("--date");

    /* Formatter for all date/time inputs */
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
}

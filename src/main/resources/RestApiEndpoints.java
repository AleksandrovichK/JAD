package by.mtis.ui.constant;

/**
 * A collection for REST URLs based on spinal-case (hyphen(-)
 * delimited)
 */
public interface RestApiEndpoints {
    interface Administration {
        String BASE = "/administration";

        String USER_MANAGEMENT = "/user";
        String GROUP_MANAGEMENT = "/group";

        String CHANGE_PASSWORD = "/chgpwd";
    }

    interface ApplicationInfo {
        String BASE = "/app-info";
        String IMPLEMENTATION_VERSION = "/impl-version";
    }

    interface BankAccount {
        String BASE = "/bank-account";
        String TYPES = "/types";
        String MAIN_TYPE_ID = "/main-type-id";
        String ADDITIONAL_TYPE_ID = "/additional-type-id";
    }

    interface Broker {
        String BASE = "/broker";
        String VAT_RATE = "/vat-rate";
        String COMMISSION = "/commission";
        String HISTORY_RECALCULATION = "/history";
        String RECONCILIATION = "/reconciliation";
        String TREE = "/tree";
        String LIST = "/list";
        String CANCELLED_STATUS = "canceled-status/{id}";
        String SIGN_STATUS = "/sign/{id}";
        String ON_CLOSURE_STATUS = "/onclosure/{id}";
        String CLOSED_STATUS = "/closed/{id}";
        String CHANGE = "/change";
        String CARD_HISTORY_SAVE = "/broker-card-history-changes-save";
        String CARD_HISTORY = "/broker-card-history";

        interface BrokerServiceAddress {
            String SERVICE_ADDRESS = "/address";
            String UNLINK_BY_STREET = "/unlink-by-street/";
        }

        interface MainData {
            String BROKER_LEVELS = "/broker-levels";
            String CONTRACT_STATUSES = "/contract-statuses";
            String PAYMENT_METHODS = "/payment-methods";
            String COMMISSIONS = "/commissions";
            String COMMISSION_BASES = "/commission-bases";
            String COMMISSION_TRANSFER_METHODS = "/commission-transfer-methods";
            String COMMISSION_DOCUMENTS = "/commission-documents";
            String BROKER_NAMES_BY_LEVELS = "/broker-names-by-levels";
            String BROKER_THIRD_LEVEL_ID = "/broker-third-level-id";
            String BROKER_SECOND_LEVEL_ID = "/broker-second-level-id";
            String BROKER_FIRST_LEVEL_ID = "/broker-first-level-id";
            String MAIL_DELIVERY_TYPES = "/mail-delivery-types";

        }
    }

    interface CommonConfigParam {
        String BASE = "/common";
    }

    interface Constructor {
        String BASE = "/constructor";

        String BONUS = "/bonus";
        String DISCOUNT = "/discount";
        String TARIFF_PLAN = "/tariff-plan";

        interface Bonus {
            String ADDING_TP = "/adding-tp";
            String FIND_BY_ID_VERSIONS = "/versions";
            String FIND_BY_DATE_VERSION = "/version";
            String FIND_BY_ID_COMMON = "/common/{id}";
            String FIND_BY_ID_INITIAL = "/initial/{id}";
            String FIND_BY_ID_EXTRA = "/extra/{id}";
            String FIND_BY_ID_STATUS_HISTORY = "/stat-history/{id}";
            String FIND_BY_ID_PARAM_HISTORY = "/param-history/{id}";
            String SAVE_STATUS = "/save-status";
            String SAVE_NULL_VERSION = "/save0";

            interface BonusGroup {
                String BASE = "/group";
                String SAVE_STATUS = "/save-status";
                String FIND_ACCOUNTS = "/accounts";
            }

            interface BonusAccountSettings {
                String BASE = "/acc-settings";
                String POINTS = "/points";
                String TARIFF_PLANS = "/tariff-plans";
                String ALL_TARIFF_PLANS = "/all-tariff-plans";
                String NUMERICAL_MEASURE = "/num-measure";
                String PERCENT_MEASURE = "pct-measure";
                String WORK_KIND = "/work-kind";
            }
        }

        interface Discount {
            String SAVE = "/save";
            String UPDATE_NULL_VERSION = "/discount-null-version";
            String KIND_DISCOUNT = "/discount-kind";
            String ACTIVATION_TYPE = "/activation-type";
            String ADD_ADDRESS_DISCOUNT = "/address";
            String GET_ADDRESS_SHORT = "/address/list";
            String GET_STREET_LIST = "/street/list";
            String GET_STREET_AND_HOUSES_LIST = "/street-and-houses/list";
            String GET_HOUSES_LIST = "/house/list";
            String GET_LINK_STREET_AT_DISCOUNT = "/street/get-link";
            String GET_LINK_HOUSES_AT_DISCOUNT = "/house/get-link";
            String DISCOUNT_TYPE = "/discount-type";
            String GET_DISCOUNTS = "/discounts";
            String GET_ADDRESS_DISCOUNT = "/address-discount";
            String GET_KEYVAL_TP = "/tariff-plan";
            String SET_DISCOUNT_STATUS_ARCHIVE = "/discount-status-archive";
            String SET_DISCOUNT_STATUS_DELETE = "/discount-status-delete";
            String GET_DISCOUNT_COMMON = "/discount-common";
            String GET_DISCOUNT_EXTRA = "/discount-extra";
            String GET_DISCOUNT_ENCUMBRANCE = "/discount-encumbrance";
            String GET_DISCOUNT_INITIAL = "/discount-initial";
            String GET_DISCOUNT_VERSIONS = "/discount-versions";
            String DELETE_DISCOUNT_VERSION = "/";
            String GET_PARAM_HISTORY = "/history-param";
            String GET_DISCOUNT_STATUS_ARCHIVE = "/status-archive";
            String GET_DISCOUNT_STATUS_ACTIV = "/status-activ";
            String GET_DISCOUNT_MEASURE_ID_ABSOLUTE = "/measure-absolute";
            String GET_DISCOUNT_MEASURE_ID_PERCENT = "/measure-percent";
            String DISCOUNT_ACTIVATION_TYPE_ID_MANUAL = "/discount-activation-manual";
            String DISCOUNT_ACTIVATION_TYPE_ID_AUTO = "/discount-activation-auto";
            String DISCOUNT_TYPE_ID_SUBSCRAGE = "/discount-type/subscr-age";
            String DISCOUNT_TYPE_ID_CHARGES_AMOUNT_ON_SERVICES = "/discount-type/amount-on-services";
            String DISCOUNT_TYPE_ID_CHARGES_AMOUNT_ON_ACC = "/discount-type/amount-on-acc";
            String DISCOUNT_TYPE_ID_USE_PERIOD = "/discount-type/use-period";
            String DISCOUNT_TYPE_ID_TRAFFIC = "/discount-type/traffic";
            String DISCOUNT_TYPE_ID_SERVICES_COMBINATION = "/discount-type/services-combination";
            String DISCOUNT_TYPE_ID_ADVANCE_PAYMENT = "/discount-type/advance-payment";
            String DISCOUNT_TYPE_ID_ADDITIONAL = "/discount-type/additional";
            String DISCOUNT_TYPE_ID_AFFILIATED_SUBSCRS = "/discount-type/affiliated-subscrs";
            String DISCOUNT_ACTIVE_BEGIN_TYPE_ID_FROM_DATE = "/activ-begin/from-date";
            String DISCOUNT_ACTIVE_BEGIN_TYPE_ID_FROM_ACTIVATION = "/activ-begin/from-activation";
            String DISCOUNT_ACTIVE_END_TYPE_ID_TO_DATE = "/activ-begin/to-date";
            String DISCOUNT_ACTIVE_END_TYPE_ID_TO_PERIOD = "/activ-begin/to-period";
            String DISCOUNT_ENCUMBRANCE_TERM_TYPE_TO_DATE = "/term-encumbr-type/to-date";
            String DISCOUNT_ENCUMBRANCE_TERM_TYPE_MIN_USE_PERIOD = "/term-encumbr-type/min-use-period";
            String DISCOUNT_TP_LINK_TYPE_ID_FREE_CHANGE = "/tp-link-type-id/free-change";
            String DISCOUNT_TP_LINK_TYPE_ID_ACCRUAL_SUM = "/tp-link-type-id/accrual-sum";
            String DISCOUNT_TP_LINK_TYPE_ID_APPLY_TO = "/tp-link-type-id/apply-to";
            String DISCOUNT_TP_LINK_TYPE_ID_TP_MIX = "/tp-link-type-id/tp-mix";
        }

        interface TariffPlan {
            String ACTIVITY_SUB_TYPES = "/activity-sub-types";
            String ACTIVITY_TYPES = "/activity-types";
            String TP_ATV = "/tariff-plans-atv";
            String BASE_UNITS = "/base-units";
            String CONNECTION_TYPES = "/connection-types";
            String CREDIT_LIMIT_TYPES = "/credit-limit-types";
            String DTV_ACCESS_CRITERIA = "/dtv-access-criteria";
            String LOCK_LINIT_TYPES = "/lock-limit-types";
            String FEE_MODES = "/fee-modes";
            String INET_TARIFFICATION_TYPE = "/internet-tariffication-type";
            String PAYMENT_TERM_TYPES = "/payment-term-types";
            String PAYMENT_TYPE = "/payment-type";
            String PERIOD_MEASURES = "/period-measures";
            String RESTRICTION_TYPES = "/restriction-types";
            String SALE_TYPE = "/sale-type";
            String TARIFFICATION_TYPE = "/tariffication-type";
            String TV_CHANNELS = "/tv-channels";
            String TV_PACKAGE_TYPES = "/tv-package-types";
            String WITHDRAWAL_TYPES = "/withdrawal-types";
        }
    }

    interface Contract {
        String BASE = "/contracts";

        String COMMON = "/common";
        String MAIN_TARIFF_PLAN = "/tariffplan";
        String ADDITIONAL_TARIFF_PLAN = "/additional-tariffplan";
        String SEARCH_TARIFF_PLAN = "/search-tariff-plan";
        String DISCOUNTS = "/discounts";
        String BONUSES = "/bonuses";
        String EQUIPMENT = "/equipment";
        String SHIPPING_LIST = "/shipping-list";
        String PACK_OF_SHIPPING_LIST = "/pack-shipping-list";
        String HISTORY_MAC_IP = "/history-mac-ip";
        String TECHNICAL_PARAMS = "/technical-parameters";

        String DOC_STATUSES = "/doc-statuses";
        String YES_NO = "/yes-no";
        String SUBSCR_TYPES = "/subscr-types";
        String ACCOUNTS = "/accounts";
        String SIGNERS = "/signers";
        String PAYMENT_RULES = "/payment-rules";
        String AGENTS = "/agents-fullname";
        String INVOICE_TYPES = "/invoice-types";
        String BILL_DELIVERY_TYPES = "/bill-delivery-types";
        String PENALTY_TYPES = "/penalty-types";
        String FINANCING_SOURCES = "/financing-sources";
        String MEDIATORS = "/mediators";
        String ACCOUNT_AT_MEDIATOR_BASE = "/account-at-mediator";
        String DOC_TYPES = "/doc-types";
        String DOC_FORMATS = "/doc-formats";
        String BY_PERSONAL_ACCOUNT = "/by-personal-account";
        String STATUS_BAN_EDITING = "/status-ban-editing";

        interface Options {
            String BASE = "/options";
            String BILLING_DATE = "/billing-date";
            String ADDITIONAL_AGREEMENT = "/additional-agreement";
            String RENEWED_AGREEMENT = "/renewed-agreement";
            String CHANGES_LIST = "/changes-list";
            String CHANGES = "/changes";
            String CLOSING = "/closing";
            String BLOCKING = "/blocking";
            String UNLOCKING = "/unlocking";
        }
    }

    interface Invoice {
        String BASE = "/invoices";
        String ROSTER = "/{invoice-id}/roster";
        String UPLOAD_INVOICE = "/upload-invoices";
        String UPDATE_STATUS = "/update-status";
        String CREATE_COMMON = "/create-common";
        String CREATE_MODIFIED = "/create-modified";
        String CREATE_ADDITIONAL = "/create-additional";
        String STATUSES = "/statuses";
        String TYPES = "/types";
        String DOCUMENT_TYPES = "/document-types";
        String DOCUMENT_TYPE_CODES = "/document-type-codes";
        String PROVIDER_STATUSES = "/provider-statuses";
        String RECIPIENT_STATUSES = "/recipient-statuses";
        String ADDITIONAL_DATA = "/additional-data";
    }

    interface Jasper{
        String BASE = "/jasper";

        String PRINT_SHIPPING_LIST_PDF = "/print-shipping-list-pdf";
        String PRINT_SHIPPING_LIST_DOC = "/print-shipping-list-doc";
        String PRINT_SHIPPING_LIST_XLS = "/print-shipping-list-xls";
        String LIST_OF_REPORTS = "/list-of-reports";
    }

    interface MainData {
        String BASE = "/main-data";

        interface ConfigParams {
            String BASE = "/config-param";
        }

        interface ContractBreak {
            String BASE = "/contract-break";
        }

        interface Address {
            String BASE = "/address";
            String DISTRICTS = "/districts";
            String STREETS = "/streets";
            String STREETS_HOUSES = "/streets-houses";
            String STREET_TYPES = "/street-types";
            String HOUSES = "/houses";
            String HOUSE_STATUSES = "/house-statuses";
            String ROOMS = "/rooms";
            String ROOM_TYPES = "/room-types";
            String BATCH = "/batch-save";
        }

        interface Discounts {
            String BASE = "/discounts";
            String TYPES = "/types";
            String KINDS = "/kinds";
            String MEASURES = "/measures";
            String USE_PERIODS = "/use-periods";
            String STATUSES = "/statuses";
            String GET_LIST_ACTIVITIES = "/activities";
            String GET_TP_BY_ACTIVITIES = "/activities/tariff-plan";
            String GET_TP_TREE_BY_ACTIVITIES = "/activities/tariff-plan/tree";
            String IS_EXIST_VERSION = "/version_exist";
        }

        interface DefectTV {
            String BASE = "/defect-tv";

            interface ChannelFaultClasses {
                String CHANNEL_CLASSES_FAULT = "/class";
                String GET_CHANNEL_CLASSES_FAULT = "/classes";
            }

            interface ChannelFaultCharacteristics {
                String CHANNEL_CHARACTERISTICS_FAULT = "/characteristic";
                String GET_CHANNEL_CHARACTERISTICS_FAULT = "/characteristics";
            }

            interface ChannelTypesWorkTroubleshooting {
                String CHANNEL_TYPE_WORK_TROUBLE = "/types-work";
                String GET_CHANNEL_TYPE_WORK_TROUBLE = "/types-works";
            }
        }

        interface DocumentTypeForming{
            String BASE = "/document-type-forming";
        }

        interface FailureChannels {
            String BASE = "/failure-tv";
            String FAILURE_CHANNEL = "/";
            String GET_FAILURES_CHANNEL = "/failures";
        }

        interface Station {
            String BASE = "/stations";
            String STATION_TYPES = "/station-types";
        }

        interface Bank {
            String BASE = "/bank";
        }

        interface CorrectingReasons {
            String BASE = "/correcting-reasons";
        }

        interface Country {
            String COUNTRY_CODE = "/country";
        }

        interface Currency {
            String BASE = "/currency";
        }

        interface CurrencyRate {
            String BASE = "/currency-rate";
            String CODE = "/code";
            String NBRB = "/nbrb";
        }

        interface Departments {
            String BASE = "/departments";
        }

        interface Employee {
            String BASE = "/employees";
            String SUMMARY = "/summary";
        }

        interface Goods {
            String BASE = "/goods";
            String MANUFACTURERS = "/manufacturers";
            String MODELS = "/models";
            String GROUPS = "/groups";
            String ACTIONS = "/actions";
            String STATUSES = "/statuses";
        }

        interface Measure {
            String BASE = "/measure";
        }

        interface Depot {
            String BASE = "/depot";
        }

        interface ReasonsClosingEmergencyOrders {
            String BASE = "/reasons-closing-emergency-orders";
        }

        interface AppealsCategories {
            String BASE = "/appeals-categories";
        }

        interface AppealsChannels {
            String BASE = "/appeals-channels";
        }

        interface ReassonsForAppeals {
            String BASE = "/reasons-for-appeals";
        }

        interface Supplies {
            String BASE = "/supplies";
            String FIND_REVISIONS = "/revisions";
        }

        interface VatRate {
            String BASE = "/vat";
            String ARCHIVE = "/saveArchive";
            String NAMEWITHRATE = "namewithrate";
        }

        interface Tv {
            String BASE = "/tv";
            String DIGITAL_CHANNELS = "/digital-channels";
            String ANALOG_CHANNELS = "/analog-channels";
            String STATUSES = "/statuses";
        }

        interface AnalogTvStatus {
            String BASE = "/analog-tv-statuses";
        }

        interface CustomOperation {
            String BASE = "/custom-operation";
            String KIND = "/kind";
            String GROUP = "/group";
            String NAME = "/name";
            String TYPE = "/type";
            String OPERATION_AFFECT = "/operation-affect";
            String SOURCE_ID = "/source-id";
            String VARIETIES = "/varieties";
        }

        interface Bonus {
            String BASIC_TP = "/basic-tp";
            String STATUSES = "/statuses";
            String TYPES = "/types";
        }

        interface BonusGroup {
            String STATUSES = "/statuses";
            String FIND_CONTRACTS = "/contracts/{id}";
        }

        interface BankReturn {
            String BASE = "/bank-return";
            String BANK_NAME = "/names";
        }

        interface PaymentMethod {
            String BASE = "/payment-method";
        }

        interface SubscriberCategories {
            String BASE = "/subscriber-categories";
        }


        interface ArrearsReasons {
            String BASE = "/arrears-reasons";
        }

        String COUNTERPARTY = "/counterparty";

        String BASIC_TP = "/basic-tp";
        String STATUSES = "/statuses";
        String TRAFF_SPEED_MEASURE = "/traff-speed-measures";
        String TRAFF_VOL_MEASURE = "/traff-vol-measures";
        String TRAFFIC_RECALC_RULE = "/traffic-recalc-rules";
    }

    interface Operation {
        String ENTRY_BY_ID = "/{id}";
        /**
         * Used to find of object(s) by not parameters, by a set of parameters
         * or by a custom parameter
         */
        String FIND_KEY_VALUE = "/key-value";
        String FIND_KEY_VALUE_BY_ID = "/key-value/{id}";
        String FIND_HISTORY = "/history";
        String LINK = "/link";
        String SAVE = "/save";
        String SEARCH = "/search";
        String EXTENDED_SEARCH = "/extended-search";
    }

    interface PathParam {
        String ID = "id";
    }

    interface PersonalAccount {
        String BASE = "/personal-account";
        String COMMON_INFO = "/common-info";

        interface Type {
            String BASE = "/type";
            String MAIN = "/main";
        }

        interface Operation {
            String BASE = "/operation";
            String BALANCE_ACTUAL_ID = "/actual";
            String BALANCE_ACCOUNTING_ID = "/accounting";
            String BALANCE_CALCULATED_ID = "/calculated";
            String BALANCE_BROKER_ID = "/broker";
            String BALANCE_SUBSCRIBER_BROKER_ID = "/subscr-broker";
            String BALANCE_OPERATION = "/balance-operation";
            String CUSTOM = "/custom";
            String CORRECTION = "/correction";
            String PARAMETERS = "/parameters";

            interface Balance {
                String BALANCE = "/balance";
                String DETAILED = "/detailed";
                String ACTUAL = "/actual";
                String CALCULATED = "/calculated";
                String ACCOUNTING = "/accounting";
            }
        }
    }

    interface SubscrGroups {
        String BASE = "/subscr-group";
    }

    interface SubscriberCard {
        String BASE = "/subscribers";

        String SHORT_DATA = "/short-data";
        String CALL_CENTER_DATA = "/call-center-data";
        String GET_ID_BY_PHONE = "/id-by-phone";

        String PHOTO = "/photo";
        String ADDRESSES = "/addresses";
        String CONTRACT = "/contract";
        String TARIFF_PLAN = "/tariff-plans";
        String EQUIPMENT = "/equipment";
        String DISCOUNT = "/discounts";
        String BONUS = "/bonuses";
        String NOTIFICATION = "/notifications";
        String RECOURSE = "/recourses";
        String BLOCKINGS = "/blockings";
        String HISTORY = "/history";

        String SEXES = "/sexes";
        String LANGUAGES = "/languages";
        String RESIDENTS = "/residents";
        String COUNTRIES = "/countries";
        String DOCUMENT_TYPES = "/document-types";
        String MAIL_DELIVERY_TYPES = "/mail-delivery-types";
        String MIGRATION_BASES = "/migration-bases";
        String SUBSCR_TYPES = "/subscr-types";
        String SUBGROUP_TYPES = "/subgroup-types";
        String SUBSCR_STATUS = "/subscr-status";
        String SUBSCR_CATEGORIES = "/subscr-categories";

        String GET_DISCOUNT_BY_FILTER = "/{id}/discounts?{typeId},{name}";
        String GET_DISCOUNT_BY_TARIFF_PLAN = "/{id}/discounts";
        String GET_CONNECTED_DISCOUNT_TP = "/{id}/connected-discounts";
        String ADD_DISCOUNT_TO_TARIFF = "/discount-tariff";
        String BONUSES_TO_BONUS_ACCOUNT = "/bonus{id}";
        String UPDATE_LIST_BONUS_FOR_ACCOUNT = "/bonus";
    }

    interface Inventory {
        String BASE = "/inventory";

        interface HouseManagement {
            String BASE = "/house-management";

            String FIND_TARIFF_PLANS = "/house/{id}/tariff-plans";
        }
    }

    interface User {
        String BASE = "/user";
    }

    interface FundsTransfer {
        String BASE = "/funds-transfer";
        String HISTORY = "history";
        String TRANSFER_PERSON_TYPE = "transfer-person-type";
    }

    interface RoundingConfig {
        String BASE = "/rounding-config";
        String METHOD = "/method";
        String PRECISION = "precision";
        String RULE = "/rule";
    }

    interface PromisedPayment {
        String BASE = "/promised-payment";
        String FIND_CONTRACTS = "/contracts/{id}";
        String FIND_OPER_NAMES = "/oper-names";
        String FIND_ACCOUNTS = "/accounts";
        String FIND_HISTORY = "/history";

        interface AvailableAmount {
            String BASE = "/available-amount";
            String TERM = "/term";
            String MIN_INTERVAL = "/min-interval";
            String PARAMS = "/params";
        }
    }

    interface Goods {
        String BASE = "/goods";

        interface Management {
            String BASE = "/management";
            String WRITE_OFF = "/write-off";
            String MOVEMENT = "/movement";
        }
    }

    interface Maintenance {
        String BASE = "/maintenance";
    }

    interface BankStatement {
        String BASE = "/bank-statement";
    }
}
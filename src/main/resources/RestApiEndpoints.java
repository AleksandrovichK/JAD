package by.mtis.constants;

/**
 * A collection for REST URLs based on spinal-case (hyphen(-)
 * delimited)
 */
public interface RestApiEndpoints {
    interface Administration {
        String BASE = "/administration";
        String USERS = "/users";
        String GROUPS = "/groups";
        String PATTERNS = "/templates";
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
        String CANCELLED_STATUS = "canceled-status";
        String SIGN_STATUS = "/sign";
        String ON_CLOSURE_STATUS = "/onclosure";
        String CLOSED_STATUS = "/closed/";
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

    interface Conax {
        String BASE = "/conax";
        String SENDFILE = "/{id}";
    }

    interface CommonConfigParam {
        String BASE = "/common";
    }

    interface Constructor {
        String BASE = "/constructor";
        String TSC_DISCOUNT = "/tsc-discount";
        String BONUS = "/bonus";
        String DISCOUNT = "/discount";

        interface Bonus {
            String ADDING_TP = "/adding-tp";
            String FIND_BY_ID_VERSION = "/version";
            String FIND_BY_ID_STATUS_HISTORY = "/status-history/{id}";
            String FIND_PARAM_HISTORY = "/param-history";
            String CHANGE_TP_REPORT = "/tp-report";
            String REPLENISHMENT_REPORT = "/replenish-report";
            String SERVICE_AMOUNT_REPORT = "/service-report";
            String ADDITIONAL_TYPE_ID = "/additional-type";
            String EQUIPMENT_TYPE_ID = "/equipment-type";
            String GROUP_TYPE_ID = "/group-type";
            String PERIODICITY_ID_ONETIME = "/period-onetime";
            String PERIODICITY_ID_MONTHLY = "/period-monthly";
            String CHECK_TP = "/check-tp";
            String SET_STATUSES_DELETED = "/bonus-status-deleted";

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

        interface ActivationType {
            String ACTIVATION_TYPE_ID_MANUAL = "/activation-type-manual";
            String ACTIVATION_TYPE_ID_AUTO = "/activation-type-auto";
        }

        interface Discount {
            String SAVE = "/save";
            String UPDATE_NULL_VERSION = "/discount-null-version";
            String ADD_ADDRESS_DISCOUNT = "/address";
            String GET_STREET_LIST = "/street/list";
            String GET_STREET_AND_HOUSES_LIST = "/street-and-houses/list";
            String GET_HOUSES_LIST = "/house/list";
            String GET_LINK_STREET_AT_DISCOUNT = "/street/get-link";
            String GET_LINK_HOUSES_AT_DISCOUNT = "/house/get-link";
            String GET_DISCOUNTS = "/discounts";
            String GET_DISCOUNTS_ALL = "/discounts-all";
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
            String DISCOUNT_TYPE_ID_ADDITIONAL = "/discount-type/additional";
            String DISCOUNT_ACTIVE_BEGIN_TYPE_ID_FROM_DATE = "/activ-begin/from-date";
            String DISCOUNT_ACTIVE_BEGIN_TYPE_ID_FROM_ACTIVATION = "/activ-begin/from-activation";
            String DISCOUNT_ACTIVE_END_TYPE_ID_TO_DATE = "/activ-begin/to-date";
            String DISCOUNT_ACTIVE_END_TYPE_ID_TO_PERIOD = "/activ-begin/to-period";
            String DISCOUNT_ENCUMBRANCE_TERM_TYPE_TO_DATE = "/term-encumbr-type/to-date";
            String DISCOUNT_ENCUMBRANCE_TERM_TYPE_MIN_USE_PERIOD = "/term-encumbr-type/min-use-period";
            String DISCOUNT_TP_LINK_TYPE_ID_FREE_CHANGE = "/tp-link-type-id/free-change";
            String DISCOUNT_TP_LINK_TYPE_ID_APPLY_TO = "/tp-link-type-id/apply-to";
            String DISCOUNT_HOUSE_REPORT = "/house";
        }

        interface TscDiscount {
            String FIND_VERSION = "/version";
            String SET_STATUS_ARCHIVED = "/set-status-archived";
            String SET_STATUS_DELETED = "/set-status-deleted";
            String TP_TREE = "/tp-tree";
            String WORK_KIND = "/work-kind";
            String TP_TYPE_ID_COMMON = "/type-common";
            String TP_TYPE_ID_ENCUMBRANCE = "/type-encumbrance";
        }
    }

    interface TariffPlan {
        String BASE = "/tariff-plan";
        String GOODS = "/goods";
        String ACTIVITY_SUB_TYPES = "/activity-sub-types";
        String ACTIVITY_TYPES = "/activity-types";
        String BASE_UNITS = "/base-units";
        String CONNECTION_TYPES = "/connection-types";
        String CREDIT_LIMIT_TYPES = "/credit-limit-types";
        String DTV_ACCESS_CRITERIA = "/dtv-access-criteria";
        String LOCK_LIMIT_TYPES = "/lock-limit-types";
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
        
        interface InteractionMatrix {
            String BASE = "/interaction-matrix";
            String TP_INTERACTION_TYPES = "/interaction-types";
            String VERSIONS = "/versions";
        }
    }

    interface Contract {
        String BASE = "/contracts";
        String CREATE = "/create";
        String CONNECT = "/connect";
        String DISCONNECT = "/disconnect";

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
        String ACCOUNT_AT_BROKER_BASE = "/account-at-broker";
        String DOC_TYPES = "/doc-types";
        String DOC_FORMATS = "/doc-formats";
        String BY_PERSONAL_ACCOUNT = "/by-personal-account";
        String STATUS_BAN_EDITING = "/status-ban-editing";
        String SIGN_ACT_TYPES = "/sign-act-types";
        String ATV_FILTER = "/atv-filter";

        interface Options {
            String BASE = "/options";
            String BILLING_DATE = "/billing-date";
            String ADDITIONAL_AGREEMENT = "/additional-agreement";
            String RENEWED_AGREEMENT = "/renewed-agreement";
            String CHANGES_LIST = "/changes-list";
            String CHANGES = "/changes";
            String CHANGE = "/change";
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

    interface Jasper {
        String BASE = "/jasper";

        String PRINT_SHIPPING_LIST_PDF = "/print-shipping-list-pdf";
        String PRINT_SHIPPING_LIST_DOC = "/print-shipping-list-doc";
        String PRINT_SHIPPING_LIST_XLSX = "/print-shipping-list-xls";
        String LIST_OF_REPORTS = "/list-of-reports";
    }

    interface MainData {
        String BASE = "/main-data";
        String COUNTERPARTY = "/counterparty";
        String BASIC_TP = "/basic-tp";
        String STATUSES = "/statuses";
        String TRAFF_SPEED_MEASURE = "/traff-speed-measures";
        String TRAFF_VOL_MEASURE = "/traff-vol-measures";
        String TRAFFIC_RECALC_RULE = "/traffic-recalc-rules";

        interface ConfigParams {
            String BASE = "/config-param";
            String APP_SETTINGS = "/app-settings";
            String BUSINESS_CONSTANTS = "/business-constants";
        }

        interface AppealWay {
            String APPEAL_WAY = "/callcenter-way";
        }

        interface OperatorProp {
            String BASE = "/operator-prop";
        }

        interface ContractBreak {
            String BASE = "/contract-break";
        }

        interface Dictionary {
            interface OneFieldDict {
                String BASE = "/one-field-dict";
                String STATUS = "/status";
                String STATUS_ARCHIVE = "/status-archive";
                String STATUS_ACTIVE = "/status-active";

            }
        }

        interface Activationtype {
            String BASE = "/activation-type";
            String KEY_VAL = "/key-val";
        }

        interface Address {
            String CITY_DISTRICTS = "/districts";
            String LOCATED_HOUSES = "/located-houses";
            String STREET_TYPES = "/street-types";
            String STREETS = "/streets";
            String HOUSE_STATUSES = "/house-statuses";
            String ROOM_TYPES = "/room-types";
            String BATCH = "/batch";
            String TP_BY_ID = "/tp-by-id";
            String STATION_TYPES = "/station-types";
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
            String GET_TP_BY_ACTIVITIES_FOR_CATALOG = "catalog/tariff-plan";
            String GET_TP_TREE_BY_ACTIVITIES = "/activities/tariff-plan/tree";
            String GET_TP_LIST_DISCOUNT = "/tariff-plan";
            String IS_EXIST_VERSION = "/version_exist";
            String GET_ADDRESS_HOUSE_LIST = "/address-house";
            String GET_SUBSCR_SUBGROUP_LIST = "/subscr-subgroup";
        }

        interface DefectTV {
            String BASE = "/defect-tv";

            interface ChannelFaultClasses {
                String CHANNEL_CLASSES_FAULT = "/class";
                String CHANNEL_CLASSES_FAULT_FULL = "/class/full";
                String GET_CHANNEL_CLASSES_FAULT = "/classes";
            }

            interface ChannelFaultCharacteristics {
                String CHANNEL_CHARACTERISTICS_FAULT = "/characteristic";
                String CHANNEL_CHARACTERISTICS_FAULT_FULL = "/characteristic/full";
                String GET_CHANNEL_CHARACTERISTICS_FAULT = "/characteristics";
            }

            interface ChannelTypesWorkTroubleshooting {
                String CHANNEL_TYPE_WORK_TROUBLE = "/types-work";
                String CHANNEL_TYPE_WORK_TROUBLE_FULL = "/types-work/full";
                String GET_CHANNEL_TYPE_WORK_TROUBLE = "/types-works";
            }
        }

        interface DocumentTypeForming {
            String BASE = "/document-type-forming";
        }

        interface FailureChannels {
            String BASE = "/failure-tv";
            String FAILURE_CHANNEL = "/";
            String GET_FAILURES_CHANNEL = "/failures";
        }

        interface Bank {
            String BASE = "/bank";
            String BIC = "/bic";
        }

        interface CorrectingReasons {
            String BASE = "/correcting-reasons";
        }

        interface Cashbox {
            String BASE = "/cashbox";
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

        interface CancelReasons {
            String BASE = "/cancel-reasons";
        }

        interface AppealsCategories {
            String BASE = "/appeals-categories";
            String SET_TO_ARCHIVE = "/set-archive";
        }

        interface AppealsChannels {
            String BASE = "/appeals-channels";
            String SET_TO_ARCHIVE = "/set-archive";
        }

        interface ReassonsForAppeals {
            String BASE = "/reasons-for-appeals";
            String SET_TO_ARCHIVE = "/set-archive";
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

        interface FrpGroup {
            String BASE = "/frp-group";
            String EMPLOYEE = "/employee";
            String EMPLOYEE_WITH_DEPARTMENT = "/employee-with-department";
            String FRP_EMPLOYEE = "/frp-employee";
        }

        interface PaymentMethod {
            String BASE = "/payment-method";
        }

        interface SubscriberCategories {
            String BASE = "/subscriber-categories";
        }

        interface Switch {
            String BASE = "/switch";
        }

        interface ArrearsReasons {
            String BASE = "/arrears-reasons";
        }
        interface ActivityKindSubgroup {
            String BASE = "/activ-kind-subgroup";
        }
        interface IpZone {
            String BASE = "/ipzone";
        }
        interface SubscrSubgroup {
            String BASE = "/subscr-subgroup";
        }
        interface SignBasis {
            String BASE = "/sign-basis";
        }

    }

    interface Operation {
        String ENTRY_BY_ID = "/{id}";
        /**
         * Used to find of object(s) by not parameters, by a set of parameters
         * or by a custom parameter
         */
        String FIND_KEY_VALUE = "/key-value";
        String FIND_HISTORY = "/history";
        String LINK = "/link";
        String SAVE = "/save";
        String GET_REPORT = "/report";
        String SEARCH = "/search";
        String EXTENDED_SEARCH = "/extended-search";
        String SET_STATUS = "/set-status";
    }

    interface PathParam {
        String ID = "id";
    }

    interface PersonalAccount {
        String BASE = "/personal-account";
        String COMMON_INFO = "/common-info";
        String HISTORY_PERSONAL_ACCAUNT_OPERATIONS = "/history-personal-account-operations";

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
        String BLOCKING_STATUSES = "/blocking-statuses";
        String DEPARTMENTS = "/departments";
        String SUBSCR_GROUPS = "/subscr-groups";
        String SET_STATUSES_DELETED = "/set-status-deleted";
    }

    interface Inventory {
        String BASE = "/inventory";

        interface HouseManagement {
            String HOUSE_CONFIGURATOR = "/house-configurator";
            String ROOM_CONFIGURATOR = "/room-configurator";
            String TARIFF_PLANS = "/tariff-plans";
            String SERVICES = "/services";
        }

        interface StationManagement {
            String BASE = "/station-configurator";
            String LINKED_HOUSES = "/linked-houses";
            String LINKED_STREETS = "/linked-streets";
            String AVAILABLE_ACTIVITY_TYPES = "/available-activ-types";
            String LINKED_ACTIVITY_TYPES = "/linked-activ-types";
        }
    }

    interface User {
        String BASE = "/user";
        String AUTHENTICATION = "/authentication";
        String LOGOUT = "/logout";
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
        String PLANNED = "/planned";
    }

    interface Debtor {
        String BASE = "/debtor";
        String DOC_TYPES = "/doc-types";
        String DOC_KINDS = "/doc-kinds";
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
            String DEPRECIATION = "/depreciation";
            String SET_STATUS_AVAILABLE = "/set-status-available";
        }
    }

    interface Maintenance {
        String BASE = "/maintenance";
    }

    interface BankStatement {
        String BASE = "/bank-statement";
        String UPDATE_RECEIPT = "/update-receipt";
    }

    interface AppealCallCenter {
        String BASE = "/appeal-call-center";
        String BY_SUBSCR_ID = "/by-subscr-id";
    }

    interface Traffic {
        String BASE = "/traffic-pptp";
    }
}
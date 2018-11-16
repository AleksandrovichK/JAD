package by.mtis.ui.rest.contract;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import by.mtis.constants.Entity;
import by.mtis.service.common.IKeyValDictService;
import by.mtis.service.contract.IContractDictionaryService;
import by.mtis.ui.constant.ContentType;
import by.mtis.constants.RestApiEndpoints;
import by.mtis.ui.rest.model.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * The REST API implementation for retrieving catalogs information
 * about contracts by id.
 *
 * @author AleksandrovichK
 */
@Path(RestApiEndpoints.MainData.BASE + RestApiEndpoints.Contract.BASE)
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Api(value = RestApiEndpoints.MainData.BASE + RestApiEndpoints.Contract.BASE)
public class ContractMainDataRestService {
    @EJB
    private IKeyValDictService keyValDictSv;

    @EJB
    private IContractDictionaryService contractsDictionaryService;

    @GET
    @Path(RestApiEndpoints.Contract.DOC_STATUSES)
    @ApiOperation(value = "Provides the list of actual subscriber types in system")
    public Response getDocStatuses() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.DOC_STATUS))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.DOC_TYPES)
    @ApiOperation(value = "Provides the list of actual doc types in system")
    public Response getDocTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.DOC_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.DOC_FORMATS)
    @ApiOperation(value = "Provides the list of actual doc formats in system")
    public Response getDocFormats() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.UNLOAD_DOC_FORMAT))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.YES_NO)
    @ApiOperation(value = "Provides the list of yes-no options in system")
    public Response getYesNo() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.YESNO))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.SUBSCR_TYPES)
    @ApiOperation(value = "Provides the list of actual subscriber types in system")
    public Response getSubscrTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.SUBSCR_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.SIGNERS)
    @ApiOperation(value = "Provides the list of actual signers in system")
    public Response getSigners() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.EMPLOYEE_WITH_POS_SIGN))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.PAYMENT_RULES)
    @ApiOperation(value = "Provides the list of actual payment rules in system")
    public Response getPaymentRules() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.PAYMENT_RULE))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.AGENTS)
    @ApiOperation(value = "Provides the list of actual agents in system")
    public Response getAgents() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.EMPLOYEE))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.INVOICE_TYPES)
    @ApiOperation(value = "Provides the list of actual invoice types in system")
    public Response getInvoiceTypes(
            @QueryParam("docTypeId") Integer docTypeId,
            @QueryParam("activeTypeId") Integer activeTypeId,
            @QueryParam("activeSubtypeId") Integer activeSubtypeId,
            @QueryParam("counterpartyTypeId") Integer counterpartyTypeId) {
        return Response.ok(new RsResponse(contractsDictionaryService.getInvoiceType(docTypeId, activeTypeId, activeSubtypeId, counterpartyTypeId))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.BILL_DELIVERY_TYPES)
    @ApiOperation(value = "Provides a collection of a bill delivery types")
    public Response getBillDeliveryType() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.BILL_DELIV_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.PENALTY_TYPES)
    @ApiOperation(value = "Provides a collection of a penalty types")
    public Response getPenaltyTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.PENALTY_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.FINANCING_SOURCES)
    @ApiOperation(value = "Provides the list of actual financing sources in system")
    public Response getSubscrFundSource() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.SUBSCR_FUND_SOURCE))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.MEDIATORS)
    @ApiOperation(value = "Provides the list of actual mediator types in system")
    public Response getMediators(
            @QueryParam("id") Long id) {
        return Response.ok(new RsResponse(contractsDictionaryService.getMediators(id))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.ACCOUNT_AT_BROKER_BASE)
    @ApiOperation(value = "Provides the list of accounts at broker base in system")
    public Response getAccountAtMediatorBase(
            @QueryParam("id") Long id,
            @QueryParam("mediatorId") Integer mediatorId) {
        return Response.ok(new RsResponse(contractsDictionaryService.getAccountsAtBrokerBase(id, mediatorId))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.SIGN_ACT_TYPES)
    @ApiOperation(value = "Provides a collection of a sign act types")
    public Response getSignActType() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.SIGN_ACT_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.ATV_FILTER)
    @ApiOperation(value = "Provides a collection of a atv filters")
    public Response getAtvFilter() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.ATV_FILTER))).build();
    }


    /**
     * @author ADrozhzha
     */
    @GET
    @Path(RestApiEndpoints.Contract.BY_PERSONAL_ACCOUNT)
    @ApiOperation(value = "Provides key value contract by the personal account id")
    public Response getContractByPersonalAccountId(
            @ApiParam("The id of the personal account") @QueryParam("id") Long id) {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.CONTRACT_BY_ACC_ID, id))).build();
    }

    @GET
    @Path(RestApiEndpoints.Broker.MainData.CONTRACT_STATUSES)
    @ApiOperation(value = "Provides a collection of a contract status key (status id) values (status name)")
    public Response getContractStatuses() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.DOC_STATUS))).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.STATUS_BAN_EDITING)
    @ApiOperation(value = "Getting the list of contract status ids for ban of contract information editing / broker information editing")
    public Response getStatusBanEditing() {
        return Response.ok(new RsResponse(contractsDictionaryService.getStatusBanEditing())).build();
    }
}

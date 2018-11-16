package by.mtis.ui.rest.contract;

import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import by.mtis.dto.contract.options.ContractAdditionalAgreement;
import by.mtis.dto.contract.options.ContractBlocking;
import by.mtis.dto.contract.options.ContractChangesDetails;
import by.mtis.dto.contract.options.ContractChangesMetaInfo;
import by.mtis.dto.contract.options.ContractClosing;
import by.mtis.dto.contract.options.ContractRenewedAgreement;
import by.mtis.dto.contract.options.ContractUnlocking;
import by.mtis.dto.pagination.PaginatedList;
import by.mtis.service.contract.IContractOptionsService;
import by.mtis.ui.constant.ContentType;
import by.mtis.constants.RestApiEndpoints;
import by.mtis.ui.rest.configuration.convert.QueryParamDate;
import by.mtis.ui.rest.model.RsResponse;

import static by.mtis.constants.RestApiEndpoints.Contract.Options.CHANGE;

/**
 * The REST API implementation for working with options in {@link ContractRestService}. Each button of the options UI
 * corresponds to a certain set of methods for working with it.
 *
 * @author AleksandrovichK
 */

@Path(RestApiEndpoints.Contract.BASE + RestApiEndpoints.Contract.Options.BASE)
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Api(value = RestApiEndpoints.Contract.BASE + RestApiEndpoints.Contract.Options.BASE)
public class ContractOptionsRestService {
    @EJB
    private IContractOptionsService contractsOptionsService;

    @POST
    @Path(RestApiEndpoints.Contract.Options.BILLING_DATE)
    @ApiOperation(value = "Saves the billing date fot tariff plan.")
    public Response saveBillingDate(
            @QueryParam("id") Long id,
            @QueryParam("billingDate") @QueryParamDate Date date) {
        Long result = contractsOptionsService.saveBillingDate(id, date);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.Options.ADDITIONAL_AGREEMENT)
    @ApiOperation(value = "Saves the information about additional agreement by contract id.")
    public Response saveAdditionalAgreement(
            ContractAdditionalAgreement updateData) {
        Long result = contractsOptionsService.saveAdditionalAgreement(updateData);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.Options.RENEWED_AGREEMENT)
    @ApiOperation(value = "Saves the information about renewed agreement by contract id.")
    public Response saveRenewedAgreement(
            ContractRenewedAgreement updateData) {
        Long result = contractsOptionsService.saveRenewedAgreement(updateData);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.Options.BILLING_DATE + CHANGE)
    @ApiOperation(value = "Changes the billing date for contract.")
    public Response changeBillingDate(
            @QueryParam("id") Long id,
            @QueryParam("tariffPlanId") Long tpId,
            @QueryParam("billingDate") @QueryParamDate Date billingDate,
            @QueryParam("changingDate") @QueryParamDate Date changingDate,
            @QueryParam("comment") String comment) {
        Long result = contractsOptionsService.changeBillingDate(id, tpId, billingDate, changingDate, comment);
        return Response.ok(new RsResponse(result)).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.Options.CHANGES_LIST)
    @ApiOperation(value = "Provides with the list of history of contract changes.")
    public Response getContractChangesList(
            @QueryParam("id") Long id) {
        PaginatedList<ContractChangesMetaInfo> result = contractsOptionsService.getChangesList(id);
        return Response.ok(new RsResponse(result.getResult(), result.getPaginationData())).build();
    }

    @GET
    @Path(RestApiEndpoints.Contract.Options.CHANGES)
    @ApiOperation(value = "Provides with the list of history of contract changes.")
    public Response getCurrentContractChanges(
            @QueryParam("id") Long id) {
        ContractChangesDetails result = contractsOptionsService.getChangesDetails(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.Options.CLOSING)
    @ApiOperation(value = "Saves the information about renewed agreement by contract id.")
    public Response saveClosedContract(
            ContractClosing updateData) {
        Long result = contractsOptionsService.saveClosedContract(updateData);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.Options.BLOCKING)
    @ApiOperation(value = "Saves the information about blocked contract.")
    public Response saveBlockedContract(
            ContractBlocking updateData) {
        Long result = contractsOptionsService.saveBlockedContract(updateData);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.Options.UNLOCKING)
    @ApiOperation(value = "Saves the information about unlocked contract.")
    public Response saveUnlockedContract(
            ContractUnlocking updateData) {
        Long result = contractsOptionsService.saveUnlockedContract(updateData);
        return Response.ok(new RsResponse(result)).build();
    }
}
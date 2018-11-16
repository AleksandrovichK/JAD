package by.mtis.ui.rest.contract;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import by.mtis.constants.RestApiEndpoints;
import by.mtis.dto.common.KeyValueType;
import by.mtis.dto.common.PaginatedKeyValueType;
import by.mtis.dto.contract.Contract;
import by.mtis.dto.contract.auxiliary.ContractSearchFormData;
import by.mtis.dto.contract.auxiliary.ContractSearchTPData;
import by.mtis.dto.contract.auxiliary.ContractSummary;
import by.mtis.dto.contract.auxiliary.ContractTPConnection;
import by.mtis.dto.contract.auxiliary.ContractTariffPlanSearchData;
import by.mtis.dto.contract.options.ContractMacIp;
import by.mtis.dto.contract.options.ContractTechnicalParameters;
import by.mtis.dto.contract.partial.ContractAdditionalTP;
import by.mtis.dto.contract.partial.ContractCommon;
import by.mtis.dto.contract.partial.ContractEquipment;
import by.mtis.dto.contract.partial.ContractShippingList;
import by.mtis.dto.contract.partial.ContractTariffPlan;
import by.mtis.dto.db.Link;
import by.mtis.dto.pagination.PaginatedList;
import by.mtis.service.contract.IContractAdditionalTPService;
import by.mtis.service.contract.IContractCommonService;
import by.mtis.service.contract.IContractEquipmentService;
import by.mtis.service.contract.IContractSearchService;
import by.mtis.service.contract.IContractShippingService;
import by.mtis.service.contract.IContractTPService;
import by.mtis.service.contract.IContractTechnicalService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.rest.model.RsResponse;

/**
 * The REST API implementation for retrieving contract data. Contract card is identified by ID only.
 * Contract card consists of general part and some lists like connected {@link ContractTariffPlan}s and {@link ContractEquipment}.
 *
 * @author AleksandrovichK
 */
@Path(RestApiEndpoints.Contract.BASE)
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Api(value = RestApiEndpoints.Contract.BASE)
public class ContractRestService {

    @EJB
    private IContractSearchService searchService;

    @EJB
    private IContractCommonService commonService;

    @EJB
    private IContractShippingService shippingService;

    @EJB
    private IContractTPService mainTariffPlanService;

    @EJB
    private IContractAdditionalTPService additionalTariffPlanService;

    @EJB
    private IContractTechnicalService technicalService;

    @EJB
    private IContractEquipmentService equipmentService;

    @POST
    @Path(RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Searches contract by parameters and returns brief information.")
    public Response searchContract(
            @ApiParam(value = "Search data") ContractSearchFormData data) {
        PaginatedList<ContractSummary> result = searchService.getPaginatedList(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.CREATE)
    @ApiOperation(value = "Creates the new contract.")
    public Response createContract(
            @ApiParam(value = "Contract data") Contract data) {
        Long contractId = commonService.createContract(data);
        data.getTariffPlans().forEach(tariffPlan -> {
            tariffPlan.setContractId(contractId);
            mainTariffPlanService.connectTP(tariffPlan);
        });
        return Response.ok(new RsResponse(contractId)).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID)
    @ApiOperation(value = "Provides with a common information about contract by id.")
    public Response getContractCommonAndDetailsSection(
            @ApiParam(value = "An id of contract") @PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        ContractCommon result = commonService.getById(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Operation.SAVE)
    @ApiOperation(value = "Saves the common information about contract.")
    public Response saveContractCommonAndDetailsSection(
            @ApiParam(value = "Update data") ContractCommon updateData) {
        Long res = commonService.setObject(updateData);
        return Response.ok(new RsResponse(res)).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.Contract.PACK_OF_SHIPPING_LIST)
    @ApiOperation(value = "Provides with a list with current contract's shipping lists by CONTRACT id.")
    public Response getShippingList(
            @ApiParam(value = "An id of contract") @PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        List<KeyValueType> result = shippingService.getList(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.Contract.SHIPPING_LIST)
    @ApiOperation(value = "Provides with a information about current shipping list by SHIPPING LIST id.")
    public Response getCurrentShippingList(
            @ApiParam(value = "An id of needed current shipping list") @PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        ContractShippingList result = shippingService.getById(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.SHIPPING_LIST)
    @ApiOperation(value = "Provides with a information about current contract's shipping lists by id.")
    public Response saveShippingList(
            @ApiParam(value = "Update data") ContractShippingList updateDate) {
        Long result = shippingService.setObject(updateDate);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.MAIN_TARIFF_PLAN)
    @ApiOperation(value = "Provides with a information about current contract general tariff plan by id.")
    public Response getContractTPSection(
            @ApiParam(value = "Contract search data") ContractSearchTPData data) {
        PaginatedList<ContractTariffPlan> result = mainTariffPlanService.getPaginatedList(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.MAIN_TARIFF_PLAN + RestApiEndpoints.Contract.CONNECT)
    @ApiOperation(value = "Connects chosen tariff plan to contract.")
    public Response connectContractTP(
            @ApiParam(value = "Contract TP data") ContractTPConnection data) {
        Long id = mainTariffPlanService.connectTP(data);
        return Response.ok(new RsResponse(id)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.MAIN_TARIFF_PLAN + RestApiEndpoints.Contract.DISCONNECT + RestApiEndpoints.Operation.ENTRY_BY_ID)
    @ApiOperation(value = "Disconnects chosen tariff plan form the contract.")
    public Response disconnectContractTP(
            @ApiParam(value = "An id of needed current shipping list") @PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        mainTariffPlanService.dropById(id);
        return Response.ok(new RsResponse()).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.ADDITIONAL_TARIFF_PLAN)
    @ApiOperation(value = "Provides with a information about current contract's additional tariff plans by id.")
    public Response getContractAdditionalTPSection(
            @ApiParam(value = "Contract search data") ContractSearchTPData data) {
        PaginatedList<ContractAdditionalTP> result = additionalTariffPlanService.getPaginatedList(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.SEARCH_TARIFF_PLAN)
    @ApiOperation(value = "Searches tariff plan by parameters and returns brief information.")
    public Response searchTP(
            @ApiParam(value = "Search data") ContractTariffPlanSearchData data) {
        PaginatedList<PaginatedKeyValueType> result = mainTariffPlanService.searchTariffPlan(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////| MOCK services |//////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @POST
    @Path(RestApiEndpoints.Operation.LINK)
    @ApiOperation(value = "Makes a link between tariff plan and contract.")
    public Response linkTariffPlan(Link target) {
        mainTariffPlanService.link(target);
        return Response.ok(new RsResponse()).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.Contract.HISTORY_MAC_IP)
    @ApiOperation(value = "Provides with a information about date of MAC and IP changing.")
    public Response getMacIPHistory(
            @ApiParam(value = "An id of contract") @PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        PaginatedList<ContractMacIp> result = mainTariffPlanService.getMacIpHistory(id);
        return Response.ok(new RsResponse(result.getResult(), result.getPaginationData())).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.Contract.TECHNICAL_PARAMS)
    @ApiOperation(value = "Provides with the information about technical parameters by id.")
    public Response getTechnicalParameters(
            @ApiParam(value = "Tariff plan id (?)") @PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        ContractTechnicalParameters result = technicalService.getById(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Contract.TECHNICAL_PARAMS)
    @ApiOperation(value = "Save the information about technical parameters.")
    public Response saveTechnicalParameters(
            @ApiParam(value = "Update data") ContractTechnicalParameters updateData) {
        Long result = technicalService.setObject(updateData);
        return Response.ok(new RsResponse(result)).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.Contract.EQUIPMENT)
    @ApiOperation(value = "Provides with a information about current contract's equipment by id.")
    public Response getContractEquipment(
            @ApiParam(value = "An id of contract") @PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        ContractEquipment result = equipmentService.getById(id);
        return Response.ok(result).build();
    }
}

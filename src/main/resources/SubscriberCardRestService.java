package by.mtis.ui.rest.subscriber;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.sql.rowset.serial.SerialBlob;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import by.mtis.constants.RestApiEndpoints;
import by.mtis.dto.db.ObjectSearchDataById;
import by.mtis.dto.pagination.PaginatedList;
import by.mtis.dto.subscriber.Subscriber;
import by.mtis.dto.subscriber.SubscriberShortData;
import by.mtis.dto.subscriber.auxiliary.SubscriberContractSearchData;
import by.mtis.dto.subscriber.auxiliary.SubscriberHistoryResult;
import by.mtis.dto.subscriber.auxiliary.SubscriberHistorySearchData;
import by.mtis.dto.subscriber.auxiliary.SubscriberPhoto;
import by.mtis.dto.subscriber.auxiliary.SubscriberSearchAddressData;
import by.mtis.dto.subscriber.auxiliary.SubscriberSearchTPData;
import by.mtis.dto.subscriber.partial.SubscriberAddress;
import by.mtis.dto.subscriber.partial.SubscriberBlocking;
import by.mtis.dto.subscriber.partial.SubscriberBonus;
import by.mtis.dto.subscriber.partial.SubscriberContract;
import by.mtis.dto.subscriber.partial.SubscriberDiscount;
import by.mtis.dto.subscriber.partial.SubscriberEquipment;
import by.mtis.dto.subscriber.partial.SubscriberNotification;
import by.mtis.dto.subscriber.partial.SubscriberRecourse;
import by.mtis.dto.subscriber.partial.SubscriberTariffPlan;
import by.mtis.service.subscriber.ISubscriberAddressService;
import by.mtis.service.subscriber.ISubscriberBlockingService;
import by.mtis.service.subscriber.ISubscriberBonusService;
import by.mtis.service.subscriber.ISubscriberContractService;
import by.mtis.service.subscriber.ISubscriberDiscountService;
import by.mtis.service.subscriber.ISubscriberEquipmentService;
import by.mtis.service.subscriber.ISubscriberGeneralService;
import by.mtis.service.subscriber.ISubscriberHistoryService;
import by.mtis.service.subscriber.ISubscriberNotificationService;
import by.mtis.service.subscriber.ISubscriberRecourseService;
import by.mtis.service.subscriber.ISubscriberTariffPlanService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.rest.model.RsResponse;

/**
 * The REST API implementation for retrieving partial information about {@link Subscriber} by id. Subscriber is identified by ID only.
 * <br>
 * Subscriber card consists of general part and plenty of lists like {@link SubscriberNotification} and {@link SubscriberEquipment}.
 * You may create new subscriber (card) by invoking the {@link this#createSubscriber(Subscriber)}. In order to delete subscriber
 * use the {@link this#(Long)} method. Each section of the subscriber card corresponds to a certain set of methods for working
 * with it.
 *
 * @author AleksandrovichK
 * @author HryhoryeuM
 */

@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Path(RestApiEndpoints.SubscriberCard.BASE)
@Api(value = RestApiEndpoints.SubscriberCard.BASE)
public class SubscriberCardRestService {
    @EJB
    private ISubscriberBonusService bonusService;
    @EJB
    private ISubscriberGeneralService generalService;
    @EJB
    private ISubscriberAddressService addressService;
    @EJB
    private ISubscriberHistoryService historyService;
    @EJB
    private ISubscriberDiscountService discountService;
    @EJB
    private ISubscriberRecourseService recourseService;
    @EJB
    private ISubscriberContractService contractService;
    @EJB
    private ISubscriberBlockingService blockingService;
    @EJB
    private ISubscriberEquipmentService equipmentService;
    @EJB
    private ISubscriberTariffPlanService tariffPlanService;
    @EJB
    private ISubscriberNotificationService notificationService;

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID)
    @ApiOperation(value = "Provides with the general information about subscriber by id")
    public Response getGeneralInfo(
            @ApiParam(value = "An id of needed subscriber") @PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        Subscriber result = generalService.getById(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Operation.SAVE)
    @ApiOperation(value = "Saves the updated general information about subscriber by id")
    public Response saveGeneralInfo(
            @ApiParam(value = "The subscriber's info to save") Subscriber updateData) {
        Long id = generalService.setObject(updateData);
        return Response.ok(new RsResponse(id)).build();
    }

    @POST
    @ApiOperation(value = "Creates new subscriber, sends back new id")
    public Response createSubscriber(Subscriber newSubscriber) {
        Long id = generalService.createSubscriber(newSubscriber);
        return Response.ok(new RsResponse(id)).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.SubscriberCard.PHOTO)
    @Produces({ContentType.APPLICATION_JSON_UTF8, ContentType.IMAGE_JPEG})
    @ApiOperation(value = "Provides with the subscriber's photo by id")
    public Response getPhoto(
            @ApiParam(value = "An id of needed subscriber") @PathParam(RestApiEndpoints.PathParam.ID) Long id) throws SQLException, IOException {
        InputStream stream = generalService.getPhoto(id);
        if (stream == null ) {
            return Response.ok().build();
        }
        byte[] result = IOUtils.toByteArray(stream);
        return Response.ok(result).build();
    }

    @POST
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.SubscriberCard.PHOTO)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @ApiOperation(value = "Saves with the subscriber's photo by id")
    public Response savePhoto(
            @ApiParam(value = "An id of needed subscriber") @PathParam(RestApiEndpoints.PathParam.ID) Long id,
            InputStream photo) throws SQLException, IOException {
        SubscriberPhoto photoObj = new SubscriberPhoto(id, new SerialBlob(IOUtils.toByteArray(photo)));
        Long code = generalService.savePhoto(photoObj);
        return Response.ok(new RsResponse(code)).build();
    }

    @POST
    @Path(RestApiEndpoints.SubscriberCard.CONTRACT + RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Provides with the contract information about subscriber by id")
    public Response getContractSection(SubscriberContractSearchData searchData) {
        PaginatedList<SubscriberContract> result = contractService.getPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.SubscriberCard.NOTIFICATION + RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Provides with the notifications information about subscriber by id")
    public Response getNotificationSection(ObjectSearchDataById searchDataById) {
        PaginatedList<SubscriberNotification> result = notificationService.getPaginatedList(searchDataById);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.SubscriberCard.RECOURSE + RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Provides with the recourses information about subscriber by id")
    public Response getRecoursesSection(ObjectSearchDataById searchDataById) {
        PaginatedList<SubscriberRecourse> result = recourseService.getPaginatedList(searchDataById);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.SubscriberCard.BLOCKINGS + RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Provides with the blockings information about subscriber by id")
    public Response getBlockingsSection(ObjectSearchDataById searchDataById) {
        PaginatedList<SubscriberBlocking> result = blockingService.getPaginatedList(searchDataById);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.SubscriberCard.BONUS + RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Provides with the bonuses information about subscriber by id")
    public Response getBonusesSection(ObjectSearchDataById searchDataById) {
        PaginatedList<SubscriberBonus> result = bonusService.getPaginatedList(searchDataById);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.SubscriberCard.DISCOUNT + RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Provides with the discounts information about subscriber by id")
    public Response getDiscountsSection(ObjectSearchDataById searchDataById) {
        PaginatedList<SubscriberDiscount> result = discountService.getPaginatedList(searchDataById);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.SubscriberCard.EQUIPMENT + RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Provides with the equipment information about subscriber by id")
    public Response getEquipmentSection(ObjectSearchDataById searchDataById) {
        PaginatedList<SubscriberEquipment> result = equipmentService.getPaginatedList(searchDataById);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.SubscriberCard.ADDRESSES)
    @ApiOperation(value = "Provides with the address information about subscriber by id")
    public Response getAddressSection(
            @ApiParam(value = "An id of needed subscriber") @PathParam(RestApiEndpoints.PathParam.ID) Long id,
            @ApiParam(value = "Size of page") @QueryParam("pageSize") Integer pageSize,
            @ApiParam(value = "Number of page") @QueryParam("pageNumber") Integer pageNumber) {
        PaginatedList<SubscriberAddress> result = addressService.getPaginatedList(new SubscriberSearchAddressData(pageSize, pageNumber, id));
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID + RestApiEndpoints.SubscriberCard.TARIFF_PLAN)
    @ApiOperation(value = "Provides with the tariff plan information about subscriber by id")
    public Response getTariffPlanSection(
            @ApiParam(value = "An id of needed subscriber") @PathParam(RestApiEndpoints.PathParam.ID) Long id,
            @ApiParam(value = "Status of tariff plan") @QueryParam("isArchived") Integer archivedId,
            @ApiParam(value = "Size of page") @QueryParam("pageSize") Integer pageSize,
            @ApiParam(value = "Number of page") @QueryParam("pageNumber") Integer pageNumber) {
        PaginatedList<SubscriberTariffPlan> result = tariffPlanService.getPaginatedList(new SubscriberSearchTPData(pageSize, pageNumber, id, archivedId));
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////| Aleksandrovich's MOCK services start |////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @POST
    @Path(RestApiEndpoints.SubscriberCard.HISTORY)
    @ApiOperation(value = "Searches subscriber history by parameters and returns brief information.")
    public Response searchHistory(
            @ApiParam(value = "The searching materials") SubscriberHistorySearchData data) {
        PaginatedList<SubscriberHistoryResult> result = historyService.getPaginatedList(data);
        List<SubscriberHistoryResult> mocks = new ArrayList<>();
        mocks.add(new SubscriberHistoryResult(1997L, new Date(), 13, "testdf", "testasd", "test"));
        result.setResult(mocks);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////| Aleksandrovich's MOCK services end |//////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @POST
    @Path(RestApiEndpoints.SubscriberCard.SHORT_DATA + RestApiEndpoints.Operation.SAVE)
    @ApiOperation(value = "Creates new subscriber for call-center, sends back new id")
    public Response createSubscriberCallCenter(SubscriberShortData newSubscriber) {
        Long id = generalService.createSubscriberCallCenter(newSubscriber);
        return Response.ok(new RsResponse(id)).build();
    }

}

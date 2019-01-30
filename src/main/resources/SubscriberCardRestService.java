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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import by.mtis.dto.db.ObjectSearchDataById;
import by.mtis.dto.export.ExportModel;
import by.mtis.dto.pagination.PaginatedList;
import by.mtis.dto.subscriber.Subscriber;
import by.mtis.dto.subscriber.SubscriberShortData;
import by.mtis.dto.subscriber.auxiliary.SubscriberContractSearchData;
import by.mtis.dto.subscriber.auxiliary.SubscriberHistoryResult;
import by.mtis.dto.subscriber.auxiliary.SubscriberHistorySearchData;
import by.mtis.dto.subscriber.auxiliary.SubscriberPhoto;
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
import by.mtis.service.export.IListToFileConverter;
import by.mtis.service.subscriber.ISubscriberGeneralService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.rest.model.RsResponse;

/**
 * The REST API implementation for retrieving partial information about {@link Subscriber} by id. Subscriber is identified by ID only.
 * <br>
 * Subscriber card consists of general part and plenty of lists like {@link SubscriberNotification} and {@link SubscriberEquipment}.
 * You may create new subscriber (card) by invoking the {@link this#createSubscriber(Subscriber)}. In order to delete subscriber
 * use the {@link this#( Long )} method. Each section of the subscriber card corresponds to a certain set of methods for working
 * with it.
 *
 * @author AleksandrovichK
 * @author HryhoryeuM
 */
@Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.BASE)
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Api(value = by.mtis.constants.RestApiEndpoints.SubscriberCard.BASE)
public class SubscriberCardRestService {
    @EJB
    private IListToFileConverter listsConverter;
    @EJB
    private ISubscriberGeneralService generalService;

    @POST
    @ApiOperation(value = "Creates new subscriber, sends back new id")
    public Response createSubscriber(Subscriber newSubscriber) {
        Long id = generalService.createSubscriber(newSubscriber);
        return Response.ok(new RsResponse(id)).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.Operation.ENTRY_BY_ID)
    @ApiOperation(value = "Provides with the general information about subscriber by id")
    public Response getGeneralInfo(
            @PathParam(by.mtis.constants.RestApiEndpoints.PathParam.ID) Long id) {
        Subscriber result = generalService.getById(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.Operation.SAVE)
    @ApiOperation(value = "Saves the updated general information about subscriber by id")
    public Response saveGeneralInfo(Subscriber updateData) {
        Long id = generalService.saveSubscriber(updateData);
        return Response.ok(new RsResponse(id)).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.Operation.ENTRY_BY_ID + by.mtis.constants.RestApiEndpoints.SubscriberCard.PHOTO)
    @Produces({ ContentType.APPLICATION_JSON_UTF8, ContentType.IMAGE_JPEG })
    @ApiOperation(value = "Provides with the subscriber's photo by id")
    public Response getPhoto(
            @PathParam(by.mtis.constants.RestApiEndpoints.PathParam.ID) Long id) throws SQLException, IOException {
        InputStream stream = generalService.getPhoto(id);
        if (stream == null) {
            return Response.ok().build();
        }
        byte[] result = IOUtils.toByteArray(stream);
        return Response.ok(result).build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.Operation.ENTRY_BY_ID + by.mtis.constants.RestApiEndpoints.SubscriberCard.PHOTO)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @ApiOperation(value = "Saves with the subscriber's photo by id")
    public Response savePhoto(
            @PathParam(by.mtis.constants.RestApiEndpoints.PathParam.ID) Long id,
            InputStream photo) throws SQLException, IOException {
        SubscriberPhoto photoObj = new SubscriberPhoto(id, new SerialBlob(IOUtils.toByteArray(photo)));
        Long code = generalService.savePhoto(photoObj);
        return Response.ok(new RsResponse(code)).build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.ADDRESSES)
    @ApiOperation(value = "Provides with the address information about subscriber by id")
    public Response getAddressSection(ObjectSearchDataById searchData) {
        PaginatedList<SubscriberAddress> result = generalService.getAddressPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.ADDRESSES + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    public Response getAddressSectionReportExcel(ExportModel<ObjectSearchDataById> exportModel) {
        PaginatedList<SubscriberAddress> paginatedList = generalService.getAddressPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.CONTRACT)
    @ApiOperation(value = "Provides with the contract information about subscriber by id")
    public Response getContractSection(SubscriberContractSearchData searchData) {
        PaginatedList<SubscriberContract> result = generalService.getContractPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.CONTRACT + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    public Response getContractSectionReportExcel(ExportModel<SubscriberContractSearchData> exportModel) {
        PaginatedList<SubscriberContract> paginatedList = generalService.getContractPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.NOTIFICATION)
    @ApiOperation(value = "Provides with the notifications information about subscriber by id")
    public Response getNotificationSection(ObjectSearchDataById searchData) {
        PaginatedList<SubscriberNotification> result = generalService.getNotificationPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.NOTIFICATION + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    public Response getNotificationSectionReportExcel(ExportModel<ObjectSearchDataById> exportModel) {
        PaginatedList<SubscriberNotification> paginatedList = generalService.getNotificationPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.RECOURSE)
    @ApiOperation(value = "Provides with the recourses information about subscriber by id")
    public Response getRecourseSection(ObjectSearchDataById searchData) {
        PaginatedList<SubscriberRecourse> result = generalService.getRecoursesPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.RECOURSE + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    public Response getRecoursesSectionReportExcel(ExportModel<ObjectSearchDataById> exportModel) {
        PaginatedList<SubscriberRecourse> paginatedList = generalService.getRecoursesPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.BLOCKINGS)
    @ApiOperation(value = "Provides with the blockings information about subscriber by id")
    public Response getBlockingsSection(ObjectSearchDataById searchData) {
        PaginatedList<SubscriberBlocking> result = generalService.getBlockingsPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.BLOCKINGS + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    public Response getBlockingsSectionReportExcel(ExportModel<ObjectSearchDataById> exportModel) {
        PaginatedList<SubscriberBlocking> paginatedList = generalService.getBlockingsPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.BONUS)
    @ApiOperation(value = "Provides with the bonuses information about subscriber by id")
    public Response getBonusSection(ObjectSearchDataById searchData) {
        PaginatedList<SubscriberBonus> result = generalService.getBonusPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.BONUS + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    public Response getBonusSectionReportExcel(ExportModel<ObjectSearchDataById> exportModel) {
        PaginatedList<SubscriberBonus> paginatedList = generalService.getBonusPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.DISCOUNT)
    @ApiOperation(value = "Provides with the discounts information about subscriber by id")
    public Response getDiscountsSection(ObjectSearchDataById searchData) {
        PaginatedList<SubscriberDiscount> result = generalService.getDiscountPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.DISCOUNT + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    public Response getDiscountsSectionReportExcel(ExportModel<ObjectSearchDataById> exportModel) {
        PaginatedList<SubscriberDiscount> paginatedList = generalService.getDiscountPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.EQUIPMENT)
    @ApiOperation(value = "Provides with the equipment information about subscriber by id")
    public Response getEquipmentSection(ObjectSearchDataById searchData) {
        PaginatedList<SubscriberEquipment> result = generalService.getEquipmentPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.EQUIPMENT + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    public Response getEquipmentSectionReportExcel(ExportModel<ObjectSearchDataById> exportModel) {
        PaginatedList<SubscriberEquipment> paginatedList = generalService.getEquipmentPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.TARIFF_PLAN)
    @ApiOperation(value = "Provides with the tariff plan information about subscriber by id")
    public Response getTariffPlanSection(SubscriberSearchTPData searchData) {
        PaginatedList<SubscriberTariffPlan> result = generalService.getTariffPlanPaginatedList(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.TARIFF_PLAN + by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Retrieves an formed excel report by section-containing information")
    public Response getTariffPlanSectionReportExcel(ExportModel<SubscriberSearchTPData> exportModel) {
        PaginatedList<SubscriberTariffPlan> paginatedList = generalService.getTariffPlanPaginatedList(exportModel.getFilter());
        Response.ResponseBuilder response = Response.ok(listsConverter.getReportExcel(paginatedList.getResult(), exportModel.getFieldNames()));
        return response.build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.SHORT_DATA + by.mtis.constants.RestApiEndpoints.Operation.SAVE)
    @ApiOperation(value = "Creates new subscriber for call-center, sends back new id")
    public Response createSubscriberCallCenter(SubscriberShortData newSubscriber) {
        Long id = generalService.createSubscriberCallCenter(newSubscriber);
        return Response.ok(new RsResponse(id)).build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////| Aleksandrovich's MOCK services |////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.HISTORY)
    @ApiOperation(value = "Searches subscriber history by parameters and returns brief information.")
    public Response searchHistory(SubscriberHistorySearchData data) {
        PaginatedList<SubscriberHistoryResult> result = generalService.getHistoryPaginatedList(data);
        List<SubscriberHistoryResult> mocks = new ArrayList<>();
        mocks.add(new SubscriberHistoryResult(1997L, new Date(), 13, "testdf", "testasd", "test"));
        result.setResult(mocks);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

}

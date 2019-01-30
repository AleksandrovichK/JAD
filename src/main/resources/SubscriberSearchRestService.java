package by.mtis.ui.rest.subscriber;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import by.mtis.dto.CollectionData;
import by.mtis.dto.common.KeyValueType;
import by.mtis.dto.db.subscriber.advancedsearch.SubscriberAdvancedSearchField;
import by.mtis.dto.db.subscriber.advancedsearch.SubscriberAdvancedSearchRequest;
import by.mtis.dto.export.ExportModel;
import by.mtis.dto.pagination.PaginatedList;
import by.mtis.dto.subscriber.Subscriber;
import by.mtis.dto.subscriber.auxiliary.SubscriberCallCenterData;
import by.mtis.dto.subscriber.auxiliary.SubscriberCallCenterSearch;
import by.mtis.dto.subscriber.auxiliary.SubscriberSearchFormData;
import by.mtis.dto.subscriber.auxiliary.SubscriberSearchShortData;
import by.mtis.dto.subscriber.auxiliary.SubscriberShortDate;
import by.mtis.dto.subscriber.auxiliary.SubscriberSummary;
import by.mtis.dto.subscriber.partial.SubscriberTariffPlanCallCenter;
import by.mtis.service.export.IListToFileConverter;
import by.mtis.service.subscriber.ISubscriberSearchService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.constant.Roles;
import by.mtis.ui.rest.model.RsResponse;

/**
 * The REST API implementation for searching {@link Subscriber} by known info,
 * or retrieving short information.
 *
 * @author AleksandrovichK
 */

@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.BASE)
@Api(value = by.mtis.constants.RestApiEndpoints.SubscriberCard.BASE)
public class SubscriberSearchRestService {
    @EJB
    private IListToFileConverter listToFileConverter;
    @EJB
    private ISubscriberSearchService subscriberSearchService;

    @RolesAllowed(Roles.SUBSCRIBER_SEARCH)
    @POST
    @Path(by.mtis.constants.RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Searches subscriber by parameters and returns brief information")
    public Response findSubscriber(
            @ApiParam(value = "The searching materials") SubscriberSearchFormData data) {
        PaginatedList<SubscriberSummary> result = subscriberSearchService.getPaginatedList(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.ADVANCED_SEARCH)
    @ApiOperation(value = "Searches subscriber by the multiset of parameters and returns brief information")
    public Response advancedFindSubscriber(
            @ApiParam(value = "The searching materials") final SubscriberAdvancedSearchRequest data) throws JAXBException, SQLException, IOException {
        PaginatedList<SubscriberSummary> result = subscriberSearchService.getAdvancedSearchList(data);
        List<SubscriberSummary> mockList = new ArrayList<>();
        if ((int) (Math.random() * 10) < 5){
        mockList.add(new SubscriberSummary(1997L, "testASFsdfdc7370", "test36bdASDG8d", "testd3be57", "test20c585", "test5eed33", "testb984c2", 4L));
        mockList.add(new SubscriberSummary(1998L, "tessdDGSGftdc7370", "tesSDGGt36bd8d", "testd3be57", "test20c585", "test5eed33", "testb984c2", 4L));
        mockList.add(new SubscriberSummary(1999L, "tesSFGSDtduyr c7370", "testSDGG36bd8d", "testd3be57", "test20c585", "test5eed33", "testb984c2", 4L));
        mockList.add(new SubscriberSummary(2000L, "teZystdc7370", "testSDG36bd8d", "testd3be57", "test20c585", "test5eed33", "testb984c2", 4L));
        }
        else {
            mockList.add(new SubscriberSummary(7991L, "SDFHtestASFsdfdc7370", "tERIYTUJSDG8d", "BMBVMN XCN", "RRTUYTI", "test5eed33", "testb984c2", 4L));
            mockList.add(new SubscriberSummary(7992L, "SDFteDSFHssdDGSGftdc7370", "teDFH8d", "WERTWET", "SAWEDFH", "test5eed33", "testb984c2", 4L));
            mockList.add(new SubscriberSummary(7993L, "BteSDFGsSFGSDtduyr c7370", "ERTJHURJG36bd8d", "IYUOY", "test20c585", "test5eed33", "testb984c2", 4L));
            mockList.add(new SubscriberSummary(7994L, "CtIOeZystdc7370", "teYTRJYTJbd8d", "FGJDF", "test20c585", "test5eed33", "testb984c2", 4L));
            mockList.add(new SubscriberSummary(7995L, "XtUILeZystdc7370", "tTRYJRTYJ8d", "GJGFJ", "test20c585", "test5eed33", "testb984c2", 4L));
            mockList.add(new SubscriberSummary(7996L, "BtEWRYeZystdc7370", "teRYJRYG36bd8d", "DFGJ", "test20c585", "test5eed33", "testb984c2", 4L));
            mockList.add(new SubscriberSummary(7997L, "MteIUPZystdc7370", "teEbd8d", "GDFGH", "test20c585", "test5eed33", "testb984c2", 4L));
        }
        result.setResult(mockList);
        result.setCollectionData(new CollectionData(4L));

        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.ADVANCED_SEARCH + by.mtis.constants.RestApiEndpoints.SubscriberCard.FIELDS)
    @ApiOperation(value = "Retrieves search fields for advanced search")
    public Response getAdvancedSearchFields() {
        List<SubscriberAdvancedSearchField> result = new ArrayList<>(); // subscriberSearchService.getAdvancedSearchFields();
        result.add(new SubscriberAdvancedSearchField("subscr.District", "TEXT"));
        result.add(new SubscriberAdvancedSearchField("subscr.BirthDate", "DATE"));
        result.add(new SubscriberAdvancedSearchField("subscr.PassportData", "NUMBER"));
        result.add(new SubscriberAdvancedSearchField("subscr.Address", "ADDRESS"));
        return Response.ok(new RsResponse(result)).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.ADVANCED_SEARCH + by.mtis.constants.RestApiEndpoints.SubscriberCard.CONDITIONS)
    @ApiOperation(value = "Retrieves search conditions for advanced search")
    public Response getAdvancedSearchConditions(@QueryParam("type") String fieldType) {
        List<KeyValueType> result = new ArrayList<>();// subscriberSearchService.getAdvancedSearchConditions(fieldType);

        if (fieldType != null) {
            System.out.println("Field type:" + fieldType);

            switch (fieldType) {
                case "subscr.District": {
                    result.add(new KeyValueType(1L, "Текст1"));
                    result.add(new KeyValueType(2L, "Текст2"));
                    result.add(new KeyValueType(3L, "Текст3"));
                    break;
                }
                case "subscr.PassportData": {
                    result.add(new KeyValueType(4L, "Числовой1"));
                    result.add(new KeyValueType(5L, "Числовой2"));
                    result.add(new KeyValueType(6L, "Числовой3"));
                    break;
                }
                case "subscr.BirthDate": {
                    result.add(new KeyValueType(7L, "Дата1"));
                    result.add(new KeyValueType(8L, "Дата2"));
                    break;
                }
                case "subscr.Address": {
                    result.add(new KeyValueType(9L, "Адрес1"));
                    result.add(new KeyValueType(10L, "Адрес2"));
                    result.add(new KeyValueType(11L, "Адрес3"));
                    result.add(new KeyValueType(12L, "Адрес4"));
                    result.add(new KeyValueType(13L, "Адрес5"));
                    break;
                }
            }
        }

        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(by.mtis.constants.RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Returns an excel report")
    public Response getReportExcel(ExportModel<SubscriberSearchFormData> exportModel) {
        PaginatedList<SubscriberSummary> exportReport = subscriberSearchService.getPaginatedList(exportModel.getFilter());
        return Response.ok(listToFileConverter
                .getReportExcel(exportReport.getResult(), exportModel.getFieldNames()))
                .build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.SHORT_DATA)
    @ApiOperation(value = "Searches subscriber by parameters and returns short list of information")
    public Response getShortList(
            @ApiParam(value = "The searching subscribers") SubscriberSearchShortData data) {
        PaginatedList<SubscriberShortDate> result = subscriberSearchService.getShortDate(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.TARIFF_PLAN + by.mtis.constants.RestApiEndpoints.Operation.ENTRY_BY_ID)
    @ApiOperation(value = "Searches subscribers list of tariff plans by subscriber id and returns list of information")
    public Response getSubscrTPById(
            @ApiParam(value = "The searching subscribers") @PathParam(by.mtis.constants.RestApiEndpoints.PathParam.ID) Long subscriberId) {
        List<SubscriberTariffPlanCallCenter> result = subscriberSearchService.getTPBySubscrId(subscriberId);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.CALL_CENTER_DATA)
    @ApiOperation(value = "Searches subscriber by parameters and returns list of information for call canter")
    public Response getListForCallCenter(
            @ApiParam(value = "The searching subscribers") SubscriberCallCenterSearch searchData) {
        PaginatedList<SubscriberCallCenterData> result = subscriberSearchService.getSubscriberCallCenterData(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.GET_ID_BY_PHONE)
    @ApiOperation(value = "Get subscriber id by his phone number")
    public Response getIdByNumber(@ApiParam(value = "The searched phone") @QueryParam("phone") String data) {
        List<Long> result = subscriberSearchService.getIdByPhone(data);
        result.add(1941L);
        result.add(1945L);
        return Response.ok(new RsResponse(result)).build();
    }
}

package by.mtis.ui.rest.subscriber;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

import by.mtis.constants.RestApiEndpoints;
import by.mtis.dto.common.CatalogType;
import by.mtis.dto.db.common.CatalogSearchData;
import by.mtis.dto.export.ExportModel;
import by.mtis.dto.pagination.PaginatedList;
import by.mtis.dto.subscriber.Subscriber;
import by.mtis.dto.subscriber.auxiliary.SubscriberCallCenterData;
import by.mtis.dto.subscriber.auxiliary.SubscriberCallCenterSearch;
import by.mtis.dto.subscriber.auxiliary.SubscriberExtendedSearchRequest;
import by.mtis.dto.subscriber.auxiliary.SubscriberSearchFormData;
import by.mtis.dto.subscriber.auxiliary.SubscriberSearchShortData;
import by.mtis.dto.subscriber.auxiliary.SubscriberShortDate;
import by.mtis.dto.subscriber.auxiliary.SubscriberSummary;
import by.mtis.dto.subscriber.partial.SubscriberTariffPlanCallCenter;
import by.mtis.service.export.IListToFileConverter;
import by.mtis.service.subscriber.ISubscriberSearchService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.rest.model.RsResponse;

import static by.mtis.constants.Entity.DISTRICT;

/**
 * The REST API implementation for searching {@link Subscriber} by known info,
 * or retrieving short information.
 *
 * @author AleksandrovichK
 */

@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Path(RestApiEndpoints.SubscriberCard.BASE)
@Api(value = RestApiEndpoints.SubscriberCard.BASE)
public class SubscriberSearchRestService {

    @EJB
    private ISubscriberSearchService subscriberSearchService;

    @EJB
    private IListToFileConverter listToFileConverter;

    @POST
    @Path(RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Searches subscriber by parameters and returns brief information.")
    public Response findSubscriber(
            @ApiParam(value = "The searching materials") SubscriberSearchFormData data) {
        PaginatedList<SubscriberSummary> result = subscriberSearchService.getPaginatedList(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.Operation.EXTENDED_SEARCH)
    @ApiOperation(value = "Searches subscriber by the multiset of parameters and returns brief information.")
    public Response extendedFindSubscriber(
            @ApiParam(value = "The searching materials") final SubscriberExtendedSearchRequest data) throws JAXBException, SQLException, IOException {
        PaginatedList<SubscriberSummary> result = subscriberSearchService.getExtendedSearchList(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @POST
    @Produces(ContentType.APPLICATION_OCTET)
    @Path(RestApiEndpoints.Operation.GET_REPORT)
    @ApiOperation(value = "Returns an excel report")
    public Response getReportExcel(ExportModel<SubscriberSearchFormData> exportModel) {
        PaginatedList<SubscriberSummary> exportReport = subscriberSearchService.getPaginatedList(exportModel.getFilter());
        return Response.ok(listToFileConverter
                .getReportExcel(exportReport.getResult(), exportModel.getFieldNames()))
                .build();
    }
    @POST
    @Path(RestApiEndpoints.SubscriberCard.SHORT_DATA)
    @ApiOperation(value = "Searches subscriber by parameters and returns short list of information.")
    public Response getShortList(
            @ApiParam(value = "The searching subscribers") SubscriberSearchShortData data) {
        PaginatedList<SubscriberShortDate> result = subscriberSearchService.getShortDate(data);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @GET
    @Path(RestApiEndpoints.SubscriberCard.TARIFF_PLAN + RestApiEndpoints.Operation.ENTRY_BY_ID)
    @ApiOperation(value = "Searches subscribers list of tariff plans by subscriber id and returns list of information.")
    public Response getSubscrTPById(
            @ApiParam(value = "The searching subscribers") @PathParam("id") Long subscriberId) {
        List<SubscriberTariffPlanCallCenter> result = subscriberSearchService.getTPBySubscrId(subscriberId);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.SubscriberCard.CALL_CENTER_DATA)
    @ApiOperation(value = "Searches subscriber by parameters and returns list of information for call canter.")
    public Response getListForCallCenter(
            @ApiParam(value = "The searching subscribers") SubscriberCallCenterSearch searchData) {
        PaginatedList<SubscriberCallCenterData> result = subscriberSearchService.getSubscriberCallCenterData(searchData);
        return Response.ok(new RsResponse(result.getResult(), result.getCollectionData())).build();
    }

    @GET
    @Path(RestApiEndpoints.SubscriberCard.GET_ID_BY_PHONE)
    @ApiOperation(value = "Get subscriber id by his phone number.")
    public Response getIdByNumber(@ApiParam(value = "The searched phone") @QueryParam("phone") String data) {
        List<Long> result = subscriberSearchService.getIdByPhone(data);
        result.add(1941L);
        result.add(1945L);
        return Response.ok(new RsResponse(result)).build();
    }
}

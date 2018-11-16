package by.mtis.ui.rest.tariffplan;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import by.mtis.dto.pagination.PaginatedList;
import by.mtis.dto.tariffplan.TariffPlan;
import by.mtis.dto.tariffplan.auxiliary.TariffPlanSearchData;
import by.mtis.dto.tariffplan.TariffPlanTvChannel;
import by.mtis.service.tariffplan.ITariffPlanService;
import by.mtis.service.tariffplan.ITariffPlanTvChannelService;
import by.mtis.ui.constant.ContentType;
import by.mtis.constants.RestApiEndpoints;
import by.mtis.ui.rest.model.RsResponse;

/**
 * The REST API implementation for tariff plans functionality.
 *
 * @author NovashA
 * @author AleksandrovichK
 */
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Path(RestApiEndpoints.TariffPlan.BASE)
@Api(value = RestApiEndpoints.TariffPlan.BASE)
public class TariffPlanRestService {

    @EJB
    private ITariffPlanService tariffPlanService;

    @EJB
    private ITariffPlanTvChannelService tariffPlanTvChannelService;

    @POST
    @Path(RestApiEndpoints.Operation.SEARCH)
    @ApiOperation(value = "Provides the list of tariff plans satisfy given parameters")
    public Response search(TariffPlanSearchData tariffPlanSearchData) {
        PaginatedList<TariffPlan> tariffPlans = tariffPlanService.getPaginatedList(tariffPlanSearchData);
        return Response.ok(new RsResponse(tariffPlans.getResult(), tariffPlans.getCollectionData())).build();
    }

    @POST
    @Path(RestApiEndpoints.Operation.SAVE)
    @ApiOperation(value = "Saves tariff plan to DB")
    public Response save(@ApiParam(value = "Tariff plan") TariffPlan tariffPlan) {
        Long tpVerId = tariffPlanService.setObject(tariffPlan);
        return Response.ok(new RsResponse(tpVerId)).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID)
    @ApiOperation(value = "Provides the tariff plan by id")
    public Response get(@PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        TariffPlan result = tariffPlanService.getById(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.TV_CHANNELS)
    @ApiOperation(value = "Provides the list of TV channels for tariff plan")
    public Response getTvChannels(
            @QueryParam("tpVerId") Long tpVerId,
            @QueryParam("tvPackageId") Byte tvPackageId,
            @QueryParam("linked") Boolean linked) {
        List<TariffPlanTvChannel> tvChannels = tariffPlanTvChannelService.getList(tvPackageId, linked, tpVerId);
        return Response.ok(new RsResponse(tvChannels)).build();
    }

}

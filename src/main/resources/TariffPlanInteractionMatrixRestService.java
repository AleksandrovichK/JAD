package by.mtis.ui.rest.tariffplan;

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

import by.mtis.constants.RestApiEndpoints;
import by.mtis.dto.db.ObjectSearchData;
import by.mtis.dto.db.common.CatalogSearchData;
import by.mtis.dto.db.tariffplan.Matrix;
import by.mtis.dto.db.tariffplan.MatrixFilter;
import by.mtis.dto.db.tariffplan.MatrixHistory;
import by.mtis.dto.db.tariffplan.MatrixInfo;
import by.mtis.service.tariffplan.IInteractionMatrixService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.rest.model.RsResponse;

/**
 * @author AleksandrovichK
 */
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Path(RestApiEndpoints.TariffPlan.BASE + RestApiEndpoints.TariffPlan.InteractionMatrix.BASE)
@Api(value = RestApiEndpoints.TariffPlan.BASE + RestApiEndpoints.TariffPlan.InteractionMatrix.BASE)
public class TariffPlanInteractionMatrixRestService {
    @EJB
    private IInteractionMatrixService matrixService;

    @POST
    @ApiOperation(value = "Retrieves TP interaction matrix")
    public Response get(MatrixFilter filter) {
        Matrix result = matrixService.getMatrix(filter);
        return Response.ok(new RsResponse(result)).build();
    }

    @POST
    @Path(RestApiEndpoints.Operation.SAVE)
    @ApiOperation(value = "Saves tariff plan interaction matrix")
    public Response save(Matrix matrix) {
        matrixService.saveMatrix(matrix);
        return Response.ok(new RsResponse()).build();
    }

    @GET
    @Path(RestApiEndpoints.Operation.ENTRY_BY_ID)
    @ApiOperation(value = "Get tariff plan interaction matrix metainfo")
    public Response getMetaInfo(@PathParam(RestApiEndpoints.PathParam.ID) Long id) {
        MatrixInfo result = matrixService.getMetaInfo(id);
        return Response.ok(new RsResponse(result)).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.InteractionMatrix.VERSIONS)
    @ApiOperation(value = "Retrieves versions of interaction matrix")
    public Response getVersions(ObjectSearchData searchData) {
        MatrixHistory result = matrixService.getVersions(searchData);
        return Response.ok(new RsResponse(result)).build();
    }
}

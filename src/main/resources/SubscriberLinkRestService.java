package by.mtis.ui.rest.subscriber;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import by.mtis.dto.subscriber.auxiliary.LinkSubscrForm;
import by.mtis.service.subscriber.ISubscriberLinkService;
import by.mtis.ui.constant.ContentType;
import by.mtis.constants.RestApiEndpoints;
import by.mtis.ui.rest.model.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The REST service that links and unlinks subscribers with subscriber groups.
 *
 * @author LanetsE
 */
@Path(RestApiEndpoints.SubscriberCard.BASE)
@Api(value = RestApiEndpoints.SubscriberCard.BASE, description = "APIs for linking and unlinking subscribers with subscriber groups.")
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
public class SubscriberLinkRestService {

    @EJB
    private ISubscriberLinkService linkService;

    @POST
    @Path(RestApiEndpoints.Operation.LINK)
    @ApiOperation(value = "Linking and unlinking subscribers with subscriber groups.")
    public Response linkSubscribers(LinkSubscrForm form) {
        linkService.linkSubscrList(form);
        return Response.ok(new RsResponse()).build();
    }
}

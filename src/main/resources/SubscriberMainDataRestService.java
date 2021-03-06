package by.mtis.ui.rest.subscriber;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import by.mtis.constants.Entity;
import by.mtis.service.common.IKeyValDictService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.constant.Roles;
import by.mtis.ui.rest.model.RsResponse;

/**
 * The REST API implementation for retrieving catalogs information
 * about subscribers by id.
 *
 * @author AleksandrovichK
 */
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Path(by.mtis.constants.RestApiEndpoints.MainData.BASE + by.mtis.constants.RestApiEndpoints.SubscriberCard.BASE)
@Api(value = by.mtis.constants.RestApiEndpoints.MainData.BASE + by.mtis.constants.RestApiEndpoints.SubscriberCard.BASE)
public class SubscriberMainDataRestService {
    @EJB
    private IKeyValDictService dictionary;

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.SEXES)
    @ApiOperation(value = "Provides the list of actual sex types in system")
    public Response getSex() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.GENDER))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.LANGUAGES)
    @ApiOperation(value = "Provides the list of actual languages using in the system")
    public Response getLang() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.LANGUAGE))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.COUNTRIES)
    @ApiOperation(value = "Provides the list of actual countries using in the system")
    public Response getCountry() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.COUNTRY))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.DOCUMENT_TYPES)
    @ApiOperation(value = "Provides the list of actual document types using in the system")
    public Response getDocument() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.PERS_DOC_KIND))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.RESIDENTS)
    @ApiOperation(value = "Provides the list of actual resident variants in system")
    public Response getResident() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.RESIDENCE_TYPE))).build();
    }

    @RolesAllowed(Roles.SUBSCRIBER_TYPE_GET_KEY_VALUE)
    /** @deprecated migrate to KeyValueRestService and it is duplicated to {@link by.mtis.ui.rest.maindata.currencyrate.ContractMainDataRestService#getSubscrTypes()}*/
    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.SUBSCR_TYPES)
    @ApiOperation(value = "Provides the list of actual subscriber types in the system")
    public Response getSubscrTypes() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.SUBSCR_TYPE))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.SUBSCR_STATUS)
    @ApiOperation(value = "Provides the list of actual subscriber statuses in the system")
    public Response getSubsrStatus() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.SUBSCR_STATUS))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.DEPARTMENTS)
    @ApiOperation(value = "Provides the list of actual departments using in the current system")
    public Response getDepartments() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.REQ_DEPARTMENT))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.SUBSCR_CATEGORIES)
    @ApiOperation(value = "Provides the list of actual subscriber categories using in the system")
    public Response getCategories() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.SUBSCR_CATEGORY))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.MIGRATION_BASES)
    @ApiOperation(value = "Provides the list of actual migration bases using in the system")
    public Response getMigrationBase() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.MIGRATION_BASE))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.SUBSCR_GROUPS)
    @ApiOperation(value = "Provides the list of actual subscriber groups using in the current system")
    public Response getSubscriberGroups() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.SUBSCR_GROUP))).build();
    }

    @RolesAllowed(Roles.SUBSCRIBER_SUBGROUP_GET_KEY_VALUE)
    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.SUBGROUP_TYPES)
    @ApiOperation(value = "Provides the list of actual subscr subgroup types in system")
    public Response getSubgroupTypes() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.SUBSCR_SUBGROUP))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.BLOCKING_STATUSES)
    @ApiOperation(value = "Provides the list of actual blocking statuses using in the current system")
    public Response getBlockingStatuses() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.LOCKING_STATUS))).build();
    }

    @GET
    @Path(by.mtis.constants.RestApiEndpoints.SubscriberCard.MAIL_DELIVERY_TYPES)
    @ApiOperation(value = "Provides the list of actual mail delivery types using in the current system")
    public Response getMailDeliveryTypes() {
        return Response.ok(new RsResponse(dictionary.getKeyValues(Entity.CORR_DELIV_TYPE))).build();
    }

}

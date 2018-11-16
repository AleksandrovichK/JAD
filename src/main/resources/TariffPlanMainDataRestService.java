package by.mtis.ui.rest.tariffplan;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import by.mtis.constants.Entity;
import by.mtis.constants.RestApiEndpoints;
import by.mtis.dto.common.KeyValueType;
import by.mtis.dto.db.DbCondition;
import by.mtis.service.common.IKeyValDictService;
import by.mtis.service.tariffplan.ITariffPlanService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.rest.model.RsResponse;

/**
 * The REST API implementation for retrieving common application data
 * for tariff plans.
 *
 * @author NovashA
 * @author AleksandrovichK
 */
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Produces(ContentType.APPLICATION_JSON_UTF8)
@Path(RestApiEndpoints.MainData.BASE + RestApiEndpoints.TariffPlan.BASE)
@Api(value = RestApiEndpoints.MainData.BASE + RestApiEndpoints.TariffPlan.BASE)
public class TariffPlanMainDataRestService {
    @EJB
    private IKeyValDictService keyValDictSv;

    @EJB
    private ITariffPlanService tariffPlanService;

    @GET
    @Path(RestApiEndpoints.Operation.FIND_KEY_VALUE)
    @ApiOperation(value = "Retrieves the list of all tariff plans by activity type id and activity subtype id")
    public Response getTariffPlansList(
            @QueryParam("activTypeId") Short activTypeId,
            @QueryParam("activSubTypeId") Short activSubTypeId){
        return Response.ok(new RsResponse(tariffPlanService.getKeyValue(activTypeId, activSubTypeId))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.ACTIVITY_TYPES)
    @ApiOperation(value = "Retrieves the list of all available activity types")
    public Response getActivityTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.ACTIV_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.ACTIVITY_SUB_TYPES)
    @ApiOperation(value = "Retrieves the list of activity subtypes of selected activity type defined by its ID")
    public Response getActivitySubTypes(
            @QueryParam("activityTypeId") Long activityTypeId) {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.ACTIV_SUB_TYPE, activityTypeId))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.BASE_UNITS)
    @ApiOperation(value = "Retrieves the list of actual base units of tariff plans in system")
    public Response getBaseUnits(
            @QueryParam("activityTypeId") Long activityTypeId,
            @QueryParam("tarifficationTypeId") Long tarifficationTypeId) {
        List<DbCondition> conditions = new LinkedList<>();
        conditions.add(DbCondition.getBuilder().setDictionaryName(Entity.ACTIV_TYPE).setId(activityTypeId).build());
        if (tarifficationTypeId != null) {
            conditions.add(DbCondition.getBuilder().setDictionaryName(Entity.INET_TARIFFICATION_TYPE).setId(tarifficationTypeId).build());
        }
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.BASE_UNIT, conditions))).build();
    }

    @GET
    @Path(RestApiEndpoints.MainData.TRAFFIC_RECALC_RULE)
    @ApiOperation(value = "Retrieves the list of actual traffic recalculation rules in system")
    public Response getCalculationRules() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TRAFFIC_RECALC_RULE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.CONNECTION_TYPES)
    @ApiOperation(value = "Retrieves the list of actual connection types in system")
    public Response getConnectionTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.CONNECTION_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.CREDIT_LIMIT_TYPES)
    @ApiOperation(value = "Retrieves the list of available credit limit types")
    public Response getCreditLimitTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.CREDIT_LIMIT_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.DTV_ACCESS_CRITERIA)
    @ApiOperation(value = "Retrieves the list of access criteria of digital TV")
    public Response getDtvAccessCriteria() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.DTV_ACCESS_CRITERIA))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.FEE_MODES)
    @ApiOperation(value = "Retrieves the list of available fee modes")
    public Response getFeeModes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.FEE_REGIME))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.SALE_TYPE)
    @ApiOperation(value = "Retrieves the list of available sell types of goods")
    public Response getGoodsSellTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.SALE_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.INET_TARIFFICATION_TYPE)
    @ApiOperation(value = "Retrieves the list of all possible values of tariffication types")
    public Response getInternetTarifficationTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.INET_TARIFFICATION_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.LOCK_LIMIT_TYPES)
    @ApiOperation(value = "Retrieves the list of lock limit types of tariff plan")
    public Response getLockLimitTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.LOCK_LIMIT_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.PAYMENT_TERM_TYPES)
    @ApiOperation(value = "Retrieves the list of available payment term types")
    public Response getPaymentTermTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.PAYMENT_TERM_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.PAYMENT_TYPE)
    @ApiOperation(value = "Retrieves the list of available payment types")
    public Response getPaymentTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TP_PAYMENT_WAY))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.PERIOD_MEASURES)
    @ApiOperation(value = "Retrieves the list of time period measures for tariff plan of requested activity type")
    public Response getPeriodMeasures(
            @ApiParam(value = "An ID of parent activity type") @QueryParam("activityTypeId") Long activityTypeId) {
        List<DbCondition> conditions = Collections.singletonList(DbCondition.getBuilder().setDictionaryName(Entity.ACTIV_TYPE).setId(activityTypeId).build());
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TIME_PERIOD_MEASURE, conditions))).build();
    }

    @GET
    @Path(RestApiEndpoints.MainData.TRAFF_SPEED_MEASURE)
    @ApiOperation(value = "Retrieves the list of actual speed units in system")
    public Response getSpeedUnits() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TRAFF_SPEED_MEASURE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.WITHDRAWAL_TYPES)
    @ApiOperation(value = "Retrieves the list of mechanisms of withdrawal of a user's payment")
    public Response getSubscriptionFeeTypes(
            @ApiParam(value = "An ID of parent activity type") @QueryParam("activityTypeId") Long activityTypeId,
            @ApiParam(value = "An ID of parent base unit") @QueryParam("baseUnitId") Long baseUnitId) {
        List<DbCondition> conditions = new LinkedList<>();
        conditions.add(DbCondition.getBuilder().setDictionaryName(Entity.ACTIV_TYPE).setId(activityTypeId).build());
        if (baseUnitId != null) {
            conditions.add(DbCondition.getBuilder().setDictionaryName(Entity.BASE_UNIT).setId(baseUnitId).build());
        }
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.SUBSCRIPTION_FEE_TYPE, conditions))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.TARIFFICATION_TYPE)
    @ApiOperation(value = "Retrieves the list of all possible values of tariff types")
    public Response getTarifficationTypes(
            @ApiParam(value = "An ID of parent activity type") @QueryParam("activityTypeId") Long activityTypeId) {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TARIFFICATION_TYPE, activityTypeId))).build();
    }

    @GET
    @Path(RestApiEndpoints.MainData.STATUSES)
    @ApiOperation(value = "Retrieves the list of actual statuses of tariff plans in system")
    public Response getTariffPlanStatuses() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TP_STATUS))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.RESTRICTION_TYPES)
    @ApiOperation(value = "Retrieves the list of available tariff plan restriction types")
    public Response getTpRestrictionTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TP_RESTRICTION_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.TV_PACKAGE_TYPES)
    @ApiOperation(value = "Retrieves the list of TV package types")
    public Response getTvPackageType(
            @ApiParam(value = "An ID of parent activity type") @QueryParam("activityTypeId") Long activityTypeId) {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TV_PACKET_TYPE, activityTypeId))).build();
    }

    @GET
    @Path(RestApiEndpoints.MainData.TRAFF_VOL_MEASURE)
    @ApiOperation(value = "Retrieves the list of actual volume units in system")
    public Response getVolumeUnits() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TRAFF_VOL_MEASURE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.InteractionMatrix.TP_INTERACTION_TYPES)
    @ApiOperation(value = "Retrieves the list of tp interaction types for interaction matrix")
    public Response getInteractionTypes() {
        return Response.ok(new RsResponse(keyValDictSv.getKeyValues(Entity.TP_INTERACTION_TYPE))).build();
    }

    @GET
    @Path(RestApiEndpoints.TariffPlan.GOODS)
    @ApiOperation(value = "Retrieves list of goods with parameters")
    public Response getGoodsParameters() {
        return Response.ok(new RsResponse()).build();
    }

    /*    @Override
        public Collection<GoodsGroup> getGoods() {
            Collection<GoodsGroup> goodsGroups = commonDataDao.getAll(GoodsGroup.class);
            Collection<GoodsModel> goodsModels = commonDataDao.getAll(GoodsModel.class);
            Collection<GoodsPart> goodsParts = commonDataDao.getAll(GoodsPart.class);

            for (GoodsModel goodsModel : goodsModels) {
                for (GoodsPart goodsPart : goodsParts) {
                    if (goodsPart.getModelId().equals(goodsModel.getId())) {
                        goodsModel.addPart(goodsPart);
                    }
                }
            }
            for (GoodsGroup goodsGroup : goodsGroups) {
                for (GoodsModel goodsModel : goodsModels) {
                    if (goodsModel.getGroupId().equals(goodsGroup.getId())) {
                        goodsGroup.addModel(goodsModel);
                    }
                }
            }
            return goodsGroups;
        }*/
}

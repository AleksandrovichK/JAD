package by.mtis.ui.rest.jasper;

import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import by.mtis.constants.RestApiEndpoints;
import by.mtis.dto.jasper.JasperReportMetaInfo;
import by.mtis.service.jasper.IJasperPrintService;
import by.mtis.ui.constant.ContentType;
import by.mtis.ui.rest.model.RsResponse;

/**
 * REST services used for printing data via {@link JasperPrint} to PDF/XLS/DOC format.
 *
 * @author AleksandrovichK
 */
@Path(RestApiEndpoints.Jasper.BASE)
@Consumes(ContentType.APPLICATION_JSON_UTF8)
@Api(value = RestApiEndpoints.Jasper.BASE)
public class JasperReportsRestService {
    @EJB
    private IJasperPrintService jasperPrintService;

    @POST
    @Path(RestApiEndpoints.Jasper.PRINT_SHIPPING_LIST_PDF)
    @Produces(ContentType.APPLICATION_PDF)
    @ApiOperation(value = "Returns formal document information about shipping list in PDF format.")
    public Response printShippingListToPDF(Map<String, Object> parameters) throws JRException, SQLException, NamingException {
        ByteArrayOutputStream stream = jasperPrintService.printShippingListPDF(parameters);
        return Response.ok(stream.toByteArray())
                .type(ContentType.APPLICATION_PDF)
                .header("Content-Disposition", "attachment; filename = Report.pdf")
                .build();
    }

    @POST
    @Path(RestApiEndpoints.Jasper.PRINT_SHIPPING_LIST_XLSX)
    @Produces(ContentType.APPLICATION_XLSX)
    @ApiOperation(value = "Returns formal document information about shipping list in XLSX format.")
    public Response printShippingListToXLSX(Map<String, Object> parameters) throws JRException, SQLException, NamingException {
        ByteArrayOutputStream stream = jasperPrintService.printShippingListXLS(parameters);
        return Response.ok(stream.toByteArray())
                .type(ContentType.APPLICATION_XLSX)
                .header("Content-Disposition", "attachment; filename = Report.xlsx")
                .build();
    }

    @POST
    @Path(RestApiEndpoints.Jasper.PRINT_SHIPPING_LIST_DOC)
    @Produces(ContentType.APPLICATION_DOC)
    @ApiOperation(value = "Returns formal document information about shipping list in DOC format.")
    public Response printShippingListToDOC(Map<String, Object> parameters) throws JRException, SQLException, NamingException {
        ByteArrayOutputStream stream = jasperPrintService.printShippingListDOC(parameters);
        return Response.ok(stream.toByteArray())
                .type(ContentType.APPLICATION_DOC)
                .header("Content-Disposition", "attachment; filename = Report.doc")
                .build();
    }

    @GET
    @Path(RestApiEndpoints.Jasper.LIST_OF_REPORTS)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    @ApiOperation(value = "Provides with a list of reports in report-config folder")
    public Response getReportsList() throws URISyntaxException {
        List<JasperReportMetaInfo> result = jasperPrintService.parseReportsList();
        return Response.ok(new RsResponse(result)).build();
    }
}

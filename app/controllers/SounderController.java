package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import domain.api.SounderDeployAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.SounderService;


@Api(value = "/sounder", description = "Sounder API", produces = "application/json")
public class SounderController extends Controller {

    @Inject SounderService service;

    @ApiOperation(
            nickname = "Sounder",
            value = "Input plane and sounders",
            httpMethod = "POST",
            response = SounderDeployAPI.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Task object that need to be created", required = true, dataType = "domain.api.SounderDeployAPI", paramType = "body")
    })
    public Result deploy() {
        JsonNode json = request().body().asJson();

        if(json == null)
            return badRequest("Invalid content");
        else {
            try {
                SounderDeployAPI api = Json.fromJson(json, SounderDeployAPI.class);
                return ok(Json.toJson(service.deploy(api)));
            } catch (Exception e) {
                e.printStackTrace();
                return badRequest(e.getMessage());
            }
        }
    }





}

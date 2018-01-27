package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import domain.api.SounderAPI;
import domain.api.SounderDeployAPI;
import domain.entities.Sounder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Tuple;

import java.util.ArrayList;


@Api(value = "/sounder", description = "Sounder API", produces = "application/json")
public class SounderController extends Controller {


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

            SounderAPI api = Json.fromJson(json, SounderAPI.class);
            return ok( "200");
        }


    }





}

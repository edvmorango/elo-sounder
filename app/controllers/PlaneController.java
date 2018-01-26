package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import domain.entities.Plane;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Tuple;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Api(value = "/plane", description = "Planes API", produces = "application/json")
public class PlaneController extends Controller {


    @ApiOperation(nickname = "list planes",
                  value = "Get Tasks",
                  response = Plane.class,
                  httpMethod = "GET",
                  notes = "Nothing")
    public Result list() {
        Plane p = new Plane(new Tuple<>(1,1));
        ArrayList<Plane> ps = new ArrayList<>();
        ps.add(p);

        return ok(Json.toJson(ps));
    }


}

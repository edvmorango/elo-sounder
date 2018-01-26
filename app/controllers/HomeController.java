package controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import play.mvc.*;

import views.html.*;



public class HomeController extends Controller {
    public Result index() {
        return ok("{status: 200 }").as("application/json");
    }

}

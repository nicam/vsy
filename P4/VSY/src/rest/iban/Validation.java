package rest.iban;

import java.io.IOException;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

@Path("/iban")
public class Validation {
	
	@GET
	@Path("/validate/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public String validate( @PathParam("number") String number ) throws IOException, JSONException {
		if(IBanService.getInstance().getIBAN_Checker().IBAN_Checker(number).equals("OK")){
			String query = "Select * where iban = '" + number + "'";
			JSONResource result = new Resty().json("http://api.usergrid.com/nicam/accounts/accounts/?ql=" + URLEncoder.encode(query, "UTF-8"));
			JSONObject obj = result.object();
			
			JSONObject entry = (JSONObject) ((JSONArray) obj.get("entities")).get(0);
			String iban = (String) entry.get("IBAN");
			if(iban.equals(number)){
				JSONObject retObj = new JSONObject();
				retObj.put("name", entry.get("name"));
				return retObj.toString();
			}
		}
		
		return "false";
	}
}
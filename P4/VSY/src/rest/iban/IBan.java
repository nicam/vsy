package rest.iban;

import java.io.IOException;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.http.HTTPException;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

@Path("/iban")
public class IBan {
	
	@GET
	@Path("/validate/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public String validateIban(@PathParam("number") String number ) throws IOException, JSONException {
		if(IBanService.getInstance().getIBAN_Checker().IBAN_Checker(number).equals("OK")){
			JSONObject retObj = new JSONObject();
			retObj.put("status", "valid");
			return retObj.toString();
		}
		
		JSONObject retObj = new JSONObject();
		retObj.put("status", "invalid");
		return retObj.toString();
	}
	
	@GET
	@Path("/accounts/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchAccount(@PathParam("number") String number) throws IOException, JSONException {
		JSONObject retObj = new JSONObject();
		
		if(IBanService.getInstance().getIBAN_Checker().IBAN_Checker(number).equals("OK")){
			String query = "Select * where iban = '" + number + "'";
			JSONResource result = new Resty().json("http://api.usergrid.com/nicam/accounts/accounts/?ql=" + URLEncoder.encode(query, "UTF-8"));
			JSONObject obj = result.object();
			JSONArray array = (JSONArray) obj.get("entities");
			if(array.length() == 0) {
				throw new WebApplicationException(404);
			}
			
			JSONObject entry = (JSONObject) array.get(0);
			String iban = (String) entry.get("IBAN");
			if(iban.equals(number)){
				retObj.put("name", entry.get("name"));
				retObj.put("iban", iban);

				return retObj.toString();
			}
		}
		
		throw new WebApplicationException(400);
	}
}
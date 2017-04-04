package Tools;

import org.json.JSONException;
import org.json.JSONObject;

public class Error  {
	public static JSONObject serviceKO(String m, int errornomber) throws JSONException{
		JSONObject r = new JSONObject();
		r.put("message", m);
		r.put("KO", new Integer(errornomber));
		return r;
	}

	public static JSONObject serviceOK(){
		return new JSONObject();
	}

}
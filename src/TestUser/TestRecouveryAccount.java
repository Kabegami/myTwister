package TestUser;

import org.json.JSONObject;

import ServiceUser.RecouveryAccountService;

public class TestRecouveryAccount {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  RecouveryAccountService.recouveryAccount("yoonestfievap7h96pmei0aadm2pv42f");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}

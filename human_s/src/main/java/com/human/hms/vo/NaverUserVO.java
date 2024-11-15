package com.human.hms.vo;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.Data;
@Data
public class NaverUserVO {
    private String name;
    private String email;
    private String birthday;
    private String birthyear;
    private String mobile;
    
	public NaverUserVO(String jsonResponseBody){
       JsonParser jsonParser = new JsonParser();
       JsonElement element = jsonParser.parse(jsonResponseBody);
       
       this.name = element.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
       this.email = element.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
       this.birthday = element.getAsJsonObject().get("response").getAsJsonObject().get("birthday").getAsString();
       this.birthyear = element.getAsJsonObject().get("response").getAsJsonObject().get("birthyear").getAsString();
       this.mobile = element.getAsJsonObject().get("response").getAsJsonObject().get("mobile").getAsString();
    }
	
}

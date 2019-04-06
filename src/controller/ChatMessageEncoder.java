package controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage>
{
    @Override
    public void destroy()
    {

    }
    @Override
    public void init(EndpointConfig epc)
    {

    }
    @Override
    public String encode(ChatMessage message)
    {
    	JSONObject jo=new JSONObject();
    	JSONArray ja = null;
		try {
			if(message.getUsers()==null)
			{

			}
			else
			{
			   ja = new JSONArray(message.getUsers());
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
        try {
            jo.put("name", message.getName());
            jo.put("message",message.getMessage());
            jo.put("users",ja);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        System.out.println(jo.toString());
        return jo.toString();
    }
}

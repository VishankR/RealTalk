package controller;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import org.json.*;
public class ChatMessageDecoder implements Decoder.Text<ChatMessage>
{
	boolean b;
    public void destroy()
    {

    }

    public void init(EndpointConfig epc)
    {

    }

    public ChatMessage decode(String s)
    {
    	ChatMessage cm=new ChatMessage();
        JSONObject jo2 = null;

		try {
			jo2 = new JSONObject(s);
			b=true;
		    } catch (JSONException e1)
		     {
		       b=false;
			   e1.printStackTrace();
		     }
        String s1 = null;
        try {
            s1=jo2.getString("message");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        cm.setMessage(s1);        
        return cm;
    }
    public boolean willDecode(String s) {
    		return true;
    }

}

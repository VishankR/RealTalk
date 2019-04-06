package controller;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
@ServerEndpoint(value="/serverendpoint",encoders= {ChatMessageEncoder.class},decoders= {ChatMessageDecoder.class},configurator = GetHttpSessionConfigurator.class)
public class ChatServerEndPoint
{
    static Set<Session> chatroomusers=Collections.synchronizedSet(new HashSet<Session>());
    private HttpSession httpSession;
    @OnOpen
    public void handleOpen(Session usersession,EndpointConfig config) throws IOException, JSONException, EncodeException
    {
      this.httpSession = (HttpSession) config.getUserProperties()
                                   .get(HttpSession.class.getName());
      chatroomusers.add(usersession);
      usersession.getUserProperties().put("username", httpSession.getAttribute("username"));
      ChatMessage outgoingmessage=new ChatMessage();
      outgoingmessage.setUsers(buildJsonUsersData());
      Iterator<Session> iterator=chatroomusers.iterator();
      while(iterator.hasNext())
      {
      	Session sess=iterator.next();
          try
          {
				       sess.getBasicRemote().sendObject(outgoingmessage);
			         }
           catch (EncodeException e)
           {
				       e.printStackTrace();
			         };
      }
    }
    @OnMessage
    public void handleMessage(ChatMessage incomingmessage , Session usersession) throws IOException, JSONException, EncodeException
    {
        String username=(String)usersession.getUserProperties().get("username");
        ChatMessage outgoingmessage=new ChatMessage();
        //ChatMessage outgoingmessage2=new ChatMessage();
        Iterator<Session> iterator=chatroomusers.iterator();
            outgoingmessage.setUsers(buildJsonUsersData());
            outgoingmessage.setName(username);
            outgoingmessage.setMessage(incomingmessage.getMessage());
            outgoingmessage.setUsers(buildJsonUsersData());
            System.out.println("getusers :"+outgoingmessage.getUsers());
                while(iterator.hasNext())
                {
                	Session sess=iterator.next();
                    try
                    {
    					       sess.getBasicRemote().sendObject(outgoingmessage);
    				         }
                     catch (EncodeException e)
                     {
    					       e.printStackTrace();
    				         };
                }
            }
    @OnClose
    public void handleClose(Session usersession) throws IOException, JSONException, EncodeException
    {
        chatroomusers.remove(usersession);
        ChatMessage outgoingmessage=new ChatMessage();;
        outgoingmessage.setUsers(buildJsonUsersData());
        Iterator<Session> itr=chatroomusers.iterator();
        while(itr.hasNext())
        {
        	itr.next().getBasicRemote().sendObject(outgoingmessage);
        }

    }
    @OnError
    public void handleError(Throwable t)
    {
       t.printStackTrace();
    }
    private Set<String> getUsersName()
    {
    	HashSet<String> returnset=new HashSet<String>();
    	Iterator<Session> itr=chatroomusers.iterator();
    	while(itr.hasNext())
    	{
    		Object s=itr.next().getUserProperties().get("username");
    		if(s==null)
    		{

    		}else
    		{
    		    returnset.add(s.toString());
    	    }
    	}
    	return returnset;
    }
    private String buildJsonUsersData() throws JSONException
    {
    	HashSet<String> hs=(HashSet<String>)getUsersName();
    	JSONArray ja=new JSONArray(hs);
    	System.out.println("ja :"+ja);
    	return ja.toString();
    }
}

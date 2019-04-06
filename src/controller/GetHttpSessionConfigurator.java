package controller;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator 
{
   @Override
   public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request,HandshakeResponse response)
   {
	   HttpSession httpsession=(HttpSession) request.getHttpSession();
	   config.getUserProperties().put(HttpSession.class.getName(), httpsession);
   }
	
}

package co.uk.codeyogi.websocketsTutorial.conrtoller;

import co.uk.codeyogi.websocketsTutorial.model.ChatMessage;
import co.uk.codeyogi.websocketsTutorial.model.MessageType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebsocketEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebSocketConnectListener (final SessionConnectedEvent event){
        LOGGER.info("connexion");
    }

    @EventListener
    public void handleWebSocketDisconnectListener (final SessionDisconnectEvent event){
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) headerAccessor.getSessionAttributes().get("username");
        final ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();
        sendingOperations.convertAndSend("/topic/public",chatMessage);

    }

}

package co.uk.codeyogi.websocketsTutorial.model;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ChatMessage {
    //il n'y a pas des constructeurs ni des methodes
    // builder, c'est un modele de conception qui nous permet de creer des object avec des attribues
    @Getter
    private MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    private String time;
}

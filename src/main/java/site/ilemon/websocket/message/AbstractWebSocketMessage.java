package site.ilemon.websocket.message;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
public abstract class AbstractWebSocketMessage {

    private String event;

    public AbstractWebSocketMessage() {

    }

    public AbstractWebSocketMessage(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}

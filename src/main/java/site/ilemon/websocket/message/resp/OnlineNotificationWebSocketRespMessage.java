package site.ilemon.websocket.message.resp;

import lombok.Builder;
import lombok.Data;
import site.ilemon.websocket.constants.WebSocketConstants;
import site.ilemon.websocket.message.AbstractWebSocketMessage;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@Data
@Builder
public class OnlineNotificationWebSocketRespMessage extends AbstractWebSocketMessage implements Serializable {
    private final String event = WebSocketConstants.Event.ONLINE_NOTIFICATION_EVENT_RESP;

    private String onlineUsername;

    private String onlineTime;
}

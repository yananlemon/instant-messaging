package site.ilemon.websocket.message.resp;

import lombok.Builder;
import lombok.Data;
import site.ilemon.websocket.constants.WebSocketConstants;
import site.ilemon.websocket.message.AbstractWebSocketMessage;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@Data
@Builder
public class HeartbeatRespMessage extends AbstractWebSocketMessage implements Serializable {
    private final String event = WebSocketConstants.Event.HEARTBEAT_EVENT_RESP;
    private long currentTime;
}

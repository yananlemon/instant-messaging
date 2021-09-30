package site.ilemon.websocket.message.resp;

import lombok.Builder;
import lombok.Data;
import site.ilemon.websocket.constants.WebSocketConstants;
import site.ilemon.websocket.message.AbstractWebSocketMessage;

import java.io.Serializable;

/**
 * <p>群消息response</p>
 *
 * @author Andy.Yan
 */
@Data
@Builder
public class GroupMessageWebSocketRespMessage extends AbstractWebSocketMessage implements Serializable {
    private final String event = WebSocketConstants.Event.GROUP_MSG_EVENT_RESP;

    private String fromUser;

    private String message;

    private String sendTime;
}

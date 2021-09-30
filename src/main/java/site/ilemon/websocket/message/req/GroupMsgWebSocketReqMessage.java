package site.ilemon.websocket.message.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.ilemon.websocket.message.AbstractWebSocketMessage;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMsgWebSocketReqMessage extends AbstractWebSocketMessage {
    private String from;
    private String msg;
}

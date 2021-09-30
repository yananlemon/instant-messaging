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
public class RegisterWebSocketReqMessage extends AbstractWebSocketMessage {
    private String username;
}

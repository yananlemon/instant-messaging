package site.ilemon.websocket.constants;


public class WebSocketConstants {

    public static class Event {


        /**
         * 心跳-请求事件
         */
        public static final String HEARTBEAT_EVENT_REQ = "heartbeatEventReq";
        /**
         * 心跳-响应事件
         */
        public static final String HEARTBEAT_EVENT_RESP = "heartbeatEventResp";

        /**
         * 心跳-请求事件
         */
        public static final String REGISTER_EVENT_REQ = "registerEventReq";
        /**
         * 注册-响应事件
         */
        public static final String REGISTER_EVENT_RESP = "registerEventResp";

        /**
         * 上线通知-响应事件
         */
        public static final String ONLINE_NOTIFICATION_EVENT_RESP = "onlineNotificationEventResp";

        /**
         * 群消息-请求事件
         */
        public static final String GROUP_MSG_EVENT_REQ = "groupMsgEventReq";

        /**
         * 群消息-响应事件
         */
        public static final String GROUP_MSG_EVENT_RESP = "groupMsgEventResp";


    }
}
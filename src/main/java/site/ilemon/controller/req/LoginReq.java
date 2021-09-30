package site.ilemon.controller.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {
    private String username;
    private String password;
}

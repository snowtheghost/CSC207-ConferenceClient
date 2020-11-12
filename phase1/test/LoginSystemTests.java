import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class LoginSystemTests {
    private UserManager userMan = new UserManager();
    private LoginSystemPresenter lsp = new LoginSystemPresenter();
    private LoginSystem loginSys;

    @Before
    public void beforeEveryTest(){
        this.loginSys = new LoginSystem(userMan);
    }

    @Test
    public void testCreateAccount(){
        ByteArrayInputStream in = new ByteArrayInputStream("asdasd".getBytes());
        System.setIn(in);
        this.loginSys.run();



    }

}

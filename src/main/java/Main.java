import com.revature.northern.daos.UserDAO;
import com.revature.northern.services.UserService;
import com.revature.northern.ui.LoginMenu;

public class Main {
    public static void main(String[] args) {

        /* dependency injection */
        new LoginMenu(new UserService(new UserDAO())).start();
        //new LoginMenu(new UserService(new UserDAO()), new BookService(new BookDAO())).start();
    }
}


import com.example.jaxrs.model.User;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class UserResourceMain {

    private static final String FULL_PATH = "http://localhost:8080/restapi/users";

    public static void main(String[] args) {
        UserResourceMain urm = new UserResourceMain();
        urm.testListAllUsers();
        urm.testGetUser();
        urm.testCreateUser();
        urm.testUpdateUser();
        urm.testDeleteUser();
    }

    public void testListAllUsers() {

        Client client = ClientBuilder.newClient();
        final WebTarget target = client
                .target(FULL_PATH);
        String response = target.request().get(String.class);
        System.out.println(response);
    }

    public void testGetUser() {

        Client client = ClientBuilder.newClient();
        final WebTarget target = client
                .target(FULL_PATH + "/100");
        Response response = target.request().get();
        User user = response.readEntity(User.class);
        System.out.println(user.toString());
        response.close();
    }

    public void testCreateUser() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(FULL_PATH);
        Response response = target.request()
                .post(Entity.entity(new User(100L,
                        "Amir", "amir@gmail.com"), "application/json"));
        System.out.println(response.getStatus());
        response.close();
    }

    public void testUpdateUser() {
        User user = new User();
        user.setName("Ram");
        user.setEmail("ram@gmail.com");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(FULL_PATH + "/100");
        Response response = target.request()
                .put(Entity.entity(user, "application/json"));
        System.out.println(response.getStatus());
        response.close();
    }

    public void testDeleteUser() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(FULL_PATH + "/101");
        Response response = target.request()
                .delete();
        System.out.println(response.getStatus());
        response.close();

        final WebTarget target1 = client
                .target(FULL_PATH);
        String response1 = target1.request().get(String.class);
        System.out.println(response1);
    }

}

import java.util.LinkedList;
import java.util.List;

/**
 * @author AleksandrovichK
 */
class RestClass {
    private List<Service> services;

    RestClass() {
        this.services = new LinkedList<>();
    }

    List<Service> getServices() {
        return services;
    }

    void pushService(Service service) {
        this.services.add(service);
    }
}

package usermanager;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Controller("/hello")
public class HelloController {
    @Inject
    @Client("/")
    private HttpClient httpClient;

    @Get(uri = "/", produces = "text/plain")
    public String index() {
        return "Example Response";
    }

    @Get("/message")
    public List message() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "swagat"));
        employees.add(new Employee(2, "swagat"));
        employees.add(new Employee(3, "swagat"));
        employees.add(new Employee(4, "swagat"));
        employees.add(new Employee(5, "swagat"));
        return employees;
    }

    @Get(value = "/ping",consumes = MediaType.APPLICATION_JSON,produces = MediaType.APPLICATION_JSON)
    public String getData() {
        String retrive = httpClient.toBlocking().retrieve(HttpRequest.GET("hhtps://www.youtube.com"));
        return retrive;
    }

    @Post(value = "/record", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Person> createPerson(final Person person) {
        person.setAge(21);
        return HttpResponse.created(person);
    }
}
package github.artmal.delivery.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/contact")
class ContactServlet{
    private final Logger logger = LoggerFactory.getLogger(ContactServlet.class);

    private ContactRepository repository;

    ContactServlet(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Contact>> findAllContacts() {
        logger.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    @Transactional
    ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        return ResponseEntity.ok(repository.save(contact));
    }
}

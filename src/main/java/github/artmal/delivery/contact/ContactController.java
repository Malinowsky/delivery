package github.artmal.delivery.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/contact")
class ContactController {
    private final Logger logger = LoggerFactory.getLogger(ContactController.class);

    private ContactRepository repository;

    ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Contact>> findAllContacts() {
        logger.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping
    @Transactional
    ResponseEntity<Contact> toggleContact(@PathVariable String name){
        var contact = repository.findById(name);
        contact.ifPresent(c -> {
            repository.save(c);
        });
        return contact.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        return ResponseEntity.ok(repository.save(contact));
    }
}

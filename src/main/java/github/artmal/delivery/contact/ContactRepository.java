package github.artmal.delivery.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ContactRepository extends JpaRepository<Contact,String>{

}

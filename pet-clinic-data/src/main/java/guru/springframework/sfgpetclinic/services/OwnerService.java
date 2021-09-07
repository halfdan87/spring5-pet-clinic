package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;
import java.util.Optional;

public interface OwnerService extends CrudService<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);
}

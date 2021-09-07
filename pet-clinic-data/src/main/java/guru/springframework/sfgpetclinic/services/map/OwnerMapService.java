package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner save(Owner o) {
        if (o == null) {
            return null;
        }

        Set<Pet> pets = o.getPets();
        if (pets != null) {
            pets.forEach(pet -> {
                PetType petType = pet.getPetType();
                if (petType.getId() == null) {
                    pet.setPetType(petTypeService.save(petType));
                }

                if (pet.getId() == null) {
                    pet.setId(petService.save(pet).getId());
                }
            });
        }

        return super.save(o);
    }

    @Override
    public Optional<Owner> findByLastName(@NonNull String lastName) {
        return map.values().stream().filter(
            it -> lastName.equals(it.getLastName())
        ).collect(Collectors.toList())
            .stream().findFirst();
    }
}

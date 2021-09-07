package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService,
        VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dogType = new PetType();
        dogType.setName("dog");
        dogType = petTypeService.save(dogType);

        Owner o1 = Owner.builder()
            .firstName("Jan")
            .lastName("Paweł")
            .address("Miastna 1")
            .city("Łódź")
            .telephone("+48000000000")
            .build();

        ownerService.save(o1);

        Pet dog = new Pet();
        dog.setName("Puppy");
        dog.setBirthDate(LocalDate.MIN);
        dog.setPetType(dogType);
        dog.setOwner(o1);
        petService.save(dog);

        o1.getPets().add(dog);

        Speciality rad = new Speciality();
        rad.setDescription("radiology");
        specialityService.save(rad);

        Vet vet1 = new Vet();
        vet1.setFirstName("Jan");
        vet1.setLastName("Paweł");
        vet1.getSpecialities().add(rad);
        vetService.save(vet1);

        Visit visit = new Visit();
        visit.setDescription("Put to sleep");
        visit.setDate(LocalDate.now());
        visit.setPet(dog);

        visitService.save(visit);
    }
}

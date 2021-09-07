package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.sfgpetclinic.model.Owner;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Owner testOwner;
    Long testOwnerId = 1l;
    String testLastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        testOwner = Owner.builder().id(testOwnerId).lastName(testLastName).build();
        ownerMapService.save(testOwner);
    }

    @Test
    void findAll() {
        Set<Owner> all = ownerMapService.findAll();

        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        Owner byId = ownerMapService.findById(testOwnerId);

        assertEquals(testOwnerId, byId.getId());
    }

    @Test
    void testSave() {
        Owner save = ownerMapService.save(Owner.builder().build());

        assertNotNull(save);
        assertNotNull(save.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(Owner.builder().id(testOwnerId).build());

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(testOwnerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Optional<Owner> byLastName = ownerMapService.findByLastName(testLastName);

        assertEquals(testOwnerId, byLastName.get().getId());
    }
}
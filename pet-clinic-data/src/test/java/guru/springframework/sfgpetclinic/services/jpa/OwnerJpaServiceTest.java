package guru.springframework.sfgpetclinic.services.jpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    public static final String LAST_NAME2 = "Graham";
    public static final long ID = 1l;
    public static final long ID2 = 2l;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    private Owner returnOwner;
    private Owner returnOwner2;
    private Set<Owner> returnOwners;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
        returnOwner2 = Owner.builder().id(ID2).lastName(LAST_NAME2).build();

        returnOwners = new HashSet<>();
        returnOwners.add(returnOwner);
        returnOwners.add(returnOwner2);
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> all = ownerJpaService.findAll();

        assertNotNull(all);
        assertEquals(2, all.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner byId = ownerJpaService.findById(ID);

        assertEquals(returnOwner, byId);
    }


    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner byId = ownerJpaService.findById(ID);

        assertNull(byId);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner save = ownerJpaService.save(returnOwner);

        assertEquals(returnOwner, save);
    }

    @Test
    void delete() {
        ownerJpaService.delete(returnOwner);

        verify(ownerRepository).delete(returnOwner);
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(ID);

        verify(ownerRepository).deleteById(ID);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(Optional.of(returnOwner));

        Optional<Owner> smith = ownerJpaService.findByLastName(LAST_NAME);

        assertEquals(returnOwner, smith.get());
        verify(ownerRepository).findByLastName(any());
    }
}
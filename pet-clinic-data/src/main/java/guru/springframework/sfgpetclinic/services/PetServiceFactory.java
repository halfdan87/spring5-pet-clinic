package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.services.map.CatService;
import guru.springframework.sfgpetclinic.services.map.DogService;


public class PetServiceFactory {

    public PetService getPetService(String type) {
        switch (type) {
            case "cat" :
                return new CatService();
            case "dog":
            default:
                return new DogService();
        }
    }
}

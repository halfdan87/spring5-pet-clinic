package guru.springframework.sfgpetclinic.services;

import java.util.Set;
import org.springframework.lang.NonNull;

public interface CrudService<T, ID extends Long> {
    Set<T> findAll();
    T findById(@NonNull ID id);
    T save(@NonNull T t);
    void delete(@NonNull T t);
    void deleteById(@NonNull ID id);
}

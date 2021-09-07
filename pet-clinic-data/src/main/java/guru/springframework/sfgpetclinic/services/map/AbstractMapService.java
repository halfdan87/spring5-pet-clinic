package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.services.CrudService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMapService<T, ID extends Long> implements CrudService<T, ID> {
    protected Map<Long, T> map = new HashMap<>();

    @Override
    public Set<T> findAll() {
        return map.values().stream().collect(Collectors.toSet());
    }

    @Override
    public T findById(ID o) {
        return map.get(o);
    }

    @Override
    public T save(T o) {
        map.put(extractId(o), o);
        return o;
    }

    @Override
    public void delete(T o) {
        map.entrySet().removeIf(it -> extractId(it.getValue()).equals(extractId(o)));
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);
    }

    private Long extractId(T o) {
        try {
            Method getId = o.getClass().getMethod("getId");
            ID id = (ID) getId.invoke(o);

            if (id == null) {
                Long newId = getNextId();
                setId(o, newId);
                return newId;
            }
            return id;
        } catch (NoSuchMethodException e) {
            throw new MapServiceException("No getId method!");
        } catch (IllegalAccessException e) {
            throw new MapServiceException("Method getId not accessible", e);
        } catch (InvocationTargetException e) {
            throw new MapServiceException("Method getId not accessible", e);
        }
    }

    private void setId(T o, Long newId) {
        try {
            Method setId = o.getClass().getMethod("setId", Long.class);
            setId.invoke(o, newId);
        } catch (NoSuchMethodException e) {
            throw new MapServiceException("No setId method!");
        } catch (IllegalAccessException e) {
            throw new MapServiceException("Method setId not accessible", e);
        } catch (InvocationTargetException e) {
            throw new MapServiceException("Method setId not accessible", e);
        }
    }

    private Long getNextId() {
        if (map.isEmpty()) {
            return 1L;
        }
        return Collections.max(map.keySet()) + 1L;
    }
}

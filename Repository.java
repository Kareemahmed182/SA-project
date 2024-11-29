import java.util.List;

public interface Repository<T> {
    void save(T item);
    List<T> findAll();
    T findById(String id);
    void update(T item);
    void delete(String id);
}

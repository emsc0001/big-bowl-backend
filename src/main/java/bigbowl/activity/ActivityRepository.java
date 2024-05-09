package bigbowl.activity;

public interface ActivityRepository { //repository interface
    void save(Activity activity);
    Activity findByName(String name);
    void delete(Activity activity);
    void update(Activity activity);
    void deleteAll();
    Iterable<Activity> findAll();
}

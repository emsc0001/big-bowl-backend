package bigbowl.activity;

public class ActivityService { //ActivityService class
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
}

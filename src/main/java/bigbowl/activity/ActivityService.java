package bigbowl.activity;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Optional<Activity> updateActivity(Long id, Activity activityDetails) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        if (activityOptional.isPresent()) {
            Activity activity = activityOptional.get();
            activity.setName(activityDetails.getName());
            activity.setDescription(activityDetails.getDescription());
            activity.setStartTime(activityDetails.getStartTime());
            activity.setEndTime(activityDetails.getEndTime());
            return Optional.of(activityRepository.save(activity));
        }
        return Optional.empty();
    }

    public void deleteActivity(Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);
        activityOptional.ifPresent(activityRepository::delete);
    }
}


package bigbowl.activity;

public class Activity { //model class
    private String name;
    private String description;
    private String location;
    private String date;
    private String time;
    private String duration;
    private String type;
    private String intensity;
    private String status;
    private String maxParticipants;
    private String participants;
    private String creator;
    private String id;

    public Activity(String name, String description, String location, String date, String time, String duration, String type, String intensity, String status, String maxParticipants, String participants, String creator, String id) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.type = type;
        this.intensity = intensity;
        this.status = status;
        this.maxParticipants = maxParticipants;
        this.participants = participants;
        this.creator = creator;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getStatus() {
        return status;
    }

    public String getMaxParticipants() {
        return maxParticipants;
    }

    public String getParticipants() {
        return participants;
    }

    public String getCreator() {
        return creator;
    }

    public String getId() {
        return id;
    }
}

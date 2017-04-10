package ponkberry.finalproject.gameobject;

/**
 * Created by Ponk on 4/3/2017.
 */

public class GameObject {
    private String name;
    private int appid;
    private String genre;
    private int completion;
    private int achievements;
    private String time;

    public GameObject(String name, int appid, String genre, int completion) {
        this.name = name;
        this.appid = appid;
        this.genre = genre;
        this.completion = completion;
    }

    public GameObject(String name, int appid, String genre, int completion, int achievements, String time) {
        this.name = name;
        this.appid = appid;
        this.genre = genre;
        this.completion = completion;
        this.achievements = achievements;
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public int getAppid() {
        return appid;
    }
    public String getGenre() {
        return genre;
    }
    public int getCompletion() {
        return completion;
    }
    public int getAchievements() { return achievements; }
    public String getTime() { return time; }
    public void setCompletion (int completion) { this.completion = completion; }
}

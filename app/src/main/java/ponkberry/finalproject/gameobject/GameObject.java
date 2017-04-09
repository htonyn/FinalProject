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

    public GameObject(String name, int appid, String genre, int completion) {
        this.name = name;
        this.appid = appid;
        this.genre = genre;
        this.completion = completion;
    }

    public GameObject(String name, int appid, String genre, int completion, int achievements) {
        this.name = name;
        this.appid = appid;
        this.genre = genre;
        this.completion = completion;
        this.achievements = achievements;
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
}

package ponkberry.finalproject.gameobject;

/**
 * Created by htony on 4/10/2017.
 */

public class GameProgress {
    private String name;
    private int appid;
    private int achievement;
    private int status;

    public GameProgress(String name, int achievement, int status) {
        this.name = name;
        this.achievement = achievement;
        this.status = status;
    }

    public String getName() { return name; }
    public int getAppid() { return appid; }
    public int getAchievement() { return achievement; }
    public int getStatus() { return status; }
}
package ponkberry.finalproject.gameobject;

/**
 * Created by Ponk on 4/3/2017.
 */

public class GameObject {
    private String name;
    private int appid;
    private String genre;
    private boolean completion;

    public GameObject(String name, int appid, String genre, boolean completion) {
        this.name = name;
        this.appid = appid;
        this.genre = genre;
        this.completion = completion;
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
    public boolean getCompletion() {
        return completion;
    }
}

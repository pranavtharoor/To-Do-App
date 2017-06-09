package pranav.com.todolist;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pranav on 23/5/17.
 */

public class Data extends RealmObject {

    private String id;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String description) {
        this.text = description;
    }
}
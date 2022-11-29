package MVC;

/**
 * Observer interface for the MVC model
 */
public interface Observer {
    void update(Subject subj);

    void update(Subject subj, Object data);
}

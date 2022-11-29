package MVC;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class for the MVC model
 */
public abstract class Subject {
    /**
     * liste d'Observer (interface)
     */
    protected List<Observer> attached;

    public Subject() {
        attached = new ArrayList<>();
    }

    /**
     * on ajoute obs à la liste d'Observer attachés
     */
    public void attach(Observer obs) {
        if (!attached.contains(obs)) {
            attached.add(obs);
        }
    }

    /**
     * retire obs de la liste d'Observer
     */
    public void detach(Observer obs) {
        attached.remove(obs);
    }

    /**
     * notifie les observer de l'existence de this
     */
    public void notifyObservers() {
        for (Observer o : attached) {
            o.update(this);
        }
    }

    /**
     * notifie les observer de l'existence de this avec un Objet lié
     */
    public void notifyObservers(Object data) {
        for (Observer o : attached) {
            o.update(this, data);
        }
    }

}

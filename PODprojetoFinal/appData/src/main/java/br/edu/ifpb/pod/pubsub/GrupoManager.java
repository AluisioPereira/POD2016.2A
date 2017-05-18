package br.edu.ifpb.pod.pubsub;

/**
 *
 * @author ajp
 */
public interface GrupoManager extends Publisher, GrupoIF, Polling {

    public void notifySubscribers(Message msg);
}

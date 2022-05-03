package br.iface.entities;

import java.util.List;

public interface Feed<T> {
    public void addNewMessage(T message);

    public List<T> getMessages();

    public void removeMessagesFromUser(User user);
}

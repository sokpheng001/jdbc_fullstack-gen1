package service;

import model.User;
import model.dto.MessageSendDto;

// Notification has a dependency relationship with User
public interface Notification {
    void sendMessage(MessageSendDto user);
}

package service.implementation;

import model.User;
import model.dto.MessageSendDto;
import service.Notification;

public class NotificationImpl implements Notification {
    @Override
    public void sendMessage(MessageSendDto user) {
        // simulate send message logic
        System.out.println("User: " + user.sender().getUserName());
        System.out.println("Received Message: " + user.message());
    }
}

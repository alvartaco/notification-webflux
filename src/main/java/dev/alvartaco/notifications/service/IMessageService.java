package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.model.Message;

interface IMessageService {

    public void notifyUsers(Message message) throws Exception;

}

package dev.alvartaco.notifications.service;

import dev.alvartaco.notifications.model.Message;

interface IMessageService {

    public void notify(Message message) throws Exception;

}

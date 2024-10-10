package dev.alvartaco.notifications.service;

interface IMessageService {

    public void notify(String categoryId, String messageBody) throws Exception;

}

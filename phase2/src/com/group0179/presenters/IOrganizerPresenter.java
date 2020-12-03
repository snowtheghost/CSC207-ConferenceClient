package com.group0179.presenters;

import java.util.ArrayList;

public interface IOrganizerPresenter {
    ArrayList<String> getSpeakerNames();

    ArrayList<String> getRoomListArray();

    int getRoomNumber(String roomsListSelection);

    ArrayList<String> getEvents(String roomsListSelection);

    String reMenuButtonText();

    String speakerManagementButtonText();

    String logoutButtonText();

    String createSpeakerFormButtonText();

    String speakerManagerButtonText();

    String createButtonText();

    String reManagerButtonText();

    String createRoomFormButtonText();

    String viewEventListButtonText();

    String createEventFormButtonText();

    String confirmButtonText();

    String removeEventButtonText();

    String rescheduleEventFormButtonText();

    String rescheduleEventButtonText();

    String reMenuTitle();

    String speakerManagementMenuTitle();

    String createSpeakerFormTitle() ;

    String speakerManagerTitle();

    String mainSceneTitle();

    String reManagerTitle();

    String createRoomFormTitle();

    String viewEventListTitle();

    String createEventFormTitle();

    String rescheduleEventFormTitle();

    String usernamePrompt();

    String roomCapacityPrompt();

    String createEventTitlePrompt();

    String createEventSpeakerPrompt();

    String eventDatePrompt();

    String eventTimePrompt();

    String createEventCapacityPrompt();

    String createSpeakerStatus(boolean status) ;

    String createRoomStatus(boolean status);

    String createEventTitleStatus();

    String createEventSpeakerStatus();

    String createEventDateTimeStatus();

    String createEventCapacityStatus();

    String createEventStatus();

    String rescheduleEventStatus(boolean status);

    String messageMenuButtonText();

    String messageCustomRecipientsFormButtonText();

    String messageAttendeesFormButtonText();

    String messageSpeakersFormButtonText();

    String recipientsPrompt();

    String messagePrompt();

    String messageSendButtonText();

    String messageRecipientExistence();

    String messageRecipientValidity();

    String messageRecipientStatus();
}


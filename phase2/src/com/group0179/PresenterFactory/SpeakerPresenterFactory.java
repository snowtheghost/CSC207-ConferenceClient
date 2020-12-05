package com.group0179.PresenterFactory;

import com.group0179.presenters.SpeakerPresenterCH;
import com.group0179.presenters.SpeakerPresenterEN;

public class SpeakerPresenterFactory extends PresenterFactory {

    public SpeakerPresenterEN getSpeakerPresenterEN(){return new SpeakerPresenterEN();}

    public SpeakerPresenterCH getSpeakerPresenterCH(){ return new SpeakerPresenterCH();
    }
}

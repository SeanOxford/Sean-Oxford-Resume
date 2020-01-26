package com.example.myapplication.util

public class OttoBusClasses {


    public class MenuFragmentNonFragmentMenuItemSelectedEvent(val title: String)
    public class MenuFragmentGoToNewFragmentEvent(val title: String)
    public class MenuFragmentExitAnimationFinishedEvent
    public class MenuFragmentReturnToMenuEvent

    public class SetBackPressBehaviorEvent(val behavior: Int)

    public class InfoFragmentGoBackEvent

    public class BackPressFromExerciseMenuEvent

    public class ReturnToMenuEvent

    public class GoToAppStoreEvent(val appId: Int)

    public class GoToLinkedInEvent
    public class EmailClickedEvent
    public class SendTextMsgEvent

    public class SwitchToMainMenuEvent

    public class SwitchToMenuEvent(val menuID: Int)

}
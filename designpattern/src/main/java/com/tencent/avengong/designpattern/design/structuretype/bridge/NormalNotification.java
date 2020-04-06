package com.tencent.avengong.designpattern.design.structuretype.bridge;

public class NormalNotification extends Notification {

    public NormalNotification(ISender sender) {
        super(sender);
    }

    @Override
    public void notify(NotificationEmergencyLevel level, String message) {
        mSender.send(message);

    }
}

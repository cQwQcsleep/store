package com.intellij.util.messages.impl;

import com.intellij.util.messages.MessageBusFactory;
import com.intellij.util.messages.MessageBusOwner;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class MessageBusFactoryImpl extends MessageBusFactory {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        objArr[0] = "owner";
        objArr[1] = "com/intellij/util/messages/impl/MessageBusFactoryImpl";
        if (i != 1) {
            objArr[2] = "createMessageBus";
        } else {
            objArr[2] = "createRootBus";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static RootBus createRootBus(MessageBusOwner messageBusOwner) {
        if (messageBusOwner == null) {
            $$$reportNull$$$0(1);
        }
        return new RootBus(messageBusOwner);
    }
}

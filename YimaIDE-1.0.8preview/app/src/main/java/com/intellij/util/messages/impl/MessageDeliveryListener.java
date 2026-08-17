package com.intellij.util.messages.impl;

import com.intellij.util.messages.Topic;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface MessageDeliveryListener {
    void messageDelivered(Topic<?> topic, String str, Object obj, long j);
}

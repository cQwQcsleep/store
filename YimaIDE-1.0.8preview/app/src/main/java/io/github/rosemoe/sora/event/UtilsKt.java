package io.github.rosemoe.sora.event;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002\u001a5\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u0001H\u0005H\u00050\u0004\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\nH\u0086\b\u001a5\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u0001H\u0005H\u00050\u0004\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\fH\u0086\b¨\u0006\r"}, d2 = {"getResultBoolean", "", "Lio/github/rosemoe/sora/event/ResultedEvent;", "subscribeEvent", "Lio/github/rosemoe/sora/event/SubscriptionReceipt;", "T", "kotlin.jvm.PlatformType", "Lio/github/rosemoe/sora/event/Event;", "Lio/github/rosemoe/sora/event/EventManager;", "receiver", "Lio/github/rosemoe/sora/event/EventReceiver;", "subscribeAlways", "Lio/github/rosemoe/sora/event/EventManager$NoUnsubscribeReceiver;", "editor_release"}, k = 2, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class UtilsKt {
    public static final boolean getResultBoolean(ResultedEvent<Boolean> resultedEvent) {
        resultedEvent.getClass();
        if (!resultedEvent.isResultSet()) {
            return false;
        }
        Boolean result = resultedEvent.getResult();
        result.getClass();
        return result.booleanValue();
    }

    public static final /* synthetic */ <T extends Event> SubscriptionReceipt<T> subscribeAlways(EventManager eventManager, EventManager.NoUnsubscribeReceiver<T> noUnsubscribeReceiver) {
        eventManager.getClass();
        noUnsubscribeReceiver.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        SubscriptionReceipt<T> subscriptionReceiptSubscribeAlways = eventManager.subscribeAlways(Event.class, noUnsubscribeReceiver);
        subscriptionReceiptSubscribeAlways.getClass();
        return subscriptionReceiptSubscribeAlways;
    }

    public static final /* synthetic */ <T extends Event> SubscriptionReceipt<T> subscribeEvent(EventManager eventManager, EventReceiver<T> eventReceiver) {
        eventManager.getClass();
        eventReceiver.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        SubscriptionReceipt<T> subscriptionReceiptSubscribeEvent = eventManager.subscribeEvent(Event.class, eventReceiver);
        subscriptionReceiptSubscribeEvent.getClass();
        return subscriptionReceiptSubscribeEvent;
    }
}

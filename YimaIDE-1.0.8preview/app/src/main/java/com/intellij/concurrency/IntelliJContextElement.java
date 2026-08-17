package com.intellij.concurrency;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lcom/intellij/concurrency/IntelliJContextElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "produceChildElement", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "isStructured", "", "beforeChildStarted", "", "context", "afterChildCompleted", "childCanceled", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface IntelliJContextElement extends CoroutineContext.Element {
    default void afterChildCompleted(CoroutineContext context) {
        context.getClass();
    }

    default void beforeChildStarted(CoroutineContext context) {
        context.getClass();
    }

    default void childCanceled(CoroutineContext context) {
        context.getClass();
    }

    default IntelliJContextElement produceChildElement(CoroutineContext parentContext, boolean isStructured) {
        parentContext.getClass();
        if (isStructured) {
            return this;
        }
        return null;
    }
}

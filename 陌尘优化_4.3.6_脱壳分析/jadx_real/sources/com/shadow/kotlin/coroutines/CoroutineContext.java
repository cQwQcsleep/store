package com.shadow.kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface CoroutineContext {

    public interface Element extends CoroutineContext {
    }

    public interface Key<E extends CoroutineContext.Element> {
    }
}

package com.shadow.kotlin.coroutines;

import com.shadow.kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface ContinuationInterceptor extends CoroutineContext.Element {
    public static final Key Key = Key.$$INSTANCE;

    public final class Key implements CoroutineContext.Key<kotlin.coroutines.ContinuationInterceptor> {
        static final /* synthetic */ Key $$INSTANCE = new Key();
    }
}

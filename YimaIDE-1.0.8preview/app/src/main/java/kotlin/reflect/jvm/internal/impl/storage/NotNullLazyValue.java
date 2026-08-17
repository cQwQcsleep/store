package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface NotNullLazyValue<T> extends Function0<T> {
    boolean isComputed();
}

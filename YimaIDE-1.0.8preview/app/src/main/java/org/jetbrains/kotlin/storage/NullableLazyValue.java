package org.jetbrains.kotlin.storage;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/storage/NullableLazyValue;", "T", "", "Lkotlin/Function0;", "isComputed", "", "isComputing", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface NullableLazyValue<T> extends Function0<T> {
    boolean isComputed();

    boolean isComputing();
}

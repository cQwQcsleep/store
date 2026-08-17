package org.jetbrains.kotlin.storage;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0003J#\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007H&¢\u0006\u0002\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/storage/CacheWithNotNullValues;", "K", "V", "", "computeIfAbsent", "key", "computation", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface CacheWithNotNullValues<K, V> {
    V computeIfAbsent(K key, Function0<? extends V> computation);
}

package org.jetbrains.kotlin.util;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00018\u00018F¢\u0006\u0006\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/util/WeakPair;", "K", "V", Argument.Delimiters.none, "first", "second", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "firstReference", "Ljava/lang/ref/WeakReference;", "secondReference", "getFirst", "()Ljava/lang/Object;", "getSecond", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WeakPair<K, V> {
    private final WeakReference<K> firstReference;
    private final WeakReference<V> secondReference;

    public WeakPair(K k, V v) {
        this.firstReference = new WeakReference<>(k);
        this.secondReference = new WeakReference<>(v);
    }

    public final K getFirst() {
        return this.firstReference.get();
    }

    public final V getSecond() {
        return this.secondReference.get();
    }
}

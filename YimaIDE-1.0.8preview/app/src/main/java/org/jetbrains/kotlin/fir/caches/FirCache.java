package org.jetbrains.kotlin.fir.caches;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\u0006\b\u0001\u0010\u0003 \u0001*\u0006\b\u0002\u0010\u0004 \u00002\u00020\u0002B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u0007\u001a\u00028\u00012\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0002H&¢\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\fR$\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e8&X§\u0004r\u0002\b\u0012¢\u0006\f\u0012\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/FirCache;", "K", Argument.Delimiters.none, "V", "CONTEXT", "<init>", "()V", "getValue", "key", "context", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getValueIfComputed", "(Ljava/lang/Object;)Ljava/lang/Object;", "cachedValues", Argument.Delimiters.none, "getCachedValues$annotations", "getCachedValues", "()Ljava/util/Collection;", "Lorg/jetbrains/kotlin/fir/caches/FirCacheInternals;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirCache<K, V, CONTEXT> {
    @FirCacheInternals
    public static /* synthetic */ void getCachedValues$annotations() {
    }

    public abstract Collection<V> getCachedValues();

    public abstract V getValue(K key, CONTEXT context);

    public abstract V getValueIfComputed(K key);
}

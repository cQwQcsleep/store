package kotlin.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.text.MatchGroup;
import kotlin.time.Clock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001:\u0001\"B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004JA\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00160\u00192\u0006\u0010\u001a\u001a\u0002H\u00172\u0006\u0010\u001b\u001a\u0002H\u0016H\u0096\u0080\u0004¢\u0006\u0002\u0010\u001cJN\u0010\u001d\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u0017\"\u0004\b\u0001\u0010\u0016\"\n\b\u0002\u0010\u001e*\u0004\b\u0002H\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00160\u001f2\u0006\u0010\u001a\u001a\u0002H\u00172\u0006\u0010 \u001a\u0002H\u001eH\u0096\u0080\u0004ø\u0001\u0000¢\u0006\u0002\u0010!\u0082\u0002\u0004\n\u0002\b9¨\u0006#"}, d2 = {"Lkotlin/internal/PlatformImplementations;", "", "<init>", "()V", "addSuppressed", "", "cause", "", "exception", "getSuppressed", "", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "defaultPlatformRandom", "Lkotlin/random/Random;", "getSystemClock", "Lkotlin/time/Clock;", "getOrDefault", "V", "K", "map", "", "key", "default", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent", "NewV", "Ljava/util/concurrent/ConcurrentMap;", "newValue", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "ReflectThrowable", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public class PlatformImplementations {
    public void addSuppressed(Throwable cause, Throwable exception) throws IllegalAccessException, InvocationTargetException {
        cause.getClass();
        exception.getClass();
        Method method = ReflectThrowable.addSuppressed;
        if (method != null) {
            method.invoke(cause, exception);
        }
    }

    public <K, V, NewV extends V> V computeIfAbsent(ConcurrentMap<K, V> map, K key, NewV newValue) {
        map.getClass();
        newValue.getClass();
        throw new UnsupportedOperationException("computeIfAbsent should not be called on the base PlatformImplementations.");
    }

    public Random defaultPlatformRandom() {
        return new FallbackThreadLocalRandom();
    }

    public MatchGroup getMatchResultNamedGroup(MatchResult matchResult, String name) {
        matchResult.getClass();
        name.getClass();
        throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
    }

    public <K, V> V getOrDefault(Map<K, ? extends V> map, K key, V v) {
        map.getClass();
        throw new UnsupportedOperationException("getOrDefault should not be called on the base PlatformImplementations.");
    }

    public List<Throwable> getSuppressed(Throwable exception) {
        Object objInvoke;
        List<Throwable> listAsList;
        exception.getClass();
        Method method = ReflectThrowable.getSuppressed;
        return (method == null || (objInvoke = method.invoke(exception, null)) == null || (listAsList = ArraysKt.asList((Throwable[]) objInvoke)) == null) ? CollectionsKt.emptyList() : listAsList;
    }

    public Clock getSystemClock() {
        throw new UnsupportedOperationException("getSystemClock should not be called on the base PlatformImplementations.");
    }
}

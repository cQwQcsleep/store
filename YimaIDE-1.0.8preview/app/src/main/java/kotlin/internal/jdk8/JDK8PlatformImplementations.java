package kotlin.internal.jdk8;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.internal.jdk8.JDK8PlatformImplementations;
import kotlin.jvm.functions.Function1;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;
import kotlin.ranges.IntRange;
import kotlin.text.MatchGroup;
import kotlin.time.Clock;
import kotlin.time.Instant;
import kotlin.time.jdk8.InstantConversionsJDK8Kt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u001fB\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0082\u0080\u0004J\u001c\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004JA\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000f0\u00122\u0006\u0010\u0013\u001a\u0002H\u00102\u0006\u0010\u0014\u001a\u0002H\u000fH\u0096\u0080\u0004¢\u0006\u0002\u0010\u0015JN\u0010\u0016\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u000f\"\n\b\u0002\u0010\u0017*\u0004\b\u0002H\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u000f0\u00182\u0006\u0010\u0013\u001a\u0002H\u00102\u0006\u0010\u0019\u001a\u0002H\u0017H\u0096\u0080\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004J\n\u0010\u001d\u001a\u00020\u001eH\u0096\u0080\u0004\u0082\u0002\u0004\n\u0002\b9¨\u0006 "}, d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations;", "Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "<init>", "()V", "sdkIsNullOrAtLeast", "", "version", "", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "getOrDefault", "V", "K", "map", "", "key", "default", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent", "NewV", "Ljava/util/concurrent/ConcurrentMap;", "newValue", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "defaultPlatformRandom", "Lkotlin/random/Random;", "getSystemClock", "Lkotlin/time/Clock;", "ReflectSdkVersion", "kotlin-stdlib-jdk8"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0084\b\u0092\u0002\u0002\b\u0007¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion;", "", "<init>", "()V", "sdkVersion", "", "Ljava/lang/Integer;", "Lkotlin/jvm/JvmField;", "kotlin-stdlib-jdk8"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class ReflectSdkVersion {
        public static final ReflectSdkVersion INSTANCE = new ReflectSdkVersion();
        public static final Integer sdkVersion;

        static {
            Integer num;
            Integer num2 = null;
            try {
                Object obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
                num = obj instanceof Integer ? (Integer) obj : null;
            } catch (Throwable unused) {
            }
            if (num != null && num.intValue() > 0) {
                num2 = num;
            }
            sdkVersion = num2;
        }

        private ReflectSdkVersion() {
        }
    }

    public static Object a(Object obj, Object obj2) {
        return obj;
    }

    public static Object b(Function1 function1, Object obj) {
        return function1.invoke(obj);
    }

    private final boolean sdkIsNullOrAtLeast(int version) {
        Integer num = ReflectSdkVersion.sdkVersion;
        return num == null || num.intValue() >= version;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.internal.PlatformImplementations
    public <K, V, NewV extends V> V computeIfAbsent(ConcurrentMap<K, V> map, K key, final NewV newValue) {
        map.getClass();
        newValue.getClass();
        if (sdkIsNullOrAtLeast(24)) {
            final Function1 function1 = new Function1() { // from class: ha7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return JDK8PlatformImplementations.a(newValue, obj);
                }
            };
            return map.computeIfAbsent(key, new Function() { // from class: ia7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return JDK8PlatformImplementations.b(function1, obj);
                }
            });
        }
        V vPutIfAbsent = map.putIfAbsent(key, newValue);
        return vPutIfAbsent == null ? newValue : vPutIfAbsent;
    }

    @Override // kotlin.internal.PlatformImplementations
    public Random defaultPlatformRandom() {
        return sdkIsNullOrAtLeast(34) ? new PlatformThreadLocalRandom() : super.defaultPlatformRandom();
    }

    @Override // kotlin.internal.PlatformImplementations
    public MatchGroup getMatchResultNamedGroup(MatchResult matchResult, String name) {
        matchResult.getClass();
        name.getClass();
        Matcher matcher = matchResult instanceof Matcher ? (Matcher) matchResult : null;
        if (matcher == null) {
            c41.a("Retrieving groups by name is not supported on this platform.");
            return null;
        }
        IntRange intRange = new IntRange(matcher.start(name), matcher.end(name) - 1);
        if (intRange.getStart().intValue() < 0) {
            return null;
        }
        String strGroup = matcher.group(name);
        strGroup.getClass();
        return new MatchGroup(strGroup, intRange);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.internal.PlatformImplementations
    public <K, V> V getOrDefault(Map<K, ? extends V> map, K key, V v) {
        map.getClass();
        if (sdkIsNullOrAtLeast(24)) {
            return map.getOrDefault(key, v);
        }
        V v2 = map.get(key);
        if (v2 != null) {
            return v2;
        }
        if (map.containsKey(key)) {
            return null;
        }
        return v;
    }

    @Override // kotlin.internal.PlatformImplementations
    public Clock getSystemClock() {
        return sdkIsNullOrAtLeast(26) ? new Clock() { // from class: kotlin.internal.jdk8.JDK8PlatformImplementations.getSystemClock.1
            public Instant now() {
                java.time.Instant instantNow = java.time.Instant.now();
                instantNow.getClass();
                return InstantConversionsJDK8Kt.toKotlinInstant(instantNow);
            }
        } : new Clock() { // from class: kotlin.internal.jdk8.JDK8PlatformImplementations.getSystemClock.2
            public Instant now() {
                return Instant.Companion.fromEpochMilliseconds(System.currentTimeMillis());
            }
        };
    }
}

package org.jetbrains.kotlin.fir.caches;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.time.Duration;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0002%&B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JJ\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u0005\"\b\b\u0000\u0010\u0006*\u00020\t\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00070\u000bH&JZ\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u0005\"\b\b\u0000\u0010\u0006*\u00020\t\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00070\u000bH&J|\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u0005\"\b\b\u0000\u0010\u0006*\u00020\t\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\u00112$\u0010\n\u001a \u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00110\u00120\u000b2\u001e\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u00150\u0014H&J}\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u0005\"\b\b\u0000\u0010\u0006*\u00020\t\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\b2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00070\u000bH&¢\u0006\u0004\b\u001f\u0010 J\"\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00070\"\"\u0004\b\u0000\u0010\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070#H&J\"\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00070\"\"\u0004\b\u0000\u0010\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070#H&¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "createCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "K", "V", "CONTEXT", Argument.Delimiters.none, "createValue", "Lkotlin/Function2;", "initialCapacity", Argument.Delimiters.none, "loadFactor", Argument.Delimiters.none, "createCacheWithPostCompute", "DATA", "Lkotlin/Pair;", "postCompute", "Lkotlin/Function3;", Argument.Delimiters.none, "createCacheWithSuggestedLimits", "expirationAfterAccess", "Lkotlin/time/Duration;", "maximumSize", Argument.Delimiters.none, "keyStrength", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$KeyReferenceStrength;", "valueStrength", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$ValueReferenceStrength;", "createCacheWithSuggestedLimits-n8is24I", "(Lkotlin/time/Duration;Ljava/lang/Long;Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$KeyReferenceStrength;Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$ValueReferenceStrength;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/caches/FirCache;", "createLazyValue", "Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "Lkotlin/Function0;", "createPossiblySoftLazyValue", "KeyReferenceStrength", "ValueReferenceStrength", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirCachesFactory implements FirSessionComponent {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$KeyReferenceStrength;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "STRONG", "WEAK", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum KeyReferenceStrength {
        STRONG,
        WEAK;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<KeyReferenceStrength> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory$ValueReferenceStrength;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "STRONG", "SOFT", "WEAK", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ValueReferenceStrength {
        STRONG,
        SOFT,
        WEAK;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ValueReferenceStrength> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: renamed from: createCacheWithSuggestedLimits-n8is24I$default, reason: not valid java name */
    public static /* synthetic */ FirCache m262createCacheWithSuggestedLimitsn8is24I$default(FirCachesFactory firCachesFactory, Duration duration, Long l, KeyReferenceStrength keyReferenceStrength, ValueReferenceStrength valueReferenceStrength, Function2 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: createCacheWithSuggestedLimits-n8is24I");
            return null;
        }
        if ((i & 1) != 0) {
            duration = null;
        }
        if ((i & 2) != 0) {
            l = null;
        }
        if ((i & 4) != 0) {
            keyReferenceStrength = KeyReferenceStrength.STRONG;
        }
        if ((i & 8) != 0) {
            valueReferenceStrength = ValueReferenceStrength.STRONG;
        }
        return firCachesFactory.mo263createCacheWithSuggestedLimitsn8is24I(duration, l, keyReferenceStrength, valueReferenceStrength, function2);
    }

    public abstract <K, V, CONTEXT> FirCache<K, V, CONTEXT> createCache(int initialCapacity, float loadFactor, Function2<? super K, ? super CONTEXT, ? extends V> createValue);

    public abstract <K, V, CONTEXT> FirCache<K, V, CONTEXT> createCache(Function2<? super K, ? super CONTEXT, ? extends V> createValue);

    public abstract <K, V, CONTEXT, DATA> FirCache<K, V, CONTEXT> createCacheWithPostCompute(Function2<? super K, ? super CONTEXT, ? extends Pair<? extends V, ? extends DATA>> createValue, Function3<? super K, ? super V, ? super DATA, Unit> postCompute);

    /* JADX INFO: renamed from: createCacheWithSuggestedLimits-n8is24I, reason: not valid java name */
    public abstract <K, V, CONTEXT> FirCache<K, V, CONTEXT> mo263createCacheWithSuggestedLimitsn8is24I(Duration expirationAfterAccess, Long maximumSize, KeyReferenceStrength keyStrength, ValueReferenceStrength valueStrength, Function2<? super K, ? super CONTEXT, ? extends V> createValue);

    public abstract <V> FirLazyValue<V> createLazyValue(Function0<? extends V> createValue);

    public abstract <V> FirLazyValue<V> createPossiblySoftLazyValue(Function0<? extends V> createValue);
}

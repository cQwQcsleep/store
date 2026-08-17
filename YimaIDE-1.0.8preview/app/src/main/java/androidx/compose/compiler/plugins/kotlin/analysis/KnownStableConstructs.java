package androidx.compose.compiler.plugins.kotlin.analysis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.ClosedRange;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/KnownStableConstructs;", "", "<init>", "()V", "stableTypes", "", "", "", "getStableTypes", "()Ljava/util/Map;", "stableFunctions", "getStableFunctions", "stableMarkers", "", "Lorg/jetbrains/kotlin/name/ClassId;", "getStableMarkers", "()Ljava/util/Set;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KnownStableConstructs {
    public static final KnownStableConstructs INSTANCE = new KnownStableConstructs();
    private static final Map<String, Integer> stableFunctions;
    private static final Set<ClassId> stableMarkers;
    private static final Map<String, Integer> stableTypes;

    static {
        String qualifiedName = Reflection.getOrCreateKotlinClass(Pair.class).getQualifiedName();
        qualifiedName.getClass();
        Pair pair = TuplesKt.to(qualifiedName, 3);
        String qualifiedName2 = Reflection.getOrCreateKotlinClass(Triple.class).getQualifiedName();
        qualifiedName2.getClass();
        Pair pair2 = TuplesKt.to(qualifiedName2, 7);
        String qualifiedName3 = Reflection.getOrCreateKotlinClass(Comparator.class).getQualifiedName();
        qualifiedName3.getClass();
        Pair pair3 = TuplesKt.to(qualifiedName3, 1);
        String qualifiedName4 = Reflection.getOrCreateKotlinClass(Result.class).getQualifiedName();
        qualifiedName4.getClass();
        Pair pair4 = TuplesKt.to(qualifiedName4, 1);
        String qualifiedName5 = Reflection.getOrCreateKotlinClass(ClosedRange.class).getQualifiedName();
        qualifiedName5.getClass();
        Pair pair5 = TuplesKt.to(qualifiedName5, 1);
        String qualifiedName6 = Reflection.getOrCreateKotlinClass(ClosedFloatingPointRange.class).getQualifiedName();
        qualifiedName6.getClass();
        Pair pair6 = TuplesKt.to(qualifiedName6, 1);
        Pair pair7 = TuplesKt.to("com.google.common.collect.ImmutableList", 1);
        Pair pair8 = TuplesKt.to("com.google.common.collect.ImmutableEnumMap", 3);
        Pair pair9 = TuplesKt.to("com.google.common.collect.ImmutableMap", 3);
        Pair pair10 = TuplesKt.to("com.google.common.collect.ImmutableEnumSet", 1);
        Pair pair11 = TuplesKt.to("com.google.common.collect.ImmutableSet", 1);
        Pair pair12 = TuplesKt.to("kotlinx.collections.immutable.ImmutableCollection", 1);
        Pair pair13 = TuplesKt.to("kotlinx.collections.immutable.ImmutableList", 1);
        Pair pair14 = TuplesKt.to("kotlinx.collections.immutable.ImmutableSet", 1);
        Pair pair15 = TuplesKt.to("kotlinx.collections.immutable.ImmutableMap", 3);
        Pair pair16 = TuplesKt.to("kotlinx.collections.immutable.PersistentCollection", 1);
        Pair pair17 = TuplesKt.to("kotlinx.collections.immutable.PersistentList", 1);
        Pair pair18 = TuplesKt.to("kotlinx.collections.immutable.PersistentSet", 1);
        Pair pair19 = TuplesKt.to("kotlinx.collections.immutable.PersistentMap", 3);
        Pair pair20 = TuplesKt.to("dagger.Lazy", 1);
        String qualifiedName7 = Reflection.getOrCreateKotlinClass(EmptyCoroutineContext.class).getQualifiedName();
        qualifiedName7.getClass();
        Pair pair21 = TuplesKt.to(qualifiedName7, 0);
        String qualifiedName8 = Reflection.getOrCreateKotlinClass(BigInteger.class).getQualifiedName();
        qualifiedName8.getClass();
        Pair pair22 = TuplesKt.to(qualifiedName8, 0);
        String qualifiedName9 = Reflection.getOrCreateKotlinClass(BigDecimal.class).getQualifiedName();
        qualifiedName9.getClass();
        Pair pair23 = TuplesKt.to(qualifiedName9, 0);
        String qualifiedName10 = Reflection.getOrCreateKotlinClass(Locale.class).getQualifiedName();
        qualifiedName10.getClass();
        stableTypes = MapsKt.mapOf(new Pair[]{pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, pair10, pair11, pair12, pair13, pair14, pair15, pair16, pair17, pair18, pair19, pair20, pair21, pair22, pair23, TuplesKt.to(qualifiedName10, 0)});
        stableFunctions = MapsKt.mapOf(new Pair[]{TuplesKt.to("kotlin.collections.emptyList", 0), TuplesKt.to("kotlin.collections.listOf", 1), TuplesKt.to("kotlin.collections.listOfNotNull", 1), TuplesKt.to("kotlin.collections.mapOf", 3), TuplesKt.to("kotlin.collections.emptyMap", 0), TuplesKt.to("kotlin.collections.setOf", 1), TuplesKt.to("kotlin.collections.emptySet", 0), TuplesKt.to("kotlin.to", 3), TuplesKt.to("kotlinx.collections.immutable.immutableListOf", 1), TuplesKt.to("kotlinx.collections.immutable.immutableSetOf", 1), TuplesKt.to("kotlinx.collections.immutable.immutableMapOf", 3), TuplesKt.to("kotlinx.collections.immutable.persistentListOf", 1), TuplesKt.to("kotlinx.collections.immutable.persistentSetOf", 1), TuplesKt.to("kotlinx.collections.immutable.persistentMapOf", 3)});
        FqName fqName = new FqName("com.google.errorprone.annotations");
        Name nameIdentifier = Name.identifier("Immutable");
        nameIdentifier.getClass();
        stableMarkers = SetsKt.setOf(new ClassId(fqName, nameIdentifier));
    }

    private KnownStableConstructs() {
    }

    public final Map<String, Integer> getStableFunctions() {
        return stableFunctions;
    }

    public final Set<ClassId> getStableMarkers() {
        return stableMarkers;
    }

    public final Map<String, Integer> getStableTypes() {
        return stableTypes;
    }
}

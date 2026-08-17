package org.jetbrains.kotlin.fir.caches;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0087@\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001)B\u001d\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\r\u001a\u00028\u00012\u0006\u0010\u000e\u001a\u00028\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0010H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001H\u0086\n¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÖ\u0083\u0004¢\u0006\u0004\b\u001f\u0010 J\u0011\u0010!\u001a\u00020\"HÖ\u0081\u0004¢\u0006\u0004\b#\u0010$J\u0011\u0010%\u001a\u00020&HÖ\u0081\u0004¢\u0006\u0004\b'\u0010(R,\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u00058\u0006X\u0087\u0004r\u0002\b\f¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0088\u0001\u0004Ê\u0001\u0002\b+\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/NullableMap;", "K", "V", Argument.Delimiters.none, "map", Argument.Delimiters.none, "constructor-impl", "(Ljava/util/Map;)Ljava/util/Map;", "getMap$annotations", "()V", "getMap", "()Ljava/util/Map;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "getOrElse", "key", "orElse", "Lkotlin/Function0;", "getOrElse-impl", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "set", Argument.Delimiters.none, "value", "set-impl", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "valuesSnapshot", Argument.Delimiters.none, "getValuesSnapshot-impl", "(Ljava/util/Map;)Ljava/util/Collection;", "equals", Argument.Delimiters.none, "other", "equals-impl", "(Ljava/util/Map;Ljava/lang/Object;)Z", "hashCode", Argument.Delimiters.none, "hashCode-impl", "(Ljava/util/Map;)I", "toString", Argument.Delimiters.none, "toString-impl", "(Ljava/util/Map;)Ljava/lang/String;", "NullValue", "org.jetbrains.kotlin:tree", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@JvmInline
public final class NullableMap<K, V> {
    private final Map<K, Object> map;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Ê\u0001\u0002\b\u0005¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/caches/NullableMap$NullValue;", Argument.Delimiters.none, "<init>", "()V", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/util/PrivateForInline;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    @PrivateForInline
    public static final class NullValue {
        public static final NullValue INSTANCE = new NullValue();

        private NullValue() {
        }
    }

    private /* synthetic */ NullableMap(Map map) {
        this.map = map;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ NullableMap m266boximpl(Map map) {
        return new NullableMap(map);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <K, V> Map<K, Object> m267constructorimpl(Map<K, Object> map) {
        map.getClass();
        return map;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ Map m268constructorimpl$default(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            map = new HashMap();
        }
        return m267constructorimpl(map);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m269equalsimpl(Map<K, Object> map, Object obj) {
        return (obj instanceof NullableMap) && Intrinsics.areEqual(map, ((NullableMap) obj).getMap());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m270equalsimpl0(Map<Object, Object> map, Map<Object, Object> map2) {
        return Intrinsics.areEqual(map, map2);
    }

    @PrivateForInline
    public static /* synthetic */ void getMap$annotations() {
    }

    /* JADX INFO: renamed from: getOrElse-impl, reason: not valid java name */
    public static final V m271getOrElseimpl(Map<K, Object> map, K k, Function0<? extends V> function0) {
        function0.getClass();
        V v = (V) map.get(k);
        if (v == null) {
            return (V) function0.invoke();
        }
        if (Intrinsics.areEqual(v, NullValue.INSTANCE)) {
            return null;
        }
        return v;
    }

    /* JADX INFO: renamed from: getValuesSnapshot-impl, reason: not valid java name */
    public static final Collection<V> m272getValuesSnapshotimpl(Map<K, Object> map) {
        Collection<Object> collectionValues = map.values();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            if (Intrinsics.areEqual(obj, NullValue.INSTANCE)) {
                obj = null;
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m273hashCodeimpl(Map<K, Object> map) {
        return map.hashCode();
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m274setimpl(Map<K, Object> map, K k, V v) {
        if (v == null) {
            v = (V) NullValue.INSTANCE;
        }
        map.put(k, v);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m275toStringimpl(Map<K, Object> map) {
        return "NullableMap(map=" + map + ')';
    }

    public boolean equals(Object obj) {
        return m269equalsimpl(this.map, obj);
    }

    public final Map<K, Object> getMap() {
        return this.map;
    }

    public int hashCode() {
        return m273hashCodeimpl(this.map);
    }

    public String toString() {
        return m275toStringimpl(this.map);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Map getMap() {
        return this.map;
    }
}

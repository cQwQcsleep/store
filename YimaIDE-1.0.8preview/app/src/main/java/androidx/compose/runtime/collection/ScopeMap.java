package androidx.compose.runtime.collection;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u001d\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0001¢\u0006\u0004\b\u0016\u0010\u0013J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ;\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u00002!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u000f0\u001eH\u0086\b¢\u0006\u0004\b!\u0010\"J;\u0010#\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00028\u00002!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00180\u001eH\u0086\b¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u000f¢\u0006\u0004\b'\u0010(J\u001d\u0010)\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001¢\u0006\u0004\b*\u0010+J5\u0010,\u001a\u00020\u000f2#\b\u0004\u0010-\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00180\u001eH\u0086\b¢\u0006\u0004\b.\u0010/J*\u00100\u001a\u00020\u000f2\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u001801H\u0086\b¢\u0006\u0004\b2\u00103J\u0015\u00104\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00028\u0001¢\u0006\u0004\b5\u00106J\u001f\u00107\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010908¢\u0006\u0004\b:\u0010;J\u0013\u0010<\u001a\u00020\u00182\b\u0010=\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010>\u001a\u00020\u000bHÖ\u0001J\t\u0010?\u001a\u00020@HÖ\u0001R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0088\u0001\u0004¨\u0006A"}, d2 = {"Landroidx/compose/runtime/collection/ScopeMap;", "Key", "", "Scope", "map", "Landroidx/collection/MutableScatterMap;", "constructor-impl", "(Landroidx/collection/MutableScatterMap;)Landroidx/collection/MutableScatterMap;", "getMap", "()Landroidx/collection/MutableScatterMap;", "size", "", "getSize-impl", "(Landroidx/collection/MutableScatterMap;)I", "add", "", "key", "scope", "add-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Ljava/lang/Object;)V", "set", "value", "set-impl", "contains", "", "element", "contains-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)Z", "forEachScopeOf", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forEachScopeOf-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "anyScopeOf", "anyScopeOf-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Z", "clear", "clear-impl", "(Landroidx/collection/MutableScatterMap;)V", "remove", "remove-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;Ljava/lang/Object;)Z", "removeScopeIf", "predicate", "removeScopeIf-impl", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function1;)V", "removeIf", "Lkotlin/Function2;", "removeIf-impl", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;)V", "removeScope", "removeScope-impl", "(Landroidx/collection/MutableScatterMap;Ljava/lang/Object;)V", "asMap", "", "", "asMap-impl", "(Landroidx/collection/MutableScatterMap;)Ljava/util/Map;", "equals", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class ScopeMap<Key, Scope> {
    private final MutableScatterMap<Object, Object> map;

    private /* synthetic */ ScopeMap(MutableScatterMap mutableScatterMap) {
        this.map = mutableScatterMap;
    }

    /* JADX INFO: renamed from: add-impl, reason: not valid java name */
    public static final void m2484addimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Scope scope) {
        int iFindInsertIndex = mutableScatterMap.findInsertIndex(key);
        boolean z = iFindInsertIndex < 0;
        Object obj = z ? null : ((ScatterMap) mutableScatterMap).values[iFindInsertIndex];
        if (obj != null) {
            if (obj instanceof MutableScatterSet) {
                ((MutableScatterSet) obj).add(scope);
            } else if (obj != scope) {
                MutableScatterSet mutableScatterSet = new MutableScatterSet(0, 1, (DefaultConstructorMarker) null);
                mutableScatterSet.add(obj);
                mutableScatterSet.add(scope);
                scope = (Scope) mutableScatterSet;
            }
            scope = (Scope) obj;
        }
        if (!z) {
            ((ScatterMap) mutableScatterMap).values[iFindInsertIndex] = scope;
            return;
        }
        int i = ~iFindInsertIndex;
        ((ScatterMap) mutableScatterMap).keys[i] = key;
        ((ScatterMap) mutableScatterMap).values[i] = scope;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0055 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x0057 A[LOOP:0: B:9:0x0018->B:21:0x0057, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:28:0x0067 A[SYNTHETIC] */
    /* JADX INFO: renamed from: anyScopeOf-impl, reason: not valid java name */
    public static final boolean m2485anyScopeOfimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Function1<? super Scope, Boolean> function1) {
        Object obj = mutableScatterMap.get(key);
        if (obj != null) {
            if (obj instanceof MutableScatterSet) {
                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
                Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
                long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i = 0;
                    while (true) {
                        long j = jArr[i];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i2 = 8 - ((~(i - length)) >>> 31);
                            for (int i3 = 0; i3 < i2; i3++) {
                                if ((255 & j) < 128 && ((Boolean) function1.invoke(objArr[(i << 3) + i3])).booleanValue()) {
                                    return true;
                                }
                                j >>= 8;
                            }
                            if (i2 == 8) {
                                if (i != length) {
                                    i++;
                                }
                            }
                        } else if (i != length) {
                            i++;
                        }
                    }
                }
            } else if (((Boolean) function1.invoke(obj)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0060 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:19:0x0062 A[LOOP:0: B:5:0x0012->B:19:0x0062, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:22:0x0065 A[EDGE_INSN: B:22:0x0065->B:20:0x0065 BREAK  A[LOOP:0: B:5:0x0012->B:19:0x0062], SYNTHETIC] */
    /* JADX INFO: renamed from: asMap-impl, reason: not valid java name */
    public static final Map<Key, Set<Scope>> m2486asMapimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        Set setMutableSetOf;
        HashMap map = new HashMap();
        Object[] objArr = ((ScatterMap) mutableScatterMap).keys;
        Object[] objArr2 = ((ScatterMap) mutableScatterMap).values;
        long[] jArr = ((ScatterMap) mutableScatterMap).metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i != length) {
                        break;
                        break;
                    }
                    i++;
                } else {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            Object obj2 = objArr2[i4];
                            obj.getClass();
                            if (obj2 instanceof MutableScatterSet) {
                                setMutableSetOf = ((MutableScatterSet) obj2).asSet();
                            } else {
                                obj2.getClass();
                                setMutableSetOf = SetsKt.mutableSetOf(new Object[]{obj2});
                            }
                            map.put(obj, setMutableSetOf);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    }
                    if (i != length) {
                        break;
                    }
                    i++;
                }
            }
        }
        return map;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ScopeMap m2487boximpl(MutableScatterMap mutableScatterMap) {
        return new ScopeMap(mutableScatterMap);
    }

    /* JADX INFO: renamed from: clear-impl, reason: not valid java name */
    public static final void m2488clearimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        mutableScatterMap.clear();
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <Key, Scope> MutableScatterMap<Object, Object> m2489constructorimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ MutableScatterMap m2490constructorimpl$default(MutableScatterMap mutableScatterMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            mutableScatterMap = ScatterMapKt.mutableScatterMapOf();
        }
        return m2489constructorimpl(mutableScatterMap);
    }

    /* JADX INFO: renamed from: contains-impl, reason: not valid java name */
    public static final boolean m2491containsimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key) {
        return mutableScatterMap.containsKey(key);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2492equalsimpl(MutableScatterMap<Object, Object> mutableScatterMap, Object obj) {
        return (obj instanceof ScopeMap) && Intrinsics.areEqual(mutableScatterMap, ((ScopeMap) obj).getMap());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2493equalsimpl0(MutableScatterMap<Object, Object> mutableScatterMap, MutableScatterMap<Object, Object> mutableScatterMap2) {
        return Intrinsics.areEqual(mutableScatterMap, mutableScatterMap2);
    }

    /* JADX INFO: renamed from: forEachScopeOf-impl, reason: not valid java name */
    public static final void m2494forEachScopeOfimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Function1<? super Scope, Unit> function1) {
        Object obj = mutableScatterMap.get(key);
        if (obj == null) {
            return;
        }
        if (!(obj instanceof MutableScatterSet)) {
            function1.invoke(obj);
            return;
        }
        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
        Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
        long[] jArr = ((ScatterSet) mutableScatterSet).metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        function1.invoke(objArr[(i << 3) + i3]);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    /* JADX INFO: renamed from: getSize-impl, reason: not valid java name */
    public static final int m2495getSizeimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.getSize();
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2496hashCodeimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return mutableScatterMap.hashCode();
    }

    /* JADX INFO: renamed from: remove-impl, reason: not valid java name */
    public static final boolean m2497removeimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Scope scope) {
        Object obj = mutableScatterMap.get(key);
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MutableScatterSet)) {
            if (!Intrinsics.areEqual(obj, scope)) {
                return false;
            }
            mutableScatterMap.remove(key);
            return true;
        }
        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
        boolean zRemove = mutableScatterSet.remove(scope);
        if (zRemove && mutableScatterSet.isEmpty()) {
            mutableScatterMap.remove(key);
        }
        return zRemove;
    }

    /* JADX INFO: renamed from: removeIf-impl, reason: not valid java name */
    public static final void m2498removeIfimpl(MutableScatterMap<Object, Object> mutableScatterMap, Function2<? super Key, ? super Scope, Boolean> function2) {
        long[] jArr;
        long[] jArr2;
        long j;
        char c;
        long j2;
        int i;
        int i2;
        boolean zBooleanValue;
        long[] jArr3;
        long j3;
        long[] jArr4 = ((ScatterMap) mutableScatterMap).metadata;
        int length = jArr4.length - 2;
        if (length < 0) {
            return;
        }
        int i3 = 0;
        while (true) {
            long j4 = jArr4[i3];
            char c2 = 7;
            long j5 = -9187201950435737472L;
            if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i4 = 8;
                int i5 = 8 - ((~(i3 - length)) >>> 31);
                int i6 = 0;
                while (i6 < i5) {
                    if ((j4 & 255) < 128) {
                        int i7 = (i3 << 3) + i6;
                        c = c2;
                        Object obj = ((ScatterMap) mutableScatterMap).keys[i7];
                        j2 = j5;
                        Object obj2 = ((ScatterMap) mutableScatterMap).values[i7];
                        obj.getClass();
                        if (obj2 instanceof MutableScatterSet) {
                            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                            Object[] objArr = ((ScatterSet) mutableScatterSet).elements;
                            long[] jArr5 = ((ScatterSet) mutableScatterSet).metadata;
                            int length2 = jArr5.length - 2;
                            if (length2 >= 0) {
                                j = j4;
                                int i8 = i4;
                                int i9 = 0;
                                while (true) {
                                    long j6 = jArr5[i9];
                                    Object[] objArr2 = objArr;
                                    i = i6;
                                    if ((((~j6) << c) & j6 & j2) != j2) {
                                        int i10 = 8 - ((~(i9 - length2)) >>> 31);
                                        int i11 = 0;
                                        while (i11 < i10) {
                                            if ((j6 & 255) < 128) {
                                                jArr3 = jArr4;
                                                int i12 = (i9 << 3) + i11;
                                                j3 = j6;
                                                if (((Boolean) function2.invoke(obj, objArr2[i12])).booleanValue()) {
                                                    mutableScatterSet.removeElementAt(i12);
                                                }
                                            } else {
                                                jArr3 = jArr4;
                                                j3 = j6;
                                            }
                                            j6 = j3 >> i8;
                                            i11++;
                                            jArr4 = jArr3;
                                        }
                                        jArr2 = jArr4;
                                        if (i10 != i8) {
                                            break;
                                        }
                                    } else {
                                        jArr2 = jArr4;
                                    }
                                    if (i9 == length2) {
                                        break;
                                    }
                                    i9++;
                                    i6 = i;
                                    objArr = objArr2;
                                    jArr4 = jArr2;
                                    i8 = 8;
                                }
                            } else {
                                jArr2 = jArr4;
                                j = j4;
                                i = i6;
                            }
                            zBooleanValue = mutableScatterSet.isEmpty();
                        } else {
                            jArr2 = jArr4;
                            j = j4;
                            i = i6;
                            obj2.getClass();
                            zBooleanValue = ((Boolean) function2.invoke(obj, obj2)).booleanValue();
                        }
                        if (zBooleanValue) {
                            mutableScatterMap.removeValueAt(i7);
                        }
                        i2 = 8;
                    } else {
                        jArr2 = jArr4;
                        j = j4;
                        c = c2;
                        j2 = j5;
                        i = i6;
                        i2 = i4;
                    }
                    j4 = j >> i2;
                    i6 = i + 1;
                    i4 = i2;
                    c2 = c;
                    j5 = j2;
                    jArr4 = jArr2;
                }
                jArr = jArr4;
                if (i5 != i4) {
                    return;
                }
            } else {
                jArr = jArr4;
            }
            if (i3 == length) {
                return;
            }
            i3++;
            jArr4 = jArr;
        }
    }

    /* JADX INFO: renamed from: removeScope-impl, reason: not valid java name */
    public static final void m2499removeScopeimpl(MutableScatterMap<Object, Object> mutableScatterMap, Scope scope) {
        boolean zIsEmpty;
        long[] jArr = ((ScatterMap) mutableScatterMap).metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        Object obj = ((ScatterMap) mutableScatterMap).keys[i4];
                        Object obj2 = ((ScatterMap) mutableScatterMap).values[i4];
                        if (obj2 instanceof MutableScatterSet) {
                            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                            mutableScatterSet.remove(scope);
                            zIsEmpty = mutableScatterSet.isEmpty();
                        } else {
                            zIsEmpty = obj2 == scope;
                        }
                        if (zIsEmpty) {
                            mutableScatterMap.removeValueAt(i4);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    /* JADX INFO: renamed from: removeScopeIf-impl, reason: not valid java name */
    public static final void m2500removeScopeIfimpl(MutableScatterMap<Object, Object> mutableScatterMap, Function1<? super Scope, Boolean> function1) {
        long[] jArr;
        long[] jArr2;
        long j;
        char c;
        long j2;
        int i;
        boolean zBooleanValue;
        MutableScatterSet mutableScatterSet;
        long[] jArr3;
        int i2;
        MutableScatterSet mutableScatterSet2;
        long[] jArr4 = ((ScatterMap) mutableScatterMap).metadata;
        int length = jArr4.length - 2;
        if (length < 0) {
            return;
        }
        int i3 = 0;
        while (true) {
            long j3 = jArr4[i3];
            char c2 = 7;
            long j4 = -9187201950435737472L;
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i4 = 8;
                int i5 = 8 - ((~(i3 - length)) >>> 31);
                int i6 = 0;
                while (i6 < i5) {
                    if ((j3 & 255) < 128) {
                        int i7 = (i3 << 3) + i6;
                        c = c2;
                        Object obj = ((ScatterMap) mutableScatterMap).keys[i7];
                        Object obj2 = ((ScatterMap) mutableScatterMap).values[i7];
                        j2 = j4;
                        if (obj2 instanceof MutableScatterSet) {
                            MutableScatterSet mutableScatterSet3 = (MutableScatterSet) obj2;
                            Object[] objArr = ((ScatterSet) mutableScatterSet3).elements;
                            long[] jArr5 = ((ScatterSet) mutableScatterSet3).metadata;
                            int length2 = jArr5.length - 2;
                            if (length2 >= 0) {
                                int i8 = i4;
                                MutableScatterSet mutableScatterSet4 = mutableScatterSet3;
                                int i9 = 0;
                                while (true) {
                                    long j5 = jArr5[i9];
                                    j = j3;
                                    if ((((~j5) << c) & j5 & j2) != j2) {
                                        int i10 = 8 - ((~(i9 - length2)) >>> 31);
                                        int i11 = 0;
                                        while (i11 < i10) {
                                            if ((j5 & 255) < 128) {
                                                jArr3 = jArr4;
                                                int i12 = (i9 << 3) + i11;
                                                i2 = i11;
                                                if (((Boolean) function1.invoke(objArr[i12])).booleanValue()) {
                                                    mutableScatterSet2 = mutableScatterSet4;
                                                    mutableScatterSet2.removeElementAt(i12);
                                                }
                                                j5 >>= i8;
                                                mutableScatterSet4 = mutableScatterSet2;
                                                i11 = i2 + 1;
                                                jArr4 = jArr3;
                                            } else {
                                                jArr3 = jArr4;
                                                i2 = i11;
                                            }
                                            mutableScatterSet2 = mutableScatterSet4;
                                            j5 >>= i8;
                                            mutableScatterSet4 = mutableScatterSet2;
                                            i11 = i2 + 1;
                                            jArr4 = jArr3;
                                        }
                                        jArr2 = jArr4;
                                        mutableScatterSet = mutableScatterSet4;
                                        if (i10 != i8) {
                                            break;
                                        }
                                    } else {
                                        jArr2 = jArr4;
                                        mutableScatterSet = mutableScatterSet4;
                                    }
                                    if (i9 == length2) {
                                        break;
                                    }
                                    i9++;
                                    mutableScatterSet4 = mutableScatterSet;
                                    j3 = j;
                                    jArr4 = jArr2;
                                    i8 = 8;
                                }
                            } else {
                                jArr2 = jArr4;
                                j = j3;
                                mutableScatterSet = mutableScatterSet3;
                            }
                            zBooleanValue = mutableScatterSet.isEmpty();
                        } else {
                            jArr2 = jArr4;
                            j = j3;
                            obj2.getClass();
                            zBooleanValue = ((Boolean) function1.invoke(obj2)).booleanValue();
                        }
                        if (zBooleanValue) {
                            mutableScatterMap.removeValueAt(i7);
                        }
                        i = 8;
                    } else {
                        jArr2 = jArr4;
                        j = j3;
                        c = c2;
                        j2 = j4;
                        i = i4;
                    }
                    j3 = j >> i;
                    i6++;
                    i4 = i;
                    c2 = c;
                    j4 = j2;
                    jArr4 = jArr2;
                }
                jArr = jArr4;
                if (i5 != i4) {
                    return;
                }
            } else {
                jArr = jArr4;
            }
            if (i3 == length) {
                return;
            }
            i3++;
            jArr4 = jArr;
        }
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m2501setimpl(MutableScatterMap<Object, Object> mutableScatterMap, Key key, Scope scope) {
        mutableScatterMap.set(key, scope);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2502toStringimpl(MutableScatterMap<Object, Object> mutableScatterMap) {
        return "ScopeMap(map=" + mutableScatterMap + ')';
    }

    public boolean equals(Object other) {
        return m2492equalsimpl(this.map, other);
    }

    public final MutableScatterMap<Object, Object> getMap() {
        return this.map;
    }

    public int hashCode() {
        return m2496hashCodeimpl(this.map);
    }

    public String toString() {
        return m2502toStringimpl(this.map);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ MutableScatterMap getMap() {
        return this.map;
    }
}

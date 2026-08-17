package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.ComposerImplKt;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.apk.ApkUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001e\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002\u001a5\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u000b\"\b\b\u0000\u0010\f*\u00020\t\"\b\b\u0001\u0010\r*\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u000f\u001a(\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\tH\u0002\u001a\u001a\u0010\u0014\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0007H\u0002\u001a\u001a\u0010\u0018\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0007H\u0002\u001a,\u0010\u0019\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00160\u001a2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\tH\u0002\u001a$\u0010\u001e\u001a\u0004\u0018\u00010\u0016*\b\u0012\u0004\u0012\u00020\u00160\u001a2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002\u001a\u001c\u0010!\u001a\u0004\u0018\u00010\u0016*\b\u0012\u0004\u0012\u00020\u00160\u001a2\u0006\u0010\u0017\u001a\u00020\u0007H\u0002\u001a\"\u0010\"\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00160\u001a2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002\u001a7\u0010#\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010%H\u0082\b\u001a\f\u0010&\u001a\u00020\u0007*\u00020'H\u0002\u001a\f\u0010(\u001a\u00020'*\u00020\u0007H\u0002\u001a\u001c\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015*\u00020*2\u0006\u0010+\u001a\u00020,H\u0002\u001a\u001c\u0010-\u001a\u00020\u0007*\u00020.2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0007H\u0002\u001a$\u00100\u001a\u00020\u0007*\u00020.2\u0006\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0007H\u0002\"\u0018\u00104\u001a\u00020\t*\u0002058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00107\"\u001e\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u001609j\b\u0012\u0004\u0012\u00020\u0016`:X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010;\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010<\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"deactivateCurrentGroup", "", "Landroidx/compose/runtime/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "removeData", "index", "", ApkUtil.NAME_data, "", "multiMap", "Landroidx/compose/runtime/collection/MultiValueMap;", "K", "V", "initialCapacity", "(I)Landroidx/collection/MutableScatterMap;", "getKey", "value", "left", "right", "findLocation", "", "Landroidx/compose/runtime/Invalidation;", "location", "findInsertLocation", "insertIfMissing", "", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "instance", "firstInRange", "start", "end", "removeLocation", "removeRange", "forEachInRange", "block", "Lkotlin/Function1;", "asInt", "", "asBool", "collectNodesFrom", "Landroidx/compose/runtime/SlotTable;", "anchor", "Landroidx/compose/runtime/Anchor;", "distanceFrom", "Landroidx/compose/runtime/SlotReader;", "root", "nearestCommonRootOf", "a", "b", "common", "joinedKey", "Landroidx/compose/runtime/KeyInfo;", "getJoinedKey", "(Landroidx/compose/runtime/KeyInfo;)Ljava/lang/Object;", "InvalidationLocationAscending", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "rootKey", "nodeKey", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposerImplKt {
    private static final Comparator<Invalidation> InvalidationLocationAscending = new Comparator() { // from class: androidx.compose.runtime.c
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComposerImplKt.a((Invalidation) obj, (Invalidation) obj2);
        }
    };
    public static final int nodeKey = 125;
    public static final int rootKey = 100;

    public static int a(Invalidation invalidation, Invalidation invalidation2) {
        return Intrinsics.compare(invalidation.getLocation(), invalidation2.getLocation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean asBool(int i) {
        return i != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int asInt(boolean z) {
        return z ? 1 : 0;
    }

    public static Unit b(RememberManager rememberManager, SlotWriter slotWriter, int i, Object obj) {
        if (obj instanceof ComposeNodeLifecycleCallback) {
            rememberManager.deactivating((ComposeNodeLifecycleCallback) obj);
        } else if (!(obj instanceof ReusableRememberObserverHolder)) {
            if (obj instanceof RememberObserverHolder) {
                removeData(slotWriter, i, obj);
                rememberManager.forgetting((RememberObserverHolder) obj);
            } else if (obj instanceof RecomposeScopeImpl) {
                removeData(slotWriter, i, obj);
                ((RecomposeScopeImpl) obj).release();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Object> collectNodesFrom(SlotTable slotTable, Anchor anchor) {
        ArrayList arrayList = new ArrayList();
        SlotReader slotReaderOpenReader = slotTable.openReader();
        try {
            collectNodesFrom$lambda$0$collectFromGroup(slotReaderOpenReader, arrayList, slotTable.anchorIndex(anchor));
            Unit unit = Unit.INSTANCE;
            return arrayList;
        } finally {
            slotReaderOpenReader.close();
        }
    }

    private static final void collectNodesFrom$lambda$0$collectFromGroup(SlotReader slotReader, List<Object> list, int i) {
        if (slotReader.isNode(i)) {
            list.add(slotReader.node(i));
            return;
        }
        int iGroupSize = i + 1;
        int iGroupSize2 = i + slotReader.groupSize(i);
        while (iGroupSize < iGroupSize2) {
            collectNodesFrom$lambda$0$collectFromGroup(slotReader, list, iGroupSize);
            iGroupSize += slotReader.groupSize(iGroupSize);
        }
    }

    public static final void deactivateCurrentGroup(final SlotWriter slotWriter, final RememberManager rememberManager) {
        slotWriter.forAllDataInRememberOrder(slotWriter.getCurrentGroup(), new Function2() { // from class: en2
            public final Object invoke(Object obj, Object obj2) {
                return ComposerImplKt.b(rememberManager, slotWriter, ((Integer) obj).intValue(), obj2);
            }
        });
    }

    private static final int distanceFrom(SlotReader slotReader, int i, int i2) {
        int i3 = 0;
        while (i > 0 && i != i2) {
            i = slotReader.parent(i);
            i3++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int findInsertLocation(List<Invalidation> list, int i) {
        int iFindLocation = findLocation(list, i);
        return iFindLocation < 0 ? -(iFindLocation + 1) : iFindLocation;
    }

    private static final int findLocation(List<Invalidation> list, int i) {
        int size = list.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            int iCompare = Intrinsics.compare(list.get(i3).getLocation(), i);
            if (iCompare < 0) {
                i2 = i3 + 1;
            } else {
                if (iCompare <= 0) {
                    return i3;
                }
                size = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation firstInRange(List<Invalidation> list, int i, int i2) {
        int iFindInsertLocation = findInsertLocation(list, i);
        if (iFindInsertLocation >= list.size()) {
            return null;
        }
        Invalidation invalidation = list.get(iFindInsertLocation);
        if (invalidation.getLocation() < i2) {
            return invalidation;
        }
        return null;
    }

    private static final void forEachInRange(List<Invalidation> list, int i, int i2, Function1<? super Invalidation, Unit> function1) {
        for (int iFindInsertLocation = findInsertLocation(list, i); iFindInsertLocation < list.size(); iFindInsertLocation++) {
            Invalidation invalidation = list.get(iFindInsertLocation);
            if (invalidation.getLocation() >= i2) {
                return;
            }
            function1.invoke(invalidation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getJoinedKey(KeyInfo keyInfo) {
        return keyInfo.getObjectKey() != null ? new JoinedKey(Integer.valueOf(keyInfo.getKey()), keyInfo.getObjectKey()) : Integer.valueOf(keyInfo.getKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getKey(Object obj, Object obj2, Object obj3) {
        JoinedKey joinedKey = obj instanceof JoinedKey ? (JoinedKey) obj : null;
        if (joinedKey == null) {
            return null;
        }
        if (Intrinsics.areEqual(joinedKey.getLeft(), obj2) && Intrinsics.areEqual(joinedKey.getRight(), obj3)) {
            return obj;
        }
        Object key = getKey(joinedKey.getLeft(), obj2, obj3);
        return key == null ? getKey(joinedKey.getRight(), obj2, obj3) : key;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertIfMissing(List<Invalidation> list, int i, RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        int iFindLocation = findLocation(list, i);
        if (iFindLocation < 0) {
            int i2 = -(iFindLocation + 1);
            if (!(obj instanceof DerivedState)) {
                obj = null;
            }
            list.add(i2, new Invalidation(recomposeScopeImpl, i, obj));
            return;
        }
        Invalidation invalidation = list.get(iFindLocation);
        if (!(obj instanceof DerivedState)) {
            invalidation.setInstances(null);
            return;
        }
        Object instances = invalidation.getInstances();
        if (instances == null) {
            invalidation.setInstances(obj);
        } else if (instances instanceof MutableScatterSet) {
            ((MutableScatterSet) instances).add(obj);
        } else {
            invalidation.setInstances(ScatterSetKt.mutableScatterSetOf(instances, obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> MutableScatterMap<Object, Object> multiMap(int i) {
        return MultiValueMap.m2467constructorimpl(new MutableScatterMap(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nearestCommonRootOf(SlotReader slotReader, int i, int i2, int i3) {
        if (i != i2) {
            if (i == i3 || i2 == i3) {
                return i3;
            }
            if (slotReader.parent(i) == i2) {
                return i2;
            }
            if (slotReader.parent(i2) != i) {
                if (slotReader.parent(i) == slotReader.parent(i2)) {
                    return slotReader.parent(i);
                }
                int iDistanceFrom = distanceFrom(slotReader, i, i3);
                int iDistanceFrom2 = distanceFrom(slotReader, i2, i3);
                int i4 = iDistanceFrom - iDistanceFrom2;
                for (int i5 = 0; i5 < i4; i5++) {
                    i = slotReader.parent(i);
                }
                int i6 = iDistanceFrom2 - iDistanceFrom;
                for (int i7 = 0; i7 < i6; i7++) {
                    i2 = slotReader.parent(i2);
                }
                while (i != i2) {
                    i = slotReader.parent(i);
                    i2 = slotReader.parent(i2);
                }
                return i;
            }
        }
        return i;
    }

    private static final void removeData(SlotWriter slotWriter, int i, Object obj) {
        Object objClear = slotWriter.clear(i);
        if (obj == objClear) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Slot table is out of sync (expected " + obj + ", got " + objClear + ')');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation removeLocation(List<Invalidation> list, int i) {
        int iFindLocation = findLocation(list, i);
        if (iFindLocation >= 0) {
            return list.remove(iFindLocation);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeRange(List<Invalidation> list, int i, int i2) {
        int iFindInsertLocation = findInsertLocation(list, i);
        while (iFindInsertLocation < list.size() && list.get(iFindInsertLocation).getLocation() < i2) {
            list.remove(iFindInsertLocation);
        }
    }
}

package androidx.compose.runtime;

import androidx.collection.IntList;
import androidx.collection.MutableIntList;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u000fJ\r\u0010\u0012\u001a\u00020\t¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\t¢\u0006\u0004\b\u0016\u0010\u0014J\r\u0010\u0017\u001a\u00020\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\tHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u001f"}, d2 = {"Landroidx/compose/runtime/PrioritySet;", "", "list", "Landroidx/collection/MutableIntList;", "constructor-impl", "(Landroidx/collection/MutableIntList;)Landroidx/collection/MutableIntList;", "add", "", "value", "", "add-impl", "(Landroidx/collection/MutableIntList;I)V", "isEmpty", "", "isEmpty-impl", "(Landroidx/collection/MutableIntList;)Z", "isNotEmpty", "isNotEmpty-impl", "peek", "peek-impl", "(Landroidx/collection/MutableIntList;)I", "takeMax", "takeMax-impl", "validateHeap", "validateHeap-impl", "(Landroidx/collection/MutableIntList;)V", "equals", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class PrioritySet {
    private final MutableIntList list;

    private /* synthetic */ PrioritySet(MutableIntList mutableIntList) {
        this.list = mutableIntList;
    }

    /* JADX INFO: renamed from: add-impl, reason: not valid java name */
    public static final void m2347addimpl(MutableIntList mutableIntList, int i) {
        if (((IntList) mutableIntList)._size == 0 || !(mutableIntList.get(0) == i || mutableIntList.get(((IntList) mutableIntList)._size - 1) == i)) {
            int i2 = ((IntList) mutableIntList)._size;
            mutableIntList.add(i);
            while (i2 > 0) {
                int i3 = ((i2 + 1) >>> 1) - 1;
                int i4 = mutableIntList.get(i3);
                if (i <= i4) {
                    break;
                }
                mutableIntList.set(i2, i4);
                i2 = i3;
            }
            mutableIntList.set(i2, i);
        }
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PrioritySet m2348boximpl(MutableIntList mutableIntList) {
        return new PrioritySet(mutableIntList);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static MutableIntList m2349constructorimpl(MutableIntList mutableIntList) {
        return mutableIntList;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ MutableIntList m2350constructorimpl$default(MutableIntList mutableIntList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            mutableIntList = new MutableIntList(0, 1, (DefaultConstructorMarker) null);
        }
        return m2349constructorimpl(mutableIntList);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2351equalsimpl(MutableIntList mutableIntList, Object obj) {
        return (obj instanceof PrioritySet) && Intrinsics.areEqual(mutableIntList, ((PrioritySet) obj).getList());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2352equalsimpl0(MutableIntList mutableIntList, MutableIntList mutableIntList2) {
        return Intrinsics.areEqual(mutableIntList, mutableIntList2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2353hashCodeimpl(MutableIntList mutableIntList) {
        return mutableIntList.hashCode();
    }

    /* JADX INFO: renamed from: isEmpty-impl, reason: not valid java name */
    public static final boolean m2354isEmptyimpl(MutableIntList mutableIntList) {
        return ((IntList) mutableIntList)._size == 0;
    }

    /* JADX INFO: renamed from: isNotEmpty-impl, reason: not valid java name */
    public static final boolean m2355isNotEmptyimpl(MutableIntList mutableIntList) {
        return ((IntList) mutableIntList)._size != 0;
    }

    /* JADX INFO: renamed from: peek-impl, reason: not valid java name */
    public static final int m2356peekimpl(MutableIntList mutableIntList) {
        return mutableIntList.first();
    }

    /* JADX INFO: renamed from: takeMax-impl, reason: not valid java name */
    public static final int m2357takeMaximpl(MutableIntList mutableIntList) {
        int i;
        int i2 = ((IntList) mutableIntList)._size;
        int i3 = mutableIntList.get(0);
        while (((IntList) mutableIntList)._size != 0 && mutableIntList.get(0) == i3) {
            mutableIntList.set(0, mutableIntList.last());
            mutableIntList.removeAt(((IntList) mutableIntList)._size - 1);
            int i4 = ((IntList) mutableIntList)._size;
            int i5 = i4 >>> 1;
            int i6 = 0;
            while (i6 < i5) {
                int i7 = mutableIntList.get(i6);
                int i8 = (i6 + 1) * 2;
                int i9 = i8 - 1;
                int i10 = mutableIntList.get(i9);
                if (i8 < i4 && (i = mutableIntList.get(i8)) > i10) {
                    if (i <= i7) {
                        break;
                    }
                    mutableIntList.set(i6, i);
                    mutableIntList.set(i8, i7);
                    i6 = i8;
                } else {
                    if (i10 <= i7) {
                        break;
                    }
                    mutableIntList.set(i6, i10);
                    mutableIntList.set(i9, i7);
                    i6 = i9;
                }
            }
        }
        return i3;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2358toStringimpl(MutableIntList mutableIntList) {
        return "PrioritySet(list=" + mutableIntList + ')';
    }

    /* JADX INFO: renamed from: validateHeap-impl, reason: not valid java name */
    public static final void m2359validateHeapimpl(MutableIntList mutableIntList) {
        int i = ((IntList) mutableIntList)._size;
        int i2 = i / 2;
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            int i5 = i4 * 2;
            boolean z = true;
            if (!(mutableIntList.get(i3) >= mutableIntList.get(i5 + (-1)))) {
                PreconditionsKt.throwIllegalStateException("Check failed.");
            }
            if (i5 < i && mutableIntList.get(i3) < mutableIntList.get(i5)) {
                z = false;
            }
            if (!z) {
                PreconditionsKt.throwIllegalStateException("Check failed.");
            }
            i3 = i4;
        }
    }

    public boolean equals(Object other) {
        return m2351equalsimpl(this.list, other);
    }

    public int hashCode() {
        return m2353hashCodeimpl(this.list);
    }

    public String toString() {
        return m2358toStringimpl(this.list);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ MutableIntList getList() {
        return this.list;
    }
}

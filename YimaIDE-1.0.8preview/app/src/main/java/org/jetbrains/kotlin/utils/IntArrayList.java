package org.jetbrains.kotlin.utils;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.utils.IntArrayList;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0003H\u0002J\u0006\u0010\b\u001a\u00020\u0003J\u0011\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0086\u0002J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0003J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/utils/IntArrayList;", "", "initialCapacity", "", "<init>", "(I)V", "data", "", "size", "trimToSize", "", "ensureCapacity", "minCapacity", "isEmpty", "", "()Z", "get", "index", "add", "o", "toString", "", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class IntArrayList {
    private int[] data;
    private int size;

    public IntArrayList(int i) {
        this.data = new int[i];
    }

    public static CharSequence a(IntArrayList intArrayList, int i) {
        return String.valueOf(intArrayList.get(i));
    }

    private final void ensureCapacity(int minCapacity) {
        int[] iArr = this.data;
        if (minCapacity > iArr.length) {
            this.data = Arrays.copyOf(iArr, Math.max(((iArr.length * 3) / 2) + 1, minCapacity));
        }
    }

    public final void add(int o) {
        ensureCapacity(this.size + 1);
        int[] iArr = this.data;
        int i = this.size;
        this.size = i + 1;
        iArr[i] = o;
    }

    public final int get(int index) {
        return this.data[index];
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    /* JADX INFO: renamed from: size, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    public String toString() {
        return kotlin.collections.CollectionsKt.joinToString$default(RangesKt.until(0, this.size), ", ", "[", "]", 0, (CharSequence) null, new Function1() { // from class: vr6
            public final Object invoke(Object obj) {
                return IntArrayList.a(this.b, ((Integer) obj).intValue());
            }
        }, 24, (Object) null);
    }

    public final void trimToSize() {
        int i = this.size;
        int[] iArr = this.data;
        if (i < iArr.length) {
            this.data = Arrays.copyOf(iArr, i);
        }
    }
}

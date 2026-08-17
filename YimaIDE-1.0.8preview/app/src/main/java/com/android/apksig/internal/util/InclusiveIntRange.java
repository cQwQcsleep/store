package com.android.apksig.internal.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class InclusiveIntRange {
    private final int max;
    private final int min;

    private InclusiveIntRange(int i, int i2) {
        this.min = i;
        this.max = i2;
    }

    public static InclusiveIntRange from(int i) {
        return new InclusiveIntRange(i, Integer.MAX_VALUE);
    }

    public static InclusiveIntRange fromTo(int i, int i2) {
        return new InclusiveIntRange(i, i2);
    }

    public int getMax() {
        return this.max;
    }

    public int getMin() {
        return this.min;
    }

    public List<InclusiveIntRange> getValuesNotIn(List<InclusiveIntRange> list) {
        if (list.isEmpty()) {
            return Collections.singletonList(this);
        }
        int i = this.min;
        ArrayList arrayList = null;
        for (InclusiveIntRange inclusiveIntRange : list) {
            int i2 = inclusiveIntRange.max;
            if (i <= i2) {
                int i3 = inclusiveIntRange.min;
                if (i < i3) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fromTo(i, i3 - 1));
                }
                if (i2 >= this.max) {
                    return arrayList != null ? arrayList : Collections.EMPTY_LIST;
                }
                i = i2 + 1;
            }
        }
        if (i <= this.max) {
            if (arrayList == null) {
                arrayList = new ArrayList(1);
            }
            arrayList.add(fromTo(i, this.max));
        }
        return arrayList != null ? arrayList : Collections.EMPTY_LIST;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.min);
        sb.append(", ");
        if (this.max < Integer.MAX_VALUE) {
            str = this.max + "]";
        } else {
            str = "∞)";
        }
        sb.append(str);
        return sb.toString();
    }
}

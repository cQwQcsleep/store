package it.unimi.dsi.fastutil;

import defpackage.pnd;
import defpackage.sg0;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Arrays {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private Arrays() {
    }

    public static void ensureFromTo(int i, int i2, int i3) {
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException("Start index (" + i2 + ") is negative");
        }
        if (i2 > i3) {
            pnd.a("Start index (", i2, ") is greater than end index (", i3, ")");
        } else {
            if (i3 <= i) {
                return;
            }
            sg0.a("End index (", i3, i);
        }
    }

    public static void ensureOffsetLength(int i, int i2, int i3) {
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException("Offset (" + i2 + ") is negative");
        }
        if (i3 < 0) {
            ty8.a("Length (", i3, ") is negative");
            return;
        }
        if (i3 <= i - i2) {
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Last index (" + (((long) i2) + ((long) i3)) + ") is greater than array length (" + i + ")");
    }
}

package defpackage;

import it.unimi.dsi.fastutil.floats.FloatComparator;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class zh5 implements FloatComparator, Serializable {
    public final /* synthetic */ FloatComparator b;
    public final /* synthetic */ FloatComparator c;

    public /* synthetic */ zh5(FloatComparator floatComparator, FloatComparator floatComparator2) {
        this.b = floatComparator;
        this.c = floatComparator2;
    }

    @Override // it.unimi.dsi.fastutil.floats.FloatComparator
    public final int compare(float f, float f2) {
        return FloatComparator.D(this.b, this.c, f, f2);
    }
}

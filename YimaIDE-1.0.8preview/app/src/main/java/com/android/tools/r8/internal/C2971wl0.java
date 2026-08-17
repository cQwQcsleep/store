package com.android.tools.r8.internal;

import com.android.tools.r8.inspector.BooleanValueInspector;
import com.android.tools.r8.inspector.ByteValueInspector;
import com.android.tools.r8.inspector.CharValueInspector;
import com.android.tools.r8.inspector.DoubleValueInspector;
import com.android.tools.r8.inspector.FloatValueInspector;
import com.android.tools.r8.inspector.IntValueInspector;
import com.android.tools.r8.inspector.LongValueInspector;
import com.android.tools.r8.inspector.ShortValueInspector;
import com.android.tools.r8.inspector.StringValueInspector;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wl0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2971wl0 implements BooleanValueInspector, ByteValueInspector, CharValueInspector, ShortValueInspector, IntValueInspector, LongValueInspector, FloatValueInspector, DoubleValueInspector, StringValueInspector {
    public final com.android.tools.r8.graph.O2 a;
    public final com.android.tools.r8.graph.I2 b;

    public C2971wl0(com.android.tools.r8.graph.O2 o2, com.android.tools.r8.graph.I2 i2) {
        this.a = o2;
        this.b = i2;
    }

    public static void a(boolean z) {
        if (z) {
            return;
        }
        k2d.a("Invalid call on ValueInspector");
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final BooleanValueInspector asBooleanValue() {
        if (this.b.J0()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final ByteValueInspector asByteValue() {
        if (this.b.K0()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final CharValueInspector asCharValue() {
        if (this.b.L0()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final DoubleValueInspector asDoubleValue() {
        if (this.b.N0()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final FloatValueInspector asFloatValue() {
        if (this.b.O0()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final IntValueInspector asIntValue() {
        if (this.b.P0()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final LongValueInspector asLongValue() {
        if (this.b.Q0()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final ShortValueInspector asShortValue() {
        if (this.b.V0()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final StringValueInspector asStringValue() {
        if (isStringValue()) {
            return this;
        }
        return null;
    }

    @Override // com.android.tools.r8.inspector.BooleanValueInspector
    public final boolean getBooleanValue() {
        a(this.b.J0());
        return this.a.r0().d1();
    }

    @Override // com.android.tools.r8.inspector.ByteValueInspector
    public final byte getByteValue() {
        a(this.b.K0());
        return this.a.s0().d1();
    }

    @Override // com.android.tools.r8.inspector.CharValueInspector
    public final char getCharValue() {
        a(this.b.L0());
        return this.a.t0().d1();
    }

    @Override // com.android.tools.r8.inspector.DoubleValueInspector
    public final double getDoubleValue() {
        a(this.b.N0());
        return this.a.u0().d1();
    }

    @Override // com.android.tools.r8.inspector.FloatValueInspector
    public final float getFloatValue() {
        a(this.b.O0());
        return this.a.x0().d1();
    }

    @Override // com.android.tools.r8.inspector.IntValueInspector
    public final int getIntValue() {
        a(this.b.P0());
        return this.a.y0().c;
    }

    @Override // com.android.tools.r8.inspector.LongValueInspector
    public final long getLongValue() {
        a(this.b.Q0());
        return this.a.z0().d1();
    }

    @Override // com.android.tools.r8.inspector.ShortValueInspector
    public final short getShortValue() {
        a(this.b.V0());
        return this.a.D0().d1();
    }

    @Override // com.android.tools.r8.inspector.StringValueInspector
    public final String getStringValue() {
        a(isStringValue());
        return ((com.android.tools.r8.graph.H2) this.a.E0().d1()).toString();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final TypeReference getTypeReference() {
        return Reference.typeFromDescriptor(this.b.Z0());
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isBooleanValue() {
        return this.b.J0();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isByteValue() {
        return this.b.K0();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isCharValue() {
        return this.b.L0();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isDoubleValue() {
        return this.b.N0();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isFloatValue() {
        return this.b.O0();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isIntValue() {
        return this.b.P0();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isLongValue() {
        return this.b.Q0();
    }

    @Override // com.android.tools.r8.inspector.BooleanValueInspector, com.android.tools.r8.inspector.ValueInspector
    public final boolean isPrimitive() {
        return this.b.T0();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isShortValue() {
        return this.b.V0();
    }

    @Override // com.android.tools.r8.inspector.ValueInspector
    public final boolean isStringValue() {
        return this.b.M0() && this.a.Y0();
    }
}

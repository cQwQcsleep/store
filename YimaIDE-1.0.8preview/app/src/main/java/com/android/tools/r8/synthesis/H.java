package com.android.tools.r8.synthesis;

import com.android.tools.r8.SyntheticInfoConsumerData;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H implements SyntheticInfoConsumerData {
    public final I2 a;
    public final I2 b;

    public H(I2 i2, I2 i3) {
        this.a = i2;
        this.b = i3;
    }

    @Override // com.android.tools.r8.SyntheticInfoConsumerData
    public final ClassReference getSynthesizingContextClass() {
        return Reference.classFromDescriptor(this.b.Z0());
    }

    @Override // com.android.tools.r8.SyntheticInfoConsumerData
    public final ClassReference getSyntheticClass() {
        return Reference.classFromDescriptor(this.a.Z0());
    }
}

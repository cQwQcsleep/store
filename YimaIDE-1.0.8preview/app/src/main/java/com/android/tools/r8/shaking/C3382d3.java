package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.origin.Origin;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.d3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3382d3 extends Origin {
    public final /* synthetic */ EnumC3077y2 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3382d3(Origin origin, EnumC3077y2 enumC3077y2) {
        super(origin);
        this.f = enumC3077y2;
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return "<SYNTHESIZED_FROM_API_LEVEL_" + this.f.d() + ">";
    }
}

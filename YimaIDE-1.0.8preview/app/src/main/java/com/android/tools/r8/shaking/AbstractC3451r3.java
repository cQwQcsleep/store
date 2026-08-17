package com.android.tools.r8.shaking;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.TextPosition;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.r3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3451r3 {
    /* JADX WARN: Multi-variable type inference failed */
    public static C3427m3 a(Origin origin, TextPosition textPosition, com.android.tools.r8.graph.I2 i2) {
        C3427m3.a aVar = (C3427m3.a) C3427m3.H().a(EnumC3447q3.b).a(O2.c);
        aVar.a = origin;
        aVar.b = textPosition;
        C3427m3.a aVar2 = (C3427m3.a) aVar.a(F2.b().a(false, K3.a(i2)).a());
        C3461t3.a aVar3 = new C3461t3.a();
        aVar3.a(EnumC3476w3.d);
        return ((C3427m3.a) ((C3427m3.a) aVar2.a(Collections.singletonList(aVar3.a()))).a("-keepkotlinmetadata")).a();
    }
}

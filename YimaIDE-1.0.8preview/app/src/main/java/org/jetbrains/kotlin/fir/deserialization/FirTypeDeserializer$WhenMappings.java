package org.jetbrains.kotlin.fir.deserialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* synthetic */ class FirTypeDeserializer$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        int[] iArr = new int[ProtoBuf.TypeParameter.Variance.values().length];
        try {
            iArr[ProtoBuf.TypeParameter.Variance.IN.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[ProtoBuf.TypeParameter.Variance.OUT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[ProtoBuf.TypeParameter.Variance.INV.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        $EnumSwitchMapping$0 = iArr;
    }
}

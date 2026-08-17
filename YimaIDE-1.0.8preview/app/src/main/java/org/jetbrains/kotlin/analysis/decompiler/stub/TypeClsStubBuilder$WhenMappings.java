package org.jetbrains.kotlin.analysis.decompiler.stub;

import kotlin.Metadata;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* synthetic */ class TypeClsStubBuilder$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;
    public static final /* synthetic */ int[] $EnumSwitchMapping$1;

    static {
        int[] iArr = new int[ProtoBuf.Type.Argument.Projection.values().length];
        try {
            iArr[ProtoBuf.Type.Argument.Projection.IN.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[ProtoBuf.Type.Argument.Projection.OUT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[ProtoBuf.Type.Argument.Projection.INV.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[ProtoBuf.Type.Argument.Projection.STAR.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        $EnumSwitchMapping$0 = iArr;
        int[] iArr2 = new int[ProtoBuf.TypeParameter.Variance.values().length];
        try {
            iArr2[ProtoBuf.TypeParameter.Variance.IN.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[ProtoBuf.TypeParameter.Variance.OUT.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr2[ProtoBuf.TypeParameter.Variance.INV.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        $EnumSwitchMapping$1 = iArr2;
    }
}

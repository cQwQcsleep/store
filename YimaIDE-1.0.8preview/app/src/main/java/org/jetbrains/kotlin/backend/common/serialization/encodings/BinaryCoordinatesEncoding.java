package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.protobuf.CodedInputStream;
import org.jetbrains.kotlin.protobuf.CodedOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinaryCoordinatesEncoding;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encode", "", "startOffset", "", "endOffset", "useZigZag", "", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/IrElementCoordinates;", "code", "usesZigZag", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BinaryCoordinatesEncoding {
    public static final BinaryCoordinatesEncoding INSTANCE = new BinaryCoordinatesEncoding();

    private BinaryCoordinatesEncoding() {
    }

    public final IrElementCoordinates decode(long code, boolean usesZigZag) {
        long jM273decode9PDhHO0 = BinaryLattice.INSTANCE.m273decode9PDhHO0(code);
        int iM268getFirstimpl = BinaryLattice.m268getFirstimpl(jM273decode9PDhHO0);
        if (usesZigZag) {
            iM268getFirstimpl = CodedInputStream.decodeZigZag32(iM268getFirstimpl);
        }
        return new IrElementCoordinates(iM268getFirstimpl, BinaryLattice.m269getSecondimpl(jM273decode9PDhHO0) + iM268getFirstimpl);
    }

    public final long encode(int startOffset, int endOffset, boolean useZigZag) {
        return BinaryLattice.INSTANCE.encode(useZigZag ? CodedOutputStream.encodeZigZag32(startOffset) : startOffset, endOffset - startOffset);
    }
}

package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.library.components.KlibIrComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/IrKlibBytesSource;", "Lorg/jetbrains/kotlin/backend/common/serialization/IrLibraryBytesSource;", "ir", "Lorg/jetbrains/kotlin/library/components/KlibIrComponent;", "fileIndex", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/library/components/KlibIrComponent;I)V", "irDeclaration", "", "index", "type", "signature", "string", "body", "debugInfo", "fileEntry", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IrKlibBytesSource extends IrLibraryBytesSource {
    private final int fileIndex;
    private final KlibIrComponent ir;

    public IrKlibBytesSource(KlibIrComponent klibIrComponent, int i) {
        klibIrComponent.getClass();
        this.ir = klibIrComponent;
        this.fileIndex = i;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrLibraryBytesSource
    public byte[] body(int index) {
        return this.ir.body(index, this.fileIndex);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrLibraryBytesSource
    public byte[] debugInfo(int index) {
        return this.ir.signatureDebugInfo(index, this.fileIndex);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrLibraryBytesSource
    public byte[] fileEntry(int index) {
        return this.ir.irFileEntry(index, this.fileIndex);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrLibraryBytesSource
    public byte[] irDeclaration(int index) {
        return this.ir.declaration(index, this.fileIndex);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrLibraryBytesSource
    public byte[] signature(int index) {
        return this.ir.signature(index, this.fileIndex);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrLibraryBytesSource
    public byte[] string(int index) {
        return this.ir.stringLiteral(index, this.fileIndex);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrLibraryBytesSource
    public byte[] type(int index) {
        return this.ir.type(index, this.fileIndex);
    }
}

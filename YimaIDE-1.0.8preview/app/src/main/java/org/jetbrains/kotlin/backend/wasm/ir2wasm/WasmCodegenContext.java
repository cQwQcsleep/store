package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IdSignatureRetriever;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/WasmCodegenContext;", "", "idSignatureRetriever", "Lorg/jetbrains/kotlin/ir/declarations/IdSignatureRetriever;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IdSignatureRetriever;)V", "getReferenceKey", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class WasmCodegenContext {
    private final IdSignatureRetriever idSignatureRetriever;

    public WasmCodegenContext(IdSignatureRetriever idSignatureRetriever) {
        idSignatureRetriever.getClass();
        this.idSignatureRetriever = idSignatureRetriever;
    }

    public final IdSignature getReferenceKey(IrSymbol irSymbol) {
        irSymbol.getClass();
        IdSignatureRetriever idSignatureRetriever = this.idSignatureRetriever;
        IrDeclaration owner = irSymbol.getOwner();
        owner.getClass();
        IdSignature idSignatureDeclarationSignature = idSignatureRetriever.declarationSignature(owner);
        idSignatureDeclarationSignature.getClass();
        return idSignatureDeclarationSignature;
    }
}

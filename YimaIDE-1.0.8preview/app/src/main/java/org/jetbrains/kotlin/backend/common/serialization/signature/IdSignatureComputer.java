package org.jetbrains.kotlin.backend.common.serialization.signature;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.symbols.IrFileSymbol;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J+\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000bH&¢\u0006\u0002\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/signature/IdSignatureComputer;", "", "computeSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "inFile", "R", "file", "Lorg/jetbrains/kotlin/ir/symbols/IrFileSymbol;", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/ir/symbols/IrFileSymbol;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface IdSignatureComputer {
    IdSignature computeSignature(IrDeclaration declaration);

    <R> R inFile(IrFileSymbol file, Function0<? extends R> block);
}

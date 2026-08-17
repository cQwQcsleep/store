package org.jetbrains.kotlin.backend.common.diagnostics;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/diagnostics/ConflictingKlibSignaturesData;", "", "signature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "declarations", "", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/util/IdSignature;Ljava/util/Collection;)V", "getSignature", "()Lorg/jetbrains/kotlin/ir/util/IdSignature;", "getDeclarations", "()Ljava/util/Collection;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ConflictingKlibSignaturesData {
    private final Collection<IrDeclaration> declarations;
    private final IdSignature signature;

    public ConflictingKlibSignaturesData(IdSignature idSignature, Collection<? extends IrDeclaration> collection) {
        idSignature.getClass();
        collection.getClass();
        this.signature = idSignature;
        this.declarations = collection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConflictingKlibSignaturesData copy$default(ConflictingKlibSignaturesData conflictingKlibSignaturesData, IdSignature idSignature, Collection collection, int i, Object obj) {
        if ((i & 1) != 0) {
            idSignature = conflictingKlibSignaturesData.signature;
        }
        if ((i & 2) != 0) {
            collection = conflictingKlibSignaturesData.declarations;
        }
        return conflictingKlibSignaturesData.copy(idSignature, collection);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IdSignature getSignature() {
        return this.signature;
    }

    public final Collection<IrDeclaration> component2() {
        return this.declarations;
    }

    public final ConflictingKlibSignaturesData copy(IdSignature signature, Collection<? extends IrDeclaration> declarations) {
        signature.getClass();
        declarations.getClass();
        return new ConflictingKlibSignaturesData(signature, declarations);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConflictingKlibSignaturesData)) {
            return false;
        }
        ConflictingKlibSignaturesData conflictingKlibSignaturesData = (ConflictingKlibSignaturesData) other;
        return Intrinsics.areEqual(this.signature, conflictingKlibSignaturesData.signature) && Intrinsics.areEqual(this.declarations, conflictingKlibSignaturesData.declarations);
    }

    public final Collection<IrDeclaration> getDeclarations() {
        return this.declarations;
    }

    public final IdSignature getSignature() {
        return this.signature;
    }

    public int hashCode() {
        return (this.signature.hashCode() * 31) + this.declarations.hashCode();
    }

    public String toString() {
        return "ConflictingKlibSignaturesData(signature=" + this.signature + ", declarations=" + this.declarations + Util.C_PARAM_END;
    }
}

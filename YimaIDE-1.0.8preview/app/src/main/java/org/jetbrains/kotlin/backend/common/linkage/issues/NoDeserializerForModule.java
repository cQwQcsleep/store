package org.jetbrains.kotlin.backend.common.linkage.issues;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IdSignatureRenderer;
import org.jetbrains.kotlin.ir.util.IdSignatureRendererKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/issues/NoDeserializerForModule;", "Lorg/jetbrains/kotlin/backend/common/linkage/issues/KotlinIrLinkerIssue;", "moduleName", "Lorg/jetbrains/kotlin/name/Name;", "idSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/ir/util/IdSignature;)V", "errorMessage", "", "getErrorMessage", "()Ljava/lang/String;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NoDeserializerForModule extends KotlinIrLinkerIssue {
    private final String errorMessage;

    public NoDeserializerForModule(Name name, IdSignature idSignature) {
        name.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("Could not load module " + name.asString());
        if (idSignature != null) {
            sb.append(" in an attempt to find deserializer for symbol " + IdSignatureRendererKt.render$default(idSignature, (IdSignatureRenderer) null, 1, (Object) null) + '.');
        }
        this.errorMessage = sb.toString();
    }

    @Override // org.jetbrains.kotlin.backend.common.linkage.issues.KotlinIrLinkerIssue
    public String getErrorMessage() {
        return this.errorMessage;
    }
}

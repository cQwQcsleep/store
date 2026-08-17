package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0004\b\u0005\u0010\u0006JA\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u00102\u001e\u0010\u0011\u001a\u001a\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00122\u0006\u0010\u0013\u001a\u0002H\u0010H\u0016¢\u0006\u0002\u0010\u0014R\u0013\u0010\u0004\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/KtErroneousValueParameterReference;", "Type", "Diagnostic", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "diagnostic", "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getDiagnostic", "()Ljava/lang/Object;", "Ljava/lang/Object;", "erroneous", Argument.Delimiters.none, "getErroneous", "()Z", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtErroneousValueParameterReference<Type, Diagnostic> extends KtValueParameterReference<Type, Diagnostic> {
    private final Diagnostic diagnostic;

    public KtErroneousValueParameterReference(Diagnostic diagnostic) {
        super(Integer.MAX_VALUE, "ERROR");
        this.diagnostic = diagnostic;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.KtValueParameterReference, org.jetbrains.kotlin.contracts.description.KtContractDescriptionValue, org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public <R, D> R accept(KtContractDescriptionVisitor<? extends R, ? super D, Type, Diagnostic> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitErroneousValueParameterReference(this, data);
    }

    public final Diagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtValueParameterReference, org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public boolean getErroneous() {
        return true;
    }
}

package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B+\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJA\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0002\u0010\u0014\"\u0004\b\u0003\u0010\u00152\u001e\u0010\u0016\u001a\u001a\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00172\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0019J\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/KtIsInstancePredicate;", "Type", "Diagnostic", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanExpression;", "arg", "Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", ModuleXmlParser.TYPE, "isNegated", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;Ljava/lang/Object;Z)V", "getArg", "()Lorg/jetbrains/kotlin/contracts/description/KtValueParameterReference;", "getType", "()Ljava/lang/Object;", "Ljava/lang/Object;", "()Z", "erroneous", "getErroneous", "accept", "R", "D", "contractDescriptionVisitor", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;", "data", "(Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "negated", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class KtIsInstancePredicate<Type, Diagnostic> implements KtBooleanExpression<Type, Diagnostic> {
    private final KtValueParameterReference<Type, Diagnostic> arg;
    private final boolean isNegated;
    private final Type type;

    public KtIsInstancePredicate(KtValueParameterReference<Type, Diagnostic> ktValueParameterReference, Type type, boolean z) {
        ktValueParameterReference.getClass();
        this.arg = ktValueParameterReference;
        this.type = type;
        this.isNegated = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.contracts.description.KtBooleanExpression, org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public <R, D> R accept(KtContractDescriptionVisitor<? extends R, ? super D, Type, Diagnostic> contractDescriptionVisitor, D data) {
        contractDescriptionVisitor.getClass();
        return contractDescriptionVisitor.visitIsInstancePredicate(this, data);
    }

    public final KtValueParameterReference<Type, Diagnostic> getArg() {
        return this.arg;
    }

    @Override // org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement
    public boolean getErroneous() {
        return this.arg.getErroneous();
    }

    public final Type getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isNegated, reason: from getter */
    public final boolean getIsNegated() {
        return this.isNegated;
    }

    public final KtIsInstancePredicate<Type, Diagnostic> negated() {
        return new KtIsInstancePredicate<>(this.arg, this.type, !this.isNegated);
    }
}

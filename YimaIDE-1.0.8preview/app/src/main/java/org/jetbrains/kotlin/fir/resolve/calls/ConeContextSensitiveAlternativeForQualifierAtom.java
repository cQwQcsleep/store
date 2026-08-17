package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirIdeOnly;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0002\b\n¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u001b\u001a\u00020\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeContextSensitiveAlternativeForQualifierAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "originalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;", "alternative", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/FirIdeOnly;", "getOriginalExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;", "getAlternative", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "inputTypes", Argument.Delimiters.none, "getInputTypes", "()Ljava/util/Collection;", "outputType", "getOutputType", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "markDiscarded", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeContextSensitiveAlternativeForQualifierAtom extends ConePostponedResolvedAtom {
    private final FirPropertyAccessExpression alternative;
    private final ConeKotlinType expectedType;
    private final Collection<ConeKotlinType> inputTypes;
    private final FirQualifierWithContextSensitiveAlternative originalExpression;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @FirIdeOnly
    public ConeContextSensitiveAlternativeForQualifierAtom(FirQualifierWithContextSensitiveAlternative firQualifierWithContextSensitiveAlternative, FirPropertyAccessExpression firPropertyAccessExpression, ConeKotlinType coneKotlinType) {
        super(null);
        firQualifierWithContextSensitiveAlternative.getClass();
        firPropertyAccessExpression.getClass();
        coneKotlinType.getClass();
        this.originalExpression = firQualifierWithContextSensitiveAlternative;
        this.alternative = firPropertyAccessExpression;
        this.expectedType = coneKotlinType;
        this.inputTypes = CollectionsKt.listOf(mo581getExpectedType());
    }

    public final FirPropertyAccessExpression getAlternative() {
        return this.alternative;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirExpression getExpression() {
        Object obj = this.originalExpression;
        obj.getClass();
        return (FirExpression) obj;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    public Collection<ConeKotlinType> getInputTypes() {
        return this.inputTypes;
    }

    public final FirQualifierWithContextSensitiveAlternative getOriginalExpression() {
        return this.originalExpression;
    }

    public final void markDiscarded() {
        setAnalyzed(true);
        this.originalExpression.replaceContextSensitiveAlternative(null);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getExpectedType, reason: from getter and merged with bridge method [inline-methods] */
    public ConeKotlinType mo581getExpectedType() {
        return this.expectedType;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom
    /* JADX INFO: renamed from: getOutputType */
    public ConeKotlinType mo582getOutputType() {
        return null;
    }
}

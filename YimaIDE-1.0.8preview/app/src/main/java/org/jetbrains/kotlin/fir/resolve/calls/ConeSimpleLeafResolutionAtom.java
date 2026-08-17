package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.resolve.calls.stages.FirFakeArgumentForCallableReference;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeSimpleLeafResolutionAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "allowUnresolvedExpression", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Z)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeSimpleLeafResolutionAtom extends ConeResolutionAtom {
    private final FirExpression expression;

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeSimpleLeafResolutionAtom(FirExpression firExpression, boolean z) throws KotlinIllegalStateExceptionWithAttachments {
        super(null);
        firExpression.getClass();
        this.expression = firExpression;
        if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
            if (z || (FirExpressionUtilKt.unwrapArgument(getExpression()) instanceof FirFakeArgumentForCallableReference) || FirTypeUtilsKt.getHasResolvedType(getExpression())) {
                return;
            }
            KotlinIllegalStateExceptionWithAttachments kotlinIllegalStateExceptionWithAttachments = new KotlinIllegalStateExceptionWithAttachments("ConeResolvedAtom should be created only for resolved expressions");
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "expression", getExpression());
            kotlinIllegalStateExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalStateExceptionWithAttachments;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom, org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public FirExpression getExpression() {
        return this.expression;
    }
}

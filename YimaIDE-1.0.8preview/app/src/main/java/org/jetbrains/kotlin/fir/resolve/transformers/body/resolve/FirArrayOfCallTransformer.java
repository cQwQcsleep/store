package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCollectionLiteralBuilder;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0002H\u0016J'\u0010\r\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u0002H\u000e2\u0006\u0010\f\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirArrayOfCallTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultTransformer;", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "()V", "toArrayLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "session", "transformFunctionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "data", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirArrayOfCallTransformer extends FirDefaultTransformer<FirSession> {
    /* JADX WARN: Multi-variable type inference failed */
    private final FirExpression toArrayLiteral(FirFunctionCall functionCall, FirSession session) {
        if (!ResolveUtilsKt.isArrayOfCall(functionCall, session) || !(functionCall.getCalleeReference() instanceof FirResolvedNamedReference)) {
            return null;
        }
        FirCollectionLiteralBuilder firCollectionLiteralBuilder = new FirCollectionLiteralBuilder();
        firCollectionLiteralBuilder.setSource(functionCall.getSource());
        CollectionsKt.addAll(firCollectionLiteralBuilder.getAnnotations(), functionCall.getAnnotations());
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        if (!functionCall.getArgumentList().getArguments().isEmpty()) {
            List<FirExpression> arguments = functionCall.getArgumentList().getArguments();
            List<FirExpression> arguments2 = firArgumentListBuilder.getArguments();
            for (FirExpression firExpression : arguments) {
                CollectionsKt.addAll(arguments2, firExpression instanceof FirVarargArgumentsExpression ? ((FirVarargArgumentsExpression) firExpression).getArguments() : CollectionsKt.listOf(firExpression));
            }
        }
        firCollectionLiteralBuilder.setArgumentList(firArgumentListBuilder.build());
        firCollectionLiteralBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(functionCall));
        FirCollectionLiteral firCollectionLiteralMo288build = firCollectionLiteralBuilder.mo288build();
        FirNamedReference calleeReference = functionCall.getCalleeReference();
        if (!FirReferenceUtilsKt.isError(calleeReference)) {
            return firCollectionLiteralMo288build;
        }
        KtSourceElement source = functionCall.getSource();
        return FirExpressionUtilKt.buildErrorExpression(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ErrorExpressionForTransformedArrayOf.INSTANCE, null, 2, null) : null, ((FirDiagnosticHolder) calleeReference).getDiagnostic(), firCollectionLiteralMo288build);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, FirSession data) {
        element.getClass();
        data.getClass();
        E e = (E) element.transformChildren(this, data);
        e.getClass();
        return e;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformFunctionCall(FirFunctionCall functionCall, FirSession data) {
        functionCall.getClass();
        data.getClass();
        functionCall.transformChildren(this, data);
        FirExpression arrayLiteral = toArrayLiteral(functionCall, data);
        return arrayLiteral != null ? arrayLiteral : functionCall;
    }
}

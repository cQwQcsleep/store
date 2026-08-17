package org.jetbrains.kotlin.fir.expressions;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010(\u001a\u0002H)\"\u0004\b\u0000\u0010)\"\u0004\b\u0001\u0010*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H)\u0012\u0004\u0012\u0002H*0,2\u0006\u0010-\u001a\u0002H*H\u0016¢\u0006\u0002\u0010.J3\u0010/\u001a\u0002H0\"\b\b\u0000\u00100*\u000201\"\u0004\b\u0001\u0010*2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H*032\u0006\u0010-\u001a\u0002H*H\u0016¢\u0006\u0002\u00104J\u0012\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0007H&J\u0016\u00108\u001a\u0002062\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH&J\u0010\u0010:\u001a\u0002062\u0006\u0010;\u001a\u00020\u0012H&J\u0016\u0010<\u001a\u0002062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00010\rH&J\u0016\u0010>\u001a\u0002062\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00180\rH&J\u0012\u0010@\u001a\u0002062\b\u0010A\u001a\u0004\u0018\u00010\u0001H&J\u0012\u0010B\u001a\u0002062\b\u0010C\u001a\u0004\u0018\u00010\u0001H&J\u0012\u0010D\u001a\u0002062\b\u0010E\u001a\u0004\u0018\u00010\u0001H&J\u0016\u0010F\u001a\u0002062\b\u0010G\u001a\u0004\u0018\u00010\"H'b\u0002\bHJ\u0016\u0010I\u001a\u0002062\f\u0010J\u001a\b\u0012\u0004\u0012\u00020&0\rH&J)\u0010K\u001a\u00020\u0000\"\u0004\b\u0000\u0010*2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H*032\u0006\u0010-\u001a\u0002H*H&¢\u0006\u0002\u0010LJ)\u0010M\u001a\u00020\u0000\"\u0004\b\u0000\u0010*2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H*032\u0006\u0010-\u001a\u0002H*H&¢\u0006\u0002\u0010LJ)\u0010N\u001a\u00020\u0000\"\u0004\b\u0000\u0010*2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H*032\u0006\u0010-\u001a\u0002H*H&¢\u0006\u0002\u0010LJ)\u0010O\u001a\u00020\u0000\"\u0004\b\u0000\u0010*2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H*032\u0006\u0010-\u001a\u0002H*H&¢\u0006\u0002\u0010LJ)\u0010P\u001a\u00020\u0000\"\u0004\b\u0000\u0010*2\f\u00102\u001a\b\u0012\u0004\u0012\u0002H*032\u0006\u0010-\u001a\u0002H*H&¢\u0006\u0002\u0010LR \u0010\u0006\u001a\u0004\u0018\u00010\u00078&X§\u0004r\u0002\b\u000b¢\u0006\f\u0012\u0004\b\b\u0010\u0005\u001a\u0004\b\t\u0010\nR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0010R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u0004\u0018\u00010\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001cR\u0014\u0010!\u001a\u0004\u0018\u00010\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0010¨\u0006Q"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "Lorg/jetbrains/kotlin/fir/expressions/FirContextArgumentListOwner;", "<init>", "()V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "contextArguments", "getContextArguments", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "explicitReceiver", "getExplicitReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "dispatchReceiver", "getDispatchReceiver", "extensionReceiver", "getExtensionReceiver", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceConeTypeOrNull", Argument.Delimiters.none, "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceCalleeReference", "newCalleeReference", "replaceContextArguments", "newContextArguments", "replaceTypeArguments", "newTypeArguments", "replaceExplicitReceiver", "newExplicitReceiver", "replaceDispatchReceiver", "newDispatchReceiver", "replaceExtensionReceiver", "newExtensionReceiver", "replaceSource", "newSource", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "replaceNonFatalDiagnostics", "newNonFatalDiagnostics", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "transformCalleeReference", "transformContextArguments", "transformTypeArguments", "transformExplicitReceiver", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirQualifiedAccessExpression extends FirExpression implements FirContextArgumentListOwner, FirResolvable {
    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitQualifiedAccessExpression(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    public abstract FirReference getCalleeReference();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract ConeKotlinType getConeTypeOrNull();

    public abstract List<FirExpression> getContextArguments();

    public abstract FirExpression getDispatchReceiver();

    public abstract FirExpression getExplicitReceiver();

    public abstract FirExpression getExtensionReceiver();

    public abstract List<ConeDiagnostic> getNonFatalDiagnostics();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    public abstract List<FirTypeProjection> getTypeArguments();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    public abstract void replaceCalleeReference(FirReference newCalleeReference);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull);

    public abstract void replaceContextArguments(List<? extends FirExpression> newContextArguments);

    public abstract void replaceDispatchReceiver(FirExpression newDispatchReceiver);

    public abstract void replaceExplicitReceiver(FirExpression newExplicitReceiver);

    public abstract void replaceExtensionReceiver(FirExpression newExtensionReceiver);

    public abstract void replaceNonFatalDiagnostics(List<? extends ConeDiagnostic> newNonFatalDiagnostics);

    @FirImplementationDetail
    public abstract void replaceSource(KtSourceElement newSource);

    public abstract void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirStatement firStatementTransformQualifiedAccessExpression = transformer.transformQualifiedAccessExpression(this, data);
        firStatementTransformQualifiedAccessExpression.getClass();
        return firStatementTransformQualifiedAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirQualifiedAccessExpression transformAnnotations(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirQualifiedAccessExpression transformCalleeReference(FirTransformer<? super D> transformer, D data);

    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* bridge */ /* synthetic */ FirContextArgumentListOwner transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    public abstract <D> FirQualifiedAccessExpression transformContextArguments(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirQualifiedAccessExpression transformExplicitReceiver(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirQualifiedAccessExpression transformTypeArguments(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}

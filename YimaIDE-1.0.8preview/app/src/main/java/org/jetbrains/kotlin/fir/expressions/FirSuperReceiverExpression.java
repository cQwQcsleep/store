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
import org.jetbrains.kotlin.fir.references.FirSuperReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(\"\u0004\b\u0001\u0010)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002H)0+2\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u0010-J3\u0010.\u001a\u0002H/\"\b\b\u0000\u0010/*\u000200\"\u0004\b\u0001\u0010)2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H)022\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00103J\u0012\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u0005H&J\u0016\u00107\u001a\u0002052\f\u00108\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&J\u0016\u00109\u001a\u0002052\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bH&J\u0016\u0010;\u001a\u0002052\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00130\u000bH&J\u0012\u0010=\u001a\u0002052\b\u0010>\u001a\u0004\u0018\u00010\u0010H&J\u0012\u0010?\u001a\u0002052\b\u0010@\u001a\u0004\u0018\u00010\u0010H&J\u0012\u0010A\u001a\u0002052\b\u0010B\u001a\u0004\u0018\u00010\u0010H&J\u0016\u0010C\u001a\u0002052\b\u0010D\u001a\u0004\u0018\u00010\u001dH'b\u0002\bEJ\u0016\u0010F\u001a\u0002052\f\u0010G\u001a\b\u0012\u0004\u0012\u00020!0\u000bH&J\u0010\u0010H\u001a\u0002052\u0006\u0010I\u001a\u00020$H&J\u0010\u0010H\u001a\u0002052\u0006\u0010I\u001a\u00020JH&J)\u0010K\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H)022\u0006\u0010,\u001a\u0002H)H&¢\u0006\u0002\u0010LJ)\u0010M\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H)022\u0006\u0010,\u001a\u0002H)H&¢\u0006\u0002\u0010LJ)\u0010N\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H)022\u0006\u0010,\u001a\u0002H)H&¢\u0006\u0002\u0010LJ)\u0010O\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H)022\u0006\u0010,\u001a\u0002H)H&¢\u0006\u0002\u0010LJ)\u0010P\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H)022\u0006\u0010,\u001a\u0002H)H&¢\u0006\u0002\u0010LR \u0010\u0004\u001a\u0004\u0018\u00010\u00058&X§\u0004r\u0002\b\t¢\u0006\f\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000eR\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000eR\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001dX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u000eR\u0012\u0010#\u001a\u00020$X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006Q"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "<init>", "()V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "contextArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getContextArguments", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "explicitReceiver", "getExplicitReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "dispatchReceiver", "getDispatchReceiver", "extensionReceiver", "getExtensionReceiver", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirSuperReference;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirSuperReference;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceConeTypeOrNull", Argument.Delimiters.none, "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceContextArguments", "newContextArguments", "replaceTypeArguments", "newTypeArguments", "replaceExplicitReceiver", "newExplicitReceiver", "replaceDispatchReceiver", "newDispatchReceiver", "replaceExtensionReceiver", "newExtensionReceiver", "replaceSource", "newSource", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "replaceNonFatalDiagnostics", "newNonFatalDiagnostics", "replaceCalleeReference", "newCalleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "transformContextArguments", "transformTypeArguments", "transformExplicitReceiver", "transformCalleeReference", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSuperReceiverExpression extends FirQualifiedAccessExpression {
    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitSuperReceiverExpression(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public abstract FirSuperReference getCalleeReference();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract ConeKotlinType getConeTypeOrNull();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public abstract List<FirExpression> getContextArguments();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract FirExpression getDispatchReceiver();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract FirExpression getExplicitReceiver();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract FirExpression getExtensionReceiver();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract List<ConeDiagnostic> getNonFatalDiagnostics();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract List<FirTypeProjection> getTypeArguments();

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public abstract void replaceCalleeReference(FirReference newCalleeReference);

    public abstract void replaceCalleeReference(FirSuperReference newCalleeReference);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public abstract void replaceContextArguments(List<? extends FirExpression> newContextArguments);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract void replaceDispatchReceiver(FirExpression newDispatchReceiver);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract void replaceExplicitReceiver(FirExpression newExplicitReceiver);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract void replaceExtensionReceiver(FirExpression newExtensionReceiver);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract void replaceNonFatalDiagnostics(List<? extends ConeDiagnostic> newNonFatalDiagnostics);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    @FirImplementationDetail
    public abstract void replaceSource(KtSourceElement newSource);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirStatement firStatementTransformSuperReceiverExpression = transformer.transformSuperReceiverExpression(this, data);
        firStatementTransformSuperReceiverExpression.getClass();
        return firStatementTransformSuperReceiverExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirSuperReceiverExpression transformAnnotations(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public abstract <D> FirSuperReceiverExpression transformCalleeReference(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirContextArgumentListOwner transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public abstract <D> FirSuperReceiverExpression transformContextArguments(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformExplicitReceiver(FirTransformer firTransformer, Object obj) {
        return transformExplicitReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract <D> FirSuperReceiverExpression transformExplicitReceiver(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public abstract <D> FirSuperReceiverExpression transformTypeArguments(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}

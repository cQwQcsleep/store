package org.jetbrains.kotlin.fir.expressions;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirIdeOnly;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0017\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010?\u001a\u0002H@\"\u0004\b\u0000\u0010@\"\u0004\b\u0001\u0010A2\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002HA0C2\u0006\u0010D\u001a\u0002HAH\u0016¢\u0006\u0002\u0010EJ3\u0010F\u001a\u0002HG\"\b\b\u0000\u0010G*\u00020H\"\u0004\b\u0001\u0010A2\f\u0010I\u001a\b\u0012\u0004\u0012\u0002HA0J2\u0006\u0010D\u001a\u0002HAH\u0016¢\u0006\u0002\u0010KJ\u0012\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\nH&J\u0012\u0010O\u001a\u00020M2\b\u0010P\u001a\u0004\u0018\u00010\u0010H&J\u0016\u0010Q\u001a\u00020M2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H&J\u0010\u0010S\u001a\u00020M2\u0006\u0010T\u001a\u00020,H&J\u0012\u0010U\u001a\u00020M2\b\u0010V\u001a\u0004\u0018\u00010\u0010H&J\u0010\u0010W\u001a\u00020M2\u0006\u0010X\u001a\u00020,H&J\u0010\u0010Y\u001a\u00020M2\u0006\u0010Z\u001a\u00020,H&J\u0016\u0010[\u001a\u00020M2\f\u0010\\\u001a\b\u0012\u0004\u0012\u0002060\u0016H&J\u0012\u0010]\u001a\u00020M2\b\u0010^\u001a\u0004\u0018\u000109H&J\u0016\u0010_\u001a\u00020M2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020=0\u0016H&J)\u0010a\u001a\u00020\u0000\"\u0004\b\u0000\u0010A2\f\u0010I\u001a\b\u0012\u0004\u0012\u0002HA0J2\u0006\u0010D\u001a\u0002HAH&¢\u0006\u0002\u0010bJ)\u0010c\u001a\u00020\u0000\"\u0004\b\u0000\u0010A2\f\u0010I\u001a\b\u0012\u0004\u0012\u0002HA0J2\u0006\u0010D\u001a\u0002HAH&¢\u0006\u0002\u0010bR\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\n8&X§\u0004r\u0002\b\u000e¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\rR \u0010\u000f\u001a\u0004\u0018\u00010\u00108&X§\u0004r\u0002\b\u0014¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u0004\u0018\u00010!X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0018\u0010$\u001a\b\u0012\u0002\b\u0003\u0018\u00010%X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0012\u0010+\u001a\u00020,X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010-R\u0014\u0010.\u001a\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0013R\u0012\u00100\u001a\u00020,X¦\u0004¢\u0006\u0006\u001a\u0004\b1\u0010-R\u0012\u00102\u001a\u00020,X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u0010-R\u0012\u00104\u001a\u00020,X¦\u0004¢\u0006\u0006\u001a\u0004\b4\u0010-R\u0018\u00105\u001a\b\u0012\u0004\u0012\u0002060\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u0019R\u0014\u00108\u001a\u0004\u0018\u000109X¦\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0018\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\u0019¨\u0006d"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "contextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "getContextSensitiveAlternative$annotations", "getContextSensitiveAlternative", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/FirIdeOnly;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "relativeClassFqName", "getRelativeClassFqName", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "explicitParent", "getExplicitParent", "()Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "isNullableLHSForCallableReference", Argument.Delimiters.none, "()Z", "resolvedLHSTypeForCallableReferenceOrNull", "getResolvedLHSTypeForCallableReferenceOrNull", "resolvedToCompanionObject", "getResolvedToCompanionObject", "canBeValue", "getCanBeValue", "isFullyQualified", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceContextSensitiveAlternative", Argument.Delimiters.none, "newContextSensitiveAlternative", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceIsNullableLHSForCallableReference", "newIsNullableLHSForCallableReference", "replaceResolvedLHSTypeForCallableReferenceOrNull", "newResolvedLHSTypeForCallableReferenceOrNull", "replaceResolvedToCompanionObject", "newResolvedToCompanionObject", "replaceCanBeValue", "newCanBeValue", "replaceNonFatalDiagnostics", "newNonFatalDiagnostics", "replaceResolvedSymbolOrigin", "newResolvedSymbolOrigin", "replaceTypeArguments", "newTypeArguments", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "transformTypeArguments", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirResolvedQualifier extends FirExpression implements FirQualifierWithContextSensitiveAlternative {
    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @FirIdeOnly
    public static /* synthetic */ void getContextSensitiveAlternative$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitResolvedQualifier(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    public abstract boolean getCanBeValue();

    public abstract ClassId getClassId();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract ConeKotlinType getConeTypeOrNull();

    public abstract FirPropertyAccessExpression getContextSensitiveAlternative();

    public abstract FirResolvedQualifier getExplicitParent();

    public abstract List<ConeDiagnostic> getNonFatalDiagnostics();

    public abstract FqName getPackageFqName();

    public abstract FqName getRelativeClassFqName();

    public abstract ConeKotlinType getResolvedLHSTypeForCallableReferenceOrNull();

    public abstract FirResolvedSymbolOrigin getResolvedSymbolOrigin();

    public abstract boolean getResolvedToCompanionObject();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    public abstract FirClassLikeSymbol<?> getSymbol();

    public abstract List<FirTypeProjection> getTypeArguments();

    public abstract boolean isFullyQualified();

    public abstract boolean isNullableLHSForCallableReference();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    public abstract void replaceCanBeValue(boolean newCanBeValue);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull);

    public abstract void replaceContextSensitiveAlternative(FirPropertyAccessExpression newContextSensitiveAlternative);

    public abstract void replaceIsNullableLHSForCallableReference(boolean newIsNullableLHSForCallableReference);

    public abstract void replaceNonFatalDiagnostics(List<? extends ConeDiagnostic> newNonFatalDiagnostics);

    public abstract void replaceResolvedLHSTypeForCallableReferenceOrNull(ConeKotlinType newResolvedLHSTypeForCallableReferenceOrNull);

    public abstract void replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin newResolvedSymbolOrigin);

    public abstract void replaceResolvedToCompanionObject(boolean newResolvedToCompanionObject);

    public abstract void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirStatement firStatementTransformResolvedQualifier = transformer.transformResolvedQualifier(this, data);
        firStatementTransformResolvedQualifier.getClass();
        return firStatementTransformResolvedQualifier;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirResolvedQualifier transformAnnotations(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirResolvedQualifier transformTypeArguments(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}

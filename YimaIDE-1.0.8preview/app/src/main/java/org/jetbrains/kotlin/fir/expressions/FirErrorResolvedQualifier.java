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
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0017\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010B\u001a\u0002HC\"\u0004\b\u0000\u0010C\"\u0004\b\u0001\u0010D2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u0002HC\u0012\u0004\u0012\u0002HD0F2\u0006\u0010G\u001a\u0002HDH\u0016¢\u0006\u0002\u0010HJ3\u0010I\u001a\u0002HJ\"\b\b\u0000\u0010J*\u00020K\"\u0004\b\u0001\u0010D2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002HD0M2\u0006\u0010G\u001a\u0002HDH\u0016¢\u0006\u0002\u0010NJ\u0012\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010\nH&J\u0012\u0010R\u001a\u00020P2\b\u0010S\u001a\u0004\u0018\u00010\u0010H&J\u0016\u0010T\u001a\u00020P2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H&J\u0010\u0010V\u001a\u00020P2\u0006\u0010W\u001a\u00020,H&J\u0012\u0010X\u001a\u00020P2\b\u0010Y\u001a\u0004\u0018\u00010\u0010H&J\u0010\u0010Z\u001a\u00020P2\u0006\u0010[\u001a\u00020,H&J\u0010\u0010\\\u001a\u00020P2\u0006\u0010]\u001a\u00020,H&J\u0016\u0010^\u001a\u00020P2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002060\u0016H&J\u0012\u0010`\u001a\u00020P2\b\u0010a\u001a\u0004\u0018\u000109H&J\u0016\u0010b\u001a\u00020P2\f\u0010c\u001a\b\u0012\u0004\u0012\u00020=0\u0016H&J)\u0010d\u001a\u00020\u0000\"\u0004\b\u0000\u0010D2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002HD0M2\u0006\u0010G\u001a\u0002HDH&¢\u0006\u0002\u0010eJ)\u0010f\u001a\u00020\u0000\"\u0004\b\u0000\u0010D2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002HD0M2\u0006\u0010G\u001a\u0002HDH&¢\u0006\u0002\u0010eR\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\n8&X§\u0004r\u0002\b\u000e¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\rR \u0010\u000f\u001a\u0004\u0018\u00010\u00108&X§\u0004r\u0002\b\u0014¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u0004\u0018\u00010!X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0018\u0010$\u001a\b\u0012\u0002\b\u0003\u0018\u00010%X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u0004\u0018\u00010\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0012\u0010+\u001a\u00020,X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010-R\u0014\u0010.\u001a\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0013R\u0012\u00100\u001a\u00020,X¦\u0004¢\u0006\u0006\u001a\u0004\b1\u0010-R\u0012\u00102\u001a\u00020,X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u0010-R\u0012\u00104\u001a\u00020,X¦\u0004¢\u0006\u0006\u001a\u0004\b4\u0010-R\u0018\u00105\u001a\b\u0012\u0004\u0012\u0002060\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u0019R\u0014\u00108\u001a\u0004\u0018\u000109X¦\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0018\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\u0019R\u0012\u0010?\u001a\u000206X¦\u0004¢\u0006\u0006\u001a\u0004\b@\u0010A¨\u0006g"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/diagnostics/FirDiagnosticHolder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "contextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "getContextSensitiveAlternative$annotations", "getContextSensitiveAlternative", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/FirIdeOnly;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "relativeClassFqName", "getRelativeClassFqName", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "explicitParent", "getExplicitParent", "()Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "isNullableLHSForCallableReference", Argument.Delimiters.none, "()Z", "resolvedLHSTypeForCallableReferenceOrNull", "getResolvedLHSTypeForCallableReferenceOrNull", "resolvedToCompanionObject", "getResolvedToCompanionObject", "canBeValue", "getCanBeValue", "isFullyQualified", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "diagnostic", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceContextSensitiveAlternative", Argument.Delimiters.none, "newContextSensitiveAlternative", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceIsNullableLHSForCallableReference", "newIsNullableLHSForCallableReference", "replaceResolvedLHSTypeForCallableReferenceOrNull", "newResolvedLHSTypeForCallableReferenceOrNull", "replaceResolvedToCompanionObject", "newResolvedToCompanionObject", "replaceCanBeValue", "newCanBeValue", "replaceNonFatalDiagnostics", "newNonFatalDiagnostics", "replaceResolvedSymbolOrigin", "newResolvedSymbolOrigin", "replaceTypeArguments", "newTypeArguments", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "transformTypeArguments", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirErrorResolvedQualifier extends FirResolvedQualifier implements FirDiagnosticHolder {
    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @FirIdeOnly
    public static /* synthetic */ void getContextSensitiveAlternative$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitErrorResolvedQualifier(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract boolean getCanBeValue();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract ClassId getClassId();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract ConeKotlinType getConeTypeOrNull();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative
    public abstract FirPropertyAccessExpression getContextSensitiveAlternative();

    @Override // org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder
    public abstract ConeDiagnostic getDiagnostic();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract FirResolvedQualifier getExplicitParent();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract List<ConeDiagnostic> getNonFatalDiagnostics();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract FqName getPackageFqName();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract FqName getRelativeClassFqName();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract ConeKotlinType getResolvedLHSTypeForCallableReferenceOrNull();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract FirResolvedSymbolOrigin getResolvedSymbolOrigin();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract boolean getResolvedToCompanionObject();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract FirClassLikeSymbol<?> getSymbol();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract List<FirTypeProjection> getTypeArguments();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    /* JADX INFO: renamed from: isFullyQualified */
    public abstract boolean getIsFullyQualified();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    /* JADX INFO: renamed from: isNullableLHSForCallableReference */
    public abstract boolean getIsNullableLHSForCallableReference();

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract void replaceCanBeValue(boolean newCanBeValue);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative
    public abstract void replaceContextSensitiveAlternative(FirPropertyAccessExpression newContextSensitiveAlternative);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract void replaceIsNullableLHSForCallableReference(boolean newIsNullableLHSForCallableReference);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract void replaceNonFatalDiagnostics(List<? extends ConeDiagnostic> newNonFatalDiagnostics);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract void replaceResolvedLHSTypeForCallableReferenceOrNull(ConeKotlinType newResolvedLHSTypeForCallableReferenceOrNull);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract void replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin newResolvedSymbolOrigin);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract void replaceResolvedToCompanionObject(boolean newResolvedToCompanionObject);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirStatement firStatementTransformErrorResolvedQualifier = transformer.transformErrorResolvedQualifier(this, data);
        firStatementTransformErrorResolvedQualifier.getClass();
        return firStatementTransformErrorResolvedQualifier;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirErrorResolvedQualifier transformAnnotations(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public abstract <D> FirErrorResolvedQualifier transformTypeArguments(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public /* bridge */ /* synthetic */ FirResolvedQualifier transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirResolvedQualifier transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}

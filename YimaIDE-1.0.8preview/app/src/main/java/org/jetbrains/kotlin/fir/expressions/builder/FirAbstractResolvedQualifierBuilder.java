package org.jetbrains.kotlin.fir.expressions.builder;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001J\b\u0010P\u001a\u00020/H&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u00020\u001aX¦\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u001aX¦\u000e¢\u0006\f\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u0004\u0018\u00010#X¦\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010(\u001a\b\u0012\u0002\b\u0003\u0018\u00010)X¦\u000e¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u0004\u0018\u00010/X¦\u000e¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0018\u00104\u001a\u000205X¦\u000e¢\u0006\f\u001a\u0004\b4\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u0004\u0018\u00010\u000fX¦\u000e¢\u0006\f\u001a\u0004\b:\u0010\u0011\"\u0004\b;\u0010\u0013R\u0018\u0010<\u001a\u000205X¦\u000e¢\u0006\f\u001a\u0004\b=\u00106\"\u0004\b>\u00108R\u0018\u0010?\u001a\u000205X¦\u000e¢\u0006\f\u001a\u0004\b@\u00106\"\u0004\bA\u00108R\u0018\u0010B\u001a\u000205X¦\u000e¢\u0006\f\u001a\u0004\bB\u00106\"\u0004\bC\u00108R\u0018\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\bF\u0010\u0018R\u001a\u0010G\u001a\u0004\u0018\u00010HX¦\u000e¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u0018\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\bO\u0010\u0018Ê\u0001\u0002\bRø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006QÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirAbstractResolvedQualifierBuilder;", Argument.Delimiters.none, "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "contextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "getContextSensitiveAlternative", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "setContextSensitiveAlternative", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;)V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "setPackageFqName", "(Lorg/jetbrains/kotlin/name/FqName;)V", "relativeClassFqName", "getRelativeClassFqName", "setRelativeClassFqName", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "setClassId", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)V", "explicitParent", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "getExplicitParent", "()Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "setExplicitParent", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "isNullableLHSForCallableReference", Argument.Delimiters.none, "()Z", "setNullableLHSForCallableReference", "(Z)V", "resolvedLHSTypeForCallableReferenceOrNull", "getResolvedLHSTypeForCallableReferenceOrNull", "setResolvedLHSTypeForCallableReferenceOrNull", "resolvedToCompanionObject", "getResolvedToCompanionObject", "setResolvedToCompanionObject", "canBeValue", "getCanBeValue", "setCanBeValue", "isFullyQualified", "setFullyQualified", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "setResolvedSymbolOrigin", "(Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "build", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirAbstractResolvedQualifierBuilder {
    FirResolvedQualifier build();

    List<FirAnnotation> getAnnotations();

    boolean getCanBeValue();

    ClassId getClassId();

    ConeKotlinType getConeTypeOrNull();

    FirPropertyAccessExpression getContextSensitiveAlternative();

    FirResolvedQualifier getExplicitParent();

    List<ConeDiagnostic> getNonFatalDiagnostics();

    FqName getPackageFqName();

    FqName getRelativeClassFqName();

    ConeKotlinType getResolvedLHSTypeForCallableReferenceOrNull();

    FirResolvedSymbolOrigin getResolvedSymbolOrigin();

    boolean getResolvedToCompanionObject();

    KtSourceElement getSource();

    FirClassLikeSymbol<?> getSymbol();

    List<FirTypeProjection> getTypeArguments();

    /* JADX INFO: renamed from: isFullyQualified */
    boolean getIsFullyQualified();

    /* JADX INFO: renamed from: isNullableLHSForCallableReference */
    boolean getIsNullableLHSForCallableReference();

    void setCanBeValue(boolean z);

    void setClassId(ClassId classId);

    void setConeTypeOrNull(ConeKotlinType coneKotlinType);

    void setContextSensitiveAlternative(FirPropertyAccessExpression firPropertyAccessExpression);

    void setExplicitParent(FirResolvedQualifier firResolvedQualifier);

    void setFullyQualified(boolean z);

    void setNullableLHSForCallableReference(boolean z);

    void setPackageFqName(FqName fqName);

    void setRelativeClassFqName(FqName fqName);

    void setResolvedLHSTypeForCallableReferenceOrNull(ConeKotlinType coneKotlinType);

    void setResolvedSymbolOrigin(FirResolvedSymbolOrigin firResolvedSymbolOrigin);

    void setResolvedToCompanionObject(boolean z);

    void setSource(KtSourceElement ktSourceElement);

    void setSymbol(FirClassLikeSymbol<?> firClassLikeSymbol);
}

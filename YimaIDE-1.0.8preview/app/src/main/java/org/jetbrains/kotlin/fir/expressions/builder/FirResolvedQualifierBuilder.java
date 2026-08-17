package org.jetbrains.kotlin.fir.expressions.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedQualifierImpl;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010Q\u001a\u00020-H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R \u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010'X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00102\u001a\u000203X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00104\"\u0004\b5\u00106R\u001c\u00107\u001a\u0004\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0015\"\u0004\b9\u0010\u0017R+\u0010;\u001a\u0002032\u0006\u0010:\u001a\u0002038V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b<\u00104\"\u0004\b=\u00106R\u001a\u0010@\u001a\u000203X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u00104\"\u0004\bB\u00106R\u001a\u0010C\u001a\u000203X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00104\"\u0004\bD\u00106R\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u001cR\u001c\u0010H\u001a\u0004\u0018\u00010IX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020O0\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u001cRH\u0010T\u001a\u0004\u0018\u00010S2\b\u0010R\u001a\u0004\u0018\u00010S8V@VX\u0097\u000er\u0018\bZ\u0012\b\b[\u0012\u0004\b\b(\\\u0012\n\b]\u0012\u0006\b\n0^8_¢\u0006\u0012\u0012\u0004\bU\u0010\u0005\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YÊ\u0001\u0002\ba¨\u0006`"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirResolvedQualifierBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirAbstractResolvedQualifierBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "contextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "getContextSensitiveAlternative", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "setContextSensitiveAlternative", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;)V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "setPackageFqName", "(Lorg/jetbrains/kotlin/name/FqName;)V", "relativeClassFqName", "getRelativeClassFqName", "setRelativeClassFqName", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)V", "explicitParent", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "getExplicitParent", "()Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "setExplicitParent", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "isNullableLHSForCallableReference", Argument.Delimiters.none, "()Z", "setNullableLHSForCallableReference", "(Z)V", "resolvedLHSTypeForCallableReferenceOrNull", "getResolvedLHSTypeForCallableReferenceOrNull", "setResolvedLHSTypeForCallableReferenceOrNull", "<set-?>", "resolvedToCompanionObject", "getResolvedToCompanionObject", "setResolvedToCompanionObject", "resolvedToCompanionObject$delegate", "Lkotlin/properties/ReadWriteProperty;", "canBeValue", "getCanBeValue", "setCanBeValue", "isFullyQualified", "setFullyQualified", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "setResolvedSymbolOrigin", "(Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "build", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/name/ClassId;", "classId", "getClassId$annotations", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "setClassId", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "Lkotlin/Deprecated;", "message", "Modification of 'classId' has no impact for FirResolvedQualifierBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedQualifierBuilder implements FirAnnotationContainerBuilder, FirAbstractResolvedQualifierBuilder, FirExpressionBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirResolvedQualifierBuilder.class, "resolvedToCompanionObject", "getResolvedToCompanionObject()Z", 0)};
    private boolean canBeValue;
    private ConeKotlinType coneTypeOrNull;
    private FirPropertyAccessExpression contextSensitiveAlternative;
    private FirResolvedQualifier explicitParent;
    private boolean isFullyQualified;
    private boolean isNullableLHSForCallableReference;
    public FqName packageFqName;
    private FqName relativeClassFqName;
    private ConeKotlinType resolvedLHSTypeForCallableReferenceOrNull;
    private FirResolvedSymbolOrigin resolvedSymbolOrigin;
    private KtSourceElement source;
    private FirClassLikeSymbol<?> symbol;
    private final List<FirAnnotation> annotations = new ArrayList();

    /* JADX INFO: renamed from: resolvedToCompanionObject$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty resolvedToCompanionObject = Delegates.INSTANCE.notNull();
    private final List<ConeDiagnostic> nonFatalDiagnostics = new ArrayList();
    private final List<FirTypeProjection> typeArguments = new ArrayList();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'classId' has no impact for FirResolvedQualifierBuilder")
    public static /* synthetic */ void getClassId$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirResolvedQualifier build() {
        return new FirResolvedQualifierImpl(getSource(), getContextSensitiveAlternative(), getConeTypeOrNull(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getPackageFqName(), getRelativeClassFqName(), getSymbol(), getExplicitParent(), getIsNullableLHSForCallableReference(), getResolvedLHSTypeForCallableReferenceOrNull(), getResolvedToCompanionObject(), getCanBeValue(), getIsFullyQualified(), FirBuilderDslKt.toMutableOrEmpty(getNonFatalDiagnostics()), getResolvedSymbolOrigin(), FirBuilderDslKt.toMutableOrEmpty(getTypeArguments()), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public boolean getCanBeValue() {
        return this.canBeValue;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public /* synthetic */ ClassId getClassId() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder, org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public FirPropertyAccessExpression getContextSensitiveAlternative() {
        return this.contextSensitiveAlternative;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public FirResolvedQualifier getExplicitParent() {
        return this.explicitParent;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public List<ConeDiagnostic> getNonFatalDiagnostics() {
        return this.nonFatalDiagnostics;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public FqName getPackageFqName() throws UninitializedPropertyAccessException {
        FqName fqName = this.packageFqName;
        if (fqName != null) {
            return fqName;
        }
        Intrinsics.throwUninitializedPropertyAccessException("packageFqName");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public FqName getRelativeClassFqName() {
        return this.relativeClassFqName;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public ConeKotlinType getResolvedLHSTypeForCallableReferenceOrNull() {
        return this.resolvedLHSTypeForCallableReferenceOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public FirResolvedSymbolOrigin getResolvedSymbolOrigin() {
        return this.resolvedSymbolOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public boolean getResolvedToCompanionObject() {
        return ((Boolean) this.resolvedToCompanionObject.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder, org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public FirClassLikeSymbol<?> getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public List<FirTypeProjection> getTypeArguments() {
        return this.typeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    /* JADX INFO: renamed from: isFullyQualified, reason: from getter */
    public boolean getIsFullyQualified() {
        return this.isFullyQualified;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    /* JADX INFO: renamed from: isNullableLHSForCallableReference, reason: from getter */
    public boolean getIsNullableLHSForCallableReference() {
        return this.isNullableLHSForCallableReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setCanBeValue(boolean z) {
        this.canBeValue = z;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public /* synthetic */ void setClassId(ClassId classId) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder, org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setContextSensitiveAlternative(FirPropertyAccessExpression firPropertyAccessExpression) {
        this.contextSensitiveAlternative = firPropertyAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setExplicitParent(FirResolvedQualifier firResolvedQualifier) {
        this.explicitParent = firResolvedQualifier;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setFullyQualified(boolean z) {
        this.isFullyQualified = z;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setNullableLHSForCallableReference(boolean z) {
        this.isNullableLHSForCallableReference = z;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setPackageFqName(FqName fqName) {
        fqName.getClass();
        this.packageFqName = fqName;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setRelativeClassFqName(FqName fqName) {
        this.relativeClassFqName = fqName;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setResolvedLHSTypeForCallableReferenceOrNull(ConeKotlinType coneKotlinType) {
        this.resolvedLHSTypeForCallableReferenceOrNull = coneKotlinType;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setResolvedSymbolOrigin(FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setResolvedToCompanionObject(boolean z) {
        this.resolvedToCompanionObject.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder, org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder
    public void setSymbol(FirClassLikeSymbol<?> firClassLikeSymbol) {
        this.symbol = firClassLikeSymbol;
    }
}

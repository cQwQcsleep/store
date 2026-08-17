package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirIdeOnly;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001Bµ\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0015\u001a\u00020\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0013\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\t\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\t\u0012\u0006\u0010\u001e\u001a\u00020\u0019¢\u0006\u0004\b\u001f\u0010 J5\u0010U\u001a\u00020V\"\u0004\b\u0000\u0010W\"\u0004\b\u0001\u0010X2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u0002HW\u0012\u0004\u0012\u0002HX0Z2\u0006\u0010[\u001a\u0002HXH\u0016¢\u0006\u0002\u0010\\J)\u0010]\u001a\u00020\u0000\"\u0004\b\u0000\u0010X2\f\u0010^\u001a\b\u0012\u0004\u0012\u0002HX0_2\u0006\u0010[\u001a\u0002HXH\u0016¢\u0006\u0002\u0010`J)\u0010a\u001a\u00020\u0000\"\u0004\b\u0000\u0010X2\f\u0010^\u001a\b\u0012\u0004\u0012\u0002HX0_2\u0006\u0010[\u001a\u0002HXH\u0016¢\u0006\u0002\u0010`J)\u0010b\u001a\u00020\u0000\"\u0004\b\u0000\u0010X2\f\u0010^\u001a\b\u0012\u0004\u0012\u0002HX0_2\u0006\u0010[\u001a\u0002HXH\u0016¢\u0006\u0002\u0010`J\u0012\u0010c\u001a\u00020V2\b\u0010d\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010e\u001a\u00020V2\b\u0010f\u001a\u0004\u0018\u00010\u0007H\u0016J\u0016\u0010g\u001a\u00020V2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\n0iH\u0016J\u0010\u0010j\u001a\u00020V2\u0006\u0010k\u001a\u00020\u0013H\u0016J\u0012\u0010l\u001a\u00020V2\b\u0010m\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010n\u001a\u00020V2\u0006\u0010o\u001a\u00020\u0013H\u0016J\u0010\u0010p\u001a\u00020V2\u0006\u0010q\u001a\u00020\u0013H\u0016J\u0016\u0010r\u001a\u00020V2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020\u00190iH\u0016J\u0012\u0010t\u001a\u00020V2\b\u0010u\u001a\u0004\u0018\u00010\u001bH\u0016J\u0016\u0010v\u001a\u00020V2\f\u0010w\u001a\b\u0012\u0004\u0012\u00020\u001d0iH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R*\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0016@\u0016X\u0097\u000er\u0002\b)¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R*\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0016@\u0016X\u0097\u000er\u0002\b/¢\u0006\u0014\n\u0000\u0012\u0004\b*\u0010$\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u000e¢\u0006\u0010\n\u0002\u00104\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0016\u0010\r\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00106R\u001a\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010>\"\u0004\b?\u0010@R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010,\"\u0004\bB\u0010.R\u001a\u0010\u0015\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010>\"\u0004\bD\u0010@R\u001a\u0010\u0016\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010>\"\u0004\bF\u0010@R\u0014\u0010\u0017\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010>R\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\tX\u0096\u000e¢\u0006\u0010\n\u0002\u00104\u001a\u0004\bG\u00101\"\u0004\bH\u00103R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\"\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\tX\u0096\u000e¢\u0006\u0010\n\u0002\u00104\u001a\u0004\bM\u00101\"\u0004\bN\u00103R\u0014\u0010\u001e\u001a\u00020\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010PR\u0016\u0010Q\u001a\u0004\u0018\u00010R8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010T¨\u0006x"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirErrorResolvedQualifierImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "contextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "relativeClassFqName", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "explicitParent", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "isNullableLHSForCallableReference", Argument.Delimiters.none, "resolvedLHSTypeForCallableReferenceOrNull", "resolvedToCompanionObject", "canBeValue", "isFullyQualified", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "diagnostic", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;ZLorg/jetbrains/kotlin/fir/types/ConeKotlinType;ZZZLjava/util/List;Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;Ljava/util/List;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getContextSensitiveAlternative$annotations", "()V", "getContextSensitiveAlternative", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "setContextSensitiveAlternative", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;)V", "Lorg/jetbrains/kotlin/fir/FirIdeOnly;", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getRelativeClassFqName", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getExplicitParent", "()Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "setExplicitParent", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "()Z", "setNullableLHSForCallableReference", "(Z)V", "getResolvedLHSTypeForCallableReferenceOrNull", "setResolvedLHSTypeForCallableReferenceOrNull", "getResolvedToCompanionObject", "setResolvedToCompanionObject", "getCanBeValue", "setCanBeValue", "getNonFatalDiagnostics-5e3fPpI", "setNonFatalDiagnostics-GqUYU-s", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "setResolvedSymbolOrigin", "(Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "getTypeArguments-5e3fPpI", "setTypeArguments-GqUYU-s", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirErrorResolvedQualifierImpl;", "transformAnnotations", "transformTypeArguments", "replaceContextSensitiveAlternative", "newContextSensitiveAlternative", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceIsNullableLHSForCallableReference", "newIsNullableLHSForCallableReference", "replaceResolvedLHSTypeForCallableReferenceOrNull", "newResolvedLHSTypeForCallableReferenceOrNull", "replaceResolvedToCompanionObject", "newResolvedToCompanionObject", "replaceCanBeValue", "newCanBeValue", "replaceNonFatalDiagnostics", "newNonFatalDiagnostics", "replaceResolvedSymbolOrigin", "newResolvedSymbolOrigin", "replaceTypeArguments", "newTypeArguments", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorResolvedQualifierImpl extends FirErrorResolvedQualifier {
    private List<FirAnnotation> annotations;
    private boolean canBeValue;
    private ConeKotlinType coneTypeOrNull;
    private FirPropertyAccessExpression contextSensitiveAlternative;
    private final ConeDiagnostic diagnostic;
    private FirResolvedQualifier explicitParent;
    private final boolean isFullyQualified;
    private boolean isNullableLHSForCallableReference;
    private List<ConeDiagnostic> nonFatalDiagnostics;
    private final FqName packageFqName;
    private final FqName relativeClassFqName;
    private ConeKotlinType resolvedLHSTypeForCallableReferenceOrNull;
    private FirResolvedSymbolOrigin resolvedSymbolOrigin;
    private boolean resolvedToCompanionObject;
    private final KtSourceElement source;
    private final FirClassLikeSymbol<?> symbol;
    private List<FirTypeProjection> typeArguments;

    private FirErrorResolvedQualifierImpl(KtSourceElement ktSourceElement, FirPropertyAccessExpression firPropertyAccessExpression, ConeKotlinType coneKotlinType, List<FirAnnotation> list, FqName fqName, FqName fqName2, FirClassLikeSymbol<?> firClassLikeSymbol, FirResolvedQualifier firResolvedQualifier, boolean z, ConeKotlinType coneKotlinType2, boolean z2, boolean z3, boolean z4, List<ConeDiagnostic> list2, FirResolvedSymbolOrigin firResolvedSymbolOrigin, List<FirTypeProjection> list3, ConeDiagnostic coneDiagnostic) {
        fqName.getClass();
        coneDiagnostic.getClass();
        this.source = ktSourceElement;
        this.contextSensitiveAlternative = firPropertyAccessExpression;
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.packageFqName = fqName;
        this.relativeClassFqName = fqName2;
        this.symbol = firClassLikeSymbol;
        this.explicitParent = firResolvedQualifier;
        this.isNullableLHSForCallableReference = z;
        this.resolvedLHSTypeForCallableReferenceOrNull = coneKotlinType2;
        this.resolvedToCompanionObject = z2;
        this.canBeValue = z3;
        this.isFullyQualified = z4;
        this.nonFatalDiagnostics = list2;
        this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
        this.typeArguments = list3;
        this.diagnostic = coneDiagnostic;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @FirIdeOnly
    public static /* synthetic */ void getContextSensitiveAlternative$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m438getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        FirResolvedQualifier explicitParent = getExplicitParent();
        if (explicitParent != null) {
            explicitParent.accept(visitor, data);
        }
        Iterator<T> it2 = MutableOrEmptyList.m194boximpl(m440getTypeArguments5e3fPpI()).iterator();
        while (it2.hasNext()) {
            ((FirTypeProjection) it2.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m438getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m438getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public boolean getCanBeValue() {
        return this.canBeValue;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public ClassId getClassId() {
        FqName relativeClassFqName = getRelativeClassFqName();
        if (relativeClassFqName != null) {
            return new ClassId(getPackageFqName(), relativeClassFqName, false);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative
    public FirPropertyAccessExpression getContextSensitiveAlternative() {
        return this.contextSensitiveAlternative;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder
    public ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public FirResolvedQualifier getExplicitParent() {
        return this.explicitParent;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public /* bridge */ /* synthetic */ List getNonFatalDiagnostics() {
        return MutableOrEmptyList.m194boximpl(m439getNonFatalDiagnostics5e3fPpI());
    }

    /* JADX INFO: renamed from: getNonFatalDiagnostics-5e3fPpI, reason: not valid java name */
    public List<ConeDiagnostic> m439getNonFatalDiagnostics5e3fPpI() {
        return this.nonFatalDiagnostics;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public FqName getPackageFqName() {
        return this.packageFqName;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public FqName getRelativeClassFqName() {
        return this.relativeClassFqName;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public ConeKotlinType getResolvedLHSTypeForCallableReferenceOrNull() {
        return this.resolvedLHSTypeForCallableReferenceOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public FirResolvedSymbolOrigin getResolvedSymbolOrigin() {
        return this.resolvedSymbolOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public boolean getResolvedToCompanionObject() {
        return this.resolvedToCompanionObject;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public FirClassLikeSymbol<?> getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public /* bridge */ /* synthetic */ List getTypeArguments() {
        return MutableOrEmptyList.m194boximpl(m440getTypeArguments5e3fPpI());
    }

    /* JADX INFO: renamed from: getTypeArguments-5e3fPpI, reason: not valid java name */
    public List<FirTypeProjection> m440getTypeArguments5e3fPpI() {
        return this.typeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    /* JADX INFO: renamed from: isFullyQualified, reason: from getter */
    public boolean getIsFullyQualified() {
        return this.isFullyQualified;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    /* JADX INFO: renamed from: isNullableLHSForCallableReference, reason: from getter */
    public boolean getIsNullableLHSForCallableReference() {
        return this.isNullableLHSForCallableReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m441setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public void replaceCanBeValue(boolean newCanBeValue) {
        setCanBeValue(newCanBeValue);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative
    public void replaceContextSensitiveAlternative(FirPropertyAccessExpression newContextSensitiveAlternative) {
        setContextSensitiveAlternative(newContextSensitiveAlternative);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public void replaceIsNullableLHSForCallableReference(boolean newIsNullableLHSForCallableReference) {
        setNullableLHSForCallableReference(newIsNullableLHSForCallableReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public void replaceNonFatalDiagnostics(List<? extends ConeDiagnostic> newNonFatalDiagnostics) {
        newNonFatalDiagnostics.getClass();
        m442setNonFatalDiagnosticsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newNonFatalDiagnostics));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public void replaceResolvedLHSTypeForCallableReferenceOrNull(ConeKotlinType newResolvedLHSTypeForCallableReferenceOrNull) {
        setResolvedLHSTypeForCallableReferenceOrNull(newResolvedLHSTypeForCallableReferenceOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public void replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin newResolvedSymbolOrigin) {
        setResolvedSymbolOrigin(newResolvedSymbolOrigin);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public void replaceResolvedToCompanionObject(boolean newResolvedToCompanionObject) {
        setResolvedToCompanionObject(newResolvedToCompanionObject);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments) {
        newTypeArguments.getClass();
        m443setTypeArgumentsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newTypeArguments));
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m441setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setCanBeValue(boolean z) {
        this.canBeValue = z;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setContextSensitiveAlternative(FirPropertyAccessExpression firPropertyAccessExpression) {
        this.contextSensitiveAlternative = firPropertyAccessExpression;
    }

    public void setExplicitParent(FirResolvedQualifier firResolvedQualifier) {
        this.explicitParent = firResolvedQualifier;
    }

    /* JADX INFO: renamed from: setNonFatalDiagnostics-GqUYU-s, reason: not valid java name */
    public void m442setNonFatalDiagnosticsGqUYUs(List<ConeDiagnostic> list) {
        this.nonFatalDiagnostics = list;
    }

    public void setNullableLHSForCallableReference(boolean z) {
        this.isNullableLHSForCallableReference = z;
    }

    public void setResolvedLHSTypeForCallableReferenceOrNull(ConeKotlinType coneKotlinType) {
        this.resolvedLHSTypeForCallableReferenceOrNull = coneKotlinType;
    }

    public void setResolvedSymbolOrigin(FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
    }

    public void setResolvedToCompanionObject(boolean z) {
        this.resolvedToCompanionObject = z;
    }

    /* JADX INFO: renamed from: setTypeArguments-GqUYU-s, reason: not valid java name */
    public void m443setTypeArgumentsGqUYUs(List<FirTypeProjection> list) {
        this.typeArguments = list;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirErrorResolvedQualifierImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m438getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirErrorResolvedQualifierImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        FirResolvedQualifier explicitParent = getExplicitParent();
        setExplicitParent(explicitParent != null ? (FirResolvedQualifier) explicitParent.transform(transformer, data) : null);
        transformTypeArguments((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public <D> FirErrorResolvedQualifierImpl transformTypeArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m440getTypeArguments5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirErrorResolvedQualifier transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public /* bridge */ /* synthetic */ FirResolvedQualifier transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier
    public /* bridge */ /* synthetic */ FirErrorResolvedQualifier transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirResolvedQualifier transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirErrorResolvedQualifierImpl(KtSourceElement ktSourceElement, FirPropertyAccessExpression firPropertyAccessExpression, ConeKotlinType coneKotlinType, List list, FqName fqName, FqName fqName2, FirClassLikeSymbol firClassLikeSymbol, FirResolvedQualifier firResolvedQualifier, boolean z, ConeKotlinType coneKotlinType2, boolean z2, boolean z3, boolean z4, List list2, FirResolvedSymbolOrigin firResolvedSymbolOrigin, List list3, ConeDiagnostic coneDiagnostic, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, firPropertyAccessExpression, coneKotlinType, list, fqName, fqName2, firClassLikeSymbol, firResolvedQualifier, z, coneKotlinType2, z2, z3, z4, list2, firResolvedSymbolOrigin, list3, coneDiagnostic);
    }
}

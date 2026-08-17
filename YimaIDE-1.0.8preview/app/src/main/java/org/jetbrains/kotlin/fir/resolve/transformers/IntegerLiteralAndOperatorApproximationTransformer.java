package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedErrorReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.IntegerLiteralAndOperatorApproximationTransformer;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirIntegerConstantOperatorScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J)\u0010\u001c\u001a\u0002H\u001d\"\b\b\u0000\u0010\u001d*\u00020\u001e2\u0006\u0010\u001f\u001a\u0002H\u001d2\b\u0010 \u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010!J\u001a\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010 \u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020(2\b\u0010 \u001a\u0004\u0018\u00010\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0014\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0015\u0010\u0011¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/IntegerLiteralAndOperatorApproximationTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "toLongSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getToLongSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "toLongSymbol$delegate", "Lkotlin/Lazy;", "toULongSymbol", "getToULongSymbol", "toULongSymbol$delegate", "findConversionFunction", "receiverType", "Lorg/jetbrains/kotlin/fir/types/impl/FirImplicitBuiltinTypeRef;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformLiteralExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "transformIntegerLiteralOperatorCall", "integerLiteralOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IntegerLiteralAndOperatorApproximationTransformer extends FirTransformer<ConeKotlinType> implements SessionAndScopeSessionHolder {
    private final ScopeSession scopeSession;
    private final FirSession session;

    /* JADX INFO: renamed from: toLongSymbol$delegate, reason: from kotlin metadata */
    private final Lazy toLongSymbol;

    /* JADX INFO: renamed from: toULongSymbol$delegate, reason: from kotlin metadata */
    private final Lazy toULongSymbol;

    public IntegerLiteralAndOperatorApproximationTransformer(FirSession firSession, ScopeSession scopeSession) {
        firSession.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.toLongSymbol = LazyKt.lazy(new Function0() { // from class: ft6
            public final Object invoke() {
                return IntegerLiteralAndOperatorApproximationTransformer.c(this.b);
            }
        });
        this.toULongSymbol = LazyKt.lazy(new Function0() { // from class: gt6
            public final Object invoke() {
                return IntegerLiteralAndOperatorApproximationTransformer.b(this.b);
            }
        });
    }

    public static FirNamedFunctionSymbol b(IntegerLiteralAndOperatorApproximationTransformer integerLiteralAndOperatorApproximationTransformer) {
        return integerLiteralAndOperatorApproximationTransformer.findConversionFunction(integerLiteralAndOperatorApproximationTransformer.getSession().getBuiltinTypes().getUIntType(), OperatorNameConventions.TO_ULONG);
    }

    public static FirNamedFunctionSymbol c(IntegerLiteralAndOperatorApproximationTransformer integerLiteralAndOperatorApproximationTransformer) {
        return integerLiteralAndOperatorApproximationTransformer.findConversionFunction(integerLiteralAndOperatorApproximationTransformer.getSession().getBuiltinTypes().getIntType(), OperatorNameConventions.TO_LONG);
    }

    private final FirNamedFunctionSymbol findConversionFunction(FirImplicitBuiltinTypeRef receiverType, Name name) {
        FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(this, receiverType.getConeType(), CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
        firTypeScopeScope.getClass();
        return (FirNamedFunctionSymbol) CollectionsKt.single(FirScopeKt.getFunctions(firTypeScopeScope, name));
    }

    private final FirNamedFunctionSymbol getToLongSymbol() {
        return (FirNamedFunctionSymbol) this.toLongSymbol.getValue();
    }

    private final FirNamedFunctionSymbol getToULongSymbol() {
        return (FirNamedFunctionSymbol) this.toULongSymbol.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformIntegerLiteralOperatorCall(FirIntegerLiteralOperatorCall integerLiteralOperatorCall, ConeKotlinType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirResolvedNamedReference firResolvedNamedReferenceBuild;
        integerLiteralOperatorCall.getClass();
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(integerLiteralOperatorCall);
        ConeIntegerLiteralType coneIntegerLiteralType = resolvedType instanceof ConeIntegerLiteralType ? (ConeIntegerLiteralType) resolvedType : null;
        if (coneIntegerLiteralType == null) {
            return integerLiteralOperatorCall;
        }
        ConeClassLikeType approximatedType = coneIntegerLiteralType.getApproximatedType(data != null ? TypeExpansionUtilsKt.fullyExpandedType(this, data) : null);
        integerLiteralOperatorCall.transformDispatchReceiver(this, null);
        integerLiteralOperatorCall.transformExtensionReceiver(this, null);
        integerLiteralOperatorCall.getArgumentList().transformArguments(this, null);
        integerLiteralOperatorCall.replaceConeTypeOrNull(approximatedType);
        FirNamedReference calleeReference = integerLiteralOperatorCall.getCalleeReference();
        if (calleeReference instanceof FirResolvedNamedReference) {
            FirResolvedNamedReference firResolvedNamedReference = (FirResolvedNamedReference) calleeReference;
            FirBasedSymbol<?> resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
            resolvedSymbol.getClass();
            FirNamedFunctionSymbol originalForWrappedIntegerOperator = FirIntegerConstantOperatorScopeKt.getOriginalForWrappedIntegerOperator((FirNamedFunction) ((FirNamedFunctionSymbol) resolvedSymbol).getFir());
            originalForWrappedIntegerOperator.getClass();
            if (firResolvedNamedReference instanceof FirResolvedErrorReference) {
                FirResolvedErrorReferenceBuilder firResolvedErrorReferenceBuilder = new FirResolvedErrorReferenceBuilder();
                FirResolvedErrorReference firResolvedErrorReference = (FirResolvedErrorReference) calleeReference;
                firResolvedErrorReferenceBuilder.setName(firResolvedErrorReference.getName());
                firResolvedErrorReferenceBuilder.setSource(firResolvedErrorReference.getSource());
                firResolvedErrorReferenceBuilder.setResolvedSymbol(originalForWrappedIntegerOperator);
                firResolvedErrorReferenceBuilder.setDiagnostic(firResolvedErrorReference.getDiagnostic());
                firResolvedNamedReferenceBuild = firResolvedErrorReferenceBuilder.build();
            } else {
                FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
                firResolvedNamedReferenceBuilder.setName(firResolvedNamedReference.getName());
                firResolvedNamedReferenceBuilder.setSource(firResolvedNamedReference.getSource());
                firResolvedNamedReferenceBuilder.setResolvedSymbol(originalForWrappedIntegerOperator);
                firResolvedNamedReferenceBuild = firResolvedNamedReferenceBuilder.build();
            }
            integerLiteralOperatorCall.replaceCalleeReference((FirNamedReference) firResolvedNamedReferenceBuild);
        }
        if (ConeBuiltinTypeUtilsKt.isInt(approximatedType) || ConeBuiltinTypeUtilsKt.isUInt(approximatedType)) {
            return integerLiteralOperatorCall;
        }
        integerLiteralOperatorCall.replaceConeTypeOrNull(coneIntegerLiteralType.getIsUnsigned() ? getSession().getBuiltinTypes().getUIntType().getConeType() : getSession().getBuiltinTypes().getIntType().getConeType());
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        KtSourceElement source = integerLiteralOperatorCall.getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.IntToLongConversion.INSTANCE, null, 2, null) : null;
        firFunctionCallBuilder.setSource(ktSourceElementFakeElement$default);
        firFunctionCallBuilder.setConeTypeOrNull(getSession().getBuiltinTypes().getLongType().getConeType());
        firFunctionCallBuilder.setExplicitReceiver(integerLiteralOperatorCall);
        firFunctionCallBuilder.setDispatchReceiver(integerLiteralOperatorCall);
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder2 = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder2.setSource(ktSourceElementFakeElement$default);
        if (coneIntegerLiteralType.getIsUnsigned()) {
            firResolvedNamedReferenceBuilder2.setName(OperatorNameConventions.TO_ULONG);
            firResolvedNamedReferenceBuilder2.setResolvedSymbol(getToULongSymbol());
        } else {
            firResolvedNamedReferenceBuilder2.setName(OperatorNameConventions.TO_LONG);
            firResolvedNamedReferenceBuilder2.setResolvedSymbol(getToLongSymbol());
        }
        firFunctionCallBuilder.setCalleeReference(firResolvedNamedReferenceBuilder2.build());
        return firFunctionCallBuilder.mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformLiteralExpression(FirLiteralExpression literalExpression, ConeKotlinType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        literalExpression.getClass();
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(literalExpression);
        ConeIntegerLiteralType coneIntegerLiteralType = resolvedType instanceof ConeIntegerLiteralType ? (ConeIntegerLiteralType) resolvedType : null;
        if (coneIntegerLiteralType == null) {
            return literalExpression;
        }
        ConeClassLikeType approximatedType = coneIntegerLiteralType.getApproximatedType(data != null ? TypeExpansionUtilsKt.fullyExpandedType(this, data) : null);
        literalExpression.replaceConeTypeOrNull(approximatedType);
        ConstantValueKind constKind = FirTypeUtilsKt.toConstKind(approximatedType);
        constKind.getClass();
        literalExpression.replaceKind(constKind);
        return literalExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, ConeKotlinType data) {
        element.getClass();
        return element;
    }
}

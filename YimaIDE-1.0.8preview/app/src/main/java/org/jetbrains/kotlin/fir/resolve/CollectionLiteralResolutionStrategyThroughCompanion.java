package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\bH\u0002J\u0018\u0010\t\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0014\u0010\f\u001a\u00020\u0007*\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0015\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\rH\u0010¢\u0006\u0002\b\u0014J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u000f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralResolutionStrategyThroughCompanion;", "Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralResolutionStrategy;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;)V", "isOperatorOf", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "isVisible", "receiver", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "declaresVisibleOf", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "buildReceiverIfThereIsVisibleOf", "expectedClass", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "declaresOperatorOf", "expectedType", "declaresOperatorOf$org_jetbrains_kotlin_resolve", "prepareRawCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CollectionLiteralResolutionStrategyThroughCompanion extends CollectionLiteralResolutionStrategy {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionLiteralResolutionStrategyThroughCompanion(ResolutionContext resolutionContext) {
        super(resolutionContext);
        resolutionContext.getClass();
    }

    public static Unit a(Ref.ObjectRef objectRef, CollectionLiteralResolutionStrategyThroughCompanion collectionLiteralResolutionStrategyThroughCompanion, FirResolvedQualifier firResolvedQualifier, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (objectRef.element != null) {
            return Unit.INSTANCE;
        }
        if (collectionLiteralResolutionStrategyThroughCompanion.isOperatorOf(firCallableSymbol) && !DeprecationUtilsKt.isDeprecationLevelHidden(firCallableSymbol, collectionLiteralResolutionStrategyThroughCompanion.getContext().getSession())) {
            objectRef.element = Boolean.valueOf(collectionLiteralResolutionStrategyThroughCompanion.isVisible(firCallableSymbol, firResolvedQualifier));
        }
        return Unit.INSTANCE;
    }

    private final FirResolvedQualifier buildReceiverIfThereIsVisibleOf(FirRegularClassSymbol expectedClass, FirCollectionLiteral collectionLiteral) {
        FirRegularClassSymbol resolvedCompanionObjectSymbol;
        KtSourceElement source;
        if (expectedClass != null && (resolvedCompanionObjectSymbol = expectedClass.getResolvedCompanionObjectSymbol()) != null) {
            FirResolvedQualifier implicitResolvedQualifierReceiver = ResolveUtilsKt.toImplicitResolvedQualifierReceiver(resolvedCompanionObjectSymbol, getComponents(), (collectionLiteral == null || (source = collectionLiteral.getSource()) == null) ? null : KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.DesugaredReceiverForOperatorOfCall.INSTANCE, null, 2, null));
            if (declaresVisibleOf(resolvedCompanionObjectSymbol, implicitResolvedQualifierReceiver)) {
                return implicitResolvedQualifierReceiver;
            }
        }
        return null;
    }

    private final boolean declaresVisibleOf(FirRegularClassSymbol firRegularClassSymbol, final FirResolvedQualifier firResolvedQualifier) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.processAllDeclaredCallables$default(firRegularClassSymbol, getContext().getSession(), null, new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.a
            public final Object invoke(Object obj) {
                return CollectionLiteralResolutionStrategyThroughCompanion.a(objectRef, this, firResolvedQualifier, (FirCallableSymbol) obj);
            }
        }, 2, null);
        Boolean bool = (Boolean) objectRef.element;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final boolean isOperatorOf(FirCallableSymbol<?> firCallableSymbol) {
        return (firCallableSymbol instanceof FirNamedFunctionSymbol) && firCallableSymbol.getResolvedStatus().isOperator() && Intrinsics.areEqual(((FirNamedFunctionSymbol) firCallableSymbol).getName(), OperatorNameConventions.OF);
    }

    private final boolean isVisible(FirCallableSymbol<?> firCallableSymbol, FirResolvedQualifier firResolvedQualifier) {
        return FirVisibilityChecker.isVisible$default(FirVisibilityCheckerKt.getVisibilityChecker(getContext().getSession()), (FirMemberDeclaration) firCallableSymbol.getFir(), getContext().getSession(), getContext().getBodyResolveComponents().getFile(), getContext().getBodyResolveComponents().getContainingDeclarations(), firResolvedQualifier, false, null, false, null, 480, null);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionStrategy
    public boolean declaresOperatorOf$org_jetbrains_kotlin_resolve(FirRegularClassSymbol expectedType) {
        expectedType.getClass();
        return buildReceiverIfThereIsVisibleOf(expectedType, null) != null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionStrategy
    public FirFunctionCall prepareRawCall(FirCollectionLiteral collectionLiteral, FirRegularClassSymbol expectedClass) {
        collectionLiteral.getClass();
        FirResolvedQualifier firResolvedQualifierBuildReceiverIfThereIsVisibleOf = buildReceiverIfThereIsVisibleOf(expectedClass, collectionLiteral);
        if (firResolvedQualifierBuildReceiverIfThereIsVisibleOf == null) {
            return null;
        }
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.getAnnotations().addAll(collectionLiteral.getAnnotations());
        firFunctionCallBuilder.setExplicitReceiver(firResolvedQualifierBuildReceiverIfThereIsVisibleOf);
        firFunctionCallBuilder.setSource(collectionLiteral.getSource());
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        KtSourceElement source = collectionLiteral.getSource();
        firSimpleNamedReferenceBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.CalleeReferenceForOperatorOfCall.INSTANCE, null, 2, null) : null);
        firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.OF);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setArgumentList(collectionLiteral.getArgumentList());
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return firFunctionCallBuilder.mo288build();
    }
}

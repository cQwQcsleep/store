package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.resolve.FirDoubleColonExpressionResolver;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\n\u001a\u00020\u000b*\u00020\fH\u0002J\f\u0010\r\u001a\u00020\u000b*\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0010JK\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\b\b\u0000\u0010\u0016*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00102\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b0\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u0001H\u00160\u0018H\u0002¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u001dH\u0002J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u000f\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirDoubleColonExpressionResolver;", Argument.Delimiters.none, "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "context", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "canBeConsideredProperExpression", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "canBeConsideredProperType", "shouldTryResolveLHSAsExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "shouldTryResolveLHSAsType", "resolveDoubleColonLHS", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", "doubleColonExpression", "tryResolveLHS", "T", "criterion", "Lkotlin/Function1;", "resolve", "(Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", "expandedRegularClassIfAny", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "resolveExpressionOnLHS", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Expression;", "resolveTypeOnLHS", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Type;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDoubleColonExpressionResolver {
    private final BodyResolveComponents components;
    private final BodyResolveContext context;
    private final FirSession session;

    public FirDoubleColonExpressionResolver(BodyResolveComponents bodyResolveComponents, BodyResolveContext bodyResolveContext) {
        bodyResolveComponents.getClass();
        bodyResolveContext.getClass();
        this.components = bodyResolveComponents;
        this.context = bodyResolveContext;
        this.session = bodyResolveComponents.getSession();
    }

    public static DoubleColonLHS.Type a(FirDoubleColonExpressionResolver firDoubleColonExpressionResolver, FirExpression firExpression) {
        firExpression.getClass();
        return firDoubleColonExpressionResolver.resolveTypeOnLHS(firExpression);
    }

    private final boolean canBeConsideredProperExpression(FirExpression firExpression) {
        FirExpression explicitReceiver;
        return !(firExpression instanceof FirQualifiedAccessExpression) || ((explicitReceiver = ((FirQualifiedAccessExpression) firExpression).getExplicitReceiver()) != null && canBeConsideredProperExpression(explicitReceiver));
    }

    private final boolean canBeConsideredProperType(FirExpression firExpression) {
        FirQualifiedAccessExpression firQualifiedAccessExpression;
        FirExpression explicitReceiver;
        FirExpression explicitReceiver2;
        if ((firExpression instanceof FirFunctionCall) && ((explicitReceiver2 = ((FirFunctionCall) firExpression).getExplicitReceiver()) == null || canBeConsideredProperType(explicitReceiver2))) {
            return false;
        }
        if (((firExpression instanceof FirQualifiedAccessExpression) && (((explicitReceiver = (firQualifiedAccessExpression = (FirQualifiedAccessExpression) firExpression).getExplicitReceiver()) == null || canBeConsideredProperType(explicitReceiver)) && (firQualifiedAccessExpression.getCalleeReference() instanceof FirNamedReference))) || (firExpression instanceof FirResolvedQualifier)) {
            return true;
        }
        if (firExpression instanceof FirSmartCastExpression) {
            return canBeConsideredProperType(((FirSmartCastExpression) firExpression).getOriginalExpression());
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirRegularClass expandedRegularClassIfAny(FirResolvedQualifier firResolvedQualifier) {
        FirClassLikeDeclaration firClassLikeDeclaration;
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> symbol;
        FirClassLikeSymbol<?> symbol2 = firResolvedQualifier.getSymbol();
        if (symbol2 != null && (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol2.getFir()) != null) {
            while (firClassLikeDeclaration instanceof FirTypeAlias) {
                ConeClassLikeType expandedConeType = FirDeclarationUtilKt.getExpandedConeType((FirTypeAlias) firClassLikeDeclaration);
                if (expandedConeType == null || (lookupTag = expandedConeType.getLookupTag()) == null || (symbol = ToSymbolUtilsKt.toSymbol(lookupTag, this.session)) == null || (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir()) == null) {
                    return null;
                }
            }
            if (firClassLikeDeclaration instanceof FirRegularClass) {
                return (FirRegularClass) firClassLikeDeclaration;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final DoubleColonLHS.Expression resolveExpressionOnLHS(FirExpression expression) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(expression);
        FirExpression firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(expression);
        if (!(firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier)) {
            return new DoubleColonLHS.Expression(resolvedType, false);
        }
        FirRegularClass firRegularClassExpandedRegularClassIfAny = expandedRegularClassIfAny((FirResolvedQualifier) firExpressionUnwrapSmartcastExpression);
        if (firRegularClassExpandedRegularClassIfAny != null && firRegularClassExpandedRegularClassIfAny.getClassKind() == ClassKind.OBJECT) {
            return new DoubleColonLHS.Expression(resolvedType, true);
        }
        return null;
    }

    private final DoubleColonLHS.Type resolveTypeOnLHS(FirExpression expression) {
        FirExpression firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(expression);
        FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
        if (firResolvedQualifier == null) {
            return null;
        }
        return FirTypeResolverKt.getTypeResolver(this.session).resolveTypeOnDoubleColonLHS(firResolvedQualifier, new TypeResolutionConfiguration(BodyResolveComponentsKt.createCurrentScopeList(this.components), this.context.getContainingClassDeclarations(), this.context.getFile(), this.context.getTopContainerForTypeResolution()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldTryResolveLHSAsExpression(FirCallableReferenceAccess expression) {
        FirExpression explicitReceiver = expression.getExplicitReceiver();
        return (explicitReceiver == null || !canBeConsideredProperExpression(explicitReceiver) || expression.getHasQuestionMarkAtLHS()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldTryResolveLHSAsType(FirCallableReferenceAccess expression) {
        FirExpression explicitReceiver = expression.getExplicitReceiver();
        return explicitReceiver != null && canBeConsideredProperType(explicitReceiver);
    }

    private final <T extends DoubleColonLHS> T tryResolveLHS(FirCallableReferenceAccess doubleColonExpression, Function1<? super FirCallableReferenceAccess, Boolean> criterion, Function1<? super FirExpression, ? extends T> resolve) {
        FirExpression explicitReceiver = doubleColonExpression.getExplicitReceiver();
        if (explicitReceiver != null && ((Boolean) criterion.invoke(doubleColonExpression)).booleanValue()) {
            return (T) resolve.invoke(explicitReceiver);
        }
        return null;
    }

    public final DoubleColonLHS resolveDoubleColonLHS(FirCallableReferenceAccess doubleColonExpression) {
        DoubleColonLHS.Type type;
        doubleColonExpression.getClass();
        DoubleColonLHS.Expression expression = (DoubleColonLHS.Expression) tryResolveLHS(doubleColonExpression, new FirDoubleColonExpressionResolver$resolveDoubleColonLHS$resultForExpr$1(this), new FirDoubleColonExpressionResolver$resolveDoubleColonLHS$resultForExpr$2(this));
        if ((expression == null || expression.getIsObjectQualifier()) && (type = (DoubleColonLHS.Type) tryResolveLHS(doubleColonExpression, new FirDoubleColonExpressionResolver$resolveDoubleColonLHS$resultForType$1(this), new Function1() { // from class: v45
            public final Object invoke(Object obj) {
                return FirDoubleColonExpressionResolver.a(this.b, (FirExpression) obj);
            }
        })) != null) {
            return (expression == null || !TypeUtilsKt.equalTypes$default(type.getType(), expression.getType(), this.session, false, 4, null)) ? type : expression;
        }
        return expression;
    }
}

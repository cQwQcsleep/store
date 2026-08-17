package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirSmartCastExpressionBuilder;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\u0001H\u0002\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\t\u001a\u00020\u0001\u001a\u001c\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u000e\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u0004H\u0002\u001a\u0012\u0010\u0011\u001a\u00020\u00012\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\u0013"}, d2 = {"isVisible", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "skipCheckForContainingClassVisibility", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "removeSmartCastTypeForAttemptToFitVisibility", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getBackingFieldIfApplicable", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "isExplicitReceiverExpression", "receiverExpression", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VisibilityUtilsKt {
    private static final FirBackingField getBackingFieldIfApplicable(FirMemberDeclaration firMemberDeclaration) {
        if (!(firMemberDeclaration instanceof FirProperty)) {
            return null;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firMemberDeclaration;
        while (!ClassMembersKt.isIntersectionOverride(firCallableDeclaration)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        return DeclarationAttributesKt.getExplicitBackingField((FirProperty) firCallableDeclaration);
    }

    private static final boolean isExplicitReceiverExpression(FirExpression firExpression) {
        if (firExpression == null) {
            return false;
        }
        FirReference referenceUnsafe = ReferenceUtilsKt.toReferenceUnsafe(firExpression);
        FirThisReference firThisReference = referenceUnsafe instanceof FirThisReference ? (FirThisReference) referenceUnsafe : null;
        if (firThisReference == null) {
            return true;
        }
        return !firThisReference.getIsImplicit();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean isVisible(FirVisibilityChecker firVisibilityChecker, FirMemberDeclaration firMemberDeclaration, CallInfo callInfo, FirExpression firExpression, boolean z) {
        FirRegularClass firRegularClass = null;
        if ((firMemberDeclaration instanceof FirCallableDeclaration) && firMemberDeclaration.getStatus().isStatic() && isExplicitReceiverExpression(firExpression)) {
            FirExpression firExpressionUnwrapSmartcastExpression = firExpression != null ? FirExpressionUtilKt.unwrapSmartcastExpression(firExpression) : null;
            FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
            FirClassLikeSymbol<?> symbol = firResolvedQualifier != null ? firResolvedQualifier.getSymbol() : null;
            if (symbol instanceof FirRegularClassSymbol) {
                firRegularClass = (FirRegularClass) ((FirRegularClassSymbol) symbol).getFir();
            } else if (symbol instanceof FirTypeAliasSymbol) {
                FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(symbol, callInfo.getSession());
                if (firRegularClassSymbolFullyExpandedClass != null) {
                    firRegularClass = (FirRegularClass) firRegularClassSymbolFullyExpandedClass.getFir();
                }
            } else if (!(symbol instanceof FirAnonymousObjectSymbol) && symbol != null) {
                bu8.a();
                return false;
            }
        }
        return FirVisibilityChecker.isVisible$default(firVisibilityChecker, firMemberDeclaration, callInfo.getSession(), callInfo.getContainingFile(), callInfo.getContainingDeclarations(), firExpression, callInfo.getCallSite() instanceof FirVariableAssignment, firRegularClass, z, null, 256, null);
    }

    public static /* synthetic */ boolean isVisible$default(FirVisibilityChecker firVisibilityChecker, FirMemberDeclaration firMemberDeclaration, Candidate candidate, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return isVisible(firVisibilityChecker, firMemberDeclaration, candidate, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final FirExpression removeSmartCastTypeForAttemptToFitVisibility(FirExpression firExpression, FirSession firSession) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirSmartCastExpression firSmartCastExpression = firExpression instanceof FirSmartCastExpression ? (FirSmartCastExpression) firExpression : null;
        if (firSmartCastExpression != null) {
            if (!firSmartCastExpression.isStable()) {
                firSmartCastExpression = null;
            }
            if (firSmartCastExpression != null) {
                KotlinTypeMarker resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
                if (ConeBuiltinTypeUtilsKt.isNullableNothing(resolvedType)) {
                    return null;
                }
                FirExpression originalExpression = firSmartCastExpression.getOriginalExpression();
                KotlinTypeMarker resolvedType2 = FirTypeUtilsKt.getResolvedType(originalExpression);
                ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default = TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default((ConeKotlinType) resolvedType2, (ConeTypeContext) TypeComponentsKt.getTypeContext(firSession), false, false, 6, (Object) null);
                if (Intrinsics.areEqual(coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default, resolvedType)) {
                    return null;
                }
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
                if (!typeContext.isNullableType(resolvedType2) || typeContext.isNullableType(resolvedType)) {
                    return originalExpression;
                }
                FirSmartCastExpressionBuilder firSmartCastExpressionBuilder = new FirSmartCastExpressionBuilder();
                firSmartCastExpressionBuilder.setOriginalExpression(originalExpression);
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                KtSourceElement source = originalExpression.getSource();
                firResolvedTypeRefBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.SmartCastedTypeRef.INSTANCE, null, 2, null) : null);
                firResolvedTypeRefBuilder.setConeType(coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default);
                firSmartCastExpressionBuilder.setSmartcastType(firResolvedTypeRefBuilder.build());
                firSmartCastExpressionBuilder.setUpperTypesFromSmartCast(CollectionsKt.listOf(coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default));
                firSmartCastExpressionBuilder.setSmartcastStability(firSmartCastExpression.getSmartcastStability());
                firSmartCastExpressionBuilder.setConeTypeOrNull(coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default);
                firSmartCastExpressionBuilder.setLowerTypesFromSmartCast(CollectionsKt.emptyList());
                return firSmartCastExpressionBuilder.mo288build();
            }
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean isVisible(FirVisibilityChecker firVisibilityChecker, FirMemberDeclaration firMemberDeclaration, Candidate candidate, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        firVisibilityChecker.getClass();
        firMemberDeclaration.getClass();
        candidate.getClass();
        CallInfo callInfo = candidate.getCallInfo();
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        if (!(symbol instanceof FirConstructorSymbol) && !(symbol instanceof FirTypeAliasSymbol)) {
            ConeResolutionAtom dispatchReceiver = candidate.getDispatchReceiver();
            FirExpression expression = dispatchReceiver != null ? dispatchReceiver.getExpression() : null;
            if (!isVisible(firVisibilityChecker, firMemberDeclaration, callInfo, expression, z)) {
                FirExpression firExpressionRemoveSmartCastTypeForAttemptToFitVisibility = removeSmartCastTypeForAttemptToFitVisibility(expression, candidate.getCallInfo().getSession());
                if (firExpressionRemoveSmartCastTypeForAttemptToFitVisibility == null || !isVisible(firVisibilityChecker, firMemberDeclaration, callInfo, firExpressionRemoveSmartCastTypeForAttemptToFitVisibility, z)) {
                    return false;
                }
                if (!isVisible(firVisibilityChecker, firMemberDeclaration, callInfo, expression, true)) {
                    candidate.setDispatchReceiver(ConeResolutionAtom.INSTANCE.createRawAtom(firExpressionRemoveSmartCastTypeForAttemptToFitVisibility));
                }
            }
            FirBackingField backingFieldIfApplicable = getBackingFieldIfApplicable(firMemberDeclaration);
            if (backingFieldIfApplicable != null) {
                candidate.setHasVisibleBackingField(isVisible(firVisibilityChecker, backingFieldIfApplicable, callInfo, expression, z));
            }
            return true;
        }
        return FirVisibilityChecker.isVisible$default(firVisibilityChecker, firMemberDeclaration, callInfo.getSession(), callInfo.getContainingFile(), callInfo.getContainingDeclarations(), null, false, null, z, null, 352, null);
    }
}

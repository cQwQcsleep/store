package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitContextParameterValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue;
import org.jetbrains.kotlin.fir.resolve.calls.ReceiverShadowedByContextParameter;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JE\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0010\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011J)\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckReceiverShadowedByContextParameter;", Argument.Delimiters.none, "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "closerOrOnTheSameLevelImplicitValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;", "kind", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitKind;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitKind;)V", "starProjectedReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitKind;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CheckReceiverShadowedByContextParameter {
    public static final CheckReceiverShadowedByContextParameter INSTANCE = new CheckReceiverShadowedByContextParameter();

    private CheckReceiverShadowedByContextParameter() {
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0088  */
    public final void check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, List<? extends ImplicitValue<?>> list, ImplicitKind implicitKind) {
        ConeKotlinType coneKotlinTypeStarProjectedReceiverType;
        boolean z;
        checkerSink.getClass();
        resolutionContext.getClass();
        candidate.getClass();
        list.getClass();
        implicitKind.getClass();
        if (implicitKind == ImplicitKind.ContextArgument) {
            w01.a("Should not be called with kind == ContextArgument");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof ImplicitContextParameterValue) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty() || (coneKotlinTypeStarProjectedReceiverType = starProjectedReceiverType(resolutionContext, candidate, implicitKind)) == null) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (ConstraintSystemBuilderKt.isSubtypeConstraintCompatible(candidate.getSystem(), ((ImplicitContextParameterValue) obj2).getType(), coneKotlinTypeStarProjectedReceiverType)) {
                arrayList2.add(obj2);
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        if (implicitKind == ImplicitKind.DispatchReceiver) {
            FirBasedSymbol<?> symbol = candidate.getSymbol();
            FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
            if ((firCallableSymbol != null ? firCallableSymbol.getReceiverParameterSymbol() : null) != null) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        FirBasedSymbol<?> symbol2 = candidate.getSymbol();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((ImplicitContextParameterValue) it.next()).getBoundSymbol());
        }
        checkerSink.reportDiagnostic(new ReceiverShadowedByContextParameter(symbol2, z, arrayList3));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ConeKotlinType starProjectedReceiverType(ResolutionContext resolutionContext, Candidate candidate, ImplicitKind implicitKind) {
        ConeRigidType coneRigidTypeUpperBoundIfFlexible;
        ConeRigidType coneRigidTypeFullyExpandedType;
        FirClassSymbol<?> classSymbol;
        ConeClassLikeType coneClassLikeTypeConstructStarProjectedType$default;
        resolutionContext.getClass();
        candidate.getClass();
        implicitKind.getClass();
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol != null) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
            while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration.getSymbol();
            if (symbol2 != null) {
                ConeKotlinType dispatchReceiverType = implicitKind == ImplicitKind.DispatchReceiver ? symbol2.getDispatchReceiverType() : symbol2.getResolvedReceiverType();
                return (dispatchReceiverType == null || (coneRigidTypeUpperBoundIfFlexible = ConeTypeUtilsKt.upperBoundIfFlexible(dispatchReceiverType)) == null || (coneRigidTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) resolutionContext, coneRigidTypeUpperBoundIfFlexible)) == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(resolutionContext, coneRigidTypeFullyExpandedType)) == null || (coneClassLikeTypeConstructStarProjectedType$default = TypeConstructionUtilsKt.constructStarProjectedType$default(classSymbol, 0, false, 3, null)) == null) ? dispatchReceiverType : coneClassLikeTypeConstructStarProjectedType$default;
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
        }
        return null;
    }
}

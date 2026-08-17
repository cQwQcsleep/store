package org.jetbrains.kotlin.fir.analysis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0006\u001a\u00020\u00072\u0010\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000bH\u0016R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u0011H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0012J\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\fH\u0014¢\u0006\u0002\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bH\u0017b\u0002\b\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/FirOverridesBackwardCompatibilityHelper;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "platformDependentAnnotation", "Lorg/jetbrains/kotlin/name/ClassId;", "overrideCanBeOmitted", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "overriddenMemberSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;)Z", "isPlatformSpecificSymbolThatCanBeImplicitlyOverridden", "symbol", "visitedSymbols", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/Set;)Z", "additionalCheck", "member", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Ljava/lang/Boolean;", "createComposed", "Lorg/jetbrains/kotlin/fir/analysis/FirOverridesBackwardCompatibilityHelper$Composed;", "components", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Composed", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirOverridesBackwardCompatibilityHelper implements FirComposableSessionComponent<FirOverridesBackwardCompatibilityHelper> {
    private final ClassId platformDependentAnnotation = ClassId.Companion.fromString$default(ClassId.Companion, "kotlin/internal/PlatformDependent", false, 2, (Object) null);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J+\u0010\t\u001a\u00020\n2\u0010\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\u0004H\u0016R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000fJ\u001b\u0010\u0010\u001a\u0004\u0018\u00010\n2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0014¢\u0006\u0002\u0010\u0012R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/FirOverridesBackwardCompatibilityHelper$Composed;", "Lorg/jetbrains/kotlin/fir/analysis/FirOverridesBackwardCompatibilityHelper;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "overrideCanBeOmitted", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "overriddenMemberSymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;)Z", "additionalCheck", "member", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirOverridesBackwardCompatibilityHelper implements FirComposableSessionComponent.Composed<FirOverridesBackwardCompatibilityHelper> {
        private final List<FirOverridesBackwardCompatibilityHelper> components;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirOverridesBackwardCompatibilityHelper> list) {
            list.getClass();
            this.components = list;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.FirOverridesBackwardCompatibilityHelper
        public Boolean additionalCheck(FirCallableSymbol<?> member) {
            member.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                Boolean boolAdditionalCheck = ((FirOverridesBackwardCompatibilityHelper) it.next()).additionalCheck(member);
                if (boolAdditionalCheck != null) {
                    return boolAdditionalCheck;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirOverridesBackwardCompatibilityHelper> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.FirOverridesBackwardCompatibilityHelper
        public boolean overrideCanBeOmitted(CheckerContext checkerContext, List<? extends FirCallableSymbol<?>> list) {
            checkerContext.getClass();
            list.getClass();
            List<FirOverridesBackwardCompatibilityHelper> components = getComponents();
            if ((components instanceof Collection) && components.isEmpty()) {
                return true;
            }
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                if (!((FirOverridesBackwardCompatibilityHelper) it.next()).overrideCanBeOmitted(checkerContext, list)) {
                    return false;
                }
            }
            return true;
        }
    }

    private final boolean isPlatformSpecificSymbolThatCanBeImplicitlyOverridden(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, Set<FirCallableSymbol<?>> set) {
        if (firCallableSymbol.getResolvedStatus().getModality() == Modality.FINAL) {
            return false;
        }
        if (!set.add(firCallableSymbol)) {
            return true;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
        while (true) {
            if (!ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                break;
            }
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
            return false;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(symbol, FirResolvePhase.BODY_RESOLVE);
        if (FirAnnotationUtilsKt.hasAnnotation(symbol, this.platformDependentAnnotation, checkerContext.getSession())) {
            return true;
        }
        Boolean boolAdditionalCheck = additionalCheck(symbol);
        if (boolAdditionalCheck != null) {
            return boolAdditionalCheck.booleanValue();
        }
        if (symbol.getResolvedStatus().getModality() != Modality.ABSTRACT) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(symbol);
            FirRegularClassSymbol regularClassSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag) : null;
            if (regularClassSymbol != null && regularClassSymbol.getClassKind() != ClassKind.INTERFACE) {
                return false;
            }
        }
        List<FirCallableSymbol<?>> listDirectOverriddenSymbolsSafe = FirHelpersKt.directOverriddenSymbolsSafe(checkerContext, symbol);
        if (listDirectOverriddenSymbolsSafe.isEmpty()) {
            return false;
        }
        List<FirCallableSymbol<?>> list = listDirectOverriddenSymbolsSafe;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!isPlatformSpecificSymbolThatCanBeImplicitlyOverridden(checkerContext, (FirCallableSymbol) it.next(), set)) {
                return false;
            }
        }
        return true;
    }

    public Boolean additionalCheck(FirCallableSymbol<?> member) {
        member.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirOverridesBackwardCompatibilityHelper> components) {
        components.getClass();
        return new Composed(components);
    }

    public boolean overrideCanBeOmitted(CheckerContext checkerContext, List<? extends FirCallableSymbol<?>> list) {
        checkerContext.getClass();
        list.getClass();
        HashSet hashSet = new HashSet();
        List<? extends FirCallableSymbol<?>> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return true;
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            if (!isPlatformSpecificSymbolThatCanBeImplicitlyOverridden(checkerContext, (FirCallableSymbol) it.next(), hashSet)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirOverridesBackwardCompatibilityHelper>) list);
    }
}

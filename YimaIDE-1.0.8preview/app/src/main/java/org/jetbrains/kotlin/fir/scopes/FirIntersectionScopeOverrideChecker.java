package org.jetbrains.kotlin.fir.scopes;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0010H\u0016J$\u0010\u0011\u001a\u00020\u00122\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirIntersectionScopeOverrideChecker;", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "standardOverrideChecker", "platformSpecificOverridabilityRules", "Lorg/jetbrains/kotlin/fir/scopes/PlatformSpecificOverridabilityRules;", "isOverriddenFunction", Argument.Delimiters.none, "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "baseDeclaration", "isOverriddenProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "chooseIntersectionVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "overrides", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIntersectionScopeOverrideChecker implements FirOverrideChecker {
    private final PlatformSpecificOverridabilityRules platformSpecificOverridabilityRules;
    private final FirOverrideChecker standardOverrideChecker;

    public FirIntersectionScopeOverrideChecker(FirSession firSession) {
        firSession.getClass();
        this.standardOverrideChecker = FirOverrideCheckerKt.getFirOverrideChecker(firSession);
        this.platformSpecificOverridabilityRules = PlatformSpecificOverridabilityRulesKt.getPlatformSpecificOverridabilityRules(firSession);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public Visibility chooseIntersectionVisibility(Collection<? extends FirCallableSymbol<?>> overrides, FirRegularClassSymbol dispatchClassSymbol) {
        Visibility visibilityChooseIntersectionVisibility;
        overrides.getClass();
        PlatformSpecificOverridabilityRules platformSpecificOverridabilityRules = this.platformSpecificOverridabilityRules;
        return (platformSpecificOverridabilityRules == null || (visibilityChooseIntersectionVisibility = platformSpecificOverridabilityRules.chooseIntersectionVisibility(overrides, dispatchClassSymbol)) == null) ? this.standardOverrideChecker.chooseIntersectionVisibility(overrides, dispatchClassSymbol) : visibilityChooseIntersectionVisibility;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public boolean isOverriddenFunction(FirNamedFunction overrideCandidate, FirNamedFunction baseDeclaration) {
        Boolean boolIsOverriddenFunction;
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        PlatformSpecificOverridabilityRules platformSpecificOverridabilityRules = this.platformSpecificOverridabilityRules;
        return (platformSpecificOverridabilityRules == null || (boolIsOverriddenFunction = platformSpecificOverridabilityRules.isOverriddenFunction(overrideCandidate, baseDeclaration)) == null) ? this.standardOverrideChecker.isOverriddenFunction(overrideCandidate, baseDeclaration) : boolIsOverriddenFunction.booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public boolean isOverriddenProperty(FirCallableDeclaration overrideCandidate, FirProperty baseDeclaration) {
        Boolean boolIsOverriddenProperty;
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        PlatformSpecificOverridabilityRules platformSpecificOverridabilityRules = this.platformSpecificOverridabilityRules;
        return (platformSpecificOverridabilityRules == null || (boolIsOverriddenProperty = platformSpecificOverridabilityRules.isOverriddenProperty(overrideCandidate, baseDeclaration)) == null) ? this.standardOverrideChecker.isOverriddenProperty(overrideCandidate, baseDeclaration) : boolIsOverriddenProperty.booleanValue();
    }
}

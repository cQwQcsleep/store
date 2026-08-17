package org.jetbrains.kotlin.fir.java.scopes;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirOverrideCheckerKt;
import org.jetbrains.kotlin.fir.scopes.PlatformSpecificOverridabilityRules;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000fJ\u001f\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0011H\u0002J\f\u0010\u0015\u001a\u00020\u000b*\u00020\u0011H\u0002J$\u0010\u0016\u001a\u00020\u00172\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/JavaOverridabilityRules;", "Lorg/jetbrains/kotlin/fir/scopes/PlatformSpecificOverridabilityRules;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "javaOverrideChecker", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaOverrideChecker;", "standardOverrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "isOverriddenFunction", Argument.Delimiters.none, "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "baseDeclaration", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Ljava/lang/Boolean;", "isOverriddenProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", "shouldApplyJavaChecker", "isOriginallyFromJava", "chooseIntersectionVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "overrides", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaOverridabilityRules extends PlatformSpecificOverridabilityRules {
    private final JavaOverrideChecker javaOverrideChecker;
    private final FirSession session;
    private final FirOverrideChecker standardOverrideChecker;

    public JavaOverridabilityRules(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.javaOverrideChecker = new JavaOverrideChecker(firSession, null, null, false);
        this.standardOverrideChecker = FirOverrideCheckerKt.getFirOverrideChecker(firSession);
    }

    private final boolean isOriginallyFromJava(FirCallableDeclaration firCallableDeclaration) {
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                return Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.Enhancement.INSTANCE);
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
    }

    private final boolean shouldApplyJavaChecker(FirCallableDeclaration overrideCandidate, FirCallableDeclaration baseDeclaration) {
        return isOriginallyFromJava(overrideCandidate) || isOriginallyFromJava(baseDeclaration);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.PlatformSpecificOverridabilityRules
    public Visibility chooseIntersectionVisibility(Collection<? extends FirCallableSymbol<?>> overrides, FirRegularClassSymbol dispatchClassSymbol) {
        overrides.getClass();
        return this.javaOverrideChecker.chooseIntersectionVisibility(overrides, dispatchClassSymbol);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.PlatformSpecificOverridabilityRules
    public Boolean isOverriddenFunction(FirNamedFunction overrideCandidate, FirNamedFunction baseDeclaration) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        if (!shouldApplyJavaChecker(overrideCandidate, baseDeclaration)) {
            return null;
        }
        boolean z = false;
        if (this.javaOverrideChecker.isOverriddenFunction(overrideCandidate, baseDeclaration) && this.standardOverrideChecker.isOverriddenFunction(overrideCandidate, baseDeclaration)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.PlatformSpecificOverridabilityRules
    public Boolean isOverriddenProperty(FirCallableDeclaration overrideCandidate, FirProperty baseDeclaration) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        if (shouldApplyJavaChecker(overrideCandidate, baseDeclaration)) {
            return Boolean.valueOf(this.javaOverrideChecker.isOverriddenProperty(overrideCandidate, baseDeclaration));
        }
        return null;
    }
}

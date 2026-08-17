package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nH$¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractOverrideChecker;", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "<init>", "()V", "buildTypeParametersSubstitutorIfCompatible", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "baseDeclaration", "checkReifiednessIsSame", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractOverrideChecker implements FirOverrideChecker {
    public static /* synthetic */ ConeSubstitutor buildTypeParametersSubstitutorIfCompatible$default(FirAbstractOverrideChecker firAbstractOverrideChecker, FirCallableDeclaration firCallableDeclaration, FirCallableDeclaration firCallableDeclaration2, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: buildTypeParametersSubstitutorIfCompatible");
            return null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return firAbstractOverrideChecker.buildTypeParametersSubstitutorIfCompatible(firCallableDeclaration, firCallableDeclaration2, z);
    }

    public abstract ConeSubstitutor buildTypeParametersSubstitutorIfCompatible(FirCallableDeclaration overrideCandidate, FirCallableDeclaration baseDeclaration, boolean checkReifiednessIsSame);
}

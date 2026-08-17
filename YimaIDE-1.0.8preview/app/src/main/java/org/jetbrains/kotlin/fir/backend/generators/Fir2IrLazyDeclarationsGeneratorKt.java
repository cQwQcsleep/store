package org.jetbrains.kotlin.fir.backend.generators;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000¨\u0006\u0005"}, d2 = {"isFakeOverride", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "firContainingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyDeclarationsGeneratorKt {
    public static final boolean isFakeOverride(FirCallableDeclaration firCallableDeclaration, FirRegularClass firRegularClass) {
        ConeClassLikeLookupTag lookupTag;
        FirRegularClassSymbol symbol;
        firCallableDeclaration.getClass();
        while (true) {
            lookupTag = null;
            if (!Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
                break;
            }
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
            return true;
        }
        if (firRegularClass != null && (symbol = firRegularClass.getSymbol()) != null) {
            lookupTag = symbol.getLookupTag();
        }
        return !Intrinsics.areEqual(lookupTag, ClassMembersKt.containingClassLookupTag(firCallableDeclaration));
    }
}

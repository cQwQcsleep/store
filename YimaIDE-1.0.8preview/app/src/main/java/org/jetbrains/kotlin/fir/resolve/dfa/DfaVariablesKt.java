package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0007"}, d2 = {"isFinal", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isInCurrentOrFriendModule", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DfaVariablesKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isFinal(ConeKotlinType coneKotlinType, FirSession firSession) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        if (coneKotlinType instanceof ConeFlexibleType) {
            return isFinal(((ConeFlexibleType) coneKotlinType).getLowerBound(), firSession);
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return isFinal(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), firSession);
        }
        if (coneKotlinType instanceof ConeClassLikeType) {
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((ConeClassLikeType) coneKotlinType, firSession);
            return (symbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(symbol, firSession)) == null || firRegularClassSymbolFullyExpandedClass.getResolvedStatus().getModality() != Modality.FINAL) ? false : true;
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
                return false;
            }
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                if (isFinal((ConeKotlinType) it.next(), firSession)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isInCurrentOrFriendModule(FirVariable firVariable, FirSession firSession) {
        FirCallableDeclaration firCallableDeclaration = firVariable;
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
        return FirModuleDataKt.canSeeInternalsOf(FirModuleDataKt.getModuleData(firSession), ((FirVariable) firCallableDeclaration).getModuleData());
    }
}

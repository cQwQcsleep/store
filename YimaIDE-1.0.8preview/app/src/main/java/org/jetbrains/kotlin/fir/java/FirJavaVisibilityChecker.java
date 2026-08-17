package org.jetbrains.kotlin.fir.java;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.descriptors.java.JavaVisibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.SupertypeSupplier;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.FirSimpleSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JT\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\"\u0010\u0016\u001a\u00020\u0005*\u00020\u00172\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J$\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u001c\u001a\u00020\u0007H\u0014J\u0018\u0010\u001d\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u001e\u001a\u00020\u001aH\u0002¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirJavaVisibilityChecker;", "Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", "<init>", "()V", "platformVisibilityCheck", Argument.Delimiters.none, "declarationVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isCallToPropertySetter", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "isCalledFromSubclass", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSimpleSyntheticPropertySymbol;", "platformOverrideVisibilityCheck", "packageNameOfDerivedClass", "Lorg/jetbrains/kotlin/name/FqName;", "symbolInBaseClass", "visibilityInBaseClass", "isInPackage", "expected", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaVisibilityChecker extends FirVisibilityChecker {
    public static final FirJavaVisibilityChecker INSTANCE = new FirJavaVisibilityChecker();

    private FirJavaVisibilityChecker() {
    }

    private final boolean isCalledFromSubclass(FirSimpleSyntheticPropertySymbol firSimpleSyntheticPropertySymbol, List<? extends FirDeclaration> list, FirSession firSession) {
        FirSession firSession2;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firSimpleSyntheticPropertySymbol);
        if (coneClassLikeLookupTagContainingClassLookupTag == null) {
            return false;
        }
        List<? extends FirDeclaration> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        for (FirDeclaration firDeclaration : list2) {
            if (firDeclaration instanceof FirClass) {
                firSession2 = firSession;
                if (SupertypeUtilsKt.isSubclassOf$default((FirClass) firDeclaration, coneClassLikeLookupTagContainingClassLookupTag, firSession2, false, null, false, 24, null)) {
                    return true;
                }
            } else {
                firSession2 = firSession;
            }
            firSession = firSession2;
        }
        return false;
    }

    private final boolean isInPackage(FirBasedSymbol<?> firBasedSymbol, FqName fqName) {
        ClassId classId;
        if (Intrinsics.areEqual(UtilsKt.packageFqName(firBasedSymbol), fqName)) {
            return true;
        }
        if (!(firBasedSymbol.getFir() instanceof FirSyntheticPropertyAccessor)) {
            return false;
        }
        ConeClassLikeLookupTag ownerLookupTag = FirVisibilityCheckerKt.getOwnerLookupTag(firBasedSymbol);
        return Intrinsics.areEqual((ownerLookupTag == null || (classId = ownerLookupTag.getClassId()) == null) ? null : classId.getPackageFqName(), fqName);
    }

    @Override // org.jetbrains.kotlin.fir.FirVisibilityChecker
    public boolean platformOverrideVisibilityCheck(FqName packageNameOfDerivedClass, FirBasedSymbol<?> symbolInBaseClass, Visibility visibilityInBaseClass) {
        packageNameOfDerivedClass.getClass();
        symbolInBaseClass.getClass();
        visibilityInBaseClass.getClass();
        if (Intrinsics.areEqual(visibilityInBaseClass, JavaVisibilities.ProtectedAndPackage.INSTANCE) || Intrinsics.areEqual(visibilityInBaseClass, JavaVisibilities.ProtectedStaticVisibility.INSTANCE) || !Intrinsics.areEqual(visibilityInBaseClass, JavaVisibilities.PackageVisibility.INSTANCE)) {
            return true;
        }
        return isInPackage(symbolInBaseClass, packageNameOfDerivedClass);
    }

    @Override // org.jetbrains.kotlin.fir.FirVisibilityChecker
    public boolean platformVisibilityCheck(Visibility declarationVisibility, FirBasedSymbol<?> symbol, FirFile useSiteFile, List<? extends FirDeclaration> containingDeclarations, FirExpression dispatchReceiver, FirSession session, boolean isCallToPropertySetter, SupertypeSupplier supertypeSupplier) {
        declarationVisibility.getClass();
        symbol.getClass();
        useSiteFile.getClass();
        containingDeclarations.getClass();
        session.getClass();
        supertypeSupplier.getClass();
        if (!Intrinsics.areEqual(declarationVisibility, JavaVisibilities.ProtectedAndPackage.INSTANCE) && !Intrinsics.areEqual(declarationVisibility, JavaVisibilities.ProtectedStaticVisibility.INSTANCE)) {
            if (Intrinsics.areEqual(declarationVisibility, JavaVisibilities.PackageVisibility.INSTANCE)) {
                return isInPackage(symbol, UtilsKt.getPackageFqName(useSiteFile));
            }
            return true;
        }
        if (Intrinsics.areEqual(UtilsKt.packageFqName(symbol), UtilsKt.getPackageFqName(useSiteFile))) {
            return true;
        }
        ConeClassLikeLookupTag ownerLookupTag = FirVisibilityCheckerKt.getOwnerLookupTag(symbol);
        if (ownerLookupTag == null) {
            return false;
        }
        if (canSeeProtectedMemberOf(symbol, containingDeclarations, ((symbol instanceof FirCallableSymbol) && ((FirCallableSymbol) symbol).getRawStatus().isStatic()) ? null : dispatchReceiver, ownerLookupTag, session, FirVisibilityCheckerKt.isVariableOrNamedFunction(symbol), symbol.getFir() instanceof FirSyntheticPropertyAccessor, supertypeSupplier)) {
            return true;
        }
        if (isCallToPropertySetter && (symbol instanceof FirSimpleSyntheticPropertySymbol)) {
            FirSimpleSyntheticPropertySymbol firSimpleSyntheticPropertySymbol = (FirSimpleSyntheticPropertySymbol) symbol;
            FirSyntheticPropertyAccessorSymbol setterSymbol = firSimpleSyntheticPropertySymbol.getSetterSymbol();
            if (Intrinsics.areEqual(setterSymbol != null ? setterSymbol.getResolvedStatus().getVisibility() : null, Visibilities.Public.INSTANCE) && isCalledFromSubclass(firSimpleSyntheticPropertySymbol, containingDeclarations, session)) {
                return true;
            }
        }
        return false;
    }
}

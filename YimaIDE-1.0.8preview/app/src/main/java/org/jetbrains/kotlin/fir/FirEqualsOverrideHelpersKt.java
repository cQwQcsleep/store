package org.jetbrains.kotlin.fir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001aB\u0010\u0000\u001a\u00020\u00012\u0010\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0010\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e2\u0006\u0010\b\u001a\u00020\t\u001a:\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0010\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a(\u0010\u0010\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0010\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\"\u0018\u0010\u0014\u001a\u00020\t*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016¨\u0006\u0017"}, d2 = {"computeEqualsOverrideContract", "Lorg/jetbrains/kotlin/fir/EqualsOverrideContract;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "trustExpectClasses", Argument.Delimiters.none, "symbolsForType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "visitedSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "getDeclaredEqualsOverrideContract", "isSmartcastPrimitive", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "isGenerated", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)Z", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEqualsOverrideHelpersKt {
    public static final EqualsOverrideContract computeEqualsOverrideContract(List<? extends FirClassSymbol<?>> list, FirSession firSession, ScopeSession scopeSession, Set<FirClassifierSymbol<?>> set, boolean z) {
        EqualsOverrideContract equalsOverrideContractComputeEqualsOverrideContract;
        list.getClass();
        firSession.getClass();
        scopeSession.getClass();
        set.getClass();
        Iterator<T> it = list.iterator();
        EqualsOverrideContract equalsOverrideContract = null;
        if (it.hasNext()) {
            equalsOverrideContractComputeEqualsOverrideContract = computeEqualsOverrideContract((FirClassSymbol<?>) it.next(), firSession, scopeSession, set, z);
            while (it.hasNext()) {
                EqualsOverrideContract equalsOverrideContractComputeEqualsOverrideContract2 = computeEqualsOverrideContract((FirClassSymbol<?>) it.next(), firSession, scopeSession, set, z);
                if (equalsOverrideContractComputeEqualsOverrideContract.compareTo(equalsOverrideContractComputeEqualsOverrideContract2) < 0) {
                    equalsOverrideContractComputeEqualsOverrideContract = equalsOverrideContractComputeEqualsOverrideContract2;
                }
            }
        } else {
            equalsOverrideContractComputeEqualsOverrideContract = null;
        }
        if (equalsOverrideContractComputeEqualsOverrideContract == null) {
            equalsOverrideContractComputeEqualsOverrideContract = EqualsOverrideContract.UNKNOWN;
        }
        EqualsOverrideContract equalsOverrideContract2 = equalsOverrideContractComputeEqualsOverrideContract;
        EqualsOverrideContract equalsOverrideContract3 = EqualsOverrideContract.UNKNOWN;
        if (equalsOverrideContract2 == equalsOverrideContract3) {
            return equalsOverrideContract3;
        }
        List listLookupSuperTypes$default = SupertypeUtilsKt.lookupSuperTypes$default(list, false, true, firSession, false, null, set, 32, null);
        ArrayList arrayList = new ArrayList();
        Iterator it2 = listLookupSuperTypes$default.iterator();
        while (it2.hasNext()) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) it2.next(), firSession, (Function1) null, 2, (Object) null), firSession);
            if (regularClassSymbol != null) {
                arrayList.add(regularClassSymbol);
            }
        }
        Iterator it3 = arrayList.iterator();
        if (it3.hasNext()) {
            EqualsOverrideContract declaredEqualsOverrideContract = getDeclaredEqualsOverrideContract((FirRegularClassSymbol) it3.next(), firSession, scopeSession, z);
            loop1: while (true) {
                equalsOverrideContract = declaredEqualsOverrideContract;
                do {
                    if (!it3.hasNext()) {
                        break loop1;
                    }
                    declaredEqualsOverrideContract = getDeclaredEqualsOverrideContract((FirRegularClassSymbol) it3.next(), firSession, scopeSession, z);
                } while (equalsOverrideContract.compareTo(declaredEqualsOverrideContract) <= 0);
            }
        }
        if (equalsOverrideContract == null) {
            equalsOverrideContract = EqualsOverrideContract.SAFE_FOR_SMART_CAST;
        }
        return (EqualsOverrideContract) ComparisonsKt.minOf(equalsOverrideContract2, equalsOverrideContract);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final EqualsOverrideContract computeEqualsOverrideContract$computeInheritorsContract(FirClassSymbol<?> firClassSymbol, FirSession firSession, ScopeSession scopeSession, Set<FirClassifierSymbol<?>> set, boolean z) {
        if (!(firClassSymbol instanceof FirRegularClassSymbol)) {
            return EqualsOverrideContract.UNKNOWN;
        }
        List<ClassId> sealedClassInheritors = SealedClassInheritorsKt.getSealedClassInheritors((FirRegularClass) ((FirRegularClassSymbol) firClassSymbol).getFir(), firSession);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(sealedClassInheritors, 10));
        Iterator<T> it = sealedClassInheritors.iterator();
        while (true) {
            DeclarationSymbolMarker declarationSymbolMarker = null;
            if (!it.hasNext()) {
                Iterator it2 = arrayList.iterator();
                if (it2.hasNext()) {
                    DeclarationSymbolMarker declarationSymbolMarkerComputeEqualsOverrideContract = computeEqualsOverrideContract((List<? extends FirClassSymbol<?>>) CollectionsKt.listOf((FirClassSymbol) it2.next()), firSession, scopeSession, set, z);
                    loop1: while (true) {
                        declarationSymbolMarker = declarationSymbolMarkerComputeEqualsOverrideContract;
                        do {
                            if (!it2.hasNext()) {
                                break loop1;
                            }
                            declarationSymbolMarkerComputeEqualsOverrideContract = computeEqualsOverrideContract((List<? extends FirClassSymbol<?>>) CollectionsKt.listOf((FirClassSymbol) it2.next()), firSession, scopeSession, set, z);
                        } while (declarationSymbolMarker.compareTo(declarationSymbolMarkerComputeEqualsOverrideContract) <= 0);
                    }
                }
                return declarationSymbolMarker == null ? EqualsOverrideContract.SAFE_FOR_SMART_CAST : declarationSymbolMarker;
            }
            DeclarationSymbolMarker symbol = ToSymbolUtilsKt.toSymbol((ClassId) it.next(), firSession);
            declarationSymbolMarker = symbol instanceof FirClassSymbol ? (FirClassSymbol) symbol : null;
            if (declarationSymbolMarker == null) {
                return EqualsOverrideContract.UNKNOWN;
            }
            arrayList.add(declarationSymbolMarker);
        }
    }

    private static final EqualsOverrideContract getDeclaredEqualsOverrideContract(FirClassSymbol<?> firClassSymbol, FirSession firSession, ScopeSession scopeSession, boolean z) {
        Object next;
        if (firClassSymbol.getResolvedStatus().isExpect() && !z) {
            return EqualsOverrideContract.UNKNOWN;
        }
        if (isSmartcastPrimitive(firClassSymbol.getClassId())) {
            return EqualsOverrideContract.SAFE_FOR_SMART_CAST;
        }
        ClassId classId = firClassSymbol.getClassId();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId, standardClassIds.getAny())) {
            return EqualsOverrideContract.SAFE_FOR_SMART_CAST;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getFloat()) || Intrinsics.areEqual(classId, standardClassIds.getDouble())) {
            return EqualsOverrideContract.UNKNOWN;
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getEnum())) {
            return EqualsOverrideContract.SAFE_FOR_SMART_CAST;
        }
        ConeClassLikeLookupTag lookupTag = firClassSymbol.getLookupTag();
        boolean z2 = false;
        Iterator<T> it = FirScopeKt.getFunctions(FirKotlinScopeProviderKt.unsubstitutedScope(firClassSymbol, firSession, scopeSession, false, FirResolvePhase.STATUS), OperatorNameConventions.EQUALS).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) next;
            if (!ClassMembersKt.isSubstitutionOrIntersectionOverride(firNamedFunctionSymbol) && DeclarationUtilsKt.isEquals(firNamedFunctionSymbol, firSession) && CallableIdUtilsKt.isRealOwnerOf(lookupTag, firNamedFunctionSymbol)) {
                break;
            }
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) next;
        if ((firClassSymbol.getRawStatus().isData() || firClassSymbol.getRawStatus().isInline() || firClassSymbol.getRawStatus().isValue() || firClassSymbol.getClassKind() == ClassKind.OBJECT) && !Intrinsics.areEqual(firClassSymbol.getModuleData(), FirModuleDataKt.getModuleData(firSession))) {
            z2 = true;
        }
        if (firNamedFunctionSymbol2 == null) {
            return EqualsOverrideContract.SAFE_FOR_SMART_CAST;
        }
        return (isGenerated(firNamedFunctionSymbol2) || z2) ? EqualsOverrideContract.TRUSTED_FOR_EXHAUSTIVENESS : EqualsOverrideContract.UNKNOWN;
    }

    private static final boolean isGenerated(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        return firNamedFunctionSymbol.getOrigin().getGeneratedAnyMethod();
    }

    public static final boolean isSmartcastPrimitive(ClassId classId) {
        return Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getString());
    }

    public static final EqualsOverrideContract computeEqualsOverrideContract(ConeKotlinType coneKotlinType, FirSession firSession, ScopeSession scopeSession, boolean z) {
        coneKotlinType.getClass();
        firSession.getClass();
        scopeSession.getClass();
        return computeEqualsOverrideContract(SupertypeUtilsKt.collectSymbolsForType(coneKotlinType, firSession), firSession, scopeSession, new LinkedHashSet(), z);
    }

    private static final EqualsOverrideContract computeEqualsOverrideContract(FirClassSymbol<?> firClassSymbol, FirSession firSession, ScopeSession scopeSession, Set<FirClassifierSymbol<?>> set, boolean z) {
        if (firClassSymbol.getResolvedStatus().getModality() == Modality.FINAL) {
            return getDeclaredEqualsOverrideContract(firClassSymbol, firSession, scopeSession, z);
        }
        if (firClassSymbol.getResolvedStatus().getModality() == Modality.SEALED) {
            return (EqualsOverrideContract) ComparisonsKt.minOf(EqualsOverrideContract.TRUSTED_FOR_EXHAUSTIVENESS, getDeclaredEqualsOverrideContract(firClassSymbol, firSession, scopeSession, z), computeEqualsOverrideContract$computeInheritorsContract(firClassSymbol, firSession, scopeSession, set, z));
        }
        return EqualsOverrideContract.UNKNOWN;
    }
}

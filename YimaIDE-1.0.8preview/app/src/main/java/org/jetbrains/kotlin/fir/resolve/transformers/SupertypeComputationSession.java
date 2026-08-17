package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeSupplier;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.LocalClassesNavigationInfo;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0011J0\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0006\u0010\u001b\u001a\u00020\u00062\u0016\u0010\u001c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t0\u001dJ0\u0010\u001e\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0006\u0010\u001f\u001a\u00020\f2\u0016\u0010\u001c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t0\u001dJ0\u0010 \u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0006\u0010\u001f\u001a\u00020\f2\u0016\u0010\u001c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t0\u001dJ0\u0010!\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0006\u0010\u001f\u001a\u00020\f2\u0016\u0010\u001c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t0\u001dJ\u000e\u0010\"\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u0011J\u001c\u0010$\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&J\u001e\u0010*\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u00112\f\u0010+\u001a\b\u0012\u0004\u0012\u00020'0&H\u0014J\u001e\u0010,\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.H\u0014J\u001e\u0010+\u001a\b\u0012\u0004\u0012\u00020/0&2\u0006\u00100\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.H\u0016JV\u00101\u001a\u00020#2\u0006\u00100\u001a\u00020\u00112\u0006\u00102\u001a\u00020.2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u0011042\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u0011042\u0016\u00106\u001a\u0012\u0012\u0004\u0012\u00020\u001107j\b\u0012\u0004\u0012\u00020\u0011`82\b\u00109\u001a\u0004\u0018\u00010:H\u0004J\u0018\u0010;\u001a\u00020#2\u0006\u00102\u001a\u00020.2\b\u00109\u001a\u0004\u0018\u00010:J\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010\u0019\u001a\u00020\u0011J\u000e\u0010=\u001a\u00020/2\u0006\u0010>\u001a\u00020?J\u0016\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u00020/2\u0006\u00102\u001a\u00020.J\u0012\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010>\u001a\u00020?H\u0002R>\u0010\u0004\u001a2\u0012\u0004\u0012\u00020\u0006\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t0\u0005j\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u000b\u001a2\u0012\u0004\u0012\u00020\f\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t0\u0005j\u0018\u0012\u0004\u0012\u00020\f\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\r\u001a2\u0012\u0004\u0012\u00020\f\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t0\u0005j\u0018\u0012\u0004\u0012\u00020\f\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u000e\u001a2\u0012\u0004\u0012\u00020\f\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t0\u0005j\u0018\u0012\u0004\u0012\u00020\f\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00110)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationSession;", Argument.Delimiters.none, "<init>", "()V", "fileScopesMap", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ScopePersistentList;", "Lkotlin/collections/HashMap;", "scopesForNestedClassesMap", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "scopesForStaticNestedClassesMap", "scopesForCompanionMap", "supertypeStatusMap", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationStatus;", "Lkotlin/collections/LinkedHashMap;", "supertypesSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "getSupertypesSupplier", "()Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "getSupertypesComputationStatus", "classLikeDeclaration", "getOrPutFileScope", "file", "scope", "Lkotlin/Function0;", "getOrPutScopeForNestedClasses", "klass", "getOrPutScopeForStaticNestedClasses", "getOrPutScopeForCompanion", "startComputingSupertypes", Argument.Delimiters.none, "storeSupertypes", "resolvedTypesRefs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "newClassifiersForBreakingLoops", Argument.Delimiters.none, "reportLoopErrorRefs", "supertypeRefs", "getResolvedSuperTypeRefsForOutOfSessionDeclaration", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "declaration", "breakLoopFor", "session", "visited", Argument.Delimiters.none, "looped", "pathOrderedSet", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "localClassesNavigationInfo", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;", "breakLoops", "getResolvedSupertypeRefs", "getResolvedExpandedTypeRef", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "expandTypealiasInPlace", "typeRef", "getResolvedExpandedType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class SupertypeComputationSession {
    private final HashMap<FirFile, PersistentList<FirScope>> fileScopesMap = new HashMap<>();
    private final HashMap<FirClass, PersistentList<FirScope>> scopesForNestedClassesMap = new HashMap<>();
    private final HashMap<FirClass, PersistentList<FirScope>> scopesForStaticNestedClassesMap = new HashMap<>();
    private final HashMap<FirClass, PersistentList<FirScope>> scopesForCompanionMap = new HashMap<>();
    private final LinkedHashMap<FirClassLikeDeclaration, SupertypeComputationStatus> supertypeStatusMap = new LinkedHashMap<>();
    private final SupertypeSupplier supertypesSupplier = new SupertypeSupplier() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.SupertypeComputationSession$supertypesSupplier$1
        @Override // org.jetbrains.kotlin.fir.resolve.SupertypeSupplier
        public ConeClassLikeType expansionForTypeAlias(FirTypeAlias typeAlias, FirSession useSiteSession) {
            typeAlias.getClass();
            useSiteSession.getClass();
            if (typeAlias.getExpandedTypeRef() instanceof FirResolvedTypeRef) {
                return FirDeclarationUtilKt.getExpandedConeType(typeAlias);
            }
            SupertypeComputationStatus supertypesComputationStatus = this.this$0.getSupertypesComputationStatus(typeAlias);
            SupertypeComputationStatus.Computed computed = supertypesComputationStatus instanceof SupertypeComputationStatus.Computed ? (SupertypeComputationStatus.Computed) supertypesComputationStatus : null;
            List<FirResolvedTypeRef> supertypeRefs = computed != null ? computed.getSupertypeRefs() : null;
            if (supertypeRefs == null) {
                supertypeRefs = this.this$0.getResolvedSuperTypeRefsForOutOfSessionDeclaration(typeAlias, useSiteSession);
            }
            FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) CollectionsKt.firstOrNull(supertypeRefs);
            if (firResolvedTypeRef == null) {
                return null;
            }
            ConeKotlinType coneType = firResolvedTypeRef.getConeType();
            return (ConeClassLikeType) (coneType instanceof ConeClassLikeType ? coneType : null);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.SupertypeSupplier
        public List<ConeClassLikeType> forClass(FirClass firClass, FirSession useSiteSession) {
            firClass.getClass();
            useSiteSession.getClass();
            SupertypeComputationStatus supertypesComputationStatus = this.this$0.getSupertypesComputationStatus(firClass);
            SupertypeComputationStatus.Computed computed = supertypesComputationStatus instanceof SupertypeComputationStatus.Computed ? (SupertypeComputationStatus.Computed) supertypesComputationStatus : null;
            List<FirResolvedTypeRef> supertypeRefs = computed != null ? computed.getSupertypeRefs() : null;
            if (supertypeRefs == null) {
                supertypeRefs = this.this$0.getResolvedSuperTypeRefsForOutOfSessionDeclaration(firClass, useSiteSession);
            }
            ArrayList arrayList = new ArrayList();
            for (FirResolvedTypeRef firResolvedTypeRef : supertypeRefs) {
                if (firResolvedTypeRef == null) {
                    firResolvedTypeRef = null;
                }
                ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                if (!(coneType instanceof ConeClassLikeType)) {
                    coneType = null;
                }
                ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
                if (coneClassLikeType != null) {
                    arrayList.add(coneClassLikeType);
                }
            }
            return arrayList;
        }
    };
    private final List<FirClassLikeDeclaration> newClassifiersForBreakingLoops = new ArrayList();

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:46:0x00be  */
    /* JADX WARN: Multi-variable type inference failed */
    private static final void breakLoopFor$checkIsInLoop(SupertypeComputationSession supertypeComputationSession, FirSession firSession, Set<FirClassLikeDeclaration> set, LinkedHashSet<FirClassLikeDeclaration> linkedHashSet, Set<FirClassLikeDeclaration> set2, LocalClassesNavigationInfo localClassesNavigationInfo, FirClassLikeDeclaration firClassLikeDeclaration, boolean z, boolean z2) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<FirResolvedTypeRef> resolvedSuperTypeRefsForOutOfSessionDeclaration;
        Set set3;
        FirResolvedTypeRef firResolvedTypeRef;
        FirResolvedTypeRef firResolvedTypeRefCreateErrorTypeRef;
        FirClassLikeDeclaration firClassLikeDeclaration2;
        FirClassLikeDeclaration firClassLikeDeclaration3;
        FirSession firSession2 = firSession;
        Set<FirClassLikeDeclaration> set4 = set;
        LinkedHashSet<FirClassLikeDeclaration> linkedHashSet2 = linkedHashSet;
        if (firClassLikeDeclaration == null) {
            return;
        }
        if (z && z2) {
            w01.a("This must hold by induction, because otherwise such a loop is allowed");
            return;
        }
        SupertypeComputationStatus supertypeComputationStatus = supertypeComputationSession.supertypeStatusMap.get(firClassLikeDeclaration);
        if (supertypeComputationStatus == null) {
            resolvedSuperTypeRefsForOutOfSessionDeclaration = supertypeComputationSession.getResolvedSuperTypeRefsForOutOfSessionDeclaration(firClassLikeDeclaration, firSession2);
        } else {
            if (!(supertypeComputationStatus instanceof SupertypeComputationStatus.Computed)) {
                dt1.a("Expected computed supertypes in breakLoops for ", firClassLikeDeclaration.getSymbol().getClassId());
                return;
            }
            resolvedSuperTypeRefsForOutOfSessionDeclaration = ((SupertypeComputationStatus.Computed) supertypeComputationStatus).getSupertypeRefs();
        }
        List<FirResolvedTypeRef> list = resolvedSuperTypeRefsForOutOfSessionDeclaration;
        if (set4.contains(firClassLikeDeclaration)) {
            if (linkedHashSet2.contains(firClassLikeDeclaration)) {
                set2.add(firClassLikeDeclaration);
                List listReversed = CollectionsKt.reversed(linkedHashSet2);
                ArrayList arrayList = new ArrayList();
                for (Object obj : listReversed) {
                    if (Intrinsics.areEqual((FirClassLikeDeclaration) obj, firClassLikeDeclaration)) {
                        break;
                    } else {
                        arrayList.add(obj);
                    }
                }
                set2.addAll(arrayList);
                return;
            }
            return;
        }
        if (!linkedHashSet2.add(firClassLikeDeclaration)) {
            w01.a("The considered declaration should be unique");
            return;
        }
        set4.add(firClassLikeDeclaration);
        if (FirDeclarationUtilKt.getClassId(firClassLikeDeclaration).isNestedClass()) {
            if (!firClassLikeDeclaration.getIsLocal()) {
                FirClassLikeSymbol<?> containingClass = FirProviderKt.getFirProvider(firSession2).getContainingClass(firClassLikeDeclaration.getSymbol());
                if (containingClass != null) {
                    firClassLikeDeclaration2 = (FirClassLikeDeclaration) containingClass.getFir();
                } else {
                    firClassLikeDeclaration3 = null;
                }
                if (firClassLikeDeclaration3 != null) {
                    breakLoopFor$checkIsInLoop(supertypeComputationSession, firSession2, set4, linkedHashSet2, set2, localClassesNavigationInfo, firClassLikeDeclaration3, z, z2);
                }
            } else {
                if (localClassesNavigationInfo == null) {
                    k2d.a("Couldn't retrieve the parent of a local class because there's no `LocalClassesNavigationInfo`");
                    return;
                }
                firClassLikeDeclaration2 = localClassesNavigationInfo.getParentForClass().get(firClassLikeDeclaration);
            }
            firClassLikeDeclaration3 = firClassLikeDeclaration2;
            if (firClassLikeDeclaration3 != null) {
                breakLoopFor$checkIsInLoop(supertypeComputationSession, firSession2, set4, linkedHashSet2, set2, localClassesNavigationInfo, firClassLikeDeclaration3, z, z2);
            }
        }
        boolean z3 = firClassLikeDeclaration instanceof FirTypeAlias;
        if (z2 && !z3) {
            linkedHashSet2.remove(firClassLikeDeclaration);
            set4.remove(firClassLikeDeclaration);
            return;
        }
        boolean z4 = z || !z3;
        ArrayList arrayList2 = new ArrayList();
        boolean z5 = false;
        for (FirResolvedTypeRef firResolvedTypeRef2 : list) {
            if (z3) {
                Iterator it = firResolvedTypeRef2.getAnnotations().iterator();
                while (it.hasNext()) {
                    ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType((FirAnnotation) it.next());
                    ConeClassLikeType coneClassLikeType = resolvedType instanceof ConeClassLikeType ? (ConeClassLikeType) resolvedType : null;
                    if (coneClassLikeType != null) {
                        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType.getLookupTag(), firSession2);
                        breakLoopFor$checkIsInLoop(supertypeComputationSession, firSession2, set4, linkedHashSet2, set2, localClassesNavigationInfo, symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null, z, z2);
                        set4 = set;
                        linkedHashSet2 = linkedHashSet;
                    }
                }
            }
            FirClassLikeDeclaration firClassLikeDeclarationFirClassLike = DeclarationUtilsKt.firClassLike(firResolvedTypeRef2, firSession2);
            if (firClassLikeDeclarationFirClassLike == null) {
                firClassLikeDeclarationFirClassLike = DeclarationUtilsKt.firClassLike(firResolvedTypeRef2, firClassLikeDeclaration.getModuleData().getSession());
            }
            boolean z6 = z4;
            breakLoopFor$checkIsInLoop(supertypeComputationSession, firSession2, set, linkedHashSet, set2, localClassesNavigationInfo, firClassLikeDeclarationFirClassLike, z6, z2);
            z4 = z6;
            if (z4) {
                linkedHashSet2 = linkedHashSet;
                set3 = set2;
                firResolvedTypeRef = firResolvedTypeRef2;
            } else {
                firResolvedTypeRef = firResolvedTypeRef2;
                breakLoopFor$checkIsInLoop$checkTypeArgumentsRecursively(firSession, z, true, supertypeComputationSession, set, linkedHashSet, set2, localClassesNavigationInfo, firResolvedTypeRef2.getConeType(), new LinkedHashSet());
                linkedHashSet2 = linkedHashSet;
                set3 = set2;
            }
            if (!set3.contains(firClassLikeDeclaration) || (firResolvedTypeRef instanceof FirImplicitBuiltinTypeRef)) {
                firResolvedTypeRefCreateErrorTypeRef = firResolvedTypeRef;
            } else {
                firResolvedTypeRefCreateErrorTypeRef = FirSupertypesResolutionKt.createErrorTypeRef(firResolvedTypeRef.getSource(), "Loop in supertypes involving " + firClassLikeDeclaration.getSymbol().getClassId(), z3 ? DiagnosticKind.RecursiveTypealiasExpansion : DiagnosticKind.LoopInSupertype);
                z5 = true;
            }
            arrayList2.add(firResolvedTypeRefCreateErrorTypeRef);
            firSession2 = firSession;
            set4 = set;
        }
        if (z5) {
            supertypeComputationSession.reportLoopErrorRefs(firClassLikeDeclaration, arrayList2);
        }
        linkedHashSet2.remove(firClassLikeDeclaration);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private static final void breakLoopFor$checkIsInLoop$checkTypeArgumentsRecursively(FirSession firSession, boolean z, boolean z2, SupertypeComputationSession supertypeComputationSession, Set<FirClassLikeDeclaration> set, LinkedHashSet<FirClassLikeDeclaration> linkedHashSet, Set<FirClassLikeDeclaration> set2, LocalClassesNavigationInfo localClassesNavigationInfo, ConeKotlinType coneKotlinType, Set<ConeKotlinType> set3) throws KotlinIllegalArgumentExceptionWithAttachments {
        Set<ConeKotlinType> set4 = set3;
        if (set4.contains(coneKotlinType)) {
            return;
        }
        set4.add(coneKotlinType);
        ConeTypeProjection[] typeArguments = coneKotlinType.getTypeArguments();
        int length = typeArguments.length;
        int i = 0;
        while (i < length) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(typeArguments[i]);
            ConeRigidType coneRigidTypeLowerBoundIfFlexible = type != null ? ConeTypeUtilsKt.lowerBoundIfFlexible(type) : null;
            ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
            if (coneClassLikeType != null) {
                FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType.getLookupTag(), firSession);
                breakLoopFor$checkIsInLoop(supertypeComputationSession, firSession, set, linkedHashSet, set2, localClassesNavigationInfo, symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null, z, z2);
                breakLoopFor$checkIsInLoop$checkTypeArgumentsRecursively(firSession, z, z2, supertypeComputationSession, set, linkedHashSet, set2, localClassesNavigationInfo, coneClassLikeType, set4);
            }
            i++;
            set4 = set3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConeClassLikeType getResolvedExpandedType(FirTypeAlias typeAlias) {
        FirTypeRef expandedTypeRef = typeAlias.getExpandedTypeRef();
        if (!(expandedTypeRef instanceof FirResolvedTypeRef)) {
            expandedTypeRef = null;
        }
        if (expandedTypeRef == null) {
            expandedTypeRef = getResolvedExpandedTypeRef(typeAlias);
        }
        FirResolvedTypeRef firResolvedTypeRef = expandedTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) expandedTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        return (ConeClassLikeType) (coneType instanceof ConeClassLikeType ? coneType : null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void breakLoopFor(FirClassLikeDeclaration declaration, FirSession session, Set<FirClassLikeDeclaration> visited, Set<FirClassLikeDeclaration> looped, LinkedHashSet<FirClassLikeDeclaration> pathOrderedSet, LocalClassesNavigationInfo localClassesNavigationInfo) throws KotlinIllegalArgumentExceptionWithAttachments {
        declaration.getClass();
        session.getClass();
        visited.getClass();
        looped.getClass();
        pathOrderedSet.getClass();
        if (!pathOrderedSet.isEmpty()) {
            w01.a("Path ordered set should be empty before starting");
            return;
        }
        breakLoopFor$checkIsInLoop(this, session, visited, pathOrderedSet, looped, localClassesNavigationInfo, declaration, false, false);
        if (pathOrderedSet.isEmpty()) {
            return;
        }
        w01.a("Path ordered set should be empty after finishing");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void breakLoops(FirSession session, LocalClassesNavigationInfo localClassesNavigationInfo) throws KotlinIllegalArgumentExceptionWithAttachments {
        session.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        LinkedHashSet<FirClassLikeDeclaration> linkedHashSet3 = new LinkedHashSet<>();
        Iterator<FirClassLikeDeclaration> it = this.newClassifiersForBreakingLoops.iterator();
        while (it.hasNext()) {
            breakLoopFor(it.next(), session, linkedHashSet, linkedHashSet2, linkedHashSet3, localClassesNavigationInfo);
        }
        this.newClassifiersForBreakingLoops.clear();
    }

    public final FirTypeRef expandTypealiasInPlace(FirTypeRef typeRef, FirSession session) {
        typeRef.getClass();
        session.getClass();
        if ((typeRef instanceof FirImplicitBuiltinTypeRef) || (typeRef instanceof FirErrorTypeRef)) {
            return typeRef;
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(FirTypeUtilsKt.getConeType(typeRef), session, new SupertypeComputationSession$expandTypealiasInPlace$expanded$1(this));
        if (!Intrinsics.areEqual(coneKotlinTypeFullyExpandedType, FirTypeUtilsKt.getConeType(typeRef)) && ((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).getFlag(AnalysisFlags.getExpandTypeAliasesInTypeResolution())).booleanValue()) {
            return TypeUtilsKt.withReplacedConeType$default(typeRef, coneKotlinTypeFullyExpandedType, null, 2, null);
        }
        return typeRef;
    }

    public final PersistentList<FirScope> getOrPutFileScope(FirFile file, Function0<? extends PersistentList<? extends FirScope>> scope) {
        file.getClass();
        scope.getClass();
        HashMap<FirFile, PersistentList<FirScope>> map = this.fileScopesMap;
        PersistentList<FirScope> persistentList = map.get(file);
        if (persistentList == null) {
            persistentList = (PersistentList) scope.invoke();
            map.put(file, persistentList);
        }
        return persistentList;
    }

    public final PersistentList<FirScope> getOrPutScopeForCompanion(FirClass klass, Function0<? extends PersistentList<? extends FirScope>> scope) {
        klass.getClass();
        scope.getClass();
        HashMap<FirClass, PersistentList<FirScope>> map = this.scopesForCompanionMap;
        PersistentList<FirScope> persistentList = map.get(klass);
        if (persistentList == null) {
            persistentList = (PersistentList) scope.invoke();
            map.put(klass, persistentList);
        }
        return persistentList;
    }

    public final PersistentList<FirScope> getOrPutScopeForNestedClasses(FirClass klass, Function0<? extends PersistentList<? extends FirScope>> scope) {
        klass.getClass();
        scope.getClass();
        HashMap<FirClass, PersistentList<FirScope>> map = this.scopesForNestedClassesMap;
        PersistentList<FirScope> persistentList = map.get(klass);
        if (persistentList == null) {
            persistentList = (PersistentList) scope.invoke();
            map.put(klass, persistentList);
        }
        return persistentList;
    }

    public final PersistentList<FirScope> getOrPutScopeForStaticNestedClasses(FirClass klass, Function0<? extends PersistentList<? extends FirScope>> scope) {
        klass.getClass();
        scope.getClass();
        HashMap<FirClass, PersistentList<FirScope>> map = this.scopesForStaticNestedClassesMap;
        PersistentList<FirScope> persistentList = map.get(klass);
        if (persistentList == null) {
            persistentList = (PersistentList) scope.invoke();
            map.put(klass, persistentList);
        }
        return persistentList;
    }

    public final FirTypeRef getResolvedExpandedTypeRef(FirTypeAlias typeAlias) {
        typeAlias.getClass();
        List<FirResolvedTypeRef> resolvedSupertypeRefs = getResolvedSupertypeRefs(typeAlias);
        resolvedSupertypeRefs.size();
        return resolvedSupertypeRefs.get(0);
    }

    public List<FirResolvedTypeRef> getResolvedSuperTypeRefsForOutOfSessionDeclaration(FirClassLikeDeclaration classLikeDeclaration, FirSession useSiteSession) {
        classLikeDeclaration.getClass();
        useSiteSession.getClass();
        if (!(classLikeDeclaration instanceof FirClass)) {
            if (classLikeDeclaration instanceof FirTypeAlias) {
                FirTypeRef expandedTypeRef = ((FirTypeAlias) classLikeDeclaration).getExpandedTypeRef();
                return CollectionsKt.listOfNotNull(expandedTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) expandedTypeRef : null);
            }
            bu8.a();
            return null;
        }
        List<FirTypeRef> superTypeRefs = ((FirClass) classLikeDeclaration).getSuperTypeRefs();
        ArrayList arrayList = new ArrayList();
        for (Object obj : superTypeRefs) {
            if (obj instanceof FirResolvedTypeRef) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final List<FirResolvedTypeRef> getResolvedSupertypeRefs(FirClassLikeDeclaration classLikeDeclaration) {
        classLikeDeclaration.getClass();
        SupertypeComputationStatus supertypesComputationStatus = getSupertypesComputationStatus(classLikeDeclaration);
        if (supertypesComputationStatus instanceof SupertypeComputationStatus.Computed) {
            return ((SupertypeComputationStatus.Computed) supertypesComputationStatus).getSupertypeRefs();
        }
        StringBuilder sb = new StringBuilder("Unexpected status at FirApplySupertypesTransformer: ");
        sb.append(supertypesComputationStatus);
        ywd.a(sb, " for ", classLikeDeclaration.getSymbol().getClassId());
        return null;
    }

    public final SupertypeComputationStatus getSupertypesComputationStatus(FirClassLikeDeclaration classLikeDeclaration) {
        classLikeDeclaration.getClass();
        SupertypeComputationStatus supertypeComputationStatus = this.supertypeStatusMap.get(classLikeDeclaration);
        return supertypeComputationStatus == null ? SupertypeComputationStatus.NotComputed.INSTANCE : supertypeComputationStatus;
    }

    public final SupertypeSupplier getSupertypesSupplier() {
        return this.supertypesSupplier;
    }

    public void reportLoopErrorRefs(FirClassLikeDeclaration classLikeDeclaration, List<? extends FirResolvedTypeRef> supertypeRefs) {
        classLikeDeclaration.getClass();
        supertypeRefs.getClass();
        this.supertypeStatusMap.put(classLikeDeclaration, new SupertypeComputationStatus.Computed(supertypeRefs));
    }

    public final void startComputingSupertypes(FirClassLikeDeclaration classLikeDeclaration) {
        classLikeDeclaration.getClass();
        if (this.supertypeStatusMap.get(classLikeDeclaration) == null) {
            this.supertypeStatusMap.put(classLikeDeclaration, SupertypeComputationStatus.Computing.INSTANCE);
            return;
        }
        StringBuilder sb = new StringBuilder("Unexpected in startComputingSupertypes supertype status for ");
        sb.append(classLikeDeclaration);
        ywd.a(sb, ": ", this.supertypeStatusMap.get(classLikeDeclaration));
    }

    public final void storeSupertypes(FirClassLikeDeclaration classLikeDeclaration, List<? extends FirResolvedTypeRef> resolvedTypesRefs) {
        classLikeDeclaration.getClass();
        resolvedTypesRefs.getClass();
        if (this.supertypeStatusMap.get(classLikeDeclaration) instanceof SupertypeComputationStatus.Computing) {
            this.supertypeStatusMap.put(classLikeDeclaration, new SupertypeComputationStatus.Computed(resolvedTypesRefs));
            this.newClassifiersForBreakingLoops.add(classLikeDeclaration);
        } else {
            StringBuilder sb = new StringBuilder("Unexpected in storeSupertypes supertype status for ");
            sb.append(classLikeDeclaration);
            ywd.a(sb, ": ", this.supertypeStatusMap.get(classLikeDeclaration));
        }
    }

    public List<FirTypeRef> supertypeRefs(FirClassLikeDeclaration declaration, FirSession useSiteSession) {
        declaration.getClass();
        useSiteSession.getClass();
        if (declaration instanceof FirRegularClass) {
            return ((FirRegularClass) declaration).getSuperTypeRefs();
        }
        return declaration instanceof FirTypeAlias ? CollectionsKt.listOf(((FirTypeAlias) declaration).getExpandedTypeRef()) : CollectionsKt.emptyList();
    }
}

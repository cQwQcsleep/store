package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.CaptureStatus;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.utils.SmartList;
import org.jetbrains.kotlin.utils.SmartSet;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\\\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00012\u0010\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0012\b\u0002\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011\u001a>\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u001a.\u0010\u0015\u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b\u001a6\u0010\u0015\u001a\u00020\u000b*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a\u0012\u0010\u001a\u001a\u00020\u000b*\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0006\u001a0\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00012\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006\u001a-\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H 0\u001e\"\n\b\u0000\u0010\u001f\u0018\u0001*\u00020!\"\n\b\u0001\u0010 \u0018\u0001*\u00020\"H\u0086\b\u001a0\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00040)2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00012\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0006\u001a*\u0010-\u001a\u0004\u0018\u00010\u00042\u0006\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u0002002\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0004H\u0002\u001a\u001a\u00102\u001a\u00020\b*\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f\u001aX\u00103\u001a\u000204*\u0006\u0012\u0002\b\u00030\u001c2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\b062\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u00112\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001a_\u00108\u001a\u000204*\u0006\u0012\u0002\b\u00030\u001c2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u00112\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002040:H\u0082\b\u001an\u0010;\u001a\u000204*\u0006\u0012\u0002\b\u00030\u001c2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\b\u0002\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u00112\u001c\u0010<\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001c\u0012\u0004\u0012\u0002040=H\u0086\bø\u0001\u0000\u001ak\u0010>\u001a\u000204\"\u0004\b\u0000\u0010?\"\u0004\b\u0001\u0010@*\u0002H?2\u0018\u0010A\u001a\u0014\u0012\u0004\u0012\u0002H?\u0012\n\u0012\b\u0012\u0004\u0012\u0002H@0\u00010:2\u001a\u0010B\u001a\u0016\u0012\u0004\u0012\u0002H?\u0012\u0004\u0012\u0002H@\u0012\u0006\u0012\u0004\u0018\u0001H?0=2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u0002H?\u0012\u0004\u0012\u00020\u000b0:H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010D\u001a\u0014\u0010E\u001a\u00020\u000b*\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020I2\u0006\u0010\u0018\u001a\u00020\u0006\u001a\u0014\u0010J\u001a\u0004\u0018\u00010K*\u00020K2\u0006\u0010\u0018\u001a\u00020\u0006\u001aD\u0010L\u001a\b\u0012\u0004\u0012\u00020\b0\u0001*\u0006\u0012\u0002\b\u00030\u001c2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010M\u001a\u00020\u000b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f\"-\u0010#\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020$\u0012\u0004\u0012\u00020%0\u001e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006N"}, d2 = {"collectSymbolsForType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "lookupSuperTypes", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "symbols", "lookupInterfaces", Argument.Delimiters.none, "deep", "substituteTypes", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "visitedSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "isSubclassOf", "ownerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "session", "isStrict", "isThereLoopInSupertypes", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "scopeSessionKey", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "ID", "FS", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "USE_SITE", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "getUSE_SITE", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "createSubstitutionForScope", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "computeNonTrivialTypeArgumentForScopeSubstitutor", "typeParameterSymbol", "originalTypeArgument", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "capturedTypeArgument", "computePartialExpansion", "collectSuperTypes", Argument.Delimiters.none, "list", Argument.Delimiters.none, "substituteSuperTypes", "forEachSupertype", "onType", "Lkotlin/Function1;", "forEachSupertypeWithInheritor", "onSupertypeAndInheritor", "Lkotlin/Function2;", "traverseDepthFirstWithoutDuplicates", "T", "K", "getSubsequent", "toStackElement", "visit", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "isClassBasedType", "createSubstitutionForSupertype", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "superType", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "getSuperClassSymbolOrAny", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getSuperTypes", "recursive", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SupertypeUtilsKt {
    private static final ScopeSessionKey<Pair<FirSession, FirClassSymbol<?>>, FirTypeScope> USE_SITE = new ScopeSessionKey<Pair<? extends FirSession, ? extends FirClassSymbol<?>>, FirTypeScope>() { // from class: org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt$special$$inlined$scopeSessionKey$1
    };

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProjectionKind.values().length];
            try {
                iArr[ProjectionKind.OUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProjectionKind.STAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static ConeClassLikeType a(SupertypeSupplier supertypeSupplier, FirSession firSession, FirTypeAlias firTypeAlias) {
        firTypeAlias.getClass();
        return supertypeSupplier.expansionForTypeAlias(firTypeAlias, firSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void collectSuperTypes(FirClassLikeSymbol<?> firClassLikeSymbol, List<ConeClassLikeType> list, Set<FirClassifierSymbol<?>> set, boolean z, boolean z2, boolean z3, FirSession firSession, SupertypeSupplier supertypeSupplier) {
        Collection<Pair> collectionEmptyList;
        ConeClassLikeType coneClassLikeTypeComputePartialExpansion;
        List listMutableListOf = CollectionsKt.mutableListOf(new Object[]{TuplesKt.to(firClassLikeSymbol, ConeSubstitutor.Empty.INSTANCE)});
        while (!listMutableListOf.isEmpty()) {
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            if (set.add((FirClassLikeSymbol) pair.component1())) {
                FirClassLikeSymbol firClassLikeSymbol2 = (FirClassLikeSymbol) pair.component1();
                ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pair.component2();
                if (firClassLikeSymbol2 instanceof FirClassSymbol) {
                    List<ConeClassLikeType> listForClass = supertypeSupplier.forClass((FirClass) ((FirClassSymbol) firClassLikeSymbol2).getFir(), firSession);
                    ArrayList<ConeClassLikeType> arrayList = new ArrayList();
                    Iterator<T> it = listForClass.iterator();
                    while (it.hasNext()) {
                        ConeClassLikeType coneClassLikeTypeComputePartialExpansion2 = computePartialExpansion((ConeClassLikeType) it.next(), firSession, supertypeSupplier);
                        if (!z2 && !isClassBasedType(coneClassLikeTypeComputePartialExpansion2, firSession)) {
                            coneClassLikeTypeComputePartialExpansion2 = null;
                        }
                        if (coneClassLikeTypeComputePartialExpansion2 != null) {
                            arrayList.add(coneClassLikeTypeComputePartialExpansion2);
                        }
                    }
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutor.substituteOrSelf((ConeClassLikeType) it2.next());
                        coneKotlinTypeSubstituteOrSelf.getClass();
                        list.add((ConeClassLikeType) coneKotlinTypeSubstituteOrSelf);
                    }
                    if (z) {
                        collectionEmptyList = new ArrayList();
                        for (ConeClassLikeType coneClassLikeType : arrayList) {
                            Pair pair2 = coneClassLikeType instanceof ConeErrorType ? null : TuplesKt.to(coneClassLikeType, z3 ? ChainedSubstitutorKt.chain(createSubstitutionForSupertype(coneClassLikeType, firSession), coneSubstitutor) : coneSubstitutor);
                            if (pair2 != null) {
                                collectionEmptyList.add(pair2);
                            }
                        }
                    } else {
                        collectionEmptyList = CollectionsKt.emptyList();
                    }
                } else if (!(firClassLikeSymbol2 instanceof FirTypeAliasSymbol)) {
                    bu8.a();
                    return;
                } else {
                    ConeClassLikeType coneClassLikeTypeExpansionForTypeAlias = supertypeSupplier.expansionForTypeAlias((FirTypeAlias) ((FirTypeAliasSymbol) firClassLikeSymbol2).getFir(), firSession);
                    collectionEmptyList = (coneClassLikeTypeExpansionForTypeAlias == null || (coneClassLikeTypeComputePartialExpansion = computePartialExpansion(coneClassLikeTypeExpansionForTypeAlias, firSession, supertypeSupplier)) == null) ? CollectionsKt.emptyList() : CollectionsKt.listOf(TuplesKt.to(coneClassLikeTypeComputePartialExpansion, coneSubstitutor));
                }
                for (Pair pair3 : collectionEmptyList) {
                    ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) pair3.component1();
                    ConeSubstitutor coneSubstitutor2 = (ConeSubstitutor) pair3.component2();
                    FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType2.getLookupTag(), firSession);
                    Pair pair4 = symbol != null ? TuplesKt.to(symbol, coneSubstitutor2) : null;
                    if (pair4 != null) {
                        listMutableListOf.add(pair4);
                    }
                }
            }
        }
    }

    public static final List<FirClassSymbol<?>> collectSymbolsForType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ArrayList arrayList = new ArrayList();
        collectSymbolsForType$collectClassIds(coneKotlinType, firSession, arrayList);
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol((ConeClassLikeLookupTag) it.next(), firSession);
            if (classSymbol != null) {
                arrayList2.add(classSymbol);
            }
        }
        return arrayList2;
    }

    private static final void collectSymbolsForType$collectClassIds(ConeKotlinType coneKotlinType, FirSession firSession, List<ConeClassLikeLookupTag> list) {
        ConeSimpleKotlinType coneSimpleKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneKotlinType), firSession, (Function1) null, 2, (Object) null);
        if (coneSimpleKotlinTypeFullyExpandedType$default instanceof ConeClassLikeType) {
            org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list, ((ConeClassLikeType) coneSimpleKotlinTypeFullyExpandedType$default).getLookupTag());
        } else if (coneSimpleKotlinTypeFullyExpandedType$default instanceof ConeIntersectionType) {
            Iterator<T> it = ((ConeIntersectionType) coneSimpleKotlinTypeFullyExpandedType$default).getIntersectedTypes().iterator();
            while (it.hasNext()) {
                collectSymbolsForType$collectClassIds((ConeKotlinType) it.next(), firSession, list);
            }
        }
    }

    private static final ConeKotlinType computeNonTrivialTypeArgumentForScopeSubstitutor(FirTypeParameterSymbol firTypeParameterSymbol, ConeTypeProjection coneTypeProjection, FirSession firSession, ConeKotlinType coneKotlinType) {
        if (firTypeParameterSymbol.getVariance() != Variance.OUT_VARIANCE) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[coneTypeProjection.getKind().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            return TypeComponentsKt.getTypeApproximator(firSession).approximateToSuperType(coneKotlinType, TypeApproximatorConfiguration.FinalApproximationAfterResolutionAndInference.INSTANCE);
        }
        ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
        type.getClass();
        return type;
    }

    public static final ConeClassLikeType computePartialExpansion(ConeClassLikeType coneClassLikeType, final FirSession firSession, final SupertypeSupplier supertypeSupplier) {
        coneClassLikeType.getClass();
        firSession.getClass();
        supertypeSupplier.getClass();
        return TypeExpansionUtilsKt.fullyExpandedType(coneClassLikeType, firSession, (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) new Function1() { // from class: utd
            public final Object invoke(Object obj) {
                return SupertypeUtilsKt.a(supertypeSupplier, firSession, (FirTypeAlias) obj);
            }
        });
    }

    public static final Map<FirTypeParameterSymbol, ConeKotlinType> createSubstitutionForScope(List<? extends FirTypeParameterRef> list, ConeClassLikeType coneClassLikeType, FirSession firSession) {
        list.getClass();
        coneClassLikeType.getClass();
        firSession.getClass();
        ConeRigidType coneRigidType = (ConeRigidType) TypeComponentsKt.getTypeContext(firSession).m672captureFromArguments((RigidTypeMarker) coneClassLikeType, CaptureStatus.FROM_EXPRESSION);
        if (coneRigidType == null) {
            coneRigidType = coneClassLikeType;
        }
        ConeTypeProjection[] typeArguments = coneRigidType.getTypeArguments();
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(list);
        ArrayList arrayList = new ArrayList();
        for (IndexedValue indexedValue : iterableWithIndex) {
            int index = indexedValue.getIndex();
            FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) indexedValue.component2();
            ConeKotlinType coneKotlinType = (ConeTypeProjection) ArraysKt.getOrNull(typeArguments, index);
            Pair pair = null;
            if (coneKotlinType != null) {
                if (!(coneKotlinType instanceof ConeKotlinType)) {
                    t2f.a("There should left no projections after capture conversion, but ", coneKotlinType, " found at ", index);
                    return null;
                }
                ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.getOrNull(coneClassLikeType.getTypeArguments(), index);
                if (coneTypeProjection != null) {
                    FirTypeParameterSymbol symbol = firTypeParameterRef.getSymbol();
                    ConeKotlinType coneKotlinType2 = coneKotlinType;
                    ConeKotlinType coneKotlinTypeComputeNonTrivialTypeArgumentForScopeSubstitutor = computeNonTrivialTypeArgumentForScopeSubstitutor(symbol, coneTypeProjection, firSession, coneKotlinType2);
                    if (coneKotlinTypeComputeNonTrivialTypeArgumentForScopeSubstitutor != null) {
                        coneKotlinType2 = coneKotlinTypeComputeNonTrivialTypeArgumentForScopeSubstitutor;
                    }
                    pair = TuplesKt.to(symbol, coneKotlinType2);
                }
            }
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return MapsKt.toMap(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeSubstitutor createSubstitutionForSupertype(ConeLookupTagBasedType coneLookupTagBasedType, FirSession firSession) {
        FirRegularClass firRegularClass;
        coneLookupTagBasedType.getClass();
        firSession.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneLookupTagBasedType.getLookupTag(), firSession);
        if (regularClassSymbol == null || (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) == null) {
            return ConeSubstitutor.Empty.INSTANCE;
        }
        ConeKotlinType[] typeArguments = coneLookupTagBasedType.getTypeArguments();
        ArrayList arrayList = new ArrayList(typeArguments.length);
        int length = typeArguments.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            ConeKotlinType coneKotlinType = typeArguments[i];
            ConeKotlinType coneErrorType = coneKotlinType instanceof ConeKotlinType ? coneKotlinType : null;
            if (coneErrorType == null) {
                coneErrorType = new ConeErrorType(new ConeSimpleDiagnostic("illegal projection usage", DiagnosticKind.IllegalProjectionUsage), false, null, null, null, null, null, 126, null);
            }
            arrayList.add(coneErrorType);
            i++;
        }
        List<FirTypeParameterRef> typeParameters = firRegularClass.getTypeParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList2.add(((FirTypeParameterRef) it.next()).getSymbol());
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(MapsKt.toMap(CollectionsKt.zip(arrayList2, arrayList)), firSession, false, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void forEachSupertypeWithInheritor(FirClassLikeSymbol<?> firClassLikeSymbol, boolean z, boolean z2, boolean z3, FirSession firSession, SupertypeSupplier supertypeSupplier, Set<FirClassifierSymbol<?>> set, Function2<? super ConeClassLikeType, ? super FirClassLikeSymbol<?>, Unit> function2) {
        Collection<Pair> collectionEmptyList;
        ConeClassLikeType coneClassLikeTypeComputePartialExpansion;
        firClassLikeSymbol.getClass();
        firSession.getClass();
        supertypeSupplier.getClass();
        set.getClass();
        function2.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Object[]{TuplesKt.to(firClassLikeSymbol, ConeSubstitutor.Empty.INSTANCE)});
        while (!listMutableListOf.isEmpty()) {
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            if (set.add((FirClassLikeSymbol) pair.component1())) {
                FirClassLikeSymbol firClassLikeSymbol2 = (FirClassLikeSymbol) pair.component1();
                ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pair.component2();
                if (firClassLikeSymbol2 instanceof FirClassSymbol) {
                    List<ConeClassLikeType> listForClass = supertypeSupplier.forClass((FirClass) ((FirClassSymbol) firClassLikeSymbol2).getFir(), firSession);
                    ArrayList<ConeClassLikeType> arrayList = new ArrayList();
                    Iterator<T> it = listForClass.iterator();
                    while (it.hasNext()) {
                        ConeClassLikeType coneClassLikeTypeComputePartialExpansion2 = computePartialExpansion((ConeClassLikeType) it.next(), firSession, supertypeSupplier);
                        if (!z2 && !isClassBasedType(coneClassLikeTypeComputePartialExpansion2, firSession)) {
                            coneClassLikeTypeComputePartialExpansion2 = null;
                        }
                        if (coneClassLikeTypeComputePartialExpansion2 != null) {
                            arrayList.add(coneClassLikeTypeComputePartialExpansion2);
                        }
                    }
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutor.substituteOrSelf((ConeClassLikeType) it2.next());
                        coneKotlinTypeSubstituteOrSelf.getClass();
                        function2.invoke((ConeClassLikeType) coneKotlinTypeSubstituteOrSelf, firClassLikeSymbol2);
                    }
                    if (z) {
                        collectionEmptyList = new ArrayList();
                        for (ConeClassLikeType coneClassLikeType : arrayList) {
                            Pair pair2 = coneClassLikeType instanceof ConeErrorType ? null : TuplesKt.to(coneClassLikeType, z3 ? ChainedSubstitutorKt.chain(createSubstitutionForSupertype(coneClassLikeType, firSession), coneSubstitutor) : coneSubstitutor);
                            if (pair2 != null) {
                                collectionEmptyList.add(pair2);
                            }
                        }
                    } else {
                        collectionEmptyList = CollectionsKt.emptyList();
                    }
                } else if (!(firClassLikeSymbol2 instanceof FirTypeAliasSymbol)) {
                    bu8.a();
                    return;
                } else {
                    ConeClassLikeType coneClassLikeTypeExpansionForTypeAlias = supertypeSupplier.expansionForTypeAlias((FirTypeAlias) ((FirTypeAliasSymbol) firClassLikeSymbol2).getFir(), firSession);
                    collectionEmptyList = (coneClassLikeTypeExpansionForTypeAlias == null || (coneClassLikeTypeComputePartialExpansion = computePartialExpansion(coneClassLikeTypeExpansionForTypeAlias, firSession, supertypeSupplier)) == null) ? CollectionsKt.emptyList() : CollectionsKt.listOf(TuplesKt.to(coneClassLikeTypeComputePartialExpansion, coneSubstitutor));
                }
                for (Pair pair3 : collectionEmptyList) {
                    ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) pair3.component1();
                    ConeSubstitutor coneSubstitutor2 = (ConeSubstitutor) pair3.component2();
                    FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType2.getLookupTag(), firSession);
                    Pair pair4 = symbol != null ? TuplesKt.to(symbol, coneSubstitutor2) : null;
                    if (pair4 != null) {
                        listMutableListOf.add(pair4);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void forEachSupertypeWithInheritor$default(FirClassLikeSymbol firClassLikeSymbol, boolean z, boolean z2, boolean z3, FirSession firSession, SupertypeSupplier supertypeSupplier, Set set, Function2 function2, int i, Object obj) {
        Collection<Pair> collectionEmptyList;
        ConeClassLikeType coneClassLikeTypeComputePartialExpansion;
        if ((i & 32) != 0) {
            set = new LinkedHashSet();
        }
        firClassLikeSymbol.getClass();
        firSession.getClass();
        supertypeSupplier.getClass();
        set.getClass();
        function2.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Object[]{TuplesKt.to(firClassLikeSymbol, ConeSubstitutor.Empty.INSTANCE)});
        while (!listMutableListOf.isEmpty()) {
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            if (set.add((FirClassLikeSymbol) pair.component1())) {
                FirClassLikeSymbol firClassLikeSymbol2 = (FirClassLikeSymbol) pair.component1();
                ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pair.component2();
                if (firClassLikeSymbol2 instanceof FirClassSymbol) {
                    List<ConeClassLikeType> listForClass = supertypeSupplier.forClass((FirClass) ((FirClassSymbol) firClassLikeSymbol2).getFir(), firSession);
                    ArrayList<ConeClassLikeType> arrayList = new ArrayList();
                    Iterator<T> it = listForClass.iterator();
                    while (it.hasNext()) {
                        ConeClassLikeType coneClassLikeTypeComputePartialExpansion2 = computePartialExpansion((ConeClassLikeType) it.next(), firSession, supertypeSupplier);
                        if (!z2 && !isClassBasedType(coneClassLikeTypeComputePartialExpansion2, firSession)) {
                            coneClassLikeTypeComputePartialExpansion2 = null;
                        }
                        if (coneClassLikeTypeComputePartialExpansion2 != null) {
                            arrayList.add(coneClassLikeTypeComputePartialExpansion2);
                        }
                    }
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutor.substituteOrSelf((ConeClassLikeType) it2.next());
                        coneKotlinTypeSubstituteOrSelf.getClass();
                        function2.invoke((ConeClassLikeType) coneKotlinTypeSubstituteOrSelf, firClassLikeSymbol2);
                    }
                    if (z) {
                        collectionEmptyList = new ArrayList();
                        for (ConeClassLikeType coneClassLikeType : arrayList) {
                            Pair pair2 = coneClassLikeType instanceof ConeErrorType ? null : TuplesKt.to(coneClassLikeType, z3 ? ChainedSubstitutorKt.chain(createSubstitutionForSupertype(coneClassLikeType, firSession), coneSubstitutor) : coneSubstitutor);
                            if (pair2 != null) {
                                collectionEmptyList.add(pair2);
                            }
                        }
                    } else {
                        collectionEmptyList = CollectionsKt.emptyList();
                    }
                } else if (!(firClassLikeSymbol2 instanceof FirTypeAliasSymbol)) {
                    bu8.a();
                    return;
                } else {
                    ConeClassLikeType coneClassLikeTypeExpansionForTypeAlias = supertypeSupplier.expansionForTypeAlias((FirTypeAlias) ((FirTypeAliasSymbol) firClassLikeSymbol2).getFir(), firSession);
                    collectionEmptyList = (coneClassLikeTypeExpansionForTypeAlias == null || (coneClassLikeTypeComputePartialExpansion = computePartialExpansion(coneClassLikeTypeExpansionForTypeAlias, firSession, supertypeSupplier)) == null) ? CollectionsKt.emptyList() : CollectionsKt.listOf(TuplesKt.to(coneClassLikeTypeComputePartialExpansion, coneSubstitutor));
                }
                for (Pair pair3 : collectionEmptyList) {
                    ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) pair3.component1();
                    ConeSubstitutor coneSubstitutor2 = (ConeSubstitutor) pair3.component2();
                    FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType2.getLookupTag(), firSession);
                    Pair pair4 = symbol != null ? TuplesKt.to(symbol, coneSubstitutor2) : null;
                    if (pair4 != null) {
                        listMutableListOf.add(pair4);
                    }
                }
            }
        }
    }

    public static final FirRegularClassSymbol getSuperClassSymbolOrAny(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
        firRegularClassSymbol.getClass();
        firSession.getClass();
        Iterator<ConeKotlinType> it = firRegularClassSymbol.getResolvedSuperTypes().iterator();
        while (it.hasNext()) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(it.next(), firSession, (Function1) null, 2, (Object) null), firSession);
            if (regularClassSymbol != null && regularClassSymbol.getClassKind() == ClassKind.CLASS) {
                return regularClassSymbol;
            }
        }
        return ToSymbolUtilsKt.toRegularClassSymbol(firSession.getBuiltinTypes().getAnyType().getConeType(), firSession);
    }

    public static final List<ConeClassLikeType> getSuperTypes(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession, boolean z, boolean z2, boolean z3, SupertypeSupplier supertypeSupplier) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        supertypeSupplier.getClass();
        SmartList smartList = new SmartList();
        collectSuperTypes(firClassLikeSymbol, smartList, SmartSet.Companion.create(), z, z2, z3, firSession, supertypeSupplier);
        return smartList;
    }

    public static /* synthetic */ List getSuperTypes$default(FirClassLikeSymbol firClassLikeSymbol, FirSession firSession, boolean z, boolean z2, boolean z3, SupertypeSupplier supertypeSupplier, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        if ((i & 8) != 0) {
            z3 = true;
        }
        if ((i & 16) != 0) {
            supertypeSupplier = SupertypeSupplier.Default.INSTANCE;
        }
        return getSuperTypes(firClassLikeSymbol, firSession, z, z2, z3, supertypeSupplier);
    }

    public static final ScopeSessionKey<Pair<FirSession, FirClassSymbol<?>>, FirTypeScope> getUSE_SITE() {
        return USE_SITE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isClassBasedType(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        ConeClassLikeLookupTag lookupTag;
        FirClassSymbol<?> classSymbol;
        firSession.getClass();
        if (!(coneClassLikeType instanceof ConeErrorType) && coneClassLikeType != null && (lookupTag = coneClassLikeType.getLookupTag()) != null && (classSymbol = ToSymbolUtilsKt.toClassSymbol(lookupTag, firSession)) != null) {
            if (classSymbol instanceof FirAnonymousObjectSymbol) {
                return true;
            }
            if (classSymbol instanceof FirRegularClassSymbol) {
                return ((FirRegularClass) ((FirRegularClassSymbol) classSymbol).getFir()).getClassKind() == ClassKind.CLASS;
            }
            bu8.a();
        }
        return false;
    }

    public static final boolean isSubclassOf(FirClass firClass, ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession, boolean z, SupertypeSupplier supertypeSupplier, boolean z2) {
        firClass.getClass();
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        supertypeSupplier.getClass();
        if (Intrinsics.areEqual(firClass.getSymbol().getLookupTag(), coneClassLikeLookupTag)) {
            return !z;
        }
        List<ConeClassLikeType> listLookupSuperTypes = lookupSuperTypes(firClass, z2, true, firSession, false, supertypeSupplier);
        if ((listLookupSuperTypes instanceof Collection) && listLookupSuperTypes.isEmpty()) {
            return false;
        }
        Iterator<T> it = listLookupSuperTypes.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((ConeClassLikeType) it.next()).getLookupTag(), coneClassLikeLookupTag)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean isSubclassOf$default(FirClass firClass, ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession, boolean z, SupertypeSupplier supertypeSupplier, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            supertypeSupplier = SupertypeSupplier.Default.INSTANCE;
        }
        SupertypeSupplier supertypeSupplier2 = supertypeSupplier;
        if ((i & 16) != 0) {
            z2 = true;
        }
        return isSubclassOf(firClass, coneClassLikeLookupTag, firSession, z, supertypeSupplier2, z2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean isThereLoopInSupertypes(FirClass firClass, FirSession firSession) throws KotlinIllegalArgumentExceptionWithAttachments {
        firClass.getClass();
        firSession.getClass();
        SmartSet smartSetCreate = SmartSet.Companion.create();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        isThereLoopInSupertypes$dfs(smartSetCreate, linkedHashSet, booleanRef, firSession, firClass.getSymbol());
        return booleanRef.element;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final void isThereLoopInSupertypes$dfs(Set<FirClassifierSymbol<?>> set, Set<FirClassifierSymbol<?>> set2, Ref.BooleanRef booleanRef, FirSession firSession, FirClassifierSymbol<?> firClassifierSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeClassLikeType expandedConeType;
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> symbol;
        if (set.contains(firClassifierSymbol)) {
            return;
        }
        if (!set2.add(firClassifierSymbol)) {
            booleanRef.element = true;
            return;
        }
        Object fir = firClassifierSymbol.getFir();
        if (fir instanceof FirClass) {
            Iterator<T> it = FirDeclarationUtilKt.getSuperConeTypes((FirClass) fir).iterator();
            while (it.hasNext()) {
                FirClassLikeSymbol<?> symbol2 = ToSymbolUtilsKt.toSymbol(((ConeClassLikeType) it.next()).getLookupTag(), firSession);
                if (symbol2 != null) {
                    isThereLoopInSupertypes$dfs(set, set2, booleanRef, firSession, symbol2);
                }
            }
        } else if ((fir instanceof FirTypeAlias) && (expandedConeType = FirDeclarationUtilKt.getExpandedConeType((FirTypeAlias) fir)) != null && (lookupTag = expandedConeType.getLookupTag()) != null && (symbol = ToSymbolUtilsKt.toSymbol(lookupTag, firSession)) != null) {
            isThereLoopInSupertypes$dfs(set, set2, booleanRef, firSession, symbol);
        }
        set.add(firClassifierSymbol);
        set2.remove(firClassifierSymbol);
    }

    public static final List<ConeClassLikeType> lookupSuperTypes(List<? extends FirClassSymbol<?>> list, boolean z, boolean z2, FirSession firSession, boolean z3, SupertypeSupplier supertypeSupplier, Set<FirClassifierSymbol<?>> set) {
        list.getClass();
        firSession.getClass();
        supertypeSupplier.getClass();
        set.getClass();
        SmartList smartList = new SmartList();
        Iterator<? extends FirClassSymbol<?>> it = list.iterator();
        while (it.hasNext()) {
            collectSuperTypes(it.next(), smartList, set, z2, z, z3, firSession, supertypeSupplier);
        }
        return smartList;
    }

    public static /* synthetic */ List lookupSuperTypes$default(List list, boolean z, boolean z2, FirSession firSession, boolean z3, SupertypeSupplier supertypeSupplier, Set set, int i, Object obj) {
        if ((i & 32) != 0) {
            supertypeSupplier = SupertypeSupplier.Default.INSTANCE;
        }
        SupertypeSupplier supertypeSupplier2 = supertypeSupplier;
        if ((i & 64) != 0) {
            set = SmartSet.Companion.create();
        }
        return lookupSuperTypes(list, z, z2, firSession, z3, supertypeSupplier2, set);
    }

    public static final /* synthetic */ <ID, FS extends FirScope> ScopeSessionKey<ID, FS> scopeSessionKey() {
        Intrinsics.needClassReification();
        return (ScopeSessionKey<ID, FS>) new ScopeSessionKey<ID, FS>() { // from class: org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt.scopeSessionKey.1
        };
    }

    public static final <T, K> void traverseDepthFirstWithoutDuplicates(T t, Function1<? super T, ? extends List<? extends K>> function1, Function2<? super T, ? super K, ? extends T> function2, Function1<? super T, Boolean> function3) {
        function1.getClass();
        function2.getClass();
        function3.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Object[]{t});
        while (!listMutableListOf.isEmpty()) {
            Object objPopLast = AddToStdlibKt.popLast(listMutableListOf);
            if (((Boolean) function3.invoke(objPopLast)).booleanValue()) {
                Iterator<T> it = ((Iterable) function1.invoke(objPopLast)).iterator();
                while (it.hasNext()) {
                    Object objInvoke = function2.invoke(objPopLast, it.next());
                    if (objInvoke != null) {
                        listMutableListOf.add(objInvoke);
                    }
                }
            }
        }
    }

    public static /* synthetic */ List lookupSuperTypes$default(FirClass firClass, boolean z, boolean z2, FirSession firSession, boolean z3, SupertypeSupplier supertypeSupplier, int i, Object obj) {
        if ((i & 32) != 0) {
            supertypeSupplier = SupertypeSupplier.Default.INSTANCE;
        }
        return lookupSuperTypes(firClass, z, z2, firSession, z3, supertypeSupplier);
    }

    public static final List<ConeClassLikeType> lookupSuperTypes(FirClass firClass, boolean z, boolean z2, FirSession firSession, boolean z3, SupertypeSupplier supertypeSupplier) {
        firClass.getClass();
        firSession.getClass();
        supertypeSupplier.getClass();
        SmartList smartList = new SmartList();
        collectSuperTypes(firClass.getSymbol(), smartList, SmartSet.Companion.create(), z2, z, z3, firSession, supertypeSupplier);
        return smartList;
    }

    public static final List<ConeClassLikeType> lookupSuperTypes(FirClassLikeSymbol<?> firClassLikeSymbol, boolean z, boolean z2, FirSession firSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        SmartList smartList = new SmartList();
        collectSuperTypes(firClassLikeSymbol, smartList, SmartSet.Companion.create(), z2, z, false, firSession, SupertypeSupplier.Default.INSTANCE);
        return smartList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isSubclassOf(FirClassSymbol<?> firClassSymbol, ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession, boolean z, boolean z2) {
        firClassSymbol.getClass();
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firClassSymbol, FirResolvePhase.SUPER_TYPES);
        return isSubclassOf((FirClass) firClassSymbol.getFir(), coneClassLikeLookupTag, firSession, z, SupertypeSupplier.Default.INSTANCE, z2);
    }
}

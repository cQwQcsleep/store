package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttribute;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeConflictingProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.util.WeakPair;
import org.jetbrains.kotlin.util.WeakPairKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u0001R\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\t\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007R\u00020\u0007b\u0002\b\fj\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000b\u001a\f\u0010\r\u001a\u0004\u0018\u00010\u0001*\u00020\u0006\u001a*\u0010\u0000\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u001a\u001b\u0010\u0000\u001a\u00020\u000e*\u00020\u000eR\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\u000f\u001a)\u0010\u0000\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0007R\u00020\u0007b\u0002\b\fj\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u0010\u001a*\u0010\u0000\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u001a\u001b\u0010\u0000\u001a\u00020\u0011*\u00020\u0011R\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\u0012\u001a)\u0010\u0000\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003H\u0007R\u00020\u0007b\u0002\b\fj\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u0013\u001a*\u0010\u0000\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u001a\u001b\u0010\u0000\u001a\u00020\u0014*\u00020\u0014R\u00020\u0007j\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\u0015\u001aQ\u0010\u0000\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0007R\u00020\u0007b*\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u001c\b\u001a\u0012\u0018\b\u000bB\u0014\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0006\b\u001e\u0012\u0002\b\fj\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u0016\u001a*\u0010\u001f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H\u0002\u001a,\u0010 \u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u001a\u001c\u0010!\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0001H\u0002\u001a\u0014\u0010$\u001a\u00020\u0001*\u00020\u00012\u0006\u0010#\u001a\u00020\u0001H\u0002\u001a$\u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'0&*\u00020\u00062\u0006\u0010*\u001a\u00020\u000e\u001a\"\u0010+\u001a\u00020,2\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0.\u001a\u001a\u0010+\u001a\u00020,*\u00020\u00062\u0006\u0010/\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a(\u00100\u001a\u00020\u000e2\u0006\u00101\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0014\u00103\u001a\u0004\u0018\u00010\u0001*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u00104\u001a\u0004\u0018\u000105*\u00020\u00062\u0006\u0010\"\u001a\u00020\u0003\u001a,\u00106\u001a\u000207*\u00020\u000e2\u0006\u0010\"\u001a\u00020\u00032\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002070\u0005H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00069"}, d2 = {"fullyExpandedType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "expandedConeType", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "sessionHolder", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "<unused var>", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/resolve/ExplicitlyPassedSession;", "expandedConeTypeWithEnsuredPhase", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeRigidType;)Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeRigidType;Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "Lkotlin/Deprecated;", "message", "Use overload without parameter.", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "fullyExpandedType()", "imports", "fullyExpandedTypeNoCache", "directExpansionType", "applyNullabilityFrom", "session", "abbreviation", "applyAttributesFrom", "mapParametersToArgumentsOf", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", ModuleXmlParser.TYPE, "createParametersSubstitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "typeAliasMap", Argument.Delimiters.none, "abbreviatedType", "mapTypeAliasArguments", "typeAlias", "resultingType", "fullyExpandedConeType", "fullyExpandedClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "forEachExpandedType", Argument.Delimiters.none, "action", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeExpansionUtilsKt {

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00361 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final C00361 INSTANCE = new C00361();

        public C00361() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$10, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass10 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass10 INSTANCE = new AnonymousClass10();

        public AnonymousClass10() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$11, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass11 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass11 INSTANCE = new AnonymousClass11();

        public AnonymousClass11() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$12, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass12 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass12 INSTANCE = new AnonymousClass12();

        public AnonymousClass12() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$13, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass13 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass13 INSTANCE = new AnonymousClass13();

        public AnonymousClass13() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$3, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$4, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$6, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass6 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass6 INSTANCE = new AnonymousClass6();

        public AnonymousClass6() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$7, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass7 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass7 INSTANCE = new AnonymousClass7();

        public AnonymousClass7() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$8, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass8 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass8 INSTANCE = new AnonymousClass8();

        public AnonymousClass8() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$fullyExpandedType$9, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass9 extends FunctionReferenceImpl implements Function1<FirTypeAlias, ConeClassLikeType> {
        public static final AnonymousClass9 INSTANCE = new AnonymousClass9();

        public AnonymousClass9() {
            super(1, TypeExpansionUtilsKt.class, "expandedConeTypeWithEnsuredPhase", "expandedConeTypeWithEnsuredPhase(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", 1);
        }

        public final ConeClassLikeType invoke(FirTypeAlias firTypeAlias) {
            firTypeAlias.getClass();
            return TypeExpansionUtilsKt.expandedConeTypeWithEnsuredPhase(firTypeAlias);
        }
    }

    public static ConeClassLikeType a(FirTypeAlias firTypeAlias) {
        firTypeAlias.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firTypeAlias, FirResolvePhase.SUPER_TYPES);
        return FirDeclarationUtilKt.getExpandedConeType(firTypeAlias);
    }

    private static final ConeClassLikeType applyAttributesFrom(ConeClassLikeType coneClassLikeType, ConeClassLikeType coneClassLikeType2) {
        return (ConeClassLikeType) TypeUtilsKt.withAttributes(coneClassLikeType, coneClassLikeType.getAttributes().add(coneClassLikeType2.getAttributes()));
    }

    private static final ConeClassLikeType applyNullabilityFrom(ConeClassLikeType coneClassLikeType, FirSession firSession, ConeClassLikeType coneClassLikeType2) {
        return coneClassLikeType2.getIsMarkedNullable() ? (ConeClassLikeType) TypeUtilsKt.withNullability$default(coneClassLikeType, true, TypeComponentsKt.getTypeContext(firSession), null, false, 12, null) : coneClassLikeType;
    }

    public static final ConeSubstitutor createParametersSubstitutor(FirTypeAlias firTypeAlias, ConeClassLikeType coneClassLikeType, FirSession firSession) {
        firTypeAlias.getClass();
        coneClassLikeType.getClass();
        firSession.getClass();
        return createParametersSubstitutor(firSession, MapsKt.toMap(mapParametersToArgumentsOf(firTypeAlias, coneClassLikeType)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeClassLikeType directExpansionType(ConeClassLikeType coneClassLikeType, FirSession firSession, Function1<? super FirTypeAlias, ? extends ConeClassLikeType> function1) {
        FirTypeAliasSymbol typeAliasSymbol;
        ConeClassLikeType coneClassLikeTypeApplyNullabilityFrom;
        ConeClassLikeType coneClassLikeTypeApplyAttributesFrom;
        coneClassLikeType.getClass();
        firSession.getClass();
        function1.getClass();
        if ((coneClassLikeType instanceof ConeErrorType) || (typeAliasSymbol = ToSymbolUtilsKt.toTypeAliasSymbol(coneClassLikeType.getLookupTag(), firSession)) == null) {
            return null;
        }
        FirTypeAlias firTypeAlias = (FirTypeAlias) typeAliasSymbol.getFir();
        ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) function1.invoke(firTypeAlias);
        if (coneClassLikeType2 != null && (coneClassLikeTypeApplyNullabilityFrom = applyNullabilityFrom(coneClassLikeType2, firSession, coneClassLikeType)) != null && (coneClassLikeTypeApplyAttributesFrom = applyAttributesFrom(coneClassLikeTypeApplyNullabilityFrom, coneClassLikeType)) != null) {
            if (coneClassLikeTypeApplyAttributesFrom.getTypeArguments().length == 0) {
                return coneClassLikeTypeApplyAttributesFrom;
            }
            ConeKotlinType coneKotlinTypeMapTypeAliasArguments = mapTypeAliasArguments(firTypeAlias, coneClassLikeType, coneClassLikeTypeApplyAttributesFrom, firSession);
            if (coneKotlinTypeMapTypeAliasArguments instanceof ConeClassLikeType) {
                return (ConeClassLikeType) coneKotlinTypeMapTypeAliasArguments;
            }
        }
        return null;
    }

    public static /* synthetic */ ConeClassLikeType directExpansionType$default(ConeClassLikeType coneClassLikeType, FirSession firSession, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: rue
                public final Object invoke(Object obj2) {
                    return TypeExpansionUtilsKt.a((FirTypeAlias) obj2);
                }
            };
        }
        return directExpansionType(coneClassLikeType, firSession, function1);
    }

    public static final ConeClassLikeType expandedConeTypeWithEnsuredPhase(FirTypeAlias firTypeAlias) {
        firTypeAlias.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firTypeAlias, FirResolvePhase.SUPER_TYPES);
        return FirDeclarationUtilKt.getExpandedConeType(firTypeAlias);
    }

    public static final void forEachExpandedType(ConeKotlinType coneKotlinType, FirSession firSession, Function1<? super ConeKotlinType, Unit> function1) {
        coneKotlinType.getClass();
        firSession.getClass();
        function1.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{coneKotlinType});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinTypeFullyExpandedType$default = fullyExpandedType$default((ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf), firSession, (Function1) null, 2, (Object) null);
            function1.invoke(coneKotlinTypeFullyExpandedType$default);
            if (coneKotlinTypeFullyExpandedType$default instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinTypeFullyExpandedType$default;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinTypeFullyExpandedType$default instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinTypeFullyExpandedType$default).getOriginal());
            } else if (coneKotlinTypeFullyExpandedType$default instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinTypeFullyExpandedType$default).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinTypeFullyExpandedType$default.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirClassLikeDeclaration fullyExpandedClass(FirTypeAlias firTypeAlias, FirSession firSession) {
        FirClassLikeSymbol<?> symbol;
        firTypeAlias.getClass();
        firSession.getClass();
        ConeClassLikeType coneClassLikeTypeFullyExpandedConeType = fullyExpandedConeType(firTypeAlias, firSession);
        if (coneClassLikeTypeFullyExpandedConeType == null || (symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeTypeFullyExpandedConeType, firSession)) == null) {
            return null;
        }
        return (FirClassLikeDeclaration) symbol.getFir();
    }

    public static final ConeClassLikeType fullyExpandedConeType(FirTypeAlias firTypeAlias, FirSession firSession) {
        firTypeAlias.getClass();
        firSession.getClass();
        ConeClassLikeType expandedConeType = FirDeclarationUtilKt.getExpandedConeType(firTypeAlias);
        if (expandedConeType != null) {
            return fullyExpandedType$default(expandedConeType, firSession, (Function1) null, 2, (Object) null);
        }
        return null;
    }

    public static final ConeKotlinType fullyExpandedType(ConeKotlinType coneKotlinType, FirSession firSession, Function1<? super FirTypeAlias, ? extends ConeClassLikeType> function1) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        coneKotlinType.getClass();
        firSession.getClass();
        function1.getClass();
        if (coneKotlinType instanceof ConeDynamicType) {
            return coneKotlinType;
        }
        if (!(coneKotlinType instanceof ConeFlexibleType)) {
            return coneKotlinType instanceof ConeClassLikeType ? fullyExpandedType((ConeClassLikeType) coneKotlinType, firSession, function1) : coneKotlinType;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        ConeRigidType coneRigidTypeFullyExpandedType = fullyExpandedType(coneFlexibleType.getLowerBound(), firSession, function1);
        ConeKotlinType coneKotlinTypeCreate = null;
        if (coneRigidTypeFullyExpandedType == coneFlexibleType.getLowerBound()) {
            coneRigidTypeFullyExpandedType = null;
        }
        if (!coneFlexibleType.getIsTrivial()) {
            ConeRigidType coneRigidTypeFullyExpandedType2 = fullyExpandedType(coneFlexibleType.getUpperBound(), firSession, function1);
            if (coneRigidTypeFullyExpandedType2 == coneFlexibleType.getUpperBound()) {
                coneRigidTypeFullyExpandedType2 = null;
            }
            if (coneRigidTypeFullyExpandedType != null || coneRigidTypeFullyExpandedType2 != null) {
                if (coneFlexibleType instanceof ConeRawType) {
                    ConeRawType.Companion companion = ConeRawType.INSTANCE;
                    if (coneRigidTypeFullyExpandedType == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(coneRigidTypeFullyExpandedType)) == null) {
                        lowerBound = coneFlexibleType.getLowerBound();
                    }
                    if (coneRigidTypeFullyExpandedType2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(coneRigidTypeFullyExpandedType2)) == null) {
                        upperBound = coneFlexibleType.getUpperBound();
                    }
                    coneKotlinTypeCreate = companion.create(lowerBound, upperBound);
                } else {
                    if (coneRigidTypeFullyExpandedType == null) {
                        coneRigidTypeFullyExpandedType = coneFlexibleType.getLowerBound();
                    }
                    if (coneRigidTypeFullyExpandedType2 == null) {
                        coneRigidTypeFullyExpandedType2 = coneFlexibleType.getUpperBound();
                    }
                    coneKotlinTypeCreate = TypeUtilsKt.coneFlexibleOrSimpleType(typeContext, coneRigidTypeFullyExpandedType, coneRigidTypeFullyExpandedType2, false);
                }
            }
        } else if (coneRigidTypeFullyExpandedType != null) {
            coneKotlinTypeCreate = TypeUtilsKt.coneFlexibleOrSimpleType(typeContext, coneRigidTypeFullyExpandedType, TypeUtilsKt.withNullability$default(coneRigidTypeFullyExpandedType, true, typeContext, null, true, 4, null), true);
        }
        return coneKotlinTypeCreate == null ? coneFlexibleType : coneKotlinTypeCreate;
    }

    public static /* synthetic */ ConeClassLikeType fullyExpandedType$default(ConeClassLikeType coneClassLikeType, FirSession firSession, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = C00361.INSTANCE;
        }
        return fullyExpandedType(coneClassLikeType, firSession, (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) function1);
    }

    private static final ConeClassLikeType fullyExpandedTypeNoCache(ConeClassLikeType coneClassLikeType, FirSession firSession, Function1<? super FirTypeAlias, ? extends ConeClassLikeType> function1) {
        ConeClassLikeType coneClassLikeTypeDirectExpansionType = directExpansionType(coneClassLikeType, firSession, function1);
        return coneClassLikeTypeDirectExpansionType == null ? coneClassLikeType : (ConeClassLikeType) TypeUtilsKt.withAbbreviation(fullyExpandedType(coneClassLikeTypeDirectExpansionType, firSession, function1), new AbbreviatedTypeAttribute(coneClassLikeType));
    }

    public static final List<Pair<FirTypeParameterSymbol, ConeTypeProjection>> mapParametersToArgumentsOf(FirTypeAlias firTypeAlias, ConeKotlinType coneKotlinType) {
        firTypeAlias.getClass();
        coneKotlinType.getClass();
        List<FirTypeParameterRef> typeParameters = firTypeAlias.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameterRef) it.next()).getSymbol());
        }
        return CollectionsKt.zip(arrayList, coneKotlinType.getTypeArguments());
    }

    private static final ConeKotlinType mapTypeAliasArguments(FirTypeAlias firTypeAlias, ConeClassLikeType coneClassLikeType, ConeClassLikeType coneClassLikeType2, FirSession firSession) {
        return (firTypeAlias.getTypeParameters().isEmpty() || coneClassLikeType.getTypeArguments().length != 0) ? createParametersSubstitutor(firTypeAlias, coneClassLikeType, firSession).substituteOrSelf(coneClassLikeType2) : TypeConstructionUtilsKt.constructClassType$default(coneClassLikeType2.getLookupTag(), null, coneClassLikeType2.getIsMarkedNullable(), null, 5, null);
    }

    public static /* synthetic */ ConeKotlinType fullyExpandedType$default(ConeKotlinType coneKotlinType, FirSession firSession, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = AnonymousClass4.INSTANCE;
        }
        return fullyExpandedType(coneKotlinType, firSession, (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) function1);
    }

    public static /* synthetic */ ConeSimpleKotlinType fullyExpandedType$default(ConeSimpleKotlinType coneSimpleKotlinType, FirSession firSession, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = AnonymousClass8.INSTANCE;
        }
        return fullyExpandedType(coneSimpleKotlinType, firSession, (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) function1);
    }

    public static /* synthetic */ ConeRigidType fullyExpandedType$default(ConeRigidType coneRigidType, FirSession firSession, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = AnonymousClass11.INSTANCE;
        }
        return fullyExpandedType(coneRigidType, firSession, (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) function1);
    }

    public static final ConeSubstitutor createParametersSubstitutor(FirSession firSession, final Map<FirTypeParameterSymbol, ? extends ConeTypeProjection> map) {
        firSession.getClass();
        map.getClass();
        return new AbstractConeSubstitutor(TypeComponentsKt.getTypeContext(firSession)) { // from class: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt.createParametersSubstitutor.1

            /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt$createParametersSubstitutor$1$WhenMappings */
            @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ProjectionKind.values().length];
                    try {
                        iArr[ProjectionKind.STAR.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[ProjectionKind.IN.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[ProjectionKind.OUT.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[ProjectionKind.INVARIANT.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            private static final ConeTypeProjection substituteArgument$convertProjectionKindToConeTypeProjection(ConeKotlinType coneKotlinType, ProjectionKind projectionKind) {
                int i = WhenMappings.$EnumSwitchMapping$0[projectionKind.ordinal()];
                if (i == 1) {
                    return ConeStarProjection.INSTANCE;
                }
                if (i == 2) {
                    return new ConeKotlinTypeProjectionIn(coneKotlinType);
                }
                if (i == 3) {
                    return new ConeKotlinTypeProjectionOut(coneKotlinType);
                }
                if (i == 4) {
                    return coneKotlinType;
                }
                bu8.a();
                return null;
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
            @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor, org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
            public ConeTypeProjection substituteArgument(ConeTypeProjection projection, int index) throws KotlinIllegalArgumentExceptionWithAttachments {
                ConeKotlinType type;
                ConeTypeParameterLookupTag lookupTag;
                FirTypeParameterSymbol symbol;
                projection.getClass();
                ConeKotlinTypeProjection coneKotlinTypeProjection = projection instanceof ConeKotlinTypeProjection ? (ConeKotlinTypeProjection) projection : null;
                if (coneKotlinTypeProjection == null || (type = coneKotlinTypeProjection.getType()) == null) {
                    return null;
                }
                ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(type);
                ConeTypeParameterType coneTypeParameterType = coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound : null;
                if (coneTypeParameterType == null || (lookupTag = coneTypeParameterType.getLookupTag()) == null || (symbol = lookupTag.getSymbol()) == null) {
                    return super.substituteArgument(projection, index);
                }
                ConeKotlinTypeProjection coneKotlinTypeProjection2 = (ConeTypeProjection) map.get(symbol);
                if (coneKotlinTypeProjection2 == null) {
                    return super.substituteArgument(projection, index);
                }
                if (!(coneKotlinTypeProjection2 instanceof ConeKotlinTypeProjection)) {
                    return coneKotlinTypeProjection2;
                }
                ConeKotlinType coneKotlinTypeUpdateNullabilityIfNeeded = updateNullabilityIfNeeded(coneKotlinTypeProjection2.getType(), type);
                ConeKotlinType coneKotlinTypeWithAttributes = TypeUtilsKt.withAttributes(coneKotlinTypeUpdateNullabilityIfNeeded, type.getAttributes().add(coneKotlinTypeUpdateNullabilityIfNeeded.getAttributes()));
                if (coneKotlinTypeProjection2.getKind() == projection.getKind()) {
                    return substituteArgument$convertProjectionKindToConeTypeProjection(coneKotlinTypeWithAttributes, coneKotlinTypeProjection2.getKind());
                }
                ProjectionKind kind = coneKotlinTypeProjection2.getKind();
                ProjectionKind projectionKind = ProjectionKind.STAR;
                if (kind == projectionKind || projection.getKind() == projectionKind) {
                    return ConeStarProjection.INSTANCE;
                }
                ProjectionKind kind2 = coneKotlinTypeProjection2.getKind();
                ProjectionKind projectionKind2 = ProjectionKind.INVARIANT;
                if (kind2 == projectionKind2) {
                    return substituteArgument$convertProjectionKindToConeTypeProjection(coneKotlinTypeWithAttributes, projection.getKind());
                }
                return projection.getKind() == projectionKind2 ? substituteArgument$convertProjectionKindToConeTypeProjection(coneKotlinTypeWithAttributes, coneKotlinTypeProjection2.getKind()) : new ConeKotlinTypeConflictingProjection(coneKotlinTypeWithAttributes);
            }

            @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
            public ConeKotlinType substituteType(ConeKotlinType type) {
                type.getClass();
                return null;
            }
        };
    }

    public static final ConeClassLikeType fullyExpandedType(SessionHolder sessionHolder, ConeClassLikeType coneClassLikeType) {
        sessionHolder.getClass();
        coneClassLikeType.getClass();
        return fullyExpandedType(coneClassLikeType, sessionHolder.getSession(), (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) AnonymousClass2.INSTANCE);
    }

    @ExplicitlyPassedSession
    public static final ConeClassLikeType fullyExpandedType(SessionHolder sessionHolder, ConeClassLikeType coneClassLikeType, FirSession firSession) {
        sessionHolder.getClass();
        coneClassLikeType.getClass();
        firSession.getClass();
        return fullyExpandedType(coneClassLikeType, firSession, (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) AnonymousClass3.INSTANCE);
    }

    public static final ConeClassLikeType fullyExpandedType(ConeClassLikeType coneClassLikeType, FirSession firSession, Function1<? super FirTypeAlias, ? extends ConeClassLikeType> function1) {
        coneClassLikeType.getClass();
        firSession.getClass();
        function1.getClass();
        if (coneClassLikeType instanceof ConeClassLikeTypeImpl) {
            ConeClassLikeTypeImpl coneClassLikeTypeImpl = (ConeClassLikeTypeImpl) coneClassLikeType;
            WeakPair<?, ConeClassLikeType> cachedExpandedType = coneClassLikeTypeImpl.getCachedExpandedType();
            Object objComponent1 = WeakPairKt.component1(cachedExpandedType);
            ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) WeakPairKt.component2(cachedExpandedType);
            if (objComponent1 == firSession && coneClassLikeType2 != null) {
                return coneClassLikeType2;
            }
            ConeClassLikeType coneClassLikeTypeFullyExpandedTypeNoCache = fullyExpandedTypeNoCache(coneClassLikeType, firSession, function1);
            coneClassLikeTypeImpl.setCachedExpandedType(new WeakPair<>(firSession, coneClassLikeTypeFullyExpandedTypeNoCache));
            return coneClassLikeTypeFullyExpandedTypeNoCache;
        }
        return fullyExpandedTypeNoCache(coneClassLikeType, firSession, function1);
    }

    public static final ConeKotlinType fullyExpandedType(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return fullyExpandedType(coneKotlinType, sessionHolder.getSession(), AnonymousClass6.INSTANCE);
    }

    @ExplicitlyPassedSession
    public static final ConeKotlinType fullyExpandedType(SessionHolder sessionHolder, ConeKotlinType coneKotlinType, FirSession firSession) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        firSession.getClass();
        return fullyExpandedType(coneKotlinType, firSession, AnonymousClass7.INSTANCE);
    }

    public static final ConeSimpleKotlinType fullyExpandedType(ConeSimpleKotlinType coneSimpleKotlinType, FirSession firSession, Function1<? super FirTypeAlias, ? extends ConeClassLikeType> function1) {
        coneSimpleKotlinType.getClass();
        firSession.getClass();
        function1.getClass();
        return coneSimpleKotlinType instanceof ConeClassLikeType ? fullyExpandedType((ConeClassLikeType) coneSimpleKotlinType, firSession, function1) : coneSimpleKotlinType;
    }

    public static final ConeSimpleKotlinType fullyExpandedType(SessionHolder sessionHolder, ConeSimpleKotlinType coneSimpleKotlinType) {
        sessionHolder.getClass();
        coneSimpleKotlinType.getClass();
        return fullyExpandedType(coneSimpleKotlinType, sessionHolder.getSession(), (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) AnonymousClass9.INSTANCE);
    }

    @ExplicitlyPassedSession
    public static final ConeSimpleKotlinType fullyExpandedType(SessionHolder sessionHolder, ConeSimpleKotlinType coneSimpleKotlinType, FirSession firSession) {
        sessionHolder.getClass();
        coneSimpleKotlinType.getClass();
        firSession.getClass();
        return fullyExpandedType(coneSimpleKotlinType, firSession, (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) AnonymousClass10.INSTANCE);
    }

    public static final ConeRigidType fullyExpandedType(ConeRigidType coneRigidType, FirSession firSession, Function1<? super FirTypeAlias, ? extends ConeClassLikeType> function1) {
        coneRigidType.getClass();
        firSession.getClass();
        function1.getClass();
        if (coneRigidType instanceof ConeSimpleKotlinType) {
            return fullyExpandedType((ConeSimpleKotlinType) coneRigidType, firSession, function1);
        }
        if (coneRigidType instanceof ConeDefinitelyNotNullType) {
            return coneRigidType;
        }
        bu8.a();
        return null;
    }

    public static final ConeRigidType fullyExpandedType(SessionHolder sessionHolder, ConeRigidType coneRigidType) {
        sessionHolder.getClass();
        coneRigidType.getClass();
        return fullyExpandedType(coneRigidType, sessionHolder.getSession(), (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) AnonymousClass12.INSTANCE);
    }

    @Deprecated(message = "Use overload without parameter.", replaceWith = @ReplaceWith(expression = "fullyExpandedType()", imports = {}))
    public static final ConeRigidType fullyExpandedType(SessionHolder sessionHolder, ConeRigidType coneRigidType, FirSession firSession) {
        sessionHolder.getClass();
        coneRigidType.getClass();
        firSession.getClass();
        return fullyExpandedType(coneRigidType, firSession, (Function1<? super FirTypeAlias, ? extends ConeClassLikeType>) AnonymousClass13.INSTANCE);
    }
}

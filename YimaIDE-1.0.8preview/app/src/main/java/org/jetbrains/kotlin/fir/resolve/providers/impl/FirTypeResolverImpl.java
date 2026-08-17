package org.jetbrains.kotlin.fir.resolve.providers.impl;

import defpackage.dwe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirOuterClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.diagnostics.ConeAmbiguousFunctionTypeKinds;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnexpectedTypeArgumentsError;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.DoubleColonLHS;
import org.jetbrains.kotlin.fir.resolve.FirQualifierResolver;
import org.jetbrains.kotlin.fir.resolve.FirQualifierResolverKt;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.resolve.FirTypeResolutionResult;
import org.jetbrains.kotlin.fir.resolve.FirTypeResolver;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeSupplier;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeResolutionConfiguration;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeForbiddenIntersection;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeOuterClassArgumentsRequired;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeArgumentsForOuterClassWhenNestedReferencedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedTypeQualifierError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeWrongNumberOfTypeArgumentsError;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirTypeResolverImpl;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirPlaceholderProjection;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirStarProjection;
import org.jetbrains.kotlin.fir.types.FirTypeArgumentList;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J0\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J(\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J(\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001b2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J*\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e*\u00020\u00102\u0006\u0010 \u001a\u00020!2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J2\u0010\"\u001a\u00020#2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00072\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u0007H\u0002J.\u0010)\u001a\u0004\u0018\u00010**\b\u0012\u0004\u0012\u00020,0+2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u001b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J4\u0010.\u001a\u0004\u0018\u00010**\b\u0012\u0004\u0012\u00020,0+2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u001b2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010/\u001a\u0004\u0018\u000100H\u0002J\u0010\u00101\u001a\u0002022\u0006\u0010\u0013\u001a\u000203H\u0002J\f\u00104\u001a\u00020,*\u000205H\u0002J\u001a\u00106\u001a\u000207*\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010-\u001a\u000208H\u0002J0\u00109\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\r\u0012\u0006\u0012\u0004\u0018\u00010*0:2\u0006\u0010-\u001a\u0002082\n\u0010;\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0002J\u001a\u0010<\u001a\u0004\u0018\u0001002\u0006\u0010-\u001a\u0002082\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001a\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010-\u001a\u0002082\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J@\u0010?\u001a\u0002022\u0006\u0010\u0013\u001a\u00020@2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010A\u001a\u00020\u0007H\u0016JF\u0010B\u001a\u000207*\u00020\u00162\u0006\u0010C\u001a\u00020D2\"\u0010E\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030G\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u0002070F2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00070JH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeResolverImpl;", "Lorg/jetbrains/kotlin/fir/resolve/FirTypeResolver;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "aliasedTypeExpansionGloballyDisabled", Argument.Delimiters.none, "useProperResolutionOfCallableReferenceLHSs", "resolveSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "symbol", "remainingQualifier", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "qualifierResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirQualifierResolver;", "resolveUserTypeToSymbol", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirTypeCandidateCollector$TypeResolutionResult;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "configuration", "Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "resolveDeprecations", "resolveLocalClassChain", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "outermostClassLikeSymbol", "resolveEnumEntrySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "resolveUserType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "areBareTypesAllowed", "topContainer", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "isOperandOfIsOperator", "initExplicitTypeArguments", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "qualifier", "addImplicitTypeArguments", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "createFunctionType", "Lorg/jetbrains/kotlin/fir/resolve/FirTypeResolutionResult;", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "toConeTypeProjectionInLHS", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "addOwnTypeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "matchQualifierPartsAndClassesForLHS", "Lkotlin/Pair;", "classSymbol", "computeSubstitutorForLHS", "resolveTypeOnDoubleColonLHS", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Type;", "resolveType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "expandTypeAliases", "iterateScopesWithSubstitution", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "stopIf", "Lkotlin/Function0;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeResolverImpl extends FirTypeResolver {
    private final boolean aliasedTypeExpansionGloballyDisabled;
    private final FirSession session;
    private final boolean useProperResolutionOfCallableReferenceLHSs;

    public FirTypeResolverImpl(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.aliasedTypeExpansionGloballyDisabled = !((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getFlag(AnalysisFlags.getExpandTypeAliasesInTypeResolution())).booleanValue();
        this.useProperResolutionOfCallableReferenceLHSs = FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(LanguageFeature.ProperSupportOfInnerClassesInCallableReferenceLHS);
    }

    public static Unit a(Name name, Ref.ObjectRef objectRef, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        if ((firClassifierSymbol instanceof FirClassLikeSymbol) && Intrinsics.areEqual(((FirClassLikeSymbol) firClassifierSymbol).getName(), name)) {
            objectRef.element = firClassifierSymbol;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ConeDiagnostic addImplicitTypeArguments(List<ConeTypeProjection> list, FirClassLikeSymbol<?> firClassLikeSymbol, FirDeclaration firDeclaration, ConeSubstitutor coneSubstitutor) {
        FirTypeResolverImpl firTypeResolverImpl;
        List<ConeTypeProjection> list2;
        FirDeclaration firDeclaration2;
        ConeSubstitutor coneSubstitutor2;
        int size = list.size();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        int i = 0;
        for (FirTypeParameterRef firTypeParameterRef : ((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getTypeParameters()) {
            int i2 = i + 1;
            if (i < size) {
                firTypeResolverImpl = this;
                list2 = list;
                firDeclaration2 = firDeclaration;
                coneSubstitutor2 = coneSubstitutor;
            } else {
                firTypeResolverImpl = this;
                list2 = list;
                firDeclaration2 = firDeclaration;
                coneSubstitutor2 = coneSubstitutor;
                addImplicitTypeArguments$addImplicitTypeArgument(firDeclaration2, firTypeResolverImpl, coneSubstitutor2, list2, objectRef, firTypeParameterRef);
            }
            firDeclaration = firDeclaration2;
            this = firTypeResolverImpl;
            coneSubstitutor = coneSubstitutor2;
            list = list2;
            i = i2;
        }
        return (ConeDiagnostic) objectRef.element;
    }

    private static final void addImplicitTypeArguments$addImplicitTypeArgument(FirDeclaration firDeclaration, FirTypeResolverImpl firTypeResolverImpl, ConeSubstitutor coneSubstitutor, List<ConeTypeProjection> list, Ref.ObjectRef<ConeDiagnostic> objectRef, FirTypeParameterRef firTypeParameterRef) {
        if (!(firTypeParameterRef instanceof FirOuterClassTypeParameterRef) || DeclarationUtilsKt.isValidTypeParameterFromOuterDeclaration(((FirOuterClassTypeParameterRef) firTypeParameterRef).getSymbol(), firDeclaration, firTypeResolverImpl.session)) {
            ConeKotlinType coneKotlinTypeSubstituteOrNull = coneSubstitutor != null ? coneSubstitutor.substituteOrNull(ResolveUtilsKt.getDefaultType(firTypeParameterRef.getSymbol())) : null;
            if (coneKotlinTypeSubstituteOrNull != null) {
                list.add(coneKotlinTypeSubstituteOrNull);
                return;
            }
        }
        list.add(ConeStarProjection.INSTANCE);
        if (objectRef.element == null) {
            FirBasedSymbol<?> containingDeclarationSymbol = firTypeParameterRef.getSymbol().getContainingDeclarationSymbol();
            containingDeclarationSymbol.getClass();
            objectRef.element = new ConeOuterClassArgumentsRequired((FirClassLikeSymbol) containingDeclarationSymbol);
        }
    }

    private final void addOwnTypeArguments(List<ConeTypeProjection> list, FirResolvedQualifier firResolvedQualifier) {
        Iterator<FirTypeProjection> it = UtilsKt.getOwnTypeArguments(firResolvedQualifier).iterator();
        while (it.hasNext()) {
            list.add(toConeTypeProjectionInLHS(it.next()));
        }
    }

    public static boolean b(FirTypeCandidateCollector firTypeCandidateCollector) {
        return firTypeCandidateCollector.getApplicability() == CandidateApplicability.RESOLVED;
    }

    public static boolean c(Function3 function3, FirScope firScope, Function0 function0, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        function3.invoke(firClassifierSymbol, coneSubstitutor, firScope);
        return ((Boolean) function0.invoke()).booleanValue();
    }

    private final ConeSubstitutor computeSubstitutorForLHS(FirResolvedQualifier qualifier, TypeResolutionConfiguration configuration) {
        final FirClassLikeSymbol<?> symbol = FirExpressionUtilKt.firstQualifierPart(qualifier).getSymbol();
        if (symbol == null) {
            return null;
        }
        Name name = symbol.getName();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        iterateScopesWithSubstitution(configuration, name, new Function3() { // from class: vf5
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return FirTypeResolverImpl.f(symbol, objectRef, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2, (FirScope) obj3);
            }
        }, new Function0() { // from class: wf5
            public final Object invoke() {
                return Boolean.valueOf(FirTypeResolverImpl.g(objectRef));
            }
        });
        return (ConeSubstitutor) objectRef.element;
    }

    private final FirTypeResolutionResult createFunctionType(FirFunctionTypeRef typeRef) {
        FunctionTypeKind.Function function;
        List contextParameterTypeRefs = typeRef.getContextParameterTypeRefs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameterTypeRefs, 10));
        Iterator it = contextParameterTypeRefs.iterator();
        while (it.hasNext()) {
            arrayList.add(FirTypeUtilsKt.getConeType((FirTypeRef) it.next()));
        }
        FirTypeRef receiverTypeRef = typeRef.getReceiverTypeRef();
        ConeAmbiguousFunctionTypeKinds coneAmbiguousFunctionTypeKinds = null;
        List listPlus = CollectionsKt.plus(arrayList, CollectionsKt.listOfNotNull(receiverTypeRef != null ? FirTypeUtilsKt.getConeType(receiverTypeRef) : null));
        List<FirFunctionTypeParameter> parameters = typeRef.getParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        for (FirFunctionTypeParameter firFunctionTypeParameter : parameters) {
            arrayList2.add(LookupTagUtilsKt.withParameterNameAnnotation(FirTypeUtilsKt.getConeType(firFunctionTypeParameter.getReturnTypeRef()), firFunctionTypeParameter));
        }
        List listPlus2 = CollectionsKt.plus(CollectionsKt.plus(listPlus, arrayList2), CollectionsKt.listOf(FirTypeUtilsKt.getConeType(typeRef.getReturnTypeRef())));
        List<FunctionTypeKind> listExtractAllSpecialKindsForFunctionTypeRef = FirFunctionTypeKindServiceKt.getFunctionTypeService(this.session).extractAllSpecialKindsForFunctionTypeRef(typeRef);
        int size = listExtractAllSpecialKindsForFunctionTypeRef.size();
        if (size == 0) {
            function = FunctionTypeKind.Function.INSTANCE;
        } else if (size != 1) {
            coneAmbiguousFunctionTypeKinds = new ConeAmbiguousFunctionTypeKinds(listExtractAllSpecialKindsForFunctionTypeRef);
            function = FunctionTypeKind.Function.INSTANCE;
        } else {
            function = (FunctionTypeKind) CollectionsKt.single(listExtractAllSpecialKindsForFunctionTypeRef);
        }
        ConeAmbiguousFunctionTypeKinds coneAmbiguousFunctionTypeKinds2 = coneAmbiguousFunctionTypeKinds;
        ClassId classIdNumberedClassId = function.numberedClassId(FirTypeUtilsKt.getParametersCount(typeRef));
        List annotations = typeRef.getAnnotations();
        FirSession firSession = this.session;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        if (typeRef.getReceiverTypeRef() != null) {
            listCreateListBuilder.add(CompilerConeAttributes.ExtensionFunctionType.INSTANCE);
        }
        if (!typeRef.getContextParameterTypeRefs().isEmpty()) {
            listCreateListBuilder.add(new CompilerConeAttributes.ContextFunctionTypeParams(typeRef.getContextParameterTypeRefs().size()));
        }
        Unit unit = Unit.INSTANCE;
        return new FirTypeResolutionResult(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(classIdNumberedClassId), (ConeTypeProjection[]) listPlus2.toArray(new ConeKotlinType[0]), typeRef.isMarkedNullable(), CopyUtilsKt.computeTypeAttributes$default(annotations, firSession, CollectionsKt.build(listCreateListBuilder), false, true, 4, null)), coneAmbiguousFunctionTypeKinds2, null, 4, null);
    }

    public static Unit d(FirTypeResolverImpl firTypeResolverImpl, List list, FirQualifierResolver firQualifierResolver, FirTypeCandidateCollector firTypeCandidateCollector, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor, FirScope firScope) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        firScope.getClass();
        FirBasedSymbol<?> firBasedSymbolResolveSymbol = firTypeResolverImpl.resolveSymbol(firClassifierSymbol, list.subList(1, list.size()), firQualifierResolver);
        if (firBasedSymbolResolveSymbol != null) {
            firTypeCandidateCollector.processCandidate(firBasedSymbolResolveSymbol, coneSubstitutor, ResolveUtilsKt.toResolvedSymbolOrigin(firScope));
        }
        return Unit.INSTANCE;
    }

    public static Unit e(Function3 function3, FirScope firScope, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        function3.invoke(firClassifierSymbol, coneSubstitutor, firScope);
        return Unit.INSTANCE;
    }

    public static Unit f(FirClassLikeSymbol firClassLikeSymbol, Ref.ObjectRef objectRef, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor, FirScope firScope) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        firScope.getClass();
        if (Intrinsics.areEqual(firClassifierSymbol, firClassLikeSymbol)) {
            objectRef.element = coneSubstitutor;
        }
        return Unit.INSTANCE;
    }

    public static boolean g(Ref.ObjectRef objectRef) {
        return objectRef.element != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ConeDiagnostic initExplicitTypeArguments(List<ConeTypeProjection> list, FirClassLikeSymbol<?> firClassLikeSymbol, List<? extends FirQualifierPart> list2) {
        FirClassLikeDeclaration containingDeclaration = (FirClassLikeDeclaration) firClassLikeSymbol.getFir();
        boolean zIsInner = true;
        for (FirQualifierPart firQualifierPart : CollectionsKt.asReversed(list2)) {
            FirTypeArgumentList typeArgumentList = firQualifierPart.getTypeArgumentList();
            int size = typeArgumentList.getTypeArguments().size();
            if (containingDeclaration == null) {
                return null;
            }
            List<FirTypeParameterRef> typeParameters = containingDeclaration.getTypeParameters();
            int i = 0;
            if (!(typeParameters instanceof Collection) || !typeParameters.isEmpty()) {
                Iterator<T> it = typeParameters.iterator();
                while (it.hasNext()) {
                    if (!(((FirTypeParameterRef) it.next()) instanceof FirOuterClassTypeParameterRef) && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            int i2 = i;
            if (zIsInner) {
                if (i2 != size) {
                    KtSourceElement source = size == 0 ? firQualifierPart.getSource() : typeArgumentList.getSource();
                    FirClassLikeSymbol<FirClassLikeDeclaration> symbol = containingDeclaration.getSymbol();
                    source.getClass();
                    return new ConeWrongNumberOfTypeArgumentsError(i2, symbol, source, false, 8, null);
                }
            } else if (size > 0) {
                KtSourceElement source2 = typeArgumentList.getSource();
                source2.getClass();
                return new ConeTypeArgumentsForOuterClassWhenNestedReferencedError(source2);
            }
            List<FirTypeProjection> typeArguments = typeArgumentList.getTypeArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeArguments, 10));
            Iterator<T> it2 = typeArguments.iterator();
            while (it2.hasNext()) {
                arrayList.add(FirTypeUtilsKt.toConeTypeProjection((FirTypeProjection) it2.next()));
            }
            list.addAll(arrayList);
            zIsInner = containingDeclaration.getStatus().isInner();
            containingDeclaration = DeclarationUtilsKt.getContainingDeclaration(containingDeclaration, this.session);
        }
        return null;
    }

    private final void iterateScopesWithSubstitution(TypeResolutionConfiguration typeResolutionConfiguration, Name name, final Function3<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, ? super FirScope, Unit> function3, final Function0<Boolean> function0) {
        for (final FirScope firScope : typeResolutionConfiguration.getScopes()) {
            if (((Boolean) function0.invoke()).booleanValue()) {
                return;
            }
            if (firScope instanceof FirDefaultStarImportingScope) {
                ((FirDefaultStarImportingScope) firScope).processClassifiersByNameWithSubstitutionFromBothLevelsConditionally(name, new Function2() { // from class: qf5
                    public final Object invoke(Object obj, Object obj2) {
                        return Boolean.valueOf(FirTypeResolverImpl.c(function3, firScope, function0, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2));
                    }
                });
            } else {
                firScope.processClassifiersByNameWithSubstitution(name, new Function2() { // from class: rf5
                    public final Object invoke(Object obj, Object obj2) {
                        return FirTypeResolverImpl.e(function3, firScope, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2);
                    }
                });
            }
        }
    }

    private final Pair<List<ConeTypeProjection>, ConeDiagnostic> matchQualifierPartsAndClassesForLHS(FirResolvedQualifier qualifier, FirClassLikeSymbol<?> classSymbol) {
        List<ConeTypeProjection> listCreateListBuilder = CollectionsKt.createListBuilder();
        ConeWrongNumberOfTypeArgumentsError coneWrongNumberOfTypeArgumentsError = null;
        while (true) {
            if (UtilsKt.getOwnTypeArguments(qualifier).size() != classSymbol.getOwnTypeParameterSymbols().size()) {
                int size = classSymbol.getOwnTypeParameterSymbols().size();
                KtSourceElement source = qualifier.getSource();
                source.getClass();
                coneWrongNumberOfTypeArgumentsError = new ConeWrongNumberOfTypeArgumentsError(size, classSymbol, source, true);
            }
            addOwnTypeArguments(listCreateListBuilder, qualifier);
            FirResolvedQualifier explicitParent = qualifier.getExplicitParent();
            FirClassLikeSymbol<?> symbol = explicitParent != null ? explicitParent.getSymbol() : null;
            if (symbol == null || !classSymbol.getRawStatus().isInner()) {
                break;
            }
            qualifier = qualifier.getExplicitParent();
            qualifier.getClass();
            classSymbol = symbol;
        }
        return TuplesKt.to(CollectionsKt.build(listCreateListBuilder), coneWrongNumberOfTypeArgumentsError);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final FirVariableSymbol<FirEnumEntry> resolveEnumEntrySymbol(FirQualifierResolver firQualifierResolver, ClassId classId, List<? extends FirQualifierPart> list) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object next;
        FirClassifierSymbol<?> firClassifierSymbolResolveSymbolWithPrefix = firQualifierResolver.resolveSymbolWithPrefix(classId, CollectionsKt.dropLast(list, 1));
        if (firClassifierSymbolResolveSymbolWithPrefix == null) {
            return null;
        }
        Object fir = firClassifierSymbolResolveSymbolWithPrefix.getFir();
        FirRegularClass firRegularClass = fir instanceof FirRegularClass ? (FirRegularClass) fir : null;
        if (firRegularClass != null && firRegularClass.getClassKind() == ClassKind.ENUM_CLASS) {
            Iterator<T> it = firRegularClass.getDeclarations().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                FirDeclaration firDeclaration = (FirDeclaration) next;
                if ((firDeclaration instanceof FirEnumEntry) && Intrinsics.areEqual(((FirEnumEntry) firDeclaration).getName(), ((FirQualifierPart) CollectionsKt.last(list)).getName())) {
                    break;
                }
            }
            FirEnumEntry firEnumEntry = next instanceof FirEnumEntry ? (FirEnumEntry) next : null;
            if (firEnumEntry != null) {
                return firEnumEntry.getSymbol();
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirClassLikeSymbol<?> resolveLocalClassChain(FirClassLikeSymbol<?> outermostClassLikeSymbol, List<? extends FirQualifierPart> remainingQualifier) {
        if ((outermostClassLikeSymbol instanceof FirRegularClassSymbol) && ((FirClassLikeDeclaration) outermostClassLikeSymbol.getFir()).getIsLocal()) {
            return resolveLocalClassChain$resolveLocalClassChain(remainingQualifier, this, outermostClassLikeSymbol, 0);
        }
        return null;
    }

    private static final FirClassLikeSymbol<?> resolveLocalClassChain$resolveLocalClassChain(List<? extends FirQualifierPart> list, FirTypeResolverImpl firTypeResolverImpl, FirClassLikeSymbol<?> firClassLikeSymbol, int i) {
        if (i == list.size()) {
            return firClassLikeSymbol;
        }
        if (!(firClassLikeSymbol instanceof FirRegularClassSymbol)) {
            return null;
        }
        final Name name = list.get(i).getName();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FirContainingNamesAwareScopeKt.processAllClassifiers(FirDeclaredMemberScopeProviderKt.declaredMemberScope((FirClassSymbol<?>) firClassLikeSymbol, firTypeResolverImpl.session, (FirResolvePhase) null), new Function1() { // from class: uf5
            public final Object invoke(Object obj) {
                return FirTypeResolverImpl.a(name, objectRef, (FirClassifierSymbol) obj);
            }
        });
        Object obj = objectRef.element;
        if (obj != null) {
            return resolveLocalClassChain$resolveLocalClassChain(list, firTypeResolverImpl, (FirClassLikeSymbol) obj, i + 1);
        }
        return null;
    }

    private final FirBasedSymbol<?> resolveSymbol(FirBasedSymbol<?> symbol, List<? extends FirQualifierPart> remainingQualifier, FirQualifierResolver qualifierResolver) {
        if (symbol instanceof FirClassLikeSymbol) {
            if (!remainingQualifier.isEmpty()) {
                FirClassLikeSymbol<?> firClassLikeSymbol = (FirClassLikeSymbol) symbol;
                FirClassLikeSymbol<?> firClassLikeSymbolResolveLocalClassChain = resolveLocalClassChain(firClassLikeSymbol, remainingQualifier);
                if (firClassLikeSymbolResolveLocalClassChain != null) {
                    return firClassLikeSymbolResolveLocalClassChain;
                }
                FirClassifierSymbol<?> firClassifierSymbolResolveSymbolWithPrefix = qualifierResolver.resolveSymbolWithPrefix(firClassLikeSymbol.getClassId(), remainingQualifier);
                return firClassifierSymbolResolveSymbolWithPrefix != null ? firClassifierSymbolResolveSymbolWithPrefix : resolveEnumEntrySymbol(qualifierResolver, firClassLikeSymbol.getClassId(), remainingQualifier);
            }
        } else {
            if (!(symbol instanceof FirTypeParameterSymbol)) {
                k2d.a("!");
                return null;
            }
            if (!remainingQualifier.isEmpty()) {
                return null;
            }
        }
        return symbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ConeKotlinType resolveUserType(FirUserTypeRef typeRef, FirTypeCandidateCollector.TypeResolutionResult result, boolean areBareTypesAllowed, FirDeclaration topContainer, boolean isOperandOfIsOperator) {
        Pair pair;
        Object[] objArr;
        ConeDiagnostic coneUnresolvedTypeQualifierError;
        if (result instanceof FirTypeCandidateCollector.TypeResolutionResult.Resolved) {
            FirTypeCandidateCollector.TypeResolutionResult.Resolved resolved = (FirTypeCandidateCollector.TypeResolutionResult.Resolved) result;
            pair = TuplesKt.to(resolved.getTypeCandidate().getSymbol(), resolved.getTypeCandidate().getSubstitutor());
        } else {
            if (!(result instanceof FirTypeCandidateCollector.TypeResolutionResult.Ambiguity) && !Intrinsics.areEqual(result, FirTypeCandidateCollector.TypeResolutionResult.Unresolved.INSTANCE)) {
                bu8.a();
                return null;
            }
            pair = TuplesKt.to(null, null);
        }
        FirBasedSymbol firBasedSymbol = (FirBasedSymbol) pair.component1();
        ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pair.component2();
        List<ConeTypeProjection> listCreateListBuilder = CollectionsKt.createListBuilder();
        List<? extends FirQualifierPart> qualifier = typeRef.getQualifier();
        if (areBareTypesAllowed) {
            List<? extends FirQualifierPart> list = qualifier;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!((FirQualifierPart) it.next()).getTypeArgumentList().getTypeArguments().isEmpty()) {
                            objArr = false;
                        }
                    }
                }
            }
            objArr = true;
        } else {
            objArr = false;
        }
        if (!(firBasedSymbol instanceof FirClassLikeSymbol) || objArr == true) {
            Iterator it2 = CollectionsKt.asReversed(qualifier).iterator();
            while (it2.hasNext()) {
                List<FirTypeProjection> typeArguments = ((FirQualifierPart) it2.next()).getTypeArgumentList().getTypeArguments();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeArguments, 10));
                Iterator<T> it3 = typeArguments.iterator();
                while (it3.hasNext()) {
                    arrayList.add(FirTypeUtilsKt.toConeTypeProjection((FirTypeProjection) it3.next()));
                }
                listCreateListBuilder.addAll(arrayList);
            }
        } else {
            FirClassLikeSymbol<?> firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol;
            ConeDiagnostic coneDiagnosticInitExplicitTypeArguments = initExplicitTypeArguments(listCreateListBuilder, firClassLikeSymbol, qualifier);
            if (coneDiagnosticInitExplicitTypeArguments != null) {
                return new ConeErrorType(coneDiagnosticInitExplicitTypeArguments, false, null, null, null, null, null, 126, null);
            }
            ConeDiagnostic coneDiagnosticAddImplicitTypeArguments = addImplicitTypeArguments(listCreateListBuilder, firClassLikeSymbol, topContainer, coneSubstitutor);
            if (coneDiagnosticAddImplicitTypeArguments != null) {
                return new ConeErrorType(coneDiagnosticAddImplicitTypeArguments, false, null, null, null, null, null, 126, null);
            }
        }
        ConeTypeProjection[] coneTypeProjectionArr = (ConeTypeProjection[]) CollectionsKt.build(listCreateListBuilder).toArray(new ConeTypeProjection[0]);
        if (firBasedSymbol == null || !(firBasedSymbol instanceof FirClassifierSymbol)) {
            if ((firBasedSymbol != null ? firBasedSymbol.getFir() : null) instanceof FirEnumEntry) {
                coneUnresolvedTypeQualifierError = isOperandOfIsOperator ? new ConeSimpleDiagnostic("'is' operator can not be applied to an enum entry.", DiagnosticKind.IsEnumEntry) : new ConeSimpleDiagnostic("An enum entry should not be used as a type.", DiagnosticKind.EnumEntryAsType);
            } else if (result instanceof FirTypeCandidateCollector.TypeResolutionResult.Ambiguity) {
                Name name = ((FirQualifierPart) CollectionsKt.last(typeRef.getQualifier())).getName();
                FirTypeCandidateCollector.TypeResolutionResult.Ambiguity ambiguity = (FirTypeCandidateCollector.TypeResolutionResult.Ambiguity) result;
                CandidateApplicability applicability = ((FirTypeCandidateCollector.TypeCandidate) CollectionsKt.first(ambiguity.getTypeCandidates())).getApplicability();
                List<FirTypeCandidateCollector.TypeCandidate> typeCandidates = ambiguity.getTypeCandidates();
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(typeCandidates, 10)), 16));
                for (Object obj : typeCandidates) {
                    linkedHashMap.put(obj, ((FirTypeCandidateCollector.TypeCandidate) obj).getDiagnostic());
                }
                coneUnresolvedTypeQualifierError = new ConeAmbiguityError(name, applicability, linkedHashMap);
            } else {
                coneUnresolvedTypeQualifierError = new ConeUnresolvedTypeQualifierError(typeRef.getQualifier());
            }
            return new ConeErrorType(coneUnresolvedTypeQualifierError, false, null, coneTypeProjectionArr, CopyUtilsKt.computeTypeAttributes$default(typeRef.getAnnotations(), this.session, null, false, true, 6, null), Boolean.valueOf(typeRef.isMarkedNullable()), null, 70, null);
        }
        if (firBasedSymbol instanceof FirTypeParameterSymbol) {
            for (FirQualifierPart firQualifierPart : typeRef.getQualifier()) {
                if (!firQualifierPart.getTypeArgumentList().getTypeArguments().isEmpty()) {
                    return new ConeErrorType(new ConeUnexpectedTypeArgumentsError("Type arguments not allowed for type parameters", firQualifierPart.getTypeArgumentList().getSource()), false, null, coneTypeProjectionArr, null, null, null, 118, null);
                }
            }
        }
        FirClassifierSymbol firClassifierSymbol = (FirClassifierSymbol) firBasedSymbol;
        boolean zIsMarkedNullable = typeRef.isMarkedNullable();
        List annotations = typeRef.getAnnotations();
        FirSession firSession = this.session;
        ConeClassifierLookupTag lookupTag = firClassifierSymbol.getLookupTag();
        ConeClassLikeLookupTag coneClassLikeLookupTag = lookupTag instanceof ConeClassLikeLookupTag ? (ConeClassLikeLookupTag) lookupTag : null;
        ConeLookupTagBasedType coneLookupTagBasedTypeConstructType = TypeConstructionUtilsKt.constructType((FirClassifierSymbol<?>) firClassifierSymbol, coneTypeProjectionArr, zIsMarkedNullable, CopyUtilsKt.computeTypeAttributes$default(annotations, firSession, null, coneClassLikeLookupTag != null && FunctionalTypeUtilsKt.isSomeFunctionType(coneClassLikeLookupTag, this.session), true, 2, null));
        ConeClassifierLookupTag lookupTag2 = coneLookupTagBasedTypeConstructType.getLookupTag();
        if ((lookupTag2 instanceof ConeClassLikeLookupTagImpl) && (firBasedSymbol instanceof FirClassLikeSymbol)) {
            LookupTagUtilsKt.bindSymbolToLookupTag((ConeClassLikeLookupTagImpl) lookupTag2, this.session, (FirClassLikeSymbol) firBasedSymbol);
        }
        return coneLookupTagBasedTypeConstructType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirTypeCandidateCollector.TypeResolutionResult resolveUserTypeToSymbol(FirUserTypeRef typeRef, TypeResolutionConfiguration configuration, SupertypeSupplier supertypeSupplier, boolean resolveDeprecations) {
        Pair<FirClassifierSymbol<?>, FirResolvedSymbolOrigin> pairResolveFullyQualifiedSymbol;
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(this.session);
        if (lookupTracker != null) {
            Iterable<FirScope> scopes = configuration.getScopes();
            ArrayList arrayList = new ArrayList();
            Iterator<FirScope> it = scopes.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, it.next().getScopeOwnerLookupNames());
            }
            FirFile useSiteFile = configuration.getUseSiteFile();
            FirLookupTrackerComponentKt.recordUserTypeRefLookup(lookupTracker, typeRef, arrayList, useSiteFile != null ? useSiteFile.getSource() : null);
        }
        final List<? extends FirQualifierPart> qualifier = typeRef.getQualifier();
        final FirQualifierResolver qualifierResolver = FirQualifierResolverKt.getQualifierResolver(this.session);
        final FirTypeCandidateCollector firTypeCandidateCollector = new FirTypeCandidateCollector(this.session, configuration.getUseSiteFile(), configuration.getContainingClassDeclarations(), supertypeSupplier, resolveDeprecations);
        if (configuration.getSealedClassForContextSensitiveResolution() == null) {
            iterateScopesWithSubstitution(configuration, ((FirQualifierPart) CollectionsKt.first(qualifier)).getName(), new Function3() { // from class: sf5
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return FirTypeResolverImpl.d(this.b, qualifier, qualifierResolver, firTypeCandidateCollector, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2, (FirScope) obj3);
                }
            }, new Function0() { // from class: tf5
                public final Object invoke() {
                    return Boolean.valueOf(FirTypeResolverImpl.b(firTypeCandidateCollector));
                }
            });
            if (firTypeCandidateCollector.getApplicability() != CandidateApplicability.RESOLVED && (pairResolveFullyQualifiedSymbol = qualifierResolver.resolveFullyQualifiedSymbol(qualifier)) != null) {
                firTypeCandidateCollector.processCandidate((FirClassifierSymbol) pairResolveFullyQualifiedSymbol.component1(), null, (FirResolvedSymbolOrigin) pairResolveFullyQualifiedSymbol.component2());
            }
            return firTypeCandidateCollector.getResult();
        }
        FirBasedSymbol<?> firBasedSymbolResolveSymbol = resolveSymbol(configuration.getSealedClassForContextSensitiveResolution(), qualifier, qualifierResolver);
        if (firBasedSymbolResolveSymbol instanceof FirRegularClassSymbol) {
            FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) firBasedSymbolResolveSymbol;
            if (!(CollectionsKt.firstOrNull(((FirRegularClass) firRegularClassSymbol.getFir()).getTypeParameters()) instanceof FirOuterClassTypeParameterRef) && SupertypeUtilsKt.isSubclassOf$default((FirClass) firRegularClassSymbol.getFir(), configuration.getSealedClassForContextSensitiveResolution().getLookupTag(), this.session, true, null, false, 24, null)) {
                firTypeCandidateCollector.processCandidate(firBasedSymbolResolveSymbol, ConeSubstitutor.Empty.INSTANCE, FirResolvedSymbolOrigin.ContextSensitive);
            }
        }
        if (!configuration.getScopes().iterator().hasNext()) {
            return firTypeCandidateCollector.getResult();
        }
        k2d.a("Check failed.");
        return null;
    }

    private final ConeTypeProjection toConeTypeProjectionInLHS(FirTypeProjection firTypeProjection) {
        if (firTypeProjection instanceof FirTypeProjectionWithVariance) {
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = (FirTypeProjectionWithVariance) firTypeProjection;
            return ConeTypeUtilsKt.toTypeProjection(FirTypeUtilsKt.getConeType(firTypeProjectionWithVariance.getTypeRef()), firTypeProjectionWithVariance.getVariance());
        }
        if ((firTypeProjection instanceof FirPlaceholderProjection) || (firTypeProjection instanceof FirStarProjection)) {
            return ConeStarProjection.INSTANCE;
        }
        bu8.a();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.FirTypeResolver
    public FirTypeResolutionResult resolveType(FirTypeRef typeRef, TypeResolutionConfiguration configuration, boolean areBareTypesAllowed, boolean isOperandOfIsOperator, boolean resolveDeprecations, SupertypeSupplier supertypeSupplier, boolean expandTypeAliases) {
        TypeResolutionConfiguration typeResolutionConfiguration;
        FirTypeResolutionResult firTypeResolutionResult;
        FirTypeResolutionResult firTypeResolutionResult2;
        FirModuleData moduleData;
        FirSession session;
        typeRef.getClass();
        configuration.getClass();
        supertypeSupplier.getClass();
        if (typeRef instanceof FirResolvedTypeRef) {
            k2d.a("Do not resolve, resolved type-refs");
            return null;
        }
        if (typeRef instanceof FirUserTypeRef) {
            FirUserTypeRef firUserTypeRef = (FirUserTypeRef) typeRef;
            typeResolutionConfiguration = configuration;
            FirTypeCandidateCollector.TypeResolutionResult typeResolutionResultResolveUserTypeToSymbol = resolveUserTypeToSymbol(firUserTypeRef, typeResolutionConfiguration, supertypeSupplier, resolveDeprecations);
            FirDeclaration topContainer = typeResolutionConfiguration.getTopContainer();
            if (topContainer == null) {
                topContainer = (FirDeclaration) CollectionsKt.lastOrNull(typeResolutionConfiguration.getContainingClassDeclarations());
            }
            ConeKotlinType coneKotlinTypeResolveUserType = resolveUserType(firUserTypeRef, typeResolutionResultResolveUserTypeToSymbol, areBareTypesAllowed, topContainer, isOperandOfIsOperator);
            FirTypeCandidateCollector.TypeCandidate typeCandidateResolvedCandidateOrNull = typeResolutionResultResolveUserTypeToSymbol.resolvedCandidateOrNull();
            FirBasedSymbol<?> symbol = typeCandidateResolvedCandidateOrNull != null ? typeCandidateResolvedCandidateOrNull.getSymbol() : null;
            boolean z = ((symbol == null || (moduleData = symbol.getModuleData()) == null || (session = moduleData.getSession()) == null) ? null : session.getKind()) == FirSession.Kind.Library;
            if (!this.aliasedTypeExpansionGloballyDisabled) {
                if (z && (symbol instanceof FirTypeAliasSymbol)) {
                    coneKotlinTypeResolveUserType = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinTypeResolveUserType, ((FirTypeAliasSymbol) symbol).getModuleData().getSession(), (Function1) null, 2, (Object) null);
                } else if (expandTypeAliases && (symbol instanceof FirTypeAliasSymbol)) {
                    coneKotlinTypeResolveUserType = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinTypeResolveUserType, this.session, (Function1) null, 2, (Object) null);
                }
            }
            firTypeResolutionResult = new FirTypeResolutionResult(coneKotlinTypeResolveUserType, typeCandidateResolvedCandidateOrNull != null ? typeCandidateResolvedCandidateOrNull.getDiagnostic() : null, typeCandidateResolvedCandidateOrNull != null ? typeCandidateResolvedCandidateOrNull.getResolvedSymbolOrigin() : null);
        } else {
            typeResolutionConfiguration = configuration;
            if (typeRef instanceof FirFunctionTypeRef) {
                firTypeResolutionResult = createFunctionType((FirFunctionTypeRef) typeRef);
            } else {
                if (typeRef instanceof FirDynamicTypeRef) {
                    firTypeResolutionResult2 = new FirTypeResolutionResult(TypeUtilsKt.create(ConeDynamicType.Companion, this.session, CopyUtilsKt.computeTypeAttributes$default(((FirDynamicTypeRef) typeRef).getAnnotations(), this.session, null, false, true, 6, null)), null, null, 4, null);
                } else {
                    if (!(typeRef instanceof FirIntersectionTypeRef)) {
                        dwe.a(UtilsKt.render(typeRef));
                        return null;
                    }
                    ConeKotlinType coneType = FirTypeUtilsKt.getConeType(((FirIntersectionTypeRef) typeRef).getLeftType());
                    if (coneType instanceof ConeTypeParameterType) {
                        firTypeResolutionResult2 = new FirTypeResolutionResult(new ConeDefinitelyNotNullType((ConeSimpleKotlinType) coneType), null, null, 4, null);
                    } else {
                        firTypeResolutionResult = new FirTypeResolutionResult(new ConeErrorType(ConeForbiddenIntersection.INSTANCE, false, null, null, null, null, null, 126, null), null, null, 4, null);
                    }
                }
                firTypeResolutionResult = firTypeResolutionResult2;
            }
        }
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(this.session);
        if (lookupTracker != null) {
            ConeKotlinType type = firTypeResolutionResult.getType();
            KtSourceElement source = ((FirUnresolvedTypeRef) typeRef).getSource();
            FirFile useSiteFile = typeResolutionConfiguration.getUseSiteFile();
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, type, source, useSiteFile != null ? useSiteFile.getSource() : null);
        }
        return firTypeResolutionResult;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00a0 A[PHI: r0
      0x00a0: PHI (r0v3 org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic) = (r0v1 org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic), (r0v6 org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic) binds: [B:41:0x00df, B:32:0x009e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.FirTypeResolver
    public DoubleColonLHS.Type resolveTypeOnDoubleColonLHS(FirResolvedQualifier qualifier, TypeResolutionConfiguration configuration) {
        ConeDiagnostic coneWrongNumberOfTypeArgumentsError;
        ConeDiagnostic coneDiagnosticAddImplicitTypeArguments;
        ConeTypeProjection defaultType;
        qualifier.getClass();
        configuration.getClass();
        FirClassLikeSymbol<?> symbol = qualifier.getSymbol();
        if (symbol == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (this.useProperResolutionOfCallableReferenceLHSs) {
            Pair<List<ConeTypeProjection>, ConeDiagnostic> pairMatchQualifierPartsAndClassesForLHS = matchQualifierPartsAndClassesForLHS(qualifier, symbol);
            List list = (List) pairMatchQualifierPartsAndClassesForLHS.component1();
            coneWrongNumberOfTypeArgumentsError = (ConeDiagnostic) pairMatchQualifierPartsAndClassesForLHS.component2();
            arrayList.addAll(list);
            if (arrayList.size() != symbol.getTypeParameterSymbols().size()) {
                ConeSubstitutor coneSubstitutorComputeSubstitutorForLHS = computeSubstitutorForLHS(qualifier, configuration);
                FirDeclaration topContainer = configuration.getTopContainer();
                if (topContainer == null) {
                    topContainer = (FirDeclaration) CollectionsKt.lastOrNull(configuration.getContainingClassDeclarations());
                }
                coneDiagnosticAddImplicitTypeArguments = addImplicitTypeArguments(arrayList, symbol, topContainer, coneSubstitutorComputeSubstitutorForLHS);
                if (coneDiagnosticAddImplicitTypeArguments != null && coneWrongNumberOfTypeArgumentsError == null) {
                    coneWrongNumberOfTypeArgumentsError = coneDiagnosticAddImplicitTypeArguments;
                }
            }
        } else {
            int size = DeclarationUtilsKt.getClassTypeParameterSymbols(symbol).size();
            if (qualifier.getTypeArguments().size() != size) {
                KtSourceElement source = qualifier.getSource();
                source.getClass();
                coneWrongNumberOfTypeArgumentsError = new ConeWrongNumberOfTypeArgumentsError(size, symbol, source, false, 8, null);
            } else {
                coneWrongNumberOfTypeArgumentsError = null;
            }
            int i = 0;
            for (Object obj : symbol.getTypeParameterSymbols()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) obj;
                FirTypeProjection firTypeProjection = (FirTypeProjection) CollectionsKt.getOrNull(qualifier.getTypeArguments(), i);
                if (firTypeProjection == null || (defaultType = toConeTypeProjectionInLHS(firTypeProjection)) == null) {
                    defaultType = ResolveUtilsKt.getDefaultType(firTypeParameterSymbol);
                    if (!((FirClassLikeDeclaration) symbol.getFir()).getIsLocal() || (firTypeParameterSymbol.getContainingDeclarationSymbol() instanceof FirClassLikeSymbol)) {
                        defaultType = null;
                    }
                    if (defaultType == null) {
                        defaultType = ConeStarProjection.INSTANCE;
                    }
                }
                arrayList.add(defaultType);
                i = i2;
            }
            coneDiagnosticAddImplicitTypeArguments = (ConeDiagnostic) matchQualifierPartsAndClassesForLHS(qualifier, symbol).component2();
            if (coneWrongNumberOfTypeArgumentsError == null) {
                coneWrongNumberOfTypeArgumentsError = coneDiagnosticAddImplicitTypeArguments;
            }
        }
        return new DoubleColonLHS.Type(new ConeClassLikeTypeImpl(symbol.getLookupTag(), (ConeTypeProjection[]) CollectionsKt.take(arrayList, symbol.getTypeParameterSymbols().size()).toArray(new ConeTypeProjection[0]), qualifier.getIsNullableLHSForCallableReference(), null, 8, null), coneWrongNumberOfTypeArgumentsError);
    }
}

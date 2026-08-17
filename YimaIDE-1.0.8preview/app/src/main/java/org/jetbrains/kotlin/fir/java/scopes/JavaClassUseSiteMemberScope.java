package org.jetbrains.kotlin.fir.java.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyBuilderKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.java.FirSyntheticPropertiesStorageKt;
import org.jetbrains.kotlin.fir.java.JavaTypeConversionKt;
import org.jetbrains.kotlin.fir.java.JavaTypeParameterStack;
import org.jetbrains.kotlin.fir.java.SyntheticPropertiesCacheKey;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaMethod;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaMethodBuilder;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameter;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameterBuilder;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassUseSiteMemberScope;
import org.jetbrains.kotlin.fir.java.symbols.FirJavaOverriddenSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirOverrideCheckerKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.AbstractFirOverrideScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScopeContext;
import org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScopeContextKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.load.java.BuiltinSpecialProperties;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.load.java.PropertiesConventionUtilKt;
import org.jetbrains.kotlin.load.java.SpecialGenericSignatures;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.CallableIdKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.expressions.OperatorConventions;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\b\u000f\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u007f2\u00020\u0001:\u0001\u007fB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ*\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0012H\u0002J\u001a\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010'\u001a\u00020(2\u0006\u0010,\u001a\u00020-H\u0002J\u001a\u0010.\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003000/2\u0006\u00101\u001a\u000202H\u0014J\u001d\u00103\u001a\u0004\u0018\u00010\u001e2\f\u00104\u001a\b\u0012\u0004\u0012\u00020!0 H\u0000¢\u0006\u0002\b5J\u001e\u00106\u001a\u0004\u0018\u00010\u001e*\u00020!2\u0006\u00107\u001a\u0002082\u0006\u0010)\u001a\u00020\u0012H\u0002J\u0016\u00109\u001a\u0004\u0018\u00010%*\u00020!2\u0006\u00107\u001a\u000208H\u0002J\u001e\u00109\u001a\u0004\u0018\u00010%*\u00020!2\u0006\u0010:\u001a\u00020;2\u0006\u00107\u001a\u000208H\u0002J\u0016\u0010<\u001a\u0004\u0018\u00010%*\u00020!2\u0006\u00107\u001a\u000208H\u0002J\u0014\u0010=\u001a\u00020\u0012*\u00020!2\u0006\u0010>\u001a\u00020-H\u0002J\f\u0010?\u001a\u00020\u0012*\u00020-H\u0002J\u000e\u0010@\u001a\u0004\u0018\u000102*\u00020!H\u0002J\f\u0010A\u001a\u00020\u0012*\u00020%H\u0014J\f\u0010B\u001a\u00020\u0012*\u00020%H\u0002J\f\u0010C\u001a\u00020\u0012*\u00020%H\u0002J\f\u0010D\u001a\u00020%*\u00020%H\u0014J\u0016\u0010E\u001a\b\u0012\u0004\u0012\u00020%0/2\u0006\u00101\u001a\u000202H\u0014J%\u0010F\u001a\u0002HG\"\f\b\u0000\u0010G*\u0006\u0012\u0002\b\u00030H*\b\u0012\u0004\u0012\u0002HG0 H\u0002¢\u0006\u0002\u0010IJH\u0010J\u001a\u00020K2\u0006\u00101\u001a\u0002022(\u0010L\u001a$\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00070\u001f0\u0007j\b\u0012\u0004\u0012\u00020%`M2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020%0OH\u0002J6\u0010P\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\f\u0010N\u001a\b\u0012\u0004\u0012\u00020%0O2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020%0 2\b\u0010R\u001a\u0004\u0018\u00010%H\u0002J,\u0010S\u001a\u0004\u0018\u00010%2\u0006\u00101\u001a\u0002022\b\u0010R\u001a\u0004\u0018\u00010%2\u0006\u0010T\u001a\u00020%2\u0006\u0010U\u001a\u00020%H\u0002J\"\u0010V\u001a\u0004\u0018\u00010%2\u0006\u00101\u001a\u0002022\u0006\u0010T\u001a\u00020%2\u0006\u0010U\u001a\u00020%H\u0002J\u0014\u0010W\u001a\u00020\u0012*\u00020%2\u0006\u0010X\u001a\u00020%H\u0002JR\u0010Y\u001a\u00020\u00122\u0006\u0010Z\u001a\u00020%2\b\u0010[\u001a\u0004\u0018\u00010%2\u0006\u0010\\\u001a\u0002022\f\u0010]\u001a\b\u0012\u0004\u0012\u00020%0 2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020%0O2\u0012\u0010^\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0 0_H\u0002J$\u0010`\u001a\u00020K2\u0006\u0010a\u001a\u00020%2\u0012\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0 0\u0007H\u0002J\u0014\u0010c\u001a\u00020\u0012*\u00020!2\u0006\u0010d\u001a\u00020%H\u0002J\f\u0010e\u001a\u00020f*\u00020gH\u0002J\f\u0010h\u001a\u00020\u0012*\u00020%H\u0002J\"\u0010i\u001a\u00020;*\u00020(2\n\b\u0002\u0010j\u001a\u0004\u0018\u00010;2\b\b\u0002\u0010k\u001a\u00020\u0012H\u0002J\"\u0010l\u001a\u00020;*\u00020(2\n\b\u0002\u0010j\u001a\u0004\u0018\u00010;2\b\b\u0002\u0010k\u001a\u00020\u0012H\u0002J\"\u0010m\u001a\u00020;*\u00020n2\n\b\u0002\u0010j\u001a\u0004\u0018\u00010;2\b\b\u0002\u0010k\u001a\u00020\u0012H\u0002J$\u0010o\u001a\u00020\u0012*\u00020p2\u0006\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010q\u001a\b\u0012\u0004\u0012\u00020p0rH\u0002J\u0016\u0010s\u001a\u0004\u0018\u00010p*\u00020t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\n\u0010u\u001a\u00020;H\u0096\u0080\u0004J\u001c\u0010v\u001a\u00020\u00002\u0006\u0010w\u001a\u00020\u00052\u0006\u0010x\u001a\u00020yH\u0017b\u0002\bzJ\"\u0010{\u001a\u00020\u00122\u0006\u0010|\u001a\u00020%2\u0006\u0010}\u001a\u00020%2\b\u0010~\u001a\u0004\u0018\u00010\bH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR8\u0010\u001b\u001a,\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0 0\u001f0\u001cj\u0002`\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0080\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassUseSiteMemberScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/AbstractFirUseSiteMemberScope;", "klass", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "superTypeScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "declaredMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/List;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;)V", "typeParameterStack", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "getTypeParameterStack", "()Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "canUseSpecialGetters", Argument.Delimiters.none, "getCanUseSpecialGetters", "()Z", "canUseSpecialGetters$delegate", "Lkotlin/Lazy;", "javaOverrideChecker", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaOverrideChecker;", "getJavaOverrideChecker", "()Lorg/jetbrains/kotlin/fir/java/scopes/JavaOverrideChecker;", "syntheticPropertyCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/java/SyntheticPropertiesCacheKey;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "Lorg/jetbrains/kotlin/fir/java/SyntheticPropertiesCache;", "generateSyntheticPropertySymbol", "getterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "setterSymbol", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "takeModalityFromGetter", "chooseModalityForAccessor", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getter", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "collectProperties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "syntheticPropertyFromOverride", "overriddenProperty", "syntheticPropertyFromOverride$org_jetbrains_kotlin_fir_jvm", "createOverridePropertyIfExists", "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "findGetterOverride", "getterName", Argument.Delimiters.none, "findSetterOverride", "checkValueParameters", "candidate", "isAcceptableAsAccessorOverride", "getBuiltinSpecialPropertyGetterName", "isVisibleInCurrentClass", "doesOverrideRenamedBuiltins", "shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters", "replaceWithWrapperSymbolIfNeeded", "collectFunctions", "extractSomeSymbolFromSuperType", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processSpecialFunctions", Argument.Delimiters.none, "functionsFromSupertypes", "Lorg/jetbrains/kotlin/fir/scopes/impl/MembersByScope;", "destination", Argument.Delimiters.none, "processOverridesForFunctionsWithErasedValueParameter", "resultOfIntersection", "explicitlyDeclaredFunction", "processOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased", "relevantFunctionFromSupertypes", "relevantFunctionFromSupertypesUnwrapped", "processOverridesForFunctionsWithErasedValueParameterIfInheritedFunctionParametersAreErased", "hasSameJvmDescriptor", "builtinWithErasedParameters", "processOverridesForFunctionsWithDifferentJvmName", "someSymbolWithNaturalNameFromSuperType", "explicitlyDeclaredFunctionWithNaturalName", "naturalName", "resultOfIntersectionWithNaturalName", "functionsFromSupertypesToSaveInCache", Argument.Delimiters.none, "setOverrides", "override", "overridden", "isOverriddenInClassBy", "functionSymbol", "probablyJavaTypeRefToConeType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "hasErasedParameters", "computeJvmDescriptorForGetter", "customName", "includeReturnType", "computeJvmDescriptorForSetter", "computeJvmDescriptor", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "hasKotlinSuper", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visited", Argument.Delimiters.none, "toFir", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "toString", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "isOverriddenFunction", "overrideCandidate", "baseDeclaration", "baseScope", "Companion", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaClassUseSiteMemberScope extends AbstractFirUseSiteMemberScope {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: canUseSpecialGetters$delegate, reason: from kotlin metadata */
    private final Lazy canUseSpecialGetters;
    private final FirJavaClass klass;
    private final FirCache<SyntheticPropertiesCacheKey, FirSyntheticPropertySymbol, Pair<JavaClassUseSiteMemberScope, ? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>> syntheticPropertyCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaClassUseSiteMemberScope(FirJavaClass firJavaClass, final FirSession firSession, List<? extends FirTypeScope> list, FirContainingNamesAwareScope firContainingNamesAwareScope) {
        super(firJavaClass.getSymbol().getLookupTag(), firSession, new JavaOverrideChecker(firSession, firJavaClass, list, true), null, list, ScopeUtilsKt.defaultType(firJavaClass), firContainingNamesAwareScope);
        firJavaClass.getClass();
        firSession.getClass();
        list.getClass();
        firContainingNamesAwareScope.getClass();
        this.klass = firJavaClass;
        this.canUseSpecialGetters = LazyKt.lazy(new Function0() { // from class: dc7
            public final Object invoke() {
                return Boolean.valueOf(JavaClassUseSiteMemberScope.f(this.b, firSession));
            }
        });
        this.syntheticPropertyCache = (FirCache) FirSyntheticPropertiesStorageKt.getSyntheticPropertiesStorage(firSession).getCacheByOwner().getValue(firJavaClass, null);
    }

    private final boolean checkValueParameters(FirPropertySymbol firPropertySymbol, FirNamedFunction firNamedFunction) {
        KtSourceElement source = firPropertySymbol.getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null;
        Iterator<FirValueParameterSymbol> it = firPropertySymbol.getContextParameterSymbols().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            if (!Intrinsics.areEqual(SignatureUtilsKt.computeJvmDescriptorRepresentation$default(it.next().getResolvedReturnType(), null, 1, null), SignatureUtilsKt.computeJvmDescriptorRepresentation$default(JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(firNamedFunction.getValueParameters().get(i).getReturnTypeRef(), getSession(), getTypeParameterStack(), ktSourceElementFakeElement$default, null, 8, null), null, 1, null))) {
                return false;
            }
            i = i2;
        }
        if (firPropertySymbol.getReceiverParameterSymbol() != null) {
            ConeKotlinType resolvedReceiverType = firPropertySymbol.getResolvedReceiverType();
            resolvedReceiverType.getClass();
            if (!Intrinsics.areEqual(SignatureUtilsKt.computeJvmDescriptorRepresentation$default(resolvedReceiverType, null, 1, null), SignatureUtilsKt.computeJvmDescriptorRepresentation$default(JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(firNamedFunction.getValueParameters().get(i).getReturnTypeRef(), getSession(), getTypeParameterStack(), ktSourceElementFakeElement$default, null, 8, null), null, 1, null))) {
                return false;
            }
        }
        return true;
    }

    private final Modality chooseModalityForAccessor(FirProperty property, FirNamedFunction getter) {
        Modality modality = property.getStatus().getModality();
        Modality modality2 = getter.getStatus().getModality();
        if (modality == null) {
            return modality2;
        }
        return modality2 == null ? modality : ComparisonsKt.minOf(modality, modality2);
    }

    private final String computeJvmDescriptor(final FirFunction firFunction, String str, boolean z) {
        return SignatureUtilsKt.computeJvmDescriptor(firFunction, str, z, new Function1() { // from class: ic7
            public final Object invoke(Object obj) {
                return JavaClassUseSiteMemberScope.i(this.b, firFunction, (FirTypeRef) obj);
            }
        });
    }

    public static /* synthetic */ String computeJvmDescriptor$default(JavaClassUseSiteMemberScope javaClassUseSiteMemberScope, FirFunction firFunction, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return javaClassUseSiteMemberScope.computeJvmDescriptor(firFunction, str, z);
    }

    private final String computeJvmDescriptorForGetter(FirProperty firProperty, String str, boolean z) {
        String strComputeJvmDescriptor;
        FirPropertyAccessor getter = firProperty.getGetter();
        return (getter == null || (strComputeJvmDescriptor = computeJvmDescriptor(getter, str, z)) == null) ? computeJvmDescriptor(new FirDefaultPropertyGetter(null, firProperty.getModuleData(), firProperty.getOrigin(), firProperty.getReturnTypeRef(), firProperty.getStatus().getVisibility(), firProperty.getSymbol(), firProperty.getStatus().getModality(), null, false, false, null, null, null, 8064, null), str, z) : strComputeJvmDescriptor;
    }

    private final String computeJvmDescriptorForSetter(FirProperty firProperty, String str, boolean z) {
        String strComputeJvmDescriptor;
        FirPropertyAccessor setter = firProperty.getSetter();
        return (setter == null || (strComputeJvmDescriptor = computeJvmDescriptor(setter, str, z)) == null) ? computeJvmDescriptor(new FirDefaultPropertySetter(null, firProperty.getModuleData(), firProperty.getOrigin(), firProperty.getReturnTypeRef(), firProperty.getStatus().getVisibility(), firProperty.getSymbol(), firProperty.getStatus().getModality(), null, false, false, null, null, null, null, null, 32640, null), str, z) : strComputeJvmDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirSyntheticPropertySymbol createOverridePropertyIfExists(FirPropertySymbol firPropertySymbol, FirScope firScope, boolean z) {
        FirNamedFunctionSymbol firNamedFunctionSymbolFindSetterOverride;
        FirNamedFunctionSymbol firNamedFunctionSymbolFindGetterOverride = findGetterOverride(firPropertySymbol, firScope);
        if (firNamedFunctionSymbolFindGetterOverride == null) {
            return null;
        }
        if (((FirProperty) firPropertySymbol.getFir()).getIsVar()) {
            firNamedFunctionSymbolFindSetterOverride = findSetterOverride(firPropertySymbol, firScope);
            if (firNamedFunctionSymbolFindSetterOverride == null) {
                return null;
            }
        } else {
            firNamedFunctionSymbolFindSetterOverride = null;
        }
        if (firNamedFunctionSymbolFindSetterOverride == null || ((FirMemberDeclaration) firNamedFunctionSymbolFindSetterOverride.getFir()).getStatus().getModality() == ((FirMemberDeclaration) firNamedFunctionSymbolFindGetterOverride.getFir()).getStatus().getModality()) {
            return generateSyntheticPropertySymbol(firNamedFunctionSymbolFindGetterOverride, firNamedFunctionSymbolFindSetterOverride, (FirProperty) firPropertySymbol.getFir(), z);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean doesOverrideRenamedBuiltins(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        Name builtinFunctionNamesByJvmName = SpecialGenericSignatures.Companion.getBuiltinFunctionNamesByJvmName(firNamedFunctionSymbol.getName());
        if (builtinFunctionNamesByJvmName == null) {
            return false;
        }
        List<FirTypeScope> scopes = getSupertypeScopeContext().getScopes();
        ArrayList<Pair> arrayList = new ArrayList();
        for (FirTypeScope firTypeScope : scopes) {
            final ArrayList arrayList2 = new ArrayList();
            firTypeScope.processFunctionsByName(builtinFunctionNamesByJvmName, new Function1<FirNamedFunctionSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.java.scopes.JavaClassUseSiteMemberScope$doesOverrideRenamedBuiltins$$inlined$collectMembersGroupedByScope$1
                public final void invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2) {
                    firNamedFunctionSymbol2.getClass();
                    if (firNamedFunctionSymbol2 instanceof FirConstructorSymbol) {
                        return;
                    }
                    arrayList2.add(firNamedFunctionSymbol2);
                }

                /* JADX WARN: Multi-variable type inference failed */
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((FirNamedFunctionSymbol) obj);
                    return Unit.INSTANCE;
                }
            });
            if (arrayList2.isEmpty()) {
                arrayList2 = null;
            }
            Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Pair pair2 : arrayList) {
            FirTypeScope firTypeScope2 = (FirTypeScope) pair2.component1();
            List list = (List) pair2.component2();
            ArrayList arrayList4 = new ArrayList();
            for (Object obj : list) {
                if (JavaScopeUtilsKt.doesOverrideBuiltinWithDifferentJvmName((FirNamedFunctionSymbol) obj, firTypeScope2, getSession())) {
                    arrayList4.add(obj);
                }
            }
            CollectionsKt.addAll(arrayList3, arrayList4);
        }
        if (arrayList3.isEmpty() || arrayList3.isEmpty()) {
            return false;
        }
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            if (getOverrideChecker().isOverriddenFunction((FirNamedFunction) firNamedFunctionSymbol.getFir(), (FirNamedFunction) ((FirNamedFunctionSymbol) it.next()).getFir())) {
                return true;
            }
        }
        return false;
    }

    private final <D extends FirCallableSymbol<?>> D extractSomeSymbolFromSuperType(FirTypeIntersectionScopeContext.ResultOfIntersection<D> resultOfIntersection) {
        return FirTypeIntersectionScopeContextKt.isIntersectionOverride(resultOfIntersection) ? (D) ((FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial) resultOfIntersection).getKeySymbol() : (D) resultOfIntersection.getChosenSymbol();
    }

    public static boolean f(JavaClassUseSiteMemberScope javaClassUseSiteMemberScope, FirSession firSession) {
        return !hasKotlinSuper$default(javaClassUseSiteMemberScope, javaClassUseSiteMemberScope.klass, firSession, null, 2, null);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0066  */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedFunctionSymbol findGetterOverride(FirPropertySymbol firPropertySymbol, String str, FirScope firScope) {
        FirResolvedTypeRef returnTypeRef = ((FirProperty) firPropertySymbol.getFir()).getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeKotlinType coneKotlinType = coneType == null ? null : coneType;
        int size = (firPropertySymbol.getReceiverParameterSymbol() != null ? 1 : 0) + firPropertySymbol.getContextParameterSymbols().size();
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        for (FirNamedFunctionSymbol firNamedFunctionSymbol : FirScopeKt.getFunctions(firScope, nameIdentifier)) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir();
            if (firNamedFunction.getValueParameters().size() == size && checkValueParameters(firPropertySymbol, firNamedFunction)) {
                FirTypeRef returnTypeRef2 = firNamedFunction.getReturnTypeRef();
                FirSession session = getSession();
                JavaTypeParameterStack typeParameterStack = getTypeParameterStack();
                KtSourceElement source = firPropertySymbol.getSource();
                ConeKotlinType coneKotlinTypeProbablyFlexible$default = JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(returnTypeRef2, session, typeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
                if (!isAcceptableAsAccessorOverride(firNamedFunction) || (coneKotlinType != null && !AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(getSession()), coneKotlinTypeProbablyFlexible$default, coneKotlinType, false, 8, (Object) null))) {
                    firNamedFunctionSymbol = null;
                }
            } else {
                firNamedFunctionSymbol = null;
            }
            if (firNamedFunctionSymbol != null) {
                return firNamedFunctionSymbol;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0081  */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedFunctionSymbol findSetterOverride(FirPropertySymbol firPropertySymbol, FirScope firScope) {
        FirResolvedTypeRef returnTypeRef = ((FirProperty) firPropertySymbol.getFir()).getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeKotlinType coneKotlinType = coneType == null ? null : coneType;
        if (coneKotlinType == null) {
            return null;
        }
        int size = (firPropertySymbol.getReceiverParameterSymbol() != null ? 1 : 0) + firPropertySymbol.getContextParameterSymbols().size();
        String strAsString = ((FirProperty) firPropertySymbol.getFir()).getName().asString();
        strAsString.getClass();
        Name nameIdentifier = Name.identifier(JvmAbi.setterName(strAsString));
        nameIdentifier.getClass();
        for (FirNamedFunctionSymbol firNamedFunctionSymbol : FirScopeKt.getFunctions(firScope, nameIdentifier)) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir();
            if (firNamedFunction.getValueParameters().size() == size + 1 && checkValueParameters(firPropertySymbol, firNamedFunction)) {
                KtSourceElement source = firPropertySymbol.getSource();
                KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null;
                if (ConeBuiltinTypeUtilsKt.isUnit(JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(firNamedFunction.getReturnTypeRef(), getSession(), getTypeParameterStack(), ktSourceElementFakeElement$default, null, 8, null))) {
                    ConeKotlinType coneKotlinTypeProbablyFlexible$default = JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(((FirValueParameter) CollectionsKt.last(firNamedFunction.getValueParameters())).getReturnTypeRef(), getSession(), getTypeParameterStack(), ktSourceElementFakeElement$default, null, 8, null);
                    if (!isAcceptableAsAccessorOverride(firNamedFunction) || !AbstractTypeChecker.equalTypes$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(getSession()), coneKotlinTypeProbablyFlexible$default, coneKotlinType, false, false, 24, (Object) null)) {
                        firNamedFunctionSymbol = null;
                    }
                } else {
                    firNamedFunctionSymbol = null;
                }
            } else {
                firNamedFunctionSymbol = null;
            }
            if (firNamedFunctionSymbol != null) {
                return firNamedFunctionSymbol;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction g(Ref.ObjectRef objectRef, FirPropertySymbol firPropertySymbol) {
        ClassId classId;
        FqName fqNameAsSingleFqName;
        firPropertySymbol.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableDeclaration) firPropertySymbol.getFir());
        Name name = (Name) BuiltinSpecialProperties.INSTANCE.getPROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP().get((coneClassLikeLookupTagContainingClassLookupTag == null || (classId = coneClassLikeLookupTagContainingClassLookupTag.getClassId()) == null || (fqNameAsSingleFqName = classId.asSingleFqName()) == null) ? null : fqNameAsSingleFqName.child(((FirProperty) firPropertySymbol.getFir()).getName()));
        if (name == null) {
            return ProcessorAction.NEXT;
        }
        objectRef.element = name;
        return ProcessorAction.STOP;
    }

    private final FirSyntheticPropertySymbol generateSyntheticPropertySymbol(final FirNamedFunctionSymbol getterSymbol, final FirNamedFunctionSymbol setterSymbol, final FirProperty property, final boolean takeModalityFromGetter) {
        final CallableId callableIdWithClassId = CallableIdKt.withClassId(getterSymbol.getCallableId(), FirDeclarationUtilKt.getClassId(this.klass));
        return FirSyntheticPropertyBuilderKt.buildSyntheticProperty(new Function1() { // from class: fc7
            public final Object invoke(Object obj) {
                return JavaClassUseSiteMemberScope.h(this.b, property, callableIdWithClassId, getterSymbol, setterSymbol, takeModalityFromGetter, (FirSyntheticPropertyBuilder) obj);
            }
        }).getSymbol();
    }

    private final Name getBuiltinSpecialPropertyGetterName(FirPropertySymbol firPropertySymbol) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FirScopeKt.processOverriddenPropertiesAndSelf(getSuperTypeScopes(), firPropertySymbol, new Function1() { // from class: ec7
            public final Object invoke(Object obj) {
                return JavaClassUseSiteMemberScope.g(objectRef, (FirPropertySymbol) obj);
            }
        });
        return (Name) objectRef.element;
    }

    private final boolean getCanUseSpecialGetters() {
        return ((Boolean) this.canUseSpecialGetters.getValue()).booleanValue();
    }

    private final JavaOverrideChecker getJavaOverrideChecker() {
        FirOverrideChecker overrideChecker = getOverrideChecker();
        overrideChecker.getClass();
        return (JavaOverrideChecker) overrideChecker;
    }

    private final JavaTypeParameterStack getTypeParameterStack() {
        return this.klass.getJavaTypeParameterStack();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit h(JavaClassUseSiteMemberScope javaClassUseSiteMemberScope, FirProperty firProperty, CallableId callableId, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2, boolean z, FirSyntheticPropertyBuilder firSyntheticPropertyBuilder) {
        firSyntheticPropertyBuilder.getClass();
        firSyntheticPropertyBuilder.setModuleData(FirModuleDataKt.getModuleData(javaClassUseSiteMemberScope.getSession()));
        firSyntheticPropertyBuilder.setName(firProperty.getName());
        firSyntheticPropertyBuilder.setSymbol(new FirJavaOverriddenSyntheticPropertySymbol(new CallableId(FirDeclarationUtilKt.getClassId(javaClassUseSiteMemberScope.klass), firProperty.getName()), callableId));
        firSyntheticPropertyBuilder.setDelegateGetter((FirNamedFunction) firNamedFunctionSymbol.getFir());
        FirDeclarationStatus firDeclarationStatusCopy = null;
        firSyntheticPropertyBuilder.setDelegateSetter(firNamedFunctionSymbol2 != null ? (FirNamedFunction) firNamedFunctionSymbol2.getFir() : null);
        if (!z) {
            FirDeclarationStatus status = ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getStatus();
            firDeclarationStatusCopy = UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : null, (8388575 & 2) != 0 ? status.getModality() : javaClassUseSiteMemberScope.chooseModalityForAccessor(firProperty, firSyntheticPropertyBuilder.getDelegateGetter()), (8388575 & 4) != 0 ? status.isExpect() : false, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null);
        }
        firSyntheticPropertyBuilder.setCustomStatus(firDeclarationStatusCopy);
        firSyntheticPropertyBuilder.setDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProviderFromAccessors(javaClassUseSiteMemberScope.getSession(), firSyntheticPropertyBuilder.getDelegateGetter(), firSyntheticPropertyBuilder.getDelegateSetter()));
        firSyntheticPropertyBuilder.setDispatchReceiverType(ScopeUtilsKt.defaultType(javaClassUseSiteMemberScope.klass));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final boolean hasErasedParameters(FirNamedFunctionSymbol firNamedFunctionSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        FirDeclarationOrigin origin = firNamedFunctionSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            D fir = firNamedFunctionSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return false;
            }
        }
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.firstOrNull(((FirNamedFunction) firNamedFunctionSymbol.getFir()).getValueParameters());
        if (firValueParameter == null) {
            return false;
        }
        FirTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
        FirSession session = getSession();
        JavaTypeParameterStack typeParameterStack = getTypeParameterStack();
        KtSourceElement source = firValueParameter.getSource();
        ConeRigidType coneRigidTypeUpperBoundIfFlexible = ConeTypeUtilsKt.upperBoundIfFlexible(JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(returnTypeRef, session, typeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null));
        if (!(coneRigidTypeUpperBoundIfFlexible instanceof ConeClassLikeType)) {
            return false;
        }
        if (!SpecialGenericSignatures.Companion.getERASED_COLLECTION_PARAMETER_NAMES().contains(((FirNamedFunction) firNamedFunctionSymbol.getFir()).getName().asString())) {
            return Intrinsics.areEqual(ConeTypeUtilsKt.getClassId((ConeClassLikeType) coneRigidTypeUpperBoundIfFlexible), StandardClassIds.INSTANCE.getAny());
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneRigidTypeUpperBoundIfFlexible;
        if (Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getCollection())) {
            return ArraysKt.singleOrNull(coneRigidTypeUpperBoundIfFlexible.getTypeArguments()) instanceof ConeStarProjection;
        }
        dt1.a("Unexpected type: ", coneClassLikeType.getLookupTag().getClassId());
        return false;
    }

    private final boolean hasKotlinSuper(FirRegularClass firRegularClass, FirSession firSession, Set<FirRegularClass> set) {
        if (!set.add(firRegularClass)) {
            return false;
        }
        if (!(firRegularClass instanceof FirJavaClass)) {
            return (firRegularClass.getClassKind() == ClassKind.INTERFACE || JavaScopeUtilsKt.isBuiltinClass(firRegularClass.getSymbol())) ? false : true;
        }
        List<ConeClassLikeType> superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(firRegularClass);
        if ((superConeTypes instanceof Collection) && superConeTypes.isEmpty()) {
            return false;
        }
        Iterator<T> it = superConeTypes.iterator();
        while (it.hasNext()) {
            FirRegularClass fir = toFir((ConeClassLikeType) it.next(), firSession);
            if (fir != null && hasKotlinSuper(fir, firSession, set)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean hasKotlinSuper$default(JavaClassUseSiteMemberScope javaClassUseSiteMemberScope, FirRegularClass firRegularClass, FirSession firSession, Set set, int i, Object obj) {
        if ((i & 2) != 0) {
            set = new LinkedHashSet();
        }
        return javaClassUseSiteMemberScope.hasKotlinSuper(firRegularClass, firSession, set);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean hasSameJvmDescriptor(FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        return Intrinsics.areEqual(computeJvmDescriptor$default(this, (FirFunction) firNamedFunctionSymbol.getFir(), null, false, 1, null), computeJvmDescriptor$default(this, (FirFunction) firNamedFunctionSymbol2.getFir(), null, false, 1, null));
    }

    public static ConeKotlinType i(JavaClassUseSiteMemberScope javaClassUseSiteMemberScope, FirFunction firFunction, FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        FirSession session = javaClassUseSiteMemberScope.getSession();
        JavaTypeParameterStack typeParameterStack = javaClassUseSiteMemberScope.getTypeParameterStack();
        KtSourceElement source = firFunction.getSource();
        return JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(firTypeRef, session, typeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
    }

    private final boolean isAcceptableAsAccessorOverride(FirNamedFunction firNamedFunction) {
        return DeclarationUtilsKt.isJavaOrEnhancement(firNamedFunction) || firNamedFunction.getTypeParameters().isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isOverriddenInClassBy(FirPropertySymbol firPropertySymbol, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        List listListOf;
        boolean z;
        Pair pair;
        FirNamedFunction delegate;
        FirNamedFunction delegate2;
        if (Intrinsics.areEqual(firPropertySymbol.getRawStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            return false;
        }
        FirProperty firProperty = (FirProperty) firPropertySymbol.getFir();
        if (firProperty instanceof FirSyntheticProperty) {
            FirSyntheticProperty firSyntheticProperty = (FirSyntheticProperty) firProperty;
            if (!Intrinsics.areEqual(firSyntheticProperty.getGetter().getDelegate().getSymbol(), firNamedFunctionSymbol)) {
                FirSyntheticPropertyAccessor setter = firSyntheticProperty.getSetter();
                if (!Intrinsics.areEqual((setter == null || (delegate2 = setter.getDelegate()) == null) ? null : delegate2.getSymbol(), firNamedFunctionSymbol)) {
                    String strComputeJvmDescriptor$default = computeJvmDescriptor$default(this, firSyntheticProperty.getGetter().getDelegate(), null, false, 1, null);
                    FirSyntheticPropertyAccessor setter2 = firSyntheticProperty.getSetter();
                    listListOf = CollectionsKt.listOf(TuplesKt.to(strComputeJvmDescriptor$default, (setter2 == null || (delegate = setter2.getDelegate()) == null) ? null : computeJvmDescriptor$default(this, delegate, null, false, 1, null)));
                }
            }
            return true;
        }
        List listListOfNotNull = CollectionsKt.listOfNotNull(JavaScopeUtilsKt.getJvmMethodNameIfSpecial(firPropertySymbol, this, getSession()));
        if (listListOfNotNull.isEmpty()) {
            listListOfNotNull = null;
        }
        if (listListOfNotNull == null) {
            listListOfNotNull = PropertiesConventionUtilKt.possibleGetMethodNames(firProperty.getName());
        }
        List<Name> list = listListOfNotNull;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Name name : list) {
            arrayList.add(TuplesKt.to(computeJvmDescriptorForGetter(firProperty, name.getIdentifier(), false), firPropertySymbol.isVar() ? computeJvmDescriptorForSetter(firProperty, PropertiesConventionUtilKt.setMethodName(name).getIdentifier(), false) : null));
        }
        listListOf = arrayList;
        String strComputeJvmDescriptor$default2 = computeJvmDescriptor$default(this, (FirFunction) firNamedFunctionSymbol.getFir(), null, false, 1, null);
        List list2 = listListOf;
        boolean z2 = list2 instanceof Collection;
        if (z2 && list2.isEmpty()) {
            z = false;
            break;
        }
        Iterator it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (Intrinsics.areEqual(strComputeJvmDescriptor$default2, (String) ((Pair) it.next()).component1())) {
                if (TypeUtilsKt.isSubtypeOf$default(probablyJavaTypeRefToConeType(((FirNamedFunction) firNamedFunctionSymbol.getFir()).getReturnTypeRef()), probablyJavaTypeRefToConeType(((FirProperty) firPropertySymbol.getFir()).getReturnTypeRef()), getSession(), false, 4, null)) {
                    z = true;
                    break;
                }
            }
        }
        if (z && firPropertySymbol.isVal()) {
            return true;
        }
        if (!z2 || !list2.isEmpty()) {
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                if (Intrinsics.areEqual(strComputeJvmDescriptor$default2, (String) ((Pair) it2.next()).component2())) {
                    if (!z) {
                        if (z) {
                            bu8.a();
                            break;
                        }
                        pair = TuplesKt.to(findGetterOverride(firPropertySymbol, this), firNamedFunctionSymbol);
                    } else {
                        pair = TuplesKt.to(firNamedFunctionSymbol, findSetterOverride(firPropertySymbol, this));
                    }
                    FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) pair.component1();
                    FirNamedFunctionSymbol firNamedFunctionSymbol3 = (FirNamedFunctionSymbol) pair.component2();
                    return (firNamedFunctionSymbol2 != null ? firNamedFunctionSymbol2.getResolvedStatus().getModality() : null) == (firNamedFunctionSymbol3 != null ? firNamedFunctionSymbol3.getResolvedStatus().getModality() : null);
                }
            }
        }
        return false;
    }

    public static Unit j(FirNamedFunction firNamedFunction, ConeKotlinType coneKotlinType, FirFunctionBuilder firFunctionBuilder) {
        firFunctionBuilder.getClass();
        firFunctionBuilder.getValueParameters().clear();
        firFunctionBuilder.getValueParameters().addAll(CollectionsKt.dropLast(firNamedFunction.getValueParameters(), 1));
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(coneKotlinType);
        firFunctionBuilder.setReturnTypeRef(firResolvedTypeRefBuilder.build());
        FirDeclarationStatus status = firFunctionBuilder.getStatus();
        status.getClass();
        ((FirDeclarationStatusImpl) status).setSuspend(true);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit k(Set set, Set set2, Set set3, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (firVariableSymbol.getRawStatus().isStatic()) {
            return Unit.INSTANCE;
        }
        set.add(firVariableSymbol);
        set2.add(((FirVariable) firVariableSymbol.getFir()).getName());
        set3.add(firVariableSymbol);
        return Unit.INSTANCE;
    }

    private final ConeKotlinType probablyJavaTypeRefToConeType(FirTypeRef firTypeRef) {
        FirSession session = getSession();
        JavaTypeParameterStack typeParameterStack = getTypeParameterStack();
        KtSourceElement source = firTypeRef.getSource();
        return JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(firTypeRef, session, typeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean processOverridesForFunctionsWithDifferentJvmName(FirNamedFunctionSymbol someSymbolWithNaturalNameFromSuperType, FirNamedFunctionSymbol explicitlyDeclaredFunctionWithNaturalName, Name naturalName, FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol> resultOfIntersectionWithNaturalName, Collection<FirNamedFunctionSymbol> destination, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> functionsFromSupertypesToSaveInCache) {
        Object obj;
        Name jvmMethodNameIfSpecial;
        Object next;
        List<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> listConvertGroupedCallablesToIntersectionResults;
        JavaClassUseSiteMemberScope javaClassUseSiteMemberScope;
        FirNamedFunctionSymbol firNamedFunctionSymbol;
        JavaClassUseSiteMemberScope javaClassUseSiteMemberScope2 = this;
        Iterator it = resultOfIntersectionWithNaturalName.getOverriddenMembers().iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                jvmMethodNameIfSpecial = null;
                break;
            }
            MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) it.next();
            jvmMethodNameIfSpecial = JavaScopeUtilsKt.getJvmMethodNameIfSpecial(memberWithBaseScope.getMember(), memberWithBaseScope.getBaseScope(), javaClassUseSiteMemberScope2.getSession());
        } while (jvmMethodNameIfSpecial == null);
        if (jvmMethodNameIfSpecial == null) {
            return false;
        }
        Iterable overriddenMembers = resultOfIntersectionWithNaturalName.getOverriddenMembers();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : overriddenMembers) {
            MemberWithBaseScope memberWithBaseScope2 = (MemberWithBaseScope) obj2;
            if (Intrinsics.areEqual(JavaScopeUtilsKt.getJvmMethodNameIfSpecial(memberWithBaseScope2.getMember(), memberWithBaseScope2.getBaseScope(), javaClassUseSiteMemberScope2.getSession()), jvmMethodNameIfSpecial)) {
                arrayList.add(obj2);
            } else {
                arrayList2.add(obj2);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List<MemberWithBaseScope> list = (List) pair.component1();
        List list2 = (List) pair.component2();
        Iterator<T> it2 = FirScopeKt.getFunctions(javaClassUseSiteMemberScope2.getDeclaredMemberScope(), jvmMethodNameIfSpecial).iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (!FirOverrideCheckerKt.isOverriddenFunction(javaClassUseSiteMemberScope2.getOverrideChecker(), (FirNamedFunctionSymbol) next, someSymbolWithNaturalNameFromSuperType));
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) next;
        for (Object obj3 : javaClassUseSiteMemberScope2.getSupertypeScopeContext().collectFunctions(jvmMethodNameIfSpecial)) {
            if (AbstractFirOverrideScopeKt.similarFunctionsOrBothProperties(javaClassUseSiteMemberScope2.getOverrideChecker(), (FirCallableSymbol<?>) javaClassUseSiteMemberScope2.extractSomeSymbolFromSuperType((FirTypeIntersectionScopeContext.ResultOfIntersection) obj3), someSymbolWithNaturalNameFromSuperType)) {
                obj = obj3;
                break;
            }
        }
        FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection = (FirTypeIntersectionScopeContext.ResultOfIntersection) obj;
        if (resultOfIntersection != null) {
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            List list3 = listCreateListBuilder;
            for (MemberWithBaseScope memberWithBaseScope3 : list) {
                list3.add(TuplesKt.to(memberWithBaseScope3.getBaseScope(), CollectionsKt.listOf(memberWithBaseScope3.getMember())));
            }
            List<MemberWithBaseScope> overriddenMembers2 = resultOfIntersection.getOverriddenMembers();
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(overriddenMembers2, 10));
            for (MemberWithBaseScope memberWithBaseScope4 : overriddenMembers2) {
                arrayList3.add(TuplesKt.to(memberWithBaseScope4.getBaseScope(), CollectionsKt.listOf(processOverridesForFunctionsWithDifferentJvmName$createCopyWithNaturalName$default(javaClassUseSiteMemberScope2, naturalName, (FirNamedFunctionSymbol) memberWithBaseScope4.getMember(), false, FirDeclarationOrigin.RenamedForOverride.INSTANCE, 8, null))));
                javaClassUseSiteMemberScope2 = this;
            }
            listCreateListBuilder.addAll(arrayList3);
            listConvertGroupedCallablesToIntersectionResults = getSupertypeScopeContext().convertGroupedCallablesToIntersectionResults(CollectionsKt.build(listCreateListBuilder));
        } else if (list2.isEmpty()) {
            listConvertGroupedCallablesToIntersectionResults = CollectionsKt.listOf(resultOfIntersectionWithNaturalName);
        } else {
            FirTypeIntersectionScopeContext supertypeScopeContext = getSupertypeScopeContext();
            List<MemberWithBaseScope> list4 = list;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            for (MemberWithBaseScope memberWithBaseScope5 : list4) {
                arrayList4.add(TuplesKt.to(memberWithBaseScope5.getBaseScope(), CollectionsKt.listOf(memberWithBaseScope5.getMember())));
            }
            listConvertGroupedCallablesToIntersectionResults = supertypeScopeContext.convertGroupedCallablesToIntersectionResults(arrayList4);
        }
        List<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> list5 = listConvertGroupedCallablesToIntersectionResults;
        boolean z = (explicitlyDeclaredFunctionWithNaturalName == null && list2.isEmpty()) ? false : true;
        if (firNamedFunctionSymbol2 != null) {
            javaClassUseSiteMemberScope = this;
            firNamedFunctionSymbol = firNamedFunctionSymbol2;
            FirNamedFunctionSymbol firNamedFunctionSymbolProcessOverridesForFunctionsWithDifferentJvmName$createCopyWithNaturalName$default = processOverridesForFunctionsWithDifferentJvmName$createCopyWithNaturalName$default(javaClassUseSiteMemberScope, naturalName, firNamedFunctionSymbol, z, null, 16, null);
            destination.add(firNamedFunctionSymbolProcessOverridesForFunctionsWithDifferentJvmName$createCopyWithNaturalName$default);
            javaClassUseSiteMemberScope.setOverrides(firNamedFunctionSymbolProcessOverridesForFunctionsWithDifferentJvmName$createCopyWithNaturalName$default, list5);
        } else {
            javaClassUseSiteMemberScope = this;
            firNamedFunctionSymbol = firNamedFunctionSymbol2;
        }
        if (z) {
            FirTypeIntersectionScopeContext supertypeScopeContext2 = javaClassUseSiteMemberScope.getSupertypeScopeContext();
            List<MemberWithBaseScope> list6 = list2;
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list6, 10));
            for (MemberWithBaseScope memberWithBaseScope6 : list6) {
                arrayList5.add(TuplesKt.to(memberWithBaseScope6.getBaseScope(), CollectionsKt.listOf(memberWithBaseScope6.getMember())));
            }
            List<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> listConvertGroupedCallablesToIntersectionResults2 = supertypeScopeContext2.convertGroupedCallablesToIntersectionResults(arrayList5);
            if (explicitlyDeclaredFunctionWithNaturalName != null) {
                if (firNamedFunctionSymbol != null) {
                    list5 = listConvertGroupedCallablesToIntersectionResults2;
                }
                javaClassUseSiteMemberScope.setOverrides(explicitlyDeclaredFunctionWithNaturalName, list5);
            } else {
                FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection2 = (FirTypeIntersectionScopeContext.ResultOfIntersection) CollectionsKt.single(listConvertGroupedCallablesToIntersectionResults2);
                destination.add(resultOfIntersection2.getChosenSymbol());
                if (resultOfIntersection2 instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial) {
                    javaClassUseSiteMemberScope.setOverrides((FirNamedFunctionSymbol) ((FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial) resultOfIntersection2).getChosenSymbol(), listConvertGroupedCallablesToIntersectionResults2);
                }
            }
        } else if (firNamedFunctionSymbol == null) {
            Iterator<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> it3 = list5.iterator();
            while (it3.hasNext()) {
                destination.add(it3.next().getChosenSymbol());
            }
            CollectionsKt.addAll(functionsFromSupertypesToSaveInCache, list5);
        }
        return true;
    }

    private static final FirNamedFunctionSymbol processOverridesForFunctionsWithDifferentJvmName$createCopyWithNaturalName(JavaClassUseSiteMemberScope javaClassUseSiteMemberScope, Name name, FirNamedFunctionSymbol firNamedFunctionSymbol, boolean z, FirDeclarationOrigin firDeclarationOrigin) {
        return JavaClassUseSiteMemberScopeKt.getRenamedFunctionsCache(javaClassUseSiteMemberScope.getSession()).getRenamedFunctionsCache().getValue(TuplesKt.to(firNamedFunctionSymbol, name), new FirRenamedForOverrideSymbolsStorage.RenamedFunctionCreationContext(javaClassUseSiteMemberScope.klass, z, firDeclarationOrigin));
    }

    public static /* synthetic */ FirNamedFunctionSymbol processOverridesForFunctionsWithDifferentJvmName$createCopyWithNaturalName$default(JavaClassUseSiteMemberScope javaClassUseSiteMemberScope, Name name, FirNamedFunctionSymbol firNamedFunctionSymbol, boolean z, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        if ((i & 16) != 0) {
            firDeclarationOrigin = null;
        }
        return processOverridesForFunctionsWithDifferentJvmName$createCopyWithNaturalName(javaClassUseSiteMemberScope, name, firNamedFunctionSymbol, z, firDeclarationOrigin);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean processOverridesForFunctionsWithErasedValueParameter(Name name, Collection<FirNamedFunctionSymbol> destination, FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol> resultOfIntersection, FirNamedFunctionSymbol explicitlyDeclaredFunction) {
        Object next;
        FirNamedFunctionSymbol firNamedFunctionSymbol;
        MemberWithBaseScope memberWithBaseScope;
        Iterator it = resultOfIntersection.getOverriddenMembers().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            memberWithBaseScope = (MemberWithBaseScope) next;
        } while (BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava((FirNamedFunctionSymbol) memberWithBaseScope.component1(), memberWithBaseScope.getBaseScope()) == null);
        MemberWithBaseScope memberWithBaseScope2 = (MemberWithBaseScope) next;
        if (memberWithBaseScope2 == null || (firNamedFunctionSymbol = (FirNamedFunctionSymbol) memberWithBaseScope2.getMember()) == null) {
            return false;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
            return false;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) symbol;
        FirNamedFunctionSymbol initialSignatureAttr = ClassMembersKt.getInitialSignatureAttr((FirCallableDeclaration) firNamedFunctionSymbol2.getFir());
        if (initialSignatureAttr != null) {
            firNamedFunctionSymbol2 = initialSignatureAttr;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbolProcessOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased = processOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased(name, explicitlyDeclaredFunction, firNamedFunctionSymbol, firNamedFunctionSymbol2);
        if (firNamedFunctionSymbolProcessOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased == null && (firNamedFunctionSymbolProcessOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased = processOverridesForFunctionsWithErasedValueParameterIfInheritedFunctionParametersAreErased(name, firNamedFunctionSymbol, firNamedFunctionSymbol2)) == null) {
            return false;
        }
        destination.add(firNamedFunctionSymbolProcessOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased);
        getDirectOverriddenFunctions().put(firNamedFunctionSymbolProcessOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased, CollectionsKt.listOf(resultOfIntersection));
        Iterator it2 = resultOfIntersection.getOverriddenMembers().iterator();
        while (it2.hasNext()) {
            getOverrideByBase().put((FirNamedFunctionSymbol) ((MemberWithBaseScope) it2.next()).component1(), firNamedFunctionSymbolProcessOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedFunctionSymbol processOverridesForFunctionsWithErasedValueParameterIfDeclaredFunctionParametersAreErased(Name name, FirNamedFunctionSymbol explicitlyDeclaredFunction, FirNamedFunctionSymbol relevantFunctionFromSupertypes, FirNamedFunctionSymbol relevantFunctionFromSupertypesUnwrapped) {
        Object next;
        FirNamedFunctionSymbol value;
        Iterator<T> it = FirScopeKt.getFunctions(getDeclaredMemberScope(), name).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) next;
            if (hasSameJvmDescriptor(firNamedFunctionSymbol, relevantFunctionFromSupertypesUnwrapped) && hasErasedParameters(firNamedFunctionSymbol) && getJavaOverrideChecker().doesReturnTypesHaveSameKind((FirNamedFunction) relevantFunctionFromSupertypesUnwrapped.getFir(), (FirNamedFunction) firNamedFunctionSymbol.getFir())) {
                break;
            }
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) next;
        if (firNamedFunctionSymbol2 == null || (value = JavaClassUseSiteMemberScopeKt.getRenamedFunctionsCache(getSession()).getDeclaredFunctionCopyWithParameterTypesFromSupertypeCache().getValue(TuplesKt.to(firNamedFunctionSymbol2, name), relevantFunctionFromSupertypes)) == null) {
            return null;
        }
        if (explicitlyDeclaredFunction == null || !FirOverrideCheckerKt.isOverriddenFunction(getOverrideChecker(), value, explicitlyDeclaredFunction)) {
            explicitlyDeclaredFunction = null;
        }
        return explicitlyDeclaredFunction == null ? value : JavaClassUseSiteMemberScopeKt.getRenamedFunctionsCache(getSession()).getAccidentalOverrideWithDeclaredFunctionHiddenCopyIfDeclaredFunctionParametersAreErasedCache().getValue(TuplesKt.to(explicitlyDeclaredFunction, name), new FirRenamedForOverrideSymbolsStorage.AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext(firNamedFunctionSymbol2, this.klass));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedFunctionSymbol processOverridesForFunctionsWithErasedValueParameterIfInheritedFunctionParametersAreErased(Name name, FirNamedFunctionSymbol relevantFunctionFromSupertypes, FirNamedFunctionSymbol relevantFunctionFromSupertypesUnwrapped) {
        Object next;
        if (!hasErasedParameters(relevantFunctionFromSupertypesUnwrapped)) {
            return null;
        }
        Iterator<T> it = FirScopeKt.getFunctions(getDeclaredMemberScope(), name).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) next;
            if (hasSameJvmDescriptor(firNamedFunctionSymbol, relevantFunctionFromSupertypes) && !hasErasedParameters(firNamedFunctionSymbol) && getJavaOverrideChecker().doesReturnTypesHaveSameKind((FirNamedFunction) relevantFunctionFromSupertypesUnwrapped.getFir(), (FirNamedFunction) firNamedFunctionSymbol.getFir())) {
                break;
            }
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) next;
        if (firNamedFunctionSymbol2 == null) {
            return null;
        }
        return FirOverrideCheckerKt.isOverriddenFunction(getOverrideChecker(), firNamedFunctionSymbol2, relevantFunctionFromSupertypes) ? JavaClassUseSiteMemberScopeKt.getRenamedFunctionsCache(getSession()).getAccidentalOverrideWithDeclaredFunctionHiddenCopyIfInheritedFunctionParametersAreErasedCache().getValue(TuplesKt.to(relevantFunctionFromSupertypes, name), this.klass) : relevantFunctionFromSupertypes;
    }

    private final void processSpecialFunctions(Name name, List<? extends Pair<? extends FirTypeScope, ? extends List<? extends FirNamedFunctionSymbol>>> functionsFromSupertypes, Collection<FirNamedFunctionSymbol> destination) {
        Object next;
        ArrayList arrayList = new ArrayList();
        JavaOverrideChecker javaOverrideChecker = new JavaOverrideChecker(getSession(), this.klass, getSuperTypeScopes(), false);
        List list = CollectionsKt.toList(destination);
        for (FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol> resultOfIntersection : getSupertypeScopeContext().convertGroupedCallablesToIntersectionResults(functionsFromSupertypes)) {
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) this.extractSomeSymbolFromSuperType(resultOfIntersection);
            Iterator it = list.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!FirOverrideCheckerKt.isOverriddenFunction(javaOverrideChecker, (FirNamedFunctionSymbol) next, firNamedFunctionSymbol));
            FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) next;
            JavaClassUseSiteMemberScope javaClassUseSiteMemberScope = this;
            Name name2 = name;
            Collection<FirNamedFunctionSymbol> collection = destination;
            if (!javaClassUseSiteMemberScope.processOverridesForFunctionsWithDifferentJvmName(firNamedFunctionSymbol, firNamedFunctionSymbol2, name2, resultOfIntersection, collection, arrayList) && !javaClassUseSiteMemberScope.processOverridesForFunctionsWithErasedValueParameter(name2, collection, resultOfIntersection, firNamedFunctionSymbol2)) {
                if (firNamedFunctionSymbol2 == null) {
                    FirNamedFunctionSymbol firNamedFunctionSymbol3 = (FirNamedFunctionSymbol) resultOfIntersection.getChosenSymbol();
                    if (javaClassUseSiteMemberScope.isVisibleInCurrentClass(firNamedFunctionSymbol3)) {
                        collection.add(firNamedFunctionSymbol3);
                        arrayList.add(resultOfIntersection);
                    }
                } else {
                    collection.add(firNamedFunctionSymbol2);
                    javaClassUseSiteMemberScope.getDirectOverriddenFunctions().put(firNamedFunctionSymbol2, CollectionsKt.listOf(resultOfIntersection));
                    Iterator it2 = resultOfIntersection.getOverriddenMembers().iterator();
                    while (it2.hasNext()) {
                        javaClassUseSiteMemberScope.getOverrideByBase().put(((MemberWithBaseScope) it2.next()).getMember(), firNamedFunctionSymbol2);
                    }
                }
            }
            this = javaClassUseSiteMemberScope;
            name = name2;
            destination = collection;
        }
        this.getFunctionsFromSupertypes().put(name, arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setOverrides(FirNamedFunctionSymbol override, List<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> overridden) {
        getDirectOverriddenFunctions().put(override, overridden);
        Iterator it = overridden.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((FirTypeIntersectionScopeContext.ResultOfIntersection) it.next()).getOverriddenMembers().iterator();
            while (it2.hasNext()) {
                getOverrideByBase().put(((MemberWithBaseScope) it2.next()).getMember(), override);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        if (!SpecialGenericSignatures.Companion.getSameAsBuiltinMethodWithErasedValueParameters(firNamedFunctionSymbol.getName())) {
            return false;
        }
        FirTypeIntersectionScopeContext supertypeScopeContext = getSupertypeScopeContext();
        Name name = firNamedFunctionSymbol.getName();
        List<FirTypeScope> scopes = supertypeScopeContext.getScopes();
        ArrayList arrayList = new ArrayList();
        for (FirTypeScope firTypeScope : scopes) {
            final ArrayList arrayList2 = new ArrayList();
            firTypeScope.processFunctionsByName(name, new Function1<FirNamedFunctionSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.java.scopes.JavaClassUseSiteMemberScope$shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters$$inlined$collectIntersectionResultsForCallables$1
                public final void invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2) {
                    firNamedFunctionSymbol2.getClass();
                    if (firNamedFunctionSymbol2 instanceof FirConstructorSymbol) {
                        return;
                    }
                    arrayList2.add(firNamedFunctionSymbol2);
                }

                /* JADX WARN: Multi-variable type inference failed */
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((FirNamedFunctionSymbol) obj);
                    return Unit.INSTANCE;
                }
            });
            if (arrayList2.isEmpty()) {
                arrayList2 = null;
            }
            Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        List listConvertGroupedCallablesToIntersectionResults = supertypeScopeContext.convertGroupedCallablesToIntersectionResults(arrayList);
        ArrayList arrayList3 = new ArrayList();
        Iterator it = listConvertGroupedCallablesToIntersectionResults.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList3, ((FirTypeIntersectionScopeContext.ResultOfIntersection) it.next()).getOverriddenMembers());
        }
        ArrayList<MemberWithBaseScope> arrayList4 = new ArrayList();
        for (Object obj : arrayList3) {
            List<FirValueParameterSymbol> valueParameterSymbols = ((FirNamedFunctionSymbol) ((MemberWithBaseScope) obj).component1()).getValueParameterSymbols();
            if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
                Iterator<T> it2 = valueParameterSymbols.iterator();
                while (it2.hasNext()) {
                    if (!ConeBuiltinTypeUtilsKt.isAny(ConeTypeUtilsKt.lowerBoundIfFlexible(((FirValueParameterSymbol) it2.next()).getResolvedReturnType()))) {
                        arrayList4.add(obj);
                        break;
                    }
                }
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (MemberWithBaseScope memberWithBaseScope : arrayList4) {
            FirNamedFunctionSymbol overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava((FirNamedFunctionSymbol) memberWithBaseScope.component1(), memberWithBaseScope.getBaseScope());
            if (overriddenBuiltinFunctionWithErasedValueParametersInJava != null) {
                arrayList5.add(overriddenBuiltinFunctionWithErasedValueParametersInJava);
            }
        }
        String strComputeJvmDescriptor$default = computeJvmDescriptor$default(this, (FirFunction) firNamedFunctionSymbol.getFir(), null, false, 3, null);
        if (arrayList5.isEmpty()) {
            return false;
        }
        Iterator it3 = arrayList5.iterator();
        while (it3.hasNext()) {
            if (Intrinsics.areEqual(computeJvmDescriptor$default(this, (FirFunction) ((FirNamedFunctionSymbol) it3.next()).getFir(), null, false, 3, null), strComputeJvmDescriptor$default) && hasErasedParameters(firNamedFunctionSymbol)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirRegularClass toFir(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType, firSession);
        if (symbol instanceof FirRegularClassSymbol) {
            return (FirRegularClass) ((FirRegularClassSymbol) symbol).getFir();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope
    public Collection<FirNamedFunctionSymbol> collectFunctions(Name name) {
        name.getClass();
        ArrayList arrayList = new ArrayList();
        collectDeclaredFunctions(name, arrayList);
        Set<? extends FirNamedFunctionSymbol> set = CollectionsKt.toSet(arrayList);
        List<FirTypeScope> scopes = getSupertypeScopeContext().getScopes();
        ArrayList arrayList2 = new ArrayList();
        for (FirTypeScope firTypeScope : scopes) {
            final ArrayList arrayList3 = new ArrayList();
            firTypeScope.processFunctionsByName(name, new Function1<FirNamedFunctionSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.java.scopes.JavaClassUseSiteMemberScope$collectFunctions$$inlined$collectMembersGroupedByScope$1
                public final void invoke(FirNamedFunctionSymbol firNamedFunctionSymbol) {
                    firNamedFunctionSymbol.getClass();
                    if (firNamedFunctionSymbol instanceof FirConstructorSymbol) {
                        return;
                    }
                    arrayList3.add(firNamedFunctionSymbol);
                }

                /* JADX WARN: Multi-variable type inference failed */
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((FirNamedFunctionSymbol) obj);
                    return Unit.INSTANCE;
                }
            });
            if (arrayList3.isEmpty()) {
                arrayList3 = null;
            }
            Pair pair = arrayList3 != null ? TuplesKt.to(firTypeScope, arrayList3) : null;
            if (pair != null) {
                arrayList2.add(pair);
            }
        }
        SpecialGenericSignatures.Companion companion = SpecialGenericSignatures.Companion;
        if (!companion.getSameAsRenamedInJvmBuiltin(name) && !companion.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            if (!arrayList2.isEmpty()) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    Iterable iterable = (Iterable) ((Pair) it.next()).getSecond();
                    if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                        Iterator it2 = iterable.iterator();
                        while (it2.hasNext()) {
                            if (((FirNamedFunctionSymbol) it2.next()).getRawStatus().isSuspend()) {
                            }
                        }
                    }
                }
            }
            super.collectFunctionsFromSupertypes(name, arrayList, set);
            return arrayList;
        }
        processSpecialFunctions(name, arrayList2, arrayList);
        return CollectionsKt.toSet(arrayList);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope
    public Collection<FirVariableSymbol<?>> collectProperties(Name name) {
        List listEmptyList;
        FirVariableSymbol firVariableSymbol;
        name.getClass();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        final LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        final LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        getDeclaredMemberScope().processPropertiesByName(name, new Function1() { // from class: gc7
            public final Object invoke(Object obj) {
                return JavaClassUseSiteMemberScope.k(linkedHashSet, linkedHashSet2, linkedHashSet3, (FirVariableSymbol) obj);
            }
        });
        FirTypeIntersectionScopeContext supertypeScopeContext = getSupertypeScopeContext();
        List<FirTypeScope> scopes = supertypeScopeContext.getScopes();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = scopes.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            FirTypeScope firTypeScope = (FirTypeScope) it.next();
            final ArrayList arrayList2 = new ArrayList();
            firTypeScope.processPropertiesByName(name, new Function1<FirVariableSymbol<?>, Unit>() { // from class: org.jetbrains.kotlin.fir.java.scopes.JavaClassUseSiteMemberScope$collectProperties$$inlined$collectIntersectionResultsForCallables$1
                public final void invoke(FirVariableSymbol<?> firVariableSymbol2) {
                    firVariableSymbol2.getClass();
                    if (firVariableSymbol2 instanceof FirConstructorSymbol) {
                        return;
                    }
                    arrayList2.add(firVariableSymbol2);
                }

                /* JADX WARN: Multi-variable type inference failed */
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((FirVariableSymbol<?>) obj);
                    return Unit.INSTANCE;
                }
            });
            if (arrayList2.isEmpty()) {
                arrayList2 = null;
            }
            Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        List listConvertGroupedCallablesToIntersectionResults = supertypeScopeContext.convertGroupedCallablesToIntersectionResults(arrayList);
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : listConvertGroupedCallablesToIntersectionResults) {
            FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection = (FirTypeIntersectionScopeContext.ResultOfIntersection) obj;
            if ((resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) && (((FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) resultOfIntersection).getChosenSymbol() instanceof FirFieldSymbol)) {
                arrayList3.add(obj);
            } else {
                arrayList4.add(obj);
            }
        }
        Pair pair2 = new Pair(arrayList3, arrayList4);
        List list = (List) pair2.component1();
        List<FirTypeIntersectionScopeContext.ResultOfIntersection> list2 = (List) pair2.component2();
        list.size();
        FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection2 = (FirTypeIntersectionScopeContext.ResultOfIntersection) CollectionsKt.firstOrNull(list);
        if (resultOfIntersection2 != null && (firVariableSymbol = (FirVariableSymbol) resultOfIntersection2.getChosenSymbol()) != null) {
            if (!(firVariableSymbol instanceof FirFieldSymbol)) {
                w01.a("Failed requirement.");
                return null;
            }
            if (!linkedHashSet2.contains(((FirFieldSymbol) firVariableSymbol).getName())) {
                linkedHashSet3.add(firVariableSymbol);
            }
        }
        list2.getClass();
        for (FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection3 : list2) {
            ConeKotlinType resolvedReceiverType = ((FirPropertySymbol) resultOfIntersection3.getChosenSymbol()).getResolvedReceiverType();
            List<FirValueParameterSymbol> contextParameterSymbols = ((FirPropertySymbol) resultOfIntersection3.getChosenSymbol()).getContextParameterSymbols();
            if (contextParameterSymbols.isEmpty()) {
                listEmptyList = null;
            } else {
                List<FirValueParameterSymbol> list3 = contextParameterSymbols;
                listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                Iterator<T> it2 = list3.iterator();
                while (it2.hasNext()) {
                    listEmptyList.add(((FirValueParameterSymbol) it2.next()).getResolvedReturnType());
                }
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            FirSyntheticPropertySymbol value = this.syntheticPropertyCache.getValue(new SyntheticPropertiesCacheKey(name, resolvedReceiverType, listEmptyList), TuplesKt.to(this, resultOfIntersection3));
            FirPropertySymbol firPropertySymbol = value != null ? value : (FirPropertySymbol) resultOfIntersection3.getChosenSymbol();
            getDirectOverriddenProperties().put(firPropertySymbol, CollectionsKt.listOf(resultOfIntersection3));
            Iterator it3 = resultOfIntersection3.getOverriddenMembers().iterator();
            while (it3.hasNext()) {
                getOverrideByBase().put(((MemberWithBaseScope) it3.next()).getMember(), value);
            }
            linkedHashSet3.add(firPropertySymbol);
        }
        return linkedHashSet3;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope
    public boolean isOverriddenFunction(FirNamedFunctionSymbol overrideCandidate, FirNamedFunctionSymbol baseDeclaration, FirTypeScope baseScope) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        if (super.isOverriddenFunction(overrideCandidate, baseDeclaration, baseScope)) {
            return baseScope == null || BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(baseDeclaration, baseScope) == null || hasErasedParameters(overrideCandidate);
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope
    public boolean isVisibleInCurrentClass(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        List propertyNamesCandidatesByAccessorName = PropertiesConventionUtilKt.getPropertyNamesCandidatesByAccessorName(firNamedFunctionSymbol.getName());
        if (!(propertyNamesCandidatesByAccessorName instanceof Collection) || !propertyNamesCandidatesByAccessorName.isEmpty()) {
            Iterator it = propertyNamesCandidatesByAccessorName.iterator();
            while (it.hasNext()) {
                List<FirVariableSymbol<?>> properties = FirScopeKt.getProperties(this, (Name) it.next());
                if (!(properties instanceof Collection) || !properties.isEmpty()) {
                    Iterator<T> it2 = properties.iterator();
                    while (it2.hasNext()) {
                        FirVariableSymbol firVariableSymbol = (FirVariableSymbol) it2.next();
                        if (firVariableSymbol instanceof FirPropertySymbol) {
                            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firVariableSymbol;
                            if (FirVisibilityCheckerKt.isVisibleInClass(firVariableSymbol, this.klass.getSymbol(), firPropertySymbol.getRawStatus()) && isOverriddenInClassBy(firPropertySymbol, firNamedFunctionSymbol)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return (doesOverrideRenamedBuiltins(firNamedFunctionSymbol) || shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(firNamedFunctionSymbol)) ? false : true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope
    public FirNamedFunctionSymbol replaceWithWrapperSymbolIfNeeded(FirNamedFunctionSymbol firNamedFunctionSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        firNamedFunctionSymbol.getClass();
        FirDeclarationOrigin origin = firNamedFunctionSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            D fir = firNamedFunctionSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return firNamedFunctionSymbol;
            }
        }
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.lastOrNull(((FirNamedFunction) firNamedFunctionSymbol.getFir()).getValueParameters());
        if (firValueParameter != null) {
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(getOwnerClassLookupTag(), getSession());
            FirClassLikeDeclaration firClassLikeDeclaration = symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null;
            FirJavaClass firJavaClass = firClassLikeDeclaration instanceof FirJavaClass ? (FirJavaClass) firClassLikeDeclaration : null;
            if (firJavaClass != null) {
                FirTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
                FirSession session = getSession();
                JavaTypeParameterStack javaTypeParameterStack = firJavaClass.getJavaTypeParameterStack();
                KtSourceElement source = firNamedFunctionSymbol.getSource();
                FirResolvedTypeRef firResolvedTypeRefResolveIfJavaType$default = JavaTypeConversionKt.resolveIfJavaType$default(returnTypeRef, session, javaTypeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
                FirResolvedTypeRef firResolvedTypeRef = firResolvedTypeRefResolveIfJavaType$default instanceof FirResolvedTypeRef ? firResolvedTypeRefResolveIfJavaType$default : null;
                ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                if (coneType == null) {
                    coneType = null;
                }
                ConeRigidType coneRigidTypeLowerBoundIfFlexible = coneType != null ? ConeTypeUtilsKt.lowerBoundIfFlexible(coneType) : null;
                ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
                if (coneClassLikeType != null && Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId().asSingleFqName(), StandardNames.CONTINUATION_INTERFACE_FQ_NAME)) {
                    final FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir();
                    final ConeKotlinType type = ConeTypeProjectionKt.getType(coneClassLikeType.getTypeArguments()[0]);
                    if (type != null) {
                        return Companion.buildMaybeJavaFunctionCopy$default(INSTANCE, firNamedFunction, new FirNamedFunctionSymbol(firNamedFunctionSymbol.getCallableId()), null, new Function1() { // from class: hc7
                            public final Object invoke(Object obj) {
                                return JavaClassUseSiteMemberScope.j(firNamedFunction, type, (FirFunctionBuilder) obj);
                            }
                        }, 4, null).getSymbol();
                    }
                }
            }
        }
        return firNamedFunctionSymbol;
    }

    public final FirSyntheticPropertySymbol syntheticPropertyFromOverride$org_jetbrains_kotlin_fir_jvm(FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol> overriddenProperty) {
        FirSyntheticPropertySymbol firSyntheticPropertySymbol;
        overriddenProperty.getClass();
        Iterator it = overriddenProperty.getOverriddenMembers().iterator();
        do {
            firSyntheticPropertySymbol = null;
            if (!it.hasNext()) {
                break;
            }
            MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) it.next();
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) memberWithBaseScope.component1();
            FirTypeScope firTypeScopeComponent2 = memberWithBaseScope.getBaseScope();
            if (FirVisibilityCheckerKt.isVisibleInClass(firPropertySymbol, this.klass.getSymbol(), firPropertySymbol.getRawStatus())) {
                FirSyntheticPropertySymbol firSyntheticPropertySymbolCreateOverridePropertyIfExists = createOverridePropertyIfExists(firPropertySymbol, getDeclaredMemberScope(), true);
                if (firSyntheticPropertySymbolCreateOverridePropertyIfExists == null) {
                    for (FirTypeScope firTypeScope : getSuperTypeScopes()) {
                        FirSyntheticPropertySymbol firSyntheticPropertySymbolCreateOverridePropertyIfExists2 = Intrinsics.areEqual(firTypeScope, firTypeScopeComponent2) ? null : createOverridePropertyIfExists(firPropertySymbol, firTypeScope, false);
                        if (firSyntheticPropertySymbolCreateOverridePropertyIfExists2 != null) {
                            firSyntheticPropertySymbol = firSyntheticPropertySymbolCreateOverridePropertyIfExists2;
                            break;
                        }
                    }
                } else {
                    firSyntheticPropertySymbol = firSyntheticPropertySymbolCreateOverridePropertyIfExists;
                }
            }
        } while (firSyntheticPropertySymbol == null);
        return firSyntheticPropertySymbol;
    }

    public String toString() {
        return "Java use site scope of " + getOwnerClassLookupTag().getClassId();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public JavaClassUseSiteMemberScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirJavaClass firJavaClass = this.klass;
        List<FirTypeScope> superTypeScopes = getSuperTypeScopes();
        List arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superTypeScopes, 10));
        Iterator<T> it = superTypeScopes.iterator();
        boolean z = false;
        while (true) {
            FirScope firScope = null;
            if (!it.hasNext()) {
                break;
            }
            FirScope firScope2 = (FirScope) it.next();
            FirScope firScopeWithReplacedSessionOrNull = firScope2.withReplacedSessionOrNull(newSession, newScopeSession);
            if (firScopeWithReplacedSessionOrNull != null) {
                z = true;
                firScope = firScopeWithReplacedSessionOrNull;
            }
            FirTypeScope firTypeScope = (FirTypeScope) firScope;
            if (firTypeScope != null) {
                firScope2 = firTypeScope;
            }
            arrayList.add(firScope2);
        }
        if (!z) {
            arrayList = null;
        }
        if (arrayList == null) {
            arrayList = getSuperTypeScopes();
        }
        FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull = getDeclaredMemberScope().withReplacedSessionOrNull(newSession, newScopeSession);
        if (firContainingNamesAwareScopeWithReplacedSessionOrNull == null) {
            firContainingNamesAwareScopeWithReplacedSessionOrNull = getDeclaredMemberScope();
        }
        return new JavaClassUseSiteMemberScope(firJavaClass, newSession, arrayList, firContainingNamesAwareScopeWithReplacedSessionOrNull);
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ \u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0005J&\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ;\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\b2\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001c¢\u0006\u0002\b\u001fH\u0002¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassUseSiteMemberScope$Companion;", Argument.Delimiters.none, "<init>", "()V", "createCopyWithNaturalName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "originalSymbol", "naturalName", "Lorg/jetbrains/kotlin/name/Name;", "klass", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "isHidden", Argument.Delimiters.none, "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "createDeclaredFunctionCopyWithParameterTypesFromSupertype", "explicitlyDeclaredFunctionWithErasedValueParameters", ModuleXmlParser.NAME, "relevantFunctionFromSupertypes", "createAccidentalOverrideWithDeclaredFunctionHiddenIfDeclaredFunctionParametersAreErasedCopy", "accidentalOverrideWithDeclaredFunction", "createAccidentalOverrideWithDeclaredFunctionHiddenIfInheritedFunctionParametersAreErasedCopy", "buildMaybeJavaFunctionCopy", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "original", "newSymbol", "newName", "builder", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFunctionBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static Unit a(FirJavaClass firJavaClass, FirFunctionBuilder firFunctionBuilder) {
            firFunctionBuilder.getClass();
            firFunctionBuilder.setDispatchReceiverType(ScopeUtilsKt.defaultType(firJavaClass));
            return Unit.INSTANCE;
        }

        public static Unit b(FirJavaClass firJavaClass, FirFunctionBuilder firFunctionBuilder) {
            firFunctionBuilder.getClass();
            firFunctionBuilder.setDispatchReceiverType(ScopeUtilsKt.defaultType(firJavaClass));
            return Unit.INSTANCE;
        }

        private final FirNamedFunction buildMaybeJavaFunctionCopy(FirNamedFunction original, FirNamedFunctionSymbol newSymbol, Name newName, Function1<? super FirFunctionBuilder, Unit> builder) {
            if (original instanceof FirJavaMethod) {
                FirJavaMethod firJavaMethod = (FirJavaMethod) original;
                FirJavaMethodBuilder firJavaMethodBuilder = new FirJavaMethodBuilder();
                firJavaMethodBuilder.setSource(firJavaMethod.getSource());
                firJavaMethodBuilder.setModuleData(firJavaMethod.getModuleData());
                firJavaMethodBuilder.setAttributes(firJavaMethod.getAttributes().copy());
                firJavaMethodBuilder.setReturnTypeRef(firJavaMethod.getReturnTypeRef());
                firJavaMethodBuilder.getValueParameters().addAll(firJavaMethod.getValueParameters());
                firJavaMethodBuilder.setBody(firJavaMethod.getBody());
                firJavaMethodBuilder.setStatus(firJavaMethod.getStatus());
                firJavaMethodBuilder.setDispatchReceiverType(firJavaMethod.getDispatchReceiverType());
                firJavaMethodBuilder.setName(firJavaMethod.getName());
                firJavaMethodBuilder.setSymbol(firJavaMethod.getSymbol());
                firJavaMethodBuilder.setFromSource(firJavaMethod.getOrigin().getFromSource());
                firJavaMethodBuilder.getTypeParameters().addAll(firJavaMethod.getTypeParameters());
                firJavaMethodBuilder.setAnnotationList(firJavaMethod.getAnnotationList());
                firJavaMethodBuilder.setContainingClassSymbol(firJavaMethod.getContainingClassSymbol());
                firJavaMethodBuilder.setSymbol(newSymbol);
                firJavaMethodBuilder.setName(newName);
                builder.invoke(firJavaMethodBuilder);
                return firJavaMethodBuilder.mo288build();
            }
            FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
            firNamedFunctionBuilder.setSource(original.getSource());
            firNamedFunctionBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(original));
            firNamedFunctionBuilder.setModuleData(original.getModuleData());
            firNamedFunctionBuilder.setOrigin(original.getOrigin());
            firNamedFunctionBuilder.setAttributes(original.getAttributes().copy());
            firNamedFunctionBuilder.setStatus(original.getStatus());
            firNamedFunctionBuilder.setLocal(original.getIsLocal());
            firNamedFunctionBuilder.setReturnTypeRef(original.getReturnTypeRef());
            firNamedFunctionBuilder.setReceiverParameter(original.getReceiverParameter());
            firNamedFunctionBuilder.setDeprecationsProvider(original.getDeprecationsProvider());
            firNamedFunctionBuilder.setContainerSource(original.getContainerSource());
            firNamedFunctionBuilder.setDispatchReceiverType(original.getDispatchReceiverType());
            firNamedFunctionBuilder.getContextParameters().addAll(original.getContextParameters());
            firNamedFunctionBuilder.getValueParameters().addAll(original.getValueParameters());
            firNamedFunctionBuilder.setBody(original.getBody());
            firNamedFunctionBuilder.setContractDescription(original.getContractDescription());
            firNamedFunctionBuilder.setName(original.getName());
            firNamedFunctionBuilder.getAnnotations().addAll(original.getAnnotations());
            firNamedFunctionBuilder.getTypeParameters().addAll(original.getTypeParameters());
            firNamedFunctionBuilder.setSymbol(newSymbol);
            firNamedFunctionBuilder.setName(newName);
            builder.invoke(firNamedFunctionBuilder);
            return firNamedFunctionBuilder.mo288build();
        }

        public static /* synthetic */ FirNamedFunction buildMaybeJavaFunctionCopy$default(Companion companion, FirNamedFunction firNamedFunction, FirNamedFunctionSymbol firNamedFunctionSymbol, Name name, Function1 function1, int i, Object obj) {
            if ((i & 4) != 0) {
                name = firNamedFunction.getName();
            }
            return companion.buildMaybeJavaFunctionCopy(firNamedFunction, firNamedFunctionSymbol, name, function1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final FirNamedFunctionSymbol createAccidentalOverrideWithDeclaredFunctionHiddenIfDeclaredFunctionParametersAreErasedCopy(FirNamedFunctionSymbol accidentalOverrideWithDeclaredFunction, Name name, FirNamedFunctionSymbol explicitlyDeclaredFunctionWithErasedValueParameters, final FirJavaClass klass) {
            accidentalOverrideWithDeclaredFunction.getClass();
            name.getClass();
            explicitlyDeclaredFunctionWithErasedValueParameters.getClass();
            klass.getClass();
            FirNamedFunction firNamedFunctionBuildMaybeJavaFunctionCopy = buildMaybeJavaFunctionCopy((FirNamedFunction) accidentalOverrideWithDeclaredFunction.getFir(), new FirNamedFunctionSymbol(accidentalOverrideWithDeclaredFunction.getCallableId()), name, new Function1() { // from class: kc7
                public final Object invoke(Object obj) {
                    return JavaClassUseSiteMemberScope.Companion.b(klass, (FirFunctionBuilder) obj);
                }
            });
            ClassMembersKt.setInitialSignatureAttr(firNamedFunctionBuildMaybeJavaFunctionCopy, explicitlyDeclaredFunctionWithErasedValueParameters);
            DeprecationUtilsKt.setHiddenToOvercomeSignatureClash(firNamedFunctionBuildMaybeJavaFunctionCopy, Boolean.TRUE);
            return firNamedFunctionBuildMaybeJavaFunctionCopy.getSymbol();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final FirNamedFunctionSymbol createAccidentalOverrideWithDeclaredFunctionHiddenIfInheritedFunctionParametersAreErasedCopy(FirNamedFunctionSymbol relevantFunctionFromSupertypes, Name name, final FirJavaClass klass) {
            relevantFunctionFromSupertypes.getClass();
            name.getClass();
            klass.getClass();
            FirNamedFunction firNamedFunctionBuildMaybeJavaFunctionCopy = buildMaybeJavaFunctionCopy((FirNamedFunction) relevantFunctionFromSupertypes.getFir(), new FirNamedFunctionSymbol(relevantFunctionFromSupertypes.getCallableId()), name, new Function1() { // from class: jc7
                public final Object invoke(Object obj) {
                    return JavaClassUseSiteMemberScope.Companion.a(klass, (FirFunctionBuilder) obj);
                }
            });
            DeprecationUtilsKt.setHiddenToOvercomeSignatureClash(firNamedFunctionBuildMaybeJavaFunctionCopy, Boolean.TRUE);
            return firNamedFunctionBuildMaybeJavaFunctionCopy.getSymbol();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final FirNamedFunctionSymbol createCopyWithNaturalName(FirNamedFunctionSymbol originalSymbol, Name naturalName, FirJavaClass klass, boolean isHidden, FirDeclarationOrigin origin) {
            FirNamedFunction firNamedFunctionBuild;
            FirDeclarationStatus status;
            originalSymbol.getClass();
            naturalName.getClass();
            klass.getClass();
            FirNamedFunction firNamedFunction = (FirNamedFunction) originalSymbol.getFir();
            FirNamedFunctionSymbol firNamedFunctionSymbol = new FirNamedFunctionSymbol(originalSymbol.getCallableId().copy(naturalName));
            if (firNamedFunction instanceof FirJavaMethod) {
                FirJavaMethod firJavaMethod = (FirJavaMethod) firNamedFunction;
                FirJavaMethodBuilder firJavaMethodBuilder = new FirJavaMethodBuilder();
                firJavaMethodBuilder.setSource(firJavaMethod.getSource());
                firJavaMethodBuilder.setModuleData(firJavaMethod.getModuleData());
                firJavaMethodBuilder.setAttributes(firJavaMethod.getAttributes().copy());
                firJavaMethodBuilder.setReturnTypeRef(firJavaMethod.getReturnTypeRef());
                firJavaMethodBuilder.getValueParameters().addAll(firJavaMethod.getValueParameters());
                firJavaMethodBuilder.setBody(firJavaMethod.getBody());
                firJavaMethodBuilder.setStatus(firJavaMethod.getStatus());
                firJavaMethodBuilder.setDispatchReceiverType(firJavaMethod.getDispatchReceiverType());
                firJavaMethodBuilder.setName(firJavaMethod.getName());
                firJavaMethodBuilder.setSymbol(firJavaMethod.getSymbol());
                firJavaMethodBuilder.setFromSource(firJavaMethod.getOrigin().getFromSource());
                firJavaMethodBuilder.getTypeParameters().addAll(firJavaMethod.getTypeParameters());
                firJavaMethodBuilder.setAnnotationList(firJavaMethod.getAnnotationList());
                firJavaMethodBuilder.setContainingClassSymbol(firJavaMethod.getContainingClassSymbol());
                firJavaMethodBuilder.setName(naturalName);
                firJavaMethodBuilder.setSymbol(firNamedFunctionSymbol);
                firJavaMethodBuilder.setDispatchReceiverType(ScopeUtilsKt.defaultType(klass));
                if (OperatorConventions.isConventionName(naturalName)) {
                    FirDeclarationStatus status2 = firJavaMethod.getStatus();
                    status = UtilsKt.copy(status2, (8388575 & 1) != 0 ? status2.getVisibility() : null, (8388575 & 2) != 0 ? status2.getModality() : null, (8388575 & 4) != 0 ? status2.isExpect() : false, (8388575 & 8) != 0 ? status2.isActual() : false, (8388575 & 16) != 0 ? status2.isOverride() : false, (8388575 & 32) != 0 ? status2.isOperator() : true, (8388575 & 64) != 0 ? status2.isInfix() : false, (8388575 & 128) != 0 ? status2.isInline() : false, (8388575 & 256) != 0 ? status2.isValue() : false, (8388575 & 512) != 0 ? status2.isTailRec() : false, (8388575 & 1024) != 0 ? status2.isExternal() : false, (8388575 & 2048) != 0 ? status2.isConst() : false, (8388575 & 4096) != 0 ? status2.isLateInit() : false, (8388575 & 8192) != 0 ? status2.isInner() : false, (8388575 & 16384) != 0 ? status2.isCompanion() : false, (8388575 & 32768) != 0 ? status2.isData() : false, (8388575 & 65536) != 0 ? status2.isSuspend() : false, (8388575 & 131072) != 0 ? status2.isStatic() : false, (8388575 & 262144) != 0 ? status2.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status2.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status2.isFun() : false, (8388575 & 2097152) != 0 ? status2.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status2.getReturnValueStatus() : null);
                } else {
                    status = firJavaMethod.getStatus();
                }
                firJavaMethodBuilder.setStatus(status);
                firNamedFunctionBuild = firJavaMethodBuilder.mo288build();
            } else {
                FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
                firNamedFunctionBuilder.setSource(firNamedFunction.getSource());
                firNamedFunctionBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firNamedFunction));
                firNamedFunctionBuilder.setModuleData(firNamedFunction.getModuleData());
                firNamedFunctionBuilder.setOrigin(firNamedFunction.getOrigin());
                firNamedFunctionBuilder.setAttributes(firNamedFunction.getAttributes().copy());
                firNamedFunctionBuilder.setStatus(firNamedFunction.getStatus());
                firNamedFunctionBuilder.setLocal(firNamedFunction.getIsLocal());
                firNamedFunctionBuilder.setReturnTypeRef(firNamedFunction.getReturnTypeRef());
                firNamedFunctionBuilder.setReceiverParameter(firNamedFunction.getReceiverParameter());
                firNamedFunctionBuilder.setDeprecationsProvider(firNamedFunction.getDeprecationsProvider());
                firNamedFunctionBuilder.setContainerSource(firNamedFunction.getContainerSource());
                firNamedFunctionBuilder.setDispatchReceiverType(firNamedFunction.getDispatchReceiverType());
                firNamedFunctionBuilder.getContextParameters().addAll(firNamedFunction.getContextParameters());
                firNamedFunctionBuilder.getValueParameters().addAll(firNamedFunction.getValueParameters());
                firNamedFunctionBuilder.setBody(firNamedFunction.getBody());
                firNamedFunctionBuilder.setContractDescription(firNamedFunction.getContractDescription());
                firNamedFunctionBuilder.setName(firNamedFunction.getName());
                firNamedFunctionBuilder.getAnnotations().addAll(firNamedFunction.getAnnotations());
                firNamedFunctionBuilder.getTypeParameters().addAll(firNamedFunction.getTypeParameters());
                firNamedFunctionBuilder.setName(naturalName);
                firNamedFunctionBuilder.setSymbol(firNamedFunctionSymbol);
                firNamedFunctionBuilder.setDispatchReceiverType(ScopeUtilsKt.defaultType(klass));
                if (origin != null) {
                    firNamedFunctionBuilder.setOrigin(origin);
                }
                firNamedFunctionBuild = firNamedFunctionBuilder.mo288build();
            }
            ClassMembersKt.setInitialSignatureAttr(firNamedFunctionBuild, firNamedFunction.getSymbol());
            if (isHidden) {
                DeprecationUtilsKt.setHiddenToOvercomeSignatureClash(firNamedFunctionBuild, Boolean.TRUE);
            }
            return firNamedFunctionBuild.getSymbol();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        public final FirNamedFunctionSymbol createDeclaredFunctionCopyWithParameterTypesFromSupertype(FirNamedFunctionSymbol explicitlyDeclaredFunctionWithErasedValueParameters, Name name, FirNamedFunctionSymbol relevantFunctionFromSupertypes) throws KotlinIllegalArgumentExceptionWithAttachments {
            explicitlyDeclaredFunctionWithErasedValueParameters.getClass();
            name.getClass();
            relevantFunctionFromSupertypes.getClass();
            D fir = explicitlyDeclaredFunctionWithErasedValueParameters.getFir();
            fir.getClass();
            FirJavaMethod firJavaMethod = (FirJavaMethod) fir;
            FirJavaMethodBuilder firJavaMethodBuilder = new FirJavaMethodBuilder();
            firJavaMethodBuilder.setSource(firJavaMethod.getSource());
            firJavaMethodBuilder.setModuleData(firJavaMethod.getModuleData());
            firJavaMethodBuilder.setAttributes(firJavaMethod.getAttributes().copy());
            firJavaMethodBuilder.setReturnTypeRef(firJavaMethod.getReturnTypeRef());
            firJavaMethodBuilder.getValueParameters().addAll(firJavaMethod.getValueParameters());
            firJavaMethodBuilder.setBody(firJavaMethod.getBody());
            firJavaMethodBuilder.setStatus(firJavaMethod.getStatus());
            firJavaMethodBuilder.setDispatchReceiverType(firJavaMethod.getDispatchReceiverType());
            firJavaMethodBuilder.setName(firJavaMethod.getName());
            firJavaMethodBuilder.setSymbol(firJavaMethod.getSymbol());
            firJavaMethodBuilder.setFromSource(firJavaMethod.getOrigin().getFromSource());
            firJavaMethodBuilder.getTypeParameters().addAll(firJavaMethod.getTypeParameters());
            firJavaMethodBuilder.setAnnotationList(firJavaMethod.getAnnotationList());
            firJavaMethodBuilder.setContainingClassSymbol(firJavaMethod.getContainingClassSymbol());
            firJavaMethodBuilder.setName(name);
            firJavaMethodBuilder.setSymbol(new FirNamedFunctionSymbol(explicitlyDeclaredFunctionWithErasedValueParameters.getCallableId()));
            firJavaMethodBuilder.getValueParameters().clear();
            List<Pair> listZip = CollectionsKt.zip(((FirNamedFunction) explicitlyDeclaredFunctionWithErasedValueParameters.getFir()).getValueParameters(), ((FirNamedFunction) relevantFunctionFromSupertypes.getFir()).getValueParameters());
            List<FirValueParameter> valueParameters = firJavaMethodBuilder.getValueParameters();
            boolean z = true;
            for (Pair pair : listZip) {
                FirValueParameter firValueParameter = (FirValueParameter) pair.component1();
                FirValueParameter firValueParameter2 = (FirValueParameter) pair.component2();
                if (!ConeBuiltinTypeUtilsKt.isAny(ConeTypeUtilsKt.lowerBoundIfFlexible(FirTypeUtilsKt.getConeType(firValueParameter2.getReturnTypeRef())))) {
                    z = false;
                }
                firValueParameter.getClass();
                FirJavaValueParameter firJavaValueParameter = (FirJavaValueParameter) firValueParameter;
                FirJavaValueParameterBuilder firJavaValueParameterBuilder = new FirJavaValueParameterBuilder();
                firJavaValueParameterBuilder.setSource(firJavaValueParameter.getSource());
                firJavaValueParameterBuilder.setModuleData(firJavaValueParameter.getModuleData());
                firJavaValueParameterBuilder.setAttributes(firJavaValueParameter.getAttributes().copy());
                firJavaValueParameterBuilder.setFromSource(firJavaValueParameter.getOrigin().getFromSource());
                firJavaValueParameterBuilder.setReturnTypeRef(firJavaValueParameter.getReturnTypeRef());
                firJavaValueParameterBuilder.setName(firJavaValueParameter.getName());
                firJavaValueParameterBuilder.setAnnotationList(firJavaValueParameter.getAnnotationList());
                firJavaValueParameterBuilder.setDefaultValue(firJavaValueParameter.getLazyDefaultValue());
                firJavaValueParameterBuilder.setContainingDeclarationSymbol(firJavaValueParameter.getContainingDeclarationSymbol());
                firJavaValueParameterBuilder.setVararg(firJavaValueParameter.getIsVararg());
                firJavaValueParameterBuilder.setReturnTypeRef(firValueParameter2.getReturnTypeRef());
                valueParameters.add(firJavaValueParameterBuilder.build());
            }
            FirJavaMethod firJavaMethodBuild = firJavaMethodBuilder.mo288build();
            ClassMembersKt.setInitialSignatureAttr(firJavaMethodBuild, explicitlyDeclaredFunctionWithErasedValueParameters);
            FirNamedFunctionSymbol symbol = firJavaMethodBuild.getSymbol();
            if (z) {
                return null;
            }
            return symbol;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedFunctionSymbol findGetterOverride(FirPropertySymbol firPropertySymbol, FirScope firScope) {
        String strAsString;
        Name builtinSpecialPropertyGetterName = getCanUseSpecialGetters() ? getBuiltinSpecialPropertyGetterName(firPropertySymbol) : null;
        if (builtinSpecialPropertyGetterName == null || (strAsString = builtinSpecialPropertyGetterName.asString()) == null) {
            String strAsString2 = ((FirProperty) firPropertySymbol.getFir()).getName().asString();
            strAsString2.getClass();
            strAsString = JvmAbi.getterName(strAsString2);
        }
        return findGetterOverride(firPropertySymbol, strAsString, firScope);
    }
}

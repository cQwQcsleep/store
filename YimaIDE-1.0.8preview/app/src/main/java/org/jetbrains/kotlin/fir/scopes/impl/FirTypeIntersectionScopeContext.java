package org.jetbrains.kotlin.fir.scopes.impl;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculatorForFullBodyResolve;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirOverrideService;
import org.jetbrains.kotlin.fir.scopes.FirOverrideServiceKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScopeContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverrideFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverrideFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverridePropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0088\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001lB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ,\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u001c\u0010#\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030%\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020 0$J&\u0010'\u001a\u0018\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030%\u0012\u0004\u0012\u00020&0(0\u00072\u0006\u0010!\u001a\u00020\"H\u0002J\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u00072\u0006\u0010!\u001a\u00020\"Ju\u0010,\u001a$\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0\u00070(0\u0007j\b\u0012\u0004\u0012\u0002H-`.\"\f\b\u0000\u0010-*\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010!\u001a\u00020\"2/\u0010/\u001a+\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020 02\u0012\u0004\u0012\u00020 00¢\u0006\u0002\b3H\u0086\bø\u0001\u0000J_\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0*0\u0007\"\f\b\u0000\u0010-*\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010!\u001a\u00020\"2/\u0010/\u001a+\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020 02\u0012\u0004\u0012\u00020 00¢\u0006\u0002\b3H\u0086\bø\u0001\u0000J@\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0*0\u0007\"\f\b\u0000\u0010-*\u0006\u0012\u0002\b\u00030\u001a2\u001e\u00106\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0\u00070(0\u0007J\u0010\u00107\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u001bH\u0002JN\u00108\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0\u001b\"\f\b\u0000\u0010-*\u0006\u0012\u0002\b\u00030\u001a2\u0012\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0\u001b0\u00072\u0012\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0\u001b0\u00072\u0006\u0010;\u001a\u00020\fJ,\u0010<\u001a\b\u0012\u0004\u0012\u0002H=0\u001b\"\f\b\u0000\u0010=*\u0006\u0012\u0002\b\u00030\u001a*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H=0\u001b0>H\u0002J,\u0010?\u001a\u0004\u0018\u00010@\"\f\b\u0000\u0010-*\u0006\u0012\u0002\b\u00030\u001a2\u0012\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0\u001b0>H\u0002Jx\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0\u001b0>\"\f\b\u0000\u0010-*\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010C\u001a\u0002H-2\u0006\u0010D\u001a\u00020\b2?\u0010E\u001a;\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H-\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020F0$\u0012\u0004\u0012\u00020F00j\b\u0012\u0004\u0012\u0002H-`G¢\u0006\u0002\b3H\u0002¢\u0006\u0002\u0010HJ\u0090\u0001\u0010I\u001a\u00020 \"\f\b\u0000\u0010-*\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010C\u001a\u0002H-2\u0006\u0010D\u001a\u00020\b2\u0012\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-0\u001b0K2\u0018\u0010L\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H-0(0M25\u0010E\u001a1\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H-\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020F0$\u0012\u0004\u0012\u00020F00¢\u0006\u0002\b3H\u0002¢\u0006\u0002\u0010NJF\u0010O\u001a\u00020+2\u0010\u00109\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>2\u0010\u0010P\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>2\b\u0010Q\u001a\u0004\u0018\u00010@2\u0006\u0010R\u001a\u00020S2\u0006\u0010;\u001a\u00020\fH\u0002JX\u0010T\u001a\u00020U2\u0010\u00109\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>2\u0010\u0010P\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>2\u0010\u0010V\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0\u00072\b\u0010Q\u001a\u0004\u0018\u00010@2\u0006\u0010R\u001a\u00020S2\u0006\u0010;\u001a\u00020\fH\u0002JF\u0010W\u001a\u00020X2\u0010\u00109\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>2\u0010\u0010P\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>2\b\u0010Q\u001a\u0004\u0018\u00010@2\u0006\u0010R\u001a\u00020S2\u0006\u0010;\u001a\u00020\fH\u0002Jß\u0001\u0010Y\u001a\u0002H=\"\u0010\b\u0000\u0010=\u0018\u0001*\b\u0012\u0004\u0012\u0002H[0Z\"\b\b\u0001\u0010[*\u00020\\2\u0010\u00109\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>2\u0010\u0010P\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>2\u0006\u0010;\u001a\u00020\f2(\u0010]\u001a$\u0012\u0004\u0012\u00020^\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H=002]\u0010_\u001aY\u0012\u0004\u0012\u0002H=\u0012\u0004\u0012\u0002H[\u0012\u0015\u0012\u0013\u0018\u00010a¢\u0006\f\bb\u0012\b\b!\u0012\u0004\b\b(c\u0012\u0015\u0012\u0013\u0018\u00010d¢\u0006\f\bb\u0012\b\b!\u0012\u0004\b\b(e\u0012\u0015\u0012\u0013\u0018\u00010f¢\u0006\f\bb\u0012\b\b!\u0012\u0004\b\b(g\u0012\u0004\u0012\u0002H[0`H\u0082\b¢\u0006\u0002\u0010hJ\u001c\u0010i\u001a\u0004\u0018\u00010j2\u0010\u00109\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>H\u0002J\u001c\u0010k\u001a\u0004\u0018\u00010d2\u0010\u0010P\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0>H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R5\u0010\u0018\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001a0\u001b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001c0\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006m"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "overrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "scopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "forClassUseSiteScope", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopes", "()Ljava/util/List;", "overrideService", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideService;", "dispatchClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "isReceiverClassExpect", "intersectionOverrides", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection$NonTrivial;", "getIntersectionOverrides", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "processClassifiersByNameWithSubstitution", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "collectClassifiers", "Lkotlin/Pair;", "collectFunctions", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "collectMembersGroupedByScope", "D", "Lorg/jetbrains/kotlin/fir/scopes/impl/MembersByScope;", "processCallables", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "collectIntersectionResultsForCallables", "convertGroupedCallablesToIntersectionResults", "membersByScope", "isVisible", "createIntersectionOverride", "mostSpecific", "extractedOverrides", "containsMultipleNonSubsumed", "maxByVisibility", "S", Argument.Delimiters.none, "chooseIntersectionOverrideModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "extractedOverridden", "realOverridden", "symbol", "scope", "processDirectOverridden", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "Lorg/jetbrains/kotlin/fir/scopes/ProcessOverriddenWithBaseScope;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lkotlin/jvm/functions/Function3;)Ljava/util/Collection;", "collectRealOverridden", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "visited", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Ljava/util/Collection;Ljava/util/Set;Lkotlin/jvm/functions/Function3;)V", "createIntersectionOverrideFunction", "overrides", "newModality", "newVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "createIntersectionOverrideProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "nonSubsumedNonPhantomOverrides", "createIntersectionOverrideField", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "createIntersectionOverrideVariable", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "createIntersectionOverrideSymbol", "Lorg/jetbrains/kotlin/name/CallableId;", "createCopy", "Lkotlin/Function5;", "Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", "Lkotlin/ParameterName;", "deferredReturnTypeCalculation", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "returnType", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "backingField", "(Ljava/util/Collection;Ljava/util/Collection;ZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function5;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "deferredReturnTypeCalculationOrNull", "Lorg/jetbrains/kotlin/fir/scopes/impl/DeferredReturnTypeOfIntersection;", "intersectReturnTypes", "ResultOfIntersection", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeIntersectionScopeContext {
    private final FirRegularClassSymbol dispatchClassSymbol;
    private final ConeSimpleKotlinType dispatchReceiverType;
    private final boolean forClassUseSiteScope;
    private final FirCache<FirCallableSymbol<?>, MemberWithBaseScope<FirCallableSymbol<?>>, ResultOfIntersection.NonTrivial<?>> intersectionOverrides;
    private final boolean isReceiverClassExpect;
    private final FirOverrideChecker overrideChecker;
    private final FirOverrideService overrideService;
    private final List<FirTypeScope> scopes;
    private final FirSession session;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Modality.values().length];
            try {
                iArr[Modality.FINAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Modality.SEALED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Modality.OPEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Modality.ABSTRACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirTypeIntersectionScopeContext(FirSession firSession, FirOverrideChecker firOverrideChecker, List<? extends FirTypeScope> list, ConeSimpleKotlinType coneSimpleKotlinType, boolean z) {
        firSession.getClass();
        firOverrideChecker.getClass();
        list.getClass();
        coneSimpleKotlinType.getClass();
        this.session = firSession;
        this.overrideChecker = firOverrideChecker;
        this.scopes = list;
        this.dispatchReceiverType = coneSimpleKotlinType;
        this.forClassUseSiteScope = z;
        this.overrideService = FirOverrideServiceKt.getOverrideService(firSession);
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneSimpleKotlinType, firSession);
        this.dispatchClassSymbol = regularClassSymbol;
        boolean z2 = false;
        if (regularClassSymbol != null && regularClassSymbol.getRawStatus().isExpect()) {
            z2 = true;
        }
        this.isReceiverClassExpect = z2;
        this.intersectionOverrides = (FirCache) FirTypeIntersectionScopeContextKt.getIntersectionOverrideStorage(firSession).getCacheByScope().getValue(coneSimpleKotlinType, null);
    }

    public static Unit a(HashSet hashSet, List list, List list2, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        if (!hashSet.contains(firClassifierSymbol)) {
            list.add(firClassifierSymbol);
            list2.add(TuplesKt.to(firClassifierSymbol, coneSubstitutor));
        }
        return Unit.INSTANCE;
    }

    public static ConeKotlinType b(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return FirTypeUtilsKt.getConeType(firCallableDeclaration.getReturnTypeRef());
    }

    public static ProcessorAction c(FirTypeIntersectionScopeContext firTypeIntersectionScopeContext, Collection collection, Set set, Function3 function3, FirCallableSymbol firCallableSymbol, FirTypeScope firTypeScope) {
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        firTypeIntersectionScopeContext.collectRealOverridden(firCallableSymbol, firTypeScope, collection, set, function3);
        return ProcessorAction.NEXT;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final <D extends FirCallableSymbol<?>> Modality chooseIntersectionOverrideModality(Collection<? extends MemberWithBaseScope<? extends D>> extractedOverridden) throws KotlinIllegalArgumentExceptionWithAttachments {
        Function3 function3;
        Iterator<? extends MemberWithBaseScope<? extends D>> it = extractedOverridden.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            D fir = it.next().component1().getFir();
            fir.getClass();
            Modality modality = ((FirMemberDeclaration) fir).getStatus().getModality();
            int i = modality == null ? -1 : WhenMappings.$EnumSwitchMapping$0[modality.ordinal()];
            if (i != -1) {
                if (i == 1) {
                    return Modality.FINAL;
                }
                if (i == 2) {
                    return null;
                }
                if (i == 3) {
                    z2 = true;
                } else {
                    if (i != 4) {
                        bu8.a();
                        return null;
                    }
                    z = true;
                }
            }
        }
        if (z && !z2) {
            return Modality.ABSTRACT;
        }
        if (!z && z2) {
            return Modality.OPEN;
        }
        Collection<? extends MemberWithBaseScope<? extends D>> collection = extractedOverridden;
        FirCallableSymbol member = ((MemberWithBaseScope) CollectionsKt.first(collection)).getMember();
        if (member instanceof FirNamedFunctionSymbol) {
            FirTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$1 firTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$1 = FirTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$1.INSTANCE;
            firTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$1.getClass();
            function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(firTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$1, 3);
        } else {
            if (!(member instanceof FirPropertySymbol)) {
                f2f.a("Unexpected callable kind: ", ((MemberWithBaseScope) CollectionsKt.first(collection)).getMember());
                return null;
            }
            FirTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2 firTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2 = FirTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2.INSTANCE;
            firTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2.getClass();
            function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(firTypeIntersectionScopeContext$chooseIntersectionOverrideModality$processDirectOverridden$2, 3);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = collection.iterator();
        while (it2.hasNext()) {
            MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) it2.next();
            CollectionsKt.addAll(arrayList, realOverridden(memberWithBaseScope.getMember(), memberWithBaseScope.getBaseScope(), function3));
        }
        Iterator it3 = FirOverrideUtilsKt.filterOutOverridden(arrayList, function3).iterator();
        if (!it3.hasNext()) {
            z0e.a();
            return null;
        }
        D fir2 = ((MemberWithBaseScope) it3.next()).getMember().getFir();
        fir2.getClass();
        Modality modality2 = ((FirMemberDeclaration) fir2).getStatus().getModality();
        if (modality2 == null) {
            modality2 = Modality.ABSTRACT;
        }
        while (it3.hasNext()) {
            D fir3 = ((MemberWithBaseScope) it3.next()).getMember().getFir();
            fir3.getClass();
            Modality modality3 = ((FirMemberDeclaration) fir3).getStatus().getModality();
            if (modality3 == null) {
                modality3 = Modality.ABSTRACT;
            }
            if (modality2.compareTo(modality3) > 0) {
                modality2 = modality3;
            }
        }
        return modality2;
    }

    private final List<Pair<FirClassifierSymbol<?>, ConeSubstitutor>> collectClassifiers(Name name) {
        final HashSet hashSet = new HashSet();
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        Iterator<FirTypeScope> it = this.scopes.iterator();
        while (it.hasNext()) {
            it.next().processClassifiersByNameWithSubstitution(name, new Function2() { // from class: nf5
                public final Object invoke(Object obj, Object obj2) {
                    return FirTypeIntersectionScopeContext.a(hashSet, arrayList, arrayList2, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2);
                }
            });
            CollectionsKt.addAll(hashSet, arrayList);
            arrayList.clear();
        }
        return arrayList2;
    }

    private final <D extends FirCallableSymbol<?>> void collectRealOverridden(D symbol, FirTypeScope scope, final Collection<MemberWithBaseScope<D>> result, final Set<Pair<FirTypeScope, D>> visited, final Function3<? super FirTypeScope, ? super D, ? super Function2<? super D, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction> processDirectOverridden) {
        if (visited.add(TuplesKt.to(scope, symbol))) {
            if (((FirCallableDeclaration) symbol.getFir()).getOrigin().getFromSupertypes()) {
                processDirectOverridden.invoke(scope, symbol, new Function2() { // from class: of5
                    public final Object invoke(Object obj, Object obj2) {
                        return FirTypeIntersectionScopeContext.c(this.b, result, visited, processDirectOverridden, (FirCallableSymbol) obj, (FirTypeScope) obj2);
                    }
                });
            } else {
                result.add(new MemberWithBaseScope<>(symbol, scope));
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:42:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ac  */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirFieldSymbol createIntersectionOverrideField(Collection<? extends FirCallableSymbol<?>> mostSpecific, Collection<? extends FirCallableSymbol<?>> overrides, Modality newModality, Visibility newVisibility, boolean containsMultipleNonSubsumed) throws KotlinIllegalStateExceptionWithAttachments {
        FirBackingField backingField;
        Object next;
        boolean z;
        Object next2;
        FirProperty firProperty;
        Collection<? extends FirCallableSymbol<?>> collection = mostSpecific;
        Iterator<T> it = collection.iterator();
        while (true) {
            backingField = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) next;
            if ((firCallableSymbol instanceof FirPropertySymbol) && ((FirPropertySymbol) firCallableSymbol).isVar()) {
                break;
            }
        }
        if (!(next instanceof FirFieldSymbol)) {
            next = null;
        }
        FirFieldSymbol firFieldSymbol = (FirFieldSymbol) next;
        if (firFieldSymbol == null) {
            Object objFirst = CollectionsKt.first(collection);
            if (objFirst == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol");
                return null;
            }
            firFieldSymbol = (FirFieldSymbol) objFirst;
        }
        FirVariable firVariable = (FirVariable) firFieldSymbol.getFir();
        ClassId classId = ConeTypeUtilsKt.getClassId(this.dispatchReceiverType);
        if (classId == null) {
            ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firVariable);
            classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null ? coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId() : null;
            classId.getClass();
        }
        FirIntersectionOverrideFieldSymbol firIntersectionOverrideFieldSymbol = new FirIntersectionOverrideFieldSymbol(new CallableId(classId, firVariable.getName()), overrides, containsMultipleNonSubsumed);
        DeferredReturnTypeOfIntersection deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull = deferredReturnTypeCalculationOrNull(mostSpecific);
        if (this.forClassUseSiteScope) {
            z = false;
        } else if (!(collection instanceof Collection) || !collection.isEmpty()) {
            Iterator<T> it2 = collection.iterator();
            while (true) {
                if (it2.hasNext()) {
                    FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) it2.next();
                    firCallableSymbol2.getClass();
                    if (((FirVariable) ((FirVariableSymbol) firCallableSymbol2).getFir()).getIsVar()) {
                    }
                } else if (deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull == null) {
                    z = true;
                }
                z = false;
            }
        } else if (deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull == null) {
            z = true;
        } else {
            z = false;
        }
        ConeKotlinType coneKotlinTypeIntersectReturnTypes = z ? intersectReturnTypes(mostSpecific) : null;
        Iterator<T> it3 = collection.iterator();
        while (true) {
            if (!it3.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it3.next();
            FirCallableSymbol firCallableSymbol3 = (FirCallableSymbol) next2;
            if ((firCallableSymbol3 instanceof FirPropertySymbol) && DeclarationAttributesKt.getHasExplicitBackingField((FirPropertySymbol) firCallableSymbol3)) {
                break;
            }
        }
        FirPropertySymbol firPropertySymbol = next2 instanceof FirPropertySymbol ? (FirPropertySymbol) next2 : null;
        if (firPropertySymbol != null && (firProperty = (FirProperty) firPropertySymbol.getFir()) != null) {
            backingField = firProperty.getBackingField();
        }
        FirField firField = (FirField) firVariable;
        FirField firFieldCreateCopyForFirField = FirFakeOverrideGenerator.INSTANCE.createCopyForFirField(firIntersectionOverrideFieldSymbol, firField, null, this.session, FirDeclarationOrigin.IntersectionOverride.INSTANCE, (384 & 32) != 0 ? firField.getStatus().isExpect() : this.isReceiverClassExpect || firField.getStatus().isExpect(), this.dispatchReceiverType, (384 & 128) != 0 ? null : null, (384 & 256) != 0 ? null : null, (384 & 512) != 0 ? null : coneKotlinTypeIntersectReturnTypes, (384 & 1024) != 0 ? null : newModality, (384 & 2048) != 0 ? null : newVisibility, (384 & 4096) != 0 ? null : deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull);
        ClassMembersKt.setOriginalForIntersectionOverrideAttr(firFieldCreateCopyForFirField, firVariable);
        FirPropertyAccessor getter = firFieldCreateCopyForFirField.getGetter();
        if (getter != null) {
            ClassMembersKt.setOriginalForIntersectionOverrideAttr(getter, firVariable.getGetter());
        }
        FirPropertyAccessor setter = firFieldCreateCopyForFirField.getSetter();
        if (setter != null) {
            ClassMembersKt.setOriginalForIntersectionOverrideAttr(setter, firVariable.getSetter());
        }
        if (backingField != null) {
            ClassMembersKt.setOriginalForIntersectionOverrideAttr(backingField, backingField);
        }
        return firIntersectionOverrideFieldSymbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedFunctionSymbol createIntersectionOverrideFunction(Collection<? extends FirCallableSymbol<?>> mostSpecific, Collection<? extends FirCallableSymbol<?>> overrides, Modality newModality, Visibility newVisibility, boolean containsMultipleNonSubsumed) {
        Object objFirst = CollectionsKt.first(mostSpecific);
        objFirst.getClass();
        FirNamedFunction firNamedFunction = (FirNamedFunction) ((FirNamedFunctionSymbol) objFirst).getFir();
        ClassId classId = ConeTypeUtilsKt.getClassId(this.dispatchReceiverType);
        if (classId == null) {
            ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firNamedFunction);
            classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null ? coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId() : null;
            classId.getClass();
        }
        FirIntersectionOverrideFunctionSymbol firIntersectionOverrideFunctionSymbol = new FirIntersectionOverrideFunctionSymbol(new CallableId(classId, firNamedFunction.getName()), overrides, containsMultipleNonSubsumed);
        DeferredReturnTypeOfIntersection deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull = deferredReturnTypeCalculationOrNull(mostSpecific);
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        FirSession firSession = this.session;
        FirDeclarationOrigin.IntersectionOverride intersectionOverride = FirDeclarationOrigin.IntersectionOverride.INSTANCE;
        boolean z = this.isReceiverClassExpect || firNamedFunction.getStatus().isExpect();
        ConeSimpleKotlinType coneSimpleKotlinType = this.dispatchReceiverType;
        ConeKotlinType coneKotlinTypeIntersectReturnTypes = (this.forClassUseSiteScope || deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull != null) ? null : intersectReturnTypes(mostSpecific);
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(this.dispatchReceiverType, this.session);
        ClassMembersKt.setOriginalForIntersectionOverrideAttr(FirFakeOverrideGenerator.createCopyForFirFunction$default(firFakeOverrideGenerator, firIntersectionOverrideFunctionSymbol, firNamedFunction, null, firSession, intersectionOverride, z, coneSimpleKotlinType, null, null, null, null, coneKotlinTypeIntersectReturnTypes, newModality, newVisibility, deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull, symbol != null ? symbol.getSource() : null, true, 1920, null), firNamedFunction);
        return firIntersectionOverrideFunctionSymbol;
    }

    /* JADX WARN: Code duplicated, block: B:113:0x019b  */
    /* JADX WARN: Code duplicated, block: B:42:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ab  */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirPropertySymbol createIntersectionOverrideProperty(Collection<? extends FirCallableSymbol<?>> mostSpecific, Collection<? extends FirCallableSymbol<?>> overrides, List<? extends FirCallableSymbol<?>> nonSubsumedNonPhantomOverrides, Modality newModality, Visibility newVisibility, boolean containsMultipleNonSubsumed) {
        Object next;
        boolean z;
        Object next2;
        ConeKotlinType coneKotlinType;
        FirResolvedTypeRef returnTypeRef;
        FirPropertyAccessorSymbol setterSymbol;
        FirProperty firProperty;
        Collection<? extends FirCallableSymbol<?>> collection = mostSpecific;
        Iterator<T> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) next;
            if ((firCallableSymbol instanceof FirPropertySymbol) && ((FirPropertySymbol) firCallableSymbol).isVar()) {
                break;
            }
        }
        if (!(next instanceof FirPropertySymbol)) {
            next = null;
        }
        FirPropertySymbol firPropertySymbol = (FirPropertySymbol) next;
        if (firPropertySymbol == null) {
            Object objFirst = CollectionsKt.first(collection);
            if (objFirst == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol");
                return null;
            }
            firPropertySymbol = (FirPropertySymbol) objFirst;
        }
        FirVariable firVariable = (FirVariable) firPropertySymbol.getFir();
        ClassId classId = ConeTypeUtilsKt.getClassId(this.dispatchReceiverType);
        if (classId == null) {
            ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firVariable);
            classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null ? coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId() : null;
            classId.getClass();
        }
        FirIntersectionOverridePropertySymbol firIntersectionOverridePropertySymbol = new FirIntersectionOverridePropertySymbol(new CallableId(classId, firVariable.getName()), overrides, containsMultipleNonSubsumed);
        DeferredReturnTypeOfIntersection deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull = deferredReturnTypeCalculationOrNull(mostSpecific);
        if (this.forClassUseSiteScope) {
            z = false;
        } else if (!(collection instanceof Collection) || !collection.isEmpty()) {
            Iterator<T> it2 = collection.iterator();
            while (true) {
                if (it2.hasNext()) {
                    FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) it2.next();
                    firCallableSymbol2.getClass();
                    if (((FirVariable) ((FirVariableSymbol) firCallableSymbol2).getFir()).getIsVar()) {
                    }
                } else if (deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull == null) {
                    z = true;
                }
                z = false;
            }
        } else if (deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull == null) {
            z = true;
        } else {
            z = false;
        }
        ConeKotlinType coneKotlinTypeIntersectReturnTypes = z ? intersectReturnTypes(mostSpecific) : null;
        Iterator<T> it3 = collection.iterator();
        while (true) {
            if (!it3.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it3.next();
            FirCallableSymbol firCallableSymbol3 = (FirCallableSymbol) next2;
            if ((firCallableSymbol3 instanceof FirPropertySymbol) && DeclarationAttributesKt.getHasExplicitBackingField((FirPropertySymbol) firCallableSymbol3)) {
                break;
            }
        }
        FirPropertySymbol firPropertySymbol2 = next2 instanceof FirPropertySymbol ? (FirPropertySymbol) next2 : null;
        FirBackingField backingField = (firPropertySymbol2 == null || (firProperty = (FirProperty) firPropertySymbol2.getFir()) == null) ? null : firProperty.getBackingField();
        FirProperty firProperty2 = (FirProperty) firVariable;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it4 = nonSubsumedNonPhantomOverrides.iterator();
        while (it4.hasNext()) {
            FirCallableSymbol firCallableSymbol4 = (FirCallableSymbol) it4.next();
            FirPropertySymbol firPropertySymbol3 = firCallableSymbol4 instanceof FirPropertySymbol ? (FirPropertySymbol) firCallableSymbol4 : null;
            if (firPropertySymbol3 != null) {
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firPropertySymbol3.getFir();
                while (true) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        break;
                    }
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
                FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
                if (symbol == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol");
                    return null;
                }
                setterSymbol = ((FirPropertySymbol) symbol).getSetterSymbol();
            } else {
                setterSymbol = null;
            }
            if (setterSymbol != null) {
                arrayList.add(setterSymbol);
            }
        }
        Visibility visibilityChooseIntersectionVisibility = this.overrideChecker.chooseIntersectionVisibility(arrayList, this.dispatchClassSymbol);
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        FirSession firSession = this.session;
        FirDeclarationOrigin.IntersectionOverride intersectionOverride = FirDeclarationOrigin.IntersectionOverride.INSTANCE;
        boolean z2 = this.isReceiverClassExpect || firProperty2.getStatus().isExpect();
        ConeSimpleKotlinType coneSimpleKotlinType = this.dispatchReceiverType;
        FirClassifierSymbol<?> symbol2 = ToSymbolUtilsKt.toSymbol(coneSimpleKotlinType, this.session);
        KtSourceElement source = symbol2 != null ? symbol2.getSource() : null;
        if (backingField == null || (returnTypeRef = backingField.getReturnTypeRef()) == null) {
            coneKotlinType = null;
        } else {
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType == null) {
                coneKotlinType = null;
            } else {
                coneKotlinType = coneType;
            }
        }
        FirProperty firPropertyCreateCopyForFirProperty$default = FirFakeOverrideGenerator.createCopyForFirProperty$default(firFakeOverrideGenerator, firIntersectionOverridePropertySymbol, firProperty2, null, firSession, intersectionOverride, z2, coneSimpleKotlinType, null, null, null, coneKotlinTypeIntersectReturnTypes, newModality, newVisibility, visibilityChooseIntersectionVisibility, deferredReturnTypeOfIntersectionDeferredReturnTypeCalculationOrNull, source, backingField, coneKotlinType, null, 263040, null);
        FirBackingField firBackingField = backingField;
        FirBackingField backingField2 = firPropertyCreateCopyForFirProperty$default.getBackingField();
        if (backingField2 != null) {
            ClassMembersKt.setOriginalForIntersectionOverrideAttr(backingField2, firBackingField);
        }
        ClassMembersKt.setOriginalForIntersectionOverrideAttr(firPropertyCreateCopyForFirProperty$default, firVariable);
        FirPropertyAccessor getter = firPropertyCreateCopyForFirProperty$default.getGetter();
        if (getter != null) {
            ClassMembersKt.setOriginalForIntersectionOverrideAttr(getter, firVariable.getGetter());
        }
        FirPropertyAccessor setter = firPropertyCreateCopyForFirProperty$default.getSetter();
        if (setter != null) {
            ClassMembersKt.setOriginalForIntersectionOverrideAttr(setter, firVariable.getSetter());
        }
        if (firBackingField != null) {
            ClassMembersKt.setOriginalForIntersectionOverrideAttr(firBackingField, firBackingField);
        }
        return firIntersectionOverridePropertySymbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final DeferredReturnTypeOfIntersection deferredReturnTypeCalculationOrNull(Collection<? extends FirCallableSymbol<?>> mostSpecific) {
        Collection<? extends FirCallableSymbol<?>> collection = mostSpecific;
        boolean z = false;
        if (!(collection instanceof Collection) || !collection.isEmpty()) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                if (((FirCallableDeclaration) ((FirCallableSymbol) it.next()).getFir()).getReturnTypeRef() instanceof FirImplicitTypeRef) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            return new DeferredReturnTypeOfIntersection(mostSpecific, this.session);
        }
        return null;
    }

    private final ConeKotlinType intersectReturnTypes(Collection<? extends FirCallableSymbol<?>> overrides) {
        return FirTypeIntersectionScopeContextKt.intersectReturnTypes(overrides, this.session, new Function1() { // from class: mf5
            public final Object invoke(Object obj) {
                return FirTypeIntersectionScopeContext.b((FirCallableDeclaration) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isVisible(MemberWithBaseScope<?> memberWithBaseScope) {
        FirRegularClassSymbol firRegularClassSymbol = this.dispatchClassSymbol;
        if (firRegularClassSymbol == null) {
            return true;
        }
        return FirVisibilityCheckerKt.getVisibilityChecker(this.session).isVisibleForOverriding(firRegularClassSymbol.getModuleData(), firRegularClassSymbol, (FirCallableDeclaration) memberWithBaseScope.getMember().getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <S extends FirCallableSymbol<?>> MemberWithBaseScope<S> maxByVisibility(Collection<? extends MemberWithBaseScope<? extends S>> collection) {
        Integer numCompare;
        MemberWithBaseScope<S> memberWithBaseScope = null;
        for (MemberWithBaseScope<? extends S> memberWithBaseScope2 : collection) {
            if (memberWithBaseScope == null || ((numCompare = Visibilities.INSTANCE.compare(((FirCallableDeclaration) memberWithBaseScope.getMember().getFir()).getStatus().getVisibility(), ((FirCallableDeclaration) memberWithBaseScope2.getMember().getFir()).getStatus().getVisibility())) != null && numCompare.intValue() < 0)) {
                memberWithBaseScope = memberWithBaseScope2;
            }
        }
        memberWithBaseScope.getClass();
        return memberWithBaseScope;
    }

    private final <D extends FirCallableSymbol<?>> Collection<MemberWithBaseScope<D>> realOverridden(D symbol, FirTypeScope scope, Function3<? super FirTypeScope, ? super D, ? super Function2<? super D, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction> processDirectOverridden) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectRealOverridden(symbol, scope, linkedHashSet, new LinkedHashSet(), processDirectOverridden);
        return linkedHashSet;
    }

    public final List<ResultOfIntersection<FirNamedFunctionSymbol>> collectFunctions(Name name) {
        name.getClass();
        List<FirTypeScope> scopes = getScopes();
        ArrayList arrayList = new ArrayList();
        for (FirTypeScope firTypeScope : scopes) {
            ArrayList arrayList2 = new ArrayList();
            firTypeScope.processFunctionsByName(name, new FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1(arrayList2));
            if (arrayList2.isEmpty()) {
                arrayList2 = null;
            }
            Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return convertGroupedCallablesToIntersectionResults(arrayList);
    }

    public final <D extends FirCallableSymbol<?>> List<ResultOfIntersection<D>> collectIntersectionResultsForCallables(Name name, Function3<? super FirScope, ? super Name, ? super Function1<? super D, Unit>, Unit> processCallables) {
        name.getClass();
        processCallables.getClass();
        List<FirTypeScope> scopes = getScopes();
        ArrayList arrayList = new ArrayList();
        for (FirTypeScope firTypeScope : scopes) {
            ArrayList arrayList2 = new ArrayList();
            processCallables.invoke(firTypeScope, name, new FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1(arrayList2));
            if (arrayList2.isEmpty()) {
                arrayList2 = null;
            }
            Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return convertGroupedCallablesToIntersectionResults(arrayList);
    }

    public final <D extends FirCallableSymbol<?>> List<Pair<FirTypeScope, List<D>>> collectMembersGroupedByScope(Name name, Function3<? super FirScope, ? super Name, ? super Function1<? super D, Unit>, Unit> processCallables) {
        name.getClass();
        processCallables.getClass();
        List<FirTypeScope> scopes = getScopes();
        ArrayList arrayList = new ArrayList();
        for (FirTypeScope firTypeScope : scopes) {
            ArrayList arrayList2 = new ArrayList();
            processCallables.invoke(firTypeScope, name, new FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1(arrayList2));
            if (arrayList2.isEmpty()) {
                arrayList2 = null;
            }
            Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <D extends FirCallableSymbol<?>> List<ResultOfIntersection<D>> convertGroupedCallablesToIntersectionResults(List<? extends Pair<? extends FirTypeScope, ? extends List<? extends D>>> membersByScope) {
        membersByScope.getClass();
        if (membersByScope.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        Pair pair = (Pair) CollectionsKt.singleOrNull(membersByScope);
        if (pair != null) {
            FirTypeScope firTypeScope = (FirTypeScope) pair.component1();
            List<FirCallableSymbol> list = (List) pair.component2();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (FirCallableSymbol firCallableSymbol : list) {
                arrayList.add(new ResultOfIntersection.SingleMember(firCallableSymbol, new MemberWithBaseScope(firCallableSymbol, firTypeScope)));
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = membersByScope.iterator();
        while (it.hasNext()) {
            Pair pair2 = (Pair) it.next();
            FirTypeScope firTypeScope2 = (FirTypeScope) pair2.component1();
            List list2 = (List) pair2.component2();
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(new MemberWithBaseScope((FirCallableSymbol) it2.next(), firTypeScope2));
            }
            CollectionsKt.addAll(arrayList2, arrayList3);
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : arrayList2) {
            if (hashSet.add(((MemberWithBaseScope) obj).getMember())) {
                arrayList4.add(obj);
            }
        }
        List mutableList = CollectionsKt.toMutableList(arrayList4);
        ArrayList arrayList5 = new ArrayList();
        while (true) {
            boolean z = true;
            if (mutableList.size() <= 1) {
                break;
            }
            List list3 = mutableList;
            List<? extends MemberWithBaseScope<? extends D>> listExtractBothWaysOverridable = this.overrideService.extractBothWaysOverridable(maxByVisibility(list3), list3, this.overrideChecker);
            ArrayList arrayList6 = new ArrayList();
            for (Object obj2 : listExtractBothWaysOverridable) {
                if (isVisible((MemberWithBaseScope) obj2)) {
                    arrayList6.add(obj2);
                }
            }
            if (!arrayList6.isEmpty()) {
                listExtractBothWaysOverridable = arrayList6;
            }
            List<? extends MemberWithBaseScope<? extends D>> list4 = listExtractBothWaysOverridable;
            List<MemberWithBaseScope<D>> listSelectMostSpecificMembers = this.overrideService.selectMostSpecificMembers(list4, ReturnTypeCalculatorForFullBodyResolve.INSTANCE.getStatus());
            if (this.forClassUseSiteScope) {
                if (list4.size() > 1) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    Iterator<T> it3 = list4.iterator();
                    while (it3.hasNext()) {
                        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((MemberWithBaseScope) it3.next()).getMember().getFir();
                        while (true) {
                            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                            if (originalForSubstitutionOverrideAttr == null) {
                                break;
                            }
                            firCallableDeclaration = originalForSubstitutionOverrideAttr;
                        }
                        linkedHashSet.add(firCallableDeclaration.getSymbol());
                    }
                    if (linkedHashSet.size() > 1) {
                        if (this.forClassUseSiteScope ? DeclarationUtilsKt.getNonSubsumedNonPhantomOverriddenSymbols(listSelectMostSpecificMembers).size() <= 1 : DeclarationUtilsKt.getNonSubsumedNonPhantomOverriddenSymbols(list4).size() <= 1) {
                        }
                        arrayList5.add(new ResultOfIntersection.NonTrivial(this, listSelectMostSpecificMembers, list4, z));
                    }
                }
                MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) CollectionsKt.first(listSelectMostSpecificMembers);
                arrayList5.add(new ResultOfIntersection.SingleMember(memberWithBaseScope.component1(), list4, memberWithBaseScope.getBaseScope()));
            } else if (listSelectMostSpecificMembers.size() > 1) {
                z = this.forClassUseSiteScope ? false : false;
                arrayList5.add(new ResultOfIntersection.NonTrivial(this, listSelectMostSpecificMembers, list4, z));
            } else {
                MemberWithBaseScope memberWithBaseScope2 = (MemberWithBaseScope) CollectionsKt.first(listSelectMostSpecificMembers);
                arrayList5.add(new ResultOfIntersection.SingleMember(memberWithBaseScope2.component1(), list4, memberWithBaseScope2.getBaseScope()));
            }
        }
        if (!mutableList.isEmpty()) {
            MemberWithBaseScope memberWithBaseScope3 = (MemberWithBaseScope) CollectionsKt.single(mutableList);
            arrayList5.add(new ResultOfIntersection.SingleMember(memberWithBaseScope3.component1(), CollectionsKt.toList(mutableList), memberWithBaseScope3.getBaseScope()));
        }
        return arrayList5;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final <D extends FirCallableSymbol<?>> MemberWithBaseScope<FirCallableSymbol<?>> createIntersectionOverride(List<? extends MemberWithBaseScope<? extends D>> mostSpecific, List<? extends MemberWithBaseScope<? extends D>> extractedOverrides, boolean containsMultipleNonSubsumed) throws KotlinIllegalStateExceptionWithAttachments, KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableSymbol firCallableSymbolCreateIntersectionOverrideField;
        mostSpecific.getClass();
        extractedOverrides.getClass();
        List<? extends MemberWithBaseScope<? extends D>> list = extractedOverrides;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, DeclarationUtilsKt.flattenIntersectionsRecursively((MemberWithBaseScope) it.next()));
        }
        Modality modalityChooseIntersectionOverrideModality = chooseIntersectionOverrideModality(DeclarationUtilsKt.nonSubsumed(arrayList));
        List<MemberWithBaseScope<FirCallableSymbol<?>>> nonSubsumedNonPhantomOverriddenSymbols = DeclarationUtilsKt.getNonSubsumedNonPhantomOverriddenSymbols(extractedOverrides);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(nonSubsumedNonPhantomOverriddenSymbols, 10));
        Iterator<T> it2 = nonSubsumedNonPhantomOverriddenSymbols.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((MemberWithBaseScope) it2.next()).getMember());
        }
        Visibility visibilityChooseIntersectionVisibility = this.overrideChecker.chooseIntersectionVisibility(arrayList2, this.dispatchClassSymbol);
        List<? extends MemberWithBaseScope<? extends D>> list2 = mostSpecific;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it3 = list2.iterator();
        while (it3.hasNext()) {
            arrayList3.add(((MemberWithBaseScope) it3.next()).getMember());
        }
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it4 = list.iterator();
        while (it4.hasNext()) {
            arrayList4.add(((MemberWithBaseScope) it4.next()).getMember());
        }
        MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) CollectionsKt.first(mostSpecific);
        FirCallableSymbol member = memberWithBaseScope.getMember();
        if (member instanceof FirNamedFunctionSymbol) {
            firCallableSymbolCreateIntersectionOverrideField = createIntersectionOverrideFunction(arrayList3, arrayList4, modalityChooseIntersectionOverrideModality, visibilityChooseIntersectionVisibility, containsMultipleNonSubsumed);
        } else if (member instanceof FirPropertySymbol) {
            firCallableSymbolCreateIntersectionOverrideField = createIntersectionOverrideProperty(arrayList3, arrayList4, arrayList2, modalityChooseIntersectionOverrideModality, visibilityChooseIntersectionVisibility, containsMultipleNonSubsumed);
        } else {
            if (!(member instanceof FirFieldSymbol)) {
                f2f.a("Unsupported symbol type for creating intersection overrides: ", memberWithBaseScope.getMember());
                return null;
            }
            if (this.forClassUseSiteScope) {
                f2f.a("Can not create intersection override in class scope for field ", memberWithBaseScope.getMember());
                return null;
            }
            firCallableSymbolCreateIntersectionOverrideField = createIntersectionOverrideField(arrayList3, arrayList4, modalityChooseIntersectionOverrideModality, visibilityChooseIntersectionVisibility, containsMultipleNonSubsumed);
        }
        return FirTypeIntersectionScopeContextKt.withScope(firCallableSymbolCreateIntersectionOverrideField, memberWithBaseScope.getBaseScope());
    }

    public final FirCache<FirCallableSymbol<?>, MemberWithBaseScope<FirCallableSymbol<?>>, ResultOfIntersection.NonTrivial<?>> getIntersectionOverrides() {
        return this.intersectionOverrides;
    }

    public final List<FirTypeScope> getScopes() {
        return this.scopes;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        for (Pair<FirClassifierSymbol<?>, ConeSubstitutor> pair : collectClassifiers(name)) {
            processor.invoke((FirClassifierSymbol) pair.component1(), (ConeSubstitutor) pair.component2());
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00020\u0003:\u0002\u000e\u000fB\u001d\b\u0004\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0001\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", Argument.Delimiters.none, "overriddenMembers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "<init>", "(Ljava/util/List;)V", "getOverriddenMembers", "()Ljava/util/List;", "chosenSymbol", "getChosenSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "SingleMember", "NonTrivial", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection$NonTrivial;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection$SingleMember;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ResultOfIntersection<D extends FirCallableSymbol<?>> {
        private final List<MemberWithBaseScope<D>> overriddenMembers;

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\u0018\u0000*\f\b\u0001\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B?\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00028\u00018VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0019\u001a\u00028\u00018F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection$NonTrivial;", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "context", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext;", "mostSpecific", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "overriddenMembers", "containsMultipleNonSubsumed", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext;Ljava/util/List;Ljava/util/List;Z)V", "getContext", "()Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext;", "getMostSpecific", "()Ljava/util/List;", "getContainsMultipleNonSubsumed", "()Z", "chosenSymbol", "getChosenSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "chosenSymbol$delegate", "Lkotlin/Lazy;", "keySymbol", "getKeySymbol", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class NonTrivial<D extends FirCallableSymbol<?>> extends ResultOfIntersection<D> {

            /* JADX INFO: renamed from: chosenSymbol$delegate, reason: from kotlin metadata */
            private final Lazy chosenSymbol;
            private final boolean containsMultipleNonSubsumed;
            private final FirTypeIntersectionScopeContext context;
            private final List<MemberWithBaseScope<D>> mostSpecific;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public NonTrivial(FirTypeIntersectionScopeContext firTypeIntersectionScopeContext, List<? extends MemberWithBaseScope<? extends D>> list, List<? extends MemberWithBaseScope<? extends D>> list2, boolean z) {
                super(list2, null);
                firTypeIntersectionScopeContext.getClass();
                list.getClass();
                list2.getClass();
                this.context = firTypeIntersectionScopeContext;
                this.mostSpecific = list;
                this.containsMultipleNonSubsumed = z;
                this.chosenSymbol = LazyKt.lazy(new Function0() { // from class: pf5
                    public final Object invoke() {
                        return FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial.a(this.b);
                    }
                });
            }

            public static FirCallableSymbol a(NonTrivial nonTrivial) {
                FirCallableSymbol member = nonTrivial.context.getIntersectionOverrides().getValue(nonTrivial.getKeySymbol(), nonTrivial).getMember();
                member.getClass();
                return member;
            }

            @Override // org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScopeContext.ResultOfIntersection
            public D getChosenSymbol() {
                return (D) this.chosenSymbol.getValue();
            }

            public final boolean getContainsMultipleNonSubsumed() {
                return this.containsMultipleNonSubsumed;
            }

            public final FirTypeIntersectionScopeContext getContext() {
                return this.context;
            }

            public final D getKeySymbol() {
                return (D) ((MemberWithBaseScope) CollectionsKt.first(this.mostSpecific)).getMember();
            }

            public final List<MemberWithBaseScope<D>> getMostSpecific() {
                return this.mostSpecific;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private ResultOfIntersection(List<? extends MemberWithBaseScope<? extends D>> list) {
            this.overriddenMembers = list;
        }

        public abstract D getChosenSymbol();

        public final List<MemberWithBaseScope<D>> getOverriddenMembers() {
            return this.overriddenMembers;
        }

        public /* synthetic */ ResultOfIntersection(List list, DefaultConstructorMarker defaultConstructorMarker) {
            this(list);
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000*\f\b\u0001\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB\u001f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0004\b\n\u0010\rR\u0016\u0010\u0004\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection$SingleMember;", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "chosenSymbol", "overriddenMembers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "scopeOfChosenSymbol", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/List;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "overriddenMember", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;)V", "getChosenSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getScopeOfChosenSymbol", "()Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class SingleMember<D extends FirCallableSymbol<?>> extends ResultOfIntersection<D> {
            private final D chosenSymbol;
            private final FirTypeScope scopeOfChosenSymbol;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SingleMember(D d, List<? extends MemberWithBaseScope<? extends D>> list, FirTypeScope firTypeScope) {
                super(list, null);
                d.getClass();
                list.getClass();
                firTypeScope.getClass();
                this.chosenSymbol = d;
                this.scopeOfChosenSymbol = firTypeScope;
            }

            @Override // org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScopeContext.ResultOfIntersection
            public D getChosenSymbol() {
                return this.chosenSymbol;
            }

            public final FirTypeScope getScopeOfChosenSymbol() {
                return this.scopeOfChosenSymbol;
            }

            /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
            public SingleMember(D d, MemberWithBaseScope<? extends D> memberWithBaseScope) {
                this(d, CollectionsKt.listOf(memberWithBaseScope), memberWithBaseScope.getBaseScope());
                d.getClass();
                memberWithBaseScope.getClass();
            }
        }
    }
}

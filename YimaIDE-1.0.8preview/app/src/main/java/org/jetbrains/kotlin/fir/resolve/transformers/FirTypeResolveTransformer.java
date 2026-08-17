package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.AnnotationTargetUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirExpressionStubBuilder;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtension;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtensionKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeResolutionConfiguration;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguouslyResolvedAnnotationFromPlugin;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeCyclicTypeBound;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.ImportingScopesKt;
import org.jetbrains.kotlin.fir.scopes.ScopesKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirMemberTypeParameterScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeWithSubstitutionKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ê\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001BC\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u000f\u0010\u0010J,\u0010.\u001a\u0002H/\"\u0004\b\u0000\u0010/2\u0006\u00100\u001a\u00020-2\u000e\b\u0004\u00101\u001a\b\u0012\u0004\u0012\u0002H/02H\u0082\b¢\u0006\u0002\u00103J\u001a\u0010<\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020\u000b2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J/\u0010?\u001a\u0002H@\"\u0004\b\u0000\u0010@2\u0006\u0010=\u001a\u00020\u000b2\u000e\b\u0004\u00101\u001a\b\u0012\u0004\u0012\u0002H@02H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010AJ\u001a\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020C2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J/\u0010E\u001a\u0002H@\"\u0004\b\u0000\u0010@2\u0006\u0010D\u001a\u00020C2\u000e\b\u0004\u00101\u001a\b\u0012\u0004\u0012\u0002H@02H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010FJ\u001a\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010K\u001a\u00020L2\u0006\u0010I\u001a\u00020J2\b\u0010>\u001a\u0004\u0018\u00010\u0002J-\u0010M\u001a\u0002H@\"\u0004\b\u0000\u0010@2\u0006\u0010I\u001a\u00020J2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H@02H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010NJ\u001a\u0010O\u001a\u00020H2\u0006\u0010P\u001a\u00020Q2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020S2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020V2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010X\u001a\u00020S2\u0006\u0010Y\u001a\u00020Z2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020\\2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020_2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020b2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020e2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010g\u001a\u00020L2\u0006\u0010f\u001a\u00020eH\u0002J\u001a\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020i2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010k\u001a\u00020H2\u0006\u0010l\u001a\u00020m2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020o2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010q\u001a\u00020L2\u0006\u0010r\u001a\u00020sH\u0002J&\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020w2\u0006\u0010x\u001a\u00020w2\f\u0010y\u001a\b\u0012\u0004\u0012\u00020w0zH\u0002J\u001a\u0010{\u001a\u00020|2\u0006\u0010}\u001a\u00020~2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001c\u0010\u007f\u001a\u00030\u0080\u00012\u0007\u0010\u0081\u0001\u001a\u00020|2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u0082\u0001\u001a\u00020H2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u0085\u0001\u001a\u00020H2\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u008a\u0001\u001a\u00030\u0089\u00012\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u008b\u0001\u001a\u00020H2\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u008e\u0001\u001a\u00020H2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0016J*\u0010\u0091\u0001\u001a\u0002H/\"\u0004\b\u0000\u0010/2\u000f\b\u0004\u0010\u0092\u0001\u001a\b\u0012\u0004\u0012\u0002H/02H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u0093\u0001J\u001c\u0010\u0094\u0001\u001a\u00020H2\u0007\u0010\u0095\u0001\u001a\u00020\u000e2\b\u0010>\u001a\u0004\u0018\u00010\u0002H\u0002J\u000f\u0010\u0096\u0001\u001a\u00020L2\u0006\u0010T\u001a\u00020SJ\u000f\u0010\u0097\u0001\u001a\u00020L2\u0006\u0010j\u001a\u00020iJ\u0010\u0010\u0098\u0001\u001a\u00020u2\u0007\u0010\u0095\u0001\u001a\u00020\u000eJC\u0010\u0099\u0001\u001a\u0002H@\"\u0004\b\u0000\u0010@2\u0007\u0010\u0095\u0001\u001a\u00020\u000e2\u000f\b\u0006\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020L022\u000e\b\u0004\u00101\u001a\b\u0012\u0004\u0012\u0002H@02H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u009b\u0001J\u0013\u0010\u009c\u0001\u001a\u00020L2\b\u0010\u009d\u0001\u001a\u00030\u009e\u0001H\u0002J\u0011\u0010\u009f\u0001\u001a\u00020L2\b\u0010 \u0001\u001a\u00030¡\u0001J\u0016\u0010¢\u0001\u001a\u00020L2\r\u0010£\u0001\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u000e\u0010¤\u0001\u001a\u00020L*\u00030¥\u0001H\u0002J!\u0010¦\u0001\u001a\u00020u*\u00030\u008d\u00012\u0007\u0010§\u0001\u001a\u00020-2\b\u0010¨\u0001\u001a\u00030©\u0001H\u0002J!\u0010ª\u0001\u001a\u00020u*\u00030\u008d\u00012\u0007\u0010§\u0001\u001a\u00020-2\b\u0010¨\u0001\u001a\u00030©\u0001H\u0002J\u001a\u0010«\u0001\u001a\u00020u2\u000f\u0010¬\u0001\u001a\n\u0012\u0005\u0012\u00030®\u00010\u00ad\u0001H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004r\u0002\b\u0017¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R&\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004r\u0002\b\u0017¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0014\u001a\u0004\b\u0019\u0010\u001aR.\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001c8\u0006@\u0006X\u0087\u000er\u0002\b\u0017¢\u0006\u0014\n\u0000\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R.\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u001c8\u0006@\u0006X\u0087\u000er\u0002\b\u0017¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010\u0014\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R)\u0010&\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001c@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u0017¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001f\"\u0004\b(\u0010!R)\u0010)\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001c@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u0017¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001f\"\u0004\b+\u0010!R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R*\u00106\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000er\u0002\b\u0017¢\u0006\u0014\n\u0000\u0012\u0004\b7\u0010\u0014\u001a\u0004\b8\u00109\"\u0004\b:\u0010;\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006¯\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirTypeResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirAbstractTreeTransformer;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "initialScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "initialCurrentFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "classDeclarationsStack", "Lkotlin/collections/ArrayDeque;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Ljava/util/List;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lkotlin/collections/ArrayDeque;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession$annotations", "()V", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "getClassDeclarationsStack$annotations", "getClassDeclarationsStack", "()Lkotlin/collections/ArrayDeque;", "scopes", "Lkotlinx/collections/immutable/PersistentList;", "getScopes$annotations", "getScopes", "()Lkotlinx/collections/immutable/PersistentList;", "setScopes", "(Lkotlinx/collections/immutable/PersistentList;)V", "staticScopes", "getStaticScopes$annotations", "getStaticScopes", "setStaticScopes", "scopesBefore", "getScopesBefore", "setScopesBefore", "staticScopesBefore", "getStaticScopesBefore", "setStaticScopesBefore", "currentDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "withDeclaration", "T", "declaration", "action", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "typeResolverTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "currentFile", "getCurrentFile$annotations", "getCurrentFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "setCurrentFile", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "transformFile", "file", "data", "withFileScope", "R", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "transformReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "replSnippet", "withReplSnippetScope", "(Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "transformClassTypeParameters", Argument.Delimiters.none, "withClassDeclarationCleanup", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "transformAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "transformConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "constructor", "transformAnonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "anonymousInitializer", "transformErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "transformTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "typeAlias", "transformEnumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "enumEntry", "transformReceiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "receiverParameter", "transformProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "setAccessorTypesByPropertyType", "transformField", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "field", "transformBackingField", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "transformNamedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "namedFunction", "unboundCyclesInTypeParametersSupertypes", "typeParametersOwner", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "hasSupertypePathToParameter", Argument.Delimiters.none, "currentTypeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "typeParameter", "visited", Argument.Delimiters.none, "transformImplicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "implicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "transformTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "typeRef", "transformValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "transformBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "transformArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "argumentList", "transformAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "transformAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "withScopeCleanup", "l", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "resolveClassContent", "firClass", "transformDelegatedConstructorCall", "transformDelegateField", "removeOuterTypeParameterScope", "withClassScopes", "actionInsideStaticScope", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "resolveConstructedTypeRefForDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "addTypeParametersScope", "firMemberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "addScopes", "list", "moveOrDeleteIrrelevantAnnotations", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "multiplexWithoutUseSiteTarget", "annotated", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "multiplexWithAllUseSiteTarget", "propertyAnnotationShouldBeMovedToField", "allowedTargets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirTypeResolveTransformer extends FirAbstractTreeTransformer<Object> {
    private final ArrayDeque<FirClass> classDeclarationsStack;
    private FirDeclaration currentDeclaration;
    private FirFile currentFile;
    private final ScopeSession scopeSession;
    private PersistentList<? extends FirScope> scopes;
    private PersistentList<? extends FirScope> scopesBefore;
    private final FirSession session;
    private PersistentList<? extends FirScope> staticScopes;
    private PersistentList<? extends FirScope> staticScopesBefore;
    private final FirSpecificTypeResolverTransformer typeResolverTransformer;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FirAnnotationResolvePhase.values().length];
            try {
                iArr[FirAnnotationResolvePhase.Unresolved.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirAnnotationResolvePhase.CompilerRequiredAnnotations.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirAnnotationResolvePhase.Types.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AnnotationUseSiteTarget.values().length];
            try {
                iArr2[AnnotationUseSiteTarget.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirTypeResolveTransformer(FirSession firSession, ScopeSession scopeSession, List<? extends FirScope> list, FirFile firFile, ArrayDeque<FirClass> arrayDeque) {
        super(FirResolvePhase.TYPES);
        firSession.getClass();
        scopeSession.getClass();
        list.getClass();
        arrayDeque.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.classDeclarationsStack = arrayDeque;
        PersistentList<? extends FirScope> persistentList = ExtensionsKt.toPersistentList(CollectionsKt.asReversed(list));
        this.scopes = persistentList;
        this.staticScopes = persistentList;
        this.typeResolverTransformer = new FirSpecificTypeResolverTransformer(firSession, false, false, null, true, 14, null);
        this.currentFile = firFile;
    }

    @PrivateForInline
    public static /* synthetic */ void getClassDeclarationsStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getCurrentFile$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getScopeSession$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getScopes$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getStaticScopes$annotations() {
    }

    private final boolean hasSupertypePathToParameter(FirTypeParameter currentTypeParameter, FirTypeParameter typeParameter, Set<FirTypeParameter> visited) {
        FirTypeParameter firTypeParameterHasSupertypePathToParameter$toNextTypeParameter;
        if (!visited.isEmpty() && Intrinsics.areEqual(currentTypeParameter, typeParameter)) {
            return true;
        }
        if (!visited.add(currentTypeParameter)) {
            return false;
        }
        List<FirTypeRef> bounds = currentTypeParameter.getBounds();
        if ((bounds instanceof Collection) && bounds.isEmpty()) {
            return false;
        }
        Iterator<T> it = bounds.iterator();
        while (it.hasNext()) {
            ConeKotlinType coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull((FirTypeRef) it.next());
            if ((coneTypeOrNull == null || (firTypeParameterHasSupertypePathToParameter$toNextTypeParameter = hasSupertypePathToParameter$toNextTypeParameter(coneTypeOrNull)) == null) ? false : hasSupertypePathToParameter(firTypeParameterHasSupertypePathToParameter$toNextTypeParameter, typeParameter, visited)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirTypeParameter hasSupertypePathToParameter$toNextTypeParameter(ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeTypeParameterType) {
            return (FirTypeParameter) ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getFir();
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return hasSupertypePathToParameter$toNextTypeParameter(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveOrDeleteIrrelevantAnnotations(FirVariable firVariable) {
        boolean zMultiplexWithoutUseSiteTarget;
        if (firVariable.getAnnotations().isEmpty()) {
            return;
        }
        LanguageVersionSettings languageVersionSettings = FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session);
        List<FirAnnotation> annotations = firVariable.getAnnotations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : annotations) {
            FirAnnotation firAnnotation = (FirAnnotation) obj;
            AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
            int i = useSiteTarget == null ? -1 : WhenMappings.$EnumSwitchMapping$1[useSiteTarget.ordinal()];
            if (i != -1) {
                zMultiplexWithoutUseSiteTarget = true;
                if (i == 1) {
                    zMultiplexWithoutUseSiteTarget = multiplexWithAllUseSiteTarget(firAnnotation, firVariable, languageVersionSettings);
                }
            } else {
                zMultiplexWithoutUseSiteTarget = multiplexWithoutUseSiteTarget(firAnnotation, firVariable, languageVersionSettings);
            }
            if (zMultiplexWithoutUseSiteTarget) {
                arrayList.add(obj);
            }
        }
        firVariable.replaceAnnotations(arrayList);
    }

    private final boolean multiplexWithAllUseSiteTarget(FirAnnotation firAnnotation, FirDeclaration firDeclaration, LanguageVersionSettings languageVersionSettings) {
        FirPropertyAccessor setter;
        List<FirValueParameter> valueParameters;
        FirValueParameter firValueParameter;
        FirPropertyAccessor getter;
        FirBackingField backingField;
        if (!languageVersionSettings.supportsFeature(LanguageFeature.AnnotationAllUseSiteTarget)) {
            return true;
        }
        Set<AnnotationUseSiteTarget> setUseSiteTargetsFromMetaAnnotation = AnnotationTargetUtilsKt.useSiteTargetsFromMetaAnnotation(firAnnotation, this.session);
        if (firDeclaration instanceof FirValueParameter) {
            return setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER);
        }
        if (firDeclaration instanceof FirProperty) {
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            if (setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.FIELD)) {
                FirProperty firProperty = (FirProperty) firDeclaration;
                if (firProperty.getDelegate() == null && (backingField = firProperty.getBackingField()) != null) {
                    multiplexWithAllUseSiteTarget$addAnnotationWithoutUseSiteTarget(backingField, booleanRef, firAnnotation);
                }
            }
            if (setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.PROPERTY_GETTER) && (getter = ((FirProperty) firDeclaration).getGetter()) != null) {
                multiplexWithAllUseSiteTarget$addAnnotationWithoutUseSiteTarget(getter, booleanRef, firAnnotation);
            }
            FirProperty firProperty2 = (FirProperty) firDeclaration;
            if (firProperty2.getIsVar() && setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.SETTER_PARAMETER) && (setter = firProperty2.getSetter()) != null && (valueParameters = setter.getValueParameters()) != null && (firValueParameter = (FirValueParameter) CollectionsKt.firstOrNull(valueParameters)) != null) {
                multiplexWithAllUseSiteTarget$addAnnotationWithoutUseSiteTarget(firValueParameter, booleanRef, firAnnotation);
            }
            if (setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER) && Intrinsics.areEqual(DeclarationAttributesKt.getFromPrimaryConstructor(firProperty2), Boolean.TRUE)) {
                booleanRef.element = true;
            }
            if (!setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.PROPERTY) && booleanRef.element && firProperty2.getDelegate() == null) {
                return false;
            }
        }
        return true;
    }

    private static final void multiplexWithAllUseSiteTarget$addAnnotationWithoutUseSiteTarget(FirCallableDeclaration firCallableDeclaration, Ref.BooleanRef booleanRef, FirAnnotation firAnnotation) {
        FirAnnotation firAnnotationMo288build;
        if (firAnnotation instanceof FirAnnotationCall) {
            FirAnnotationCall firAnnotationCall = (FirAnnotationCall) firAnnotation;
            FirAnnotationCallBuilder firAnnotationCallBuilder = new FirAnnotationCallBuilder();
            firAnnotationCallBuilder.setSource(firAnnotationCall.getSource());
            firAnnotationCallBuilder.setUseSiteTarget(firAnnotationCall.getUseSiteTarget());
            firAnnotationCallBuilder.setAnnotationTypeRef(firAnnotationCall.getAnnotationTypeRef());
            firAnnotationCallBuilder.getTypeArguments().addAll(firAnnotationCall.getTypeArguments());
            firAnnotationCallBuilder.setArgumentList(firAnnotationCall.getArgumentList());
            firAnnotationCallBuilder.setCalleeReference(firAnnotationCall.getCalleeReference());
            firAnnotationCallBuilder.setArgumentMapping(firAnnotationCall.getArgumentMapping());
            firAnnotationCallBuilder.setAnnotationResolvePhase(firAnnotationCall.getAnnotationResolvePhase());
            firAnnotationCallBuilder.setContainingDeclarationSymbol(firAnnotationCall.getContainingDeclarationSymbol());
            firAnnotationCallBuilder.setUseSiteTarget(null);
            firAnnotationMo288build = firAnnotationCallBuilder.mo288build();
        } else {
            FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
            firAnnotationBuilder.setSource(firAnnotation.getSource());
            firAnnotationBuilder.setUseSiteTarget(firAnnotation.getUseSiteTarget());
            firAnnotationBuilder.setAnnotationTypeRef(firAnnotation.getAnnotationTypeRef());
            firAnnotationBuilder.setArgumentMapping(firAnnotation.getArgumentMapping());
            firAnnotationBuilder.getTypeArguments().addAll(firAnnotation.getTypeArguments());
            firAnnotationBuilder.setUseSiteTarget(null);
            firAnnotationMo288build = firAnnotationBuilder.mo288build();
        }
        firCallableDeclaration.replaceAnnotations(CollectionsKt.plus(firCallableDeclaration.getAnnotations(), firAnnotationMo288build));
        booleanRef.element = true;
    }

    private final boolean multiplexWithoutUseSiteTarget(FirAnnotation firAnnotation, FirDeclaration firDeclaration, LanguageVersionSettings languageVersionSettings) {
        Set<AnnotationUseSiteTarget> setUseSiteTargetsFromMetaAnnotation = AnnotationTargetUtilsKt.useSiteTargetsFromMetaAnnotation(firAnnotation, this.session);
        if (firDeclaration instanceof FirValueParameter) {
            return setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER);
        }
        boolean z = firDeclaration instanceof FirProperty;
        if (z) {
            FirProperty firProperty = (FirProperty) firDeclaration;
            if (Intrinsics.areEqual(DeclarationAttributesKt.getFromPrimaryConstructor(firProperty), Boolean.TRUE) && setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER)) {
                if (!languageVersionSettings.supportsFeature(LanguageFeature.PropertyParamAnnotationDefaultTargetMode)) {
                    return false;
                }
                if (setUseSiteTargetsFromMetaAnnotation.contains(AnnotationUseSiteTarget.PROPERTY)) {
                    return true;
                }
                if (firProperty.getBackingField() != null && propertyAnnotationShouldBeMovedToField(setUseSiteTargetsFromMetaAnnotation)) {
                    FirClass firClass = (FirClass) this.classDeclarationsStack.lastOrNull();
                    if ((firClass != null ? firClass.getClassKind() : null) != ClassKind.ANNOTATION_CLASS) {
                        FirBackingField backingField = firProperty.getBackingField();
                        backingField.getClass();
                        backingField.replaceAnnotations(CollectionsKt.plus(backingField.getAnnotations(), firAnnotation));
                    }
                }
                return false;
            }
        }
        if (z) {
            FirProperty firProperty2 = (FirProperty) firDeclaration;
            if (firProperty2.getBackingField() != null && propertyAnnotationShouldBeMovedToField(setUseSiteTargetsFromMetaAnnotation)) {
                FirBackingField backingField2 = firProperty2.getBackingField();
                backingField2.getClass();
                backingField2.replaceAnnotations(CollectionsKt.plus(backingField2.getAnnotations(), firAnnotation));
                return false;
            }
        }
        return true;
    }

    private final boolean propertyAnnotationShouldBeMovedToField(Set<? extends AnnotationUseSiteTarget> allowedTargets) {
        return (allowedTargets.contains(AnnotationUseSiteTarget.FIELD) || allowedTargets.contains(AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD)) && !allowedTargets.contains(AnnotationUseSiteTarget.PROPERTY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirStatement resolveClassContent(FirClass firClass, Object data) {
        FirRegularClass firRegularClass;
        FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope;
        PersistentList<FirScope> scopes = getScopes();
        PersistentList<FirScope> scopesBefore = getScopesBefore();
        setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
        setStaticScopesBefore(staticScopes);
        try {
            if (removeOuterTypeParameterScope(firClass)) {
                setScopes(getStaticScopes());
            }
            scopes = getScopes();
            scopesBefore = getScopesBefore();
            setScopesBefore(scopes);
            staticScopes = getStaticScopes();
            staticScopesBefore = getStaticScopesBefore();
            setStaticScopesBefore(staticScopes);
            try {
                firClass.transformAnnotations((FirTransformer<? super Object>) this, (Object) null);
                if (firClass instanceof FirRegularClass) {
                    addTypeParametersScope(firClass);
                }
                for (FirDeclaration firDeclaration : firClass.getDeclarations()) {
                    if (firDeclaration instanceof FirConstructor) {
                        transformDelegatedConstructorCall((FirConstructor) firDeclaration);
                    } else if ((firDeclaration instanceof FirField) && Intrinsics.areEqual(((FirField) firDeclaration).getOrigin(), FirDeclarationOrigin.Synthetic.DelegateField.INSTANCE)) {
                        transformDelegateField((FirField) firDeclaration);
                    }
                }
                Unit unit = Unit.INSTANCE;
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
                List<ConeClassLikeType> listAsReversed = CollectionsKt.asReversed(SupertypeUtilsKt.lookupSuperTypes$default(firClass, false, true, getSession(), true, null, 32, null));
                ArrayList arrayList = new ArrayList();
                for (ConeClassLikeType coneClassLikeType : listAsReversed) {
                    FirContainingNamesAwareScope nestedClassifierScope = ScopesKt.getNestedClassifierScope(coneClassLikeType.getLookupTag(), getSession(), getScopeSession());
                    if (nestedClassifierScope != null) {
                        arrayList.add(FirNestedClassifierScopeWithSubstitutionKt.wrapNestedClassifierScopeWithSubstitutionForSuperType(nestedClassifierScope, coneClassLikeType, getSession()));
                    }
                }
                if (firClass instanceof FirRegularClass) {
                    FirRegularClassSymbol companionObjectSymbol = ((FirRegularClass) firClass).getCompanionObjectSymbol();
                    if (companionObjectSymbol != null && (firRegularClass = (FirRegularClass) companionObjectSymbol.getFir()) != null && (firNestedClassifierScopeNestedClassifierScope = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(getSession(), firRegularClass)) != null) {
                        arrayList.add(firNestedClassifierScopeNestedClassifierScope);
                    }
                    FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope2 = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(getSession(), firClass);
                    if (firNestedClassifierScopeNestedClassifierScope2 != null) {
                        arrayList.add(firNestedClassifierScopeNestedClassifierScope2);
                    }
                    addScopes(arrayList);
                    addTypeParametersScope(firClass);
                } else {
                    FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope3 = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(getSession(), firClass);
                    if (firNestedClassifierScopeNestedClassifierScope3 != null) {
                        arrayList.add(firNestedClassifierScopeNestedClassifierScope3);
                    }
                    addScopes(arrayList);
                }
                return (FirClass) transformElement(firClass, data);
            } finally {
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
            }
        } catch (Throwable th) {
            setScopes(scopes);
            setScopesBefore(scopesBefore);
            setStaticScopes(staticScopes);
            setStaticScopesBefore(staticScopesBefore);
            throw th;
        }
    }

    private final void resolveConstructedTypeRefForDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall) {
        delegatedConstructorCall.replaceConstructedTypeRef((FirTypeRef) FirTransformerUtilKt.transformSingle(delegatedConstructorCall.getConstructedTypeRef(), this, null));
        delegatedConstructorCall.transformCalleeReference((FirTransformer<? super Object>) this, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAccessorTypesByPropertyType(FirProperty property) {
        List<FirValueParameter> valueParameters;
        FirPropertyAccessor getter = property.getGetter();
        if (getter != null) {
            getter.replaceReturnTypeRef(property.getReturnTypeRef());
        }
        FirPropertyAccessor setter = property.getSetter();
        if (setter == null || (valueParameters = setter.getValueParameters()) == null) {
            return;
        }
        List<FirValueParameter> list = valueParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((FirValueParameter) it.next()).replaceReturnTypeRef(property.getReturnTypeRef());
            arrayList.add(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unboundCyclesInTypeParametersSupertypes(FirTypeParameterRefsOwner typeParametersOwner) {
        for (FirTypeParameterRef firTypeParameterRef : typeParametersOwner.getTypeParameters()) {
            if (firTypeParameterRef instanceof FirTypeParameter) {
                FirTypeParameter firTypeParameter = (FirTypeParameter) firTypeParameterRef;
                if (hasSupertypePathToParameter(firTypeParameter, firTypeParameter, new LinkedHashSet())) {
                    FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                    firErrorTypeRefBuilder.setDiagnostic(new ConeCyclicTypeBound(firTypeParameter.getSymbol(), ExtensionsKt.toImmutableList(firTypeParameter.getBounds())));
                    firErrorTypeRefBuilder.setSource(((FirTypeRef) CollectionsKt.first(firTypeParameter.getBounds())).getSource());
                    firTypeParameter.replaceBounds(CollectionsKt.listOf(firErrorTypeRefBuilder.build()));
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object withClassScopes$default(FirTypeResolveTransformer firTypeResolveTransformer, FirClass firClass, Function0 function0, Function0 function1, int i, Object obj) {
        FirRegularClass firRegularClass;
        FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope;
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: withClassScopes");
            return null;
        }
        Function0 function2 = (i & 2) != 0 ? new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.FirTypeResolveTransformer.withClassScopes.1
            public /* bridge */ /* synthetic */ Object invoke() {
                m601invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m601invoke() {
            }
        } : function0;
        firClass.getClass();
        function2.getClass();
        function1.getClass();
        PersistentList<FirScope> scopes = firTypeResolveTransformer.getScopes();
        PersistentList<FirScope> scopesBefore = firTypeResolveTransformer.getScopesBefore();
        firTypeResolveTransformer.setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = firTypeResolveTransformer.getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = firTypeResolveTransformer.getStaticScopesBefore();
        firTypeResolveTransformer.setStaticScopesBefore(staticScopes);
        try {
            if (firTypeResolveTransformer.removeOuterTypeParameterScope(firClass)) {
                firTypeResolveTransformer.setScopes(firTypeResolveTransformer.getStaticScopes());
            }
            function2.invoke();
            List<ConeClassLikeType> listAsReversed = CollectionsKt.asReversed(SupertypeUtilsKt.lookupSuperTypes$default(firClass, false, true, firTypeResolveTransformer.getSession(), true, null, 32, null));
            ArrayList arrayList = new ArrayList();
            for (ConeClassLikeType coneClassLikeType : listAsReversed) {
                FirContainingNamesAwareScope nestedClassifierScope = ScopesKt.getNestedClassifierScope(coneClassLikeType.getLookupTag(), firTypeResolveTransformer.getSession(), firTypeResolveTransformer.getScopeSession());
                if (nestedClassifierScope != null) {
                    arrayList.add(FirNestedClassifierScopeWithSubstitutionKt.wrapNestedClassifierScopeWithSubstitutionForSuperType(nestedClassifierScope, coneClassLikeType, firTypeResolveTransformer.getSession()));
                }
            }
            if (firClass instanceof FirRegularClass) {
                FirRegularClassSymbol companionObjectSymbol = ((FirRegularClass) firClass).getCompanionObjectSymbol();
                if (companionObjectSymbol != null && (firRegularClass = (FirRegularClass) companionObjectSymbol.getFir()) != null && (firNestedClassifierScopeNestedClassifierScope = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(firTypeResolveTransformer.getSession(), firRegularClass)) != null) {
                    arrayList.add(firNestedClassifierScopeNestedClassifierScope);
                }
                FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope2 = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(firTypeResolveTransformer.getSession(), firClass);
                if (firNestedClassifierScopeNestedClassifierScope2 != null) {
                    arrayList.add(firNestedClassifierScopeNestedClassifierScope2);
                }
                firTypeResolveTransformer.addScopes(arrayList);
                firTypeResolveTransformer.addTypeParametersScope(firClass);
            } else {
                FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope3 = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(firTypeResolveTransformer.getSession(), firClass);
                if (firNestedClassifierScopeNestedClassifierScope3 != null) {
                    arrayList.add(firNestedClassifierScopeNestedClassifierScope3);
                }
                firTypeResolveTransformer.addScopes(arrayList);
            }
            return function1.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            firTypeResolveTransformer.setScopes(scopes);
            firTypeResolveTransformer.setScopesBefore(scopesBefore);
            firTypeResolveTransformer.setStaticScopes(staticScopes);
            firTypeResolveTransformer.setStaticScopesBefore(staticScopesBefore);
            InlineMarker.finallyEnd(1);
        }
    }

    public final void addScopes(List<? extends FirScope> list) {
        list.getClass();
        PersistentList<? extends FirScope> persistentList = this.scopes;
        boolean z = persistentList == this.staticScopes;
        List<? extends FirScope> list2 = list;
        PersistentList<? extends FirScope> persistentListAddAll = persistentList.addAll(list2);
        this.scopes = persistentListAddAll;
        if (!z) {
            persistentListAddAll = this.staticScopes.addAll(list2);
        }
        this.staticScopes = persistentListAddAll;
    }

    public final void addTypeParametersScope(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        if (firMemberDeclaration.getTypeParameters().isEmpty()) {
            return;
        }
        this.scopes = this.scopes.add(new FirMemberTypeParameterScope(firMemberDeclaration));
    }

    public final ArrayDeque<FirClass> getClassDeclarationsStack() {
        return this.classDeclarationsStack;
    }

    public final FirFile getCurrentFile() {
        return this.currentFile;
    }

    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public final PersistentList<FirScope> getScopes() {
        return this.scopes;
    }

    public final PersistentList<FirScope> getScopesBefore() {
        return this.scopesBefore;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public final FirSession getSession() {
        return this.session;
    }

    public final PersistentList<FirScope> getStaticScopes() {
        return this.staticScopes;
    }

    public final PersistentList<FirScope> getStaticScopesBefore() {
        return this.staticScopesBefore;
    }

    public final boolean removeOuterTypeParameterScope(FirClass firClass) {
        firClass.getClass();
        return (firClass.getStatus().isInner() || firClass.getIsLocal()) ? false : true;
    }

    public final void setCurrentFile(FirFile firFile) {
        this.currentFile = firFile;
    }

    public final void setScopes(PersistentList<? extends FirScope> persistentList) {
        persistentList.getClass();
        this.scopes = persistentList;
    }

    @PrivateForInline
    public final void setScopesBefore(PersistentList<? extends FirScope> persistentList) {
        this.scopesBefore = persistentList;
    }

    public final void setStaticScopes(PersistentList<? extends FirScope> persistentList) {
        persistentList.getClass();
        this.staticScopes = persistentList;
    }

    @PrivateForInline
    public final void setStaticScopesBefore(PersistentList<? extends FirScope> persistentList) {
        this.staticScopesBefore = persistentList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotation(FirAnnotation annotation, Object data) throws KotlinNothingValueException {
        annotation.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotationCall(FirAnnotationCall annotationCall, Object data) {
        FirTypeRef firTypeRef;
        annotationCall.getClass();
        FirSession firSession = this.session;
        try {
            FirTypeRef annotationTypeRef = annotationCall.getAnnotationTypeRef();
            if (!(annotationTypeRef instanceof FirResolvedTypeRef)) {
                FirTypeRef firTypeRef2 = (FirTypeRef) FirTransformerUtilKt.transformSingle(annotationTypeRef, this, data);
                annotationCall.transformTypeArguments((FirTransformer<? super Object>) this, data);
                annotationCall.replaceAnnotationResolvePhase(FirAnnotationResolvePhase.Types);
                annotationCall.replaceAnnotationTypeRef(firTypeRef2);
                return annotationCall;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[annotationCall.getAnnotationResolvePhase().ordinal()];
            if (i == 1) {
                if (((FirResolvedTypeRef) annotationTypeRef) instanceof FirErrorTypeRef) {
                    annotationCall.replaceAnnotationResolvePhase(FirAnnotationResolvePhase.Types);
                    return annotationCall;
                }
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                throw new KotlinNothingValueException();
            }
            if (i == 2) {
                annotationCall.transformTypeArguments((FirTransformer<? super Object>) this, data);
                annotationCall.replaceAnnotationResolvePhase(FirAnnotationResolvePhase.Types);
                FirTypeRef delegatedTypeRef = ((FirResolvedTypeRef) annotationTypeRef).getDelegatedTypeRef();
                if (delegatedTypeRef != null && (firTypeRef = (FirTypeRef) FirTransformerUtilKt.transformSingle(delegatedTypeRef, this, data)) != null) {
                    ConeKotlinType coneType = ((FirResolvedTypeRef) annotationTypeRef).getConeType();
                    ConeKotlinType coneType2 = FirTypeUtilsKt.getConeType(firTypeRef);
                    if (!Intrinsics.areEqual(coneType2, coneType)) {
                        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                        firErrorTypeRefBuilder.setSource(((FirResolvedTypeRef) annotationTypeRef).getSource());
                        firErrorTypeRefBuilder.setConeType(coneType);
                        CollectionsKt.addAll(firErrorTypeRefBuilder.getAnnotations(), ((FirResolvedTypeRef) annotationTypeRef).getAnnotations());
                        firErrorTypeRefBuilder.setDelegatedTypeRef(((FirResolvedTypeRef) annotationTypeRef).getDelegatedTypeRef());
                        firErrorTypeRefBuilder.setDiagnostic(new ConeAmbiguouslyResolvedAnnotationFromPlugin(coneType, coneType2));
                        annotationCall.replaceAnnotationTypeRef(firErrorTypeRefBuilder.build());
                    }
                }
            } else if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            return annotationCall;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(annotationCall, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirAnonymousInitializer transformAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, Object data) {
        anonymousInitializer.getClass();
        PersistentList<FirScope> scopes = getScopes();
        PersistentList<FirScope> scopesBefore = getScopesBefore();
        setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
        setStaticScopesBefore(staticScopes);
        try {
            FirDeclaration firDeclarationTransformDeclaration = transformDeclaration(anonymousInitializer, data);
            firDeclarationTransformDeclaration.getClass();
            return (FirAnonymousInitializer) firDeclarationTransformDeclaration;
        } finally {
            setScopes(scopes);
            setScopesBefore(scopesBefore);
            setStaticScopes(staticScopes);
            setStaticScopesBefore(staticScopesBefore);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousObject(FirAnonymousObject anonymousObject, Object data) {
        anonymousObject.getClass();
        ArrayDeque<FirClass> arrayDeque = this.classDeclarationsStack;
        arrayDeque.addLast(anonymousObject);
        try {
            return resolveClassContent(anonymousObject, data);
        } finally {
            arrayDeque.removeLast();
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirArgumentList transformArgumentList(FirArgumentList argumentList, Object data) {
        argumentList.getClass();
        return argumentList;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBackingField(FirBackingField backingField, Object data) {
        backingField.getClass();
        FirSession firSession = this.session;
        try {
            backingField.transformAnnotations((FirTransformer<? super Object>) this, data);
            return super.transformBackingField(backingField, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(backingField, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBlock(FirBlock block, Object data) {
        block.getClass();
        return block;
    }

    public final void transformClassTypeParameters(FirRegularClass regularClass, Object data) {
        regularClass.getClass();
        PersistentList<FirScope> scopes = getScopes();
        PersistentList<FirScope> scopesBefore = getScopesBefore();
        setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
        setStaticScopesBefore(staticScopes);
        try {
            if (removeOuterTypeParameterScope(regularClass)) {
                setScopes(getStaticScopes());
            }
            addTypeParametersScope(regularClass);
            Iterator<T> it = regularClass.getTypeParameters().iterator();
            while (it.hasNext()) {
                ((FirTypeParameterRef) it.next()).accept(this, data);
            }
            unboundCyclesInTypeParametersSupertypes(regularClass);
            Unit unit = Unit.INSTANCE;
        } finally {
            setScopes(scopes);
            setScopesBefore(scopesBefore);
            setStaticScopes(staticScopes);
            setStaticScopesBefore(staticScopesBefore);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirConstructor transformConstructor(FirConstructor constructor, Object data) {
        constructor.getClass();
        FirSession firSession = this.session;
        try {
            PersistentList<FirScope> scopes = getScopes();
            PersistentList<FirScope> scopesBefore = getScopesBefore();
            setScopesBefore(scopes);
            PersistentList<FirScope> staticScopes = getStaticScopes();
            PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
            setStaticScopesBefore(staticScopes);
            try {
                addTypeParametersScope(constructor);
                FirDeclaration firDeclarationTransformDeclaration = transformDeclaration(constructor, data);
                firDeclarationTransformDeclaration.getClass();
                FirConstructor firConstructor = (FirConstructor) firDeclarationTransformDeclaration;
                if (firConstructor.getIsPrimary()) {
                    boolean z = ((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue() && FirModuleDataKt.getModuleData(getSession()).getIsCommon() && Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getConeType(constructor.getReturnTypeRef())), StandardClassIds.INSTANCE.getEnum());
                    for (FirValueParameter firValueParameter : firConstructor.getValueParameters()) {
                        if (ClassMembersKt.getCorrespondingProperty(firValueParameter) != null) {
                            moveOrDeleteIrrelevantAnnotations(firValueParameter);
                        }
                        if (z) {
                            FirExpressionStubBuilder firExpressionStubBuilder = new FirExpressionStubBuilder();
                            Unit unit = Unit.INSTANCE;
                            firValueParameter.replaceDefaultValue(firExpressionStubBuilder.mo288build());
                        }
                    }
                }
                return firConstructor;
            } finally {
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(constructor, th);
            wq6.a();
            return null;
        }
    }

    public final void transformDelegateField(FirField field) {
        field.getClass();
        field.transformReturnTypeRef((FirTransformer<? super Object>) this, (Object) null);
    }

    public final void transformDelegatedConstructorCall(FirConstructor constructor) {
        constructor.getClass();
        FirDelegatedConstructorCall delegatedConstructor = constructor.getDelegatedConstructor();
        if (delegatedConstructor != null) {
            resolveConstructedTypeRefForDelegatedConstructorCall(delegatedConstructor);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirEnumEntry transformEnumEntry(FirEnumEntry enumEntry, Object data) {
        enumEntry.getClass();
        FirSession firSession = this.session;
        try {
            enumEntry.transformReturnTypeRef((FirTransformer<? super Object>) this, data);
            enumEntry.transformTypeParameters((FirTransformer<? super Object>) this, data);
            enumEntry.transformAnnotations((FirTransformer<? super Object>) this, data);
            return enumEntry;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(enumEntry, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirConstructor transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, Object data) {
        errorPrimaryConstructor.getClass();
        return transformConstructor((FirConstructor) errorPrimaryConstructor, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirField transformField(FirField field, Object data) {
        field.getClass();
        FirSession firSession = this.session;
        try {
            PersistentList<FirScope> scopes = getScopes();
            PersistentList<FirScope> scopesBefore = getScopesBefore();
            setScopesBefore(scopes);
            PersistentList<FirScope> staticScopes = getStaticScopes();
            PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
            setStaticScopesBefore(staticScopes);
            try {
                field.transformReturnTypeRef((FirTransformer<? super Object>) this, data).transformAnnotations((FirTransformer<? super Object>) this, data);
                return field;
            } finally {
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(field, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractPhaseTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, Object data) {
        file.getClass();
        checkSessionConsistency(file);
        setCurrentFile(file);
        PersistentList<FirScope> scopes = getScopes();
        PersistentList<FirScope> scopesBefore = getScopesBefore();
        setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
        setStaticScopesBefore(staticScopes);
        try {
            addScopes(ImportingScopesKt.createImportingScopes$default(file, getSession(), getScopeSession(), false, 8, null));
            return super.transformFile(file, data);
        } finally {
            setScopes(scopes);
            setScopesBefore(scopesBefore);
            setStaticScopes(staticScopes);
            setStaticScopesBefore(staticScopesBefore);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformImplicitTypeRef(FirImplicitTypeRef implicitTypeRef, Object data) {
        implicitTypeRef.getClass();
        return implicitTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirNamedFunction transformNamedFunction(FirNamedFunction namedFunction, Object data) {
        namedFunction.getClass();
        FirSession firSession = this.session;
        try {
            PersistentList<FirScope> scopes = getScopes();
            PersistentList<FirScope> scopesBefore = getScopesBefore();
            setScopesBefore(scopes);
            PersistentList<FirScope> staticScopes = getStaticScopes();
            PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
            setStaticScopesBefore(staticScopes);
            try {
                if (namedFunction.getStatus().isStatic()) {
                    setScopes(getStaticScopes());
                }
                FirDeclaration firDeclaration = this.currentDeclaration;
                try {
                    this.currentDeclaration = namedFunction;
                    addTypeParametersScope(namedFunction);
                    FirAnnotationContainer firAnnotationContainerTransformDeclaration = transformDeclaration(namedFunction, data);
                    firAnnotationContainerTransformDeclaration.getClass();
                    unboundCyclesInTypeParametersSupertypes((FirTypeParametersOwner) firAnnotationContainerTransformDeclaration);
                    KtSourceElement source = firAnnotationContainerTransformDeclaration.getSource();
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE) && (firAnnotationContainerTransformDeclaration instanceof FirNamedFunction) && Intrinsics.areEqual(((FirNamedFunction) firAnnotationContainerTransformDeclaration).getName(), StandardNames.DATA_CLASS_COPY)) {
                        Iterator<FirValueParameter> it = ((FirNamedFunction) firAnnotationContainerTransformDeclaration).getValueParameters().iterator();
                        while (it.hasNext()) {
                            moveOrDeleteIrrelevantAnnotations(it.next());
                        }
                    }
                    this.currentDeclaration = firDeclaration;
                    setScopes(scopes);
                    setScopesBefore(scopesBefore);
                    setStaticScopes(staticScopes);
                    setStaticScopesBefore(staticScopesBefore);
                    return (FirNamedFunction) firAnnotationContainerTransformDeclaration;
                } catch (Throwable th) {
                    this.currentDeclaration = firDeclaration;
                    throw th;
                }
            } catch (Throwable th2) {
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
                throw th2;
            }
        } catch (Throwable th3) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(namedFunction, th3);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirProperty transformProperty(FirProperty property, Object data) {
        List<FirValueParameter> valueParameters;
        property.getClass();
        FirSession firSession = this.session;
        try {
            PersistentList<FirScope> scopes = getScopes();
            PersistentList<FirScope> scopesBefore = getScopesBefore();
            setScopesBefore(scopes);
            PersistentList<FirScope> staticScopes = getStaticScopes();
            PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
            setStaticScopesBefore(staticScopes);
            try {
                if (property.getStatus().isStatic()) {
                    setScopes(getStaticScopes());
                }
                FirDeclaration firDeclaration = this.currentDeclaration;
                try {
                    this.currentDeclaration = property;
                    addTypeParametersScope(property);
                    property.transformTypeParameters((FirTransformer<? super Object>) this, data).transformReturnTypeRef((FirTransformer<? super Object>) this, data).transformReceiverParameter((FirTransformer<? super Object>) this, data).transformContextParameters((FirTransformer<? super Object>) this, data).transformGetter((FirTransformer<? super Object>) this, data).transformSetter((FirTransformer<? super Object>) this, data).transformBackingField((FirTransformer<? super Object>) this, data).transformAnnotations((FirTransformer<? super Object>) this, data);
                    if (Intrinsics.areEqual(DeclarationAttributesKt.isFromVararg(property), Boolean.TRUE)) {
                        TransformUtilsKt.transformTypeToArrayType(property, getSession());
                        FirBackingField backingField = property.getBackingField();
                        if (backingField != null) {
                            TransformUtilsKt.transformTypeToArrayType(backingField, getSession());
                        }
                        setAccessorTypesByPropertyType(property);
                    }
                    if ((property.getReturnTypeRef() instanceof FirResolvedTypeRef) && property.getDelegate() != null) {
                        setAccessorTypesByPropertyType(property);
                    } else if (!(property.getReturnTypeRef() instanceof FirResolvedTypeRef) && property.getInitializer() == null) {
                        FirPropertyAccessor getter = property.getGetter();
                        if ((getter != null ? getter.getReturnTypeRef() : null) instanceof FirResolvedTypeRef) {
                            FirPropertyAccessor getter2 = property.getGetter();
                            getter2.getClass();
                            FirTypeRef returnTypeRef = getter2.getReturnTypeRef();
                            KtFakeSourceElementKind.PropertyTypeFromGetterReturnType propertyTypeFromGetterReturnType = KtFakeSourceElementKind.PropertyTypeFromGetterReturnType.INSTANCE;
                            property.replaceReturnTypeRef(UtilsKt.copyWithNewSourceKind(returnTypeRef, propertyTypeFromGetterReturnType));
                            FirBackingField backingField2 = property.getBackingField();
                            if (backingField2 != null) {
                                backingField2.replaceReturnTypeRef(UtilsKt.copyWithNewSourceKind(returnTypeRef, propertyTypeFromGetterReturnType));
                            }
                            FirPropertyAccessor setter = property.getSetter();
                            if (setter != null && (valueParameters = setter.getValueParameters()) != null) {
                                Iterator<T> it = valueParameters.iterator();
                                while (it.hasNext()) {
                                    ((FirValueParameter) it.next()).replaceReturnTypeRef(UtilsKt.copyWithNewSourceKind(returnTypeRef, KtFakeSourceElementKind.PropertyTypeFromGetterReturnType.INSTANCE));
                                }
                            }
                        }
                    }
                    unboundCyclesInTypeParametersSupertypes(property);
                    moveOrDeleteIrrelevantAnnotations(property);
                    this.currentDeclaration = firDeclaration;
                    setScopes(scopes);
                    setScopesBefore(scopesBefore);
                    setStaticScopes(staticScopes);
                    setStaticScopesBefore(staticScopesBefore);
                    return property;
                } catch (Throwable th) {
                    this.currentDeclaration = firDeclaration;
                    throw th;
                }
            } catch (Throwable th2) {
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
                throw th2;
            }
        } catch (Throwable th3) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(property, th3);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReceiverParameter transformReceiverParameter(FirReceiverParameter receiverParameter, Object data) {
        receiverParameter.getClass();
        FirReceiverParameter firReceiverParameterTransformAnnotations = receiverParameter.transformAnnotations((FirTransformer<? super Object>) this, data);
        FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer = this.typeResolverTransformer;
        FirBasedSymbol<?> containingDeclarationSymbol = receiverParameter.getContainingDeclarationSymbol();
        boolean z = (containingDeclarationSymbol instanceof FirCallableSymbol) && FirSymbolStatusUtilsKt.isCompanionExtension((FirCallableSymbol) containingDeclarationSymbol);
        boolean areBareTypesAllowed = firSpecificTypeResolverTransformer.getAreBareTypesAllowed();
        firSpecificTypeResolverTransformer.setAreBareTypesAllowed(z);
        try {
            return firReceiverParameterTransformAnnotations.transformTypeRef(this, data);
        } finally {
            firSpecificTypeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformRegularClass(FirRegularClass regularClass, Object data) {
        regularClass.getClass();
        FirSession firSession = this.session;
        try {
            ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
            classDeclarationsStack.addLast(regularClass);
            try {
                transformClassTypeParameters(regularClass, data);
                return resolveClassContent(regularClass, data);
            } finally {
                classDeclarationsStack.removeLast();
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(regularClass, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReplSnippet transformReplSnippet(FirReplSnippet replSnippet, Object data) {
        FirScope snippetScope;
        replSnippet.getClass();
        FirSession firSession = this.session;
        try {
            PersistentList<FirScope> scopes = getScopes();
            PersistentList<FirScope> scopesBefore = getScopesBefore();
            setScopesBefore(scopes);
            PersistentList<FirScope> staticScopes = getStaticScopes();
            PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
            setStaticScopesBefore(staticScopes);
            try {
                FirReplSnippetResolveExtension replSnippetResolveExtension = FirReplSnippetResolveExtensionKt.getReplSnippetResolveExtension(getSession());
                if (replSnippetResolveExtension != null && (snippetScope = replSnippetResolveExtension.getSnippetScope(replSnippet, getSession())) != null) {
                    addScopes(CollectionsKt.listOf(snippetScope));
                }
                return (FirReplSnippet) transformElement(replSnippet, data);
            } finally {
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(replSnippet, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeAlias transformTypeAlias(FirTypeAlias typeAlias, Object data) {
        typeAlias.getClass();
        FirSession firSession = this.session;
        try {
            PersistentList<FirScope> scopes = getScopes();
            PersistentList<FirScope> scopesBefore = getScopesBefore();
            setScopesBefore(scopes);
            PersistentList<FirScope> staticScopes = getStaticScopes();
            PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
            setStaticScopesBefore(staticScopes);
            try {
                addTypeParametersScope(typeAlias);
                FirDeclaration firDeclarationTransformDeclaration = transformDeclaration(typeAlias, data);
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
                firDeclarationTransformDeclaration.getClass();
                return (FirTypeAlias) firDeclarationTransformDeclaration;
            } catch (Throwable th) {
                setScopes(scopes);
                setScopesBefore(scopesBefore);
                setStaticScopes(staticScopes);
                setStaticScopesBefore(staticScopesBefore);
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(typeAlias, th2);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    /* JADX INFO: renamed from: transformTypeRef, reason: merged with bridge method [inline-methods] */
    public FirResolvedTypeRef mo600transformTypeRef(FirTypeRef typeRef, Object data) {
        typeRef.getClass();
        return typeRef.transform(this.typeResolverTransformer, new TypeResolutionConfiguration((Iterable) CollectionsKt.asReversed(this.scopes), (List) this.classDeclarationsStack, this.currentFile, (FirDeclaration) null, 8, (DefaultConstructorMarker) null));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformValueParameter(FirValueParameter valueParameter, Object data) {
        valueParameter.getClass();
        FirSession firSession = this.session;
        try {
            FirDeclaration firDeclaration = this.currentDeclaration;
            try {
                this.currentDeclaration = valueParameter;
                valueParameter.transformReturnTypeRef((FirTransformer<? super Object>) this, data);
                valueParameter.transformAnnotations((FirTransformer<? super Object>) this, data);
                TransformUtilsKt.transformVarargTypeToArrayType(valueParameter, getSession());
                return valueParameter;
            } finally {
                this.currentDeclaration = firDeclaration;
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(firSession).handleExceptionOnElementAnalysis(valueParameter, th);
            wq6.a();
            return null;
        }
    }

    public final <R> R withClassDeclarationCleanup(FirRegularClass regularClass, Function0<? extends R> action) {
        regularClass.getClass();
        action.getClass();
        ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
        classDeclarationsStack.addLast(regularClass);
        try {
            return (R) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            classDeclarationsStack.removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> R withClassScopes(FirClass firClass, Function0<Unit> actionInsideStaticScope, Function0<? extends R> action) {
        FirRegularClass firRegularClass;
        FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope;
        firClass.getClass();
        actionInsideStaticScope.getClass();
        action.getClass();
        PersistentList<FirScope> scopes = getScopes();
        PersistentList<FirScope> scopesBefore = getScopesBefore();
        setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
        setStaticScopesBefore(staticScopes);
        try {
            if (removeOuterTypeParameterScope(firClass)) {
                setScopes(getStaticScopes());
            }
            actionInsideStaticScope.invoke();
            List<ConeClassLikeType> listAsReversed = CollectionsKt.asReversed(SupertypeUtilsKt.lookupSuperTypes$default(firClass, false, true, getSession(), true, null, 32, null));
            ArrayList arrayList = new ArrayList();
            for (ConeClassLikeType coneClassLikeType : listAsReversed) {
                FirContainingNamesAwareScope nestedClassifierScope = ScopesKt.getNestedClassifierScope(coneClassLikeType.getLookupTag(), getSession(), getScopeSession());
                if (nestedClassifierScope != null) {
                    arrayList.add(FirNestedClassifierScopeWithSubstitutionKt.wrapNestedClassifierScopeWithSubstitutionForSuperType(nestedClassifierScope, coneClassLikeType, getSession()));
                }
            }
            if (firClass instanceof FirRegularClass) {
                FirRegularClassSymbol companionObjectSymbol = ((FirRegularClass) firClass).getCompanionObjectSymbol();
                if (companionObjectSymbol != null && (firRegularClass = (FirRegularClass) companionObjectSymbol.getFir()) != null && (firNestedClassifierScopeNestedClassifierScope = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(getSession(), firRegularClass)) != null) {
                    arrayList.add(firNestedClassifierScopeNestedClassifierScope);
                }
                FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope2 = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(getSession(), firClass);
                if (firNestedClassifierScopeNestedClassifierScope2 != null) {
                    arrayList.add(firNestedClassifierScopeNestedClassifierScope2);
                }
                addScopes(arrayList);
                addTypeParametersScope(firClass);
            } else {
                FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope3 = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(getSession(), firClass);
                if (firNestedClassifierScopeNestedClassifierScope3 != null) {
                    arrayList.add(firNestedClassifierScopeNestedClassifierScope3);
                }
                addScopes(arrayList);
            }
            return (R) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setScopes(scopes);
            setScopesBefore(scopesBefore);
            setStaticScopes(staticScopes);
            setStaticScopesBefore(staticScopesBefore);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withFileScope(FirFile file, Function0<? extends R> action) {
        file.getClass();
        action.getClass();
        setCurrentFile(file);
        PersistentList<FirScope> scopes = getScopes();
        PersistentList<FirScope> scopesBefore = getScopesBefore();
        setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
        setStaticScopesBefore(staticScopes);
        try {
            addScopes(ImportingScopesKt.createImportingScopes$default(file, getSession(), getScopeSession(), false, 8, null));
            return (R) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setScopes(scopes);
            setScopesBefore(scopesBefore);
            setStaticScopes(staticScopes);
            setStaticScopesBefore(staticScopesBefore);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withReplSnippetScope(FirReplSnippet replSnippet, Function0<? extends R> action) {
        FirScope snippetScope;
        replSnippet.getClass();
        action.getClass();
        PersistentList<FirScope> scopes = getScopes();
        PersistentList<FirScope> scopesBefore = getScopesBefore();
        setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
        setStaticScopesBefore(staticScopes);
        try {
            FirReplSnippetResolveExtension replSnippetResolveExtension = FirReplSnippetResolveExtensionKt.getReplSnippetResolveExtension(getSession());
            if (replSnippetResolveExtension != null && (snippetScope = replSnippetResolveExtension.getSnippetScope(replSnippet, getSession())) != null) {
                addScopes(CollectionsKt.listOf(snippetScope));
            }
            return (R) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setScopes(scopes);
            setScopesBefore(scopesBefore);
            setStaticScopes(staticScopes);
            setStaticScopesBefore(staticScopesBefore);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withScopeCleanup(Function0<? extends T> l) {
        l.getClass();
        PersistentList<FirScope> scopes = getScopes();
        PersistentList<FirScope> scopesBefore = getScopesBefore();
        setScopesBefore(scopes);
        PersistentList<FirScope> staticScopes = getStaticScopes();
        PersistentList<FirScope> staticScopesBefore = getStaticScopesBefore();
        setStaticScopesBefore(staticScopes);
        try {
            return (T) l.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setScopes(scopes);
            setScopesBefore(scopesBefore);
            setStaticScopes(staticScopes);
            setStaticScopesBefore(staticScopesBefore);
            InlineMarker.finallyEnd(1);
        }
    }

    public /* synthetic */ FirTypeResolveTransformer(FirSession firSession, ScopeSession scopeSession, List list, FirFile firFile, ArrayDeque arrayDeque, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? null : firFile, (i & 16) != 0 ? new ArrayDeque() : arrayDeque);
    }
}

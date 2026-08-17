package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataElement;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKindKt;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.declarations.ScriptScopesKt;
import org.jetbrains.kotlin.fir.declarations.TowerElementsForClass;
import org.jetbrains.kotlin.fir.declarations.TowerElementsForScript;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.InaccessibleReceiverKind;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtension;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionHacksComponent;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionHacksComponentKt;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.FirCodeFragmentContext;
import org.jetbrains.kotlin.fir.resolve.FirRegularTowerDataContexts;
import org.jetbrains.kotlin.fir.resolve.FirSpecialTowerDataContexts;
import org.jetbrains.kotlin.fir.resolve.FirTowerDataMode;
import org.jetbrains.kotlin.fir.resolve.ImplicitValueStorage;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitContextParameterValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitExtensionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValueForScriptOrSnippet;
import org.jetbrains.kotlin.fir.resolve.calls.InaccessibleImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.dfa.DataFlowAnalyzerContext;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession;
import org.jetbrains.kotlin.fir.resolve.transformers.PublishedApiEffectiveVisibilityKt;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.ImportingScopesKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirMemberTypeParameterScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000È\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ%\u0010V\u001a\u0002HW\"\u0004\b\u0000\u0010W2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HW0YH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010ZJ%\u0010]\u001a\u0002HW\"\u0004\b\u0000\u0010W2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HW0YH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010ZJ\"\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020E2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020_0YH\u0086\bø\u0001\u0000J-\u0010v\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0006\u0010x\u001a\u00020\u001d2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010zJ-\u0010{\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0006\u0010|\u001a\u00020*2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010}J0\u0010~\u001a\u0002HW\"\u0004\b\u0000\u0010W2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u0002HW0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u0082\u0001J3\u0010\u0089\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\n\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u0084\u00012\f\u0010X\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u008b\u0001J1\u0010\u008c\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u008a\u0001\u001a\u00030\u0084\u00012\f\u0010X\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u008b\u0001J4\u0010\u008f\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0007\u0010\u0090\u0001\u001a\u00020<2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0087\bb\u0002\b\u000eø\u0001\u0000¢\u0006\u0003\u0010\u0091\u0001J1\u0010\u0092\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0007\u0010\u0090\u0001\u001a\u00020E2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0083\bb\u0002\b\u000e¢\u0006\u0003\u0010\u0093\u0001J0\u0010\u0094\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0007\u0010\u0090\u0001\u001a\u00020h2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u0095\u0001J+\u0010\u0096\u0001\u001a\u0002HW\"\u0004\b\u0000\u0010W2\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u0002HW0YH\u0087\bb\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010ZJ5\u0010\u0097\u0001\u001a\u0002HW\"\u0004\b\u0000\u0010W2\u0007\u0010\u0098\u0001\u001a\u00020\u00072\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u0002HW0YH\u0087\bb\u0002\b\u000eø\u0001\u0000¢\u0006\u0003\u0010\u0099\u0001J6\u0010\u009a\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\t\u0010\u009b\u0001\u001a\u0004\u0018\u00010/2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0087\bb\u0002\b\u000eø\u0001\u0000¢\u0006\u0003\u0010\u009c\u0001J+\u0010\u009d\u0001\u001a\u0002HW\"\u0004\b\u0000\u0010W2\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u0002HW0YH\u0087\bb\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010ZJ\u0015\u0010\u009e\u0001\u001a\u00020_2\u0006\u0010|\u001a\u00020*H\u0007b\u0002\b\u000eJ\r\u0010\u009f\u0001\u001a\u00020_H\u0007b\u0002\b\u000eJ\u0017\u0010 \u0001\u001a\u00020_2\b\u0010¡\u0001\u001a\u00030¢\u0001H\u0007b\u0002\b\u000eJ\u001e\u0010£\u0001\u001a\u00020_2\u000f\u0010¤\u0001\u001a\n\u0012\u0005\u0012\u00030¢\u00010¥\u0001H\u0007b\u0002\b\u000eJ\u0017\u0010¦\u0001\u001a\u00020_2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0007b\u0002\b\u000eJ'\u0010©\u0001\u001a\u00020_2\n\u0010ª\u0001\u001a\u0005\u0018\u00010«\u00012\f\u0010¬\u0001\u001a\u0007\u0012\u0002\b\u00030\u00ad\u0001H\u0007b\u0002\b\u000eJ\u0017\u0010®\u0001\u001a\u00020_2\b\u0010¯\u0001\u001a\u00030°\u0001H\u0007b\u0002\b\u000eJ,\u0010±\u0001\u001a\u00020_2\u001c\u0010²\u0001\u001a\u0017\u0012\u0005\u0012\u00030¨\u0001\u0012\u0005\u0012\u00030¨\u00010³\u0001¢\u0006\u0003\b´\u0001H\u0083\bb\u0002\b\u000eJ!\u0010µ\u0001\u001a\u00020_2\b\u0010\u008a\u0001\u001a\u00030¶\u00012\b\u0010·\u0001\u001a\u00030¸\u0001H\u0007b\u0002\b\u000eJT\u0010¹\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\n\u0010º\u0001\u001a\u0005\u0018\u00010«\u00012\b\u0010»\u0001\u001a\u00030¼\u00012\n\u0010½\u0001\u001a\u0005\u0018\u00010¾\u00012\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0083\bb\u0002\b\u000e¢\u0006\u0003\u0010Á\u0001J6\u0010Â\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u0090\u0001\u001a\u00030Ã\u00012\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0087\bb\u0002\b\u000eø\u0001\u0000¢\u0006\u0003\u0010Ä\u0001J\u0011\u0010Å\u0001\u001a\u0005\u0018\u00010Æ\u0001*\u00030Ã\u0001H\u0002J\u001c\u0010Ç\u0001\u001a\u00030¨\u00012\b\u0010È\u0001\u001a\u00030É\u00012\b\u0010·\u0001\u001a\u00030¸\u0001J\"\u0010Ê\u0001\u001a\u00020_2\t\u0010Ë\u0001\u001a\u0004\u0018\u00010E2\b\u0010¿\u0001\u001a\u00030À\u0001H\u0007b\u0002\b\u000eJ!\u0010Ì\u0001\u001a\u00020_2\b\u0010Í\u0001\u001a\u00030Î\u00012\b\u0010·\u0001\u001a\u00030¸\u0001H\u0003b\u0002\b\u000eJ\n\u0010Ï\u0001\u001a\u0005\u0018\u00010¨\u0001J\n\u0010Ð\u0001\u001a\u0005\u0018\u00010¨\u0001J\u001a\u0010Ñ\u0001\u001a\u00020_2\u0007\u0010Ò\u0001\u001a\u00020m2\b\u0010·\u0001\u001a\u00030¸\u0001J\u001b\u0010Ó\u0001\u001a\u00020_2\b\u0010Ô\u0001\u001a\u00030Õ\u00012\b\u0010·\u0001\u001a\u00030¸\u0001JI\u0010Ö\u0001\u001a\u0002HW\"\u0004\b\u0000\u0010W\"\t\b\u0001\u0010×\u0001*\u00020M2\u0007\u0010L\u001a\u0003H×\u00012\u001a\u0010X\u001a\u0016\u0012\u0005\u0012\u0003H×\u0001\u0012\u0004\u0012\u0002HW0³\u0001¢\u0006\u0003\b´\u0001H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010Ø\u0001J1\u0010Ù\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010Ú\u0001\u001a\u00030\u0080\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u0082\u0001J1\u0010Û\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010Ü\u0001\u001a\u00030Ý\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010Þ\u0001JI\u0010ß\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u001c\u0010|\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020M\u0018\u00010à\u0001j\u0005\u0018\u0001`á\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0087\bb\u0002\b\u000eø\u0001\u0000¢\u0006\u0003\u0010â\u0001J\u0011\u0010ã\u0001\u001a\u00020_2\b\u0010ä\u0001\u001a\u00030å\u0001J=\u0010æ\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010k\u001a\b\u0012\u0004\u0012\u00020m0l2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010ç\u0001J/\u0010è\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010é\u0001J9\u0010ê\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010ë\u0001J4\u0010ì\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0007\u0010í\u0001\u001a\u00020E2\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010î\u0001J4\u0010ï\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0007\u0010í\u0001\u001a\u00020E2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0087\bb\u0002\b\u000eø\u0001\u0000¢\u0006\u0003\u0010\u0093\u0001J1\u0010ð\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010ñ\u0001\u001a\u00030ò\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010ó\u0001J=\u0010ô\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010õ\u0001\u001a\u00030ö\u00012\b\u0010¿\u0001\u001a\u00030À\u00012\u000e\b\u0004\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010÷\u0001J4\u0010ø\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0007\u0010»\u0001\u001a\u00020h2\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010ù\u0001J5\u0010ú\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010»\u0001\u001a\u00030û\u00012\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010ü\u0001J5\u0010ý\u0001\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010þ\u0001\u001a\u00030ÿ\u00012\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010\u0080\u0002J5\u0010\u0081\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u0082\u0002\u001a\u00030\u0083\u00022\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010\u0084\u0002J'\u0010\u0085\u0002\u001a\u0011\u0012\u0005\u0012\u00030¨\u0001\u0012\u0005\u0012\u00030¨\u00010à\u0001*\u00030É\u00012\b\u0010·\u0001\u001a\u00030¸\u0001H\u0002J;\u0010\u0086\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u0087\u0002\u001a\u00030¶\u00012\b\u0010·\u0001\u001a\u00030¸\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u0088\u0002J5\u0010\u0089\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u008a\u0002\u001a\u00030¼\u00012\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010\u008b\u0002J5\u0010\u008c\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u008a\u0001\u001a\u00030\u0084\u00012\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010\u008d\u0002J5\u0010\u008e\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010È\u0001\u001a\u00030É\u00012\b\u0010·\u0001\u001a\u00030¸\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010\u008f\u0002J+\u0010\u0090\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u0091\u0002\u001a\u00030\u0092\u00022\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010\u0093\u0002J5\u0010\u0094\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010ä\u0001\u001a\u00030å\u00012\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010\u0095\u0002J\u0011\u0010\u0096\u0002\u001a\u00020_2\b\u0010ä\u0001\u001a\u00030å\u0001J1\u0010\u0097\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u0098\u0002\u001a\u00030\u0099\u00022\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u009a\u0002J1\u0010\u009b\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010\u009c\u0002\u001a\u00030\u009d\u00022\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u009e\u0002J;\u0010\u009f\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010¯\u0001\u001a\u00030°\u00012\b\u0010·\u0001\u001a\u00030¸\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010 \u0002J;\u0010¡\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010¢\u0002\u001a\u00030£\u00022\b\u0010·\u0001\u001a\u00030¸\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010¤\u0002J\u001b\u0010¥\u0002\u001a\u00020_2\b\u0010¢\u0002\u001a\u00030£\u00022\b\u0010·\u0001\u001a\u00030¸\u0001J1\u0010¦\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010¢\u0002\u001a\u00030§\u00022\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010¨\u0002J1\u0010©\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010Í\u0001\u001a\u00030Î\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010ª\u0002J:\u0010«\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010Ô\u0001\u001a\u00030Î\u00012\u0007\u0010¬\u0002\u001a\u00020\u00072\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u00ad\u0002JJ\u0010®\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010Í\u0001\u001a\u00030Î\u00012\b\u0010¯\u0002\u001a\u00030°\u00022\b\u0010¿\u0001\u001a\u00030À\u00012\t\b\u0002\u0010±\u0002\u001a\u00020\u00072\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010²\u0002J0\u0010³\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\u0007\u0010\u0098\u0001\u001a\u00020\u00072\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u0099\u0001J1\u0010´\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010È\u0001\u001a\u00030É\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010µ\u0002JF\u0010¶\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010È\u0001\u001a\u00030É\u00012\t\u0010Ë\u0001\u001a\u0004\u0018\u00010E2\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010·\u0002JF\u0010¸\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010È\u0001\u001a\u00030É\u00012\t\u0010Ë\u0001\u001a\u0004\u0018\u00010E2\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010·\u0002J&\u0010¹\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010ZJF\u0010º\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010È\u0001\u001a\u00030É\u00012\t\u0010Ë\u0001\u001a\u0004\u0018\u00010E2\b\u0010¿\u0001\u001a\u00030À\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010·\u0002J\u0011\u0010»\u0002\u001a\u00020_2\b\u0010¼\u0002\u001a\u00030Ý\u0001J\u0011\u0010½\u0002\u001a\u00020_2\b\u0010¼\u0002\u001a\u00030Ý\u0001J5\u0010¾\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010¿\u0002\u001a\u00030À\u00022\b\u0010·\u0001\u001a\u00030¸\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0Y¢\u0006\u0003\u0010Á\u0002J1\u0010Â\u0002\u001a\u0002Hw\"\u0004\b\u0000\u0010w2\b\u0010·\u0001\u001a\u00030¸\u00012\f\u0010y\u001a\b\u0012\u0004\u0012\u0002Hw0YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010Ã\u0002R!\u0010\u0002\u001a\u00020\u0003@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R!\u0010\u0016\u001a\u00020\u0017@\u0007X\u0086.\u0082\u0001\u0002\b\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR(\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000er\u0002\b\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R \u0010$\u001a\u00020%8\u0006X\u0087\u0004r\u0002\b\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u001f\u001a\u0004\b'\u0010(R\u0017\u0010)\u001a\u00020*8F¢\u0006\f\u0012\u0004\b+\u0010\u001f\u001a\u0004\b,\u0010-R*\u00100\u001a\u00020/2\u0006\u0010.\u001a\u00020/8F@FX\u0086\u000e¢\u0006\u0012\u0012\u0004\b1\u0010\u001f\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0011\u00106\u001a\u0002078F¢\u0006\u0006\u001a\u0004\b8\u00109R'\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0013\u0010A\u001a\u0004\u0018\u00010<8F¢\u0006\u0006\u001a\u0004\bB\u0010CR#\u0010D\u001a\u0004\u0018\u00010E@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0013\u0010J\u001a\u0004\u0018\u00010<8F¢\u0006\u0006\u001a\u0004\bK\u0010CR!\u0010L\u001a\u00020M@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR!\u0010R\u001a\u00020\u0007@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR!\u0010[\u001a\u00020\u0007@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010S\"\u0004\b\\\u0010UR\u001b\u0010b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030d0c¢\u0006\b\n\u0000\u001a\u0004\be\u0010fR \u0010g\u001a\b\u0012\u0004\u0012\u00020h0;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010>\"\u0004\bj\u0010@R'\u0010k\u001a\b\u0012\u0004\u0012\u00020m0l@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010f\"\u0004\bo\u0010pR%\u0010q\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030s\u0012\b\u0012\u0006\u0012\u0002\b\u00030s0r¢\u0006\b\n\u0000\u001a\u0004\bt\u0010uR\"\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001\"\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0019\u0010\u008d\u0001\u001a\u00020\u0007*\u00030\u0084\u00018F¢\u0006\b\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006Ä\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", Argument.Delimiters.none, "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "dataFlowAnalyzerContext", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;", "isContextCollectorMode", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;Z)V", "getReturnTypeCalculator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "setReturnTypeCalculator", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;)V", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "getDataFlowAnalyzerContext", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;", "fileImportsScope", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "getFileImportsScope", "()Ljava/util/List;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "setFile", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "regularTowerDataContexts", "Lorg/jetbrains/kotlin/fir/resolve/FirRegularTowerDataContexts;", "getRegularTowerDataContexts$annotations", "()V", "getRegularTowerDataContexts", "()Lorg/jetbrains/kotlin/fir/resolve/FirRegularTowerDataContexts;", "setRegularTowerDataContexts", "(Lorg/jetbrains/kotlin/fir/resolve/FirRegularTowerDataContexts;)V", "specialTowerDataContexts", "Lorg/jetbrains/kotlin/fir/resolve/FirSpecialTowerDataContexts;", "getSpecialTowerDataContexts$annotations", "getSpecialTowerDataContexts", "()Lorg/jetbrains/kotlin/fir/resolve/FirSpecialTowerDataContexts;", "towerDataContext", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "getTowerDataContext$annotations", "getTowerDataContext", "()Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "value", "Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;", "towerDataMode", "getTowerDataMode$annotations", "getTowerDataMode", "()Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;", "setTowerDataMode", "(Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;)V", "implicitValueStorage", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "getImplicitValueStorage", "()Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "containers", "Lkotlin/collections/ArrayDeque;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getContainers", "()Lkotlin/collections/ArrayDeque;", "setContainers", "(Lkotlin/collections/ArrayDeque;)V", "topContainerForTypeResolution", "getTopContainerForTypeResolution", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "containingRegularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getContainingRegularClass", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "setContainingRegularClass", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "containerIfAny", "getContainerIfAny", "inferenceSession", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "getInferenceSession", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "setInferenceSession", "(Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;)V", "isInsideAssignmentRhs", "()Z", "setInsideAssignmentRhs", "(Z)V", "withAssignmentRhs", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isInsideAnnotationContext", "setInsideAnnotationContext", "withAnnotationContext", "withClassHeader", Argument.Delimiters.none, "clazz", "action", "anonymousFunctionsAnalyzedInDependentContext", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "getAnonymousFunctionsAnalyzedInDependentContext", "()Ljava/util/Set;", "containingClassDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getContainingClassDeclarations", "setContainingClassDeclarations", "targetedLocalClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "getTargetedLocalClasses", "setTargetedLocalClasses", "(Ljava/util/Set;)V", "outerLocalClassForNested", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getOuterLocalClassForNested", "()Ljava/util/Map;", "withTowerDataContexts", "T", "newContexts", "f", "(Lorg/jetbrains/kotlin/fir/resolve/FirRegularTowerDataContexts;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withTowerDataContext", "newContext", "(Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLambdaBeingAnalyzedInDependentContext", "lambda", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", "l", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "publicApiInlineFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getPublicApiInlineFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "setPublicApiInlineFunction", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "withPublicApiInlineFunction", "function", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withPublicApiInlineFunctionIfApplicable", "isPublicInline", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Z", "withContainer", "declaration", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withContainerRegularClass", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withContainingClass", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withTowerDataCleanup", "withConditionalTowerDataCleanup", "skipCleanup", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withTowerDataMode", "mode", "(Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withTowerDataModeCleanup", "replaceTowerDataContext", "clear", "addNonLocalTowerDataElement", "element", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataElement;", "addNonLocalTowerDataElements", "newElements", Argument.Delimiters.none, "addLocalScope", "localScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "addReceiver", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "implicitReceiverValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "addAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "updateLastScope", "transform", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "storeFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "withLabelAndReceiverType", "labelName", "owner", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "holder", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withTypeParametersOf", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "typeParameterScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirMemberTypeParameterScope;", "buildConstructorParametersScope", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "addInaccessibleImplicitReceiverValue", "owningClass", "storeBackingField", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getPrimaryConstructorPureParametersScope", "getPrimaryConstructorAllParametersScope", "storeClassOrTypealiasIfNotNested", "classOrTypeAlias", "storeVariable", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "withInferenceSession", "S", "(Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withAnonymousFunctionTowerDataContext", "symbol", "withCallableReferenceTowerDataContext", "access", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withTemporaryRegularContext", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/PostponedAtomsResolutionContext;", "(Lkotlin/Pair;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "dropContextForAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "forLocalClasses", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;Ljava/util/Set;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withReturnTypeCalculator", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withFile", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forRegularClassBody", "regularClass", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withSwitchedTowerDataModeForStaticNestedClass", "forTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withScopesForClass", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "(Lorg/jetbrains/kotlin/fir/declarations/FirScript;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withReplSnippet", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "(Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withCodeFragment", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "scopesWithPrimaryConstructorParameters", "withNamedFunction", "namedFunction", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/FirSession;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withParameters", "callable", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forFunctionBody", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forConstructorBody", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Lorg/jetbrains/kotlin/fir/FirSession;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withDanglingModifierList", "danglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withAnonymousFunction", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "storeContextForAnonymousFunction", "withField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "(Lorg/jetbrains/kotlin/fir/declarations/FirField;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "(Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withAnonymousInitializer", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;Lorg/jetbrains/kotlin/fir/FirSession;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Lorg/jetbrains/kotlin/fir/FirSession;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "storeValueParameterIfNeeded", "withReceiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withProperty", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withVariableAsContainerIfNeeded", "treatAsProperty", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withPropertyAccessor", "accessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "forContracts", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forPropertyInitializer", "forConstructor", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forConstructorParameters", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forDelegatedConstructorCallChildren", "forDelegatedConstructorCallResolution", "forConstructorParametersOrDelegatedConstructorCallChildren", "storeCallableReferenceContext", "callableReferenceAccess", "dropCallableReferenceContext", "withWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/FirSession;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forBlock", "(Lorg/jetbrains/kotlin/fir/FirSession;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BodyResolveContext {
    private final Set<FirFunctionSymbol<?>> anonymousFunctionsAnalyzedInDependentContext;
    private ArrayDeque<FirDeclaration> containers;
    private ArrayDeque<FirClass> containingClassDeclarations;
    private FirRegularClass containingRegularClass;
    private final DataFlowAnalyzerContext dataFlowAnalyzerContext;
    public FirFile file;
    private final List<FirScope> fileImportsScope;
    private FirInferenceSession inferenceSession;
    private final boolean isContextCollectorMode;
    private boolean isInsideAnnotationContext;
    private boolean isInsideAssignmentRhs;
    private final Map<FirClassLikeSymbol<?>, FirClassLikeSymbol<?>> outerLocalClassForNested;
    private FirFunction publicApiInlineFunction;
    private FirRegularTowerDataContexts regularTowerDataContexts;
    private ReturnTypeCalculator returnTypeCalculator;
    private final FirSpecialTowerDataContexts specialTowerDataContexts;
    private Set<? extends FirClassLikeDeclaration> targetedLocalClasses;

    public BodyResolveContext(ReturnTypeCalculator returnTypeCalculator, DataFlowAnalyzerContext dataFlowAnalyzerContext, boolean z) {
        returnTypeCalculator.getClass();
        dataFlowAnalyzerContext.getClass();
        this.returnTypeCalculator = returnTypeCalculator;
        this.dataFlowAnalyzerContext = dataFlowAnalyzerContext;
        this.isContextCollectorMode = z;
        this.fileImportsScope = new ArrayList();
        this.regularTowerDataContexts = new FirRegularTowerDataContexts(new FirTowerDataContext(), null, null, null, null, null, null, null, 254, null);
        this.specialTowerDataContexts = new FirSpecialTowerDataContexts();
        this.containers = new ArrayDeque<>();
        this.inferenceSession = FirInferenceSession.INSTANCE.getDEFAULT();
        this.anonymousFunctionsAnalyzedInDependentContext = new LinkedHashSet();
        this.containingClassDeclarations = new ArrayDeque<>();
        this.targetedLocalClasses = SetsKt.emptySet();
        this.outerLocalClassForNested = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object forRegularClassBody$lambda$0$0(BodyResolveContext bodyResolveContext, FirRegularClass firRegularClass, Function0 function0) {
        FirRegularClass firRegularClass2 = bodyResolveContext.containingRegularClass;
        bodyResolveContext.containers.add(firRegularClass);
        bodyResolveContext.containingRegularClass = firRegularClass;
        try {
            return function0.invoke();
        } finally {
            bodyResolveContext.containers.removeLast();
            bodyResolveContext.containingRegularClass = firRegularClass2;
        }
    }

    @PrivateForInline
    public static /* synthetic */ void getRegularTowerDataContexts$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getSpecialTowerDataContexts$annotations() {
    }

    public static /* synthetic */ void getTowerDataContext$annotations() {
    }

    public static /* synthetic */ void getTowerDataMode$annotations() {
    }

    private final Pair<FirLocalScope, FirLocalScope> scopesWithPrimaryConstructorParameters(FirConstructor firConstructor, FirSession firSession) {
        FirLocalScope firLocalScope = new FirLocalScope(firSession);
        FirLocalScope firLocalScope2 = new FirLocalScope(firSession);
        for (FirValueParameter firValueParameter : firConstructor.getValueParameters()) {
            firLocalScope2 = firLocalScope2.storeVariable(firValueParameter, firSession);
            if (ClassMembersKt.getCorrespondingProperty(firValueParameter) == null) {
                firLocalScope = firLocalScope.storeVariable(firValueParameter, firSession);
            }
        }
        return TuplesKt.to(firLocalScope, firLocalScope2);
    }

    @PrivateForInline
    private final void storeBackingField(FirProperty property, FirSession session) {
        FirLocalScope firLocalScope = (FirLocalScope) CollectionsKt.lastOrNull(getTowerDataContext().getLocalScopes());
        if (firLocalScope == null) {
            return;
        }
        replaceTowerDataContext(getTowerDataContext().setLastLocalScope(firLocalScope.storeBackingField(property, session)));
    }

    private final FirMemberTypeParameterScope typeParameterScope(FirMemberDeclaration firMemberDeclaration) {
        if (firMemberDeclaration.getTypeParameters().isEmpty()) {
            return null;
        }
        return new FirMemberTypeParameterScope(firMemberDeclaration);
    }

    public static /* synthetic */ Object withPropertyAccessor$default(BodyResolveContext bodyResolveContext, FirProperty firProperty, FirPropertyAccessor firPropertyAccessor, SessionAndScopeSessionHolder sessionAndScopeSessionHolder, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return bodyResolveContext.withPropertyAccessor(firProperty, firPropertyAccessor, sessionAndScopeSessionHolder, z, function0);
    }

    @PrivateForInline
    public final void addAnonymousInitializer(FirAnonymousInitializer anonymousInitializer) {
        anonymousInitializer.getClass();
        replaceTowerDataContext(getTowerDataContext().addAnonymousInitializer(anonymousInitializer));
    }

    @PrivateForInline
    public final void addInaccessibleImplicitReceiverValue(FirRegularClass owningClass, SessionAndScopeSessionHolder holder) {
        holder.getClass();
        if (owningClass == null || LanguageVersionUtilsKt.isEnabled(holder, LanguageFeature.ImprovedResolutionInSecondaryConstructors)) {
            return;
        }
        addReceiver(owningClass.getName(), new InaccessibleImplicitReceiverValue(owningClass.getSymbol(), ScopeUtilsKt.defaultType(owningClass), InaccessibleReceiverKind.SecondaryConstructor, holder.getSession(), holder.getScopeSession()));
    }

    @PrivateForInline
    public final void addLocalScope(FirLocalScope localScope) {
        localScope.getClass();
        replaceTowerDataContext(getTowerDataContext().addLocalScope(localScope));
    }

    @PrivateForInline
    public final void addNonLocalTowerDataElement(FirTowerDataElement element) {
        element.getClass();
        replaceTowerDataContext(getTowerDataContext().addNonLocalTowerDataElements(CollectionsKt.listOf(element)));
    }

    @PrivateForInline
    public final void addNonLocalTowerDataElements(List<FirTowerDataElement> newElements) {
        newElements.getClass();
        replaceTowerDataContext(getTowerDataContext().addNonLocalTowerDataElements(newElements));
    }

    @PrivateForInline
    public final void addReceiver(Name name, ImplicitReceiverValue<?> implicitReceiverValue) {
        implicitReceiverValue.getClass();
        replaceTowerDataContext(getTowerDataContext().addReceiver(name, implicitReceiverValue));
    }

    public final FirLocalScope buildConstructorParametersScope(FirConstructor constructor, FirSession session) {
        constructor.getClass();
        session.getClass();
        List<FirValueParameter> valueParameters = constructor.getValueParameters();
        FirLocalScope firLocalScope = new FirLocalScope(session);
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            firLocalScope = firLocalScope.storeVariable((FirValueParameter) it.next(), session);
        }
        return firLocalScope;
    }

    @PrivateForInline
    public final void clear() {
        this.specialTowerDataContexts.clear();
        this.dataFlowAnalyzerContext.reset();
    }

    public final void dropCallableReferenceContext(FirCallableReferenceAccess callableReferenceAccess) {
        callableReferenceAccess.getClass();
        this.specialTowerDataContexts.dropCallableReferenceContext(callableReferenceAccess);
    }

    public final void dropContextForAnonymousFunction(FirAnonymousFunction anonymousFunction) {
        anonymousFunction.getClass();
        this.specialTowerDataContexts.dropAnonymousFunctionContext(anonymousFunction.getSymbol());
    }

    public final <T> T forBlock(FirSession session, Function0<? extends T> f) {
        session.getClass();
        f.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            addLocalScope(new FirLocalScope(session));
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            replaceTowerDataContext(towerDataContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T forConstructor(FirConstructor constructor, Function0<? extends T> f) {
        constructor.getClass();
        f.getClass();
        FirTowerDataMode towerDataMode = FirTowerDataMode.CONSTRUCTOR_HEADER;
        FirTowerDataMode towerDataMode2 = getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = getTowerDataMode();
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode2);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        setTowerDataMode(towerDataMode);
        getContainers().add(constructor);
        try {
            T t = (T) f.invoke();
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode2);
            return t;
        } finally {
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T forConstructorBody(FirConstructor constructor, FirSession session, Function0<? extends T> f) {
        constructor.getClass();
        session.getClass();
        f.getClass();
        if (getTowerDataMode() != FirTowerDataMode.CONSTRUCTOR_HEADER) {
            w01.a("Failed requirement.");
            return null;
        }
        if (constructor.getIsPrimary()) {
            FirTowerDataContext towerDataContext = getTowerDataContext();
            try {
                addLocalScope(buildConstructorParametersScope(constructor, session));
                return (T) f.invoke();
            } finally {
                replaceTowerDataContext(towerDataContext);
            }
        }
        FirTowerDataMode towerDataMode = FirTowerDataMode.REGULAR;
        FirTowerDataMode towerDataMode2 = getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = getTowerDataMode();
            } catch (Throwable th) {
                setTowerDataMode(towerDataMode2);
                throw th;
            }
        }
        setTowerDataMode(towerDataMode);
        FirTowerDataContext towerDataContext2 = getTowerDataContext();
        try {
            addLocalScope(buildConstructorParametersScope(constructor, session));
            T t = (T) f.invoke();
            replaceTowerDataContext(towerDataContext2);
            setTowerDataMode(towerDataMode2);
            return t;
        } catch (Throwable th2) {
            replaceTowerDataContext(towerDataContext2);
            throw th2;
        }
    }

    public final <T> T forConstructorParameters(FirConstructor constructor, FirRegularClass owningClass, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        constructor.getClass();
        holder.getClass();
        f.getClass();
        if (getTowerDataMode() != FirTowerDataMode.CONSTRUCTOR_HEADER) {
            w01.a("Failed requirement.");
            return null;
        }
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            if (!constructor.getIsPrimary()) {
                addInaccessibleImplicitReceiverValue(owningClass, holder);
            }
            addLocalScope(buildConstructorParametersScope(constructor, holder.getSession()));
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            replaceTowerDataContext(towerDataContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T forConstructorParametersOrDelegatedConstructorCallChildren(FirConstructor constructor, FirRegularClass owningClass, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        constructor.getClass();
        holder.getClass();
        f.getClass();
        if (getTowerDataMode() != FirTowerDataMode.CONSTRUCTOR_HEADER) {
            w01.a("Failed requirement.");
            return null;
        }
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            if (!constructor.getIsPrimary()) {
                addInaccessibleImplicitReceiverValue(owningClass, holder);
            }
            addLocalScope(buildConstructorParametersScope(constructor, holder.getSession()));
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            replaceTowerDataContext(towerDataContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T forDelegatedConstructorCallChildren(FirConstructor constructor, FirRegularClass owningClass, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        constructor.getClass();
        holder.getClass();
        f.getClass();
        if (getTowerDataMode() != FirTowerDataMode.CONSTRUCTOR_HEADER) {
            w01.a("Failed requirement.");
            return null;
        }
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            if (!constructor.getIsPrimary()) {
                addInaccessibleImplicitReceiverValue(owningClass, holder);
            }
            addLocalScope(buildConstructorParametersScope(constructor, holder.getSession()));
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            replaceTowerDataContext(towerDataContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T forDelegatedConstructorCallResolution(Function0<? extends T> f) {
        f.getClass();
        if (getTowerDataMode() != FirTowerDataMode.CONSTRUCTOR_HEADER) {
            w01.a("Failed requirement.");
            return null;
        }
        FirTowerDataMode towerDataMode = FirTowerDataMode.REGULAR;
        FirTowerDataMode towerDataMode2 = getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = getTowerDataMode();
            } finally {
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode2);
                InlineMarker.finallyEnd(1);
            }
        }
        setTowerDataMode(towerDataMode);
        return (T) f.invoke();
    }

    public final <T> T forFunctionBody(FirFunction function, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        T t;
        function.getClass();
        holder.getClass();
        f.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            if (function instanceof FirNamedFunction) {
                t = (T) withParameters(function, holder, f);
            } else {
                addLocalScope(new FirLocalScope(holder.getSession()));
                t = (T) f.invoke();
            }
            return t;
        } finally {
            replaceTowerDataContext(towerDataContext);
        }
    }

    public final <T> T forLocalClasses(ReturnTypeCalculator returnTypeCalculator, Set<? extends FirClassLikeDeclaration> targetedLocalClasses, Function0<? extends T> f) {
        returnTypeCalculator.getClass();
        targetedLocalClasses.getClass();
        f.getClass();
        ReturnTypeCalculator returnTypeCalculator2 = getReturnTypeCalculator();
        Set<FirClassLikeDeclaration> targetedLocalClasses2 = getTargetedLocalClasses();
        try {
            setReturnTypeCalculator(returnTypeCalculator);
            setTargetedLocalClasses(targetedLocalClasses);
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setReturnTypeCalculator(returnTypeCalculator2);
            setTargetedLocalClasses(targetedLocalClasses2);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T forPropertyInitializer(boolean skipCleanup, Function0<? extends T> f) {
        f.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            FirLocalScope primaryConstructorPureParametersScope = getPrimaryConstructorPureParametersScope();
            if (primaryConstructorPureParametersScope != null) {
                addLocalScope(primaryConstructorPureParametersScope);
            }
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (!skipCleanup) {
                replaceTowerDataContext(towerDataContext);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T forRegularClassBody(final FirRegularClass regularClass, SessionAndScopeSessionHolder holder, final Function0<? extends T> f) {
        regularClass.getClass();
        holder.getClass();
        f.getClass();
        storeClassOrTypealiasIfNotNested(regularClass, holder.getSession());
        FirTowerDataMode towerDataMode = getTowerDataMode();
        try {
            if (!regularClass.getStatus().isInner() && (getContainerIfAny() instanceof FirRegularClass)) {
                setTowerDataMode(regularClass.getStatus().isCompanion() ? FirTowerDataMode.COMPANION_OBJECT : FirTowerDataMode.NESTED_CLASS);
            }
            return (T) withScopesForClass(regularClass, holder, new Function0() { // from class: ay0
                public final Object invoke() {
                    return BodyResolveContext.forRegularClassBody$lambda$0$0(this.b, regularClass, f);
                }
            });
        } finally {
            setTowerDataMode(towerDataMode);
        }
    }

    public final <T> T forTypeAlias(FirTypeAlias typeAlias, Function0<? extends T> f) {
        FirTowerDataMode towerDataMode;
        typeAlias.getClass();
        f.getClass();
        boolean z = getContainerIfAny() instanceof FirRegularClass;
        getContainers().add(typeAlias);
        try {
            if (!z) {
                T t = (T) f.invoke();
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
                return t;
            }
            towerDataMode = getTowerDataMode();
            if (!typeAlias.getStatus().isInner()) {
                setTowerDataMode(FirTowerDataMode.NESTED_CLASS);
            }
            T t2 = (T) f.invoke();
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode);
            InlineMarker.finallyEnd(1);
            InlineMarker.finallyStart(2);
            getContainers().removeLast();
            InlineMarker.finallyEnd(2);
            return t2;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode);
            throw th;
        } finally {
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final Set<FirFunctionSymbol<?>> getAnonymousFunctionsAnalyzedInDependentContext() {
        return this.anonymousFunctionsAnalyzedInDependentContext;
    }

    public final FirDeclaration getContainerIfAny() {
        return (FirDeclaration) this.containers.lastOrNull();
    }

    public final ArrayDeque<FirDeclaration> getContainers() {
        return this.containers;
    }

    public final ArrayDeque<FirClass> getContainingClassDeclarations() {
        return this.containingClassDeclarations;
    }

    public final FirRegularClass getContainingRegularClass() {
        return this.containingRegularClass;
    }

    public final DataFlowAnalyzerContext getDataFlowAnalyzerContext() {
        return this.dataFlowAnalyzerContext;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirFile getFile() throws UninitializedPropertyAccessException {
        FirFile firFile = this.file;
        if (firFile != null) {
            return firFile;
        }
        Intrinsics.throwUninitializedPropertyAccessException("file");
        return null;
    }

    public final List<FirScope> getFileImportsScope() {
        return this.fileImportsScope;
    }

    public final ImplicitValueStorage getImplicitValueStorage() {
        return getTowerDataContext().getImplicitValueStorage();
    }

    public final FirInferenceSession getInferenceSession() {
        return this.inferenceSession;
    }

    public final Map<FirClassLikeSymbol<?>, FirClassLikeSymbol<?>> getOuterLocalClassForNested() {
        return this.outerLocalClassForNested;
    }

    public final FirLocalScope getPrimaryConstructorAllParametersScope() {
        return this.regularTowerDataContexts.getPrimaryConstructorAllParametersScope();
    }

    public final FirLocalScope getPrimaryConstructorPureParametersScope() {
        return this.regularTowerDataContexts.getPrimaryConstructorPureParametersScope();
    }

    public final FirFunction getPublicApiInlineFunction() {
        return this.publicApiInlineFunction;
    }

    public final FirRegularTowerDataContexts getRegularTowerDataContexts() {
        return this.regularTowerDataContexts;
    }

    public final ReturnTypeCalculator getReturnTypeCalculator() {
        return this.returnTypeCalculator;
    }

    public final FirSpecialTowerDataContexts getSpecialTowerDataContexts() {
        return this.specialTowerDataContexts;
    }

    public final Set<FirClassLikeDeclaration> getTargetedLocalClasses() {
        return this.targetedLocalClasses;
    }

    public final FirDeclaration getTopContainerForTypeResolution() {
        Object objPrevious;
        ArrayDeque<FirDeclaration> arrayDeque = this.containers;
        ListIterator listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (listIterator.hasPrevious()) {
            objPrevious = listIterator.previous();
            FirDeclaration firDeclaration = (FirDeclaration) objPrevious;
            if ((firDeclaration instanceof FirTypeParameterRefsOwner) && !(firDeclaration instanceof FirAnonymousFunction)) {
                return (FirDeclaration) objPrevious;
            }
        }
        objPrevious = null;
        return (FirDeclaration) objPrevious;
    }

    public final FirTowerDataContext getTowerDataContext() {
        FirTowerDataContext currentContext = this.regularTowerDataContexts.getCurrentContext();
        if (currentContext != null) {
            return currentContext;
        }
        pe1.a("No regular data context found, towerDataMode = ", getTowerDataMode());
        return null;
    }

    public final FirTowerDataMode getTowerDataMode() {
        return this.regularTowerDataContexts.getActiveMode();
    }

    /* JADX INFO: renamed from: isInsideAnnotationContext, reason: from getter */
    public final boolean getIsInsideAnnotationContext() {
        return this.isInsideAnnotationContext;
    }

    /* JADX INFO: renamed from: isInsideAssignmentRhs, reason: from getter */
    public final boolean getIsInsideAssignmentRhs() {
        return this.isInsideAssignmentRhs;
    }

    public final boolean isPublicInline(FirFunction firFunction) {
        firFunction.getClass();
        if (!firFunction.getStatus().isInline()) {
            return false;
        }
        EffectiveVisibility publishedApiEffectiveVisibility = PublishedApiEffectiveVisibilityKt.getPublishedApiEffectiveVisibility(firFunction);
        if (publishedApiEffectiveVisibility == null) {
            FirDeclarationStatus status = firFunction.getStatus();
            FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
            if (firResolvedDeclarationStatus == null || (publishedApiEffectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
                publishedApiEffectiveVisibility = EffectiveVisibility.Local.INSTANCE;
            }
        }
        return publishedApiEffectiveVisibility.getPublicApi();
    }

    @PrivateForInline
    public final void replaceTowerDataContext(FirTowerDataContext newContext) {
        newContext.getClass();
        this.regularTowerDataContexts = this.regularTowerDataContexts.replaceCurrentlyActiveContext(newContext);
    }

    @PrivateForInline
    public final void setContainers(ArrayDeque<FirDeclaration> arrayDeque) {
        arrayDeque.getClass();
        this.containers = arrayDeque;
    }

    public final void setContainingClassDeclarations(ArrayDeque<FirClass> arrayDeque) {
        arrayDeque.getClass();
        this.containingClassDeclarations = arrayDeque;
    }

    @PrivateForInline
    public final void setContainingRegularClass(FirRegularClass firRegularClass) {
        this.containingRegularClass = firRegularClass;
    }

    @PrivateForInline
    public final void setFile(FirFile firFile) {
        firFile.getClass();
        this.file = firFile;
    }

    @PrivateForInline
    public final void setInferenceSession(FirInferenceSession firInferenceSession) {
        firInferenceSession.getClass();
        this.inferenceSession = firInferenceSession;
    }

    @PrivateForInline
    public final void setInsideAnnotationContext(boolean z) {
        this.isInsideAnnotationContext = z;
    }

    @PrivateForInline
    public final void setInsideAssignmentRhs(boolean z) {
        this.isInsideAssignmentRhs = z;
    }

    public final void setPublicApiInlineFunction(FirFunction firFunction) {
        this.publicApiInlineFunction = firFunction;
    }

    public final void setRegularTowerDataContexts(FirRegularTowerDataContexts firRegularTowerDataContexts) {
        firRegularTowerDataContexts.getClass();
        this.regularTowerDataContexts = firRegularTowerDataContexts;
    }

    @PrivateForInline
    public final void setReturnTypeCalculator(ReturnTypeCalculator returnTypeCalculator) {
        returnTypeCalculator.getClass();
        this.returnTypeCalculator = returnTypeCalculator;
    }

    @PrivateForInline
    public final void setTargetedLocalClasses(Set<? extends FirClassLikeDeclaration> set) {
        set.getClass();
        this.targetedLocalClasses = set;
    }

    public final void setTowerDataMode(FirTowerDataMode firTowerDataMode) {
        firTowerDataMode.getClass();
        this.regularTowerDataContexts = this.regularTowerDataContexts.replaceTowerDataMode(firTowerDataMode);
    }

    public final void storeCallableReferenceContext(FirCallableReferenceAccess callableReferenceAccess) {
        callableReferenceAccess.getClass();
        this.specialTowerDataContexts.storeCallableReferenceContext(callableReferenceAccess, getTowerDataContext().createSnapshot(false), this.inferenceSession);
    }

    public final void storeClassOrTypealiasIfNotNested(FirClassLikeDeclaration classOrTypeAlias, FirSession session) {
        FirLocalScope firLocalScope;
        classOrTypeAlias.getClass();
        session.getClass();
        if ((getContainerIfAny() instanceof FirClass) || Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(classOrTypeAlias), Boolean.TRUE) || (firLocalScope = (FirLocalScope) CollectionsKt.lastOrNull(getTowerDataContext().getLocalScopes())) == null) {
            return;
        }
        replaceTowerDataContext(getTowerDataContext().setLastLocalScope(firLocalScope.storeClassOrTypeAlias(classOrTypeAlias, session)));
    }

    public final void storeContextForAnonymousFunction(FirAnonymousFunction anonymousFunction) {
        anonymousFunction.getClass();
        this.specialTowerDataContexts.storeAnonymousFunctionContext(anonymousFunction.getSymbol(), getTowerDataContext(), this.inferenceSession);
    }

    @PrivateForInline
    public final void storeFunction(FirNamedFunction function, FirSession session) {
        FirLocalScope firLocalScope;
        function.getClass();
        session.getClass();
        if (Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(function), Boolean.TRUE) || (firLocalScope = (FirLocalScope) CollectionsKt.lastOrNull(getTowerDataContext().getLocalScopes())) == null) {
            return;
        }
        replaceTowerDataContext(getTowerDataContext().setLastLocalScope(firLocalScope.storeFunction(function, session)));
    }

    public final void storeValueParameterIfNeeded(FirValueParameter valueParameter, FirSession session) {
        valueParameter.getClass();
        session.getClass();
        if (FirValueParameterKindKt.isLegacyContextReceiver(valueParameter) || Intrinsics.areEqual(valueParameter.getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR)) {
            return;
        }
        storeVariable(valueParameter, session);
    }

    public final void storeVariable(FirVariable variable, FirSession session) {
        variable.getClass();
        session.getClass();
        if (Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(variable), Boolean.TRUE)) {
            return;
        }
        replaceTowerDataContext(getTowerDataContext().addLocalVariable(variable, session));
    }

    public final <R> R withAnnotationContext(Function0<? extends R> block) {
        block.getClass();
        boolean isInsideAnnotationContext = getIsInsideAnnotationContext();
        setInsideAnnotationContext(true);
        try {
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setInsideAnnotationContext(isInsideAnnotationContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withAnonymousFunction(FirAnonymousFunction anonymousFunction, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        ConeKotlinType coneType;
        String name;
        anonymousFunction.getClass();
        holder.getClass();
        f.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            addLocalScope(new FirLocalScope(holder.getSession()));
            FirReceiverParameter receiverParameter = anonymousFunction.getReceiverParameter();
            FirTypeRef typeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
            FirLabel label = anonymousFunction.getLabel();
            Name nameIdentifier = (label == null || (name = label.getName()) == null) ? null : Name.identifier(name);
            getContainers().add(anonymousFunction);
            if (typeRef != null) {
                try {
                    coneType = FirTypeUtilsKt.getConeType(typeRef);
                } catch (Throwable th) {
                    getContainers().removeLast();
                    throw th;
                }
            } else {
                coneType = null;
            }
            FirTowerDataContext towerDataContext2 = getTowerDataContext();
            try {
                List<FirValueParameter> contextParameters = anonymousFunction.getContextParameters();
                ArrayList arrayList = new ArrayList();
                for (FirValueParameter firValueParameter : contextParameters) {
                    ImplicitContextParameterValue implicitContextParameterValue = !FirValueParameterKindKt.isLegacyContextReceiver(firValueParameter) ? new ImplicitContextParameterValue(firValueParameter.getSymbol(), FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef())) : null;
                    if (implicitContextParameterValue != null) {
                        arrayList.add(implicitContextParameterValue);
                    }
                }
                replaceTowerDataContext(getTowerDataContext().addContextGroups(arrayList));
                if (coneType != null) {
                    if (FirStatusUtilsKt.isCompanionExtension(anonymousFunction)) {
                        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(holder, TypeExpansionUtilsKt.fullyExpandedType(holder, coneType));
                        FirContainingNamesAwareScope firContainingNamesAwareScopeStaticScope = regularClassSymbol != null ? ImplicitReceiverUtilsKt.staticScope(regularClassSymbol, holder) : null;
                        if (regularClassSymbol != null) {
                            addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElementForStaticScope(regularClassSymbol, firContainingNamesAwareScopeStaticScope));
                        }
                    } else {
                        FirReceiverParameter receiverParameter2 = anonymousFunction.getReceiverParameter();
                        receiverParameter2.getClass();
                        addReceiver(nameIdentifier, new ImplicitExtensionReceiverValue(receiverParameter2.getSymbol(), coneType, holder.getSession(), holder.getScopeSession()));
                    }
                }
                T t = (T) f.invoke();
                replaceTowerDataContext(towerDataContext2);
                getContainers().removeLast();
                return t;
            } finally {
                replaceTowerDataContext(towerDataContext2);
            }
        } catch (Throwable th2) {
            replaceTowerDataContext(towerDataContext);
            throw th2;
        }
    }

    public final <T> T withAnonymousFunctionTowerDataContext(FirAnonymousFunctionSymbol symbol, Function0<? extends T> f) {
        T t;
        symbol.getClass();
        f.getClass();
        Pair<FirTowerDataContext, FirInferenceSession> anonymousFunctionContext = getSpecialTowerDataContexts().getAnonymousFunctionContext(symbol);
        if (anonymousFunctionContext == null) {
            return (T) f.invoke();
        }
        FirTowerDataContext firTowerDataContext = (FirTowerDataContext) anonymousFunctionContext.component1();
        FirInferenceSession firInferenceSession = (FirInferenceSession) anonymousFunctionContext.component2();
        FirTowerDataMode towerDataMode = getTowerDataMode();
        try {
            FirRegularTowerDataContexts firRegularTowerDataContextsReplaceAndSetActiveRegularContext = getRegularTowerDataContexts().replaceAndSetActiveRegularContext(firTowerDataContext);
            FirRegularTowerDataContexts regularTowerDataContexts = getRegularTowerDataContexts();
            setRegularTowerDataContexts(firRegularTowerDataContextsReplaceAndSetActiveRegularContext);
            try {
                if (firInferenceSession != getInferenceSession()) {
                    FirInferenceSession inferenceSession = getInferenceSession();
                    setInferenceSession(firInferenceSession);
                    try {
                        t = (T) f.invoke();
                        InlineMarker.finallyStart(1);
                        setInferenceSession(inferenceSession);
                        InlineMarker.finallyEnd(1);
                    } finally {
                        InlineMarker.finallyStart(1);
                        setInferenceSession(inferenceSession);
                        InlineMarker.finallyEnd(1);
                    }
                } else {
                    t = (T) f.invoke();
                }
                InlineMarker.finallyStart(1);
                setRegularTowerDataContexts(regularTowerDataContexts);
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode);
                return t;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setRegularTowerDataContexts(regularTowerDataContexts);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public final <T> T withAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, FirSession session, Function0<? extends T> f) {
        FirScriptResolutionHacksComponent scriptResolutionHacksComponent;
        anonymousInitializer.getClass();
        session.getClass();
        f.getClass();
        boolean z = Intrinsics.areEqual(DeclarationAttributesKt.isScriptTopLevelDeclaration(anonymousInitializer), Boolean.TRUE) && (scriptResolutionHacksComponent = FirScriptResolutionHacksComponentKt.getScriptResolutionHacksComponent(session)) != null && scriptResolutionHacksComponent.getSkipTowerDataCleanupForTopLevelInitializers();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            FirLocalScope primaryConstructorPureParametersScope = getPrimaryConstructorPureParametersScope();
            if (primaryConstructorPureParametersScope != null) {
                addLocalScope(primaryConstructorPureParametersScope);
            }
            addLocalScope(new FirLocalScope(session));
            addAnonymousInitializer(anonymousInitializer);
            getContainers().add(anonymousInitializer);
            try {
                T t = (T) f.invoke();
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                if (!z) {
                    replaceTowerDataContext(towerDataContext);
                }
                return t;
            } finally {
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (!z) {
                replaceTowerDataContext(towerDataContext);
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public final <T> T withAnonymousObject(final FirAnonymousObject anonymousObject, SessionAndScopeSessionHolder holder, final Function0<? extends T> f) {
        anonymousObject.getClass();
        holder.getClass();
        f.getClass();
        return (T) withScopesForClass(anonymousObject, holder, new Function0<T>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext.withAnonymousObject.1
            public final T invoke() {
                BodyResolveContext bodyResolveContext = BodyResolveContext.this;
                FirAnonymousObject firAnonymousObject = anonymousObject;
                Function0<T> function0 = f;
                bodyResolveContext.getContainers().add(firAnonymousObject);
                try {
                    return (T) function0.invoke();
                } finally {
                    bodyResolveContext.getContainers().removeLast();
                }
            }
        });
    }

    public final <R> R withAssignmentRhs(Function0<? extends R> block) {
        block.getClass();
        boolean isInsideAssignmentRhs = getIsInsideAssignmentRhs();
        setInsideAssignmentRhs(true);
        try {
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setInsideAssignmentRhs(isInsideAssignmentRhs);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withCallableReferenceTowerDataContext(FirCallableReferenceAccess access, Function0<? extends T> f) {
        T t;
        access.getClass();
        f.getClass();
        Pair<FirTowerDataContext, FirInferenceSession> callableReferenceContext = getSpecialTowerDataContexts().getCallableReferenceContext(access);
        if (callableReferenceContext == null) {
            return (T) f.invoke();
        }
        FirTowerDataContext firTowerDataContext = (FirTowerDataContext) callableReferenceContext.component1();
        FirInferenceSession firInferenceSession = (FirInferenceSession) callableReferenceContext.component2();
        FirTowerDataMode towerDataMode = getTowerDataMode();
        try {
            FirRegularTowerDataContexts firRegularTowerDataContextsReplaceAndSetActiveRegularContext = getRegularTowerDataContexts().replaceAndSetActiveRegularContext(firTowerDataContext);
            FirRegularTowerDataContexts regularTowerDataContexts = getRegularTowerDataContexts();
            setRegularTowerDataContexts(firRegularTowerDataContextsReplaceAndSetActiveRegularContext);
            try {
                if (firInferenceSession != getInferenceSession()) {
                    FirInferenceSession inferenceSession = getInferenceSession();
                    setInferenceSession(firInferenceSession);
                    try {
                        t = (T) f.invoke();
                        InlineMarker.finallyStart(1);
                        setInferenceSession(inferenceSession);
                        InlineMarker.finallyEnd(1);
                    } finally {
                        InlineMarker.finallyStart(1);
                        setInferenceSession(inferenceSession);
                        InlineMarker.finallyEnd(1);
                    }
                } else {
                    t = (T) f.invoke();
                }
                InlineMarker.finallyStart(1);
                setRegularTowerDataContexts(regularTowerDataContexts);
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode);
                return t;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setRegularTowerDataContexts(regularTowerDataContexts);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public final void withClassHeader(FirRegularClass clazz, Function0<Unit> action) {
        clazz.getClass();
        action.getClass();
        FirTowerDataMode towerDataMode = getTowerDataMode();
        try {
            if (!clazz.getStatus().isInner() && (getContainerIfAny() instanceof FirRegularClass)) {
                setTowerDataMode(clazz.getStatus().isCompanion() ? FirTowerDataMode.COMPANION_OBJECT : FirTowerDataMode.NESTED_CLASS);
            }
            getContainers().add(clazz);
            try {
                action.invoke();
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode);
            } finally {
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public final <T> T withCodeFragment(FirCodeFragment codeFragment, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        codeFragment.getClass();
        holder.getClass();
        f.getClass();
        FirCodeFragmentContext codeFragmentContext = DeclarationUtilsKt.getCodeFragmentContext(codeFragment);
        if (codeFragmentContext == null) {
            k2d.a("Context is not set for a code fragment");
            return null;
        }
        FirTowerDataContext towerDataContext = codeFragmentContext.getTowerDataContext();
        List listComputeImportingScopes$default = ImportingScopesKt.computeImportingScopes$default(getFile(), holder.getSession(), holder.getScopeSession(), false, false, 24, null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listComputeImportingScopes$default, 10));
        Iterator<T> it = listComputeImportingScopes$default.iterator();
        while (it.hasNext()) {
            arrayList.add(ImplicitReceiverUtilsKt.asTowerDataElement((FirScope) it.next(), false));
        }
        FirTowerDataContext firTowerDataContextAddNonLocalTowerDataElements = towerDataContext.addNonLocalTowerDataElements(towerDataContext.getNonLocalTowerDataElements()).addNonLocalTowerDataElements(arrayList);
        Iterator<T> it2 = towerDataContext.getLocalScopes().iterator();
        FirTowerDataContext firTowerDataContextAddLocalScope = firTowerDataContextAddNonLocalTowerDataElements;
        while (it2.hasNext()) {
            firTowerDataContextAddLocalScope = firTowerDataContextAddLocalScope.addLocalScope((FirLocalScope) it2.next());
        }
        FirTowerDataContext firTowerDataContext = firTowerDataContextAddLocalScope;
        FirTowerDataContext firTowerDataContext2 = firTowerDataContextAddLocalScope;
        FirRegularTowerDataContexts firRegularTowerDataContexts = new FirRegularTowerDataContexts(firTowerDataContextAddLocalScope, firTowerDataContext, firTowerDataContext2, null, null, null, null, null, 8, null);
        FirRegularTowerDataContexts regularTowerDataContexts = getRegularTowerDataContexts();
        setRegularTowerDataContexts(firRegularTowerDataContexts);
        try {
            getContainers().add(codeFragment);
            try {
                T t = (T) f.invoke();
                getContainers().removeLast();
                setRegularTowerDataContexts(regularTowerDataContexts);
                return t;
            } catch (Throwable th) {
                getContainers().removeLast();
                throw th;
            }
        } catch (Throwable th2) {
            setRegularTowerDataContexts(regularTowerDataContexts);
            throw th2;
        }
    }

    @PrivateForInline
    public final <R> R withConditionalTowerDataCleanup(boolean skipCleanup, Function0<? extends R> l) {
        l.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            return (R) l.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (!skipCleanup) {
                replaceTowerDataContext(towerDataContext);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    @PrivateForInline
    public final <T> T withContainer(FirDeclaration declaration, Function0<? extends T> f) {
        declaration.getClass();
        f.getClass();
        getContainers().add(declaration);
        try {
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withContainingClass(FirClass declaration, Function0<? extends T> f) {
        declaration.getClass();
        f.getClass();
        getContainingClassDeclarations().add(declaration);
        try {
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContainingClassDeclarations().removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withDanglingModifierList(FirDanglingModifierList danglingModifierList, Function0<? extends T> f) {
        danglingModifierList.getClass();
        f.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            getContainers().add(danglingModifierList);
            try {
                T t = (T) f.invoke();
                getContainers().removeLast();
                replaceTowerDataContext(towerDataContext);
                return t;
            } catch (Throwable th) {
                getContainers().removeLast();
                throw th;
            }
        } catch (Throwable th2) {
            replaceTowerDataContext(towerDataContext);
            throw th2;
        }
    }

    public final <T> T withEnumEntry(FirEnumEntry enumEntry, Function0<? extends T> f) {
        enumEntry.getClass();
        f.getClass();
        FirTowerDataMode towerDataMode = FirTowerDataMode.ENUM_ENTRY;
        FirTowerDataMode towerDataMode2 = getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = getTowerDataMode();
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode2);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        setTowerDataMode(towerDataMode);
        getContainers().add(enumEntry);
        try {
            T t = (T) f.invoke();
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode2);
            return t;
        } finally {
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withField(FirField field, Function0<? extends T> f) {
        field.getClass();
        f.getClass();
        FirTowerDataMode towerDataMode = FirTowerDataMode.CONSTRUCTOR_HEADER;
        FirTowerDataMode towerDataMode2 = getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = getTowerDataMode();
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode2);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        setTowerDataMode(towerDataMode);
        getContainers().add(field);
        try {
            FirTowerDataContext towerDataContext = getTowerDataContext();
            try {
                FirLocalScope primaryConstructorAllParametersScope = getPrimaryConstructorAllParametersScope();
                if (primaryConstructorAllParametersScope != null) {
                    addLocalScope(primaryConstructorAllParametersScope);
                }
                T t = (T) f.invoke();
                InlineMarker.finallyStart(1);
                replaceTowerDataContext(towerDataContext);
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode2);
                return t;
            } finally {
                InlineMarker.finallyStart(1);
                replaceTowerDataContext(towerDataContext);
                InlineMarker.finallyEnd(1);
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public final <T> T withFile(FirFile file, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        file.getClass();
        holder.getClass();
        f.getClass();
        clear();
        setFile(file);
        List<FirScope> fileImportsScope = getFileImportsScope();
        int size = fileImportsScope.size();
        int i = 0;
        try {
            FirTowerDataContext towerDataContext = getTowerDataContext();
            try {
                List listCreateImportingScopes$default = ImportingScopesKt.createImportingScopes$default(file, holder.getSession(), holder.getScopeSession(), false, 8, null);
                CollectionsKt.addAll(getFileImportsScope(), listCreateImportingScopes$default);
                List list = listCreateImportingScopes$default;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(ImplicitReceiverUtilsKt.asTowerDataElement((FirScope) it.next(), false));
                }
                addNonLocalTowerDataElements(arrayList);
                getContainers().add(file);
                try {
                    T t = (T) f.invoke();
                    InlineMarker.finallyStart(1);
                    getContainers().removeLast();
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    replaceTowerDataContext(towerDataContext);
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    int size2 = fileImportsScope.size() - size;
                    while (i < size2) {
                        fileImportsScope.remove(fileImportsScope.size() - 1);
                        i++;
                    }
                    return t;
                } finally {
                    InlineMarker.finallyStart(1);
                    getContainers().removeLast();
                    InlineMarker.finallyEnd(1);
                }
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                replaceTowerDataContext(towerDataContext);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            int size3 = fileImportsScope.size() - size;
            while (i < size3) {
                fileImportsScope.remove(fileImportsScope.size() - 1);
                i++;
            }
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public final <R, S extends FirInferenceSession> R withInferenceSession(S inferenceSession, Function1<? super S, ? extends R> block) {
        inferenceSession.getClass();
        block.getClass();
        FirInferenceSession inferenceSession2 = getInferenceSession();
        setInferenceSession(inferenceSession);
        try {
            return (R) block.invoke(inferenceSession);
        } finally {
            InlineMarker.finallyStart(1);
            setInferenceSession(inferenceSession2);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withLambdaBeingAnalyzedInDependentContext(FirAnonymousFunctionSymbol lambda, Function0<? extends R> l) {
        lambda.getClass();
        l.getClass();
        getAnonymousFunctionsAnalyzedInDependentContext().add(lambda);
        try {
            return (R) l.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getAnonymousFunctionsAnalyzedInDependentContext().remove(lambda);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withNamedFunction(FirNamedFunction namedFunction, FirSession session, Function0<? extends T> f) {
        FirFunction publicApiInlineFunction;
        T t;
        namedFunction.getClass();
        session.getClass();
        f.getClass();
        if (!(getContainerIfAny() instanceof FirClass)) {
            storeFunction(namedFunction, session);
        }
        FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(namedFunction) ? FirTowerDataMode.COMPANION_BLOCK : null;
        FirTowerDataMode towerDataMode2 = getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = getTowerDataMode();
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode2);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        setTowerDataMode(towerDataMode);
        if (namedFunction.getTypeParameters().isEmpty()) {
            publicApiInlineFunction = isPublicInline(namedFunction) ? namedFunction : null;
            if (publicApiInlineFunction == null) {
                publicApiInlineFunction = getPublicApiInlineFunction();
            }
            FirFunction publicApiInlineFunction2 = getPublicApiInlineFunction();
            try {
                setPublicApiInlineFunction(publicApiInlineFunction);
                getContainers().add(namedFunction);
                try {
                    t = (T) f.invoke();
                    InlineMarker.finallyStart(1);
                    getContainers().removeLast();
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    setPublicApiInlineFunction(publicApiInlineFunction2);
                    InlineMarker.finallyEnd(1);
                } finally {
                    InlineMarker.finallyStart(1);
                    getContainers().removeLast();
                    InlineMarker.finallyEnd(1);
                }
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                setPublicApiInlineFunction(publicApiInlineFunction2);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        } else {
            FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(namedFunction);
            FirTowerDataContext towerDataContext = getTowerDataContext();
            try {
                addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                publicApiInlineFunction = isPublicInline(namedFunction) ? namedFunction : null;
                if (publicApiInlineFunction == null) {
                    publicApiInlineFunction = getPublicApiInlineFunction();
                }
                FirFunction publicApiInlineFunction3 = getPublicApiInlineFunction();
                try {
                    setPublicApiInlineFunction(publicApiInlineFunction);
                    getContainers().add(namedFunction);
                    try {
                        t = (T) f.invoke();
                        InlineMarker.finallyStart(1);
                        getContainers().removeLast();
                        InlineMarker.finallyEnd(1);
                        InlineMarker.finallyStart(1);
                        setPublicApiInlineFunction(publicApiInlineFunction3);
                        InlineMarker.finallyEnd(1);
                        InlineMarker.finallyStart(1);
                        replaceTowerDataContext(towerDataContext);
                        InlineMarker.finallyEnd(1);
                    } finally {
                        InlineMarker.finallyStart(1);
                        getContainers().removeLast();
                        InlineMarker.finallyEnd(1);
                    }
                } catch (Throwable th3) {
                    InlineMarker.finallyStart(1);
                    setPublicApiInlineFunction(publicApiInlineFunction3);
                    InlineMarker.finallyEnd(1);
                    throw th3;
                }
            } catch (Throwable th4) {
                InlineMarker.finallyStart(1);
                replaceTowerDataContext(towerDataContext);
                InlineMarker.finallyEnd(1);
                throw th4;
            }
        }
        InlineMarker.finallyStart(1);
        setTowerDataMode(towerDataMode2);
        return t;
    }

    public final <T> T withParameters(FirCallableDeclaration callable, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        callable.getClass();
        holder.getClass();
        f.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            addLocalScope(new FirLocalScope(holder.getSession()));
            Iterator<FirValueParameter> it = callable.getContextParameters().iterator();
            while (it.hasNext()) {
                storeValueParameterIfNeeded(it.next(), holder.getSession());
            }
            if (callable instanceof FirFunction) {
                Iterator<FirValueParameter> it2 = ((FirFunction) callable).getValueParameters().iterator();
                while (it2.hasNext()) {
                    storeVariable(it2.next(), holder.getSession());
                }
            }
            FirReceiverParameter receiverParameter = callable.getReceiverParameter();
            FirTypeRef typeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
            ConeKotlinType coneType = typeRef != null ? FirTypeUtilsKt.getConeType(typeRef) : null;
            Name name = callable.getSymbol().getName();
            FirTowerDataContext towerDataContext2 = getTowerDataContext();
            try {
                List<FirValueParameter> contextParameters = callable.getContextParameters();
                ArrayList arrayList = new ArrayList();
                for (FirValueParameter firValueParameter : contextParameters) {
                    ImplicitContextParameterValue implicitContextParameterValue = !FirValueParameterKindKt.isLegacyContextReceiver(firValueParameter) ? new ImplicitContextParameterValue(firValueParameter.getSymbol(), FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef())) : null;
                    if (implicitContextParameterValue != null) {
                        arrayList.add(implicitContextParameterValue);
                    }
                }
                replaceTowerDataContext(getTowerDataContext().addContextGroups(arrayList));
                if (coneType != null) {
                    if (FirStatusUtilsKt.isCompanionExtension(callable)) {
                        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(holder, TypeExpansionUtilsKt.fullyExpandedType(holder, coneType));
                        FirContainingNamesAwareScope firContainingNamesAwareScopeStaticScope = regularClassSymbol != null ? ImplicitReceiverUtilsKt.staticScope(regularClassSymbol, holder) : null;
                        if (regularClassSymbol != null) {
                            addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElementForStaticScope(regularClassSymbol, firContainingNamesAwareScopeStaticScope));
                        }
                    } else {
                        FirReceiverParameter receiverParameter2 = callable.getReceiverParameter();
                        receiverParameter2.getClass();
                        addReceiver(name, new ImplicitExtensionReceiverValue(receiverParameter2.getSymbol(), coneType, holder.getSession(), holder.getScopeSession()));
                    }
                }
                T t = (T) f.invoke();
                replaceTowerDataContext(towerDataContext2);
                return t;
            } finally {
                replaceTowerDataContext(towerDataContext2);
            }
        } catch (Throwable th) {
            replaceTowerDataContext(towerDataContext);
            throw th;
        }
    }

    public final <T> T withProperty(FirProperty property, Function0<? extends T> f) {
        T t;
        property.getClass();
        f.getClass();
        FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(property) ? FirTowerDataMode.COMPANION_BLOCK : null;
        FirTowerDataMode towerDataMode2 = getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = getTowerDataMode();
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode2);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        setTowerDataMode(towerDataMode);
        if (property.getTypeParameters().isEmpty()) {
            getContainers().add(property);
            try {
                t = (T) f.invoke();
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
            } finally {
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
            }
        } else {
            FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(property);
            FirTowerDataContext towerDataContext = getTowerDataContext();
            try {
                addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                getContainers().add(property);
                try {
                    t = (T) f.invoke();
                    InlineMarker.finallyStart(1);
                    getContainers().removeLast();
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    replaceTowerDataContext(towerDataContext);
                    InlineMarker.finallyEnd(1);
                } finally {
                    InlineMarker.finallyStart(1);
                    getContainers().removeLast();
                    InlineMarker.finallyEnd(1);
                }
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                replaceTowerDataContext(towerDataContext);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
        InlineMarker.finallyStart(1);
        setTowerDataMode(towerDataMode2);
        return t;
    }

    public final <T> T withPropertyAccessor(FirProperty property, FirPropertyAccessor accessor, SessionAndScopeSessionHolder holder, boolean forContracts, Function0<? extends T> f) {
        ConeKotlinType coneType;
        FirFunction publicApiInlineFunction;
        property.getClass();
        accessor.getClass();
        holder.getClass();
        f.getClass();
        if ((accessor instanceof FirDefaultPropertyAccessor) || accessor.getBody() == null) {
            if (accessor.getIsGetter()) {
                getContainers().add(accessor);
                try {
                    return (T) f.invoke();
                } finally {
                    getContainers().removeLast();
                }
            }
            FirTowerDataContext towerDataContext = getTowerDataContext();
            try {
                addLocalScope(new FirLocalScope(holder.getSession()));
                getContainers().add(accessor);
                try {
                    T t = (T) f.invoke();
                    getContainers().removeLast();
                    replaceTowerDataContext(towerDataContext);
                    return t;
                } catch (Throwable th) {
                    getContainers().removeLast();
                    throw th;
                }
            } catch (Throwable th2) {
                replaceTowerDataContext(towerDataContext);
                throw th2;
            }
        }
        FirTowerDataContext towerDataContext2 = getTowerDataContext();
        try {
            FirReceiverParameter receiverParameter = property.getReceiverParameter();
            FirTypeRef typeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
            addLocalScope(new FirLocalScope(holder.getSession()));
            Iterator<FirValueParameter> it = property.getContextParameters().iterator();
            while (it.hasNext()) {
                storeValueParameterIfNeeded(it.next(), holder.getSession());
            }
            if (!forContracts && typeRef == null && !(property.getReturnTypeRef() instanceof FirImplicitTypeRef) && (property.getSymbol() instanceof FirRegularPropertySymbol) && property.getDelegate() == null && property.getContextParameters().isEmpty()) {
                storeBackingField(property, holder.getSession());
            }
            getContainers().add(accessor);
            if (typeRef != null) {
                try {
                    coneType = FirTypeUtilsKt.getConeType(typeRef);
                } catch (Throwable th3) {
                    getContainers().removeLast();
                    throw th3;
                }
            } else {
                coneType = null;
            }
            if (!isPublicInline(accessor)) {
                publicApiInlineFunction = null;
            }
            if (publicApiInlineFunction == null) {
                publicApiInlineFunction = accessor;
                publicApiInlineFunction = getPublicApiInlineFunction();
            }
            publicApiInlineFunction = accessor;
            FirFunction publicApiInlineFunction2 = getPublicApiInlineFunction();
            try {
                setPublicApiInlineFunction(publicApiInlineFunction);
                Name name = property.getName();
                FirTowerDataContext towerDataContext3 = getTowerDataContext();
                try {
                    List<FirValueParameter> contextParameters = property.getContextParameters();
                    ArrayList arrayList = new ArrayList();
                    for (FirValueParameter firValueParameter : contextParameters) {
                        ImplicitContextParameterValue implicitContextParameterValue = !FirValueParameterKindKt.isLegacyContextReceiver(firValueParameter) ? new ImplicitContextParameterValue(firValueParameter.getSymbol(), FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef())) : null;
                        if (implicitContextParameterValue != null) {
                            arrayList.add(implicitContextParameterValue);
                        }
                    }
                    replaceTowerDataContext(getTowerDataContext().addContextGroups(arrayList));
                    if (coneType != null) {
                        if (FirStatusUtilsKt.isCompanionExtension(property)) {
                            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(holder, TypeExpansionUtilsKt.fullyExpandedType(holder, coneType));
                            FirContainingNamesAwareScope firContainingNamesAwareScopeStaticScope = regularClassSymbol != null ? ImplicitReceiverUtilsKt.staticScope(regularClassSymbol, holder) : null;
                            if (regularClassSymbol != null) {
                                addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElementForStaticScope(regularClassSymbol, firContainingNamesAwareScopeStaticScope));
                            }
                        } else {
                            FirReceiverParameter receiverParameter2 = property.getReceiverParameter();
                            receiverParameter2.getClass();
                            addReceiver(name, new ImplicitExtensionReceiverValue(receiverParameter2.getSymbol(), coneType, holder.getSession(), holder.getScopeSession()));
                        }
                    }
                    T t2 = (T) f.invoke();
                    replaceTowerDataContext(towerDataContext3);
                    setPublicApiInlineFunction(publicApiInlineFunction2);
                    getContainers().removeLast();
                    return t2;
                } finally {
                    replaceTowerDataContext(towerDataContext3);
                }
            } catch (Throwable th4) {
                setPublicApiInlineFunction(publicApiInlineFunction2);
                throw th4;
            }
        } catch (Throwable th5) {
            replaceTowerDataContext(towerDataContext2);
            throw th5;
        }
    }

    public final <T> T withPublicApiInlineFunction(FirFunction function, Function0<? extends T> block) {
        block.getClass();
        FirFunction publicApiInlineFunction = getPublicApiInlineFunction();
        try {
            setPublicApiInlineFunction(function);
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setPublicApiInlineFunction(publicApiInlineFunction);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withPublicApiInlineFunctionIfApplicable(FirFunction function, Function0<? extends T> block) {
        function.getClass();
        block.getClass();
        if (!isPublicInline(function)) {
            function = null;
        }
        if (function == null) {
            function = getPublicApiInlineFunction();
        }
        FirFunction publicApiInlineFunction = getPublicApiInlineFunction();
        try {
            setPublicApiInlineFunction(function);
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setPublicApiInlineFunction(publicApiInlineFunction);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withReceiverParameter(FirReceiverParameter valueParameter, Function0<? extends T> f) {
        valueParameter.getClass();
        f.getClass();
        getContainers().add(valueParameter);
        try {
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withReplSnippet(FirReplSnippet replSnippet, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        FirScope snippetScope;
        replSnippet.getClass();
        holder.getClass();
        f.getClass();
        getContainers().add(replSnippet);
        try {
            FirTowerDataContext towerDataContext = getTowerDataContext();
            try {
                List<FirScriptReceiverParameter> receivers = replSnippet.getReceivers();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(receivers, 10));
                int i = 0;
                for (T t : receivers) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    FirScriptReceiverParameter firScriptReceiverParameter = (FirScriptReceiverParameter) t;
                    arrayList.add(new ImplicitReceiverValueForScriptOrSnippet(firScriptReceiverParameter.getSymbol(), FirTypeUtilsKt.getConeType(firScriptReceiverParameter.getTypeRef()), holder.getSession(), holder.getScopeSession()));
                    i = i2;
                }
                Iterator<T> it = CollectionsKt.asReversed(arrayList).iterator();
                while (it.hasNext()) {
                    addReceiver(null, (ImplicitReceiverValueForScriptOrSnippet) it.next());
                }
                FirReplSnippetResolveExtension replSnippetResolveExtension = FirReplSnippetResolveExtensionKt.getReplSnippetResolveExtension(holder.getSession());
                if (replSnippetResolveExtension != null && (snippetScope = replSnippetResolveExtension.getSnippetScope(replSnippet, holder.getSession())) != null) {
                    addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(snippetScope, false));
                }
                T t2 = (T) f.invoke();
                replaceTowerDataContext(towerDataContext);
                getContainers().removeLast();
                return t2;
            } catch (Throwable th) {
                replaceTowerDataContext(towerDataContext);
                throw th;
            }
        } catch (Throwable th2) {
            getContainers().removeLast();
            throw th2;
        }
    }

    public final <T> T withReturnTypeCalculator(ReturnTypeCalculator returnTypeCalculator, Function0<? extends T> f) {
        returnTypeCalculator.getClass();
        f.getClass();
        ReturnTypeCalculator returnTypeCalculator2 = getReturnTypeCalculator();
        try {
            setReturnTypeCalculator(returnTypeCalculator);
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setReturnTypeCalculator(returnTypeCalculator2);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withScopesForClass(FirClass owner, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        Name nameShortName;
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        CallableId callableId;
        FqName className;
        boolean z;
        FirTowerDataContext firTowerDataContextAddReceiver;
        FirDeclaration firDeclaration;
        List<FirDeclaration> declarations;
        T next;
        ClassKind classKind;
        owner.getClass();
        holder.getClass();
        f.getClass();
        boolean z2 = owner instanceof FirRegularClass;
        FirRegularClass firRegularClass = z2 ? (FirRegularClass) owner : null;
        if (firRegularClass == null || (nameShortName = firRegularClass.getName()) == null) {
            nameShortName = (owner.getClassKind() != ClassKind.ENUM_ENTRY || (firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(owner, holder.getSession())) == null || (callableId = firConstructorSymbolPrimaryConstructorIfAny.getCallableId()) == null || (className = callableId.getClassName()) == null) ? null : className.shortName();
        }
        ConeClassLikeType coneClassLikeTypeDefaultType = ScopeUtilsKt.defaultType(owner);
        TowerElementsForClass towerElementsForClassCollectTowerDataElementsForClass = ImplicitReceiverUtilsKt.collectTowerDataElementsForClass(holder, owner, coneClassLikeTypeDefaultType);
        FirTowerDataContext firTowerDataContextAddNonLocalTowerDataElements = getTowerDataContext().addNonLocalTowerDataElements(towerElementsForClassCollectTowerDataElementsForClass.getSuperClassesStaticsAndCompanionReceivers());
        FirTowerDataContext firTowerDataContextAddCompanionAndStaticScopes = firTowerDataContextAddNonLocalTowerDataElements.addCompanionAndStaticScopes(towerElementsForClassCollectTowerDataElementsForClass);
        ImplicitReceiverValue<?> companionReceiver = towerElementsForClassCollectTowerDataElementsForClass.getCompanionReceiver();
        FirTowerDataContext firTowerDataContextAddCompanionAndStaticScopes2 = companionReceiver == null ? firTowerDataContextAddCompanionAndStaticScopes : firTowerDataContextAddNonLocalTowerDataElements.addReceiver(null, companionReceiver).addCompanionAndStaticScopes(towerElementsForClassCollectTowerDataElementsForClass);
        FirRegularClass firRegularClass2 = z2 ? (FirRegularClass) owner : null;
        FirMemberTypeParameterScope firMemberTypeParameterScopeTypeParameterScope = firRegularClass2 != null ? typeParameterScope(firRegularClass2) : null;
        FirTowerDataContext firTowerDataContextAddNonLocalScope = firMemberTypeParameterScopeTypeParameterScope != null ? getTowerDataContext().addNonLocalTowerDataElements(towerElementsForClassCollectTowerDataElementsForClass.getSuperClassesStaticsAndCompanionReceivers()).addReceiverIfNotNull(null, towerElementsForClassCollectTowerDataElementsForClass.getCompanionReceiver()).addCompanionAndStaticScopes(towerElementsForClassCollectTowerDataElementsForClass).addNonLocalScope(firMemberTypeParameterScopeTypeParameterScope) : firTowerDataContextAddCompanionAndStaticScopes2;
        FirTowerDataContext firTowerDataContextAddReceiver2 = firTowerDataContextAddNonLocalScope.addReceiver(nameShortName, towerElementsForClassCollectTowerDataElementsForClass.getThisReceiver());
        FirRegularClass firRegularClass3 = z2 ? (FirRegularClass) owner : null;
        if (firRegularClass3 != null && (classKind = firRegularClass3.getClassKind()) != null && classKind.isSingleton()) {
            z = true;
            firTowerDataContextAddReceiver = firTowerDataContextAddReceiver2;
        } else if (this.isContextCollectorMode) {
            z = true;
            firTowerDataContextAddReceiver = firTowerDataContextAddCompanionAndStaticScopes2;
        } else {
            z = true;
            firTowerDataContextAddReceiver = firTowerDataContextAddCompanionAndStaticScopes2.addReceiver(nameShortName, new InaccessibleImplicitReceiverValue(owner.getSymbol(), coneClassLikeTypeDefaultType, InaccessibleReceiverKind.OuterClassOfNonInner, holder.getSession(), holder.getScopeSession()));
        }
        FirRegularClass firRegularClass4 = z2 ? (FirRegularClass) owner : null;
        if (firRegularClass4 == null || (declarations = firRegularClass4.getDeclarations()) == null) {
            firDeclaration = null;
        } else {
            Iterator<T> it = declarations.iterator();
            do {
                if (!it.hasNext()) {
                    next = (T) null;
                    break;
                }
                next = it.next();
            } while (!(((FirDeclaration) next) instanceof FirConstructor));
            firDeclaration = next;
        }
        FirConstructor firConstructor = firDeclaration instanceof FirConstructor ? (FirConstructor) firDeclaration : null;
        Pair<FirLocalScope, FirLocalScope> pairScopesWithPrimaryConstructorParameters = (firConstructor == null || firConstructor.getIsPrimary() != z) ? TuplesKt.to(null, null) : scopesWithPrimaryConstructorParameters(firConstructor, holder.getSession());
        FirRegularTowerDataContexts firRegularTowerDataContexts = new FirRegularTowerDataContexts(firTowerDataContextAddReceiver2, firTowerDataContextAddReceiver, firTowerDataContextAddCompanionAndStaticScopes, firTowerDataContextAddCompanionAndStaticScopes2, !this.isContextCollectorMode ? getTowerDataContext().addReceiver(nameShortName, new InaccessibleImplicitReceiverValue(owner.getSymbol(), coneClassLikeTypeDefaultType, InaccessibleReceiverKind.ClassHeader, holder.getSession(), holder.getScopeSession())).addNonLocalTowerDataElements(towerElementsForClassCollectTowerDataElementsForClass.getSuperClassesStaticsAndCompanionReceivers()).addReceiverIfNotNull(null, towerElementsForClassCollectTowerDataElementsForClass.getCompanionReceiver()).addCompanionAndStaticScopes(towerElementsForClassCollectTowerDataElementsForClass).addNonLocalScopeIfNotNull(firMemberTypeParameterScopeTypeParameterScope) : firTowerDataContextAddNonLocalScope, firTowerDataContextAddNonLocalScope, (FirLocalScope) pairScopesWithPrimaryConstructorParameters.component1(), (FirLocalScope) pairScopesWithPrimaryConstructorParameters.component2());
        FirRegularTowerDataContexts regularTowerDataContexts = getRegularTowerDataContexts();
        setRegularTowerDataContexts(firRegularTowerDataContexts);
        try {
            return (T) f.invoke();
        } finally {
            setRegularTowerDataContexts(regularTowerDataContexts);
        }
    }

    public final <T> T withScript(FirScript owner, SessionAndScopeSessionHolder holder, Function0<? extends T> f) {
        owner.getClass();
        holder.getClass();
        f.getClass();
        TowerElementsForScript towerElementsForScriptCollectTowerDataElementsForScript = ScriptScopesKt.collectTowerDataElementsForScript(holder, owner);
        FirTowerDataContext firTowerDataContextAddNonLocalTowerDataElements = getTowerDataContext().addNonLocalTowerDataElements(CollectionsKt.emptyList());
        for (ImplicitReceiverValueForScriptOrSnippet implicitReceiverValueForScriptOrSnippet : towerElementsForScriptCollectTowerDataElementsForScript.getImplicitReceivers()) {
            ClassId classId = ConeTypeUtilsKt.getClassId(implicitReceiverValueForScriptOrSnippet.getType());
            firTowerDataContextAddNonLocalTowerDataElements = firTowerDataContextAddNonLocalTowerDataElements.addReceiver(classId != null ? classId.getShortClassName() : null, implicitReceiverValueForScriptOrSnippet);
        }
        FirTowerDataContext firTowerDataContextAddNonLocalScopeIfNotNull = firTowerDataContextAddNonLocalTowerDataElements.addNonLocalScopeIfNotNull(towerElementsForScriptCollectTowerDataElementsForScript.getStaticScope());
        List<FirProperty> parameters = owner.getParameters();
        ArrayList arrayList = new ArrayList();
        for (T t : parameters) {
            if (!Intrinsics.areEqual(((FirProperty) t).getOrigin(), FirDeclarationOrigin.ScriptCustomization.ParameterFromBaseClass.INSTANCE)) {
                arrayList.add(t);
            }
        }
        FirLocalScope firLocalScope = new FirLocalScope(holder.getSession());
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            firLocalScope = firLocalScope.storeVariable((FirProperty) it.next(), holder.getSession());
        }
        FirTowerDataContext firTowerDataContextAddLocalScope = firTowerDataContextAddNonLocalScopeIfNotNull.addLocalScope(firLocalScope);
        FirRegularTowerDataContexts firRegularTowerDataContexts = new FirRegularTowerDataContexts(firTowerDataContextAddLocalScope, firTowerDataContextAddLocalScope, firTowerDataContextAddNonLocalScopeIfNotNull, null, null, null, null, null, 8, null);
        FirRegularTowerDataContexts regularTowerDataContexts = getRegularTowerDataContexts();
        setRegularTowerDataContexts(firRegularTowerDataContexts);
        try {
            getContainers().add(owner);
            try {
                T t2 = (T) f.invoke();
                getContainers().removeLast();
                setRegularTowerDataContexts(regularTowerDataContexts);
                return t2;
            } catch (Throwable th) {
                getContainers().removeLast();
                throw th;
            }
        } catch (Throwable th2) {
            setRegularTowerDataContexts(regularTowerDataContexts);
            throw th2;
        }
    }

    @PrivateForInline
    public final <T> T withSwitchedTowerDataModeForStaticNestedClass(FirRegularClass regularClass, Function0<? extends T> f) {
        regularClass.getClass();
        f.getClass();
        FirTowerDataMode towerDataMode = getTowerDataMode();
        try {
            if (!regularClass.getStatus().isInner() && (getContainerIfAny() instanceof FirRegularClass)) {
                setTowerDataMode(regularClass.getStatus().isCompanion() ? FirTowerDataMode.COMPANION_OBJECT : FirTowerDataMode.NESTED_CLASS);
            }
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode);
            InlineMarker.finallyEnd(1);
        }
    }

    @PrivateForInline
    public final <T> T withTemporaryRegularContext(Pair<FirTowerDataContext, ? extends FirInferenceSession> newContext, Function0<? extends T> f) {
        T t;
        f.getClass();
        if (newContext == null) {
            return (T) f.invoke();
        }
        FirTowerDataContext firTowerDataContext = (FirTowerDataContext) newContext.component1();
        FirInferenceSession firInferenceSession = (FirInferenceSession) newContext.component2();
        FirTowerDataMode towerDataMode = getTowerDataMode();
        try {
            FirRegularTowerDataContexts firRegularTowerDataContextsReplaceAndSetActiveRegularContext = getRegularTowerDataContexts().replaceAndSetActiveRegularContext(firTowerDataContext);
            FirRegularTowerDataContexts regularTowerDataContexts = getRegularTowerDataContexts();
            setRegularTowerDataContexts(firRegularTowerDataContextsReplaceAndSetActiveRegularContext);
            try {
                if (firInferenceSession != getInferenceSession()) {
                    FirInferenceSession inferenceSession = getInferenceSession();
                    setInferenceSession(firInferenceSession);
                    try {
                        t = (T) f.invoke();
                        InlineMarker.finallyStart(1);
                        setInferenceSession(inferenceSession);
                        InlineMarker.finallyEnd(1);
                    } finally {
                        InlineMarker.finallyStart(1);
                        setInferenceSession(inferenceSession);
                        InlineMarker.finallyEnd(1);
                    }
                } else {
                    t = (T) f.invoke();
                }
                InlineMarker.finallyStart(1);
                setRegularTowerDataContexts(regularTowerDataContexts);
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode);
                return t;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setRegularTowerDataContexts(regularTowerDataContexts);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    @PrivateForInline
    public final <R> R withTowerDataCleanup(Function0<? extends R> l) {
        l.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            return (R) l.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            replaceTowerDataContext(towerDataContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withTowerDataContext(FirTowerDataContext newContext, Function0<? extends T> f) {
        newContext.getClass();
        f.getClass();
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            replaceTowerDataContext(newContext);
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            replaceTowerDataContext(towerDataContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withTowerDataContexts(FirRegularTowerDataContexts newContexts, Function0<? extends T> f) {
        newContexts.getClass();
        f.getClass();
        FirRegularTowerDataContexts regularTowerDataContexts = getRegularTowerDataContexts();
        setRegularTowerDataContexts(newContexts);
        try {
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setRegularTowerDataContexts(regularTowerDataContexts);
            InlineMarker.finallyEnd(1);
        }
    }

    @PrivateForInline
    public final <T> T withTowerDataMode(FirTowerDataMode mode, Function0<? extends T> f) {
        f.getClass();
        FirTowerDataMode towerDataMode = getTowerDataMode();
        if (mode == null) {
            try {
                mode = getTowerDataMode();
            } finally {
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode);
                InlineMarker.finallyEnd(1);
            }
        }
        setTowerDataMode(mode);
        return (T) f.invoke();
    }

    @PrivateForInline
    public final <R> R withTowerDataModeCleanup(Function0<? extends R> l) {
        l.getClass();
        FirTowerDataMode towerDataMode = getTowerDataMode();
        try {
            return (R) l.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setTowerDataMode(towerDataMode);
            InlineMarker.finallyEnd(1);
        }
    }

    @PrivateForInline
    public final <T> T withTypeParametersOf(FirMemberDeclaration declaration, Function0<? extends T> l) {
        declaration.getClass();
        l.getClass();
        if (declaration.getTypeParameters().isEmpty()) {
            return (T) l.invoke();
        }
        FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(declaration);
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
            return (T) l.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            replaceTowerDataContext(towerDataContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withValueParameter(FirValueParameter valueParameter, FirSession session, Function0<? extends T> f) {
        valueParameter.getClass();
        session.getClass();
        f.getClass();
        storeValueParameterIfNeeded(valueParameter, session);
        getContainers().add(valueParameter);
        try {
            return (T) f.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContainers().removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withVariableAsContainerIfNeeded(FirProperty variable, boolean treatAsProperty, Function0<? extends T> f) {
        T t;
        variable.getClass();
        f.getClass();
        if (!treatAsProperty) {
            return (T) f.invoke();
        }
        FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(variable) ? FirTowerDataMode.COMPANION_BLOCK : null;
        FirTowerDataMode towerDataMode2 = getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = getTowerDataMode();
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                setTowerDataMode(towerDataMode2);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        setTowerDataMode(towerDataMode);
        if (variable.getTypeParameters().isEmpty()) {
            getContainers().add(variable);
            try {
                t = (T) f.invoke();
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
            } finally {
                InlineMarker.finallyStart(1);
                getContainers().removeLast();
                InlineMarker.finallyEnd(1);
            }
        } else {
            FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(variable);
            FirTowerDataContext towerDataContext = getTowerDataContext();
            try {
                addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                getContainers().add(variable);
                try {
                    t = (T) f.invoke();
                    InlineMarker.finallyStart(1);
                    getContainers().removeLast();
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    replaceTowerDataContext(towerDataContext);
                    InlineMarker.finallyEnd(1);
                } finally {
                    InlineMarker.finallyStart(1);
                    getContainers().removeLast();
                    InlineMarker.finallyEnd(1);
                }
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                replaceTowerDataContext(towerDataContext);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
        InlineMarker.finallyStart(1);
        setTowerDataMode(towerDataMode2);
        return t;
    }

    public final <T> T withWhenExpression(FirWhenExpression whenExpression, FirSession session, Function0<? extends T> f) {
        whenExpression.getClass();
        session.getClass();
        f.getClass();
        if (whenExpression.getSubjectVariable() == null) {
            return (T) f.invoke();
        }
        FirTowerDataContext towerDataContext = getTowerDataContext();
        try {
            addLocalScope(new FirLocalScope(session));
            return (T) f.invoke();
        } finally {
            replaceTowerDataContext(towerDataContext);
        }
    }
}

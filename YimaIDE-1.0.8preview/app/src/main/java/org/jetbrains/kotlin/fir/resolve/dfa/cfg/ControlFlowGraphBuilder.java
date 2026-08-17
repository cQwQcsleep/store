package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.InlineStatus;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirBreakExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirContinueExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirUnitExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.Stack;
import org.jetbrains.kotlin.fir.resolve.dfa.StackKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphBuilder;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallArgumentsExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.SplitPostponedLambdasNode;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.util.ListMultimap;
import org.jetbrains.kotlin.fir.util.MultimapKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¤\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001:\u0002ð\u0002BÑ\u0003\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0003\u0012\u0016\u0010\u0007\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\n0\b\u0012&\u0010\u000b\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u000e0\r0\b\u0012\u0016\u0010\u000f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0003\u0012(\u0010\u0014\u001a$\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0016\u0012\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r0\b\u0012\u0016\u0010\u0016\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00170\b\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0003\u0012\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\b\u0012\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001e0\b\u0012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0003\u0012\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0003\u0012\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0\u0003\u0012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u0003\u0012\f\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u0003\u0012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u0003\u0012\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+\u0012\u000e\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010.0\u0003\u0012\f\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u0003\u0012\f\u00101\u001a\b\u0012\u0004\u0012\u0002020\u0003\u0012\f\u00103\u001a\b\u0012\u0004\u0012\u0002040\u0003\u0012\u0010\u00105\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0003\u0012\u0012\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000208070\u0003¢\u0006\u0004\b9\u0010:B\t\b\u0016¢\u0006\u0004\b9\u0010;J\u0015\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020>H\u0000¢\u0006\u0002\b?J\u000e\u0010Q\u001a\u00020A2\u0006\u0010R\u001a\u00020,J\u0016\u0010S\u001a\n\u0012\u0004\u0012\u00020U\u0018\u00010T2\u0006\u0010V\u001a\u00020WJ\u0084\u0001\u0010X\u001a\u0002HY\"\b\b\u0000\u0010Z*\u00020,\"\n\b\u0001\u0010[*\u0004\u0018\u0001HZ\"\u0012\b\u0002\u0010Y*\b\u0012\u0004\u0012\u0002HZ0\u0006*\u00020\\\"\u0012\b\u0003\u0010]*\b\u0012\u0004\u0012\u0002HZ0\u0006*\u00020^2\u0006\u0010_\u001a\u0002H[2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020c2\u001e\u0010d\u001a\u001a\u0012\u0004\u0012\u0002H[\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002HY\u0012\u0004\u0012\u0002H]0\r0eH\u0082\b¢\u0006\u0002\u0010fJ\b\u0010g\u001a\u00020\u0004H\u0002J)\u0010h\u001a\u000e\u0012\u0004\u0012\u0002H]\u0012\u0004\u0012\u00020\u00040\r\"\u0012\b\u0000\u0010]\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0006*\u00020^H\u0082\bJ\u001c\u0010i\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010j\u0012\u0004\u0012\u00020k0\r2\u0006\u0010V\u001a\u00020lJ\u001a\u0010m\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010V\u001a\u00020lJ\u001e\u0010n\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010o\u0012\u0006\u0012\u0004\u0018\u00010\u00170\r2\u0006\u0010p\u001a\u00020qJ\u000e\u0010r\u001a\u00020k2\u0006\u0010s\u001a\u00020WJ\"\u0010t\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00040u2\u0006\u0010s\u001a\u00020WJ\u001c\u0010v\u001a\u00020w2\u0012\b\u0002\u0010x\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0yH\u0002J\u0014\u0010z\u001a\u00020w2\n\u0010{\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\u001c\u0010|\u001a\u00020w2\n\u0010}\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010~\u001a\u00020AH\u0002J\u001c\u0010\u007f\u001a\u00020w2\n\u0010}\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010~\u001a\u00020AH\u0002J\u001d\u0010\u0080\u0001\u001a\u0005\u0018\u00010\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020AJ\u0018\u0010\u0085\u0001\u001a\u0013\u0012\u0007\u0012\u0005\u0018\u00010\u0086\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\rJ*\u0010\u0087\u0001\u001a\u00020w*\b\u0012\u0004\u0012\u00020,0$2\u0014\u0010\u0088\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0089\u0001\u0012\u0004\u0012\u00020w0eH\u0082\bJ#\u0010\u008a\u0001\u001a\u00020w\"\t\b\u0000\u0010[*\u00030\u008b\u00012\r\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u0002H[0\u0006H\u0002J/\u0010\u008d\u0001\u001a\u0017\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\u0007\u0012\u0005\u0018\u00010\u008e\u00010\r2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u0084\u0001\u001a\u00020AJ\u0018\u0010\u0091\u0001\u001a\u0013\u0012\u0007\u0012\u0005\u0018\u00010\u0092\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\rJ\u0014\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u00012\b\u0010\u0095\u0001\u001a\u00030\u0096\u0001J\u001d\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u00012\b\u0010\u0099\u0001\u001a\u00030\u009a\u00012\u0007\u0010\u0084\u0001\u001a\u00020AJ\u0018\u0010\u009b\u0001\u001a\u0013\u0012\u0007\u0012\u0005\u0018\u00010\u009c\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00040\rJ\u0012\u0010\u009d\u0001\u001a\u00030\u009e\u00012\b\u0010\u009f\u0001\u001a\u00030 \u0001J\u0014\u0010¡\u0001\u001a\u000f\u0012\u0005\u0012\u00030¢\u0001\u0012\u0004\u0012\u00020\u00040\rJ!\u0010£\u0001\u001a\u0012\u0012\u0005\u0012\u00030¤\u0001\u0012\u0005\u0012\u00030¥\u0001\u0018\u00010\r2\b\u0010¦\u0001\u001a\u00030§\u0001J'\u0010¨\u0001\u001a\u0018\u0012\u0005\u0012\u00030©\u0001\u0012\u0005\u0012\u00030ª\u0001\u0012\u0004\u0012\u00020\u0004\u0018\u00010u2\b\u0010¦\u0001\u001a\u00030§\u0001J\u0012\u0010«\u0001\u001a\u00030¬\u00012\b\u0010\u0088\u0001\u001a\u00030\u00ad\u0001J\u0015\u0010®\u0001\u001a\u0006\u0012\u0002\b\u00030\u00062\b\u0010\u0088\u0001\u001a\u00030\u00ad\u0001J\u0014\u0010¯\u0001\u001a\u0005\u0018\u00010°\u00012\b\u0010±\u0001\u001a\u00030²\u0001J \u0010³\u0001\u001a\u0011\u0012\u0005\u0012\u00030´\u0001\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\u0010±\u0001\u001a\u00030²\u0001J\u0014\u0010µ\u0001\u001a\u0005\u0018\u00010¶\u00012\b\u0010·\u0001\u001a\u00030¸\u0001J \u0010¹\u0001\u001a\u0011\u0012\u0005\u0012\u00030º\u0001\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\u0010·\u0001\u001a\u00030¸\u0001J\u0007\u0010»\u0001\u001a\u00020wJ\u0011\u0010¼\u0001\u001a\u00030½\u00012\u0007\u0010_\u001a\u00030¾\u0001J\u0012\u0010¿\u0001\u001a\u00030À\u00012\b\u0010Á\u0001\u001a\u00030Â\u0001J\u0012\u0010Ã\u0001\u001a\u00030Ä\u00012\b\u0010Å\u0001\u001a\u00030Æ\u0001J\u0007\u0010Ç\u0001\u001a\u00020wJ*\u0010È\u0001\u001a\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0005\u0012\u00030É\u00010\r2\b\u0010Ê\u0001\u001a\u00030Ë\u00012\u0006\u0010~\u001a\u00020AJ\u0015\u0010Ì\u0001\u001a\u00020w2\f\u0010Í\u0001\u001a\u0007\u0012\u0002\b\u00030Î\u0001J\u0015\u0010Ï\u0001\u001a\u00020\u00112\f\u0010Í\u0001\u001a\u0007\u0012\u0002\b\u00030Î\u0001J\u0012\u0010Ó\u0001\u001a\u00030Ô\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u0001J\u0012\u0010×\u0001\u001a\u00030Ø\u00012\b\u0010Ù\u0001\u001a\u00030Ú\u0001J\u0012\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u0001J\u001f\u0010ß\u0001\u001a\u0010\u0012\u0005\u0012\u00030à\u0001\u0012\u0005\u0012\u00030á\u00010\r2\b\u0010Ý\u0001\u001a\u00030Þ\u0001J\u0012\u0010â\u0001\u001a\u00030ã\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u0001J(\u0010ä\u0001\u001a\u0011\u0012\u0004\u0012\u00020 \u0012\u0007\u0012\u0005\u0018\u00010å\u00010\r2\b\u0010Õ\u0001\u001a\u00030Ö\u00012\u0006\u0010~\u001a\u00020AJ\u001d\u0010æ\u0001\u001a\u000f\u0012\u0005\u0012\u00030ç\u0001\u0012\u0004\u0012\u00020\u001c0\r2\u0007\u0010è\u0001\u001a\u00020\u001bJ\u001e\u0010é\u0001\u001a\u0010\u0012\u0005\u0012\u00030ê\u0001\u0012\u0005\u0012\u00030ë\u00010\r2\u0007\u0010è\u0001\u001a\u00020\u001bJ#\u0010ì\u0001\u001a\u0015\u0012\u0004\u0012\u00020\u001c\u0012\u0005\u0012\u00030í\u0001\u0012\u0004\u0012\u00020\u001e0u2\u0007\u0010è\u0001\u001a\u00020\u001bJ\u001e\u0010î\u0001\u001a\u0010\u0012\u0005\u0012\u00030ç\u0001\u0012\u0005\u0012\u00030ë\u00010\r2\u0007\u0010è\u0001\u001a\u00020\u001bJ\u001d\u0010ï\u0001\u001a\u000f\u0012\u0005\u0012\u00030í\u0001\u0012\u0004\u0012\u00020\u001c0\r2\u0007\u0010è\u0001\u001a\u00020\u001bJ\u001d\u0010ð\u0001\u001a\u000f\u0012\u0005\u0012\u00030ê\u0001\u0012\u0004\u0012\u00020\u001e0\r2\u0007\u0010è\u0001\u001a\u00020\u001bJ\u0018\u0010ñ\u0001\u001a\t\u0012\u0005\u0012\u00030ò\u00010\u00062\b\u0010ó\u0001\u001a\u00030ò\u0001J+\u0010ô\u0001\u001a\u001c\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030ò\u00010\u0006\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030ò\u00010\u00060\r2\b\u0010ó\u0001\u001a\u00030ò\u0001J\u0012\u0010õ\u0001\u001a\u00030ö\u00012\b\u0010ó\u0001\u001a\u00030ò\u0001J\u001f\u0010÷\u0001\u001a\u0010\u0012\u0005\u0012\u00030ø\u0001\u0012\u0005\u0012\u00030ù\u00010\r2\b\u0010ú\u0001\u001a\u00030û\u0001J\b\u0010ü\u0001\u001a\u00030ý\u0001J\u0011\u0010þ\u0001\u001a\u00020%2\b\u0010ÿ\u0001\u001a\u00030\u0080\u0002J\u0012\u0010\u0081\u0002\u001a\u00030\u0082\u00022\b\u0010ÿ\u0001\u001a\u00030\u0080\u0002J\u0007\u0010\u0083\u0002\u001a\u00020(J\b\u0010\u0084\u0002\u001a\u00030\u0085\u0002J<\u0010\u0089\u0002\u001a\u00020w\"\u0011\b\u0000\u0010Z*\u0006\u0012\u0002\b\u00030\u0006*\u00030\u008a\u0002*\u0006\u0012\u0002\b\u00030\u00062\r\u0010d\u001a\t\u0012\u0004\u0012\u0002HZ0\u008b\u00022\u0007\u0010\u008c\u0002\u001a\u00020IH\u0002J\u000f\u0010\u008d\u0002\u001a\u00020\"2\u0006\u0010~\u001a\u00020AJ\t\u0010\u008e\u0002\u001a\u00020IH\u0002J\u0011\u0010\u008f\u0002\u001a\u00020A2\u0006\u0010}\u001a\u000208H\u0002J\u0012\u0010\u0090\u0002\u001a\u00030\u0091\u00022\b\u0010\u0092\u0002\u001a\u00030\u0093\u0002J\u0012\u0010\u0094\u0002\u001a\u00030\u0095\u00022\b\u0010\u0096\u0002\u001a\u00030\u0097\u0002J\u0012\u0010\u0098\u0002\u001a\u00030\u0099\u00022\b\u0010\u009a\u0002\u001a\u00030\u009b\u0002J\u001b\u0010\u009c\u0002\u001a\u00020w2\u0012\b\u0002\u0010x\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0yJ#\u0010\u009d\u0002\u001a\u0005\u0018\u00010\u009e\u00022\b\u0010\u009f\u0002\u001a\u00030 \u00022\r\u0010¡\u0002\u001a\b\u0012\u0004\u0012\u00020W0$J\u0017\u0010¢\u0002\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0006\u0012\u0004\u0018\u00010.0\rJ\u0007\u0010£\u0002\u001a\u00020wJ\u0012\u0010¤\u0002\u001a\u00030¥\u00022\b\u0010¦\u0002\u001a\u00030§\u0002J\u0019\u0010¨\u0002\u001a\u0002082\b\u0010¦\u0002\u001a\u00030§\u00022\u0006\u0010~\u001a\u00020AJ\u001a\u0010©\u0002\u001a\u00030ª\u00022\b\u0010\u009f\u0002\u001a\u00030«\u00022\u0006\u0010~\u001a\u00020AJ\u0012\u0010¬\u0002\u001a\u00030\u00ad\u00022\b\u0010\u009f\u0002\u001a\u00030®\u0002J\u0012\u0010¯\u0002\u001a\u00030°\u00022\b\u0010±\u0002\u001a\u00030²\u0002J\u0012\u0010³\u0002\u001a\u00030´\u00022\b\u0010µ\u0002\u001a\u00030²\u0001J\u0012\u0010¶\u0002\u001a\u00030·\u00022\b\u0010µ\u0002\u001a\u00030²\u0001J\u0012\u0010¸\u0002\u001a\u00030¹\u00022\b\u0010º\u0002\u001a\u00030»\u0002J\u0012\u0010¼\u0002\u001a\u00030½\u00022\b\u0010¾\u0002\u001a\u00030¿\u0002J\u001a\u0010À\u0002\u001a\u00030Á\u00022\b\u0010Â\u0002\u001a\u00030Ã\u00022\u0006\u0010~\u001a\u00020AJ\b\u0010Ä\u0002\u001a\u00030Å\u0002J\u0007\u0010Æ\u0002\u001a\u00020wJ\u0012\u0010Ç\u0002\u001a\u00030È\u00022\b\u0010É\u0002\u001a\u00030Ê\u0002J\u0012\u0010Ë\u0002\u001a\u00030Ì\u00022\b\u0010Í\u0002\u001a\u00030Î\u0002J\u0012\u0010Ï\u0002\u001a\u00030Ð\u00022\b\u0010Ñ\u0002\u001a\u00030Ò\u0002J\u0014\u0010Ó\u0002\u001a\u000f\u0012\u0005\u0012\u00030Ô\u0002\u0012\u0004\u0012\u00020\u00040\rJ\u0012\u0010Õ\u0002\u001a\u00030Ö\u00022\b\u0010×\u0002\u001a\u00030Ø\u0002J\u0007\u0010Ù\u0002\u001a\u000200J\u0011\u0010Ú\u0002\u001a\u00020w2\b\u0010Û\u0002\u001a\u00030Ü\u0002J%\u0010Ý\u0002\u001a\u0016\u0012\u0005\u0012\u00030Þ\u0002\u0012\u0005\u0012\u00030ß\u0002\u0012\u0004\u0012\u0002040u2\b\u0010Û\u0002\u001a\u00030Ü\u0002J\u0018\u0010à\u0002\u001a\u0002022\u0007\u0010á\u0002\u001a\u00020A2\u0006\u0010~\u001a\u00020AJ\u0007\u0010â\u0002\u001a\u00020wJ \u0010ã\u0002\u001a\u00020w2\n\u0010}\u001a\u0006\u0012\u0002\b\u00030\u00062\t\b\u0002\u0010ä\u0002\u001a\u00020AH\u0002J\u0015\u0010å\u0002\u001a\u00020w2\n\u0010}\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J!\u0010æ\u0002\u001a\u00020w2\u000b\u0010ç\u0002\u001a\u0006\u0012\u0002\b\u00030\u00062\t\b\u0002\u0010è\u0002\u001a\u00020\u000eH\u0002JP\u0010é\u0002\u001a\u00020w2\u000b\u0010ê\u0002\u001a\u0006\u0012\u0002\b\u00030\u00062\u000b\u0010ç\u0002\u001a\u0006\u0012\u0002\b\u00030\u00062\t\b\u0002\u0010ë\u0002\u001a\u00020A2\t\b\u0002\u0010ä\u0002\u001a\u00020A2\t\b\u0002\u0010è\u0002\u001a\u00020\u000e2\n\b\u0002\u0010ì\u0002\u001a\u00030\u008a\u0002H\u0002J#\u0010í\u0002\u001a\u00020w2\u000b\u0010ê\u0002\u001a\u0006\u0012\u0002\b\u00030\u00062\u000b\u0010ç\u0002\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J:\u0010î\u0002\u001a\u00020w2\u000b\u0010ê\u0002\u001a\u0006\u0012\u0002\b\u00030\u00062\u000b\u0010ç\u0002\u001a\u0006\u0012\u0002\b\u00030\u00062\t\b\u0002\u0010ä\u0002\u001a\u00020A2\n\b\u0002\u0010ì\u0002\u001a\u00030\u008a\u0002H\u0002J\u0015\u0010ï\u0002\u001a\u00020w2\n\u0010}\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u000b\u001a\"\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u000e0\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u0014\u001a$\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0016\u0012\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00170\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010.0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u0002020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00103\u001a\b\u0012\u0004\u0012\u0002040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u00105\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000208070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010@\u001a\u00020A8F¢\u0006\u0006\u001a\u0004\b@\u0010BR\u0011\u0010C\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020A8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010BR\u0011\u0010H\u001a\u00020I8F¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0015\u0010L\u001a\u0006\u0012\u0002\b\u00030\u00068F¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0017\u0010O\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\bP\u0010NR\u001f\u0010Ð\u0001\u001a\u00020A*\u0006\u0012\u0002\b\u00030\u00068BX\u0082\u0004¢\u0006\b\u001a\u0006\bÑ\u0001\u0010Ò\u0001R\u001b\u0010\u0086\u0002\u001a\u00020A*\u00020(8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0087\u0002\u0010\u0088\u0002¨\u0006ñ\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder;", Argument.Delimiters.none, "graphs", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "lastNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "exitTargetsForReturn", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionExitNode;", "enterToLocalClassesMembers", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;", "nonDirectJumps", "Lorg/jetbrains/kotlin/fir/util/ListMultimap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/JumpNode;", "argumentListSplitNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SplitPostponedLambdasNode;", "postponedAnonymousFunctionNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PostponedLambdaExitNode;", "anonymousFunctionCaptureNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionCaptureNode;", "postponedLambdaExits", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder$PostponedLambdas;", "loopConditionEnterNodes", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;", "loopExitNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;", "whenExitNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenExitNode;", "tryExitNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionExitNode;", "catchNodes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseEnterNode;", "catchBlocksInProgress", "finallyEnterNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;", "finallyBlocksInProgress", "finallyBlocksInProgressSet", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "exitFunctionCallArgumentsNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;", "exitSafeCallNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitSafeCallNode;", "exitElvisExpressionNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;", "elvisRhsEnterNodes", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisRhsEnterNode;", "equalityOperatorCallLhsExitNodes", "notCompletedFunctionCalls", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Ljava/util/Map;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/util/ListMultimap;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Ljava/util/Map;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Ljava/util/Map;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;)V", "()V", "createSnapshot", "copier", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphCopier;", "createSnapshot$org_jetbrains_kotlin_resolve", "isTopLevel", Argument.Delimiters.none, "()Z", "currentGraph", "getCurrentGraph", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "bodyBuildingMode", "getBodyBuildingMode", "levelCounter", Argument.Delimiters.none, "getLevelCounter", "()I", "lastNode", "getLastNode", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "lastNodeOrNull", "getLastNodeOrNull", "withinFinallyBlock", "element", "returnExpressionsOfAnonymousFunction", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FirAnonymousFunctionReturnExpressionInfo;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "enterGraph", "EnterNode", "T", "E", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GraphEnterNodeMarker;", "ExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GraphExitNodeMarker;", "fir", ModuleXmlParser.NAME, Argument.Delimiters.none, "kind", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph$Kind;", "nodes", "Lkotlin/Function1;", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/String;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph$Kind;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "popGraph", "exitGraph", "enterFunction", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalFunctionDeclarationNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionEnterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "exitFunction", "enterAnonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionExpressionNode;", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "enterAnonymousFunction", "anonymousFunction", "exitAnonymousFunction", "Lkotlin/Triple;", "splitDataFlowForPostponedLambdas", Argument.Delimiters.none, "lambdas", Argument.Delimiters.none, "jumpDataFlowFromPostponedLambdas", "symbol", "unifyDataFlowFromPostponedLambdas", "node", "callCompleted", "mergeDataFlowFromPostponedLambdas", "enterFile", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileEnterNode;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "buildGraph", "exitFile", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileExitNode;", "forEachGraphOwner", "block", "Lorg/jetbrains/kotlin/fir/declarations/FirControlFlowGraphOwner;", "addEdgeIfLocalClassMember", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "enterNode", "enterClass", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassEnterNode;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "exitClass", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassExitNode;", "exitAnonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectExpressionExitNode;", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "enterScript", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptEnterNode;", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "exitScript", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptExitNode;", "enterCodeFragment", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentEnterNode;", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "exitCodeFragment", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentExitNode;", "enterValueParameter", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterValueParameterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterDefaultArgumentsNode;", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "exitValueParameter", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitDefaultArgumentsNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitValueParameterNode;", "enterBlock", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockEnterNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "exitBlock", "enterProperty", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerEnterNode;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "exitProperty", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;", "enterField", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerEnterNode;", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "exitField", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerExitNode;", "enterDelegateExpression", "exitDelegateExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegateExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "exitTypeOperatorCall", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TypeOperatorCallNode;", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "exitComparisonExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ComparisonExpressionNode;", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "exitEqualityOperatorLhs", "exitEqualityOperatorCall", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EqualityOperatorCallNode;", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "enterJump", "jump", "Lorg/jetbrains/kotlin/fir/expressions/FirJump;", "exitJump", "returnPathIsBackwards", "getReturnPathIsBackwards", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Z", "enterWhenExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenEnterNode;", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "exitWhenSubjectExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSubjectExpressionExitNode;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "enterWhenBranchCondition", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionEnterNode;", "whenBranch", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "exitWhenBranchCondition", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultEnterNode;", "exitWhenBranchResult", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultExitNode;", "exitWhenExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSyntheticElseBranchNode;", "enterWhileLoop", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopEnterNode;", "loop", "exitWhileLoopCondition", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockEnterNode;", "exitWhileLoop", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockExitNode;", "enterDoWhileLoop", "enterDoWhileLoopCondition", "exitDoWhileLoop", "enterBooleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "booleanOperatorExpression", "exitLeftBooleanOperatorExpressionArgument", "exitBooleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;", "enterTryExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockEnterNode;", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "exitTryMainBlock", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockExitNode;", "enterCatchClause", "catch", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "exitCatchClause", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseExitNode;", "enterFinallyBlock", "exitFinallyBlock", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockExitNode;", "allNormalInputsAreDead", "getAllNormalInputsAreDead", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;)Z", "addReturnEdges", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", Argument.Delimiters.none, "minLevel", "exitTryExpression", "levelOfNextExceptionCatchingGraph", "completeFunctionCall", "exitQualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "exitSmartCastExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SmartCastExpressionExitNode;", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "exitResolvedQualifierNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ResolvedQualifierNode;", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "enterCall", "enterCallArguments", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsEnterNode;", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "anonymousFunctions", "exitCallArguments", "exitCallExplicitReceiver", "enterFunctionCall", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallEnterNode;", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "exitFunctionCall", "exitDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegatedConstructorCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "exitStringConcatenationCall", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StringConcatenationCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "exitLiteralExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LiteralExpressionNode;", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "enterVariableDeclaration", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationEnterNode;", "variable", "exitVariableDeclaration", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "exitVariableAssignment", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "assignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "exitThrowExceptionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ThrowExceptionNode;", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "exitCheckNotNullCall", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CheckNotNullCallNode;", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "enterFakeExpression", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FakeExpressionEnterNode;", "exitFakeExpression", "exitCallableReference", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CallableReferenceNode;", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "exitGetClassCall", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GetClassCallNode;", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "enterInitBlock", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockEnterNode;", "initBlock", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "exitInitBlock", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockExitNode;", "enterSafeCall", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterSafeCallNode;", "safeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "exitSafeCall", "enterElvis", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "exitElvisLhs", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsIsNotNullNode;", "exitElvis", "lhsIsNotNull", "reset", "addNewSimpleNode", "isDead", "addNonSuccessfullyTerminatingNode", "popAndAddEdge", "to", "preferredKind", "addEdge", "from", "propagateDeadness", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "addEdgeToSubGraph", "addBackEdge", "propagateDeadnessForward", "PostponedLambdas", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowGraphBuilder {
    private final Map<FirFunctionSymbol<?>, AnonymousFunctionCaptureNode> anonymousFunctionCaptureNodes;
    private final Stack<SplitPostponedLambdasNode> argumentListSplitNodes;
    private final Stack<CatchClauseEnterNode> catchBlocksInProgress;
    private final Stack<List<CatchClauseEnterNode>> catchNodes;
    private final Stack<ElvisRhsEnterNode> elvisRhsEnterNodes;
    private final Map<FirBasedSymbol<?>, Pair<CFGNode<?>, EdgeKind>> enterToLocalClassesMembers;
    private final Stack<CFGNode<?>> equalityOperatorCallLhsExitNodes;
    private final Stack<ElvisExitNode> exitElvisExpressionNodes;
    private final Stack<FunctionCallArgumentsExitNode> exitFunctionCallArgumentsNodes;
    private final Stack<ExitSafeCallNode> exitSafeCallNodes;
    private final Map<FirFunctionSymbol<?>, FunctionExitNode> exitTargetsForReturn;
    private final Stack<FinallyBlockEnterNode> finallyBlocksInProgress;
    private final Set<FirElement> finallyBlocksInProgressSet;
    private final Stack<FinallyBlockEnterNode> finallyEnterNodes;
    private final Stack<ControlFlowGraph> graphs;
    private final Stack<CFGNode<?>> lastNodes;
    private final Map<FirLoop, LoopConditionEnterNode> loopConditionEnterNodes;
    private final Map<FirLoop, LoopExitNode> loopExitNodes;
    private final ListMultimap<CFGNode<?>, JumpNode> nonDirectJumps;
    private final Stack<List<FunctionCallExitNode>> notCompletedFunctionCalls;
    private final Map<FirFunctionSymbol<?>, Pair<CFGNode<?>, PostponedLambdaExitNode>> postponedAnonymousFunctionNodes;
    private final Stack<PostponedLambdas> postponedLambdaExits;
    private final Stack<TryExpressionExitNode> tryExitNodes;
    private final Stack<WhenExitNode> whenExitNodes;

    public ControlFlowGraphBuilder() {
        this(StackKt.stackOf(new ControlFlowGraph[0]), StackKt.stackOf(new CFGNode[0]), new LinkedHashMap(), new LinkedHashMap(), MultimapKt.listMultimapOf(), StackKt.stackOf(new SplitPostponedLambdasNode[0]), new LinkedHashMap(), new LinkedHashMap(), StackKt.stackOf(new PostponedLambdas[0]), new LinkedHashMap(), new LinkedHashMap(), StackKt.stackOf(new WhenExitNode[0]), StackKt.stackOf(new TryExpressionExitNode[0]), StackKt.stackOf(new List[0]), StackKt.stackOf(new CatchClauseEnterNode[0]), StackKt.stackOf(new FinallyBlockEnterNode[0]), StackKt.stackOf(new FinallyBlockEnterNode[0]), new LinkedHashSet(), StackKt.stackOf(new FunctionCallArgumentsExitNode[0]), StackKt.stackOf(new ExitSafeCallNode[0]), StackKt.stackOf(new ElvisExitNode[0]), StackKt.stackOf(new ElvisRhsEnterNode[0]), StackKt.stackOf(new CFGNode[0]), StackKt.stackOf(new List[0]));
    }

    public static FunctionCallArgumentsExitNode a(ControlFlowGraphCopier controlFlowGraphCopier, FunctionCallArgumentsExitNode functionCallArgumentsExitNode) {
        if (functionCallArgumentsExitNode != null) {
            return (FunctionCallArgumentsExitNode) controlFlowGraphCopier.get(functionCallArgumentsExitNode);
        }
        return null;
    }

    private final void addBackEdge(CFGNode<?> from, CFGNode<?> to, boolean isDead, EdgeLabel label) {
        CFGNode.INSTANCE.addEdge(from, to, (isDead || from.getIsDead() || to.getIsDead()) ? EdgeKind.DeadCfgBackward : EdgeKind.CfgBackward, false, label);
    }

    public static /* synthetic */ void addBackEdge$default(ControlFlowGraphBuilder controlFlowGraphBuilder, CFGNode cFGNode, CFGNode cFGNode2, boolean z, EdgeLabel edgeLabel, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            edgeLabel = NormalPath.INSTANCE;
        }
        controlFlowGraphBuilder.addBackEdge(cFGNode, cFGNode2, z, edgeLabel);
    }

    private final void addEdge(CFGNode<?> from, CFGNode<?> to, boolean propagateDeadness, boolean isDead, EdgeKind preferredKind, EdgeLabel label) {
        if (isDead || from.getIsDead() || to.getIsDead()) {
            preferredKind = preferredKind.toDead();
        }
        CFGNode.INSTANCE.addEdge(from, to, preferredKind, propagateDeadness, label);
    }

    public static /* synthetic */ void addEdge$default(ControlFlowGraphBuilder controlFlowGraphBuilder, CFGNode cFGNode, CFGNode cFGNode2, boolean z, boolean z2, EdgeKind edgeKind, EdgeLabel edgeLabel, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            z2 = false;
        }
        boolean z4 = z2;
        if ((i & 16) != 0) {
            edgeKind = EdgeKind.Forward;
        }
        EdgeKind edgeKind2 = edgeKind;
        if ((i & 32) != 0) {
            edgeLabel = NormalPath.INSTANCE;
        }
        controlFlowGraphBuilder.addEdge(cFGNode, cFGNode2, z3, z4, edgeKind2, edgeLabel);
    }

    private final <E extends FirDeclaration> void addEdgeIfLocalClassMember(CFGNode<? extends E> enterNode) {
        Pair<CFGNode<?>, EdgeKind> pairRemove = this.enterToLocalClassesMembers.remove(((FirDeclaration) enterNode.getFir()).getSymbol());
        if (pairRemove == null) {
            return;
        }
        addEdge$default(this, (CFGNode) pairRemove.component1(), enterNode, false, false, (EdgeKind) pairRemove.component2(), null, 44, null);
    }

    private final void addEdgeToSubGraph(CFGNode<?> from, CFGNode<?> to) {
        boolean isDead = to.getIsDead();
        boolean z = isDead || from.getIsDead();
        CFGNode.Companion.addEdge$default(CFGNode.INSTANCE, from, to, z ? EdgeKind.DeadForward : EdgeKind.CfgForward, true, null, 16, null);
        if (!z || isDead) {
            return;
        }
        propagateDeadnessForward(to);
    }

    private final void addNewSimpleNode(CFGNode<?> node, boolean isDead) {
        addEdge$default(this, this.lastNodes.pop(), node, false, false, isDead ? EdgeKind.DeadForward : EdgeKind.Forward, null, 44, null);
        this.lastNodes.push(node);
    }

    public static /* synthetic */ void addNewSimpleNode$default(ControlFlowGraphBuilder controlFlowGraphBuilder, CFGNode cFGNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        controlFlowGraphBuilder.addNewSimpleNode(cFGNode, z);
    }

    private final void addNonSuccessfullyTerminatingNode(CFGNode<?> node) {
        popAndAddEdge$default(this, node, null, 2, null);
        StubNode stubNodeCreateStubNode = ControlFlowGraphNodeBuilderKt.createStubNode(this);
        addEdge$default(this, node, stubNodeCreateStubNode, false, false, null, null, 60, null);
        this.lastNodes.push(stubNodeCreateStubNode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends CFGNode<?> & EdgeLabel> void addReturnEdges(CFGNode<?> cFGNode, Iterable<? extends T> iterable, int i) {
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            CFGNode cFGNode2 = (CFGNode) it.next();
            if (cFGNode2.getLevel() >= i && this.nonDirectJumps.contains(cFGNode2)) {
                if (getReturnPathIsBackwards(cFGNode2)) {
                    addBackEdge$default(this, cFGNode, cFGNode2, false, (EdgeLabel) cFGNode2, 4, null);
                } else {
                    addEdge$default(this, cFGNode, cFGNode2, false, false, null, (EdgeLabel) cFGNode2, 24, null);
                }
            }
        }
    }

    public static List b(ControlFlowGraphCopier controlFlowGraphCopier, List list) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((FunctionCallExitNode) controlFlowGraphCopier.get((FunctionCallExitNode) it.next()));
        }
        return arrayList;
    }

    public static List c(ControlFlowGraphCopier controlFlowGraphCopier, List list) {
        list.getClass();
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((CatchClauseEnterNode) controlFlowGraphCopier.get((CatchClauseEnterNode) it.next()));
        }
        return arrayList;
    }

    private final boolean completeFunctionCall(FunctionCallExitNode node) {
        if (!ControlFlowGraphBuilderKt.getHasNothingType(node.getFir())) {
            return false;
        }
        StubNode stubNode = new StubNode(node.getOwner(), node.getLevel());
        List<CFGNode<?>> followingNodes = node.getFollowingNodes();
        ArrayList<Pair> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(followingNodes, 10));
        for (CFGNode<?> cFGNode : followingNodes) {
            arrayList.add(TuplesKt.to(cFGNode, node.edgeTo(cFGNode)));
        }
        CFGNode.Companion companion = CFGNode.INSTANCE;
        companion.removeAllOutgoingEdges(node);
        CFGNode.Companion.addEdge$default(companion, node, stubNode, EdgeKind.DeadForward, false, null, 16, null);
        for (Pair pair : arrayList) {
            CFGNode<?> cFGNode2 = (CFGNode) pair.component1();
            Edge edge = (Edge) pair.component2();
            CFGNode.INSTANCE.addEdge(stubNode, cFGNode2, edge.getKind().getIsBack() ? EdgeKind.DeadCfgBackward : EdgeKind.DeadForward, false, edge.getLabel());
            cFGNode2.updateDeadStatus();
            propagateDeadnessForward(cFGNode2);
        }
        return true;
    }

    public static PostponedLambdas d(ControlFlowGraphCopier controlFlowGraphCopier, PostponedLambdas postponedLambdas) {
        postponedLambdas.getClass();
        List<Pair<CFGNode<?>, EdgeKind>> exits = postponedLambdas.getExits();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = exits.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            arrayList.add(new Pair(controlFlowGraphCopier.get((CFGNode) pair.getFirst()), pair.getSecond()));
        }
        return new PostponedLambdas(postponedLambdas.getLambdas(), arrayList);
    }

    public static SplitPostponedLambdasNode e(ControlFlowGraphCopier controlFlowGraphCopier, SplitPostponedLambdasNode splitPostponedLambdasNode) {
        if (splitPostponedLambdasNode != null) {
            return (SplitPostponedLambdasNode) controlFlowGraphCopier.get(splitPostponedLambdasNode);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void enterCall$default(ControlFlowGraphBuilder controlFlowGraphBuilder, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            set = SetsKt.emptySet();
        }
        controlFlowGraphBuilder.enterCall(set);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x003e  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final int exitClass$computeDelegationLevel(FirConstructor firConstructor, Map<FirConstructor, Integer> map, Map<FirConstructor, FirConstructor> map2) {
        int i;
        FirReference calleeReference;
        FirConstructorSymbol resolvedConstructorSymbol;
        FirConstructor firConstructor2;
        Integer numValueOf = map.get(firConstructor);
        if (numValueOf == null) {
            map.put(firConstructor, -1);
            FirDelegatedConstructorCall delegatedConstructor = firConstructor.getDelegatedConstructor();
            if (delegatedConstructor == null) {
                i = 0;
            } else {
                if (!delegatedConstructor.getIsThis()) {
                    delegatedConstructor = null;
                }
                if (delegatedConstructor == null || (calleeReference = delegatedConstructor.getCalleeReference()) == null || (resolvedConstructorSymbol = FirReferenceUtilsKt.toResolvedConstructorSymbol(calleeReference, true)) == null || (firConstructor2 = (FirConstructor) resolvedConstructorSymbol.getFir()) == null) {
                    i = 0;
                } else {
                    int iExitClass$computeDelegationLevel = exitClass$computeDelegationLevel(firConstructor2, map, map2);
                    if (iExitClass$computeDelegationLevel != -1) {
                        map2.put(firConstructor, firConstructor2);
                    }
                    i = iExitClass$computeDelegationLevel + 1;
                }
            }
            numValueOf = Integer.valueOf(i);
            map.put(firConstructor, numValueOf);
        }
        return numValueOf.intValue();
    }

    private final boolean getAllNormalInputsAreDead(FinallyBlockEnterNode finallyBlockEnterNode) {
        List<CFGNode<?>> previousNodes = finallyBlockEnterNode.getPreviousNodes();
        if ((previousNodes instanceof Collection) && previousNodes.isEmpty()) {
            return true;
        }
        Iterator<T> it = previousNodes.iterator();
        while (it.hasNext()) {
            Edge edgeEdgeFrom = finallyBlockEnterNode.edgeFrom((CFGNode) it.next());
            if (!edgeEdgeFrom.getKind().getIsDead() && Intrinsics.areEqual(edgeEdgeFrom.getLabel(), NormalPath.INSTANCE)) {
                return false;
            }
        }
        return true;
    }

    private final boolean getBodyBuildingMode() {
        return StackKt.isNotEmpty(this.graphs) && getCurrentGraph().getKind() != ControlFlowGraph.Kind.Class;
    }

    private final boolean getReturnPathIsBackwards(CFGNode<?> cFGNode) {
        if (!(cFGNode instanceof LoopConditionEnterNode)) {
            return false;
        }
        LoopConditionEnterNode loopConditionEnterNode = (LoopConditionEnterNode) cFGNode;
        if (!(loopConditionEnterNode.getLoop() instanceof FirDoWhileLoop)) {
            return true;
        }
        List<CFGNode<?>> previousNodes = loopConditionEnterNode.getPreviousNodes();
        if ((previousNodes instanceof Collection) && previousNodes.isEmpty()) {
            return false;
        }
        Iterator<T> it = previousNodes.iterator();
        while (it.hasNext()) {
            if (((CFGNode) it.next()) instanceof LoopBlockExitNode) {
                return true;
            }
        }
        return false;
    }

    private final void jumpDataFlowFromPostponedLambdas(FirFunctionSymbol<?> symbol) {
        List<Pair<CFGNode<?>, EdgeKind>> exits = this.postponedLambdaExits.pop().getExits();
        if (exits.isEmpty()) {
            return;
        }
        for (PostponedLambdas postponedLambdas : this.postponedLambdaExits.all()) {
            Set<FirFunctionSymbol<?>> setComponent1 = postponedLambdas.component1();
            List<Pair<CFGNode<?>, EdgeKind>> listComponent2 = postponedLambdas.component2();
            if (setComponent1.contains(symbol)) {
                listComponent2.addAll(exits);
                return;
            }
        }
    }

    private final int levelOfNextExceptionCatchingGraph() {
        for (ControlFlowGraph controlFlowGraph : this.graphs.all()) {
            if (controlFlowGraph.getKind() != ControlFlowGraph.Kind.AnonymousFunctionCalledInPlace) {
                return controlFlowGraph.getExitNode().getLevel();
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
        return 0;
    }

    private final void mergeDataFlowFromPostponedLambdas(CFGNode<?> node, boolean callCompleted) {
        List<Pair<CFGNode<?>, EdgeKind>> exits = this.postponedLambdaExits.pop().getExits();
        if (exits.isEmpty()) {
            return;
        }
        PostponedLambdas postponedLambdas = (PostponedLambdas) StackKt.topOrNull(this.postponedLambdaExits);
        List<Pair<CFGNode<?>, EdgeKind>> exits2 = callCompleted ? null : postponedLambdas != null ? postponedLambdas.getExits() : null;
        if (exits2 == null) {
            for (Pair<CFGNode<?>, EdgeKind> pair : exits) {
                addEdge$default(this, (CFGNode) pair.component1(), node, false, false, (EdgeKind) pair.component2(), PostponedPath.INSTANCE, 8, null);
            }
            return;
        }
        node.updateDeadStatus();
        List<Pair<CFGNode<?>, EdgeKind>> list = exits2;
        MergePostponedLambdaExitsNode mergePostponedLambdaExitsNodeCreateMergePostponedLambdaExitsNode = ControlFlowGraphNodeBuilderKt.createMergePostponedLambdaExitsNode(this, node.getFir());
        addEdge$default(this, node, mergePostponedLambdaExitsNodeCreateMergePostponedLambdaExitsNode, false, false, null, null, 60, null);
        for (Pair<CFGNode<?>, EdgeKind> pair2 : exits) {
            CFGNode cFGNode = (CFGNode) pair2.component1();
            if (((EdgeKind) pair2.component2()).getUsedInCfa()) {
                addEdge$default(this, cFGNode, node, false, false, EdgeKind.CfgForward, null, 40, null);
            }
            addEdge$default(this, cFGNode, mergePostponedLambdaExitsNodeCreateMergePostponedLambdaExitsNode, false, false, EdgeKind.DfgForward, null, 40, null);
        }
        list.add(TuplesKt.to(mergePostponedLambdaExitsNodeCreateMergePostponedLambdaExitsNode, EdgeKind.DfgForward));
    }

    private final void popAndAddEdge(CFGNode<?> to, EdgeKind preferredKind) {
        addEdge$default(this, this.lastNodes.pop(), to, false, false, preferredKind, null, 44, null);
    }

    public static /* synthetic */ void popAndAddEdge$default(ControlFlowGraphBuilder controlFlowGraphBuilder, CFGNode cFGNode, EdgeKind edgeKind, int i, Object obj) {
        if ((i & 2) != 0) {
            edgeKind = EdgeKind.Forward;
        }
        controlFlowGraphBuilder.popAndAddEdge(cFGNode, edgeKind);
    }

    private final ControlFlowGraph popGraph() {
        ControlFlowGraph controlFlowGraphPop = this.graphs.pop();
        controlFlowGraphPop.complete();
        return controlFlowGraphPop;
    }

    private final void propagateDeadnessForward(CFGNode<?> node) {
        if (node.getIsDead()) {
            for (CFGNode<?> cFGNode : node.getFollowingNodes()) {
                EdgeKind kind = node.edgeTo(cFGNode).getKind();
                if (CFGNode.INSTANCE.killEdge(node, cFGNode, false) && !kind.getIsBack() && kind.getUsedInCfa()) {
                    cFGNode.updateDeadStatus();
                    propagateDeadnessForward(cFGNode);
                }
            }
        }
    }

    private static final FirAnonymousFunctionReturnExpressionInfo returnExpressionsOfAnonymousFunction$returnExpression(CFGNode<?> cFGNode, FirAnonymousFunction firAnonymousFunction) {
        FirExpression result;
        KtSourceElement source;
        if (!(cFGNode instanceof BlockExitNode)) {
            if (cFGNode instanceof JumpNode) {
                JumpNode jumpNode = (JumpNode) cFGNode;
                FirJump<?> fir = jumpNode.getFir();
                FirReturnExpression firReturnExpression = fir instanceof FirReturnExpression ? (FirReturnExpression) fir : null;
                if (firReturnExpression != null) {
                    if (!Intrinsics.areEqual(((FirFunction) firReturnExpression.getTarget().getLabeledElement()).getSymbol(), firAnonymousFunction.getSymbol())) {
                        firReturnExpression = null;
                    }
                    if (firReturnExpression != null && (result = firReturnExpression.getResult()) != null) {
                        return new FirAnonymousFunctionReturnExpressionInfo(result, true, jumpNode.getFir());
                    }
                }
            }
            return null;
        }
        if (!firAnonymousFunction.getIsLambda()) {
            if (firAnonymousFunction.getBody() instanceof FirSingleExpressionBlock) {
                return null;
            }
            FirStub firStub = FirStub.INSTANCE;
            BlockExitNode blockExitNode = (BlockExitNode) cFGNode;
            List<CFGNode<?>> previousNodes = blockExitNode.getPreviousNodes();
            if (!(previousNodes instanceof Collection) || !previousNodes.isEmpty()) {
                Iterator<T> it = previousNodes.iterator();
                while (it.hasNext()) {
                    if (!(((CFGNode) it.next()) instanceof StubNode)) {
                        firStub = null;
                        break;
                    }
                }
            }
            if (firStub != null) {
                return new FirAnonymousFunctionReturnExpressionInfo(firStub, false, blockExitNode.getFir());
            }
            return null;
        }
        FirStatement firStatementLastStatement = ControlFlowGraphBuilderKt.lastStatement(firAnonymousFunction);
        if (firStatementLastStatement instanceof FirReturnExpression) {
            FirReturnExpression firReturnExpression2 = (FirReturnExpression) firStatementLastStatement;
            if (Intrinsics.areEqual(((FirFunction) firReturnExpression2.getTarget().getLabeledElement()).getSymbol(), firAnonymousFunction.getSymbol())) {
                KtSourceElement source2 = firReturnExpression2.getSource();
                if (!Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitReturn.FromLastStatement.INSTANCE)) {
                    return null;
                }
            }
        }
        FirExpression firExpressionBuild = firStatementLastStatement instanceof FirExpression ? (FirExpression) firStatementLastStatement : null;
        if (firExpressionBuild == null) {
            FirUnitExpressionBuilder firUnitExpressionBuilder = new FirUnitExpressionBuilder();
            BlockExitNode blockExitNode2 = (BlockExitNode) cFGNode;
            FirStatement firStatement = (FirStatement) CollectionsKt.lastOrNull(blockExitNode2.getFir().getStatements());
            if (firStatement == null || (source = firStatement.getSource()) == null) {
                source = blockExitNode2.getFir().getSource();
            }
            firUnitExpressionBuilder.setSource(source);
            firExpressionBuild = firUnitExpressionBuilder.mo288build();
        }
        return new FirAnonymousFunctionReturnExpressionInfo(firExpressionBuild, false, ((BlockExitNode) cFGNode).getFir());
    }

    private final void splitDataFlowForPostponedLambdas(Set<? extends FirFunctionSymbol<?>> lambdas) {
        List list = null;
        this.postponedLambdaExits.push(new PostponedLambdas(lambdas, list, 2, list));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void splitDataFlowForPostponedLambdas$default(ControlFlowGraphBuilder controlFlowGraphBuilder, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            set = SetsKt.emptySet();
        }
        controlFlowGraphBuilder.splitDataFlowForPostponedLambdas(set);
    }

    private final void unifyDataFlowFromPostponedLambdas(CFGNode<?> node, boolean callCompleted) {
        CFGNode cFGNode;
        List<Pair<CFGNode<?>, EdgeKind>> exits = this.postponedLambdaExits.pop().getExits();
        if (exits.isEmpty()) {
            return;
        }
        PostponedLambdas postponedLambdas = (PostponedLambdas) StackKt.topOrNull(this.postponedLambdaExits);
        List<Pair<CFGNode<?>, EdgeKind>> exits2 = !callCompleted ? postponedLambdas != null ? postponedLambdas.getExits() : null : null;
        if (exits2 == null) {
            for (Pair<CFGNode<?>, EdgeKind> pair : exits) {
                CFGNode cFGNode2 = (CFGNode) pair.component1();
                EdgeKind edgeKind = (EdgeKind) pair.component2();
                if (edgeKind.getUsedInCfa() || !cFGNode2.getIsDead()) {
                    addEdge$default(this, cFGNode2, node, false, false, edgeKind, PostponedPath.INSTANCE, 12, null);
                }
            }
            return;
        }
        for (Pair<CFGNode<?>, EdgeKind> pair2 : exits) {
            CFGNode cFGNode3 = (CFGNode) pair2.component1();
            if (((EdgeKind) pair2.component2()).getUsedInCfa()) {
                cFGNode = cFGNode3;
                addEdge$default(this, cFGNode, node, false, false, EdgeKind.CfgForward, null, 44, null);
            } else {
                cFGNode = cFGNode3;
            }
            exits2.add(TuplesKt.to(cFGNode, EdgeKind.DfgForward));
        }
    }

    public final ControlFlowGraphBuilder createSnapshot$org_jetbrains_kotlin_resolve(final ControlFlowGraphCopier copier) {
        copier.getClass();
        Stack<R> stackCreateSnapshot = this.graphs.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$1(copier));
        Stack<R> stackCreateSnapshot2 = this.lastNodes.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$2(copier));
        Map<FirFunctionSymbol<?>, FunctionExitNode> map = this.exitTargetsForReturn;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), (FunctionExitNode) copier.get((CFGNode) entry.getValue()));
        }
        Map<FirBasedSymbol<?>, Pair<CFGNode<?>, EdgeKind>> map2 = this.enterToLocalClassesMembers;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        Iterator<T> it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            Object key = entry2.getKey();
            Pair pair = (Pair) entry2.getValue();
            linkedHashMap2.put(key, new Pair(copier.get((CFGNode) pair.getFirst()), pair.getSecond()));
        }
        ListMultimap listMultimapListMultimapOf = MultimapKt.listMultimapOf();
        Iterator it3 = this.nonDirectJumps.iterator();
        while (it3.hasNext()) {
            Map.Entry entry3 = (Map.Entry) it3.next();
            CFGNode cFGNode = (CFGNode) entry3.getKey();
            List list = (List) entry3.getValue();
            CFGNode cFGNode2 = copier.get(cFGNode);
            List list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it4 = list2.iterator();
            while (it4.hasNext()) {
                arrayList.add((JumpNode) copier.get((JumpNode) it4.next()));
            }
            listMultimapListMultimapOf.putAll(cFGNode2, arrayList);
        }
        Unit unit = Unit.INSTANCE;
        Stack<R> stackCreateSnapshot3 = this.argumentListSplitNodes.createSnapshot(new Function1() { // from class: rw2
            public final Object invoke(Object obj) {
                return ControlFlowGraphBuilder.e(copier, (SplitPostponedLambdasNode) obj);
            }
        });
        Map<FirFunctionSymbol<?>, Pair<CFGNode<?>, PostponedLambdaExitNode>> map3 = this.postponedAnonymousFunctionNodes;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        Iterator<T> it5 = map3.entrySet().iterator();
        while (it5.hasNext()) {
            Map.Entry entry4 = (Map.Entry) it5.next();
            Object key2 = entry4.getKey();
            Pair pair2 = (Pair) entry4.getValue();
            CFGNode cFGNode3 = copier.get((CFGNode) pair2.getFirst());
            PostponedLambdaExitNode postponedLambdaExitNode = (PostponedLambdaExitNode) pair2.getSecond();
            linkedHashMap3.put(key2, new Pair(cFGNode3, postponedLambdaExitNode != null ? (PostponedLambdaExitNode) copier.get(postponedLambdaExitNode) : null));
        }
        Map<FirFunctionSymbol<?>, AnonymousFunctionCaptureNode> map4 = this.anonymousFunctionCaptureNodes;
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        Iterator<T> it6 = map4.entrySet().iterator();
        while (it6.hasNext()) {
            Map.Entry entry5 = (Map.Entry) it6.next();
            linkedHashMap4.put(entry5.getKey(), (AnonymousFunctionCaptureNode) copier.get((CFGNode) entry5.getValue()));
        }
        Stack<R> stackCreateSnapshot4 = this.postponedLambdaExits.createSnapshot(new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.a
            public final Object invoke(Object obj) {
                return ControlFlowGraphBuilder.d(copier, (ControlFlowGraphBuilder.PostponedLambdas) obj);
            }
        });
        Map<FirLoop, LoopConditionEnterNode> map5 = this.loopConditionEnterNodes;
        LinkedHashMap linkedHashMap5 = new LinkedHashMap();
        Iterator<T> it7 = map5.entrySet().iterator();
        while (it7.hasNext()) {
            Map.Entry entry6 = (Map.Entry) it7.next();
            linkedHashMap5.put(entry6.getKey(), (LoopConditionEnterNode) copier.get((CFGNode) entry6.getValue()));
        }
        Map<FirLoop, LoopExitNode> map6 = this.loopExitNodes;
        LinkedHashMap linkedHashMap6 = new LinkedHashMap();
        Iterator<T> it8 = map6.entrySet().iterator();
        while (it8.hasNext()) {
            Map.Entry entry7 = (Map.Entry) it8.next();
            linkedHashMap6.put(entry7.getKey(), (LoopExitNode) copier.get((CFGNode) entry7.getValue()));
        }
        return new ControlFlowGraphBuilder(stackCreateSnapshot, stackCreateSnapshot2, linkedHashMap, linkedHashMap2, listMultimapListMultimapOf, stackCreateSnapshot3, linkedHashMap3, linkedHashMap4, stackCreateSnapshot4, linkedHashMap5, linkedHashMap6, this.whenExitNodes.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$12(copier)), this.tryExitNodes.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$13(copier)), this.catchNodes.createSnapshot(new Function1() { // from class: sw2
            public final Object invoke(Object obj) {
                return ControlFlowGraphBuilder.c(copier, (List) obj);
            }
        }), this.catchBlocksInProgress.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$15(copier)), this.finallyEnterNodes.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$16(copier)), this.finallyBlocksInProgress.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$17(copier)), CollectionsKt.toMutableSet(this.finallyBlocksInProgressSet), this.exitFunctionCallArgumentsNodes.createSnapshot(new Function1() { // from class: tw2
            public final Object invoke(Object obj) {
                return ControlFlowGraphBuilder.a(copier, (FunctionCallArgumentsExitNode) obj);
            }
        }), this.exitSafeCallNodes.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$19(copier)), this.exitElvisExpressionNodes.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$20(copier)), this.elvisRhsEnterNodes.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$21(copier)), this.equalityOperatorCallLhsExitNodes.createSnapshot(new ControlFlowGraphBuilder$createSnapshot$22(copier)), this.notCompletedFunctionCalls.createSnapshot(new Function1() { // from class: uw2
            public final Object invoke(Object obj) {
                return ControlFlowGraphBuilder.b(copier, (List) obj);
            }
        }));
    }

    public final FunctionEnterNode enterAnonymousFunction(FirAnonymousFunction anonymousFunction) {
        ControlFlowGraphBuilder controlFlowGraphBuilder;
        anonymousFunction.getClass();
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(anonymousFunction, SpecialNames.ANONYMOUS_STRING, EventOccurrencesRangeKt.isInPlace(anonymousFunction.getInvocationKind()) ? ControlFlowGraph.Kind.AnonymousFunctionCalledInPlace : ControlFlowGraph.Kind.AnonymousFunction);
        this.graphs.push(controlFlowGraph);
        FunctionEnterNode functionEnterNodeCreateFunctionEnterNode = ControlFlowGraphNodeBuilderKt.createFunctionEnterNode(this, anonymousFunction);
        FunctionExitNode functionExitNodeCreateFunctionExitNode = ControlFlowGraphNodeBuilderKt.createFunctionExitNode(this, anonymousFunction);
        this.exitTargetsForReturn.put(anonymousFunction.getSymbol(), functionExitNodeCreateFunctionExitNode);
        Pair pair = TuplesKt.to(functionEnterNodeCreateFunctionEnterNode, functionExitNodeCreateFunctionExitNode);
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        FunctionEnterNode functionEnterNode = (FunctionEnterNode) cFGNode;
        AnonymousFunctionCaptureNode anonymousFunctionCaptureNode = this.anonymousFunctionCaptureNodes.get(anonymousFunction.getSymbol());
        if (anonymousFunctionCaptureNode != null) {
            controlFlowGraphBuilder = this;
            addEdge$default(controlFlowGraphBuilder, anonymousFunctionCaptureNode, functionEnterNode, false, false, EdgeKind.CfgForward, CapturedByValue.INSTANCE, 12, null);
        } else {
            controlFlowGraphBuilder = this;
        }
        addEdge$default(controlFlowGraphBuilder, (CFGNode) ((Pair) MapsKt.getValue(controlFlowGraphBuilder.postponedAnonymousFunctionNodes, anonymousFunction.getSymbol())).getFirst(), functionEnterNode, false, false, null, null, 60, null);
        return functionEnterNode;
    }

    public final Pair<AnonymousFunctionExpressionNode, AnonymousFunctionCaptureNode> enterAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression) {
        CFGNode cFGNode;
        anonymousFunctionExpression.getClass();
        FirAnonymousFunctionSymbol symbol = anonymousFunctionExpression.getAnonymousFunction().getSymbol();
        Pair<CFGNode<?>, PostponedLambdaExitNode> pair = this.postponedAnonymousFunctionNodes.get(symbol);
        if (pair == null || (cFGNode = (CFGNode) pair.getFirst()) == null) {
            AnonymousFunctionExpressionNode anonymousFunctionExpressionNodeCreateAnonymousFunctionExpressionNode = ControlFlowGraphNodeBuilderKt.createAnonymousFunctionExpressionNode(this, anonymousFunctionExpression);
            addNewSimpleNode$default(this, anonymousFunctionExpressionNodeCreateAnonymousFunctionExpressionNode, false, 2, null);
            this.postponedAnonymousFunctionNodes.put(symbol, TuplesKt.to(anonymousFunctionExpressionNodeCreateAnonymousFunctionExpressionNode, null));
            return TuplesKt.to(anonymousFunctionExpressionNodeCreateAnonymousFunctionExpressionNode, null);
        }
        AnonymousFunctionCaptureNode anonymousFunctionCaptureNodeCreateAnonymousFunctionCaptureNode = ControlFlowGraphNodeBuilderKt.createAnonymousFunctionCaptureNode(this, anonymousFunctionExpression);
        addNewSimpleNode$default(this, anonymousFunctionCaptureNodeCreateAnonymousFunctionCaptureNode, false, 2, null);
        this.anonymousFunctionCaptureNodes.put(symbol, anonymousFunctionCaptureNodeCreateAnonymousFunctionCaptureNode);
        PostponedLambdaExitNode postponedLambdaExitNodeCreatePostponedLambdaExitNode = ControlFlowGraphNodeBuilderKt.createPostponedLambdaExitNode(this, anonymousFunctionExpression);
        addEdge$default(this, cFGNode, postponedLambdaExitNodeCreatePostponedLambdaExitNode, false, false, null, null, 60, null);
        this.postponedAnonymousFunctionNodes.put(symbol, TuplesKt.to(cFGNode, postponedLambdaExitNodeCreatePostponedLambdaExitNode));
        this.postponedLambdaExits.top().getExits().add(TuplesKt.to(postponedLambdaExitNodeCreatePostponedLambdaExitNode, EdgeKind.Forward));
        return TuplesKt.to(null, anonymousFunctionCaptureNodeCreateAnonymousFunctionCaptureNode);
    }

    public final BlockEnterNode enterBlock(FirBlock block) {
        block.getClass();
        BlockEnterNode blockEnterNodeCreateBlockEnterNode = ControlFlowGraphNodeBuilderKt.createBlockEnterNode(this, block);
        addNewSimpleNode$default(this, blockEnterNodeCreateBlockEnterNode, false, 2, null);
        return blockEnterNodeCreateBlockEnterNode;
    }

    public final CFGNode<FirBooleanOperatorExpression> enterBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression) {
        booleanOperatorExpression.getClass();
        BooleanOperatorEnterNode booleanOperatorEnterNodeCreateBooleanOperatorEnterNode = ControlFlowGraphNodeBuilderKt.createBooleanOperatorEnterNode(this, booleanOperatorExpression);
        addNewSimpleNode$default(this, booleanOperatorEnterNodeCreateBooleanOperatorEnterNode, false, 2, null);
        return booleanOperatorEnterNodeCreateBooleanOperatorEnterNode;
    }

    public final void enterCall(Set<? extends FirFunctionSymbol<?>> lambdas) {
        lambdas.getClass();
        splitDataFlowForPostponedLambdas(lambdas);
    }

    public final FunctionCallArgumentsEnterNode enterCallArguments(FirStatement call, List<? extends FirAnonymousFunction> anonymousFunctions) {
        call.getClass();
        anonymousFunctions.getClass();
        if (anonymousFunctions.isEmpty()) {
            this.argumentListSplitNodes.push(null);
        } else {
            SplitPostponedLambdasNode splitPostponedLambdasNodeCreateSplitPostponedLambdasNode = ControlFlowGraphNodeBuilderKt.createSplitPostponedLambdasNode(this, call, anonymousFunctions);
            Map<FirFunctionSymbol<?>, Pair<CFGNode<?>, PostponedLambdaExitNode>> map = this.postponedAnonymousFunctionNodes;
            Iterator<T> it = anonymousFunctions.iterator();
            while (it.hasNext()) {
                Pair pair = TuplesKt.to(((FirAnonymousFunction) it.next()).getSymbol(), TuplesKt.to(splitPostponedLambdasNodeCreateSplitPostponedLambdasNode, null));
                map.put((FirFunctionSymbol<?>) pair.getFirst(), (Pair<CFGNode<?>, PostponedLambdaExitNode>) pair.getSecond());
            }
            this.argumentListSplitNodes.push(splitPostponedLambdasNodeCreateSplitPostponedLambdasNode);
        }
        if (!(call instanceof FirFunctionCall)) {
            this.exitFunctionCallArgumentsNodes.push(null);
            return null;
        }
        FirFunctionCall firFunctionCall = (FirFunctionCall) call;
        FunctionCallArgumentsEnterNode functionCallArgumentsEnterNodeCreateFunctionCallArgumentsEnterNode = ControlFlowGraphNodeBuilderKt.createFunctionCallArgumentsEnterNode(this, firFunctionCall);
        this.exitFunctionCallArgumentsNodes.push(ControlFlowGraphNodeBuilderKt.createFunctionCallArgumentsExitNode(this, firFunctionCall, functionCallArgumentsEnterNodeCreateFunctionCallArgumentsEnterNode));
        addNewSimpleNode$default(this, functionCallArgumentsEnterNodeCreateFunctionCallArgumentsEnterNode, false, 2, null);
        return functionCallArgumentsEnterNodeCreateFunctionCallArgumentsEnterNode;
    }

    public final CatchClauseEnterNode enterCatchClause(FirCatch firCatch) {
        ControlFlowGraphBuilder controlFlowGraphBuilder;
        firCatch.getClass();
        CatchClauseEnterNode catchClauseEnterNodePop = this.catchBlocksInProgress.pop();
        Intrinsics.areEqual(catchClauseEnterNodePop.getFir(), firCatch);
        if (this.tryExitNodes.top().getFir().getFinallyBlock() != null) {
            controlFlowGraphBuilder = this;
            addEdge$default(controlFlowGraphBuilder, catchClauseEnterNodePop, this.finallyEnterNodes.top(), false, false, null, UncaughtExceptionPath.INSTANCE, 24, null);
        } else {
            controlFlowGraphBuilder = this;
        }
        controlFlowGraphBuilder.lastNodes.push(catchClauseEnterNodePop);
        return catchClauseEnterNodePop;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002d  */
    /* JADX WARN: Code duplicated, block: B:13:0x0031  */
    /* JADX WARN: Code duplicated, block: B:18:0x0045  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Pair<CFGNode<?>, ClassEnterNode> enterClass(FirClass klass, boolean buildGraph) {
        CFGNode cFGNodeCreateLocalClassExitNode;
        FirRegularClass firRegularClass;
        CFGNode cFGNode;
        String strAsString;
        ControlFlowGraphBuilder controlFlowGraphBuilder;
        Object next;
        FirDelegatedConstructorCall delegatedConstructor;
        klass.getClass();
        Object obj = null;
        if (!buildGraph) {
            this.graphs.push(new ControlFlowGraph(null, "<discarded class graph>", ControlFlowGraph.Kind.Class));
            return TuplesKt.to(null, null);
        }
        boolean z = klass instanceof FirAnonymousObject;
        if (z) {
            FirAnonymousObject firAnonymousObject = (FirAnonymousObject) klass;
            if (firAnonymousObject.getClassKind() != ClassKind.ENUM_ENTRY) {
                cFGNodeCreateLocalClassExitNode = ControlFlowGraphNodeBuilderKt.createAnonymousObjectEnterNode(this, firAnonymousObject);
            } else if (klass instanceof FirRegularClass) {
                firRegularClass = (FirRegularClass) klass;
                if (firRegularClass.getIsLocal() || !getBodyBuildingMode()) {
                    cFGNodeCreateLocalClassExitNode = null;
                } else {
                    cFGNodeCreateLocalClassExitNode = ControlFlowGraphNodeBuilderKt.createLocalClassExitNode(this, firRegularClass);
                }
            } else {
                cFGNodeCreateLocalClassExitNode = null;
            }
        } else if (klass instanceof FirRegularClass) {
            firRegularClass = (FirRegularClass) klass;
            if (firRegularClass.getIsLocal()) {
                cFGNodeCreateLocalClassExitNode = null;
            } else {
                cFGNodeCreateLocalClassExitNode = null;
            }
        } else {
            cFGNodeCreateLocalClassExitNode = null;
        }
        if (cFGNodeCreateLocalClassExitNode != null) {
            addNewSimpleNode$default(this, cFGNodeCreateLocalClassExitNode, false, 2, null);
            cFGNode = cFGNodeCreateLocalClassExitNode;
        } else {
            cFGNode = null;
        }
        if (z) {
            strAsString = "<anonymous object>";
        } else {
            if (!(klass instanceof FirRegularClass)) {
                bu8.a();
                return null;
            }
            strAsString = ((FirRegularClass) klass).getName().asString();
            strAsString.getClass();
        }
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(klass != null ? klass : null, strAsString, ControlFlowGraph.Kind.Class);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createClassEnterNode(this, klass), ControlFlowGraphNodeBuilderKt.createClassExitNode(this, klass));
        CFGNode<?> cFGNode2 = (CFGNode) pair.component1();
        CFGNode<?> cFGNode3 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode2);
        controlFlowGraph.setExitNode(cFGNode3);
        this.lastNodes.push(cFGNode2);
        ClassEnterNode classEnterNode = (ClassEnterNode) cFGNode2;
        if (cFGNode != null) {
            controlFlowGraphBuilder = this;
            addEdge$default(controlFlowGraphBuilder, cFGNode, classEnterNode, false, false, null, null, 60, null);
        } else {
            controlFlowGraphBuilder = this;
            controlFlowGraphBuilder.addEdgeIfLocalClassMember(classEnterNode);
        }
        if (!classEnterNode.getPreviousNodes().isEmpty()) {
            Iterator<T> it = klass.getDeclarations().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                FirDeclaration firDeclaration = (FirDeclaration) next;
                if ((firDeclaration instanceof FirConstructor) && ((FirConstructor) firDeclaration).getIsPrimary()) {
                    break;
                }
            }
            FirDeclaration firDeclaration2 = (FirDeclaration) next;
            if (firDeclaration2 == null) {
                for (Object obj2 : klass.getDeclarations()) {
                    FirAnnotationContainer firAnnotationContainer = (FirDeclaration) obj2;
                    if ((firAnnotationContainer instanceof FirControlFlowGraphOwner) && !(firAnnotationContainer instanceof FirConstructor) && ControlFlowGraphBuilderKt.isUsedInControlFlowGraphBuilderForClass((FirControlFlowGraphOwner) firAnnotationContainer)) {
                        obj = obj2;
                        break;
                    }
                }
                firDeclaration2 = (FirDeclaration) obj;
            }
            for (FirAnnotationContainer firAnnotationContainer2 : klass.getDeclarations()) {
                if (firAnnotationContainer2 instanceof FirControlFlowGraphOwner) {
                    FirControlFlowGraphOwner firControlFlowGraphOwner = (FirControlFlowGraphOwner) firAnnotationContainer2;
                    if (ControlFlowGraphBuilderKt.getMemberShouldHaveGraph(firControlFlowGraphOwner)) {
                        controlFlowGraphBuilder.enterToLocalClassesMembers.put(((FirDeclaration) firControlFlowGraphOwner).getSymbol(), TuplesKt.to(classEnterNode, (Intrinsics.areEqual(firControlFlowGraphOwner, firDeclaration2) || ((firControlFlowGraphOwner instanceof FirConstructor) && (firDeclaration2 == null || ((delegatedConstructor = ((FirConstructor) firControlFlowGraphOwner).getDelegatedConstructor()) != null && delegatedConstructor.getIsThis())))) ? EdgeKind.Forward : EdgeKind.DfgForward));
                    }
                }
                if (firAnnotationContainer2 instanceof FirProperty) {
                    FirProperty firProperty = (FirProperty) firAnnotationContainer2;
                    FirPropertyAccessor getter = firProperty.getGetter();
                    if (getter != null) {
                        controlFlowGraphBuilder.enterToLocalClassesMembers.put(getter.getSymbol(), TuplesKt.to(classEnterNode, !Intrinsics.areEqual(getter, firDeclaration2) ? EdgeKind.DfgForward : EdgeKind.Forward));
                    }
                    FirPropertyAccessor setter = firProperty.getSetter();
                    if (setter != null) {
                        controlFlowGraphBuilder.enterToLocalClassesMembers.put(setter.getSymbol(), TuplesKt.to(classEnterNode, !Intrinsics.areEqual(setter, firDeclaration2) ? EdgeKind.DfgForward : EdgeKind.Forward));
                    }
                }
            }
        }
        return TuplesKt.to(cFGNode, classEnterNode);
    }

    public final CodeFragmentEnterNode enterCodeFragment(FirCodeFragment codeFragment) {
        codeFragment.getClass();
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(codeFragment != null ? codeFragment : null, "CODE_FRAGMENT_GRAPH", ControlFlowGraph.Kind.Function);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createCodeFragmentEnterNode(this, codeFragment), ControlFlowGraphNodeBuilderKt.createCodeFragmentExitNode(this, codeFragment));
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        return (CodeFragmentEnterNode) cFGNode;
    }

    public final void enterDelegateExpression() {
        splitDataFlowForPostponedLambdas$default(this, null, 1, null);
    }

    public final Pair<LoopEnterNode, LoopBlockEnterNode> enterDoWhileLoop(FirLoop loop) {
        loop.getClass();
        LoopEnterNode loopEnterNodeCreateLoopEnterNode = ControlFlowGraphNodeBuilderKt.createLoopEnterNode(this, loop);
        addNewSimpleNode$default(this, loopEnterNodeCreateLoopEnterNode, false, 2, null);
        this.loopExitNodes.put(loop, ControlFlowGraphNodeBuilderKt.createLoopExitNode(this, loop));
        LoopBlockEnterNode loopBlockEnterNodeCreateLoopBlockEnterNode = ControlFlowGraphNodeBuilderKt.createLoopBlockEnterNode(this, loop);
        addNewSimpleNode$default(this, loopBlockEnterNodeCreateLoopBlockEnterNode, false, 2, null);
        this.lastNodes.push(loopBlockEnterNodeCreateLoopBlockEnterNode);
        this.loopConditionEnterNodes.put(loop, ControlFlowGraphNodeBuilderKt.createLoopConditionEnterNode(this, loop.getCondition(), loop));
        return TuplesKt.to(loopEnterNodeCreateLoopEnterNode, loopBlockEnterNodeCreateLoopBlockEnterNode);
    }

    public final Pair<LoopBlockExitNode, LoopConditionEnterNode> enterDoWhileLoopCondition(FirLoop loop) {
        loop.getClass();
        LoopBlockExitNode loopBlockExitNodeCreateLoopBlockExitNode = ControlFlowGraphNodeBuilderKt.createLoopBlockExitNode(this, loop);
        addNewSimpleNode$default(this, loopBlockExitNodeCreateLoopBlockExitNode, false, 2, null);
        LoopConditionEnterNode loopConditionEnterNode = (LoopConditionEnterNode) MapsKt.getValue(this.loopConditionEnterNodes, loop);
        addNewSimpleNode$default(this, loopConditionEnterNode, false, 2, null);
        loopConditionEnterNode.updateDeadStatus();
        return TuplesKt.to(loopBlockExitNodeCreateLoopBlockExitNode, loopConditionEnterNode);
    }

    public final void enterElvis(FirElvisExpression elvisExpression) {
        elvisExpression.getClass();
        this.elvisRhsEnterNodes.push(ControlFlowGraphNodeBuilderKt.createElvisRhsEnterNode(this, elvisExpression));
        splitDataFlowForPostponedLambdas$default(this, null, 1, null);
    }

    public final FakeExpressionEnterNode enterFakeExpression() {
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(null, "<compile-time expression graph>", ControlFlowGraph.Kind.FakeCall);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createFakeExpressionEnterNode(this), ControlFlowGraphNodeBuilderKt.createFakeExpressionEnterNode(this));
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        return (FakeExpressionEnterNode) cFGNode;
    }

    public final FieldInitializerEnterNode enterField(FirField field) {
        field.getClass();
        if (!ControlFlowGraphBuilderKt.getMemberShouldHaveGraph(field)) {
            return null;
        }
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(field != null ? field : null, "val " + field.getName(), ControlFlowGraph.Kind.FieldInitializer);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createFieldInitializerEnterNode(this, field), ControlFlowGraphNodeBuilderKt.createFieldInitializerExitNode(this, field));
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        FieldInitializerEnterNode fieldInitializerEnterNode = (FieldInitializerEnterNode) cFGNode;
        addEdgeIfLocalClassMember(fieldInitializerEnterNode);
        return fieldInitializerEnterNode;
    }

    public final FileEnterNode enterFile(FirFile file, boolean buildGraph) {
        file.getClass();
        if (!buildGraph) {
            this.graphs.push(new ControlFlowGraph(null, "<discarded file graph>", ControlFlowGraph.Kind.File));
            return null;
        }
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(file != null ? file : null, "FILE_GRAPH", ControlFlowGraph.Kind.File);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createFileEnterNode(this, file), ControlFlowGraphNodeBuilderKt.createFileExitNode(this, file));
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        return (FileEnterNode) cFGNode;
    }

    public final FinallyBlockEnterNode enterFinallyBlock() {
        FinallyBlockEnterNode finallyBlockEnterNodePop = this.finallyEnterNodes.pop();
        this.lastNodes.push(finallyBlockEnterNodePop);
        this.finallyBlocksInProgress.push(finallyBlockEnterNodePop);
        this.finallyBlocksInProgressSet.add(finallyBlockEnterNodePop.getFir());
        return finallyBlockEnterNodePop;
    }

    public final Pair<LocalFunctionDeclarationNode, FunctionEnterNode> enterFunction(FirFunction function) {
        String strAsString;
        LocalFunctionDeclarationNode localFunctionDeclarationNode;
        ControlFlowGraph.Kind kind;
        function.getClass();
        if (function instanceof FirAnonymousFunction) {
            w01.a("Failed requirement.");
            return null;
        }
        boolean z = function instanceof FirNamedFunction;
        if (z) {
            strAsString = ((FirNamedFunction) function).getName().asString();
        } else if (function instanceof FirPropertyAccessor) {
            strAsString = ((FirPropertyAccessor) function).getIsGetter() ? "<getter>" : "<setter>";
        } else {
            if (!(function instanceof FirConstructor)) {
                z01.a("Unknown function: ", UtilsKt.render(function));
                return null;
            }
            strAsString = "<init>";
        }
        strAsString.getClass();
        if (z && Intrinsics.areEqual(((FirNamedFunction) function).getStatus().getVisibility(), Visibilities.Local.INSTANCE) && getBodyBuildingMode()) {
            LocalFunctionDeclarationNode localFunctionDeclarationNodeCreateLocalFunctionDeclarationNode = ControlFlowGraphNodeBuilderKt.createLocalFunctionDeclarationNode(this, function);
            addNewSimpleNode$default(this, localFunctionDeclarationNodeCreateLocalFunctionDeclarationNode, false, 2, null);
            localFunctionDeclarationNode = localFunctionDeclarationNodeCreateLocalFunctionDeclarationNode;
        } else {
            localFunctionDeclarationNode = null;
        }
        if (localFunctionDeclarationNode != null) {
            kind = ControlFlowGraph.Kind.LocalFunction;
        } else {
            kind = function instanceof FirConstructor ? ControlFlowGraph.Kind.Constructor : ControlFlowGraph.Kind.Function;
        }
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(function != null ? function : null, strAsString, kind);
        this.graphs.push(controlFlowGraph);
        FunctionEnterNode functionEnterNodeCreateFunctionEnterNode = ControlFlowGraphNodeBuilderKt.createFunctionEnterNode(this, function);
        FunctionExitNode functionExitNodeCreateFunctionExitNode = ControlFlowGraphNodeBuilderKt.createFunctionExitNode(this, function);
        this.exitTargetsForReturn.put(function.getSymbol(), functionExitNodeCreateFunctionExitNode);
        Pair pair = TuplesKt.to(functionEnterNodeCreateFunctionEnterNode, functionExitNodeCreateFunctionExitNode);
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        FunctionEnterNode functionEnterNode = (FunctionEnterNode) cFGNode;
        if (localFunctionDeclarationNode != null) {
            addEdge$default(this, localFunctionDeclarationNode, functionEnterNode, false, false, null, null, 60, null);
            addBackEdge$default(this, functionEnterNode.getOwner().getExitNode(), functionEnterNode, false, null, 12, null);
            functionEnterNode = functionEnterNode;
        } else {
            addEdgeIfLocalClassMember(functionEnterNode);
        }
        return new Pair<>(localFunctionDeclarationNode, functionEnterNode);
    }

    public final FunctionCallEnterNode enterFunctionCall(FirFunctionCall functionCall) {
        functionCall.getClass();
        FunctionCallEnterNode functionCallEnterNodeCreateFunctionCallEnterNode = ControlFlowGraphNodeBuilderKt.createFunctionCallEnterNode(this, functionCall);
        addNewSimpleNode$default(this, functionCallEnterNodeCreateFunctionCallEnterNode, false, 2, null);
        return functionCallEnterNodeCreateFunctionCallEnterNode;
    }

    public final InitBlockEnterNode enterInitBlock(FirAnonymousInitializer initBlock) {
        initBlock.getClass();
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(initBlock != null ? initBlock : null, "init block", ControlFlowGraph.Kind.ClassInitializer);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createInitBlockEnterNode(this, initBlock), ControlFlowGraphNodeBuilderKt.createInitBlockExitNode(this, initBlock));
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        InitBlockEnterNode initBlockEnterNode = (InitBlockEnterNode) cFGNode;
        addEdgeIfLocalClassMember(initBlockEnterNode);
        return initBlockEnterNode;
    }

    public final void enterJump(FirJump<?> jump) {
        jump.getClass();
        if ((jump instanceof FirReturnExpression) && (((FirReturnExpression) jump).getTarget().getLabeledElement() instanceof FirAnonymousFunction)) {
            splitDataFlowForPostponedLambdas$default(this, null, 1, null);
        }
    }

    public final PropertyInitializerEnterNode enterProperty(FirProperty property) {
        property.getClass();
        if (!ControlFlowGraphBuilderKt.getMemberShouldHaveGraph(property)) {
            return null;
        }
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(property != null ? property : null, "val " + property.getName(), ControlFlowGraph.Kind.PropertyInitializer);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createPropertyInitializerEnterNode(this, property), ControlFlowGraphNodeBuilderKt.createPropertyInitializerExitNode(this, property));
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        PropertyInitializerEnterNode propertyInitializerEnterNode = (PropertyInitializerEnterNode) cFGNode;
        addEdgeIfLocalClassMember(propertyInitializerEnterNode);
        return propertyInitializerEnterNode;
    }

    public final EnterSafeCallNode enterSafeCall(FirSafeCallExpression safeCall) {
        CFGNode<?> cFGNode;
        FirElvisExpression fir;
        safeCall.getClass();
        EnterSafeCallNode enterSafeCallNodeCreateEnterSafeCallNode = ControlFlowGraphNodeBuilderKt.createEnterSafeCallNode(this, safeCall);
        ExitSafeCallNode exitSafeCallNodeCreateExitSafeCallNode = ControlFlowGraphNodeBuilderKt.createExitSafeCallNode(this, safeCall);
        this.exitSafeCallNodes.push(exitSafeCallNodeCreateExitSafeCallNode);
        CFGNode<?> cFGNodePop = this.lastNodes.pop();
        if (cFGNodePop instanceof ExitSafeCallNode) {
            addEdge$default(this, CFGNodeKt.getLastPreviousNode(cFGNodePop), enterSafeCallNodeCreateEnterSafeCallNode, false, false, null, null, 60, null);
            cFGNode = cFGNodePop;
        } else {
            cFGNode = cFGNodePop;
            addEdge$default(this, cFGNode, enterSafeCallNodeCreateEnterSafeCallNode, false, false, null, null, 60, null);
        }
        ElvisRhsEnterNode elvisRhsEnterNode = (ElvisRhsEnterNode) StackKt.topOrNull(this.elvisRhsEnterNodes);
        if (((elvisRhsEnterNode == null || (fir = elvisRhsEnterNode.getFir()) == null) ? null : fir.getLhs()) == safeCall) {
            addEdge$default(this, cFGNode, elvisRhsEnterNode, false, false, null, null, 60, null);
        } else {
            addEdge$default(this, cFGNode, exitSafeCallNodeCreateExitSafeCallNode, false, false, null, null, 60, null);
        }
        this.lastNodes.push(enterSafeCallNodeCreateEnterSafeCallNode);
        splitDataFlowForPostponedLambdas$default(this, null, 1, null);
        return enterSafeCallNodeCreateEnterSafeCallNode;
    }

    public final ScriptEnterNode enterScript(FirScript script, boolean buildGraph) {
        script.getClass();
        if (!buildGraph) {
            this.graphs.push(new ControlFlowGraph(null, "<discarded script graph>", ControlFlowGraph.Kind.Script));
            return null;
        }
        String strAsString = script.getName().asString();
        strAsString.getClass();
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(script != null ? script : null, strAsString, ControlFlowGraph.Kind.Script);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createScriptEnterNode(this, script), ControlFlowGraphNodeBuilderKt.createScriptExitNode(this, script));
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        return (ScriptEnterNode) cFGNode;
    }

    public final Pair<TryExpressionEnterNode, TryMainBlockEnterNode> enterTryExpression(FirTryExpression tryExpression) {
        tryExpression.getClass();
        TryExpressionEnterNode tryExpressionEnterNodeCreateTryExpressionEnterNode = ControlFlowGraphNodeBuilderKt.createTryExpressionEnterNode(this, tryExpression);
        addNewSimpleNode$default(this, tryExpressionEnterNodeCreateTryExpressionEnterNode, false, 2, null);
        this.tryExitNodes.push(ControlFlowGraphNodeBuilderKt.createTryExpressionExitNode(this, tryExpression));
        TryMainBlockEnterNode tryMainBlockEnterNodeCreateTryMainBlockEnterNode = ControlFlowGraphNodeBuilderKt.createTryMainBlockEnterNode(this, tryExpression);
        addNewSimpleNode$default(this, tryMainBlockEnterNodeCreateTryMainBlockEnterNode, false, 2, null);
        Stack<List<CatchClauseEnterNode>> stack = this.catchNodes;
        List<FirCatch> catches = tryExpression.getCatches();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(catches, 10));
        Iterator<T> it = catches.iterator();
        while (it.hasNext()) {
            arrayList.add(ControlFlowGraphNodeBuilderKt.createCatchClauseEnterNode(this, (FirCatch) it.next()));
        }
        stack.push(arrayList);
        if (tryExpression.getFinallyBlock() != null) {
            this.finallyEnterNodes.push(ControlFlowGraphNodeBuilderKt.createFinallyBlockEnterNode(this, tryExpression));
        }
        Iterator<CatchClauseEnterNode> it2 = this.catchNodes.top().iterator();
        while (it2.hasNext()) {
            addEdge$default(this, tryExpressionEnterNodeCreateTryExpressionEnterNode, it2.next(), false, false, null, null, 60, null);
        }
        if (tryExpression.getFinallyBlock() != null) {
            addEdge$default(this, tryExpressionEnterNodeCreateTryExpressionEnterNode, this.finallyEnterNodes.top(), false, false, null, UncaughtExceptionPath.INSTANCE, 28, null);
        }
        this.notCompletedFunctionCalls.push(new ArrayList());
        splitDataFlowForPostponedLambdas$default(this, null, 1, null);
        return TuplesKt.to(tryExpressionEnterNodeCreateTryExpressionEnterNode, tryMainBlockEnterNodeCreateTryMainBlockEnterNode);
    }

    public final Pair<EnterValueParameterNode, EnterDefaultArgumentsNode> enterValueParameter(FirValueParameter valueParameter) {
        valueParameter.getClass();
        if (valueParameter.getDefaultValue() == null || valueParameter.getValueParameterKind() != FirValueParameterKind.Regular) {
            return null;
        }
        EnterValueParameterNode enterValueParameterNodeCreateEnterValueParameterNode = ControlFlowGraphNodeBuilderKt.createEnterValueParameterNode(this, valueParameter);
        addNewSimpleNode$default(this, enterValueParameterNodeCreateEnterValueParameterNode, false, 2, null);
        ControlFlowGraph controlFlowGraph = new ControlFlowGraph(valueParameter, "default value of " + valueParameter.getName(), ControlFlowGraph.Kind.DefaultArgument);
        this.graphs.push(controlFlowGraph);
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createEnterDefaultArgumentsNode(this, valueParameter), ControlFlowGraphNodeBuilderKt.createExitDefaultArgumentsNode(this, valueParameter));
        CFGNode<?> cFGNode = (CFGNode) pair.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pair.component2();
        controlFlowGraph.setEnterNode(cFGNode);
        controlFlowGraph.setExitNode(cFGNode2);
        this.lastNodes.push(cFGNode);
        EnterDefaultArgumentsNode enterDefaultArgumentsNode = (EnterDefaultArgumentsNode) cFGNode;
        addEdge$default(this, enterValueParameterNodeCreateEnterValueParameterNode, enterDefaultArgumentsNode, false, false, null, null, 60, null);
        return TuplesKt.to(enterValueParameterNodeCreateEnterValueParameterNode, enterDefaultArgumentsNode);
    }

    public final VariableDeclarationEnterNode enterVariableDeclaration(FirProperty variable) {
        variable.getClass();
        VariableDeclarationEnterNode variableDeclarationEnterNodeCreateVariableDeclarationEnterNode = ControlFlowGraphNodeBuilderKt.createVariableDeclarationEnterNode(this, variable);
        addNewSimpleNode$default(this, variableDeclarationEnterNodeCreateVariableDeclarationEnterNode, false, 2, null);
        return variableDeclarationEnterNodeCreateVariableDeclarationEnterNode;
    }

    public final WhenBranchConditionEnterNode enterWhenBranchCondition(FirWhenBranch whenBranch) {
        whenBranch.getClass();
        WhenBranchConditionEnterNode whenBranchConditionEnterNodeCreateWhenBranchConditionEnterNode = ControlFlowGraphNodeBuilderKt.createWhenBranchConditionEnterNode(this, whenBranch);
        addNewSimpleNode$default(this, whenBranchConditionEnterNodeCreateWhenBranchConditionEnterNode, false, 2, null);
        return whenBranchConditionEnterNodeCreateWhenBranchConditionEnterNode;
    }

    public final WhenEnterNode enterWhenExpression(FirWhenExpression whenExpression) {
        whenExpression.getClass();
        WhenEnterNode whenEnterNodeCreateWhenEnterNode = ControlFlowGraphNodeBuilderKt.createWhenEnterNode(this, whenExpression);
        addNewSimpleNode$default(this, whenEnterNodeCreateWhenEnterNode, false, 2, null);
        this.whenExitNodes.push(ControlFlowGraphNodeBuilderKt.createWhenExitNode(this, whenExpression));
        this.notCompletedFunctionCalls.push(new ArrayList());
        splitDataFlowForPostponedLambdas$default(this, null, 1, null);
        return whenEnterNodeCreateWhenEnterNode;
    }

    public final Pair<LoopEnterNode, LoopConditionEnterNode> enterWhileLoop(FirLoop loop) {
        loop.getClass();
        LoopEnterNode loopEnterNodeCreateLoopEnterNode = ControlFlowGraphNodeBuilderKt.createLoopEnterNode(this, loop);
        addNewSimpleNode$default(this, loopEnterNodeCreateLoopEnterNode, false, 2, null);
        this.loopExitNodes.put(loop, ControlFlowGraphNodeBuilderKt.createLoopExitNode(this, loop));
        LoopConditionEnterNode loopConditionEnterNodeCreateLoopConditionEnterNode = ControlFlowGraphNodeBuilderKt.createLoopConditionEnterNode(this, loop.getCondition(), loop);
        addNewSimpleNode$default(this, loopConditionEnterNodeCreateLoopConditionEnterNode, false, 2, null);
        this.loopConditionEnterNodes.put(loop, loopConditionEnterNodeCreateLoopConditionEnterNode);
        return TuplesKt.to(loopEnterNodeCreateLoopEnterNode, loopConditionEnterNodeCreateLoopConditionEnterNode);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Triple<FunctionExitNode, PostponedLambdaExitNode, ControlFlowGraph> exitAnonymousFunction(FirAnonymousFunction anonymousFunction) throws UninitializedPropertyAccessException {
        PostponedLambdaExitNode postponedLambdaExitNode;
        FunctionExitNode functionExitNode;
        anonymousFunction.getClass();
        this.exitTargetsForReturn.remove(anonymousFunction.getSymbol());
        ControlFlowGraph controlFlowGraphPop = this.graphs.pop();
        CFGNode<?> exitNode = controlFlowGraphPop.getExitNode();
        if (exitNode == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionExitNode");
            return null;
        }
        FunctionExitNode functionExitNode2 = (FunctionExitNode) exitNode;
        popAndAddEdge$default(this, functionExitNode2, null, 2, null);
        if (functionExitNode2.getPreviousNodes().size() > 1) {
            functionExitNode2.updateDeadStatus();
        }
        controlFlowGraphPop.complete();
        Pair pair = TuplesKt.to(functionExitNode2, controlFlowGraphPop);
        FunctionExitNode functionExitNode3 = (FunctionExitNode) pair.component1();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pair.component2();
        Pair<CFGNode<?>, PostponedLambdaExitNode> pairRemove = this.postponedAnonymousFunctionNodes.remove(anonymousFunction.getSymbol());
        pairRemove.getClass();
        Pair<CFGNode<?>, PostponedLambdaExitNode> pair2 = pairRemove;
        CFGNode<?> cFGNode = (CFGNode) pair2.component1();
        PostponedLambdaExitNode postponedLambdaExitNode2 = (PostponedLambdaExitNode) pair2.component2();
        EventOccurrencesRange invocationKind = anonymousFunction.getInvocationKind();
        if (postponedLambdaExitNode2 == null) {
            return new Triple<>(functionExitNode3, (Object) null, controlFlowGraph);
        }
        boolean z = invocationKind != null && EventOccurrencesRangeKt.isDefinitelyVisited(invocationKind);
        if (z || cFGNode.getIsDead()) {
            CFGNode.INSTANCE.killEdge(cFGNode, postponedLambdaExitNode2, !z);
        }
        if (invocationKind == null) {
            postponedLambdaExitNode = postponedLambdaExitNode2;
            functionExitNode = functionExitNode3;
            addBackEdge$default(this, controlFlowGraph.getExitNode(), controlFlowGraph.getEnterNode(), false, null, 12, null);
        } else if (EventOccurrencesRangeKt.canBeVisited(invocationKind)) {
            addEdge$default(this, functionExitNode3, postponedLambdaExitNode2, z, false, null, null, 56, null);
            functionExitNode = functionExitNode3;
            if (EventOccurrencesRangeKt.canBeRevisited(invocationKind)) {
                addBackEdge$default(this, postponedLambdaExitNode2, cFGNode, false, null, 12, null);
            }
            postponedLambdaExitNode = postponedLambdaExitNode2;
        } else {
            postponedLambdaExitNode = postponedLambdaExitNode2;
            functionExitNode = functionExitNode3;
        }
        AnonymousFunctionCaptureNode anonymousFunctionCaptureNodeRemove = this.anonymousFunctionCaptureNodes.remove(anonymousFunction.getSymbol());
        if (anonymousFunctionCaptureNodeRemove != null && (anonymousFunction.getInlineStatus() == InlineStatus.Inline || anonymousFunction.getInlineStatus() == InlineStatus.CrossInline)) {
            CFGNode.INSTANCE.killEdge(anonymousFunctionCaptureNodeRemove, controlFlowGraph.getEnterNode(), false);
        }
        return new Triple<>(functionExitNode, postponedLambdaExitNode, controlFlowGraph);
    }

    public final AnonymousObjectExpressionExitNode exitAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression) {
        ControlFlowGraph controlFlowGraph;
        anonymousObjectExpression.getClass();
        FirAnonymousObject anonymousObject = anonymousObjectExpression.getAnonymousObject();
        if (anonymousObject.getClassKind() == ClassKind.ENUM_ENTRY) {
            return null;
        }
        AnonymousObjectExpressionExitNode anonymousObjectExpressionExitNodeCreateAnonymousObjectExpressionExitNode = ControlFlowGraphNodeBuilderKt.createAnonymousObjectExpressionExitNode(this, anonymousObjectExpression);
        FirControlFlowGraphReference controlFlowGraphReference = anonymousObject.getControlFlowGraphReference();
        CFGNode<?> exitNode = (controlFlowGraphReference == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null) ? null : controlFlowGraph.getExitNode();
        if (exitNode == null || !(getLastNode() instanceof AnonymousObjectEnterNode)) {
            addNewSimpleNode$default(this, anonymousObjectExpressionExitNodeCreateAnonymousObjectExpressionExitNode, false, 2, null);
            return anonymousObjectExpressionExitNodeCreateAnonymousObjectExpressionExitNode;
        }
        addEdge$default(this, exitNode, anonymousObjectExpressionExitNodeCreateAnonymousObjectExpressionExitNode, false, false, null, null, 60, null);
        addEdge$default(this, this.lastNodes.pop(), anonymousObjectExpressionExitNodeCreateAnonymousObjectExpressionExitNode, false, false, EdgeKind.DeadForward, null, 40, null);
        this.lastNodes.push(anonymousObjectExpressionExitNodeCreateAnonymousObjectExpressionExitNode);
        return anonymousObjectExpressionExitNodeCreateAnonymousObjectExpressionExitNode;
    }

    public final CFGNode<?> exitBlock(FirBlock block) {
        block.getClass();
        BlockExitNode blockExitNodeCreateBlockExitNode = ControlFlowGraphNodeBuilderKt.createBlockExitNode(this, block);
        addNewSimpleNode$default(this, blockExitNodeCreateBlockExitNode, false, 2, null);
        return blockExitNodeCreateBlockExitNode;
    }

    public final BooleanOperatorExitNode exitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression) {
        ControlFlowGraphBuilder controlFlowGraphBuilder;
        booleanOperatorExpression.getClass();
        CFGNode<?> cFGNodePop = this.lastNodes.pop();
        CFGNode<?> cFGNodePop2 = this.lastNodes.pop();
        BooleanOperatorExitNode booleanOperatorExitNodeCreateBooleanOperatorExitNode = ControlFlowGraphNodeBuilderKt.createBooleanOperatorExitNode(this, booleanOperatorExpression, cFGNodePop2, cFGNodePop);
        if (Intrinsics.areEqual(ControlFlowGraphBuilderKt.getBooleanLiteralValue(booleanOperatorExpression.getLeftOperand()), Boolean.valueOf(booleanOperatorExpression.getKind() == LogicOperationKind.AND))) {
            controlFlowGraphBuilder = this;
            addEdge$default(controlFlowGraphBuilder, cFGNodePop, booleanOperatorExitNodeCreateBooleanOperatorExitNode, false, false, null, null, 60, null);
        } else {
            controlFlowGraphBuilder = this;
            addEdge$default(controlFlowGraphBuilder, cFGNodePop2, booleanOperatorExitNodeCreateBooleanOperatorExitNode, true, false, null, null, 56, null);
            booleanOperatorExitNodeCreateBooleanOperatorExitNode = booleanOperatorExitNodeCreateBooleanOperatorExitNode;
            addEdge$default(controlFlowGraphBuilder, cFGNodePop, booleanOperatorExitNodeCreateBooleanOperatorExitNode, false, false, null, null, 56, null);
        }
        controlFlowGraphBuilder.lastNodes.push(booleanOperatorExitNodeCreateBooleanOperatorExitNode);
        return booleanOperatorExitNodeCreateBooleanOperatorExitNode;
    }

    public final Pair<SplitPostponedLambdasNode, FunctionCallArgumentsExitNode> exitCallArguments() {
        SplitPostponedLambdasNode splitPostponedLambdasNodePop = this.argumentListSplitNodes.pop();
        FunctionCallArgumentsExitNode functionCallArgumentsExitNode = null;
        if (splitPostponedLambdasNodePop != null) {
            addNewSimpleNode$default(this, splitPostponedLambdasNodePop, false, 2, null);
        } else {
            splitPostponedLambdasNodePop = null;
        }
        FunctionCallArgumentsExitNode functionCallArgumentsExitNodePop = this.exitFunctionCallArgumentsNodes.pop();
        if (functionCallArgumentsExitNodePop != null) {
            addNewSimpleNode$default(this, functionCallArgumentsExitNodePop, false, 2, null);
            functionCallArgumentsExitNode = functionCallArgumentsExitNodePop;
        }
        return TuplesKt.to(splitPostponedLambdasNodePop, functionCallArgumentsExitNode);
    }

    public final void exitCallExplicitReceiver() {
        FunctionCallArgumentsExitNode functionCallArgumentsExitNode = (FunctionCallArgumentsExitNode) StackKt.topOrNull(this.exitFunctionCallArgumentsNodes);
        if (functionCallArgumentsExitNode != null) {
            functionCallArgumentsExitNode.setExplicitReceiverExitNode(getLastNode());
        }
    }

    public final CallableReferenceNode exitCallableReference(FirCallableReferenceAccess callableReferenceAccess) {
        callableReferenceAccess.getClass();
        CallableReferenceNode callableReferenceNodeCreateCallableReferenceNode = ControlFlowGraphNodeBuilderKt.createCallableReferenceNode(this, callableReferenceAccess);
        addNewSimpleNode$default(this, callableReferenceNodeCreateCallableReferenceNode, false, 2, null);
        return callableReferenceNodeCreateCallableReferenceNode;
    }

    public final CatchClauseExitNode exitCatchClause(FirCatch firCatch) {
        firCatch.getClass();
        FinallyBlockEnterNode pVar = this.tryExitNodes.top();
        CatchClauseExitNode catchClauseExitNodeCreateCatchClauseExitNode = ControlFlowGraphNodeBuilderKt.createCatchClauseExitNode(this, firCatch);
        popAndAddEdge$default(this, catchClauseExitNodeCreateCatchClauseExitNode, null, 2, null);
        if (pVar.getFir().getFinallyBlock() != null) {
            pVar = this.finallyEnterNodes.top();
        }
        addEdge$default(this, catchClauseExitNodeCreateCatchClauseExitNode, pVar, false, false, null, null, 56, null);
        return catchClauseExitNodeCreateCatchClauseExitNode;
    }

    public final CheckNotNullCallNode exitCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, boolean callCompleted) {
        checkNotNullCall.getClass();
        CheckNotNullCallNode checkNotNullCallNodeCreateCheckNotNullCallNode = ControlFlowGraphNodeBuilderKt.createCheckNotNullCallNode(this, checkNotNullCall);
        unifyDataFlowFromPostponedLambdas(checkNotNullCallNodeCreateCheckNotNullCallNode, callCompleted);
        if (ControlFlowGraphBuilderKt.getHasNothingType(checkNotNullCall)) {
            addNonSuccessfullyTerminatingNode(checkNotNullCallNodeCreateCheckNotNullCallNode);
            return checkNotNullCallNodeCreateCheckNotNullCallNode;
        }
        addNewSimpleNode$default(this, checkNotNullCallNodeCreateCheckNotNullCallNode, false, 2, null);
        return checkNotNullCallNodeCreateCheckNotNullCallNode;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Code duplicated, block: B:100:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:96:0x01dc  */
    public final Pair<ClassExitNode, ControlFlowGraph> exitClass() throws UninitializedPropertyAccessException {
        ClassEnterNode classEnterNode;
        ControlFlowGraphBuilder controlFlowGraphBuilder;
        Pair<ClassExitNode, ControlFlowGraph> pair;
        CFGNode<?> cFGNode;
        LinkedHashMap linkedHashMap;
        FirControlFlowGraphOwner firControlFlowGraphOwner;
        LinkedHashMap linkedHashMap2;
        LinkedHashMap linkedHashMap3;
        FirControlFlowGraphReference controlFlowGraphReference;
        ControlFlowGraph controlFlowGraph;
        FirControlFlowGraphReference controlFlowGraphReference2;
        ControlFlowGraph controlFlowGraph2;
        FirControlFlowGraphReference controlFlowGraphReference3;
        ControlFlowGraph controlFlowGraph3;
        ControlFlowGraphBuilder controlFlowGraphBuilder2 = this;
        controlFlowGraphBuilder2.getCurrentGraph().getKind();
        ControlFlowGraph.Kind kind = ControlFlowGraph.Kind.File;
        Pair<ClassExitNode, ControlFlowGraph> pair2 = null;
        if (controlFlowGraphBuilder2.getCurrentGraph().getDeclaration() == null) {
            controlFlowGraphBuilder2.graphs.pop();
            return TuplesKt.to(null, null);
        }
        CFGNode<?> cFGNodePop = controlFlowGraphBuilder2.lastNodes.pop();
        cFGNodePop.getClass();
        ClassEnterNode classEnterNode2 = (ClassEnterNode) cFGNodePop;
        Pair<ClassExitNode, ControlFlowGraph> exitNode = controlFlowGraphBuilder2.getCurrentGraph().getExitNode();
        exitNode.getClass();
        Pair<ClassExitNode, ControlFlowGraph> pair3 = (ClassExitNode) exitNode;
        FirClass fir = classEnterNode2.getFir();
        fir.getClass();
        if (fir.getControlFlowGraphReference() != null) {
            controlFlowGraphBuilder2.graphs.pop();
            return TuplesKt.to(null, null);
        }
        boolean zIsLocal = fir.getIsLocal();
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        ArrayList<ControlFlowGraph> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        FirControlFlowGraphOwner firControlFlowGraphOwner2 = null;
        for (FirAnnotationContainer firAnnotationContainer : fir.getDeclarations()) {
            if (firAnnotationContainer instanceof FirControlFlowGraphOwner) {
                FirControlFlowGraphOwner firControlFlowGraphOwner3 = (FirControlFlowGraphOwner) firAnnotationContainer;
                if (ControlFlowGraphBuilderKt.getMemberShouldHaveGraph(firControlFlowGraphOwner3) && (controlFlowGraphReference3 = firControlFlowGraphOwner3.getControlFlowGraphReference()) != null && (controlFlowGraph3 = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference3)) != null) {
                    if (firControlFlowGraphOwner3 instanceof FirConstructor) {
                        if (((FirConstructor) firControlFlowGraphOwner3).getIsPrimary()) {
                            arrayList.add(controlFlowGraph3);
                            firControlFlowGraphOwner2 = firControlFlowGraphOwner3;
                        } else {
                            linkedHashMap4.put(firControlFlowGraphOwner3, controlFlowGraph3);
                        }
                    } else if (!(firControlFlowGraphOwner3 instanceof FirPropertyAccessor) && !(firControlFlowGraphOwner3 instanceof FirFunction) && !(firControlFlowGraphOwner3 instanceof FirClass)) {
                        arrayList.add(controlFlowGraph3);
                    } else if (zIsLocal) {
                        arrayList2.add(controlFlowGraph3);
                    }
                }
            }
            if (firAnnotationContainer instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) firAnnotationContainer;
                FirPropertyAccessor getter = firProperty.getGetter();
                if (getter != null && (controlFlowGraphReference2 = getter.getControlFlowGraphReference()) != null && (controlFlowGraph2 = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference2)) != null && zIsLocal) {
                    arrayList2.add(controlFlowGraph2);
                }
                FirPropertyAccessor setter = firProperty.getSetter();
                if (setter != null && (controlFlowGraphReference = setter.getControlFlowGraphReference()) != null && (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) != null && zIsLocal) {
                    arrayList2.add(controlFlowGraph);
                }
            }
        }
        ControlFlowGraph controlFlowGraph4 = (ControlFlowGraph) CollectionsKt.firstOrNull(arrayList);
        CFGNode<?> enterNode = controlFlowGraph4 != null ? controlFlowGraph4.getEnterNode() : null;
        CFGNode<?> exitNode2 = classEnterNode2;
        for (ControlFlowGraph controlFlowGraph5 : arrayList) {
            if (exitNode2 != classEnterNode2 || !zIsLocal) {
                controlFlowGraphBuilder2.addEdgeToSubGraph(exitNode2, controlFlowGraph5.getEnterNode());
            }
            exitNode2 = controlFlowGraph5.getExitNode();
        }
        for (ControlFlowGraph controlFlowGraph6 : arrayList) {
            EdgeKind edgeKindForward = EdgeKind.INSTANCE.forward(firControlFlowGraphOwner2 != null && Intrinsics.areEqual(controlFlowGraph6.getExitNode(), exitNode2), pair3.isUnion());
            if (edgeKindForward != null) {
                addEdge$default(controlFlowGraphBuilder2, controlFlowGraph6.getExitNode(), pair3, false, false, edgeKindForward, null, 44, null);
                enterNode = enterNode;
                exitNode2 = exitNode2;
                pair2 = pair2;
                zIsLocal = zIsLocal;
            }
        }
        Pair<ClassExitNode, ControlFlowGraph> pair4 = pair2;
        boolean z = zIsLocal;
        CFGNode<?> cFGNode2 = enterNode;
        CFGNode<?> cFGNode3 = exitNode2;
        LinkedHashMap linkedHashMap5 = new LinkedHashMap();
        LinkedHashMap linkedHashMap6 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap4.entrySet()) {
            FirConstructor firConstructor = (FirConstructor) entry.getKey();
            ControlFlowGraph controlFlowGraph7 = (ControlFlowGraph) entry.getValue();
            exitClass$computeDelegationLevel(firConstructor, linkedHashMap6, linkedHashMap5);
            FirConstructor firConstructor2 = (FirConstructor) linkedHashMap5.get(firConstructor);
            ControlFlowGraph controlFlowGraph8 = (ControlFlowGraph) linkedHashMap4.get(firConstructor2);
            if (controlFlowGraph8 != null) {
                pair = TuplesKt.to((linkedHashMap5.containsKey(firConstructor2) || cFGNode2 == null) ? controlFlowGraph8.getEnterNode() : cFGNode2, controlFlowGraph8.getExitNode());
                if (pair == null) {
                    if (firConstructor2 == null) {
                        pair = pair4;
                    } else {
                        pair = pair4;
                    }
                }
            } else if (firConstructor2 == null && Intrinsics.areEqual(firControlFlowGraphOwner2, firConstructor2)) {
                cFGNode2.getClass();
                pair = TuplesKt.to(cFGNode2, cFGNode3);
            } else {
                pair = pair4;
            }
            if (pair != null) {
                if (!z) {
                    controlFlowGraphBuilder2.addEdgeToSubGraph(classEnterNode2, controlFlowGraph7.getEnterNode());
                }
                CFGNode cFGNode4 = (CFGNode) pair.component1();
                CFGNode<?> cFGNode5 = (CFGNode) pair.component2();
                boolean z2 = false;
                Object obj = pair4;
                for (Object obj2 : controlFlowGraph7.getNodes()) {
                    if (((CFGNode) obj2) instanceof DelegatedConstructorCallNode) {
                        if (z2) {
                            w01.a("Collection contains more than one matching element.");
                            return pair4;
                        }
                        obj = obj2;
                        z2 = true;
                    }
                }
                if (!z2) {
                    hb9.a("Collection contains no element matching the predicate.");
                    return pair4;
                }
                CFGNode<?> cFGNode6 = (CFGNode) obj;
                List<CFGNode<?>> list = CollectionsKt.toList(cFGNode6.getFollowingNodes());
                CFGNode.INSTANCE.removeAllOutgoingEdges(cFGNode6);
                linkedHashMap = linkedHashMap4;
                linkedHashMap3 = linkedHashMap6;
                firControlFlowGraphOwner = firControlFlowGraphOwner2;
                cFGNode = cFGNode2;
                linkedHashMap2 = linkedHashMap5;
                controlFlowGraphBuilder2 = this;
                addEdge$default(controlFlowGraphBuilder2, cFGNode6, cFGNode4, false, false, EdgeKind.CfgForward, null, 40, null);
                for (CFGNode<?> cFGNode7 : list) {
                    controlFlowGraphBuilder2.addEdgeToSubGraph(cFGNode5, cFGNode7);
                    addEdge$default(controlFlowGraphBuilder2, cFGNode6, cFGNode7, false, false, EdgeKind.DfgForward, null, 44, null);
                }
            } else {
                cFGNode = cFGNode2;
                linkedHashMap = linkedHashMap4;
                firControlFlowGraphOwner = firControlFlowGraphOwner2;
                linkedHashMap2 = linkedHashMap5;
                linkedHashMap3 = linkedHashMap6;
                if (cFGNode3 != classEnterNode2 || !z) {
                    controlFlowGraphBuilder2.addEdgeToSubGraph(cFGNode3, controlFlowGraph7.getEnterNode());
                }
            }
            addEdge$default(controlFlowGraphBuilder2, controlFlowGraph7.getExitNode(), pair3, false, false, pair3.isUnion() ? EdgeKind.Forward : EdgeKind.CfgForward, null, 44, null);
            controlFlowGraphBuilder2 = this;
            linkedHashMap5 = linkedHashMap2;
            linkedHashMap6 = linkedHashMap3;
            linkedHashMap4 = linkedHashMap;
            firControlFlowGraphOwner2 = firControlFlowGraphOwner;
            cFGNode2 = cFGNode;
        }
        final LinkedHashMap linkedHashMap7 = linkedHashMap5;
        LinkedHashMap linkedHashMap8 = linkedHashMap4;
        final LinkedHashMap linkedHashMap9 = linkedHashMap6;
        if (firControlFlowGraphOwner2 == null && linkedHashMap8.isEmpty()) {
            controlFlowGraphBuilder = this;
            classEnterNode = classEnterNode2;
            addEdge$default(controlFlowGraphBuilder, classEnterNode, pair3, false, false, EdgeKind.CfgForward, null, 44, null);
        } else {
            classEnterNode = classEnterNode2;
            controlFlowGraphBuilder = this;
            addEdge$default(controlFlowGraphBuilder, classEnterNode, pair3, false, false, EdgeKind.DeadForward, null, 40, null);
        }
        Iterator<ControlFlowGraph> it = arrayList2.iterator();
        while (it.hasNext()) {
            controlFlowGraphBuilder.addEdgeToSubGraph(pair3, it.next().getEnterNode());
        }
        List listSortedWith = CollectionsKt.sortedWith(linkedHashMap8.entrySet(), new Comparator() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphBuilder$exitClass$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(ControlFlowGraphBuilder.exitClass$computeDelegationLevel((FirConstructor) ((Map.Entry) t).getKey(), linkedHashMap9, linkedHashMap7)), Integer.valueOf(ControlFlowGraphBuilder.exitClass$computeDelegationLevel((FirConstructor) ((Map.Entry) t2).getKey(), linkedHashMap9, linkedHashMap7)));
            }
        });
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10));
        Iterator it2 = listSortedWith.iterator();
        while (it2.hasNext()) {
            arrayList3.add((ControlFlowGraph) ((Map.Entry) it2.next()).getValue());
        }
        classEnterNode.setSubGraphs(CollectionsKt.plus(arrayList, arrayList3));
        pair3.setSubGraphs(arrayList2);
        return TuplesKt.to(pair3.isUnion() ? pair3 : pair4, controlFlowGraphBuilder.popGraph());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Pair<CodeFragmentExitNode, ControlFlowGraph> exitCodeFragment() throws UninitializedPropertyAccessException {
        ControlFlowGraph controlFlowGraphPop = this.graphs.pop();
        CFGNode<?> exitNode = controlFlowGraphPop.getExitNode();
        if (exitNode == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.resolve.dfa.cfg.CodeFragmentExitNode");
            return null;
        }
        CodeFragmentExitNode codeFragmentExitNode = (CodeFragmentExitNode) exitNode;
        popAndAddEdge$default(this, codeFragmentExitNode, null, 2, null);
        if (codeFragmentExitNode.getPreviousNodes().size() > 1) {
            codeFragmentExitNode.updateDeadStatus();
        }
        controlFlowGraphPop.complete();
        return TuplesKt.to(codeFragmentExitNode, controlFlowGraphPop);
    }

    public final ComparisonExpressionNode exitComparisonExpression(FirComparisonExpression comparisonExpression) {
        comparisonExpression.getClass();
        ComparisonExpressionNode comparisonExpressionNodeCreateComparisonExpressionNode = ControlFlowGraphNodeBuilderKt.createComparisonExpressionNode(this, comparisonExpression);
        addNewSimpleNode$default(this, comparisonExpressionNodeCreateComparisonExpressionNode, false, 2, null);
        return comparisonExpressionNodeCreateComparisonExpressionNode;
    }

    public final DelegateExpressionExitNode exitDelegateExpression(FirExpression fir) {
        fir.getClass();
        DelegateExpressionExitNode delegateExpressionExitNodeCreateDelegateExpressionExitNode = ControlFlowGraphNodeBuilderKt.createDelegateExpressionExitNode(this, fir);
        unifyDataFlowFromPostponedLambdas(delegateExpressionExitNodeCreateDelegateExpressionExitNode, true);
        addNewSimpleNode$default(this, delegateExpressionExitNodeCreateDelegateExpressionExitNode, false, 2, null);
        return delegateExpressionExitNodeCreateDelegateExpressionExitNode;
    }

    public final DelegatedConstructorCallNode exitDelegatedConstructorCall(FirDelegatedConstructorCall call, boolean callCompleted) {
        call.getClass();
        DelegatedConstructorCallNode delegatedConstructorCallNodeCreateDelegatedConstructorCallNode = ControlFlowGraphNodeBuilderKt.createDelegatedConstructorCallNode(this, call);
        unifyDataFlowFromPostponedLambdas(delegatedConstructorCallNodeCreateDelegatedConstructorCallNode, callCompleted);
        addNewSimpleNode$default(this, delegatedConstructorCallNodeCreateDelegatedConstructorCallNode, false, 2, null);
        return delegatedConstructorCallNodeCreateDelegatedConstructorCallNode;
    }

    public final Pair<LoopConditionExitNode, LoopExitNode> exitDoWhileLoop(FirLoop loop) {
        loop.getClass();
        this.loopConditionEnterNodes.remove(loop);
        LoopConditionExitNode loopConditionExitNodeCreateLoopConditionExitNode = ControlFlowGraphNodeBuilderKt.createLoopConditionExitNode(this, loop.getCondition(), loop);
        Boolean booleanLiteralValue = ControlFlowGraphBuilderKt.getBooleanLiteralValue(loop.getCondition());
        popAndAddEdge$default(this, loopConditionExitNodeCreateLoopConditionExitNode, null, 2, null);
        CFGNode<?> cFGNodePop = this.lastNodes.pop();
        if (!(cFGNodePop instanceof LoopBlockEnterNode)) {
            w01.a("Failed requirement.");
            return null;
        }
        addBackEdge$default(this, loopConditionExitNodeCreateLoopConditionExitNode, cFGNodePop, Intrinsics.areEqual(booleanLiteralValue, Boolean.FALSE), null, 8, null);
        LoopExitNode loopExitNodeRemove = this.loopExitNodes.remove(loop);
        loopExitNodeRemove.getClass();
        LoopExitNode loopExitNode = loopExitNodeRemove;
        addEdge$default(this, loopConditionExitNodeCreateLoopConditionExitNode, loopExitNode, false, Intrinsics.areEqual(booleanLiteralValue, Boolean.TRUE), null, null, 48, null);
        loopExitNode.updateDeadStatus();
        this.lastNodes.push(loopExitNode);
        return TuplesKt.to(loopConditionExitNodeCreateLoopConditionExitNode, loopExitNode);
    }

    public final ElvisExitNode exitElvis(boolean lhsIsNotNull, boolean callCompleted) {
        ElvisExitNode elvisExitNodePop = this.exitElvisExpressionNodes.pop();
        boolean z = callCompleted && ControlFlowGraphBuilderKt.getHasNothingType(elvisExitNodePop.getFir());
        if (z) {
            addNonSuccessfullyTerminatingNode(elvisExitNodePop);
        } else {
            addNewSimpleNode(elvisExitNodePop, lhsIsNotNull);
        }
        mergeDataFlowFromPostponedLambdas(elvisExitNodePop, callCompleted);
        if (!z) {
            elvisExitNodePop.updateDeadStatus();
        }
        return elvisExitNodePop;
    }

    public final Triple<ElvisLhsExitNode, ElvisLhsIsNotNullNode, ElvisRhsEnterNode> exitElvisLhs(FirElvisExpression elvisExpression) {
        elvisExpression.getClass();
        ElvisExitNode elvisExitNodeCreateElvisExitNode = ControlFlowGraphNodeBuilderKt.createElvisExitNode(this, elvisExpression);
        this.exitElvisExpressionNodes.push(elvisExitNodeCreateElvisExitNode);
        ElvisLhsExitNode elvisLhsExitNodeCreateElvisLhsExitNode = ControlFlowGraphNodeBuilderKt.createElvisLhsExitNode(this, elvisExpression);
        popAndAddEdge$default(this, elvisLhsExitNodeCreateElvisLhsExitNode, null, 2, null);
        ElvisLhsIsNotNullNode elvisLhsIsNotNullNodeCreateElvisLhsIsNotNullNode = ControlFlowGraphNodeBuilderKt.createElvisLhsIsNotNullNode(this, elvisExpression);
        ConeKotlinType coneTypeOrNull = elvisExpression.getLhs().getConeTypeOrNull();
        addEdge$default(this, elvisLhsExitNodeCreateElvisLhsExitNode, elvisLhsIsNotNullNodeCreateElvisLhsIsNotNullNode, false, coneTypeOrNull != null && ConeBuiltinTypeUtilsKt.isNullableNothing(coneTypeOrNull), null, null, 52, null);
        addEdge$default(this, elvisLhsIsNotNullNodeCreateElvisLhsIsNotNullNode, elvisExitNodeCreateElvisExitNode, false, false, null, null, 56, null);
        ElvisRhsEnterNode elvisRhsEnterNodePop = this.elvisRhsEnterNodes.pop();
        addEdge$default(this, elvisLhsExitNodeCreateElvisLhsExitNode, elvisRhsEnterNodePop, elvisRhsEnterNodePop.getPreviousNodes().isEmpty(), false, null, null, 56, null);
        this.lastNodes.push(elvisRhsEnterNodePop);
        return new Triple<>(elvisLhsExitNodeCreateElvisLhsExitNode, elvisLhsIsNotNullNodeCreateElvisLhsIsNotNullNode, elvisRhsEnterNodePop);
    }

    public final Pair<CFGNode<?>, EqualityOperatorCallNode> exitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, boolean callCompleted) {
        equalityOperatorCall.getClass();
        CFGNode<?> cFGNodePop = this.equalityOperatorCallLhsExitNodes.pop();
        EqualityOperatorCallNode equalityOperatorCallNodeCreateEqualityOperatorCallNode = ControlFlowGraphNodeBuilderKt.createEqualityOperatorCallNode(this, equalityOperatorCall);
        unifyDataFlowFromPostponedLambdas(equalityOperatorCallNodeCreateEqualityOperatorCallNode, callCompleted);
        addNewSimpleNode$default(this, equalityOperatorCallNodeCreateEqualityOperatorCallNode, false, 2, null);
        return TuplesKt.to(cFGNodePop, equalityOperatorCallNodeCreateEqualityOperatorCallNode);
    }

    public final void exitEqualityOperatorLhs() {
        this.equalityOperatorCallLhsExitNodes.push(getLastNode());
    }

    public final void exitFakeExpression() {
        this.lastNodes.pop();
        this.graphs.pop().getKind();
        ControlFlowGraph.Kind kind = ControlFlowGraph.Kind.File;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Pair<FieldInitializerExitNode, ControlFlowGraph> exitField(FirField field) throws UninitializedPropertyAccessException {
        field.getClass();
        if (!ControlFlowGraphBuilderKt.getMemberShouldHaveGraph(field)) {
            return null;
        }
        ControlFlowGraph controlFlowGraphPop = this.graphs.pop();
        CFGNode<?> exitNode = controlFlowGraphPop.getExitNode();
        if (exitNode == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.resolve.dfa.cfg.FieldInitializerExitNode");
            return null;
        }
        FieldInitializerExitNode fieldInitializerExitNode = (FieldInitializerExitNode) exitNode;
        popAndAddEdge$default(this, fieldInitializerExitNode, null, 2, null);
        if (fieldInitializerExitNode.getPreviousNodes().size() > 1) {
            fieldInitializerExitNode.updateDeadStatus();
        }
        controlFlowGraphPop.complete();
        return TuplesKt.to(fieldInitializerExitNode, controlFlowGraphPop);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Pair<FileExitNode, ControlFlowGraph> exitFile() throws UninitializedPropertyAccessException {
        FirControlFlowGraphReference controlFlowGraphReference;
        FirControlFlowGraphReference controlFlowGraphReference2;
        FirControlFlowGraphReference controlFlowGraphReference3;
        ControlFlowGraph controlFlowGraph;
        getCurrentGraph().getKind();
        ControlFlowGraph.Kind kind = ControlFlowGraph.Kind.File;
        if (getCurrentGraph().getDeclaration() == null) {
            this.graphs.pop();
            return TuplesKt.to(null, null);
        }
        CFGNode<?> cFGNodePop = this.lastNodes.pop();
        cFGNodePop.getClass();
        FileEnterNode fileEnterNode = (FileEnterNode) cFGNodePop;
        CFGNode<?> exitNode = getCurrentGraph().getExitNode();
        exitNode.getClass();
        FileExitNode fileExitNode = (FileExitNode) exitNode;
        ArrayList arrayList = new ArrayList();
        for (FirAnnotationContainer firAnnotationContainer : fileEnterNode.getFir().getDeclarations()) {
            if (firAnnotationContainer instanceof FirControlFlowGraphOwner) {
                FirControlFlowGraphOwner firControlFlowGraphOwner = (FirControlFlowGraphOwner) firAnnotationContainer;
                if (ControlFlowGraphBuilderKt.getMemberShouldHaveGraph(firControlFlowGraphOwner) && (controlFlowGraphReference3 = firControlFlowGraphOwner.getControlFlowGraphReference()) != null && (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference3)) != null && (firControlFlowGraphOwner instanceof FirProperty)) {
                    arrayList.add(controlFlowGraph);
                }
            }
            if (firAnnotationContainer instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) firAnnotationContainer;
                FirPropertyAccessor getter = firProperty.getGetter();
                if (getter != null && (controlFlowGraphReference2 = getter.getControlFlowGraphReference()) != null) {
                    FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference2);
                }
                FirPropertyAccessor setter = firProperty.getSetter();
                if (setter != null && (controlFlowGraphReference = setter.getControlFlowGraphReference()) != null) {
                    FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference);
                }
            }
        }
        CFGNode<?> exitNode2 = fileEnterNode;
        for (ControlFlowGraph controlFlowGraph2 : arrayList) {
            CFGNode.Companion.addEdge$default(CFGNode.INSTANCE, exitNode2, controlFlowGraph2.getEnterNode(), EdgeKind.CfgForward, false, null, 16, null);
            exitNode2 = controlFlowGraph2.getExitNode();
        }
        addEdge$default(this, exitNode2, fileExitNode, false, false, EdgeKind.CfgForward, null, 40, null);
        if (!arrayList.isEmpty()) {
            addEdge$default(this, fileEnterNode, fileExitNode, false, false, EdgeKind.DeadForward, null, 40, null);
        }
        fileEnterNode.setSubGraphs(arrayList);
        return TuplesKt.to(fileExitNode, popGraph());
    }

    public final FinallyBlockExitNode exitFinallyBlock() {
        FinallyBlockEnterNode pVar = this.finallyBlocksInProgress.top();
        TryExpressionExitNode pVar2 = this.tryExitNodes.top();
        FinallyBlockExitNode finallyBlockExitNodeCreateFinallyBlockExitNode = ControlFlowGraphNodeBuilderKt.createFinallyBlockExitNode(this, pVar);
        popAndAddEdge$default(this, finallyBlockExitNodeCreateFinallyBlockExitNode, null, 2, null);
        addEdge$default(this, finallyBlockExitNodeCreateFinallyBlockExitNode, pVar2, false, getAllNormalInputsAreDead(pVar), null, null, 52, null);
        int iLevelOfNextExceptionCatchingGraph = levelOfNextExceptionCatchingGraph();
        FinallyBlockEnterNode finallyBlockEnterNode = (FinallyBlockEnterNode) StackKt.topOrNull(this.finallyEnterNodes);
        FinallyBlockEnterNode finallyBlockEnterNode2 = (finallyBlockEnterNode == null || finallyBlockEnterNode.getLevel() <= iLevelOfNextExceptionCatchingGraph) ? null : finallyBlockEnterNode;
        if (finallyBlockEnterNode2 != null) {
            addEdge$default(this, finallyBlockExitNodeCreateFinallyBlockExitNode, finallyBlockEnterNode2, false, false, null, UncaughtExceptionPath.INSTANCE, 24, null);
        }
        List<CFGNode<?>> previousNodes = pVar.getPreviousNodes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(previousNodes, 10));
        Iterator<T> it = previousNodes.iterator();
        while (it.hasNext()) {
            arrayList.add(((CFGNode) it.next()).edgeTo(pVar).getLabel());
        }
        Set set = CollectionsKt.toSet(arrayList);
        if (finallyBlockEnterNode2 != null) {
            iLevelOfNextExceptionCatchingGraph = finallyBlockEnterNode2.getLevel();
        }
        Collection<FunctionExitNode> collectionValues = this.exitTargetsForReturn.values();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : collectionValues) {
            if (set.contains((FunctionExitNode) obj)) {
                arrayList2.add(obj);
            }
        }
        addReturnEdges(finallyBlockExitNodeCreateFinallyBlockExitNode, arrayList2, iLevelOfNextExceptionCatchingGraph);
        Collection<LoopConditionEnterNode> collectionValues2 = this.loopConditionEnterNodes.values();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : collectionValues2) {
            if (set.contains((LoopConditionEnterNode) obj2)) {
                arrayList3.add(obj2);
            }
        }
        addReturnEdges(finallyBlockExitNodeCreateFinallyBlockExitNode, arrayList3, iLevelOfNextExceptionCatchingGraph);
        Collection<LoopExitNode> collectionValues3 = this.loopExitNodes.values();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : collectionValues3) {
            if (set.contains((LoopExitNode) obj3)) {
                arrayList4.add(obj3);
            }
        }
        addReturnEdges(finallyBlockExitNodeCreateFinallyBlockExitNode, arrayList4, iLevelOfNextExceptionCatchingGraph);
        return finallyBlockExitNodeCreateFinallyBlockExitNode;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Pair<FunctionExitNode, ControlFlowGraph> exitFunction(FirFunction function) throws UninitializedPropertyAccessException {
        function.getClass();
        if (function instanceof FirAnonymousFunction) {
            w01.a("Failed requirement.");
            return null;
        }
        this.exitTargetsForReturn.remove(function.getSymbol());
        ControlFlowGraph controlFlowGraphPop = this.graphs.pop();
        CFGNode<?> exitNode = controlFlowGraphPop.getExitNode();
        if (exitNode == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionExitNode");
            return null;
        }
        FunctionExitNode functionExitNode = (FunctionExitNode) exitNode;
        popAndAddEdge$default(this, functionExitNode, null, 2, null);
        if (functionExitNode.getPreviousNodes().size() > 1) {
            functionExitNode.updateDeadStatus();
        }
        controlFlowGraphPop.complete();
        return TuplesKt.to(functionExitNode, controlFlowGraphPop);
    }

    public final FunctionCallExitNode exitFunctionCall(FirFunctionCall functionCall, boolean callCompleted) {
        List list;
        functionCall.getClass();
        boolean hasNothingType = ControlFlowGraphBuilderKt.getHasNothingType(functionCall);
        FunctionCallExitNode functionCallExitNodeCreateFunctionCallExitNode = ControlFlowGraphNodeBuilderKt.createFunctionCallExitNode(this, functionCall);
        unifyDataFlowFromPostponedLambdas(functionCallExitNodeCreateFunctionCallExitNode, callCompleted);
        if (hasNothingType) {
            addNonSuccessfullyTerminatingNode(functionCallExitNodeCreateFunctionCallExitNode);
        } else {
            addNewSimpleNode$default(this, functionCallExitNodeCreateFunctionCallExitNode, false, 2, null);
        }
        if (!hasNothingType && !callCompleted && (list = (List) StackKt.topOrNull(this.notCompletedFunctionCalls)) != null) {
            list.add(functionCallExitNodeCreateFunctionCallExitNode);
        }
        return functionCallExitNodeCreateFunctionCallExitNode;
    }

    public final GetClassCallNode exitGetClassCall(FirGetClassCall getClassCall) {
        getClassCall.getClass();
        GetClassCallNode getClassCallNodeCreateGetClassCallNode = ControlFlowGraphNodeBuilderKt.createGetClassCallNode(this, getClassCall);
        addNewSimpleNode$default(this, getClassCallNodeCreateGetClassCallNode, false, 2, null);
        return getClassCallNodeCreateGetClassCallNode;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Pair<InitBlockExitNode, ControlFlowGraph> exitInitBlock() throws UninitializedPropertyAccessException {
        ControlFlowGraph controlFlowGraphPop = this.graphs.pop();
        CFGNode<?> exitNode = controlFlowGraphPop.getExitNode();
        if (exitNode == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.resolve.dfa.cfg.InitBlockExitNode");
            return null;
        }
        InitBlockExitNode initBlockExitNode = (InitBlockExitNode) exitNode;
        popAndAddEdge$default(this, initBlockExitNode, null, 2, null);
        if (initBlockExitNode.getPreviousNodes().size() > 1) {
            initBlockExitNode.updateDeadStatus();
        }
        controlFlowGraphPop.complete();
        return TuplesKt.to(initBlockExitNode, controlFlowGraphPop);
    }

    public final JumpNode exitJump(FirJump<?> jump) {
        CFGNode<?> cFGNode;
        jump.getClass();
        JumpNode jumpNodeCreateJumpNode = ControlFlowGraphNodeBuilderKt.createJumpNode(this, jump);
        addNonSuccessfullyTerminatingNode(jumpNodeCreateJumpNode);
        boolean z = jump instanceof FirReturnExpression;
        if (z) {
            FirReturnExpression firReturnExpression = (FirReturnExpression) jump;
            if (firReturnExpression.getTarget().getLabeledElement() instanceof FirAnonymousFunction) {
                jumpDataFlowFromPostponedLambdas(((FirFunction) firReturnExpression.getTarget().getLabeledElement()).getSymbol());
            }
        }
        FinallyBlockEnterNode finallyBlockEnterNode = null;
        if (z) {
            cFGNode = this.exitTargetsForReturn.get(((FirFunction) ((FirReturnExpression) jump).getTarget().getLabeledElement()).getSymbol());
        } else if (jump instanceof FirContinueExpression) {
            cFGNode = this.loopConditionEnterNodes.get(((FirContinueExpression) jump).getTarget().getLabeledElement());
        } else {
            if (!(jump instanceof FirBreakExpression)) {
                z01.a("Unknown jump type: ", UtilsKt.render(jump));
                return null;
            }
            cFGNode = this.loopExitNodes.get(((FirBreakExpression) jump).getTarget().getLabeledElement());
        }
        if (cFGNode == null) {
            return jumpNodeCreateJumpNode;
        }
        FinallyBlockEnterNode finallyBlockEnterNode2 = (FinallyBlockEnterNode) StackKt.topOrNull(this.finallyEnterNodes);
        if (finallyBlockEnterNode2 != null && finallyBlockEnterNode2.getLevel() > cFGNode.getLevel()) {
            finallyBlockEnterNode = finallyBlockEnterNode2;
        }
        if (finallyBlockEnterNode != null) {
            addEdge$default(this, jumpNodeCreateJumpNode, finallyBlockEnterNode, false, false, null, (EdgeLabel) cFGNode, 24, null);
            this.nonDirectJumps.put(cFGNode, jumpNodeCreateJumpNode);
            return jumpNodeCreateJumpNode;
        }
        CFGNode<?> cFGNode2 = cFGNode;
        if (getReturnPathIsBackwards(cFGNode2)) {
            addBackEdge$default(this, jumpNodeCreateJumpNode, cFGNode2, false, null, 12, null);
            return jumpNodeCreateJumpNode;
        }
        addEdge$default(this, jumpNodeCreateJumpNode, cFGNode2, false, false, null, null, 56, null);
        return jumpNodeCreateJumpNode;
    }

    public final Pair<CFGNode<FirBooleanOperatorExpression>, CFGNode<FirBooleanOperatorExpression>> exitLeftBooleanOperatorExpressionArgument(FirBooleanOperatorExpression booleanOperatorExpression) {
        booleanOperatorExpression.getClass();
        Pair pair = TuplesKt.to(ControlFlowGraphNodeBuilderKt.createBooleanOperatorExitLeftOperandNode(this, booleanOperatorExpression), ControlFlowGraphNodeBuilderKt.createBooleanOperatorEnterRightOperandNode(this, booleanOperatorExpression));
        BooleanOperatorExitLeftOperandNode booleanOperatorExitLeftOperandNode = (BooleanOperatorExitLeftOperandNode) pair.component1();
        BooleanOperatorEnterRightOperandNode booleanOperatorEnterRightOperandNode = (BooleanOperatorEnterRightOperandNode) pair.component2();
        addNewSimpleNode$default(this, booleanOperatorExitLeftOperandNode, false, 2, null);
        this.lastNodes.push(booleanOperatorExitLeftOperandNode);
        addNewSimpleNode(booleanOperatorEnterRightOperandNode, Intrinsics.areEqual(ControlFlowGraphBuilderKt.getBooleanLiteralValue(booleanOperatorExpression.getLeftOperand()), Boolean.valueOf(booleanOperatorExpression.getKind() != LogicOperationKind.AND)));
        return TuplesKt.to(booleanOperatorExitLeftOperandNode, booleanOperatorEnterRightOperandNode);
    }

    public final LiteralExpressionNode exitLiteralExpression(FirLiteralExpression literalExpression) {
        literalExpression.getClass();
        LiteralExpressionNode literalExpressionNodeCreateLiteralExpressionNode = ControlFlowGraphNodeBuilderKt.createLiteralExpressionNode(this, literalExpression);
        addNewSimpleNode$default(this, literalExpressionNodeCreateLiteralExpressionNode, false, 2, null);
        return literalExpressionNodeCreateLiteralExpressionNode;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Pair<PropertyInitializerExitNode, ControlFlowGraph> exitProperty(FirProperty property) throws UninitializedPropertyAccessException {
        property.getClass();
        if (!ControlFlowGraphBuilderKt.getMemberShouldHaveGraph(property)) {
            return null;
        }
        ControlFlowGraph controlFlowGraphPop = this.graphs.pop();
        CFGNode<?> exitNode = controlFlowGraphPop.getExitNode();
        if (exitNode == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.resolve.dfa.cfg.PropertyInitializerExitNode");
            return null;
        }
        PropertyInitializerExitNode propertyInitializerExitNode = (PropertyInitializerExitNode) exitNode;
        popAndAddEdge$default(this, propertyInitializerExitNode, null, 2, null);
        if (propertyInitializerExitNode.getPreviousNodes().size() > 1) {
            propertyInitializerExitNode.updateDeadStatus();
        }
        controlFlowGraphPop.complete();
        return TuplesKt.to(propertyInitializerExitNode, controlFlowGraphPop);
    }

    public final QualifiedAccessNode exitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression) {
        qualifiedAccessExpression.getClass();
        boolean hasNothingType = ControlFlowGraphBuilderKt.getHasNothingType(qualifiedAccessExpression);
        QualifiedAccessNode qualifiedAccessNodeCreateQualifiedAccessNode = ControlFlowGraphNodeBuilderKt.createQualifiedAccessNode(this, qualifiedAccessExpression);
        if (hasNothingType) {
            addNonSuccessfullyTerminatingNode(qualifiedAccessNodeCreateQualifiedAccessNode);
            return qualifiedAccessNodeCreateQualifiedAccessNode;
        }
        addNewSimpleNode$default(this, qualifiedAccessNodeCreateQualifiedAccessNode, false, 2, null);
        return qualifiedAccessNodeCreateQualifiedAccessNode;
    }

    public final ResolvedQualifierNode exitResolvedQualifierNode(FirResolvedQualifier resolvedQualifier) {
        resolvedQualifier.getClass();
        ResolvedQualifierNode resolvedQualifierNodeCreateResolvedQualifierNode = ControlFlowGraphNodeBuilderKt.createResolvedQualifierNode(this, resolvedQualifier);
        addNewSimpleNode$default(this, resolvedQualifierNodeCreateResolvedQualifierNode, false, 2, null);
        return resolvedQualifierNodeCreateResolvedQualifierNode;
    }

    public final ExitSafeCallNode exitSafeCall() {
        ExitSafeCallNode exitSafeCallNodePop = this.exitSafeCallNodes.pop();
        addNewSimpleNode$default(this, exitSafeCallNodePop, false, 2, null);
        mergeDataFlowFromPostponedLambdas(exitSafeCallNodePop, false);
        exitSafeCallNodePop.updateDeadStatus();
        return exitSafeCallNodePop;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final Pair<ScriptExitNode, ControlFlowGraph> exitScript() throws UninitializedPropertyAccessException, KotlinIllegalArgumentExceptionWithAttachments {
        FirControlFlowGraphReference controlFlowGraphReference;
        ControlFlowGraph controlFlowGraph;
        FirControlFlowGraphReference controlFlowGraphReference2;
        ControlFlowGraph controlFlowGraph2;
        FirControlFlowGraphReference controlFlowGraphReference3;
        ControlFlowGraph controlFlowGraph3;
        if (getCurrentGraph().getKind() != ControlFlowGraph.Kind.Script) {
            w01.a("Failed requirement.");
            return null;
        }
        if (getCurrentGraph().getDeclaration() == null) {
            this.graphs.pop();
            return TuplesKt.to(null, null);
        }
        CFGNode<?> cFGNodePop = this.lastNodes.pop();
        cFGNodePop.getClass();
        ScriptEnterNode scriptEnterNode = (ScriptEnterNode) cFGNodePop;
        CFGNode<?> exitNode = getCurrentGraph().getExitNode();
        exitNode.getClass();
        ScriptExitNode scriptExitNode = (ScriptExitNode) exitNode;
        FirScript fir = scriptEnterNode.getFir();
        fir.getClass();
        if (!(fir.getControlFlowGraphReference() == null)) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected state: script already has a CFG attached");
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "script", fir);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        ArrayList<ControlFlowGraph> arrayList = new ArrayList();
        for (FirAnnotationContainer firAnnotationContainer : fir.getDeclarations()) {
            if (firAnnotationContainer instanceof FirControlFlowGraphOwner) {
                FirControlFlowGraphOwner firControlFlowGraphOwner = (FirControlFlowGraphOwner) firAnnotationContainer;
                if (ControlFlowGraphBuilderKt.getMemberShouldHaveGraph(firControlFlowGraphOwner) && (controlFlowGraphReference3 = firControlFlowGraphOwner.getControlFlowGraphReference()) != null && (controlFlowGraph3 = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference3)) != null && ControlFlowGraphBuilderKt.isUsedInControlFlowGraphBuilderForScript(firControlFlowGraphOwner)) {
                    arrayList.add(controlFlowGraph3);
                }
            }
            if (firAnnotationContainer instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) firAnnotationContainer;
                FirPropertyAccessor getter = firProperty.getGetter();
                if (getter != null && (controlFlowGraphReference2 = getter.getControlFlowGraphReference()) != null && (controlFlowGraph2 = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference2)) != null && ControlFlowGraphBuilderKt.isUsedInControlFlowGraphBuilderForScript(getter)) {
                    arrayList.add(controlFlowGraph2);
                }
                FirPropertyAccessor setter = firProperty.getSetter();
                if (setter != null && (controlFlowGraphReference = setter.getControlFlowGraphReference()) != null && (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) != null && ControlFlowGraphBuilderKt.isUsedInControlFlowGraphBuilderForScript(setter)) {
                    arrayList.add(controlFlowGraph);
                }
            }
        }
        CFGNode<?> exitNode2 = scriptEnterNode;
        for (ControlFlowGraph controlFlowGraph4 : arrayList) {
            if (exitNode2 != scriptEnterNode || ((ScriptEnterNode) exitNode2).getPreviousNodes().isEmpty()) {
                addEdgeToSubGraph(exitNode2, controlFlowGraph4.getEnterNode());
            }
            exitNode2 = controlFlowGraph4.getExitNode();
        }
        addEdge$default(this, exitNode2, scriptExitNode, false, false, EdgeKind.CfgForward, null, 40, null);
        if (!arrayList.isEmpty()) {
            addEdge$default(this, scriptEnterNode, scriptExitNode, false, false, EdgeKind.DeadForward, null, 40, null);
        }
        scriptEnterNode.setSubGraphs(arrayList);
        return TuplesKt.to(scriptExitNode, popGraph());
    }

    public final SmartCastExpressionExitNode exitSmartCastExpression(FirSmartCastExpression smartCastExpression) {
        smartCastExpression.getClass();
        SmartCastExpressionExitNode smartCastExpressionExitNodeCreateSmartCastExitNode = ControlFlowGraphNodeBuilderKt.createSmartCastExitNode(this, smartCastExpression);
        addNewSimpleNode$default(this, smartCastExpressionExitNodeCreateSmartCastExitNode, false, 2, null);
        return smartCastExpressionExitNodeCreateSmartCastExitNode;
    }

    public final StringConcatenationCallNode exitStringConcatenationCall(FirStringConcatenationCall call) {
        call.getClass();
        StringConcatenationCallNode stringConcatenationCallNodeCreateStringConcatenationCallNode = ControlFlowGraphNodeBuilderKt.createStringConcatenationCallNode(this, call);
        unifyDataFlowFromPostponedLambdas(stringConcatenationCallNodeCreateStringConcatenationCallNode, true);
        addNewSimpleNode$default(this, stringConcatenationCallNodeCreateStringConcatenationCallNode, false, 2, null);
        return stringConcatenationCallNodeCreateStringConcatenationCallNode;
    }

    public final ThrowExceptionNode exitThrowExceptionNode(FirThrowExpression throwExpression) {
        throwExpression.getClass();
        ThrowExceptionNode throwExceptionNodeCreateThrowExceptionNode = ControlFlowGraphNodeBuilderKt.createThrowExceptionNode(this, throwExpression);
        addNonSuccessfullyTerminatingNode(throwExceptionNodeCreateThrowExceptionNode);
        return throwExceptionNodeCreateThrowExceptionNode;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x006c  */
    public final TryExpressionExitNode exitTryExpression(boolean callCompleted) {
        boolean z;
        ControlFlowGraphBuilder controlFlowGraphBuilder;
        Iterator<T> it = this.notCompletedFunctionCalls.pop().iterator();
        loop0: while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                if (completeFunctionCall((FunctionCallExitNode) it.next()) || z) {
                    z = true;
                }
            }
        }
        TryExpressionExitNode tryExpressionExitNodePop = this.tryExitNodes.pop();
        if (tryExpressionExitNodePop.getFir().getFinallyBlock() != null) {
            FinallyBlockEnterNode finallyBlockEnterNodePop = this.finallyBlocksInProgress.pop();
            this.finallyBlocksInProgressSet.remove(finallyBlockEnterNodePop.getFir());
            if (z && getAllNormalInputsAreDead(finallyBlockEnterNodePop)) {
                CFGNode cFGNode = (CFGNode) CollectionsKt.single(tryExpressionExitNodePop.getPreviousNodes());
                CFGNode.INSTANCE.removeAllIncomingEdges(tryExpressionExitNodePop);
                controlFlowGraphBuilder = this;
                addEdge$default(controlFlowGraphBuilder, cFGNode, tryExpressionExitNodePop, false, true, null, null, 52, null);
            } else {
                controlFlowGraphBuilder = this;
            }
        } else {
            controlFlowGraphBuilder = this;
        }
        controlFlowGraphBuilder.mergeDataFlowFromPostponedLambdas(tryExpressionExitNodePop, callCompleted);
        tryExpressionExitNodePop.updateDeadStatus();
        controlFlowGraphBuilder.lastNodes.push(tryExpressionExitNodePop);
        return tryExpressionExitNodePop;
    }

    public final TryMainBlockExitNode exitTryMainBlock() {
        FinallyBlockEnterNode pVar = this.tryExitNodes.top();
        TryMainBlockExitNode tryMainBlockExitNodeCreateTryMainBlockExitNode = ControlFlowGraphNodeBuilderKt.createTryMainBlockExitNode(this, pVar.getFir());
        popAndAddEdge$default(this, tryMainBlockExitNodeCreateTryMainBlockExitNode, null, 2, null);
        if (tryMainBlockExitNodeCreateTryMainBlockExitNode.getFir().getFinallyBlock() != null) {
            pVar = this.finallyEnterNodes.top();
        }
        addEdge$default(this, tryMainBlockExitNodeCreateTryMainBlockExitNode, pVar, false, false, null, null, 56, null);
        for (CatchClauseEnterNode catchClauseEnterNode : CollectionsKt.asReversed(this.catchNodes.pop())) {
            this.catchBlocksInProgress.push(catchClauseEnterNode);
            addEdge$default(this, tryMainBlockExitNodeCreateTryMainBlockExitNode, catchClauseEnterNode, false, false, null, null, 56, null);
        }
        return tryMainBlockExitNodeCreateTryMainBlockExitNode;
    }

    public final TypeOperatorCallNode exitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall) {
        typeOperatorCall.getClass();
        TypeOperatorCallNode typeOperatorCallNodeCreateTypeOperatorCallNode = ControlFlowGraphNodeBuilderKt.createTypeOperatorCallNode(this, typeOperatorCall);
        addNewSimpleNode$default(this, typeOperatorCallNodeCreateTypeOperatorCallNode, false, 2, null);
        return typeOperatorCallNodeCreateTypeOperatorCallNode;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Triple<ExitDefaultArgumentsNode, ExitValueParameterNode, ControlFlowGraph> exitValueParameter(FirValueParameter valueParameter) throws UninitializedPropertyAccessException {
        valueParameter.getClass();
        if (valueParameter.getDefaultValue() != null && valueParameter.getValueParameterKind() == FirValueParameterKind.Regular) {
            ControlFlowGraph controlFlowGraphPop = this.graphs.pop();
            CFGNode<?> exitNode = controlFlowGraphPop.getExitNode();
            if (exitNode != null) {
                ExitDefaultArgumentsNode exitDefaultArgumentsNode = (ExitDefaultArgumentsNode) exitNode;
                popAndAddEdge$default(this, exitDefaultArgumentsNode, null, 2, null);
                if (exitDefaultArgumentsNode.getPreviousNodes().size() > 1) {
                    exitDefaultArgumentsNode.updateDeadStatus();
                }
                controlFlowGraphPop.complete();
                Pair pair = TuplesKt.to(exitDefaultArgumentsNode, controlFlowGraphPop);
                ExitDefaultArgumentsNode exitDefaultArgumentsNode2 = (ExitDefaultArgumentsNode) pair.component1();
                ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pair.component2();
                ExitValueParameterNode exitValueParameterNodeCreateExitValueParameterNode = ControlFlowGraphNodeBuilderKt.createExitValueParameterNode(this, valueParameter);
                addNewSimpleNode$default(this, exitValueParameterNodeCreateExitValueParameterNode, false, 2, null);
                addEdge$default(this, exitDefaultArgumentsNode2, exitValueParameterNodeCreateExitValueParameterNode, false, false, null, null, 56, null);
                return new Triple<>(exitDefaultArgumentsNode2, exitValueParameterNodeCreateExitValueParameterNode, controlFlowGraph);
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.resolve.dfa.cfg.ExitDefaultArgumentsNode");
        }
        return null;
    }

    public final VariableAssignmentNode exitVariableAssignment(FirVariableAssignment assignment) {
        assignment.getClass();
        VariableAssignmentNode variableAssignmentNodeCreateVariableAssignmentNode = ControlFlowGraphNodeBuilderKt.createVariableAssignmentNode(this, assignment);
        addNewSimpleNode$default(this, variableAssignmentNodeCreateVariableAssignmentNode, false, 2, null);
        List list = (List) StackKt.topOrNull(this.catchNodes);
        List list2 = list;
        if (list2 != null && !list2.isEmpty()) {
            EdgeKind edgeKind = ((CatchClauseEnterNode) CollectionsKt.first(list)).getLevel() > levelOfNextExceptionCatchingGraph() ? EdgeKind.Forward : EdgeKind.DfgForward;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                addEdge$default(this, variableAssignmentNodeCreateVariableAssignmentNode, (CatchClauseEnterNode) it.next(), false, false, edgeKind, null, 40, null);
            }
        }
        FinallyBlockEnterNode finallyBlockEnterNode = (FinallyBlockEnterNode) StackKt.topOrNull(this.finallyEnterNodes);
        if (finallyBlockEnterNode != null) {
            addEdge$default(this, variableAssignmentNodeCreateVariableAssignmentNode, finallyBlockEnterNode, false, false, finallyBlockEnterNode.getLevel() > levelOfNextExceptionCatchingGraph() ? EdgeKind.Forward : EdgeKind.DfgForward, UncaughtExceptionPath.INSTANCE, 8, null);
        }
        return variableAssignmentNodeCreateVariableAssignmentNode;
    }

    public final VariableDeclarationExitNode exitVariableDeclaration(FirProperty variable) {
        variable.getClass();
        VariableDeclarationExitNode variableDeclarationExitNodeCreateVariableDeclarationExitNode = ControlFlowGraphNodeBuilderKt.createVariableDeclarationExitNode(this, variable);
        addNewSimpleNode$default(this, variableDeclarationExitNodeCreateVariableDeclarationExitNode, false, 2, null);
        return variableDeclarationExitNodeCreateVariableDeclarationExitNode;
    }

    public final Pair<WhenBranchConditionExitNode, WhenBranchResultEnterNode> exitWhenBranchCondition(FirWhenBranch whenBranch) {
        whenBranch.getClass();
        WhenBranchConditionExitNode whenBranchConditionExitNodeCreateWhenBranchConditionExitNode = ControlFlowGraphNodeBuilderKt.createWhenBranchConditionExitNode(this, whenBranch);
        addNewSimpleNode$default(this, whenBranchConditionExitNodeCreateWhenBranchConditionExitNode, false, 2, null);
        this.lastNodes.push(whenBranchConditionExitNodeCreateWhenBranchConditionExitNode);
        WhenBranchResultEnterNode whenBranchResultEnterNodeCreateWhenBranchResultEnterNode = ControlFlowGraphNodeBuilderKt.createWhenBranchResultEnterNode(this, whenBranch);
        addNewSimpleNode$default(this, whenBranchResultEnterNodeCreateWhenBranchResultEnterNode, false, 2, null);
        return TuplesKt.to(whenBranchConditionExitNodeCreateWhenBranchConditionExitNode, whenBranchResultEnterNodeCreateWhenBranchResultEnterNode);
    }

    public final WhenBranchResultExitNode exitWhenBranchResult(FirWhenBranch whenBranch) {
        whenBranch.getClass();
        WhenBranchResultExitNode whenBranchResultExitNodeCreateWhenBranchResultExitNode = ControlFlowGraphNodeBuilderKt.createWhenBranchResultExitNode(this, whenBranch);
        popAndAddEdge$default(this, whenBranchResultExitNodeCreateWhenBranchResultExitNode, null, 2, null);
        addEdge$default(this, whenBranchResultExitNodeCreateWhenBranchResultExitNode, this.whenExitNodes.top(), false, false, null, null, 56, null);
        return whenBranchResultExitNodeCreateWhenBranchResultExitNode;
    }

    public final Pair<WhenExitNode, WhenSyntheticElseBranchNode> exitWhenExpression(FirWhenExpression whenExpression, boolean callCompleted) {
        ControlFlowGraphBuilder controlFlowGraphBuilder;
        WhenSyntheticElseBranchNode whenSyntheticElseBranchNodeCreateWhenSyntheticElseBranchNode;
        whenExpression.getClass();
        WhenExitNode whenExitNodePop = this.whenExitNodes.pop();
        Iterator<T> it = this.notCompletedFunctionCalls.pop().iterator();
        while (it.hasNext()) {
            completeFunctionCall((FunctionCallExitNode) it.next());
        }
        CFGNode<?> cFGNodePop = this.lastNodes.pop();
        if (ExhaustivenessStatusKt.isProperlyExhaustive(whenExpression)) {
            controlFlowGraphBuilder = this;
            whenSyntheticElseBranchNodeCreateWhenSyntheticElseBranchNode = null;
        } else {
            whenSyntheticElseBranchNodeCreateWhenSyntheticElseBranchNode = ControlFlowGraphNodeBuilderKt.createWhenSyntheticElseBranchNode(this, whenExpression);
            addEdge$default(this, cFGNodePop, whenSyntheticElseBranchNodeCreateWhenSyntheticElseBranchNode, false, false, null, null, 60, null);
            controlFlowGraphBuilder = this;
            addEdge$default(controlFlowGraphBuilder, whenSyntheticElseBranchNodeCreateWhenSyntheticElseBranchNode, whenExitNodePop, false, false, null, null, 60, null);
        }
        controlFlowGraphBuilder.mergeDataFlowFromPostponedLambdas(whenExitNodePop, callCompleted);
        whenExitNodePop.updateDeadStatus();
        controlFlowGraphBuilder.lastNodes.push(whenExitNodePop);
        return TuplesKt.to(whenExitNodePop, whenSyntheticElseBranchNodeCreateWhenSyntheticElseBranchNode);
    }

    public final WhenSubjectExpressionExitNode exitWhenSubjectExpression(FirWhenSubjectExpression expression) {
        expression.getClass();
        WhenSubjectExpressionExitNode whenSubjectExpressionExitNodeCreateWhenSubjectExpressionExitNode = ControlFlowGraphNodeBuilderKt.createWhenSubjectExpressionExitNode(this, expression);
        addNewSimpleNode$default(this, whenSubjectExpressionExitNodeCreateWhenSubjectExpressionExitNode, false, 2, null);
        return whenSubjectExpressionExitNodeCreateWhenSubjectExpressionExitNode;
    }

    public final Triple<LoopConditionEnterNode, LoopBlockExitNode, LoopExitNode> exitWhileLoop(FirLoop loop) {
        loop.getClass();
        LoopBlockExitNode loopBlockExitNodeCreateLoopBlockExitNode = ControlFlowGraphNodeBuilderKt.createLoopBlockExitNode(this, loop);
        popAndAddEdge$default(this, loopBlockExitNodeCreateLoopBlockExitNode, null, 2, null);
        LoopConditionEnterNode loopConditionEnterNodeRemove = this.loopConditionEnterNodes.remove(loop);
        loopConditionEnterNodeRemove.getClass();
        LoopConditionEnterNode loopConditionEnterNode = loopConditionEnterNodeRemove;
        addBackEdge$default(this, loopBlockExitNodeCreateLoopBlockExitNode, loopConditionEnterNode, false, null, 12, null);
        LoopExitNode loopExitNodeRemove = this.loopExitNodes.remove(loop);
        loopExitNodeRemove.getClass();
        LoopExitNode loopExitNode = loopExitNodeRemove;
        loopExitNode.updateDeadStatus();
        this.lastNodes.push(loopExitNode);
        return new Triple<>(loopConditionEnterNode, loopBlockExitNodeCreateLoopBlockExitNode, loopExitNode);
    }

    public final Pair<LoopConditionExitNode, LoopBlockEnterNode> exitWhileLoopCondition(FirLoop loop) {
        loop.getClass();
        LoopConditionExitNode loopConditionExitNodeCreateLoopConditionExitNode = ControlFlowGraphNodeBuilderKt.createLoopConditionExitNode(this, loop.getCondition(), loop);
        addNewSimpleNode$default(this, loopConditionExitNodeCreateLoopConditionExitNode, false, 2, null);
        Boolean booleanLiteralValue = ControlFlowGraphBuilderKt.getBooleanLiteralValue(loop.getCondition());
        addEdge$default(this, loopConditionExitNodeCreateLoopConditionExitNode, (CFGNode) MapsKt.getValue(this.loopExitNodes, loop), false, Intrinsics.areEqual(booleanLiteralValue, Boolean.TRUE), null, null, 48, null);
        LoopBlockEnterNode loopBlockEnterNodeCreateLoopBlockEnterNode = ControlFlowGraphNodeBuilderKt.createLoopBlockEnterNode(this, loop);
        addNewSimpleNode(loopBlockEnterNodeCreateLoopBlockEnterNode, Intrinsics.areEqual(booleanLiteralValue, Boolean.FALSE));
        return TuplesKt.to(loopConditionExitNodeCreateLoopConditionExitNode, loopBlockEnterNodeCreateLoopBlockEnterNode);
    }

    public final ControlFlowGraph getCurrentGraph() {
        return this.graphs.top();
    }

    public final CFGNode<?> getLastNode() {
        return this.lastNodes.top();
    }

    public final CFGNode<?> getLastNodeOrNull() {
        return (CFGNode) StackKt.topOrNull(this.lastNodes);
    }

    public final int getLevelCounter() {
        return this.graphs.getSize() + this.tryExitNodes.getSize();
    }

    public final boolean isTopLevel() {
        if (StackKt.isEmpty(this.graphs)) {
            return true;
        }
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) StackKt.topOrNull(this.graphs);
        return (controlFlowGraph != null ? controlFlowGraph.getKind() : null) == ControlFlowGraph.Kind.File;
    }

    public final void reset() {
        this.enterToLocalClassesMembers.clear();
        this.postponedLambdaExits.reset();
        this.lastNodes.reset();
    }

    public final Collection<FirAnonymousFunctionReturnExpressionInfo> returnExpressionsOfAnonymousFunction(FirAnonymousFunction function) {
        ControlFlowGraph controlFlowGraph;
        CFGNode<?> exitNode;
        function.getClass();
        FirControlFlowGraphReference controlFlowGraphReference = function.getControlFlowGraphReference();
        if (controlFlowGraphReference == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null || (exitNode = controlFlowGraph.getExitNode()) == null) {
            return null;
        }
        List<CFGNode<?>> previousNodes = exitNode.getPreviousNodes();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (CFGNode<?> cFGNode : previousNodes) {
            Edge edgeEdgeFrom = exitNode.edgeFrom(cFGNode);
            if (!edgeEdgeFrom.getKind().getUsedInCfa() || !Intrinsics.areEqual(edgeEdgeFrom.getLabel(), NormalPath.INSTANCE)) {
                cFGNode = null;
            }
            FirAnonymousFunctionReturnExpressionInfo firAnonymousFunctionReturnExpressionInfoReturnExpressionsOfAnonymousFunction$returnExpression = cFGNode != null ? returnExpressionsOfAnonymousFunction$returnExpression(cFGNode, function) : null;
            if (firAnonymousFunctionReturnExpressionInfoReturnExpressionsOfAnonymousFunction$returnExpression != null) {
                linkedHashSet.add(firAnonymousFunctionReturnExpressionInfoReturnExpressionsOfAnonymousFunction$returnExpression);
            }
        }
        Iterator it = this.nonDirectJumps.get(exitNode).iterator();
        while (it.hasNext()) {
            FirAnonymousFunctionReturnExpressionInfo firAnonymousFunctionReturnExpressionInfoReturnExpressionsOfAnonymousFunction$returnExpression2 = returnExpressionsOfAnonymousFunction$returnExpression((JumpNode) it.next(), function);
            if (firAnonymousFunctionReturnExpressionInfoReturnExpressionsOfAnonymousFunction$returnExpression2 != null) {
                linkedHashSet.add(firAnonymousFunctionReturnExpressionInfoReturnExpressionsOfAnonymousFunction$returnExpression2);
            }
        }
        return linkedHashSet;
    }

    public final boolean withinFinallyBlock(FirElement element) {
        element.getClass();
        return this.finallyBlocksInProgressSet.contains(element);
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u001e\b\u0002\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u00070\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003HÆ\u0003J\u001f\u0010\u0011\u001a\u0018\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u00070\u0006HÆ\u0003J=\u0010\u0012\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00032\u001e\b\u0002\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u00070\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR'\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder$PostponedLambdas;", Argument.Delimiters.none, "lambdas", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "exits", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;", "<init>", "(Ljava/util/Set;Ljava/util/List;)V", "getLambdas", "()Ljava/util/Set;", "getExits", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class PostponedLambdas {
        private final List<Pair<CFGNode<?>, EdgeKind>> exits;
        private final Set<FirFunctionSymbol<?>> lambdas;

        /* JADX WARN: Multi-variable type inference failed */
        public PostponedLambdas(Set<? extends FirFunctionSymbol<?>> set, List<Pair<CFGNode<?>, EdgeKind>> list) {
            set.getClass();
            list.getClass();
            this.lambdas = set;
            this.exits = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PostponedLambdas copy$default(PostponedLambdas postponedLambdas, Set set, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                set = postponedLambdas.lambdas;
            }
            if ((i & 2) != 0) {
                list = postponedLambdas.exits;
            }
            return postponedLambdas.copy(set, list);
        }

        public final Set<FirFunctionSymbol<?>> component1() {
            return this.lambdas;
        }

        public final List<Pair<CFGNode<?>, EdgeKind>> component2() {
            return this.exits;
        }

        public final PostponedLambdas copy(Set<? extends FirFunctionSymbol<?>> lambdas, List<Pair<CFGNode<?>, EdgeKind>> exits) {
            lambdas.getClass();
            exits.getClass();
            return new PostponedLambdas(lambdas, exits);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PostponedLambdas)) {
                return false;
            }
            PostponedLambdas postponedLambdas = (PostponedLambdas) other;
            return Intrinsics.areEqual(this.lambdas, postponedLambdas.lambdas) && Intrinsics.areEqual(this.exits, postponedLambdas.exits);
        }

        public final List<Pair<CFGNode<?>, EdgeKind>> getExits() {
            return this.exits;
        }

        public final Set<FirFunctionSymbol<?>> getLambdas() {
            return this.lambdas;
        }

        public int hashCode() {
            return (this.lambdas.hashCode() * 31) + this.exits.hashCode();
        }

        public String toString() {
            return "PostponedLambdas(lambdas=" + this.lambdas + ", exits=" + this.exits + ')';
        }

        public /* synthetic */ PostponedLambdas(Set set, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(set, (i & 2) != 0 ? new ArrayList() : list);
        }
    }

    private ControlFlowGraphBuilder(Stack<ControlFlowGraph> stack, Stack<CFGNode<?>> stack2, Map<FirFunctionSymbol<?>, FunctionExitNode> map, Map<FirBasedSymbol<?>, Pair<CFGNode<?>, EdgeKind>> map2, ListMultimap<CFGNode<?>, JumpNode> listMultimap, Stack<SplitPostponedLambdasNode> stack3, Map<FirFunctionSymbol<?>, Pair<CFGNode<?>, PostponedLambdaExitNode>> map3, Map<FirFunctionSymbol<?>, AnonymousFunctionCaptureNode> map4, Stack<PostponedLambdas> stack4, Map<FirLoop, LoopConditionEnterNode> map5, Map<FirLoop, LoopExitNode> map6, Stack<WhenExitNode> stack5, Stack<TryExpressionExitNode> stack6, Stack<List<CatchClauseEnterNode>> stack7, Stack<CatchClauseEnterNode> stack8, Stack<FinallyBlockEnterNode> stack9, Stack<FinallyBlockEnterNode> stack10, Set<FirElement> set, Stack<FunctionCallArgumentsExitNode> stack11, Stack<ExitSafeCallNode> stack12, Stack<ElvisExitNode> stack13, Stack<ElvisRhsEnterNode> stack14, Stack<CFGNode<?>> stack15, Stack<List<FunctionCallExitNode>> stack16) {
        this.graphs = stack;
        this.lastNodes = stack2;
        this.exitTargetsForReturn = map;
        this.enterToLocalClassesMembers = map2;
        this.nonDirectJumps = listMultimap;
        this.argumentListSplitNodes = stack3;
        this.postponedAnonymousFunctionNodes = map3;
        this.anonymousFunctionCaptureNodes = map4;
        this.postponedLambdaExits = stack4;
        this.loopConditionEnterNodes = map5;
        this.loopExitNodes = map6;
        this.whenExitNodes = stack5;
        this.tryExitNodes = stack6;
        this.catchNodes = stack7;
        this.catchBlocksInProgress = stack8;
        this.finallyEnterNodes = stack9;
        this.finallyBlocksInProgress = stack10;
        this.finallyBlocksInProgressSet = set;
        this.exitFunctionCallArgumentsNodes = stack11;
        this.exitSafeCallNodes = stack12;
        this.exitElvisExpressionNodes = stack13;
        this.elvisRhsEnterNodes = stack14;
        this.equalityOperatorCallLhsExitNodes = stack15;
        this.notCompletedFunctionCalls = stack16;
    }
}

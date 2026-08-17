package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds;
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import androidx.compose.compiler.plugins.kotlin.FeatureFlag;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.WeakBindingTraceKt;
import androidx.compose.compiler.plugins.kotlin.analysis.ComposeWritableSlices;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposerLambdaMemoization;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.UtilsKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder;
import org.jetbrains.kotlin.backend.jvm.codegen.PromisedValueKt;
import org.jetbrains.kotlin.backend.jvm.ir.JvmIrInlineUtilsKt;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.builders.ExpressionHelpersKt;
import org.jetbrains.kotlin.ir.builders.IrBlockBodyBuilder;
import org.jetbrains.kotlin.ir.builders.IrBlockBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.builders.declarations.IrClassBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrFieldBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrFunctionBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrPropertyBuilder;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrPropertyReference;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrTry;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.IrValueAccessExpression;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrTypeOperatorCallImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DeepCopyIrTreeWithSymbols;
import org.jetbrains.kotlin.ir.util.DeepCopySymbolRemapper;
import org.jetbrains.kotlin.ir.util.DeepCopyTypeRemapper;
import org.jetbrains.kotlin.ir.util.DescriptorsRemapper;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.PatchDeclarationParentsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.load.kotlin.PackagePartClassUtils;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.JsPlatformKt;
import org.jetbrains.kotlin.platform.jvm.JvmPlatformKt;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u00109\u001a\u00020\u0013H\u0002J\u0010\u0010:\u001a\u00020\u00152\u0006\u0010;\u001a\u00020\u0015H\u0016J\u0010\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020A2\u0006\u0010;\u001a\u00020BH\u0016J\b\u0010C\u001a\u00020DH\u0002J\u0010\u0010I\u001a\u00020A2\u0006\u0010;\u001a\u00020FH\u0016J\u0010\u0010J\u001a\u00020A2\u0006\u0010;\u001a\u00020\u0013H\u0016J\u0010\u0010K\u001a\u00020A2\u0006\u0010;\u001a\u00020LH\u0016J\u0010\u0010M\u001a\u00020D2\u0006\u0010N\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u00020A2\u0006\u0010;\u001a\u00020QH\u0016J\u0010\u0010R\u001a\u00020D2\u0006\u0010S\u001a\u00020TH\u0016J\u0010\u0010U\u001a\u00020D2\u0006\u0010S\u001a\u00020VH\u0016J\u0010\u0010W\u001a\u00020D2\u0006\u0010S\u001a\u00020XH\u0016J\u0018\u0010Y\u001a\u00020D2\u0006\u0010Z\u001a\u00020X2\u0006\u0010S\u001a\u00020DH\u0002J\u0010\u0010[\u001a\u00020D2\u0006\u0010S\u001a\u00020\\H\u0016J\u0010\u0010]\u001a\u00020D2\u0006\u0010S\u001a\u00020^H\u0002J\u0010\u0010_\u001a\u00020D2\u0006\u0010S\u001a\u00020`H\u0016J\u0010\u0010a\u001a\u00020D2\u0006\u0010S\u001a\u00020bH\u0016J\u0018\u0010c\u001a\u00020D2\u0006\u0010S\u001a\u00020^2\u0006\u0010d\u001a\u00020\u000fH\u0002J\u0010\u0010e\u001a\u00020\u00182\u0006\u0010S\u001a\u00020^H\u0002J\u0010\u0010f\u001a\u0002052\u0006\u0010g\u001a\u00020hH\u0002J \u0010i\u001a\u00020`2\u0006\u0010j\u001a\u00020D2\u0006\u0010k\u001a\u00020h2\u0006\u0010l\u001a\u00020\u0018H\u0002J\u0010\u0010m\u001a\u00020D2\u0006\u0010S\u001a\u00020^H\u0016J\u0010\u0010n\u001a\u00020=2\u0006\u0010o\u001a\u00020pH\u0002J\u0010\u0010q\u001a\u00020=2\u0006\u0010o\u001a\u00020pH\u0002J(\u0010r\u001a\u00020`2\u0006\u0010d\u001a\u00020\u000f2\u0006\u0010S\u001a\u00020^2\u0006\u0010o\u001a\u00020p2\u0006\u0010s\u001a\u000205H\u0002J&\u0010t\u001a\u00020D2\u0006\u0010u\u001a\u00020\u00112\u0006\u0010S\u001a\u00020D2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020w0\u001dH\u0002J(\u0010x\u001a\u00020D2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020D0\u001d2\u0006\u0010S\u001a\u00020D2\b\u0010y\u001a\u0004\u0018\u00010\u0015H\u0002J\u001e\u0010z\u001a\u00020D2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020D0\u001d2\u0006\u0010S\u001a\u00020DH\u0002J\u001a\u0010{\u001a\u00020D2\u0006\u0010|\u001a\u00020D2\b\u0010}\u001a\u0004\u0018\u00010\u0015H\u0002J\f\u0010~\u001a\u000205*\u00020wH\u0002J\f\u0010\u007f\u001a\u000205*\u00020wH\u0002J\r\u0010\u0080\u0001\u001a\u000205*\u00020wH\u0002J\r\u0010\u0081\u0001\u001a\u000205*\u00020wH\u0002J)\u0010\u0082\u0001\u001a\u0003H\u0083\u0001\"\t\b\u0000\u0010\u0083\u0001*\u00020D*\u0003H\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u000205H\u0002¢\u0006\u0003\u0010\u0085\u0001J!\u0010\u0086\u0001\u001a\u0003H\u0083\u0001\"\n\b\u0000\u0010\u0083\u0001*\u00030\u0087\u0001*\u0003H\u0083\u0001H\u0002¢\u0006\u0003\u0010\u0088\u0001J!\u0010\u0089\u0001\u001a\u0003H\u0083\u0001\"\n\b\u0000\u0010\u0083\u0001*\u00030\u0087\u0001*\u0003H\u0083\u0001H\u0002¢\u0006\u0003\u0010\u0088\u0001J!\u0010\u008a\u0001\u001a\u0003H\u0083\u0001\"\n\b\u0000\u0010\u0083\u0001*\u00030\u0087\u0001*\u0003H\u0083\u0001H\u0002¢\u0006\u0003\u0010\u0088\u0001J!\u0010\u008b\u0001\u001a\u0003H\u0083\u0001\"\n\b\u0000\u0010\u0083\u0001*\u00030\u0087\u0001*\u0003H\u0083\u0001H\u0002¢\u0006\u0003\u0010\u0088\u0001J\u001a\u0010\u008f\u0001\u001a\u000205*\u0004\u0018\u00010D2\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0015H\u0002J\r\u0010\u0091\u0001\u001a\u000205*\u00020wH\u0002R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u001b\u0010%\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010$\u001a\u0004\b&\u0010\"R\u001b\u0010(\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010$\u001a\u0004\b)\u0010\"R\u001b\u0010+\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010$\u001a\u0004\b,\u0010\"R\u001d\u0010.\u001a\u0004\u0018\u00010 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u0010$\u001a\u0004\b/\u0010\"R\u001d\u00101\u001a\u0004\u0018\u00010 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u0010$\u001a\u0004\b2\u0010\"R\u001b\u00104\u001a\u0002058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u0010$\u001a\u0004\b6\u00107R\u0018\u0010E\u001a\u000205*\u00020F8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u001b\u0010\u008c\u0001\u001a\u000205*\u00020D8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001¨\u0006\u0092\u0001"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposerLambdaMemoization;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "declarationContextStack", "", "Landroidx/compose/compiler/plugins/kotlin/lower/DeclarationContext;", "currentFunctionContext", "Landroidx/compose/compiler/plugins/kotlin/lower/FunctionContext;", "composableSingletonsClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "currentFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "usedSingletonLambdaNames", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "inlineLambdaInfo", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposeInlineLambdaLocator;", "rememberFunctions", "", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "composableLambdaFunction", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getComposableLambdaFunction", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "composableLambdaFunction$delegate", "Landroidx/compose/compiler/plugins/kotlin/lower/GuardedLazy;", "composableLambdaNFunction", "getComposableLambdaNFunction", "composableLambdaNFunction$delegate", "composableLambdaInstanceFunction", "getComposableLambdaInstanceFunction", "composableLambdaInstanceFunction$delegate", "composableLambdaInstanceNFunction", "getComposableLambdaInstanceNFunction", "composableLambdaInstanceNFunction$delegate", "rememberComposableLambdaFunction", "getRememberComposableLambdaFunction", "rememberComposableLambdaFunction$delegate", "rememberComposableLambdaNFunction", "getRememberComposableLambdaNFunction", "rememberComposableLambdaNFunction$delegate", "useNonSkippingGroupOptimization", "", "getUseNonSkippingGroupOptimization", "()Z", "useNonSkippingGroupOptimization$delegate", "getOrCreateComposableSingletonsClass", "visitFile", "declaration", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "visitDeclaration", "Lorg/jetbrains/kotlin/ir/IrStatement;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationBase;", "irCurrentComposer", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "allowsComposableCalls", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getAllowsComposableCalls", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Z", "visitFunction", "visitClass", "visitAnonymousInitializer", "Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;", "visitTry", "aTry", "Lorg/jetbrains/kotlin/ir/expressions/IrTry;", "visitVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "visitValueAccess", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrValueAccessExpression;", "visitBlock", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "visitFunctionReference", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionReference;", "rememberFunctionReference", "reference", "visitTypeOperator", "Lorg/jetbrains/kotlin/ir/expressions/IrTypeOperatorCall;", "visitNonComposableFunctionExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionExpression;", "visitCall", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "visitConstructorCall", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "visitComposableFunctionExpression", "declarationContext", "createSingletonLambdaName", "hasTypeParameter", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "irGetComposableSingleton", "lambdaExpression", "lambdaType", "lambdaName", "visitFunctionExpression", "startCollector", "collector", "Landroidx/compose/compiler/plugins/kotlin/lower/CaptureCollector;", "stopCollector", "wrapFunctionExpression", "useComposableFactory", "rememberExpression", "functionContext", "captures", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "irCache", "fileContainingExpression", "irRemember", "irChanged", "value", "fileContainingValue", "isVar", "isStable", "isInlinedLambda", "isInlineableFunction", "markAsStatic", "T", "mark", "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Z)Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "markAsComposableSingleton", "Lorg/jetbrains/kotlin/ir/IrElement;", "(Lorg/jetbrains/kotlin/ir/IrElement;)Lorg/jetbrains/kotlin/ir/IrElement;", "markAsComposableSingletonClass", "markHasTransformedLambda", "markIsTransformedLambda", "hasDontMemoizeAnnotation", "getHasDontMemoizeAnnotation", "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)Z", "isNullOrStable", "fileContainingDependent", "isPropertyReferenceDelegate", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposerLambdaMemoization extends AbstractComposeLowering implements ModuleLoweringPass {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(ComposerLambdaMemoization.class, "composableLambdaFunction", "getComposableLambdaFunction()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", 0), new PropertyReference1Impl<>(ComposerLambdaMemoization.class, "composableLambdaNFunction", "getComposableLambdaNFunction()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", 0), new PropertyReference1Impl<>(ComposerLambdaMemoization.class, "composableLambdaInstanceFunction", "getComposableLambdaInstanceFunction()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", 0), new PropertyReference1Impl<>(ComposerLambdaMemoization.class, "composableLambdaInstanceNFunction", "getComposableLambdaInstanceNFunction()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", 0), new PropertyReference1Impl<>(ComposerLambdaMemoization.class, "rememberComposableLambdaFunction", "getRememberComposableLambdaFunction()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", 0), new PropertyReference1Impl<>(ComposerLambdaMemoization.class, "rememberComposableLambdaNFunction", "getRememberComposableLambdaNFunction()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", 0), new PropertyReference1Impl<>(ComposerLambdaMemoization.class, "useNonSkippingGroupOptimization", "getUseNonSkippingGroupOptimization()Z", 0)};

    /* JADX INFO: renamed from: composableLambdaFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy composableLambdaFunction;

    /* JADX INFO: renamed from: composableLambdaInstanceFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy composableLambdaInstanceFunction;

    /* JADX INFO: renamed from: composableLambdaInstanceNFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy composableLambdaInstanceNFunction;

    /* JADX INFO: renamed from: composableLambdaNFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy composableLambdaNFunction;
    private IrClass composableSingletonsClass;
    private IrFile currentFile;
    private FunctionContext currentFunctionContext;
    private final List<DeclarationContext> declarationContextStack;
    private ComposeInlineLambdaLocator inlineLambdaInfo;

    /* JADX INFO: renamed from: rememberComposableLambdaFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy rememberComposableLambdaFunction;

    /* JADX INFO: renamed from: rememberComposableLambdaNFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy rememberComposableLambdaNFunction;
    private final List<IrSimpleFunction> rememberFunctions;

    /* JADX INFO: renamed from: useNonSkippingGroupOptimization$delegate, reason: from kotlin metadata */
    private final GuardedLazy useNonSkippingGroupOptimization;
    private final HashSet<String> usedSingletonLambdaNames;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerLambdaMemoization(IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        this.declarationContextStack = new ArrayList();
        this.usedSingletonLambdaNames = new HashSet<>();
        this.inlineLambdaInfo = new ComposeInlineLambdaLocator(irPluginContext);
        List<IrSimpleFunctionSymbol> topLevelFunctions = getTopLevelFunctions(ComposeCallableIds.INSTANCE.getRemember());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(topLevelFunctions, 10));
        Iterator<T> it = topLevelFunctions.iterator();
        while (it.hasNext()) {
            arrayList.add(((IrSimpleFunctionSymbol) it.next()).getOwner());
        }
        this.rememberFunctions = arrayList;
        this.composableLambdaFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: in2
            public final Object invoke() {
                return ComposerLambdaMemoization.l(this.b);
            }
        });
        this.composableLambdaNFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: jn2
            public final Object invoke() {
                return ComposerLambdaMemoization.m(this.b);
            }
        });
        this.composableLambdaInstanceFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: kn2
            public final Object invoke() {
                return ComposerLambdaMemoization.o(this.b);
            }
        });
        this.composableLambdaInstanceNFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: ln2
            public final Object invoke() {
                return ComposerLambdaMemoization.s(this.b);
            }
        });
        this.rememberComposableLambdaFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: mn2
            public final Object invoke() {
                return ComposerLambdaMemoization.p(this.b);
            }
        });
        this.rememberComposableLambdaNFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: nn2
            public final Object invoke() {
                return ComposerLambdaMemoization.k(this.b);
            }
        });
        this.useNonSkippingGroupOptimization = GuardedLazyKt.guardedLazy(new Function0() { // from class: on2
            public final Object invoke() {
                return Boolean.valueOf(ComposerLambdaMemoization.t(this.b));
            }
        });
    }

    private final String createSingletonLambdaName(IrFunctionExpression expression) {
        String str = "lambda$" + sourceKey(expression.getFunction());
        if (this.usedSingletonLambdaNames.add(str)) {
            return str;
        }
        int i = 0;
        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append('$');
            int i2 = i + 1;
            sb.append(i);
            String string = sb.toString();
            if (this.usedSingletonLambdaNames.add(string)) {
                return string;
            }
            i = i2;
        }
    }

    private final boolean getAllowsComposableCalls(IrFunction irFunction) {
        FunctionContext functionContext;
        return hasComposableAnnotation(irFunction) || (this.inlineLambdaInfo.preservesComposableScope(irFunction) && (functionContext = this.currentFunctionContext) != null && functionContext.getComposable());
    }

    private final IrSimpleFunctionSymbol getComposableLambdaFunction() {
        return (IrSimpleFunctionSymbol) this.composableLambdaFunction.value($$delegatedProperties[0].getName());
    }

    private final IrSimpleFunctionSymbol getComposableLambdaInstanceFunction() {
        return (IrSimpleFunctionSymbol) this.composableLambdaInstanceFunction.value($$delegatedProperties[2].getName());
    }

    private final IrSimpleFunctionSymbol getComposableLambdaInstanceNFunction() {
        return (IrSimpleFunctionSymbol) this.composableLambdaInstanceNFunction.value($$delegatedProperties[3].getName());
    }

    private final IrSimpleFunctionSymbol getComposableLambdaNFunction() {
        return (IrSimpleFunctionSymbol) this.composableLambdaNFunction.value($$delegatedProperties[1].getName());
    }

    private final boolean getHasDontMemoizeAnnotation(IrExpression irExpression) {
        IrSimpleFunction function;
        IrFunctionExpression irFunctionExpression = irExpression instanceof IrFunctionExpression ? (IrFunctionExpression) irExpression : null;
        if (irFunctionExpression == null || (function = irFunctionExpression.getFunction()) == null) {
            return false;
        }
        return IrUtilsKt.hasAnnotation(function, ComposeFqNames.INSTANCE.getDontMemoize());
    }

    private final IrClass getOrCreateComposableSingletonsClass() {
        IrClass irClass = this.composableSingletonsClass;
        if (irClass != null) {
            irClass.getClass();
            return irClass;
        }
        IrFile irFile = this.currentFile;
        irFile.getClass();
        String str = (String) CollectionsKt.last(StringsKt.split$default(irFile.getFileEntry().getName(), new char[]{'/'}, false, 0, 6, (Object) null));
        IrFactory irFactory = getContext().getIrFactory();
        IrClassBuilder irClassBuilder = new IrClassBuilder();
        irClassBuilder.setStartOffset(-2);
        irClassBuilder.setEndOffset(-2);
        irClassBuilder.setKind(ClassKind.OBJECT);
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.INTERNAL;
        descriptorVisibility.getClass();
        irClassBuilder.setVisibility(descriptorVisibility);
        Name nameIdentifier = Name.identifier("ComposableSingletons$" + PackagePartClassUtils.getFilePartShortName(str));
        nameIdentifier.getClass();
        irClassBuilder.setName(nameIdentifier);
        IrClass irClassBuildClass = DeclarationBuildersKt.buildClass(irFactory, irClassBuilder);
        IrUtilsKt.createThisReceiverParameter(irClassBuildClass);
        IrFactory factory = irClassBuildClass.getFactory();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        irFunctionBuilder.setPrimary(true);
        irFunctionBuilder.setReturnType(IrUtilsKt.getDefaultType(irClassBuildClass));
        IrConstructor irConstructorBuildConstructor = DeclarationBuildersKt.buildConstructor(factory, irFunctionBuilder);
        irClassBuildClass.getDeclarations().add(irConstructorBuildConstructor);
        irConstructorBuildConstructor.setParent(irClassBuildClass);
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(getContext(), irClassBuildClass.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        IrConstructor primaryConstructor = IrUtilsKt.getPrimaryConstructor(irBlockBodyBuilder.getContext().getIrBuiltIns().getAnyClass().getOwner());
        primaryConstructor.getClass();
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irDelegatingConstructorCall(irBlockBodyBuilder, primaryConstructor));
        irBlockBodyBuilder.unaryPlus(BuildersKt.IrInstanceInitializerCallImpl(irBlockBodyBuilder.getStartOffset(), irBlockBodyBuilder.getEndOffset(), irClassBuildClass.getSymbol(), irBlockBodyBuilder.getContext().getIrBuiltIns().getUnitType()));
        irConstructorBuildConstructor.setBody(irBlockBodyBuilder.doBuild());
        IrClass irClassMarkAsComposableSingletonClass = markAsComposableSingletonClass(irClassBuildClass);
        this.composableSingletonsClass = irClassMarkAsComposableSingletonClass;
        return irClassMarkAsComposableSingletonClass;
    }

    private final IrSimpleFunctionSymbol getRememberComposableLambdaFunction() {
        return (IrSimpleFunctionSymbol) this.rememberComposableLambdaFunction.value($$delegatedProperties[4].getName());
    }

    private final IrSimpleFunctionSymbol getRememberComposableLambdaNFunction() {
        return (IrSimpleFunctionSymbol) this.rememberComposableLambdaNFunction.value($$delegatedProperties[5].getName());
    }

    private final boolean getUseNonSkippingGroupOptimization() {
        return ((Boolean) this.useNonSkippingGroupOptimization.value($$delegatedProperties[6].getName())).booleanValue();
    }

    private final boolean hasTypeParameter(IrType type) {
        return PromisedValueKt.anyTypeArgument(type, new Function1() { // from class: hn2
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ComposerLambdaMemoization.n((IrTypeParameter) obj));
            }
        });
    }

    private final IrExpression irCache(List<? extends IrExpression> captures, final IrExpression expression, IrFile fileContainingExpression) {
        Object next;
        IrFunction irFunctionMo278getDeclaration;
        FqName kotlinFqName;
        List<? extends IrExpression> list = captures;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(irChanged((IrExpression) it.next(), fileContainingExpression));
        }
        Iterator it2 = arrayList.iterator();
        String strAsString = null;
        if (it2.hasNext()) {
            next = it2.next();
            while (it2.hasNext()) {
                next = irBooleanOr((IrExpression) next, (IrExpression) it2.next());
            }
        } else {
            next = null;
        }
        IrConstImpl irConstImplIrConst = (IrExpression) next;
        if (irConstImplIrConst == null) {
            irConstImplIrConst = irConst(false);
        }
        IrCall irCallIrCache = irCache(irCurrentComposer(), expression.getStartOffset(), expression.getEndOffset(), expression.getType(), irConstImplIrConst, irLambdaExpression(-1, -1, expression.getType(), new Function1() { // from class: pn2
            public final Object invoke(Object obj) {
                return ComposerLambdaMemoization.q(this.b, expression, (IrSimpleFunction) obj);
            }
        }));
        if (getUseNonSkippingGroupOptimization()) {
            return irCallIrCache;
        }
        FunctionContext functionContext = this.currentFunctionContext;
        if (functionContext != null && (irFunctionMo278getDeclaration = functionContext.mo278getDeclaration()) != null && (kotlinFqName = AdditionalIrUtilsKt.getKotlinFqName(irFunctionMo278getDeclaration)) != null) {
            strAsString = kotlinFqName.asString();
        }
        int iHashCode = (strAsString != null ? strAsString.hashCode() : 0) + expression.getStartOffset();
        IrVariableImpl irVariableImplIrTemporary$default = AbstractComposeLowering.irTemporary$default(this, irCallIrCache, "tmpCache", null, false, null, 28, null);
        return AbstractComposeLowering.wrap$default(this, irVariableImplIrTemporary$default, 0, 0, expression.getType(), CollectionsKt.listOf(AbstractComposeLowering.irStartReplaceGroup$default(this, irCurrentComposer(), irConst(iHashCode), 0, 0, 12, null)), CollectionsKt.listOf(new IrExpression[]{AbstractComposeLowering.irEndReplaceGroup$default(this, irCurrentComposer(), 0, 0, 6, null), irGet(irVariableImplIrTemporary$default)}), 3, null);
    }

    private final IrExpression irChanged(IrExpression value, IrFile fileContainingValue) {
        return irChanged(irCurrentComposer(), value, fileContainingValue, false, false, getEnabled(FeatureFlag.StrongSkipping));
    }

    private final IrExpression irCurrentComposer() {
        IrSimpleFunctionSymbol topLevelPropertyGetter = getTopLevelPropertyGetter(ComposeCallableIds.INSTANCE.getCurrentComposer());
        IrType irTypeReplaceArgumentsWithStarProjections = replaceArgumentsWithStarProjections(IrUtilsKt.getDefaultType(getComposerIrClass()));
        topLevelPropertyGetter.getClass();
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = topLevelPropertyGetter;
        return BuildersKt.IrCallImpl$default(-1, -1, irTypeReplaceArgumentsWithStarProjections, irSimpleFunctionSymbol, irSimpleFunctionSymbol.getOwner().getTypeParameters().size(), IrStatementOrigin.Companion.getFOR_LOOP_ITERATOR(), (IrClassSymbol) null, 64, (Object) null);
    }

    private final IrCall irGetComposableSingleton(IrExpression lambdaExpression, IrType lambdaType, String lambdaName) {
        IrFunction irFunctionMo278getDeclaration;
        IrClass orCreateComposableSingletonsClass = getOrCreateComposableSingletonsClass();
        IrFactory factory = orCreateComposableSingletonsClass.getFactory();
        IrPropertyBuilder irPropertyBuilder = new IrPropertyBuilder();
        Name nameIdentifier = Name.identifier(lambdaName);
        nameIdentifier.getClass();
        irPropertyBuilder.setName(nameIdentifier);
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.INTERNAL;
        descriptorVisibility.getClass();
        irPropertyBuilder.setVisibility(descriptorVisibility);
        IrProperty irPropertyBuildProperty = DeclarationBuildersKt.buildProperty(factory, irPropertyBuilder);
        orCreateComposableSingletonsClass.getDeclarations().add(irPropertyBuildProperty);
        irPropertyBuildProperty.setParent(orCreateComposableSingletonsClass);
        IrFactory irFactory = getContext().getIrFactory();
        IrFieldBuilder irFieldBuilder = new IrFieldBuilder();
        irFieldBuilder.setStartOffset(-2);
        irFieldBuilder.setEndOffset(-2);
        Name nameIdentifier2 = Name.identifier(lambdaName);
        nameIdentifier2.getClass();
        irFieldBuilder.setName(nameIdentifier2);
        irFieldBuilder.setType(lambdaType);
        DescriptorVisibility descriptorVisibility2 = DescriptorVisibilities.PRIVATE;
        descriptorVisibility2.getClass();
        irFieldBuilder.setVisibility(descriptorVisibility2);
        irFieldBuilder.setStatic(JvmPlatformKt.isJvm(getContext().getPlatform()));
        IrField irFieldBuildField = DeclarationBuildersKt.buildField(irFactory, irFieldBuilder);
        irFieldBuildField.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
        irFieldBuildField.setParent(orCreateComposableSingletonsClass);
        irFieldBuildField.setInitializer(ExpressionHelpersKt.irExprBody(new DeclarationIrBuilder(getContext(), orCreateComposableSingletonsClass.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null), markIsTransformedLambda(lambdaExpression)));
        irPropertyBuildProperty.setBackingField(irFieldBuildField);
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        Name nameSpecial = Name.special("<get-" + irPropertyBuildProperty.getName() + '>');
        nameSpecial.getClass();
        irFunctionBuilder.setName(nameSpecial);
        irFunctionBuilder.setReturnType(lambdaType);
        descriptorVisibility.getClass();
        irFunctionBuilder.setVisibility(descriptorVisibility);
        irFunctionBuilder.setOrigin(IrDeclarationOrigin.Companion.getDEFAULT_PROPERTY_ACCESSOR());
        IrSimpleFunction irSimpleFunctionBuildFunction = DeclarationBuildersKt.buildFunction(irPropertyBuildProperty.getFactory(), irFunctionBuilder);
        irPropertyBuildProperty.setGetter(irSimpleFunctionBuildFunction);
        irSimpleFunctionBuildFunction.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
        irSimpleFunctionBuildFunction.setParent(irPropertyBuildProperty.getParent());
        IrValueParameter thisReceiver = orCreateComposableSingletonsClass.getThisReceiver();
        thisReceiver.getClass();
        IrValueParameter irValueParameterCopyTo$default = IrUtilsKt.copyTo$default(thisReceiver, irSimpleFunctionBuildFunction, (IrDeclarationOrigin) null, 0, 0, (Name) null, (Map) null, (IrType) null, (IrType) null, (IrExpressionBody) null, false, false, false, (IrParameterKind) null, (Map) null, 16382, (Object) null);
        irSimpleFunctionBuildFunction.setParent(orCreateComposableSingletonsClass);
        irSimpleFunctionBuildFunction.setParameters(CollectionsKt.plus(irSimpleFunctionBuildFunction.getParameters(), irValueParameterCopyTo$default));
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(getContext(), irSimpleFunctionBuildFunction.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        IrGetValueImpl irGetValueImplIrGet = ExpressionHelpersKt.irGet(irBlockBodyBuilder, irValueParameterCopyTo$default);
        IrField backingField = irPropertyBuildProperty.getBackingField();
        backingField.getClass();
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder, ExpressionHelpersKt.irGetField$default(irBlockBodyBuilder, irGetValueImplIrGet, backingField, (IrType) null, 4, (Object) null)));
        irSimpleFunctionBuildFunction.setBody(irBlockBodyBuilder.doBuild());
        FunctionContext functionContext = this.currentFunctionContext;
        if (functionContext != null && (irFunctionMo278getDeclaration = functionContext.mo278getDeclaration()) != null && JvmIrInlineUtilsKt.isInPublicInlineScope(irFunctionMo278getDeclaration)) {
            IrFactory factory2 = orCreateComposableSingletonsClass.getFactory();
            IrPropertyBuilder irPropertyBuilder2 = new IrPropertyBuilder();
            Name nameIdentifier3 = Name.identifier("lambda-" + this.usedSingletonLambdaNames.size());
            nameIdentifier3.getClass();
            irPropertyBuilder2.setName(nameIdentifier3);
            descriptorVisibility.getClass();
            irPropertyBuilder2.setVisibility(descriptorVisibility);
            IrProperty irPropertyBuildProperty2 = DeclarationBuildersKt.buildProperty(factory2, irPropertyBuilder2);
            orCreateComposableSingletonsClass.getDeclarations().add(irPropertyBuildProperty2);
            irPropertyBuildProperty2.setParent(orCreateComposableSingletonsClass);
            IrFunctionBuilder irFunctionBuilder2 = new IrFunctionBuilder();
            Name nameSpecial2 = Name.special("<get-" + irPropertyBuildProperty2.getName() + '>');
            nameSpecial2.getClass();
            irFunctionBuilder2.setName(nameSpecial2);
            irFunctionBuilder2.setReturnType(lambdaType);
            descriptorVisibility.getClass();
            irFunctionBuilder2.setVisibility(descriptorVisibility);
            IrSimpleFunction irSimpleFunctionBuildFunction2 = DeclarationBuildersKt.buildFunction(irPropertyBuildProperty2.getFactory(), irFunctionBuilder2);
            irPropertyBuildProperty2.setGetter(irSimpleFunctionBuildFunction2);
            irSimpleFunctionBuildFunction2.setCorrespondingPropertySymbol(irPropertyBuildProperty2.getSymbol());
            irSimpleFunctionBuildFunction2.setParent(irPropertyBuildProperty2.getParent());
            IrValueParameter thisReceiver2 = orCreateComposableSingletonsClass.getThisReceiver();
            thisReceiver2.getClass();
            IrValueParameter irValueParameterCopyTo$default2 = IrUtilsKt.copyTo$default(thisReceiver2, irSimpleFunctionBuildFunction2, (IrDeclarationOrigin) null, 0, 0, (Name) null, (Map) null, (IrType) null, (IrType) null, (IrExpressionBody) null, false, false, false, (IrParameterKind) null, (Map) null, 16382, (Object) null);
            irSimpleFunctionBuildFunction2.setParent(orCreateComposableSingletonsClass);
            irSimpleFunctionBuildFunction2.setParameters(CollectionsKt.plus(irSimpleFunctionBuildFunction2.getParameters(), irValueParameterCopyTo$default2));
            DeclarationIrBuilder declarationIrBuilder2 = new DeclarationIrBuilder(getContext(), irSimpleFunctionBuildFunction2.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
            IrBlockBodyBuilder irBlockBodyBuilder2 = new IrBlockBodyBuilder(declarationIrBuilder2.getContext(), declarationIrBuilder2.getScope(), declarationIrBuilder2.getStartOffset(), declarationIrBuilder2.getEndOffset());
            irBlockBodyBuilder2.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder2, ExpressionHelpersKt.irCall(irBlockBodyBuilder2, irSimpleFunctionBuildFunction)));
            irSimpleFunctionBuildFunction2.setBody(irBlockBodyBuilder2.doBuild());
        }
        IrSimpleFunction getter = irPropertyBuildProperty.getGetter();
        getter.getClass();
        return markAsComposableSingleton(AbstractComposeLowering.irCall$default(this, getter.getSymbol(), null, null, BuildersKt.IrGetObjectValueImpl(-1, -1, IrUtilsKt.getDefaultType(orCreateComposableSingletonsClass), orCreateComposableSingletonsClass.getSymbol()), null, new IrExpression[0], 22, null));
    }

    private final IrExpression irRemember(List<? extends IrExpression> captures, final IrExpression expression) {
        int size;
        IrSimpleFunction irSimpleFunction;
        Iterator<T> it = this.rememberFunctions.iterator();
        Object obj = null;
        boolean z = false;
        while (true) {
            size = 1;
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                break;
            }
            Object next = it.next();
            IrSimpleFunction irSimpleFunction2 = (IrSimpleFunction) next;
            if (irSimpleFunction2.getParameters().size() == captures.size() + 1) {
                IrValueParameter irValueParameter = (IrValueParameter) CollectionsKt.firstOrNull(irSimpleFunction2.getParameters());
                if ((irValueParameter != null ? irValueParameter.getVarargElementType() : null) != null) {
                    continue;
                } else if (!z) {
                    obj = next;
                    z = true;
                }
            }
            obj = null;
            break;
        }
        IrSimpleFunction irSimpleFunction3 = (IrSimpleFunction) obj;
        if (irSimpleFunction3 == null) {
            Object obj2 = null;
            boolean z2 = false;
            for (Object obj3 : this.rememberFunctions) {
                IrValueParameter irValueParameter2 = (IrValueParameter) CollectionsKt.firstOrNull(((IrSimpleFunction) obj3).getParameters());
                if ((irValueParameter2 != null ? irValueParameter2.getVarargElementType() : null) != null) {
                    if (z2) {
                        w01.a("Collection contains more than one matching element.");
                        return null;
                    }
                    z2 = true;
                    obj2 = obj3;
                }
            }
            if (!z2) {
                hb9.a("Collection contains no element matching the predicate.");
                return null;
            }
            irSimpleFunction = (IrSimpleFunction) obj2;
        } else {
            irSimpleFunction = irSimpleFunction3;
        }
        IrSimpleFunctionSymbol symbol = irSimpleFunction.getSymbol();
        IrPluginContext context = getContext();
        FunctionContext functionContext = this.currentFunctionContext;
        functionContext.getClass();
        IrCall irCallIrCall$default = ExpressionHelpersKt.irCall$default(new DeclarationIrBuilder(context, functionContext.mo278getDeclaration().getSymbol(), expression.getStartOffset(), expression.getEndOffset()), symbol, expression.getType(), 0, ComposeMemoizedLambdaOrigin.INSTANCE, 4, (Object) null);
        irCallIrCall$default.getTypeArguments().set(0, expression.getType());
        if (irSimpleFunction3 != null) {
            int size2 = captures.size();
            for (int i = 0; i < size2; i++) {
                irCallIrCall$default.getArguments().set(i, captures.get(i));
            }
            size = captures.size();
        } else {
            irCallIrCall$default.getArguments().set(0, BuildersKt.IrVarargImpl(-1, -1, ((IrValueParameter) irSimpleFunction.getParameters().get(0)).getType(), getContext().getIrBuiltIns().getAnyType(), captures));
        }
        irCallIrCall$default.getArguments().set(size, irLambdaExpression(expression.getStartOffset(), expression.getEndOffset(), expression.getType(), new Function1() { // from class: gn2
            public final Object invoke(Object obj4) {
                return ComposerLambdaMemoization.irRemember$lambda$2$0(this.b, expression, (IrSimpleFunction) obj4);
            }
        }));
        return irCallIrCall$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit irRemember$lambda$2$0(ComposerLambdaMemoization composerLambdaMemoization, IrExpression irExpression, IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(composerLambdaMemoization.getContext(), irSimpleFunction.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder, irExpression));
        irSimpleFunction.setBody(irBlockBodyBuilder.doBuild());
        return Unit.INSTANCE;
    }

    private final boolean isInlineableFunction(IrValueDeclaration irValueDeclaration) {
        return IrTypeUtilsKt.isFunctionOrKFunction(irValueDeclaration.getType()) || AbstractComposeLoweringKt.isSyntheticComposableFunction(irValueDeclaration.getType()) || IrTypeUtilsKt.isSuspendFunctionOrKFunction(irValueDeclaration.getType());
    }

    private final boolean isInlinedLambda(IrValueDeclaration irValueDeclaration) {
        if (!isInlineableFunction(irValueDeclaration) || !(irValueDeclaration instanceof IrValueParameter)) {
            return false;
        }
        IrValueParameter irValueParameter = (IrValueParameter) irValueDeclaration;
        IrFunction parent = irValueParameter.getParent();
        IrFunction irFunction = parent instanceof IrFunction ? parent : null;
        return (irFunction == null || !irFunction.isInline() || irValueParameter.isNoinline()) ? false : true;
    }

    private final boolean isNullOrStable(IrExpression irExpression, IrFile irFile) {
        return irExpression == null || StabilityKt.knownStable(getStabilityInferencer().stabilityOf(irExpression, irFile));
    }

    private final boolean isPropertyReferenceDelegate(IrValueDeclaration irValueDeclaration) {
        return Intrinsics.areEqual(irValueDeclaration.getOrigin(), IrDeclarationOrigin.Companion.getPROPERTY_DELEGATE()) && (irValueDeclaration instanceof IrVariable) && (((IrVariable) irValueDeclaration).getInitializer() instanceof IrPropertyReference);
    }

    private final boolean isStable(IrValueDeclaration irValueDeclaration) {
        return StabilityKt.knownStable(getStabilityInferencer().stabilityOf(irValueDeclaration.getType(), IrUtilsKt.getFile(irValueDeclaration)));
    }

    private final boolean isVar(IrValueDeclaration irValueDeclaration) {
        IrVariable irVariable = irValueDeclaration instanceof IrVariable ? (IrVariable) irValueDeclaration : null;
        return irVariable != null && irVariable.isVar();
    }

    public static IrSimpleFunctionSymbol k(ComposerLambdaMemoization composerLambdaMemoization) {
        return (IrSimpleFunctionSymbol) CollectionsKt.singleOrNull(composerLambdaMemoization.getTopLevelFunctions(ComposeCallableIds.INSTANCE.getRememberComposableLambdaN()));
    }

    public static IrSimpleFunctionSymbol l(ComposerLambdaMemoization composerLambdaMemoization) {
        return composerLambdaMemoization.getTopLevelFunction(ComposeCallableIds.INSTANCE.getComposableLambda());
    }

    public static IrSimpleFunctionSymbol m(ComposerLambdaMemoization composerLambdaMemoization) {
        return composerLambdaMemoization.getTopLevelFunction(ComposeCallableIds.INSTANCE.getComposableLambdaN());
    }

    private final <T extends IrElement> T markAsComposableSingleton(T t) {
        WeakBindingTraceKt.getIrTrace(getContext()).record(ComposeWritableSlices.INSTANCE.getIS_COMPOSABLE_SINGLETON(), t, Boolean.TRUE);
        return t;
    }

    private final <T extends IrElement> T markAsComposableSingletonClass(T t) {
        WeakBindingTraceKt.getIrTrace(getContext()).record(ComposeWritableSlices.INSTANCE.getIS_COMPOSABLE_SINGLETON_CLASS(), t, Boolean.TRUE);
        return t;
    }

    private final <T extends IrExpression> T markAsStatic(T t, boolean z) {
        if (z) {
            WeakBindingTraceKt.getIrTrace(getContext()).record(ComposeWritableSlices.INSTANCE.getIS_STATIC_FUNCTION_EXPRESSION(), t, Boolean.TRUE);
        }
        return t;
    }

    private final <T extends IrElement> T markHasTransformedLambda(T t) {
        WeakBindingTraceKt.getIrTrace(getContext()).record(ComposeWritableSlices.INSTANCE.getHAS_TRANSFORMED_LAMBDA(), t, Boolean.TRUE);
        return t;
    }

    private final <T extends IrElement> T markIsTransformedLambda(T t) {
        WeakBindingTraceKt.getIrTrace(getContext()).record(ComposeWritableSlices.INSTANCE.getIS_TRANSFORMED_LAMBDA(), t, Boolean.TRUE);
        return t;
    }

    public static boolean n(IrTypeParameter irTypeParameter) {
        irTypeParameter.getClass();
        return true;
    }

    public static IrSimpleFunctionSymbol o(ComposerLambdaMemoization composerLambdaMemoization) {
        return composerLambdaMemoization.getTopLevelFunction(ComposeCallableIds.INSTANCE.getComposableLambdaInstance());
    }

    public static IrSimpleFunctionSymbol p(ComposerLambdaMemoization composerLambdaMemoization) {
        return (IrSimpleFunctionSymbol) CollectionsKt.singleOrNull(composerLambdaMemoization.getTopLevelFunctions(ComposeCallableIds.INSTANCE.getRememberComposableLambda()));
    }

    public static Unit q(ComposerLambdaMemoization composerLambdaMemoization, IrExpression irExpression, IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(composerLambdaMemoization.getContext(), irSimpleFunction.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder, irExpression));
        irSimpleFunction.setBody(irBlockBodyBuilder.doBuild());
        return Unit.INSTANCE;
    }

    private final IrExpression rememberExpression(FunctionContext functionContext, IrExpression expression, List<? extends IrValueDeclaration> captures) {
        if (!JsPlatformKt.isJs(getContext().getPlatform()) && ((!JvmPlatformKt.isJvm(getContext().getPlatform()) || !getContext().getLanguageVersionSettings().getLanguageVersion().getUsesK2()) && captures.isEmpty())) {
            getMetrics().recordLambda(false, true, true);
            return markAsStatic(expression, true);
        }
        if (!IrUtilsKt.hasAnnotation(functionContext.mo278getDeclaration(), ComposeFqNames.INSTANCE.getDontMemoize()) && !getHasDontMemoizeAnnotation(expression)) {
            List<? extends IrValueDeclaration> list = captures;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                for (IrValueDeclaration irValueDeclaration : list) {
                    if (isVar(irValueDeclaration) || ((!isStable(irValueDeclaration) && !getEnabled(FeatureFlag.StrongSkipping)) || isPropertyReferenceDelegate(irValueDeclaration) || isInlinedLambda(irValueDeclaration))) {
                    }
                }
            }
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(irGet((IrValueDeclaration) it.next()));
            }
            getMetrics().recordLambda(false, true, false);
            return PatchDeclarationParentsKt.patchDeclarationParents(!getEnabled(FeatureFlag.IntrinsicRemember) ? irCache(arrayList, expression, IrUtilsKt.getFileOrNull(functionContext.mo278getDeclaration())) : irRemember(arrayList, expression), functionContext.mo278getDeclaration());
        }
        getMetrics().recordLambda(false, false, false);
        return expression;
    }

    private final IrExpression rememberFunctionReference(IrFunctionReference reference, IrExpression expression) {
        boolean z;
        Set setRecordLocalCapture = Intrinsics.areEqual(reference.getSymbol().getOwner().getVisibility(), DescriptorVisibilities.LOCAL) ? ComposerLambdaMemoizationKt.recordLocalCapture(this.declarationContextStack, reference.getSymbol().getOwner()) : null;
        FunctionContext functionContext = this.currentFunctionContext;
        if (functionContext != null && functionContext.getCanRemember()) {
            IrMemberAccessExpression.ValueArgumentsList arguments = reference.getArguments();
            boolean z2 = true;
            if (arguments != null && arguments.isEmpty()) {
                z = true;
                break;
            }
            Iterator it = arguments.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                if (((IrExpression) it.next()) != null) {
                    z = false;
                    break;
                }
            }
            IrMemberAccessExpression.ValueArgumentsList arguments2 = reference.getArguments();
            if (arguments2 == null || !arguments2.isEmpty()) {
                Iterator it2 = arguments2.iterator();
                while (it2.hasNext()) {
                    if (!isNullOrStable((IrExpression) it2.next(), IrUtilsKt.getFileOrNull(functionContext.mo278getDeclaration()))) {
                        z2 = false;
                        break;
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            if (setRecordLocalCapture != null) {
                arrayList.addAll(setRecordLocalCapture);
            }
            if (!z && (getEnabled(FeatureFlag.StrongSkipping) || z2)) {
                DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(getContext(), functionContext.mo278getDeclaration().getSymbol(), expression.getStartOffset(), expression.getEndOffset());
                IrBlockBuilder irBlockBuilder = new IrBlockBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset(), (IrStatementOrigin) null, expression.getType(), false, 64, (DefaultConstructorMarker) null);
                int size = reference.getArguments().size();
                for (int i = 0; i < size; i++) {
                    if (reference.getArguments().get(i) != null) {
                        IrVariable irVariableIrTemporary$default = ExpressionHelpersKt.irTemporary$default(irBlockBuilder, (IrExpression) reference.getArguments().get(i), (String) null, (IrType) null, false, (IrDeclarationOrigin) null, 30, (Object) null);
                        arrayList.add(irVariableIrTemporary$default);
                        reference.getArguments().set(i, ExpressionHelpersKt.irGet(irBlockBuilder, irVariableIrTemporary$default));
                    }
                }
                irBlockBuilder.unaryPlus(rememberExpression(functionContext, expression, arrayList));
                return irBlockBuilder.doBuild();
            }
            if (z) {
                return rememberExpression(functionContext, expression, arrayList);
            }
        }
        return expression;
    }

    public static IrSimpleFunctionSymbol s(ComposerLambdaMemoization composerLambdaMemoization) {
        return composerLambdaMemoization.getTopLevelFunction(ComposeCallableIds.INSTANCE.getComposableLambdaNInstance());
    }

    private final void startCollector(CaptureCollector collector) {
        Iterator<DeclarationContext> it = this.declarationContextStack.iterator();
        while (it.hasNext()) {
            it.next().pushCollector(collector);
        }
    }

    private final void stopCollector(CaptureCollector collector) {
        Iterator<DeclarationContext> it = this.declarationContextStack.iterator();
        while (it.hasNext()) {
            it.next().popCollector(collector);
        }
    }

    public static boolean t(ComposerLambdaMemoization composerLambdaMemoization) {
        return composerLambdaMemoization.getEnabled(FeatureFlag.OptimizeNonSkippingGroups) && composerLambdaMemoization.getRememberComposableLambdaFunction() != null;
    }

    private final IrExpression visitComposableFunctionExpression(IrFunctionExpression expression, DeclarationContext declarationContext) {
        CaptureCollector captureCollector = new CaptureCollector();
        startCollector(captureCollector);
        IrExpression irExpressionVisitFunctionExpression = super.visitFunctionExpression(expression);
        stopCollector(captureCollector);
        IrFunctionExpression irFunctionExpression = irExpressionVisitFunctionExpression instanceof IrFunctionExpression ? (IrFunctionExpression) irExpressionVisitFunctionExpression : null;
        if (irFunctionExpression == null) {
            return irExpressionVisitFunctionExpression;
        }
        if (this.inlineLambdaInfo.isInlineLambda(expression.getFunction())) {
            return irFunctionExpression;
        }
        if (!IrTypePredicatesKt.isUnit(irFunctionExpression.getFunction().getReturnType())) {
            getMetrics().recordLambda(true, !captureCollector.getHasCaptures(), !captureCollector.getHasCaptures());
            return irFunctionExpression;
        }
        getMetrics().recordLambda(true, true, !captureCollector.getHasCaptures());
        FunctionContext functionContext = this.currentFunctionContext;
        boolean z = functionContext != null && functionContext.getComposable();
        if (captureCollector.getHasCaptures()) {
            return wrapFunctionExpression(declarationContext, irFunctionExpression, captureCollector, z);
        }
        FunctionContext functionContext2 = this.currentFunctionContext;
        IrFunction irFunctionMo278getDeclaration = functionContext2 != null ? functionContext2.mo278getDeclaration() : null;
        boolean z2 = irFunctionMo278getDeclaration != null && JvmIrInlineUtilsKt.isInPublicInlineScope(irFunctionMo278getDeclaration);
        if (!JvmPlatformKt.isJvm(getContext().getPlatform()) && hasTypeParameter(expression.getType())) {
            return wrapFunctionExpression(declarationContext, irFunctionExpression, captureCollector, z);
        }
        IrCall irCallIrGetComposableSingleton = irGetComposableSingleton(wrapFunctionExpression(declarationContext, irFunctionExpression, captureCollector, false), expression.getType(), createSingletonLambdaName(irFunctionExpression));
        if (!z2) {
            return irCallIrGetComposableSingleton;
        }
        IrDeclarationParent parent = irFunctionExpression.getFunction().getParent();
        DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
        IrVisitorsKt.acceptVoid(irFunctionExpression, deepCopySymbolRemapper);
        IrFunctionExpression irFunctionExpressionTransform = irFunctionExpression.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
        if (irFunctionExpressionTransform == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrFunctionExpression");
            return null;
        }
        IrCall irCallWrapFunctionExpression = wrapFunctionExpression(declarationContext, (IrFunctionExpression) PatchDeclarationParentsKt.patchDeclarationParents(irFunctionExpressionTransform, parent), captureCollector, z);
        ComposePluginAttributesKt.setAssociatedComposableSingletonStub(irCallWrapFunctionExpression, irCallIrGetComposableSingleton);
        return irCallWrapFunctionExpression;
    }

    private final IrExpression visitNonComposableFunctionExpression(IrFunctionExpression expression) {
        FunctionContext functionContext = this.currentFunctionContext;
        if (functionContext == null) {
            return super.visitFunctionExpression(expression);
        }
        if (!functionContext.getCanRemember() || this.inlineLambdaInfo.isInlineLambda(expression.getFunction())) {
            return super.visitFunctionExpression(expression);
        }
        CaptureCollector captureCollector = new CaptureCollector();
        startCollector(captureCollector);
        IrFunctionExpression irFunctionExpressionVisitFunctionExpression = super.visitFunctionExpression(expression);
        stopCollector(captureCollector);
        IrFunctionExpression irFunctionExpression = irFunctionExpressionVisitFunctionExpression instanceof IrFunctionExpression ? irFunctionExpressionVisitFunctionExpression : null;
        return irFunctionExpression == null ? irFunctionExpressionVisitFunctionExpression : rememberExpression(functionContext, irFunctionExpression, CollectionsKt.toList(captureCollector.getCaptures()));
    }

    private final IrCall wrapFunctionExpression(DeclarationContext declarationContext, IrFunctionExpression expression, CaptureCollector collector, boolean useComposableFactory) {
        IrSimpleFunction function = expression.getFunction();
        int size = function.getParameters().size();
        if (size > 22 && !JvmPlatformKt.isJvm(getContext().getPlatform())) {
            k2d.a("only 22 parameters in @Composable lambda are supported onnon-JVM targets (K/JS or K/Wasm or K/Native)");
            return null;
        }
        int i = 0;
        boolean z = size > 22;
        IrSimpleFunctionSymbol rememberComposableLambdaFunction = getRememberComposableLambdaFunction();
        if (rememberComposableLambdaFunction == null) {
            rememberComposableLambdaFunction = getComposableLambdaFunction();
        }
        boolean z2 = useComposableFactory && getRememberComposableLambdaFunction() == null;
        if (!useComposableFactory) {
            rememberComposableLambdaFunction = z ? getComposableLambdaInstanceNFunction() : getComposableLambdaInstanceFunction();
        } else if (z && (rememberComposableLambdaFunction = getRememberComposableLambdaNFunction()) == null) {
            rememberComposableLambdaFunction = getComposableLambdaNFunction();
        }
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(getContext(), declarationContext.getDeclaration().getSymbol(), expression.getStartOffset(), expression.getEndOffset());
        IrCall irCall = ExpressionHelpersKt.irCall(declarationIrBuilder, rememberComposableLambdaFunction);
        if (z2) {
            irCall.getArguments().set(0, irCurrentComposer());
            i = 1;
        }
        irCall.getArguments().set(i, ExpressionHelpersKt.irInt$default(declarationIrBuilder, sourceKey(expression.getFunction()), (IrType) null, 2, (Object) null));
        int i2 = i + 2;
        irCall.getArguments().set(i + 1, ExpressionHelpersKt.irBoolean(declarationIrBuilder, !collector.getCaptures().isEmpty()));
        if (z) {
            irCall.getArguments().set(i2, ExpressionHelpersKt.irInt$default(declarationIrBuilder, size, (IrType) null, 2, (Object) null));
            i2 = i + 3;
        }
        if (i2 < irCall.getArguments().size()) {
            irCall.getArguments().set(i2, markIsTransformedLambda(expression));
            return markHasTransformedLambda(irCall);
        }
        throw new IllegalStateException(("function = " + RenderIrElementKt.render$default(function, (DumpIrTreeOptions) null, 1, (Object) null) + ", count = " + irCall.getArguments().size() + ", index = " + i2).toString());
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        this.inlineLambdaInfo.scan(irModule);
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
    }

    public IrStatement visitAnonymousInitializer(IrAnonymousInitializer declaration) {
        declaration.getClass();
        AnonymousInitializerContext anonymousInitializerContext = new AnonymousInitializerContext(declaration);
        ComposerLambdaMemoizationKt.recordLocalDeclaration(this.declarationContextStack, anonymousInitializerContext);
        UtilsKt.push(this.declarationContextStack, anonymousInitializerContext);
        IrStatement irStatementVisitAnonymousInitializer = super.visitAnonymousInitializer(declaration);
        UtilsKt.pop(this.declarationContextStack);
        return irStatementVisitAnonymousInitializer;
    }

    public IrExpression visitBlock(IrBlock expression) {
        expression.getClass();
        IrBlock irBlockVisitBlock = super.visitBlock(expression);
        if (irBlockVisitBlock instanceof IrBlock) {
            IrBlock irBlock = irBlockVisitBlock;
            if (Intrinsics.areEqual(irBlock.getOrigin(), IrStatementOrigin.Companion.getADAPTED_FUNCTION_REFERENCE()) && !this.inlineLambdaInfo.isInlineFunctionExpression(expression)) {
                IrStatement irStatement = (IrStatement) CollectionsKt.last(irBlock.getStatements());
                if (irStatement instanceof IrFunctionReference) {
                    return rememberFunctionReference((IrFunctionReference) irStatement, expression);
                }
            }
            return irBlockVisitBlock;
        }
        return irBlockVisitBlock;
    }

    public IrExpression visitCall(IrCall expression) {
        expression.getClass();
        IrSimpleFunction owner = expression.getSymbol().getOwner();
        if (Intrinsics.areEqual(owner.getVisibility(), DescriptorVisibilities.LOCAL)) {
            ComposerLambdaMemoizationKt.recordLocalCapture(this.declarationContextStack, owner);
        }
        return super.visitCall(expression);
    }

    public IrStatement visitClass(IrClass declaration) {
        declaration.getClass();
        ClassContext classContext = new ClassContext(declaration);
        if (AdditionalIrUtilsKt.isLocal(declaration)) {
            ComposerLambdaMemoizationKt.recordLocalDeclaration(this.declarationContextStack, classContext);
        }
        UtilsKt.push(this.declarationContextStack, classContext);
        FunctionContext functionContext = this.currentFunctionContext;
        this.currentFunctionContext = null;
        IrStatement irStatementVisitClass = super.visitClass(declaration);
        this.currentFunctionContext = functionContext;
        UtilsKt.pop(this.declarationContextStack);
        return irStatementVisitClass;
    }

    public IrExpression visitConstructorCall(IrConstructorCall expression) {
        expression.getClass();
        IrConstructor owner = expression.getSymbol().getOwner();
        IrClass parent = owner.getParent();
        IrClass irClass = parent instanceof IrClass ? parent : null;
        if (irClass != null && AdditionalIrUtilsKt.isLocal(owner)) {
            ComposerLambdaMemoizationKt.recordLocalCapture(this.declarationContextStack, irClass);
        }
        return super.visitConstructorCall(expression);
    }

    public IrStatement visitDeclaration(IrDeclarationBase declaration) {
        declaration.getClass();
        if ((declaration instanceof IrClass) || (declaration instanceof IrFunction) || (declaration instanceof IrAnonymousInitializer)) {
            return super.visitDeclaration(declaration);
        }
        UtilsKt.push(this.declarationContextStack, new SymbolOwnerContext(declaration));
        IrStatement irStatementVisitDeclaration = super.visitDeclaration(declaration);
        UtilsKt.pop(this.declarationContextStack);
        return irStatementVisitDeclaration;
    }

    public IrFile visitFile(IrFile declaration) throws Exception {
        declaration.getClass();
        try {
            IrFile irFile = this.currentFile;
            IrClass irClass = this.composableSingletonsClass;
            try {
                this.currentFile = declaration;
                this.composableSingletonsClass = null;
                this.usedSingletonLambdaNames.clear();
                IrFile irFileVisitFile = super.visitFile(declaration);
                IrClass irClass2 = this.composableSingletonsClass;
                if (irClass2 != null && !irClass2.getDeclarations().isEmpty()) {
                    IrUtilsKt.addChild(irFileVisitFile, irClass2);
                }
                return irFileVisitFile;
            } finally {
                this.currentFile = irFile;
                this.composableSingletonsClass = irClass;
            }
        } catch (Exception e) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
            throw new Exception("IR lowering failed at: " + IrDeclarationsKt.getName(declaration), e);
        }
    }

    public IrStatement visitFunction(IrFunction declaration) {
        FunctionContext functionContext;
        declaration.getClass();
        boolean allowsComposableCalls = getAllowsComposableCalls(declaration);
        int enclosingTryCount = 0;
        if (this.inlineLambdaInfo.isInlineLambda(declaration) && (functionContext = this.currentFunctionContext) != null) {
            enclosingTryCount = functionContext.getEnclosingTryCount();
        }
        FunctionContext functionContext2 = new FunctionContext(declaration, allowsComposableCalls, enclosingTryCount);
        if (AdditionalIrUtilsKt.isLocal(declaration)) {
            ComposerLambdaMemoizationKt.recordLocalDeclaration(this.declarationContextStack, functionContext2);
        }
        UtilsKt.push(this.declarationContextStack, functionContext2);
        FunctionContext functionContext3 = this.currentFunctionContext;
        this.currentFunctionContext = functionContext2;
        IrStatement irStatementVisitFunction = super.visitFunction(declaration);
        this.currentFunctionContext = functionContext3;
        UtilsKt.pop(this.declarationContextStack);
        return irStatementVisitFunction;
    }

    public IrExpression visitFunctionExpression(IrFunctionExpression expression) {
        expression.getClass();
        DeclarationContext declarationContext = (DeclarationContext) UtilsKt.peek(this.declarationContextStack);
        if (declarationContext == null) {
            return super.visitFunctionExpression(expression);
        }
        return getAllowsComposableCalls(expression.getFunction()) ? visitComposableFunctionExpression(expression, declarationContext) : visitNonComposableFunctionExpression(expression);
    }

    public IrExpression visitFunctionReference(IrFunctionReference expression) {
        expression.getClass();
        IrExpression irExpressionVisitFunctionReference = super.visitFunctionReference(expression);
        return (this.inlineLambdaInfo.isInlineFunctionExpression(expression) || this.inlineLambdaInfo.isInlineLambda(expression.getSymbol().getOwner()) || Intrinsics.areEqual(expression.getSymbol().getOwner().getOrigin(), IrDeclarationOrigin.Companion.getADAPTER_FOR_CALLABLE_REFERENCE()) || !(irExpressionVisitFunctionReference instanceof IrFunctionReference)) ? irExpressionVisitFunctionReference : rememberFunctionReference((IrFunctionReference) irExpressionVisitFunctionReference, irExpressionVisitFunctionReference);
    }

    public IrExpression visitTry(IrTry aTry) {
        aTry.getClass();
        FunctionContext functionContext = this.currentFunctionContext;
        if (functionContext != null) {
            functionContext.setEnclosingTryCount(functionContext.getEnclosingTryCount() + 1);
        }
        IrExpression irExpressionVisitExpression = super.visitExpression(aTry);
        FunctionContext functionContext2 = this.currentFunctionContext;
        if (functionContext2 != null) {
            functionContext2.setEnclosingTryCount(functionContext2.getEnclosingTryCount() - 1);
        }
        return irExpressionVisitExpression;
    }

    public IrExpression visitTypeOperator(IrTypeOperatorCall expression) {
        FunctionContext functionContext;
        expression.getClass();
        IrTypeOperator operator = expression.getOperator();
        IrTypeOperator irTypeOperator = IrTypeOperator.SAM_CONVERSION;
        if (operator != irTypeOperator || (functionContext = this.currentFunctionContext) == null || !functionContext.getCanRemember()) {
            return super.visitTypeOperator(expression);
        }
        IrFunctionExpression irFunctionExpressionFindSamFunctionExpr = ComposableFunInterfaceLoweringKt.findSamFunctionExpr(expression);
        if (irFunctionExpressionFindSamFunctionExpr == null) {
            return super.visitTypeOperator(expression);
        }
        CaptureCollector captureCollector = new CaptureCollector();
        startCollector(captureCollector);
        IrExpression irExpressionVisitFunctionExpression = super.visitFunctionExpression(irFunctionExpressionFindSamFunctionExpr);
        stopCollector(captureCollector);
        IrExpression irExpressionIrTypeOperatorCallImpl = irExpressionVisitFunctionExpression instanceof IrFunctionExpression ? (IrFunctionExpression) irExpressionVisitFunctionExpression : null;
        if (irExpressionIrTypeOperatorCallImpl == null) {
            return irExpressionVisitFunctionExpression;
        }
        IrTypeOperatorCall argument = expression.getArgument();
        if (!(argument instanceof IrFunctionExpression)) {
            if (!(argument instanceof IrTypeOperatorCall)) {
                k2d.a("Unknown ");
                return null;
            }
            IrTypeOperatorCall irTypeOperatorCall = argument;
            if (irTypeOperatorCall.getOperator() != IrTypeOperator.IMPLICIT_CAST || !Intrinsics.areEqual(irTypeOperatorCall.getArgument(), irFunctionExpressionFindSamFunctionExpr)) {
                w01.a("Only implicit cast is supported inside SAM conversion");
                return null;
            }
            irExpressionIrTypeOperatorCallImpl = BuildersKt.IrTypeOperatorCallImpl(argument.getStartOffset(), argument.getEndOffset(), argument.getType(), irTypeOperatorCall.getOperator(), irTypeOperatorCall.getTypeOperand(), irExpressionIrTypeOperatorCallImpl);
        }
        IrTypeOperatorCallImpl IrTypeOperatorCallImpl = BuildersKt.IrTypeOperatorCallImpl(expression.getStartOffset(), expression.getEndOffset(), expression.getType(), irTypeOperator, expression.getTypeOperand(), irExpressionIrTypeOperatorCallImpl);
        FunctionContext functionContext2 = this.currentFunctionContext;
        functionContext2.getClass();
        return rememberExpression(functionContext2, IrTypeOperatorCallImpl, CollectionsKt.toList(captureCollector.getCaptures()));
    }

    public IrExpression visitValueAccess(IrValueAccessExpression expression) {
        expression.getClass();
        ComposerLambdaMemoizationKt.recordCapture(this.declarationContextStack, expression.getSymbol().getOwner());
        return super.visitValueAccess(expression);
    }

    public IrStatement visitVariable(IrVariable declaration) {
        declaration.getClass();
        FunctionContext functionContext = this.currentFunctionContext;
        if (functionContext != null) {
            functionContext.declareLocal(declaration);
        }
        return super.visitVariable(declaration);
    }
}

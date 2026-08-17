package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds;
import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import androidx.compose.compiler.plugins.kotlin.ComposeNames;
import androidx.compose.compiler.plugins.kotlin.ComposeRuntimeFeature;
import androidx.compose.compiler.plugins.kotlin.ComposeRuntimeVersion;
import androidx.compose.compiler.plugins.kotlin.FeatureFlag;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.FunctionMetrics;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.RuntimeFeaturesKt;
import androidx.compose.compiler.plugins.kotlin.WeakBindingTraceKt;
import androidx.compose.compiler.plugins.kotlin.analysis.ComposeWritableSlices;
import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.sequences.Sequence;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.FileLoweringPass;
import org.jetbrains.kotlin.backend.common.UtilsKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder;
import org.jetbrains.kotlin.backend.jvm.ir.JvmIrInlineUtilsKt;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrFileEntry;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.builders.ExpressionHelpersKt;
import org.jetbrains.kotlin.ir.builders.IrBlockBodyBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeAlias;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.declarations.impl.BuildersKt;
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrBreakContinue;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrComposite;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.expressions.IrContinue;
import org.jetbrains.kotlin.ir.expressions.IrDoWhileLoop;
import org.jetbrains.kotlin.ir.expressions.IrElseBranch;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrLoop;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrReturn;
import org.jetbrains.kotlin.ir.expressions.IrSpreadElement;
import org.jetbrains.kotlin.ir.expressions.IrStatementContainer;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrWhen;
import org.jetbrains.kotlin.ir.expressions.IrWhileLoop;
import org.jetbrains.kotlin.ir.expressions.impl.IrBlockImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCompositeImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrWhenImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrReturnTargetSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrVariableSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.PatchDeclarationParentsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.JsPlatformKt;
import org.jetbrains.kotlin.platform.jvm.JvmPlatformKt;
import org.jetbrains.kotlin.platform.konan.NativePlatformKt;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Ö\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t*\u0002À\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u000eÞ\u0002ß\u0002à\u0002á\u0002â\u0002ã\u0002ä\u0002BA\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010s\u001a\u00020tH\u0002J\u0010\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020}H\u0016J\u0010\u0010~\u001a\u00020{2\u0006\u0010|\u001a\u00020\u007fH\u0016J\u0011\u0010\u0080\u0001\u001a\u00020{2\u0006\u0010|\u001a\u00020\u007fH\u0002J0\u0010\u0081\u0001\u001a\u00020\u007f2\u0006\u0010|\u001a\u00020\u007f2\u0007\u0010\u0082\u0001\u001a\u00020w2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\n\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001H\u0002J$\u0010\u0087\u0001\u001a\u00020\u007f2\u0006\u0010|\u001a\u00020\u007f2\u0007\u0010\u0082\u0001\u001a\u00020w2\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0002J0\u0010\u0088\u0001\u001a\u00020\u007f2\u0006\u0010|\u001a\u00020\u007f2\u0007\u0010\u0082\u0001\u001a\u00020w2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\n\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001H\u0002J\u0011\u0010\u0089\u0001\u001a\u00020{2\u0006\u0010|\u001a\u00020\u007fH\u0002J\u0011\u0010\u008a\u0001\u001a\u00020{2\u0006\u0010|\u001a\u00020\u007fH\u0002J\u001a\u0010\u008b\u0001\u001a\u00020{2\u0006\u0010|\u001a\u00020\u007f2\u0007\u0010\u0082\u0001\u001a\u00020wH\u0002J'\u0010\u008f\u0001\u001a\u00020\u00162\b\u0010\u0090\u0001\u001a\u00030\u0091\u00012\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J\t\u0010\u009b\u0001\u001a\u00020\u0016H\u0002J\u0013\u0010\u009c\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u0082\u0001\u001a\u00020wH\u0002Jc\u0010\u009e\u0001\u001a\u00020\u000b2\b\u0010\u009f\u0001\u001a\u00030 \u00012\b\u0010¡\u0001\u001a\u00030¢\u00012\b\u0010£\u0001\u001a\u00030¢\u00012\u0007\u0010¤\u0001\u001a\u00020\u000b2\u0007\u0010\u0082\u0001\u001a\u00020w2\b\u0010¥\u0001\u001a\u00030\u0084\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\n\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u00012\b\u0010¦\u0001\u001a\u00030\u009d\u0001H\u0002J2\u0010§\u0001\u001a\u00030¨\u00012\b\u0010©\u0001\u001a\u00030ª\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010«\u0001\u001a\u00030\u0093\u00012\b\u0010¬\u0001\u001a\u00030\u00ad\u0001H\u0002J3\u0010®\u0001\u001a\u00030¨\u00012\u0007\u0010\u0082\u0001\u001a\u00020w2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\n\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u00012\b\u0010¯\u0001\u001a\u00030\u0093\u0001H\u0002J\u0012\u0010°\u0001\u001a\u00030\u0091\u00012\b\u0010±\u0001\u001a\u00030²\u0001J\n\u0010³\u0001\u001a\u00030\u0091\u0001H\u0002J\u001e\u0010´\u0001\u001a\u00030¨\u00012\b\u0010µ\u0001\u001a\u00030¨\u00012\b\u0010¶\u0001\u001a\u00030¨\u0001H\u0002J\n\u0010·\u0001\u001a\u00030\u0091\u0001H\u0002J\u001e\u0010¸\u0001\u001a\u00030¨\u00012\b\u0010¹\u0001\u001a\u00030\u0086\u00012\b\u0010º\u0001\u001a\u00030\u0093\u0001H\u0002J\u001e\u0010»\u0001\u001a\u00030¨\u00012\b\u0010¼\u0001\u001a\u00030\u0084\u00012\b\u0010º\u0001\u001a\u00030\u0093\u0001H\u0002J\u001e\u0010½\u0001\u001a\u00030¨\u00012\b\u0010¼\u0001\u001a\u00030\u0084\u00012\b\u0010º\u0001\u001a\u00030\u0093\u0001H\u0002J\u001e\u0010¾\u0001\u001a\u00030¨\u00012\b\u0010¼\u0001\u001a\u00030\u0084\u00012\b\u0010º\u0001\u001a\u00030\u0093\u0001H\u0002J\u001e\u0010¿\u0001\u001a\u00030¨\u00012\b\u0010À\u0001\u001a\u00030\u0093\u00012\b\u0010º\u0001\u001a\u00030\u0093\u0001H\u0002J\u000e\u0010Á\u0001\u001a\u00020\u000b*\u00030¨\u0001H\u0002J!\u0010Â\u0001\u001a\u00020\u0016*\u00030Ã\u00012\b\u0010Ä\u0001\u001a\u00030¨\u00012\u0007\u0010\u0082\u0001\u001a\u00020wH\u0002J,\u0010Å\u0001\u001a\u0013\u0012\u0005\u0012\u00030Ã\u0001\u0012\u0007\u0012\u0005\u0018\u00010Ç\u00010Æ\u0001*\u00030È\u00012\u000b\b\u0002\u0010É\u0001\u001a\u0004\u0018\u00010\u007fH\u0002J\u0011\u0010Ê\u0001\u001a\u00020{2\u0006\u0010|\u001a\u000201H\u0016J\u0012\u0010Ë\u0001\u001a\u00020{2\u0007\u0010|\u001a\u00030Ì\u0001H\u0016J\u0011\u0010Í\u0001\u001a\u00020\u001a2\u0006\u0010|\u001a\u00020\u001aH\u0016J\u0012\u0010Î\u0001\u001a\u00020{2\u0007\u0010|\u001a\u00030Ï\u0001H\u0016J\n\u0010Ð\u0001\u001a\u00030²\u0001H\u0002J.\u0010Ñ\u0001\u001a\u00030¨\u00012\n\b\u0002\u0010Ò\u0001\u001a\u00030\u0093\u00012\n\b\u0002\u0010Ó\u0001\u001a\u00030\u0093\u00012\n\b\u0002\u0010±\u0001\u001a\u00030²\u0001H\u0002J'\u0010Ñ\u0001\u001a\u00030¨\u0001*\u00030\u0094\u00012\n\b\u0002\u0010Ò\u0001\u001a\u00030\u0093\u00012\n\b\u0002\u0010Ó\u0001\u001a\u00030\u0093\u0001H\u0002J\u000f\u0010Ô\u0001\u001a\u00030\u0093\u0001*\u00030 \u0001H\u0002J\u0013\u0010Õ\u0001\u001a\u00030\u0093\u00012\u0007\u0010Ö\u0001\u001a\u00020\u007fH\u0002J\u000f\u0010×\u0001\u001a\u00030Ø\u0001*\u00030 \u0001H\u0002J\u0015\u0010Ù\u0001\u001a\u00030Ø\u00012\t\b\u0002\u0010Ö\u0001\u001a\u00020\u007fH\u0002JB\u0010Ú\u0001\u001a\u00030¨\u00012\b\u0010Û\u0001\u001a\u00030 \u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u00012\n\b\u0002\u0010Ä\u0001\u001a\u00030¨\u00012\n\b\u0002\u0010Ò\u0001\u001a\u00030\u0093\u00012\n\b\u0002\u0010Ó\u0001\u001a\u00030\u0093\u0001H\u0002J\u001e\u0010Ü\u0001\u001a\u00030¨\u00012\b\u0010Ý\u0001\u001a\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J\u0014\u0010Þ\u0001\u001a\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J*\u0010ß\u0001\u001a\u00030¨\u00012\b\u0010Û\u0001\u001a\u00030 \u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u00012\n\b\u0002\u0010Ä\u0001\u001a\u00030¨\u0001H\u0002J\u001e\u0010à\u0001\u001a\u00030¨\u00012\b\u0010Û\u0001\u001a\u00030 \u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J-\u0010á\u0001\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u00012\r\u0010ã\u0001\u001a\b\u0012\u0004\u0012\u00020{0`H\u0002J\f\u0010ä\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0002J\u0016\u0010å\u0001\u001a\u0005\u0018\u00010¨\u00012\b\u0010æ\u0001\u001a\u00030¨\u0001H\u0002J\u001f\u0010ç\u0001\u001a\u0005\u0018\u00010¨\u00012\b\u0010Ä\u0001\u001a\u00030¨\u00012\u0007\u0010\u0082\u0001\u001a\u00020wH\u0002J\f\u0010è\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0002J\u001e\u0010é\u0001\u001a\u00030¨\u00012\b\u0010Û\u0001\u001a\u00030 \u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J*\u0010ê\u0001\u001a\u00030¨\u00012\b\u0010Û\u0001\u001a\u00030 \u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u00012\n\b\u0002\u0010Ä\u0001\u001a\u00030¨\u0001H\u0002J\u0014\u0010ë\u0001\u001a\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J3\u0010ì\u0001\u001a\u00030¨\u00012\b\u0010í\u0001\u001a\u00030¨\u00012\t\u0010î\u0001\u001a\u0004\u0018\u00010\u001a2\u0007\u0010ï\u0001\u001a\u00020\u000b2\t\b\u0002\u0010ð\u0001\u001a\u00020\u000bH\u0002J\"\u0010ñ\u0001\u001a\u00030¨\u00012\n\b\u0002\u0010Ò\u0001\u001a\u00030\u0093\u00012\n\b\u0002\u0010Ó\u0001\u001a\u00030\u0093\u0001H\u0002J,\u0010ò\u0001\u001a\u00030¨\u00012\n\b\u0002\u0010Ò\u0001\u001a\u00030\u0093\u00012\n\b\u0002\u0010Ó\u0001\u001a\u00030\u0093\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J\n\u0010ó\u0001\u001a\u00030¨\u0001H\u0002J(\u0010ô\u0001\u001a\u00030¨\u00012\b\u0010Û\u0001\u001a\u00030 \u00012\b\u0010õ\u0001\u001a\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J\u0014\u0010ö\u0001\u001a\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J\u001e\u0010÷\u0001\u001a\u00030¨\u00012\b\u0010ø\u0001\u001a\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J\u001a\u0010ù\u0001\u001a\u00030¨\u00012\u000e\u0010ú\u0001\u001a\t\u0012\u0005\u0012\u00030¨\u00010`H\u0002J<\u0010û\u0001\u001a\u00030¨\u00012\b\u0010ü\u0001\u001a\u00030¨\u00012\b\u0010ý\u0001\u001a\u00030þ\u00012\u0016\u0010ÿ\u0001\u001a\f\u0012\u0007\b\u0001\u0012\u00030¨\u00010\u0080\u0002\"\u00030¨\u0001H\u0002¢\u0006\u0003\u0010\u0081\u0002JB\u0010\u0082\u0002\u001a\u00030\u0083\u00022\b\u0010í\u0001\u001a\u00030¨\u00012\u000b\b\u0002\u0010\u0084\u0002\u001a\u0004\u0018\u00010t2\t\b\u0002\u0010\u0085\u0002\u001a\u00020b2\t\b\u0002\u0010\u0086\u0002\u001a\u00020\u000b2\t\b\u0002\u0010\u0087\u0002\u001a\u00020\u000bH\u0002J%\u0010\u0088\u0002\u001a\u00030¨\u0001*\u00030\u0089\u00022\b\u0010\u0082\u0001\u001a\u00030\u0094\u00012\n\b\u0002\u0010\u008a\u0002\u001a\u00030\u0093\u0001H\u0002J\u0019\u0010\u008b\u0002\u001a\u00030¨\u0001*\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J\u0019\u0010\u008c\u0002\u001a\u00030\u008d\u0002*\u00030¨\u00012\b\u0010\u008e\u0002\u001a\u00030Ç\u0001H\u0002J/\u0010\u008f\u0002\u001a\u00030Ã\u0001*\u00030¨\u00012\u000f\b\u0002\u0010ã\u0001\u001a\b\u0012\u0004\u0012\u00020{0`2\u000f\b\u0002\u0010\u0090\u0002\u001a\b\u0012\u0004\u0012\u00020{0`J\u0019\u0010\u0091\u0002\u001a\u00030¨\u0001*\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u00030\u0094\u0001H\u0002J\u0018\u0010\u0092\u0002\u001a\u00030Ã\u0001*\u00030Ã\u00012\u0007\u0010\u0082\u0001\u001a\u00020wH\u0002J\n\u0010\u0093\u0002\u001a\u00030Ã\u0001H\u0002J\u0012\u0010\u0094\u0002\u001a\u00020\u00162\u0007\u0010\u0095\u0002\u001a\u00020\u000bH\u0002J\u0013\u0010\u0096\u0002\u001a\u00020\u00162\b\u0010\u0090\u0001\u001a\u00030 \u0001H\u0002J\t\u0010\u0097\u0002\u001a\u00020\u0016H\u0002J4\u0010\u0098\u0002\u001a\u00020\u00162\b\u0010\u0099\u0002\u001a\u00030\u0094\u00012\u000e\u0010\u009a\u0002\u001a\t\u0012\u0004\u0012\u00020\u00160\u009b\u00022\u000f\u0010\u009c\u0002\u001a\n\u0012\u0005\u0012\u00030¨\u00010\u009b\u0002H\u0002J*\u0010\u009d\u0002\u001a\u00020\u00162\b\u0010ý\u0001\u001a\u00030\u009e\u00022\u0015\u0010\u009f\u0002\u001a\u0010\u0012\u0005\u0012\u00030¨\u0001\u0012\u0004\u0012\u00020\u00160 \u0002H\u0002J*\u0010¡\u0002\u001a\u00020\u00162\b\u0010¢\u0002\u001a\u00030£\u00022\u0015\u0010\u009f\u0002\u001a\u0010\u0012\u0005\u0012\u00030¨\u0001\u0012\u0004\u0012\u00020\u00160 \u0002H\u0002J8\u0010¤\u0002\u001a\u0011\u0012\u0005\u0012\u0003H¥\u0002\u0012\u0005\u0012\u00030¨\u00010Æ\u0001\"\t\b\u0000\u0010¥\u0002*\u00020r*\u00030¨\u00012\b\u0010\u0082\u0001\u001a\u0003H¥\u0002H\u0002¢\u0006\u0003\u0010¦\u0002J6\u0010§\u0002\u001a\u0003H¥\u0002\"\t\b\u0000\u0010¥\u0002*\u00020r2\b\u0010\u0082\u0001\u001a\u0003H¥\u00022\u000e\u0010¨\u0002\u001a\t\u0012\u0004\u0012\u00020\u00160\u009b\u0002H\u0082\b¢\u0006\u0003\u0010©\u0002J2\u0010ª\u0002\u001a\u0003H«\u0002\"\u0005\b\u0000\u0010«\u00022\u0007\u0010\u0082\u0001\u001a\u00020r2\u000f\u0010¨\u0002\u001a\n\u0012\u0005\u0012\u0003H«\u00020\u009b\u0002H\u0082\b¢\u0006\u0003\u0010¬\u0002J8\u0010\u00ad\u0002\u001a\u00020\u0016*\u00020r2(\b\u0004\u0010¨\u0002\u001a!\u0012\u0016\u0012\u00140r¢\u0006\u000f\b®\u0002\u0012\n\b¯\u0002\u0012\u0005\b\b(\u0082\u0001\u0012\u0004\u0012\u00020\u00160 \u0002H\u0082\bJ(\u0010°\u0002\u001a\u00030±\u00022\b\u0010²\u0002\u001a\u00030¨\u00012\t\u0010³\u0002\u001a\u0004\u0018\u00010\u001a2\u0007\u0010´\u0002\u001a\u00020\u000bH\u0002J\u001d\u0010µ\u0002\u001a\u00020\u00162\b\u0010²\u0002\u001a\u00030¨\u00012\b\u0010¶\u0002\u001a\u00030±\u0002H\u0002J\u0016\u0010·\u0002\u001a\u0005\u0018\u00010¸\u00022\b\u0010¬\u0001\u001a\u00030\u00ad\u0001H\u0002J\u0014\u0010¹\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030\u0089\u0002H\u0016J\u0014\u0010º\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030»\u0002H\u0016J\u0014\u0010¼\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030\u0091\u0001H\u0002J\u0014\u0010½\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030\u0091\u0001H\u0002J\u0014\u0010¾\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030\u0091\u0001H\u0002J\u001a\u0010¿\u0002\u001a\u00030À\u00022\b\u0010Á\u0002\u001a\u00030\u0091\u0001H\u0002¢\u0006\u0003\u0010Â\u0002JZ\u0010Ã\u0002\u001a\u00030¨\u00012\u0007\u0010Ä\u0002\u001a\u00020\u000b2\u000e\u0010ÿ\u0001\u001a\t\u0012\u0005\u0012\u00030¨\u00010`2\u000e\u0010Å\u0002\u001a\t\u0012\u0005\u0012\u00030±\u00020`2%\u0010Æ\u0002\u001a \u0012\u0004\u0012\u00020\u000b\u0012\u0005\u0012\u00030¨\u0001\u0012\u0005\u0012\u00030±\u0002\u0012\u0007\u0012\u0005\u0018\u00010¨\u00010Ç\u0002H\u0002J)\u0010È\u0002\u001a\u0005\u0018\u00010¨\u00012\u0007\u0010Ä\u0002\u001a\u00020\u000b2\b\u0010²\u0002\u001a\u00030¨\u00012\b\u0010É\u0002\u001a\u00030±\u0002H\u0002J\u0014\u0010Ê\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030\u0091\u0001H\u0002J \u0010Ë\u0002\u001a\t\u0012\u0005\u0012\u00030¨\u00010`2\u000e\u0010ÿ\u0001\u001a\t\u0012\u0005\u0012\u00030±\u00020`H\u0002J\u0019\u0010Ì\u0002\u001a\u00030¨\u00012\r\u0010_\u001a\t\u0012\u0005\u0012\u00030±\u00020`H\u0002J\u0016\u0010Í\u0002\u001a\u0005\u0018\u00010¨\u00012\b\u0010¬\u0001\u001a\u00030Î\u0002H\u0002J\u0014\u0010Ï\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030Ð\u0002H\u0016J\u0014\u0010Ñ\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030Ò\u0002H\u0016J\u0014\u0010Ó\u0002\u001a\u00030¨\u00012\b\u0010¢\u0002\u001a\u00030£\u0002H\u0016J\u0014\u0010Ô\u0002\u001a\u00030¨\u00012\b\u0010Õ\u0002\u001a\u00030Ö\u0002H\u0016J\u0014\u0010×\u0002\u001a\u00030¨\u00012\b\u0010Õ\u0002\u001a\u00030Ø\u0002H\u0016J\u0014\u0010Ù\u0002\u001a\u00030¨\u00012\b\u0010Õ\u0002\u001a\u00030Ú\u0002H\u0002J\u0014\u0010Û\u0002\u001a\u00030¨\u00012\b\u0010â\u0001\u001a\u00030Ü\u0002H\u0016J\u000e\u0010Ý\u0002\u001a\u00020\u000b*\u00030¨\u0001H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010!\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b\"\u0010\u001eR\u001b\u0010$\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010 \u001a\u0004\b%\u0010\u001eR\u001b\u0010'\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010 \u001a\u0004\b(\u0010\u001eR\u001b\u0010*\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010 \u001a\u0004\b+\u0010\u001eR\u001b\u0010-\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010 \u001a\u0004\b.\u0010\u001eR\u001d\u00100\u001a\u0004\u0018\u0001018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u0010 \u001a\u0004\b2\u00103R\u001d\u00105\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u0010 \u001a\u0004\b6\u0010\u001eR\u0014\u00108\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u001b\u0010;\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010 \u001a\u0004\b<\u0010\u001eR\u001d\u0010>\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010 \u001a\u0004\b?\u0010\u001eR\u001b\u0010A\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bC\u0010 \u001a\u0004\bB\u0010\u001eR\u001b\u0010D\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bF\u0010 \u001a\u0004\bE\u0010\u001eR\u001d\u0010G\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bI\u0010 \u001a\u0004\bH\u0010\u001eR\u001d\u0010J\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bK\u0010 \u001a\u0004\bJ\u0010\u001eR\u001d\u0010L\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u0010 \u001a\u0004\bM\u0010\u001eR\u001d\u0010O\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010 \u001a\u0004\bP\u0010\u001eR\u0014\u0010R\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bS\u0010:R\u001b\u0010T\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010 \u001a\u0004\bU\u0010\u001eR\u001d\u0010W\u001a\u0004\u0018\u00010X8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b[\u0010 \u001a\u0004\bY\u0010ZR\u001b\u0010\\\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b^\u0010 \u001a\u0004\b]\u0010:R\u001e\u0010_\u001a\b\u0012\u0004\u0012\u00020a0`*\u00020b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bc\u0010dR\u001b\u0010e\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bg\u0010 \u001a\u0004\bf\u0010\u001eR\u001b\u0010h\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bi\u0010 \u001a\u0004\bh\u00103R\u001b\u0010j\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bl\u0010 \u001a\u0004\bk\u00103R\u001b\u0010m\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bo\u0010 \u001a\u0004\bn\u0010\u001eR\u000e\u0010p\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010u\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bu\u0010:R\u0014\u0010v\u001a\u00020w8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bx\u0010yR\u0017\u0010\u008c\u0001\u001a\n\u0012\u0005\u0012\u00030\u008e\u00010\u008d\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0095\u0001\u001a\u00020\u000b*\u00030\u0094\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u001e\u0010\u0098\u0001\u001a\u0004\u0018\u00010t*\u00030\u0094\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001¨\u0006å\u0002"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "Lorg/jetbrains/kotlin/backend/common/FileLoweringPass;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "collectSourceInformation", "", "traceMarkersEnabled", "targetRuntimeVersion", "Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;ZZLandroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "inlineLambdaInfo", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposeInlineLambdaLocator;", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "irFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "skipToGroupEndFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getSkipToGroupEndFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "skipToGroupEndFunction$delegate", "Landroidx/compose/compiler/plugins/kotlin/lower/GuardedLazy;", "startDefaultsFunction", "getStartDefaultsFunction", "startDefaultsFunction$delegate", "endDefaultsFunction", "getEndDefaultsFunction", "endDefaultsFunction$delegate", "startMovableFunction", "getStartMovableFunction", "startMovableFunction$delegate", "endMovableFunction", "getEndMovableFunction", "endMovableFunction$delegate", "startRestartGroupFunction", "getStartRestartGroupFunction", "startRestartGroupFunction$delegate", "currentMarkerProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "getCurrentMarkerProperty", "()Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "currentMarkerProperty$delegate", "endToMarkerFunction", "getEndToMarkerFunction", "endToMarkerFunction$delegate", "rollbackGroupMarkerEnabled", "getRollbackGroupMarkerEnabled", "()Z", "endRestartGroupFunction", "getEndRestartGroupFunction", "endRestartGroupFunction$delegate", "shouldExecuteFunction", "getShouldExecuteFunction", "shouldExecuteFunction$delegate", "sourceInformationFunction", "getSourceInformationFunction", "sourceInformationFunction$delegate", "sourceInformationMarkerStartFunction", "getSourceInformationMarkerStartFunction", "sourceInformationMarkerStartFunction$delegate", "updateChangedFlagsFunction", "getUpdateChangedFlagsFunction", "updateChangedFlagsFunction$delegate", "isTraceInProgressFunction", "isTraceInProgressFunction$delegate", "traceEventStartFunction", "getTraceEventStartFunction", "traceEventStartFunction$delegate", "traceEventEndFunction", "getTraceEventEndFunction", "traceEventEndFunction$delegate", "traceEventMarkersEnabled", "getTraceEventMarkersEnabled", "sourceInformationMarkerEndFunction", "getSourceInformationMarkerEndFunction", "sourceInformationMarkerEndFunction$delegate", "rememberComposableLambdaFunction", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getRememberComposableLambdaFunction", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "rememberComposableLambdaFunction$delegate", "useNonSkippingGroupOptimization", "getUseNonSkippingGroupOptimization", "useNonSkippingGroupOptimization$delegate", "arguments", "", "Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "Lorg/jetbrains/kotlin/ir/types/IrType;", "getArguments", "(Lorg/jetbrains/kotlin/ir/types/IrType;)Ljava/util/List;", "updateScopeFunction", "getUpdateScopeFunction", "updateScopeFunction$delegate", "isSkippingFunction", "isSkippingFunction$delegate", "defaultsInvalidFunction", "getDefaultsInvalidFunction", "defaultsInvalidFunction$delegate", "joinKeyFunction", "getJoinKeyFunction", "joinKeyFunction$delegate", "emitParameterNames", "currentScope", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "printScopeStack", "", "isInComposableScope", "currentFunctionScope", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FunctionScope;", "getCurrentFunctionScope", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FunctionScope;", "visitClass", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "visitFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "visitFunctionInScope", "visitNonRestartableComposableFunction", "scope", "changedParam", "Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;", "defaultParam", "Landroidx/compose/compiler/plugins/kotlin/lower/IrDefaultBitMaskValue;", "visitComposableLambda", "visitRestartableComposableFunction", "visitComposableFunctionStub", "visitInlinedLambdaInComposableScope", "visitComposableReferenceAdapter", "sourceFixups", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$SourceInfoFixup;", "recordSourceParameter", "call", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "index", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "hasSourceInformation", "getHasSourceInformation", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;)Z", "sourceInformation", "getSourceInformation", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;)Ljava/lang/String;", "applySourceFixups", "transformDefaults", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$ParametersScope;", "buildPreambleStatementsAndReturnIfSkippingPossible", "sourceElement", "Lorg/jetbrains/kotlin/ir/IrElement;", "skipPreamble", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementContainer;", "bodyPreamble", "isSkippableDeclaration", "dirty", "defaultScope", "irCallChanged", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "slotIndex", "param", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "irEndRestartGroupAndUpdateScope", "numRealValueParameters", "irCurrentMarker", "composerParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "irIsSkipping", "irShouldExecute", "parametersChanged", "flags", "irDefaultsInvalid", "irIsProvided", "default", "slot", "irIsUncertainAndStable", "changed", "irIsStable", "irIsUncertain", "irBitsForSlot", "bits", "endsWithReturnOrJump", "wrapWithTraceEvents", "Lorg/jetbrains/kotlin/ir/expressions/IrContainerExpression;", "key", "asBodyAndResultVar", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "Lorg/jetbrains/kotlin/ir/expressions/IrBody;", "expectedTarget", "visitProperty", "visitField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "visitFile", "visitDeclaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationBase;", "nearestComposer", "irCurrentComposer", "startOffset", "endOffset", "sourceKey", "functionSourceKey", "function", "irSourceKey", "Lorg/jetbrains/kotlin/ir/expressions/IrConst;", "irFunctionSourceKey", "irStartReplaceGroup", "element", "irWithSourceInformation", "startGroup", "irSourceInformation", "irSourceInformationMarkerStart", "irSourceInformationMarkerEnd", "irWithSourceInformationMarker", "expression", "before", "irIsTraceInProgress", "irIfTraceInProgress", "body", "irTraceEventStart", "irTraceEventEnd", "irStartDefaults", "irStartRestartGroup", "irEndRestartGroup", "irChanged", "value", "fileContainingValue", "compareInstanceForFunctionTypes", "compareInstanceForUnstableValues", "irSkipToGroupEnd", "irEndReplaceGroup", "irEndDefaults", "irStartMovableGroup", "joinedData", "irEndMovableGroup", "irEndToMarker", "marker", "irJoinKeyChain", "keyExprs", "irSafeCall", "target", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "args", "", "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;[Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "irTemporary", "Lorg/jetbrains/kotlin/ir/declarations/impl/IrVariableImpl;", "nameHint", "irType", "isVar", "exactName", "withReplaceGroupStatements", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "insertAt", "asReplaceGroup", "variablePrefix", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrBlockImpl;", "variable", "wrap", "after", "asCoalescableGroup", "asSourceOrEarlyExitGroup", "mutableStatementContainer", "encounteredComposableCall", "withGroups", "recordCallInSource", "encounteredCapturedComposableCall", "encounteredCoalescableGroup", "coalescableScope", "realizeGroup", "Lkotlin/Function0;", "makeEnd", "encounteredReturn", "Lorg/jetbrains/kotlin/ir/symbols/IrReturnTargetSymbol;", "extraEndLocation", "Lkotlin/Function1;", "encounteredJump", "jump", "Lorg/jetbrains/kotlin/ir/expressions/IrBreakContinue;", "transformWithScope", "T", "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;)Lkotlin/Pair;", "withScope", "block", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;Lkotlin/jvm/functions/Function0;)Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "inScope", "R", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forEach", "Lkotlin/ParameterName;", "name", "argumentMetaOf", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "arg", "fileContainingArg", "isProvided", "populateArgumentMeta", "meta", "extractParamMetaFromScopes", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$ParamMeta;", "visitBlock", "visitFunctionAccess", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "visitComposableCall", "visitNormalComposableCall", "visitRememberCall", "intrinsicRememberScope", "androidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$intrinsicRememberScope$1", "rememberCall", "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;)Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$intrinsicRememberScope$1;", "irIntrinsicRememberInvalid", "isMemoizedLambda", "metas", "changedExpr", "Lkotlin/Function3;", "irIntrinsicChanged", "argInfo", "visitKeyCall", "buildChangedArgumentsForCall", "buildChangedArgumentForCall", "irTypeParameterStability", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "visitGetValue", "Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "visitReturn", "Lorg/jetbrains/kotlin/ir/expressions/IrReturn;", "visitBreakContinue", "visitDoWhileLoop", "loop", "Lorg/jetbrains/kotlin/ir/expressions/IrDoWhileLoop;", "visitWhileLoop", "Lorg/jetbrains/kotlin/ir/expressions/IrWhileLoop;", "handleLoop", "Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "visitWhen", "Lorg/jetbrains/kotlin/ir/expressions/IrWhen;", "isGroupBalanced", "SourceInfoFixup", "CallArgumentMeta", "ParamMeta", "Scope", "IrDefaultBitMaskValueImpl", "IrChangedBitMaskValueImpl", "IrChangedBitMaskVariableImpl", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableFunctionBodyTransformer extends AbstractComposeLowering implements FileLoweringPass, ModuleLoweringPass {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "skipToGroupEndFunction", "getSkipToGroupEndFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "startDefaultsFunction", "getStartDefaultsFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "endDefaultsFunction", "getEndDefaultsFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "startMovableFunction", "getStartMovableFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "endMovableFunction", "getEndMovableFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "startRestartGroupFunction", "getStartRestartGroupFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "currentMarkerProperty", "getCurrentMarkerProperty()Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "endToMarkerFunction", "getEndToMarkerFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "endRestartGroupFunction", "getEndRestartGroupFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "shouldExecuteFunction", "getShouldExecuteFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "sourceInformationFunction", "getSourceInformationFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "sourceInformationMarkerStartFunction", "getSourceInformationMarkerStartFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "updateChangedFlagsFunction", "getUpdateChangedFlagsFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "isTraceInProgressFunction", "isTraceInProgressFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "traceEventStartFunction", "getTraceEventStartFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "traceEventEndFunction", "getTraceEventEndFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "sourceInformationMarkerEndFunction", "getSourceInformationMarkerEndFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "rememberComposableLambdaFunction", "getRememberComposableLambdaFunction()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "useNonSkippingGroupOptimization", "getUseNonSkippingGroupOptimization()Z", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "updateScopeFunction", "getUpdateScopeFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "isSkippingFunction", "isSkippingFunction()Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "defaultsInvalidFunction", "getDefaultsInvalidFunction()Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", 0), new PropertyReference1Impl<>(ComposableFunctionBodyTransformer.class, "joinKeyFunction", "getJoinKeyFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0)};
    private final boolean collectSourceInformation;

    /* JADX INFO: renamed from: currentMarkerProperty$delegate, reason: from kotlin metadata */
    private final GuardedLazy currentMarkerProperty;
    private Scope currentScope;

    /* JADX INFO: renamed from: defaultsInvalidFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy defaultsInvalidFunction;
    private final boolean emitParameterNames;

    /* JADX INFO: renamed from: endDefaultsFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy endDefaultsFunction;

    /* JADX INFO: renamed from: endMovableFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy endMovableFunction;

    /* JADX INFO: renamed from: endRestartGroupFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy endRestartGroupFunction;

    /* JADX INFO: renamed from: endToMarkerFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy endToMarkerFunction;
    private ComposeInlineLambdaLocator inlineLambdaInfo;

    /* JADX INFO: renamed from: isSkippingFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy isSkippingFunction;

    /* JADX INFO: renamed from: isTraceInProgressFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy isTraceInProgressFunction;

    /* JADX INFO: renamed from: joinKeyFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy joinKeyFunction;

    /* JADX INFO: renamed from: rememberComposableLambdaFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy rememberComposableLambdaFunction;

    /* JADX INFO: renamed from: shouldExecuteFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy shouldExecuteFunction;

    /* JADX INFO: renamed from: skipToGroupEndFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy skipToGroupEndFunction;
    private final List<SourceInfoFixup> sourceFixups;

    /* JADX INFO: renamed from: sourceInformationFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy sourceInformationFunction;

    /* JADX INFO: renamed from: sourceInformationMarkerEndFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy sourceInformationMarkerEndFunction;

    /* JADX INFO: renamed from: sourceInformationMarkerStartFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy sourceInformationMarkerStartFunction;

    /* JADX INFO: renamed from: startDefaultsFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy startDefaultsFunction;

    /* JADX INFO: renamed from: startMovableFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy startMovableFunction;

    /* JADX INFO: renamed from: startRestartGroupFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy startRestartGroupFunction;

    /* JADX INFO: renamed from: traceEventEndFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy traceEventEndFunction;

    /* JADX INFO: renamed from: traceEventStartFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy traceEventStartFunction;
    private final boolean traceMarkersEnabled;

    /* JADX INFO: renamed from: updateChangedFlagsFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy updateChangedFlagsFunction;

    /* JADX INFO: renamed from: updateScopeFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy updateScopeFunction;

    /* JADX INFO: renamed from: useNonSkippingGroupOptimization$delegate, reason: from kotlin metadata */
    private final GuardedLazy useNonSkippingGroupOptimization;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0096\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0018\u0010\u0019\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006H\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\"\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\fH\u0016J \u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\fH\u0016J\u0010\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u0015H\u0002J\u0018\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u00060"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$IrChangedBitMaskValueImpl;", "Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;", "params", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "count", "", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;Ljava/util/List;I)V", "paramIndexForSlot", "slot", "used", "", "getUsed", "()Z", "setUsed", "(Z)V", "declarations", "getDeclarations", "()Ljava/util/List;", "irLowBit", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "irIsolateBitsAtSlot", "includeStableBit", "irStableBitAtSlot", "irSlotAnd", "bits", "irRestartFlags", "irHasDifferences", "usedParams", "", "irCopyToTemporary", "Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskVariable;", "nameHint", "", "isVar", "exactName", "putAsValueArgumentInWithLowBit", "", "fn", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "startIndex", "lowBit", "irUpdateChangedFlags", "expression", "irShiftBits", "fromSlot", "toSlot", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public class IrChangedBitMaskValueImpl implements IrChangedBitMaskValue {
        private final int count;
        private final List<IrValueDeclaration> params;
        final /* synthetic */ ComposableFunctionBodyTransformer this$0;
        private boolean used;

        public IrChangedBitMaskValueImpl(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, List<? extends IrValueDeclaration> list, int i) {
            list.getClass();
            this.this$0 = composableFunctionBodyTransformer;
            this.params = list;
            this.count = i;
            int size = list.size();
            int iChangedParamCount = ComposableFunctionBodyTransformerKt.changedParamCount(i, 0);
            if (size == iChangedParamCount) {
                return;
            }
            throw new IllegalArgumentException(("Function with " + i + " params had " + size + " changed params but expected " + iChangedParamCount).toString());
        }

        private final IrExpression irUpdateChangedFlags(IrExpression expression) {
            IrSimpleFunction updateChangedFlagsFunction = this.this$0.getUpdateChangedFlagsFunction();
            if (updateChangedFlagsFunction == null) {
                return expression;
            }
            IrCall irCallIrCall$default = AbstractComposeLowering.irCall$default(this.this$0, updateChangedFlagsFunction, 0, 0, 6, null);
            irCallIrCall$default.getArguments().set(0, expression);
            return irCallIrCall$default;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public List<IrValueDeclaration> getDeclarations() {
            return this.params;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public boolean getUsed() {
            return this.used;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public IrChangedBitMaskVariable irCopyToTemporary(String nameHint, boolean isVar, boolean exactName) {
            setUsed(true);
            List<IrValueDeclaration> list = this.params;
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                IrValueDeclaration irValueDeclaration = (IrValueDeclaration) obj;
                IrDeclarationOrigin defined = IrDeclarationOrigin.Companion.getDEFINED();
                IrVariableSymbolImpl irVariableSymbolImpl = new IrVariableSymbolImpl((VariableDescriptor) null, 1, (DefaultConstructorMarker) null);
                String str = "$dirty";
                if (i != 0) {
                    str = "$dirty" + i;
                }
                Name nameIdentifier = Name.identifier(str);
                nameIdentifier.getClass();
                IrVariableImpl IrVariableImpl = BuildersKt.IrVariableImpl(-1, -1, defined, irVariableSymbolImpl, nameIdentifier, irValueDeclaration.getType(), isVar, false, false);
                IrVariableImpl.setParent(composableFunctionBodyTransformer.getCurrentFunctionScope().getFunction().getParent());
                IrVariableImpl.setInitializer(composableFunctionBodyTransformer.irGet(irValueDeclaration));
                arrayList.add(IrVariableImpl);
                i = i2;
            }
            return new IrChangedBitMaskVariableImpl(this.this$0, arrayList, this.count);
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public IrExpression irHasDifferences(boolean[] usedParams) {
            usedParams.getClass();
            setUsed(true);
            int length = usedParams.length;
            int i = this.count;
            if (length != i) {
                w01.a("Failed requirement.");
                return null;
            }
            if (i == 0) {
                ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
                return composableFunctionBodyTransformer.irNotEqual(composableFunctionBodyTransformer.irGet(this.params.get(0)), this.this$0.irConst(0));
            }
            List<IrValueDeclaration> list = this.params;
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer2 = this.this$0;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i2 = 0;
            for (Object obj : list) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                IrValueDeclaration irValueDeclaration = (IrValueDeclaration) obj;
                int i4 = i2 * 10;
                int iMin = Math.min(i4 + 10, this.count);
                int i5 = composableFunctionBodyTransformer2.getEnabled(FeatureFlag.StrongSkipping) ? 1 : 5;
                IntIterator it = RangesKt.until(i4, iMin).iterator();
                int iBitsForSlot = 0;
                while (it.hasNext()) {
                    int iNextInt = it.nextInt();
                    if (usedParams[iNextInt]) {
                        iBitsForSlot |= ComposableFunctionBodyTransformerKt.bitsForSlot(i5, iNextInt);
                    }
                }
                IntIterator it2 = RangesKt.until(i4, iMin).iterator();
                int iBitsForSlot2 = 0;
                while (it2.hasNext()) {
                    int iNextInt2 = it2.nextInt();
                    if (usedParams[iNextInt2]) {
                        iBitsForSlot2 |= ComposableFunctionBodyTransformerKt.bitsForSlot(1, iNextInt2);
                    }
                }
                arrayList.add(iBitsForSlot2 == 0 ? composableFunctionBodyTransformer2.irNotEqual(composableFunctionBodyTransformer2.irAnd(composableFunctionBodyTransformer2.irGet(irValueDeclaration), composableFunctionBodyTransformer2.irConst(1)), composableFunctionBodyTransformer2.irConst(0)) : composableFunctionBodyTransformer2.irNotEqual(composableFunctionBodyTransformer2.irAnd(composableFunctionBodyTransformer2.irGet(irValueDeclaration), composableFunctionBodyTransformer2.irConst(iBitsForSlot | 1)), composableFunctionBodyTransformer2.irConst(iBitsForSlot2)));
                i2 = i3;
            }
            if (arrayList.size() == 1) {
                return (IrExpression) CollectionsKt.single(arrayList);
            }
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer3 = this.this$0;
            Iterator it3 = arrayList.iterator();
            if (!it3.hasNext()) {
                c41.a("Empty collection can't be reduced.");
                return null;
            }
            Object next = it3.next();
            while (it3.hasNext()) {
                next = composableFunctionBodyTransformer3.irOrOr((IrExpression) next, (IrExpression) it3.next());
            }
            return (IrExpression) next;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public IrExpression irIsolateBitsAtSlot(int slot, boolean includeStableBit) {
            setUsed(true);
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            return composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irGet(this.params.get(paramIndexForSlot(slot))), this.this$0.irBitsForSlot(includeStableBit ? ParamState.Mask.getBits() : ParamState.Static.getBits(), slot));
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public IrExpression irLowBit() {
            setUsed(true);
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            return composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irGet(this.params.get(0)), this.this$0.irConst(1));
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public IrExpression irRestartFlags() {
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            return composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irGet(this.params.get(0)), this.this$0.irConst(1));
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public IrExpression irShiftBits(int fromSlot, int toSlot) {
            setUsed(true);
            int i = ((toSlot % 10) - (fromSlot % 10)) * 3;
            IrExpression irExpressionIrGet = this.this$0.irGet(this.params.get(paramIndexForSlot(fromSlot)));
            if (i == 0) {
                return irExpressionIrGet;
            }
            IrType intType = this.this$0.getContext().getIrBuiltIns().getIntType();
            IrFunctionSymbol irFunctionSymbolBinaryOperator = this.this$0.binaryOperator(intType, OperatorNameConventions.SHL, intType);
            IrFunctionSymbol irFunctionSymbolBinaryOperator2 = this.this$0.binaryOperator(intType, OperatorNameConventions.SHR, intType);
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            return AbstractComposeLowering.irCall$default(composableFunctionBodyTransformer, i > 0 ? irFunctionSymbolBinaryOperator : irFunctionSymbolBinaryOperator2, null, null, irExpressionIrGet, null, new IrExpression[]{composableFunctionBodyTransformer.irConst(Math.abs(i))}, 22, null);
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public IrExpression irSlotAnd(int slot, int bits) {
            setUsed(true);
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            return composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irGet(this.params.get(paramIndexForSlot(slot))), this.this$0.irBitsForSlot(bits, slot));
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public IrExpression irStableBitAtSlot(int slot) {
            setUsed(true);
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            return composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irGet(this.params.get(paramIndexForSlot(slot))), this.this$0.irBitsForSlot(4, slot));
        }

        public final int paramIndexForSlot(int slot) {
            return slot / 10;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskValue
        public void putAsValueArgumentInWithLowBit(IrFunctionAccessExpression fn, int startIndex, boolean lowBit) {
            fn.getClass();
            setUsed(true);
            List<IrValueDeclaration> list = this.params;
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            int size = list.size();
            int i = 0;
            while (i < size) {
                IrValueDeclaration irValueDeclaration = list.get(i);
                fn.getArguments().set(startIndex + i, i == 0 ? irUpdateChangedFlags(composableFunctionBodyTransformer.irOr(composableFunctionBodyTransformer.irGet(irValueDeclaration), composableFunctionBodyTransformer.irConst(lowBit ? 1 : 0))) : irUpdateChangedFlags(composableFunctionBodyTransformer.irGet(irValueDeclaration)));
                i++;
            }
        }

        public void setUsed(boolean z) {
            this.used = z;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u00012\u00060\u0002R\u00020\u0003B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005H\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$IrChangedBitMaskVariableImpl;", "Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskVariable;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$IrChangedBitMaskValueImpl;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;", "temps", "", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "count", "", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;Ljava/util/List;I)V", "asStatements", "Lorg/jetbrains/kotlin/ir/IrStatement;", "irOrSetBitsAtSlot", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "slot", "value", "irSetSlotUncertain", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public final class IrChangedBitMaskVariableImpl extends IrChangedBitMaskValueImpl implements IrChangedBitMaskVariable {
        private final List<IrVariable> temps;
        final /* synthetic */ ComposableFunctionBodyTransformer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IrChangedBitMaskVariableImpl(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, List<? extends IrVariable> list, int i) {
            super(composableFunctionBodyTransformer, list, i);
            list.getClass();
            this.this$0 = composableFunctionBodyTransformer;
            this.temps = list;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskVariable
        public List<IrStatement> asStatements() {
            return this.temps;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskVariable
        public IrExpression irOrSetBitsAtSlot(int slot, IrExpression value) {
            value.getClass();
            setUsed(true);
            IrValueDeclaration irValueDeclaration = (IrVariable) this.temps.get(paramIndexForSlot(slot));
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            return composableFunctionBodyTransformer.irSet(irValueDeclaration, composableFunctionBodyTransformer.irOr(composableFunctionBodyTransformer.irGet(irValueDeclaration), value));
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrChangedBitMaskVariable
        public IrExpression irSetSlotUncertain(int slot) {
            setUsed(true);
            IrValueDeclaration irValueDeclaration = (IrVariable) this.temps.get(paramIndexForSlot(slot));
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            return composableFunctionBodyTransformer.irSet(irValueDeclaration, composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irGet(irValueDeclaration), this.this$0.irConst(~ParamState.Mask.bitsForSlot(slot))));
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$IrDefaultBitMaskValueImpl;", "Landroidx/compose/compiler/plugins/kotlin/lower/IrDefaultBitMaskValue;", "params", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "count", "", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;Ljava/util/List;I)V", "irIsolateBitAtIndex", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "index", "irHasAnyProvidedAndUnstable", "unstable", "", "putAsValueArgumentIn", "", "fn", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "startIndex", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public final class IrDefaultBitMaskValueImpl implements IrDefaultBitMaskValue {
        private final int count;
        private final List<IrValueParameter> params;
        final /* synthetic */ ComposableFunctionBodyTransformer this$0;

        public IrDefaultBitMaskValueImpl(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, List<? extends IrValueParameter> list, int i) {
            list.getClass();
            this.this$0 = composableFunctionBodyTransformer;
            this.params = list;
            this.count = i;
            int size = list.size();
            int iDefaultParamCount = ComposableFunctionBodyTransformerKt.defaultParamCount(i);
            if (size == iDefaultParamCount) {
                return;
            }
            throw new IllegalArgumentException(("Function with " + i + " params had " + size + " default params but expected " + iDefaultParamCount).toString());
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrDefaultBitMaskValue
        public IrExpression irHasAnyProvidedAndUnstable(boolean[] unstable) {
            unstable.getClass();
            if (this.count != unstable.length) {
                w01.a("Failed requirement.");
                return null;
            }
            List<IrValueParameter> list = this.params;
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int i3 = i * 31;
                boolean[] zArrSliceArray = ArraysKt.sliceArray(unstable, RangesKt.until(i3, Math.min(i3 + 31, this.count)));
                int iBitMask = composableFunctionBodyTransformer.bitMask(Arrays.copyOf(zArrSliceArray, zArrSliceArray.length));
                arrayList.add(composableFunctionBodyTransformer.irNotEqual(composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irGet((IrValueParameter) obj), composableFunctionBodyTransformer.irConst(iBitMask)), composableFunctionBodyTransformer.irConst(iBitMask)));
                i = i2;
            }
            if (arrayList.size() == 1) {
                return (IrExpression) CollectionsKt.single(arrayList);
            }
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer2 = this.this$0;
            Iterator it = arrayList.iterator();
            if (!it.hasNext()) {
                c41.a("Empty collection can't be reduced.");
                return null;
            }
            Object next = it.next();
            while (it.hasNext()) {
                next = composableFunctionBodyTransformer2.irOrOr((IrExpression) next, (IrExpression) it.next());
            }
            return (IrExpression) next;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrDefaultBitMaskValue
        public IrExpression irIsolateBitAtIndex(int index) {
            if (index <= this.count) {
                ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
                return composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irGet((IrValueDeclaration) this.params.get(ComposableFunctionBodyTransformerKt.defaultsParamIndex(index))), this.this$0.irConst(1 << ComposableFunctionBodyTransformerKt.defaultsBitIndex(index)));
            }
            w01.a("Failed requirement.");
            return null;
        }

        @Override // androidx.compose.compiler.plugins.kotlin.lower.IrDefaultBitMaskValue
        public void putAsValueArgumentIn(IrFunctionAccessExpression fn, int startIndex) {
            fn.getClass();
            List<IrValueParameter> list = this.params;
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.this$0;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                fn.getArguments().set(startIndex + i, composableFunctionBodyTransformer.irGet(list.get(i)));
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$SourceInfoFixup;", "", "call", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "index", "", "scope", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "<init>", "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;ILandroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;)V", "getCall", "()Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "getIndex", "()I", "getScope", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SourceInfoFixup {
        private final IrCall call;
        private final int index;
        private final Scope.BlockScope scope;

        public SourceInfoFixup(IrCall irCall, int i, Scope.BlockScope blockScope) {
            irCall.getClass();
            blockScope.getClass();
            this.call = irCall;
            this.index = i;
            this.scope = blockScope;
        }

        public final IrCall getCall() {
            return this.call;
        }

        public final int getIndex() {
            return this.index;
        }

        public final Scope.BlockScope getScope() {
            return this.scope;
        }
    }

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[IrTypeOperator.values().length];
            try {
                iArr[IrTypeOperator.CAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IrTypeOperator.IMPLICIT_CAST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[IrParameterKind.values().length];
            try {
                iArr2[IrParameterKind.DispatchReceiver.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[IrParameterKind.Context.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[IrParameterKind.ExtensionReceiver.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[IrParameterKind.Regular.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableFunctionBodyTransformer(final IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, boolean z, boolean z2, ComposeRuntimeVersion composeRuntimeVersion, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        this.collectSourceInformation = z;
        this.traceMarkersEnabled = z2;
        this.inlineLambdaInfo = new ComposeInlineLambdaLocator(irPluginContext);
        this.skipToGroupEndFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: gd2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.u(this.b);
            }
        });
        this.startDefaultsFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: zb2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.o(this.b);
            }
        });
        this.endDefaultsFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: ec2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.N(this.b);
            }
        });
        this.startMovableFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: fc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.K(this.b);
            }
        });
        this.endMovableFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: gc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.l(this.b);
            }
        });
        this.startRestartGroupFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: hc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.r(this.b);
            }
        });
        this.currentMarkerProperty = GuardedLazyKt.guardedLazy(new Function0() { // from class: ic2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.L(this.b);
            }
        });
        this.endToMarkerFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: kc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.S(this.b);
            }
        });
        this.endRestartGroupFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: lc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.V(this.b);
            }
        });
        this.shouldExecuteFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: mc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.A(this.b);
            }
        });
        this.sourceInformationFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: ob2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.X(this.b);
            }
        });
        this.sourceInformationMarkerStartFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: pb2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.n(this.b);
            }
        });
        this.updateChangedFlagsFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: qb2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.P(this.b);
            }
        });
        this.isTraceInProgressFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: rb2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.p(this.b);
            }
        });
        this.traceEventStartFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: sb2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.a0(this.b, irPluginContext);
            }
        });
        this.traceEventEndFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: tb2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.m(this.b);
            }
        });
        this.sourceInformationMarkerEndFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: ub2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.Q(this.b);
            }
        });
        this.rememberComposableLambdaFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: vb2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.T(this.b);
            }
        });
        this.useNonSkippingGroupOptimization = GuardedLazyKt.guardedLazy(new Function0() { // from class: wb2
            public final Object invoke() {
                return Boolean.valueOf(ComposableFunctionBodyTransformer.U(this.b));
            }
        });
        this.updateScopeFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: xb2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.w(this.b);
            }
        });
        this.isSkippingFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: ac2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.z(this.b);
            }
        });
        this.defaultsInvalidFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: bc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.G(this.b);
            }
        });
        this.joinKeyFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: cc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.Z(this.b);
            }
        });
        this.emitParameterNames = irPluginContext.getLanguageVersionSettings().getLanguageVersion().getUsesK2() && JvmPlatformKt.isJvm(irPluginContext.getPlatform()) && RuntimeFeaturesKt.supportsFeature(composeRuntimeVersion, ComposeRuntimeFeature.SourceInfoParameterNames, new Function0() { // from class: dc2
            public final Object invoke() {
                return Boolean.valueOf(ComposableFunctionBodyTransformer.x(irPluginContext));
            }
        });
        this.currentScope = new Scope.RootScope();
        this.sourceFixups = new ArrayList();
    }

    public static IrSimpleFunction A(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        Object obj = null;
        if (!composableFunctionBodyTransformer.getEnabled(FeatureFlag.PausableComposition)) {
            return null;
        }
        for (Object obj2 : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            IrSimpleFunction irSimpleFunction = (IrSimpleFunction) obj2;
            if (Intrinsics.areEqual(irSimpleFunction.getName(), ComposeNames.INSTANCE.getShouldExecute()) && irSimpleFunction.getParameters().size() == 3 && IrTypePredicatesKt.isBoolean(((IrValueParameter) irSimpleFunction.getParameters().get(1)).getType()) && IrTypePredicatesKt.isInt(((IrValueParameter) irSimpleFunction.getParameters().get(2)).getType())) {
                obj = obj2;
                break;
            }
        }
        return (IrSimpleFunction) obj;
    }

    public static IrExpression B(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, boolean z, boolean z2, IrExpression irExpression, CallArgumentMeta callArgumentMeta) {
        irExpression.getClass();
        callArgumentMeta.getClass();
        return composableFunctionBodyTransformer.irChanged(irExpression, callArgumentMeta.getFileContainingArg(), false, z);
    }

    public static IrExpression C(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, boolean z, IrBody irBody, Scope.FunctionScope functionScope) {
        return AbstractComposeLowering.irComposite$default(composableFunctionBodyTransformer, null, null, CollectionsKt.listOfNotNull(new IrExpression[]{z ? composableFunctionBodyTransformer.irTraceEventEnd() : null, composableFunctionBodyTransformer.irEndReplaceGroup(irBody.getEndOffset(), irBody.getEndOffset(), functionScope)}), 3, null);
    }

    public static IrExpression E(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Scope.KeyScope keyScope) {
        return composableFunctionBodyTransformer.irEndMovableGroup(keyScope);
    }

    public static IrExpression F(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        IrExpression irExpressionIrTraceEventEnd = composableFunctionBodyTransformer.irTraceEventEnd();
        irExpressionIrTraceEventEnd.getClass();
        return irExpressionIrTraceEventEnd;
    }

    public static IrProperty G(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrProperty irProperty : IrUtilsKt.getProperties(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irProperty.getName().asString(), "defaultsInvalid")) {
                return irProperty;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static Unit H(IrContainerExpression irContainerExpression, FunctionMetrics functionMetrics, ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrExpression irExpression, Scope.BlockScope blockScope, IrContainerExpression irContainerExpression2) {
        if (irContainerExpression.getStatements().isEmpty()) {
            functionMetrics.recordGroup();
            irContainerExpression.getStatements().add(irStartReplaceGroup$default(composableFunctionBodyTransformer, irExpression, blockScope, null, irExpression.getStartOffset(), irExpression.getStartOffset(), 4, null));
            irContainerExpression2.getStatements().add(composableFunctionBodyTransformer.irEndReplaceGroup(irExpression.getEndOffset(), irExpression.getEndOffset(), blockScope));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static IrExpression I(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Function0 function0) {
        IrExpression[] irExpressionArr = new IrExpression[2];
        irExpressionArr[0] = composableFunctionBodyTransformer.getTraceEventMarkersEnabled() ? composableFunctionBodyTransformer.irTraceEventEnd() : null;
        irExpressionArr[1] = function0.invoke();
        return AbstractComposeLowering.irComposite$default(composableFunctionBodyTransformer, null, null, CollectionsKt.listOfNotNull(irExpressionArr), 3, null);
    }

    public static Unit J(IrFunction irFunction, ComposableFunctionBodyTransformer composableFunctionBodyTransformer, int i, IrChangedBitMaskValue irChangedBitMaskValue, int i2, IrDefaultBitMaskValue irDefaultBitMaskValue, int i3, IrVariableImpl irVariableImpl, IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        irSimpleFunction.setParent(irFunction);
        ComposeNames composeNames = ComposeNames.INSTANCE;
        String identifier = composeNames.getComposerParameter().getIdentifier();
        identifier.getClass();
        IrValueParameter irValueParameterAddValueParameter$default = DeclarationBuildersKt.addValueParameter$default(irSimpleFunction, identifier, IrTypesKt.makeNullable(composableFunctionBodyTransformer.replaceArgumentsWithStarProjections(IrUtilsKt.getDefaultType(composableFunctionBodyTransformer.getComposerIrClass()))), (IrDeclarationOrigin) null, 4, (Object) null);
        DeclarationBuildersKt.addValueParameter$default(irSimpleFunction, composeNames.getForceParameter(), composableFunctionBodyTransformer.getBuiltIns().getIntType(), (IrDeclarationOrigin) null, 4, (Object) null);
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(composableFunctionBodyTransformer.getContext(), irSimpleFunction.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        IrFunctionAccessExpression irFunctionAccessExpressionIrCall = ExpressionHelpersKt.irCall(irBlockBodyBuilder, irFunction.getSymbol());
        List parameters = irFunctionAccessExpressionIrCall.getSymbol().getOwner().getParameters();
        int size = parameters.size();
        for (int i4 = 0; i4 < size; i4++) {
            IrValueParameter irValueParameter = (IrValueParameter) parameters.get(i4);
            irFunctionAccessExpressionIrCall.getArguments().set(irValueParameter.getIndexInParameters(), irValueParameter.getKind() == IrParameterKind.DispatchReceiver ? irVariableImpl != null ? ExpressionHelpersKt.irGet(irBlockBodyBuilder, irVariableImpl) : null : ExpressionHelpersKt.irGet(irBlockBodyBuilder, irValueParameter));
        }
        irFunctionAccessExpressionIrCall.getArguments().set(i, ExpressionHelpersKt.irGet(irBlockBodyBuilder, irValueParameterAddValueParameter$default));
        irChangedBitMaskValue.putAsValueArgumentInWithLowBit(irFunctionAccessExpressionIrCall, i2, true);
        if (irDefaultBitMaskValue != null) {
            irDefaultBitMaskValue.putAsValueArgumentIn(irFunctionAccessExpressionIrCall, i3);
        }
        List typeParameters = irFunction.getTypeParameters();
        int size2 = typeParameters.size();
        for (int i5 = 0; i5 < size2; i5++) {
            irFunctionAccessExpressionIrCall.getTypeArguments().set(i5, IrTypesKt.getDefaultType((IrTypeParameter) typeParameters.get(i5)));
        }
        Unit unit = Unit.INSTANCE;
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder, irFunctionAccessExpressionIrCall));
        irSimpleFunction.setBody(irBlockBodyBuilder.doBuild());
        return Unit.INSTANCE;
    }

    public static IrSimpleFunction K(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "startMovableGroup") && irSimpleFunction.getParameters().size() == 3) {
                return irSimpleFunction;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static IrProperty L(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        Object next;
        Iterator it = IrUtilsKt.getProperties(composableFunctionBodyTransformer.getComposerIrClass()).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(((IrProperty) next).getName(), ComposeNames.INSTANCE.getCurrentMarker())) {
                return (IrProperty) next;
            }
        }
        next = null;
        return (IrProperty) next;
    }

    public static IrExpression M(IrContainerExpression irContainerExpression, ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Scope.FunctionScope functionScope) {
        return composableFunctionBodyTransformer.irEndReplaceGroup(irContainerExpression.getEndOffset(), irContainerExpression.getEndOffset(), functionScope);
    }

    public static IrSimpleFunction N(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "endDefaults") && irSimpleFunction.getParameters().size() == 1) {
                return irSimpleFunction;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static Unit O(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrContainerExpression irContainerExpression, Scope.FunctionScope functionScope, IrVariable irVariable, IrFunction irFunction, IrBlockBody irBlockBody) {
        irBlockBody.getClass();
        irBlockBody.getStatements().add(composableFunctionBodyTransformer.irStartReplaceGroup(irContainerExpression, functionScope, irFunctionSourceKey$default(composableFunctionBodyTransformer, null, 1, null), irContainerExpression.getStartOffset(), irContainerExpression.getStartOffset()));
        irBlockBody.getStatements().addAll(irContainerExpression.getStatements());
        irBlockBody.getStatements().add(composableFunctionBodyTransformer.irEndReplaceGroup(irContainerExpression.getEndOffset(), irContainerExpression.getEndOffset(), functionScope));
        if (irVariable != null) {
            irBlockBody.getStatements().add(composableFunctionBodyTransformer.irReturnVar(irFunction.getSymbol(), irVariable));
        }
        return Unit.INSTANCE;
    }

    public static IrSimpleFunction P(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        IrSimpleFunctionSymbol topLevelFunctionOrNull = composableFunctionBodyTransformer.getTopLevelFunctionOrNull(ComposeCallableIds.INSTANCE.getUpdateChangedFlags());
        if (topLevelFunctionOrNull == null) {
            return null;
        }
        IrSimpleFunction owner = topLevelFunctionOrNull.getOwner();
        if (owner.getParameters().size() == 1) {
            return owner;
        }
        return null;
    }

    public static IrSimpleFunction Q(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        return composableFunctionBodyTransformer.getTopLevelFunction(ComposeCallableIds.INSTANCE.getSourceInformationMarkerEnd()).getOwner();
    }

    public static IrExpression R(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Scope.BlockScope blockScope) {
        return irEndReplaceGroup$default(composableFunctionBodyTransformer, 0, 0, blockScope, 3, (Object) null);
    }

    public static IrSimpleFunction S(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        Object next;
        Iterator it = IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass()).iterator();
        while (it.hasNext()) {
            next = it.next();
            IrSimpleFunction irSimpleFunction = (IrSimpleFunction) next;
            if (Intrinsics.areEqual(irSimpleFunction.getName(), ComposeNames.INSTANCE.getEndToMarker()) && irSimpleFunction.getParameters().size() == 2) {
                return (IrSimpleFunction) next;
            }
        }
        next = null;
        return (IrSimpleFunction) next;
    }

    public static IrSimpleFunctionSymbol T(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        return (IrSimpleFunctionSymbol) CollectionsKt.singleOrNull(composableFunctionBodyTransformer.getTopLevelFunctions(ComposeCallableIds.INSTANCE.getRememberComposableLambda()));
    }

    public static boolean U(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        return composableFunctionBodyTransformer.getEnabled(FeatureFlag.OptimizeNonSkippingGroups) && composableFunctionBodyTransformer.getRememberComposableLambdaFunction() != null;
    }

    public static IrSimpleFunction V(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irSimpleFunction.getName(), ComposeNames.INSTANCE.getEndRestartGroup()) && irSimpleFunction.getParameters().size() == 1) {
                return irSimpleFunction;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static Unit W(IrContainerExpression irContainerExpression, IrExpression irExpression) {
        irExpression.getClass();
        irContainerExpression.getStatements().add(irExpression);
        return Unit.INSTANCE;
    }

    public static IrSimpleFunction X(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        return composableFunctionBodyTransformer.getTopLevelFunction(ComposeCallableIds.INSTANCE.getSourceInformation()).getOwner();
    }

    public static IrExpression Y(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Scope.FunctionScope functionScope, IrChangedBitMaskValue irChangedBitMaskValue, IrDefaultBitMaskValue irDefaultBitMaskValue) {
        return composableFunctionBodyTransformer.irEndRestartGroupAndUpdateScope(functionScope, irChangedBitMaskValue, irDefaultBitMaskValue, functionScope.getRealValueParamCount());
    }

    public static IrSimpleFunction Z(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irSimpleFunction.getName(), ComposeNames.INSTANCE.getJoinKey()) && irSimpleFunction.getParameters().size() == 3) {
                return irSimpleFunction;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static IrSimpleFunction a0(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrPluginContext irPluginContext) {
        Iterator<T> it = composableFunctionBodyTransformer.getTopLevelFunctions(ComposeCallableIds.INSTANCE.getTraceEventStart()).iterator();
        boolean z = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                break;
            }
            Object next = it.next();
            List parameters = ((IrSimpleFunctionSymbol) next).getOwner().getParameters();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
            Iterator it2 = parameters.iterator();
            while (it2.hasNext()) {
                arrayList.add(((IrValueParameter) it2.next()).getType());
            }
            if (Intrinsics.areEqual(arrayList, CollectionsKt.listOf(new IrType[]{irPluginContext.getIrBuiltIns().getIntType(), irPluginContext.getIrBuiltIns().getIntType(), irPluginContext.getIrBuiltIns().getIntType(), irPluginContext.getIrBuiltIns().getStringType()}))) {
                if (!z) {
                    z = true;
                    obj = next;
                }
            }
            obj = null;
            break;
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) obj;
        if (irSimpleFunctionSymbol != null) {
            return irSimpleFunctionSymbol.getOwner();
        }
        return null;
    }

    private final void applySourceFixups() {
        List<SourceInfoFixup> list = this.sourceFixups;
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, new Comparator() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer$applySourceFixups$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(-((ComposableFunctionBodyTransformer.SourceInfoFixup) t).getScope().getLevel()), Integer.valueOf(-((ComposableFunctionBodyTransformer.SourceInfoFixup) t2).getScope().getLevel()));
                }
            });
        }
        for (SourceInfoFixup sourceInfoFixup : this.sourceFixups) {
            IrMemberAccessExpression.ValueArgumentsList arguments = sourceInfoFixup.getCall().getArguments();
            int index = sourceInfoFixup.getIndex();
            String sourceInformation = getSourceInformation(sourceInfoFixup.getScope());
            if (sourceInformation == null) {
                sourceInformation = "";
            }
            arguments.set(index, irConst(sourceInformation));
        }
        this.sourceFixups.clear();
    }

    private final CallArgumentMeta argumentMetaOf(IrExpression arg, IrFile fileContainingArg, boolean isProvided) {
        CallArgumentMeta callArgumentMeta = new CallArgumentMeta(fileContainingArg, null, false, isProvided, false, null, 54, null);
        populateArgumentMeta(arg, callArgumentMeta);
        return callArgumentMeta;
    }

    private final Pair<IrContainerExpression, IrVariable> asBodyAndResultVar(IrBody irBody, IrFunction irFunction) {
        IrType returnType;
        IrFunction owner;
        IrBlock irCompositeImpl = new IrCompositeImpl(irBody.getStartOffset(), irBody.getEndOffset(), getContext().getIrBuiltIns().getUnitType(), (IrStatementOrigin) null, IrUtilsKt.getStatements(irBody));
        List statements = irCompositeImpl.getStatements();
        IrStatement irStatement = statements != null ? (IrStatement) CollectionsKt.lastOrNull(statements) : null;
        IrBlock irBlock = irCompositeImpl;
        while (irStatement != null) {
            if ((irStatement instanceof IrReturn) && (irFunction == null || Intrinsics.areEqual(irFunction, ((IrReturn) irStatement).getReturnTargetSymbol().getOwner()))) {
                UtilsKt.pop(irBlock.getStatements());
                IrReturn irReturn = (IrReturn) irStatement;
                IrType type = irReturn.getValue().getType();
                IrFunctionSymbol returnTargetSymbol = irReturn.getReturnTargetSymbol();
                IrFunctionSymbol irFunctionSymbol = returnTargetSymbol instanceof IrFunctionSymbol ? returnTargetSymbol : null;
                if (irFunctionSymbol == null || (owner = irFunctionSymbol.getOwner()) == null || (returnType = owner.getReturnType()) == null) {
                    returnType = type;
                }
                if (IrTypePredicatesKt.isUnit(returnType) || IrTypePredicatesKt.isNothing(returnType) || IrTypePredicatesKt.isNothing(type)) {
                    irBlock.getStatements().add(irReturn.getValue());
                    return TuplesKt.to(irCompositeImpl, (Object) null);
                }
                IrVariableImpl irVariableImplIrTemporary$default = irTemporary$default(this, irReturn.getValue(), (String) null, (IrType) null, false, false, 30, (Object) null);
                irBlock.getStatements().add(irVariableImplIrTemporary$default);
                return TuplesKt.to(irCompositeImpl, irVariableImplIrTemporary$default);
            }
            ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this;
            if (!(irStatement instanceof IrBlock)) {
                return TuplesKt.to(irCompositeImpl, (Object) null);
            }
            irBlock = (IrStatementContainer) irStatement;
            irStatement = (IrStatement) CollectionsKt.lastOrNull(irBlock.getStatements());
            this = composableFunctionBodyTransformer;
        }
        return TuplesKt.to(irCompositeImpl, (Object) null);
    }

    public static /* synthetic */ Pair asBodyAndResultVar$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrBody irBody, IrFunction irFunction, int i, Object obj) {
        if ((i & 1) != 0) {
            irFunction = null;
        }
        return composableFunctionBodyTransformer.asBodyAndResultVar(irBody, irFunction);
    }

    private final IrExpression asCoalescableGroup(final IrExpression irExpression, final Scope.BlockScope blockScope) {
        final FunctionMetrics metrics = getCurrentFunctionScope().getMetrics();
        final IrContainerExpression irContainerExpressionMutableStatementContainer = mutableStatementContainer();
        final IrContainerExpression irContainerExpressionMutableStatementContainer2 = mutableStatementContainer();
        encounteredCoalescableGroup(blockScope, new Function0() { // from class: vc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.H(irContainerExpressionMutableStatementContainer, metrics, this, irExpression, blockScope, irContainerExpressionMutableStatementContainer2);
            }
        }, new Function0() { // from class: wc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.R(this.b, blockScope);
            }
        });
        return wrap(irExpression, CollectionsKt.listOf(irContainerExpressionMutableStatementContainer), CollectionsKt.listOf(irContainerExpressionMutableStatementContainer2));
    }

    private final IrExpression asReplaceGroup(IrExpression irExpression, final Scope.BlockScope blockScope) {
        getCurrentFunctionScope().getMetrics().recordGroup();
        if (!blockScope.getHasComposableCalls() && !blockScope.getHasReturn() && !blockScope.getHasJump()) {
            return wrap$default(this, irExpression, CollectionsKt.listOf(new IrExpression[]{irStartReplaceGroup$default(this, irExpression, blockScope, null, irExpression.getStartOffset(), irExpression.getStartOffset(), 4, null), irEndReplaceGroup(irExpression.getEndOffset(), irExpression.getEndOffset(), blockScope)}), null, 2, null);
        }
        blockScope.realizeGroup(new Function0() { // from class: oc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.y(this.b, blockScope);
            }
        });
        return endsWithReturnOrJump(irExpression) ? wrap$default(this, irExpression, CollectionsKt.listOf(irStartReplaceGroup$default(this, irExpression, blockScope, null, 0, 0, 28, null)), null, 2, null) : wrap(irExpression, CollectionsKt.listOf(irStartReplaceGroup$default(this, irExpression, blockScope, null, irExpression.getStartOffset(), irExpression.getEndOffset(), 4, null)), CollectionsKt.listOf(irEndReplaceGroup(irExpression.getStartOffset(), irExpression.getEndOffset(), blockScope)));
    }

    private final IrContainerExpression asSourceOrEarlyExitGroup(final IrContainerExpression irContainerExpression, final Scope.FunctionScope functionScope) {
        final boolean z = functionScope.getHasInlineEarlyReturn() || functionScope.isCrossinlineLambda();
        if (z) {
            getCurrentFunctionScope().getMetrics().recordGroup();
        } else if (!this.collectSourceInformation) {
            return irContainerExpression;
        }
        Function0 function0 = new Function0() { // from class: dd2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.s(z, this, irContainerExpression, functionScope);
            }
        };
        Function0<? extends IrExpression> function1 = new Function0() { // from class: ed2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.v(z, irContainerExpression, this, functionScope);
            }
        };
        if (!functionScope.getHasComposableCalls() && !functionScope.getHasReturn() && !functionScope.getHasJump()) {
            return wrap(irContainerExpression, CollectionsKt.listOf(function0.invoke()), CollectionsKt.listOf(function1.invoke()));
        }
        functionScope.realizeGroup(function1);
        return endsWithReturnOrJump(irContainerExpression) ? wrap$default(this, irContainerExpression, CollectionsKt.listOf(function0.invoke()), null, 2, null) : wrap(irContainerExpression, CollectionsKt.listOf(function0.invoke()), CollectionsKt.listOf(function1.invoke()));
    }

    public static Unit b0(IrContainerExpression irContainerExpression, IrExpression irExpression) {
        irExpression.getClass();
        irContainerExpression.getStatements().add(irExpression);
        return Unit.INSTANCE;
    }

    private final IrExpression buildChangedArgumentForCall(List<CallArgumentMeta> arguments) {
        ComposableFunctionBodyTransformer composableFunctionBodyTransformer;
        int iBitsForSlot;
        ArrayList arrayList = new ArrayList();
        int size = arguments.size();
        int i = 0;
        int iBitsForSlot2 = 0;
        while (i < size) {
            CallArgumentMeta callArgumentMeta = arguments.get(i);
            Stability stability = callArgumentMeta.getStability();
            if (this.getEnabled(FeatureFlag.StrongSkipping) || !StabilityKt.knownUnstable(stability)) {
                if (StabilityKt.knownStable(stability)) {
                    iBitsForSlot2 |= StabilityBits.STABLE.bitsForSlot(i);
                    composableFunctionBodyTransformer = this;
                } else {
                    composableFunctionBodyTransformer = this;
                    IrCallImpl irCallImplIrStableExpression$default = AbstractComposeLowering.irStableExpression$default(composableFunctionBodyTransformer, stability, new Function1() { // from class: yb2
                        public final Object invoke(Object obj) {
                            return ComposableFunctionBodyTransformer.buildChangedArgumentForCall$lambda$0$0(this.b, (IrTypeParameter) obj);
                        }
                    }, null, 2, null);
                    if (irCallImplIrStableExpression$default != null) {
                        if (i != 0) {
                            irCallImplIrStableExpression$default = composableFunctionBodyTransformer.irShl(irCallImplIrStableExpression$default, composableFunctionBodyTransformer.irConst(i * 3));
                        }
                        arrayList.add(irCallImplIrStableExpression$default);
                    }
                }
                if (callArgumentMeta.isVararg() || !callArgumentMeta.isProvided()) {
                    iBitsForSlot = ParamState.Uncertain.bitsForSlot(i);
                } else if (callArgumentMeta.isStatic()) {
                    iBitsForSlot = ParamState.Static.bitsForSlot(i);
                } else if (callArgumentMeta.isCertain()) {
                    ParamMeta paramRef = callArgumentMeta.getParamRef();
                    if (paramRef == null) {
                        k2d.a("Meta is required if param is Certain");
                        return null;
                    }
                    IrChangedBitMaskValue maskParam = paramRef.getMaskParam();
                    if (maskParam == null) {
                        k2d.a("Mask param required if param is Certain");
                        return null;
                    }
                    int maskSlot = paramRef.getMaskSlot();
                    if (maskSlot == -1) {
                        w01.a("invalid parent slot for Certain param");
                        return null;
                    }
                    arrayList.add(composableFunctionBodyTransformer.irAnd(composableFunctionBodyTransformer.irConst(ParamState.Mask.bitsForSlot(i)), maskParam.irShiftBits(maskSlot, i)));
                } else {
                    iBitsForSlot = ParamState.Uncertain.bitsForSlot(i);
                }
                iBitsForSlot2 |= iBitsForSlot;
            } else {
                iBitsForSlot2 |= StabilityBits.UNSTABLE.bitsForSlot(i);
                composableFunctionBodyTransformer = this;
            }
            i++;
            this = composableFunctionBodyTransformer;
        }
        ComposableFunctionBodyTransformer composableFunctionBodyTransformer2 = this;
        if (arrayList.isEmpty()) {
            return composableFunctionBodyTransformer2.irConst(iBitsForSlot2);
        }
        if (iBitsForSlot2 != 0) {
            IrExpression irExpressionIrConst = composableFunctionBodyTransformer2.irConst(iBitsForSlot2);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                irExpressionIrConst = composableFunctionBodyTransformer2.irOr(irExpressionIrConst, (IrExpression) it.next());
            }
            return irExpressionIrConst;
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return null;
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            next = composableFunctionBodyTransformer2.irOr((IrExpression) next, (IrExpression) it2.next());
        }
        return (IrExpression) next;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression buildChangedArgumentForCall$lambda$0$0(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrTypeParameter irTypeParameter) {
        irTypeParameter.getClass();
        return composableFunctionBodyTransformer.irTypeParameterStability(irTypeParameter);
    }

    private final List<IrExpression> buildChangedArgumentsForCall(List<CallArgumentMeta> args) {
        int iChangedParamCount = ComposableFunctionBodyTransformerKt.changedParamCount(args.size(), 0);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iChangedParamCount; i++) {
            int i2 = i * 10;
            arrayList.add(buildChangedArgumentForCall(args.subList(i2, Math.min(i2 + 10, args.size()))));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:93:0x02cb  */
    private final boolean buildPreambleStatementsAndReturnIfSkippingPossible(IrElement sourceElement, IrStatementContainer skipPreamble, IrStatementContainer bodyPreamble, boolean isSkippableDeclaration, Scope.FunctionScope scope, final IrChangedBitMaskValue dirty, final IrChangedBitMaskValue changedParam, IrDefaultBitMaskValue defaultParam, Scope.ParametersScope defaultScope) throws Throwable {
        IrExpression expression;
        Scope scope2;
        List statements;
        int i;
        Stability[] stabilityArr;
        final ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this;
        Scope.FunctionScope functionScope = scope;
        List<IrValueParameter> allTrackedParams = functionScope.getAllTrackedParams();
        IrFile fileOrNull = IrUtilsKt.getFileOrNull(functionScope.getFunction());
        int size = allTrackedParams.size();
        boolean[] zArr = new boolean[size];
        for (int i2 = 0; i2 < size; i2++) {
            zArr[i2] = true;
        }
        int size2 = allTrackedParams.size();
        IrExpression[] irExpressionArr = new IrExpression[size2];
        int i3 = 0;
        while (true) {
            expression = null;
            if (i3 >= size2) {
                break;
            }
            irExpressionArr[i3] = null;
            i3++;
        }
        int size3 = allTrackedParams.size();
        Stability[] stabilityArr2 = new Stability[size3];
        for (int i4 = 0; i4 < size3; i4++) {
            stabilityArr2[i4] = Stability.INSTANCE.getUnstable();
        }
        IrContainerExpression irContainerExpressionMutableStatementContainer = composableFunctionBodyTransformer.mutableStatementContainer();
        IrContainerExpression irContainerExpressionMutableStatementContainer2 = composableFunctionBodyTransformer.mutableStatementContainer();
        Scope scope3 = composableFunctionBodyTransformer.currentScope;
        composableFunctionBodyTransformer.currentScope = defaultScope;
        defaultScope.setParent(scope3);
        defaultScope.setLevel(scope3.getLevel() + 1);
        try {
            int size4 = allTrackedParams.size();
            int i5 = 0;
            while (i5 < size4) {
                IrValueParameter irValueParameter = allTrackedParams.get(i5);
                Scope scope4 = scope3;
                try {
                    int iDefaultIndexForSlotIndex = functionScope.defaultIndexForSlotIndex(i5);
                    IrExpressionBody defaultValue = irValueParameter.getDefaultValue();
                    if (defaultValue != null) {
                        expression = defaultValue.getExpression();
                    }
                    if (defaultParam == null || expression == null) {
                        i5 = i5;
                        i = size4;
                        stabilityArr = stabilityArr2;
                        scope2 = scope4;
                    } else {
                        boolean zIsStatic = composableFunctionBodyTransformer.isStatic(expression, fileOrNull);
                        zArr[i5] = zIsStatic;
                        irExpressionArr[i5] = expression;
                        if (!isSkippableDeclaration || zIsStatic) {
                            i = size4;
                        } else {
                            i = size4;
                            if (dirty instanceof IrChangedBitMaskVariable) {
                                stabilityArr = stabilityArr2;
                                scope2 = scope4;
                                i5 = i5;
                                try {
                                    irContainerExpressionMutableStatementContainer.getStatements().add(composableFunctionBodyTransformer.irIf(composableFunctionBodyTransformer.irGetBit(defaultParam, iDefaultIndexForSlotIndex), AbstractComposeLowering.irBlock$default(composableFunctionBodyTransformer, null, null, 0, 0, CollectionsKt.listOf(new IrExpression[]{composableFunctionBodyTransformer.irSet(irValueParameter, expression), ((IrChangedBitMaskVariable) dirty).irSetSlotUncertain(i5)}), 15, null)));
                                    irContainerExpressionMutableStatementContainer2.getStatements().add(composableFunctionBodyTransformer.irIf(composableFunctionBodyTransformer.irGetBit(defaultParam, iDefaultIndexForSlotIndex), ((IrChangedBitMaskVariable) dirty).irSetSlotUncertain(i5)));
                                } catch (Throwable th) {
                                    th = th;
                                    composableFunctionBodyTransformer.currentScope = scope2;
                                    throw th;
                                }
                            }
                        }
                        stabilityArr = stabilityArr2;
                        scope2 = scope4;
                        irContainerExpressionMutableStatementContainer.getStatements().add(composableFunctionBodyTransformer.irIf(composableFunctionBodyTransformer.irGetBit(defaultParam, iDefaultIndexForSlotIndex), composableFunctionBodyTransformer.irSet(irValueParameter, expression)));
                    }
                    i5++;
                    functionScope = scope;
                    scope3 = scope2;
                    size = size;
                    size4 = i;
                    stabilityArr2 = stabilityArr;
                    irExpressionArr = irExpressionArr;
                    expression = null;
                } catch (Throwable th2) {
                    th = th2;
                    scope2 = scope4;
                }
            }
            IrExpression[] irExpressionArr2 = irExpressionArr;
            Stability[] stabilityArr3 = stabilityArr2;
            int i6 = size;
            composableFunctionBodyTransformer.currentScope = scope3;
            List<IrValueParameter> list = allTrackedParams;
            int size5 = list.size();
            boolean z = isSkippableDeclaration;
            for (int i7 = 0; i7 < size5; i7++) {
                IrValueParameter irValueParameter2 = allTrackedParams.get(i7);
                StabilityInferencer stabilityInferencer = composableFunctionBodyTransformer.getStabilityInferencer();
                IrType varargElementType = irValueParameter2.getVarargElementType();
                if (varargElementType == null) {
                    varargElementType = irValueParameter2.getType();
                }
                Stability stabilityStabilityOf = stabilityInferencer.stabilityOf(varargElementType, fileOrNull);
                stabilityArr3[i7] = stabilityStabilityOf;
                boolean z2 = irValueParameter2.getDefaultValue() == null;
                boolean zKnownUnstable = StabilityKt.knownUnstable(stabilityStabilityOf);
                boolean z3 = scope.getUsedParams()[i7];
                scope.getMetrics().recordParameter(irValueParameter2, irValueParameter2.getType(), stabilityStabilityOf, irExpressionArr2[i7], zArr[i7], z3);
                if (!composableFunctionBodyTransformer.getEnabled(FeatureFlag.StrongSkipping) && z3 && zKnownUnstable && z2) {
                    z = false;
                }
            }
            int size6 = list.size();
            int i8 = 0;
            while (i8 < size6) {
                IrValueParameter irValueParameter3 = allTrackedParams.get(i8);
                if (!AdditionalIrUtilsKt.isVararg(irValueParameter3)) {
                    int iDefaultIndexForSlotIndex2 = scope.defaultIndexForSlotIndex(i8);
                    IrExpressionBody defaultValue2 = irValueParameter3.getDefaultValue();
                    Stability stability = stabilityArr3[i8];
                    boolean zKnownUnstable2 = StabilityKt.knownUnstable(stability);
                    boolean z4 = scope.getUsedParams()[i8];
                    if (z && z4 && (dirty instanceof IrChangedBitMaskVariable)) {
                        FeatureFlag featureFlag = FeatureFlag.StrongSkipping;
                        if (!composableFunctionBodyTransformer.getEnabled(featureFlag) && zKnownUnstable2 && defaultParam != null && defaultValue2 != null) {
                            skipPreamble.getStatements().add(composableFunctionBodyTransformer.irIf(composableFunctionBodyTransformer.irGetBit(defaultParam, iDefaultIndexForSlotIndex2), ((IrChangedBitMaskVariable) dirty).irOrSetBitsAtSlot(i8, composableFunctionBodyTransformer.irConst(ParamState.Same.bitsForSlot(i8)))));
                        } else if (composableFunctionBodyTransformer.getEnabled(featureFlag) || !zKnownUnstable2) {
                            boolean z5 = zArr[i8];
                            IrExpression irExpressionIrCallChanged = composableFunctionBodyTransformer.irCallChanged(stability, changedParam, i8, irValueParameter3);
                            if (defaultParam != null && !z5) {
                                irExpressionIrCallChanged = composableFunctionBodyTransformer.irAndAnd(composableFunctionBodyTransformer.irIsProvided(defaultParam, iDefaultIndexForSlotIndex2), irExpressionIrCallChanged);
                            }
                            IrChangedBitMaskVariable irChangedBitMaskVariable = (IrChangedBitMaskVariable) dirty;
                            IrExpression irExpressionIrOrSetBitsAtSlot = irChangedBitMaskVariable.irOrSetBitsAtSlot(i8, AbstractComposeLowering.irIfThenElse$default(composableFunctionBodyTransformer, composableFunctionBodyTransformer.getContext().getIrBuiltIns().getIntType(), irExpressionIrCallChanged, composableFunctionBodyTransformer.irConst(ParamState.Different.bitsForSlot(i8)), composableFunctionBodyTransformer.irConst(ParamState.Same.bitsForSlot(i8)), 0, 0, 48, null));
                            IrExpression irExpressionIrIsUncertain = composableFunctionBodyTransformer.getEnabled(featureFlag) ? composableFunctionBodyTransformer.irIsUncertain(changedParam, i8) : composableFunctionBodyTransformer.irIsUncertainAndStable(changedParam, i8);
                            skipPreamble.getStatements().add((defaultParam == null || defaultValue2 == null || !z5) ? composableFunctionBodyTransformer.irIf(irExpressionIrIsUncertain, irExpressionIrOrSetBitsAtSlot) : AbstractComposeLowering.irWhen$default(composableFunctionBodyTransformer, null, IrStatementOrigin.Companion.getIF(), CollectionsKt.listOf(new IrBranch[]{composableFunctionBodyTransformer.irBranch(composableFunctionBodyTransformer.irGetBit(defaultParam, iDefaultIndexForSlotIndex2), irChangedBitMaskVariable.irOrSetBitsAtSlot(i8, composableFunctionBodyTransformer.irConst(ParamState.Static.bitsForSlot(i8)))), composableFunctionBodyTransformer.irBranch(irExpressionIrIsUncertain, irExpressionIrOrSetBitsAtSlot)}), 1, null));
                        }
                    }
                }
                i8++;
                size6 = size6;
                list = list;
                z = z;
                fileOrNull = fileOrNull;
                i6 = i6;
            }
            boolean z6 = z;
            final IrFile irFile = fileOrNull;
            int i9 = i6;
            List<IrValueParameter> list2 = list;
            final int i10 = 0;
            for (int size7 = list2.size(); i10 < size7; size7 = size7) {
                IrValueParameter irValueParameter4 = allTrackedParams.get(i10);
                final IrType varargElementType2 = irValueParameter4.getVarargElementType();
                if (varargElementType2 != null && z6 && (dirty instanceof IrChangedBitMaskVariable)) {
                    if (defaultParam == null || irValueParameter4.getDefaultValue() == null) {
                        statements = skipPreamble.getStatements();
                    } else {
                        int iDefaultIndexForSlotIndex3 = scope.defaultIndexForSlotIndex(i10);
                        IrBlock irBlockIrBlock$default = AbstractComposeLowering.irBlock$default(composableFunctionBodyTransformer, null, null, 0, 0, CollectionsKt.emptyList(), 15, null);
                        skipPreamble.getStatements().add(composableFunctionBodyTransformer.irIf(composableFunctionBodyTransformer.irIsProvided(defaultParam, iDefaultIndexForSlotIndex3), irBlockIrBlock$default));
                        statements = irBlockIrBlock$default.getStatements();
                    }
                    List list3 = statements;
                    IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irValueParameter4.getType());
                    classOrNull.getClass();
                    IrSimpleFunctionSymbol propertyGetter = AdditionalIrUtilsKt.getPropertyGetter(classOrNull, "size");
                    propertyGetter.getClass();
                    IrSimpleFunction owner = propertyGetter.getOwner();
                    list3.add(composableFunctionBodyTransformer.irStartMovableGroup(irValueParameter4, AbstractComposeLowering.irMethodCall$default(composableFunctionBodyTransformer, composableFunctionBodyTransformer.irGet(irValueParameter4), owner, 0, 0, 12, null), defaultScope));
                    IrChangedBitMaskVariable irChangedBitMaskVariable2 = (IrChangedBitMaskVariable) dirty;
                    IrType intType = composableFunctionBodyTransformer.getContext().getIrBuiltIns().getIntType();
                    IrCall irCallIrMethodCall$default = AbstractComposeLowering.irMethodCall$default(composableFunctionBodyTransformer, composableFunctionBodyTransformer.irGet(irValueParameter4), owner, 0, 0, 12, null);
                    composableFunctionBodyTransformer = this;
                    list3.add(irChangedBitMaskVariable2.irOrSetBitsAtSlot(i10, AbstractComposeLowering.irIfThenElse$default(composableFunctionBodyTransformer, intType, irChanged$default(composableFunctionBodyTransformer, irCallIrMethodCall$default, irFile, true, false, 8, null), composableFunctionBodyTransformer.irConst(ParamState.Different.bitsForSlot(i10)), composableFunctionBodyTransformer.irConst(ParamState.Uncertain.bitsForSlot(i10)), 0, 0, 48, null)));
                    i10 = i10;
                    list3.add(composableFunctionBodyTransformer.irForLoop(scope.getFunction(), varargElementType2, composableFunctionBodyTransformer.irGet(irValueParameter4), new Function1() { // from class: nc2
                        public final Object invoke(Object obj) {
                            return ComposableFunctionBodyTransformer.buildPreambleStatementsAndReturnIfSkippingPossible$lambda$3$0(this.b, varargElementType2, irFile, changedParam, i10, dirty, (IrValueDeclaration) obj);
                        }
                    }));
                    list3.add(composableFunctionBodyTransformer.irEndMovableGroup(scope));
                    list3.add(composableFunctionBodyTransformer.irIf(composableFunctionBodyTransformer.irIsUncertainAndStable(dirty, i10), irChangedBitMaskVariable2.irOrSetBitsAtSlot(i10, composableFunctionBodyTransformer.irConst(ParamState.Same.bitsForSlot(i10)))));
                }
                i10++;
            }
            int size8 = list2.size();
            for (int i11 = 0; i11 < size8; i11++) {
                allTrackedParams.get(i11).setDefaultValue((IrExpressionBody) null);
            }
            if (z6) {
                for (int i12 = 0; i12 < i9; i12++) {
                    if (!zArr[i12]) {
                        if (!irContainerExpressionMutableStatementContainer.getStatements().isEmpty()) {
                            scope.setHasDefaultsGroup(true);
                            scope.getMetrics().recordGroup();
                            bodyPreamble.getStatements().add(composableFunctionBodyTransformer.irStartDefaults(sourceElement, defaultScope));
                            List statements2 = bodyPreamble.getStatements();
                            IrExpression irExpressionIrOrOr = composableFunctionBodyTransformer.irOrOr(composableFunctionBodyTransformer.irEqual(changedParam.irLowBit(), composableFunctionBodyTransformer.irConst(0)), composableFunctionBodyTransformer.irDefaultsInvalid());
                            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
                            spreadBuilder.add(irSkipToGroupEnd$default(composableFunctionBodyTransformer, 0, 0, 3, null));
                            spreadBuilder.addSpread(irContainerExpressionMutableStatementContainer2.getStatements().toArray(new IrStatement[0]));
                            statements2.add(AbstractComposeLowering.irIfThenElse$default(this, null, irExpressionIrOrOr, irContainerExpressionMutableStatementContainer, AbstractComposeLowering.irBlock$default(composableFunctionBodyTransformer, null, null, 0, 0, CollectionsKt.listOf(spreadBuilder.toArray(new IrStatement[spreadBuilder.size()])), 15, null), 0, 0, 49, null));
                            bodyPreamble.getStatements().add(irEndDefaults());
                        }
                        return z6;
                    }
                }
            }
            bodyPreamble.getStatements().addAll(irContainerExpressionMutableStatementContainer.getStatements());
            return z6;
        } catch (Throwable th3) {
            th = th3;
            scope2 = scope3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression buildPreambleStatementsAndReturnIfSkippingPossible$lambda$3$0(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrType irType, IrFile irFile, IrChangedBitMaskValue irChangedBitMaskValue, int i, IrChangedBitMaskValue irChangedBitMaskValue2, IrValueDeclaration irValueDeclaration) {
        irValueDeclaration.getClass();
        return ((IrChangedBitMaskVariable) irChangedBitMaskValue2).irOrSetBitsAtSlot(i, AbstractComposeLowering.irIfThenElse$default(composableFunctionBodyTransformer, composableFunctionBodyTransformer.getContext().getIrBuiltIns().getIntType(), composableFunctionBodyTransformer.irCallChanged(composableFunctionBodyTransformer.getStabilityInferencer().stabilityOf(irType, irFile), irChangedBitMaskValue, i, irValueDeclaration), composableFunctionBodyTransformer.irConst(ParamState.Different.bitsForSlot(i)), composableFunctionBodyTransformer.irConst(ParamState.Uncertain.bitsForSlot(i)), 0, 0, 48, null));
    }

    public static IrExpression c0(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Scope.BlockScope blockScope) {
        return irEndReplaceGroup$default(composableFunctionBodyTransformer, 0, 0, blockScope, 3, (Object) null);
    }

    public static IrExpression d0(boolean z, ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrChangedBitMaskValue irChangedBitMaskValue, IrChangedBitMaskValue irChangedBitMaskValue2, boolean z2, List list, List list2) {
        ParamMeta paramRef;
        list.getClass();
        list2.getClass();
        if (!z) {
            int size = list2.size();
            for (int i = 0; i < size; i++) {
                CallArgumentMeta callArgumentMeta = (CallArgumentMeta) list2.get(i);
                ParamMeta paramRef2 = callArgumentMeta.getParamRef();
                if (Intrinsics.areEqual(paramRef2 != null ? paramRef2.getMaskParam() : null, irChangedBitMaskValue) && (paramRef = callArgumentMeta.getParamRef()) != null) {
                    paramRef.setMaskParam(irChangedBitMaskValue2);
                }
            }
        }
        return composableFunctionBodyTransformer.irIntrinsicRememberInvalid(z2, list, list2, new ComposableFunctionBodyTransformer$visitRestartableComposableFunction$1$2(composableFunctionBodyTransformer));
    }

    private final void encounteredCapturedComposableCall() {
        for (Scope parent = this.currentScope; parent != null; parent = parent.getParent()) {
            if (parent instanceof Scope.CaptureScope) {
                ((Scope.CaptureScope) parent).markCapturedComposableCall();
                return;
            }
        }
    }

    private final void encounteredCoalescableGroup(Scope.BlockScope coalescableScope, Function0<Unit> realizeGroup, Function0<? extends IrExpression> makeEnd) {
        for (Scope parent = this.currentScope; parent != null; parent = parent.getParent()) {
            if (!(parent instanceof Scope.CallScope) && !(parent instanceof Scope.ReturnScope)) {
                if (!(parent instanceof Scope.FunctionScope)) {
                    if (parent instanceof Scope.BlockScope) {
                        ((Scope.BlockScope) parent).markCoalescableGroup(coalescableScope, realizeGroup, makeEnd);
                        return;
                    } else {
                        k2d.a("Unexpected scope type");
                        return;
                    }
                }
                Scope.FunctionScope functionScope = (Scope.FunctionScope) parent;
                functionScope.markCoalescableGroup(coalescableScope, realizeGroup, makeEnd);
                if (!functionScope.isInlinedLambda() || functionScope.getIsComposable()) {
                    return;
                }
            }
        }
    }

    private final void encounteredComposableCall(boolean withGroups) {
        for (Scope parent = this.currentScope; parent != null; parent = parent.getParent()) {
            if (parent instanceof Scope.FunctionScope) {
                Scope.FunctionScope functionScope = (Scope.FunctionScope) parent;
                functionScope.recordComposableCall(withGroups);
                if (!functionScope.isInlinedLambda()) {
                    return;
                }
            } else {
                if (parent instanceof Scope.BlockScope) {
                    ((Scope.BlockScope) parent).recordComposableCall(withGroups);
                } else if (parent instanceof Scope.ClassScope) {
                    return;
                }
            }
            withGroups = true;
        }
    }

    private final void encounteredJump(IrBreakContinue jump, Function1<? super IrExpression, Unit> extraEndLocation) {
        for (Scope parent = this.currentScope; parent != null; parent = parent.getParent()) {
            if (parent instanceof Scope.ClassScope) {
                k2d.a("Unexpected Class Scope encountered");
                return;
            }
            if (parent instanceof Scope.FunctionScope) {
                if (!((Scope.FunctionScope) parent).isInlinedLambda()) {
                    k2d.a("Unexpected Function Scope encountered");
                    return;
                }
            } else if (parent instanceof Scope.LoopScope) {
                Scope.LoopScope loopScope = (Scope.LoopScope) parent;
                loopScope.markJump(jump, extraEndLocation);
                if (Intrinsics.areEqual(jump.getLoop(), loopScope.getLoop())) {
                    return;
                }
            } else if (parent instanceof Scope.BlockScope) {
                ((Scope.BlockScope) parent).markJump(extraEndLocation);
            }
        }
    }

    private final void encounteredReturn(IrReturnTargetSymbol symbol, Function1<? super IrExpression, Unit> extraEndLocation) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (Scope parent = this.currentScope; parent != null; parent = parent.getParent()) {
            if (parent instanceof Scope.FunctionScope) {
                Scope.FunctionScope functionScope = (Scope.FunctionScope) parent;
                if (Intrinsics.areEqual(functionScope.getFunction(), symbol.getOwner())) {
                    functionScope.setHasAnyEarlyReturn(true);
                    if (getRollbackGroupMarkerEnabled() && z) {
                        Scope scope = this.currentScope;
                        Scope.BlockScope blockScope = scope instanceof Scope.BlockScope ? (Scope.BlockScope) scope : null;
                        if (blockScope == null) {
                            blockScope = (Scope.BlockScope) parent;
                        }
                        extraEndLocation.invoke(irEndToMarker(irGet(functionScope.allocateMarker()), blockScope));
                        if (functionScope.isInlinedLambda()) {
                            functionScope.setHasInlineEarlyReturn(true);
                            return;
                        } else {
                            functionScope.markReturn(extraEndLocation);
                            return;
                        }
                    }
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        ((Scope.BlockScope) arrayList.get(i)).markReturn(extraEndLocation);
                    }
                    functionScope.markReturn(extraEndLocation);
                    if (functionScope.isInlinedLambda() && functionScope.getInComposableCall()) {
                        functionScope.setHasInlineEarlyReturn(true);
                        return;
                    }
                    return;
                }
                if (functionScope.isInlinedLambda()) {
                    arrayList.add(parent);
                    if (functionScope.getInComposableCall()) {
                        functionScope.setHasInlineEarlyReturn(true);
                        z = true;
                    }
                }
            } else if (parent instanceof Scope.BlockScope) {
                arrayList.add(parent);
            }
        }
    }

    private final boolean endsWithReturnOrJump(IrExpression irExpression) {
        while (irExpression != null) {
            if ((irExpression instanceof IrReturn) || (irExpression instanceof IrBreakContinue)) {
                return true;
            }
            if (!(irExpression instanceof IrBlock)) {
                return false;
            }
            irExpression = (IrStatement) CollectionsKt.lastOrNull(((IrBlock) irExpression).getStatements());
        }
        return false;
    }

    private final ParamMeta extractParamMetaFromScopes(IrValueDeclaration param) {
        int iIndexOf;
        IrExpressionBody defaultValue;
        IrExpression expression;
        IrDeclarationParent parent = param.getParent();
        for (Scope parent2 = this.currentScope; parent2 != null; parent2 = parent2.getParent()) {
            if (parent2 instanceof Scope.FunctionScope) {
                Scope.FunctionScope functionScope = (Scope.FunctionScope) parent2;
                if (Intrinsics.areEqual(functionScope.getFunction(), parent)) {
                    if (!functionScope.getIsComposable() || (iIndexOf = CollectionsKt.indexOf(functionScope.getAllTrackedParams(), param)) == -1) {
                        return null;
                    }
                    IrChangedBitMaskValue dirty = functionScope.getDirty();
                    boolean z = false;
                    if ((param instanceof IrValueParameter) && (defaultValue = ((IrValueParameter) param).getDefaultValue()) != null && (expression = defaultValue.getExpression()) != null && !isStatic(expression, IrUtilsKt.getFileOrNull(functionScope.getFunction()))) {
                        z = true;
                    }
                    return new ParamMeta(iIndexOf, dirty, z);
                }
                if (!this.inlineLambdaInfo.isInlineLambda(functionScope.getFunction()) || this.inlineLambdaInfo.isCrossinlineLambda(functionScope.getFunction())) {
                    return null;
                }
            }
        }
        return null;
    }

    private final void forEach(Scope scope, Function1<? super Scope, Unit> function1) {
        while (scope != null) {
            function1.invoke(scope);
            scope = scope.getParent();
        }
    }

    private final int functionSourceKey(IrFunction function) {
        if (function instanceof IrSimpleFunction) {
            return sourceKey((IrSimpleFunction) function);
        }
        if (function instanceof IrConstructor) {
            k2d.a("expected simple function, got constructor");
            return 0;
        }
        bu8.a();
        return 0;
    }

    private final List<IrTypeArgument> getArguments(IrType irType) {
        IrSimpleType irSimpleType = irType instanceof IrSimpleType ? (IrSimpleType) irType : null;
        List<IrTypeArgument> arguments = irSimpleType != null ? irSimpleType.getArguments() : null;
        return arguments == null ? CollectionsKt.emptyList() : arguments;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Scope.FunctionScope getCurrentFunctionScope() {
        Scope.FunctionScope functionScope = this.currentScope.getFunctionScope();
        if (functionScope != null) {
            return functionScope;
        }
        f2f.a("Expected a FunctionScope but none exist. \n", printScopeStack());
        return null;
    }

    private final IrProperty getCurrentMarkerProperty() {
        return (IrProperty) this.currentMarkerProperty.value($$delegatedProperties[6].getName());
    }

    private final IrProperty getDefaultsInvalidFunction() {
        return (IrProperty) this.defaultsInvalidFunction.value($$delegatedProperties[21].getName());
    }

    private final IrSimpleFunction getEndDefaultsFunction() {
        return (IrSimpleFunction) this.endDefaultsFunction.value($$delegatedProperties[2].getName());
    }

    private final IrSimpleFunction getEndMovableFunction() {
        return (IrSimpleFunction) this.endMovableFunction.value($$delegatedProperties[4].getName());
    }

    private final IrSimpleFunction getEndRestartGroupFunction() {
        return (IrSimpleFunction) this.endRestartGroupFunction.value($$delegatedProperties[8].getName());
    }

    private final IrSimpleFunction getEndToMarkerFunction() {
        return (IrSimpleFunction) this.endToMarkerFunction.value($$delegatedProperties[7].getName());
    }

    private final boolean getHasSourceInformation(Scope.BlockScope blockScope) {
        return blockScope.calculateHasSourceInformation(this.collectSourceInformation);
    }

    private final IrSimpleFunction getJoinKeyFunction() {
        return (IrSimpleFunction) this.joinKeyFunction.value($$delegatedProperties[22].getName());
    }

    private final IrSimpleFunctionSymbol getRememberComposableLambdaFunction() {
        return (IrSimpleFunctionSymbol) this.rememberComposableLambdaFunction.value($$delegatedProperties[17].getName());
    }

    private final boolean getRollbackGroupMarkerEnabled() {
        return (getCurrentMarkerProperty() == null || getEndToMarkerFunction() == null) ? false : true;
    }

    private final IrSimpleFunction getShouldExecuteFunction() {
        return (IrSimpleFunction) this.shouldExecuteFunction.value($$delegatedProperties[9].getName());
    }

    private final IrSimpleFunction getSkipToGroupEndFunction() {
        return (IrSimpleFunction) this.skipToGroupEndFunction.value($$delegatedProperties[0].getName());
    }

    private final String getSourceInformation(Scope.BlockScope blockScope) {
        return blockScope.calculateSourceInfo(this.collectSourceInformation);
    }

    private final IrSimpleFunction getSourceInformationFunction() {
        return (IrSimpleFunction) this.sourceInformationFunction.value($$delegatedProperties[10].getName());
    }

    private final IrSimpleFunction getSourceInformationMarkerEndFunction() {
        return (IrSimpleFunction) this.sourceInformationMarkerEndFunction.value($$delegatedProperties[16].getName());
    }

    private final IrSimpleFunction getSourceInformationMarkerStartFunction() {
        return (IrSimpleFunction) this.sourceInformationMarkerStartFunction.value($$delegatedProperties[11].getName());
    }

    private final IrSimpleFunction getStartDefaultsFunction() {
        return (IrSimpleFunction) this.startDefaultsFunction.value($$delegatedProperties[1].getName());
    }

    private final IrSimpleFunction getStartMovableFunction() {
        return (IrSimpleFunction) this.startMovableFunction.value($$delegatedProperties[3].getName());
    }

    private final IrSimpleFunction getStartRestartGroupFunction() {
        return (IrSimpleFunction) this.startRestartGroupFunction.value($$delegatedProperties[5].getName());
    }

    private final IrSimpleFunction getTraceEventEndFunction() {
        return (IrSimpleFunction) this.traceEventEndFunction.value($$delegatedProperties[15].getName());
    }

    private final boolean getTraceEventMarkersEnabled() {
        return this.traceMarkersEnabled && getTraceEventEndFunction() != null;
    }

    private final IrSimpleFunction getTraceEventStartFunction() {
        return (IrSimpleFunction) this.traceEventStartFunction.value($$delegatedProperties[14].getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IrSimpleFunction getUpdateChangedFlagsFunction() {
        return (IrSimpleFunction) this.updateChangedFlagsFunction.value($$delegatedProperties[12].getName());
    }

    private final IrSimpleFunction getUpdateScopeFunction() {
        return (IrSimpleFunction) this.updateScopeFunction.value($$delegatedProperties[19].getName());
    }

    private final boolean getUseNonSkippingGroupOptimization() {
        return ((Boolean) this.useNonSkippingGroupOptimization.value($$delegatedProperties[18].getName())).booleanValue();
    }

    private final IrExpression handleLoop(IrLoop loop) {
        Scope.LoopScope loopScope = new Scope.LoopScope(loop);
        Scope scope = this.currentScope;
        this.currentScope = loopScope;
        loopScope.setParent(scope);
        loopScope.setLevel(scope.getLevel() + 1);
        try {
            loop.setCondition(loop.getCondition().transform(this, (Object) null));
            if (loopScope.getNeedsGroupPerIteration() && loopScope.getHasComposableCalls()) {
                loop.setCondition(asReplaceGroup(loop.getCondition(), loopScope));
            }
            IrExpression body = loop.getBody();
            loop.setBody(body != null ? body.transform(this, (Object) null) : null);
            if (loopScope.getNeedsGroupPerIteration() && loopScope.getHasComposableCalls()) {
                IrExpression body2 = loop.getBody();
                if (body2 instanceof IrBlock) {
                    Iterator it = ((IrBlock) body2).getStatements().iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i = -1;
                            break;
                        }
                        IrVariable irVariable = (IrStatement) it.next();
                        IrVariable irVariable2 = irVariable instanceof IrVariable ? irVariable : null;
                        if (Intrinsics.areEqual(irVariable2 != null ? irVariable2.getOrigin() : null, IrDeclarationOrigin.Companion.getFOR_LOOP_VARIABLE())) {
                            break;
                        }
                        i++;
                    }
                    loop.setBody(withReplaceGroupStatements((IrBlock) body2, loopScope, i + 1));
                } else {
                    loop.setBody(body2 != null ? asReplaceGroup(body2, loopScope) : null);
                }
            }
            this.currentScope = scope;
            if ((loopScope.getNeedsGroupPerIteration() && (getCurrentFunctionScope().getOuterGroupRequired() || getCurrentFunctionScope().getHasAnyEarlyReturn())) || !loopScope.getHasComposableCalls()) {
                return loop;
            }
            loopScope.realizeAllDirectChildren();
            return asCoalescableGroup(loop, loopScope);
        } catch (Throwable th) {
            this.currentScope = scope;
            throw th;
        }
    }

    private final <R> R inScope(Scope scope, Function0<? extends R> block) {
        Scope scope2 = this.currentScope;
        this.currentScope = scope;
        scope.setParent(scope2);
        scope.setLevel(scope2.getLevel() + 1);
        try {
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            this.currentScope = scope2;
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer$intrinsicRememberScope$1] */
    private final AnonymousClass1 intrinsicRememberScope(IrCall rememberCall) {
        return new Scope.BlockScope(rememberCall, this) { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.intrinsicRememberScope.1
            private final IrFunction currentFunction;
            private final IrSimpleFunction rememberFunction;

            {
                super("<intrinsic-remember>");
                this.rememberFunction = rememberCall.getSymbol().getOwner();
                this.currentFunction = this.getCurrentFunctionScope().getFunction();
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public boolean calculateHasSourceInformation(boolean sourceInformationEnabled) {
                return sourceInformationEnabled;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public String calculateSourceInfo(boolean sourceInformationEnabled) {
                if (!sourceInformationEnabled) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ComposableFunctionBodyTransformerKt.callInformation(this.rememberFunction));
                String strCalculateSourceInfo = super.calculateSourceInfo(true);
                if (strCalculateSourceInfo != null) {
                    sb.append(strCalculateSourceInfo);
                }
                sb.append(":");
                sb.append(IrDeclarationsKt.getName(IrUtilsKt.getFile(this.currentFunction)));
                sb.append("#");
                String string = Integer.toString(ComposableFunctionBodyTransformerKt.packageHash(this.rememberFunction), CharsKt.checkRadix(36));
                string.getClass();
                sb.append(string);
                return sb.toString();
            }

            public final IrFunction getCurrentFunction() {
                return this.currentFunction;
            }

            public final IrSimpleFunction getRememberFunction() {
                return this.rememberFunction;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IrExpression irBitsForSlot(int bits, int slot) {
        return irConst(ComposableFunctionBodyTransformerKt.bitsForSlot(bits, slot));
    }

    private final IrExpression irCallChanged(Stability stability, IrChangedBitMaskValue changedParam, int slotIndex, IrValueDeclaration param) {
        IrFile fileOrNull = IrUtilsKt.getFileOrNull(param);
        return (getEnabled(FeatureFlag.StrongSkipping) && StabilityKt.isUncertain(stability)) ? AbstractComposeLowering.irIfThenElse$default(this, getContext().getIrBuiltIns().getBooleanType(), irIsStable(changedParam, slotIndex), irChanged(irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null), irGet(param), fileOrNull, true, true, true), irChanged(irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null), irGet(param), fileOrNull, false, true, true), 0, 0, 48, null) : irChanged$default(this, irGet(param), fileOrNull, true, false, 8, null);
    }

    private final IrExpression irChanged(IrExpression value, IrFile fileContainingValue, boolean compareInstanceForFunctionTypes, boolean compareInstanceForUnstableValues) {
        return irChanged(irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null), value, fileContainingValue, false, compareInstanceForFunctionTypes, compareInstanceForUnstableValues);
    }

    public static /* synthetic */ IrExpression irChanged$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrExpression irExpression, IrFile irFile, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = composableFunctionBodyTransformer.getEnabled(FeatureFlag.StrongSkipping);
        }
        return composableFunctionBodyTransformer.irChanged(irExpression, irFile, z, z2);
    }

    private final IrExpression irCurrentComposer(int startOffset, int endOffset, IrValueParameter composerParameter) {
        return org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrGetValueImpl$default(startOffset, endOffset, composerParameter.getSymbol(), (IrStatementOrigin) null, 8, (Object) null);
    }

    public static /* synthetic */ IrExpression irCurrentComposer$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, int i, int i2, IrValueParameter irValueParameter, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = -1;
        }
        if ((i3 & 2) != 0) {
            i2 = -1;
        }
        if ((i3 & 4) != 0) {
            irValueParameter = composableFunctionBodyTransformer.nearestComposer();
        }
        return composableFunctionBodyTransformer.irCurrentComposer(i, i2, irValueParameter);
    }

    private final IrCall irDefaultsInvalid() {
        IrExpression irExpressionIrCurrentComposer$default = irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null);
        IrSimpleFunction getter = getDefaultsInvalidFunction().getGetter();
        getter.getClass();
        return AbstractComposeLowering.irMethodCall$default(this, irExpressionIrCurrentComposer$default, getter, 0, 0, 12, null);
    }

    private final IrExpression irEndDefaults() {
        return AbstractComposeLowering.irMethodCall$default(this, irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null), getEndDefaultsFunction(), 0, 0, 12, null);
    }

    private final IrExpression irEndMovableGroup(Scope.BlockScope scope) {
        return AbstractComposeLowering.irMethodCall$default(this, irCurrentComposer$default(this, scope, 0, 0, 3, (Object) null), getEndMovableFunction(), 0, 0, 12, null);
    }

    private final IrExpression irEndReplaceGroup(int startOffset, int endOffset, Scope.BlockScope scope) {
        return irEndReplaceGroup(irCurrentComposer(scope, startOffset, endOffset), startOffset, endOffset);
    }

    public static /* synthetic */ IrExpression irEndReplaceGroup$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, int i, int i2, Scope.BlockScope blockScope, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = -1;
        }
        if ((i3 & 2) != 0) {
            i2 = -1;
        }
        return composableFunctionBodyTransformer.irEndReplaceGroup(i, i2, blockScope);
    }

    private final IrExpression irEndRestartGroup(Scope.BlockScope scope) {
        IrFunction function;
        Scope.FunctionScope functionScope = scope instanceof Scope.FunctionScope ? (Scope.FunctionScope) scope : null;
        int endOffset = (functionScope == null || (function = functionScope.getFunction()) == null) ? -1 : function.getEndOffset();
        return irMethodCall(irCurrentComposer(scope, endOffset, endOffset), getEndRestartGroupFunction(), endOffset, endOffset);
    }

    private final IrExpression irEndRestartGroupAndUpdateScope(Scope.FunctionScope scope, final IrChangedBitMaskValue changedParam, final IrDefaultBitMaskValue defaultParam, int numRealValueParameters) {
        final IrFunction function = scope.getFunction();
        IrValueParameter dispatchReceiverParameter = function.getDispatchReceiverParameter();
        IrVariableImpl irVariableImplIrTemporary$default = dispatchReceiverParameter != null ? irTemporary$default(this, irGet(dispatchReceiverParameter), "rcvr", (IrType) null, false, false, 28, (Object) null) : null;
        int size = function.getParameters().size();
        final int thisParamCount = numRealValueParameters + ComposableFunctionBodyTransformerKt.getThisParamCount(function);
        final int i = thisParamCount + 1;
        final int iChangedParamCount = ComposableFunctionBodyTransformerKt.changedParamCount(numRealValueParameters, ComposableFunctionBodyTransformerKt.getThisParamCount(function)) + i;
        if (defaultParam != null) {
            int iDefaultParamCount = ComposableFunctionBodyTransformerKt.defaultParamCount(numRealValueParameters) + iChangedParamCount;
            if (size != iDefaultParamCount) {
                uhe.a("Expected ", iDefaultParamCount, " params for ", IrUtilsKt.getFqNameWhenAvailable(function), ", found ", size);
                return null;
            }
        } else if (size != iChangedParamCount) {
            uhe.a("Expected ", iChangedParamCount, " params for ", IrUtilsKt.getFqNameWhenAvailable(function), ", found ", size);
            return null;
        }
        final IrVariableImpl irVariableImpl = irVariableImplIrTemporary$default;
        return AbstractComposeLowering.irBlock$default(this, null, null, 0, 0, CollectionsKt.listOfNotNull(new IrElement[]{irVariableImpl, irSafeCall(irEndRestartGroup(scope), getUpdateScopeFunction().getSymbol(), irLambdaExpression(-1, -1, getBuiltIns().getUnitType(), new Function1() { // from class: nb2
            public final Object invoke(Object obj) {
                return ComposableFunctionBodyTransformer.J(function, this, thisParamCount, changedParam, i, defaultParam, iChangedParamCount, irVariableImpl, (IrSimpleFunction) obj);
            }
        }))}), 15, null);
    }

    private final IrExpression irEndToMarker(IrExpression marker, Scope.BlockScope scope) {
        IrExpression irExpressionIrCurrentComposer$default = irCurrentComposer$default(this, scope, 0, 0, 3, (Object) null);
        IrSimpleFunction endToMarkerFunction = getEndToMarkerFunction();
        endToMarkerFunction.getClass();
        IrCall irCallIrMethodCall$default = AbstractComposeLowering.irMethodCall$default(this, irExpressionIrCurrentComposer$default, endToMarkerFunction, 0, 0, 12, null);
        irCallIrMethodCall$default.getArguments().set(1, marker);
        return irCallIrMethodCall$default;
    }

    private final IrConst irFunctionSourceKey(IrFunction function) {
        return IrConstImpl.Companion.int(-1, -1, getContext().getIrBuiltIns().getIntType(), functionSourceKey(function));
    }

    public static /* synthetic */ IrConst irFunctionSourceKey$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrFunction irFunction, int i, Object obj) {
        if ((i & 1) != 0) {
            irFunction = composableFunctionBodyTransformer.getCurrentFunctionScope().getFunction();
        }
        return composableFunctionBodyTransformer.irFunctionSourceKey(irFunction);
    }

    private final IrExpression irIfTraceInProgress(IrExpression body) {
        IrExpression irExpressionIrIsTraceInProgress = irIsTraceInProgress();
        if (irExpressionIrIsTraceInProgress != null) {
            return irIf(irExpressionIrIsTraceInProgress, body);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IrExpression irIntrinsicChanged(boolean isMemoizedLambda, IrExpression arg, CallArgumentMeta argInfo) {
        ParamMeta paramRef = argInfo.getParamRef();
        IrChangedBitMaskValue maskParam = paramRef != null ? paramRef.getMaskParam() : null;
        if (argInfo.isStatic()) {
            return null;
        }
        if (argInfo.isCertain() && StabilityKt.knownStable(argInfo.getStability()) && (maskParam instanceof IrChangedBitMaskVariable) && !paramRef.getHasNonStaticDefault()) {
            return irEqual(((IrChangedBitMaskVariable) maskParam).irIsolateBitsAtSlot(paramRef.getMaskSlot(), true), irConst(ParamState.Different.bitsForSlot(paramRef.getMaskSlot())));
        }
        if (!argInfo.isCertain() || StabilityKt.knownUnstable(argInfo.getStability()) || !(maskParam instanceof IrChangedBitMaskVariable) || paramRef.getHasNonStaticDefault()) {
            return (!argInfo.isCertain() || StabilityKt.knownUnstable(argInfo.getStability()) || maskParam == null) ? irChanged(arg, argInfo.getFileContainingArg(), false, isMemoizedLambda) : irOrOr(irAndAnd(irGreater(irXor(maskParam.irIsolateBitsAtSlot(paramRef.getMaskSlot(), true), irConst(ComposableFunctionBodyTransformerKt.bitsForSlot(3, paramRef.getMaskSlot()))), irConst(ComposableFunctionBodyTransformerKt.bitsForSlot(2, paramRef.getMaskSlot()))), irChanged(arg, argInfo.getFileContainingArg(), false, isMemoizedLambda)), irEqual(maskParam.irIsolateBitsAtSlot(paramRef.getMaskSlot(), false), irConst(ParamState.Different.bitsForSlot(paramRef.getMaskSlot()))));
        }
        IrChangedBitMaskVariable irChangedBitMaskVariable = (IrChangedBitMaskVariable) maskParam;
        return irOrOr(irEqual(irChangedBitMaskVariable.irIsolateBitsAtSlot(paramRef.getMaskSlot(), true), irConst(ParamState.Different.bitsForSlot(paramRef.getMaskSlot()))), irAndAnd(irNotEqual(irChangedBitMaskVariable.irSlotAnd(paramRef.getMaskSlot(), StabilityBits.UNSTABLE.getBits()), irConst(0)), irChanged(arg, argInfo.getFileContainingArg(), false, isMemoizedLambda)));
    }

    private final IrExpression irIntrinsicRememberInvalid(boolean isMemoizedLambda, List<? extends IrExpression> args, List<CallArgumentMeta> metas, Function3<? super Boolean, ? super IrExpression, ? super CallArgumentMeta, ? extends IrExpression> changedExpr) {
        Object obj;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj2 : args) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            IrExpression irExpression = (IrExpression) changedExpr.invoke(Boolean.valueOf(isMemoizedLambda), (IrExpression) obj2, metas.get(i));
            if (irExpression != null) {
                arrayList.add(irExpression);
            }
            i = i2;
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = irBooleanOr((IrExpression) next, (IrExpression) it.next());
            }
            obj = next;
        } else {
            obj = null;
        }
        IrExpression irExpression2 = (IrExpression) obj;
        return irExpression2 == null ? irConst(false) : irExpression2;
    }

    private final IrExpression irIsProvided(IrDefaultBitMaskValue irDefaultBitMaskValue, int slot) {
        return irEqual(irDefaultBitMaskValue.irIsolateBitAtIndex(slot), irConst(0));
    }

    private final IrCall irIsSkipping() {
        IrExpression irExpressionIrCurrentComposer$default = irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null);
        IrSimpleFunction getter = isSkippingFunction().getGetter();
        getter.getClass();
        return AbstractComposeLowering.irMethodCall$default(this, irExpressionIrCurrentComposer$default, getter, 0, 0, 12, null);
    }

    private final IrExpression irIsStable(IrChangedBitMaskValue changed, int slot) {
        return irEqual(changed.irStableBitAtSlot(slot), irConst(0));
    }

    private final IrExpression irIsTraceInProgress() {
        IrSimpleFunction irSimpleFunctionIsTraceInProgressFunction = isTraceInProgressFunction();
        if (irSimpleFunctionIsTraceInProgressFunction != null) {
            return AbstractComposeLowering.irCall$default(this, irSimpleFunctionIsTraceInProgressFunction, 0, 0, 6, null);
        }
        return null;
    }

    private final IrExpression irIsUncertain(IrChangedBitMaskValue changed, int slot) {
        return irEqual(changed.irIsolateBitsAtSlot(slot, false), irConst(0));
    }

    private final IrExpression irIsUncertainAndStable(IrChangedBitMaskValue changed, int slot) {
        return irEqual(changed.irIsolateBitsAtSlot(slot, true), irConst(0));
    }

    private final IrExpression irJoinKeyChain(List<? extends IrExpression> keyExprs) {
        Iterator<T> it = keyExprs.iterator();
        if (!it.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return null;
        }
        Object next = it.next();
        while (it.hasNext()) {
            IrExpression irExpression = (IrExpression) it.next();
            IrCall irCallIrMethodCall$default = AbstractComposeLowering.irMethodCall$default(this, irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null), getJoinKeyFunction(), 0, 0, 12, null);
            irCallIrMethodCall$default.getArguments().set(1, (IrExpression) next);
            irCallIrMethodCall$default.getArguments().set(2, irExpression);
            next = irCallIrMethodCall$default;
        }
        return (IrExpression) next;
    }

    private final IrExpression irSafeCall(IrExpression target, IrFunctionSymbol symbol, IrExpression... args) {
        IrElement irElementIrTemporary$default = irTemporary$default(this, target, "safe_receiver", (IrType) null, false, false, 28, (Object) null);
        return AbstractComposeLowering.irBlock$default(this, null, IrStatementOrigin.Companion.getSAFE_CALL(), 0, 0, CollectionsKt.listOf(new IrElement[]{irElementIrTemporary$default, AbstractComposeLowering.irIfThenElse$default(this, null, irEqual(irGet(irElementIrTemporary$default), irNull()), irNull(), AbstractComposeLowering.irCall$default(this, symbol, null, null, irGet(irElementIrTemporary$default), null, (IrExpression[]) Arrays.copyOf(args, args.length), 22, null), 0, 0, 49, null)}), 13, null);
    }

    private final IrExpression irShouldExecute(IrExpression parametersChanged, IrExpression flags) {
        IrSimpleFunction shouldExecuteFunction = getShouldExecuteFunction();
        if (shouldExecuteFunction == null) {
            return irOrOr(parametersChanged, irNot(irIsSkipping()));
        }
        IrCall irCallIrMethodCall$default = AbstractComposeLowering.irMethodCall$default(this, irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null), shouldExecuteFunction, 0, 0, 12, null);
        irCallIrMethodCall$default.getArguments().set(1, parametersChanged);
        irCallIrMethodCall$default.getArguments().set(2, flags);
        return irCallIrMethodCall$default;
    }

    private final IrExpression irSkipToGroupEnd(int startOffset, int endOffset) {
        return irMethodCall(irCurrentComposer$default(this, startOffset, endOffset, (IrValueParameter) null, 4, (Object) null), getSkipToGroupEndFunction(), startOffset, endOffset);
    }

    public static /* synthetic */ IrExpression irSkipToGroupEnd$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = composableFunctionBodyTransformer.getCurrentFunctionScope().getFunction().getStartOffset();
        }
        if ((i3 & 2) != 0) {
            i2 = composableFunctionBodyTransformer.getCurrentFunctionScope().getFunction().getStartOffset();
        }
        return composableFunctionBodyTransformer.irSkipToGroupEnd(i, i2);
    }

    private final IrExpression irSourceInformation(Scope.BlockScope scope) {
        IrCall irCallIrCall$default = AbstractComposeLowering.irCall$default(this, getSourceInformationFunction(), 0, 0, 6, null);
        irCallIrCall$default.getArguments().set(0, irCurrentComposer$default(this, scope, 0, 0, 3, (Object) null));
        recordSourceParameter(irCallIrCall$default, 1, scope);
        return irCallIrCall$default;
    }

    private final IrExpression irSourceInformationMarkerEnd(IrElement element, Scope.BlockScope scope) {
        IrCall irCall = irCall(getSourceInformationMarkerEndFunction(), element.getStartOffset(), element.getEndOffset());
        irCall.getArguments().set(0, irCurrentComposer$default(this, scope, 0, 0, 3, (Object) null));
        return irCall;
    }

    private final IrExpression irSourceInformationMarkerStart(IrElement element, Scope.BlockScope scope, IrExpression key) {
        IrCall irCall = irCall(getSourceInformationMarkerStartFunction(), element.getStartOffset(), element.getEndOffset());
        irCall.getArguments().set(0, irCurrentComposer$default(this, scope, 0, 0, 3, (Object) null));
        irCall.getArguments().set(1, key);
        recordSourceParameter(irCall, 2, scope);
        return irCall;
    }

    public static /* synthetic */ IrExpression irSourceInformationMarkerStart$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrElement irElement, Scope.BlockScope blockScope, IrExpression irExpression, int i, Object obj) {
        if ((i & 4) != 0) {
            irExpression = composableFunctionBodyTransformer.irSourceKey(irElement);
        }
        return composableFunctionBodyTransformer.irSourceInformationMarkerStart(irElement, blockScope, irExpression);
    }

    private final IrConst irSourceKey(IrElement irElement) {
        return IrConstImpl.Companion.int(-1, -1, getContext().getIrBuiltIns().getIntType(), sourceKey(irElement));
    }

    private final IrExpression irStartDefaults(IrElement element, Scope.BlockScope scope) {
        return irWithSourceInformation(irMethodCall(irCurrentComposer$default(this, 0, 0, (IrValueParameter) null, 7, (Object) null), getStartDefaultsFunction(), element.getStartOffset(), element.getEndOffset()), scope);
    }

    private final IrExpression irStartMovableGroup(IrElement element, IrExpression joinedData, Scope.BlockScope scope) {
        IrCall irCallIrMethodCall = irMethodCall(irCurrentComposer$default(this, scope, 0, 0, 3, (Object) null), getStartMovableFunction(), element.getStartOffset(), element.getEndOffset());
        irCallIrMethodCall.getArguments().set(1, irSourceKey(element));
        irCallIrMethodCall.getArguments().set(2, joinedData);
        return irWithSourceInformation(irCallIrMethodCall, scope);
    }

    private final IrExpression irStartReplaceGroup(IrElement element, Scope.BlockScope scope, IrExpression key, int startOffset, int endOffset) {
        return irWithSourceInformation(irStartReplaceGroup(irCurrentComposer(scope, startOffset, endOffset), key, startOffset, endOffset), scope);
    }

    public static /* synthetic */ IrExpression irStartReplaceGroup$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrElement irElement, Scope.BlockScope blockScope, IrExpression irExpression, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            irExpression = composableFunctionBodyTransformer.irSourceKey(irElement);
        }
        return composableFunctionBodyTransformer.irStartReplaceGroup(irElement, blockScope, irExpression, (i3 & 8) != 0 ? -1 : i, (i3 & 16) != 0 ? -1 : i2);
    }

    private final IrExpression irStartRestartGroup(IrElement element, Scope.BlockScope scope, IrExpression key) {
        IrValueParameter irValueParameterNearestComposer = nearestComposer();
        IrCall irCallIrMethodCall = irMethodCall(irCurrentComposer$default(this, scope, 0, 0, 3, (Object) null), getStartRestartGroupFunction(), element.getStartOffset(), element.getEndOffset());
        irCallIrMethodCall.getArguments().set(1, key);
        Unit unit = Unit.INSTANCE;
        return irWithSourceInformation(irSet(irValueParameterNearestComposer, irCallIrMethodCall), scope);
    }

    public static /* synthetic */ IrExpression irStartRestartGroup$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrElement irElement, Scope.BlockScope blockScope, IrExpression irExpression, int i, Object obj) {
        if ((i & 4) != 0) {
            irExpression = composableFunctionBodyTransformer.irSourceKey(irElement);
        }
        return composableFunctionBodyTransformer.irStartRestartGroup(irElement, blockScope, irExpression);
    }

    private final IrVariableImpl irTemporary(IrExpression value, String nameHint, IrType irType, boolean isVar, boolean exactName) {
        Scope.FunctionScope currentFunctionScope = getCurrentFunctionScope();
        if (!exactName || nameHint == null) {
            nameHint = currentFunctionScope.getNameForTemporary(nameHint);
        }
        IrVariableImpl irVariableImplIrTemporary$default = AbstractComposeLowering.irTemporary$default(this, value, nameHint, irType, isVar, null, 16, null);
        irVariableImplIrTemporary$default.setParent(getCurrentFunctionScope().getFunction().getParent());
        return irVariableImplIrTemporary$default;
    }

    public static /* synthetic */ IrVariableImpl irTemporary$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrExpression irExpression, String str, IrType irType, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            irType = irExpression.getType();
        }
        return composableFunctionBodyTransformer.irTemporary(irExpression, str2, irType, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2);
    }

    private final IrExpression irTraceEventEnd() {
        IrSimpleFunction traceEventEndFunction = getTraceEventEndFunction();
        if (traceEventEndFunction != null) {
            return irIfTraceInProgress(AbstractComposeLowering.irCall$default(this, traceEventEndFunction, 0, 0, 6, null));
        }
        return null;
    }

    private final IrExpression irTraceEventStart(IrExpression key, Scope.FunctionScope scope) {
        IrExpression irExpressionIrConst;
        IrExpression irExpressionIrConst2;
        IrValueDeclaration irValueDeclaration;
        IrValueDeclaration irValueDeclaration2;
        IrSimpleFunction traceEventStartFunction = getTraceEventStartFunction();
        List<IrValueDeclaration> declarations = null;
        if (traceEventStartFunction == null) {
            return null;
        }
        IrFunction function = scope.getFunction();
        IrBody body = function.getBody();
        body.getClass();
        int startOffset = body.getStartOffset();
        String str = AdditionalIrUtilsKt.getKotlinFqName(function) + " (" + IrDeclarationsKt.getName(IrUtilsKt.getFile(function)) + ':' + IrUtilsKt.getFile(function).getFileEntry().getLineNumber(startOffset) + ')';
        IrChangedBitMaskValue dirty = scope.getDirty();
        IrChangedBitMaskValue changedParameter = scope.getChangedParameter();
        if (dirty != null && dirty.getUsed()) {
            declarations = dirty.getDeclarations();
        } else if (changedParameter != null) {
            declarations = changedParameter.getDeclarations();
        }
        if (declarations == null || (irValueDeclaration2 = (IrValueDeclaration) CollectionsKt.getOrNull(declarations, 0)) == null || (irExpressionIrConst = irGet(irValueDeclaration2)) == null) {
            irExpressionIrConst = irConst(-1);
        }
        IrExpression irExpression = irExpressionIrConst;
        if (declarations == null || (irValueDeclaration = (IrValueDeclaration) CollectionsKt.getOrNull(declarations, 1)) == null || (irExpressionIrConst2 = irGet(irValueDeclaration)) == null) {
            irExpressionIrConst2 = irConst(-1);
        }
        IrExpression irExpression2 = irExpressionIrConst2;
        IrCall irCallIrCall$default = AbstractComposeLowering.irCall$default(this, traceEventStartFunction, 0, 0, 6, null);
        irCallIrCall$default.getArguments().set(0, key);
        irCallIrCall$default.getArguments().set(1, irExpression);
        irCallIrCall$default.getArguments().set(2, irExpression2);
        irCallIrCall$default.getArguments().set(3, irConst(str));
        return irIfTraceInProgress(irCallIrCall$default);
    }

    private final IrExpression irTypeParameterStability(IrTypeParameter param) {
        for (Scope parent = this.currentScope; parent != null; parent = parent.getParent()) {
            if (!(parent instanceof Scope.FunctionScope)) {
                if ((parent instanceof Scope.RootScope) || (parent instanceof Scope.FileScope) || (parent instanceof Scope.ClassScope)) {
                    break;
                }
            } else {
                Scope.FunctionScope functionScope = (Scope.FunctionScope) parent;
                if (functionScope.getIsComposable()) {
                    IrFunction function = functionScope.getFunction();
                    IrChangedBitMaskValue dirty = functionScope.getDirty();
                    if (dirty == null) {
                        dirty = functionScope.getChangedParameter();
                    }
                    if (dirty != null && !function.getTypeParameters().isEmpty()) {
                        for (IrValueParameter irValueParameter : function.getParameters()) {
                            if (Intrinsics.areEqual(IrTypesKt.getClassifierOrNull(irValueParameter.getType()), param.getSymbol())) {
                                int iIndexOf = functionScope.getAllTrackedParams().indexOf(irValueParameter);
                                if (iIndexOf == -1) {
                                    return null;
                                }
                                return irAnd(irConst(StabilityBits.UNSTABLE.bitsForSlot(0)), dirty.irShiftBits(iIndexOf, 0));
                            }
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    private final IrExpression irWithSourceInformation(IrExpression startGroup, Scope.BlockScope scope) {
        return (this.collectSourceInformation && getHasSourceInformation(scope)) ? AbstractComposeLowering.irBlock$default(this, null, null, 0, 0, CollectionsKt.listOf(new IrExpression[]{startGroup, irSourceInformation(scope)}), 15, null) : startGroup;
    }

    private final IrExpression irWithSourceInformationMarker(IrExpression expression, Scope.BlockScope scope, List<? extends IrStatement> before) {
        if (this.collectSourceInformation && getHasSourceInformation(scope)) {
            return wrap(expression, CollectionsKt.plus(before, CollectionsKt.listOf(irSourceInformationMarkerStart$default(this, expression, scope, null, 4, null))), CollectionsKt.listOf(irSourceInformationMarkerEnd(expression, scope)));
        }
        return !before.isEmpty() ? wrap$default(this, expression, before, null, 2, null) : expression;
    }

    private final boolean isGroupBalanced(IrExpression irExpression) {
        if (irExpression instanceof IrWhen) {
            return getEnabled(FeatureFlag.OptimizeNonSkippingGroups);
        }
        return false;
    }

    private final boolean isInComposableScope() {
        return this.currentScope.isInComposable();
    }

    private final IrProperty isSkippingFunction() {
        return (IrProperty) this.isSkippingFunction.value($$delegatedProperties[20].getName());
    }

    private final IrSimpleFunction isTraceInProgressFunction() {
        return (IrSimpleFunction) this.isTraceInProgressFunction.value($$delegatedProperties[13].getName());
    }

    public static IrExpression k(Ref.BooleanRef booleanRef, ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrChangedBitMaskValue irChangedBitMaskValue, IrChangedBitMaskValue irChangedBitMaskValue2, boolean z, List list, List list2) {
        ParamMeta paramRef;
        list.getClass();
        list2.getClass();
        if (!booleanRef.element) {
            int size = list2.size();
            for (int i = 0; i < size; i++) {
                CallArgumentMeta callArgumentMeta = (CallArgumentMeta) list2.get(i);
                ParamMeta paramRef2 = callArgumentMeta.getParamRef();
                if (Intrinsics.areEqual(paramRef2 != null ? paramRef2.getMaskParam() : null, irChangedBitMaskValue) && (paramRef = callArgumentMeta.getParamRef()) != null) {
                    paramRef.setMaskParam(irChangedBitMaskValue2);
                }
            }
        }
        return composableFunctionBodyTransformer.irIntrinsicRememberInvalid(z, list, list2, new ComposableFunctionBodyTransformer$visitComposableLambda$2$2(composableFunctionBodyTransformer));
    }

    public static IrSimpleFunction l(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "endMovableGroup") && irSimpleFunction.getParameters().size() == 1) {
                return irSimpleFunction;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static IrSimpleFunction m(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        Iterator<T> it = composableFunctionBodyTransformer.getTopLevelFunctions(ComposeCallableIds.INSTANCE.getTraceEventEnd()).iterator();
        boolean z = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                break;
            }
            Object next = it.next();
            if (((IrSimpleFunctionSymbol) next).getOwner().getParameters().isEmpty()) {
                if (!z) {
                    z = true;
                    obj = next;
                }
            }
            obj = null;
            break;
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) obj;
        if (irSimpleFunctionSymbol != null) {
            return irSimpleFunctionSymbol.getOwner();
        }
        return null;
    }

    private final IrContainerExpression mutableStatementContainer() {
        return ComposableFunctionBodyTransformerKt.mutableStatementContainer(getContext());
    }

    public static IrSimpleFunction n(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        return composableFunctionBodyTransformer.getTopLevelFunction(ComposeCallableIds.INSTANCE.getSourceInformationMarkerStart()).getOwner();
    }

    private final IrValueParameter nearestComposer() {
        return this.currentScope.getMyComposer();
    }

    public static IrSimpleFunction o(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "startDefaults") && irSimpleFunction.getParameters().size() == 1) {
                return irSimpleFunction;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static IrSimpleFunction p(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        Iterator<T> it = composableFunctionBodyTransformer.getTopLevelFunctions(ComposeCallableIds.INSTANCE.isTraceInProgress()).iterator();
        boolean z = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                break;
            }
            Object next = it.next();
            if (((IrSimpleFunctionSymbol) next).getOwner().getParameters().isEmpty()) {
                if (!z) {
                    z = true;
                    obj = next;
                }
            }
            obj = null;
            break;
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) obj;
        if (irSimpleFunctionSymbol != null) {
            return irSimpleFunctionSymbol.getOwner();
        }
        return null;
    }

    private final void populateArgumentMeta(IrExpression arg, CallArgumentMeta meta) {
        meta.setStability(getStabilityInferencer().stabilityOf(arg, meta.getFileContainingArg()));
        if (isStatic(arg, meta.getFileContainingArg())) {
            meta.setStatic(true);
            return;
        }
        if (!(arg instanceof IrGetValue)) {
            if (!(arg instanceof IrTypeOperatorCall)) {
                if (arg instanceof IrVararg) {
                    meta.setStability(getStabilityInferencer().stabilityOf(((IrVararg) arg).getVarargElementType(), meta.getFileContainingArg()));
                    return;
                }
                return;
            }
            IrTypeOperatorCall irTypeOperatorCall = (IrTypeOperatorCall) arg;
            int i = WhenMappings.$EnumSwitchMapping$0[irTypeOperatorCall.getOperator().ordinal()];
            if (i == 1 || i == 2) {
                populateArgumentMeta(irTypeOperatorCall.getArgument(), meta);
                return;
            }
            return;
        }
        IrVariable owner = ((IrGetValue) arg).getSymbol().getOwner();
        if (owner instanceof IrValueParameter) {
            meta.setParamRef(extractParamMetaFromScopes(owner));
            return;
        }
        if (owner instanceof IrVariable) {
            IrVariable irVariable = owner;
            if (irVariable.isConst()) {
                meta.setStatic(true);
            } else {
                if (irVariable.isVar() || irVariable.getInitializer() == null) {
                    return;
                }
                IrExpression initializer = irVariable.getInitializer();
                initializer.getClass();
                populateArgumentMeta(initializer, meta);
            }
        }
    }

    private final String printScopeStack() {
        StringBuilder sb = new StringBuilder();
        for (Scope parent = this.currentScope; parent != null; parent = parent.getParent()) {
            sb.append(parent.getName());
            sb.append('\n');
        }
        return sb.toString();
    }

    public static IrExpression q(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, boolean z, IrBody irBody, Scope.FunctionScope functionScope) {
        return AbstractComposeLowering.irComposite$default(composableFunctionBodyTransformer, null, null, CollectionsKt.listOfNotNull(new IrExpression[]{z ? composableFunctionBodyTransformer.irTraceEventEnd() : null, composableFunctionBodyTransformer.collectSourceInformation ? composableFunctionBodyTransformer.irSourceInformationMarkerEnd(irBody, functionScope) : null}), 3, null);
    }

    public static IrSimpleFunction r(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irSimpleFunction.getName(), ComposeNames.INSTANCE.getStartRestartGroup()) && irSimpleFunction.getParameters().size() == 2) {
                return irSimpleFunction;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    private final void recordCallInSource(IrElement call) {
        Scope.SourceLocation sourceLocationRecordSourceLocation = null;
        for (Scope parent = this.currentScope; parent != null; parent = parent.getParent()) {
            if (parent instanceof Scope.FunctionScope) {
                sourceLocationRecordSourceLocation = ((Scope.FunctionScope) parent).recordSourceLocation(call, sourceLocationRecordSourceLocation);
            } else if (parent instanceof Scope.BlockScope) {
                sourceLocationRecordSourceLocation = ((Scope.BlockScope) parent).recordSourceLocation(call, sourceLocationRecordSourceLocation);
            } else if (parent instanceof Scope.ClassScope) {
                return;
            }
        }
    }

    private final void recordSourceParameter(IrCall call, int index, Scope.BlockScope scope) {
        this.sourceFixups.add(new SourceInfoFixup(call, index, scope));
    }

    public static IrExpression s(boolean z, ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrContainerExpression irContainerExpression, Scope.FunctionScope functionScope) {
        return z ? irStartReplaceGroup$default(composableFunctionBodyTransformer, irContainerExpression, functionScope, null, irContainerExpression.getStartOffset(), irContainerExpression.getStartOffset(), 4, null) : irSourceInformationMarkerStart$default(composableFunctionBodyTransformer, irContainerExpression, functionScope, null, 4, null);
    }

    private final int sourceKey(IrElement irElement) {
        int iFunctionSourceKey = (((functionSourceKey(getCurrentFunctionScope().getFunction()) * 31) + (irElement.getStartOffset() - getCurrentFunctionScope().getFunction().getStartOffset())) * 31) + (irElement.getEndOffset() - getCurrentFunctionScope().getFunction().getStartOffset());
        if (irElement instanceof IrConst) {
            int i = iFunctionSourceKey * 31;
            Object value = ((IrConst) irElement).getValue();
            return i + (value != null ? value.hashCode() : 1);
        }
        if (irElement instanceof IrBlock) {
            return (iFunctionSourceKey * 31) + 2;
        }
        return irElement instanceof IrComposite ? (iFunctionSourceKey * 31) + 3 : iFunctionSourceKey;
    }

    private final Scope.ParametersScope transformDefaults(Scope.FunctionScope scope) {
        List<IrValueParameter> allTrackedParams = scope.getAllTrackedParams();
        Scope.ParametersScope parametersScope = new Scope.ParametersScope();
        int size = allTrackedParams.size();
        for (int i = 0; i < size; i++) {
            IrExpressionBody defaultValue = allTrackedParams.get(i).getDefaultValue();
            if (defaultValue != null) {
                Scope scope2 = this.currentScope;
                this.currentScope = parametersScope;
                parametersScope.setParent(scope2);
                parametersScope.setLevel(scope2.getLevel() + 1);
                try {
                    IrExpression irExpressionTransform = defaultValue.getExpression().transform(this, (Object) null);
                    this.currentScope = scope2;
                    defaultValue.setExpression(irExpressionTransform);
                } catch (Throwable th) {
                    this.currentScope = scope2;
                    throw th;
                }
            }
        }
        return parametersScope;
    }

    private final <T extends Scope> Pair<T, IrExpression> transformWithScope(IrExpression irExpression, T t) {
        Scope scope = this.currentScope;
        try {
            this.currentScope = t;
            t.setParent(scope);
            t.setLevel(scope.getLevel() + 1);
            return TuplesKt.to(t, irExpression.transform(this, (Object) null));
        } finally {
            this.currentScope = scope;
        }
    }

    public static IrSimpleFunction u(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "skipToGroupEnd") && irSimpleFunction.getParameters().size() == 1) {
                return irSimpleFunction;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static IrExpression v(boolean z, IrContainerExpression irContainerExpression, ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Scope.FunctionScope functionScope) {
        return z ? composableFunctionBodyTransformer.irEndReplaceGroup(irContainerExpression.getEndOffset(), irContainerExpression.getEndOffset(), functionScope) : composableFunctionBodyTransformer.irSourceInformationMarkerEnd(irContainerExpression, functionScope);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final IrBlockImpl variablePrefix(IrExpression irExpression, IrVariable irVariable) {
        return org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrBlockImpl(irExpression.getStartOffset(), irExpression.getEndOffset(), irExpression.getType(), (IrStatementOrigin) null, CollectionsKt.listOf(new IrElement[]{irVariable, irExpression}));
    }

    private final IrExpression visitComposableCall(IrCall expression) {
        FqName kotlinFqName = AdditionalIrUtilsKt.getKotlinFqName(expression.getSymbol().getOwner());
        ComposeFqNames composeFqNames = ComposeFqNames.INSTANCE;
        if (Intrinsics.areEqual(kotlinFqName, composeFqNames.getRemember())) {
            return getEnabled(FeatureFlag.IntrinsicRemember) ? visitRememberCall(expression) : visitNormalComposableCall(expression);
        }
        return Intrinsics.areEqual(kotlinFqName, composeFqNames.getKey()) ? visitKeyCall(expression) : visitNormalComposableCall(expression);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0092  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:35:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:45:0x00cf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x00b8 A[SYNTHETIC] */
    private final IrStatement visitComposableFunctionStub(IrFunction declaration) {
        Iterator it;
        Object next;
        IrValueParameter irValueParameter;
        List parameters = declaration.getParameters();
        int size = parameters.size();
        for (int i = 0; i < size; i++) {
            ((IrValueParameter) parameters.get(i)).setDefaultValue((IrExpressionBody) null);
        }
        IrBody body = declaration.getBody();
        if (body == null) {
            k2d.a("Expected body for composable function stub");
            return null;
        }
        Object obj = IrUtilsKt.getStatements(body).get(0);
        IrReturn irReturn = obj instanceof IrReturn ? (IrReturn) obj : null;
        IrExpression value = irReturn != null ? irReturn.getValue() : null;
        IrCall irCall = value instanceof IrCall ? (IrCall) value : null;
        if (irCall == null) {
            k2d.a("Expected a single return statement with a call");
            return null;
        }
        List parameters2 = irCall.getSymbol().getOwner().getParameters();
        int size2 = parameters2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            IrValueParameter irValueParameter2 = (IrValueParameter) parameters2.get(i2);
            String strAsString = irValueParameter2.getName().asString();
            strAsString.getClass();
            ComposeNames composeNames = ComposeNames.INSTANCE;
            String strAsString2 = composeNames.getChangedParameter().asString();
            strAsString2.getClass();
            if (StringsKt.startsWith$default(strAsString, strAsString2, false, 2, (Object) null)) {
                it = declaration.getParameters().iterator();
                do {
                    if (it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((IrValueParameter) next).getName(), irValueParameter2.getName()));
                irValueParameter = (IrValueParameter) next;
                if (irValueParameter != null) {
                    f2f.a("Expected parameter for ", irValueParameter2.getName());
                    return null;
                }
                irCall.getArguments().set(irValueParameter2.getIndexInParameters(), irGet(irValueParameter));
            } else {
                String strAsString3 = composeNames.getDefaultParameter().asString();
                strAsString3.getClass();
                if (StringsKt.startsWith$default(strAsString, strAsString3, false, 2, (Object) null)) {
                    it = declaration.getParameters().iterator();
                    do {
                        if (it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Intrinsics.areEqual(((IrValueParameter) next).getName(), irValueParameter2.getName()));
                    irValueParameter = (IrValueParameter) next;
                    if (irValueParameter != null) {
                        f2f.a("Expected parameter for ", irValueParameter2.getName());
                        return null;
                    }
                    irCall.getArguments().set(irValueParameter2.getIndexInParameters(), irGet(irValueParameter));
                } else {
                    continue;
                }
            }
        }
        return declaration;
    }

    private final IrFunction visitComposableLambda(IrFunction declaration, Scope.FunctionScope scope, final IrChangedBitMaskValue changedParam) {
        boolean z;
        final IrChangedBitMaskValue irChangedBitMaskValueIrCopyToTemporary;
        IrContainerExpression irContainerExpression;
        IrChangedBitMaskValue irChangedBitMaskValue;
        IrBody body = declaration.getBody();
        body.getClass();
        IrContainerExpression irContainerExpressionMutableStatementContainer = mutableStatementContainer();
        IrContainerExpression irContainerExpressionMutableStatementContainer2 = mutableStatementContainer();
        IrContainerExpression irContainerExpressionMutableStatementContainer3 = mutableStatementContainer();
        IrContainerExpression irContainerExpressionMutableStatementContainer4 = mutableStatementContainer();
        boolean zIsInlinedLambda = scope.isInlinedLambda();
        if (this.collectSourceInformation && !zIsInlinedLambda) {
            irContainerExpressionMutableStatementContainer.getStatements().add(irSourceInformation(scope));
        }
        IrFile fileOrNull = IrUtilsKt.getFileOrNull(declaration);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        if (!IrTypePredicatesKt.isUnit(declaration.getReturnType()) || zIsInlinedLambda) {
            z = false;
        } else {
            List<IrValueParameter> allTrackedParams = scope.getAllTrackedParams();
            if (!(allTrackedParams instanceof Collection) || !allTrackedParams.isEmpty()) {
                Iterator<T> it = allTrackedParams.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (StabilityKt.knownUnstable(getStabilityInferencer().stabilityOf(((IrValueParameter) it.next()).getType(), fileOrNull))) {
                            z = false;
                        }
                    }
                }
            }
            z = true;
        }
        booleanRef.element = z;
        if (!z || scope.getAllTrackedParams().isEmpty()) {
            irChangedBitMaskValueIrCopyToTemporary = changedParam;
        } else {
            irChangedBitMaskValueIrCopyToTemporary = changedParam.irCopyToTemporary("$dirty", (JvmPlatformKt.isJvm(getContext().getPlatform()) || JsPlatformKt.isJs(getContext().getPlatform())) ? false : true, true);
        }
        scope.setDirty(irChangedBitMaskValueIrCopyToTemporary);
        Pair<IrContainerExpression, IrVariable> pairAsBodyAndResultVar = asBodyAndResultVar(body, declaration);
        IrContainerExpression irContainerExpressionAsSourceOrEarlyExitGroup = (IrContainerExpression) pairAsBodyAndResultVar.component1();
        IrVariable irVariable = (IrVariable) pairAsBodyAndResultVar.component2();
        boolean z2 = getTraceEventMarkersEnabled() && !scope.isInlinedLambda();
        transformChildrenVoid(irContainerExpressionAsSourceOrEarlyExitGroup);
        if (scope.isInlinedLambda() && scope.getIsComposable()) {
            scope.realizeAllDirectChildren();
        }
        if (zIsInlinedLambda) {
            irContainerExpressionAsSourceOrEarlyExitGroup = asSourceOrEarlyExitGroup(irContainerExpressionAsSourceOrEarlyExitGroup, scope);
        }
        IrContainerExpression irContainerExpression2 = irContainerExpressionAsSourceOrEarlyExitGroup;
        booleanRef.element = buildPreambleStatementsAndReturnIfSkippingPossible(body, irContainerExpressionMutableStatementContainer2, irContainerExpressionMutableStatementContainer3, booleanRef.element, scope, irChangedBitMaskValueIrCopyToTemporary, changedParam, null, new Scope.ParametersScope());
        if (z2) {
            irContainerExpression = irContainerExpression2;
            wrapWithTraceEvents(irContainerExpression, irFunctionSourceKey$default(this, null, 1, null), scope);
        } else {
            irContainerExpression = irContainerExpression2;
        }
        if (irChangedBitMaskValueIrCopyToTemporary.getUsed() && (irChangedBitMaskValueIrCopyToTemporary instanceof IrChangedBitMaskVariable)) {
            irContainerExpressionMutableStatementContainer2.getStatements().addAll(0, ((IrChangedBitMaskVariable) irChangedBitMaskValueIrCopyToTemporary).asStatements());
            irChangedBitMaskValue = irChangedBitMaskValueIrCopyToTemporary;
        } else {
            irChangedBitMaskValue = changedParam;
        }
        if (z2) {
            scope.realizeEndCalls(new Function0() { // from class: pc2
                public final Object invoke() {
                    return ComposableFunctionBodyTransformer.F(this.b);
                }
            });
        }
        scope.applyIntrinsicRememberFixups(new Function3() { // from class: qc2
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ComposableFunctionBodyTransformer.k(booleanRef, this, irChangedBitMaskValueIrCopyToTemporary, changedParam, ((Boolean) obj).booleanValue(), (List) obj2, (List) obj3);
            }
        });
        if (booleanRef.element) {
            IrWhenImpl irWhenImplIrIfThenElse$default = AbstractComposeLowering.irIfThenElse$default(this, null, irShouldExecute(irChangedBitMaskValue.irHasDifferences(scope.getUsedParams()), irChangedBitMaskValue.irRestartFlags()), AbstractComposeLowering.irBlock$default(this, getContext().getIrBuiltIns().getUnitType(), null, 0, 0, irContainerExpression.getStatements(), 14, null), irSkipToGroupEnd$default(this, 0, 0, 3, null), 0, 0, 49, null);
            scope.realizeCoalescableGroup();
            IrBlockBody irBlockBodyCreateBlockBody = getContext().getIrFactory().createBlockBody(body.getStartOffset(), body.getEndOffset());
            List statements = irBlockBodyCreateBlockBody.getStatements();
            SpreadBuilder spreadBuilder = new SpreadBuilder(6);
            spreadBuilder.addSpread(irContainerExpressionMutableStatementContainer.getStatements().toArray(new IrStatement[0]));
            spreadBuilder.addSpread(scope.getMarkerPreamble().getStatements().toArray(new IrStatement[0]));
            spreadBuilder.addSpread(irContainerExpressionMutableStatementContainer2.getStatements().toArray(new IrStatement[0]));
            spreadBuilder.addSpread(irContainerExpressionMutableStatementContainer3.getStatements().toArray(new IrStatement[0]));
            spreadBuilder.add(irWhenImplIrIfThenElse$default);
            spreadBuilder.add(irVariable != null ? irReturnVar(declaration.getSymbol(), irVariable) : null);
            statements.addAll(CollectionsKt.listOfNotNull(spreadBuilder.toArray(new IrStatement[spreadBuilder.size()])));
            declaration.setBody(irBlockBodyCreateBlockBody);
        } else {
            scope.realizeCoalescableGroup();
            IrBlockBody irBlockBodyCreateBlockBody2 = getContext().getIrFactory().createBlockBody(body.getStartOffset(), body.getEndOffset());
            List statements2 = irBlockBodyCreateBlockBody2.getStatements();
            SpreadBuilder spreadBuilder2 = new SpreadBuilder(7);
            spreadBuilder2.addSpread(scope.getMarkerPreamble().getStatements().toArray(new IrStatement[0]));
            spreadBuilder2.addSpread(irContainerExpressionMutableStatementContainer.getStatements().toArray(new IrStatement[0]));
            spreadBuilder2.addSpread(irContainerExpressionMutableStatementContainer2.getStatements().toArray(new IrStatement[0]));
            spreadBuilder2.addSpread(irContainerExpressionMutableStatementContainer3.getStatements().toArray(new IrStatement[0]));
            spreadBuilder2.add(irContainerExpression);
            spreadBuilder2.addSpread(irContainerExpressionMutableStatementContainer4.getStatements().toArray(new IrStatement[0]));
            spreadBuilder2.add(irVariable != null ? irReturnVar(declaration.getSymbol(), irVariable) : null);
            statements2.addAll(CollectionsKt.listOfNotNull(spreadBuilder2.toArray(new IrStatement[spreadBuilder2.size()])));
            declaration.setBody(irBlockBodyCreateBlockBody2);
        }
        scope.getMetrics().recordFunction(true, true, booleanRef.element, true, false, false, false);
        scope.getMetrics().recordGroup();
        return declaration;
    }

    private final IrStatement visitComposableReferenceAdapter(IrFunction declaration, Scope.FunctionScope scope) {
        scope.setDirty(scope.getChangedParameter());
        scope.setPreserveIrShape(true);
        transformChildrenVoid(declaration);
        return declaration;
    }

    private final IrStatement visitFunctionInScope(IrFunction declaration) {
        Scope.FunctionScope currentFunctionScope = getCurrentFunctionScope();
        if (!currentFunctionScope.getIsComposable()) {
            return (currentFunctionScope.isInlinedLambda() && currentFunctionScope.isInComposable()) ? visitInlinedLambdaInComposableScope(declaration) : super.visitFunction(declaration);
        }
        if (ComposePluginAttributesKt.isDefaultParamStub(declaration)) {
            return visitComposableFunctionStub(declaration);
        }
        if (Intrinsics.areEqual(declaration.getOrigin(), IrDeclarationOrigin.Companion.getADAPTER_FOR_CALLABLE_REFERENCE())) {
            return visitComposableReferenceAdapter(declaration, currentFunctionScope);
        }
        boolean z = shouldBeRestartable(declaration) && !this.inlineLambdaInfo.isInlineLambda(declaration);
        boolean zIsLambda = ComposableFunctionBodyTransformerKt.isLambda(declaration);
        boolean zIsUnit = IrTypePredicatesKt.isUnit(declaration.getReturnType());
        if (declaration.getBody() == null) {
            if ((declaration instanceof IrSimpleFunction) && ((IrSimpleFunction) declaration).getModality() == Modality.ABSTRACT) {
                currentFunctionScope.getMetrics().recordFunction(true, z, false, false, false, false, false);
                IrFile fileOrNull = IrUtilsKt.getFileOrNull(declaration);
                for (IrValueParameter irValueParameter : currentFunctionScope.getAllTrackedParams()) {
                    StabilityInferencer stabilityInferencer = getStabilityInferencer();
                    IrType varargElementType = irValueParameter.getVarargElementType();
                    if (varargElementType == null) {
                        varargElementType = irValueParameter.getType();
                    }
                    Stability stabilityStabilityOf = stabilityInferencer.stabilityOf(varargElementType, fileOrNull);
                    IrExpressionBody defaultValue = irValueParameter.getDefaultValue();
                    IrExpression expression = defaultValue != null ? defaultValue.getExpression() : null;
                    currentFunctionScope.getMetrics().recordParameter(irValueParameter, irValueParameter.getType(), stabilityStabilityOf, expression, expression != null && isStatic(expression, fileOrNull), true);
                }
            }
            return declaration;
        }
        IrChangedBitMaskValue changedParameter = currentFunctionScope.getChangedParameter();
        changedParameter.getClass();
        IrDefaultBitMaskValue defaultParameter = currentFunctionScope.getDefaultParameter();
        IrFunction irFunctionVisitComposableLambda = (zIsLambda && zIsUnit) ? visitComposableLambda(declaration, currentFunctionScope, changedParameter) : (z && zIsUnit) ? visitRestartableComposableFunction(declaration, currentFunctionScope, changedParameter, defaultParameter) : visitNonRestartableComposableFunction(declaration, currentFunctionScope, changedParameter, defaultParameter);
        List parameters = irFunctionVisitComposableLambda.getParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : parameters) {
            if (((IrValueParameter) obj).isAssignable()) {
                arrayList.add(obj);
            }
        }
        final Set set = CollectionsKt.toSet(arrayList);
        if (!set.isEmpty()) {
            irFunctionVisitComposableLambda.transform(new IrElementTransformerVoid() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer$visitFunctionInScope$2$1
                public IrExpression visitGetValue(IrGetValue expression2) {
                    expression2.getClass();
                    if (!CollectionsKt.contains(set, expression2.getSymbol().getOwner())) {
                        return super.visitGetValue(expression2);
                    }
                    IrType irTypeDefaultParameterType = this.defaultParameterType(expression2.getType());
                    return !Intrinsics.areEqual(irTypeDefaultParameterType, expression2.getType()) ? org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrTypeOperatorCallImpl(expression2.getStartOffset(), expression2.getEndOffset(), expression2.getType(), IrTypeOperator.IMPLICIT_CAST, expression2.getType(), org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrGetValueImpl(expression2.getStartOffset(), expression2.getEndOffset(), irTypeDefaultParameterType, expression2.getSymbol(), expression2.getOrigin())) : super.visitGetValue(expression2);
                }
            }, (Object) null);
        }
        return irFunctionVisitComposableLambda;
    }

    private final IrStatement visitInlinedLambdaInComposableScope(final IrFunction declaration) {
        final Scope.FunctionScope currentFunctionScope = getCurrentFunctionScope();
        Scope parent = currentFunctionScope.getParent();
        if (!(parent instanceof Scope.CaptureScope) || !((Scope.CaptureScope) parent).getForceInlinedLambdaGroup()) {
            IrStatement irStatementVisitFunction = super.visitFunction(declaration);
            if (currentFunctionScope.getHasComposableCalls()) {
                encounteredCapturedComposableCall();
            }
            return irStatementVisitFunction;
        }
        IrBody body = declaration.getBody();
        if (body == null) {
            return super.visitFunction(declaration);
        }
        Pair pairAsBodyAndResultVar$default = asBodyAndResultVar$default(this, body, null, 1, null);
        final IrContainerExpression irContainerExpression = (IrContainerExpression) pairAsBodyAndResultVar$default.component1();
        final IrVariable irVariable = (IrVariable) pairAsBodyAndResultVar$default.component2();
        transformChildrenVoid(irContainerExpression);
        if (!currentFunctionScope.getHasComposableCalls()) {
            return super.visitFunction(declaration);
        }
        currentFunctionScope.realizeGroup(new Function0() { // from class: zc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.M(irContainerExpression, this, currentFunctionScope);
            }
        });
        declaration.setBody(IrFactoryHelpersKt.createBlockBody(getContext().getIrFactory(), irContainerExpression.getStartOffset(), irContainerExpression.getEndOffset(), new Function1() { // from class: ad2
            public final Object invoke(Object obj) {
                return ComposableFunctionBodyTransformer.O(this.b, irContainerExpression, currentFunctionScope, irVariable, declaration, (IrBlockBody) obj);
            }
        }));
        return declaration;
    }

    private final IrExpression visitKeyCall(IrCall expression) {
        encounteredComposableCall(true);
        ArrayList arrayList = new ArrayList();
        int size = expression.getArguments().size();
        IrVararg irVararg = null;
        for (int i = 0; i < size; i++) {
            IrValueParameter irValueParameter = (IrValueParameter) expression.getSymbol().getOwner().getParameters().get(i);
            IrVararg irVararg2 = (IrExpression) expression.getArguments().get(i);
            if (irVararg2 == null) {
                k2d.a("Unexpected null argument found on key call");
                return null;
            }
            String strAsString = irValueParameter.getName().asString();
            strAsString.getClass();
            if (StringsKt.startsWith$default(strAsString, '$', false, 2, (Object) null)) {
                break;
            }
            if (Intrinsics.areEqual(irValueParameter.getName().getIdentifier(), "block")) {
                irVararg = irVararg2;
            } else if (irVararg2 instanceof IrVararg) {
                List<IrExpression> elements = irVararg2.getElements();
                ArrayList arrayList2 = new ArrayList();
                for (IrExpression irExpression : elements) {
                    IrExpression irExpression2 = irExpression instanceof IrExpression ? irExpression : null;
                    if (irExpression2 != null) {
                        arrayList2.add(irExpression2);
                    }
                }
                arrayList.addAll(arrayList2);
            } else {
                arrayList.add(irVararg2);
            }
        }
        IrContainerExpression irContainerExpressionMutableStatementContainer = mutableStatementContainer();
        IrContainerExpression irContainerExpressionMutableStatementContainer2 = mutableStatementContainer();
        if (!(irVararg instanceof IrFunctionExpression)) {
            j37.a(new StringBuilder("Expected function expression but was "), irVararg != null ? Reflection.getOrCreateKotlinClass(irVararg.getClass()) : null);
            return null;
        }
        IrFunctionExpression irFunctionExpression = (IrFunctionExpression) irVararg;
        IrBody body = irFunctionExpression.getFunction().getBody();
        body.getClass();
        Pair<IrContainerExpression, IrVariable> pairAsBodyAndResultVar = asBodyAndResultVar(body, irFunctionExpression.getFunction());
        IrExpression irExpression3 = (IrContainerExpression) pairAsBodyAndResultVar.component1();
        IrValueDeclaration irValueDeclaration = (IrVariable) pairAsBodyAndResultVar.component2();
        final Scope.KeyScope keyScope = new Scope.KeyScope();
        Scope scope = this.currentScope;
        this.currentScope = keyScope;
        keyScope.setParent(scope);
        keyScope.setLevel(scope.getLevel() + 1);
        try {
            irExpression3.transform(this, (Object) null);
            this.currentScope = scope;
            IrElementTransformerVoidKt.transformChildrenVoid(irExpression3, new IrElementTransformerVoid() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.visitKeyCall.3
                public IrStatement visitFunction(IrFunction declaration) {
                    declaration.getClass();
                    return ComposableFunctionBodyTransformer.this.inlineLambdaInfo.isInlineLambda(declaration) ? super.visitFunction(declaration) : declaration;
                }

                public IrExpression visitGetValue(IrGetValue expression2) {
                    expression2.getClass();
                    super.visitGetValue(expression2);
                    IrValueDeclaration owner = expression2.getSymbol().getOwner();
                    return ((owner instanceof IrValueParameter) && Intrinsics.areEqual(owner.getName(), ComposeNames.INSTANCE.getComposerParameter())) ? ComposableFunctionBodyTransformer.irCurrentComposer$default(ComposableFunctionBodyTransformer.this, 0, 0, (IrValueParameter) null, 7, (Object) null) : expression2;
                }
            });
            if (keyScope.getHasComposableCalls()) {
                keyScope.realizeAllDirectChildren();
                keyScope.realizeCoalescableGroup();
            }
            keyScope.realizeEndCalls(new Function0() { // from class: fd2
                public final Object invoke() {
                    return ComposableFunctionBodyTransformer.E(this.b, keyScope);
                }
            });
            IrType type = expression.getType();
            List<? extends IrExpression> arrayList3 = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList3.add(((IrExpression) it.next()).transform(this, (Object) null));
            }
            return AbstractComposeLowering.irBlock$default(this, type, null, 0, 0, CollectionsKt.listOfNotNull(new IrExpression[]{irContainerExpressionMutableStatementContainer, irStartMovableGroup(expression, irJoinKeyChain(arrayList3), keyScope), irExpression3, irEndMovableGroup(keyScope), irContainerExpressionMutableStatementContainer2, irValueDeclaration != null ? irGet(irValueDeclaration) : null}), 14, null);
        } catch (Throwable th) {
            this.currentScope = scope;
            throw th;
        }
    }

    private final IrFunction visitNonRestartableComposableFunction(IrFunction declaration, final Scope.FunctionScope scope, IrChangedBitMaskValue changedParam, IrDefaultBitMaskValue defaultParam) throws Throwable {
        IrExpression irExpressionIrSourceInformationMarkerStart;
        IrExpression irExpressionIrSourceInformationMarkerEnd;
        final IrBody body = declaration.getBody();
        body.getClass();
        boolean hasExplicitGroups = getHasExplicitGroups(declaration);
        boolean z = getHasReadOnlyAnnotation(declaration) || isComposableDelegatedAccessor(declaration);
        boolean z2 = !(z || hasExplicitGroups || getUseNonSkippingGroupOptimization()) || ComposableFunctionBodyTransformerKt.isLambda(declaration) || IrUtilsKt.isOverridableOrOverrides(declaration) || ComposePluginAttributesKt.isComposableReferenceAdapter(declaration);
        IrContainerExpression irContainerExpressionMutableStatementContainer = mutableStatementContainer();
        IrContainerExpression irContainerExpressionMutableStatementContainer2 = mutableStatementContainer();
        scope.setDirty(changedParam);
        scope.setOuterGroupRequired(z2);
        Scope.ParametersScope parametersScopeTransformDefaults = transformDefaults(scope);
        Pair pairAsBodyAndResultVar$default = asBodyAndResultVar$default(this, body, null, 1, null);
        Object objComponent1 = pairAsBodyAndResultVar$default.component1();
        Object objComponent2 = pairAsBodyAndResultVar$default.component2();
        final boolean z3 = (!getTraceEventMarkersEnabled() || scope.getFunction().isInline() || ComposePluginAttributesKt.isComposableReferenceAdapter(declaration)) ? false : true;
        IrContainerExpression irContainerExpression = (IrContainerExpression) objComponent1;
        transformChildrenVoid(irContainerExpression);
        boolean z4 = (z || hasExplicitGroups || !scope.getHasAnyEarlyReturn()) ? z2 : true;
        buildPreambleStatementsAndReturnIfSkippingPossible(body, irContainerExpressionMutableStatementContainer, irContainerExpressionMutableStatementContainer2, false, scope, changedParam, changedParam, defaultParam, parametersScopeTransformDefaults);
        if (z3) {
            wrapWithTraceEvents(irContainerExpression, irFunctionSourceKey$default(this, null, 1, null), scope);
        }
        if (z4) {
            scope.realizeGroup(new Function0() { // from class: xc2
                public final Object invoke() {
                    return ComposableFunctionBodyTransformer.C(this.b, z3, body, scope);
                }
            });
        } else if (getUseNonSkippingGroupOptimization()) {
            scope.realizeAllDirectChildren();
            scope.realizeCoalescableGroup();
        }
        IrBlockBody irBlockBodyCreateBlockBody = getContext().getIrFactory().createBlockBody(body.getStartOffset(), body.getEndOffset());
        List statements = irBlockBodyCreateBlockBody.getStatements();
        SpreadBuilder spreadBuilder = new SpreadBuilder(6);
        if (z4) {
            irExpressionIrSourceInformationMarkerStart = irStartReplaceGroup(body, scope, irFunctionSourceKey$default(this, null, 1, null), body.getStartOffset(), body.getStartOffset());
        } else {
            irExpressionIrSourceInformationMarkerStart = this.collectSourceInformation ? irSourceInformationMarkerStart(body, scope, irFunctionSourceKey$default(this, null, 1, null)) : null;
        }
        spreadBuilder.add(irExpressionIrSourceInformationMarkerStart);
        spreadBuilder.addSpread(scope.getMarkerPreamble().getStatements().toArray(new IrStatement[0]));
        spreadBuilder.addSpread(irContainerExpressionMutableStatementContainer2.getStatements().toArray(new IrStatement[0]));
        spreadBuilder.addSpread(irContainerExpression.getStatements().toArray(new IrStatement[0]));
        if (z4) {
            irExpressionIrSourceInformationMarkerEnd = irEndReplaceGroup(body.getEndOffset(), body.getEndOffset(), scope);
        } else {
            irExpressionIrSourceInformationMarkerEnd = this.collectSourceInformation ? irSourceInformationMarkerEnd(body, scope) : null;
        }
        spreadBuilder.add(irExpressionIrSourceInformationMarkerEnd);
        IrVariable irVariable = (IrVariable) objComponent2;
        spreadBuilder.add(irVariable != null ? irReturnVar(declaration.getSymbol(), irVariable) : null);
        statements.addAll(CollectionsKt.listOfNotNull(spreadBuilder.toArray(new IrStatement[spreadBuilder.size()])));
        declaration.setBody(irBlockBodyCreateBlockBody);
        if (!z4) {
            scope.realizeEndCalls(new Function0() { // from class: yc2
                public final Object invoke() {
                    return ComposableFunctionBodyTransformer.q(this.b, z3, body, scope);
                }
            });
        }
        scope.getMetrics().recordFunction(true, false, false, ComposableFunctionBodyTransformerKt.isLambda(declaration), declaration.isInline(), false, z);
        scope.getMetrics().recordGroup();
        return declaration;
    }

    private final IrExpression visitNormalComposableCall(IrCall expression) {
        boolean z;
        int i;
        int i2;
        int iChangedParamCountFromTotal;
        IrBlockImpl irBlockImplVariablePrefix;
        Scope.CallScope callScope = new Scope.CallScope(expression, this);
        Scope scope = this.currentScope;
        this.currentScope = callScope;
        callScope.setParent(scope);
        callScope.setLevel(scope.getLevel() + 1);
        try {
            transformChildrenVoid(expression);
            Unit unit = Unit.INSTANCE;
            this.currentScope = scope;
            encounteredComposableCall(!getHasReadOnlyAnnotation(expression.getSymbol().getOwner()));
            IrSimpleFunction owner = expression.getSymbol().getOwner();
            int size = owner.getParameters().size();
            List parameters = owner.getParameters();
            if (!(parameters instanceof Collection) || !parameters.isEmpty()) {
                Iterator it = parameters.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    IrValueParameter irValueParameter = (IrValueParameter) it.next();
                    if (irValueParameter.getKind() == IrParameterKind.Regular && Intrinsics.areEqual(irValueParameter.getName(), ComposeNames.INSTANCE.getDefaultParameter())) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
            }
            DumpIrTreeOptions dumpIrTreeOptions = null;
            if (z || !isInvoke(expression)) {
                Iterator it2 = owner.getParameters().iterator();
                int i3 = 0;
                while (true) {
                    if (!it2.hasNext()) {
                        i3 = -1;
                        break;
                    }
                    IrValueParameter irValueParameter2 = (IrValueParameter) it2.next();
                    if (irValueParameter2.getKind() == IrParameterKind.Regular && Intrinsics.areEqual(irValueParameter2.getName(), ComposeNames.INSTANCE.getComposerParameter())) {
                        break;
                    }
                    i3++;
                }
                if (i3 < 0) {
                    dt1.a("Expected a $composer parameter in ", RenderIrElementKt.render$default(owner, (DumpIrTreeOptions) null, 1, (Object) null));
                    return null;
                }
                int iChangedParamCount = ComposableFunctionBodyTransformerKt.changedParamCount(i3, 0);
                int iDefaultParamCount = z ? ComposableFunctionBodyTransformerKt.defaultParamCount(i3 - ComposableFunctionBodyTransformerKt.getThisParamCount(owner)) : 0;
                i = ((size - 1) - iChangedParamCount) - iDefaultParamCount;
                i2 = iDefaultParamCount;
                iChangedParamCountFromTotal = iChangedParamCount;
            } else {
                iChangedParamCountFromTotal = ComposableFunctionBodyTransformerKt.changedParamCountFromTotal(size);
                i = (size - 1) - iChangedParamCountFromTotal;
                i2 = 0;
            }
            int i4 = i + 1;
            int i5 = iChangedParamCountFromTotal + i4;
            int i6 = i2 + i5;
            if (size != i6) {
                uhe.a("Expected ", i6, " params for ", RenderIrElementKt.render$default(owner, (DumpIrTreeOptions) null, 1, (Object) null), ", but got ", size);
                return null;
            }
            IntRange intRangeUntil = RangesKt.until(i5, size);
            ArrayList<IrConst> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
            IntIterator it3 = intRangeUntil.iterator();
            while (it3.hasNext()) {
                arrayList.add((IrExpression) expression.getArguments().get(it3.nextInt()));
            }
            boolean zIsEmpty = arrayList.isEmpty();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            for (IrConst irConst : arrayList) {
                if (!(irConst instanceof IrConst)) {
                    k2d.a("Expected default mask to be a const");
                    return null;
                }
                Object value = irConst.getValue();
                Integer num = value instanceof Integer ? (Integer) value : null;
                if (num == null) {
                    k2d.a("Expected default mask to be an Int");
                    return null;
                }
                arrayList2.add(num);
            }
            Scope.FileScope fileScope = this.currentScope.getFileScope();
            IrFile declaration = fileScope != null ? fileScope.getDeclaration() : null;
            ArrayList arrayList3 = new ArrayList();
            int i7 = 0;
            int i8 = 0;
            CallArgumentMeta callArgumentMetaArgumentMetaOf = null;
            while (i7 < i) {
                IrExpression irExpression = (IrExpression) expression.getArguments().get(i7);
                IrValueParameter irValueParameter3 = (IrValueParameter) owner.getParameters().get(i7);
                if (irExpression != null) {
                    int i9 = WhenMappings.$EnumSwitchMapping$1[irValueParameter3.getKind().ordinal()];
                    if (i9 == 1) {
                        callArgumentMetaArgumentMetaOf = argumentMetaOf(irExpression, declaration, true);
                    } else if (i9 == 2 || i9 == 3) {
                        arrayList3.add(argumentMetaOf(irExpression, declaration, true));
                    } else {
                        if (i9 != 4) {
                            DumpIrTreeOptions dumpIrTreeOptions2 = dumpIrTreeOptions;
                            bu8.a();
                            return dumpIrTreeOptions2;
                        }
                        int i10 = i8 + 1;
                        arrayList3.add(argumentMetaOf(irExpression, declaration, ((!zIsEmpty ? ((Number) arrayList2.get(ComposableFunctionBodyTransformerKt.defaultsParamIndex(i8))).intValue() : 0) & (1 << ComposableFunctionBodyTransformerKt.defaultsBitIndex(i8))) == 0));
                        i8 = i10;
                    }
                } else {
                    if (irValueParameter3.getVarargElementType() == null) {
                        f2f.a("Unexpected null argument for composable call: ", DumpIrTreeKt.dump$default(expression, dumpIrTreeOptions, 1, dumpIrTreeOptions));
                        return dumpIrTreeOptions;
                    }
                    arrayList3.add(new CallArgumentMeta(declaration, null, true, false, false, null, 58, null));
                }
                i7++;
                dumpIrTreeOptions = dumpIrTreeOptions;
            }
            List<IrExpression> listBuildChangedArgumentsForCall = buildChangedArgumentsForCall(CollectionsKt.plus(arrayList3, CollectionsKt.listOfNotNull(callArgumentMetaArgumentMetaOf)));
            int size2 = listBuildChangedArgumentsForCall.size();
            for (int i11 = 0; i11 < size2; i11++) {
                expression.getArguments().set(i4 + i11, listBuildChangedArgumentsForCall.get(i11));
            }
            getCurrentFunctionScope().getMetrics().recordComposableCall(expression, arrayList3);
            getMetrics().recordComposableCall(expression, arrayList3);
            recordCallInSource(expression);
            IrVariable marker = callScope.getMarker();
            return (marker == null || (irBlockImplVariablePrefix = variablePrefix(expression, marker)) == null) ? expression : irBlockImplVariablePrefix;
        } catch (Throwable th) {
            this.currentScope = scope;
            throw th;
        }
    }

    private final IrExpression visitRememberCall(IrCall expression) throws Throwable {
        boolean z;
        boolean z2;
        IrContainerExpression irContainerExpressionWrap;
        IrExpression irExpressionIrGet;
        boolean z3;
        ArrayList arrayList;
        Function3<? super Boolean, ? super IrExpression, ? super CallArgumentMeta, ? extends IrExpression> function3;
        IrVariableImpl irVariableImplIrTemporary$default;
        IrExpression irExpression;
        final ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this;
        Scope.FileScope fileScope = composableFunctionBodyTransformer.currentScope.getFileScope();
        IrFile declaration = fileScope != null ? fileScope.getDeclaration() : null;
        ArrayList arrayList2 = new ArrayList();
        int size = expression.getArguments().size();
        IrExpression irExpression2 = null;
        int i = 0;
        boolean z4 = false;
        while (true) {
            z = true;
            if (i >= size) {
                break;
            }
            IrValueParameter irValueParameter = (IrValueParameter) expression.getSymbol().getOwner().getParameters().get(i);
            IrExpression irExpression3 = (IrExpression) expression.getArguments().get(i);
            if (irExpression3 == null) {
                k2d.a("Unexpected null argument found on key call");
                return null;
            }
            String strAsString = irValueParameter.getName().asString();
            strAsString.getClass();
            if (StringsKt.startsWith$default(strAsString, '$', false, 2, (Object) null)) {
                break;
            }
            if (Intrinsics.areEqual(irValueParameter.getName().getIdentifier(), "calculation")) {
                irExpression2 = irExpression3;
            } else if (irExpression3 instanceof IrVararg) {
                List<IrExpression> elements = ((IrVararg) irExpression3).getElements();
                ArrayList arrayList3 = new ArrayList();
                for (IrExpression irExpression4 : elements) {
                    if (irExpression4 instanceof IrSpreadElement) {
                        irExpression = irExpression3;
                        z4 = true;
                    } else {
                        irExpression = irExpression4 instanceof IrExpression ? irExpression4 : null;
                    }
                    if (irExpression != null) {
                        arrayList3.add(irExpression);
                    }
                }
                arrayList2.addAll(arrayList3);
            } else {
                arrayList2.add(irExpression3);
            }
            i++;
        }
        int size2 = arrayList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            arrayList2.set(i2, ((IrExpression) arrayList2.get(i2)).transform(composableFunctionBodyTransformer, (Object) null));
        }
        composableFunctionBodyTransformer.encounteredComposableCall(true);
        recordCallInSource(expression);
        if (irExpression2 == null) {
            return expression;
        }
        if (z4) {
            irExpression2.transform(composableFunctionBodyTransformer, (Object) null);
            return expression;
        }
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList4.add(composableFunctionBodyTransformer.argumentMetaOf((IrExpression) it.next(), declaration, true));
        }
        composableFunctionBodyTransformer.buildChangedArgumentsForCall(arrayList4);
        int size3 = arrayList4.size();
        IrChangedBitMaskValue maskParam = null;
        for (int i3 = 0; i3 < size3; i3++) {
            ParamMeta paramRef = arrayList4.get(i3).getParamRef();
            if ((paramRef != null ? paramRef.getMaskParam() : null) instanceof IrChangedBitMaskVariable) {
                if (maskParam == null) {
                    maskParam = paramRef.getMaskParam();
                } else if (!Intrinsics.areEqual(maskParam, paramRef.getMaskParam())) {
                    w01.a("Only single dirty param is allowed in a capture scope");
                    return null;
                }
            }
        }
        if (arrayList4.isEmpty()) {
            z2 = false;
            break;
        }
        Iterator it2 = arrayList4.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z2 = false;
                break;
            }
            ParamMeta paramRef2 = ((CallArgumentMeta) it2.next()).getParamRef();
            if ((paramRef2 != null ? paramRef2.getMaskParam() : null) instanceof IrChangedBitMaskVariable) {
                z2 = true;
                break;
            }
        }
        final boolean zAreEqual = Intrinsics.areEqual(expression.getOrigin(), ComposeMemoizedLambdaOrigin.INSTANCE);
        boolean z5 = composableFunctionBodyTransformer.getUpdateChangedFlagsFunction() != null;
        Function3<? super Boolean, ? super IrExpression, ? super CallArgumentMeta, ? extends IrExpression> composableFunctionBodyTransformer$visitRememberCall$changedFunction$2 = (z2 || !z5) ? new Function3() { // from class: cd2
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ComposableFunctionBodyTransformer.B(this.b, zAreEqual, ((Boolean) obj).booleanValue(), (IrExpression) obj2, (ComposableFunctionBodyTransformer.CallArgumentMeta) obj3);
            }
        } : new ComposableFunctionBodyTransformer$visitRememberCall$changedFunction$2(composableFunctionBodyTransformer);
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        int i4 = 0;
        for (Object obj : arrayList2) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            IrExpression irExpression5 = (IrExpression) obj;
            if (arrayList4.get(i4).isCertain() || (irExpression5 instanceof IrGetValue) || (irExpression5 instanceof IrConst)) {
                z3 = zAreEqual;
                arrayList = arrayList5;
                function3 = composableFunctionBodyTransformer$visitRememberCall$changedFunction$2;
                irVariableImplIrTemporary$default = null;
            } else {
                arrayList = arrayList5;
                z3 = zAreEqual;
                function3 = composableFunctionBodyTransformer$visitRememberCall$changedFunction$2;
                irVariableImplIrTemporary$default = irTemporary$default(composableFunctionBodyTransformer, irExpression5, "remember$arg$" + i4, (IrType) null, false, false, 28, (Object) null);
            }
            arrayList.add(irVariableImplIrTemporary$default);
            arrayList5 = arrayList;
            zAreEqual = z3;
            composableFunctionBodyTransformer$visitRememberCall$changedFunction$2 = function3;
            i4 = i5;
            z = z;
        }
        boolean z6 = zAreEqual;
        ArrayList arrayList6 = arrayList5;
        Function3<? super Boolean, ? super IrExpression, ? super CallArgumentMeta, ? extends IrExpression> function4 = composableFunctionBodyTransformer$visitRememberCall$changedFunction$2;
        ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList6, 10));
        int i6 = 0;
        for (Object obj2 : arrayList6) {
            int i7 = i6 + 1;
            if (i6 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            IrVariableImpl irVariableImpl = (IrVariableImpl) obj2;
            if (irVariableImpl == null || (irExpressionIrGet = composableFunctionBodyTransformer.irGet(irVariableImpl)) == null) {
                irExpressionIrGet = (IrExpression) arrayList2.get(i6);
            }
            arrayList7.add(irExpressionIrGet);
            i6 = i7;
        }
        IrExpression irExpressionIrIntrinsicRememberInvalid = composableFunctionBodyTransformer.irIntrinsicRememberInvalid(z6, arrayList7, arrayList4, function4);
        Scope.FunctionScope currentFunctionScope = composableFunctionBodyTransformer.getCurrentFunctionScope();
        IrCall irCallIrCache = composableFunctionBodyTransformer.irCache(irCurrentComposer$default(composableFunctionBodyTransformer, 0, 0, (IrValueParameter) null, 7, (Object) null), expression.getStartOffset(), expression.getEndOffset(), expression.getType(), irExpressionIrIntrinsicRememberInvalid, irExpression2.transform(composableFunctionBodyTransformer, (Object) null));
        if (z2 && z5) {
            currentFunctionScope.recordIntrinsicRememberFixUp(z6, arrayList7, arrayList4, irCallIrCache);
        }
        AnonymousClass1 anonymousClass1IntrinsicRememberScope = intrinsicRememberScope(expression);
        Scope scope = composableFunctionBodyTransformer.currentScope;
        composableFunctionBodyTransformer.currentScope = anonymousClass1IntrinsicRememberScope;
        anonymousClass1IntrinsicRememberScope.setParent(scope);
        anonymousClass1IntrinsicRememberScope.setLevel(scope.getLevel() + 1);
        try {
            List<? extends IrStatement> listFilterNotNull = CollectionsKt.filterNotNull(arrayList6);
            if (composableFunctionBodyTransformer.getUseNonSkippingGroupOptimization()) {
                IrContainerExpression irContainerExpressionIrWithSourceInformationMarker = composableFunctionBodyTransformer.irWithSourceInformationMarker(irCallIrCache, anonymousClass1IntrinsicRememberScope, listFilterNotNull);
                irContainerExpressionWrap = !(irContainerExpressionIrWithSourceInformationMarker instanceof IrBlock) ? AbstractComposeLowering.wrap$default(composableFunctionBodyTransformer, irContainerExpressionIrWithSourceInformationMarker, 0, 0, irContainerExpressionIrWithSourceInformationMarker.getType(), null, null, 27, null) : irContainerExpressionIrWithSourceInformationMarker;
            } else {
                try {
                    List<? extends IrStatement> listPlus = CollectionsKt.plus(listFilterNotNull, CollectionsKt.listOf(irStartReplaceGroup$default(composableFunctionBodyTransformer, expression, anonymousClass1IntrinsicRememberScope, composableFunctionBodyTransformer.irFunctionSourceKey(expression.getSymbol().getOwner()), 0, 0, 24, null)));
                    composableFunctionBodyTransformer = this;
                    irContainerExpressionWrap = composableFunctionBodyTransformer.wrap(irCallIrCache, listPlus, CollectionsKt.listOf(irEndReplaceGroup$default(composableFunctionBodyTransformer, 0, 0, anonymousClass1IntrinsicRememberScope, 3, (Object) null)));
                } catch (Throwable th) {
                    th = th;
                    composableFunctionBodyTransformer = this;
                    composableFunctionBodyTransformer.currentScope = scope;
                    throw th;
                }
            }
            composableFunctionBodyTransformer.currentScope = scope;
            if (StabilityKt.knownStable(composableFunctionBodyTransformer.getStabilityInferencer().stabilityOf(irContainerExpressionWrap.getType(), declaration))) {
                if (!arrayList4.isEmpty()) {
                    Iterator it3 = arrayList4.iterator();
                    while (it3.hasNext()) {
                        if (!((CallArgumentMeta) it3.next()).isStatic()) {
                            return irContainerExpressionWrap;
                        }
                    }
                }
                WeakBindingTraceKt.getIrTrace(composableFunctionBodyTransformer.getContext()).record(ComposeWritableSlices.INSTANCE.getIS_STATIC_EXPRESSION(), irContainerExpressionWrap, Boolean.TRUE);
            }
            return irContainerExpressionWrap;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private final IrFunction visitRestartableComposableFunction(IrFunction declaration, final Scope.FunctionScope scope, final IrChangedBitMaskValue changedParam, final IrDefaultBitMaskValue defaultParam) throws Throwable {
        IrChangedBitMaskValue irChangedBitMaskValue;
        IrElement irElement;
        int i;
        Scope.FunctionScope functionScope;
        ComposableFunctionBodyTransformer composableFunctionBodyTransformer;
        IrWhenImpl irWhenImplIrComposite$default;
        boolean z;
        IrElement body = declaration.getBody();
        body.getClass();
        IrContainerExpression irContainerExpressionMutableStatementContainer = mutableStatementContainer();
        IrContainerExpression irContainerExpressionMutableStatementContainer2 = mutableStatementContainer();
        final IrChangedBitMaskValue irChangedBitMaskValueIrCopyToTemporary = !scope.getAllTrackedParams().isEmpty() ? changedParam.irCopyToTemporary("$dirty", NativePlatformKt.isNative(getContext().getPlatform()), true) : changedParam;
        scope.setDirty(irChangedBitMaskValueIrCopyToTemporary);
        Pair pairAsBodyAndResultVar$default = asBodyAndResultVar$default(this, body, null, 1, null);
        IrContainerExpression irContainerExpression = (IrContainerExpression) pairAsBodyAndResultVar$default.component1();
        IrVariable irVariable = (IrVariable) pairAsBodyAndResultVar$default.component2();
        final Function0 function0 = new Function0() { // from class: jc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.Y(this.b, scope, changedParam, defaultParam);
            }
        };
        Function0<? extends IrExpression> function1 = new Function0() { // from class: uc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.I(this.b, function0);
            }
        };
        Scope.ParametersScope parametersScopeTransformDefaults = transformDefaults(scope);
        transformChildrenVoid(irContainerExpression);
        final boolean zBuildPreambleStatementsAndReturnIfSkippingPossible = buildPreambleStatementsAndReturnIfSkippingPossible(body, irContainerExpressionMutableStatementContainer, irContainerExpressionMutableStatementContainer2, !getHasNonSkippableAnnotation(declaration), scope, irChangedBitMaskValueIrCopyToTemporary, changedParam, defaultParam, parametersScopeTransformDefaults);
        if (getTraceEventMarkersEnabled()) {
            wrapWithTraceEvents(irContainerExpression, irFunctionSourceKey$default(this, null, 1, null), scope);
        }
        if (irChangedBitMaskValueIrCopyToTemporary.getUsed() && (irChangedBitMaskValueIrCopyToTemporary instanceof IrChangedBitMaskVariable)) {
            irContainerExpressionMutableStatementContainer.getStatements().addAll(0, ((IrChangedBitMaskVariable) irChangedBitMaskValueIrCopyToTemporary).asStatements());
            irChangedBitMaskValue = irChangedBitMaskValueIrCopyToTemporary;
        } else {
            irChangedBitMaskValue = changedParam;
        }
        scope.applyIntrinsicRememberFixups(new Function3() { // from class: bd2
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ComposableFunctionBodyTransformer.d0(zBuildPreambleStatementsAndReturnIfSkippingPossible, this, irChangedBitMaskValueIrCopyToTemporary, changedParam, ((Boolean) obj).booleanValue(), (List) obj2, (List) obj3);
            }
        });
        if (zBuildPreambleStatementsAndReturnIfSkippingPossible) {
            IrExpression irExpressionIrShouldExecute = irShouldExecute(irChangedBitMaskValue.irHasDifferences(scope.getUsedParams()), irChangedBitMaskValue.irRestartFlags());
            List listTake = CollectionsKt.take(AbstractComposeLoweringKt.getNamedParameters(declaration), scope.getRealValueParamCount());
            IrFile fileOrNull = IrUtilsKt.getFileOrNull(declaration);
            List<IrValueParameter> list = listTake;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (IrValueParameter irValueParameter : list) {
                StabilityInferencer stabilityInferencer = getStabilityInferencer();
                IrType varargElementType = irValueParameter.getVarargElementType();
                if (varargElementType == null) {
                    varargElementType = irValueParameter.getType();
                }
                arrayList.add(Boolean.valueOf(StabilityKt.knownUnstable(stabilityInferencer.stabilityOf(varargElementType, fileOrNull))));
            }
            boolean[] booleanArray = CollectionsKt.toBooleanArray(arrayList);
            int length = booleanArray.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                }
                if (booleanArray[i2]) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (!getEnabled(FeatureFlag.StrongSkipping) && z && defaultParam != null) {
                irExpressionIrShouldExecute = irOrOr(defaultParam.irHasAnyProvidedAndUnstable(booleanArray), irExpressionIrShouldExecute);
            }
            irElement = body;
            functionScope = scope;
            irWhenImplIrComposite$default = AbstractComposeLowering.irIfThenElse$default(this, null, irExpressionIrShouldExecute, AbstractComposeLowering.irBlock$default(this, null, null, 0, 0, CollectionsKt.plus(irContainerExpressionMutableStatementContainer2.getStatements(), irContainerExpression.getStatements()), 15, null), irSkipToGroupEnd$default(this, 0, 0, 3, null), 0, 0, 49, null);
            composableFunctionBodyTransformer = this;
            i = 0;
        } else {
            irElement = body;
            i = 0;
            functionScope = scope;
            composableFunctionBodyTransformer = this;
            irWhenImplIrComposite$default = AbstractComposeLowering.irComposite$default(composableFunctionBodyTransformer, null, null, CollectionsKt.plus(irContainerExpressionMutableStatementContainer2.getStatements(), irContainerExpression.getStatements()), 3, null);
        }
        functionScope.realizeGroup(function1);
        IrBlockBody irBlockBodyCreateBlockBody = composableFunctionBodyTransformer.getContext().getIrFactory().createBlockBody(irElement.getStartOffset(), irElement.getEndOffset());
        List statements = irBlockBodyCreateBlockBody.getStatements();
        SpreadBuilder spreadBuilder = new SpreadBuilder(6);
        spreadBuilder.add(composableFunctionBodyTransformer.irStartRestartGroup(irElement, functionScope, irFunctionSourceKey$default(composableFunctionBodyTransformer, null, 1, null)));
        spreadBuilder.addSpread(functionScope.getMarkerPreamble().getStatements().toArray(new IrStatement[i]));
        spreadBuilder.addSpread(irContainerExpressionMutableStatementContainer.getStatements().toArray(new IrStatement[i]));
        spreadBuilder.add(irWhenImplIrComposite$default);
        spreadBuilder.add(irVariable == null ? (IrExpression) function0.invoke() : null);
        spreadBuilder.add(irVariable != null ? composableFunctionBodyTransformer.irReturnVar(declaration.getSymbol(), irVariable) : null);
        statements.addAll(CollectionsKt.listOfNotNull(spreadBuilder.toArray(new IrStatement[spreadBuilder.size()])));
        declaration.setBody(irBlockBodyCreateBlockBody);
        functionScope.getMetrics().recordFunction(true, true, zBuildPreambleStatementsAndReturnIfSkippingPossible, false, false, functionScope.getHasDefaultsGroup(), false);
        functionScope.getMetrics().recordGroup();
        return declaration;
    }

    public static IrSimpleFunction w(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        IrClass owner;
        Sequence functions;
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(composableFunctionBodyTransformer.getEndRestartGroupFunction().getReturnType());
        if (classOrNull != null && (owner = classOrNull.getOwner()) != null && (functions = IrUtilsKt.getFunctions(owner)) != null) {
            Iterator it = functions.iterator();
            boolean z = false;
            Object obj = null;
            while (true) {
                if (!it.hasNext()) {
                    if (!z) {
                        break;
                    }
                    break;
                }
                Object next = it.next();
                IrSimpleFunction irSimpleFunction = (IrSimpleFunction) next;
                if (Intrinsics.areEqual(irSimpleFunction.getName(), ComposeNames.INSTANCE.getUpdateScope()) && irSimpleFunction.getParameters().size() == 2 && composableFunctionBodyTransformer.getArguments(((IrValueParameter) irSimpleFunction.getParameters().get(1)).getType()).size() == 3) {
                    if (!z) {
                        obj = next;
                        z = true;
                    }
                }
                obj = null;
                break;
            }
            IrSimpleFunction irSimpleFunction2 = (IrSimpleFunction) obj;
            if (irSimpleFunction2 != null) {
                return irSimpleFunction2;
            }
        }
        k2d.a("new updateScope not found in result type of endRestartGroup");
        return null;
    }

    private final IrExpression withReplaceGroupStatements(IrBlock irBlock, final Scope.BlockScope blockScope, int i) {
        getCurrentFunctionScope().getMetrics().recordGroup();
        blockScope.realizeGroup(new Function0() { // from class: sc2
            public final Object invoke() {
                return ComposableFunctionBodyTransformer.c0(this.b, blockScope);
            }
        });
        List listSubList = irBlock.getStatements().subList(0, i);
        List listSubList2 = irBlock.getStatements().subList(i, irBlock.getStatements().size());
        return endsWithReturnOrJump(irBlock) ? org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrBlockImpl(irBlock.getStartOffset(), irBlock.getEndOffset(), irBlock.getType(), irBlock.getOrigin(), CollectionsKt.plus(CollectionsKt.plus(listSubList, CollectionsKt.listOf(irStartReplaceGroup$default(this, irBlock, blockScope, null, 0, 0, 28, null))), listSubList2)) : org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrBlockImpl(irBlock.getStartOffset(), irBlock.getEndOffset(), irBlock.getType(), irBlock.getOrigin(), CollectionsKt.plus(CollectionsKt.plus(CollectionsKt.plus(listSubList, CollectionsKt.listOf(irStartReplaceGroup$default(this, irBlock, blockScope, null, irBlock.getStartOffset(), irBlock.getStartOffset(), 4, null))), listSubList2), CollectionsKt.listOf(irEndReplaceGroup(irBlock.getEndOffset(), irBlock.getEndOffset(), blockScope))));
    }

    public static /* synthetic */ IrExpression withReplaceGroupStatements$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrBlock irBlock, Scope.BlockScope blockScope, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return composableFunctionBodyTransformer.withReplaceGroupStatements(irBlock, blockScope, i);
    }

    private final <T extends Scope> T withScope(T scope, Function0<Unit> block) {
        Scope scope2 = this.currentScope;
        this.currentScope = scope;
        scope.setParent(scope2);
        scope.setLevel(scope2.getLevel() + 1);
        try {
            block.invoke();
            return scope;
        } finally {
            InlineMarker.finallyStart(1);
            this.currentScope = scope2;
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IrContainerExpression wrap$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, IrExpression irExpression, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 2) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return composableFunctionBodyTransformer.wrap(irExpression, list, list2);
    }

    private final void wrapWithTraceEvents(IrContainerExpression irContainerExpression, IrExpression irExpression, Scope.FunctionScope functionScope) {
        IrExpression irExpressionIrTraceEventStart = irTraceEventStart(irExpression, functionScope);
        IrExpression irExpressionIrTraceEventEnd = irTraceEventEnd();
        if (irExpressionIrTraceEventStart == null || irExpressionIrTraceEventEnd == null) {
            return;
        }
        irContainerExpression.getStatements().add(0, irExpressionIrTraceEventStart);
        irContainerExpression.getStatements().add(irExpressionIrTraceEventEnd);
    }

    public static boolean x(IrPluginContext irPluginContext) {
        return irPluginContext.finderForBuiltins().findClass(ComposeClassIds.INSTANCE.getSourceInformation()) != null;
    }

    public static IrExpression y(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Scope.BlockScope blockScope) {
        return irEndReplaceGroup$default(composableFunctionBodyTransformer, 0, 0, blockScope, 3, (Object) null);
    }

    public static IrProperty z(ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
        for (IrProperty irProperty : IrUtilsKt.getProperties(composableFunctionBodyTransformer.getComposerIrClass())) {
            if (Intrinsics.areEqual(irProperty.getName().asString(), "skipping")) {
                return irProperty;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public final IrCall irCurrentMarker(IrValueParameter composerParameter) {
        composerParameter.getClass();
        IrExpression irExpressionIrCurrentComposer$default = irCurrentComposer$default(this, 0, 0, composerParameter, 3, (Object) null);
        IrProperty currentMarkerProperty = getCurrentMarkerProperty();
        currentMarkerProperty.getClass();
        IrSimpleFunction getter = currentMarkerProperty.getGetter();
        getter.getClass();
        return AbstractComposeLowering.irMethodCall$default(this, irExpressionIrCurrentComposer$default, getter, 0, 0, 12, null);
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        this.inlineLambdaInfo.scan(irModule);
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
        applySourceFixups();
        PatchDeclarationParentsKt.patchDeclarationParents$default(irModule, (IrDeclarationParent) null, 1, (Object) null);
    }

    public IrExpression visitBlock(IrBlock expression) {
        expression.getClass();
        IrStatementOrigin origin = expression.getOrigin();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        if (!Intrinsics.areEqual(origin, companion.getFOR_LOOP())) {
            return Intrinsics.areEqual(origin, companion.getFOR_LOOP_INNER_WHILE()) ? super.visitBlock(expression) : super.visitBlock(expression);
        }
        List statements = expression.getStatements();
        if (statements.size() != 2) {
            w01.a("Expected 2 statements in for-loop block");
            return null;
        }
        Object obj = statements.get(0);
        obj.getClass();
        IrVariable irVariable = (IrVariable) obj;
        if (!Intrinsics.areEqual(irVariable.getOrigin(), IrDeclarationOrigin.Companion.getFOR_LOOP_ITERATOR())) {
            w01.a("Expected FOR_LOOP_ITERATOR origin for iterator variable");
            return null;
        }
        IrElement irElementTransform = irVariable.transform(this, (Object) null);
        irElementTransform.getClass();
        IrElement irElement = (IrVariable) irElementTransform;
        Object obj2 = statements.get(1);
        obj2.getClass();
        IrWhileLoop irWhileLoop = (IrWhileLoop) obj2;
        if (!Intrinsics.areEqual(irWhileLoop.getOrigin(), companion.getFOR_LOOP_INNER_WHILE())) {
            w01.a("Expected FOR_LOOP_INNER_WHILE origin for while loop");
            return null;
        }
        IrBlock irBlockTransform = irWhileLoop.transform(this, (Object) null);
        if (Intrinsics.areEqual(irElement, irVariable) && Intrinsics.areEqual(irBlockTransform, irWhileLoop)) {
            return expression;
        }
        if (!(irBlockTransform instanceof IrBlock)) {
            k2d.a("Expected transformed loop to be an IrBlock");
            return null;
        }
        IrBlock irBlock = irBlockTransform;
        if (irBlock.getStatements().size() != 3) {
            w01.a("Failed requirement.");
            return null;
        }
        Object obj3 = irBlock.getStatements().get(0);
        obj3.getClass();
        IrContainerExpression irContainerExpression = (IrContainerExpression) obj3;
        Object obj4 = irBlock.getStatements().get(1);
        obj4.getClass();
        Object obj5 = irBlock.getStatements().get(2);
        obj5.getClass();
        IrContainerExpression irContainerExpressionMutableStatementContainer = mutableStatementContainer();
        irContainerExpressionMutableStatementContainer.getStatements().addAll(CollectionsKt.listOf(new IrContainerExpression[]{irContainerExpression, AbstractComposeLowering.irBlock$default(this, expression.getType(), companion.getFOR_LOOP(), 0, 0, CollectionsKt.listOf(new IrElement[]{irElement, (IrWhileLoop) obj4}), 12, null), (IrContainerExpression) obj5}));
        return irContainerExpressionMutableStatementContainer;
    }

    public IrExpression visitBreakContinue(IrBreakContinue jump) {
        jump.getClass();
        if (!isInComposableScope()) {
            return super.visitBreakContinue(jump);
        }
        final IrContainerExpression irContainerExpressionMutableStatementContainer = mutableStatementContainer();
        encounteredJump(jump, new Function1() { // from class: rc2
            public final Object invoke(Object obj) {
                return ComposableFunctionBodyTransformer.W(irContainerExpressionMutableStatementContainer, (IrExpression) obj);
            }
        });
        return wrap$default(this, jump, CollectionsKt.listOf(irContainerExpressionMutableStatementContainer), null, 2, null);
    }

    public IrStatement visitClass(IrClass declaration) {
        declaration.getClass();
        if (isComposableSingletonClass(declaration)) {
            return declaration;
        }
        Scope.ClassScope classScope = new Scope.ClassScope(declaration.getName());
        Scope scope = this.currentScope;
        this.currentScope = classScope;
        classScope.setParent(scope);
        classScope.setLevel(scope.getLevel() + 1);
        try {
            return super.visitDeclaration(declaration);
        } finally {
            this.currentScope = scope;
        }
    }

    public IrStatement visitDeclaration(IrDeclarationBase declaration) {
        declaration.getClass();
        if ((declaration instanceof IrField) || (declaration instanceof IrProperty) || (declaration instanceof IrFunction) || (declaration instanceof IrClass)) {
            return super.visitDeclaration(declaration);
        }
        if ((declaration instanceof IrTypeAlias) || (declaration instanceof IrEnumEntry) || (declaration instanceof IrAnonymousInitializer) || (declaration instanceof IrTypeParameter) || (declaration instanceof IrLocalDelegatedProperty) || (declaration instanceof IrValueDeclaration) || (declaration instanceof IrScript)) {
            return super.visitDeclaration(declaration);
        }
        dwe.a("Unhandled declaration! ".concat(declaration.getClass().getSimpleName()));
        return null;
    }

    public IrExpression visitDoWhileLoop(IrDoWhileLoop loop) {
        loop.getClass();
        return !isInComposableScope() ? super.visitDoWhileLoop(loop) : handleLoop(loop);
    }

    public IrStatement visitField(IrField declaration) {
        declaration.getClass();
        Scope.FieldScope fieldScope = new Scope.FieldScope(declaration.getName());
        Scope scope = this.currentScope;
        this.currentScope = fieldScope;
        fieldScope.setParent(scope);
        fieldScope.setLevel(scope.getLevel() + 1);
        try {
            return super.visitField(declaration);
        } finally {
            this.currentScope = scope;
        }
    }

    public IrFile visitFile(IrFile declaration) throws Exception {
        declaration.getClass();
        try {
            Scope.FileScope fileScope = new Scope.FileScope(declaration);
            Scope scope = this.currentScope;
            this.currentScope = fileScope;
            fileScope.setParent(scope);
            fileScope.setLevel(scope.getLevel() + 1);
            try {
                return super.visitFile(declaration);
            } finally {
                this.currentScope = scope;
            }
        } catch (Exception e) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
            throw new Exception("IR lowering failed at: " + IrDeclarationsKt.getName(declaration), e);
        }
    }

    public IrStatement visitFunction(IrFunction declaration) {
        declaration.getClass();
        Scope.FunctionScope functionScope = new Scope.FunctionScope(declaration, this);
        Scope scope = this.currentScope;
        this.currentScope = functionScope;
        functionScope.setParent(scope);
        functionScope.setLevel(scope.getLevel() + 1);
        try {
            IrStatement irStatementVisitFunctionInScope = visitFunctionInScope(declaration);
            this.currentScope = scope;
            getMetrics().recordFunction(functionScope.getMetrics());
            ComposePluginAttributesKt.setFunctionMetrics(declaration, functionScope.getMetrics());
            return irStatementVisitFunctionInScope;
        } catch (Throwable th) {
            this.currentScope = scope;
            throw th;
        }
    }

    public IrExpression visitFunctionAccess(IrFunctionAccessExpression expression) {
        int i;
        IrExpression irExpressionTransform;
        IrSimpleFunctionSymbol symbol;
        IrSimpleFunction owner;
        IrPropertySymbol correspondingPropertySymbol;
        expression.getClass();
        if (ComposePluginAttributesKt.getAssociatedComposableSingletonStub(expression) != null) {
            IrCall associatedComposableSingletonStub = ComposePluginAttributesKt.getAssociatedComposableSingletonStub(expression);
            IrProperty owner2 = (associatedComposableSingletonStub == null || (symbol = associatedComposableSingletonStub.getSymbol()) == null || (owner = symbol.getOwner()) == null || (correspondingPropertySymbol = owner.getCorrespondingPropertySymbol()) == null) ? null : correspondingPropertySymbol.getOwner();
            if (owner2 != null) {
                transformChildrenVoid(owner2);
            }
        }
        if (expression instanceof IrCall) {
            IrCall irCall = (IrCall) expression;
            if (isComposableCall(irCall) || isSyntheticComposableCall(irCall)) {
                return visitComposableCall(irCall);
            }
        }
        if (!expression.getSymbol().getOwner().isInline() && !AdditionalIrUtilsKt.isInlineArrayConstructor(expression.getSymbol().getOwner())) {
            if (expression instanceof IrCall) {
                IrCall irCall2 = (IrCall) expression;
                if (isComposableSingletonGetter(irCall2)) {
                    IrPropertySymbol correspondingPropertySymbol2 = irCall2.getSymbol().getOwner().getCorrespondingPropertySymbol();
                    IrProperty owner3 = correspondingPropertySymbol2 != null ? correspondingPropertySymbol2.getOwner() : null;
                    if (owner3 != null) {
                        transformChildrenVoid(owner3);
                    }
                    return super.visitFunctionAccess(expression);
                }
            }
            return super.visitFunctionAccess(expression);
        }
        Scope.CaptureScope captureScope = new Scope.CaptureScope();
        Scope.CallScope callScope = new Scope.CallScope(expression, this);
        Scope scope = this.currentScope;
        this.currentScope = callScope;
        callScope.setParent(scope);
        callScope.setLevel(scope.getLevel() + 1);
        try {
            IrFunction owner4 = expression.getSymbol().getOwner();
            List parameters = owner4.getParameters();
            if ((parameters instanceof Collection) && parameters.isEmpty()) {
                i = 0;
            } else {
                Iterator it = parameters.iterator();
                i = 0;
                while (it.hasNext()) {
                    if (JvmIrInlineUtilsKt.isInlineParameter((IrValueParameter) it.next()) && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            captureScope.setForceInlinedLambdaGroup(i > 1);
            IrMemberAccessExpression.ValueArgumentsList arguments = expression.getArguments();
            int size = arguments.size();
            for (int i2 = 0; i2 < size; i2++) {
                IrExpression irExpression = (IrExpression) arguments.get(i2);
                if (JvmIrInlineUtilsKt.isInlineParameter((IrValueParameter) owner4.getParameters().get(i2))) {
                    Scope scope2 = this.currentScope;
                    this.currentScope = captureScope;
                    captureScope.setParent(scope2);
                    captureScope.setLevel(scope2.getLevel() + 1);
                    if (irExpression != null) {
                        try {
                            irExpressionTransform = irExpression.transform(this, (Object) null);
                        } catch (Throwable th) {
                            this.currentScope = scope2;
                            throw th;
                        }
                    } else {
                        irExpressionTransform = null;
                    }
                    this.currentScope = scope2;
                } else {
                    irExpressionTransform = irExpression != null ? irExpression.transform(this, (Object) null) : null;
                }
                expression.getArguments().set(i2, irExpressionTransform);
            }
            this.currentScope = scope;
            if (!captureScope.getHasCapturedComposableCall() || captureScope.getForceInlinedLambdaGroup()) {
                return expression;
            }
            captureScope.realizeAllDirectChildren();
            return asCoalescableGroup(expression, captureScope);
        } catch (Throwable th2) {
            this.currentScope = scope;
            throw th2;
        }
    }

    public IrExpression visitGetValue(IrGetValue expression) {
        expression.getClass();
        IrValueParameter owner = expression.getSymbol().getOwner();
        if (owner instanceof IrValueParameter) {
            IrDeclarationParent parent = owner.getParent();
            for (Scope parent2 = this.currentScope; parent2 != null; parent2 = parent2.getParent()) {
                if (parent2 instanceof Scope.FunctionScope) {
                    Scope.FunctionScope functionScope = (Scope.FunctionScope) parent2;
                    if (Intrinsics.areEqual(functionScope.getFunction(), parent)) {
                        int iIndexOf = functionScope.getAllTrackedParams().indexOf(owner);
                        if (iIndexOf == -1) {
                            break;
                        }
                        functionScope.getUsedParams()[iIndexOf] = true;
                        return expression;
                    }
                }
            }
        }
        return expression;
    }

    public IrStatement visitProperty(IrProperty declaration) {
        declaration.getClass();
        Scope.PropertyScope propertyScope = new Scope.PropertyScope(declaration.getName());
        Scope scope = this.currentScope;
        this.currentScope = propertyScope;
        propertyScope.setParent(scope);
        propertyScope.setLevel(scope.getLevel() + 1);
        try {
            return super.visitProperty(declaration);
        } finally {
            this.currentScope = scope;
        }
    }

    public IrExpression visitReturn(IrReturn expression) {
        expression.getClass();
        if (!isInComposableScope() || getCurrentFunctionScope().getPreserveIrShape()) {
            return super.visitReturn(expression);
        }
        Scope.ReturnScope returnScope = new Scope.ReturnScope(expression);
        Scope scope = this.currentScope;
        this.currentScope = returnScope;
        returnScope.setParent(scope);
        returnScope.setLevel(scope.getLevel() + 1);
        try {
            transformChildrenVoid(expression);
            this.currentScope = scope;
            final IrExpression irExpressionMutableStatementContainer = mutableStatementContainer();
            encounteredReturn(expression.getReturnTargetSymbol(), new Function1() { // from class: tc2
                public final Object invoke(Object obj) {
                    return ComposableFunctionBodyTransformer.b0(irExpressionMutableStatementContainer, (IrExpression) obj);
                }
            });
            if (!returnScope.getHasComposableCalls() && ComposableFunctionBodyTransformerKt.isUnitOrNullableUnit(expression.getValue().getType())) {
                return wrap$default(this, expression, CollectionsKt.listOf(irExpressionMutableStatementContainer), null, 2, null);
            }
            IrVariableImpl irVariableImplIrTemporary$default = irTemporary$default(this, expression.getValue(), "return", (IrType) null, false, false, 28, (Object) null);
            return AbstractComposeLowering.wrap$default(this, irVariableImplIrTemporary$default, expression.getStartOffset(), expression.getEndOffset(), expression.getType(), null, CollectionsKt.listOf(new IrExpression[]{irExpressionMutableStatementContainer, new IrReturnImpl(expression.getStartOffset(), expression.getEndOffset(), expression.getType(), expression.getReturnTargetSymbol(), irGet(irVariableImplIrTemporary$default))}), 8, null);
        } catch (Throwable th) {
            this.currentScope = scope;
            throw th;
        }
    }

    public IrExpression visitWhen(IrWhen expression) {
        ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this;
        expression.getClass();
        if (composableFunctionBodyTransformer.isInComposableScope() && !composableFunctionBodyTransformer.getHasExplicitGroups(composableFunctionBodyTransformer.getCurrentFunctionScope().getFunction())) {
            boolean enabled = composableFunctionBodyTransformer.getEnabled(FeatureFlag.OptimizeNonSkippingGroups);
            IrWhenImpl IrWhenImpl = org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrWhenImpl(expression.getStartOffset(), expression.getEndOffset(), expression.getType(), expression.getOrigin());
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Scope.WhenScope whenScope = new Scope.WhenScope();
            Scope scope = composableFunctionBodyTransformer.currentScope;
            composableFunctionBodyTransformer.currentScope = whenScope;
            whenScope.setParent(scope);
            whenScope.setLevel(scope.getLevel() + 1);
            try {
                List branches = expression.getBranches();
                int size = branches.size();
                int i = 0;
                boolean z = false;
                int i2 = 0;
                boolean z2 = false;
                while (i < size) {
                    IrBranch irBranch = (IrBranch) branches.get(i);
                    if (irBranch instanceof IrElseBranch) {
                        Pair pairTransformWithScope = composableFunctionBodyTransformer.transformWithScope(irBranch.getResult(), new Scope.BranchScope());
                        Scope.BranchScope branchScope = (Scope.BranchScope) pairTransformWithScope.component1();
                        IrExpression irExpression = (IrExpression) pairTransformWithScope.component2();
                        arrayList2.add(new Scope.BranchScope());
                        arrayList.add(branchScope);
                        if (branchScope.getHasComposableCalls()) {
                            i2++;
                        }
                        IrWhenImpl.getBranches().add(org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrElseBranchImpl(irBranch.getStartOffset(), irBranch.getEndOffset(), irBranch.getCondition(), irExpression));
                        z = true;
                    } else {
                        Pair pairTransformWithScope2 = composableFunctionBodyTransformer.transformWithScope(irBranch.getCondition(), new Scope.BranchScope());
                        Scope.BranchScope branchScope2 = (Scope.BranchScope) pairTransformWithScope2.component1();
                        IrExpression irExpression2 = (IrExpression) pairTransformWithScope2.component2();
                        Pair pairTransformWithScope3 = composableFunctionBodyTransformer.transformWithScope(irBranch.getResult(), new Scope.BranchScope());
                        Scope.BranchScope branchScope3 = (Scope.BranchScope) pairTransformWithScope3.component1();
                        IrExpression irExpression3 = (IrExpression) pairTransformWithScope3.component2();
                        arrayList2.add(branchScope2);
                        arrayList.add(branchScope3);
                        z2 = z2 || (i != 0 && branchScope2.getHasComposableCalls());
                        if (branchScope3.getHasComposableCalls() && !composableFunctionBodyTransformer.isGroupBalanced(irBranch.getResult())) {
                            i2++;
                        }
                        IrWhenImpl.getBranches().add(org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrBranchImpl(irBranch.getStartOffset(), irBranch.getEndOffset(), irExpression2, irExpression3));
                    }
                    i++;
                    enabled = enabled;
                }
                boolean z3 = enabled;
                composableFunctionBodyTransformer.currentScope = scope;
                boolean z4 = !z3 ? i2 <= 1 || z2 : i2 <= 0;
                if (!z && z4) {
                    arrayList2.add(new Scope.BranchScope());
                    arrayList.add(new Scope.BranchScope());
                    List branches2 = IrWhenImpl.getBranches();
                    IrBlock irBlockIrBlock$default = AbstractComposeLowering.irBlock$default(composableFunctionBodyTransformer, composableFunctionBodyTransformer.getContext().getIrBuiltIns().getUnitType(), null, expression.getEndOffset(), expression.getEndOffset(), CollectionsKt.emptyList(), 2, null);
                    composableFunctionBodyTransformer = this;
                    branches2.add(AbstractComposeLowering.irElseBranch$default(composableFunctionBodyTransformer, irBlockIrBlock$default, 0, 0, 6, null));
                }
                List branches3 = IrWhenImpl.getBranches();
                int size2 = branches3.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    Object obj = branches3.get(i3);
                    Object obj2 = arrayList2.get(i3);
                    Scope.BranchScope branchScope4 = (Scope.BranchScope) arrayList.get(i3);
                    Scope.BranchScope branchScope5 = (Scope.BranchScope) obj2;
                    IrBranch irBranch2 = (IrBranch) obj;
                    if (branchScope5.getHasComposableCalls()) {
                        if (!z2 || z3) {
                            branchScope5.realizeAllDirectChildren();
                            branchScope5.realizeCoalescableGroup();
                        } else {
                            irBranch2.setCondition(composableFunctionBodyTransformer.asReplaceGroup(irBranch2.getCondition(), branchScope5));
                        }
                    }
                    if (z4 || (!z3 && z2 && branchScope4.getHasComposableCalls())) {
                        irBranch2.setResult(composableFunctionBodyTransformer.asReplaceGroup(irBranch2.getResult(), branchScope4));
                    }
                    if (i2 == 1 && branchScope4.getHasComposableCalls()) {
                        branchScope4.realizeCoalescableGroup();
                    }
                }
                if (z3 && z4) {
                    IrStatementOrigin origin = IrWhenImpl.getOrigin();
                    IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
                    if (Intrinsics.areEqual(origin, companion.getANDAND()) || Intrinsics.areEqual(IrWhenImpl.getOrigin(), companion.getOROR())) {
                        IrWhenImpl.setOrigin(companion.getWHEN());
                    }
                }
                return ((z3 || i2 != 1) && !z2) ? IrWhenImpl : composableFunctionBodyTransformer.asCoalescableGroup(IrWhenImpl, whenScope);
            } catch (Throwable th) {
                composableFunctionBodyTransformer.currentScope = scope;
                throw th;
            }
        }
        return super.visitWhen(expression);
    }

    public IrExpression visitWhileLoop(IrWhileLoop loop) {
        loop.getClass();
        return !isInComposableScope() ? super.visitWhileLoop(loop) : handleLoop(loop);
    }

    public final IrContainerExpression wrap(IrExpression irExpression, List<? extends IrStatement> list, List<? extends IrStatement> list2) {
        irExpression.getClass();
        list.getClass();
        list2.getClass();
        if (list2.isEmpty() || IrTypePredicatesKt.isNothing(irExpression.getType()) || IrTypePredicatesKt.isUnit(irExpression.getType())) {
            return wrap(irExpression, irExpression.getStartOffset(), irExpression.getEndOffset(), irExpression.getType(), list, list2);
        }
        IrVariableImpl irVariableImplIrTemporary$default = irTemporary$default(this, irExpression, "group", (IrType) null, false, false, 28, (Object) null);
        return wrap(irVariableImplIrTemporary$default, irExpression.getStartOffset(), irExpression.getEndOffset(), irExpression.getType(), list, CollectionsKt.plus(list2, irGet(irVariableImplIrTemporary$default)));
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0010$%&'()*+,-./0123B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b#\u0010!\u0082\u0001\u00074567\u001b89¨\u0006:"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "", "name", "", "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "parent", "getParent", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "setParent", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;)V", "level", "", "getLevel", "()I", "setLevel", "(I)V", "isInComposable", "", "()Z", "functionScope", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FunctionScope;", "getFunctionScope", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FunctionScope;", "fileScope", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FileScope;", "getFileScope", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FileScope;", "nearestComposer", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "getNearestComposer", "()Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "myComposer", "getMyComposer", "SourceLocation", "RootScope", "FunctionScope", "BlockScope", "ClassScope", "PropertyScope", "FieldScope", "FileScope", "LoopScope", "KeyScope", "WhenScope", "BranchScope", "CaptureScope", "ParametersScope", "CallScope", "ReturnScope", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$CallScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$ClassScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FieldScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$PropertyScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$RootScope;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class Scope {
        private int level;
        private final String name;
        private Scope parent;

        @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u00018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0010\u001a\u00020\n2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0012J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u000eJ\u0006\u0010\u0015\u001a\u00020\nJ\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\fJ\u001a\u0010\u001a\u001a\u00020\n2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ\u001a\u0010\u001c\u001a\u00020\n2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ*\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u00002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000eH\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\u00032\u0006\u0010 \u001a\u00020\u000eH\u0016J\u0010\u0010\"\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0014\u0010#\u001a\u00020\n2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\f0%J\u0006\u0010&\u001a\u00020\nJ\u0016\u0010'\u001a\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u0016R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u001a\u0010(\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u000f\"\u0004\b*\u0010+R\u001e\u0010-\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u000fR\u001e\u0010/\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u000fR\u001e\u00101\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u000fR$\u00103\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000f\"\u0004\b5\u0010+R\u0014\u00106\u001a\b\u0012\u0004\u0012\u0002070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "name", "", "<init>", "(Ljava/lang/String;)V", "extraEndLocations", "", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "", "sourceLocations", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$SourceLocation;", "isInComposable", "", "()Z", "realizeGroup", "makeEnd", "Lkotlin/Function0;", "recordComposableCall", "withGroups", "realizeAllDirectChildren", "recordSourceLocation", "call", "Lorg/jetbrains/kotlin/ir/IrElement;", "location", "markReturn", "extraEndLocation", "markJump", "markCoalescableGroup", "scope", "calculateHasSourceInformation", "sourceInformationEnabled", "calculateSourceInfo", "sourceLocationOf", "addProvisionalSourceLocations", "locations", "", "realizeCoalescableGroup", "realizeEndCalls", "hasDefaultsGroup", "getHasDefaultsGroup", "setHasDefaultsGroup", "(Z)V", "value", "hasComposableCallsWithGroups", "getHasComposableCallsWithGroups", "hasComposableCalls", "getHasComposableCalls", "hasReturn", "getHasReturn", "hasJump", "getHasJump", "setHasJump", "coalescableChildren", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope$CoalescableGroupInfo;", "CoalescableGroupInfo", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static abstract class BlockScope extends Scope {
            private final List<CoalescableGroupInfo> coalescableChildren;
            private final List<Function1<IrExpression, Unit>> extraEndLocations;
            private boolean hasComposableCalls;
            private boolean hasComposableCallsWithGroups;
            private boolean hasDefaultsGroup;
            private boolean hasJump;
            private boolean hasReturn;
            private final List<SourceLocation> sourceLocations;

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope$CoalescableGroupInfo;", "", "scope", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "realizeGroup", "Lkotlin/Function0;", "", "makeEnd", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "shouldRealize", "", "getShouldRealize", "()Z", "setShouldRealize", "(Z)V", "realized", "realize", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
            public static final class CoalescableGroupInfo {
                private final Function0<IrExpression> makeEnd;
                private final Function0<Unit> realizeGroup;
                private boolean realized;
                private final BlockScope scope;
                private boolean shouldRealize;

                public CoalescableGroupInfo(BlockScope blockScope, Function0<Unit> function0, Function0<? extends IrExpression> function1) {
                    blockScope.getClass();
                    function0.getClass();
                    function1.getClass();
                    this.scope = blockScope;
                    this.realizeGroup = function0;
                    this.makeEnd = function1;
                }

                public final boolean getShouldRealize() {
                    return this.shouldRealize;
                }

                public final void realize() {
                    if (this.realized) {
                        return;
                    }
                    this.realized = true;
                    boolean z = this.shouldRealize;
                    BlockScope blockScope = this.scope;
                    if (!z) {
                        blockScope.realizeCoalescableGroup();
                    } else {
                        blockScope.realizeGroup(this.makeEnd);
                        this.realizeGroup.invoke();
                    }
                }

                public final void setShouldRealize(boolean z) {
                    this.shouldRealize = z;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BlockScope(String str) {
                super(str, null);
                str.getClass();
                this.extraEndLocations = new ArrayList();
                this.sourceLocations = new ArrayList();
                this.coalescableChildren = new ArrayList();
            }

            public static CharSequence a(IrFileEntry irFileEntry, Ref.BooleanRef booleanRef, SourceLocation sourceLocation) {
                String str;
                sourceLocation.getClass();
                sourceLocation.markUsed();
                Object objValueOf = irFileEntry != null ? Integer.valueOf(irFileEntry.getLineNumber(sourceLocation.getElement().getStartOffset())) : "";
                if (sourceLocation.getElement().getStartOffset() < sourceLocation.getElement().getEndOffset()) {
                    str = "@" + sourceLocation.getElement().getStartOffset() + 'L' + (sourceLocation.getElement().getEndOffset() - sourceLocation.getElement().getStartOffset());
                } else {
                    str = "@" + sourceLocation.getElement().getStartOffset();
                }
                if (!sourceLocation.getRepeatable() || booleanRef.element) {
                    return objValueOf + str;
                }
                booleanRef.element = true;
                return "*" + objValueOf + str;
            }

            public final void addProvisionalSourceLocations(List<? extends SourceLocation> locations) {
                locations.getClass();
                CollectionsKt.addAll(this.sourceLocations, locations);
            }

            public boolean calculateHasSourceInformation(boolean sourceInformationEnabled) {
                return sourceInformationEnabled && !this.sourceLocations.isEmpty();
            }

            public String calculateSourceInfo(boolean sourceInformationEnabled) {
                IrFile declaration;
                if (!sourceInformationEnabled || this.sourceLocations.isEmpty()) {
                    return null;
                }
                List<SourceLocation> list = this.sourceLocations;
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    SourceLocation sourceLocation = (SourceLocation) obj;
                    if (!sourceLocation.getUsed() && sourceLocation.getElement().getStartOffset() != -1 && sourceLocation.getElement().getEndOffset() != -1) {
                        arrayList.add(obj);
                    }
                }
                List listDistinct = CollectionsKt.distinct(arrayList);
                final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                FileScope fileScope = getFileScope();
                final IrFileEntry fileEntry = (fileScope == null || (declaration = fileScope.getDeclaration()) == null) ? null : declaration.getFileEntry();
                if (listDistinct.isEmpty()) {
                    return null;
                }
                return CollectionsKt.joinToString$default(listDistinct, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: hd2
                    public final Object invoke(Object obj2) {
                        return ComposableFunctionBodyTransformer.Scope.BlockScope.a(fileEntry, booleanRef, (ComposableFunctionBodyTransformer.Scope.SourceLocation) obj2);
                    }
                }, 30, (Object) null);
            }

            public final boolean getHasComposableCalls() {
                return this.hasComposableCalls;
            }

            public final boolean getHasComposableCallsWithGroups() {
                return this.hasComposableCallsWithGroups;
            }

            public final boolean getHasDefaultsGroup() {
                return this.hasDefaultsGroup;
            }

            public final boolean getHasJump() {
                return this.hasJump;
            }

            public final boolean getHasReturn() {
                return this.hasReturn;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope
            public boolean isInComposable() {
                Scope parent = getParent();
                if (parent != null) {
                    return parent.isInComposable();
                }
                return false;
            }

            public final void markCoalescableGroup(BlockScope scope, Function0<Unit> realizeGroup, Function0<? extends IrExpression> makeEnd) {
                scope.getClass();
                realizeGroup.getClass();
                makeEnd.getClass();
                addProvisionalSourceLocations(scope.sourceLocations);
                this.coalescableChildren.add(new CoalescableGroupInfo(scope, realizeGroup, makeEnd));
            }

            public final void markJump(Function1<? super IrExpression, Unit> extraEndLocation) {
                extraEndLocation.getClass();
                this.hasJump = true;
                UtilsKt.push(this.extraEndLocations, extraEndLocation);
            }

            public final void markReturn(Function1<? super IrExpression, Unit> extraEndLocation) {
                extraEndLocation.getClass();
                this.hasReturn = true;
                UtilsKt.push(this.extraEndLocations, extraEndLocation);
            }

            public final void realizeAllDirectChildren() {
                if (this.coalescableChildren.isEmpty()) {
                    return;
                }
                List<CoalescableGroupInfo> list = this.coalescableChildren;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.get(i).setShouldRealize(true);
                }
            }

            public final void realizeCoalescableGroup() {
                List<CoalescableGroupInfo> list = this.coalescableChildren;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.get(i).realize();
                }
            }

            public void realizeEndCalls(Function0<? extends IrExpression> makeEnd) {
                makeEnd.getClass();
                List<Function1<IrExpression, Unit>> list = this.extraEndLocations;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.get(i).invoke(makeEnd.invoke());
                }
            }

            public final void realizeGroup(Function0<? extends IrExpression> makeEnd) {
                realizeCoalescableGroup();
                if (makeEnd != null) {
                    realizeEndCalls(makeEnd);
                }
            }

            public final void recordComposableCall(boolean withGroups) {
                this.hasComposableCalls = true;
                if (withGroups) {
                    this.hasComposableCallsWithGroups = true;
                }
                if (this.coalescableChildren.isEmpty()) {
                    return;
                }
                ((CoalescableGroupInfo) CollectionsKt.last(this.coalescableChildren)).setShouldRealize(true);
            }

            public final SourceLocation recordSourceLocation(IrElement call, SourceLocation location) {
                call.getClass();
                if (location == null) {
                    location = sourceLocationOf(call);
                }
                this.sourceLocations.add(location);
                return location;
            }

            public final void setHasDefaultsGroup(boolean z) {
                this.hasDefaultsGroup = z;
            }

            public final void setHasJump(boolean z) {
                this.hasJump = z;
            }

            public SourceLocation sourceLocationOf(IrElement call) {
                call.getClass();
                return new SourceLocation(call);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BranchScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "<init>", "()V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class BranchScope extends BlockScope {
            public BranchScope() {
                super("branch");
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0012\u001a\u00020\u000eJ\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$CallScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;", "<init>", "(Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;)V", "getExpression", "()Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "isInComposable", "", "()Z", "value", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "marker", "getMarker", "()Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "allocateMarker", "getNameForTemporary", "", "nameHint", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class CallScope extends Scope {
            private final IrFunctionAccessExpression expression;
            private IrVariable marker;
            private final ComposableFunctionBodyTransformer transformer;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public CallScope(IrFunctionAccessExpression irFunctionAccessExpression, ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
                super("call", null);
                irFunctionAccessExpression.getClass();
                composableFunctionBodyTransformer.getClass();
                this.expression = irFunctionAccessExpression;
                this.transformer = composableFunctionBodyTransformer;
            }

            private final String getNameForTemporary(String nameHint) {
                String nameForTemporary;
                FunctionScope functionScope = getFunctionScope();
                if (functionScope != null && (nameForTemporary = functionScope.getNameForTemporary(nameHint)) != null) {
                    return nameForTemporary;
                }
                k2d.a("Expected to be in a function");
                return null;
            }

            public final IrVariable allocateMarker() {
                IrVariable irVariable = this.marker;
                if (irVariable != null) {
                    return irVariable;
                }
                ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.transformer;
                IrVariableImpl irVariableImplIrTemporary$default = AbstractComposeLowering.irTemporary$default(composableFunctionBodyTransformer, composableFunctionBodyTransformer.irCurrentMarker(getMyComposer()), getNameForTemporary("marker"), null, false, null, 28, null);
                this.marker = irVariableImplIrTemporary$default;
                return irVariableImplIrTemporary$default;
            }

            public final IrFunctionAccessExpression getExpression() {
                return this.expression;
            }

            public final IrVariable getMarker() {
                return this.marker;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope
            public boolean isInComposable() {
                Scope parent = getParent();
                return parent != null && parent.isInComposable();
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\b\"\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$CaptureScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "<init>", "()V", "value", "", "hasCapturedComposableCall", "getHasCapturedComposableCall", "()Z", "forceInlinedLambdaGroup", "getForceInlinedLambdaGroup", "setForceInlinedLambdaGroup", "(Z)V", "markCapturedComposableCall", "", "sourceLocationOf", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$SourceLocation;", "call", "Lorg/jetbrains/kotlin/ir/IrElement;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class CaptureScope extends BlockScope {
            private boolean forceInlinedLambdaGroup;
            private boolean hasCapturedComposableCall;

            public CaptureScope() {
                super("capture");
            }

            public final boolean getForceInlinedLambdaGroup() {
                return this.forceInlinedLambdaGroup;
            }

            public final boolean getHasCapturedComposableCall() {
                return this.hasCapturedComposableCall;
            }

            public final void markCapturedComposableCall() {
                this.hasCapturedComposableCall = true;
            }

            public final void setForceInlinedLambdaGroup(boolean z) {
                this.forceInlinedLambdaGroup = z;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public SourceLocation sourceLocationOf(final IrElement call) {
                call.getClass();
                return new SourceLocation(call) { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer$Scope$CaptureScope$sourceLocationOf$1
                    @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.SourceLocation
                    public boolean getRepeatable() {
                        return true;
                    }
                };
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$ClassScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "name", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class ClassScope extends Scope {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ClassScope(Name name) {
                super("class " + name.asString(), null);
                name.getClass();
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FieldScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "name", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class FieldScope extends Scope {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public FieldScope(Name name) {
                super("field " + name.asString(), null);
                name.getClass();
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FileScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "fileScope", "getFileScope", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FileScope;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class FileScope extends Scope {
            private final IrFile declaration;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public FileScope(IrFile irFile) {
                super("file " + IrDeclarationsKt.getName(irFile), null);
                irFile.getClass();
                this.declaration = irFile;
            }

            public final IrFile getDeclaration() {
                return this.declaration;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope
            public FileScope getFileScope() {
                return this;
            }
        }

        @Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001pB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\u0006\u0010F\u001a\u00020EJ\b\u0010G\u001a\u00020HH\u0002J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u0016J\b\u0010M\u001a\u00020HH\u0002J\u0010\u0010N\u001a\u00020\u000b2\u0006\u0010O\u001a\u00020\u000bH\u0016J\u0012\u0010P\u001a\u0004\u0018\u00010H2\u0006\u0010O\u001a\u00020\u000bH\u0016J\u000e\u0010X\u001a\u00020\u001c2\u0006\u0010Y\u001a\u00020\u001cJ\u0010\u0010^\u001a\u00020H2\b\u0010_\u001a\u0004\u0018\u00010HJ2\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020\u000b2\f\u0010f\u001a\b\u0012\u0004\u0012\u00020g0S2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020i0S2\u0006\u0010K\u001a\u00020jJA\u0010k\u001a\u00020d29\u0010l\u001a5\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bn\u0012\b\bo\u0012\u0004\b\b(e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0S\u0012\n\u0012\b\u0012\u0004\u0012\u00020i0S\u0012\u0004\u0012\u00020g0mR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0011\u0010\r\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0011\u0010\u000e\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u0017R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\fR\u0014\u0010\u001f\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0016\u0010\"\u001a\u0004\u0018\u00010#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\"\u0010'\u001a\u0004\u0018\u00010#2\b\u0010&\u001a\u0004\u0018\u00010#@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010%R\"\u0010*\u001a\u0004\u0018\u00010)2\b\u0010&\u001a\u0004\u0018\u00010)@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\"\u0010.\u001a\u0004\u0018\u00010-2\b\u0010&\u001a\u0004\u0018\u00010-@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001e\u00101\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001e\u00104\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u00103R\u001c\u00106\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00100\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\f\"\u0004\b<\u0010\u0017R\u001a\u0010=\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\f\"\u0004\b?\u0010\u0017R\u0011\u0010@\u001a\u00020A¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010Q\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\fR\u0017\u0010R\u001a\b\u0012\u0004\u0012\u00020#0S¢\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u0011\u0010V\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\bW\u00103R\u0011\u0010Z\u001a\u00020[¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010]R\u0014\u0010`\u001a\b\u0012\u0004\u0012\u00020b0aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006q"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FunctionScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer;)V", "getFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "isInlinedLambda", "", "()Z", "isCrossinlineLambda", "inComposableCall", "getInComposableCall", "metrics", "Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "getMetrics", "()Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "hasInlineEarlyReturn", "getHasInlineEarlyReturn", "setHasInlineEarlyReturn", "(Z)V", "hasAnyEarlyReturn", "getHasAnyEarlyReturn", "setHasAnyEarlyReturn", "lastTemporaryIndex", "", "nextTemporaryIndex", "isInComposable", "functionScope", "getFunctionScope", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FunctionScope;", "nearestComposer", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "getNearestComposer", "()Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "value", "composerParameter", "getComposerParameter", "Landroidx/compose/compiler/plugins/kotlin/lower/IrDefaultBitMaskValue;", "defaultParameter", "getDefaultParameter", "()Landroidx/compose/compiler/plugins/kotlin/lower/IrDefaultBitMaskValue;", "Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;", "changedParameter", "getChangedParameter", "()Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;", "realValueParamCount", "getRealValueParamCount", "()I", "slotCount", "getSlotCount", "dirty", "getDirty", "setDirty", "(Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;)V", "outerGroupRequired", "getOuterGroupRequired", "setOuterGroupRequired", "preserveIrShape", "getPreserveIrShape", "setPreserveIrShape", "markerPreamble", "Lorg/jetbrains/kotlin/ir/expressions/IrContainerExpression;", "getMarkerPreamble", "()Lorg/jetbrains/kotlin/ir/expressions/IrContainerExpression;", "marker", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "allocateMarker", "parameterInformation", "", "sourceLocationOf", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$SourceLocation;", "call", "Lorg/jetbrains/kotlin/ir/IrElement;", "callInformation", "calculateHasSourceInformation", "sourceInformationEnabled", "calculateSourceInfo", "isComposable", "allTrackedParams", "", "getAllTrackedParams", "()Ljava/util/List;", "valueArgsStart", "getValueArgsStart", "defaultIndexForSlotIndex", "index", "usedParams", "", "getUsedParams", "()[Z", "getNameForTemporary", "nameHint", "intrinsicRememberFixups", "", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FunctionScope$IntrinsicRememberFixup;", "recordIntrinsicRememberFixUp", "", "isMemoizedLambda", "args", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "metas", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "applyIntrinsicRememberFixups", "invalidExpr", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "IntrinsicRememberFixup", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class FunctionScope extends BlockScope {
            private final List<IrValueParameter> allTrackedParams;
            private IrChangedBitMaskValue changedParameter;
            private IrValueParameter composerParameter;
            private IrDefaultBitMaskValue defaultParameter;
            private IrChangedBitMaskValue dirty;
            private final IrFunction function;
            private boolean hasAnyEarlyReturn;
            private boolean hasInlineEarlyReturn;
            private final List<IntrinsicRememberFixup> intrinsicRememberFixups;
            private final boolean isComposable;
            private int lastTemporaryIndex;
            private IrVariable marker;
            private final IrContainerExpression markerPreamble;
            private final FunctionMetrics metrics;
            private boolean outerGroupRequired;
            private boolean preserveIrShape;
            private int realValueParamCount;
            private int slotCount;
            private final ComposableFunctionBodyTransformer transformer;
            private final boolean[] usedParams;
            private final int valueArgsStart;

            @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$FunctionScope$IntrinsicRememberFixup;", "", "isMemoizedLambda", "", "args", "", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "metas", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "call", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "<init>", "(ZLjava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/ir/expressions/IrCall;)V", "()Z", "getArgs", "()Ljava/util/List;", "getMetas", "getCall", "()Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
            public static final class IntrinsicRememberFixup {
                private final List<IrExpression> args;
                private final IrCall call;
                private final boolean isMemoizedLambda;
                private final List<CallArgumentMeta> metas;

                public IntrinsicRememberFixup(boolean z, List<? extends IrExpression> list, List<CallArgumentMeta> list2, IrCall irCall) {
                    list.getClass();
                    list2.getClass();
                    irCall.getClass();
                    this.isMemoizedLambda = z;
                    this.args = list;
                    this.metas = list2;
                    this.call = irCall;
                }

                public final List<IrExpression> getArgs() {
                    return this.args;
                }

                public final IrCall getCall() {
                    return this.call;
                }

                public final List<CallArgumentMeta> getMetas() {
                    return this.metas;
                }

                /* JADX INFO: renamed from: isMemoizedLambda, reason: from getter */
                public final boolean getIsMemoizedLambda() {
                    return this.isMemoizedLambda;
                }
            }

            @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[IrParameterKind.values().length];
                    try {
                        iArr[IrParameterKind.DispatchReceiver.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[IrParameterKind.ExtensionReceiver.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[IrParameterKind.Context.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[IrParameterKind.Regular.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public FunctionScope(IrFunction irFunction, ComposableFunctionBodyTransformer composableFunctionBodyTransformer) {
                IrValueParameter dispatchReceiverParameter;
                IrType type;
                IrClassSymbol classOrNull;
                IrClass owner;
                super("fun " + irFunction.getName().asString());
                irFunction.getClass();
                composableFunctionBodyTransformer.getClass();
                this.function = irFunction;
                this.transformer = composableFunctionBodyTransformer;
                this.metrics = composableFunctionBodyTransformer.metricsFor(irFunction);
                this.markerPreamble = ComposableFunctionBodyTransformerKt.mutableStatementContainer(composableFunctionBodyTransformer.getContext());
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Iterator it = irFunction.getParameters().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        this.slotCount += this.realValueParamCount;
                        if (Intrinsics.areEqual(this.function.getOrigin(), IrDeclarationOrigin.Companion.getLOCAL_FUNCTION_FOR_LAMBDA())) {
                            this.slotCount++;
                        }
                        this.changedParameter = this.composerParameter != null ? new IrChangedBitMaskValueImpl(this.transformer, arrayList2, this.slotCount) : null;
                        this.defaultParameter = arrayList.isEmpty() ? null : new IrDefaultBitMaskValueImpl(this.transformer, arrayList, this.realValueParamCount);
                        this.isComposable = this.composerParameter != null;
                        List listCreateListBuilder = CollectionsKt.createListBuilder();
                        int i = this.realValueParamCount;
                        List parameters = this.function.getParameters();
                        int size = parameters.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            IrValueParameter irValueParameter = (IrValueParameter) parameters.get(i2);
                            if (irValueParameter.getKind() == IrParameterKind.Context || irValueParameter.getKind() == IrParameterKind.ExtensionReceiver) {
                                listCreateListBuilder.add(irValueParameter);
                            }
                        }
                        List parameters2 = this.function.getParameters();
                        int size2 = parameters2.size();
                        for (int i3 = 0; i3 < size2; i3++) {
                            IrValueParameter irValueParameter2 = (IrValueParameter) parameters2.get(i3);
                            if (i > 0 && irValueParameter2.getKind() == IrParameterKind.Regular) {
                                i--;
                                listCreateListBuilder.add(irValueParameter2);
                            }
                        }
                        List parameters3 = this.function.getParameters();
                        int size3 = parameters3.size();
                        for (int i4 = 0; i4 < size3; i4++) {
                            IrValueParameter irValueParameter3 = (IrValueParameter) parameters3.get(i4);
                            if (irValueParameter3.getKind() == IrParameterKind.DispatchReceiver) {
                                listCreateListBuilder.add(irValueParameter3);
                            }
                        }
                        List<IrValueParameter> listBuild = CollectionsKt.build(listCreateListBuilder);
                        this.allTrackedParams = listBuild;
                        Iterator<IrValueParameter> it2 = listBuild.iterator();
                        int i5 = 0;
                        while (true) {
                            if (!it2.hasNext()) {
                                i5 = -1;
                                break;
                            } else if (it2.next().getKind() == IrParameterKind.Regular) {
                                break;
                            } else {
                                i5++;
                            }
                        }
                        this.valueArgsStart = i5;
                        int i6 = this.slotCount;
                        boolean[] zArr = new boolean[i6];
                        for (int i7 = 0; i7 < i6; i7++) {
                            zArr[i7] = false;
                        }
                        this.usedParams = zArr;
                        if (this.isComposable && (Intrinsics.areEqual(this.function.getOrigin(), IrDeclarationOrigin.Companion.getLOCAL_FUNCTION_FOR_LAMBDA()) || ((dispatchReceiverParameter = this.function.getDispatchReceiverParameter()) != null && (type = dispatchReceiverParameter.getType()) != null && (classOrNull = IrTypesKt.getClassOrNull(type)) != null && (owner = classOrNull.getOwner()) != null && AdditionalIrUtilsKt.isLocal(owner)))) {
                            zArr[this.slotCount - 1] = true;
                        }
                        this.intrinsicRememberFixups = new ArrayList();
                        return;
                    }
                    IrValueParameter irValueParameter4 = (IrValueParameter) it.next();
                    int i8 = WhenMappings.$EnumSwitchMapping$0[irValueParameter4.getKind().ordinal()];
                    if (i8 == 1 || i8 == 2 || i8 == 3) {
                        this.slotCount++;
                    } else {
                        if (i8 != 4) {
                            bu8.a();
                            throw null;
                        }
                        String strAsString = irValueParameter4.getName().asString();
                        strAsString.getClass();
                        ComposeNames composeNames = ComposeNames.INSTANCE;
                        if (Intrinsics.areEqual(strAsString, composeNames.getComposerParameter().getIdentifier())) {
                            this.composerParameter = irValueParameter4;
                        } else {
                            String identifier = composeNames.getDefaultParameter().getIdentifier();
                            identifier.getClass();
                            if (StringsKt.startsWith$default(strAsString, identifier, false, 2, (Object) null)) {
                                arrayList.add(irValueParameter4);
                            } else {
                                String identifier2 = composeNames.getChangedParameter().getIdentifier();
                                identifier2.getClass();
                                if (StringsKt.startsWith$default(strAsString, identifier2, false, 2, (Object) null)) {
                                    arrayList2.add(irValueParameter4);
                                } else if (!StringsKt.startsWith$default(strAsString, "$context_receiver_", false, 2, (Object) null) && !StringsKt.startsWith$default(strAsString, "$name$for$destructuring", false, 2, (Object) null) && !StringsKt.startsWith$default(strAsString, "$noName_", false, 2, (Object) null) && !Intrinsics.areEqual(strAsString, "$this")) {
                                    this.realValueParamCount++;
                                }
                            }
                        }
                    }
                }
            }

            private final String callInformation() {
                return ComposableFunctionBodyTransformerKt.callInformation(this.function);
            }

            private final int nextTemporaryIndex() {
                int i = this.lastTemporaryIndex;
                this.lastTemporaryIndex = i + 1;
                return i;
            }

            private final String parameterInformation() {
                boolean z = this.transformer.emitParameterNames;
                IrFunction irFunction = this.function;
                return !z ? ComposableFunctionBodyTransformerKt.parameterInformation(irFunction) : ComposableFunctionBodyTransformerKt.parameterNameInformation(irFunction);
            }

            public final IrVariable allocateMarker() {
                IrVariable irVariable = this.marker;
                if (irVariable != null) {
                    return irVariable;
                }
                Scope parent = getParent();
                if (isInlinedLambda() && !this.isComposable && (parent instanceof CallScope)) {
                    return ((CallScope) parent).allocateMarker();
                }
                ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.transformer;
                IrVariableImpl irVariableImplIrTemporary$default = AbstractComposeLowering.irTemporary$default(composableFunctionBodyTransformer, composableFunctionBodyTransformer.irCurrentMarker(getMyComposer()), getNameForTemporary("marker"), null, false, null, 28, null);
                this.markerPreamble.getStatements().add(irVariableImplIrTemporary$default);
                this.marker = irVariableImplIrTemporary$default;
                return irVariableImplIrTemporary$default;
            }

            public final void applyIntrinsicRememberFixups(Function3<? super Boolean, ? super List<? extends IrExpression>, ? super List<CallArgumentMeta>, ? extends IrExpression> invalidExpr) {
                invalidExpr.getClass();
                List<IntrinsicRememberFixup> list = this.intrinsicRememberFixups;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    IntrinsicRememberFixup intrinsicRememberFixup = list.get(i);
                    intrinsicRememberFixup.getCall().getArguments().set(1, (IrExpression) invalidExpr.invoke(Boolean.valueOf(intrinsicRememberFixup.getIsMemoizedLambda()), intrinsicRememberFixup.getArgs(), intrinsicRememberFixup.getMetas()));
                }
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public boolean calculateHasSourceInformation(boolean sourceInformationEnabled) {
                IrFunction irFunction = this.function;
                if (!sourceInformationEnabled) {
                    return irFunction.getVisibility().isPublicAPI();
                }
                if (!ComposableFunctionBodyTransformerKt.isLambda(irFunction) || isInlinedLambda()) {
                    return true;
                }
                return super.calculateHasSourceInformation(sourceInformationEnabled);
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public String calculateSourceInfo(boolean sourceInformationEnabled) {
                if (!sourceInformationEnabled) {
                    if (!this.function.getVisibility().isPublicAPI()) {
                        return null;
                    }
                    return callInformation() + parameterInformation();
                }
                StringBuilder sb = new StringBuilder();
                sb.append(callInformation());
                sb.append(parameterInformation());
                String strCalculateSourceInfo = super.calculateSourceInfo(sourceInformationEnabled);
                if (strCalculateSourceInfo == null) {
                    strCalculateSourceInfo = "";
                }
                sb.append(strCalculateSourceInfo);
                sb.append(':');
                sb.append(ComposableFunctionBodyTransformerKt.sourceFileInformation(this.function));
                return sb.toString();
            }

            public final int defaultIndexForSlotIndex(int index) {
                return index - this.valueArgsStart;
            }

            public final List<IrValueParameter> getAllTrackedParams() {
                return this.allTrackedParams;
            }

            public final IrChangedBitMaskValue getChangedParameter() {
                return this.changedParameter;
            }

            public final IrValueParameter getComposerParameter() {
                return this.composerParameter;
            }

            public final IrDefaultBitMaskValue getDefaultParameter() {
                return this.defaultParameter;
            }

            public final IrChangedBitMaskValue getDirty() {
                return this.dirty;
            }

            public final IrFunction getFunction() {
                return this.function;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope
            public FunctionScope getFunctionScope() {
                return this;
            }

            public final boolean getHasAnyEarlyReturn() {
                return this.hasAnyEarlyReturn;
            }

            public final boolean getHasInlineEarlyReturn() {
                return this.hasInlineEarlyReturn;
            }

            public final boolean getInComposableCall() {
                IrCall expression;
                Scope parent = getParent();
                CallScope callScope = parent instanceof CallScope ? (CallScope) parent : null;
                if (callScope != null && (expression = callScope.getExpression()) != null) {
                    ComposableFunctionBodyTransformer composableFunctionBodyTransformer = this.transformer;
                    if (expression instanceof IrCall) {
                        IrCall irCall = expression;
                        if (composableFunctionBodyTransformer.isComposableCall(irCall) || composableFunctionBodyTransformer.isSyntheticComposableCall(irCall)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            public final IrContainerExpression getMarkerPreamble() {
                return this.markerPreamble;
            }

            public final FunctionMetrics getMetrics() {
                return this.metrics;
            }

            public final String getNameForTemporary(String nameHint) {
                int iNextTemporaryIndex = nextTemporaryIndex();
                if (nameHint == null) {
                    return "tmp" + iNextTemporaryIndex;
                }
                return "tmp" + iNextTemporaryIndex + '_' + nameHint;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope
            public IrValueParameter getNearestComposer() {
                IrValueParameter irValueParameter = this.composerParameter;
                return irValueParameter == null ? super.getNearestComposer() : irValueParameter;
            }

            public final boolean getOuterGroupRequired() {
                return this.outerGroupRequired;
            }

            public final boolean getPreserveIrShape() {
                return this.preserveIrShape;
            }

            public final int getRealValueParamCount() {
                return this.realValueParamCount;
            }

            public final int getSlotCount() {
                return this.slotCount;
            }

            public final boolean[] getUsedParams() {
                return this.usedParams;
            }

            public final int getValueArgsStart() {
                return this.valueArgsStart;
            }

            /* JADX INFO: renamed from: isComposable, reason: from getter */
            public final boolean getIsComposable() {
                return this.isComposable;
            }

            public final boolean isCrossinlineLambda() {
                return this.transformer.inlineLambdaInfo.isCrossinlineLambda(this.function);
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope, androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope
            public boolean isInComposable() {
                Scope parent;
                return this.isComposable || (this.transformer.inlineLambdaInfo.preservesComposableScope(this.function) && (parent = getParent()) != null && parent.isInComposable());
            }

            public final boolean isInlinedLambda() {
                return this.transformer.inlineLambdaInfo.isInlineLambda(this.function);
            }

            public final void recordIntrinsicRememberFixUp(boolean isMemoizedLambda, List<? extends IrExpression> args, List<CallArgumentMeta> metas, IrCall call) {
                IrChangedBitMaskValue maskParam;
                Object next;
                ParamMeta paramRef;
                ParamMeta paramRef2;
                args.getClass();
                metas.getClass();
                call.getClass();
                Iterator<T> it = metas.iterator();
                do {
                    maskParam = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        paramRef2 = ((CallArgumentMeta) next).getParamRef();
                    }
                } while (!((paramRef2 != null ? paramRef2.getMaskParam() : null) instanceof IrChangedBitMaskVariable));
                CallArgumentMeta callArgumentMeta = (CallArgumentMeta) next;
                if (callArgumentMeta != null && (paramRef = callArgumentMeta.getParamRef()) != null) {
                    maskParam = paramRef.getMaskParam();
                }
                if (Intrinsics.areEqual(maskParam, this.dirty)) {
                    this.intrinsicRememberFixups.add(new IntrinsicRememberFixup(isMemoizedLambda, args, metas, call));
                    return;
                }
                Scope parent = getParent();
                while (!(parent instanceof FunctionScope)) {
                    parent.getClass();
                    parent = parent.getParent();
                }
                ((FunctionScope) parent).recordIntrinsicRememberFixUp(isMemoizedLambda, args, metas, call);
            }

            public final void setDirty(IrChangedBitMaskValue irChangedBitMaskValue) {
                this.dirty = irChangedBitMaskValue;
            }

            public final void setHasAnyEarlyReturn(boolean z) {
                this.hasAnyEarlyReturn = z;
            }

            public final void setHasInlineEarlyReturn(boolean z) {
                this.hasInlineEarlyReturn = z;
            }

            public final void setOuterGroupRequired(boolean z) {
                this.outerGroupRequired = z;
            }

            public final void setPreserveIrShape(boolean z) {
                this.preserveIrShape = z;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public SourceLocation sourceLocationOf(IrElement call) {
                call.getClass();
                Scope parent = getParent();
                return (isInlinedLambda() && (parent instanceof BlockScope)) ? ((BlockScope) parent).sourceLocationOf(call) : super.sourceLocationOf(call);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$KeyScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "<init>", "()V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class KeyScope extends BlockScope {
            public KeyScope() {
                super("key");
            }
        }

        @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\"\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nJ\u0016\u0010\u001a\u001a\u00020\f2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001cH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$LoopScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "loop", "Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "<init>", "(Lorg/jetbrains/kotlin/ir/expressions/IrLoop;)V", "getLoop", "()Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "jumpEndLocations", "", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "", "value", "", "needsGroupPerIteration", "getNeedsGroupPerIteration", "()Z", "sourceLocationOf", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$SourceLocation;", "call", "Lorg/jetbrains/kotlin/ir/IrElement;", "markJump", "jump", "Lorg/jetbrains/kotlin/ir/expressions/IrBreakContinue;", "extraEndLocation", "realizeEndCalls", "makeEnd", "Lkotlin/Function0;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class LoopScope extends BlockScope {
            private final List<Function1<IrExpression, Unit>> jumpEndLocations;
            private final IrLoop loop;
            private boolean needsGroupPerIteration;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LoopScope(IrLoop irLoop) {
                super("loop");
                irLoop.getClass();
                this.loop = irLoop;
                this.jumpEndLocations = new ArrayList();
            }

            public final IrLoop getLoop() {
                return this.loop;
            }

            public final boolean getNeedsGroupPerIteration() {
                return this.needsGroupPerIteration;
            }

            public final void markJump(IrBreakContinue jump, Function1<? super IrExpression, Unit> extraEndLocation) {
                jump.getClass();
                extraEndLocation.getClass();
                if (!Intrinsics.areEqual(jump.getLoop(), this.loop)) {
                    super.markJump(extraEndLocation);
                    return;
                }
                setHasJump(true);
                if (jump instanceof IrContinue) {
                    this.needsGroupPerIteration = true;
                }
                UtilsKt.push(this.jumpEndLocations, extraEndLocation);
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public void realizeEndCalls(Function0<? extends IrExpression> makeEnd) {
                makeEnd.getClass();
                super.realizeEndCalls(makeEnd);
                if (this.needsGroupPerIteration) {
                    List<Function1<IrExpression, Unit>> list = this.jumpEndLocations;
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        list.get(i).invoke(makeEnd.invoke());
                    }
                    this.jumpEndLocations.clear();
                }
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public SourceLocation sourceLocationOf(final IrElement call) {
                call.getClass();
                return new SourceLocation(call) { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer$Scope$LoopScope$sourceLocationOf$1
                    @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.SourceLocation
                    public boolean getRepeatable() {
                        return !this.getNeedsGroupPerIteration();
                    }
                };
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$ParametersScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "<init>", "()V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class ParametersScope extends BlockScope {
            public ParametersScope() {
                super("parameters");
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$PropertyScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "name", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class PropertyScope extends Scope {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PropertyScope(Name name) {
                super("val " + name.asString(), null);
                name.getClass();
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$ReturnScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrReturn;", "<init>", "(Lorg/jetbrains/kotlin/ir/expressions/IrReturn;)V", "getExpression", "()Lorg/jetbrains/kotlin/ir/expressions/IrReturn;", "sourceLocationOf", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$SourceLocation;", "call", "Lorg/jetbrains/kotlin/ir/IrElement;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class ReturnScope extends BlockScope {
            private final IrReturn expression;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ReturnScope(IrReturn irReturn) {
                super("return");
                irReturn.getClass();
                this.expression = irReturn;
            }

            public final IrReturn getExpression() {
                return this.expression;
            }

            @Override // androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer.Scope.BlockScope
            public SourceLocation sourceLocationOf(IrElement call) {
                call.getClass();
                Scope parent = getParent();
                return parent instanceof BlockScope ? ((BlockScope) parent).sourceLocationOf(call) : super.sourceLocationOf(call);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$RootScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope;", "<init>", "()V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class RootScope extends Scope {
            public RootScope() {
                super("<root>", null);
            }
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0011"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$SourceLocation;", "", "element", "Lorg/jetbrains/kotlin/ir/IrElement;", "<init>", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "getElement", "()Lorg/jetbrains/kotlin/ir/IrElement;", "repeatable", "", "getRepeatable", "()Z", "value", "used", "getUsed", "markUsed", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static class SourceLocation {
            private final IrElement element;
            private boolean used;

            public SourceLocation(IrElement irElement) {
                irElement.getClass();
                this.element = irElement;
            }

            public final IrElement getElement() {
                return this.element;
            }

            public boolean getRepeatable() {
                return false;
            }

            public final boolean getUsed() {
                return this.used;
            }

            public final void markUsed() {
                this.used = true;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$WhenScope;", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$Scope$BlockScope;", "<init>", "()V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class WhenScope extends BlockScope {
            public WhenScope() {
                super("when");
            }
        }

        private Scope(String str) {
            this.name = str;
        }

        public FileScope getFileScope() {
            Scope scope = this.parent;
            if (scope != null) {
                return scope.getFileScope();
            }
            return null;
        }

        public FunctionScope getFunctionScope() {
            Scope scope = this.parent;
            if (scope != null) {
                return scope.getFunctionScope();
            }
            return null;
        }

        public final int getLevel() {
            return this.level;
        }

        public final IrValueParameter getMyComposer() {
            IrValueParameter nearestComposer = getNearestComposer();
            if (nearestComposer != null) {
                return nearestComposer;
            }
            k2d.a("Not in a composable function");
            return null;
        }

        public final String getName() {
            return this.name;
        }

        public IrValueParameter getNearestComposer() {
            Scope scope = this.parent;
            if (scope != null) {
                return scope.getNearestComposer();
            }
            return null;
        }

        public final Scope getParent() {
            return this.parent;
        }

        public boolean isInComposable() {
            return false;
        }

        public final void setLevel(int i) {
            this.level = i;
        }

        public final void setParent(Scope scope) {
            this.parent = scope;
        }

        public /* synthetic */ Scope(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }
    }

    private final IrExpression irCurrentComposer(Scope.BlockScope blockScope, int i, int i2) {
        IrValueParameter nearestComposer = blockScope.getNearestComposer();
        if (nearestComposer == null) {
            nearestComposer = nearestComposer();
        }
        return irCurrentComposer(i, i2, nearestComposer);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J)\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$ParamMeta;", "", "maskSlot", "", "maskParam", "Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;", "hasNonStaticDefault", "", "<init>", "(ILandroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;Z)V", "getMaskSlot", "()I", "getMaskParam", "()Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;", "setMaskParam", "(Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;)V", "getHasNonStaticDefault", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class ParamMeta {
        private final boolean hasNonStaticDefault;
        private IrChangedBitMaskValue maskParam;
        private final int maskSlot;

        public /* synthetic */ ParamMeta(int i, IrChangedBitMaskValue irChangedBitMaskValue, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? -1 : i, (i2 & 2) != 0 ? null : irChangedBitMaskValue, (i2 & 4) != 0 ? false : z);
        }

        public static /* synthetic */ ParamMeta copy$default(ParamMeta paramMeta, int i, IrChangedBitMaskValue irChangedBitMaskValue, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = paramMeta.maskSlot;
            }
            if ((i2 & 2) != 0) {
                irChangedBitMaskValue = paramMeta.maskParam;
            }
            if ((i2 & 4) != 0) {
                z = paramMeta.hasNonStaticDefault;
            }
            return paramMeta.copy(i, irChangedBitMaskValue, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMaskSlot() {
            return this.maskSlot;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final IrChangedBitMaskValue getMaskParam() {
            return this.maskParam;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getHasNonStaticDefault() {
            return this.hasNonStaticDefault;
        }

        public final ParamMeta copy(int maskSlot, IrChangedBitMaskValue maskParam, boolean hasNonStaticDefault) {
            return new ParamMeta(maskSlot, maskParam, hasNonStaticDefault);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParamMeta)) {
                return false;
            }
            ParamMeta paramMeta = (ParamMeta) other;
            return this.maskSlot == paramMeta.maskSlot && Intrinsics.areEqual(this.maskParam, paramMeta.maskParam) && this.hasNonStaticDefault == paramMeta.hasNonStaticDefault;
        }

        public final boolean getHasNonStaticDefault() {
            return this.hasNonStaticDefault;
        }

        public final IrChangedBitMaskValue getMaskParam() {
            return this.maskParam;
        }

        public final int getMaskSlot() {
            return this.maskSlot;
        }

        public int hashCode() {
            int iHashCode = Integer.hashCode(this.maskSlot) * 31;
            IrChangedBitMaskValue irChangedBitMaskValue = this.maskParam;
            return ((iHashCode + (irChangedBitMaskValue == null ? 0 : irChangedBitMaskValue.hashCode())) * 31) + Boolean.hashCode(this.hasNonStaticDefault);
        }

        public final void setMaskParam(IrChangedBitMaskValue irChangedBitMaskValue) {
            this.maskParam = irChangedBitMaskValue;
        }

        public String toString() {
            return "ParamMeta(maskSlot=" + this.maskSlot + ", maskParam=" + this.maskParam + ", hasNonStaticDefault=" + this.hasNonStaticDefault + ')';
        }

        public ParamMeta(int i, IrChangedBitMaskValue irChangedBitMaskValue, boolean z) {
            this.maskSlot = i;
            this.maskParam = irChangedBitMaskValue;
            this.hasNonStaticDefault = z;
        }

        public ParamMeta() {
            this(0, null, false, 7, null);
        }
    }

    public void lower(IrFile irFile) {
        irFile.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(irFile, this);
        applySourceFixups();
    }

    public static /* synthetic */ IrExpression irCurrentComposer$default(ComposableFunctionBodyTransformer composableFunctionBodyTransformer, Scope.BlockScope blockScope, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = -1;
        }
        if ((i3 & 2) != 0) {
            i2 = -1;
        }
        return composableFunctionBodyTransformer.irCurrentComposer(blockScope, i, i2);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u000bHÆ\u0003JI\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0014\u0010'\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020*HÖ\u0081\u0004J\n\u0010+\u001a\u00020,HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0016\"\u0004\b\u0019\u0010\u0018R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0016¨\u0006-"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$CallArgumentMeta;", "", "fileContainingArg", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "stability", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "isVararg", "", "isProvided", "isStatic", "paramRef", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$ParamMeta;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;ZZZLandroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$ParamMeta;)V", "getFileContainingArg", "()Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "setFileContainingArg", "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;)V", "getStability", "()Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "setStability", "(Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;)V", "()Z", "setVararg", "(Z)V", "setProvided", "setStatic", "getParamRef", "()Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$ParamMeta;", "setParamRef", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableFunctionBodyTransformer$ParamMeta;)V", "isCertain", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class CallArgumentMeta {
        private IrFile fileContainingArg;
        private boolean isProvided;
        private boolean isStatic;
        private boolean isVararg;
        private ParamMeta paramRef;
        private Stability stability;

        public /* synthetic */ CallArgumentMeta(IrFile irFile, Stability stability, boolean z, boolean z2, boolean z3, ParamMeta paramMeta, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(irFile, (i & 2) != 0 ? Stability.INSTANCE.getUnstable() : stability, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3, (i & 32) != 0 ? null : paramMeta);
        }

        public static /* synthetic */ CallArgumentMeta copy$default(CallArgumentMeta callArgumentMeta, IrFile irFile, Stability stability, boolean z, boolean z2, boolean z3, ParamMeta paramMeta, int i, Object obj) {
            if ((i & 1) != 0) {
                irFile = callArgumentMeta.fileContainingArg;
            }
            if ((i & 2) != 0) {
                stability = callArgumentMeta.stability;
            }
            if ((i & 4) != 0) {
                z = callArgumentMeta.isVararg;
            }
            if ((i & 8) != 0) {
                z2 = callArgumentMeta.isProvided;
            }
            if ((i & 16) != 0) {
                z3 = callArgumentMeta.isStatic;
            }
            if ((i & 32) != 0) {
                paramMeta = callArgumentMeta.paramRef;
            }
            boolean z4 = z3;
            ParamMeta paramMeta2 = paramMeta;
            return callArgumentMeta.copy(irFile, stability, z, z2, z4, paramMeta2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrFile getFileContainingArg() {
            return this.fileContainingArg;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Stability getStability() {
            return this.stability;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsVararg() {
            return this.isVararg;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsProvided() {
            return this.isProvided;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsStatic() {
            return this.isStatic;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final ParamMeta getParamRef() {
            return this.paramRef;
        }

        public final CallArgumentMeta copy(IrFile fileContainingArg, Stability stability, boolean isVararg, boolean isProvided, boolean isStatic, ParamMeta paramRef) {
            stability.getClass();
            return new CallArgumentMeta(fileContainingArg, stability, isVararg, isProvided, isStatic, paramRef);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CallArgumentMeta)) {
                return false;
            }
            CallArgumentMeta callArgumentMeta = (CallArgumentMeta) other;
            return Intrinsics.areEqual(this.fileContainingArg, callArgumentMeta.fileContainingArg) && Intrinsics.areEqual(this.stability, callArgumentMeta.stability) && this.isVararg == callArgumentMeta.isVararg && this.isProvided == callArgumentMeta.isProvided && this.isStatic == callArgumentMeta.isStatic && Intrinsics.areEqual(this.paramRef, callArgumentMeta.paramRef);
        }

        public final IrFile getFileContainingArg() {
            return this.fileContainingArg;
        }

        public final ParamMeta getParamRef() {
            return this.paramRef;
        }

        public final Stability getStability() {
            return this.stability;
        }

        public int hashCode() {
            IrFile irFile = this.fileContainingArg;
            int iHashCode = (((((((((irFile == null ? 0 : irFile.hashCode()) * 31) + this.stability.hashCode()) * 31) + Boolean.hashCode(this.isVararg)) * 31) + Boolean.hashCode(this.isProvided)) * 31) + Boolean.hashCode(this.isStatic)) * 31;
            ParamMeta paramMeta = this.paramRef;
            return iHashCode + (paramMeta != null ? paramMeta.hashCode() : 0);
        }

        public final boolean isCertain() {
            return this.paramRef != null;
        }

        public final boolean isProvided() {
            return this.isProvided;
        }

        public final boolean isStatic() {
            return this.isStatic;
        }

        public final boolean isVararg() {
            return this.isVararg;
        }

        public final void setFileContainingArg(IrFile irFile) {
            this.fileContainingArg = irFile;
        }

        public final void setParamRef(ParamMeta paramMeta) {
            this.paramRef = paramMeta;
        }

        public final void setProvided(boolean z) {
            this.isProvided = z;
        }

        public final void setStability(Stability stability) {
            stability.getClass();
            this.stability = stability;
        }

        public final void setStatic(boolean z) {
            this.isStatic = z;
        }

        public final void setVararg(boolean z) {
            this.isVararg = z;
        }

        public String toString() {
            return "CallArgumentMeta(fileContainingArg=" + this.fileContainingArg + ", stability=" + this.stability + ", isVararg=" + this.isVararg + ", isProvided=" + this.isProvided + ", isStatic=" + this.isStatic + ", paramRef=" + this.paramRef + ')';
        }

        public CallArgumentMeta(IrFile irFile, Stability stability, boolean z, boolean z2, boolean z3, ParamMeta paramMeta) {
            stability.getClass();
            this.fileContainingArg = irFile;
            this.stability = stability;
            this.isVararg = z;
            this.isProvided = z2;
            this.isStatic = z3;
            this.paramRef = paramMeta;
        }
    }
}

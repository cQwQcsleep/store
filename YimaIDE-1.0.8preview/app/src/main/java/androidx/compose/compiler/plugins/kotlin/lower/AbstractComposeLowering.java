package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds;
import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import androidx.compose.compiler.plugins.kotlin.ComposeMetadata;
import androidx.compose.compiler.plugins.kotlin.ComposeNames;
import androidx.compose.compiler.plugins.kotlin.ComposePluginKt;
import androidx.compose.compiler.plugins.kotlin.FeatureFlag;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.FunctionMetrics;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.WeakBindingTraceKt;
import androidx.compose.compiler.plugins.kotlin.analysis.ComposeWritableSlices;
import androidx.compose.compiler.plugins.kotlin.analysis.KnownStableConstructs;
import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityKt;
import androidx.compose.compiler.plugins.kotlin.lower.AbstractComposeLowering;
import androidx.compose.compiler.plugins.kotlin.lower.hiddenfromobjc.AddHiddenFromObjCLoweringKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.extensions.DeclarationFinder;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder;
import org.jetbrains.kotlin.backend.jvm.JvmLoweredDeclarationOrigin;
import org.jetbrains.kotlin.backend.jvm.ir.JvmIrTypeUtilsKt;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.InlineClassRepresentation;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.builders.ExpressionHelpersKt;
import org.jetbrains.kotlin.ir.builders.IrBlockBodyBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.builders.declarations.IrFieldBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrFunctionBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrPropertyBuilder;
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.declarations.impl.IrExternalPackageFragmentImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstKind;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue;
import org.jetbrains.kotlin.ir.expressions.IrGetField;
import org.jetbrains.kotlin.ir.expressions.IrGetObjectValue;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrReturn;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCompositeImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrElseBranchImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrWhenImpl;
import org.jetbrains.kotlin.ir.interpreter.UtilsKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrEnumEntrySymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrReturnTargetSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrVariableSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.types.impl.IrStarProjectionImpl;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.DeepCopyIrTreeWithSymbols;
import org.jetbrains.kotlin.ir.util.DeepCopySymbolRemapper;
import org.jetbrains.kotlin.ir.util.DeepCopyTypeRemapper;
import org.jetbrains.kotlin.ir.util.DescriptorsRemapper;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.PatchDeclarationParentsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.KotlinLibraryVersioning;
import org.jetbrains.kotlin.library.metadata.DeserializedSourceFile;
import org.jetbrains.kotlin.load.kotlin.MethodSignatureMappingKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.platform.jvm.JvmPlatformKt;
import org.jetbrains.kotlin.platform.konan.NativePlatformKt;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000È\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'J\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020%0*2\u0006\u0010&\u001a\u00020'J\u000e\u0010+\u001a\u00020,2\u0006\u0010&\u001a\u00020'J\u000e\u00102\u001a\u0002032\u0006\u00104\u001a\u000205J\n\u00106\u001a\u000207*\u000207J\n\u00108\u001a\u000207*\u000207J\n\u00109\u001a\u000207*\u000207J\f\u0010:\u001a\u0004\u0018\u000107*\u000207J\f\u0010;\u001a\u00020<*\u00020<H\u0004J\n\u0010=\u001a\u00020.*\u00020>J\n\u0010?\u001a\u00020.*\u00020@J\n\u0010A\u001a\u00020.*\u00020@J\n\u0010B\u001a\u00020.*\u00020@J\n\u0010C\u001a\u00020.*\u00020@J\n\u0010D\u001a\u00020.*\u00020@J\n\u0010E\u001a\u00020.*\u00020\u001aJ:\u0010F\u001a\u0004\u0018\u00010<*\u00020G2\u0016\b\u0002\u0010H\u001a\u0010\u0012\u0004\u0012\u00020J\u0012\u0006\u0012\u0004\u0018\u00010<0I2\u0014\b\u0002\u0010K\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020L0IJ\u001c\u0010M\u001a\u00020N*\u00020N2\u0006\u0010O\u001a\u00020N2\u0006\u0010P\u001a\u00020.H\u0002J\u0015\u0010Q\u001a\u00020.*\u00020N2\u0006\u0010O\u001a\u00020NH\u0084\u0002J\u0014\u0010R\u001a\u00020N2\n\u0010S\u001a\u00020T\"\u00020.H\u0004J\u0018\u0010U\u001a\u00020<2\u0006\u0010V\u001a\u00020W2\u0006\u0010O\u001a\u00020NH\u0004J\u0018\u0010X\u001a\u00020<2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010P\u001a\u00020<H\u0004J\u0010\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020,H\u0004JW\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020,2\n\b\u0002\u0010^\u001a\u0004\u0018\u00010_2\b\b\u0002\u0010`\u001a\u0002072\n\b\u0002\u0010a\u001a\u0004\u0018\u00010<2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010<2\u0012\u0010c\u001a\n\u0012\u0006\b\u0001\u0012\u00020<0d\"\u00020<H\u0004¢\u0006\u0002\u0010eJ\u001c\u0010f\u001a\u00020,*\u0002072\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u000207H\u0004J4\u0010j\u001a\u00020\\2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<2\u0006\u0010g\u001a\u00020h2\b\b\u0002\u0010m\u001a\u0002072\b\b\u0002\u0010n\u001a\u000207H\u0002J\u0018\u0010o\u001a\u00020\\2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0018\u0010p\u001a\u00020\\2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0018\u0010q\u001a\u00020<2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0018\u0010r\u001a\u00020\\2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0018\u0010s\u001a\u00020<2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0018\u0010t\u001a\u00020<2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0018\u0010u\u001a\u00020\\2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0018\u0010v\u001a\u00020\\2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\"\u0010w\u001a\u00020<2\u0006\u0010x\u001a\u00020y2\u0006\u0010P\u001a\u00020<2\b\b\u0002\u0010z\u001a\u000207H\u0004J\u0018\u0010{\u001a\u00020<2\u0006\u0010x\u001a\u00020y2\u0006\u0010P\u001a\u00020|H\u0004J\u0018\u0010}\u001a\u00020<2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0010\u0010~\u001a\u00020<2\u0006\u0010P\u001a\u00020<H\u0004J\u0018\u0010\u007f\u001a\u00020<2\u0006\u0010k\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0004J\u0012\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0006\u0010P\u001a\u00020NH\u0004J\u0013\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010P\u001a\u00030\u0082\u0001H\u0004J\u0013\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010P\u001a\u00030\u0083\u0001H\u0004J\u0012\u0010\u0080\u0001\u001a\u00030\u0084\u00012\u0006\u0010P\u001a\u00020.H\u0004J\n\u0010\u0085\u0001\u001a\u00030\u0084\u0001H\u0004J;\u0010\u0086\u0001\u001a\u00030\u0087\u00012\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u0007\u0010\u008a\u0001\u001a\u0002072\u0007\u0010\u008b\u0001\u001a\u00020<2\u0013\u0010\u008c\u0001\u001a\u000e\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020<0IH\u0004J<\u0010\u008d\u0001\u001a\u00030\u008e\u00012\u0006\u0010P\u001a\u00020<2\u0007\u0010g\u001a\u00030\u0083\u00012\t\b\u0002\u0010\u008f\u0001\u001a\u0002072\t\b\u0002\u0010\u0090\u0001\u001a\u00020.2\t\b\u0002\u0010^\u001a\u00030\u0091\u0001H\u0004J\u001a\u0010\u0092\u0001\u001a\u00020<2\u0006\u0010z\u001a\u0002072\u0007\u0010]\u001a\u00030\u0093\u0001H\u0004J\u0011\u0010\u0092\u0001\u001a\u00020<2\u0006\u0010Y\u001a\u00020ZH\u0004J\u0014\u0010\u0094\u0001\u001a\u00030\u0095\u00012\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0004J\u001b\u0010\u0098\u0001\u001a\u00020<2\u0007\u0010\u0099\u0001\u001a\u00020<2\u0007\u0010\u009a\u0001\u001a\u00020<H\u0004JE\u0010\u009b\u0001\u001a\u00030\u009c\u00012\b\b\u0002\u0010z\u001a\u0002072\u0007\u0010\u0099\u0001\u001a\u00020<2\u0007\u0010\u009d\u0001\u001a\u00020<2\u0007\u0010\u009e\u0001\u001a\u00020<2\t\b\u0002\u0010\u009f\u0001\u001a\u00020N2\t\b\u0002\u0010 \u0001\u001a\u00020NH\u0004J0\u0010¡\u0001\u001a\u00030\u009c\u00012\b\b\u0002\u0010z\u001a\u0002072\n\b\u0002\u0010^\u001a\u0004\u0018\u00010_2\u000e\u0010¢\u0001\u001a\t\u0012\u0005\u0012\u00030£\u00010*H\u0004J\u001c\u0010¤\u0001\u001a\u00030£\u00012\u0007\u0010\u0099\u0001\u001a\u00020<2\u0007\u0010¥\u0001\u001a\u00020<H\u0004J)\u0010¦\u0001\u001a\u00030§\u00012\u0007\u0010¨\u0001\u001a\u00020<2\t\b\u0002\u0010\u009f\u0001\u001a\u00020N2\t\b\u0002\u0010 \u0001\u001a\u00020NH\u0004JF\u0010©\u0001\u001a\u00030ª\u00012\b\b\u0002\u0010z\u001a\u0002072\n\b\u0002\u0010^\u001a\u0004\u0018\u00010_2\t\b\u0002\u0010\u009f\u0001\u001a\u00020N2\t\b\u0002\u0010 \u0001\u001a\u00020N2\u000e\u0010«\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010*H\u0004J/\u0010¬\u0001\u001a\u00020<2\b\b\u0002\u0010z\u001a\u0002072\n\b\u0002\u0010^\u001a\u0004\u0018\u00010_2\u000e\u0010«\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010*H\u0004J9\u0010\u00ad\u0001\u001a\u00020<2\u0007\u0010\u009f\u0001\u001a\u00020N2\u0007\u0010 \u0001\u001a\u00020N2\u0006\u0010`\u001a\u0002072\u0014\u0010\u009a\u0001\u001a\u000f\u0012\u0005\u0012\u00030®\u0001\u0012\u0004\u0012\u00020L0IH\u0004J\r\u0010¯\u0001\u001a\u00020h*\u00020\u001aH\u0002J\r\u0010°\u0001\u001a\u00020h*\u00020\u001aH\u0002J\r\u0010±\u0001\u001a\u00020h*\u00020\u001aH\u0002J\u000f\u0010²\u0001\u001a\u0004\u0018\u00010%*\u00020\u001aH\u0002J\u000f\u0010³\u0001\u001a\u0004\u0018\u00010<*\u00020\u001aH\u0002J\u0014\u0010´\u0001\u001a\u00030\u0097\u0001*\u00020\u001aH\u0000¢\u0006\u0003\bµ\u0001J\u0013\u0010¶\u0001\u001a\u00030\u0097\u00012\u0007\u0010·\u0001\u001a\u00020hH\u0002J\u0017\u0010¸\u0001\u001a\u00030¹\u0001*\u00020\u001a2\u0007\u0010º\u0001\u001a\u00020.H\u0002J\u0017\u0010»\u0001\u001a\u00030¹\u0001*\u00020\u001a2\u0007\u0010º\u0001\u001a\u00020.H\u0002J\u0017\u0010¼\u0001\u001a\u00020L*\u00020\u001a2\b\u0010½\u0001\u001a\u00030¹\u0001H\u0002J!\u0010¾\u0001\u001a\u00020L*\u00020\u001a2\b\u0010½\u0001\u001a\u00030¹\u00012\b\u0010\u0088\u0001\u001a\u00030¿\u0001H\u0002J\u0017\u0010À\u0001\u001a\u00020.*\u00020<2\n\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u0001J\u001a\u0010À\u0001\u001a\u00020.*\u00030Ã\u00012\n\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u0001H\u0002J\u000f\u0010Ä\u0001\u001a\u00020.*\u0004\u0018\u00010_H\u0002J\u000f\u0010Å\u0001\u001a\u00020.*\u0004\u0018\u00010_H\u0002J\u0019\u0010À\u0001\u001a\u00020.*\u00020@2\n\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u0001H\u0002J\u001e\u0010Æ\u0001\u001a\u00020.*\u0007\u0012\u0002\b\u00030Ç\u00012\n\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u0001H\u0002J\u0011\u0010È\u0001\u001a\u00020h2\u0006\u0010g\u001a\u00020hH\u0004J\"\u0010É\u0001\u001a\u00020\\2\u0007\u0010Ê\u0001\u001a\u00020<2\u0007\u0010Ë\u0001\u001a\u0002072\u0007\u0010Ì\u0001\u001a\u000207J\u000b\u0010Í\u0001\u001a\u00020\\*\u00020<J\f\u0010Õ\u0001\u001a\u00020N*\u00030®\u0001J\u000b\u0010Ö\u0001\u001a\u00020.*\u000205J<\u0010Ü\u0001\u001a\u00020@2\u0007\u0010Ý\u0001\u001a\u00020<2\u0007\u0010\u009f\u0001\u001a\u00020N2\u0007\u0010 \u0001\u001a\u00020N2\u0006\u0010`\u001a\u0002072\u0007\u0010Þ\u0001\u001a\u00020<2\u0007\u0010ß\u0001\u001a\u00020<J?\u0010à\u0001\u001a\u00020<2\u0007\u0010Ý\u0001\u001a\u00020<2\u0006\u0010P\u001a\u00020<2\n\u0010á\u0001\u001a\u0005\u0018\u00010Â\u00012\u0007\u0010â\u0001\u001a\u00020.2\u0007\u0010ã\u0001\u001a\u00020.2\u0007\u0010ä\u0001\u001a\u00020.J\r\u0010ç\u0001\u001a\u00020<*\u00020<H\u0002J/\u0010è\u0001\u001a\u00020<2\u0007\u0010Ý\u0001\u001a\u00020<2\u0007\u0010é\u0001\u001a\u00020<2\t\b\u0002\u0010\u009f\u0001\u001a\u00020N2\t\b\u0002\u0010 \u0001\u001a\u00020NJ&\u0010ê\u0001\u001a\u00020<2\u0007\u0010Ý\u0001\u001a\u00020<2\t\b\u0002\u0010\u009f\u0001\u001a\u00020N2\t\b\u0002\u0010 \u0001\u001a\u00020NJO\u0010ë\u0001\u001a\u00030ì\u0001*\u00030\u0087\u00012\t\b\u0002\u0010\u009f\u0001\u001a\u00020N2\t\b\u0002\u0010 \u0001\u001a\u00020N2\u0006\u0010z\u001a\u0002072\u0010\b\u0002\u0010í\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010*2\u0010\b\u0002\u0010î\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010*J\u0010\u0010÷\u0001\u001a\u0005\u0018\u00010ø\u0001*\u000207H\u0002J-\u0010þ\u0001\u001a\u00020@2\u0006\u0010x\u001a\u00020<2\u0006\u00104\u001a\u0002052\t\b\u0002\u0010\u009f\u0001\u001a\u00020N2\t\b\u0002\u0010 \u0001\u001a\u00020NJ$\u0010[\u001a\u00020@2\u0006\u00104\u001a\u0002052\t\b\u0002\u0010\u009f\u0001\u001a\u00020N2\t\b\u0002\u0010 \u0001\u001a\u00020NJ'\u0010ÿ\u0001\u001a\u00020L*\u0002052\u0007\u0010\u0080\u0002\u001a\u0002052\t\b\u0002\u0010\u0081\u0002\u001a\u00020.H\u0000¢\u0006\u0003\b\u0082\u0002J \u0010\u0083\u0002\u001a\u00020L*\u00030\u0084\u00022\u0007\u0010\u0085\u0002\u001a\u0002052\u0007\u0010\u0086\u0002\u001a\u000205H\u0002J\f\u0010\u009e\u0002\u001a\u0005\u0018\u00010\u009f\u0002H\u0002J\f\u0010 \u0002\u001a\u0005\u0018\u00010\u009f\u0002H\u0002J\f\u0010¡\u0002\u001a\u0005\u0018\u00010\u009f\u0002H\u0002J\u0014\u0010¢\u0002\u001a\u00030\u009f\u00022\b\u0010£\u0002\u001a\u00030\u0083\u0001H\u0002J\u000f\u0010¤\u0002\u001a\u00030®\u0001*\u00030®\u0001H\u0004J\r\u0010¥\u0002\u001a\u00020.*\u000205H\u0004J\r\u0010¦\u0002\u001a\u00020.*\u000205H\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001aX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001aX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0015\u0010-\u001a\u00020.*\u00020/8F¢\u0006\u0006\u001a\u0004\b0\u00101R*\u0010Î\u0001\u001a\u0004\u0018\u00010%8BX\u0082\u0084\u0002¢\u0006\u0018\n\u0006\bÓ\u0001\u0010Ô\u0001\u0012\u0006\bÏ\u0001\u0010Ð\u0001\u001a\u0006\bÑ\u0001\u0010Ò\u0001R!\u0010×\u0001\u001a\u00030®\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÚ\u0001\u0010Û\u0001\u001a\u0006\bØ\u0001\u0010Ù\u0001R\u0010\u0010å\u0001\u001a\u00030®\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010æ\u0001\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010ï\u0001\u001a\u00030®\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010ð\u0001\u001a\u00030®\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010ñ\u0001\u001a\u00030®\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bó\u0001\u0010Û\u0001\u001a\u0006\bò\u0001\u0010Ù\u0001R!\u0010ô\u0001\u001a\u00030®\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bö\u0001\u0010Û\u0001\u001a\u0006\bõ\u0001\u0010Ù\u0001R/\u0010ù\u0001\u001a\u0011\u0012\u0005\u0012\u00030ø\u0001\u0012\u0005\u0012\u00030®\u00010ú\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bý\u0001\u0010Û\u0001\u001a\u0006\bû\u0001\u0010ü\u0001R4\u0010\u0088\u0002\u001a\u0005\u0018\u00010\u0087\u0002*\u00030\u0089\u00022\t\u0010P\u001a\u0005\u0018\u00010\u0087\u00028D@DX\u0084\u000e¢\u0006\u0010\u001a\u0006\b\u008a\u0002\u0010\u008b\u0002\"\u0006\b\u008c\u0002\u0010\u008d\u0002R\u001b\u0010\u008e\u0002\u001a\u00020.*\u0002058DX\u0084\u0004¢\u0006\b\u001a\u0006\b\u008f\u0002\u0010\u0090\u0002R\u001b\u0010\u0091\u0002\u001a\u00020.*\u0002058DX\u0084\u0004¢\u0006\b\u001a\u0006\b\u0092\u0002\u0010\u0090\u0002R\u001b\u0010\u0093\u0002\u001a\u00020.*\u0002058DX\u0084\u0004¢\u0006\b\u001a\u0006\b\u0094\u0002\u0010\u0090\u0002R\u001b\u0010\u0095\u0002\u001a\u00020.*\u0002058DX\u0084\u0004¢\u0006\b\u001a\u0006\b\u0096\u0002\u0010\u0090\u0002R\u0011\u0010\u0097\u0002\u001a\u0004\u0018\u00010\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0098\u0002\u001a\u0004\u0018\u00010\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0099\u0002\u001a\u0004\u0018\u00010\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u009a\u0002\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u009b\u0002\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u009c\u0002\u001a\u00030\u009d\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006§\u0002"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "Lorg/jetbrains/kotlin/ir/visitors/IrElementTransformerVoid;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "getContext", "()Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "getMetrics", "()Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "getStabilityInferencer", "()Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "builtIns", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "getBuiltIns", "()Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "finderForBuiltins", "Lorg/jetbrains/kotlin/backend/common/extensions/DeclarationFinder;", "composerIrClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getComposerIrClass", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "composableIrClass", "getComposableIrClass", "getTopLevelClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getTopLevelClassOrNull", "getTopLevelFunction", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "getTopLevelFunctionOrNull", "getTopLevelFunctions", "", "getTopLevelPropertyGetter", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "enabled", "", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlag;", "getEnabled", "(Landroidx/compose/compiler/plugins/kotlin/FeatureFlag;)Z", "metricsFor", "Landroidx/compose/compiler/plugins/kotlin/FunctionMetrics;", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "unboxInlineClass", "Lorg/jetbrains/kotlin/ir/types/IrType;", "defaultParameterType", "replaceArgumentsWithStarProjections", "unboxType", "unboxValueIfInline", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "hasComposableAnnotation", "Lorg/jetbrains/kotlin/ir/declarations/IrAnnotationContainer;", "isInvoke", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "isComposableCall", "isSyntheticComposableCall", "isComposableLambdaInvoke", "isComposableSingletonGetter", "isComposableSingletonClass", "irStableExpression", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "resolve", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "reportUnknownStability", "", "withBit", "", "index", "value", "get", "bitMask", "values", "", "irGetBit", "param", "Landroidx/compose/compiler/plugins/kotlin/lower/IrDefaultBitMaskValue;", "irSet", "variable", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "irCall", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrCallImpl;", "symbol", "origin", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "returnType", "dispatchReceiver", "extensionReceiver", "args", "", "(Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;Lorg/jetbrains/kotlin/ir/types/IrType;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;[Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)Lorg/jetbrains/kotlin/ir/expressions/impl/IrCallImpl;", "binaryOperator", "name", "Lorg/jetbrains/kotlin/name/Name;", "paramType", "binaryOperatorCall", "lhs", "rhs", "lhsType", "rhsType", "irAnd", "irShl", "irOr", "irBooleanOr", "irOrOr", "irAndAnd", "irXor", "irGreater", "irReturn", "target", "Lorg/jetbrains/kotlin/ir/symbols/IrReturnTargetSymbol;", "type", "irReturnVar", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "irEqual", "irNot", "irNotEqual", "irConst", "Lorg/jetbrains/kotlin/ir/expressions/IrConst;", "", "", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrConstImpl;", "irNull", "irForLoop", "Lorg/jetbrains/kotlin/ir/IrStatement;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "elementType", "subject", "loopBody", "irTemporary", "Lorg/jetbrains/kotlin/ir/declarations/impl/IrVariableImpl;", "irType", "isVar", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "irGet", "Lorg/jetbrains/kotlin/ir/symbols/IrValueSymbol;", "irGetField", "Lorg/jetbrains/kotlin/ir/expressions/IrGetField;", "field", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "irIf", "condition", "body", "irIfThenElse", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrWhenImpl;", "thenPart", "elsePart", "startOffset", "endOffset", "irWhen", "branches", "Lorg/jetbrains/kotlin/ir/expressions/IrBranch;", "irBranch", "result", "irElseBranch", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrElseBranchImpl;", "expression", "irBlock", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "statements", "irComposite", "irLambdaExpression", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "uniqueStabilityFieldName", "uniqueStabilityPropertyName", "uniqueStabilityGetterName", "getMetadataStabilityGetterFun", "getRuntimeStabilityValue", "makeStabilityField", "makeStabilityField$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "buildStabilityField", "fieldName", "buildStabilityPropJvm", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "buildGetter", "buildStabilityPropNonJvm", "buildStabilityGetterJvm", "stabilityProp", "buildStabilityGetterNonJvm", "Lorg/jetbrains/kotlin/ir/declarations/IrPackageFragment;", "isStatic", "fileContainingDependent", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "isGetProperty", "isSpecialCaseMathOp", "areAllArgumentsStatic", "Lorg/jetbrains/kotlin/ir/expressions/IrMemberAccessExpression;", "dexSafeName", "coerceInlineClasses", "argument", "from", "to", "coerceToUnboxed", "unsafeCoerceIntrinsic", "getUnsafeCoerceIntrinsic$annotations", "()V", "getUnsafeCoerceIntrinsic", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "unsafeCoerceIntrinsic$delegate", "Lkotlin/Lazy;", "sourceKey", "isComposableDelegatedAccessor", "cacheFunction", "getCacheFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "cacheFunction$delegate", "Landroidx/compose/compiler/plugins/kotlin/lower/GuardedLazy;", "irCache", "currentComposer", "invalid", "calculation", "irChanged", "fileContainingValue", "inferredStable", "compareInstanceForFunctionTypes", "compareInstanceForUnstableValues", "irEnumOrdinal", "protobufEnumClassId", "ordinalIfEnum", "irStartReplaceGroup", "key", "irEndReplaceGroup", "wrap", "Lorg/jetbrains/kotlin/ir/expressions/IrContainerExpression;", "before", "after", "changedFunction", "changedInstanceFunction", "startReplaceFunction", "getStartReplaceFunction", "startReplaceFunction$delegate", "endReplaceFunction", "getEndReplaceFunction", "endReplaceFunction$delegate", "toPrimitiveType", "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "changedPrimitiveFunctions", "", "getChangedPrimitiveFunctions", "()Ljava/util/Map;", "changedPrimitiveFunctions$delegate", "irMethodCall", "copyParametersFrom", "original", "copyDefaultValues", "copyParametersFrom$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "transformDefaultValue", "Lorg/jetbrains/kotlin/ir/expressions/IrExpressionBody;", "originalFunction", "newFunction", "Landroidx/compose/compiler/plugins/kotlin/ComposeMetadata;", "composeMetadata", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "getComposeMetadata-fNhgvTQ", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;)[B", "setComposeMetadata-IvR2BlU", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;[B)V", "hasNonRestartableAnnotation", "getHasNonRestartableAnnotation", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)Z", "hasReadOnlyAnnotation", "getHasReadOnlyAnnotation", "hasExplicitGroups", "getHasExplicitGroups", "hasNonSkippableAnnotation", "getHasNonSkippableAnnotation", "jvmFieldIrClass", "jvmSyntheticIrClass", "hiddenFromObjCIrClass", "deprecationLevelIrClass", "deprecatedIrClass", "hiddenDeprecationLevel", "Lorg/jetbrains/kotlin/ir/symbols/IrEnumEntrySymbol;", "jvmField", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrAnnotationImpl;", "jvmSynthetic", "hiddenFromObjC", "hiddenDeprecated", "message", "makeStub", "shouldBeRestartable", "isVirtualFunctionWithDefaultParam", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class AbstractComposeLowering extends IrElementTransformerVoid implements ModuleLoweringPass {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(AbstractComposeLowering.class, "cacheFunction", "getCacheFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(AbstractComposeLowering.class, "startReplaceFunction", "getStartReplaceFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(AbstractComposeLowering.class, "endReplaceFunction", "getEndReplaceFunction()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", 0), new PropertyReference1Impl<>(AbstractComposeLowering.class, "changedPrimitiveFunctions", "getChangedPrimitiveFunctions()Ljava/util/Map;", 0)};
    private final IrBuiltIns builtIns;

    /* JADX INFO: renamed from: cacheFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy cacheFunction;
    private final IrSimpleFunction changedFunction;
    private final IrSimpleFunction changedInstanceFunction;

    /* JADX INFO: renamed from: changedPrimitiveFunctions$delegate, reason: from kotlin metadata */
    private final GuardedLazy changedPrimitiveFunctions;
    private final IrClass composableIrClass;
    private final IrClass composerIrClass;
    private final IrPluginContext context;
    private final IrClassSymbol deprecatedIrClass;
    private final IrClass deprecationLevelIrClass;

    /* JADX INFO: renamed from: endReplaceFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy endReplaceFunction;
    private final FeatureFlags featureFlags;
    private final DeclarationFinder finderForBuiltins;
    private final IrEnumEntrySymbol hiddenDeprecationLevel;
    private final IrClass hiddenFromObjCIrClass;
    private final IrSimpleFunction irEnumOrdinal;
    private final IrClass jvmFieldIrClass;
    private final IrClass jvmSyntheticIrClass;
    private final ModuleMetrics metrics;
    private final ClassId protobufEnumClassId;
    private final StabilityInferencer stabilityInferencer;

    /* JADX INFO: renamed from: startReplaceFunction$delegate, reason: from kotlin metadata */
    private final GuardedLazy startReplaceFunction;

    /* JADX INFO: renamed from: unsafeCoerceIntrinsic$delegate, reason: from kotlin metadata */
    private final Lazy unsafeCoerceIntrinsic;

    public AbstractComposeLowering(IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, FeatureFlags featureFlags) {
        IrClass owner;
        IrClass owner2;
        IrValueParameter irValueParameter;
        Object next;
        IrClass irClass;
        IrValueParameter irValueParameter2;
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        this.context = irPluginContext;
        this.metrics = moduleMetrics;
        this.stabilityInferencer = stabilityInferencer;
        this.featureFlags = featureFlags;
        this.builtIns = irPluginContext.getIrBuiltIns();
        DeclarationFinder declarationFinderFinderForBuiltins = irPluginContext.finderForBuiltins();
        this.finderForBuiltins = declarationFinderFinderForBuiltins;
        ComposeClassIds composeClassIds = ComposeClassIds.INSTANCE;
        IrClassSymbol irClassSymbolFindClass = declarationFinderFinderForBuiltins.findClass(composeClassIds.getComposer());
        if (irClassSymbolFindClass == null || (owner = irClassSymbolFindClass.getOwner()) == null) {
            k2d.a("Cannot find the Composer class in the classpath");
            throw null;
        }
        this.composerIrClass = owner;
        IrClassSymbol irClassSymbolFindClass2 = declarationFinderFinderForBuiltins.findClass(composeClassIds.getComposable());
        if (irClassSymbolFindClass2 == null || (owner2 = irClassSymbolFindClass2.getOwner()) == null) {
            k2d.a("Cannot find the Composable annotation class in the classpath");
            throw null;
        }
        this.composableIrClass = owner2;
        this.unsafeCoerceIntrinsic = LazyKt.lazy(new Function0() { // from class: sl
            public final Object invoke() {
                return AbstractComposeLowering.d(this.b);
            }
        });
        this.cacheFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: tl
            public final Object invoke() {
                return AbstractComposeLowering.e(this.b);
            }
        });
        boolean z = false;
        boolean z2 = false;
        Object obj = null;
        for (Object obj2 : IrUtilsKt.getProperties(irPluginContext.getIrBuiltIns().getEnumClass().getOwner())) {
            if (Intrinsics.areEqual(((IrProperty) obj2).getName().asString(), "ordinal")) {
                if (z2) {
                    w01.a("Sequence contains more than one matching element.");
                    throw null;
                }
                obj = obj2;
                z2 = true;
            }
        }
        if (!z2) {
            hb9.a("Sequence contains no element matching the predicate.");
            throw null;
        }
        IrSimpleFunction getter = ((IrProperty) obj).getGetter();
        getter.getClass();
        this.irEnumOrdinal = getter;
        this.protobufEnumClassId = ClassId.Companion.fromString$default(ClassId.Companion, "com/google/protobuf/Internal.EnumLite", false, 2, (Object) null);
        for (IrSimpleFunction irSimpleFunction : IrUtilsKt.getFunctions(this.composerIrClass)) {
            if (Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "changed")) {
                Iterator it = irSimpleFunction.getParameters().iterator();
                do {
                    if (!it.hasNext()) {
                        hb9.a("Collection contains no element matching the predicate.");
                        throw null;
                    }
                    irValueParameter = (IrValueParameter) it.next();
                } while (irValueParameter.getKind() != IrParameterKind.Regular);
                if (IrTypePredicatesKt.isNullableAny(irValueParameter.getType())) {
                    this.changedFunction = irSimpleFunction;
                    Iterator it2 = IrUtilsKt.getFunctions(this.composerIrClass).iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                        IrSimpleFunction irSimpleFunction2 = (IrSimpleFunction) next;
                        if (Intrinsics.areEqual(irSimpleFunction2.getName().getIdentifier(), "changedInstance")) {
                            Iterator it3 = irSimpleFunction2.getParameters().iterator();
                            do {
                                if (!it3.hasNext()) {
                                    hb9.a("Collection contains no element matching the predicate.");
                                    throw null;
                                }
                                irValueParameter2 = (IrValueParameter) it3.next();
                            } while (irValueParameter2.getKind() != IrParameterKind.Regular);
                            if (IrTypePredicatesKt.isNullableAny(irValueParameter2.getType())) {
                                break;
                            }
                        }
                    }
                    IrSimpleFunction irSimpleFunction3 = (IrSimpleFunction) next;
                    this.changedInstanceFunction = irSimpleFunction3 == null ? this.changedFunction : irSimpleFunction3;
                    this.startReplaceFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: ul
                        public final Object invoke() {
                            return AbstractComposeLowering.g(this.b);
                        }
                    });
                    this.endReplaceFunction = GuardedLazyKt.guardedLazy(new Function0() { // from class: vl
                        public final Object invoke() {
                            return AbstractComposeLowering.h(this.b);
                        }
                    });
                    this.changedPrimitiveFunctions = GuardedLazyKt.guardedLazy(new Function0() { // from class: wl
                        public final Object invoke() {
                            return AbstractComposeLowering.i(this.b);
                        }
                    });
                    this.jvmFieldIrClass = JvmPlatformKt.isJvm(this.context.getPlatform()) ? (IrClass) getTopLevelClass(JvmStandardClassIds.Annotations.INSTANCE.getJvmField()).getOwner() : null;
                    if (JvmPlatformKt.isJvm(this.context.getPlatform())) {
                        FqName base_jvm_package = StandardClassIds.INSTANCE.getBASE_JVM_PACKAGE();
                        Name nameIdentifier = Name.identifier("JvmSynthetic");
                        nameIdentifier.getClass();
                        irClass = (IrClass) getTopLevelClass(new ClassId(base_jvm_package, nameIdentifier)).getOwner();
                    } else {
                        irClass = null;
                    }
                    this.jvmSyntheticIrClass = irClass;
                    this.hiddenFromObjCIrClass = NativePlatformKt.isNative(this.context.getPlatform()) ? (IrClass) getTopLevelClass(AddHiddenFromObjCLoweringKt.getHiddenFromObjCClassId()).getOwner() : null;
                    ClassId.Companion companion = ClassId.Companion;
                    IrClass owner3 = getTopLevelClass(ClassId.Companion.fromString$default(companion, "kotlin/DeprecationLevel", false, 2, (Object) null)).getOwner();
                    this.deprecationLevelIrClass = owner3;
                    this.deprecatedIrClass = getTopLevelClass(ClassId.Companion.fromString$default(companion, "kotlin/Deprecated", false, 2, (Object) null));
                    List declarations = owner3.getDeclarations();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj3 : declarations) {
                        if (obj3 instanceof IrEnumEntry) {
                            arrayList.add(obj3);
                        }
                    }
                    Object obj4 = null;
                    for (Object obj5 : arrayList) {
                        if (Intrinsics.areEqual(((IrEnumEntry) obj5).getName().toString(), "HIDDEN")) {
                            if (z) {
                                w01.a("Collection contains more than one matching element.");
                                throw null;
                            }
                            obj4 = obj5;
                            z = true;
                        }
                    }
                    if (z) {
                        this.hiddenDeprecationLevel = ((IrEnumEntry) obj4).getSymbol();
                        return;
                    } else {
                        hb9.a("Collection contains no element matching the predicate.");
                        throw null;
                    }
                }
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        throw null;
    }

    private final boolean areAllArgumentsStatic(IrMemberAccessExpression<?> irMemberAccessExpression, IrFile irFile) {
        boolean zIsStatic;
        List argumentsWithIr = IrUtilsKt.getArgumentsWithIr(irMemberAccessExpression);
        if ((argumentsWithIr instanceof Collection) && argumentsWithIr.isEmpty()) {
            return true;
        }
        Iterator it = argumentsWithIr.iterator();
        while (it.hasNext()) {
            IrVararg irVararg = (IrExpression) ((Pair) it.next()).component2();
            if (irVararg instanceof IrVararg) {
                List elements = irVararg.getElements();
                if (!(elements instanceof Collection) || !elements.isEmpty()) {
                    Iterator it2 = elements.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            zIsStatic = true;
                            break;
                        }
                        IrVarargElement irVarargElement = (IrVarargElement) it2.next();
                        IrExpression irExpression = irVarargElement instanceof IrExpression ? (IrExpression) irVarargElement : null;
                        if (!(irExpression != null ? isStatic(irExpression, irFile) : false)) {
                            zIsStatic = false;
                            break;
                        }
                    }
                } else {
                    zIsStatic = true;
                    break;
                    break;
                }
            } else {
                zIsStatic = isStatic((IrExpression) irVararg, irFile);
            }
            if (!zIsStatic) {
                return false;
            }
        }
        return true;
    }

    public static Unit b(IrClass irClass) {
        irClass.getClass();
        return Unit.INSTANCE;
    }

    private final IrCallImpl binaryOperatorCall(IrExpression lhs, IrExpression rhs, Name name, IrType lhsType, IrType rhsType) {
        return irCall$default(this, binaryOperator(lhsType, name, rhsType), null, null, lhs, null, new IrExpression[]{rhs}, 22, null);
    }

    public static /* synthetic */ IrCallImpl binaryOperatorCall$default(AbstractComposeLowering abstractComposeLowering, IrExpression irExpression, IrExpression irExpression2, Name name, IrType irType, IrType irType2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: binaryOperatorCall");
            return null;
        }
        if ((i & 8) != 0) {
            irType = irExpression.getType();
        }
        IrType irType3 = irType;
        if ((i & 16) != 0) {
            irType2 = irExpression2.getType();
        }
        return abstractComposeLowering.binaryOperatorCall(irExpression, irExpression2, name, irType3, irType2);
    }

    private final IrField buildStabilityField(Name fieldName) {
        IrFactory irFactory = this.context.getIrFactory();
        IrFieldBuilder irFieldBuilder = new IrFieldBuilder();
        irFieldBuilder.setStartOffset(-2);
        irFieldBuilder.setEndOffset(-2);
        irFieldBuilder.setName(fieldName);
        irFieldBuilder.setStatic(true);
        irFieldBuilder.setFinal(true);
        irFieldBuilder.setType(this.context.getIrBuiltIns().getIntType());
        DescriptorVisibility descriptorVisibility = JvmPlatformKt.isJvm(this.context.getPlatform()) ? DescriptorVisibilities.PUBLIC : DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        irFieldBuilder.setVisibility(descriptorVisibility);
        IrField irFieldBuildField = DeclarationBuildersKt.buildField(irFactory, irFieldBuilder);
        if (JvmPlatformKt.isJvm(this.context.getPlatform())) {
            IrAnnotationImpl irAnnotationImplJvmField = jvmField();
            irAnnotationImplJvmField.getClass();
            irFieldBuildField.setAnnotations(CollectionsKt.listOf(irAnnotationImplJvmField));
        }
        return irFieldBuildField;
    }

    private final void buildStabilityGetterJvm(IrClass irClass, IrProperty irProperty) {
        Name nameUniqueStabilityGetterName = uniqueStabilityGetterName(irClass);
        IrField backingField = irProperty.getBackingField();
        backingField.getClass();
        IrFactory irFactory = this.context.getIrFactory();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        irFunctionBuilder.setStartOffset(irClass.getStartOffset());
        irFunctionBuilder.setEndOffset(irClass.getEndOffset());
        irFunctionBuilder.setName(nameUniqueStabilityGetterName);
        irFunctionBuilder.setReturnType(backingField.getType());
        irFunctionBuilder.setVisibility(irClass.getVisibility());
        irFunctionBuilder.setOrigin(new IrDeclarationOrigin.GeneratedByPlugin(ComposeCompilerKey.INSTANCE));
        IrSimpleFunction irSimpleFunctionBuildFunction = DeclarationBuildersKt.buildFunction(irFactory, irFunctionBuilder);
        irProperty.setGetter(irSimpleFunctionBuildFunction);
        irSimpleFunctionBuildFunction.setCorrespondingPropertySymbol(irProperty.getSymbol());
        irSimpleFunctionBuildFunction.setParent(irProperty.getParent());
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(this.context, irSimpleFunctionBuildFunction.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder, irGetField(backingField)));
        irSimpleFunctionBuildFunction.setBody(irBlockBodyBuilder.doBuild());
    }

    private final void buildStabilityGetterNonJvm(IrClass irClass, IrProperty irProperty, IrPackageFragment irPackageFragment) {
        List listListOf;
        Name nameUniqueStabilityGetterName = uniqueStabilityGetterName(irClass);
        IrField backingField = irProperty.getBackingField();
        backingField.getClass();
        IrFactory irFactory = this.context.getIrFactory();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        irFunctionBuilder.setStartOffset(irClass.getStartOffset());
        irFunctionBuilder.setEndOffset(irClass.getEndOffset());
        irFunctionBuilder.setName(nameUniqueStabilityGetterName);
        irFunctionBuilder.setReturnType(backingField.getType());
        irFunctionBuilder.setVisibility(irClass.getVisibility());
        irFunctionBuilder.setOrigin(new IrDeclarationOrigin.GeneratedByPlugin(ComposeCompilerKey.INSTANCE));
        IrSimpleFunction irSimpleFunctionBuildFunction = DeclarationBuildersKt.buildFunction(irFactory, irFunctionBuilder);
        irSimpleFunctionBuildFunction.setParent(irPackageFragment);
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(this.context, irSimpleFunctionBuildFunction.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder, irGetField(backingField)));
        irSimpleFunctionBuildFunction.setBody(irBlockBodyBuilder.doBuild());
        IrUtilsKt.addChild(irPackageFragment, irSimpleFunctionBuildFunction);
        IrAnnotationImpl irAnnotationImplHiddenDeprecated = hiddenDeprecated("Synthetic declaration generated by the Compose compiler. Please do not use.");
        if (NativePlatformKt.isNative(this.context.getPlatform())) {
            IrAnnotationImpl irAnnotationImplHiddenFromObjC = hiddenFromObjC();
            if (irAnnotationImplHiddenFromObjC == null) {
                k2d.a("Expected @HiddenFromObjC annotation to be present.");
                return;
            }
            listListOf = CollectionsKt.listOf(new IrAnnotationImpl[]{irAnnotationImplHiddenFromObjC, irAnnotationImplHiddenDeprecated});
        } else {
            listListOf = CollectionsKt.listOf(irAnnotationImplHiddenDeprecated);
        }
        irSimpleFunctionBuildFunction.setAnnotations(listListOf);
        this.context.getMetadataDeclarationRegistrar().registerFunctionAsMetadataVisible(irSimpleFunctionBuildFunction);
    }

    private final IrProperty buildStabilityPropJvm(IrClass irClass, boolean z) {
        Object next;
        Name stabilityFlag = ComposeNames.INSTANCE.getStabilityFlag();
        Iterator it = irClass.getDeclarations().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            IrProperty irProperty = (IrDeclaration) next;
            if ((irProperty instanceof IrProperty) && Intrinsics.areEqual(irProperty.getName(), stabilityFlag)) {
                break;
            }
        }
        IrProperty irProperty2 = next instanceof IrProperty ? (IrProperty) next : null;
        if (irProperty2 != null) {
            return irProperty2;
        }
        IrField irFieldBuildStabilityField = buildStabilityField(ComposeNames.INSTANCE.getStabilityFlag());
        irFieldBuildStabilityField.setParent(irClass);
        IrFactory irFactory = this.context.getIrFactory();
        IrPropertyBuilder irPropertyBuilder = new IrPropertyBuilder();
        irPropertyBuilder.setStartOffset(-2);
        irPropertyBuilder.setEndOffset(-2);
        irPropertyBuilder.setName(stabilityFlag);
        irPropertyBuilder.setVisibility(irClass.getVisibility());
        IrProperty irPropertyBuildProperty = DeclarationBuildersKt.buildProperty(irFactory, irPropertyBuilder);
        irFieldBuildStabilityField.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
        irPropertyBuildProperty.setBackingField(irFieldBuildStabilityField);
        IrUtilsKt.addChild(irClass, irPropertyBuildProperty);
        if (z) {
            buildStabilityGetterJvm(irClass, irPropertyBuildProperty);
        }
        return irPropertyBuildProperty;
    }

    private final IrProperty buildStabilityPropNonJvm(IrClass irClass, boolean z) {
        Object next;
        IrPackageFragment packageFragment = IrUtilsKt.getPackageFragment(irClass);
        Name nameUniqueStabilityPropertyName = uniqueStabilityPropertyName(irClass);
        Iterator it = packageFragment.getDeclarations().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            IrProperty irProperty = (IrDeclaration) next;
            if ((irProperty instanceof IrProperty) && Intrinsics.areEqual(irProperty.getName(), nameUniqueStabilityPropertyName)) {
                break;
            }
        }
        IrProperty irProperty2 = next instanceof IrProperty ? (IrProperty) next : null;
        if (irProperty2 != null) {
            return irProperty2;
        }
        IrField irFieldBuildStabilityField = buildStabilityField(uniqueStabilityFieldName(irClass));
        irFieldBuildStabilityField.setParent(packageFragment);
        IrFactory irFactory = this.context.getIrFactory();
        IrPropertyBuilder irPropertyBuilder = new IrPropertyBuilder();
        irPropertyBuilder.setStartOffset(-2);
        irPropertyBuilder.setEndOffset(-2);
        irPropertyBuilder.setName(nameUniqueStabilityPropertyName);
        irPropertyBuilder.setVisibility(irClass.getVisibility());
        IrProperty irPropertyBuildProperty = DeclarationBuildersKt.buildProperty(irFactory, irPropertyBuilder);
        irPropertyBuildProperty.setParent(packageFragment);
        irFieldBuildStabilityField.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
        irPropertyBuildProperty.setBackingField(irFieldBuildStabilityField);
        IrUtilsKt.addChild(packageFragment, irPropertyBuildProperty);
        if (z) {
            buildStabilityGetterNonJvm(irClass, irPropertyBuildProperty, packageFragment);
        }
        return irPropertyBuildProperty;
    }

    public static IrExpression c(IrTypeParameter irTypeParameter) {
        irTypeParameter.getClass();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean changedPrimitiveFunctions_delegate$lambda$0$0(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        return Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "changed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair changedPrimitiveFunctions_delegate$lambda$0$1(AbstractComposeLowering abstractComposeLowering, IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        for (IrValueParameter irValueParameter : irSimpleFunction.getParameters()) {
            if (irValueParameter.getKind() == IrParameterKind.Regular) {
                PrimitiveType primitiveType = abstractComposeLowering.toPrimitiveType(irValueParameter.getType());
                if (primitiveType != null) {
                    return TuplesKt.to(primitiveType, irSimpleFunction);
                }
                return null;
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
        return null;
    }

    public static /* synthetic */ void copyParametersFrom$org_jetbrains_kotlin_kotlin_compose_compiler_plugin$default(AbstractComposeLowering abstractComposeLowering, IrFunction irFunction, IrFunction irFunction2, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: copyParametersFrom");
            return;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        abstractComposeLowering.copyParametersFrom$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(irFunction, irFunction2, z);
    }

    public static IrSimpleFunctionSymbol d(AbstractComposeLowering abstractComposeLowering) {
        if (!JvmPlatformKt.isJvm(abstractComposeLowering.context.getPlatform())) {
            return null;
        }
        IrFactory irFactory = abstractComposeLowering.context.getIrFactory();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        Name nameSpecial = Name.special("<unsafe-coerce>");
        nameSpecial.getClass();
        irFunctionBuilder.setName(nameSpecial);
        irFunctionBuilder.setOrigin(IrDeclarationOrigin.Companion.getIR_BUILTINS_STUB());
        IrSimpleFunction irSimpleFunctionBuildFunction = DeclarationBuildersKt.buildFunction(irFactory, irFunctionBuilder);
        irSimpleFunctionBuildFunction.setParent(IrExternalPackageFragmentImpl.Companion.createEmptyExternalPackageFragment(abstractComposeLowering.context.getModuleDescriptor(), new FqName("kotlin.jvm.internal")));
        IrTypeParameter irTypeParameterAddTypeParameter$default = DeclarationBuildersKt.addTypeParameter$default(irSimpleFunctionBuildFunction, "T", abstractComposeLowering.context.getIrBuiltIns().getAnyNType(), (Variance) null, 4, (Object) null);
        IrTypeParameter irTypeParameterAddTypeParameter$default2 = DeclarationBuildersKt.addTypeParameter$default(irSimpleFunctionBuildFunction, "R", abstractComposeLowering.context.getIrBuiltIns().getAnyNType(), (Variance) null, 4, (Object) null);
        DeclarationBuildersKt.addValueParameter$default(irSimpleFunctionBuildFunction, "v", IrTypesKt.getDefaultType(irTypeParameterAddTypeParameter$default), (IrDeclarationOrigin) null, 4, (Object) null);
        irSimpleFunctionBuildFunction.setReturnType(IrTypesKt.getDefaultType(irTypeParameterAddTypeParameter$default2));
        return irSimpleFunctionBuildFunction.getSymbol();
    }

    public static IrSimpleFunction e(AbstractComposeLowering abstractComposeLowering) {
        for (IrSimpleFunctionSymbol irSimpleFunctionSymbol : abstractComposeLowering.getTopLevelFunctions(ComposeCallableIds.INSTANCE.getCache())) {
            List parameters = irSimpleFunctionSymbol.getOwner().getParameters();
            if (parameters.size() == 3 && ((IrValueParameter) parameters.get(0)).getKind() == IrParameterKind.ExtensionReceiver) {
                IrParameterKind kind = ((IrValueParameter) parameters.get(1)).getKind();
                IrParameterKind irParameterKind = IrParameterKind.Regular;
                if (kind == irParameterKind && ((IrValueParameter) parameters.get(2)).getKind() == irParameterKind) {
                    return irSimpleFunctionSymbol.getOwner();
                }
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
        return null;
    }

    public static IrSimpleFunction g(AbstractComposeLowering abstractComposeLowering) {
        Object next;
        int i;
        int i2;
        Iterator it = IrUtilsKt.getFunctions(abstractComposeLowering.composerIrClass).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            IrSimpleFunction irSimpleFunction = (IrSimpleFunction) next;
            if (Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "startReplaceGroup")) {
                List parameters = irSimpleFunction.getParameters();
                if ((parameters instanceof Collection) && parameters.isEmpty()) {
                    i2 = 0;
                } else {
                    Iterator it2 = parameters.iterator();
                    i2 = 0;
                    while (it2.hasNext()) {
                        if (((IrValueParameter) it2.next()).getKind() == IrParameterKind.Regular && (i2 = i2 + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                if (i2 == 1) {
                    break;
                }
            }
        }
        IrSimpleFunction irSimpleFunction2 = (IrSimpleFunction) next;
        if (irSimpleFunction2 != null) {
            return irSimpleFunction2;
        }
        for (IrSimpleFunction irSimpleFunction3 : IrUtilsKt.getFunctions(abstractComposeLowering.composerIrClass)) {
            if (Intrinsics.areEqual(irSimpleFunction3.getName().getIdentifier(), "startReplaceableGroup")) {
                List parameters2 = irSimpleFunction3.getParameters();
                if ((parameters2 instanceof Collection) && parameters2.isEmpty()) {
                    i = 0;
                } else {
                    Iterator it3 = parameters2.iterator();
                    i = 0;
                    while (it3.hasNext()) {
                        if (((IrValueParameter) it3.next()).getKind() == IrParameterKind.Regular && (i = i + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                if (i == 1) {
                    return irSimpleFunction3;
                }
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    private final IrSimpleFunction getCacheFunction() {
        return (IrSimpleFunction) this.cacheFunction.value($$delegatedProperties[0].getName());
    }

    private final Map<PrimitiveType, IrSimpleFunction> getChangedPrimitiveFunctions() {
        return (Map) this.changedPrimitiveFunctions.value($$delegatedProperties[3].getName());
    }

    private final IrSimpleFunction getEndReplaceFunction() {
        return (IrSimpleFunction) this.endReplaceFunction.value($$delegatedProperties[2].getName());
    }

    private final IrSimpleFunctionSymbol getMetadataStabilityGetterFun(IrClass irClass) {
        DeclarationFinder declarationFinder = this.finderForBuiltins;
        FqName packageFqName = AdditionalIrUtilsKt.getPackageFqName(irClass);
        packageFqName.getClass();
        return (IrSimpleFunctionSymbol) CollectionsKt.firstOrNull(declarationFinder.findFunctions(new CallableId(packageFqName, uniqueStabilityGetterName(irClass))));
    }

    private final IrExpression getRuntimeStabilityValue(IrClass irClass) {
        KotlinLibrary library;
        KotlinLibraryVersioning versions;
        FirRegularClass fir;
        if (JvmPlatformKt.isJvm(this.context.getPlatform())) {
            if (Intrinsics.areEqual(irClass.getOrigin(), IrDeclarationOrigin.Companion.getIR_EXTERNAL_DECLARATION_STUB())) {
                IrField backingField = buildStabilityPropJvm(irClass, false).getBackingField();
                backingField.getClass();
                return irGetField(backingField);
            }
            IrField backingField2 = buildStabilityPropJvm(irClass, true).getBackingField();
            backingField2.getClass();
            IrPropertySymbol correspondingPropertySymbol = backingField2.getCorrespondingPropertySymbol();
            correspondingPropertySymbol.getClass();
            IrSimpleFunction getter = correspondingPropertySymbol.getOwner().getGetter();
            getter.getClass();
            return irCall$default(this, getter, 0, 0, 6, null);
        }
        IrSimpleFunctionSymbol metadataStabilityGetterFun = getMetadataStabilityGetterFun(irClass);
        if (metadataStabilityGetterFun != null) {
            return irCall(metadataStabilityGetterFun);
        }
        Fir2IrLazyClass fir2IrLazyClass = irClass instanceof Fir2IrLazyClass ? (Fir2IrLazyClass) irClass : null;
        SourceFile klibSourceFile = (fir2IrLazyClass == null || (fir = fir2IrLazyClass.getFir()) == null) ? null : DeclarationAttributesKt.getKlibSourceFile(fir);
        DeserializedSourceFile deserializedSourceFile = klibSourceFile instanceof DeserializedSourceFile ? (DeserializedSourceFile) klibSourceFile : null;
        String compilerVersion = (deserializedSourceFile == null || (library = deserializedSourceFile.getLibrary()) == null || (versions = library.getVersions()) == null) ? null : versions.getCompilerVersion();
        if (compilerVersion == null || !StringsKt.startsWith$default(compilerVersion, "1.9", false, 2, (Object) null)) {
            return null;
        }
        IrField backingField3 = buildStabilityPropNonJvm(irClass, false).getBackingField();
        backingField3.getClass();
        return irGetField(backingField3);
    }

    private final IrSimpleFunction getStartReplaceFunction() {
        return (IrSimpleFunction) this.startReplaceFunction.value($$delegatedProperties[1].getName());
    }

    private final IrSimpleFunctionSymbol getUnsafeCoerceIntrinsic() {
        return (IrSimpleFunctionSymbol) this.unsafeCoerceIntrinsic.getValue();
    }

    private static /* synthetic */ void getUnsafeCoerceIntrinsic$annotations() {
    }

    public static IrSimpleFunction h(AbstractComposeLowering abstractComposeLowering) {
        Object next;
        Iterator it = IrUtilsKt.getFunctions(abstractComposeLowering.composerIrClass).iterator();
        loop0: while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            IrSimpleFunction irSimpleFunction = (IrSimpleFunction) next;
            if (Intrinsics.areEqual(irSimpleFunction.getName().getIdentifier(), "endReplaceGroup")) {
                List parameters = irSimpleFunction.getParameters();
                if (!(parameters instanceof Collection) || !parameters.isEmpty()) {
                    Iterator it2 = parameters.iterator();
                    do {
                        if (!it2.hasNext()) {
                            break loop0;
                        }
                    } while (((IrValueParameter) it2.next()).getKind() != IrParameterKind.Regular);
                } else {
                    break;
                }
            }
        }
        IrSimpleFunction irSimpleFunction2 = (IrSimpleFunction) next;
        if (irSimpleFunction2 != null) {
            return irSimpleFunction2;
        }
        for (IrSimpleFunction irSimpleFunction3 : IrUtilsKt.getFunctions(abstractComposeLowering.composerIrClass)) {
            if (Intrinsics.areEqual(irSimpleFunction3.getName().getIdentifier(), "endReplaceableGroup")) {
                List parameters2 = irSimpleFunction3.getParameters();
                if (!(parameters2 instanceof Collection) || !parameters2.isEmpty()) {
                    Iterator it3 = parameters2.iterator();
                    while (it3.hasNext()) {
                        if (((IrValueParameter) it3.next()).getKind() == IrParameterKind.Regular) {
                        }
                    }
                }
                return irSimpleFunction3;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    private final IrAnnotationImpl hiddenDeprecated(String message) {
        IrAnnotationImpl.Companion companion = IrAnnotationImpl.Companion;
        IrSimpleType defaultType = IrTypesKt.getDefaultType(this.deprecatedIrClass);
        for (IrConstructorSymbol irConstructorSymbol : IrUtilsKt.getConstructors(this.deprecatedIrClass)) {
            if (irConstructorSymbol.getOwner().isPrimary()) {
                IrAnnotationImpl irAnnotationImplFromSymbolOwner$default = BuildersKt.fromSymbolOwner$default(companion, defaultType, irConstructorSymbol, (IrStatementOrigin) null, 4, (Object) null);
                irAnnotationImplFromSymbolOwner$default.getArguments().set(0, IrConstImpl.Companion.string(-2, -2, this.context.getIrBuiltIns().getStringType(), message));
                irAnnotationImplFromSymbolOwner$default.getArguments().set(2, BuildersKt.IrGetEnumValueImpl(-2, -2, IrUtilsKt.getDefaultType(this.deprecationLevelIrClass), this.hiddenDeprecationLevel));
                return irAnnotationImplFromSymbolOwner$default;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    private final IrAnnotationImpl hiddenFromObjC() {
        IrClass irClass = this.hiddenFromObjCIrClass;
        if (irClass != null) {
            return BuildersKt.fromSymbolOwner$default(IrAnnotationImpl.Companion, IrUtilsKt.getDefaultType(irClass), ((IrConstructor) SequencesKt.first(IrUtilsKt.getConstructors(irClass))).getSymbol(), (IrStatementOrigin) null, 4, (Object) null);
        }
        return null;
    }

    public static Map i(final AbstractComposeLowering abstractComposeLowering) {
        return MapsKt.toMap(SequencesKt.mapNotNull(SequencesKt.filter(IrUtilsKt.getFunctions(abstractComposeLowering.composerIrClass), new Function1() { // from class: ol
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AbstractComposeLowering.changedPrimitiveFunctions_delegate$lambda$0$0((IrSimpleFunction) obj));
            }
        }), new Function1() { // from class: pl
            public final Object invoke(Object obj) {
                return AbstractComposeLowering.changedPrimitiveFunctions_delegate$lambda$0$1(this.b, (IrSimpleFunction) obj);
            }
        }));
    }

    public static /* synthetic */ IrBlock irBlock$default(AbstractComposeLowering abstractComposeLowering, IrType irType, IrStatementOrigin irStatementOrigin, int i, int i2, List list, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irBlock");
            return null;
        }
        if ((i3 & 1) != 0) {
            irType = abstractComposeLowering.context.getIrBuiltIns().getUnitType();
        }
        if ((i3 & 2) != 0) {
            irStatementOrigin = null;
        }
        if ((i3 & 4) != 0) {
            i = -1;
        }
        return abstractComposeLowering.irBlock(irType, irStatementOrigin, i, (i3 & 8) != 0 ? -1 : i2, list);
    }

    public static /* synthetic */ IrCallImpl irCall$default(AbstractComposeLowering abstractComposeLowering, IrFunctionSymbol irFunctionSymbol, IrStatementOrigin irStatementOrigin, IrType irType, IrExpression irExpression, IrExpression irExpression2, IrExpression[] irExpressionArr, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irCall");
            return null;
        }
        if ((i & 2) != 0) {
            irStatementOrigin = null;
        }
        if ((i & 4) != 0) {
            irType = irFunctionSymbol.getOwner().getReturnType();
        }
        if ((i & 8) != 0) {
            irExpression = null;
        }
        if ((i & 16) != 0) {
            irExpression2 = null;
        }
        return abstractComposeLowering.irCall(irFunctionSymbol, irStatementOrigin, irType, irExpression, irExpression2, irExpressionArr);
    }

    public static /* synthetic */ IrExpression irComposite$default(AbstractComposeLowering abstractComposeLowering, IrType irType, IrStatementOrigin irStatementOrigin, List list, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irComposite");
            return null;
        }
        if ((i & 1) != 0) {
            irType = abstractComposeLowering.context.getIrBuiltIns().getUnitType();
        }
        if ((i & 2) != 0) {
            irStatementOrigin = null;
        }
        return abstractComposeLowering.irComposite(irType, irStatementOrigin, list);
    }

    public static /* synthetic */ IrElseBranchImpl irElseBranch$default(AbstractComposeLowering abstractComposeLowering, IrExpression irExpression, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irElseBranch");
            return null;
        }
        if ((i3 & 2) != 0) {
            i = -1;
        }
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        return abstractComposeLowering.irElseBranch(irExpression, i, i2);
    }

    public static /* synthetic */ IrExpression irEndReplaceGroup$default(AbstractComposeLowering abstractComposeLowering, IrExpression irExpression, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irEndReplaceGroup");
            return null;
        }
        if ((i3 & 2) != 0) {
            i = -1;
        }
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        return abstractComposeLowering.irEndReplaceGroup(irExpression, i, i2);
    }

    public static /* synthetic */ IrWhenImpl irIfThenElse$default(AbstractComposeLowering abstractComposeLowering, IrType irType, IrExpression irExpression, IrExpression irExpression2, IrExpression irExpression3, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irIfThenElse");
            return null;
        }
        if ((i3 & 1) != 0) {
            irType = abstractComposeLowering.context.getIrBuiltIns().getUnitType();
        }
        if ((i3 & 16) != 0) {
            i = -1;
        }
        return abstractComposeLowering.irIfThenElse(irType, irExpression, irExpression2, irExpression3, i, (i3 & 32) != 0 ? -1 : i2);
    }

    public static /* synthetic */ IrCall irMethodCall$default(AbstractComposeLowering abstractComposeLowering, IrExpression irExpression, IrFunction irFunction, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irMethodCall");
            return null;
        }
        if ((i3 & 4) != 0) {
            i = -1;
        }
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        return abstractComposeLowering.irMethodCall(irExpression, irFunction, i, i2);
    }

    public static /* synthetic */ IrExpression irReturn$default(AbstractComposeLowering abstractComposeLowering, IrReturnTargetSymbol irReturnTargetSymbol, IrExpression irExpression, IrType irType, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irReturn");
            return null;
        }
        if ((i & 4) != 0) {
            irType = irExpression.getType();
        }
        return abstractComposeLowering.irReturn(irReturnTargetSymbol, irExpression, irType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IrExpression irStableExpression$default(AbstractComposeLowering abstractComposeLowering, Stability stability, Function1 function1, Function1 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irStableExpression");
            return null;
        }
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: ql
                public final Object invoke(Object obj2) {
                    return AbstractComposeLowering.c((IrTypeParameter) obj2);
                }
            };
        }
        if ((i & 2) != 0) {
            function2 = new Function1() { // from class: rl
                public final Object invoke(Object obj2) {
                    return AbstractComposeLowering.b((IrClass) obj2);
                }
            };
        }
        return abstractComposeLowering.irStableExpression(stability, function1, function2);
    }

    public static /* synthetic */ IrExpression irStartReplaceGroup$default(AbstractComposeLowering abstractComposeLowering, IrExpression irExpression, IrExpression irExpression2, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irStartReplaceGroup");
            return null;
        }
        if ((i3 & 4) != 0) {
            i = -1;
        }
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        return abstractComposeLowering.irStartReplaceGroup(irExpression, irExpression2, i, i2);
    }

    public static /* synthetic */ IrVariableImpl irTemporary$default(AbstractComposeLowering abstractComposeLowering, IrExpression irExpression, String str, IrType irType, boolean z, IrDeclarationOrigin irDeclarationOrigin, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irTemporary");
            return null;
        }
        if ((i & 4) != 0) {
            irType = irExpression.getType();
        }
        IrType irType2 = irType;
        if ((i & 8) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            irDeclarationOrigin = IrDeclarationOrigin.Companion.getIR_TEMPORARY_VARIABLE();
        }
        return abstractComposeLowering.irTemporary(irExpression, str, irType2, z2, irDeclarationOrigin);
    }

    public static /* synthetic */ IrWhenImpl irWhen$default(AbstractComposeLowering abstractComposeLowering, IrType irType, IrStatementOrigin irStatementOrigin, List list, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irWhen");
            return null;
        }
        if ((i & 1) != 0) {
            irType = abstractComposeLowering.context.getIrBuiltIns().getUnitType();
        }
        if ((i & 2) != 0) {
            irStatementOrigin = null;
        }
        return abstractComposeLowering.irWhen(irType, irStatementOrigin, list);
    }

    private final boolean isGetProperty(IrStatementOrigin irStatementOrigin) {
        return Intrinsics.areEqual(irStatementOrigin, IrStatementOrigin.Companion.getGET_PROPERTY());
    }

    private final boolean isSpecialCaseMathOp(IrStatementOrigin irStatementOrigin) {
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        return CollectionsKt.contains(SetsKt.setOf(new IrStatementOriginImpl[]{companion.getPLUS(), companion.getMUL(), companion.getMINUS(), companion.getANDAND(), companion.getOROR(), companion.getDIV(), companion.getEQ(), companion.getEQEQ(), companion.getEQEQEQ(), companion.getGT(), companion.getGTEQ(), companion.getLT(), companion.getLTEQ()}), irStatementOrigin);
    }

    private final boolean isStatic(IrCall irCall, IrFile irFile) {
        IrProperty owner;
        boolean z;
        IrSimpleFunction owner2 = irCall.getSymbol().getOwner();
        FqName kotlinFqName = AdditionalIrUtilsKt.getKotlinFqName(owner2);
        if (!isGetProperty(irCall.getOrigin())) {
            if (isSpecialCaseMathOp(irCall.getOrigin())) {
                boolean z2 = Intrinsics.areEqual(AbstractComposeLoweringKt.topLevelName(kotlinFqName), "kotlin") || UtilsKt.hasAnnotation(owner2, ComposeFqNames.INSTANCE.getStable());
                if (!StabilityKt.knownStable(this.stabilityInferencer.stabilityOf(irCall.getType(), irFile)) || !z2) {
                    return false;
                }
                List argumentsWithIr = IrUtilsKt.getArgumentsWithIr(irCall);
                if ((argumentsWithIr instanceof Collection) && argumentsWithIr.isEmpty()) {
                    return true;
                }
                Iterator it = argumentsWithIr.iterator();
                while (it.hasNext()) {
                    if (!isStatic((IrExpression) ((Pair) it.next()).getSecond(), irFile)) {
                        return false;
                    }
                }
                return true;
            }
            if (irCall.getOrigin() != null) {
                return false;
            }
            ComposeFqNames composeFqNames = ComposeFqNames.INSTANCE;
            if (Intrinsics.areEqual(kotlinFqName, composeFqNames.getRemember())) {
                if (irCall.getArguments().size() == 3 && StabilityKt.knownStable(this.stabilityInferencer.stabilityOf(irCall.getType(), irFile))) {
                    return true;
                }
            } else if (Intrinsics.areEqual(kotlinFqName, composeFqNames.getComposableLambda()) || Intrinsics.areEqual(kotlinFqName, composeFqNames.getRememberComposableLambda())) {
                return true;
            }
            if (Intrinsics.areEqual(WeakBindingTraceKt.getIrTrace(this.context).get(ComposeWritableSlices.INSTANCE.getIS_COMPOSABLE_SINGLETON(), irCall), Boolean.TRUE)) {
                return true;
            }
            if (KnownStableConstructs.INSTANCE.getStableFunctions().containsKey(kotlinFqName.asString()) || (UtilsKt.hasAnnotation(irCall.getSymbol().getOwner(), composeFqNames.getStable()) && StabilityKt.knownStable(this.stabilityInferencer.stabilityOf(irCall.getType(), irFile)))) {
                return areAllArgumentsStatic(irCall, irFile);
            }
            return false;
        }
        IrPropertySymbol correspondingPropertySymbol = owner2.getCorrespondingPropertySymbol();
        if (correspondingPropertySymbol != null && (owner = correspondingPropertySymbol.getOwner()) != null) {
            if (owner.isConst()) {
                return true;
            }
            boolean zKnownStable = StabilityKt.knownStable(this.stabilityInferencer.stabilityOf(irCall.getType(), irFile));
            IrMemberAccessExpression.ValueArgumentsList arguments = irCall.getArguments();
            if (arguments != null && arguments.isEmpty()) {
                z = true;
                break;
            }
            Iterator it2 = arguments.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    break;
                }
                IrExpression irExpression = (IrExpression) it2.next();
                if ((irExpression == null || isStatic(irExpression, irFile)) ? false : true) {
                    z = false;
                    break;
                }
            }
            if (Intrinsics.areEqual(IrUtilsKt.getFileOrNull(owner), irFile) && !owner.isVar()) {
                IrSimpleFunction getter = owner.getGetter();
                if (Intrinsics.areEqual(getter != null ? getter.getOrigin() : null, IrDeclarationOrigin.Companion.getDEFAULT_PROPERTY_ACCESSOR()) && zKnownStable && z) {
                    return true;
                }
            }
            ComposeFqNames composeFqNames2 = ComposeFqNames.INSTANCE;
            if ((UtilsKt.hasAnnotation(owner, composeFqNames2.getStable()) || UtilsKt.hasAnnotation(owner2, composeFqNames2.getStable())) && zKnownStable && z) {
                return true;
            }
        }
        return false;
    }

    private final IrAnnotationImpl jvmField() {
        IrClass irClass = this.jvmFieldIrClass;
        if (irClass != null) {
            return BuildersKt.fromSymbolOwner$default(IrAnnotationImpl.Companion, IrUtilsKt.getDefaultType(irClass), ((IrConstructor) SequencesKt.first(IrUtilsKt.getConstructors(irClass))).getSymbol(), (IrStatementOrigin) null, 4, (Object) null);
        }
        return null;
    }

    private final IrAnnotationImpl jvmSynthetic() {
        IrClass irClass = this.jvmSyntheticIrClass;
        if (irClass != null) {
            return BuildersKt.fromSymbolOwner$default(IrAnnotationImpl.Companion, IrUtilsKt.getDefaultType(irClass), ((IrConstructor) SequencesKt.first(IrUtilsKt.getConstructors(irClass))).getSymbol(), (IrStatementOrigin) null, 4, (Object) null);
        }
        return null;
    }

    private final IrExpression ordinalIfEnum(IrExpression irExpression) {
        IrSimpleFunction irSimpleFunction;
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irExpression.getType());
        IrClass irClass = classOrNull != null ? (IrClass) classOrNull.getOwner() : null;
        ClassKind kind = irClass != null ? irClass.getKind() : null;
        int i = kind == null ? -1 : WhenMappings.$EnumSwitchMapping$1[kind.ordinal()];
        if (i != 1 && i != 2) {
            return irExpression;
        }
        if (AbstractComposeLoweringKt.isSubclassOf(irClass, this.protobufEnumClassId)) {
            Object obj = null;
            boolean z = false;
            for (Object obj2 : IrUtilsKt.getFunctions(irClass)) {
                IrSimpleFunction irSimpleFunction2 = (IrSimpleFunction) obj2;
                if (Intrinsics.areEqual(irSimpleFunction2.getName().asString(), "getNumber") && irSimpleFunction2.getParameters().size() == 1 && ((IrValueParameter) irSimpleFunction2.getParameters().get(0)).getKind() == IrParameterKind.DispatchReceiver) {
                    if (z) {
                        w01.a("Sequence contains more than one matching element.");
                        return null;
                    }
                    obj = obj2;
                    z = true;
                }
            }
            if (!z) {
                hb9.a("Sequence contains no element matching the predicate.");
                return null;
            }
            irSimpleFunction = (IrSimpleFunction) obj;
        } else {
            irSimpleFunction = this.irEnumOrdinal;
        }
        IrSimpleFunction irSimpleFunction3 = irSimpleFunction;
        if (!IrTypeUtilsKt.isNullable(irExpression.getType())) {
            return irCall$default(this, irSimpleFunction3.getSymbol(), null, null, irExpression, null, new IrExpression[0], 22, null);
        }
        IrElement irElementIrTemporary$default = irTemporary$default(this, irExpression, "tmpEnum", null, false, null, 28, null);
        return irBlock$default(this, this.context.getIrBuiltIns().getIntType(), null, 0, 0, CollectionsKt.listOf(new IrElement[]{irElementIrTemporary$default, irIfThenElse$default(this, this.context.getIrBuiltIns().getIntType(), irEqual(irGet(irElementIrTemporary$default), irNull()), irConst(-1), irCall$default(this, irSimpleFunction3.getSymbol(), null, null, irGet(irElementIrTemporary$default), null, new IrExpression[0], 22, null), 0, 0, 48, null)}), 14, null);
    }

    private final PrimitiveType toPrimitiveType(IrType irType) {
        if (IrTypePredicatesKt.isInt(irType)) {
            return PrimitiveType.INT;
        }
        if (IrTypePredicatesKt.isBoolean(irType)) {
            return PrimitiveType.BOOLEAN;
        }
        if (IrTypePredicatesKt.isFloat(irType)) {
            return PrimitiveType.FLOAT;
        }
        if (IrTypePredicatesKt.isLong(irType)) {
            return PrimitiveType.LONG;
        }
        if (IrTypePredicatesKt.isDouble(irType)) {
            return PrimitiveType.DOUBLE;
        }
        if (IrTypePredicatesKt.isByte(irType)) {
            return PrimitiveType.BYTE;
        }
        if (IrTypePredicatesKt.isChar(irType)) {
            return PrimitiveType.CHAR;
        }
        if (IrTypePredicatesKt.isShort(irType)) {
            return PrimitiveType.SHORT;
        }
        return null;
    }

    private final void transformDefaultValue(IrExpressionBody irExpressionBody, final IrFunction irFunction, final IrFunction irFunction2) {
        IrElementTransformerVoidKt.transformChildrenVoid(irExpressionBody, new IrElementTransformerVoid() { // from class: androidx.compose.compiler.plugins.kotlin.lower.AbstractComposeLowering.transformDefaultValue.1
            public IrExpression visitGetValue(IrGetValue expression) {
                expression.getClass();
                IrExpression irExpressionVisitGetValue = super.visitGetValue(expression);
                IrValueParameter owner = expression.getSymbol().getOwner();
                IrValueParameter irValueParameter = owner instanceof IrValueParameter ? owner : null;
                if (irValueParameter == null) {
                    return irExpressionVisitGetValue;
                }
                return !Intrinsics.areEqual(irValueParameter.getParent(), irFunction) ? super.visitGetValue(expression) : BuildersKt.IrGetValueImpl(expression.getStartOffset(), expression.getEndOffset(), ((IrValueParameter) irFunction2.getParameters().get(irValueParameter.getIndexInParameters())).getSymbol(), expression.getOrigin());
            }
        });
    }

    private final Name uniqueStabilityFieldName(IrClass irClass) {
        Name nameIdentifier = Name.identifier(StringsKt.replace$default(AdditionalIrUtilsKt.getKotlinFqName(irClass).asString(), ".", "_", false, 4, (Object) null) + ComposeNames.INSTANCE.getStabilityFlag());
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    private final Name uniqueStabilityGetterName(IrClass irClass) {
        Name nameIdentifier = Name.identifier(StringsKt.replace$default(AdditionalIrUtilsKt.getKotlinFqName(irClass).asString(), ".", "_", false, 4, (Object) null) + ComposeNames.INSTANCE.getStabilityFlagPropertyGetter());
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    private final Name uniqueStabilityPropertyName(IrClass irClass) {
        Name nameIdentifier = Name.identifier(StringsKt.replace$default(AdditionalIrUtilsKt.getKotlinFqName(irClass).asString(), ".", "_", false, 4, (Object) null) + ComposeNames.INSTANCE.getStabilityFlagProperty());
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    private final int withBit(int i, int i2, boolean z) {
        return z ? (1 << i2) | i : (~(1 << i2)) & i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IrContainerExpression wrap$default(AbstractComposeLowering abstractComposeLowering, IrStatement irStatement, int i, int i2, IrType irType, List list, List list2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: wrap");
            return null;
        }
        if ((i3 & 1) != 0) {
            i = irStatement.getStartOffset();
        }
        int i4 = i;
        if ((i3 & 2) != 0) {
            i2 = irStatement.getEndOffset();
        }
        int i5 = i2;
        if ((i3 & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list3 = list;
        if ((i3 & 16) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return abstractComposeLowering.wrap(irStatement, i4, i5, irType, list3, list2);
    }

    public final IrFunctionSymbol binaryOperator(IrType irType, Name name, IrType irType2) {
        irType.getClass();
        name.getClass();
        irType2.getClass();
        DeclarationFinder declarationFinder = this.finderForBuiltins;
        ClassId classId = AdditionalIrUtilsKt.getClassId(IrTypesKt.getClassOrFail(irType).getOwner());
        classId.getClass();
        boolean z = false;
        Object obj = null;
        for (Object obj2 : declarationFinder.findFunctions(new CallableId(classId, name))) {
            if (IrUtilsKt.hasShape$default(((IrSimpleFunctionSymbol) obj2).getOwner(), true, false, 0, 1, CollectionsKt.listOf(new IrType[]{irType, irType2}), 6, (Object) null)) {
                if (z) {
                    w01.a("Collection contains more than one matching element.");
                    return null;
                }
                z = true;
                obj = obj2;
            }
        }
        if (z) {
            return (IrFunctionSymbol) obj;
        }
        hb9.a("Collection contains no element matching the predicate.");
        return null;
    }

    public final int bitMask(boolean... values) {
        values.getClass();
        int length = values.length;
        int i = 0;
        int iWithBit = 0;
        int i2 = 0;
        while (i < length) {
            iWithBit = withBit(iWithBit, i2, values[i]);
            i++;
            i2++;
        }
        return iWithBit;
    }

    public final IrCallImpl coerceInlineClasses(IrExpression argument, IrType from, IrType to) {
        argument.getClass();
        from.getClass();
        to.getClass();
        IrCallImpl.Companion companion = IrCallImpl.Companion;
        IrSimpleFunctionSymbol unsafeCoerceIntrinsic = getUnsafeCoerceIntrinsic();
        unsafeCoerceIntrinsic.getClass();
        IrCallImpl irCallImplFromSymbolOwner$default = BuildersKt.fromSymbolOwner$default(companion, -1, -1, to, unsafeCoerceIntrinsic, (IrStatementOrigin) null, (IrClassSymbol) null, 48, (Object) null);
        irCallImplFromSymbolOwner$default.getTypeArguments().set(0, from);
        irCallImplFromSymbolOwner$default.getTypeArguments().set(1, to);
        irCallImplFromSymbolOwner$default.getArguments().set(0, argument);
        return irCallImplFromSymbolOwner$default;
    }

    public final IrCallImpl coerceToUnboxed(IrExpression irExpression) {
        irExpression.getClass();
        return coerceInlineClasses(irExpression, irExpression.getType(), unboxInlineClass(irExpression.getType()));
    }

    public final void copyParametersFrom$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(IrFunction irFunction, IrFunction irFunction2, boolean z) {
        ArrayList arrayList;
        IrValueParameter irValueParameterCopyTo$default;
        IrExpressionBody defaultValue;
        irFunction.getClass();
        irFunction2.getClass();
        IrUtilsKt.copyTypeParametersFrom$default(irFunction, irFunction2, (IrDeclarationOrigin) null, (Map) null, 6, (Object) null);
        IrFunction irFunction3 = irFunction;
        irFunction3.setReturnType(IrUtilsKt.remapTypeParameters$default(irFunction.getReturnType(), irFunction2, irFunction3, (Map) null, 4, (Object) null));
        List<IrValueParameter> parameters = irFunction2.getParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        for (IrValueParameter irValueParameter : parameters) {
            int i = WhenMappings.$EnumSwitchMapping$0[irValueParameter.getKind().ordinal()];
            IrExpressionBody irExpressionBody = null;
            if (i == 1 || i == 2) {
                arrayList = arrayList2;
                AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1 abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1 = new AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1(irFunction2, irFunction3);
                DeepCopyTypeRemapper deepCopyTypeRemapper = new DeepCopyTypeRemapper(abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1);
                DeepCopyPreservingMetadata deepCopyPreservingMetadata = new DeepCopyPreservingMetadata(abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1, new AbstractComposeLoweringKt.copyWithNewTypeParams.typeParamRemapper.1(deepCopyTypeRemapper, irFunction2, irFunction3));
                deepCopyTypeRemapper.setDeepCopy(deepCopyPreservingMetadata);
                IrVisitorsKt.acceptVoid(irValueParameter, abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1);
                IrValueParameter irValueParameterPatchDeclarationParents = PatchDeclarationParentsKt.patchDeclarationParents(irValueParameter.transform(deepCopyPreservingMetadata, (Object) null), irFunction3);
                if (irValueParameterPatchDeclarationParents == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrValueParameter");
                    return;
                }
                irValueParameterCopyTo$default = irValueParameterPatchDeclarationParents;
            } else {
                if (i != 3 && i != 4) {
                    bu8.a();
                    return;
                }
                Name nameDexSafeName = dexSafeName(irValueParameter.getName());
                IrType irTypeRemapTypeParameters$default = IrUtilsKt.remapTypeParameters$default(irValueParameter.getType(), irFunction2, irFunction3, (Map) null, 4, (Object) null);
                if (z && (defaultValue = irValueParameter.getDefaultValue()) != null) {
                    AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1 abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$2 = new AbstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$1(irFunction2, irFunction3);
                    DeepCopyTypeRemapper deepCopyTypeRemapper2 = new DeepCopyTypeRemapper(abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$2);
                    DeepCopyPreservingMetadata deepCopyPreservingMetadata2 = new DeepCopyPreservingMetadata(abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$2, new AbstractComposeLoweringKt.copyWithNewTypeParams.typeParamRemapper.1(deepCopyTypeRemapper2, irFunction2, irFunction3));
                    deepCopyTypeRemapper2.setDeepCopy(deepCopyPreservingMetadata2);
                    IrVisitorsKt.acceptVoid(defaultValue, abstractComposeLoweringKt$copyWithNewTypeParams$typeParamsAwareSymbolRemapper$2);
                    IrElement irElementPatchDeclarationParents = PatchDeclarationParentsKt.patchDeclarationParents(defaultValue.transform(deepCopyPreservingMetadata2, (Object) null), irFunction3);
                    if (irElementPatchDeclarationParents == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.expressions.IrExpressionBody");
                        return;
                    }
                    irExpressionBody = (IrExpressionBody) irElementPatchDeclarationParents;
                }
                arrayList = arrayList2;
                irValueParameterCopyTo$default = IrUtilsKt.copyTo$default(irValueParameter, irFunction, (IrDeclarationOrigin) null, 0, 0, nameDexSafeName, (Map) null, irTypeRemapTypeParameters$default, (IrType) null, irExpressionBody, false, false, false, (IrParameterKind) null, (Map) null, 16046, (Object) null);
                irFunction3 = irFunction;
            }
            ArrayList arrayList3 = arrayList;
            arrayList3.add(irValueParameterCopyTo$default);
            arrayList2 = arrayList3;
        }
        irFunction3.setParameters(arrayList2);
        if (z) {
            Iterator it = irFunction3.getParameters().iterator();
            while (it.hasNext()) {
                IrExpressionBody defaultValue2 = ((IrValueParameter) it.next()).getDefaultValue();
                if (defaultValue2 != null) {
                    transformDefaultValue(defaultValue2, irFunction2, irFunction3);
                }
            }
        }
    }

    public final IrType defaultParameterType(IrType irType) {
        IrClass owner;
        irType.getClass();
        if (!IrTypePredicatesKt.isPrimitiveType$default(irType, false, 1, (Object) null)) {
            if (!JvmIrTypeUtilsKt.isInlineClassType(irType)) {
                return IrTypesKt.makeNullable(irType);
            }
            IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType);
            boolean z = ((classOrNull == null || (owner = classOrNull.getOwner()) == null) ? null : IrUtilsKt.getPrimaryConstructor(owner)) != null;
            if ((!JvmPlatformKt.isJvm(this.context.getPlatform()) && !z) || !IrTypePredicatesKt.isPrimitiveType$default(unboxInlineClass(irType), false, 1, (Object) null)) {
                return IrTypesKt.makeNullable(irType);
            }
        }
        return irType;
    }

    public final Name dexSafeName(Name name) {
        name.getClass();
        if (!name.isSpecial()) {
            String strAsString = name.asString();
            strAsString.getClass();
            if (!AbstractComposeLoweringKt.unsafeSymbolsRegex.containsMatchIn(strAsString)) {
                return name;
            }
        }
        String strAsString2 = name.asString();
        strAsString2.getClass();
        Name nameIdentifier = Name.identifier(AbstractComposeLoweringKt.unsafeSymbolsRegex.replace(strAsString2, "\\$"));
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    public final boolean get(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    public final IrBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    public final IrClass getComposableIrClass() {
        return this.composableIrClass;
    }

    /* JADX INFO: renamed from: getComposeMetadata-fNhgvTQ, reason: not valid java name */
    public final byte[] m276getComposeMetadatafNhgvTQ(IrDeclaration irDeclaration) {
        irDeclaration.getClass();
        byte[] customMetadataExtension = this.context.getMetadataDeclarationRegistrar().getCustomMetadataExtension(irDeclaration, ComposePluginKt.COMPOSE_PLUGIN_ID);
        if (customMetadataExtension != null) {
            return ComposeMetadata.m268constructorimpl(customMetadataExtension);
        }
        return null;
    }

    public final IrClass getComposerIrClass() {
        return this.composerIrClass;
    }

    public final IrPluginContext getContext() {
        return this.context;
    }

    public final boolean getEnabled(FeatureFlag featureFlag) {
        featureFlag.getClass();
        return this.featureFlags.isEnabled(featureFlag);
    }

    public final boolean getHasExplicitGroups(IrFunction irFunction) {
        irFunction.getClass();
        return UtilsKt.hasAnnotation(irFunction, ComposeFqNames.INSTANCE.getExplicitGroupsComposable());
    }

    public final boolean getHasNonRestartableAnnotation(IrFunction irFunction) {
        irFunction.getClass();
        return UtilsKt.hasAnnotation(irFunction, ComposeFqNames.INSTANCE.getNonRestartableComposable());
    }

    public final boolean getHasNonSkippableAnnotation(IrFunction irFunction) {
        irFunction.getClass();
        return UtilsKt.hasAnnotation(irFunction, ComposeFqNames.INSTANCE.getNonSkippableComposable());
    }

    public final boolean getHasReadOnlyAnnotation(IrFunction irFunction) {
        irFunction.getClass();
        return UtilsKt.hasAnnotation(irFunction, ComposeFqNames.INSTANCE.getReadOnlyComposable());
    }

    public final ModuleMetrics getMetrics() {
        return this.metrics;
    }

    public final StabilityInferencer getStabilityInferencer() {
        return this.stabilityInferencer;
    }

    public final IrClassSymbol getTopLevelClass(ClassId classId) {
        classId.getClass();
        IrClassSymbol topLevelClassOrNull = getTopLevelClassOrNull(classId);
        if (topLevelClassOrNull != null) {
            return topLevelClassOrNull;
        }
        f2f.a("Class not found in the classpath: ", classId.asSingleFqName());
        return null;
    }

    public final IrClassSymbol getTopLevelClassOrNull(ClassId classId) {
        classId.getClass();
        return this.finderForBuiltins.findClass(classId);
    }

    public final IrSimpleFunctionSymbol getTopLevelFunction(CallableId callableId) {
        callableId.getClass();
        IrSimpleFunctionSymbol topLevelFunctionOrNull = getTopLevelFunctionOrNull(callableId);
        if (topLevelFunctionOrNull != null) {
            return topLevelFunctionOrNull;
        }
        f2f.a("Function not found in the classpath: ", callableId.asSingleFqName());
        return null;
    }

    public final IrSimpleFunctionSymbol getTopLevelFunctionOrNull(CallableId callableId) {
        callableId.getClass();
        return (IrSimpleFunctionSymbol) CollectionsKt.firstOrNull(this.finderForBuiltins.findFunctions(callableId));
    }

    public final List<IrSimpleFunctionSymbol> getTopLevelFunctions(CallableId callableId) {
        callableId.getClass();
        return CollectionsKt.toList(this.finderForBuiltins.findFunctions(callableId));
    }

    public final IrFunctionSymbol getTopLevelPropertyGetter(CallableId callableId) {
        callableId.getClass();
        IrPropertySymbol irPropertySymbol = (IrPropertySymbol) CollectionsKt.firstOrNull(this.finderForBuiltins.findProperties(callableId));
        if (irPropertySymbol == null) {
            f2f.a("Property was not found ", callableId.asSingleFqName());
            return null;
        }
        IrSimpleFunction getter = irPropertySymbol.getOwner().getGetter();
        getter.getClass();
        return getter.getSymbol();
    }

    public final boolean hasComposableAnnotation(IrAnnotationContainer irAnnotationContainer) {
        irAnnotationContainer.getClass();
        return UtilsKt.hasAnnotation(irAnnotationContainer, ComposeFqNames.INSTANCE.getComposable());
    }

    public final IrCallImpl irAnd(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        return binaryOperatorCall$default(this, lhs, rhs, OperatorNameConventions.AND, null, null, 24, null);
    }

    public final IrExpression irAndAnd(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        return BuildersKt.IrWhenImpl(-1, -1, this.context.getIrBuiltIns().getBooleanType(), IrStatementOrigin.Companion.getANDAND(), CollectionsKt.listOf(new IrBranch[]{BuildersKt.IrBranchImpl(-1, -1, lhs, rhs), BuildersKt.IrElseBranchImpl(-1, -1, irConst(true), irConst(false))}));
    }

    public final IrBlock irBlock(IrType type, IrStatementOrigin origin, int startOffset, int endOffset, List<? extends IrStatement> statements) {
        type.getClass();
        statements.getClass();
        return BuildersKt.IrBlockImpl(startOffset, endOffset, type, origin, statements);
    }

    public final IrCallImpl irBooleanOr(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        IrType booleanType = this.context.getIrBuiltIns().getBooleanType();
        return binaryOperatorCall(lhs, rhs, OperatorNameConventions.OR, booleanType, booleanType);
    }

    public final IrBranch irBranch(IrExpression condition, IrExpression result) {
        condition.getClass();
        result.getClass();
        return BuildersKt.IrBranchImpl(condition, result);
    }

    public final IrCall irCache(IrExpression currentComposer, int startOffset, int endOffset, IrType returnType, IrExpression invalid, IrExpression calculation) {
        currentComposer.getClass();
        returnType.getClass();
        invalid.getClass();
        calculation.getClass();
        IrSimpleFunctionSymbol symbol = getCacheFunction().getSymbol();
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(startOffset, endOffset, returnType, symbol, symbol.getOwner().getTypeParameters().size(), (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
        irCallImplIrCallImpl$default.getArguments().set(0, currentComposer);
        irCallImplIrCallImpl$default.getArguments().set(1, invalid);
        irCallImplIrCallImpl$default.getArguments().set(2, calculation);
        irCallImplIrCallImpl$default.getTypeArguments().set(0, returnType);
        return irCallImplIrCallImpl$default;
    }

    public final IrCallImpl irCall(IrFunctionSymbol symbol, IrStatementOrigin origin, IrType returnType, IrExpression dispatchReceiver, IrExpression extensionReceiver, IrExpression... args) {
        symbol.getClass();
        returnType.getClass();
        args.getClass();
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) symbol;
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(-1, -1, returnType, irSimpleFunctionSymbol, irSimpleFunctionSymbol.getOwner().getTypeParameters().size(), origin, (IrClassSymbol) null, 64, (Object) null);
        int i = 0;
        for (IrValueParameter irValueParameter : irSimpleFunctionSymbol.getOwner().getParameters()) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[irValueParameter.getKind().ordinal()];
            if (i2 == 1) {
                irCallImplIrCallImpl$default.getArguments().set(irValueParameter.getIndexInParameters(), dispatchReceiver);
            } else if (i2 == 2) {
                irCallImplIrCallImpl$default.getArguments().set(irValueParameter.getIndexInParameters(), extensionReceiver);
            } else {
                if (i2 != 3 && i2 != 4) {
                    bu8.a();
                    return null;
                }
                irCallImplIrCallImpl$default.getArguments().set(irValueParameter.getIndexInParameters(), args[i]);
                i++;
            }
        }
        return irCallImplIrCallImpl$default;
    }

    public final IrExpression irChanged(IrExpression currentComposer, IrExpression value, IrFile fileContainingValue, boolean inferredStable, boolean compareInstanceForFunctionTypes, boolean compareInstanceForUnstableValues) {
        currentComposer.getClass();
        value.getClass();
        IrExpression irExpressionOrdinalIfEnum = ordinalIfEnum(unboxValueIfInline(value));
        IrType type = irExpressionOrdinalIfEnum.getType();
        Stability stabilityStabilityOf = this.stabilityInferencer.stabilityOf(value, fileContainingValue);
        IrSimpleFunction irSimpleFunction = getChangedPrimitiveFunctions().get(toPrimitiveType(type));
        if (!compareInstanceForUnstableValues) {
            if (irSimpleFunction == null) {
                irSimpleFunction = (IrTypeUtilsKt.isFunction(type) && compareInstanceForFunctionTypes) ? this.changedInstanceFunction : this.changedFunction;
            }
            IrCall irCallIrMethodCall$default = irMethodCall$default(this, currentComposer, irSimpleFunction, 0, 0, 12, null);
            irCallIrMethodCall$default.getArguments().set(1, irExpressionOrdinalIfEnum);
            return irCallIrMethodCall$default;
        }
        if (irSimpleFunction == null) {
            if (compareInstanceForFunctionTypes && IrTypeUtilsKt.isFunction(type)) {
                irSimpleFunction = this.changedInstanceFunction;
            } else if (StabilityKt.knownStable(stabilityStabilityOf) || inferredStable) {
                irSimpleFunction = this.changedFunction;
            } else {
                if (!StabilityKt.knownUnstable(stabilityStabilityOf) && !StabilityKt.isUncertain(stabilityStabilityOf)) {
                    k2d.a("Cannot determine descriptor for irChanged");
                    return null;
                }
                irSimpleFunction = this.changedInstanceFunction;
            }
        }
        IrCall irCallIrMethodCall$default2 = irMethodCall$default(this, currentComposer, irSimpleFunction, 0, 0, 12, null);
        irCallIrMethodCall$default2.getArguments().set(1, irExpressionOrdinalIfEnum);
        return irCallIrMethodCall$default2;
    }

    public final IrExpression irComposite(IrType type, IrStatementOrigin origin, List<? extends IrStatement> statements) {
        type.getClass();
        statements.getClass();
        return new IrCompositeImpl(-1, -1, type, origin, statements);
    }

    public final IrConst irConst(int value) {
        return BuildersKt.IrConstImpl(-1, -1, this.context.getIrBuiltIns().getIntType(), IrConstKind.Int.INSTANCE, Integer.valueOf(value));
    }

    public final IrElseBranchImpl irElseBranch(IrExpression expression, int startOffset, int endOffset) {
        expression.getClass();
        return BuildersKt.IrElseBranchImpl(startOffset, endOffset, irConst(true), expression);
    }

    public final IrExpression irEndReplaceGroup(IrExpression currentComposer, int startOffset, int endOffset) {
        currentComposer.getClass();
        return irMethodCall(currentComposer, getEndReplaceFunction(), startOffset, endOffset);
    }

    public final IrExpression irEqual(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        return irCall$default(this, this.context.getIrBuiltIns().getEqeqSymbol(), null, null, null, null, new IrExpression[]{lhs, rhs}, 30, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrStatement irForLoop(IrDeclarationParent parent, IrType elementType, IrExpression subject, Function1<? super IrValueDeclaration, ? extends IrExpression> loopBody) {
        parent.getClass();
        elementType.getClass();
        subject.getClass();
        loopBody.getClass();
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(subject.getType());
        classOrNull.getClass();
        Object obj = null;
        boolean z = false;
        for (Object obj2 : IrUtilsKt.getFunctions(classOrNull.getOwner())) {
            if (Intrinsics.areEqual(((IrSimpleFunction) obj2).getName().asString(), "iterator")) {
                if (z) {
                    w01.a("Sequence contains more than one matching element.");
                    return null;
                }
                obj = obj2;
                z = true;
            }
        }
        if (!z) {
            hb9.a("Sequence contains no element matching the predicate.");
            return null;
        }
        IrSimpleFunction irSimpleFunction = (IrSimpleFunction) obj;
        IrClassSymbol classOrNull2 = IrTypesKt.getClassOrNull(irSimpleFunction.getReturnType());
        classOrNull2.getClass();
        IrSimpleType irSimpleTypeTypeWith = !classOrNull2.getOwner().getTypeParameters().isEmpty() ? IrTypesKt.typeWith(classOrNull2, new IrType[]{elementType}) : IrTypesKt.getDefaultType(classOrNull2);
        Object obj3 = null;
        boolean z2 = false;
        for (Object obj4 : IrUtilsKt.getFunctions(classOrNull2.getOwner())) {
            if (Intrinsics.areEqual(((IrSimpleFunction) obj4).getName().asString(), "next")) {
                if (z2) {
                    w01.a("Sequence contains more than one matching element.");
                    return null;
                }
                z2 = true;
                obj3 = obj4;
            }
        }
        if (!z2) {
            hb9.a("Sequence contains no element matching the predicate.");
            return null;
        }
        IrSimpleFunction irSimpleFunction2 = (IrSimpleFunction) obj3;
        Object obj5 = null;
        boolean z3 = false;
        for (Object obj6 : IrUtilsKt.getFunctions(classOrNull2.getOwner())) {
            if (Intrinsics.areEqual(((IrSimpleFunction) obj6).getName().asString(), "hasNext")) {
                if (z3) {
                    w01.a("Sequence contains more than one matching element.");
                    return null;
                }
                obj5 = obj6;
                z3 = true;
            }
        }
        if (!z3) {
            hb9.a("Sequence contains no element matching the predicate.");
            return null;
        }
        IrSimpleFunctionSymbol symbol = irSimpleFunction.getSymbol();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        IrSimpleType irSimpleType = irSimpleTypeTypeWith;
        IrCallImpl irCallImplIrCall$default = irCall$default(this, symbol, companion.getFOR_LOOP_ITERATOR(), irSimpleType, subject, null, new IrExpression[0], 16, null);
        IrDeclarationOrigin.Companion companion2 = IrDeclarationOrigin.Companion;
        IrElement irElementIrTemporary = irTemporary(irCallImplIrCall$default, "tmp0_iterator", irSimpleType, false, companion2.getFOR_LOOP_ITERATOR());
        IrType unitType = this.builtIns.getUnitType();
        IrStatementOriginImpl for_loop = companion.getFOR_LOOP();
        IrElement irElementIrWhileLoopImpl = BuildersKt.IrWhileLoopImpl(-1, -1, this.builtIns.getUnitType(), companion.getFOR_LOOP_INNER_WHILE());
        IrVariableImpl irVariableImplIrTemporary = irTemporary(irCall$default(this, irSimpleFunction2.getSymbol(), companion.getFOR_LOOP_NEXT(), elementType, irGet(irElementIrTemporary), null, new IrExpression[0], 16, null), "value", elementType, false, companion2.getFOR_LOOP_VARIABLE());
        irVariableImplIrTemporary.setParent(parent);
        irElementIrWhileLoopImpl.setCondition(irCall$default(this, ((IrSimpleFunction) obj5).getSymbol(), companion.getFOR_LOOP_HAS_NEXT(), null, irGet(irElementIrTemporary), null, new IrExpression[0], 20, null));
        irElementIrWhileLoopImpl.setBody(irBlock$default(this, this.builtIns.getUnitType(), companion.getFOR_LOOP_INNER_WHILE(), 0, 0, CollectionsKt.listOf(new IrElement[]{irVariableImplIrTemporary, loopBody.invoke(irVariableImplIrTemporary)}), 12, null));
        Unit unit = Unit.INSTANCE;
        return irBlock$default(this, unitType, for_loop, 0, 0, CollectionsKt.listOf(new IrElement[]{irElementIrTemporary, irElementIrWhileLoopImpl}), 12, null);
    }

    public final IrExpression irGet(IrType type, IrValueSymbol symbol) {
        type.getClass();
        symbol.getClass();
        return BuildersKt.IrGetValueImpl$default(-1, -1, type, symbol, (IrStatementOrigin) null, 16, (Object) null);
    }

    public final IrExpression irGetBit(IrDefaultBitMaskValue param, int index) {
        param.getClass();
        return irNotEqual(param.irIsolateBitAtIndex(index), irConst(0));
    }

    public final IrGetField irGetField(IrField field) {
        field.getClass();
        return BuildersKt.IrGetFieldImpl$default(-1, -1, field.getSymbol(), field.getType(), (IrStatementOrigin) null, (IrClassSymbol) null, 48, (Object) null);
    }

    public final IrCallImpl irGreater(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) this.context.getIrBuiltIns().getGreaterFunByOperandType().get(IrTypesKt.getClassifierOrFail(this.context.getIrBuiltIns().getIntType()));
        irSimpleFunctionSymbol.getClass();
        return irCall$default(this, irSimpleFunctionSymbol, IrStatementOrigin.Companion.getGT(), null, null, null, new IrExpression[]{lhs, rhs}, 28, null);
    }

    public final IrExpression irIf(IrExpression condition, IrExpression body) {
        condition.getClass();
        body.getClass();
        IrWhenImpl IrWhenImpl = BuildersKt.IrWhenImpl(-1, -1, this.context.getIrBuiltIns().getUnitType(), IrStatementOrigin.Companion.getIF());
        IrWhenImpl.getBranches().add(BuildersKt.IrBranchImpl(condition, body));
        return IrWhenImpl;
    }

    public final IrWhenImpl irIfThenElse(IrType type, IrExpression condition, IrExpression thenPart, IrExpression elsePart, int startOffset, int endOffset) {
        type.getClass();
        condition.getClass();
        thenPart.getClass();
        elsePart.getClass();
        IrWhenImpl IrWhenImpl = BuildersKt.IrWhenImpl(startOffset, endOffset, type, IrStatementOrigin.Companion.getIF());
        IrWhenImpl.getBranches().add(BuildersKt.IrBranchImpl(startOffset, endOffset, condition, thenPart));
        IrWhenImpl.getBranches().add(irElseBranch(elsePart, startOffset, endOffset));
        return IrWhenImpl;
    }

    public final IrExpression irLambdaExpression(int startOffset, int endOffset, IrType returnType, Function1<? super IrSimpleFunction, Unit> body) {
        returnType.getClass();
        body.getClass();
        IrFactory irFactory = this.context.getIrFactory();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        irFunctionBuilder.setStartOffset(-2);
        irFunctionBuilder.setEndOffset(-2);
        irFunctionBuilder.setReturnType(returnType);
        irFunctionBuilder.setOrigin(IrDeclarationOrigin.Companion.getLOCAL_FUNCTION_FOR_LAMBDA());
        irFunctionBuilder.setName(SpecialNames.ANONYMOUS);
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.LOCAL;
        descriptorVisibility.getClass();
        irFunctionBuilder.setVisibility(descriptorVisibility);
        IrSimpleFunction irSimpleFunctionBuildFunction = DeclarationBuildersKt.buildFunction(irFactory, irFunctionBuilder);
        body.invoke(irSimpleFunctionBuildFunction);
        IrClass irClassFunctionN = this.context.getIrBuiltIns().functionN(irSimpleFunctionBuildFunction.getParameters().size());
        List parameters = irSimpleFunctionBuildFunction.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        Iterator it = parameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((IrValueParameter) it.next()).getType());
        }
        return BuildersKt.IrFunctionExpressionImpl(startOffset, endOffset, IrTypesKt.typeWith(irClassFunctionN, CollectionsKt.plus(arrayList, CollectionsKt.listOf(irSimpleFunctionBuildFunction.getReturnType()))), irSimpleFunctionBuildFunction, IrStatementOrigin.Companion.getLAMBDA());
    }

    public final IrCall irMethodCall(IrExpression target, IrFunction function, int startOffset, int endOffset) {
        target.getClass();
        function.getClass();
        IrCall irCall = irCall(function, startOffset, endOffset);
        irCall.getArguments().set(0, target);
        return irCall;
    }

    public final IrExpression irNot(IrExpression value) {
        value.getClass();
        return irCall$default(this, this.context.getIrBuiltIns().getBooleanNotSymbol(), null, null, value, null, new IrExpression[0], 22, null);
    }

    public final IrExpression irNotEqual(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        return irNot(irEqual(lhs, rhs));
    }

    public final IrConstImpl irNull() {
        return BuildersKt.IrConstImpl(-1, -1, this.context.getIrBuiltIns().getAnyNType(), IrConstKind.Null.INSTANCE, (Object) null);
    }

    public final IrExpression irOr(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        if ((rhs instanceof IrConst) && Intrinsics.areEqual(((IrConst) rhs).getValue(), 0)) {
            return lhs;
        }
        if ((lhs instanceof IrConst) && Intrinsics.areEqual(((IrConst) lhs).getValue(), 0)) {
            return rhs;
        }
        IrType intType = this.context.getIrBuiltIns().getIntType();
        return binaryOperatorCall(lhs, rhs, OperatorNameConventions.OR, intType, intType);
    }

    public final IrExpression irOrOr(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        return BuildersKt.IrWhenImpl(-1, -1, this.context.getIrBuiltIns().getBooleanType(), IrStatementOrigin.Companion.getOROR(), CollectionsKt.listOf(new IrBranch[]{BuildersKt.IrBranchImpl(-1, -1, lhs, irConst(true)), BuildersKt.IrElseBranchImpl(-1, -1, irConst(true), rhs)}));
    }

    public final IrExpression irReturn(IrReturnTargetSymbol target, IrExpression value, IrType type) {
        target.getClass();
        value.getClass();
        type.getClass();
        return new IrReturnImpl(-1, -1, type, target, value);
    }

    public final IrExpression irReturnVar(IrReturnTargetSymbol target, IrVariable value) {
        target.getClass();
        value.getClass();
        IrExpression initializer = value.getInitializer();
        int startOffset = initializer != null ? initializer.getStartOffset() : -1;
        IrExpression initializer2 = value.getInitializer();
        return new IrReturnImpl(startOffset, initializer2 != null ? initializer2.getEndOffset() : -1, value.getType(), target, irGet(value));
    }

    public final IrExpression irSet(IrValueDeclaration variable, IrExpression value) {
        variable.getClass();
        value.getClass();
        return BuildersKt.IrSetValueImpl(-1, -1, this.context.getIrBuiltIns().getUnitType(), variable.getSymbol(), value, (IrStatementOrigin) null);
    }

    public final IrCallImpl irShl(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        IrType intType = this.context.getIrBuiltIns().getIntType();
        return binaryOperatorCall(lhs, rhs, OperatorNameConventions.SHL, intType, intType);
    }

    public final IrExpression irStableExpression(Stability stability, Function1<? super IrTypeParameter, ? extends IrExpression> function1, Function1<? super IrClass, Unit> function2) {
        stability.getClass();
        function1.getClass();
        function2.getClass();
        if (!(stability instanceof Stability.Combined)) {
            if (stability instanceof Stability.Certain) {
                if (((Stability.Certain) stability).getStable()) {
                    return irConst(StabilityBits.STABLE.bitsForSlot(0));
                }
                return null;
            }
            if (stability instanceof Stability.Parameter) {
                return (IrExpression) function1.invoke(((Stability.Parameter) stability).getParameter());
            }
            if (!(stability instanceof Stability.Runtime)) {
                if (stability instanceof Stability.Unknown) {
                    return null;
                }
                bu8.a();
                return null;
            }
            Stability.Runtime runtime = (Stability.Runtime) stability;
            IrExpression runtimeStabilityValue = getRuntimeStabilityValue(runtime.getDeclaration());
            if (runtimeStabilityValue == null) {
                function2.invoke(runtime.getDeclaration());
            }
            return runtimeStabilityValue;
        }
        Stability.Combined combined = (Stability.Combined) stability;
        List<Stability> elements = combined.getElements();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            IrExpression irExpressionIrStableExpression = irStableExpression((Stability) it.next(), function1, function2);
            if (irExpressionIrStableExpression != null) {
                arrayList.add(irExpressionIrStableExpression);
            }
        }
        if (arrayList.size() != combined.getElements().size()) {
            return null;
        }
        if (arrayList.isEmpty()) {
            return irConst(StabilityBits.STABLE.bitsForSlot(0));
        }
        if (arrayList.size() == 1) {
            return (IrExpression) CollectionsKt.first(arrayList);
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return null;
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            next = irOr((IrExpression) next, (IrExpression) it2.next());
        }
        return (IrExpression) next;
    }

    public final IrExpression irStartReplaceGroup(IrExpression currentComposer, IrExpression key, int startOffset, int endOffset) {
        currentComposer.getClass();
        key.getClass();
        IrCall irCallIrMethodCall = irMethodCall(currentComposer, getStartReplaceFunction(), startOffset, endOffset);
        irCallIrMethodCall.getArguments().set(1, key);
        return irCallIrMethodCall;
    }

    public final IrVariableImpl irTemporary(IrExpression value, String name, IrType irType, boolean isVar, IrDeclarationOrigin origin) {
        value.getClass();
        name.getClass();
        irType.getClass();
        origin.getClass();
        int startOffset = value.getStartOffset();
        int endOffset = value.getEndOffset();
        IrVariableSymbolImpl irVariableSymbolImpl = new IrVariableSymbolImpl((VariableDescriptor) null, 1, (DefaultConstructorMarker) null);
        Name nameIdentifier = Name.identifier(name);
        nameIdentifier.getClass();
        IrVariableImpl IrVariableImpl = org.jetbrains.kotlin.ir.declarations.impl.BuildersKt.IrVariableImpl(startOffset, endOffset, origin, irVariableSymbolImpl, nameIdentifier, irType, isVar, false, false);
        IrVariableImpl.setInitializer(value);
        return IrVariableImpl;
    }

    public final IrWhenImpl irWhen(IrType type, IrStatementOrigin origin, List<? extends IrBranch> branches) {
        type.getClass();
        branches.getClass();
        return BuildersKt.IrWhenImpl(-1, -1, type, origin, branches);
    }

    public final IrCallImpl irXor(IrExpression lhs, IrExpression rhs) {
        lhs.getClass();
        rhs.getClass();
        IrType intType = this.context.getIrBuiltIns().getIntType();
        return binaryOperatorCall(lhs, rhs, OperatorNameConventions.XOR, intType, intType);
    }

    public final boolean isComposableCall(IrCall irCall) {
        irCall.getClass();
        return hasComposableAnnotation(irCall.getSymbol().getOwner()) || isComposableLambdaInvoke(irCall);
    }

    public final boolean isComposableDelegatedAccessor(IrFunction irFunction) {
        boolean zAreEqual;
        IrSimpleFunctionSymbol symbol;
        irFunction.getClass();
        if (Intrinsics.areEqual(irFunction.getOrigin(), IrDeclarationOrigin.Companion.getDELEGATED_PROPERTY_ACCESSOR())) {
            IrBody body = irFunction.getBody();
            if (body != null) {
                Object objSingleOrNull = CollectionsKt.singleOrNull(IrUtilsKt.getStatements(body));
                IrReturn irReturn = objSingleOrNull instanceof IrReturn ? (IrReturn) objSingleOrNull : null;
                IrExpression value = irReturn != null ? irReturn.getValue() : null;
                IrCall irCall = value instanceof IrCall ? (IrCall) value : null;
                IrSimpleFunction owner = (irCall == null || (symbol = irCall.getSymbol()) == null) ? null : symbol.getOwner();
                zAreEqual = Intrinsics.areEqual(owner != null ? Boolean.valueOf(hasComposableAnnotation(owner)) : null, Boolean.TRUE);
            } else {
                zAreEqual = false;
            }
            if (zAreEqual) {
                return true;
            }
        }
        return false;
    }

    public final boolean isComposableLambdaInvoke(IrCall irCall) {
        IrType type;
        irCall.getClass();
        if (!isInvoke(irCall)) {
            return false;
        }
        IrExpression dispatchReceiver = irCall.getDispatchReceiver();
        IrExpression irExpression = null;
        if (dispatchReceiver != null) {
            IrElement attributeOwnerId = dispatchReceiver.getAttributeOwnerId();
            irExpression = attributeOwnerId instanceof IrExpression ? (IrExpression) attributeOwnerId : null;
            if (irExpression != null) {
                dispatchReceiver = irExpression;
            }
            irExpression = dispatchReceiver;
        }
        if (irExpression == null || (type = irExpression.getType()) == null) {
            return false;
        }
        return hasComposableAnnotation(type) || AbstractComposeLoweringKt.isSyntheticComposableFunction(type);
    }

    public final boolean isComposableSingletonClass(IrClass irClass) {
        irClass.getClass();
        return Intrinsics.areEqual(WeakBindingTraceKt.getIrTrace(this.context).get(ComposeWritableSlices.INSTANCE.getIS_COMPOSABLE_SINGLETON_CLASS(), irClass), Boolean.TRUE);
    }

    public final boolean isComposableSingletonGetter(IrCall irCall) {
        irCall.getClass();
        return Intrinsics.areEqual(WeakBindingTraceKt.getIrTrace(this.context).get(ComposeWritableSlices.INSTANCE.getIS_COMPOSABLE_SINGLETON(), irCall), Boolean.TRUE);
    }

    public final boolean isInvoke(IrCall irCall) {
        IrClass parentClassOrNull;
        IrSimpleType defaultType;
        irCall.getClass();
        if (Intrinsics.areEqual(irCall.getOrigin(), IrStatementOrigin.Companion.getINVOKE())) {
            return true;
        }
        IrSimpleFunction owner = irCall.getSymbol().getOwner();
        if (!Intrinsics.areEqual(owner.getName(), OperatorNameConventions.INVOKE) || (parentClassOrNull = IrUtilsKt.getParentClassOrNull(owner)) == null || (defaultType = IrUtilsKt.getDefaultType(parentClassOrNull)) == null) {
            return false;
        }
        return IrTypeUtilsKt.isFunction(defaultType) || AbstractComposeLoweringKt.isSyntheticComposableFunction(defaultType);
    }

    public final boolean isSyntheticComposableCall(IrCall irCall) {
        irCall.getClass();
        return Intrinsics.areEqual(WeakBindingTraceKt.getIrTrace(this.context).get(ComposeWritableSlices.INSTANCE.getIS_SYNTHETIC_COMPOSABLE_CALL(), irCall), Boolean.TRUE);
    }

    public final boolean isVirtualFunctionWithDefaultParam(IrFunction irFunction) {
        irFunction.getClass();
        if (!(irFunction instanceof IrSimpleFunction)) {
            return false;
        }
        IrSimpleFunction irSimpleFunction = (IrSimpleFunction) irFunction;
        if (Intrinsics.areEqual(ComposePluginAttributesKt.isVirtualFunctionWithDefaultParam(irSimpleFunction), Boolean.TRUE)) {
            return true;
        }
        List overriddenSymbols = irSimpleFunction.getOverriddenSymbols();
        if ((overriddenSymbols instanceof Collection) && overriddenSymbols.isEmpty()) {
            return false;
        }
        Iterator it = overriddenSymbols.iterator();
        while (it.hasNext()) {
            if (isVirtualFunctionWithDefaultParam(((IrSimpleFunctionSymbol) it.next()).getOwner())) {
                return true;
            }
        }
        return false;
    }

    public final IrField makeStabilityField$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(IrClass irClass) {
        irClass.getClass();
        IrField backingField = (JvmPlatformKt.isJvm(this.context.getPlatform()) ? buildStabilityPropJvm(irClass, true) : buildStabilityPropNonJvm(irClass, true)).getBackingField();
        backingField.getClass();
        return backingField;
    }

    public final IrSimpleFunction makeStub(IrSimpleFunction irSimpleFunction) {
        ClassId classId;
        IrClassSymbol annotationClass;
        IrClass owner;
        IrClass owner2;
        irSimpleFunction.getClass();
        IrDeclarationParent parent = irSimpleFunction.getParent();
        DeepCopySymbolRemapper deepCopySymbolRemapper = new DeepCopySymbolRemapper((DescriptorsRemapper) null, 1, (DefaultConstructorMarker) null);
        IrVisitorsKt.acceptVoid(irSimpleFunction, deepCopySymbolRemapper);
        IrSimpleFunction irSimpleFunctionTransform = irSimpleFunction.transform(new DeepCopyIrTreeWithSymbols(deepCopySymbolRemapper, new DeepCopyTypeRemapper(deepCopySymbolRemapper)), (Object) null);
        if (irSimpleFunctionTransform == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrSimpleFunction");
            return null;
        }
        IrSimpleFunction irSimpleFunctionPatchDeclarationParents = PatchDeclarationParentsKt.patchDeclarationParents(irSimpleFunctionTransform, parent);
        irSimpleFunctionPatchDeclarationParents.setAttributeOwnerId(irSimpleFunctionPatchDeclarationParents);
        ComposePluginAttributesKt.setDefaultParamStub(irSimpleFunctionPatchDeclarationParents, true);
        List listListOfNotNull = CollectionsKt.listOfNotNull(new IrAnnotationImpl[]{jvmSynthetic(), hiddenFromObjC(), hiddenDeprecated("Binary compatibility stub for default parameters")});
        List annotations = irSimpleFunctionPatchDeclarationParents.getAnnotations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : annotations) {
            IrAnnotation irAnnotation = (IrAnnotation) obj;
            List list = listListOfNotNull;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator it = list.iterator();
                do {
                    if (it.hasNext()) {
                        IrClassSymbol annotationClass2 = AbstractComposeLoweringKt.getAnnotationClass((IrAnnotationImpl) it.next());
                        classId = (annotationClass2 == null || (owner2 = annotationClass2.getOwner()) == null) ? null : AdditionalIrUtilsKt.getClassId(owner2);
                        annotationClass = AbstractComposeLoweringKt.getAnnotationClass(irAnnotation);
                    }
                } while (!Intrinsics.areEqual(classId, (annotationClass == null || (owner = annotationClass.getOwner()) == null) ? null : AdditionalIrUtilsKt.getClassId(owner)));
            }
            arrayList.add(obj);
        }
        irSimpleFunctionPatchDeclarationParents.setAnnotations(arrayList);
        irSimpleFunctionPatchDeclarationParents.setAnnotations(CollectionsKt.plus(irSimpleFunctionPatchDeclarationParents.getAnnotations(), listListOfNotNull));
        irSimpleFunctionPatchDeclarationParents.setBody((IrBody) null);
        return irSimpleFunctionPatchDeclarationParents;
    }

    public final FunctionMetrics metricsFor(IrFunction function) {
        function.getClass();
        FunctionMetrics functionMetrics = ComposePluginAttributesKt.getFunctionMetrics(function);
        if (functionMetrics != null) {
            return functionMetrics;
        }
        FunctionMetrics functionMetricsMakeFunctionMetrics = this.metrics.makeFunctionMetrics(function);
        ComposePluginAttributesKt.setFunctionMetrics(function, functionMetricsMakeFunctionMetrics);
        return functionMetricsMakeFunctionMetrics;
    }

    public final IrType replaceArgumentsWithStarProjections(IrType irType) {
        irType.getClass();
        if (!(irType instanceof IrSimpleType)) {
            return irType;
        }
        IrSimpleType irSimpleType = (IrSimpleType) irType;
        IrClassifierSymbol classifier = irSimpleType.getClassifier();
        boolean zIsMarkedNullable = IrTypePredicatesKt.isMarkedNullable(irSimpleType);
        int size = irSimpleType.getArguments().size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(IrStarProjectionImpl.INSTANCE);
        }
        return IrSimpleTypeImplKt.IrSimpleTypeImpl(classifier, zIsMarkedNullable, arrayList, irType.getAnnotations());
    }

    /* JADX INFO: renamed from: setComposeMetadata-IvR2BlU, reason: not valid java name */
    public final void m277setComposeMetadataIvR2BlU(IrDeclaration irDeclaration, byte[] bArr) {
        irDeclaration.getClass();
        if (bArr == null || !AbstractComposeLoweringKt.hasFirDeclaration(irDeclaration)) {
            return;
        }
        this.context.getMetadataDeclarationRegistrar().addCustomMetadataExtension(irDeclaration, ComposePluginKt.COMPOSE_PLUGIN_ID, bArr);
    }

    public final boolean shouldBeRestartable(IrFunction irFunction) {
        IrClass parentClassOrNull;
        irFunction.getClass();
        if (irFunction.getBody() == null || !(irFunction instanceof IrSimpleFunction)) {
            return false;
        }
        if (AdditionalIrUtilsKt.isLocal(irFunction)) {
            IrClass parentClassOrNull2 = IrUtilsKt.getParentClassOrNull(irFunction);
            if (!Intrinsics.areEqual(parentClassOrNull2 != null ? parentClassOrNull2.getOrigin() : null, JvmLoweredDeclarationOrigin.INSTANCE.getLAMBDA_IMPL())) {
                return false;
            }
        }
        if (irFunction.isInline() || getHasNonRestartableAnnotation(irFunction) || getHasExplicitGroups(irFunction) || !IrTypePredicatesKt.isUnit(irFunction.getReturnType()) || isComposableDelegatedAccessor(irFunction) || isVirtualFunctionWithDefaultParam(irFunction)) {
            return false;
        }
        if (((IrSimpleFunction) irFunction).getModality() != Modality.OPEN || ((parentClassOrNull = IrUtilsKt.getParentClassOrNull(irFunction)) != null && IrUtilsKt.isFinalClass(parentClassOrNull))) {
            return !Intrinsics.areEqual(irFunction.getOrigin(), IrDeclarationOrigin.Companion.getLOCAL_FUNCTION_FOR_LAMBDA());
        }
        return false;
    }

    public final int sourceKey(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        KeyInfo keyInfo = (KeyInfo) WeakBindingTraceKt.getIrTrace(this.context).get(ComposeWritableSlices.INSTANCE.getDURABLE_FUNCTION_KEY(), irSimpleFunction);
        if (keyInfo != null) {
            keyInfo.setUsed(true);
            return keyInfo.getKey();
        }
        String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(irSimpleFunction.getSymbol().getDescriptor(), false, false, 1, (Object) null);
        return (AdditionalIrUtilsKt.getFqNameForIrSerialization(irSimpleFunction) + strComputeJvmDescriptor$default).hashCode();
    }

    public final IrType unboxInlineClass(IrType irType) {
        irType.getClass();
        IrType irTypeUnboxType = unboxType(irType);
        return irTypeUnboxType == null ? irType : irTypeUnboxType;
    }

    public final IrType unboxType(IrType irType) {
        IrClass owner;
        InlineClassRepresentation inlineClassRepresentation;
        irType.getClass();
        IrClassSymbol classOrNull = IrTypesKt.getClassOrNull(irType);
        if (classOrNull == null || (owner = classOrNull.getOwner()) == null || (inlineClassRepresentation = IrDeclarationsKt.getInlineClassRepresentation(owner)) == null || !JvmIrTypeUtilsKt.isInlineClassType(irType)) {
            return null;
        }
        IrType irTypeUnboxInlineClass = unboxInlineClass((IrType) inlineClassRepresentation.getUnderlyingType());
        if (!IrTypeUtilsKt.isNullable(irType)) {
            return irTypeUnboxInlineClass;
        }
        if (!IrTypeUtilsKt.isNullable(irTypeUnboxInlineClass) && !IrTypePredicatesKt.isPrimitiveType$default(irTypeUnboxInlineClass, false, 1, (Object) null)) {
            return IrTypesKt.makeNullable(irTypeUnboxInlineClass);
        }
        return null;
    }

    public final IrExpression unboxValueIfInline(IrExpression irExpression) {
        IrClassSymbol classOrNull;
        IrValueParameter irValueParameter;
        List parameters;
        irExpression.getClass();
        if (!IrTypeUtilsKt.isNullable(irExpression.getType()) && (classOrNull = IrTypesKt.getClassOrNull(irExpression.getType())) != null) {
            IrClass owner = classOrNull.getOwner();
            if (JvmIrTypeUtilsKt.isInlineClassType(irExpression.getType())) {
                if (JvmPlatformKt.isJvm(this.context.getPlatform())) {
                    return unboxValueIfInline(coerceInlineClasses(irExpression, irExpression.getType(), unboxInlineClass(irExpression.getType())));
                }
                IrConstructor primaryConstructor = IrUtilsKt.getPrimaryConstructor(owner);
                if (primaryConstructor == null || (parameters = primaryConstructor.getParameters()) == null) {
                    irValueParameter = null;
                } else {
                    Iterator it = parameters.iterator();
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
                        if (((IrValueParameter) next).getKind() == IrParameterKind.Regular) {
                            if (!z) {
                                z = true;
                                obj = next;
                            }
                        }
                        obj = null;
                        break;
                    }
                    irValueParameter = (IrValueParameter) obj;
                }
                if (irValueParameter != null) {
                    for (IrProperty irProperty : IrUtilsKt.getProperties(owner)) {
                        if (Intrinsics.areEqual(irProperty.getName(), irValueParameter.getName()) && irProperty.getGetter() != null) {
                            String identifier = irValueParameter.getName().getIdentifier();
                            identifier.getClass();
                            IrSimpleFunctionSymbol propertyGetter = AdditionalIrUtilsKt.getPropertyGetter(owner, identifier);
                            if (propertyGetter != null) {
                                AbstractComposeLowering abstractComposeLowering = this;
                                return abstractComposeLowering.unboxValueIfInline(irCall$default(abstractComposeLowering, propertyGetter, null, null, irExpression, null, new IrExpression[0], 22, null));
                            }
                            k2d.a("Expected a getter");
                            return null;
                        }
                        this = this;
                        irExpression = irExpression;
                    }
                }
                return irExpression;
            }
        }
        return irExpression;
    }

    public final IrContainerExpression wrap(IrStatement irStatement, int i, int i2, IrType irType, List<? extends IrStatement> list, List<? extends IrStatement> list2) {
        irStatement.getClass();
        irType.getClass();
        list.getClass();
        list2.getClass();
        return BuildersKt.IrBlockImpl(i, i2, irType, (IrStatementOrigin) null, CollectionsKt.plus(CollectionsKt.plus(list, irStatement), list2));
    }

    public final IrExpression irGet(IrValueDeclaration variable) {
        variable.getClass();
        return irGet(variable.getType(), variable.getSymbol());
    }

    public final IrConst irConst(long value) {
        return BuildersKt.IrConstImpl(-1, -1, this.context.getIrBuiltIns().getLongType(), IrConstKind.Long.INSTANCE, Long.valueOf(value));
    }

    public final IrConst irConst(String value) {
        value.getClass();
        return BuildersKt.IrConstImpl(-1, -1, this.context.getIrBuiltIns().getStringType(), IrConstKind.String.INSTANCE, value);
    }

    public final IrConstImpl irConst(boolean value) {
        return BuildersKt.IrConstImpl(-1, -1, this.context.getIrBuiltIns().getBooleanType(), IrConstKind.Boolean.INSTANCE, Boolean.valueOf(value));
    }

    public static /* synthetic */ IrCall irCall$default(AbstractComposeLowering abstractComposeLowering, IrFunction irFunction, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irCall");
            return null;
        }
        if ((i3 & 2) != 0) {
            i = -1;
        }
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        return abstractComposeLowering.irCall(irFunction, i, i2);
    }

    public final IrCallImpl irCall(IrFunctionSymbol symbol) {
        symbol.getClass();
        return BuildersKt.IrCallImpl$default(-1, -1, symbol.getOwner().getReturnType(), (IrSimpleFunctionSymbol) symbol, 0, (IrStatementOrigin) null, (IrClassSymbol) null, 112, (Object) null);
    }

    public final IrCall irCall(IrFunction function, int startOffset, int endOffset) {
        function.getClass();
        IrType returnType = function.getReturnType();
        IrSimpleFunctionSymbol symbol = function.getSymbol();
        symbol.getClass();
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = symbol;
        return BuildersKt.IrCallImpl$default(startOffset, endOffset, returnType, irSimpleFunctionSymbol, irSimpleFunctionSymbol.getOwner().getTypeParameters().size(), (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
    }

    private final boolean isStatic(IrConstructorCall irConstructorCall, IrFile irFile) {
        IrExpression irExpression;
        if (JvmIrTypeUtilsKt.isInlineClassType(irConstructorCall.getType())) {
            return StabilityKt.knownStable(this.stabilityInferencer.stabilityOf(unboxInlineClass(irConstructorCall.getType()), irFile)) && (irExpression = (IrExpression) irConstructorCall.getArguments().get(0)) != null && isStatic(irExpression, irFile);
        }
        if (AbstractComposeLoweringKt.hasAnnotationSafe(IrUtilsKt.getParentAsClass(irConstructorCall.getSymbol().getOwner()), ComposeFqNames.INSTANCE.getImmutable())) {
            return areAllArgumentsStatic(irConstructorCall, irFile);
        }
        return false;
    }

    public final boolean isStatic(IrExpression irExpression, IrFile irFile) {
        Boolean bool;
        IrProperty owner;
        IrExpression initializer;
        irExpression.getClass();
        if ((irExpression instanceof IrConst) || (irExpression instanceof IrGetEnumValue)) {
            return true;
        }
        if (irExpression instanceof IrGetObjectValue) {
            if (IrUtilsKt.isObject(((IrGetObjectValue) irExpression).getSymbol().getOwner())) {
                return true;
            }
            return StabilityKt.knownStable(this.stabilityInferencer.stabilityOf(irExpression.getType(), irFile));
        }
        if (irExpression instanceof IrConstructorCall) {
            return isStatic((IrConstructorCall) irExpression, irFile);
        }
        if (irExpression instanceof IrCall) {
            return isStatic((IrCall) irExpression, irFile);
        }
        if (irExpression instanceof IrGetValue) {
            IrVariable owner2 = ((IrGetValue) irExpression).getSymbol().getOwner();
            if (owner2 instanceof IrVariable) {
                IrVariable irVariable = owner2;
                if (!irVariable.isVar() && (initializer = irVariable.getInitializer()) != null && isStatic(initializer, irFile)) {
                    return true;
                }
            }
            return false;
        }
        if (!(irExpression instanceof IrFunctionExpression) && !(irExpression instanceof IrTypeOperatorCall)) {
            if (irExpression instanceof IrGetField) {
                IrPropertySymbol correspondingPropertySymbol = ((IrGetField) irExpression).getSymbol().getOwner().getCorrespondingPropertySymbol();
                return (correspondingPropertySymbol == null || (owner = correspondingPropertySymbol.getOwner()) == null || !owner.isConst()) ? false : true;
            }
            if (!(irExpression instanceof IrBlock) || (bool = (Boolean) WeakBindingTraceKt.getIrTrace(this.context).get(ComposeWritableSlices.INSTANCE.getIS_STATIC_EXPRESSION(), irExpression)) == null) {
                return false;
            }
            return bool.booleanValue();
        }
        Boolean bool2 = (Boolean) WeakBindingTraceKt.getIrTrace(this.context).get(ComposeWritableSlices.INSTANCE.getIS_STATIC_FUNCTION_EXPRESSION(), irExpression);
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        return false;
    }
}

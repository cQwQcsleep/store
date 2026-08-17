package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeCallableIds;
import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.lower.LiveLiteralTransformer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.SourceRangeInfo;
import org.jetbrains.kotlin.ir.builders.ExpressionHelpersKt;
import org.jetbrains.kotlin.ir.builders.IrBlockBodyBuilder;
import org.jetbrains.kotlin.ir.builders.IrBlockBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.builders.declarations.IrClassBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrFieldBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrFunctionBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.IrPropertyBuilder;
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrCatch;
import org.jetbrains.kotlin.ir.expressions.IrComposite;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstKind;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrDelegatingConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrElseBranch;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrLoop;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrSetField;
import org.jetbrains.kotlin.ir.expressions.IrSetValue;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStringConcatenation;
import org.jetbrains.kotlin.ir.expressions.IrTry;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;
import org.jetbrains.kotlin.ir.expressions.IrWhen;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrStringConcatenationImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrVarargImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrWhenImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.load.kotlin.PackagePartClassUtils;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000È\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\f\u0010\u001f\u001a\u00020\u0004*\u00020 H\u0002J)\u0010!\u001a\u0002H\"\"\u0004\b\u0000\u0010\"2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\"0&H\u0002¢\u0006\u0002\u0010'J)\u0010(\u001a\u0002H\"\"\u0004\b\u0000\u0010\"2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\"0&H\u0002¢\u0006\u0002\u0010'J!\u0010(\u001a\u0002H\"\"\u0004\b\u0000\u0010\"2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H\"0&H\u0002¢\u0006\u0002\u0010)J\u0018\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0002J\f\u00104\u001a\u00020$*\u000205H\u0002J\u0018\u00106\u001a\u0002072\u0006\u0010#\u001a\u00020$2\u0006\u00108\u001a\u000202H\u0002J\u0010\u00109\u001a\u0002072\u0006\u0010:\u001a\u00020$H\u0002J(\u0010;\u001a\u00020<2\u0006\u0010#\u001a\u00020$2\u0006\u0010=\u001a\u0002002\u0006\u0010>\u001a\u00020?2\u0006\u00101\u001a\u000202H\u0002J\u0010\u0010@\u001a\u0002002\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020+H\u0016J\u0010\u0010F\u001a\u00020D2\u0006\u0010E\u001a\u00020GH\u0016J\u000e\u0010H\u001a\b\u0012\u0004\u0012\u00020$0IH\u0016J\u0010\u0010J\u001a\u00020.2\u0006\u0010E\u001a\u00020.H\u0016J\u0010\u0010K\u001a\u0002002\u0006\u0010L\u001a\u00020MH\u0016J\u0010\u0010N\u001a\u0002002\u0006\u0010A\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u00020D2\u0006\u0010E\u001a\u00020QH\u0016J\u0010\u0010R\u001a\u0002002\u0006\u0010A\u001a\u00020SH\u0016J\u0010\u0010T\u001a\u00020D2\u0006\u0010E\u001a\u00020<H\u0016J\u0010\u0010U\u001a\u0002002\u0006\u0010V\u001a\u00020WH\u0016J\u0010\u0010X\u001a\u0002002\u0006\u0010A\u001a\u00020YH\u0016J\u0010\u0010Z\u001a\u0002002\u0006\u0010A\u001a\u00020[H\u0016J\u0010\u0010\\\u001a\u00020D2\u0006\u0010E\u001a\u00020]H\u0016J\u0010\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020_H\u0016J\u0010\u0010a\u001a\u00020b2\u0006\u0010`\u001a\u00020bH\u0016J\u0010\u0010c\u001a\u0002002\u0006\u0010A\u001a\u00020dH\u0016J\u0010\u0010e\u001a\u0002002\u0006\u0010A\u001a\u00020fH\u0016J\u0010\u0010g\u001a\u0002002\u0006\u0010A\u001a\u00020hH\u0016J\u0010\u0010i\u001a\u0002002\u0006\u0010A\u001a\u00020jH\u0016J\u0010\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020nH\u0016J\u0010\u0010o\u001a\u00020D2\u0006\u0010E\u001a\u00020pH\u0016J\u0010\u0010q\u001a\u00020D2\u0006\u0010E\u001a\u00020rH\u0016J+\u0010s\u001a\u00020<*\u00020r2\u0019\b\u0002\u0010t\u001a\u0013\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u00020\u00130u¢\u0006\u0002\bwH\u0086\bø\u0001\u0000J\u0012\u0010x\u001a\u00020<*\u00020y2\u0006\u0010t\u001a\u00020vR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006z"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/LiveLiteralTransformer;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "liveLiteralsEnabled", "", "usePerFileEnabledFlag", "keyVisitor", "Landroidx/compose/compiler/plugins/kotlin/lower/DurableKeyVisitor;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(ZZLandroidx/compose/compiler/plugins/kotlin/lower/DurableKeyVisitor;Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "liveLiteral", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "isLiveLiteralsEnabled", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "liveLiteralInfoAnnotation", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "liveLiteralFileInfoAnnotation", "stateInterface", "NoLiveLiteralsAnnotation", "hasNoLiveLiteralsAnnotation", "Lorg/jetbrains/kotlin/ir/declarations/IrAnnotationContainer;", "enter", "T", "key", "", "block", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "siblings", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "liveLiteralsClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "liveLiteralsEnabledSymbol", "currentFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "irGetLiveLiteralsClass", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "startOffset", "", "endOffset", "asJvmFriendlyString", "Lorg/jetbrains/kotlin/name/Name;", "irLiveLiteralInfoAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "offset", "irLiveLiteralFileInfoAnnotation", "file", "irLiveLiteralGetter", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "literalValue", "literalType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "visitConst", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrConst;", "visitClass", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "visitAnonymousInitializer", "Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;", "makeKeySet", "", "visitFile", "visitTry", "aTry", "Lorg/jetbrains/kotlin/ir/expressions/IrTry;", "visitFunctionAccess", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "visitEnumEntry", "Lorg/jetbrains/kotlin/ir/declarations/IrEnumEntry;", "visitVararg", "Lorg/jetbrains/kotlin/ir/expressions/IrVararg;", "visitSimpleFunction", "visitLoop", "loop", "Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "visitStringConcatenation", "Lorg/jetbrains/kotlin/ir/expressions/IrStringConcatenation;", "visitWhen", "Lorg/jetbrains/kotlin/ir/expressions/IrWhen;", "visitValueParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "visitElseBranch", "Lorg/jetbrains/kotlin/ir/expressions/IrElseBranch;", "branch", "visitBranch", "Lorg/jetbrains/kotlin/ir/expressions/IrBranch;", "visitComposite", "Lorg/jetbrains/kotlin/ir/expressions/IrComposite;", "visitBlock", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "visitSetValue", "Lorg/jetbrains/kotlin/ir/expressions/IrSetValue;", "visitSetField", "Lorg/jetbrains/kotlin/ir/expressions/IrSetField;", "visitBlockBody", "Lorg/jetbrains/kotlin/ir/expressions/IrBody;", "body", "Lorg/jetbrains/kotlin/ir/expressions/IrBlockBody;", "visitVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "visitProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "addSetter", "builder", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/builders/declarations/IrFunctionBuilder;", "Lkotlin/ExtensionFunctionType;", "buildFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class LiveLiteralTransformer extends AbstractComposeLowering implements ModuleLoweringPass {
    private final IrClassSymbol NoLiveLiteralsAnnotation;
    private IrFile currentFile;
    private final IrFunctionSymbol isLiveLiteralsEnabled;
    private final DurableKeyVisitor keyVisitor;
    private final IrSimpleFunctionSymbol liveLiteral;
    private final IrClassSymbol liveLiteralFileInfoAnnotation;
    private final IrClassSymbol liveLiteralInfoAnnotation;
    private IrClass liveLiteralsClass;
    private final boolean liveLiteralsEnabled;
    private IrSimpleFunctionSymbol liveLiteralsEnabledSymbol;
    private final IrClassSymbol stateInterface;
    private final boolean usePerFileEnabledFlag;

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
    public LiveLiteralTransformer(boolean z, boolean z2, DurableKeyVisitor durableKeyVisitor, IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        durableKeyVisitor.getClass();
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        this.liveLiteralsEnabled = z;
        this.usePerFileEnabledFlag = z2;
        this.keyVisitor = durableKeyVisitor;
        ComposeCallableIds composeCallableIds = ComposeCallableIds.INSTANCE;
        this.liveLiteral = getTopLevelFunction(composeCallableIds.getLiveLiteral());
        this.isLiveLiteralsEnabled = getTopLevelPropertyGetter(composeCallableIds.isLiveLiteralsEnabled());
        ComposeClassIds composeClassIds = ComposeClassIds.INSTANCE;
        this.liveLiteralInfoAnnotation = getTopLevelClass(composeClassIds.getLiveLiteralInfo());
        this.liveLiteralFileInfoAnnotation = getTopLevelClass(composeClassIds.getLiveLiteralFileInfo());
        this.stateInterface = getTopLevelClass(composeClassIds.getState());
        this.NoLiveLiteralsAnnotation = getTopLevelClass(composeClassIds.getNoLiveLiterals());
    }

    public static IrExpression A(LiveLiteralTransformer liveLiteralTransformer, IrSetValue irSetValue) {
        return super.visitSetValue(irSetValue);
    }

    public static IrStatement D(LiveLiteralTransformer liveLiteralTransformer, IrValueParameter irValueParameter) {
        return super.visitValueParameter(irValueParameter);
    }

    public static IrStatement E(LiveLiteralTransformer liveLiteralTransformer, IrVariable irVariable) {
        return super.visitVariable(irVariable);
    }

    public static IrExpression G(IrElseBranch irElseBranch, LiveLiteralTransformer liveLiteralTransformer) {
        return irElseBranch.getResult().transform(liveLiteralTransformer, (Object) null);
    }

    public static IrExpression H(IrBranch irBranch, LiveLiteralTransformer liveLiteralTransformer) {
        return irBranch.getResult().transform(liveLiteralTransformer, (Object) null);
    }

    public static IrStatement I(LiveLiteralTransformer liveLiteralTransformer, IrSimpleFunction irSimpleFunction) {
        return super.visitSimpleFunction(irSimpleFunction);
    }

    public static IrExpression J(LiveLiteralTransformer liveLiteralTransformer, IrSetField irSetField) {
        return super.visitSetField(irSetField);
    }

    public static IrExpression L(IrTry irTry, LiveLiteralTransformer liveLiteralTransformer) {
        return irTry.getTryResult().transform(liveLiteralTransformer, (Object) null);
    }

    public static IrStringConcatenationImpl M(final LiveLiteralTransformer liveLiteralTransformer, final IrStringConcatenation irStringConcatenation) {
        return (IrStringConcatenationImpl) liveLiteralTransformer.siblings(new Function0() { // from class: id9
            public final Object invoke() {
                return LiveLiteralTransformer.visitStringConcatenation$lambda$0$0(irStringConcatenation, liveLiteralTransformer);
            }
        });
    }

    public static IrExpression N(LiveLiteralTransformer liveLiteralTransformer, IrWhen irWhen) {
        return super.visitWhen(irWhen);
    }

    public static Unit P(IrTry irTry, final LiveLiteralTransformer liveLiteralTransformer) {
        for (final IrCatch irCatch : irTry.getCatches()) {
            irCatch.setResult((IrExpression) liveLiteralTransformer.enter("catch", new Function0() { // from class: yc9
                public final Object invoke() {
                    return LiveLiteralTransformer.visitTry$lambda$1$0$0(irCatch, liveLiteralTransformer);
                }
            }));
        }
        return Unit.INSTANCE;
    }

    public static IrExpression Q(LiveLiteralTransformer liveLiteralTransformer, IrWhen irWhen) {
        return super.visitWhen(irWhen);
    }

    public static IrLoop R(final IrLoop irLoop, final LiveLiteralTransformer liveLiteralTransformer) {
        irLoop.setBody((IrExpression) liveLiteralTransformer.enter("body", new Function0() { // from class: fd9
            public final Object invoke() {
                return LiveLiteralTransformer.visitLoop$lambda$0$0(irLoop, liveLiteralTransformer);
            }
        }));
        return irLoop;
    }

    public static IrStatement S(LiveLiteralTransformer liveLiteralTransformer, IrClass irClass) {
        return super.visitClass(irClass);
    }

    public static IrExpression T(LiveLiteralTransformer liveLiteralTransformer, IrComposite irComposite) {
        return super.visitComposite(irComposite);
    }

    public static /* synthetic */ IrSimpleFunction addSetter$default(LiveLiteralTransformer liveLiteralTransformer, IrProperty irProperty, Function1 function1, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: addSetter");
            return null;
        }
        if ((i & 1) != 0) {
            function1 = new Function1<IrFunctionBuilder, Unit>() { // from class: androidx.compose.compiler.plugins.kotlin.lower.LiveLiteralTransformer.addSetter.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((IrFunctionBuilder) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(IrFunctionBuilder irFunctionBuilder) {
                    irFunctionBuilder.getClass();
                }
            };
        }
        irProperty.getClass();
        function1.getClass();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        Name nameSpecial = Name.special("<set-" + irProperty.getName() + '>');
        nameSpecial.getClass();
        irFunctionBuilder.setName(nameSpecial);
        function1.invoke(irFunctionBuilder);
        IrSimpleFunction irSimpleFunctionBuildFunction = liveLiteralTransformer.buildFunction(liveLiteralTransformer.getContext().getIrFactory(), irFunctionBuilder);
        irProperty.setSetter(irSimpleFunctionBuildFunction);
        irSimpleFunctionBuildFunction.setParent(irProperty.getParent());
        return irSimpleFunctionBuildFunction;
    }

    private final String asJvmFriendlyString(Name name) {
        if (name.isSpecial()) {
            String strAsString = name.asString();
            strAsString.getClass();
            return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(strAsString, '<', '$', false, 4, (Object) null), '>', '$', false, 4, (Object) null), ' ', '-', false, 4, (Object) null);
        }
        String identifier = name.getIdentifier();
        identifier.getClass();
        return identifier;
    }

    private final <T> T enter(String key, Function0<? extends T> block) {
        return (T) this.keyVisitor.enter(key, block);
    }

    private final boolean hasNoLiveLiteralsAnnotation(IrAnnotationContainer irAnnotationContainer) {
        List annotations = irAnnotationContainer.getAnnotations();
        if ((annotations instanceof Collection) && annotations.isEmpty()) {
            return false;
        }
        Iterator it = annotations.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((IrAnnotation) it.next()).getSymbol().getOwner(), IrUtilsKt.getPrimaryConstructor(this.NoLiveLiteralsAnnotation.getOwner()))) {
                return true;
            }
        }
        return false;
    }

    private final IrExpression irGetLiveLiteralsClass(int startOffset, int endOffset) {
        IrClass irClass = this.liveLiteralsClass;
        irClass.getClass();
        IrSimpleType defaultType = IrUtilsKt.getDefaultType(irClass);
        IrClass irClass2 = this.liveLiteralsClass;
        irClass2.getClass();
        return BuildersKt.IrGetObjectValueImpl(startOffset, endOffset, defaultType, irClass2.getSymbol());
    }

    private final IrAnnotation irLiveLiteralFileInfoAnnotation(String file) {
        IrAnnotationImpl irAnnotationImplIrAnnotationImpl$default = BuildersKt.IrAnnotationImpl$default(-1, -1, IrTypesKt.getDefaultType(this.liveLiteralFileInfoAnnotation), (IrConstructorSymbol) SequencesKt.single(IrUtilsKt.getConstructors(this.liveLiteralFileInfoAnnotation)), 0, 0, (IrStatementOrigin) null, (SourceElement) null, 192, (Object) null);
        irAnnotationImplIrAnnotationImpl$default.getArguments().set(0, irConst(file));
        return irAnnotationImplIrAnnotationImpl$default;
    }

    private final IrSimpleFunction irLiveLiteralGetter(String key, IrExpression literalValue, IrType literalType, int startOffset) {
        IrExpression irExpressionIrNot;
        IrClass irClass = this.liveLiteralsClass;
        irClass.getClass();
        IrType irTypeMakeNullable = IrTypesKt.makeNullable(IrTypesKt.typeWith(this.stateInterface.getOwner(), new IrType[]{literalType}));
        IrSimpleFunctionSymbol propertyGetter = AdditionalIrUtilsKt.getPropertyGetter(this.stateInterface, "value");
        propertyGetter.getClass();
        IrFactory factory = irClass.getFactory();
        IrPropertyBuilder irPropertyBuilder = new IrPropertyBuilder();
        Name nameIdentifier = Name.identifier(key);
        nameIdentifier.getClass();
        irPropertyBuilder.setName(nameIdentifier);
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        irPropertyBuilder.setVisibility(descriptorVisibility);
        IrProperty irPropertyBuildProperty = DeclarationBuildersKt.buildProperty(factory, irPropertyBuilder);
        irClass.getDeclarations().add(irPropertyBuildProperty);
        irPropertyBuildProperty.setParent(irClass);
        IrFactory irFactory = getContext().getIrFactory();
        IrFieldBuilder irFieldBuilder = new IrFieldBuilder();
        Name nameIdentifier2 = Name.identifier(key);
        nameIdentifier2.getClass();
        irFieldBuilder.setName(nameIdentifier2);
        irFieldBuilder.setStatic(true);
        irFieldBuilder.setType(literalType);
        descriptorVisibility.getClass();
        irFieldBuilder.setVisibility(descriptorVisibility);
        IrField irFieldBuildField = DeclarationBuildersKt.buildField(irFactory, irFieldBuilder);
        irFieldBuildField.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
        irFieldBuildField.setParent(irClass);
        irFieldBuildField.setInitializer(getContext().getIrFactory().createExpressionBody(-2, -2, literalValue));
        irPropertyBuildProperty.setBackingField(irFieldBuildField);
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        Name nameSpecial = Name.special("<get-" + irPropertyBuildProperty.getName() + '>');
        nameSpecial.getClass();
        irFunctionBuilder.setName(nameSpecial);
        irFunctionBuilder.setReturnType(literalType);
        descriptorVisibility.getClass();
        irFunctionBuilder.setVisibility(descriptorVisibility);
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        irFunctionBuilder.setOrigin(companion.getDEFAULT_PROPERTY_ACCESSOR());
        IrSimpleFunction irSimpleFunctionBuildFunction = DeclarationBuildersKt.buildFunction(irPropertyBuildProperty.getFactory(), irFunctionBuilder);
        irPropertyBuildProperty.setGetter(irSimpleFunctionBuildFunction);
        irSimpleFunctionBuildFunction.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
        irSimpleFunctionBuildFunction.setParent(irPropertyBuildProperty.getParent());
        irSimpleFunctionBuildFunction.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
        IrValueParameter thisReceiver = irClass.getThisReceiver();
        thisReceiver.getClass();
        IrValueParameter irValueParameterCopyTo$default = IrUtilsKt.copyTo$default(thisReceiver, irSimpleFunctionBuildFunction, (IrDeclarationOrigin) null, 0, 0, (Name) null, (Map) null, (IrType) null, (IrType) null, (IrExpressionBody) null, false, false, false, (IrParameterKind) null, (Map) null, 16382, (Object) null);
        irSimpleFunctionBuildFunction.setParameters(CollectionsKt.plus(irSimpleFunctionBuildFunction.getParameters(), irValueParameterCopyTo$default));
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(getContext(), irSimpleFunctionBuildFunction.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        IrGetValueImpl irGetValueImplIrGet = ExpressionHelpersKt.irGet(irBlockBodyBuilder, irValueParameterCopyTo$default);
        IrField backingField = irPropertyBuildProperty.getBackingField();
        backingField.getClass();
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder, ExpressionHelpersKt.irGetField$default(irBlockBodyBuilder, irGetValueImplIrGet, backingField, (IrType) null, 4, (Object) null)));
        irSimpleFunctionBuildFunction.setBody(irBlockBodyBuilder.doBuild());
        IrFactory factory2 = irClass.getFactory();
        IrPropertyBuilder irPropertyBuilder2 = new IrPropertyBuilder();
        Name nameIdentifier3 = Name.identifier("State$" + key);
        nameIdentifier3.getClass();
        irPropertyBuilder2.setName(nameIdentifier3);
        descriptorVisibility.getClass();
        irPropertyBuilder2.setVisibility(descriptorVisibility);
        irPropertyBuilder2.setVar(true);
        IrProperty irPropertyBuildProperty2 = DeclarationBuildersKt.buildProperty(factory2, irPropertyBuilder2);
        irClass.getDeclarations().add(irPropertyBuildProperty2);
        irPropertyBuildProperty2.setParent(irClass);
        IrFactory irFactory2 = getContext().getIrFactory();
        IrFieldBuilder irFieldBuilder2 = new IrFieldBuilder();
        Name nameIdentifier4 = Name.identifier("State$" + key);
        nameIdentifier4.getClass();
        irFieldBuilder2.setName(nameIdentifier4);
        irFieldBuilder2.setType(irTypeMakeNullable);
        descriptorVisibility.getClass();
        irFieldBuilder2.setVisibility(descriptorVisibility);
        irFieldBuilder2.setStatic(true);
        IrField irFieldBuildField2 = DeclarationBuildersKt.buildField(irFactory2, irFieldBuilder2);
        irFieldBuildField2.setCorrespondingPropertySymbol(irPropertyBuildProperty2.getSymbol());
        irFieldBuildField2.setParent(irClass);
        irPropertyBuildProperty2.setBackingField(irFieldBuildField2);
        IrFunctionBuilder irFunctionBuilder2 = new IrFunctionBuilder();
        Name nameSpecial2 = Name.special("<get-" + irPropertyBuildProperty2.getName() + '>');
        nameSpecial2.getClass();
        irFunctionBuilder2.setName(nameSpecial2);
        irFunctionBuilder2.setReturnType(irTypeMakeNullable);
        descriptorVisibility.getClass();
        irFunctionBuilder2.setVisibility(descriptorVisibility);
        irFunctionBuilder2.setOrigin(companion.getDEFAULT_PROPERTY_ACCESSOR());
        IrSimpleFunction irSimpleFunctionBuildFunction2 = DeclarationBuildersKt.buildFunction(irPropertyBuildProperty2.getFactory(), irFunctionBuilder2);
        irPropertyBuildProperty2.setGetter(irSimpleFunctionBuildFunction2);
        irSimpleFunctionBuildFunction2.setCorrespondingPropertySymbol(irPropertyBuildProperty2.getSymbol());
        irSimpleFunctionBuildFunction2.setParent(irPropertyBuildProperty2.getParent());
        irSimpleFunctionBuildFunction2.setCorrespondingPropertySymbol(irPropertyBuildProperty2.getSymbol());
        IrValueParameter thisReceiver2 = irClass.getThisReceiver();
        thisReceiver2.getClass();
        IrValueParameter irValueParameterCopyTo$default2 = IrUtilsKt.copyTo$default(thisReceiver2, irSimpleFunctionBuildFunction2, (IrDeclarationOrigin) null, 0, 0, (Name) null, (Map) null, (IrType) null, (IrType) null, (IrExpressionBody) null, false, false, false, (IrParameterKind) null, (Map) null, 16382, (Object) null);
        irSimpleFunctionBuildFunction2.setParameters(CollectionsKt.plus(irSimpleFunctionBuildFunction2.getParameters(), irValueParameterCopyTo$default2));
        DeclarationIrBuilder declarationIrBuilder2 = new DeclarationIrBuilder(getContext(), irSimpleFunctionBuildFunction2.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder2 = new IrBlockBodyBuilder(declarationIrBuilder2.getContext(), declarationIrBuilder2.getScope(), declarationIrBuilder2.getStartOffset(), declarationIrBuilder2.getEndOffset());
        IrGetValueImpl irGetValueImplIrGet2 = ExpressionHelpersKt.irGet(irBlockBodyBuilder2, irValueParameterCopyTo$default2);
        IrField backingField2 = irPropertyBuildProperty2.getBackingField();
        backingField2.getClass();
        irBlockBodyBuilder2.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder2, ExpressionHelpersKt.irGetField$default(irBlockBodyBuilder2, irGetValueImplIrGet2, backingField2, (IrType) null, 4, (Object) null)));
        irSimpleFunctionBuildFunction2.setBody(irBlockBodyBuilder2.doBuild());
        IrFunctionBuilder irFunctionBuilder3 = new IrFunctionBuilder();
        Name nameSpecial3 = Name.special("<set-" + irPropertyBuildProperty2.getName() + '>');
        nameSpecial3.getClass();
        irFunctionBuilder3.setName(nameSpecial3);
        irFunctionBuilder3.setReturnType(getContext().getIrBuiltIns().getUnitType());
        descriptorVisibility.getClass();
        irFunctionBuilder3.setVisibility(descriptorVisibility);
        irFunctionBuilder3.setOrigin(companion.getDEFAULT_PROPERTY_ACCESSOR());
        IrSimpleFunction irSimpleFunctionBuildFunction3 = buildFunction(getContext().getIrFactory(), irFunctionBuilder3);
        irPropertyBuildProperty2.setSetter(irSimpleFunctionBuildFunction3);
        irSimpleFunctionBuildFunction3.setParent(irPropertyBuildProperty2.getParent());
        irSimpleFunctionBuildFunction3.setCorrespondingPropertySymbol(irPropertyBuildProperty2.getSymbol());
        IrValueParameter thisReceiver3 = irClass.getThisReceiver();
        thisReceiver3.getClass();
        IrValueParameter irValueParameterCopyTo$default3 = IrUtilsKt.copyTo$default(thisReceiver3, irSimpleFunctionBuildFunction3, (IrDeclarationOrigin) null, 0, 0, (Name) null, (Map) null, (IrType) null, (IrType) null, (IrExpressionBody) null, false, false, false, (IrParameterKind) null, (Map) null, 16382, (Object) null);
        irSimpleFunctionBuildFunction3.setParameters(CollectionsKt.plus(irSimpleFunctionBuildFunction3.getParameters(), irValueParameterCopyTo$default3));
        IrValueParameter irValueParameterAddValueParameter$default = DeclarationBuildersKt.addValueParameter$default(irSimpleFunctionBuildFunction3, "value", irTypeMakeNullable, (IrDeclarationOrigin) null, 4, (Object) null);
        DeclarationIrBuilder declarationIrBuilder3 = new DeclarationIrBuilder(getContext(), irSimpleFunctionBuildFunction3.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder3 = new IrBlockBodyBuilder(declarationIrBuilder3.getContext(), declarationIrBuilder3.getScope(), declarationIrBuilder3.getStartOffset(), declarationIrBuilder3.getEndOffset());
        IrGetValueImpl irGetValueImplIrGet3 = ExpressionHelpersKt.irGet(irBlockBodyBuilder3, irValueParameterCopyTo$default3);
        IrField backingField3 = irPropertyBuildProperty2.getBackingField();
        backingField3.getClass();
        irBlockBodyBuilder3.unaryPlus(ExpressionHelpersKt.irSetField$default(irBlockBodyBuilder3, irGetValueImplIrGet3, backingField3, ExpressionHelpersKt.irGet(irBlockBodyBuilder3, irValueParameterAddValueParameter$default), (IrStatementOrigin) null, 8, (Object) null));
        irSimpleFunctionBuildFunction3.setBody(irBlockBodyBuilder3.doBuild());
        IrSimpleFunction irSimpleFunctionAddFunction$default = DeclarationBuildersKt.addFunction$default(irClass, key, literalType, (Modality) null, (DescriptorVisibility) null, false, false, false, false, (IrDeclarationOrigin) null, 0, 0, 2044, (Object) null);
        IrValueParameter dispatchReceiverParameter = irSimpleFunctionAddFunction$default.getDispatchReceiverParameter();
        dispatchReceiverParameter.getClass();
        irSimpleFunctionAddFunction$default.setAnnotations(CollectionsKt.plus(irSimpleFunctionAddFunction$default.getAnnotations(), irLiveLiteralInfoAnnotation(key, startOffset)));
        DeclarationIrBuilder declarationIrBuilder4 = new DeclarationIrBuilder(getContext(), irSimpleFunctionAddFunction$default.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder4 = new IrBlockBodyBuilder(declarationIrBuilder4.getContext(), declarationIrBuilder4.getScope(), declarationIrBuilder4.getStartOffset(), declarationIrBuilder4.getEndOffset());
        if (this.usePerFileEnabledFlag) {
            IrType booleanType = getBuiltIns().getBooleanType();
            IrGetValueImpl irGetValueImplIrGet4 = ExpressionHelpersKt.irGet(irBlockBodyBuilder4, dispatchReceiverParameter);
            IrSimpleFunctionSymbol irSimpleFunctionSymbol = this.liveLiteralsEnabledSymbol;
            irSimpleFunctionSymbol.getClass();
            irExpressionIrNot = irNot(ExpressionHelpersKt.irGet(irBlockBodyBuilder4, booleanType, irGetValueImplIrGet4, irSimpleFunctionSymbol));
        } else {
            irExpressionIrNot = irNot(ExpressionHelpersKt.irCall(irBlockBodyBuilder4, this.isLiveLiteralsEnabled));
        }
        IrGetValueImpl irGetValueImplIrGet5 = ExpressionHelpersKt.irGet(irBlockBodyBuilder4, dispatchReceiverParameter);
        IrSimpleFunction getter = irPropertyBuildProperty.getGetter();
        getter.getClass();
        irBlockBodyBuilder4.unaryPlus(irIf(irExpressionIrNot, ExpressionHelpersKt.irReturn(irBlockBodyBuilder4, ExpressionHelpersKt.irGet(irBlockBodyBuilder4, literalType, irGetValueImplIrGet5, getter.getSymbol()))));
        IrGetValueImpl irGetValueImplIrGet6 = ExpressionHelpersKt.irGet(irBlockBodyBuilder4, dispatchReceiverParameter);
        IrSimpleFunction getter2 = irPropertyBuildProperty2.getGetter();
        getter2.getClass();
        IrVariable irVariableIrTemporary$default = ExpressionHelpersKt.irTemporary$default(irBlockBodyBuilder4, ExpressionHelpersKt.irGet(irBlockBodyBuilder4, irTypeMakeNullable, irGetValueImplIrGet6, getter2.getSymbol()), (String) null, (IrType) null, false, (IrDeclarationOrigin) null, 30, (Object) null);
        IrGetValueImpl irGetValueImplIrGet7 = ExpressionHelpersKt.irGet(irBlockBodyBuilder4, irVariableIrTemporary$default);
        IrBlockBuilder irBlockBuilder = new IrBlockBuilder(irBlockBodyBuilder4.getContext(), irBlockBodyBuilder4.getScope(), irBlockBodyBuilder4.getStartOffset(), irBlockBodyBuilder4.getEndOffset(), (IrStatementOrigin) null, irTypeMakeNullable, false, 64, (DefaultConstructorMarker) null);
        IrCall irCall = ExpressionHelpersKt.irCall(irBlockBuilder, this.liveLiteral);
        irCall.getArguments().set(0, ExpressionHelpersKt.irString(irBlockBuilder, key));
        IrMemberAccessExpression.ValueArgumentsList arguments = irCall.getArguments();
        IrGetValueImpl irGetValueImplIrGet8 = ExpressionHelpersKt.irGet(irBlockBuilder, dispatchReceiverParameter);
        IrSimpleFunction getter3 = irPropertyBuildProperty.getGetter();
        getter3.getClass();
        arguments.set(1, ExpressionHelpersKt.irGet(irBlockBuilder, literalType, irGetValueImplIrGet8, getter3.getSymbol()));
        irCall.getTypeArguments().set(0, literalType);
        IrVariable irVariableIrTemporary$default2 = ExpressionHelpersKt.irTemporary$default(irBlockBuilder, irCall, (String) null, (IrType) null, false, (IrDeclarationOrigin) null, 30, (Object) null);
        IrGetValueImpl irGetValueImplIrGet9 = ExpressionHelpersKt.irGet(irBlockBuilder, dispatchReceiverParameter);
        IrSimpleFunction setter = irPropertyBuildProperty2.getSetter();
        setter.getClass();
        irBlockBuilder.unaryPlus(ExpressionHelpersKt.irSet(irBlockBuilder, irTypeMakeNullable, irGetValueImplIrGet9, setter.getSymbol(), ExpressionHelpersKt.irGet(irBlockBuilder, irVariableIrTemporary$default2)));
        irBlockBuilder.unaryPlus(ExpressionHelpersKt.irGet(irBlockBuilder, irVariableIrTemporary$default2));
        Unit unit = Unit.INSTANCE;
        IrWhenImpl irWhenImplIrIfNull = ExpressionHelpersKt.irIfNull(irBlockBodyBuilder4, irTypeMakeNullable, irGetValueImplIrGet7, irBlockBuilder.doBuild(), ExpressionHelpersKt.irGet(irBlockBodyBuilder4, irVariableIrTemporary$default));
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(-1, -1, literalType, propertyGetter, propertyGetter.getOwner().getTypeParameters().size(), IrStatementOrigin.Companion.getFOR_LOOP_ITERATOR(), (IrClassSymbol) null, 64, (Object) null);
        irCallImplIrCallImpl$default.setDispatchReceiver(irWhenImplIrIfNull);
        irBlockBodyBuilder4.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder4, irCallImplIrCallImpl$default));
        irSimpleFunctionAddFunction$default.setBody(irBlockBodyBuilder4.doBuild());
        return irSimpleFunctionAddFunction$default;
    }

    private final IrAnnotation irLiveLiteralInfoAnnotation(String key, int offset) {
        IrAnnotationImpl irAnnotationImplIrAnnotationImpl$default = BuildersKt.IrAnnotationImpl$default(-1, -1, IrTypesKt.getDefaultType(this.liveLiteralInfoAnnotation), (IrConstructorSymbol) SequencesKt.single(IrUtilsKt.getConstructors(this.liveLiteralInfoAnnotation)), 0, 0, (IrStatementOrigin) null, (SourceElement) null, 192, (Object) null);
        irAnnotationImplIrAnnotationImpl$default.getArguments().set(0, irConst(key));
        irAnnotationImplIrAnnotationImpl$default.getArguments().set(1, irConst(offset));
        return irAnnotationImplIrAnnotationImpl$default;
    }

    public static IrLoop l(final IrLoop irLoop, final LiveLiteralTransformer liveLiteralTransformer) {
        irLoop.setCondition((IrExpression) liveLiteralTransformer.enter("cond", new Function0() { // from class: dd9
            public final Object invoke() {
                return LiveLiteralTransformer.visitLoop$lambda$1$0(irLoop, liveLiteralTransformer);
            }
        }));
        irLoop.setBody((IrExpression) liveLiteralTransformer.enter("body", new Function0() { // from class: od9
            public final Object invoke() {
                return LiveLiteralTransformer.visitLoop$lambda$1$1(irLoop, liveLiteralTransformer);
            }
        }));
        return irLoop;
    }

    public static IrProperty m(IrProperty irProperty, IrField irField, final LiveLiteralTransformer liveLiteralTransformer, final IrSimpleFunction irSimpleFunction, final IrSimpleFunction irSimpleFunction2) {
        irProperty.setBackingField(irField);
        irProperty.setGetter((IrSimpleFunction) liveLiteralTransformer.enter("get", new Function0() { // from class: ld9
            public final Object invoke() {
                return LiveLiteralTransformer.visitProperty$lambda$0$0(irSimpleFunction, liveLiteralTransformer);
            }
        }));
        irProperty.setSetter((IrSimpleFunction) liveLiteralTransformer.enter("set", new Function0() { // from class: md9
            public final Object invoke() {
                return LiveLiteralTransformer.visitProperty$lambda$0$1(irSimpleFunction2, liveLiteralTransformer);
            }
        }));
        return irProperty;
    }

    public static IrStatement n(LiveLiteralTransformer liveLiteralTransformer, IrEnumEntry irEnumEntry) {
        return super.visitEnumEntry(irEnumEntry);
    }

    public static IrExpression p(IrBranch irBranch, LiveLiteralTransformer liveLiteralTransformer) {
        return irBranch.getCondition().transform(liveLiteralTransformer, (Object) null);
    }

    public static IrVarargImpl q(IrVararg irVararg, final LiveLiteralTransformer liveLiteralTransformer) {
        IrVarargImpl irVarargImpl = (IrVarargImpl) irVararg;
        int i = 0;
        for (Object obj : irVarargImpl.getElements()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final IrVarargElement irVarargElement = (IrVarargElement) obj;
            irVarargImpl.getElements().set(i, liveLiteralTransformer.enter(String.valueOf(i), new Function0() { // from class: ud9
                public final Object invoke() {
                    return LiveLiteralTransformer.visitVararg$lambda$0$0$0(irVarargElement, liveLiteralTransformer);
                }
            }));
            i = i2;
        }
        return irVarargImpl;
    }

    public static IrBody r(LiveLiteralTransformer liveLiteralTransformer, IrBlockBody irBlockBody) {
        return super.visitBlockBody(irBlockBody);
    }

    private final <T> T siblings(String key, Function0<? extends T> block) {
        return (T) this.keyVisitor.siblings(key, block);
    }

    public static IrExpression u(LiveLiteralTransformer liveLiteralTransformer, IrBlock irBlock) {
        return super.visitBlock(irBlock);
    }

    public static IrExpression v(IrTry irTry, LiveLiteralTransformer liveLiteralTransformer) {
        IrExpression finallyExpression = irTry.getFinallyExpression();
        if (finallyExpression != null) {
            return finallyExpression.transform(liveLiteralTransformer, (Object) null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrFile visitFile$lambda$0$0(LiveLiteralTransformer liveLiteralTransformer, IrFile irFile, Set set, String str) {
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = liveLiteralTransformer.liveLiteralsEnabledSymbol;
        IrClass irClass = liveLiteralTransformer.liveLiteralsClass;
        IrFactory irFactory = liveLiteralTransformer.getContext().getIrFactory();
        IrClassBuilder irClassBuilder = new IrClassBuilder();
        irClassBuilder.setKind(ClassKind.OBJECT);
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.INTERNAL;
        descriptorVisibility.getClass();
        irClassBuilder.setVisibility(descriptorVisibility);
        Name nameIdentifier = Name.identifier("LiveLiterals$" + PackagePartClassUtils.getFilePartShortName(str));
        nameIdentifier.getClass();
        irClassBuilder.setName(nameIdentifier);
        IrClass irClassBuildClass = DeclarationBuildersKt.buildClass(irFactory, irClassBuilder);
        IrUtilsKt.createThisReceiverParameter(irClassBuildClass);
        irClassBuildClass.setAnnotations(CollectionsKt.plus(irClassBuildClass.getAnnotations(), liveLiteralTransformer.irLiveLiteralFileInfoAnnotation(irFile.getFileEntry().getName())));
        IrFactory factory = irClassBuildClass.getFactory();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        irFunctionBuilder.setPrimary(true);
        irFunctionBuilder.setReturnType(IrUtilsKt.getDefaultType(irClassBuildClass));
        IrConstructor irConstructorBuildConstructor = DeclarationBuildersKt.buildConstructor(factory, irFunctionBuilder);
        irClassBuildClass.getDeclarations().add(irConstructorBuildConstructor);
        irConstructorBuildConstructor.setParent(irClassBuildClass);
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(liveLiteralTransformer.getContext(), irClassBuildClass.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        IrConstructor primaryConstructor = IrUtilsKt.getPrimaryConstructor(irBlockBodyBuilder.getContext().getIrBuiltIns().getAnyClass().getOwner());
        primaryConstructor.getClass();
        irBlockBodyBuilder.unaryPlus(ExpressionHelpersKt.irDelegatingConstructorCall(irBlockBodyBuilder, primaryConstructor));
        irConstructorBuildConstructor.setBody(irBlockBodyBuilder.doBuild());
        IrSimpleFunctionSymbol symbol = null;
        if (liveLiteralTransformer.usePerFileEnabledFlag) {
            IrFactory factory2 = irClassBuildClass.getFactory();
            IrPropertyBuilder irPropertyBuilder = new IrPropertyBuilder();
            Name nameIdentifier2 = Name.identifier("enabled");
            nameIdentifier2.getClass();
            irPropertyBuilder.setName(nameIdentifier2);
            DescriptorVisibility descriptorVisibility2 = DescriptorVisibilities.PRIVATE;
            descriptorVisibility2.getClass();
            irPropertyBuilder.setVisibility(descriptorVisibility2);
            IrProperty irPropertyBuildProperty = DeclarationBuildersKt.buildProperty(factory2, irPropertyBuilder);
            irClassBuildClass.getDeclarations().add(irPropertyBuildProperty);
            irPropertyBuildProperty.setParent(irClassBuildClass);
            IrFactory irFactory2 = liveLiteralTransformer.getContext().getIrFactory();
            IrFieldBuilder irFieldBuilder = new IrFieldBuilder();
            Name nameIdentifier3 = Name.identifier("enabled");
            nameIdentifier3.getClass();
            irFieldBuilder.setName(nameIdentifier3);
            irFieldBuilder.setStatic(true);
            irFieldBuilder.setType(liveLiteralTransformer.getBuiltIns().getBooleanType());
            descriptorVisibility2.getClass();
            irFieldBuilder.setVisibility(descriptorVisibility2);
            IrField irFieldBuildField = DeclarationBuildersKt.buildField(irFactory2, irFieldBuilder);
            irFieldBuildField.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
            irFieldBuildField.setParent(irClassBuildClass);
            irFieldBuildField.setInitializer(liveLiteralTransformer.getContext().getIrFactory().createExpressionBody(-2, -2, liveLiteralTransformer.irConst(false)));
            irPropertyBuildProperty.setBackingField(irFieldBuildField);
            IrFunctionBuilder irFunctionBuilder2 = new IrFunctionBuilder();
            Name nameSpecial = Name.special("<get-" + irPropertyBuildProperty.getName() + '>');
            nameSpecial.getClass();
            irFunctionBuilder2.setName(nameSpecial);
            irFunctionBuilder2.setReturnType(liveLiteralTransformer.getBuiltIns().getBooleanType());
            descriptorVisibility2.getClass();
            irFunctionBuilder2.setVisibility(descriptorVisibility2);
            irFunctionBuilder2.setOrigin(IrDeclarationOrigin.Companion.getDEFAULT_PROPERTY_ACCESSOR());
            IrSimpleFunction irSimpleFunctionBuildFunction = DeclarationBuildersKt.buildFunction(irPropertyBuildProperty.getFactory(), irFunctionBuilder2);
            irPropertyBuildProperty.setGetter(irSimpleFunctionBuildFunction);
            irSimpleFunctionBuildFunction.setCorrespondingPropertySymbol(irPropertyBuildProperty.getSymbol());
            irSimpleFunctionBuildFunction.setParent(irPropertyBuildProperty.getParent());
            IrValueParameter thisReceiver = irClassBuildClass.getThisReceiver();
            thisReceiver.getClass();
            IrValueParameter irValueParameterCopyTo$default = IrUtilsKt.copyTo$default(thisReceiver, irSimpleFunctionBuildFunction, (IrDeclarationOrigin) null, 0, 0, (Name) null, (Map) null, (IrType) null, (IrType) null, (IrExpressionBody) null, false, false, false, (IrParameterKind) null, (Map) null, 16382, (Object) null);
            irSimpleFunctionBuildFunction.setParameters(CollectionsKt.plus(irSimpleFunctionBuildFunction.getParameters(), irValueParameterCopyTo$default));
            DeclarationIrBuilder declarationIrBuilder2 = new DeclarationIrBuilder(liveLiteralTransformer.getContext(), irSimpleFunctionBuildFunction.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
            IrBlockBodyBuilder irBlockBodyBuilder2 = new IrBlockBodyBuilder(declarationIrBuilder2.getContext(), declarationIrBuilder2.getScope(), declarationIrBuilder2.getStartOffset(), declarationIrBuilder2.getEndOffset());
            IrGetValueImpl irGetValueImplIrGet = ExpressionHelpersKt.irGet(irBlockBodyBuilder2, irValueParameterCopyTo$default);
            IrField backingField = irPropertyBuildProperty.getBackingField();
            backingField.getClass();
            irBlockBodyBuilder2.unaryPlus(ExpressionHelpersKt.irReturn(irBlockBodyBuilder2, ExpressionHelpersKt.irGetField$default(irBlockBodyBuilder2, irGetValueImplIrGet, backingField, (IrType) null, 4, (Object) null)));
            irSimpleFunctionBuildFunction.setBody(irBlockBodyBuilder2.doBuild());
            IrSimpleFunction getter = irPropertyBuildProperty.getGetter();
            if (getter != null) {
                symbol = getter.getSymbol();
            }
        }
        try {
            liveLiteralTransformer.liveLiteralsClass = irClassBuildClass;
            liveLiteralTransformer.currentFile = irFile;
            liveLiteralTransformer.liveLiteralsEnabledSymbol = symbol;
            IrFile irFileVisitFile = super.visitFile(irFile);
            if (liveLiteralTransformer.liveLiteralsEnabled && !set.isEmpty()) {
                IrUtilsKt.addChild(irFileVisitFile, irClassBuildClass);
            }
            return irFileVisitFile;
        } finally {
            liveLiteralTransformer.liveLiteralsClass = irClass;
            liveLiteralTransformer.liveLiteralsEnabledSymbol = irSimpleFunctionSymbol;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit visitFunctionAccess$lambda$0$0(IrFunctionAccessExpression irFunctionAccessExpression, int i, IrExpression irExpression, LiveLiteralTransformer liveLiteralTransformer) {
        irFunctionAccessExpression.getArguments().set(i, irExpression.transform(liveLiteralTransformer, (Object) null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitLoop$lambda$0$0(IrLoop irLoop, LiveLiteralTransformer liveLiteralTransformer) {
        IrExpression body = irLoop.getBody();
        if (body != null) {
            return body.transform(liveLiteralTransformer, (Object) null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitLoop$lambda$1$0(IrLoop irLoop, LiveLiteralTransformer liveLiteralTransformer) {
        return irLoop.getCondition().transform(liveLiteralTransformer, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitLoop$lambda$1$1(IrLoop irLoop, LiveLiteralTransformer liveLiteralTransformer) {
        IrExpression body = irLoop.getBody();
        if (body != null) {
            return body.transform(liveLiteralTransformer, (Object) null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrSimpleFunction visitProperty$lambda$0$0(IrSimpleFunction irSimpleFunction, LiveLiteralTransformer liveLiteralTransformer) {
        IrElement irElementTransform = irSimpleFunction != null ? irSimpleFunction.transform(liveLiteralTransformer, (Object) null) : null;
        if (irElementTransform instanceof IrSimpleFunction) {
            return (IrSimpleFunction) irElementTransform;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrSimpleFunction visitProperty$lambda$0$1(IrSimpleFunction irSimpleFunction, LiveLiteralTransformer liveLiteralTransformer) {
        IrElement irElementTransform = irSimpleFunction != null ? irSimpleFunction.transform(liveLiteralTransformer, (Object) null) : null;
        if (irElementTransform instanceof IrSimpleFunction) {
            return (IrSimpleFunction) irElementTransform;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrStringConcatenationImpl visitStringConcatenation$lambda$0$0(IrStringConcatenation irStringConcatenation, final LiveLiteralTransformer liveLiteralTransformer) {
        IrStringConcatenationImpl irStringConcatenationImpl = (IrStringConcatenationImpl) irStringConcatenation;
        int i = 0;
        for (Object obj : irStringConcatenationImpl.getArguments()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final IrExpression irExpression = (IrExpression) obj;
            irStringConcatenationImpl.getArguments().set(i, liveLiteralTransformer.enter(String.valueOf(i), new Function0() { // from class: vd9
                public final Object invoke() {
                    return LiveLiteralTransformer.visitStringConcatenation$lambda$0$0$0$0(irExpression, liveLiteralTransformer);
                }
            }));
            i = i2;
        }
        return irStringConcatenationImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitStringConcatenation$lambda$0$0$0$0(IrExpression irExpression, LiveLiteralTransformer liveLiteralTransformer) {
        return irExpression.transform(liveLiteralTransformer, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitTry$lambda$1$0$0(IrCatch irCatch, LiveLiteralTransformer liveLiteralTransformer) {
        return irCatch.getResult().transform(liveLiteralTransformer, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrVarargElement visitVararg$lambda$0$0$0(IrVarargElement irVarargElement, LiveLiteralTransformer liveLiteralTransformer) {
        IrVarargElement irVarargElementTransform = irVarargElement.transform(liveLiteralTransformer, (Object) null);
        irVarargElementTransform.getClass();
        return irVarargElementTransform;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0063  */
    /* JADX WARN: Code duplicated, block: B:26:0x006b A[SYNTHETIC] */
    public static IrFunctionAccessExpression w(final IrFunctionAccessExpression irFunctionAccessExpression, final LiveLiteralTransformer liveLiteralTransformer) {
        String str;
        int i;
        String string;
        int size = irFunctionAccessExpression.getArguments().size();
        final int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            final IrExpression irExpression = (IrExpression) irFunctionAccessExpression.getArguments().get(i2);
            int i4 = WhenMappings.$EnumSwitchMapping$0[((IrValueParameter) irFunctionAccessExpression.getSymbol().getOwner().getParameters().get(i2)).getKind().ordinal()];
            if (i4 != 1) {
                if (i4 == 2) {
                    str = "$$this";
                } else {
                    if (i4 != 3 && i4 != 4) {
                        bu8.a();
                        return null;
                    }
                    StringBuilder sb = new StringBuilder("arg-");
                    i = i3 + 1;
                    sb.append(i3);
                    string = sb.toString();
                }
                if (irExpression != null) {
                    liveLiteralTransformer.enter(string, new Function0() { // from class: sc9
                        public final Object invoke() {
                            return LiveLiteralTransformer.visitFunctionAccess$lambda$0$0(irFunctionAccessExpression, i2, irExpression, liveLiteralTransformer);
                        }
                    });
                }
                i2++;
                i3 = i;
            } else {
                str = "$this";
            }
            i = i3;
            string = str;
            if (irExpression != null) {
                liveLiteralTransformer.enter(string, new Function0() { // from class: sc9
                    public final Object invoke() {
                        return LiveLiteralTransformer.visitFunctionAccess$lambda$0$0(irFunctionAccessExpression, i2, irExpression, liveLiteralTransformer);
                    }
                });
            }
            i2++;
            i3 = i;
        }
        return irFunctionAccessExpression;
    }

    public static IrStatement y(LiveLiteralTransformer liveLiteralTransformer, IrAnonymousInitializer irAnonymousInitializer) {
        return super.visitAnonymousInitializer(irAnonymousInitializer);
    }

    public final IrSimpleFunction addSetter(IrProperty irProperty, Function1<? super IrFunctionBuilder, Unit> function1) {
        irProperty.getClass();
        function1.getClass();
        IrFunctionBuilder irFunctionBuilder = new IrFunctionBuilder();
        Name nameSpecial = Name.special("<set-" + irProperty.getName() + '>');
        nameSpecial.getClass();
        irFunctionBuilder.setName(nameSpecial);
        function1.invoke(irFunctionBuilder);
        IrSimpleFunction irSimpleFunctionBuildFunction = buildFunction(getContext().getIrFactory(), irFunctionBuilder);
        irProperty.setSetter(irSimpleFunctionBuildFunction);
        irSimpleFunctionBuildFunction.setParent(irProperty.getParent());
        return irSimpleFunctionBuildFunction;
    }

    public final IrSimpleFunction buildFunction(IrFactory irFactory, IrFunctionBuilder irFunctionBuilder) {
        irFactory.getClass();
        irFunctionBuilder.getClass();
        return irFactory.createSimpleFunction(irFunctionBuilder.getStartOffset(), irFunctionBuilder.getEndOffset(), irFunctionBuilder.getOrigin(), irFunctionBuilder.getName(), irFunctionBuilder.getVisibility(), irFunctionBuilder.isInline(), irFunctionBuilder.isExpect(), irFunctionBuilder.getReturnType(), irFunctionBuilder.getModality(), new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), irFunctionBuilder.isTailrec(), irFunctionBuilder.isSuspend(), irFunctionBuilder.isOperator(), irFunctionBuilder.isInfix(), irFunctionBuilder.isExternal(), irFunctionBuilder.getContainerSource(), irFunctionBuilder.isFakeOverride());
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
    }

    public Set<String> makeKeySet() {
        return new LinkedHashSet();
    }

    public IrStatement visitAnonymousInitializer(final IrAnonymousInitializer declaration) {
        declaration.getClass();
        return hasNoLiveLiteralsAnnotation(declaration) ? declaration : (IrStatement) enter("init", new Function0() { // from class: xc9
            public final Object invoke() {
                return LiveLiteralTransformer.y(this.b, declaration);
            }
        });
    }

    public IrExpression visitBlock(final IrBlock expression) {
        expression.getClass();
        IrStatementOrigin origin = expression.getOrigin();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        if (!Intrinsics.areEqual(origin, companion.getFOR_LOOP()) && !Intrinsics.areEqual(origin, companion.getFOR_LOOP_INNER_WHILE())) {
            return (IrExpression) siblings(new Function0() { // from class: td9
                public final Object invoke() {
                    return LiveLiteralTransformer.u(this.b, expression);
                }
            });
        }
        List statements = expression.getStatements();
        IrStatement irStatementTransform = ((IrStatement) expression.getStatements().get(1)).transform(this, (Object) null);
        irStatementTransform.getClass();
        statements.set(1, irStatementTransform);
        return expression;
    }

    public IrBody visitBlockBody(final IrBlockBody body) {
        body.getClass();
        return (IrBody) siblings(new Function0() { // from class: hd9
            public final Object invoke() {
                return LiveLiteralTransformer.r(this.b, body);
            }
        });
    }

    public IrBranch visitBranch(final IrBranch branch) {
        branch.getClass();
        return BuildersKt.IrBranchImpl(branch.getStartOffset(), branch.getEndOffset(), (IrExpression) enter("cond", new Function0() { // from class: zd9
            public final Object invoke() {
                return LiveLiteralTransformer.p(branch, this);
            }
        }), (IrExpression) enter("branch", new Function0() { // from class: ae9
            public final Object invoke() {
                return LiveLiteralTransformer.H(branch, this);
            }
        }));
    }

    public IrStatement visitClass(final IrClass declaration) {
        declaration.getClass();
        if (hasNoLiveLiteralsAnnotation(declaration) || IrUtilsKt.isAnnotationClass(declaration)) {
            return declaration;
        }
        return (IrStatement) siblings("class-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: rd9
            public final Object invoke() {
                return LiveLiteralTransformer.S(this.b, declaration);
            }
        });
    }

    public IrExpression visitComposite(final IrComposite expression) {
        expression.getClass();
        return (IrExpression) siblings(new Function0() { // from class: bd9
            public final Object invoke() {
                return LiveLiteralTransformer.T(this.b, expression);
            }
        });
    }

    public IrExpression visitConst(IrConst expression) {
        expression.getClass();
        if (!Intrinsics.areEqual(expression.getKind(), IrConstKind.Null.INSTANCE)) {
            Pair<String, Boolean> pairBuildPath = this.keyVisitor.buildPath(expression.getKind().getAsString(), "$", "-");
            String str = (String) pairBuildPath.component1();
            if (!((Boolean) pairBuildPath.component2()).booleanValue()) {
                IrFile irFile = this.currentFile;
                if (irFile != null) {
                    SourceRangeInfo sourceRangeInfo = irFile.getFileEntry().getSourceRangeInfo(expression.getStartOffset(), expression.getEndOffset());
                    throw new IllegalStateException(("Duplicate live literal key found: " + str + "\nCaused by element at: " + sourceRangeInfo.getFilePath() + ':' + sourceRangeInfo.getStartLineNumber() + ':' + sourceRangeInfo.getStartColumnNumber() + "\nIf you encounter this error, please file a bug at https://issuetracker.google.com/issues?q=componentid:610764\nTry adding the `@NoLiveLiterals` annotation around the surrounding code to avoid this exception.").toString());
                }
            } else if (this.liveLiteralsEnabled) {
                int startOffset = expression.getStartOffset();
                int endOffset = expression.getEndOffset();
                expression.setStartOffset(-1);
                expression.setEndOffset(-1);
                Unit unit = Unit.INSTANCE;
                IrSimpleFunction irSimpleFunctionIrLiveLiteralGetter = irLiveLiteralGetter(str, expression, expression.getType(), startOffset);
                IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(startOffset, endOffset, expression.getType(), irSimpleFunctionIrLiveLiteralGetter.getSymbol(), irSimpleFunctionIrLiveLiteralGetter.getSymbol().getOwner().getTypeParameters().size(), (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
                irCallImplIrCallImpl$default.setDispatchReceiver(irGetLiveLiteralsClass(startOffset, endOffset));
                return irCallImplIrCallImpl$default;
            }
        }
        return expression;
    }

    public IrElseBranch visitElseBranch(final IrElseBranch branch) {
        branch.getClass();
        return BuildersKt.IrElseBranchImpl(branch.getStartOffset(), branch.getEndOffset(), branch.getCondition(), (IrExpression) enter("else", new Function0() { // from class: vc9
            public final Object invoke() {
                return LiveLiteralTransformer.G(branch, this);
            }
        }));
    }

    public IrStatement visitEnumEntry(final IrEnumEntry declaration) {
        declaration.getClass();
        return (IrStatement) enter("entry-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: yd9
            public final Object invoke() {
                return LiveLiteralTransformer.n(this.b, declaration);
            }
        });
    }

    public IrFile visitFile(final IrFile declaration) throws Exception {
        declaration.getClass();
        try {
            if (hasNoLiveLiteralsAnnotation(declaration)) {
                return declaration;
            }
            final String str = (String) CollectionsKt.last(StringsKt.split$default(declaration.getFileEntry().getName(), new char[]{'/'}, false, 0, 6, (Object) null));
            final Set<String> setMakeKeySet = makeKeySet();
            return (IrFile) this.keyVisitor.root(setMakeKeySet, new Function0() { // from class: gd9
                public final Object invoke() {
                    return LiveLiteralTransformer.visitFile$lambda$0$0(this.b, declaration, setMakeKeySet, str);
                }
            });
        } catch (Exception e) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
            throw new Exception("IR lowering failed at: " + IrDeclarationsKt.getName(declaration), e);
        }
    }

    public IrExpression visitFunctionAccess(final IrFunctionAccessExpression expression) {
        expression.getClass();
        IrFunction owner = expression.getSymbol().getOwner();
        if (((expression instanceof IrConstructorCall) || (expression instanceof IrDelegatingConstructorCall)) && IrUtilsKt.isAnnotationClass(IrUtilsKt.getParentAsClass(owner))) {
            return expression;
        }
        return (IrExpression) enter("call-" + asJvmFriendlyString(owner.getName()), new Function0() { // from class: jd9
            public final Object invoke() {
                return LiveLiteralTransformer.w(expression, this);
            }
        });
    }

    public IrExpression visitLoop(final IrLoop loop) {
        loop.getClass();
        IrStatementOrigin origin = loop.getOrigin();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        return (Intrinsics.areEqual(origin, companion.getWHILE_LOOP()) || Intrinsics.areEqual(origin, companion.getFOR_LOOP_INNER_WHILE())) ? (IrExpression) enter("loop", new Function0() { // from class: wd9
            public final Object invoke() {
                return LiveLiteralTransformer.R(loop, this);
            }
        }) : (IrExpression) enter("loop", new Function0() { // from class: xd9
            public final Object invoke() {
                return LiveLiteralTransformer.l(loop, this);
            }
        });
    }

    public IrStatement visitProperty(final IrProperty declaration) {
        declaration.getClass();
        if (hasNoLiveLiteralsAnnotation(declaration)) {
            return declaration;
        }
        final IrField backingField = declaration.getBackingField();
        final IrSimpleFunction getter = declaration.getGetter();
        final IrSimpleFunction setter = declaration.getSetter();
        return (IrStatement) enter("val-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: zc9
            public final Object invoke() {
                return LiveLiteralTransformer.m(declaration, backingField, this, getter, setter);
            }
        });
    }

    public IrExpression visitSetField(final IrSetField expression) {
        expression.getClass();
        return (IrExpression) enter("set-" + expression.getSymbol().getOwner().getName(), new Function0() { // from class: ad9
            public final Object invoke() {
                return LiveLiteralTransformer.J(this.b, expression);
            }
        });
    }

    public IrExpression visitSetValue(final IrSetValue expression) {
        expression.getClass();
        IrValueDeclaration owner = expression.getSymbol().getOwner();
        Name name = owner.getName();
        IrDeclarationOrigin origin = owner.getOrigin();
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        if (Intrinsics.areEqual(origin, companion.getFOR_LOOP_IMPLICIT_VARIABLE()) || Intrinsics.areEqual(origin, companion.getIR_TEMPORARY_VARIABLE()) || Intrinsics.areEqual(origin, companion.getFOR_LOOP_VARIABLE())) {
            return expression;
        }
        return (IrExpression) enter("set-" + name, new Function0() { // from class: wc9
            public final Object invoke() {
                return LiveLiteralTransformer.A(this.b, expression);
            }
        });
    }

    public IrStatement visitSimpleFunction(final IrSimpleFunction declaration) {
        String str;
        declaration.getClass();
        if (hasNoLiveLiteralsAnnotation(declaration)) {
            return declaration;
        }
        String strAsJvmFriendlyString = asJvmFriendlyString(declaration.getName());
        if (Intrinsics.areEqual(strAsJvmFriendlyString, "<anonymous>")) {
            str = "lambda";
        } else {
            str = "fun-" + strAsJvmFriendlyString;
        }
        return (IrStatement) enter(str, new Function0() { // from class: sd9
            public final Object invoke() {
                return LiveLiteralTransformer.I(this.b, declaration);
            }
        });
    }

    public IrExpression visitStringConcatenation(final IrStringConcatenation expression) {
        expression.getClass();
        return !(expression instanceof IrStringConcatenationImpl) ? expression : (IrExpression) enter("str", new Function0() { // from class: qd9
            public final Object invoke() {
                return LiveLiteralTransformer.M(this.b, expression);
            }
        });
    }

    public IrExpression visitTry(final IrTry aTry) {
        aTry.getClass();
        aTry.setTryResult((IrExpression) enter("try", new Function0() { // from class: be9
            public final Object invoke() {
                return LiveLiteralTransformer.L(aTry, this);
            }
        }));
        siblings(new Function0() { // from class: tc9
            public final Object invoke() {
                return LiveLiteralTransformer.P(aTry, this);
            }
        });
        aTry.setFinallyExpression((IrExpression) enter("finally", new Function0() { // from class: uc9
            public final Object invoke() {
                return LiveLiteralTransformer.v(aTry, this);
            }
        }));
        return aTry;
    }

    public IrStatement visitValueParameter(final IrValueParameter declaration) {
        declaration.getClass();
        return (IrStatement) enter("param-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: ed9
            public final Object invoke() {
                return LiveLiteralTransformer.D(this.b, declaration);
            }
        });
    }

    public IrExpression visitVararg(final IrVararg expression) {
        expression.getClass();
        return !(expression instanceof IrVarargImpl) ? expression : (IrExpression) enter("vararg", new Function0() { // from class: cd9
            public final Object invoke() {
                return LiveLiteralTransformer.q(expression, this);
            }
        });
    }

    public IrStatement visitVariable(final IrVariable declaration) {
        declaration.getClass();
        return (IrStatement) enter("val-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: kd9
            public final Object invoke() {
                return LiveLiteralTransformer.E(this.b, declaration);
            }
        });
    }

    public IrExpression visitWhen(final IrWhen expression) {
        expression.getClass();
        IrStatementOrigin origin = expression.getOrigin();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        if (Intrinsics.areEqual(origin, companion.getANDAND())) {
            expression.getBranches().set(0, ((IrBranch) expression.getBranches().get(0)).transform(this, (Object) null));
            return expression;
        }
        if (!Intrinsics.areEqual(origin, companion.getOROR())) {
            return Intrinsics.areEqual(origin, companion.getIF()) ? (IrExpression) siblings("if", new Function0() { // from class: nd9
                public final Object invoke() {
                    return LiveLiteralTransformer.N(this.b, expression);
                }
            }) : (IrExpression) siblings("when", new Function0() { // from class: pd9
                public final Object invoke() {
                    return LiveLiteralTransformer.Q(this.b, expression);
                }
            });
        }
        expression.getBranches().set(1, ((IrBranch) expression.getBranches().get(1)).transform(this, (Object) null));
        return expression;
    }

    private final <T> T siblings(Function0<? extends T> block) {
        return (T) this.keyVisitor.siblings(block);
    }
}

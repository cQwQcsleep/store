package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.ComposeIrGenerationExtension;
import androidx.compose.compiler.plugins.kotlin.analysis.FqNameMatcher;
import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.k1.ComposeDescriptorSerializerContext;
import androidx.compose.compiler.plugins.kotlin.lower.ClassStabilityInferredCollection;
import androidx.compose.compiler.plugins.kotlin.lower.ClassStabilityTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableDefaultParamLowering;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunInterfaceLowering;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableLambdaAnnotator;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableTargetAnnotationsTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableVersionOverloadsLowering;
import androidx.compose.compiler.plugins.kotlin.lower.ComposerIntrinsicTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.ComposerLambdaMemoization;
import androidx.compose.compiler.plugins.kotlin.lower.ComposerParamTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.CopyDefaultValuesFromExpectLowering;
import androidx.compose.compiler.plugins.kotlin.lower.DurableFunctionKeyTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.DurableKeyVisitor;
import androidx.compose.compiler.plugins.kotlin.lower.KlibAssignableParamTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.LiveLiteralTransformer;
import androidx.compose.compiler.plugins.kotlin.lower.WrapJsComposableLambdaLowering;
import androidx.compose.compiler.plugins.kotlin.lower.hiddenfromobjc.AddHiddenFromObjCLowering;
import com.intellij.openapi.progress.ProgressManager;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.descriptors.annotations.KotlinRetention;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;
import org.jetbrains.kotlin.platform.JsPlatformKt;
import org.jetbrains.kotlin.platform.WasmPlatformKt;
import org.jetbrains.kotlin.platform.jvm.JvmPlatformKt;
import org.jetbrains.kotlin.platform.konan.NativePlatformKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B½\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u001c\b\u0002\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J\u000e\u00100\u001a\u00020\u0003*\u0004\u0018\u00010-H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b \u0010\u001fR\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010,\u001a\u0004\u0018\u00010-*\u00020+8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u00061"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeIrGenerationExtension;", "Lorg/jetbrains/kotlin/backend/common/extensions/IrGenerationExtension;", "liveLiteralsEnabled", "", "liveLiteralsV2Enabled", "generateFunctionKeyMetaAnnotations", "sourceInformationEnabled", "traceMarkersEnabled", "metricsDestination", "", "reportsDestination", "useK2", "stableTypeMatchers", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcher;", "moduleMetricsFactory", "Lkotlin/Function2;", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "descriptorSerializerContext", "Landroidx/compose/compiler/plugins/kotlin/k1/ComposeDescriptorSerializerContext;", "featureFlags", "skipIfRuntimeNotFound", "targetRuntimeVersion", "Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "<init>", "(ZZLjava/lang/Boolean;ZZLjava/lang/String;Ljava/lang/String;ZLjava/util/Set;Lkotlin/jvm/functions/Function2;Landroidx/compose/compiler/plugins/kotlin/k1/ComposeDescriptorSerializerContext;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;ZLandroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;)V", "getLiveLiteralsEnabled$annotations", "()V", "getLiveLiteralsV2Enabled$annotations", "Ljava/lang/Boolean;", "value", "metrics", "getMetrics", "()Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "generate", "", "moduleFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "pluginContext", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "keyMetaAnnotation", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getKeyMetaAnnotation", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;)Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "hasRuntimeRetention", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeIrGenerationExtension implements IrGenerationExtension {
    private final ComposeDescriptorSerializerContext descriptorSerializerContext;
    private final FeatureFlags featureFlags;
    private final Boolean generateFunctionKeyMetaAnnotations;
    private final boolean liveLiteralsEnabled;
    private final boolean liveLiteralsV2Enabled;
    private final MessageCollector messageCollector;
    private ModuleMetrics metrics;
    private final String metricsDestination;
    private final Function2<StabilityInferencer, FeatureFlags, ModuleMetrics> moduleMetricsFactory;
    private final String reportsDestination;
    private final boolean skipIfRuntimeNotFound;
    private final boolean sourceInformationEnabled;
    private final Set<FqNameMatcher> stableTypeMatchers;
    private final ComposeRuntimeVersion targetRuntimeVersion;
    private final boolean traceMarkersEnabled;
    private final boolean useK2;

    public /* synthetic */ ComposeIrGenerationExtension(boolean z, boolean z2, Boolean bool, boolean z3, boolean z4, String str, String str2, boolean z5, Set set, Function2 function2, ComposeDescriptorSerializerContext composeDescriptorSerializerContext, FeatureFlags featureFlags, boolean z6, ComposeRuntimeVersion composeRuntimeVersion, MessageCollector messageCollector, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? null : bool, (i & 8) != 0 ? true : z3, (i & 16) != 0 ? true : z4, (i & 32) != 0 ? null : str, (i & 64) != 0 ? null : str2, (i & 128) != 0 ? false : z5, (i & 256) != 0 ? SetsKt.emptySet() : set, (i & 512) != 0 ? null : function2, (i & 1024) != 0 ? null : composeDescriptorSerializerContext, featureFlags, (i & 4096) != 0 ? false : z6, (i & 8192) != 0 ? null : composeRuntimeVersion, messageCollector);
    }

    public static Stability a(StabilityInferencer stabilityInferencer, IrType irType, IrFile irFile) {
        irType.getClass();
        return stabilityInferencer.stabilityOf(irType, irFile);
    }

    private final IrClass getKeyMetaAnnotation(IrPluginContext irPluginContext) {
        IrClassSymbol irClassSymbolFindClass = irPluginContext.finderForBuiltins().findClass(ComposeClassIds.INSTANCE.getFunctionKeyMeta());
        if (irClassSymbolFindClass != null) {
            return irClassSymbolFindClass.getOwner();
        }
        return null;
    }

    private static /* synthetic */ void getLiveLiteralsEnabled$annotations() {
    }

    private static /* synthetic */ void getLiveLiteralsV2Enabled$annotations() {
    }

    private final boolean hasRuntimeRetention(IrClass irClass) {
        KotlinRetention annotationRetention;
        return irClass == null || (annotationRetention = IrUtilsKt.getAnnotationRetention(irClass)) == null || annotationRetention == KotlinRetention.RUNTIME;
    }

    public void generate(IrModuleFragment moduleFragment, IrPluginContext pluginContext) {
        IrPluginContext irPluginContext;
        ClassStabilityInferredCollection classStabilityInferredCollection;
        moduleFragment.getClass();
        pluginContext.getClass();
        boolean zIsJvm = JvmPlatformKt.isJvm(pluginContext.getPlatform());
        if (new VersionChecker(pluginContext, this.messageCollector).check(this.skipIfRuntimeNotFound) == VersionCheckerResult.NOT_FOUND) {
            return;
        }
        final StabilityInferencer stabilityInferencer = new StabilityInferencer(JvmPlatformKt.isJvm(pluginContext.getPlatform()), pluginContext.getModuleDescriptor(), this.stableTypeMatchers);
        if (this.useK2) {
            IrVisitorsKt.acceptVoid(moduleFragment, new ComposableLambdaAnnotator(pluginContext));
        }
        Function2<StabilityInferencer, FeatureFlags, ModuleMetrics> function2 = this.moduleMetricsFactory;
        if (function2 != null) {
            this.metrics = (ModuleMetrics) function2.invoke(stabilityInferencer, this.featureFlags);
        } else if (this.metricsDestination != null || this.reportsDestination != null) {
            String strAsString = moduleFragment.getName().asString();
            strAsString.getClass();
            this.metrics = new ModuleMetricsImpl(strAsString, this.featureFlags, new Function2() { // from class: mm2
                public final Object invoke(Object obj, Object obj2) {
                    return ComposeIrGenerationExtension.a(stabilityInferencer, (IrType) obj, (IrFile) obj2);
                }
            });
        }
        if (NativePlatformKt.isNative(pluginContext.getPlatform())) {
            ModuleMetrics moduleMetrics = this.metrics;
            ComposeDescriptorSerializerContext composeDescriptorSerializerContext = this.descriptorSerializerContext;
            new AddHiddenFromObjCLowering(pluginContext, moduleMetrics, composeDescriptorSerializerContext != null ? composeDescriptorSerializerContext.getHideFromObjCDeclarationsSet() : null, stabilityInferencer, this.featureFlags).lower(moduleFragment);
            irPluginContext = pluginContext;
        } else {
            irPluginContext = pluginContext;
        }
        boolean z = this.useK2;
        ModuleMetrics moduleMetrics2 = this.metrics;
        ComposeDescriptorSerializerContext composeDescriptorSerializerContext2 = this.descriptorSerializerContext;
        ClassStabilityTransformer classStabilityTransformer = new ClassStabilityTransformer(z, irPluginContext, moduleMetrics2, stabilityInferencer, (composeDescriptorSerializerContext2 == null || (classStabilityInferredCollection = composeDescriptorSerializerContext2.getClassStabilityInferredCollection()) == null || JvmPlatformKt.isJvm(irPluginContext.getPlatform())) ? null : classStabilityInferredCollection, this.featureFlags, this.messageCollector);
        IrPluginContext irPluginContext2 = irPluginContext;
        classStabilityTransformer.lower(moduleFragment);
        ProgressManager.checkCanceled();
        if (this.liveLiteralsEnabled || this.liveLiteralsV2Enabled) {
            LiveLiteralTransformer liveLiteralTransformer = new LiveLiteralTransformer(true, this.liveLiteralsV2Enabled, new DurableKeyVisitor(null, 1, null), irPluginContext2, this.metrics, stabilityInferencer, this.featureFlags);
            irPluginContext2 = irPluginContext2;
            stabilityInferencer = stabilityInferencer;
            liveLiteralTransformer.lower(moduleFragment);
        }
        new ComposableFunInterfaceLowering(irPluginContext2).lower(moduleFragment);
        ProgressManager.checkCanceled();
        DurableFunctionKeyTransformer durableFunctionKeyTransformer = new DurableFunctionKeyTransformer(irPluginContext2, this.metrics, stabilityInferencer, this.featureFlags);
        durableFunctionKeyTransformer.lower(moduleFragment);
        if (!this.useK2) {
            new CopyDefaultValuesFromExpectLowering(irPluginContext2).lower(moduleFragment);
        }
        ProgressManager.checkCanceled();
        new ComposableVersionOverloadsLowering(irPluginContext2).lower(moduleFragment);
        ProgressManager.checkCanceled();
        new ComposableDefaultParamLowering(irPluginContext2, this.metrics, stabilityInferencer, this.featureFlags).lower(moduleFragment);
        ProgressManager.checkCanceled();
        new ComposerLambdaMemoization(irPluginContext2, this.metrics, stabilityInferencer, this.featureFlags).lower(moduleFragment);
        ProgressManager.checkCanceled();
        new ComposerParamTransformer(irPluginContext2, stabilityInferencer, this.metrics, this.featureFlags).lower(moduleFragment);
        ProgressManager.checkCanceled();
        new ComposableTargetAnnotationsTransformer(irPluginContext2, this.metrics, stabilityInferencer, this.featureFlags).lower(moduleFragment);
        new ComposerIntrinsicTransformer(irPluginContext2).lower(moduleFragment);
        ProgressManager.checkCanceled();
        StabilityInferencer stabilityInferencer2 = stabilityInferencer;
        new ComposableFunctionBodyTransformer(irPluginContext2, this.metrics, stabilityInferencer2, this.sourceInformationEnabled, this.traceMarkersEnabled, this.targetRuntimeVersion, this.featureFlags).lower(moduleFragment);
        if (!zIsJvm) {
            new KlibAssignableParamTransformer(irPluginContext2, this.metrics, stabilityInferencer2, this.featureFlags).lower(moduleFragment);
        }
        if (JsPlatformKt.isJs(irPluginContext2.getPlatform()) || WasmPlatformKt.isWasm(irPluginContext2.getPlatform())) {
            new WrapJsComposableLambdaLowering(irPluginContext2, this.metrics, stabilityInferencer2, this.featureFlags).lower(moduleFragment);
        }
        if (Intrinsics.areEqual(this.generateFunctionKeyMetaAnnotations, Boolean.TRUE) || (this.generateFunctionKeyMetaAnnotations == null && !hasRuntimeRetention(getKeyMetaAnnotation(irPluginContext2)))) {
            durableFunctionKeyTransformer.realizeKeyMetaAnnotations(moduleFragment);
        }
        String str = this.metricsDestination;
        if (str != null) {
            this.metrics.saveMetricsTo(str);
        }
        String str2 = this.reportsDestination;
        if (str2 != null) {
            this.metrics.saveReportsTo(str2);
        }
    }

    public final ModuleMetrics getMetrics() {
        return this.metrics;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ComposeIrGenerationExtension(boolean z, boolean z2, Boolean bool, boolean z3, boolean z4, String str, String str2, boolean z5, Set<FqNameMatcher> set, Function2<? super StabilityInferencer, ? super FeatureFlags, ? extends ModuleMetrics> function2, ComposeDescriptorSerializerContext composeDescriptorSerializerContext, FeatureFlags featureFlags, boolean z6, ComposeRuntimeVersion composeRuntimeVersion, MessageCollector messageCollector) {
        set.getClass();
        featureFlags.getClass();
        messageCollector.getClass();
        this.liveLiteralsEnabled = z;
        this.liveLiteralsV2Enabled = z2;
        this.generateFunctionKeyMetaAnnotations = bool;
        this.sourceInformationEnabled = z3;
        this.traceMarkersEnabled = z4;
        this.metricsDestination = str;
        this.reportsDestination = str2;
        this.useK2 = z5;
        this.stableTypeMatchers = set;
        this.moduleMetricsFactory = function2;
        this.descriptorSerializerContext = composeDescriptorSerializerContext;
        this.featureFlags = featureFlags;
        this.skipIfRuntimeNotFound = z6;
        this.targetRuntimeVersion = composeRuntimeVersion;
        this.messageCollector = messageCollector;
        this.metrics = EmptyModuleMetrics.INSTANCE;
    }
}

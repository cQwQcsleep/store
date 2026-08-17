package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.WeakBindingTraceKt;
import androidx.compose.compiler.plugins.kotlin.analysis.ComposeWritableSlices;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/DurableFunctionKeyTransformer;", "Landroidx/compose/compiler/plugins/kotlin/lower/DurableKeyTransformer;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "realizeKeyMetaAnnotations", "", "moduleFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "keyMetaAnnotation", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "irKeyMetaAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "key", "Landroidx/compose/compiler/plugins/kotlin/lower/KeyInfo;", "visitSimpleFunction", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DurableFunctionKeyTransformer extends DurableKeyTransformer {
    private final IrClassSymbol keyMetaAnnotation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DurableFunctionKeyTransformer(IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, FeatureFlags featureFlags) {
        super(new DurableKeyVisitor(null, 1, null), irPluginContext, stabilityInferencer, moduleMetrics, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        this.keyMetaAnnotation = getTopLevelClassOrNull(ComposeClassIds.INSTANCE.getFunctionKeyMeta());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IrAnnotation irKeyMetaAnnotation(KeyInfo key) {
        IrClassSymbol irClassSymbol = this.keyMetaAnnotation;
        irClassSymbol.getClass();
        IrAnnotationImpl irAnnotationImplIrAnnotationImpl$default = BuildersKt.IrAnnotationImpl$default(-1, -1, IrTypesKt.getDefaultType(irClassSymbol), (IrConstructorSymbol) SequencesKt.single(IrUtilsKt.getConstructors(this.keyMetaAnnotation)), 0, 0, (IrStatementOrigin) null, (SourceElement) null, 192, (Object) null);
        irAnnotationImplIrAnnotationImpl$default.getArguments().set(0, irConst(Integer.hashCode(key.getKey())));
        irAnnotationImplIrAnnotationImpl$default.getArguments().set(1, irConst(key.getStartOffset()));
        irAnnotationImplIrAnnotationImpl$default.getArguments().set(2, irConst(key.getEndOffset()));
        return irAnnotationImplIrAnnotationImpl$default;
    }

    public final void realizeKeyMetaAnnotations(IrModuleFragment moduleFragment) {
        moduleFragment.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(moduleFragment, new IrElementTransformerVoid() { // from class: androidx.compose.compiler.plugins.kotlin.lower.DurableFunctionKeyTransformer.realizeKeyMetaAnnotations.1
            public IrStatement visitSimpleFunction(IrSimpleFunction declaration) {
                declaration.getClass();
                DurableFunctionKeyTransformer durableFunctionKeyTransformer = DurableFunctionKeyTransformer.this;
                KeyInfo keyInfo = (KeyInfo) WeakBindingTraceKt.getIrTrace(durableFunctionKeyTransformer.getContext()).get(ComposeWritableSlices.INSTANCE.getDURABLE_FUNCTION_KEY(), declaration);
                if (keyInfo != null && durableFunctionKeyTransformer.hasComposableAnnotation(declaration) && !IrUtilsKt.hasAnnotation(declaration, ComposeClassIds.INSTANCE.getFunctionKeyMeta())) {
                    declaration.setAnnotations(CollectionsKt.plus(declaration.getAnnotations(), durableFunctionKeyTransformer.irKeyMetaAnnotation(keyInfo)));
                }
                return super.visitSimpleFunction(declaration);
            }
        });
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.DurableKeyTransformer
    public IrStatement visitSimpleFunction(IrSimpleFunction declaration) {
        declaration.getClass();
        Pair pairBuildKey$default = DurableKeyTransformer.buildKey$default(this, "fun-" + signatureString(declaration), null, null, 6, null);
        WeakBindingTraceKt.getIrTrace(getContext()).record(ComposeWritableSlices.INSTANCE.getDURABLE_FUNCTION_KEY(), declaration, new KeyInfo((String) pairBuildKey$default.component1(), declaration.getStartOffset(), declaration.getEndOffset(), ((Boolean) pairBuildKey$default.component2()).booleanValue() ^ true));
        return super.visitSimpleFunction(declaration);
    }
}

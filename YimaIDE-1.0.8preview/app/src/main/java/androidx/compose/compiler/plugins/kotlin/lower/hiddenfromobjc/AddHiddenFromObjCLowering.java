package androidx.compose.compiler.plugins.kotlin.lower.hiddenfromobjc;

import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.lower.AbstractComposeLowering;
import androidx.compose.compiler.plugins.kotlin.lower.AbstractComposeLoweringKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableTypeRemapperKt;
import androidx.compose.compiler.plugins.kotlin.lower.hiddenfromobjc.AddHiddenFromObjCLowering;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.platform.konan.NativePlatformKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\f\u0010\u001e\u001a\u00020\u0015*\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001fH\u0016J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\"H\u0016J\f\u0010#\u001a\u00020\u0017*\u00020$H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/hiddenfromobjc/AddHiddenFromObjCLowering;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "pluginContext", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "hideFromObjCDeclarationsSet", "Landroidx/compose/compiler/plugins/kotlin/lower/hiddenfromobjc/HideFromObjCDeclarationsSet;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/lower/hiddenfromobjc/HideFromObjCDeclarationsSet;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "hiddenFromObjCAnnotation", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getHiddenFromObjCAnnotation", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "hiddenFromObjCAnnotation$delegate", "Lkotlin/Lazy;", "currentShouldAnnotateClass", "", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "visitClass", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "isSyntheticFun", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "visitFunction", "visitProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "addHiddenFromObjCAnnotation", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class AddHiddenFromObjCLowering extends AbstractComposeLowering {
    private boolean currentShouldAnnotateClass;

    /* JADX INFO: renamed from: hiddenFromObjCAnnotation$delegate, reason: from kotlin metadata */
    private final Lazy hiddenFromObjCAnnotation;
    private final HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet;
    private final IrPluginContext pluginContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddHiddenFromObjCLowering(IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet, StabilityInferencer stabilityInferencer, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        this.pluginContext = irPluginContext;
        this.hideFromObjCDeclarationsSet = hideFromObjCDeclarationsSet;
        this.hiddenFromObjCAnnotation = LazyKt.lazy(new Function0() { // from class: pv
            public final Object invoke() {
                return AddHiddenFromObjCLowering.k(this.b);
            }
        });
    }

    private final void addHiddenFromObjCAnnotation(IrDeclaration irDeclaration) {
        if (AbstractComposeLoweringKt.hasFirDeclaration(irDeclaration)) {
            this.pluginContext.getMetadataDeclarationRegistrar().addMetadataVisibleAnnotationsToElement(irDeclaration, BuildersKt.fromSymbolOwner$default(IrAnnotationImpl.Companion, IrTypesKt.getDefaultType(getHiddenFromObjCAnnotation()), (IrConstructorSymbol) SequencesKt.first(IrUtilsKt.getConstructors(getHiddenFromObjCAnnotation())), (IrStatementOrigin) null, 4, (Object) null));
        }
    }

    private final IrClassSymbol getHiddenFromObjCAnnotation() {
        return (IrClassSymbol) this.hiddenFromObjCAnnotation.getValue();
    }

    private final boolean isSyntheticFun(IrFunction irFunction) {
        return Intrinsics.areEqual(irFunction.getOrigin(), IrDeclarationOrigin.Companion.getFAKE_OVERRIDE());
    }

    public static IrClassSymbol k(AddHiddenFromObjCLowering addHiddenFromObjCLowering) {
        return addHiddenFromObjCLowering.getTopLevelClass(AddHiddenFromObjCLoweringKt.getHiddenFromObjCClassId());
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        if (NativePlatformKt.isNative(getContext().getPlatform())) {
            IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
        } else {
            dt1.a("AddHiddenFromObjCLowering is expected to run only for k/native. The platform - ", getContext().getPlatform());
        }
    }

    public IrStatement visitClass(IrClass declaration) {
        declaration.getClass();
        boolean z = this.currentShouldAnnotateClass;
        this.currentShouldAnnotateClass = false;
        IrClass irClassVisitClass = super.visitClass(declaration);
        irClassVisitClass.getClass();
        IrClass irClass = irClassVisitClass;
        if (this.currentShouldAnnotateClass && irClass.isData()) {
            addHiddenFromObjCAnnotation(irClass);
            HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet = this.hideFromObjCDeclarationsSet;
            if (hideFromObjCDeclarationsSet != null) {
                hideFromObjCDeclarationsSet.add(irClass);
            }
        }
        this.currentShouldAnnotateClass = z;
        return irClass;
    }

    public IrStatement visitFunction(IrFunction declaration) {
        declaration.getClass();
        IrStatement irStatementVisitFunction = super.visitFunction(declaration);
        irStatementVisitFunction.getClass();
        IrFunction irFunction = (IrFunction) irStatementVisitFunction;
        if (AdditionalIrUtilsKt.isLocal(irFunction) || isSyntheticFun(irFunction) || !((Intrinsics.areEqual(irFunction.getVisibility(), DescriptorVisibilities.PUBLIC) || Intrinsics.areEqual(irFunction.getVisibility(), DescriptorVisibilities.PROTECTED)) && (hasComposableAnnotation(irFunction) || ComposableTypeRemapperKt.needsComposableRemapping(irFunction)))) {
            return irFunction;
        }
        addHiddenFromObjCAnnotation(irFunction);
        HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet = this.hideFromObjCDeclarationsSet;
        if (hideFromObjCDeclarationsSet != null) {
            hideFromObjCDeclarationsSet.add(irFunction);
        }
        this.currentShouldAnnotateClass = true;
        return irFunction;
    }

    public IrStatement visitProperty(IrProperty declaration) {
        IrSimpleFunction getter;
        declaration.getClass();
        IrProperty irPropertyVisitProperty = super.visitProperty(declaration);
        irPropertyVisitProperty.getClass();
        IrProperty irProperty = irPropertyVisitProperty;
        if (!AdditionalIrUtilsKt.isLocal(irProperty) && (((getter = irProperty.getGetter()) == null || !isSyntheticFun(getter)) && Intrinsics.areEqual(irProperty.getVisibility(), DescriptorVisibilities.PUBLIC))) {
            IrSimpleFunction getter2 = irProperty.getGetter();
            if (!(getter2 != null ? hasComposableAnnotation(getter2) : false)) {
                IrSimpleFunction getter3 = irProperty.getGetter();
                if (!(getter3 != null ? ComposableTypeRemapperKt.needsComposableRemapping(getter3) : false)) {
                    IrField backingField = irProperty.getBackingField();
                    if (!ComposableTypeRemapperKt.containsComposableAnnotation(backingField != null ? backingField.getType() : null)) {
                        return irProperty;
                    }
                }
            }
            addHiddenFromObjCAnnotation(irProperty);
            HideFromObjCDeclarationsSet hideFromObjCDeclarationsSet = this.hideFromObjCDeclarationsSet;
            if (hideFromObjCDeclarationsSet != null) {
                hideFromObjCDeclarationsSet.add(irProperty);
            }
            this.currentShouldAnnotateClass = true;
        }
        return irProperty;
    }
}

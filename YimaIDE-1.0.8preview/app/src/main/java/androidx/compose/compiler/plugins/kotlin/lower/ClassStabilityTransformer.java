package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.analysis.Stability;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityKt;
import androidx.compose.compiler.plugins.kotlin.lower.ClassStabilityTransformer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.ClassLoweringPass;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.jvm.ir.JvmIrTypeUtilsKt;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.platform.jvm.JvmPlatformKt;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003BC\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020!H\u0016J\u0014\u0010'\u001a\u00020\u001d*\u00020!2\u0006\u0010(\u001a\u00020)H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ClassStabilityTransformer;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "Lorg/jetbrains/kotlin/backend/common/ClassLoweringPass;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "useK2", "", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "classStabilityInferredCollection", "Landroidx/compose/compiler/plugins/kotlin/lower/ClassStabilityInferredCollection;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "<init>", "(ZLorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/lower/ClassStabilityInferredCollection;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;)V", "StabilityInferredClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "UNSTABLE", "", "STABLE", "unstableClassesWarning", "", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "irFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "visitClass", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "addStabilityMarkerField", "stabilityExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClassStabilityTransformer extends AbstractComposeLowering implements ClassLoweringPass, ModuleLoweringPass {
    private final int STABLE;
    private final IrClassSymbol StabilityInferredClass;
    private final int UNSTABLE;
    private final ClassStabilityInferredCollection classStabilityInferredCollection;
    private final MessageCollector messageCollector;
    private final Set<ClassDescriptor> unstableClassesWarning;
    private final boolean useK2;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassStabilityTransformer(boolean z, IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, ClassStabilityInferredCollection classStabilityInferredCollection, FeatureFlags featureFlags, MessageCollector messageCollector) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        messageCollector.getClass();
        this.useK2 = z;
        this.classStabilityInferredCollection = classStabilityInferredCollection;
        this.messageCollector = messageCollector;
        this.StabilityInferredClass = getTopLevelClass(ComposeClassIds.INSTANCE.getStabilityInferred());
        this.UNSTABLE = StabilityBits.UNSTABLE.bitsForSlot(0);
        this.STABLE = StabilityBits.STABLE.bitsForSlot(0);
        this.unstableClassesWarning = !JvmPlatformKt.isJvm(irPluginContext.getPlatform()) ? new LinkedHashSet() : null;
    }

    private final void addStabilityMarkerField(IrClass irClass, IrExpression irExpression) {
        makeStabilityField$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(irClass).setInitializer(getContext().getIrFactory().createExpressionBody(-1, -1, irExpression));
    }

    public static Unit k(ClassStabilityTransformer classStabilityTransformer, IrClass irClass) {
        irClass.getClass();
        Set<ClassDescriptor> set = classStabilityTransformer.unstableClassesWarning;
        if (set != null) {
            set.add(irClass.getDescriptor());
        }
        return Unit.INSTANCE;
    }

    public static IrExpression l(ClassStabilityTransformer classStabilityTransformer, IrTypeParameter irTypeParameter) {
        irTypeParameter.getClass();
        return classStabilityTransformer.irConst(classStabilityTransformer.STABLE);
    }

    public static Unit m(List list, Ref.IntRef intRef, Ref.BooleanRef booleanRef, Stability stability) {
        stability.getClass();
        if (stability instanceof Stability.Parameter) {
            int iIndexOf = list.indexOf(((Stability.Parameter) stability).getParameter().getSymbol());
            if (iIndexOf != -1) {
                intRef.element = (1 << iIndexOf) | intRef.element;
            } else {
                booleanRef.element = true;
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit n(ClassStabilityTransformer classStabilityTransformer, IrClass irClass) {
        irClass.getClass();
        Set<ClassDescriptor> set = classStabilityTransformer.unstableClassesWarning;
        if (set != null) {
            set.add(irClass.getDescriptor());
        }
        return Unit.INSTANCE;
    }

    public void lower(IrModuleFragment irModule) {
        Set<ClassDescriptor> set;
        irModule.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
        if (JvmPlatformKt.isJvm(getContext().getPlatform()) || (set = this.unstableClassesWarning) == null || set.isEmpty()) {
            return;
        }
        Set<ClassDescriptor> set2 = this.unstableClassesWarning;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = set2.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(DescriptorUtilsKt.getFqNameSafe((ClassDescriptor) it.next()).toString());
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.sorted(linkedHashSet), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        MessageCollector.report$default(this.messageCollector, CompilerMessageSeverity.WARNING, "Some of the dependencies were build using an older version of the Compose compiler plugin, which may cause additional (or endless) recompositions on non-JVM targets. To prevent that consider updating dependency libraries to versions built with a newer Compose compiler. Right now, the following classes are considered `Unstable`:\n" + strJoinToString$default, (CompilerMessageSourceLocation) null, 4, (Object) null);
    }

    public IrStatement visitClass(IrClass declaration) {
        IrConst irConstIrStableExpression$default;
        declaration.getClass();
        IrClass irClassVisitClass = super.visitClass(declaration);
        IrClass irClass = irClassVisitClass instanceof IrClass ? irClassVisitClass : null;
        if (irClass != null) {
            if ((!Intrinsics.areEqual(irClass.getVisibility(), DescriptorVisibilities.PUBLIC) && !Intrinsics.areEqual(irClass.getVisibility(), DescriptorVisibilities.INTERNAL)) || IrUtilsKt.isEnumClass(irClass) || IrUtilsKt.isEnumEntry(irClass) || IrUtilsKt.isInterface(irClass) || IrUtilsKt.isAnnotationClass(irClass) || IrUtilsKt.isAnonymousObject(irClass) || irClass.isExpect() || irClass.isInner() || IrUtilsKt.isFileClass(irClass) || irClass.isCompanion() || JvmIrTypeUtilsKt.isInlineClassType(IrUtilsKt.getDefaultType(irClass))) {
                return irClass;
            }
            if (StabilityKt.hasStableMarker(declaration)) {
                getMetrics().recordClass(declaration, true, Stability.INSTANCE.getStable());
                addStabilityMarkerField(irClass, irConst(this.STABLE));
                return irClass;
            }
            Stability stabilityNormalize = StabilityKt.normalize(getStabilityInferencer().stabilityOf((IrType) IrUtilsKt.getDefaultType(declaration), IrUtilsKt.getFileOrNull(declaration)));
            final Ref.IntRef intRef = new Ref.IntRef();
            if (irClass.getTypeParameters().isEmpty()) {
                irConstIrStableExpression$default = AbstractComposeLowering.irStableExpression$default(this, stabilityNormalize, null, new Function1() { // from class: kx1
                    public final Object invoke(Object obj) {
                        return ClassStabilityTransformer.k(this.b, (IrClass) obj);
                    }
                }, 1, null);
                if (irConstIrStableExpression$default == null) {
                    irConstIrStableExpression$default = irConst(this.UNSTABLE);
                }
                if (StabilityKt.knownStable(stabilityNormalize)) {
                    intRef.element = 1;
                }
            } else {
                List typeParameters = irClass.getTypeParameters();
                final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
                Iterator it = typeParameters.iterator();
                while (it.hasNext()) {
                    arrayList.add(((IrTypeParameter) it.next()).getSymbol());
                }
                final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                StabilityKt.forEach(stabilityNormalize, new Function1() { // from class: hx1
                    public final Object invoke(Object obj) {
                        return ClassStabilityTransformer.m(arrayList, intRef, booleanRef, (Stability) obj);
                    }
                });
                if (StabilityKt.knownStable(stabilityNormalize) && arrayList.size() < 32) {
                    intRef.element = (1 << arrayList.size()) | intRef.element;
                }
                if (booleanRef.element || (irConstIrStableExpression$default = irStableExpression(stabilityNormalize, new Function1() { // from class: ix1
                    public final Object invoke(Object obj) {
                        return ClassStabilityTransformer.l(this.b, (IrTypeParameter) obj);
                    }
                }, new Function1() { // from class: jx1
                    public final Object invoke(Object obj) {
                        return ClassStabilityTransformer.n(this.b, (IrClass) obj);
                    }
                })) == null) {
                    irConstIrStableExpression$default = irConst(this.UNSTABLE);
                }
            }
            getMetrics().recordClass(declaration, false, stabilityNormalize);
            IrAnnotation irAnnotationIrAnnotationImpl$default = BuildersKt.IrAnnotationImpl$default(-1, -1, IrTypesKt.getDefaultType(this.StabilityInferredClass), (IrConstructorSymbol) SequencesKt.first(IrUtilsKt.getConstructors(this.StabilityInferredClass)), 0, 0, (IrStatementOrigin) null, (SourceElement) null, 128, (Object) null);
            irAnnotationIrAnnotationImpl$default.getArguments().set(0, irConst(intRef.element));
            if (this.useK2 && AbstractComposeLoweringKt.hasFirDeclaration(irClass)) {
                getContext().getMetadataDeclarationRegistrar().addMetadataVisibleAnnotationsToElement((IrDeclaration) irClass, irAnnotationIrAnnotationImpl$default);
            } else {
                irClass.setAnnotations(CollectionsKt.plus(irClass.getAnnotations(), irAnnotationIrAnnotationImpl$default));
                ClassStabilityInferredCollection classStabilityInferredCollection = this.classStabilityInferredCollection;
                if (classStabilityInferredCollection != null) {
                    classStabilityInferredCollection.addClass(irClass, intRef.element);
                }
            }
            if (irClass.getVisibility().isPublicAPI() || Intrinsics.areEqual(irClass.getVisibility(), DescriptorVisibilities.INTERNAL)) {
                addStabilityMarkerField(irClass, irConstIrStableExpression$default);
                return irClassVisitClass;
            }
        }
        return irClassVisitClass;
    }

    public /* synthetic */ ClassStabilityTransformer(boolean z, IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, ClassStabilityInferredCollection classStabilityInferredCollection, FeatureFlags featureFlags, MessageCollector messageCollector, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, irPluginContext, moduleMetrics, stabilityInferencer, (i & 16) != 0 ? null : classStabilityInferredCollection, featureFlags, messageCollector);
    }

    public void lower(IrClass irClass) {
        irClass.getClass();
    }

    public void lower(IrFile irFile) {
        irFile.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(irFile, this);
    }
}

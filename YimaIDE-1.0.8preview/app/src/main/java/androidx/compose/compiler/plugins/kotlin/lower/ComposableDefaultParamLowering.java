package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.ComposeMetadata;
import androidx.compose.compiler.plugins.kotlin.ComposeNames;
import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableDefaultParamLowering;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.builders.IrBlockBodyBuilder;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.builders.declarations.IrClassBuilder;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\f\u0010\u001a\u001a\u00020\u000e*\u00020\u000eH\u0002J\u000e\u0010\u001b\u001a\u0004\u0018\u00010\u000e*\u00020\u000eH\u0002J\f\u0010\u001c\u001a\u00020\u001d*\u00020\u000eH\u0002J\f\u0010\u001e\u001a\u00020\u001d*\u00020\u000eH\u0002J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000eH\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0002J\u0016\u0010$\u001a\u0004\u0018\u00010\u000e*\u00020\u000e2\u0006\u0010%\u001a\u00020\u000eH\u0002R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ComposableDefaultParamLowering;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "originalToTransformed", "", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "visitSimpleFunction", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "visitCall", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "transformIfNeeded", "findOverriddenFunWithDefaultParam", "isVirtualFunctionWithDefaultParam", "", "isVirtualFunction", "makeDefaultParameterWrapper", "source", "getOrCreateDefaultImpls", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "parent", "makeStubForOpenFunctionWDefaultParamsIfNeeded", "wrapper", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableDefaultParamLowering extends AbstractComposeLowering {
    private final Map<IrSimpleFunction, IrSimpleFunction> originalToTransformed;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableDefaultParamLowering(IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
        this.originalToTransformed = new LinkedHashMap();
    }

    private final IrSimpleFunction findOverriddenFunWithDefaultParam(IrSimpleFunction irSimpleFunction) {
        if (this.originalToTransformed.containsKey(irSimpleFunction) || isVirtualFunctionWithDefaultParam(irSimpleFunction)) {
            return irSimpleFunction;
        }
        Iterator it = irSimpleFunction.getOverriddenSymbols().iterator();
        while (it.hasNext()) {
            IrSimpleFunction irSimpleFunctionFindOverriddenFunWithDefaultParam = findOverriddenFunWithDefaultParam((IrSimpleFunction) ((IrSimpleFunctionSymbol) it.next()).getOwner());
            if (irSimpleFunctionFindOverriddenFunWithDefaultParam != null) {
                return irSimpleFunctionFindOverriddenFunWithDefaultParam;
            }
        }
        return null;
    }

    private final IrClass getOrCreateDefaultImpls(IrClass parent) {
        Object next;
        Iterator it = parent.getDeclarations().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            IrClass irClass = (IrDeclaration) next;
            if ((irClass instanceof IrClass) && Intrinsics.areEqual(irClass.getName(), ComposeNames.INSTANCE.getDefaultImpls())) {
                break;
            }
        }
        IrClass irClass2 = next instanceof IrClass ? (IrClass) next : null;
        if (irClass2 != null) {
            return irClass2;
        }
        IrFactory irFactory = getContext().getIrFactory();
        IrClassBuilder irClassBuilder = new IrClassBuilder();
        irClassBuilder.setStartOffset(parent.getStartOffset());
        irClassBuilder.setEndOffset(parent.getEndOffset());
        irClassBuilder.setName(ComposeNames.INSTANCE.getDefaultImpls());
        IrClass irClassBuildClass = DeclarationBuildersKt.buildClass(irFactory, irClassBuilder);
        IrUtilsKt.addChild(parent, irClassBuildClass);
        IrUtilsKt.createThisReceiverParameter(irClassBuildClass);
        return irClassBuildClass;
    }

    private final boolean isVirtualFunction(IrSimpleFunction irSimpleFunction) {
        byte[] bArrM276getComposeMetadatafNhgvTQ;
        if (irSimpleFunction.getModality() != Modality.ABSTRACT) {
            if (irSimpleFunction.getModality() != Modality.OPEN) {
                return false;
            }
            if (Intrinsics.areEqual(irSimpleFunction.getOrigin(), IrDeclarationOrigin.Companion.getIR_EXTERNAL_DECLARATION_STUB()) && ((bArrM276getComposeMetadatafNhgvTQ = m276getComposeMetadatafNhgvTQ(irSimpleFunction)) == null || !ComposeMetadata.m272supportsOpenFunctionsWithDefaultParamsimpl(bArrM276getComposeMetadatafNhgvTQ))) {
                return false;
            }
        }
        return true;
    }

    private final boolean isVirtualFunctionWithDefaultParam(IrSimpleFunction irSimpleFunction) {
        if (!hasComposableAnnotation(irSimpleFunction) || irSimpleFunction.isExpect() || !isVirtualFunction(irSimpleFunction) || !irSimpleFunction.getOverriddenSymbols().isEmpty()) {
            return false;
        }
        List parameters = irSimpleFunction.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            return false;
        }
        Iterator it = parameters.iterator();
        while (it.hasNext()) {
            if (((IrValueParameter) it.next()).getDefaultValue() != null) {
                return true;
            }
        }
        return false;
    }

    private final IrSimpleFunction makeDefaultParameterWrapper(IrSimpleFunction source) {
        IrFactory irFactory = getContext().getIrFactory();
        int startOffset = source.getStartOffset();
        int endOffset = source.getEndOffset();
        IrDeclarationOrigin defined = IrDeclarationOrigin.Companion.getDEFINED();
        Name nameIdentifier = Name.identifier(source.getName().asString() + "$default");
        nameIdentifier.getClass();
        DescriptorVisibility visibility = source.getVisibility().isPublicAPI() ? DescriptorVisibilities.PUBLIC : source.getVisibility();
        visibility.getClass();
        IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactory, startOffset, endOffset, defined, nameIdentifier, visibility, false, false, source.getReturnType(), Modality.FINAL, new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), source.isTailrec(), false, false, false, false, (DeserializedContainerSource) null, false, 114688, (Object) null);
        IrUtilsKt.copyAnnotationsFrom(irSimpleFunctionCreateSimpleFunction$default, source);
        AbstractComposeLowering.copyParametersFrom$org_jetbrains_kotlin_kotlin_compose_compiler_plugin$default(this, irSimpleFunctionCreateSimpleFunction$default, source, false, 2, null);
        Iterator it = irSimpleFunctionCreateSimpleFunction$default.getParameters().iterator();
        while (it.hasNext()) {
            IrExpressionBody defaultValue = ((IrValueParameter) it.next()).getDefaultValue();
            if (defaultValue != null) {
                transformChildrenVoid(defaultValue);
            }
        }
        DeclarationIrBuilder declarationIrBuilder = new DeclarationIrBuilder(getContext(), irSimpleFunctionCreateSimpleFunction$default.getSymbol(), 0, 0, 12, (DefaultConstructorMarker) null);
        IrBlockBodyBuilder irBlockBodyBuilder = new IrBlockBodyBuilder(declarationIrBuilder.getContext(), declarationIrBuilder.getScope(), declarationIrBuilder.getStartOffset(), declarationIrBuilder.getEndOffset());
        IrSimpleFunctionSymbol symbol = irSimpleFunctionCreateSimpleFunction$default.getSymbol();
        IrCallImpl irCallImplIrCall = irCall(source.getSymbol());
        List parameters = irSimpleFunctionCreateSimpleFunction$default.getParameters();
        int size = parameters.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            IrValueParameter irValueParameter = (IrValueParameter) parameters.get(i2);
            irCallImplIrCall.getArguments().set(irValueParameter.getIndexInParameters(), irGet(irValueParameter));
        }
        Unit unit = Unit.INSTANCE;
        irBlockBodyBuilder.unaryPlus(AbstractComposeLowering.irReturn$default(this, symbol, irCallImplIrCall, null, 4, null));
        irSimpleFunctionCreateSimpleFunction$default.setBody(irBlockBodyBuilder.doBuild());
        Iterator it2 = irSimpleFunctionCreateSimpleFunction$default.getParameters().iterator();
        while (true) {
            if (!it2.hasNext()) {
                i = -1;
                break;
            }
            if (((IrValueParameter) it2.next()).getKind() == IrParameterKind.Regular) {
                break;
            }
            i++;
        }
        List listTake = CollectionsKt.take(irSimpleFunctionCreateSimpleFunction$default.getParameters(), i);
        Iterator it3 = listTake.iterator();
        while (it3.hasNext()) {
            ((IrValueParameter) it3.next()).setKind(IrParameterKind.Regular);
        }
        irSimpleFunctionCreateSimpleFunction$default.setParameters(CollectionsKt.plus(CollectionsKt.drop(irSimpleFunctionCreateSimpleFunction$default.getParameters(), i), listTake));
        return irSimpleFunctionCreateSimpleFunction$default;
    }

    private final IrSimpleFunction makeStubForOpenFunctionWDefaultParamsIfNeeded(IrSimpleFunction irSimpleFunction, final IrSimpleFunction irSimpleFunction2) {
        if (!irSimpleFunction.getVisibility().isPublicAPI() && !IrUtilsKt.isPublishedApi(irSimpleFunction)) {
            return null;
        }
        final IrSimpleFunction irSimpleFunctionMakeStub = makeStub(irSimpleFunction);
        this.originalToTransformed.put(irSimpleFunctionMakeStub, irSimpleFunction2);
        List parameters = irSimpleFunctionMakeStub.getParameters();
        int size = parameters.size();
        for (int i = 0; i < size; i++) {
            IrValueParameter irValueParameter = (IrValueParameter) parameters.get(i);
            if (irValueParameter.getDefaultValue() != null) {
                irValueParameter.setDefaultValue(irSimpleFunction.getFactory().createExpressionBody(-1, -1, irCall(getBuiltIns().getThrowIseSymbol())));
            }
        }
        irSimpleFunctionMakeStub.setBody(IrFactoryHelpersKt.createBlockBody(getContext().getIrFactory(), -1, -1, new Function1() { // from class: mb2
            public final Object invoke(Object obj) {
                return ComposableDefaultParamLowering.makeStubForOpenFunctionWDefaultParamsIfNeeded$lambda$0$1(this.b, irSimpleFunctionMakeStub, irSimpleFunction2, (IrBlockBody) obj);
            }
        }));
        return irSimpleFunctionMakeStub;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit makeStubForOpenFunctionWDefaultParamsIfNeeded$lambda$0$1(ComposableDefaultParamLowering composableDefaultParamLowering, IrSimpleFunction irSimpleFunction, IrSimpleFunction irSimpleFunction2, IrBlockBody irBlockBody) {
        irBlockBody.getClass();
        List statements = irBlockBody.getStatements();
        IrSimpleFunctionSymbol symbol = irSimpleFunction.getSymbol();
        IrCall irCallIrCall$default = AbstractComposeLowering.irCall$default(composableDefaultParamLowering, irSimpleFunction2, 0, 0, 6, null);
        List parameters = irSimpleFunction.getParameters();
        int size = parameters.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            IrValueParameter irValueParameter = (IrValueParameter) parameters.get(i2);
            if (irValueParameter.getKind() == IrParameterKind.Regular) {
                irCallIrCall$default.getArguments().set(i, composableDefaultParamLowering.irGet(irValueParameter));
                i++;
            }
        }
        List parameters2 = irSimpleFunction.getParameters();
        int size2 = parameters2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            IrValueParameter irValueParameter2 = (IrValueParameter) parameters2.get(i3);
            if (irValueParameter2.getKind() == IrParameterKind.DispatchReceiver) {
                irCallIrCall$default.getArguments().set(i, composableDefaultParamLowering.irGet(irValueParameter2));
                i++;
            }
        }
        List parameters3 = irSimpleFunction.getParameters();
        int size3 = parameters3.size();
        for (int i4 = 0; i4 < size3; i4++) {
            IrValueParameter irValueParameter3 = (IrValueParameter) parameters3.get(i4);
            if (irValueParameter3.getKind() == IrParameterKind.Context) {
                irCallIrCall$default.getArguments().set(i, composableDefaultParamLowering.irGet(irValueParameter3));
                i++;
            }
        }
        List parameters4 = irSimpleFunction.getParameters();
        int size4 = parameters4.size();
        for (int i5 = 0; i5 < size4; i5++) {
            IrValueParameter irValueParameter4 = (IrValueParameter) parameters4.get(i5);
            if (irValueParameter4.getKind() == IrParameterKind.ExtensionReceiver) {
                irCallIrCall$default.getArguments().set(i, composableDefaultParamLowering.irGet(irValueParameter4));
                i++;
            }
        }
        Unit unit = Unit.INSTANCE;
        statements.add(composableDefaultParamLowering.irReturn(symbol, irCallIrCall$default, irSimpleFunction.getReturnType()));
        return Unit.INSTANCE;
    }

    private final IrSimpleFunction transformIfNeeded(IrSimpleFunction irSimpleFunction) {
        if (this.originalToTransformed.containsKey(irSimpleFunction)) {
            IrSimpleFunction irSimpleFunction2 = this.originalToTransformed.get(irSimpleFunction);
            irSimpleFunction2.getClass();
            return irSimpleFunction2;
        }
        transformChildrenVoid(irSimpleFunction);
        IrSimpleFunction irSimpleFunctionMakeDefaultParameterWrapper = makeDefaultParameterWrapper(irSimpleFunction);
        this.originalToTransformed.put(irSimpleFunction, irSimpleFunctionMakeDefaultParameterWrapper);
        this.originalToTransformed.put(irSimpleFunctionMakeDefaultParameterWrapper, irSimpleFunctionMakeDefaultParameterWrapper);
        IrDeclarationParent parent = irSimpleFunction.getParent();
        if (!(parent instanceof IrClass)) {
            w04.a("Cannot add wrapper function to ", parent);
            return null;
        }
        IrUtilsKt.addChild(getOrCreateDefaultImpls((IrClass) parent), irSimpleFunctionMakeDefaultParameterWrapper);
        ComposePluginAttributesKt.setVirtualFunctionWithDefaultParam(irSimpleFunction, Boolean.TRUE);
        if (irSimpleFunction.getModality() == Modality.OPEN && Intrinsics.areEqual(irSimpleFunction.getOrigin(), IrDeclarationOrigin.Companion.getDEFINED())) {
            m277setComposeMetadataIvR2BlU(irSimpleFunction, ComposeMetadata.m267constructorimpl(LanguageVersion.LATEST_STABLE));
            IrSimpleFunction irSimpleFunctionMakeStubForOpenFunctionWDefaultParamsIfNeeded = makeStubForOpenFunctionWDefaultParamsIfNeeded(irSimpleFunction, irSimpleFunctionMakeDefaultParameterWrapper);
            if (irSimpleFunctionMakeStubForOpenFunctionWDefaultParamsIfNeeded != null) {
                IrClass parent2 = irSimpleFunction.getParent();
                IrClass irClass = parent2 instanceof IrClass ? parent2 : null;
                if (irClass != null) {
                    IrUtilsKt.addChild(irClass, irSimpleFunctionMakeStubForOpenFunctionWDefaultParamsIfNeeded);
                }
            }
        }
        Iterator it = irSimpleFunction.getParameters().iterator();
        while (it.hasNext()) {
            ((IrValueParameter) it.next()).setDefaultValue((IrExpressionBody) null);
        }
        return irSimpleFunctionMakeDefaultParameterWrapper;
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        transformChildrenVoid(irModule);
    }

    public IrExpression visitCall(IrCall expression) {
        expression.getClass();
        if (expression.getSuperQualifierSymbol() != null) {
            return super.visitCall(expression);
        }
        IrSimpleFunction owner = expression.getSymbol().getOwner();
        if (!hasComposableAnnotation(owner)) {
            return super.visitCall(expression);
        }
        IrSimpleFunction irSimpleFunctionFindOverriddenFunWithDefaultParam = findOverriddenFunWithDefaultParam(owner);
        IrSimpleFunction irSimpleFunctionTransformIfNeeded = irSimpleFunctionFindOverriddenFunWithDefaultParam != null ? transformIfNeeded(irSimpleFunctionFindOverriddenFunWithDefaultParam) : null;
        if (irSimpleFunctionTransformIfNeeded == null) {
            return super.visitCall(expression);
        }
        IrCall irCall = irCall(irSimpleFunctionTransformIfNeeded, expression.getStartOffset(), expression.getEndOffset());
        Iterator it = expression.getSymbol().getOwner().getParameters().iterator();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            if (((IrValueParameter) it.next()).getKind() == IrParameterKind.Regular) {
                break;
            }
            i2++;
        }
        int size = expression.getArguments().size();
        int i3 = 0;
        int i4 = i2;
        while (i4 < size) {
            irCall.getArguments().set(i3, expression.getArguments().get(i4));
            i4++;
            i3++;
        }
        while (i < i2) {
            irCall.getArguments().set(i3, expression.getArguments().get(i));
            i++;
            i3++;
        }
        return super.visitCall(irCall);
    }

    public IrStatement visitSimpleFunction(IrSimpleFunction declaration) {
        declaration.getClass();
        if (this.originalToTransformed.containsKey(declaration)) {
            return declaration;
        }
        if (isVirtualFunctionWithDefaultParam(declaration)) {
            transformIfNeeded(declaration);
            return declaration;
        }
        IrSimpleFunction irSimpleFunctionFindOverriddenFunWithDefaultParam = findOverriddenFunWithDefaultParam(declaration);
        if (irSimpleFunctionFindOverriddenFunWithDefaultParam != null && Intrinsics.areEqual(irSimpleFunctionFindOverriddenFunWithDefaultParam.getOrigin(), IrDeclarationOrigin.Companion.getIR_EXTERNAL_DECLARATION_STUB())) {
            ComposePluginAttributesKt.setVirtualFunctionWithDefaultParam(irSimpleFunctionFindOverriddenFunWithDefaultParam, Boolean.TRUE);
        }
        return super.visitSimpleFunction(declaration);
    }
}

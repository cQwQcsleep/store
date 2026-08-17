package org.jetbrains.kotlin.fir.lightTree.fir;

import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilderKt;
import org.jetbrains.kotlin.fir.builder.Context;
import org.jetbrains.kotlin.fir.builder.ConversionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeContextParameterWithDefaultValue;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.lightTree.fir.ValueParameter;
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList;
import org.jetbrains.kotlin.fir.references.builder.FirPropertyFromParameterResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\n\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\f\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001b\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ\u0006\u0010*\u001a\u00020\u0005J<\u00105\u001a\u000206\"\u0004\b\u0000\u001072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u00052\b\u0010;\u001a\u0004\u0018\u00010<2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H70>R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R!\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00150\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b,\u0010-R\u001b\u00100\u001a\u0002018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b4\u0010/\u001a\u0004\b2\u00103¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/ValueParameter;", Argument.Delimiters.none, "valueParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "isVal", Argument.Delimiters.none, "isVar", "modifiers", "Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierList;", "valueParameterAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "isFromPrimaryConstructor", "isContextParameter", "additionalAnnotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "defaultValue", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "destructuringDeclaration", "Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;ZZLorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierList;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/FirModuleData;ZZLjava/util/List;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringDeclaration;)V", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getDefaultValue", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getDestructuringDeclaration", "()Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringDeclaration;", "hasValOrVar", "annotations", "getAnnotations", "()Ljava/util/List;", "annotations$delegate", "Lkotlin/Lazy;", "firValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getFirValueParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "firValueParameter$delegate", "toFirPropertyFromPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "T", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "isExpect", "currentDispatchReceiver", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "context", "Lorg/jetbrains/kotlin/fir/builder/Context;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ValueParameter {
    private final List<FirAnnotation> additionalAnnotations;

    /* JADX INFO: renamed from: annotations$delegate, reason: from kotlin metadata */
    private final Lazy annotations;
    private final FirBasedSymbol<?> containingDeclarationSymbol;
    private final FirExpression defaultValue;
    private final DestructuringDeclaration destructuringDeclaration;

    /* JADX INFO: renamed from: firValueParameter$delegate, reason: from kotlin metadata */
    private final Lazy firValueParameter;
    private final boolean isContextParameter;
    private final boolean isFromPrimaryConstructor;
    private final boolean isVal;
    private final boolean isVar;
    private final ModifierList modifiers;
    private final FirModuleData moduleData;
    private final Name name;
    private final FirTypeRef returnTypeRef;
    private final KtSourceElement source;
    private final List<FirAnnotationCall> valueParameterAnnotations;
    private final FirValueParameterSymbol valueParameterSymbol;

    /* JADX WARN: Multi-variable type inference failed */
    public ValueParameter(FirValueParameterSymbol firValueParameterSymbol, boolean z, boolean z2, ModifierList modifierList, List<? extends FirAnnotationCall> list, FirTypeRef firTypeRef, KtSourceElement ktSourceElement, FirModuleData firModuleData, boolean z3, boolean z4, List<? extends FirAnnotation> list2, Name name, FirExpression firExpression, FirBasedSymbol<?> firBasedSymbol, DestructuringDeclaration destructuringDeclaration) {
        firValueParameterSymbol.getClass();
        modifierList.getClass();
        list.getClass();
        firTypeRef.getClass();
        ktSourceElement.getClass();
        firModuleData.getClass();
        list2.getClass();
        name.getClass();
        this.valueParameterSymbol = firValueParameterSymbol;
        this.isVal = z;
        this.isVar = z2;
        this.modifiers = modifierList;
        this.valueParameterAnnotations = list;
        this.returnTypeRef = firTypeRef;
        this.source = ktSourceElement;
        this.moduleData = firModuleData;
        this.isFromPrimaryConstructor = z3;
        this.isContextParameter = z4;
        this.additionalAnnotations = list2;
        this.name = name;
        this.defaultValue = firExpression;
        this.containingDeclarationSymbol = firBasedSymbol;
        this.destructuringDeclaration = destructuringDeclaration;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.annotations = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: q9f
            public final Object invoke() {
                return ValueParameter.b(this.b);
            }
        });
        this.firValueParameter = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: r9f
            public final Object invoke() {
                return ValueParameter.a(this.b);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0030  */
    public static FirValueParameter a(ValueParameter valueParameter) {
        FirResolvedTypeRef firResolvedTypeRefWrapIntoArray;
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setSource(valueParameter.source);
        firValueParameterBuilder.setModuleData(valueParameter.moduleData);
        firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firValueParameterBuilder.setVararg(valueParameter.modifiers.hasVararg());
        if (firValueParameterBuilder.getIsVararg()) {
            Object obj = valueParameter.returnTypeRef;
            if (obj instanceof FirErrorTypeRef) {
                firResolvedTypeRefWrapIntoArray = ConversionUtilsKt.wrapIntoArray((FirErrorTypeRef) obj);
            } else {
                firResolvedTypeRefWrapIntoArray = valueParameter.returnTypeRef;
            }
        } else {
            firResolvedTypeRefWrapIntoArray = valueParameter.returnTypeRef;
        }
        firValueParameterBuilder.setReturnTypeRef(firResolvedTypeRefWrapIntoArray);
        firValueParameterBuilder.setName(valueParameter.name);
        firValueParameterBuilder.setSymbol(valueParameter.valueParameterSymbol);
        FirExpression firExpressionMo289build = valueParameter.defaultValue;
        if (firExpressionMo289build == null) {
            firExpressionMo289build = null;
        } else if (valueParameter.isContextParameter) {
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder.setSource(KtSourceElementKt.fakeElement$default(valueParameter.source, KtFakeSourceElementKind.ContextParameterDefaultValue.INSTANCE, null, 2, null));
            firErrorExpressionBuilder.setDiagnostic(ConeContextParameterWithDefaultValue.INSTANCE);
            firExpressionMo289build = firErrorExpressionBuilder.mo289build();
        }
        firValueParameterBuilder.setDefaultValue(firExpressionMo289build);
        firValueParameterBuilder.setCrossinline(valueParameter.modifiers.hasCrossinline());
        firValueParameterBuilder.setNoinline(valueParameter.modifiers.hasNoinline());
        firValueParameterBuilder.setValueParameterKind(valueParameter.isContextParameter ? FirValueParameterKind.ContextParameter : FirValueParameterKind.Regular);
        FirBasedSymbol<?> firBasedSymbol = valueParameter.containingDeclarationSymbol;
        if (firBasedSymbol == null) {
            k2d.a("containingFunctionSymbol should present when converting ValueParameter to a FirValueParameter");
            return null;
        }
        firValueParameterBuilder.setContainingDeclarationSymbol(firBasedSymbol);
        CollectionsKt.addAll(firValueParameterBuilder.getAnnotations(), valueParameter.getAnnotations());
        CollectionsKt.addAll(firValueParameterBuilder.getAnnotations(), valueParameter.additionalAnnotations);
        return firValueParameterBuilder.mo289build();
    }

    public static List b(ValueParameter valueParameter) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        boolean z = valueParameter.isFromPrimaryConstructor;
        List<FirAnnotationCall> list = valueParameter.valueParameterAnnotations;
        if (z) {
            List list2 = listCreateListBuilder;
            for (Object obj : list) {
                if (ConversionUtilsKt.appliesToPrimaryConstructorParameter(((FirAnnotationCall) obj).getUseSiteTarget())) {
                    list2.add(obj);
                }
            }
        } else {
            listCreateListBuilder.addAll(list);
        }
        listCreateListBuilder.addAll(valueParameter.additionalAnnotations);
        return CollectionsKt.build(listCreateListBuilder);
    }

    public final List<FirAnnotation> getAnnotations() {
        return (List) this.annotations.getValue();
    }

    public final FirExpression getDefaultValue() {
        return this.defaultValue;
    }

    public final DestructuringDeclaration getDestructuringDeclaration() {
        return this.destructuringDeclaration;
    }

    public final FirValueParameter getFirValueParameter() {
        return (FirValueParameter) this.firValueParameter.getValue();
    }

    public final Name getName() {
        return this.name;
    }

    public final FirTypeRef getReturnTypeRef() {
        return this.returnTypeRef;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final boolean hasValOrVar() {
        return this.isVal || this.isVar;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final <T> FirProperty toFirPropertyFromPrimaryConstructor(FirModuleData moduleData, CallableId callableId, boolean isExpect, ConeClassLikeType currentDispatchReceiver, Context<T> context) throws UninitializedPropertyAccessException {
        moduleData.getClass();
        callableId.getClass();
        context.getClass();
        Name name = getFirValueParameter().getName();
        FirResolvedTypeRef returnTypeRef = getFirValueParameter().getReturnTypeRef();
        if (returnTypeRef instanceof FirImplicitTypeRef) {
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Incomplete code"));
            returnTypeRef = firErrorTypeRefBuilder.build();
        }
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        KtSourceElement source = getFirValueParameter().getSource();
        FirDefaultPropertySetter firDefaultPropertySetter = null;
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE, null, 2, null) : null;
        firPropertyBuilder.setSource(ktSourceElementFakeElement$default);
        firPropertyBuilder.setModuleData(moduleData);
        firPropertyBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firPropertyBuilder.setReturnTypeRef(UtilsKt.copyWithNewSourceKind(returnTypeRef, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE));
        firPropertyBuilder.setName(name);
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(ktSourceElementFakeElement$default);
        FirPropertyFromParameterResolvedNamedReferenceBuilder firPropertyFromParameterResolvedNamedReferenceBuilder = new FirPropertyFromParameterResolvedNamedReferenceBuilder();
        firPropertyFromParameterResolvedNamedReferenceBuilder.setSource(ktSourceElementFakeElement$default);
        firPropertyFromParameterResolvedNamedReferenceBuilder.setName(name);
        firPropertyFromParameterResolvedNamedReferenceBuilder.setResolvedSymbol(getFirValueParameter().getSymbol());
        firPropertyFromParameterResolvedNamedReferenceBuilder.setSource(ktSourceElementFakeElement$default);
        firPropertyAccessExpressionBuilder.setCalleeReference(firPropertyFromParameterResolvedNamedReferenceBuilder.build());
        firPropertyBuilder.setInitializer(firPropertyAccessExpressionBuilder.mo289build());
        firPropertyBuilder.setVar(this.isVar);
        FirRegularPropertySymbol firRegularPropertySymbol = new FirRegularPropertySymbol(callableId);
        List<FirAnnotationCall> list = this.valueParameterAnnotations;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (FirAnnotationCall firAnnotationCall : list) {
            FirAnnotationCallBuilder firAnnotationCallBuilder = new FirAnnotationCallBuilder();
            firAnnotationCallBuilder.setSource(firAnnotationCall.getSource());
            firAnnotationCallBuilder.setUseSiteTarget(firAnnotationCall.getUseSiteTarget());
            firAnnotationCallBuilder.setAnnotationTypeRef(firAnnotationCall.getAnnotationTypeRef());
            firAnnotationCallBuilder.getTypeArguments().addAll(firAnnotationCall.getTypeArguments());
            firAnnotationCallBuilder.setArgumentList(firAnnotationCall.getArgumentList());
            firAnnotationCallBuilder.setCalleeReference(firAnnotationCall.getCalleeReference());
            firAnnotationCallBuilder.setArgumentMapping(firAnnotationCall.getArgumentMapping());
            firAnnotationCallBuilder.setAnnotationResolvePhase(firAnnotationCall.getAnnotationResolvePhase());
            firAnnotationCallBuilder.setContainingDeclarationSymbol(firAnnotationCall.getContainingDeclarationSymbol());
            firAnnotationCallBuilder.setContainingDeclarationSymbol(firRegularPropertySymbol);
            arrayList.add(firAnnotationCallBuilder.mo289build());
        }
        firPropertyBuilder.setSymbol(firRegularPropertySymbol);
        firPropertyBuilder.setDispatchReceiverType(currentDispatchReceiver);
        FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(ModifierList.getVisibility$default(this.modifiers, false, 1, null), this.modifiers.getModality(false));
        firDeclarationStatusImpl.setExpect(isExpect);
        firDeclarationStatusImpl.setActual(this.modifiers.hasActual());
        firDeclarationStatusImpl.setOverride(this.modifiers.hasOverride());
        firDeclarationStatusImpl.setConst(this.modifiers.hasConst());
        firDeclarationStatusImpl.setLateInit(this.modifiers.hasLateinit());
        firDeclarationStatusImpl.setExternal(this.modifiers.hasExternal());
        firPropertyBuilder.setStatus(firDeclarationStatusImpl);
        firPropertyBuilder.setLocal(context.getInLocalContext());
        KtSourceElement ktSourceElementFakeElement$default2 = ktSourceElementFakeElement$default != null ? KtSourceElementKt.fakeElement$default(ktSourceElementFakeElement$default, KtFakeSourceElementKind.DefaultAccessor.INSTANCE, null, 2, null) : null;
        FirDeclarationOrigin.Source source2 = FirDeclarationOrigin.Source.INSTANCE;
        ArrayList arrayList2 = new ArrayList();
        for (T t : arrayList) {
            FirAnnotationCall firAnnotationCall2 = (FirAnnotationCall) t;
            if (firAnnotationCall2.getUseSiteTarget() == AnnotationUseSiteTarget.FIELD || firAnnotationCall2.getUseSiteTarget() == AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD) {
                arrayList2.add(t);
            }
        }
        List mutableList = CollectionsKt.toMutableList(arrayList2);
        FirTypeRef returnTypeRef2 = firPropertyBuilder.getReturnTypeRef();
        KtFakeSourceElementKind.DefaultAccessor defaultAccessor = KtFakeSourceElementKind.DefaultAccessor.INSTANCE;
        FirTypeRef firTypeRefCopyWithNewSourceKind = UtilsKt.copyWithNewSourceKind(returnTypeRef2, defaultAccessor);
        boolean zIsVar = firPropertyBuilder.isVar();
        FirPropertySymbol symbol = firPropertyBuilder.getSymbol();
        FirDeclarationStatus status = firPropertyBuilder.getStatus();
        KtSourceElement ktSourceElement = ktSourceElementFakeElement$default2;
        firPropertyBuilder.setBackingField(new FirDefaultPropertyBackingField(moduleData, source2, ktSourceElementFakeElement$default2, mutableList, firTypeRefCopyWithNewSourceKind, zIsVar, symbol, UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : null, (8388575 & 2) != 0 ? status.getModality() : null, (8388575 & 4) != 0 ? status.isExpect() : false, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null), null, 256, null));
        CollectionsKt.addAll(firPropertyBuilder.getAnnotations(), AbstractRawFirBuilderKt.filterConstructorPropertyRelevantAnnotations(arrayList, firPropertyBuilder.isVar()));
        FirDeclarationOrigin.Source source3 = FirDeclarationOrigin.Source.INSTANCE;
        FirDefaultPropertyGetter firDefaultPropertyGetter = new FirDefaultPropertyGetter(ktSourceElement, moduleData, source3, UtilsKt.copyWithNewSourceKind(returnTypeRef, defaultAccessor), firPropertyBuilder.getStatus().getVisibility(), firPropertyBuilder.getSymbol(), firPropertyBuilder.getStatus().getModality(), null, this.modifiers.hasInline(), false, null, null, null, 7808, null);
        ConversionUtilsKt.initContainingClassAttr(firDefaultPropertyGetter, context);
        firDefaultPropertyGetter.replaceAnnotations(ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.PROPERTY_GETTER));
        firPropertyBuilder.setGetter(firDefaultPropertyGetter);
        if (firPropertyBuilder.isVar()) {
            FirDefaultPropertySetter firDefaultPropertySetter2 = new FirDefaultPropertySetter(ktSourceElement, moduleData, source3, UtilsKt.copyWithNewSourceKind(returnTypeRef, defaultAccessor), firPropertyBuilder.getStatus().getVisibility(), firPropertyBuilder.getSymbol(), firPropertyBuilder.getStatus().getModality(), null, this.modifiers.hasInline(), false, null, null, ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.SETTER_PARAMETER), null, null, 28288, null);
            ConversionUtilsKt.initContainingClassAttr(firDefaultPropertySetter2, context);
            firDefaultPropertySetter2.replaceAnnotations(ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.PROPERTY_SETTER));
            firDefaultPropertySetter = firDefaultPropertySetter2;
        }
        firPropertyBuilder.setSetter(firDefaultPropertySetter);
        FirProperty firPropertyMo289build = firPropertyBuilder.mo289build();
        if (getFirValueParameter().getIsVararg()) {
            DeclarationAttributesKt.setFromVararg(firPropertyMo289build, Boolean.TRUE);
        }
        ClassMembersKt.setCorrespondingProperty(getFirValueParameter(), firPropertyMo289build);
        DeclarationAttributesKt.setFromPrimaryConstructor(firPropertyMo289build, Boolean.TRUE);
        return firPropertyMo289build;
    }

    public /* synthetic */ ValueParameter(FirValueParameterSymbol firValueParameterSymbol, boolean z, boolean z2, ModifierList modifierList, List list, FirTypeRef firTypeRef, KtSourceElement ktSourceElement, FirModuleData firModuleData, boolean z3, boolean z4, List list2, Name name, FirExpression firExpression, FirBasedSymbol firBasedSymbol, DestructuringDeclaration destructuringDeclaration, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firValueParameterSymbol, z, z2, modifierList, list, firTypeRef, ktSourceElement, firModuleData, z3, z4, list2, name, firExpression, firBasedSymbol, (i & 16384) != 0 ? null : destructuringDeclaration);
    }
}

package org.jetbrains.kotlin.fir.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirThisReceiverExpressionBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirImplicitThisReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¾\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002\"\b\b\u0002\u0010\u0004*\u0002H\u0002*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00032\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u00142\u001d\u0010\u0017\u001a\u0019\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00190\u0014¢\u0006\u0002\b\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00100\u001c¢\u0006\u0002\u0010\u001d\u001a\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\f*\b\u0012\u0004\u0012\u00020\u001f0\f2\u0006\u0010 \u001a\u00020\u0010\u001a\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\f*\b\u0012\u0004\u0012\u00020\u001f0\f2\u0006\u0010 \u001a\u00020\u0010¨\u0006\""}, d2 = {"createDataClassCopyFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "TBase", "TSource", "TParameter", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "sourceElement", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "zippedParameters", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "isFromLibrary", Argument.Delimiters.none, "firConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "toFirSource", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "Lorg/jetbrains/kotlin/KtSourceElement;", "addValueParameterAnnotations", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirValueParameterBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "isVararg", "Lkotlin/Function1;", "(Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;Lorg/jetbrains/kotlin/name/ClassId;Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;Ljava/util/List;ZLorg/jetbrains/kotlin/fir/declarations/FirConstructor;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "filterConstructorPropertyRelevantAnnotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "isVar", "filterStandalonePropertyRelevantAnnotations", "org.jetbrains.kotlin:raw-fir.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AbstractRawFirBuilderKt {
    public static final <TBase, TSource extends TBase, TParameter extends TBase> FirNamedFunction createDataClassCopyFunction(FirRegularClassBuilder firRegularClassBuilder, ClassId classId, TSource tsource, ConeClassLikeType coneClassLikeType, List<? extends Pair<? extends TParameter, ? extends FirProperty>> list, boolean z, FirConstructor firConstructor, Function2<? super TBase, ? super KtFakeSourceElementKind, ? extends KtSourceElement> function2, Function2<? super FirValueParameterBuilder, ? super TParameter, Unit> function3, Function1<? super TParameter, Boolean> function1) {
        firRegularClassBuilder.getClass();
        classId.getClass();
        list.getClass();
        firConstructor.getClass();
        function2.getClass();
        function3.getClass();
        function1.getClass();
        FirDeclarationOrigin firDeclarationOrigin = z ? FirDeclarationOrigin.Library.INSTANCE : FirDeclarationOrigin.Synthetic.DataClassMember.INSTANCE;
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        FirTypeRef returnTypeRef = firConstructor.getReturnTypeRef();
        KtFakeSourceElementKind.DataClassGeneratedMembers dataClassGeneratedMembers = KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE;
        FirTypeRef firTypeRefCopyWithNewSourceKind = UtilsKt.copyWithNewSourceKind(returnTypeRef, dataClassGeneratedMembers);
        firNamedFunctionBuilder.setSource((KtSourceElement) function2.invoke(tsource, dataClassGeneratedMembers));
        firNamedFunctionBuilder.setModuleData(firRegularClassBuilder.getModuleData());
        firNamedFunctionBuilder.setOrigin(firDeclarationOrigin);
        firNamedFunctionBuilder.setReturnTypeRef(firTypeRefCopyWithNewSourceKind);
        Name name = StandardNames.DATA_CLASS_COPY;
        firNamedFunctionBuilder.setName(name);
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(classId.getPackageFqName(), classId.getRelativeClassName(), name)));
        firNamedFunctionBuilder.setDispatchReceiverType(coneClassLikeType);
        firNamedFunctionBuilder.setResolvePhase(firRegularClassBuilder.getResolvePhase());
        firNamedFunctionBuilder.setStatus(z ? new FirResolvedDeclarationStatusImpl(Visibilities.Unknown.INSTANCE, Modality.FINAL, EffectiveVisibility.Unknown.INSTANCE) : new FirDeclarationStatusImpl(Visibilities.Unknown.INSTANCE, Modality.FINAL));
        firNamedFunctionBuilder.setLocal(firConstructor.getIsLocal());
        for (Pair<? extends TParameter, ? extends FirProperty> pair : list) {
            Object objComponent1 = pair.component1();
            FirProperty firProperty = (FirProperty) pair.component2();
            Name name2 = firProperty.getName();
            KtFakeSourceElementKind.DataClassGeneratedMembers dataClassGeneratedMembers2 = KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE;
            KtSourceElement ktSourceElement = (KtSourceElement) function2.invoke(objComponent1, dataClassGeneratedMembers2);
            FirTypeRef firTypeRefCopyWithNewSourceKind2 = UtilsKt.copyWithNewSourceKind(firProperty.getReturnTypeRef(), dataClassGeneratedMembers2);
            List<FirValueParameter> valueParameters = firNamedFunctionBuilder.getValueParameters();
            FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
            firValueParameterBuilder.setResolvePhase(firRegularClassBuilder.getResolvePhase());
            firValueParameterBuilder.setSource(ktSourceElement);
            firValueParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
            firValueParameterBuilder.setModuleData(firRegularClassBuilder.getModuleData());
            firValueParameterBuilder.setOrigin(firDeclarationOrigin);
            firValueParameterBuilder.setReturnTypeRef(firTypeRefCopyWithNewSourceKind2);
            firValueParameterBuilder.setName(name2);
            firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
            firValueParameterBuilder.setDefaultValue(createDataClassCopyFunction$generateComponentAccess(firRegularClassBuilder, ktSourceElement, firProperty, firTypeRefCopyWithNewSourceKind, firTypeRefCopyWithNewSourceKind2));
            firValueParameterBuilder.setCrossinline(false);
            firValueParameterBuilder.setNoinline(false);
            firValueParameterBuilder.setVararg(((Boolean) function1.invoke(objComponent1)).booleanValue());
            function3.invoke(firValueParameterBuilder, objComponent1);
            Iterator<FirAnnotation> it = firValueParameterBuilder.getAnnotations().iterator();
            while (it.hasNext()) {
                it.next().replaceUseSiteTarget(null);
            }
            valueParameters.add(firValueParameterBuilder.mo288build());
        }
        return firNamedFunctionBuilder.mo288build();
    }

    private static final FirPropertyAccessExpression createDataClassCopyFunction$generateComponentAccess(FirRegularClassBuilder firRegularClassBuilder, KtSourceElement ktSourceElement, FirProperty firProperty, FirTypeRef firTypeRef, FirTypeRef firTypeRef2) {
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(ktSourceElement);
        firPropertyAccessExpressionBuilder.setConeTypeOrNull(FirTypeUtilsKt.getConeTypeOrNull(firTypeRef2));
        FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
        firThisReceiverExpressionBuilder.setSource(ktSourceElement);
        FirImplicitThisReferenceBuilder firImplicitThisReferenceBuilder = new FirImplicitThisReferenceBuilder();
        firImplicitThisReferenceBuilder.setBoundSymbol(firRegularClassBuilder.getSymbol());
        firThisReceiverExpressionBuilder.setCalleeReference(firImplicitThisReferenceBuilder.build());
        firThisReceiverExpressionBuilder.setConeTypeOrNull(FirTypeUtilsKt.getConeTypeOrNull(firTypeRef));
        firPropertyAccessExpressionBuilder.setDispatchReceiver(firThisReceiverExpressionBuilder.mo288build());
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setSource(ktSourceElement);
        firResolvedNamedReferenceBuilder.setName(firProperty.getName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(firProperty.getSymbol());
        firPropertyAccessExpressionBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
        return firPropertyAccessExpressionBuilder.mo288build();
    }

    public static final List<FirAnnotationCall> filterConstructorPropertyRelevantAnnotations(List<? extends FirAnnotationCall> list, boolean z) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            FirAnnotationCall firAnnotationCall = (FirAnnotationCall) obj;
            if (firAnnotationCall.getUseSiteTarget() == null || firAnnotationCall.getUseSiteTarget() == AnnotationUseSiteTarget.PROPERTY || firAnnotationCall.getUseSiteTarget() == AnnotationUseSiteTarget.ALL || (!z && (firAnnotationCall.getUseSiteTarget() == AnnotationUseSiteTarget.SETTER_PARAMETER || firAnnotationCall.getUseSiteTarget() == AnnotationUseSiteTarget.PROPERTY_SETTER))) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<FirAnnotationCall> filterStandalonePropertyRelevantAnnotations(List<? extends FirAnnotationCall> list, boolean z) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            FirAnnotationCall firAnnotationCall = (FirAnnotationCall) obj;
            if (firAnnotationCall.getUseSiteTarget() != AnnotationUseSiteTarget.FIELD && firAnnotationCall.getUseSiteTarget() != AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD && firAnnotationCall.getUseSiteTarget() != AnnotationUseSiteTarget.PROPERTY_GETTER && (!z || (firAnnotationCall.getUseSiteTarget() != AnnotationUseSiteTarget.SETTER_PARAMETER && firAnnotationCall.getUseSiteTarget() != AnnotationUseSiteTarget.PROPERTY_SETTER))) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}

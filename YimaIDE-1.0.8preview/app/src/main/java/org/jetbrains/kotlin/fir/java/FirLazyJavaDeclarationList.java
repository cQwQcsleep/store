package org.jetbrains.kotlin.fir.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.EnumClassUtilsKt;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.java.FirLazyJavaDeclarationList;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaConstructor;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaMethod;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameter;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaDeclarationList;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaConstructor;
import org.jetbrains.kotlin.load.java.structure.JavaField;
import org.jetbrains.kotlin.load.java.structure.JavaLoadingKt;
import org.jetbrains.kotlin.load.java.structure.JavaMethod;
import org.jetbrains.kotlin.load.java.structure.JavaPackage;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirImplementationDetail
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eÊ\u0001\u0002\b\u0012¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirLazyJavaDeclarationList;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaDeclarationList;", "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "javaPackage", "Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;", "<init>", "(Lorg/jetbrains/kotlin/load/java/structure/JavaClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;)V", "declarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getDeclarations", "()Ljava/util/List;", "declarations$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:fir-jvm", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLazyJavaDeclarationList implements FirJavaDeclarationList {

    /* JADX INFO: renamed from: declarations$delegate, reason: from kotlin metadata */
    private final Lazy declarations;

    public FirLazyJavaDeclarationList(final JavaClass javaClass, final FirRegularClassSymbol firRegularClassSymbol, final JavaPackage javaPackage) {
        javaClass.getClass();
        firRegularClassSymbol.getClass();
        this.declarations = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: na5
            public final Object invoke() {
                return FirLazyJavaDeclarationList.b(firRegularClassSymbol, javaClass, javaPackage);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static List b(FirRegularClassSymbol firRegularClassSymbol, JavaClass javaClass, JavaPackage javaPackage) throws KotlinIllegalArgumentExceptionWithAttachments {
        ArrayList arrayList;
        FirRegularClassSymbol firRegularClassSymbol2;
        final FirRegularClassSymbol firRegularClassSymbol3;
        ValueParametersForAnnotationConstructor valueParametersForAnnotationConstructor;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        E fir = firRegularClassSymbol.getFir();
        fir.getClass();
        FirJavaClass firJavaClass = (FirJavaClass) fir;
        FirClassSymbol<?> containingClassSymbol$org_jetbrains_kotlin_fir_jvm = firJavaClass.getContainingClassSymbol$org_jetbrains_kotlin_fir_jvm();
        FirRegularClassSymbol firRegularClassSymbol4 = containingClassSymbol$org_jetbrains_kotlin_fir_jvm instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingClassSymbol$org_jetbrains_kotlin_fir_jvm : null;
        MutableJavaTypeParameterStack classJavaTypeParameterStack = firJavaClass.getClassJavaTypeParameterStack();
        FirModuleData moduleData = firJavaClass.getModuleData();
        final FirSession session = moduleData.getSession();
        ClassId classId = firRegularClassSymbol.getClassId();
        List<FirTypeParameterRef> typeParameters = firJavaClass.getTypeParameters();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : typeParameters) {
            if (obj instanceof FirTypeParameter) {
                arrayList4.add(obj);
            }
        }
        ClassKind classKind = firJavaClass.getClassKind();
        FirResolvedDeclarationStatusImpl originalStatus = firJavaClass.getOriginalStatus();
        FirResolvePhase resolvePhase = FirResolveStateKt.getResolvePhase(firJavaClass);
        KtSourceElement source = firJavaClass.getSource();
        ValueParametersForAnnotationConstructor valueParametersForAnnotationConstructor2 = new ValueParametersForAnnotationConstructor();
        boolean z = classKind == ClassKind.ANNOTATION_CLASS;
        ConeClassLikeType coneClassLikeTypeDefaultType = ScopeUtilsKt.defaultType(firJavaClass);
        Iterator it = javaClass.getFields().iterator();
        while (it.hasNext()) {
            arrayList3.add(FirJavaFacadeKt.convertJavaFieldToFir((JavaField) it.next(), classId, classJavaTypeParameterStack, coneClassLikeTypeDefaultType, moduleData, firRegularClassSymbol));
        }
        for (JavaMethod javaMethod : javaClass.getMethods()) {
            if (!JavaLoadingKt.isObjectMethodInInterface(javaMethod)) {
                ClassId classId2 = classId;
                FirRegularClassSymbol firRegularClassSymbol5 = firRegularClassSymbol4;
                FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = originalStatus;
                ValueParametersForAnnotationConstructor valueParametersForAnnotationConstructor3 = valueParametersForAnnotationConstructor2;
                FirJavaMethod firJavaMethodConvertJavaMethodToFir = FirJavaFacadeKt.convertJavaMethodToFir(javaClass, javaMethod, classId2, coneClassLikeTypeDefaultType, moduleData, firRegularClassSymbol, javaPackage);
                ConeClassLikeType coneClassLikeType = coneClassLikeTypeDefaultType;
                arrayList3.add(firJavaMethodConvertJavaMethodToFir);
                if (z) {
                    FirJavaValueParameter firJavaValueParameterConvertJavaAnnotationMethodToValueParameter = FirJavaFacadeKt.convertJavaAnnotationMethodToValueParameter(javaMethod, firJavaMethodConvertJavaMethodToFir, moduleData);
                    if (Intrinsics.areEqual(javaMethod.getName(), FirJavaFacade.INSTANCE.getVALUE_METHOD_NAME())) {
                        valueParametersForAnnotationConstructor3.setValueParameterForValue(TuplesKt.to(javaMethod, firJavaValueParameterConvertJavaAnnotationMethodToValueParameter));
                    } else {
                        valueParametersForAnnotationConstructor3.getValueParameters().put(javaMethod, firJavaValueParameterConvertJavaAnnotationMethodToValueParameter);
                    }
                }
                valueParametersForAnnotationConstructor2 = valueParametersForAnnotationConstructor3;
                originalStatus = firResolvedDeclarationStatusImpl;
                firRegularClassSymbol4 = firRegularClassSymbol5;
                classId = classId2;
                coneClassLikeTypeDefaultType = coneClassLikeType;
            }
        }
        ClassId classId3 = classId;
        ConeClassLikeType coneClassLikeType2 = coneClassLikeTypeDefaultType;
        FirRegularClassSymbol firRegularClassSymbol6 = firRegularClassSymbol4;
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl2 = originalStatus;
        ValueParametersForAnnotationConstructor valueParametersForAnnotationConstructor4 = valueParametersForAnnotationConstructor2;
        Collection constructors = javaClass.getConstructors();
        CallableId callableId = new CallableId(classId3.getPackageFqName(), classId3.getRelativeClassName(), classId3.getShortClassName());
        if (constructors.isEmpty() && classKind == ClassKind.CLASS && !javaClass.isRecord() && javaClass.hasDefaultConstructor()) {
            firRegularClassSymbol2 = firRegularClassSymbol6;
            FirJavaConstructor firJavaConstructorConvertJavaConstructorToFir = FirJavaFacadeKt.convertJavaConstructorToFir(null, callableId, javaClass, firRegularClassSymbol, arrayList4, firRegularClassSymbol2, moduleData, javaPackage);
            arrayList = arrayList4;
            moduleData = moduleData;
            arrayList3.add(firJavaConstructorConvertJavaConstructorToFir);
        } else {
            arrayList = arrayList4;
            firRegularClassSymbol2 = firRegularClassSymbol6;
        }
        Iterator it2 = constructors.iterator();
        while (it2.hasNext()) {
            FirModuleData firModuleData = moduleData;
            ArrayList arrayList5 = arrayList;
            CallableId callableId2 = callableId;
            FirJavaConstructor firJavaConstructorConvertJavaConstructorToFir2 = FirJavaFacadeKt.convertJavaConstructorToFir((JavaConstructor) it2.next(), callableId2, javaClass, firRegularClassSymbol, arrayList5, firRegularClassSymbol2, firModuleData, javaPackage);
            moduleData = firModuleData;
            arrayList3.add(firJavaConstructorConvertJavaConstructorToFir2);
            callableId = callableId2;
            arrayList = arrayList5;
        }
        ArrayList arrayList6 = arrayList;
        CallableId callableId3 = callableId;
        if (classKind == ClassKind.ENUM_CLASS) {
            FirDeclarationOrigin firDeclarationOrigin = firJavaClass.getOrigin().getFromSource() ? FirDeclarationOrigin.Java.Source.INSTANCE : FirDeclarationOrigin.Java.Library.INSTANCE;
            valueParametersForAnnotationConstructor = valueParametersForAnnotationConstructor4;
            arrayList3.add(EnumClassUtilsKt.generateValuesFunction(firRegularClassSymbol, source, firResolvedDeclarationStatusImpl2, resolvePhase, moduleData, classId3.getPackageFqName(), classId3.getRelativeClassName(), (128 & 128) != 0 ? false : false, (128 & 256) != 0 ? FirDeclarationOrigin.Source.INSTANCE : firDeclarationOrigin));
            arrayList3.add(EnumClassUtilsKt.generateValueOfFunction(firRegularClassSymbol, source, firResolvedDeclarationStatusImpl2, resolvePhase, moduleData, classId3.getPackageFqName(), classId3.getRelativeClassName(), (128 & 128) != 0 ? false : false, (128 & 256) != 0 ? FirDeclarationOrigin.Source.INSTANCE : firDeclarationOrigin));
            firRegularClassSymbol3 = firRegularClassSymbol;
            arrayList3.add(EnumClassUtilsKt.generateEntriesGetter(firRegularClassSymbol3, source, firResolvedDeclarationStatusImpl2, resolvePhase, moduleData, classId3.getPackageFqName(), classId3.getRelativeClassName(), (128 & 128) != 0 ? false : false, (128 & 256) != 0 ? FirDeclarationOrigin.Source.INSTANCE : firJavaClass.getOrigin().getFromSource() ? FirDeclarationOrigin.Source.INSTANCE : FirDeclarationOrigin.Library.INSTANCE));
        } else {
            firRegularClassSymbol3 = firRegularClassSymbol;
            valueParametersForAnnotationConstructor = valueParametersForAnnotationConstructor4;
        }
        if (z) {
            arrayList3.add(FirJavaFacadeKt.buildConstructorForAnnotationClass(javaClass, callableId3, firRegularClassSymbol3, valueParametersForAnnotationConstructor, moduleData));
        }
        if (javaClass.isRecord() && javaClass.isFromSource()) {
            FirRegularClassSymbol firRegularClassSymbol7 = firRegularClassSymbol3;
            arrayList2 = arrayList3;
            FirJavaFacadeKt.createDeclarationsForJavaRecord(javaClass, classId3, moduleData, coneClassLikeType2, arrayList6, arrayList2, firRegularClassSymbol7);
            firRegularClassSymbol3 = firRegularClassSymbol7;
        } else {
            arrayList2 = arrayList3;
        }
        if (z) {
            Pair<JavaMethod, FirJavaValueParameter> valueParameterForValue = valueParametersForAnnotationConstructor.getValueParameterForValue();
            if (valueParameterForValue != null) {
                JavaMethod javaMethod2 = (JavaMethod) valueParameterForValue.component1();
                final FirJavaValueParameter firJavaValueParameter = (FirJavaValueParameter) valueParameterForValue.component2();
                final JavaAnnotationArgument annotationParameterDefaultValue = javaMethod2.getAnnotationParameterDefaultValue();
                if (annotationParameterDefaultValue != null) {
                    firJavaValueParameter.setLazyDefaultValue(LazyKt.lazy(new Function0() { // from class: ma5
                        public final Object invoke() {
                            return FirLazyJavaDeclarationList.declarations_delegate$lambda$0$0$0$0(annotationParameterDefaultValue, session, firRegularClassSymbol3, firJavaValueParameter);
                        }
                    }));
                }
            }
            for (Map.Entry<JavaMethod, FirJavaValueParameter> entry : valueParametersForAnnotationConstructor.getValueParameters().entrySet()) {
                JavaMethod key = entry.getKey();
                final FirJavaValueParameter value = entry.getValue();
                final JavaAnnotationArgument annotationParameterDefaultValue2 = key.getAnnotationParameterDefaultValue();
                if (annotationParameterDefaultValue2 != null) {
                    value.setLazyDefaultValue(LazyKt.lazy(new Function0() { // from class: ma5
                        public final Object invoke() {
                            return FirLazyJavaDeclarationList.declarations_delegate$lambda$0$0$0$0(annotationParameterDefaultValue2, session, firRegularClassSymbol3, value);
                        }
                    }));
                }
            }
        }
        return arrayList2.isEmpty() ? CollectionsKt.emptyList() : arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final FirExpression declarations_delegate$lambda$0$0$0$0(JavaAnnotationArgument javaAnnotationArgument, FirSession firSession, FirRegularClassSymbol firRegularClassSymbol, FirJavaValueParameter firJavaValueParameter) throws KotlinIllegalArgumentExceptionWithAttachments {
        E fir = firRegularClassSymbol.getFir();
        fir.getClass();
        MutableJavaTypeParameterStack classJavaTypeParameterStack = ((FirJavaClass) fir).getClassJavaTypeParameterStack();
        FirTypeRef returnTypeRef = firJavaValueParameter.getReturnTypeRef();
        KtSourceElement source = firJavaValueParameter.getSource();
        return JavaAnnotationsMappingKt.toFirExpression(javaAnnotationArgument, firSession, classJavaTypeParameterStack, returnTypeRef, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null);
    }

    @Override // org.jetbrains.kotlin.fir.java.enhancement.FirJavaDeclarationList
    public List<FirDeclaration> getDeclarations() {
        return (List) this.declarations.getValue();
    }
}

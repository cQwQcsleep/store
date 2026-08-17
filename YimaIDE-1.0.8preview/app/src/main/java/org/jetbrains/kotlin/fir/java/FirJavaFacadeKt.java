package org.jetbrains.kotlin.fir.java;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.JavaPsiRecordUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtFakePsiSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponent;
import org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponentKt;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructedClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirEnumEntryBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.java.FirJavaFacadeKt;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaConstructor;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaConstructorBuilder;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaField;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaFieldBuilder;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaMethod;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaMethodBuilder;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameterBuilder;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameter;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameterBuilder;
import org.jetbrains.kotlin.fir.java.declarations.UtilsKt;
import org.jetbrains.kotlin.fir.java.enhancement.FirLazyJavaAnnotationList;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaArrayType;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaConstructor;
import org.jetbrains.kotlin.load.java.structure.JavaElement;
import org.jetbrains.kotlin.load.java.structure.JavaField;
import org.jetbrains.kotlin.load.java.structure.JavaMethod;
import org.jetbrains.kotlin.load.java.structure.JavaModifierListOwner;
import org.jetbrains.kotlin.load.java.structure.JavaPackage;
import org.jetbrains.kotlin.load.java.structure.JavaRecordComponent;
import org.jetbrains.kotlin.load.java.structure.JavaType;
import org.jetbrains.kotlin.load.java.structure.JavaTypeParameter;
import org.jetbrains.kotlin.load.java.structure.JavaValueParameter;
import org.jetbrains.kotlin.load.java.structure.impl.JavaElementImpl;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001aL\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a8\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001aB\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002\u001a \u0010#\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001aT\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010,\u001a\u00020\u00152\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\b\u0010-\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002\u001a0\u0010.\u001a\u00020'2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00152\u0006\u0010/\u001a\u0002002\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\f\u00101\u001a\u000202*\u000203H\u0002\u001a\u0018\u00104\u001a\u0004\u0018\u000105*\u0002062\b\b\u0002\u00107\u001a\u000208H\u0000\u001a\u0018\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u0010*\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0002¨\u0006;"}, d2 = {"toFirTypeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/load/java/structure/JavaTypeParameter;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "createDeclarationsForJavaRecord", Argument.Delimiters.none, "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "classType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "classTypeParameters", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "convertJavaFieldToFir", "javaField", "Lorg/jetbrains/kotlin/load/java/structure/JavaField;", "javaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;", "dispatchReceiver", "convertJavaMethodToFir", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaMethod;", "containingClass", "javaMethod", "Lorg/jetbrains/kotlin/load/java/structure/JavaMethod;", "javaPackage", "Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;", "convertJavaAnnotationMethodToValueParameter", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaValueParameter;", "firJavaMethod", "convertJavaConstructorToFir", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaConstructor;", "javaConstructor", "Lorg/jetbrains/kotlin/load/java/structure/JavaConstructor;", "constructorId", "Lorg/jetbrains/kotlin/name/CallableId;", "classSymbol", "outerClassSymbol", "buildConstructorForAnnotationClass", "valueParametersForAnnotationConstructor", "Lorg/jetbrains/kotlin/fir/java/ValueParametersForAnnotationConstructor;", "topLevelName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "toSourceElement", "Lorg/jetbrains/kotlin/KtSourceElement;", "Lorg/jetbrains/kotlin/load/java/structure/JavaElement;", "sourceElementKind", "Lorg/jetbrains/kotlin/KtSourceElementKind;", "toRefs", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaFacadeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FirJavaConstructor buildConstructorForAnnotationClass(JavaClass javaClass, CallableId callableId, FirRegularClassSymbol firRegularClassSymbol, ValueParametersForAnnotationConstructor valueParametersForAnnotationConstructor, FirModuleData firModuleData) {
        FirJavaConstructorBuilder firJavaConstructorBuilder = new FirJavaConstructorBuilder();
        firJavaConstructorBuilder.setContainingClassSymbol(firRegularClassSymbol);
        firJavaConstructorBuilder.setSource(toSourceElement(javaClass, KtFakeSourceElementKind.ImplicitConstructor.INSTANCE));
        firJavaConstructorBuilder.setModuleData(firModuleData);
        firJavaConstructorBuilder.setFromSource(javaClass.isFromSource());
        firJavaConstructorBuilder.setSymbol(new FirConstructorSymbol(callableId));
        firJavaConstructorBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE));
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(ScopeUtilsKt.defaultType(firRegularClassSymbol));
        firJavaConstructorBuilder.setReturnTypeRef(firResolvedTypeRefBuilder.build());
        Pair<JavaMethod, FirJavaValueParameter> valueParameterForValue = valueParametersForAnnotationConstructor.getValueParameterForValue();
        if (valueParameterForValue != null) {
            firJavaConstructorBuilder.getValueParameters().add((FirJavaValueParameter) valueParameterForValue.component2());
        }
        for (Map.Entry<JavaMethod, FirJavaValueParameter> entry : valueParametersForAnnotationConstructor.getValueParameters().entrySet()) {
            entry.getKey();
            firJavaConstructorBuilder.getValueParameters().add(entry.getValue());
        }
        firJavaConstructorBuilder.setPrimary(true);
        FirJavaConstructor firJavaConstructorBuild = firJavaConstructorBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firJavaConstructorBuild, firRegularClassSymbol.getLookupTag());
        return firJavaConstructorBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirJavaValueParameter convertJavaAnnotationMethodToValueParameter(JavaMethod javaMethod, FirJavaMethod firJavaMethod, FirModuleData firModuleData) {
        FirJavaValueParameterBuilder firJavaValueParameterBuilder = new FirJavaValueParameterBuilder();
        firJavaValueParameterBuilder.setSource(toSourceElement(javaMethod, KtFakeSourceElementKind.ImplicitJavaAnnotationConstructor.INSTANCE));
        firJavaValueParameterBuilder.setModuleData(firModuleData);
        firJavaValueParameterBuilder.setFromSource(javaMethod.isFromSource());
        firJavaValueParameterBuilder.setReturnTypeRef(firJavaMethod.getReturnTypeRef());
        firJavaValueParameterBuilder.setContainingDeclarationSymbol(firJavaMethod.getSymbol());
        firJavaValueParameterBuilder.setName(javaMethod.getName());
        firJavaValueParameterBuilder.setVararg((javaMethod.getReturnType() instanceof JavaArrayType) && Intrinsics.areEqual(javaMethod.getName(), FirJavaFacade.INSTANCE.getVALUE_METHOD_NAME()));
        return firJavaValueParameterBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final FirJavaConstructor convertJavaConstructorToFir(JavaConstructor javaConstructor, CallableId callableId, JavaClass javaClass, FirRegularClassSymbol firRegularClassSymbol, List<? extends FirTypeParameter> list, FirRegularClassSymbol firRegularClassSymbol2, FirModuleData firModuleData, JavaPackage javaPackage) throws KotlinIllegalArgumentExceptionWithAttachments {
        Visibility visibility;
        JavaClass javaClass2;
        KtSourceElement sourceElement;
        ConeClassLikeType coneClassLikeTypeDefaultType;
        Collection annotations;
        FirRegularClass firRegularClass;
        FirSession session = firModuleData.getSession();
        FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(callableId);
        E fir = firRegularClassSymbol.getFir();
        fir.getClass();
        FirJavaClass firJavaClass = (FirJavaClass) fir;
        if (javaConstructor == null || (visibility = javaConstructor.getVisibility()) == null) {
            visibility = firJavaClass.getOriginalStatus().getVisibility();
        }
        Visibility visibility2 = visibility;
        int i = 0;
        boolean z = true;
        boolean z2 = (javaClass.getOuterClass() == null || javaClass.isStatic()) ? false : true;
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(visibility2, Modality.FINAL, EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(visibility2, (FirClassLikeSymbol) firRegularClassSymbol, false, false, 6, (Object) null));
        firResolvedDeclarationStatusImpl.setInner(z2);
        firResolvedDeclarationStatusImpl.setHasStableParameterNames(false);
        FirJavaConstructorBuilder firJavaConstructorBuilder = new FirJavaConstructorBuilder();
        firJavaConstructorBuilder.setContainingClassSymbol(firRegularClassSymbol);
        ArrayList arrayList = null;
        if (javaConstructor == null || (sourceElement = toSourceElement$default(javaConstructor, null, 1, null)) == null) {
            javaClass2 = javaClass;
            sourceElement = toSourceElement(javaClass2, KtFakeSourceElementKind.ImplicitConstructor.INSTANCE);
        } else {
            javaClass2 = javaClass;
        }
        firJavaConstructorBuilder.setSource(sourceElement);
        firJavaConstructorBuilder.setModuleData(firModuleData);
        firJavaConstructorBuilder.setFromSource(javaClass2.isFromSource());
        firJavaConstructorBuilder.setSymbol(firConstructorSymbol);
        firJavaConstructorBuilder.setStatus(firResolvedDeclarationStatusImpl);
        if (javaConstructor != null) {
            KtSourceElement source = firJavaConstructorBuilder.getSource();
            PsiElement psi = source != null ? KtSourceElementKt.getPsi(source) : null;
            if (!((psi instanceof PsiMethod) && JavaPsiRecordUtil.isCanonicalConstructor((PsiMethod) psi))) {
                z = false;
            }
        }
        firJavaConstructorBuilder.setPrimary(z);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(ScopeUtilsKt.defaultType(firRegularClassSymbol));
        firJavaConstructorBuilder.setReturnTypeRef(firResolvedTypeRefBuilder.build());
        if (!z2 || firRegularClassSymbol2 == null || (firRegularClass = (FirRegularClass) firRegularClassSymbol2.getFir()) == null) {
            coneClassLikeTypeDefaultType = null;
        } else {
            ClassId classId = firRegularClassSymbol2.getClassId();
            List<FirTypeParameterRef> nonEnhancedTypeParameters = ((FirJavaClass) firRegularClass).getNonEnhancedTypeParameters();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(nonEnhancedTypeParameters, 10));
            Iterator<T> it = nonEnhancedTypeParameters.iterator();
            while (it.hasNext()) {
                arrayList2.add(((FirTypeParameterRef) it.next()).getSymbol());
            }
            coneClassLikeTypeDefaultType = ScopeUtilsKt.defaultType(classId, arrayList2);
        }
        firJavaConstructorBuilder.setDispatchReceiverType(coneClassLikeTypeDefaultType);
        CollectionsKt.addAll(firJavaConstructorBuilder.getTypeParameters(), toRefs(list));
        if (javaConstructor != null) {
            List typeParameters = javaConstructor.getTypeParameters();
            List<FirTypeParameterRef> typeParameters2 = firJavaConstructorBuilder.getTypeParameters();
            Iterator it2 = typeParameters.iterator();
            while (it2.hasNext()) {
                typeParameters2.add(toFirTypeParameter((JavaTypeParameter) it2.next(), firConstructorSymbol, firModuleData));
            }
            firJavaConstructorBuilder.setAnnotationList(new FirLazyJavaAnnotationList(javaConstructor, firModuleData));
            Iterator it3 = javaConstructor.getValueParameters().iterator();
            while (it3.hasNext()) {
                firJavaConstructorBuilder.getValueParameters().add(JavaAnnotationsMappingKt.toFirValueParameter((JavaValueParameter) it3.next(), session, firConstructorSymbol, firModuleData, i));
                i++;
            }
        }
        FirJavaConstructor firJavaConstructorBuild = firJavaConstructorBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firJavaConstructorBuild, firRegularClassSymbol.getLookupTag());
        FirMustUseReturnValueStatusComponent mustUseReturnValueStatusComponent = FirMustUseReturnValueStatusComponentKt.getMustUseReturnValueStatusComponent(session);
        if (javaPackage != null && (annotations = javaPackage.getAnnotations()) != null) {
            arrayList = new ArrayList();
            Iterator it4 = annotations.iterator();
            while (it4.hasNext()) {
                ClassId classId2 = ((JavaAnnotation) it4.next()).getClassId();
                if (classId2 != null) {
                    arrayList.add(classId2);
                }
            }
        }
        firResolvedDeclarationStatusImpl.setReturnValueStatus(mustUseReturnValueStatusComponent.computeMustUseReturnValueForJavaCallable(session, firConstructorSymbol, firRegularClassSymbol, arrayList));
        return firJavaConstructorBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirDeclaration convertJavaFieldToFir(final JavaField javaField, ClassId classId, MutableJavaTypeParameterStack mutableJavaTypeParameterStack, ConeClassLikeType coneClassLikeType, FirModuleData firModuleData, FirRegularClassSymbol firRegularClassSymbol) {
        final FirSession session = firModuleData.getSession();
        Name name = javaField.getName();
        CallableId callableId = new CallableId(classId.getPackageFqName(), classId.getRelativeClassName(), name);
        JavaType type = javaField.getType();
        KtSourceElement sourceElement$default = toSourceElement$default(javaField, null, 1, null);
        KtSourceElement ktSourceElementFakeElement$default = sourceElement$default != null ? KtSourceElementKt.fakeElement$default(sourceElement$default, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null;
        if (javaField.isEnumEntry()) {
            FirEnumEntryBuilder firEnumEntryBuilder = new FirEnumEntryBuilder();
            firEnumEntryBuilder.setSource(toSourceElement$default(javaField, null, 1, null));
            firEnumEntryBuilder.setModuleData(firModuleData);
            firEnumEntryBuilder.setSymbol(new FirEnumEntrySymbol(callableId));
            firEnumEntryBuilder.setName(name);
            FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(javaField.getVisibility(), JavaUtilsKt.getModality((JavaModifierListOwner) javaField), EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(javaField.getVisibility(), coneClassLikeType.getLookupTag(), false, false, 6, (Object) null));
            firResolvedDeclarationStatusImpl.setStatic(javaField.isStatic());
            firEnumEntryBuilder.setStatus(firResolvedDeclarationStatusImpl);
            firEnumEntryBuilder.setLocal(false);
            firEnumEntryBuilder.setReturnTypeRef(JavaTypeConversionKt.resolveIfJavaType(JavaTypeConversionKt.toFirJavaTypeRef(type, session, ktSourceElementFakeElement$default), session, mutableJavaTypeParameterStack, ktSourceElementFakeElement$default, FirJavaTypeConversionMode.ANNOTATION_MEMBER));
            firEnumEntryBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            firEnumEntryBuilder.setOrigin(UtilsKt.javaOrigin(javaField.isFromSource()));
            FirEnumEntry firEnumEntryBuild = firEnumEntryBuilder.mo288build();
            ClassMembersKt.setContainingClassForStaticMemberAttr(firEnumEntryBuild, TypeConstructionUtilsKt.toLookupTag(classId));
            JavaAnnotationsMappingKt.setAnnotationsFromJava(firEnumEntryBuild, session, ktSourceElementFakeElement$default, javaField);
            firEnumEntryBuild.replaceDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProviderFromAnnotations$default(firEnumEntryBuild.getAnnotations(), session, true, null, 4, null));
            return firEnumEntryBuild;
        }
        FirJavaFieldBuilder firJavaFieldBuilder = new FirJavaFieldBuilder();
        firJavaFieldBuilder.setContainingClassSymbol(firRegularClassSymbol);
        firJavaFieldBuilder.setSource(toSourceElement$default(javaField, null, 1, null));
        firJavaFieldBuilder.setModuleData(firModuleData);
        firJavaFieldBuilder.setSymbol(new FirFieldSymbol(callableId));
        firJavaFieldBuilder.setName(name);
        firJavaFieldBuilder.setFromSource(javaField.isFromSource());
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl2 = new FirResolvedDeclarationStatusImpl(javaField.getVisibility(), JavaUtilsKt.getModality((JavaModifierListOwner) javaField), EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(javaField.getVisibility(), coneClassLikeType.getLookupTag(), false, false, 6, (Object) null));
        firResolvedDeclarationStatusImpl2.setStatic(javaField.isStatic());
        firJavaFieldBuilder.setStatus(firResolvedDeclarationStatusImpl2);
        firJavaFieldBuilder.setReturnTypeRef(JavaTypeConversionKt.toFirJavaTypeRef(type, session, ktSourceElementFakeElement$default));
        firJavaFieldBuilder.setVar(!javaField.isFinal());
        firJavaFieldBuilder.setAnnotationList(new FirLazyJavaAnnotationList(javaField, firModuleData));
        firJavaFieldBuilder.setLazyInitializer(LazyKt.lazy(new Function0() { // from class: g95
            public final Object invoke() {
                return FirJavaFacadeKt.convertJavaFieldToFir$lambda$2$1(javaField, session);
            }
        }));
        firJavaFieldBuilder.setLazyHasConstantInitializer(LazyKt.lazy(new Function0() { // from class: h95
            public final Object invoke() {
                return Boolean.valueOf(javaField.getHasConstantNotNullInitializer());
            }
        }));
        if (!javaField.isStatic()) {
            firJavaFieldBuilder.setDispatchReceiverType(coneClassLikeType);
        }
        FirJavaField firJavaFieldBuild = firJavaFieldBuilder.mo288build();
        if (javaField.isStatic()) {
            ClassMembersKt.setContainingClassForStaticMemberAttr(firJavaFieldBuild, TypeConstructionUtilsKt.toLookupTag(classId));
        }
        return firJavaFieldBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirExpression convertJavaFieldToFir$lambda$2$1(JavaField javaField, FirSession firSession) {
        Object initializerValue = javaField.getInitializerValue();
        if (initializerValue != null) {
            return JavaUtilsKt.createConstantIfAny$default(initializerValue, firSession, false, 2, null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirJavaMethod convertJavaMethodToFir(JavaClass javaClass, JavaMethod javaMethod, ClassId classId, ConeClassLikeType coneClassLikeType, FirModuleData firModuleData, FirRegularClassSymbol firRegularClassSymbol, JavaPackage javaPackage) {
        Collection annotations;
        FirSession session = firModuleData.getSession();
        Name name = javaMethod.getName();
        FirNamedFunctionSymbol firNamedFunctionSymbol = new FirNamedFunctionSymbol(new CallableId(classId.getPackageFqName(), classId.getRelativeClassName(), name));
        JavaType returnType = javaMethod.getReturnType();
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(javaMethod.getVisibility(), JavaUtilsKt.getModality((JavaModifierListOwner) javaMethod), EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(javaMethod.getVisibility(), coneClassLikeType.getLookupTag(), false, false, 6, (Object) null));
        firResolvedDeclarationStatusImpl.setStatic(javaMethod.isStatic());
        int i = 0;
        firResolvedDeclarationStatusImpl.setHasStableParameterNames(false);
        firResolvedDeclarationStatusImpl.setExternal(javaMethod.isNative());
        FirJavaMethodBuilder firJavaMethodBuilder = new FirJavaMethodBuilder();
        firJavaMethodBuilder.setContainingClassSymbol(firRegularClassSymbol);
        firJavaMethodBuilder.setModuleData(firModuleData);
        ArrayList arrayList = null;
        firJavaMethodBuilder.setSource(toSourceElement$default(javaMethod, null, 1, null));
        firJavaMethodBuilder.setSymbol(firNamedFunctionSymbol);
        firJavaMethodBuilder.setName(name);
        firJavaMethodBuilder.setFromSource(javaMethod.isFromSource());
        KtSourceElement source = firJavaMethodBuilder.getSource();
        firJavaMethodBuilder.setReturnTypeRef(JavaTypeConversionKt.toFirJavaTypeRef(returnType, session, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null));
        List typeParameters = javaMethod.getTypeParameters();
        List<FirTypeParameter> typeParameters2 = firJavaMethodBuilder.getTypeParameters();
        Iterator it = typeParameters.iterator();
        while (it.hasNext()) {
            typeParameters2.add(toFirTypeParameter((JavaTypeParameter) it.next(), firNamedFunctionSymbol, firModuleData));
        }
        Iterator it2 = javaMethod.getValueParameters().iterator();
        while (it2.hasNext()) {
            firJavaMethodBuilder.getValueParameters().add(JavaAnnotationsMappingKt.toFirValueParameter((JavaValueParameter) it2.next(), session, firNamedFunctionSymbol, firModuleData, i));
            i++;
        }
        firJavaMethodBuilder.setAnnotationList(new FirLazyJavaAnnotationList(javaMethod, firModuleData));
        firJavaMethodBuilder.setStatus(firResolvedDeclarationStatusImpl);
        if (!javaMethod.isStatic()) {
            firJavaMethodBuilder.setDispatchReceiverType(coneClassLikeType);
        }
        FirJavaMethod firJavaMethodBuild = firJavaMethodBuilder.mo288build();
        if (javaMethod.isStatic()) {
            ClassMembersKt.setContainingClassForStaticMemberAttr(firJavaMethodBuild, TypeConstructionUtilsKt.toLookupTag(classId));
        }
        if (javaClass.isRecord() && firJavaMethodBuild.getValueParameters().isEmpty()) {
            Collection recordComponents = javaClass.getRecordComponents();
            if (!(recordComponents instanceof Collection) || !recordComponents.isEmpty()) {
                Iterator it3 = recordComponents.iterator();
                while (it3.hasNext()) {
                    if (Intrinsics.areEqual(((JavaRecordComponent) it3.next()).getName(), name)) {
                        ClassMembersKt.setJavaRecordComponent(firJavaMethodBuild, Boolean.TRUE);
                        break;
                    }
                }
            }
        }
        FirMustUseReturnValueStatusComponent mustUseReturnValueStatusComponent = FirMustUseReturnValueStatusComponentKt.getMustUseReturnValueStatusComponent(session);
        if (javaPackage != null && (annotations = javaPackage.getAnnotations()) != null) {
            arrayList = new ArrayList();
            Iterator it4 = annotations.iterator();
            while (it4.hasNext()) {
                ClassId classId2 = ((JavaAnnotation) it4.next()).getClassId();
                if (classId2 != null) {
                    arrayList.add(classId2);
                }
            }
        }
        firResolvedDeclarationStatusImpl.setReturnValueStatus(mustUseReturnValueStatusComponent.computeMustUseReturnValueForJavaCallable(session, firNamedFunctionSymbol, firRegularClassSymbol, arrayList));
        return firJavaMethodBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createDeclarationsForJavaRecord(JavaClass javaClass, ClassId classId, FirModuleData firModuleData, ConeClassLikeType coneClassLikeType, List<? extends FirTypeParameter> list, List<FirDeclaration> list2, FirRegularClassSymbol firRegularClassSymbol) {
        FirSession session = firModuleData.getSession();
        List<FirDeclaration> list3 = list2;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list3) {
            if (obj instanceof FirJavaMethod) {
                arrayList.add(obj);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : arrayList) {
            Name name = ((FirJavaMethod) obj2).getName();
            Object arrayList2 = linkedHashMap.get(name);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(name, arrayList2);
            }
            ((List) arrayList2).add(obj2);
        }
        for (JavaRecordComponent javaRecordComponent : javaClass.getRecordComponents()) {
            Name name2 = javaRecordComponent.getName();
            List listEmptyList = (List) linkedHashMap.get(name2);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List list4 = listEmptyList;
            if (!(list4 instanceof Collection) || !list4.isEmpty()) {
                Iterator it = list4.iterator();
                do {
                    if (it.hasNext()) {
                    }
                } while (!((FirJavaMethod) it.next()).getValueParameters().isEmpty());
            }
            CallableId callableId = new CallableId(classId, name2);
            FirJavaMethodBuilder firJavaMethodBuilder = new FirJavaMethodBuilder();
            firJavaMethodBuilder.setContainingClassSymbol(firRegularClassSymbol);
            firJavaMethodBuilder.setModuleData(firModuleData);
            firJavaMethodBuilder.setSource(toSourceElement(javaRecordComponent, KtFakeSourceElementKind.JavaRecordComponentFunction.INSTANCE));
            firJavaMethodBuilder.setSymbol(new FirNamedFunctionSymbol(callableId));
            firJavaMethodBuilder.setName(name2);
            firJavaMethodBuilder.setFromSource(javaRecordComponent.isFromSource());
            firJavaMethodBuilder.setReturnTypeRef(JavaTypeConversionKt.toFirJavaTypeRef(javaRecordComponent.getType(), session, firJavaMethodBuilder.getSource()));
            firJavaMethodBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE));
            firJavaMethodBuilder.setDispatchReceiverType(coneClassLikeType);
            FirJavaMethod firJavaMethodBuild = firJavaMethodBuilder.mo288build();
            ClassMembersKt.setJavaRecordComponent(firJavaMethodBuild, Boolean.TRUE);
            list2.add(firJavaMethodBuild);
        }
        if (!(list3 instanceof Collection) || !list3.isEmpty()) {
            for (FirDeclaration firDeclaration : list3) {
                if ((firDeclaration instanceof FirJavaConstructor) && ((FirJavaConstructor) firDeclaration).getIsPrimary()) {
                    return;
                }
            }
        }
        List<FirDeclaration> list5 = list2;
        FirJavaConstructorBuilder firJavaConstructorBuilder = new FirJavaConstructorBuilder();
        firJavaConstructorBuilder.setContainingClassSymbol(firRegularClassSymbol);
        firJavaConstructorBuilder.setSource(toSourceElement(javaClass, KtFakeSourceElementKind.ImplicitJavaRecordConstructor.INSTANCE));
        firJavaConstructorBuilder.setModuleData(firModuleData);
        firJavaConstructorBuilder.setFromSource(javaClass.isFromSource());
        firJavaConstructorBuilder.setSymbol(new FirConstructorSymbol(new CallableId(classId, classId.getShortClassName())));
        firJavaConstructorBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE));
        firJavaConstructorBuilder.setPrimary(true);
        firJavaConstructorBuilder.setReturnTypeRef(org.jetbrains.kotlin.fir.UtilsKt.toFirResolvedTypeRef$default(coneClassLikeType, null, null, 3, null));
        firJavaConstructorBuilder.setDispatchReceiverType(null);
        CollectionsKt.addAll(firJavaConstructorBuilder.getTypeParameters(), toRefs(list));
        Collection<JavaRecordComponent> recordComponents = javaClass.getRecordComponents();
        List<FirValueParameter> valueParameters = firJavaConstructorBuilder.getValueParameters();
        for (JavaRecordComponent javaRecordComponent2 : recordComponents) {
            FirJavaValueParameterBuilder firJavaValueParameterBuilder = new FirJavaValueParameterBuilder();
            firJavaValueParameterBuilder.setContainingDeclarationSymbol(firJavaConstructorBuilder.getSymbol());
            firJavaValueParameterBuilder.setSource(toSourceElement(javaRecordComponent2, KtFakeSourceElementKind.ImplicitRecordConstructorParameter.INSTANCE));
            firJavaValueParameterBuilder.setModuleData(firModuleData);
            firJavaValueParameterBuilder.setFromSource(javaRecordComponent2.isFromSource());
            firJavaValueParameterBuilder.setReturnTypeRef(JavaTypeConversionKt.toFirJavaTypeRef(javaRecordComponent2.getType(), session, firJavaValueParameterBuilder.getSource()));
            firJavaValueParameterBuilder.setName(javaRecordComponent2.getName());
            firJavaValueParameterBuilder.setVararg(javaRecordComponent2.isVararg());
            firJavaValueParameterBuilder.setAnnotationList(new FirLazyJavaAnnotationList(javaRecordComponent2, firModuleData));
            valueParameters.add(firJavaValueParameterBuilder.build());
        }
        FirJavaConstructor firJavaConstructorBuild = firJavaConstructorBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firJavaConstructorBuild, coneClassLikeType.getLookupTag());
        list5.add(firJavaConstructorBuild);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirTypeParameter toFirTypeParameter(JavaTypeParameter javaTypeParameter, FirBasedSymbol<?> firBasedSymbol, FirModuleData firModuleData) {
        FirJavaTypeParameterBuilder firJavaTypeParameterBuilder = new FirJavaTypeParameterBuilder();
        firJavaTypeParameterBuilder.setJavaTypeParameter(javaTypeParameter);
        firJavaTypeParameterBuilder.setModuleData(firModuleData);
        firJavaTypeParameterBuilder.setOrigin(UtilsKt.javaOrigin(javaTypeParameter.isFromSource()));
        firJavaTypeParameterBuilder.setName(javaTypeParameter.getName());
        firJavaTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
        firJavaTypeParameterBuilder.setSource(toSourceElement$default(javaTypeParameter, null, 1, null));
        firJavaTypeParameterBuilder.setContainingDeclarationSymbol(firBasedSymbol);
        firJavaTypeParameterBuilder.setAnnotationList(new FirLazyJavaAnnotationList(javaTypeParameter, firModuleData));
        return firJavaTypeParameterBuilder.build();
    }

    private static final List<FirTypeParameterRef> toRefs(List<? extends FirTypeParameter> list) {
        List<? extends FirTypeParameter> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (FirTypeParameter firTypeParameter : list2) {
            FirConstructedClassTypeParameterRefBuilder firConstructedClassTypeParameterRefBuilder = new FirConstructedClassTypeParameterRefBuilder();
            firConstructedClassTypeParameterRefBuilder.setSymbol(firTypeParameter.getSymbol());
            arrayList.add(firConstructedClassTypeParameterRefBuilder.build());
        }
        return arrayList;
    }

    public static final KtSourceElement toSourceElement(JavaElement javaElement, KtSourceElementKind ktSourceElementKind) {
        PsiElement psi;
        javaElement.getClass();
        ktSourceElementKind.getClass();
        JavaElementImpl javaElementImpl = javaElement instanceof JavaElementImpl ? (JavaElementImpl) javaElement : null;
        if (javaElementImpl != null && (psi = javaElementImpl.getPsi()) != null) {
            if (ktSourceElementKind instanceof KtRealSourceElementKind) {
                return new KtRealPsiSourceElement(psi);
            }
            if (ktSourceElementKind instanceof KtFakeSourceElementKind) {
                return new KtFakePsiSourceElement(psi, (KtFakeSourceElementKind) ktSourceElementKind);
            }
            bu8.a();
        }
        return null;
    }

    public static /* synthetic */ KtSourceElement toSourceElement$default(JavaElement javaElement, KtSourceElementKind ktSourceElementKind, int i, Object obj) {
        if ((i & 1) != 0) {
            ktSourceElementKind = KtRealSourceElementKind.INSTANCE;
        }
        return toSourceElement(javaElement, ktSourceElementKind);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String topLevelName(FqName fqName) {
        return StringsKt.substringBefore$default(fqName.asString(), ".", (String) null, 2, (Object) null);
    }
}

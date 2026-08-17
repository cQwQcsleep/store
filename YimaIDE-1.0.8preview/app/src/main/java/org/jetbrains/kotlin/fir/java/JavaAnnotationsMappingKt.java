package org.jetbrains.kotlin.fir.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirClassReferenceExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCollectionLiteralBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirEnumEntryDeserializedAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGetClassCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirVarargArgumentsExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirAnnotationArgumentMappingImpl;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaExternalAnnotationBuilder;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameterBuilder;
import org.jetbrains.kotlin.fir.java.enhancement.FirLazyJavaAnnotationList;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedReferenceError;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationAsAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationOwner;
import org.jetbrains.kotlin.load.java.structure.JavaArrayAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaClassObjectAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaEnumValueAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaLiteralAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaMethod;
import org.jetbrains.kotlin.load.java.structure.JavaValueParameter;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0000\u001a*\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0002\u001a'\u0010\u000e\u001a\u00020\u000f2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014H\u0086\bø\u0001\u0000\u001a\u0012\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0001*\u00020\u0002H\u0002\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000\u001a&\u0010\u0018\u001a\u00020\u0013*\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001a\u001a\u00020\u0017H\u0000\u001a0\u0010\u001b\u001a\u00020\u001c*\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0000\u001a0\u0010$\u001a\u00020\u0016*\u00020%2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000\u001a\u0014\u00104\u001a\u0004\u0018\u00010\u0016*\b\u0012\u0004\u0012\u00020%0\u0001H\u0002\u001a\u000e\u00105\u001a\u0004\u0018\u00010\u0016*\u00020%H\u0002\u001aL\u00106\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00042\f\u0010:\u001a\b\u0012\u0004\u0012\u00020%0;2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020\u00160=2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002\u001a,\u0010?\u001a\u0010\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020)\u0018\u00010+*\u00020@2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002\u001a\u001a\u0010?\u001a\u0010\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020)\u0018\u00010+*\u00020AH\u0002\u001a\f\u0010B\u001a\u00020\n*\u00020\u0004H\u0000\u001a\u001e\u0010C\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002\u001a\"\u0010D\u001a\u00020E2\u0006\u00109\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002\"\u001a\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010.\u001a\u0014\u0012\u0004\u0012\u00020,\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0+X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020302X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006F"}, d2 = {"convertAnnotationsToFir", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isDeprecatedInJavaDoc", Argument.Delimiters.none, "mergeTargetAnnotations", "annotationWithJavaTarget", "annotationWithKotlinTarget", "buildVarargArgumentsExpressionWithTargets", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirVarargArgumentsExpressionBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "targetArgumentExpressions", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotationOwner;", "setAnnotationsFromJava", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "javaAnnotationOwner", "toFirValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/load/java/structure/JavaValueParameter;", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "index", Argument.Delimiters.none, "toFirExpression", "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotationArgument;", "javaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "expectedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "JAVA_RETENTION_TO_KOTLIN", Argument.Delimiters.none, Argument.Delimiters.none, "Lkotlin/annotation/AnnotationRetention;", "JAVA_TARGETS_TO_KOTLIN", "Ljava/util/EnumSet;", "Lkotlin/annotation/AnnotationTarget;", "JAVA_DEFAULT_TARGET_SET", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "mapJavaTargetArguments", "mapJavaRetentionArgument", "fillAnnotationArgumentMapping", "lookupTag", "Lorg/jetbrains/kotlin/fir/symbols/impl/ConeClassLikeLookupTagImpl;", "javaAnnotation", "annotationArguments", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "annotationParametersMapping", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "isJavaDeprecatedAnnotation", "toFirAnnotation", "buildFirAnnotation", "Lorg/jetbrains/kotlin/fir/java/AnnotationData;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaAnnotationsMappingKt {
    private static final Map<String, AnnotationRetention> JAVA_RETENTION_TO_KOTLIN = MapsKt.mapOf(new Pair[]{TuplesKt.to("RUNTIME", AnnotationRetention.RUNTIME), TuplesKt.to("CLASS", AnnotationRetention.BINARY), TuplesKt.to("SOURCE", AnnotationRetention.SOURCE)});
    private static final Map<String, EnumSet<AnnotationTarget>> JAVA_TARGETS_TO_KOTLIN = MapsKt.mapOf(new Pair[]{TuplesKt.to("TYPE", EnumSet.of(AnnotationTarget.CLASS, AnnotationTarget.FILE)), TuplesKt.to("ANNOTATION_TYPE", EnumSet.of(AnnotationTarget.ANNOTATION_CLASS)), TuplesKt.to("TYPE_PARAMETER", EnumSet.of(AnnotationTarget.TYPE_PARAMETER)), TuplesKt.to("FIELD", EnumSet.of(AnnotationTarget.FIELD)), TuplesKt.to("LOCAL_VARIABLE", EnumSet.of(AnnotationTarget.LOCAL_VARIABLE)), TuplesKt.to("PARAMETER", EnumSet.of(AnnotationTarget.VALUE_PARAMETER)), TuplesKt.to("CONSTRUCTOR", EnumSet.of(AnnotationTarget.CONSTRUCTOR)), TuplesKt.to("METHOD", EnumSet.of(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)), TuplesKt.to("TYPE_USE", EnumSet.of(AnnotationTarget.TYPE))});
    private static final Set<KotlinTarget> JAVA_DEFAULT_TARGET_SET = SetsKt.minus(KotlinTarget.INSTANCE.getDEFAULT_TARGET_SET(), KotlinTarget.PROPERTY);

    private static final Map<Name, FirTypeRef> annotationParametersMapping(FirRegularClass firRegularClass) {
        List<FirDeclaration> declarations;
        Object next;
        List<FirValueParameter> valueParameters;
        if (firRegularClass.getClassKind() != ClassKind.ANNOTATION_CLASS) {
            firRegularClass = null;
        }
        if (firRegularClass != null && (declarations = firRegularClass.getDeclarations()) != null) {
            Iterator<T> it = declarations.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!(next instanceof FirConstructor));
            FirConstructor firConstructor = (FirConstructor) next;
            if (firConstructor != null && (valueParameters = firConstructor.getValueParameters()) != null) {
                List<FirValueParameter> list = valueParameters;
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
                for (FirValueParameter firValueParameter : list) {
                    Pair pair = TuplesKt.to(firValueParameter.getName(), firValueParameter.getReturnTypeRef());
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                }
                return linkedHashMap;
            }
        }
        return null;
    }

    private static final AnnotationData buildFirAnnotation(JavaAnnotation javaAnnotation, FirSession firSession, KtSourceElement ktSourceElement) {
        ClassId deprecated;
        ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl;
        Name shortClassName;
        FirResolvedTypeRef firResolvedTypeRefBuild;
        FirAnnotationArgumentMapping firAnnotationArgumentMappingImpl;
        ClassId classId = javaAnnotation.getClassId();
        JvmStandardClassIds.Annotations.Java java = JvmStandardClassIds.Annotations.Java.INSTANCE;
        if (Intrinsics.areEqual(classId, java.getTarget())) {
            deprecated = StandardClassIds$Annotations.INSTANCE.getTarget();
        } else if (Intrinsics.areEqual(classId, java.getRetention())) {
            deprecated = StandardClassIds$Annotations.INSTANCE.getRetention();
        } else if (Intrinsics.areEqual(classId, java.getDocumented())) {
            deprecated = StandardClassIds$Annotations.INSTANCE.getMustBeDocumented();
        } else {
            deprecated = Intrinsics.areEqual(classId, java.getDeprecated()) ? StandardClassIds$Annotations.INSTANCE.getDeprecated() : classId;
        }
        ConeClassLikeLookupTagImpl lookupTag = deprecated != null ? TypeConstructionUtilsKt.toLookupTag(deprecated) : null;
        if (lookupTag != null) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            coneClassLikeLookupTagImpl = lookupTag;
            firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(coneClassLikeLookupTagImpl, new ConeTypeProjection[0], false, null, 8, null));
            firResolvedTypeRefBuilder.setSource(ktSourceElement);
            firResolvedTypeRefBuild = firResolvedTypeRefBuilder.build();
        } else {
            coneClassLikeLookupTagImpl = lookupTag;
            if (classId == null || (shortClassName = classId.getShortClassName()) == null) {
                shortClassName = SpecialNames.NO_NAME_PROVIDED;
            }
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setDiagnostic(new ConeUnresolvedReferenceError(shortClassName));
            firErrorTypeRefBuilder.setSource(ktSourceElement);
            firResolvedTypeRefBuild = firErrorTypeRefBuilder.build();
        }
        FirResolvedTypeRef firResolvedTypeRef = firResolvedTypeRefBuild;
        if (coneClassLikeLookupTagImpl == null || Intrinsics.areEqual(classId, java.getDocumented())) {
            firAnnotationArgumentMappingImpl = FirEmptyAnnotationArgumentMapping.INSTANCE;
        } else {
            firAnnotationArgumentMappingImpl = Intrinsics.areEqual(classId, java.getDeprecated()) ? new FirAnnotationArgumentMappingImpl(null, MapsKt.mapOf(TuplesKt.to(StandardClassIds$Annotations.ParameterNames.INSTANCE.getDeprecatedMessage(), JavaUtilsKt.createConstantOrError$default("Deprecated in Java", firSession, null, 2, null)))) : new JavaAnnotationsMappingKt$buildFirAnnotation$argumentMapping$1(classId, javaAnnotation, firSession, coneClassLikeLookupTagImpl, ktSourceElement);
        }
        return new AnnotationData(firResolvedTypeRef, firAnnotationArgumentMappingImpl);
    }

    public static final FirVarargArgumentsExpression buildVarargArgumentsExpressionWithTargets(Function1<? super FirVarargArgumentsExpressionBuilder, Unit> function1) {
        function1.getClass();
        FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder = new FirVarargArgumentsExpressionBuilder();
        function1.invoke(firVarargArgumentsExpressionBuilder);
        ConeClassLikeTypeImpl coneClassLikeTypeImpl = new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getAnnotationTarget()), new ConeTypeProjection[0], false, ConeAttributes.INSTANCE.getEmpty());
        firVarargArgumentsExpressionBuilder.setConeTypeOrNull(ArrayUtilsKt.createOutArrayType$default(coneClassLikeTypeImpl, false, false, 3, null));
        firVarargArgumentsExpressionBuilder.setConeElementTypeOrNull(coneClassLikeTypeImpl);
        return firVarargArgumentsExpressionBuilder.mo288build();
    }

    public static /* synthetic */ FirVarargArgumentsExpression buildVarargArgumentsExpressionWithTargets$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<FirVarargArgumentsExpressionBuilder, Unit>() { // from class: org.jetbrains.kotlin.fir.java.JavaAnnotationsMappingKt.buildVarargArgumentsExpressionWithTargets.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirVarargArgumentsExpressionBuilder) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder) {
                    firVarargArgumentsExpressionBuilder.getClass();
                }
            };
        }
        function1.getClass();
        FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder = new FirVarargArgumentsExpressionBuilder();
        function1.invoke(firVarargArgumentsExpressionBuilder);
        ConeClassLikeTypeImpl coneClassLikeTypeImpl = new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getAnnotationTarget()), new ConeTypeProjection[0], false, ConeAttributes.INSTANCE.getEmpty());
        firVarargArgumentsExpressionBuilder.setConeTypeOrNull(ArrayUtilsKt.createOutArrayType$default(coneClassLikeTypeImpl, false, false, 3, null));
        firVarargArgumentsExpressionBuilder.setConeElementTypeOrNull(coneClassLikeTypeImpl);
        return firVarargArgumentsExpressionBuilder.mo288build();
    }

    public static final List<FirAnnotation> convertAnnotationsToFir(Iterable<? extends JavaAnnotation> iterable, FirSession firSession, KtSourceElement ktSourceElement, boolean z) {
        iterable.getClass();
        firSession.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        FirAnnotation firAnnotation = null;
        boolean z2 = false;
        FirAnnotation firAnnotation2 = null;
        for (JavaAnnotation javaAnnotation : iterable) {
            if (isJavaDeprecatedAnnotation(javaAnnotation)) {
                z2 = true;
            }
            FirAnnotation firAnnotation3 = toFirAnnotation(javaAnnotation, firSession, ktSourceElement);
            ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId(firAnnotation3, firSession);
            StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
            if (Intrinsics.areEqual(annotationClassId, standardClassIds$Annotations.getTarget())) {
                boolean zAreEqual = Intrinsics.areEqual(javaAnnotation.getClassId(), standardClassIds$Annotations.getTarget());
                if (firAnnotation == null && !zAreEqual) {
                    firAnnotation = firAnnotation3;
                }
                if (firAnnotation2 == null && zAreEqual) {
                    firAnnotation2 = firAnnotation3;
                }
            }
            list.add(firAnnotation3);
        }
        if (!z2 && z) {
            listCreateListBuilder.add(toFirAnnotation(DeprecatedInJavaDocAnnotation.INSTANCE, firSession, ktSourceElement));
        }
        List<FirAnnotation> listBuild = CollectionsKt.build(listCreateListBuilder);
        return firAnnotation2 == null ? listBuild : mergeTargetAnnotations(listBuild, firAnnotation, firAnnotation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:18:0x002e  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void fillAnnotationArgumentMapping(FirSession firSession, ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl, JavaAnnotation javaAnnotation, Collection<? extends JavaAnnotationArgument> collection, Map<Name, FirExpression> map, KtSourceElement ktSourceElement) {
        Map<Name, FirTypeRef> mapAnnotationParametersMapping;
        if (collection.isEmpty()) {
            return;
        }
        if (firSession.getKind() == FirSession.Kind.Library) {
            JavaClass javaClassResolve = javaAnnotation.resolve();
            JavaClass originalClsJavaClass = javaClassResolve != null ? javaClassResolve.getOriginalClsJavaClass() : null;
            if (originalClsJavaClass == null || originalClsJavaClass.getLightClassOriginKind() != null) {
                originalClsJavaClass = null;
            }
            if (originalClsJavaClass != null) {
                mapAnnotationParametersMapping = annotationParametersMapping(originalClsJavaClass, firSession, ktSourceElement);
            } else {
                mapAnnotationParametersMapping = null;
            }
        } else {
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((ConeClassLikeLookupTag) coneClassLikeLookupTagImpl, firSession);
            FirClassLikeDeclaration firClassLikeDeclaration = symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null;
            FirRegularClass firRegularClass = firClassLikeDeclaration instanceof FirRegularClass ? (FirRegularClass) firClassLikeDeclaration : null;
            if (firRegularClass != null) {
                mapAnnotationParametersMapping = annotationParametersMapping(firRegularClass);
            } else {
                mapAnnotationParametersMapping = null;
            }
        }
        for (JavaAnnotationArgument javaAnnotationArgument : collection) {
            Name name = javaAnnotationArgument.getName();
            if (name == null) {
                name = StandardClassIds$Annotations.ParameterNames.INSTANCE.getValue();
            }
            Pair pair = TuplesKt.to(name, toFirExpression(javaAnnotationArgument, firSession, JavaTypeParameterStack.INSTANCE.getEMPTY(), mapAnnotationParametersMapping != null ? mapAnnotationParametersMapping.get(name) : null, ktSourceElement));
            map.put(pair.getFirst(), pair.getSecond());
        }
    }

    public static final boolean isJavaDeprecatedAnnotation(JavaAnnotation javaAnnotation) {
        javaAnnotation.getClass();
        return Intrinsics.areEqual(javaAnnotation.getClassId(), JvmStandardClassIds.Annotations.Java.INSTANCE.getDeprecated());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirExpression mapJavaRetentionArgument(JavaAnnotationArgument javaAnnotationArgument) {
        Name entryName;
        Map<String, AnnotationRetention> map = JAVA_RETENTION_TO_KOTLIN;
        JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument = javaAnnotationArgument instanceof JavaEnumValueAnnotationArgument ? (JavaEnumValueAnnotationArgument) javaAnnotationArgument : null;
        AnnotationRetention annotationRetention = map.get((javaEnumValueAnnotationArgument == null || (entryName = javaEnumValueAnnotationArgument.getEntryName()) == null) ? null : entryName.asString());
        if (annotationRetention == null) {
            return null;
        }
        FirEnumEntryDeserializedAccessExpressionBuilder firEnumEntryDeserializedAccessExpressionBuilder = new FirEnumEntryDeserializedAccessExpressionBuilder();
        firEnumEntryDeserializedAccessExpressionBuilder.setEnumClassId(StandardClassIds.INSTANCE.getAnnotationRetention());
        Name nameIdentifier = Name.identifier(annotationRetention.name());
        nameIdentifier.getClass();
        firEnumEntryDeserializedAccessExpressionBuilder.setEnumEntryName(nameIdentifier);
        return firEnumEntryDeserializedAccessExpressionBuilder.mo288build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirExpression mapJavaTargetArguments(List<? extends JavaAnnotationArgument> list) {
        FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder = new FirVarargArgumentsExpressionBuilder();
        EnumSet<AnnotationTarget> enumSetNoneOf = EnumSet.noneOf(AnnotationTarget.class);
        Iterator<? extends JavaAnnotationArgument> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                enumSetNoneOf.getClass();
                List<FirExpression> arguments = firVarargArgumentsExpressionBuilder.getArguments();
                for (AnnotationTarget annotationTarget : enumSetNoneOf) {
                    FirEnumEntryDeserializedAccessExpressionBuilder firEnumEntryDeserializedAccessExpressionBuilder = new FirEnumEntryDeserializedAccessExpressionBuilder();
                    firEnumEntryDeserializedAccessExpressionBuilder.setEnumClassId(StandardClassIds.INSTANCE.getAnnotationTarget());
                    Name nameIdentifier = Name.identifier(annotationTarget.name());
                    nameIdentifier.getClass();
                    firEnumEntryDeserializedAccessExpressionBuilder.setEnumEntryName(nameIdentifier);
                    arguments.add(firEnumEntryDeserializedAccessExpressionBuilder.mo288build());
                }
                ConeClassLikeTypeImpl coneClassLikeTypeImpl = new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getAnnotationTarget()), new ConeTypeProjection[0], false, ConeAttributes.INSTANCE.getEmpty());
                firVarargArgumentsExpressionBuilder.setConeTypeOrNull(ArrayUtilsKt.createOutArrayType$default(coneClassLikeTypeImpl, false, false, 3, null));
                firVarargArgumentsExpressionBuilder.setConeElementTypeOrNull(coneClassLikeTypeImpl);
                return firVarargArgumentsExpressionBuilder.mo288build();
            }
            JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument = (JavaAnnotationArgument) it.next();
            if (!(javaEnumValueAnnotationArgument instanceof JavaEnumValueAnnotationArgument)) {
                return null;
            }
            Map<String, EnumSet<AnnotationTarget>> map = JAVA_TARGETS_TO_KOTLIN;
            Name entryName = javaEnumValueAnnotationArgument.getEntryName();
            EnumSet<AnnotationTarget> enumSet = map.get(entryName != null ? entryName.asString() : null);
            if (enumSet != null) {
                enumSetNoneOf.addAll(enumSet);
            }
        }
    }

    private static final List<FirAnnotation> mergeTargetAnnotations(List<? extends FirAnnotation> list, FirAnnotation firAnnotation, FirAnnotation firAnnotation2) {
        Collection collectionTargetArgumentExpressions;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            FirAnnotation firAnnotation3 = (FirAnnotation) obj;
            if (firAnnotation3 != firAnnotation && firAnnotation3 != firAnnotation2) {
                arrayList.add(obj);
            }
        }
        FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
        firAnnotationBuilder.setSource(firAnnotation2.getSource());
        firAnnotationBuilder.setUseSiteTarget(firAnnotation2.getUseSiteTarget());
        firAnnotationBuilder.setAnnotationTypeRef(firAnnotation2.getAnnotationTypeRef());
        firAnnotationBuilder.setArgumentMapping(firAnnotation2.getArgumentMapping());
        firAnnotationBuilder.getTypeArguments().addAll(firAnnotation2.getTypeArguments());
        FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
        firAnnotationArgumentMappingBuilder.setSource(firAnnotation2.getArgumentMapping().getSource());
        Map<Name, FirExpression> mapping = firAnnotationArgumentMappingBuilder.getMapping();
        Name targetAllowedTargets = StandardClassIds$Annotations.ParameterNames.INSTANCE.getTargetAllowedTargets();
        FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder = new FirVarargArgumentsExpressionBuilder();
        List<FirExpression> arguments = firVarargArgumentsExpressionBuilder.getArguments();
        if (firAnnotation == null) {
            Set<KotlinTarget> set = JAVA_DEFAULT_TARGET_SET;
            collectionTargetArgumentExpressions = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
            for (KotlinTarget kotlinTarget : set) {
                FirEnumEntryDeserializedAccessExpressionBuilder firEnumEntryDeserializedAccessExpressionBuilder = new FirEnumEntryDeserializedAccessExpressionBuilder();
                firEnumEntryDeserializedAccessExpressionBuilder.setEnumClassId(StandardClassIds.INSTANCE.getAnnotationTarget());
                Name nameIdentifier = Name.identifier(kotlinTarget.name());
                nameIdentifier.getClass();
                firEnumEntryDeserializedAccessExpressionBuilder.setEnumEntryName(nameIdentifier);
                collectionTargetArgumentExpressions.add(firEnumEntryDeserializedAccessExpressionBuilder.mo288build());
            }
        } else {
            collectionTargetArgumentExpressions = targetArgumentExpressions(firAnnotation);
        }
        CollectionsKt.addAll(arguments, collectionTargetArgumentExpressions);
        CollectionsKt.addAll(firVarargArgumentsExpressionBuilder.getArguments(), targetArgumentExpressions(firAnnotation2));
        ConeClassLikeTypeImpl coneClassLikeTypeImpl = new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getAnnotationTarget()), new ConeTypeProjection[0], false, ConeAttributes.INSTANCE.getEmpty());
        firVarargArgumentsExpressionBuilder.setConeTypeOrNull(ArrayUtilsKt.createOutArrayType$default(coneClassLikeTypeImpl, false, false, 3, null));
        firVarargArgumentsExpressionBuilder.setConeElementTypeOrNull(coneClassLikeTypeImpl);
        mapping.put(targetAllowedTargets, firVarargArgumentsExpressionBuilder.mo288build());
        firAnnotationBuilder.setArgumentMapping(firAnnotationArgumentMappingBuilder.build());
        return CollectionsKt.plus(arrayList, firAnnotationBuilder.mo288build());
    }

    public static final void setAnnotationsFromJava(FirAnnotationContainer firAnnotationContainer, FirSession firSession, KtSourceElement ktSourceElement, JavaAnnotationOwner javaAnnotationOwner) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        javaAnnotationOwner.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = javaAnnotationOwner.getAnnotations().iterator();
        while (it.hasNext()) {
            arrayList.add(toFirAnnotation((JavaAnnotation) it.next(), firSession, ktSourceElement));
        }
        firAnnotationContainer.replaceAnnotations(arrayList);
    }

    private static final List<FirExpression> targetArgumentExpressions(FirAnnotation firAnnotation) {
        FirExpression firExpression = firAnnotation.getArgumentMapping().getMapping().get(StandardClassIds$Annotations.ParameterNames.INSTANCE.getTargetAllowedTargets());
        if (firExpression instanceof FirVarargArgumentsExpression) {
            return ((FirVarargArgumentsExpression) firExpression).getArguments();
        }
        return firExpression instanceof FirCollectionLiteral ? ((FirCollectionLiteral) firExpression).getArgumentList().getArguments() : CollectionsKt.listOf(firAnnotation);
    }

    private static final FirAnnotation toFirAnnotation(JavaAnnotation javaAnnotation, FirSession firSession, KtSourceElement ktSourceElement) {
        AnnotationData annotationDataBuildFirAnnotation = buildFirAnnotation(javaAnnotation, firSession, ktSourceElement);
        if (javaAnnotation.isIdeExternalAnnotation()) {
            FirJavaExternalAnnotationBuilder firJavaExternalAnnotationBuilder = new FirJavaExternalAnnotationBuilder();
            firJavaExternalAnnotationBuilder.setAnnotationTypeRef(annotationDataBuildFirAnnotation.getAnnotationTypeRef());
            firJavaExternalAnnotationBuilder.setArgumentMapping(annotationDataBuildFirAnnotation.getArgumentsMapping());
            return firJavaExternalAnnotationBuilder.build();
        }
        FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
        firAnnotationBuilder.setAnnotationTypeRef(annotationDataBuildFirAnnotation.getAnnotationTypeRef());
        firAnnotationBuilder.setArgumentMapping(annotationDataBuildFirAnnotation.getArgumentsMapping());
        firAnnotationBuilder.setSource(ktSourceElement);
        return firAnnotationBuilder.mo288build();
    }

    public static final FirExpression toFirExpression(JavaAnnotationArgument javaAnnotationArgument, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, FirTypeRef firTypeRef, KtSourceElement ktSourceElement) {
        FirSession firSession2;
        KtSourceElement ktSourceElement2;
        ConeKotlinType coneKotlinTypeProbablyFlexible$default;
        ConeKotlinType coneKotlinTypeArrayElementType$default;
        FirExpression firExpressionBuild;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible2;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible3;
        javaAnnotationArgument.getClass();
        firSession.getClass();
        javaTypeParameterStack.getClass();
        FirResolvedTypeRef firResolvedTypeRefBuild = null;
        if (firTypeRef != null) {
            firSession2 = firSession;
            ktSourceElement2 = ktSourceElement;
            coneKotlinTypeProbablyFlexible$default = JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(firTypeRef, firSession2, javaTypeParameterStack, ktSourceElement, null, 8, null);
        } else {
            firSession2 = firSession;
            ktSourceElement2 = ktSourceElement;
            coneKotlinTypeProbablyFlexible$default = null;
        }
        if (coneKotlinTypeProbablyFlexible$default == null || (coneRigidTypeLowerBoundIfFlexible3 = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeProbablyFlexible$default)) == null || (coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(coneRigidTypeLowerBoundIfFlexible3, false, 1, null)) == null) {
            coneKotlinTypeArrayElementType$default = coneKotlinTypeProbablyFlexible$default;
        }
        if (javaAnnotationArgument instanceof JavaLiteralAnnotationArgument) {
            firExpressionBuild = JavaUtilsKt.createConstantOrError(((JavaLiteralAnnotationArgument) javaAnnotationArgument).getValue(), firSession2, coneKotlinTypeArrayElementType$default);
        } else if (javaAnnotationArgument instanceof JavaArrayAnnotationArgument) {
            FirCollectionLiteralBuilder firCollectionLiteralBuilder = new FirCollectionLiteralBuilder();
            if (coneKotlinTypeProbablyFlexible$default != null) {
                firCollectionLiteralBuilder.setConeTypeOrNull(coneKotlinTypeProbablyFlexible$default);
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                ConeKotlinType coneKotlinTypeArrayElementType$default2 = FirTypeUtilsKt.arrayElementType$default(ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeProbablyFlexible$default), false, 1, null);
                if (coneKotlinTypeArrayElementType$default2 == null) {
                    coneKotlinTypeArrayElementType$default2 = new ConeErrorType(new ConeSimpleDiagnostic("expected type is not array type", null, 2, null), false, null, null, null, null, null, 126, null);
                }
                firResolvedTypeRefBuilder.setConeType(coneKotlinTypeArrayElementType$default2);
                firResolvedTypeRefBuild = firResolvedTypeRefBuilder.build();
            }
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            List elements = ((JavaArrayAnnotationArgument) javaAnnotationArgument).getElements();
            List<FirExpression> arguments = firArgumentListBuilder.getArguments();
            Iterator it = elements.iterator();
            while (it.hasNext()) {
                arguments.add(toFirExpression((JavaAnnotationArgument) it.next(), firSession2, javaTypeParameterStack, firResolvedTypeRefBuild, ktSourceElement2));
            }
            firCollectionLiteralBuilder.setArgumentList(firArgumentListBuilder.build());
            firExpressionBuild = firCollectionLiteralBuilder.mo288build();
        } else if (javaAnnotationArgument instanceof JavaEnumValueAnnotationArgument) {
            JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument = (JavaEnumValueAnnotationArgument) javaAnnotationArgument;
            ClassId enumClassId = javaEnumValueAnnotationArgument.getEnumClassId();
            if (enumClassId == null) {
                enumClassId = (coneKotlinTypeArrayElementType$default == null || (coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeArrayElementType$default)) == null) ? null : ConeTypeUtilsKt.getClassId(coneRigidTypeLowerBoundIfFlexible);
            }
            if (enumClassId == null) {
                w01.a("Required value was null.");
                return null;
            }
            FirEnumEntryDeserializedAccessExpressionBuilder firEnumEntryDeserializedAccessExpressionBuilder = new FirEnumEntryDeserializedAccessExpressionBuilder();
            firEnumEntryDeserializedAccessExpressionBuilder.setEnumClassId(enumClassId);
            Name entryName = javaEnumValueAnnotationArgument.getEntryName();
            if (entryName == null) {
                entryName = SpecialNames.NO_NAME_PROVIDED;
            }
            firEnumEntryDeserializedAccessExpressionBuilder.setEnumEntryName(entryName);
            firExpressionBuild = firEnumEntryDeserializedAccessExpressionBuilder.mo288build();
        } else if (javaAnnotationArgument instanceof JavaClassObjectAnnotationArgument) {
            FirGetClassCallBuilder firGetClassCallBuilder = new FirGetClassCallBuilder();
            FirTypeRef firResolvedTypeRef$default = JavaTypeConversionKt.toFirResolvedTypeRef$default(((JavaClassObjectAnnotationArgument) javaAnnotationArgument).getReferencedType(), firSession2, javaTypeParameterStack, ktSourceElement2, null, 8, null);
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder2.setConeType(TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getKClass(), new ConeKotlinType[]{firResolvedTypeRef$default.getConeType()}, false, null, 4, null));
            FirResolvedTypeRef firResolvedTypeRefBuild2 = firResolvedTypeRefBuilder2.build();
            FirClassReferenceExpressionBuilder firClassReferenceExpressionBuilder = new FirClassReferenceExpressionBuilder();
            firClassReferenceExpressionBuilder.setClassTypeRef(firResolvedTypeRef$default);
            firClassReferenceExpressionBuilder.setConeTypeOrNull(firResolvedTypeRefBuild2.getConeType());
            firGetClassCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firClassReferenceExpressionBuilder.mo288build()));
            firGetClassCallBuilder.setConeTypeOrNull(firResolvedTypeRefBuild2.getConeType());
            firExpressionBuild = firGetClassCallBuilder.mo288build();
        } else if (javaAnnotationArgument instanceof JavaAnnotationAsAnnotationArgument) {
            firExpressionBuild = toFirAnnotation(((JavaAnnotationAsAnnotationArgument) javaAnnotationArgument).getAnnotation(), firSession2, ktSourceElement2);
        } else {
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic("Unknown JavaAnnotationArgument: " + FirErrorExpressionBuilder.class, DiagnosticKind.Java));
            firExpressionBuild = firErrorExpressionBuilder.mo288build();
        }
        if (coneKotlinTypeProbablyFlexible$default == null || (coneRigidTypeLowerBoundIfFlexible2 = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeProbablyFlexible$default)) == null || !ArrayUtilsKt.isArrayOrPrimitiveArray(coneRigidTypeLowerBoundIfFlexible2) || (firExpressionBuild instanceof FirCollectionLiteral)) {
            return firExpressionBuild;
        }
        FirCollectionLiteralBuilder firCollectionLiteralBuilder2 = new FirCollectionLiteralBuilder();
        firCollectionLiteralBuilder2.setConeTypeOrNull(coneKotlinTypeProbablyFlexible$default);
        FirArgumentListBuilder firArgumentListBuilder2 = new FirArgumentListBuilder();
        firArgumentListBuilder2.getArguments().add(firExpressionBuild);
        firCollectionLiteralBuilder2.setArgumentList(firArgumentListBuilder2.build());
        return firCollectionLiteralBuilder2.mo288build();
    }

    public static final FirValueParameter toFirValueParameter(JavaValueParameter javaValueParameter, FirSession firSession, FirFunctionSymbol<?> firFunctionSymbol, FirModuleData firModuleData, int i) {
        javaValueParameter.getClass();
        firSession.getClass();
        firFunctionSymbol.getClass();
        firModuleData.getClass();
        FirJavaValueParameterBuilder firJavaValueParameterBuilder = new FirJavaValueParameterBuilder();
        firJavaValueParameterBuilder.setSource(FirJavaFacadeKt.toSourceElement$default(javaValueParameter, null, 1, null));
        firJavaValueParameterBuilder.setFromSource(javaValueParameter.isFromSource());
        firJavaValueParameterBuilder.setModuleData(firModuleData);
        firJavaValueParameterBuilder.setContainingDeclarationSymbol(firFunctionSymbol);
        Name nameOrGeneratedName = javaValueParameter.getNameOrGeneratedName();
        if (nameOrGeneratedName == null) {
            nameOrGeneratedName = Name.identifier("p" + i);
            nameOrGeneratedName.getClass();
        }
        firJavaValueParameterBuilder.setName(nameOrGeneratedName);
        firJavaValueParameterBuilder.setReturnTypeRef(JavaTypeConversionKt.toFirJavaTypeRef(javaValueParameter.getType(), firSession, firJavaValueParameterBuilder.getSource()));
        firJavaValueParameterBuilder.setVararg(javaValueParameter.getIsVararg());
        firJavaValueParameterBuilder.setAnnotationList(new FirLazyJavaAnnotationList(javaValueParameter, firModuleData));
        return firJavaValueParameterBuilder.build();
    }

    public static final List<FirAnnotation> convertAnnotationsToFir(JavaAnnotationOwner javaAnnotationOwner, FirSession firSession, KtSourceElement ktSourceElement) {
        javaAnnotationOwner.getClass();
        firSession.getClass();
        return convertAnnotationsToFir(javaAnnotationOwner.getAnnotations(), firSession, ktSourceElement, javaAnnotationOwner.isDeprecatedInJavaDoc());
    }

    public static final List<FirAnnotation> convertAnnotationsToFir(Iterable<? extends JavaAnnotation> iterable, FirSession firSession, KtSourceElement ktSourceElement) {
        iterable.getClass();
        firSession.getClass();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<? extends JavaAnnotation> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(toFirAnnotation(it.next(), firSession, ktSourceElement));
        }
        return arrayList;
    }

    private static final Map<Name, FirTypeRef> annotationParametersMapping(JavaClass javaClass, FirSession firSession, KtSourceElement ktSourceElement) {
        Collection methods;
        LinkedHashMap linkedHashMap = null;
        if (!javaClass.isAnnotationType()) {
            javaClass = null;
        }
        if (javaClass != null && (methods = javaClass.getMethods()) != null) {
            Collection<JavaMethod> collection = methods;
            linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection, 10)), 16));
            for (JavaMethod javaMethod : collection) {
                Pair pair = TuplesKt.to(javaMethod.getName(), JavaTypeConversionKt.toFirJavaTypeRef(javaMethod.getReturnType(), firSession, ktSourceElement));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
        }
        return linkedHashMap;
    }
}

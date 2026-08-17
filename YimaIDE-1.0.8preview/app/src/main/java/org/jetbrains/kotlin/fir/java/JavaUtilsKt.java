package org.jetbrains.kotlin.fir.java;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCollectionLiteralBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorExpressionBuilder;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveUtilsKt;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.load.java.JavaNullabilityAnnotationSettingsKt;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaField;
import org.jetbrains.kotlin.load.java.structure.JavaModifierListOwner;
import org.jetbrains.kotlin.load.java.structure.JavaWildcardType;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\u0005\u001a\"\u0010\r\u001a\u00020\u000e*\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0000\u001a\"\u0010\u0014\u001a\u0004\u0018\u00010\u000e*\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\fH\u0000\u001a(\u0010\u0016\u001a\u00020\u0017\"\u0004\b\u0000\u0010\u0018*\b\u0012\u0004\u0012\u0002H\u00180\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001a\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f\u001a\u0012\u0010 \u001a\u00020\f*\u00020!2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\"\u001a\u00020\f*\u00020#2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010$\u001a\u0004\u0018\u00010#*\u00020%2\u0006\u0010&\u001a\u00020'H\u0002\u001a\f\u0010(\u001a\u0004\u0018\u00010#*\u00020%\u001a\f\u0010)\u001a\u0004\u0018\u00010#*\u00020%\u001a\f\u0010*\u001a\u0004\u0018\u00010+*\u00020%\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006,"}, d2 = {"modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "Lorg/jetbrains/kotlin/load/java/structure/JavaModifierListOwner;", "getModality", "(Lorg/jetbrains/kotlin/load/java/structure/JavaModifierListOwner;)Lorg/jetbrains/kotlin/descriptors/Modality;", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "(Lorg/jetbrains/kotlin/load/java/structure/JavaClass;)Lorg/jetbrains/kotlin/descriptors/Modality;", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getClassKind", "(Lorg/jetbrains/kotlin/load/java/structure/JavaClass;)Lorg/jetbrains/kotlin/descriptors/ClassKind;", "hasMetadataAnnotation", Argument.Delimiters.none, "createConstantOrError", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "expectedConeType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "createConstantIfAny", "unsigned", "createArrayLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "T", Argument.Delimiters.none, "kind", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "extractNullabilityAnnotationOnBoundedWildcard", "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "wildcardType", "Lorg/jetbrains/kotlin/load/java/structure/JavaWildcardType;", "hasJvmFieldAnnotation", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "isJvmFieldAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "findAnnotationByClassId", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "findJvmNameAnnotation", "findJvmStaticAnnotation", "findJvmNameValue", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaUtilsKt {
    private static final <T> FirCollectionLiteral createArrayLiteral(List<? extends T> list, FirSession firSession, ConstantValueKind constantValueKind) {
        FirCollectionLiteralBuilder firCollectionLiteralBuilder = new FirCollectionLiteralBuilder();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        Iterator<? extends T> it = list.iterator();
        while (it.hasNext()) {
            firArgumentListBuilder.getArguments().add(createConstantOrError$default(it.next(), firSession, null, 2, null));
        }
        firCollectionLiteralBuilder.setArgumentList(firArgumentListBuilder.build());
        firCollectionLiteralBuilder.setConeTypeOrNull(ArrayUtilsKt.createArrayType$default(BodyResolveUtilsKt.expectedConeType(constantValueKind, firSession), false, false, 3, null));
        return firCollectionLiteralBuilder.mo288build();
    }

    public static final FirExpression createConstantIfAny(Object obj, FirSession firSession, boolean z) {
        firSession.getClass();
        if (obj instanceof Byte) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, z ? ConstantValueKind.UnsignedByte.INSTANCE : ConstantValueKind.Byte.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof Short) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, z ? ConstantValueKind.UnsignedShort.INSTANCE : ConstantValueKind.Short.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof Integer) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, z ? ConstantValueKind.UnsignedInt.INSTANCE : ConstantValueKind.Int.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof Long) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, z ? ConstantValueKind.UnsignedLong.INSTANCE : ConstantValueKind.Long.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof Character) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.Char.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof Float) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.Float.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof Double) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.Double.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof Boolean) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.Boolean.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof String) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.String.INSTANCE, obj, null, true, null, 40, null);
        }
        if (obj instanceof byte[]) {
            return createArrayLiteral(ArraysKt.toList((byte[]) obj), firSession, ConstantValueKind.Byte.INSTANCE);
        }
        if (obj instanceof short[]) {
            return createArrayLiteral(ArraysKt.toList((short[]) obj), firSession, ConstantValueKind.Short.INSTANCE);
        }
        if (obj instanceof int[]) {
            return createArrayLiteral(ArraysKt.toList((int[]) obj), firSession, ConstantValueKind.Int.INSTANCE);
        }
        if (obj instanceof long[]) {
            return createArrayLiteral(ArraysKt.toList((long[]) obj), firSession, ConstantValueKind.Long.INSTANCE);
        }
        if (obj instanceof char[]) {
            return createArrayLiteral(ArraysKt.toList((char[]) obj), firSession, ConstantValueKind.Char.INSTANCE);
        }
        if (obj instanceof float[]) {
            return createArrayLiteral(ArraysKt.toList((float[]) obj), firSession, ConstantValueKind.Float.INSTANCE);
        }
        if (obj instanceof double[]) {
            return createArrayLiteral(ArraysKt.toList((double[]) obj), firSession, ConstantValueKind.Double.INSTANCE);
        }
        if (obj instanceof boolean[]) {
            return createArrayLiteral(ArraysKt.toList((boolean[]) obj), firSession, ConstantValueKind.Boolean.INSTANCE);
        }
        if (obj == null) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.Null.INSTANCE, null, null, true, null, 40, null);
        }
        return null;
    }

    public static /* synthetic */ FirExpression createConstantIfAny$default(Object obj, FirSession firSession, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            z = false;
        }
        return createConstantIfAny(obj, firSession, z);
    }

    public static final FirExpression createConstantOrError(Object obj, FirSession firSession, ConeKotlinType coneKotlinType) {
        firSession.getClass();
        if ((obj instanceof Integer) && coneKotlinType != null) {
            if (ConeBuiltinTypeUtilsKt.isByte(coneKotlinType)) {
                obj = Byte.valueOf((byte) ((Number) obj).intValue());
            } else if (ConeBuiltinTypeUtilsKt.isShort(coneKotlinType)) {
                obj = Short.valueOf((short) ((Number) obj).intValue());
            } else if (ConeBuiltinTypeUtilsKt.isLong(coneKotlinType)) {
                obj = Long.valueOf(((Number) obj).intValue());
            }
        }
        FirExpression firExpressionCreateConstantIfAny$default = createConstantIfAny$default(obj, firSession, false, 2, null);
        if (firExpressionCreateConstantIfAny$default != null) {
            return firExpressionCreateConstantIfAny$default;
        }
        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
        firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic("Unknown value in JavaLiteralAnnotationArgument: " + firErrorExpressionBuilder, DiagnosticKind.Java));
        return firErrorExpressionBuilder.mo288build();
    }

    public static /* synthetic */ FirExpression createConstantOrError$default(Object obj, FirSession firSession, ConeKotlinType coneKotlinType, int i, Object obj2) {
        if ((i & 2) != 0) {
            coneKotlinType = null;
        }
        return createConstantOrError(obj, firSession, coneKotlinType);
    }

    public static final JavaAnnotation extractNullabilityAnnotationOnBoundedWildcard(JavaWildcardType javaWildcardType) {
        javaWildcardType.getClass();
        Object obj = null;
        if (javaWildcardType.getBound() == null) {
            w01.a("Nullability annotations on unbounded wildcards aren't supported");
            return null;
        }
        loop0: for (Object obj2 : javaWildcardType.getAnnotations()) {
            JavaAnnotation javaAnnotation = (JavaAnnotation) obj2;
            for (FqName fqName : JavaNullabilityAnnotationSettingsKt.getRXJAVA3_ANNOTATIONS()) {
                ClassId classId = javaAnnotation.getClassId();
                if (Intrinsics.areEqual(classId != null ? classId.asSingleFqName() : null, fqName)) {
                    obj = obj2;
                    break loop0;
                }
            }
        }
        return (JavaAnnotation) obj;
    }

    private static final FirAnnotation findAnnotationByClassId(FirDeclaration firDeclaration, ClassId classId) {
        Object obj;
        Object next;
        ConeKotlinType coneTypeOrNull;
        Iterator<T> it = firDeclaration.getAnnotations().iterator();
        do {
            obj = null;
            if (it.hasNext()) {
                next = it.next();
                coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(((FirAnnotation) next).getAnnotationTypeRef());
            }
            return (FirAnnotation) obj;
        } while (!Intrinsics.areEqual(coneTypeOrNull != null ? ConeTypeUtilsKt.getClassId(coneTypeOrNull) : null, classId));
        obj = next;
        return (FirAnnotation) obj;
    }

    public static final FirAnnotation findJvmNameAnnotation(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return findAnnotationByClassId(firDeclaration, JvmStandardClassIds.Annotations.INSTANCE.getJvmName());
    }

    public static final String findJvmNameValue(FirDeclaration firDeclaration) {
        FirExpression firExpressionFindArgumentByName$default;
        firDeclaration.getClass();
        FirAnnotation firAnnotationFindJvmNameAnnotation = findJvmNameAnnotation(firDeclaration);
        if (firAnnotationFindJvmNameAnnotation != null && (firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotationFindJvmNameAnnotation, StandardNames.NAME, false, 2, null)) != null) {
            FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName$default instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName$default : null;
            Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
            if (value instanceof String) {
                return (String) value;
            }
        }
        return null;
    }

    public static final FirAnnotation findJvmStaticAnnotation(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return findAnnotationByClassId(firDeclaration, JvmStandardClassIds.Annotations.INSTANCE.getJvmStatic());
    }

    public static final ClassKind getClassKind(JavaClass javaClass) {
        javaClass.getClass();
        if (javaClass.isAnnotationType()) {
            return ClassKind.ANNOTATION_CLASS;
        }
        if (javaClass.isInterface()) {
            return ClassKind.INTERFACE;
        }
        return javaClass.isEnum() ? ClassKind.ENUM_CLASS : ClassKind.CLASS;
    }

    public static final Modality getModality(JavaClass javaClass) {
        javaClass.getClass();
        if (javaClass.isAnnotationType() || javaClass.isEnum()) {
            return Modality.FINAL;
        }
        if (javaClass.isSealed()) {
            return Modality.SEALED;
        }
        if (javaClass.isAbstract()) {
            return Modality.ABSTRACT;
        }
        return javaClass.isFinal() ? Modality.FINAL : Modality.OPEN;
    }

    public static final boolean hasJvmFieldAnnotation(FirProperty firProperty, FirSession firSession) {
        List<FirAnnotation> annotations;
        firProperty.getClass();
        firSession.getClass();
        FirBackingField backingField = firProperty.getBackingField();
        if (backingField != null && (annotations = backingField.getAnnotations()) != null) {
            List<FirAnnotation> list = annotations;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (isJvmFieldAnnotation((FirAnnotation) it.next(), firSession)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final boolean hasMetadataAnnotation(JavaClass javaClass) {
        javaClass.getClass();
        Collection<JavaAnnotation> annotations = javaClass.getAnnotations();
        if ((annotations instanceof Collection) && annotations.isEmpty()) {
            return false;
        }
        for (JavaAnnotation javaAnnotation : annotations) {
            FqName fqName = JvmAnnotationNames.METADATA_FQ_NAME;
            fqName.getClass();
            if (javaAnnotation.isResolvedTo(fqName)) {
                Collection arguments = javaAnnotation.getArguments();
                if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
                    Iterator it = arguments.iterator();
                    while (it.hasNext()) {
                        Name name = ((JavaAnnotationArgument) it.next()).getName();
                        if (Intrinsics.areEqual(name != null ? name.asString() : null, "k")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static final boolean isJvmFieldAnnotation(FirAnnotation firAnnotation, FirSession firSession) {
        firAnnotation.getClass();
        firSession.getClass();
        return Intrinsics.areEqual(FirAnnotationUtilsKt.toAnnotationClassId(firAnnotation, firSession), JvmStandardClassIds.Annotations.INSTANCE.getJvmField());
    }

    public static final Modality getModality(JavaModifierListOwner javaModifierListOwner) {
        javaModifierListOwner.getClass();
        if (javaModifierListOwner instanceof JavaField) {
            return Modality.FINAL;
        }
        if (javaModifierListOwner.isAbstract()) {
            return Modality.ABSTRACT;
        }
        return javaModifierListOwner.isFinal() ? Modality.FINAL : Modality.OPEN;
    }
}

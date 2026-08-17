package org.jetbrains.kotlin.descriptors.runtime.components;

import defpackage.p9c;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.runtime.structure.ReflectClassUtilKt;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.constants.ClassLiteralValue;
import org.jetbrains.kotlin.resolve.jvm.JvmPrimitiveType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tJ\u001a\u0010\n\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\r\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001c\u0010\u000e\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001c\u0010\u000f\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J$\u0010\u0013\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002J\u0010\u0010\u0016\u001a\u00020\u0017*\u0006\u0012\u0002\b\u00030\u0007H\u0002J \u0010\u0018\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0001H\u0002¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectClassStructure;", Argument.Delimiters.none, "<init>", "()V", "loadClassAnnotations", Argument.Delimiters.none, "klass", "Ljava/lang/Class;", "visitor", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationVisitor;", "visitMembers", "memberVisitor", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$MemberVisitor;", "loadMethodAnnotations", "loadConstructorAnnotations", "loadFieldAnnotations", "processAnnotation", "annotation", Argument.Delimiters.none, "processAnnotationArguments", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "annotationType", "classLiteralValue", "Lorg/jetbrains/kotlin/resolve/constants/ClassLiteralValue;", "processAnnotationArgumentValue", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "value", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ReflectClassStructure {
    public static final ReflectClassStructure INSTANCE = new ReflectClassStructure();

    private ReflectClassStructure() {
    }

    private final ClassLiteralValue classLiteralValue(Class<?> cls) {
        int i = 0;
        while (cls.isArray()) {
            i++;
            cls = cls.getComponentType();
            cls.getClass();
        }
        if (cls.isPrimitive()) {
            if (Intrinsics.areEqual(cls, Void.TYPE)) {
                return new ClassLiteralValue(ClassId.Companion.topLevel(StandardNames.FqNames.unit.toSafe()), i);
            }
            PrimitiveType primitiveType = JvmPrimitiveType.get(cls.getName()).getPrimitiveType();
            primitiveType.getClass();
            return i > 0 ? new ClassLiteralValue(ClassId.Companion.topLevel(primitiveType.getArrayTypeFqName()), i - 1) : new ClassLiteralValue(ClassId.Companion.topLevel(primitiveType.getTypeFqName()), i);
        }
        ClassId classId = ReflectClassUtilKt.getClassId(cls);
        ClassId classIdMapJavaToKotlin = JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(classId.asSingleFqName());
        if (classIdMapJavaToKotlin != null) {
            classId = classIdMapJavaToKotlin;
        }
        return new ClassLiteralValue(classId, i);
    }

    private final void loadConstructorAnnotations(Class<?> klass, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws InvocationTargetException {
        Constructor<?>[] constructorArr;
        int i;
        Constructor<?>[] declaredConstructors = klass.getDeclaredConstructors();
        declaredConstructors.getClass();
        int length = declaredConstructors.length;
        int i2 = 0;
        while (i2 < length) {
            Constructor<?> constructor = declaredConstructors[i2];
            Name name = SpecialNames.INIT;
            SignatureSerializer signatureSerializer = SignatureSerializer.INSTANCE;
            constructor.getClass();
            KotlinJvmBinaryClass.MethodAnnotationVisitor methodAnnotationVisitorVisitMethod = memberVisitor.visitMethod(name, signatureSerializer.constructorDesc(constructor));
            if (methodAnnotationVisitorVisitMethod == null) {
                constructorArr = declaredConstructors;
                i = length;
            } else {
                Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();
                declaredAnnotations.getClass();
                for (Annotation annotation : declaredAnnotations) {
                    annotation.getClass();
                    processAnnotation(methodAnnotationVisitorVisitMethod, annotation);
                }
                Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                parameterAnnotations.getClass();
                if (!(parameterAnnotations.length == 0)) {
                    int length2 = constructor.getParameterTypes().length - parameterAnnotations.length;
                    int length3 = parameterAnnotations.length;
                    for (int i3 = 0; i3 < length3; i3++) {
                        Annotation[] annotationArr = parameterAnnotations[i3];
                        annotationArr.getClass();
                        int length4 = annotationArr.length;
                        int i4 = 0;
                        while (i4 < length4) {
                            Annotation annotation2 = annotationArr[i4];
                            Class<?> javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2));
                            Constructor<?>[] constructorArr2 = declaredConstructors;
                            ClassId classId = ReflectClassUtilKt.getClassId(javaClass);
                            int i5 = length;
                            annotation2.getClass();
                            KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitParameterAnnotation = methodAnnotationVisitorVisitMethod.visitParameterAnnotation(i3 + length2, classId, new ReflectAnnotationSource(annotation2));
                            if (annotationArgumentVisitorVisitParameterAnnotation != null) {
                                INSTANCE.processAnnotationArguments(annotationArgumentVisitorVisitParameterAnnotation, annotation2, javaClass);
                            }
                            i4++;
                            declaredConstructors = constructorArr2;
                            length = i5;
                        }
                    }
                }
                constructorArr = declaredConstructors;
                i = length;
                methodAnnotationVisitorVisitMethod.visitEnd();
            }
            i2++;
            declaredConstructors = constructorArr;
            length = i;
        }
    }

    private final void loadFieldAnnotations(Class<?> klass, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws InvocationTargetException {
        Field[] declaredFields = klass.getDeclaredFields();
        declaredFields.getClass();
        for (Field field : declaredFields) {
            Name nameIdentifier = Name.identifier(field.getName());
            nameIdentifier.getClass();
            KotlinJvmBinaryClass.AnnotationVisitor annotationVisitorVisitField = memberVisitor.visitField(nameIdentifier, SignatureSerializer.INSTANCE.fieldDesc(field), (Object) null);
            if (annotationVisitorVisitField != null) {
                Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                declaredAnnotations.getClass();
                for (Annotation annotation : declaredAnnotations) {
                    annotation.getClass();
                    processAnnotation(annotationVisitorVisitField, annotation);
                }
                annotationVisitorVisitField.visitEnd();
            }
        }
    }

    private final void loadMethodAnnotations(Class<?> klass, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws InvocationTargetException {
        Method[] declaredMethods = klass.getDeclaredMethods();
        declaredMethods.getClass();
        for (Method method : declaredMethods) {
            Name nameIdentifier = Name.identifier(method.getName());
            nameIdentifier.getClass();
            KotlinJvmBinaryClass.MethodAnnotationVisitor methodAnnotationVisitorVisitMethod = memberVisitor.visitMethod(nameIdentifier, SignatureSerializer.INSTANCE.methodDesc(method));
            if (methodAnnotationVisitorVisitMethod != null) {
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                declaredAnnotations.getClass();
                for (Annotation annotation : declaredAnnotations) {
                    annotation.getClass();
                    processAnnotation(methodAnnotationVisitorVisitMethod, annotation);
                }
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                parameterAnnotations.getClass();
                Annotation[][] annotationArr = parameterAnnotations;
                int length = annotationArr.length;
                for (int i = 0; i < length; i++) {
                    Annotation[] annotationArr2 = annotationArr[i];
                    annotationArr2.getClass();
                    for (Annotation annotation2 : annotationArr2) {
                        Class<?> javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation2));
                        ClassId classId = ReflectClassUtilKt.getClassId(javaClass);
                        annotation2.getClass();
                        KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitParameterAnnotation = methodAnnotationVisitorVisitMethod.visitParameterAnnotation(i, classId, new ReflectAnnotationSource(annotation2));
                        if (annotationArgumentVisitorVisitParameterAnnotation != null) {
                            INSTANCE.processAnnotationArguments(annotationArgumentVisitorVisitParameterAnnotation, annotation2, javaClass);
                        }
                    }
                }
                methodAnnotationVisitorVisitMethod.visitEnd();
            }
        }
    }

    private final void processAnnotation(KotlinJvmBinaryClass.AnnotationVisitor visitor, Annotation annotation) throws InvocationTargetException {
        Class<?> javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation));
        KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitAnnotation = visitor.visitAnnotation(ReflectClassUtilKt.getClassId(javaClass), new ReflectAnnotationSource(annotation));
        if (annotationArgumentVisitorVisitAnnotation != null) {
            INSTANCE.processAnnotationArguments(annotationArgumentVisitorVisitAnnotation, annotation, javaClass);
        }
    }

    private final void processAnnotationArgumentValue(KotlinJvmBinaryClass.AnnotationArgumentVisitor visitor, Name name, Object value) throws InvocationTargetException {
        Class<?> enclosingClass = value.getClass();
        if (Intrinsics.areEqual(enclosingClass, Class.class)) {
            visitor.visitClassLiteral(name, classLiteralValue((Class) value));
            return;
        }
        if (ReflectKotlinClassKt.TYPES_ELIGIBLE_FOR_SIMPLE_VISIT.contains(enclosingClass)) {
            visitor.visit(name, value);
            return;
        }
        if (ReflectClassUtilKt.isEnumClassOrSpecializedEnumEntryClass(enclosingClass)) {
            if (!enclosingClass.isEnum()) {
                enclosingClass = enclosingClass.getEnclosingClass();
            }
            enclosingClass.getClass();
            ClassId classId = ReflectClassUtilKt.getClassId(enclosingClass);
            Name nameIdentifier = Name.identifier(((Enum) value).name());
            nameIdentifier.getClass();
            visitor.visitEnum(name, classId, nameIdentifier);
            return;
        }
        if (Annotation.class.isAssignableFrom(enclosingClass)) {
            Class<?>[] interfaces = enclosingClass.getInterfaces();
            interfaces.getClass();
            Class<?> cls = (Class) ArraysKt.single(interfaces);
            cls.getClass();
            KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitAnnotation = visitor.visitAnnotation(name, ReflectClassUtilKt.getClassId(cls));
            if (annotationArgumentVisitorVisitAnnotation == null) {
                return;
            }
            processAnnotationArguments(annotationArgumentVisitorVisitAnnotation, (Annotation) value, cls);
            return;
        }
        if (!enclosingClass.isArray()) {
            p9c.a("Unsupported annotation argument value (", enclosingClass, "): ", value);
            return;
        }
        KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor annotationArrayArgumentVisitorVisitArray = visitor.visitArray(name);
        if (annotationArrayArgumentVisitorVisitArray == null) {
            return;
        }
        Class<?> componentType = enclosingClass.getComponentType();
        int i = 0;
        if (componentType.isEnum()) {
            ClassId classId2 = ReflectClassUtilKt.getClassId(componentType);
            Object[] objArr = (Object[]) value;
            int length = objArr.length;
            while (i < length) {
                Object obj = objArr[i];
                obj.getClass();
                Name nameIdentifier2 = Name.identifier(((Enum) obj).name());
                nameIdentifier2.getClass();
                annotationArrayArgumentVisitorVisitArray.visitEnum(classId2, nameIdentifier2);
                i++;
            }
        } else if (Intrinsics.areEqual(componentType, Class.class)) {
            Object[] objArr2 = (Object[]) value;
            int length2 = objArr2.length;
            while (i < length2) {
                Object obj2 = objArr2[i];
                obj2.getClass();
                annotationArrayArgumentVisitorVisitArray.visitClassLiteral(classLiteralValue((Class) obj2));
                i++;
            }
        } else if (Annotation.class.isAssignableFrom(componentType)) {
            Object[] objArr3 = (Object[]) value;
            int length3 = objArr3.length;
            while (i < length3) {
                Object obj3 = objArr3[i];
                KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorVisitAnnotation2 = annotationArrayArgumentVisitorVisitArray.visitAnnotation(ReflectClassUtilKt.getClassId(componentType));
                if (annotationArgumentVisitorVisitAnnotation2 != null) {
                    obj3.getClass();
                    processAnnotationArguments(annotationArgumentVisitorVisitAnnotation2, (Annotation) obj3, componentType);
                }
                i++;
            }
        } else {
            Object[] objArr4 = (Object[]) value;
            int length4 = objArr4.length;
            while (i < length4) {
                annotationArrayArgumentVisitorVisitArray.visit(objArr4[i]);
                i++;
            }
        }
        annotationArrayArgumentVisitorVisitArray.visitEnd();
    }

    private final void processAnnotationArguments(KotlinJvmBinaryClass.AnnotationArgumentVisitor visitor, Annotation annotation, Class<?> annotationType) throws InvocationTargetException {
        Method[] declaredMethods = annotationType.getDeclaredMethods();
        declaredMethods.getClass();
        for (Method method : declaredMethods) {
            try {
                Object objInvoke = method.invoke(annotation, null);
                objInvoke.getClass();
                Name nameIdentifier = Name.identifier(method.getName());
                nameIdentifier.getClass();
                processAnnotationArgumentValue(visitor, nameIdentifier, objInvoke);
            } catch (IllegalAccessException unused) {
            }
        }
        visitor.visitEnd();
    }

    public final void loadClassAnnotations(Class<?> klass, KotlinJvmBinaryClass.AnnotationVisitor visitor) throws InvocationTargetException {
        klass.getClass();
        visitor.getClass();
        Annotation[] declaredAnnotations = klass.getDeclaredAnnotations();
        declaredAnnotations.getClass();
        for (Annotation annotation : declaredAnnotations) {
            annotation.getClass();
            processAnnotation(visitor, annotation);
        }
        visitor.visitEnd();
    }

    public final void visitMembers(Class<?> klass, KotlinJvmBinaryClass.MemberVisitor memberVisitor) throws InvocationTargetException {
        klass.getClass();
        memberVisitor.getClass();
        loadMethodAnnotations(klass, memberVisitor);
        loadConstructorAnnotations(klass, memberVisitor);
        loadFieldAnnotations(klass, memberVisitor);
    }
}

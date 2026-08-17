package kotlin.jvm;

import java.lang.annotation.Annotation;
import javax.xml.transform.OutputKeys;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.library.SearchPathResolverKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\u0010\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a#\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u000b*\u0006\u0012\u0002\b\u00030#H\u0086\u0080\u0004¢\u0006\u0002\u0010$\"?\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038GX\u0086\u0084\bz\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0006¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"1\u0010\n\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u000b*\b\u0012\u0004\u0012\u0002H\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\u0007\"/\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u000b*\b\u0012\u0004\u0012\u0002H\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0007\"=\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\b\b\u0000\u0010\u0002*\u00020\u000b*\b\u0012\u0004\u0012\u0002H\u00020\u00018GX\u0086\u0084\bz\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0010¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"*\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u000b*\u0002H\u00028Æ\u0002X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0013\"\u0082\u0001\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u000b*\b\u0012\u0004\u0012\u0002H\u00020\u00038Ç\u0002X\u0087\u0084\br6\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u001c\b\u0019\u0012\u0018\b\u000bB\u0014\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0006\b\u001d\u0012\u0002\b\f\u0012\n\b\u001e\u0012\u0006\b\n0\u001f8 z\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0015¢\u0006\f\u0012\u0004\b\u0014\u0010\u0005\u001a\u0004\b\u0015\u0010\u0007\"+\u0010%\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003\"\b\b\u0000\u0010\u0002*\u00020&*\u0002H\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b'\u0010(\"N\u0010)\u001a\b\u0012\u0004\u0012\u0002H*0\u0001\"\u000e\b\u0000\u0010**\b\u0012\u0004\u0012\u0002H*0+*\b\u0012\u0004\u0012\u0002H*0+8Æ\u0002X\u0087\u0084\br\f\b0\u0012\b\b1\u0012\u0004\b\b(2r\u0002\b3¢\u0006\f\u0012\u0004\b,\u0010-\u001a\u0004\b.\u0010/¨\u00064"}, d2 = {"java", "Ljava/lang/Class;", "T", "Lkotlin/reflect/KClass;", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "Lkotlin/jvm/JvmName;", "name", "javaPrimitiveType", "", "getJavaPrimitiveType", "javaObjectType", "getJavaObjectType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "javaClass", "(Ljava/lang/Object;)Ljava/lang/Class;", "getRuntimeClassOfKClassInstance$annotations", "getRuntimeClassOfKClassInstance", "Lkotlin/Deprecated;", "message", "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "(this as Any).javaClass", "imports", "level", "Lkotlin/DeprecationLevel;", "ERROR", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "annotationClass", "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "declaringJavaClass", "E", "", "getDeclaringJavaClass$annotations", "(Ljava/lang/Enum;)V", "getDeclaringJavaClass", "(Ljava/lang/Enum;)Ljava/lang/Class;", "Lkotlin/SinceKotlin;", OutputKeys.VERSION, "1.7", "Lkotlin/internal/InlineOnly;", SearchPathResolverKt.KOTLIN_JKLIB_STDLIB_NAME}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmClassMappingKt {
    public static final <T extends Annotation> KClass<? extends T> getAnnotationClass(T t) {
        t.getClass();
        Class<? extends Annotation> clsAnnotationType = t.annotationType();
        clsAnnotationType.getClass();
        KClass<? extends T> kotlinClass = getKotlinClass(clsAnnotationType);
        kotlinClass.getClass();
        return kotlinClass;
    }

    private static final <E extends Enum<E>> Class<E> getDeclaringJavaClass(Enum<E> r0) {
        r0.getClass();
        Class<E> declaringClass = r0.getDeclaringClass();
        declaringClass.getClass();
        return declaringClass;
    }

    public static /* synthetic */ void getDeclaringJavaClass$annotations(Enum r0) {
    }

    public static final <T> Class<T> getJavaClass(KClass<T> kClass) {
        kClass.getClass();
        Class<T> jClass = ((ClassBasedDeclarationContainer) kClass).getJClass();
        jClass.getClass();
        return jClass;
    }

    public static /* synthetic */ void getJavaClass$annotations(KClass kClass) {
    }

    public static final <T> Class<T> getJavaObjectType(KClass<T> kClass) {
        kClass.getClass();
        Class<T> jClass = ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (!jClass.isPrimitive()) {
            return jClass;
        }
        String name = jClass.getName();
        switch (name.hashCode()) {
            case -1325958191:
                return !name.equals("double") ? jClass : Double.class;
            case 104431:
                return !name.equals("int") ? jClass : Integer.class;
            case 3039496:
                return !name.equals("byte") ? jClass : Byte.class;
            case 3052374:
                return !name.equals("char") ? jClass : Character.class;
            case 3327612:
                return !name.equals("long") ? jClass : Long.class;
            case 3625364:
                return !name.equals("void") ? jClass : Void.class;
            case 64711720:
                return !name.equals("boolean") ? jClass : Boolean.class;
            case 97526364:
                return !name.equals("float") ? jClass : Float.class;
            case 109413500:
                return !name.equals("short") ? jClass : Short.class;
            default:
                return jClass;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final <T> Class<T> getJavaPrimitiveType(KClass<T> kClass) {
        kClass.getClass();
        Class<T> jClass = ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (jClass.isPrimitive()) {
            return jClass;
        }
        String name = jClass.getName();
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    return Integer.TYPE;
                }
                return null;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    return Float.TYPE;
                }
                return null;
            case -515992664:
                if (name.equals("java.lang.Short")) {
                    return Short.TYPE;
                }
                return null;
            case 155276373:
                if (name.equals("java.lang.Character")) {
                    return Character.TYPE;
                }
                return null;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    return Boolean.TYPE;
                }
                return null;
            case 398507100:
                if (name.equals("java.lang.Byte")) {
                    return Byte.TYPE;
                }
                return null;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    return Long.TYPE;
                }
                return null;
            case 399092968:
                if (name.equals("java.lang.Void")) {
                    return Void.TYPE;
                }
                return null;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    return Double.TYPE;
                }
                return null;
            default:
                return null;
        }
    }

    public static final <T> KClass<T> getKotlinClass(Class<T> cls) {
        cls.getClass();
        return Reflection.getOrCreateKotlinClass(cls);
    }

    public static final <T> Class<KClass<T>> getRuntimeClassOfKClassInstance(KClass<T> kClass) {
        kClass.getClass();
        return kClass.getClass();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith = @ReplaceWith(expression = "(this as Any).javaClass", imports = {}))
    public static /* synthetic */ void getRuntimeClassOfKClassInstance$annotations(KClass kClass) {
    }

    public static final /* synthetic */ boolean isArrayOf(Object[] objArr) {
        objArr.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return Object.class.isAssignableFrom(objArr.getClass().getComponentType());
    }

    public static final <T> Class<T> getJavaClass(T t) {
        t.getClass();
        return (Class<T>) t.getClass();
    }
}

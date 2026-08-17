package org.jetbrains.kotlin.cli.common.arguments;

import com.intellij.util.text.VersionComparatorUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u001d\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001aS\u0010\u0005\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u0002H\u00012\u0006\u0010\u0007\u001a\u0002H\u00012,\b\u0002\u0010\b\u001a&\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t¢\u0006\u0002\u0010\f\u001a/\u0010\r\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000f*\u00020\u0002\"\b\b\u0001\u0010\u000e*\u0002H\u000f2\u0006\u0010\u0006\u001a\u0002H\u000f2\u0006\u0010\u0007\u001a\u0002H\u000e¢\u0006\u0002\u0010\u0010\u001a/\u0010\u0011\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000f*\u00020\u0002\"\b\b\u0001\u0010\u000e*\u00020\u00022\u0006\u0010\u0006\u001a\u0002H\u000f2\u0006\u0010\u0007\u001a\u0002H\u000e¢\u0006\u0002\u0010\u0010\u001aQ\u0010\u0012\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000f*\u00020\u0002\"\b\b\u0001\u0010\u000e*\u00020\u00022\u0006\u0010\u0006\u001a\u0002H\u000f2\u0006\u0010\u0007\u001a\u0002H\u000e2 \u0010\u0013\u001a\u001c\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0002\u0010\u0015\u001a\u0083\u0001\u0010\u0016\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000f*\u00020\u0002\"\b\b\u0001\u0010\u000e*\u00020\u00022\u0006\u0010\u0006\u001a\u0002H\u000f2\u0006\u0010\u0007\u001a\u0002H\u000e2\u0006\u0010\u0017\u001a\u00020\u000b2\u001a\u0010\u0018\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n0\u00192,\b\u0002\u0010\b\u001a&\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u001a\u001a\f\u0010\u001b\u001a\u00020\u0002*\u00020\u0002H\u0002\u001a:\u0010\u001c\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n0\u0019\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u000b\u001a\n\u0010 \u001a\u00020!*\u00020\"\u001a\u001e\u0010'\u001a\u00020(*\u000e\u0012\u0006\b\u0001\u0012\u00020\"\u0012\u0002\b\u00030\n2\u0006\u0010+\u001a\u00020(\u001a\n\u0010,\u001a\u00020\u000b*\u00020-\"!\u0010#\u001a\u00020$*\u000e\u0012\u0006\b\u0001\u0012\u00020\"\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b%\u0010&\"!\u0010'\u001a\u00020(*\u000e\u0012\u0006\b\u0001\u0012\u00020\"\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006."}, d2 = {"copyBean", "T", Argument.Delimiters.none, "bean", "(Ljava/lang/Object;)Ljava/lang/Object;", "copyBeanTo", "from", "to", "filter", "Lkotlin/Function2;", "Lkotlin/reflect/KProperty1;", Argument.Delimiters.none, "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "mergeBeans", "To", "From", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "copyInheritedFields", "copyFieldsSatisfying", "predicate", "Lkotlin/Function1;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "copyProperties", "deepCopyWhenNeeded", "propertiesToCopy", Argument.Delimiters.none, "(Ljava/lang/Object;Ljava/lang/Object;ZLjava/util/List;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "copyValueIfNeeded", "collectProperties", "kClass", "Lkotlin/reflect/KClass;", "inheritedOnly", "setApiVersionToLanguageVersionIfNeeded", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "argumentAnnotation", "Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", "getArgumentAnnotation", "(Lkotlin/reflect/KProperty1;)Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", "cliArgument", Argument.Delimiters.none, "getCliArgument", "(Lkotlin/reflect/KProperty1;)Ljava/lang/String;", "value", "isNativeSecondStage", "Lorg/jetbrains/kotlin/cli/common/arguments/K2NativeCompilerArguments;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArgumentUtilsKt {
    public static final String cliArgument(KProperty1<? extends CommonCompilerArguments, ?> kProperty1, String str) {
        kProperty1.getClass();
        str.getClass();
        return getCliArgument(kProperty1) + '=' + str;
    }

    public static final <T> List<KProperty1<T, Object>> collectProperties(KClass<T> kClass, boolean z) {
        Field javaField;
        kClass.getClass();
        ArrayList arrayList = new ArrayList(KClasses.getMemberProperties(kClass));
        if (z) {
            arrayList.removeAll(KClasses.getDeclaredMemberProperties(kClass));
        }
        ArrayList arrayList2 = new ArrayList();
        for (T t : arrayList) {
            KProperty1 kProperty1 = (KProperty1) t;
            if (kProperty1.getVisibility() == KVisibility.PUBLIC && ((javaField = ReflectJvmMapping.getJavaField(kProperty1)) == null || !Modifier.isTransient(javaField.getModifiers()))) {
                if (!kProperty1.isAbstract()) {
                    arrayList2.add(t);
                }
            }
        }
        return arrayList2;
    }

    public static final <T> T copyBean(T t) throws IllegalAccessException, InstantiationException {
        t.getClass();
        Object objNewInstance = t.getClass().newInstance();
        objNewInstance.getClass();
        return (T) copyBeanTo$default(t, objNewInstance, null, 4, null);
    }

    public static final <T> T copyBeanTo(T t, T t2, Function2<? super KProperty1<T, ? extends Object>, Object, Boolean> function2) {
        t.getClass();
        t2.getClass();
        return (T) copyProperties(t, t2, true, collectProperties(Reflection.getOrCreateKotlinClass(t.getClass()), false), function2);
    }

    public static /* synthetic */ Object copyBeanTo$default(Object obj, Object obj2, Function2 function2, int i, Object obj3) {
        if ((i & 4) != 0) {
            function2 = null;
        }
        return copyBeanTo(obj, obj2, function2);
    }

    public static final <From, To> To copyFieldsSatisfying(From from, To to, Function1<? super KProperty1<From, ? extends Object>, Boolean> function1) {
        from.getClass();
        to.getClass();
        function1.getClass();
        List listCollectProperties = collectProperties(Reflection.getOrCreateKotlinClass(from.getClass()), false);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listCollectProperties) {
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return (To) copyProperties$default(from, to, true, arrayList, null, 16, null);
    }

    public static final <From, To> To copyInheritedFields(From from, To to) {
        from.getClass();
        to.getClass();
        return (To) copyProperties$default(from, to, true, collectProperties(Reflection.getOrCreateKotlinClass(from.getClass()), true), null, 16, null);
    }

    private static final <From, To> To copyProperties(From from, To to, boolean z, List<? extends KProperty1<From, ? extends Object>> list, Function2<? super KProperty1<From, ? extends Object>, Object, Boolean> function2) throws IllegalAccessException, InstantiationException {
        if (Intrinsics.areEqual(from, to)) {
            return to;
        }
        Collection memberProperties = KClasses.getMemberProperties(Reflection.getOrCreateKotlinClass(to.getClass()));
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(memberProperties, 10)), 16));
        for (Object obj : memberProperties) {
            linkedHashMap.put(((KProperty1) obj).getName(), obj);
        }
        for (KProperty1<From, ? extends Object> kProperty1 : list) {
            Object obj2 = linkedHashMap.get(kProperty1.getName());
            Object objCopyValueIfNeeded = null;
            KMutableProperty1 kMutableProperty1 = obj2 instanceof KMutableProperty1 ? (KMutableProperty1) obj2 : null;
            if (kMutableProperty1 != null) {
                Object obj3 = kProperty1.get(from);
                if (function2 == null || ((Boolean) function2.invoke(kProperty1, obj3)).booleanValue()) {
                    if (!z) {
                        objCopyValueIfNeeded = obj3;
                    } else if (obj3 != null) {
                        objCopyValueIfNeeded = copyValueIfNeeded(obj3);
                    }
                    kMutableProperty1.set(to, objCopyValueIfNeeded);
                }
            }
        }
        return to;
    }

    public static /* synthetic */ Object copyProperties$default(Object obj, Object obj2, boolean z, List list, Function2 function2, int i, Object obj3) {
        if ((i & 16) != 0) {
            function2 = null;
        }
        return copyProperties(obj, obj2, z, list, function2);
    }

    private static final Object copyValueIfNeeded(Object obj) throws IllegalAccessException, InstantiationException {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            return Arrays.copyOf(bArr, bArr.length);
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            return Arrays.copyOf(cArr, cArr.length);
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            return Arrays.copyOf(sArr, sArr.length);
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            return Arrays.copyOf(iArr, iArr.length);
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            return Arrays.copyOf(jArr, jArr.length);
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            return Arrays.copyOf(fArr, fArr.length);
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            return Arrays.copyOf(dArr, dArr.length);
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            return Arrays.copyOf(zArr, zArr.length);
        }
        if (obj instanceof Object[]) {
            Class<?> componentType = obj.getClass().getComponentType();
            Object[] objArr = (Object[]) obj;
            Object objNewInstance = Array.newInstance(componentType, objArr.length);
            objNewInstance.getClass();
            Object[] objArr2 = (Object[]) objNewInstance;
            int length = objArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                Object obj2 = objArr[i];
                int i3 = i2 + 1;
                objArr2[i2] = obj2 != null ? copyValueIfNeeded(obj2) : null;
                i++;
                i2 = i3;
            }
            objNewInstance.getClass();
            return objNewInstance;
        }
        if (TypeIntrinsics.isMutableCollection(obj)) {
            obj.getClass();
            Collection collection = (Collection) obj;
            Object objNewInstance2 = obj.getClass().newInstance();
            objNewInstance2.getClass();
            Collection collectionAsMutableCollection = TypeIntrinsics.asMutableCollection(objNewInstance2);
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                collectionAsMutableCollection.add(next != null ? copyValueIfNeeded(next) : null);
            }
            return collectionAsMutableCollection;
        }
        if (!TypeIntrinsics.isMutableMap(obj)) {
            return obj;
        }
        Object objNewInstance3 = obj.getClass().newInstance();
        objNewInstance3.getClass();
        Map mapAsMutableMap = TypeIntrinsics.asMutableMap(objNewInstance3);
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            mapAsMutableMap.put(key != null ? copyValueIfNeeded(key) : null, value != null ? copyValueIfNeeded(value) : null);
        }
        return mapAsMutableMap;
    }

    public static final Argument getArgumentAnnotation(KProperty1<? extends CommonCompilerArguments, ?> kProperty1) {
        kProperty1.getClass();
        Field javaField = ReflectJvmMapping.getJavaField(kProperty1);
        if (javaField == null) {
            w04.a("Java field should be present for ", kProperty1);
            return null;
        }
        Annotation annotation = javaField.getAnnotation(Argument.class);
        annotation.getClass();
        return (Argument) annotation;
    }

    public static final String getCliArgument(KProperty1<? extends CommonCompilerArguments, ?> kProperty1) {
        kProperty1.getClass();
        return getArgumentAnnotation(kProperty1).value();
    }

    public static final boolean isNativeSecondStage(K2NativeCompilerArguments k2NativeCompilerArguments) {
        k2NativeCompilerArguments.getClass();
        return !Intrinsics.areEqual(k2NativeCompilerArguments.getProduce(), "library");
    }

    public static final <From, To extends From> To mergeBeans(From from, To to) {
        from.getClass();
        to.getClass();
        return (To) copyProperties$default(from, to, false, collectProperties(Reflection.getOrCreateKotlinClass(from.getClass()), false), null, 16, null);
    }

    public static final void setApiVersionToLanguageVersionIfNeeded(CommonCompilerArguments commonCompilerArguments) {
        commonCompilerArguments.getClass();
        if (commonCompilerArguments.getLanguageVersion() == null || VersionComparatorUtil.compare(commonCompilerArguments.getLanguageVersion(), commonCompilerArguments.getApiVersion()) >= 0) {
            return;
        }
        commonCompilerArguments.setApiVersion(commonCompilerArguments.getLanguageVersion());
    }
}

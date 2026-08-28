package com.shadow.kotlin.jvm.internal;

import com.shadow.kotlin.Pair;
import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.MapsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.JvmClassMappingKt;
import com.shadow.kotlin.jvm.functions.Function0;
import com.shadow.kotlin.jvm.functions.Function1;
import com.shadow.kotlin.jvm.functions.Function10;
import com.shadow.kotlin.jvm.functions.Function11;
import com.shadow.kotlin.jvm.functions.Function12;
import com.shadow.kotlin.jvm.functions.Function13;
import com.shadow.kotlin.jvm.functions.Function14;
import com.shadow.kotlin.jvm.functions.Function15;
import com.shadow.kotlin.jvm.functions.Function16;
import com.shadow.kotlin.jvm.functions.Function17;
import com.shadow.kotlin.jvm.functions.Function18;
import com.shadow.kotlin.jvm.functions.Function19;
import com.shadow.kotlin.jvm.functions.Function2;
import com.shadow.kotlin.jvm.functions.Function20;
import com.shadow.kotlin.jvm.functions.Function21;
import com.shadow.kotlin.jvm.functions.Function22;
import com.shadow.kotlin.jvm.functions.Function3;
import com.shadow.kotlin.jvm.functions.Function4;
import com.shadow.kotlin.jvm.functions.Function5;
import com.shadow.kotlin.jvm.functions.Function6;
import com.shadow.kotlin.jvm.functions.Function7;
import com.shadow.kotlin.jvm.functions.Function8;
import com.shadow.kotlin.jvm.functions.Function9;
import com.shadow.kotlin.text.StringsKt;
import com.swift.sandhook.annotation.MethodReflectParams;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.reflect.KClass;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ClassReference implements KClass<Object>, kotlin.jvm.internal.ClassBasedDeclarationContainer {
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    private static final HashMap<String, String> classFqNames;
    private static final LinkedHashMap simpleNames;
    private final Class<?> jClass;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Map mapA;
        List listA = ArraysKt.a(new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        ArrayList arrayList = new ArrayList(CollectionsKt.b(listA));
        int i = 0;
        for (Object obj : listA) {
            int i2 = i + 1;
            if (i < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            arrayList.add(new Pair((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        int size = arrayList.size();
        if (size == 0) {
            mapA = MapsKt.a();
        } else if (size != 1) {
            mapA = new LinkedHashMap(MapsKt.b(arrayList.size()));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                mapA.put(pair.component1(), pair.component2());
            }
        } else {
            Pair pair2 = (Pair) arrayList.get(0);
            CloseableKt.checkNotNullParameter(pair2, "pair");
            mapA = Collections.singletonMap(pair2.getFirst(), pair2.getSecond());
            CloseableKt.checkNotNullExpressionValue(mapA, "singletonMap(...)");
        }
        FUNCTION_CLASSES = mapA;
        HashMap map = new HashMap();
        map.put(MethodReflectParams.BOOLEAN, "kotlin.Boolean");
        map.put(MethodReflectParams.CHAR, "kotlin.Char");
        map.put(MethodReflectParams.BYTE, "kotlin.Byte");
        map.put(MethodReflectParams.SHORT, "kotlin.Short");
        map.put(MethodReflectParams.INT, "kotlin.Int");
        map.put(MethodReflectParams.FLOAT, "kotlin.Float");
        map.put(MethodReflectParams.LONG, "kotlin.Long");
        map.put(MethodReflectParams.DOUBLE, "kotlin.Double");
        HashMap map2 = new HashMap();
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Double", "kotlin.Double");
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("java.lang.Object", "kotlin.Any");
        map3.put("java.lang.String", "kotlin.String");
        map3.put("java.lang.CharSequence", "kotlin.CharSequence");
        map3.put("java.lang.Throwable", "kotlin.Throwable");
        map3.put("java.lang.Cloneable", "kotlin.Cloneable");
        map3.put("java.lang.Number", "kotlin.Number");
        map3.put("java.lang.Comparable", "kotlin.Comparable");
        map3.put("java.lang.Enum", "kotlin.Enum");
        map3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        map3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        map3.put("java.util.Iterator", "kotlin.collections.Iterator");
        map3.put("java.util.Collection", "kotlin.collections.Collection");
        map3.put("java.util.List", "kotlin.collections.List");
        map3.put("java.util.Set", "kotlin.collections.Set");
        map3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        map3.put("java.util.Map", "kotlin.collections.Map");
        map3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        map3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        map3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        map3.putAll(map);
        map3.putAll(map2);
        Collection<String> collectionValues = map.values();
        CloseableKt.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        for (String str : collectionValues) {
            StringBuilder sb = new StringBuilder("kotlin.jvm.internal.");
            CloseableKt.checkNotNull(str);
            sb.append(StringsKt.t(str));
            sb.append("CompanionObject");
            Pair pair3 = new Pair(sb.toString(), str.concat(".Companion"));
            map3.put(pair3.getFirst(), pair3.getSecond());
        }
        for (Map.Entry<Class<? extends Function<?>>, Integer> entry : FUNCTION_CLASSES.entrySet()) {
            Class<? extends Function<?>> key = entry.getKey();
            int iIntValue = entry.getValue().intValue();
            map3.put(key.getName(), "kotlin.Function" + iIntValue);
        }
        classFqNames = map3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.b(map3.size()));
        for (Map.Entry entry2 : map3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), StringsKt.t((String) entry2.getValue()));
        }
        simpleNames = linkedHashMap;
    }

    public ClassReference(Class<?> cls) {
        CloseableKt.checkNotNullParameter(cls, "jClass");
        this.jClass = cls;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ClassReference) && JvmClassMappingKt.getJavaObjectType(this).equals(JvmClassMappingKt.getJavaObjectType((com.shadow.kotlin.reflect.KClass) obj));
    }

    public final Class<?> getJClass() {
        return this.jClass;
    }

    public final String getQualifiedName() {
        String str;
        Class<?> cls = this.jClass;
        CloseableKt.checkNotNullParameter(cls, "jClass");
        String strConcat = null;
        if (cls.isAnonymousClass() || cls.isLocalClass()) {
            return null;
        }
        if (!cls.isArray()) {
            String str2 = (String) classFqNames.get(cls.getName());
            return str2 == null ? cls.getCanonicalName() : str2;
        }
        Class<?> componentType = cls.getComponentType();
        if (componentType.isPrimitive() && (str = (String) classFqNames.get(componentType.getName())) != null) {
            strConcat = str.concat("Array");
        }
        return strConcat == null ? "kotlin.Array" : strConcat;
    }

    public final String getSimpleName() {
        String str;
        Class<?> cls = this.jClass;
        CloseableKt.checkNotNullParameter(cls, "jClass");
        String strConcat = null;
        if (cls.isAnonymousClass()) {
            return null;
        }
        if (!cls.isLocalClass()) {
            boolean zIsArray = cls.isArray();
            LinkedHashMap linkedHashMap = simpleNames;
            if (!zIsArray) {
                String str2 = (String) linkedHashMap.get(cls.getName());
                return str2 == null ? cls.getSimpleName() : str2;
            }
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (str = (String) linkedHashMap.get(componentType.getName())) != null) {
                strConcat = str.concat("Array");
            }
            return strConcat == null ? "Array" : strConcat;
        }
        String simpleName = cls.getSimpleName();
        Method enclosingMethod = cls.getEnclosingMethod();
        if (enclosingMethod != null) {
            return StringsKt.s(simpleName, enclosingMethod.getName() + '$');
        }
        Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
        if (enclosingConstructor != null) {
            return StringsKt.s(simpleName, enclosingConstructor.getName() + '$');
        }
        int iH = StringsKt.h(simpleName, '$', 0, false, 6);
        if (iH == -1) {
            return simpleName;
        }
        String strSubstring = simpleName.substring(iH + 1, simpleName.length());
        CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    public final boolean isInstance(Object obj) {
        Class<?> javaObjectType = this.jClass;
        CloseableKt.checkNotNullParameter(javaObjectType, "jClass");
        Map<Class<? extends Function<?>>, Integer> map = FUNCTION_CLASSES;
        CloseableKt.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
        Integer num = map.get(javaObjectType);
        if (num == null) {
            if (javaObjectType.isPrimitive()) {
                javaObjectType = JvmClassMappingKt.getJavaObjectType(Reflection.getOrCreateKotlinClass(javaObjectType));
            }
            return javaObjectType.isInstance(obj);
        }
        int iIntValue = num.intValue();
        if (obj instanceof com.shadow.kotlin.Function) {
            return (obj instanceof FunctionBase ? ((FunctionBase) obj).getArity() : obj instanceof Function0 ? 0 : obj instanceof Function1 ? 1 : obj instanceof Function2 ? 2 : -1) == iIntValue;
        }
        return false;
    }

    public final String toString() {
        return this.jClass.toString() + " (Kotlin reflection is not available)";
    }
}

package com.intellij.util.ref;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.PairProcessor;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.containers.CollectionFactory;
import com.intellij.util.containers.FList;
import com.intellij.util.containers.HashingStrategy;
import com.intellij.util.containers.RefValueHashMapUtil;
import defpackage.a5e;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DebugReflectionUtil {
    private static final Key<Boolean> REPORTED_LEAKED;
    private static final Method Unsafe_shouldBeInitialized;
    private static final Map<Class<?>, Field[]> allFields = Collections.synchronizedMap(CollectionFactory.createCustomHashingStrategyMap(new HashingStrategy<Class<?>>() { // from class: com.intellij.util.ref.DebugReflectionUtil.1
        @Override // com.intellij.util.containers.HashingStrategy
        public int hashCode(Class<?> cls) {
            if (cls == null) {
                return 0;
            }
            return cls.getName().hashCode();
        }

        @Override // com.intellij.util.containers.HashingStrategy
        public boolean equals(Class<?> cls, Class<?> cls2) {
            return cls == cls2;
        }
    }));
    private static final Field[] EMPTY_FIELD_ARRAY = new Field[0];

    public static class BackLink<V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final BackLink<?> backLink;
        private final int depth;
        private final Field field;
        private final String fieldName;
        private final V value;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "value";
            } else {
                objArr[0] = "result";
            }
            objArr[1] = "com/intellij/util/ref/DebugReflectionUtil$BackLink";
            if (i != 1) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "print";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public BackLink(V v, Field field, String str, BackLink<?> backLink) {
            if (v == null) {
                $$$reportNull$$$0(0);
            }
            this.value = v;
            this.field = field;
            this.fieldName = str;
            this.backLink = backLink;
            this.depth = backLink != null ? backLink.depth + 1 : 0;
        }

        public String getFieldName() {
            String str = this.fieldName;
            if (str != null) {
                return str;
            }
            return this.field.getDeclaringClass().getName() + "." + this.field.getName();
        }

        public BackLink<?> prev() {
            return this.backLink;
        }

        public void print(StringBuilder sb) {
            String strFirst;
            String string;
            if (sb == null) {
                $$$reportNull$$$0(1);
            }
            V v = this.value;
            try {
                if (v instanceof FList) {
                    string = "FList (size=" + ((FList) v).size() + ")";
                } else if (v instanceof Collection) {
                    string = "Collection (size=" + ((Collection) v).size() + ")";
                } else {
                    string = v instanceof Object[] ? Arrays.toString((Object[]) v) : String.valueOf(v);
                }
                strFirst = StringUtil.first(StringUtil.convertLineSeparators(string, "\\n"), 200, true);
            } catch (Throwable th) {
                strFirst = "(" + th.getMessage() + " while computing .toString())";
            }
            sb.append("via '");
            sb.append(getFieldName());
            sb.append("'; Value: '");
            sb.append(strFirst);
            sb.append("' of ");
            sb.append(v.getClass());
            sb.append("\n");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            while (this != null) {
                this.print(sb);
                this = (BackLink<V>) this.prev();
            }
            return sb.toString();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        switch (i) {
            case 1:
                objArr[0] = "com/intellij/util/ref/DebugReflectionUtil";
                break;
            case 2:
                objArr[0] = "type";
                break;
            case 3:
            case 9:
                objArr[0] = "root";
                break;
            case 4:
                objArr[0] = "startRoots";
                break;
            case 5:
                objArr[0] = "lookFor";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 11:
            case 14:
                objArr[0] = "shouldExamineValue";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "leakProcessor";
                break;
            case 8:
            case 13:
                objArr[0] = "queue";
                break;
            case 10:
            case 12:
                objArr[0] = "backLink";
                break;
            default:
                objArr[0] = "aClass";
                break;
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/ref/DebugReflectionUtil";
        } else {
            objArr[1] = "getAllFields";
        }
        switch (i) {
            case 1:
                break;
            case 2:
                objArr[2] = "isTrivial";
                break;
            case 3:
                objArr[2] = "isInitialized";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "walkObjects";
                break;
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "queueStronglyReferencedValues";
                break;
            case 12:
            case 13:
            case 14:
                objArr[2] = "queue";
                break;
            default:
                objArr[2] = "getAllFields";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    static {
        Method declaredMethod;
        try {
            declaredMethod = ReflectionUtil.getDeclaredMethod(Class.forName("sun.misc.Unsafe"), "shouldBeInitialized", Class.class);
        } catch (ClassNotFoundException unused) {
            declaredMethod = null;
        }
        Unsafe_shouldBeInitialized = declaredMethod;
        REPORTED_LEAKED = Key.create("REPORTED_LEAKED");
    }

    private static Field[] getAllFields(Class<?> cls) {
        Field[] fieldArr;
        if (cls == null) {
            $$$reportNull$$$0(0);
        }
        Field[] fieldArr2 = allFields.get(cls);
        if (fieldArr2 == null) {
            try {
                Field[] declaredFields = cls.getDeclaredFields();
                ArrayList arrayList = new ArrayList(declaredFields.length + 5);
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (!isTrivial(field.getType())) {
                        arrayList.add(field);
                    }
                }
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass != null) {
                    for (Field field2 : getAllFields(superclass)) {
                        if (!arrayList.contains(field2)) {
                            arrayList.add(field2);
                        }
                    }
                }
                fieldArr = arrayList.isEmpty() ? EMPTY_FIELD_ARRAY : (Field[]) arrayList.toArray(new Field[0]);
            } catch (IncompatibleClassChangeError | NoClassDefFoundError | SecurityException unused) {
                fieldArr = EMPTY_FIELD_ARRAY;
            } catch (RuntimeException e) {
                if (!e.getClass().getName().equals("java.lang.reflect.InaccessibleObjectException")) {
                    throw e;
                }
                fieldArr = EMPTY_FIELD_ARRAY;
            }
            fieldArr2 = fieldArr;
            allFields.put(cls, fieldArr2);
        }
        if (fieldArr2 == null) {
            $$$reportNull$$$0(1);
        }
        return fieldArr2;
    }

    private static boolean isInitialized(Class<?> cls) {
        if (cls == null) {
            $$$reportNull$$$0(3);
        }
        Method method = Unsafe_shouldBeInitialized;
        if (method == null) {
            return false;
        }
        try {
            return !((Boolean) method.invoke(ReflectionUtil.getUnsafe(), cls)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isTrivial(Class<?> cls) {
        if (cls == null) {
            $$$reportNull$$$0(2);
        }
        if (cls.isPrimitive() || cls == String.class || cls == Class.class) {
            return true;
        }
        return cls.isArray() && isTrivial(cls.getComponentType());
    }

    private static boolean markLeaked(Object obj) {
        return !(obj instanceof UserDataHolderEx) || ((UserDataHolderEx) obj).replace(REPORTED_LEAKED, (Object) null, Boolean.TRUE);
    }

    private static void queue(Object obj, Field field, String str, BackLink<?> backLink, Deque<? super BackLink<?>> deque, Predicate<Object> predicate) {
        if (backLink == null) {
            $$$reportNull$$$0(12);
        }
        if (deque == null) {
            $$$reportNull$$$0(13);
        }
        if (predicate == null) {
            $$$reportNull$$$0(14);
        }
        if (obj == null || isTrivial(obj.getClass()) || !predicate.test(obj)) {
            return;
        }
        deque.addLast(new BackLink(obj, field, str, backLink));
    }

    private static void queueStronglyReferencedValues(Deque<? super BackLink<?>> deque, Object obj, BackLink<?> backLink, Predicate<Object> predicate) {
        if (deque == null) {
            $$$reportNull$$$0(8);
        }
        if (obj == null) {
            $$$reportNull$$$0(9);
        }
        if (backLink == null) {
            $$$reportNull$$$0(10);
        }
        if (predicate == null) {
            $$$reportNull$$$0(11);
        }
        Class<?> cls = obj.getClass();
        if (obj instanceof Map) {
            RefValueHashMapUtil.expungeStaleEntries((Map) obj);
        }
        for (Field field : getAllFields(cls)) {
            String name = field.getName();
            if (!(obj instanceof Reference) || (!"referent".equals(name) && !"discovered".equals(name))) {
                try {
                    queue(field.get(obj), field, null, backLink, deque, predicate);
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    rc6.a(e);
                    return;
                }
            }
        }
        if (cls.isArray()) {
            try {
                Object[] objArr = (Object[]) obj;
                for (int i = 0; i < objArr.length; i++) {
                    queue(objArr[i], null, "[" + i + "]", backLink, deque, predicate);
                }
            } catch (ClassCastException unused) {
            }
        }
        if (obj instanceof Class) {
            Class cls2 = (Class) obj;
            if (isInitialized(cls2)) {
                for (Field field2 : getAllFields(cls2)) {
                    if ((field2.getModifiers() & 8) != 0) {
                        try {
                            queue(field2.get(null), field2, null, backLink, deque, predicate);
                        } catch (IllegalAccessException unused2) {
                        }
                    }
                }
            }
        }
    }

    public static <V> boolean walkObjects(int i, Map<Object, String> map, Class<V> cls, Predicate<Object> predicate, PairProcessor<? super V, ? super BackLink<?>> pairProcessor) {
        if (map == null) {
            $$$reportNull$$$0(4);
        }
        if (cls == null) {
            $$$reportNull$$$0(5);
        }
        if (predicate == null) {
            $$$reportNull$$$0(6);
        }
        if (pairProcessor == null) {
            $$$reportNull$$$0(7);
        }
        IntOpenHashSet intOpenHashSet = new IntOpenHashSet(100);
        ArrayDeque arrayDeque = new ArrayDeque(100);
        for (Map.Entry<Object, String> entry : map.entrySet()) {
            Object key = entry.getKey();
            final String value = entry.getValue();
            arrayDeque.addLast(new BackLink<Object>(key, null, "(root)", null) { // from class: com.intellij.util.ref.DebugReflectionUtil.2
                private static /* synthetic */ void $$$reportNull$$$0(int i2) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/intellij/util/ref/DebugReflectionUtil$2", "print"));
                }

                @Override // com.intellij.util.ref.DebugReflectionUtil.BackLink
                public void print(StringBuilder sb) {
                    if (sb == null) {
                        $$$reportNull$$$0(0);
                    }
                    super.print(sb);
                    sb.append(" (from ");
                    sb.append(value);
                    sb.append(")");
                }
            });
        }
        while (true) {
            BackLink backLink = (BackLink) arrayDeque.pollFirst();
            if (backLink == null) {
                return true;
            }
            if (backLink.depth <= i) {
                a5e a5eVar = (Object) backLink.value;
                if (cls.isAssignableFrom(a5eVar.getClass()) && markLeaked(a5eVar) && !pairProcessor.process(a5eVar, backLink)) {
                    return false;
                }
                if (intOpenHashSet.add(System.identityHashCode(a5eVar))) {
                    queueStronglyReferencedValues(arrayDeque, a5eVar, backLink, predicate);
                }
            }
        }
    }
}

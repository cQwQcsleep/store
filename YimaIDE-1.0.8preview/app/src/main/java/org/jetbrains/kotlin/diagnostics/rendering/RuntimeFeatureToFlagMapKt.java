package org.jetbrains.kotlin.diagnostics.rendering;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\b\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\f0\t*\u00020\rH\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a\u001c\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0002\u001a\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u00182\u0006\u0010\u001a\u001a\u00020\r\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"COMPILER_ARGUMENTS_CLASS", Argument.Delimiters.none, "ARGUMENT_CLASS", "ARGUMENT_VALUE", "ENABLES_CLASS", "DISABLES_CLASS", "FEATURE", "IF_VALUE_IS", "loadArgumentAnnotationInfo", "Lkotlin/Pair;", "Ljava/lang/Class;", Argument.Delimiters.none, "Ljava/lang/reflect/Method;", "Ljava/lang/ClassLoader;", "loadEnablesOrDisablesAnnotationInfo", "Lorg/jetbrains/kotlin/diagnostics/rendering/AnnotationAndMethods;", "fqName", "loadAnnotationClass", "getFeaturesAndValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/rendering/FeatureAndValue;", "Ljava/lang/reflect/Field;", "triple", "buildRuntimeFeatureToFlagMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/LanguageFeature;", "classLoader", "org.jetbrains.kotlin:frontend.common-psi"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RuntimeFeatureToFlagMapKt {

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\"\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"org/jetbrains/kotlin/diagnostics/rendering/RuntimeFeatureToFlagMapKt$buildRuntimeFeatureToFlagMap$ArgumentAndValue", Argument.Delimiters.none, "argument", Argument.Delimiters.none, "value", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getArgument", "()Ljava/lang/String;", "getValue", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/String;)Lorg/jetbrains/kotlin/diagnostics/rendering/RuntimeFeatureToFlagMapKt$buildRuntimeFeatureToFlagMap$ArgumentAndValue;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ArgumentAndValue {
        private final String argument;
        private final String value;

        public ArgumentAndValue(String str, String str2) {
            str.getClass();
            str2.getClass();
            this.argument = str;
            this.value = str2;
        }

        public static /* synthetic */ ArgumentAndValue copy$default(ArgumentAndValue argumentAndValue, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = argumentAndValue.argument;
            }
            if ((i & 2) != 0) {
                str2 = argumentAndValue.value;
            }
            return argumentAndValue.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getArgument() {
            return this.argument;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        public final ArgumentAndValue copy(String argument, String value) {
            argument.getClass();
            value.getClass();
            return new ArgumentAndValue(argument, value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ArgumentAndValue)) {
                return false;
            }
            ArgumentAndValue argumentAndValue = (ArgumentAndValue) other;
            return Intrinsics.areEqual(this.argument, argumentAndValue.argument) && Intrinsics.areEqual(this.value, argumentAndValue.value);
        }

        public final String getArgument() {
            return this.argument;
        }

        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            return (this.argument.hashCode() * 31) + this.value.hashCode();
        }

        public String toString() {
            return "ArgumentAndValue(argument=" + this.argument + ", value=" + this.value + ')';
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x008c  */
    public static final Map<LanguageFeature, String> buildRuntimeFeatureToFlagMap(ClassLoader classLoader) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        Collection collectionEmptyList;
        classLoader.getClass();
        Class<?> clsLoadClass = classLoader.loadClass("org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments");
        Pair<Class<? extends Annotation>, Method> pairLoadArgumentAnnotationInfo = loadArgumentAnnotationInfo(classLoader);
        Class cls = (Class) pairLoadArgumentAnnotationInfo.component1();
        Method method = (Method) pairLoadArgumentAnnotationInfo.component2();
        AnnotationAndMethods annotationAndMethodsLoadEnablesOrDisablesAnnotationInfo = loadEnablesOrDisablesAnnotationInfo(classLoader, "org.jetbrains.kotlin.cli.common.arguments.Enables");
        AnnotationAndMethods annotationAndMethodsLoadEnablesOrDisablesAnnotationInfo2 = loadEnablesOrDisablesAnnotationInfo(classLoader, "org.jetbrains.kotlin.cli.common.arguments.Disables");
        Field[] declaredFields = clsLoadClass.getDeclaredFields();
        declaredFields.getClass();
        ArrayList<Pair> arrayList = new ArrayList();
        for (Field field : declaredFields) {
            Annotation[] annotationsByType = field.getAnnotationsByType(cls);
            annotationsByType.getClass();
            Annotation annotation = (Annotation) ArraysKt.firstOrNull(annotationsByType);
            if (annotation != null) {
                Object objInvoke = method.invoke(annotation, null);
                String str = objInvoke instanceof String ? (String) objInvoke : null;
                if (str == null) {
                    collectionEmptyList = CollectionsKt.emptyList();
                } else {
                    List listPlus = CollectionsKt.plus(getFeaturesAndValues(field, annotationAndMethodsLoadEnablesOrDisablesAnnotationInfo), getFeaturesAndValues(field, annotationAndMethodsLoadEnablesOrDisablesAnnotationInfo2));
                    collectionEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
                    Iterator it = listPlus.iterator();
                    while (it.hasNext()) {
                        collectionEmptyList.add(TuplesKt.to((FeatureAndValue) it.next(), str));
                    }
                }
            } else {
                collectionEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList, collectionEmptyList);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair pair : arrayList) {
            LanguageFeature feature = ((FeatureAndValue) pair.getFirst()).getFeature();
            Object arrayList2 = linkedHashMap.get(feature);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(feature, arrayList2);
            }
            ((List) arrayList2).add(new ArgumentAndValue((String) pair.getSecond(), ((FeatureAndValue) pair.getFirst()).getValue()));
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            List list = (List) entry.getValue();
            String argument = ((ArgumentAndValue) CollectionsKt.first(list)).getArgument();
            List list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    if (((ArgumentAndValue) it2.next()).getValue().length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(argument);
                        sb.append('=');
                        if (list.size() == 1) {
                            sb.append(((ArgumentAndValue) CollectionsKt.first(list)).getValue());
                        }
                        argument = sb.toString();
                        break;
                    }
                }
            }
            linkedHashMap2.put(key, argument);
        }
        return linkedHashMap2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence buildRuntimeFeatureToFlagMap$lambda$3$1$0(ArgumentAndValue argumentAndValue) {
        argumentAndValue.getClass();
        return argumentAndValue.getValue();
    }

    private static final List<FeatureAndValue> getFeaturesAndValues(Field field, AnnotationAndMethods annotationAndMethods) throws IllegalAccessException, InvocationTargetException {
        Class<? extends Annotation> clsComponent1 = annotationAndMethods.component1();
        Method featureMethod = annotationAndMethods.getFeatureMethod();
        Method ifValueIsMethod = annotationAndMethods.getIfValueIsMethod();
        Annotation[] annotationsByType = field.getAnnotationsByType(clsComponent1);
        annotationsByType.getClass();
        ArrayList arrayList = new ArrayList(annotationsByType.length);
        for (Annotation annotation : annotationsByType) {
            Object objInvoke = featureMethod.invoke(annotation, null);
            objInvoke.getClass();
            Object objInvoke2 = ifValueIsMethod.invoke(annotation, null);
            objInvoke2.getClass();
            arrayList.add(new FeatureAndValue((LanguageFeature) objInvoke, (String) objInvoke2));
        }
        return arrayList;
    }

    private static final Class<? extends Annotation> loadAnnotationClass(ClassLoader classLoader, String str) throws ClassNotFoundException {
        Class clsLoadClass = classLoader.loadClass(str);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private static final Pair<Class<? extends Annotation>, Method> loadArgumentAnnotationInfo(ClassLoader classLoader) throws ClassNotFoundException {
        Class<? extends Annotation> clsLoadAnnotationClass = loadAnnotationClass(classLoader, "org.jetbrains.kotlin.cli.common.arguments.Argument");
        return TuplesKt.to(clsLoadAnnotationClass, clsLoadAnnotationClass.getMethod("value", null));
    }

    private static final AnnotationAndMethods loadEnablesOrDisablesAnnotationInfo(ClassLoader classLoader, String str) throws NoSuchMethodException, ClassNotFoundException {
        Class<? extends Annotation> clsLoadAnnotationClass = loadAnnotationClass(classLoader, str);
        Method method = clsLoadAnnotationClass.getMethod("feature", null);
        Method method2 = clsLoadAnnotationClass.getMethod("ifValueIs", null);
        method.getClass();
        method2.getClass();
        return new AnnotationAndMethods(clsLoadAnnotationClass, method, method2);
    }
}

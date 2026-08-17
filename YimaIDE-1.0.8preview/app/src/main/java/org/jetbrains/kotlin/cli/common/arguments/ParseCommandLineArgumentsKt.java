package org.jetbrains.kotlin.cli.common.arguments;

import defpackage.dwe;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.CompilerSystemProperties;
import org.jetbrains.kotlin.cli.common.arguments.ManualLanguageFeatureSetting;
import org.jetbrains.kotlin.cli.common.arguments.ParseCommandLineArgumentsKt;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;
import org.jetbrains.kotlin.konan.file.File;
import org.jetbrains.kotlin.load.java.JvmAbi;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a(\u0010\u0010\u001a\u0002H\u0011\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H\u0086\b¢\u0006\u0002\u0010\u0015\u001a1\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00172\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\u0010\u0018\u001a5\u0010\u0010\u001a\u00020\u0019\"\b\b\u0000\u0010\u001a*\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00142\u0006\u0010\u001b\u001a\u0002H\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u0001¢\u0006\u0002\u0010\u001d\u001a\u001d\u0010\u001e\u001a\u00020\u0019\"\b\b\u0000\u0010\u001a*\u00020\u00122\u0006\u0010\u001f\u001a\u0002H\u001a¢\u0006\u0002\u0010 \u001a\u0012\u0010%\u001a\u00020$2\n\u0010&\u001a\u0006\u0012\u0002\b\u00030#\u001aC\u0010'\u001a\u00020\u0019\"\b\b\u0000\u0010\u001a*\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00142\u0006\u0010\u001b\u001a\u0002H\u001a2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010\u001c\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010+\u001a.\u0010,\u001a\u00020\u00012\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002\u001a9\u00102\u001a\b\u0012\u0004\u0012\u00020\u0007032\u0006\u00104\u001a\u00020\u00022\u0006\u00105\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00012\f\u00106\u001a\b\u0012\u0004\u0012\u00020807H\u0002¢\u0006\u0002\u00109\u001a;\u0010:\u001a\u00020\u0019\"\b\b\u0000\u0010\u001a*\u00020\u0012*\u0002H\u001a2\u0016\u0010;\u001a\u0012\u0012\u0004\u0012\u00020=0<j\b\u0012\u0004\u0012\u00020=`>2\u0006\u0010\u001c\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010?\u001a\u0012\u0010@\u001a\u0004\u0018\u00010\u00072\b\u0010(\u001a\u0004\u0018\u00010*\u001a\u0016\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00070\u00142\b\u0010(\u001a\u0004\u0018\u00010*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u001d\u0010\b\u001a\u0004\u0018\u00010\u0007*\u00020\u00028F¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u000e\u0010\r\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u001e\u0010!\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030#\u0012\u0004\u0012\u00020$0\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B²\u0006\u0010\u00106\u001a\b\u0012\u0004\u0012\u00020807X\u008a\u0084\u0002"}, d2 = {"isAdvanced", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", "(Lorg/jetbrains/kotlin/cli/common/arguments/Argument;)Z", "isInternal", "isSpecial", "prefix", Argument.Delimiters.none, "resolvedDelimiter", "getResolvedDelimiter$annotations", "(Lorg/jetbrains/kotlin/cli/common/arguments/Argument;)V", "getResolvedDelimiter", "(Lorg/jetbrains/kotlin/cli/common/arguments/Argument;)Ljava/lang/String;", "ADVANCED_ARGUMENT_PREFIX", "INTERNAL_ARGUMENT_PREFIX", "FREE_ARGS_DELIMITER", "parseCommandLineArguments", "T", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "args", Argument.Delimiters.none, "(Ljava/util/List;)Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "clazz", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;Ljava/util/List;)Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", Argument.Delimiters.none, "A", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "overrideArguments", "(Ljava/util/List;Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;Z)V", "parseCommandLineArgumentsFromEnvironment", "arguments", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;)V", "argumentsCache", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/lang/Class;", "Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentsInfo;", "getArgumentsInfo", "klass", "parsePreprocessedCommandLineArguments", AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS, "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentParseErrors;", "(Ljava/util/List;Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;Lkotlin/Lazy;Z)V", "parseBooleanValue", "arg", "argumentField", "Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentField;", "delimiter", Argument.Delimiters.none, "convertArrayOfStrings", Argument.Delimiters.none, "argument", "stringValue", "existingValues", Argument.Delimiters.none, Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/cli/common/arguments/Argument;Ljava/lang/String;ZLjava/util/List;)[Ljava/lang/String;", "updateInternalArguments", "newInternalArguments", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/cli/common/arguments/ManualLanguageFeatureSetting;", "Lkotlin/collections/ArrayList;", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;Ljava/util/ArrayList;Z)V", "validateArguments", "validateArgumentsAllErrors", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ParseCommandLineArgumentsKt {
    public static final String INTERNAL_ARGUMENT_PREFIX = "-XX";
    private static final ConcurrentHashMap<Class<?>, ArgumentsInfo> argumentsCache = new ConcurrentHashMap<>();

    public static ArgumentParseErrors b(CommonToolArguments commonToolArguments) {
        ArgumentParseErrors errors = commonToolArguments.getErrors();
        if (errors != null) {
            return errors;
        }
        ArgumentParseErrors argumentParseErrors = new ArgumentParseErrors(null, null, null, null, null, null, null, null, null, null, 1023, null);
        commonToolArguments.setErrors(argumentParseErrors);
        return argumentParseErrors;
    }

    public static boolean c(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    private static final String[] convertArrayOfStrings(Argument argument, String str, boolean z, List<Object> list) {
        String resolvedDelimiter = getResolvedDelimiter(argument);
        List listListOf = (resolvedDelimiter == null || resolvedDelimiter.length() == 0) ? CollectionsKt.listOf(str) : StringsKt.split$default(str, new String[]{resolvedDelimiter}, false, 0, 6, (Object) null);
        List mutableList = null;
        Object obj = null;
        if (!z) {
            for (Object obj2 : list) {
                if (TypeIntrinsics.isMutableList(obj2)) {
                    obj = obj2;
                    break;
                }
            }
            mutableList = (List) obj;
        }
        if (mutableList != null) {
            mutableList.addAll(listListOf);
        } else {
            mutableList = CollectionsKt.toMutableList(listListOf);
            list.add(mutableList);
        }
        return (String[]) mutableList.toArray(new String[0]);
    }

    public static boolean d(ManualLanguageFeatureSetting manualLanguageFeatureSetting, ManualLanguageFeatureSetting manualLanguageFeatureSetting2) {
        manualLanguageFeatureSetting2.getClass();
        return manualLanguageFeatureSetting2.getLanguageFeature() == manualLanguageFeatureSetting.getLanguageFeature();
    }

    public static List e(Map map, ArgumentField argumentField) {
        Object arrayList = map.get(argumentField);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map.put(argumentField, arrayList);
        }
        return (List) arrayList;
    }

    public static final ArgumentsInfo getArgumentsInfo(Class<?> cls) throws NoSuchMethodException {
        Constructor<?> constructor;
        cls.getClass();
        ConcurrentHashMap<Class<?>, ArgumentsInfo> concurrentHashMap = argumentsCache;
        ArgumentsInfo argumentsInfo = concurrentHashMap.get(cls);
        if (argumentsInfo == null) {
            Map mapCreateMapBuilder = MapsKt.createMapBuilder();
            Class<? super Object> superclass = cls.getSuperclass();
            if (CommonToolArguments.class.isAssignableFrom(superclass)) {
                superclass.getClass();
                mapCreateMapBuilder.putAll(getArgumentsInfo(superclass).getCliArgNameToArguments());
            }
            Field[] declaredFields = cls.getDeclaredFields();
            declaredFields.getClass();
            int length = declaredFields.length;
            int i = 0;
            while (true) {
                constructor = null;
                if (i >= length) {
                    break;
                }
                Field field = declaredFields[i];
                Argument argument = (Argument) field.getAnnotation(Argument.class);
                if (argument != null) {
                    Annotation[] annotationsByType = field.getAnnotationsByType(Enables.class);
                    annotationsByType.getClass();
                    List list = ArraysKt.toList(annotationsByType);
                    Annotation[] annotationsByType2 = field.getAnnotationsByType(Disables.class);
                    annotationsByType2.getClass();
                    List list2 = ArraysKt.toList(annotationsByType2);
                    String name = field.getName();
                    name.getClass();
                    Method method = cls.getMethod(JvmAbi.getterName(name), null);
                    String name2 = field.getName();
                    name2.getClass();
                    Method method2 = cls.getMethod(JvmAbi.setterName(name2), field.getType());
                    method.getClass();
                    method2.getClass();
                    ArgumentField argumentField = new ArgumentField(method, method2, argument, list, list2);
                    for (String str : CollectionsKt.listOf(new String[]{argument.value(), argument.shortName(), argument.deprecatedName()})) {
                        if (str.length() > 0) {
                            mapCreateMapBuilder.put(str, argumentField);
                        }
                    }
                }
                i++;
            }
            Map mapBuild = MapsKt.build(mapCreateMapBuilder);
            Constructor<?>[] constructors = cls.getConstructors();
            constructors.getClass();
            for (Constructor<?> constructor2 : constructors) {
                Parameter[] parameters = constructor2.getParameters();
                parameters.getClass();
                if (parameters.length == 0) {
                    constructor = constructor2;
                    break;
                }
            }
            ArgumentsInfo argumentsInfo2 = new ArgumentsInfo(mapBuild, constructor);
            ArgumentsInfo argumentsInfoPutIfAbsent = concurrentHashMap.putIfAbsent(cls, argumentsInfo2);
            argumentsInfo = argumentsInfoPutIfAbsent == null ? argumentsInfo2 : argumentsInfoPutIfAbsent;
        }
        return argumentsInfo;
    }

    public static final String getResolvedDelimiter(Argument argument) {
        argument.getClass();
        String strDelimiter = argument.delimiter();
        if (Intrinsics.areEqual(strDelimiter, Argument.Delimiters.none)) {
            return null;
        }
        return Intrinsics.areEqual(strDelimiter, Argument.Delimiters.pathSeparator) ? File.Companion.getPathSeparator() : argument.delimiter();
    }

    public static /* synthetic */ void getResolvedDelimiter$annotations(Argument argument) {
    }

    public static final boolean isAdvanced(Argument argument) {
        argument.getClass();
        return isSpecial(argument, "-X");
    }

    public static final boolean isInternal(Argument argument) {
        argument.getClass();
        return isSpecial(argument, INTERNAL_ARGUMENT_PREFIX);
    }

    private static final boolean isSpecial(Argument argument, String str) {
        return StringsKt.startsWith$default(argument.value(), str, false, 2, (Object) null) && argument.value().length() > str.length();
    }

    private static final boolean parseBooleanValue(String str, ArgumentField argumentField, char c, Lazy<ArgumentParseErrors> lazy) {
        String strValue = argumentField.getArgument().value();
        boolean z = false;
        if (!StringsKt.startsWith$default(str, strValue + c, false, 2, (Object) null)) {
            return true;
        }
        boolean changesLanguageFeatures = argumentField.getChangesLanguageFeatures();
        String strSubstring = str.substring(strValue.length() + 1);
        if (Intrinsics.areEqual(strSubstring, "true")) {
            z = true;
        } else if (!Intrinsics.areEqual(strSubstring, "false")) {
            if (!changesLanguageFeatures) {
                ((ArgumentParseErrors) lazy.getValue()).getBooleanArgumentsWithIncorrectValue().add(str);
            }
            z = true;
        }
        if (changesLanguageFeatures) {
            ((ArgumentParseErrors) lazy.getValue()).getBooleanLangFeatureArgumentsWithValue().add(str);
        }
        return z;
    }

    public static final <T extends CommonToolArguments> T parseCommandLineArguments(KClass<T> kClass, List<String> list) throws IllegalAccessException, InvocationTargetException {
        Object objNewInstance;
        kClass.getClass();
        list.getClass();
        Constructor<?> defaultArgsConstructor = getArgumentsInfo(JvmClassMappingKt.getJavaClass(kClass)).getDefaultArgsConstructor();
        if (defaultArgsConstructor == null || (objNewInstance = defaultArgsConstructor.newInstance(null)) == null) {
            dwe.a("Missing empty constructor on '".concat(JvmClassMappingKt.getJavaClass(kClass).getName()));
            return null;
        }
        T t = (T) KClasses.cast(kClass, objNewInstance);
        parseCommandLineArguments$default(list, t, false, 4, null);
        return t;
    }

    public static /* synthetic */ void parseCommandLineArguments$default(List list, CommonToolArguments commonToolArguments, boolean z, int i, Object obj) throws IllegalAccessException, InvocationTargetException {
        if ((i & 4) != 0) {
            z = false;
        }
        parseCommandLineArguments(list, commonToolArguments, z);
    }

    public static final <A extends CommonToolArguments> void parseCommandLineArgumentsFromEnvironment(A a) throws IllegalAccessException, InvocationTargetException {
        List listSplit;
        a.getClass();
        String value = CompilerSystemProperties.LANGUAGE_VERSION_SETTINGS.getValue();
        if (value != null) {
            if (value.length() <= 0) {
                value = null;
            }
            if (value == null || (listSplit = new Regex("\\s").split(value, 0)) == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : listSplit) {
                if (!StringsKt.isBlank((String) obj)) {
                    arrayList.add(obj);
                }
            }
            parseCommandLineArguments(arrayList, a, true);
        }
    }

    private static final <A extends CommonToolArguments> void parsePreprocessedCommandLineArguments(List<String> list, A a, Lazy<ArgumentParseErrors> lazy, boolean z) throws IllegalAccessException, InvocationTargetException {
        Lazy<ArgumentParseErrors> lazy2;
        Map<String, ArgumentField> map;
        boolean z2;
        ArgumentField argumentField;
        String strSubstring;
        String str;
        String strSubstring2;
        Object objConvertArrayOfStrings;
        Map<String, ArgumentField> cliArgNameToArguments = getArgumentsInfo(a.getClass()).getCliArgNameToArguments();
        ArrayList arrayList = new ArrayList();
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean z3 = false;
        int i = 0;
        boolean z4 = false;
        while (true) {
            if (i >= list.size()) {
                lazy2 = lazy;
                break;
            }
            int i2 = i + 1;
            String str2 = list.get(i);
            if (z4) {
                arrayList.add(str2);
            } else if (Intrinsics.areEqual(str2, "--")) {
                i = i2;
                z4 = true;
            } else {
                char c = StringsKt.startsWith$default(str2, "-XXLanguage", z3, 2, (Object) null) ? ':' : '=';
                String strSubstringBefore$default = StringsKt.substringBefore$default(str2, c, (String) null, 2, (Object) null);
                final ArgumentField argumentField2 = cliArgNameToArguments.get(strSubstringBefore$default);
                if (argumentField2 != null) {
                    Argument argument = argumentField2.getArgument();
                    Class<?> returnType = argumentField2.getGetter().getReturnType();
                    returnType.getClass();
                    KClass kotlinClass = JvmClassMappingKt.getKotlinClass(returnType);
                    if (Intrinsics.areEqual(strSubstringBefore$default, str2) || !Intrinsics.areEqual(strSubstringBefore$default, argument.shortName())) {
                        if (argument.isObsolete()) {
                            ((ArgumentParseErrors) lazy.getValue()).getUnknownArgs().add(str2);
                        }
                        String strDeprecatedName = argument.deprecatedName();
                        if (Intrinsics.areEqual(strDeprecatedName, strSubstringBefore$default)) {
                            ((ArgumentParseErrors) lazy.getValue()).getDeprecatedArguments().put(strDeprecatedName, argument.value());
                        }
                        boolean zAreEqual = Intrinsics.areEqual(argument.value(), str2);
                        Class cls = Boolean.TYPE;
                        if (zAreEqual && isAdvanced(argument) && !Intrinsics.areEqual(kotlinClass, Reflection.getOrCreateKotlinClass(cls))) {
                            ((ArgumentParseErrors) lazy.getValue()).getExtraArgumentsPassedInObsoleteForm().add(str2);
                        }
                        Lazy lazy3 = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: oya
                            public final Object invoke() {
                                return ParseCommandLineArgumentsKt.e(linkedHashMap, argumentField2);
                            }
                        });
                        if (Intrinsics.areEqual(kotlinClass, Reflection.getOrCreateKotlinClass(cls))) {
                            boolean booleanValue = parseBooleanValue(str2, argumentField2, c, lazy);
                            Boolean boolValueOf = Boolean.valueOf(booleanValue);
                            parsePreprocessedCommandLineArguments$lambda$1(lazy3).add(Boolean.valueOf(booleanValue));
                            map = cliArgNameToArguments;
                            z2 = z4;
                            argumentField = argumentField2;
                            objConvertArrayOfStrings = boolValueOf;
                        } else {
                            lazy2 = lazy;
                            Argument argument2 = argumentField2.getArgument();
                            map = cliArgNameToArguments;
                            StringBuilder sb = new StringBuilder();
                            int i3 = i;
                            sb.append(argument2.value());
                            sb.append(c);
                            z2 = z4;
                            argumentField = argumentField2;
                            if (StringsKt.startsWith$default(str2, sb.toString(), false, 2, (Object) null)) {
                                Set setCreateSetBuilder = SetsKt.createSetBuilder();
                                Iterator<T> it = argumentField.getEnablesAnnotations().iterator();
                                while (it.hasNext()) {
                                    setCreateSetBuilder.add(((Enables) it.next()).ifValueIs());
                                }
                                Iterator<T> it2 = argumentField.getDisablesAnnotations().iterator();
                                while (it2.hasNext()) {
                                    setCreateSetBuilder.add(((Disables) it2.next()).ifValueIs());
                                }
                                Set setBuild = SetsKt.build(setCreateSetBuilder);
                                strSubstring2 = str2.substring(argument2.value().length() + 1);
                                str = strSubstring2;
                                if (!setBuild.isEmpty() && !setBuild.contains(strSubstring2)) {
                                    str = strSubstring2;
                                    ((ArgumentParseErrors) lazy2.getValue()).getStringLangFeatureArgumentsWithIncorrectValue().add(TuplesKt.to(str2, setBuild));
                                    str = strSubstring2;
                                }
                            } else {
                                if (StringsKt.startsWith$default(str2, argument2.deprecatedName() + c, false, 2, (Object) null)) {
                                    strSubstring = str2.substring(argument2.deprecatedName().length() + 1);
                                } else if (i2 == list.size()) {
                                    ((ArgumentParseErrors) lazy2.getValue()).getArgumentsWithoutValue().add(str2);
                                    break;
                                } else {
                                    strSubstring = list.get(i2);
                                    i2 = i3 + 2;
                                }
                                str = strSubstring;
                            }
                            str = strSubstring2;
                            if (Intrinsics.areEqual(kotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                                parsePreprocessedCommandLineArguments$lambda$1(lazy3).add(str);
                                objConvertArrayOfStrings = str;
                            } else {
                                if (!Intrinsics.areEqual(kotlinClass, Reflection.getOrCreateKotlinClass(String[].class))) {
                                    w04.a("Unexpected argument type: ", kotlinClass);
                                    return;
                                }
                                objConvertArrayOfStrings = convertArrayOfStrings(argument2, str, z, parsePreprocessedCommandLineArguments$lambda$1(lazy3));
                            }
                        }
                        argumentField.getSetter().invoke(a, objConvertArrayOfStrings);
                        i = i2;
                        cliArgNameToArguments = map;
                        z4 = z2;
                    } else {
                        ((ArgumentParseErrors) lazy.getValue()).getUnknownArgs().add(str2);
                    }
                    z3 = false;
                } else if (StringsKt.startsWith$default(str2, "-X", z3, 2, (Object) null)) {
                    ((ArgumentParseErrors) lazy.getValue()).getUnknownExtraFlags().add(str2);
                } else if (StringsKt.startsWith$default(str2, "-", z3, 2, (Object) null)) {
                    ((ArgumentParseErrors) lazy.getValue()).getUnknownArgs().add(str2);
                } else {
                    arrayList.add(str2);
                }
            }
            i = i2;
            z3 = false;
        }
        a.setFreeArgs(CollectionsKt.plus(a.getFreeArgs(), arrayList));
        a.setExplicitArguments(linkedHashMap);
        if (a instanceof CommonCompilerArguments) {
            ArrayList arrayList2 = new ArrayList();
            String[] manuallyConfiguredFeatures = ((CommonCompilerArguments) a).getManuallyConfiguredFeatures();
            if (manuallyConfiguredFeatures == null) {
                manuallyConfiguredFeatures = new String[0];
            }
            for (String str3 : manuallyConfiguredFeatures) {
                final ManualLanguageFeatureSetting languageFeature = LanguageSettingsParser.INSTANCE.parseLanguageFeature(str3, "-XXLanguage:" + str3, (ArgumentParseErrors) lazy2.getValue());
                if (languageFeature != null) {
                    final Function1 function1 = new Function1() { // from class: pya
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(ParseCommandLineArgumentsKt.d(languageFeature, (ManualLanguageFeatureSetting) obj));
                        }
                    };
                    arrayList2.removeIf(new Predicate() { // from class: qya
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return ParseCommandLineArgumentsKt.c(function1, obj);
                        }
                    });
                    arrayList2.add(languageFeature);
                }
            }
            updateInternalArguments(a, arrayList2, z);
        }
    }

    private static final List<Object> parsePreprocessedCommandLineArguments$lambda$1(Lazy<? extends List<Object>> lazy) {
        return (List) lazy.getValue();
    }

    private static final <A extends CommonToolArguments> void updateInternalArguments(A a, ArrayList<ManualLanguageFeatureSetting> arrayList, boolean z) {
        Collection internalArguments;
        if (z) {
            List<ManualLanguageFeatureSetting> internalArguments2 = a.getInternalArguments();
            internalArguments = new ArrayList();
            for (Object obj : internalArguments2) {
                ManualLanguageFeatureSetting manualLanguageFeatureSetting = (ManualLanguageFeatureSetting) obj;
                if (arrayList == null || !arrayList.isEmpty()) {
                    Iterator<T> it = arrayList.iterator();
                    do {
                        if (it.hasNext()) {
                        }
                    } while (((ManualLanguageFeatureSetting) it.next()).getLanguageFeature() != manualLanguageFeatureSetting.getLanguageFeature());
                }
                internalArguments.add(obj);
            }
        } else {
            internalArguments = a.getInternalArguments();
        }
        a.setInternalArguments(CollectionsKt.plus(internalArguments, arrayList));
    }

    public static final String validateArguments(ArgumentParseErrors argumentParseErrors) {
        List<String> listValidateArgumentsAllErrors = validateArgumentsAllErrors(argumentParseErrors);
        if (listValidateArgumentsAllErrors.isEmpty()) {
            listValidateArgumentsAllErrors = null;
        }
        if (listValidateArgumentsAllErrors != null) {
            return CollectionsKt.joinToString$default(listValidateArgumentsAllErrors, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        return null;
    }

    public static final List<String> validateArgumentsAllErrors(ArgumentParseErrors argumentParseErrors) {
        if (argumentParseErrors == null) {
            return CollectionsKt.emptyList();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator<T> it = argumentParseErrors.getArgumentsWithoutValue().iterator();
        while (it.hasNext()) {
            listCreateListBuilder.add("No value passed for argument " + ((String) it.next()));
        }
        Iterator<T> it2 = argumentParseErrors.getBooleanArgumentsWithIncorrectValue().iterator();
        while (it2.hasNext()) {
            listCreateListBuilder.add("Incorrect value for boolean argument '" + StringsKt.substringBefore$default((String) it2.next(), '=', (String) null, 2, (Object) null) + "'. Only 'true' and 'false' are allowed.");
        }
        Iterator<T> it3 = argumentParseErrors.getBooleanLangFeatureArgumentsWithValue().iterator();
        while (it3.hasNext()) {
            listCreateListBuilder.add("No value is expected for argument '" + StringsKt.substringBefore$default((String) it3.next(), '=', (String) null, 2, (Object) null) + "'.");
        }
        Iterator<T> it4 = argumentParseErrors.getStringLangFeatureArgumentsWithIncorrectValue().iterator();
        while (it4.hasNext()) {
            Pair pair = (Pair) it4.next();
            String str = (String) pair.component1();
            Set set = (Set) pair.component2();
            List listSplit$default = StringsKt.split$default(str, new char[]{'='}, false, 0, 6, (Object) null);
            listCreateListBuilder.add("Incorrect value for argument '" + ((String) listSplit$default.get(0)) + "'. Actual value: '" + ((String) listSplit$default.get(1)) + "', but allowed values: " + CollectionsKt.joinToString$default(set, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: sya
                public final Object invoke(Object obj) {
                    return ParseCommandLineArgumentsKt.validateArgumentsAllErrors$lambda$0$3$0((String) obj);
                }
            }, 30, (Object) null) + '.');
        }
        Iterator<T> it5 = argumentParseErrors.getUnknownArgs().iterator();
        while (it5.hasNext()) {
            listCreateListBuilder.add("Invalid argument: " + ((String) it5.next()));
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence validateArgumentsAllErrors$lambda$0$3$0(String str) {
        str.getClass();
        return "'" + str + '\'';
    }

    public static final /* synthetic */ <T extends CommonToolArguments> T parseCommandLineArguments(List<String> list) {
        list.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) parseCommandLineArguments(Reflection.getOrCreateKotlinClass(CommonToolArguments.class), list);
    }

    public static final <A extends CommonToolArguments> void parseCommandLineArguments(List<String> list, final A a, boolean z) throws IllegalAccessException, InvocationTargetException {
        list.getClass();
        a.getClass();
        Lazy lazy = LazyKt.lazy(new Function0() { // from class: rya
            public final Object invoke() {
                return ParseCommandLineArgumentsKt.b(a);
            }
        });
        parsePreprocessedCommandLineArguments(PreprocessCommandLineArgumentsKt.preprocessCommandLineArguments(list, lazy), a, lazy, z);
    }
}

package org.jetbrains.kotlin.compilerRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonToolArguments;
import org.jetbrains.kotlin.cli.common.arguments.ManualLanguageFeatureSetting;
import org.jetbrains.kotlin.cli.common.arguments.ParseCommandLineArgumentsKt;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007\u001aC\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0007*\u00020\u00032\u0006\u0010\b\u001a\u0002H\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u000b\u001a1\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00102\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\u0011\u001a!\u0010\u0012\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00070\nH\u0002¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"toArgumentStrings", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "shortArgumentKeys", Argument.Delimiters.none, "compactArgumentValues", "T", "thisArguments", ModuleXmlParser.TYPE, "Lkotlin/reflect/KClass;", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;Lkotlin/reflect/KClass;ZZ)Ljava/util/List;", "getArgumentStringValue", "argumentAnnotation", "Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", "values", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/cli/common/arguments/Argument;[Ljava/lang/Object;Z)Ljava/util/List;", "newArgumentsInstance", "(Lkotlin/reflect/KClass;)Lorg/jetbrains/kotlin/cli/common/arguments/CommonToolArguments;", "org.jetbrains.kotlin:kotlin-build-common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArgumentsToStrings {
    private static final List<String> getArgumentStringValue(Argument argument, Object[] objArr, boolean z) {
        if (objArr == null || objArr.length == 0) {
            return CollectionsKt.emptyList();
        }
        String resolvedDelimiter = ParseCommandLineArgumentsKt.getResolvedDelimiter(argument);
        if (resolvedDelimiter != null && resolvedDelimiter.length() != 0 && z) {
            return CollectionsKt.listOf(ArraysKt.joinToString$default(objArr, resolvedDelimiter, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        }
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(String.valueOf(obj));
        }
        return arrayList;
    }

    private static final <T extends CommonToolArguments> T newArgumentsInstance(KClass<T> kClass) {
        Object next;
        Iterator it = kClass.getConstructors().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((KFunction) next).getParameters().isEmpty());
        KFunction kFunction = (KFunction) next;
        if (kFunction != null) {
            return (T) kFunction.call(new Object[0]);
        }
        iu9.a(kClass.getQualifiedName(), " has no empty constructor");
        return null;
    }

    public static final <T extends CommonToolArguments> List<String> toArgumentStrings(T t, KClass<T> kClass, boolean z, boolean z2) {
        Argument argument;
        List<String> listListOf;
        Class javaClass;
        String string;
        t.getClass();
        kClass.getClass();
        ArrayList arrayList = new ArrayList();
        CommonToolArguments commonToolArgumentsNewArgumentsInstance = newArgumentsInstance(kClass);
        for (KProperty1 kProperty1 : KClasses.getMemberProperties(kClass)) {
            Field javaField = ReflectJvmMapping.getJavaField(kProperty1);
            if (javaField != null && (argument = (Argument) javaField.getAnnotation(Argument.class)) != null) {
                Object obj = kProperty1.get(t);
                if (!Intrinsics.areEqual(obj, kProperty1.get(commonToolArgumentsNewArgumentsInstance))) {
                    KClassifier classifier = kProperty1.getReturnType().getClassifier();
                    Class cls = Boolean.TYPE;
                    if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(cls))) {
                        if (obj == null || (string = obj.toString()) == null) {
                            string = "false";
                        }
                        listListOf = CollectionsKt.listOf(string);
                    } else {
                        KClass classifier2 = kProperty1.getReturnType().getClassifier();
                        KClass kClass2 = classifier2 instanceof KClass ? classifier2 : null;
                        if (kClass2 != null && (javaClass = JvmClassMappingKt.getJavaClass(kClass2)) != null && javaClass.isArray()) {
                            listListOf = getArgumentStringValue(argument, (Object[]) obj, z2);
                        } else if (Intrinsics.areEqual(kProperty1.getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(List.class))) {
                            List list = (List) obj;
                            listListOf = getArgumentStringValue(argument, list != null ? list.toArray(new Object[0]) : null, z2);
                        } else {
                            listListOf = CollectionsKt.listOf(String.valueOf(obj));
                        }
                    }
                    String strValue = (!z || argument.shortName().length() <= 0) ? argument.value() : argument.shortName();
                    for (String str : listListOf) {
                        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                            arrayList.add(strValue);
                        } else if (Intrinsics.areEqual(argument.value(), "-XXLanguage")) {
                            arrayList.add(strValue + ':' + str);
                        } else if (ParseCommandLineArgumentsKt.isAdvanced(argument) || Intrinsics.areEqual(kProperty1.getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(cls))) {
                            arrayList.add(strValue + '=' + str);
                        } else {
                            arrayList.add(strValue);
                            arrayList.add(str);
                        }
                    }
                }
            }
        }
        arrayList.addAll(t.getFreeArgs());
        List<ManualLanguageFeatureSetting> internalArguments = t.getInternalArguments();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(internalArguments, 10));
        Iterator<T> it = internalArguments.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ManualLanguageFeatureSetting) it.next()).getStringRepresentation());
        }
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    public static /* synthetic */ List toArgumentStrings$default(CommonToolArguments commonToolArguments, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return toArgumentStrings(commonToolArguments, z, z2);
    }

    public static final List<String> toArgumentStrings(CommonToolArguments commonToolArguments, boolean z) {
        commonToolArguments.getClass();
        return toArgumentStrings$default(commonToolArguments, z, false, 2, null);
    }

    public static final List<String> toArgumentStrings(CommonToolArguments commonToolArguments, boolean z, boolean z2) {
        commonToolArguments.getClass();
        return toArgumentStrings(commonToolArguments, Reflection.getOrCreateKotlinClass(commonToolArguments.getClass()), z, z2);
    }

    public static final List<String> toArgumentStrings(CommonToolArguments commonToolArguments) {
        commonToolArguments.getClass();
        return toArgumentStrings$default(commonToolArguments, false, false, 3, null);
    }
}

package org.jetbrains.kotlin.config.nativeBinaryOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\u001aM\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\n\u001aD\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\f\u0018\u00010\u0002\"\b\b\u0000\u0010\f*\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007H\u0002\u001a?\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00122\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007H\u0002¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"parseBinaryOptions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOptionWithValue;", "argumentValue", Argument.Delimiters.none, Argument.Delimiters.none, "reportWarning", "Lkotlin/Function1;", Argument.Delimiters.none, "reportError", "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "parseBinaryOption", "T", Argument.Delimiters.none, "option", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption;", "valueName", "parseKeyValuePairs", Argument.Delimiters.none, "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "org.jetbrains.kotlin:binary-options"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ParsingKt {
    private static final <T> BinaryOptionWithValue<T> parseBinaryOption(BinaryOption<T> binaryOption, String str, Function1<? super String, Unit> function1) {
        T t = binaryOption.getValueParser().parse(str);
        if (t != null) {
            return new BinaryOptionWithValue<>(binaryOption.getCompilerConfigurationKey(), t, str);
        }
        function1.invoke("Unknown value '" + str + "' of binary option '" + binaryOption.getName() + "'. Possible values are: " + binaryOption.getValueParser().getValidValuesHint());
        return null;
    }

    public static final List<BinaryOptionWithValue<?>> parseBinaryOptions(String[] strArr, Function1<? super String, Unit> function1, Function1<? super String, Unit> function2) {
        BinaryOptionWithValue binaryOption;
        function1.getClass();
        function2.getClass();
        Map<String, String> keyValuePairs = parseKeyValuePairs(strArr, function2);
        if (keyValuePairs == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            BinaryOption<?> byName = BinaryOptions.INSTANCE.getByName(key);
            if (byName == null) {
                function1.invoke("Unknown binary option '" + key + '\'');
                binaryOption = null;
            } else {
                binaryOption = parseBinaryOption(byName, value, function1);
            }
            if (binaryOption != null) {
                arrayList.add(binaryOption);
            }
        }
        return arrayList;
    }

    private static final Map<String, String> parseKeyValuePairs(String[] strArr, Function1<? super String, Unit> function1) {
        Pair pair;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (StringsKt.isBlank(str)) {
                pair = null;
            } else if (StringsKt.indexOf$default(str, '=', 0, false, 6, (Object) null) > 0) {
                pair = TuplesKt.to(StringsKt.substringBefore$default(str, '=', (String) null, 2, (Object) null), StringsKt.substringAfter$default(str, '=', (String) null, 2, (Object) null));
            } else {
                function1.invoke("incorrect property format: expected '<key>=<value>', got '" + str + '\'');
                pair = null;
            }
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return MapsKt.toMap(arrayList);
    }
}

package org.jetbrains.kotlin.compiler.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u001a\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0001\u001a\u0010\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0001\u001a\u001e\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001¨\u0006\r"}, d2 = {"cliPluginUsageString", Argument.Delimiters.none, "pluginId", "options", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/AbstractCliOption;", "parseLegacyPluginOption", "Lorg/jetbrains/kotlin/compiler/plugin/CliOptionValue;", "argumentValue", "parseModernPluginOption", "getPluginOptionString", "key", "value", "org.jetbrains.kotlin:plugin-api"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliOptionsKt {
    public static final String cliPluginUsageString(String str, Collection<? extends AbstractCliOption> collection) {
        str.getClass();
        collection.getClass();
        Collection<? extends AbstractCliOption> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        for (AbstractCliOption abstractCliOption : collection2) {
            String str2 = abstractCliOption.getOptionName() + ' ' + abstractCliOption.getValueDescription();
            String strRepeat = str2.length() > 26 ? "\n" + StringsKt.repeat(Argument.Delimiters.space, 29) : StringsKt.repeat(Argument.Delimiters.space, 27 - str2.length());
            List listListOfNotNull = CollectionsKt.listOfNotNull(new String[]{abstractCliOption.getRequired() ? "required" : null, abstractCliOption.getAllowMultipleOccurrences() ? "multiple" : null});
            arrayList.add(StringsKt.repeat(Argument.Delimiters.space, 2) + str2 + strRepeat + abstractCliOption.getDescription() + (listListOfNotNull.isEmpty() ? Argument.Delimiters.none : " (" + CollectionsKt.joinToString$default(listListOfNotNull, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + ')'));
        }
        return "Plugin \"" + str + "\" usage:\n" + CollectionsKt.joinToString$default(arrayList, "\n", (CharSequence) null, "\n", 0, (CharSequence) null, (Function1) null, 58, (Object) null);
    }

    public static final String getPluginOptionString(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        return "plugin:" + str + ':' + str2 + '=' + str3;
    }

    public static final CliOptionValue parseLegacyPluginOption(String str) {
        str.getClass();
        Matcher matcher = Pattern.compile("^plugin:([^:]*):([^=]*)=(.*)$").matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(1);
        strGroup.getClass();
        String strGroup2 = matcher.group(2);
        strGroup2.getClass();
        String strGroup3 = matcher.group(3);
        strGroup3.getClass();
        return new CliOptionValue(strGroup, strGroup2, strGroup3);
    }

    public static final CliOptionValue parseModernPluginOption(String str) {
        str.getClass();
        Matcher matcher = Pattern.compile("^([^=]*)=(.*)$").matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(1);
        strGroup.getClass();
        String strGroup2 = matcher.group(2);
        strGroup2.getClass();
        return new CliOptionValue("<NO_ID>", strGroup, strGroup2);
    }
}

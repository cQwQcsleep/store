package org.jetbrains.kotlin.codegen;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.checkers.DalvikIdentifierUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"mangleNameIfNeeded", Argument.Delimiters.none, ModuleXmlParser.NAME, "isValidCharacter", Argument.Delimiters.none, Argument.Delimiters.none, "sanitizeNameIfNeeded", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:backend.common.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonVariableAsmNameManglingUtils {
    private static final boolean isValidCharacter(char c) {
        return (c == '$' || c == '-' || !DalvikIdentifierUtils.isValidDalvikCharacter(c)) ? false : true;
    }

    public static final String mangleNameIfNeeded(String str) {
        str.getClass();
        for (int i = 0; i < str.length(); i++) {
            if (!isValidCharacter(str.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                int length = str.length();
                for (int i2 = 0; i2 < length; i2++) {
                    char cCharAt = str.charAt(i2);
                    if (isValidCharacter(cCharAt)) {
                        sb.append(cCharAt);
                    } else {
                        String hexString = Integer.toHexString(cCharAt);
                        hexString.length();
                        sb.append("_u");
                        sb.append(hexString);
                    }
                }
                return sb.toString();
            }
        }
        return str;
    }

    public static final String sanitizeNameIfNeeded(String str, LanguageVersionSettings languageVersionSettings) {
        str.getClass();
        languageVersionSettings.getClass();
        return ((Boolean) languageVersionSettings.getFlag(JvmAnalysisFlags.getSanitizeParentheses())).booleanValue() ? StringsKt.replace$default(StringsKt.replace$default(str, "(", "$_", false, 4, (Object) null), ")", "$_", false, 4, (Object) null) : str;
    }
}

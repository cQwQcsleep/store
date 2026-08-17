package org.jetbrains.kotlin.cli.common;

import com.intellij.openapi.util.SystemInfo;
import java.lang.reflect.Field;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.ParseCommandLineArgumentsKt;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class Usage {
    public static final String BAT_DELIMITER_CHARACTERS_NOTE = "Note: on Windows, arguments that contain delimiter characters (whitespace, =, ;, ,) need to be surrounded with double quotes (\").";

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        switch (i) {
            case 1:
                objArr[0] = "arguments";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                objArr[0] = "org/jetbrains/kotlin/cli/common/Usage";
                break;
            case 3:
            case 5:
            case 6:
            case 7:
                objArr[0] = "sb";
                break;
            case 4:
                objArr[0] = "field";
                break;
            case 8:
                objArr[0] = "string";
                break;
            default:
                objArr[0] = "compiler";
                break;
        }
        if (i != 2) {
            objArr[1] = "org/jetbrains/kotlin/cli/common/Usage";
        } else {
            objArr[1] = "render";
        }
        switch (i) {
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                break;
            case 3:
            case 4:
                objArr[2] = "fieldUsage";
                break;
            case 5:
                objArr[2] = "renderOptionJUsage";
                break;
            case 6:
                objArr[2] = "renderArgfileUsage";
                break;
            case 7:
            case 8:
                objArr[2] = "appendln";
                break;
            default:
                objArr[2] = "render";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    private static void appendln(StringBuilder sb, String str) {
        if (sb == null) {
            $$$reportNull$$$0(7);
        }
        if (str == null) {
            $$$reportNull$$$0(8);
        }
        sb.append(str);
        sb.append('\n');
    }

    private static void fieldUsage(StringBuilder sb, Field field, boolean z) {
        if (sb == null) {
            $$$reportNull$$$0(3);
        }
        if (field == null) {
            $$$reportNull$$$0(4);
        }
        Argument argument = (Argument) field.getAnnotation(Argument.class);
        if (argument == null || argument.isObsolete() || ParseCommandLineArgumentsKt.isInternal(argument) || z != ParseCommandLineArgumentsKt.isAdvanced(argument)) {
            return;
        }
        int length = sb.length();
        sb.append("  ");
        sb.append(argument.value());
        if (!argument.shortName().isEmpty()) {
            sb.append(" (");
            sb.append(argument.shortName());
            sb.append(")");
        }
        if (!argument.valueDescription().isEmpty()) {
            sb.append(ParseCommandLineArgumentsKt.isAdvanced(argument) ? "=" : Argument.Delimiters.space);
            sb.append(argument.valueDescription());
        }
        int length2 = length + 28;
        if (sb.length() >= length + 33) {
            sb.append("\n");
            length2 += sb.length() - length;
        }
        while (sb.length() < length2) {
            sb.append(Argument.Delimiters.space);
        }
        sb.append(Argument.Delimiters.space);
        appendln(sb, argument.description().replace("\n", "\n" + StringsKt.repeat(Argument.Delimiters.space, 29)));
    }

    public static <A extends CommonCompilerArguments> String render(CLICompiler<A> cLICompiler, A a) {
        if (cLICompiler == null) {
            $$$reportNull$$$0(0);
        }
        if (a == null) {
            $$$reportNull$$$0(1);
        }
        boolean extraHelp = a.getExtraHelp();
        StringBuilder sb = new StringBuilder();
        appendln(sb, "Usage: " + cLICompiler.executableScriptFileName() + " <options> <source files>");
        StringBuilder sb2 = new StringBuilder("where ");
        sb2.append(extraHelp ? "advanced" : "possible");
        sb2.append(" options include:");
        appendln(sb, sb2.toString());
        for (Class<?> superclass = a.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (Field field : superclass.getDeclaredFields()) {
                fieldUsage(sb, field, extraHelp);
            }
        }
        if (extraHelp) {
            appendln(sb, Argument.Delimiters.none);
            appendln(sb, "Advanced options are non-standard and may be changed or removed without any notice.");
        } else {
            renderOptionJUsage(sb);
            renderArgfileUsage(sb);
        }
        if (SystemInfo.isWindows) {
            appendln(sb, Argument.Delimiters.none);
            appendln(sb, BAT_DELIMITER_CHARACTERS_NOTE);
        }
        if (!extraHelp) {
            appendln(sb, Argument.Delimiters.none);
            appendln(sb, "For details, see https://kotl.in/cli");
        }
        return sb.toString();
    }

    private static void renderArgfileUsage(StringBuilder sb) {
        if (sb == null) {
            $$$reportNull$$$0(6);
        }
        int length = sb.length() + 29;
        sb.append("  ");
        sb.append(PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT);
        sb.append("<argfile>");
        while (sb.length() < length) {
            sb.append(Argument.Delimiters.space);
        }
        appendln(sb, "Read compiler arguments and file paths from the given file.");
    }

    private static void renderOptionJUsage(StringBuilder sb) {
        if (sb == null) {
            $$$reportNull$$$0(5);
        }
        int length = sb.length() + 29;
        sb.append("  -J<option>");
        while (sb.length() < length) {
            sb.append(Argument.Delimiters.space);
        }
        appendln(sb, "Pass an option directly to JVM.");
    }
}

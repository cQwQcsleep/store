package org.jetbrains.kotlin.compilerRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.jetbrains.kotlin.cli.common.arguments.CommonToolArguments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class ArgumentUtils {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "arguments";
        } else {
            objArr[0] = "org/jetbrains/kotlin/compilerRunner/ArgumentUtils";
        }
        if (i != 1) {
            objArr[1] = "org/jetbrains/kotlin/compilerRunner/ArgumentUtils";
        } else {
            objArr[1] = "convertArgumentsToStringList";
        }
        if (i != 1) {
            if (i == 2) {
                objArr[2] = "convertArgumentsToStringListNoDefaults";
            } else if (i != 3) {
                objArr[2] = "convertArgumentsToStringList";
            } else {
                objArr[2] = "convertArgumentsToStringListInternal";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    private ArgumentUtils() {
    }

    public static List<String> convertArgumentsToStringList(CommonToolArguments commonToolArguments) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (commonToolArguments == null) {
            $$$reportNull$$$0(0);
        }
        List<String> listConvertArgumentsToStringListInternal = convertArgumentsToStringListInternal(commonToolArguments);
        if (listConvertArgumentsToStringListInternal == null) {
            $$$reportNull$$$0(1);
        }
        return listConvertArgumentsToStringListInternal;
    }

    private static List<String> convertArgumentsToStringListInternal(CommonToolArguments commonToolArguments) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (commonToolArguments == null) {
            $$$reportNull$$$0(3);
        }
        return ArgumentsToStrings.toArgumentStrings(commonToolArguments, false);
    }

    @Deprecated
    public static List<String> convertArgumentsToStringListNoDefaults(CommonToolArguments commonToolArguments) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (commonToolArguments == null) {
            $$$reportNull$$$0(2);
        }
        return convertArgumentsToStringList(commonToolArguments);
    }
}

package com.intellij.util.text;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.openapi.util.Condition;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class UniqueNameGenerator implements Condition<String> {
    private final Set<String> myExistingNames = new HashSet();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 32 || i == 33 || i == 37 || i == 43 || i == 46) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 32 || i == 33 || i == 37 || i == 43 || i == 46) ? 2 : 3];
        switch (i) {
            case 1:
            case 2:
                objArr[0] = "candidate";
                break;
            case 3:
                objArr[0] = "name";
                break;
            case 4:
            case 9:
            case 17:
            case 21:
            case 27:
            case 35:
            case 39:
                objArr[0] = "prefix";
                break;
            case 5:
            case 10:
            case 18:
            case 22:
            case 28:
            case 36:
            case 40:
                objArr[0] = "suffix";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 12:
            case 14:
            case 16:
            case 20:
            case 26:
            case 34:
            case 38:
            case 45:
                objArr[0] = "defaultName";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 11:
                objArr[0] = "existingNames";
                break;
            case 13:
            case 15:
            case 19:
            case 25:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[0] = "validator";
                break;
            case 23:
            case 29:
            case 41:
                objArr[0] = "beforeNumber";
                break;
            case 24:
            case 30:
            case 42:
                objArr[0] = "afterNumber";
                break;
            case 32:
            case 33:
            case 37:
            case 43:
            case 46:
                objArr[0] = "com/intellij/util/text/UniqueNameGenerator";
                break;
            case 44:
                objArr[0] = "result";
                break;
            default:
                objArr[0] = "elements";
                break;
        }
        if (i == 32 || i == 33 || i == 37 || i == 43 || i == 46) {
            objArr[1] = "generateUniqueName";
        } else {
            objArr[1] = "com/intellij/util/text/UniqueNameGenerator";
        }
        switch (i) {
            case 1:
                objArr[2] = "value";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                objArr[2] = "isUnique";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 34:
            case 35:
            case 36:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 45:
                objArr[2] = "generateUniqueName";
                break;
            case 14:
            case 15:
                objArr[2] = "generateUniqueNameOneBased";
                break;
            case 32:
            case 33:
            case 37:
            case 43:
            case 46:
                break;
            case 44:
                objArr[2] = "addExistingName";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 32 && i != 33 && i != 37 && i != 43 && i != 46) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private static String generateUniqueName(String str, String str2, String str3, String str4, String str5, Condition<? super String> condition, int i) {
        String strTrim;
        if (str == null) {
            $$$reportNull$$$0(26);
        }
        if (str2 == null) {
            $$$reportNull$$$0(27);
        }
        if (str3 == null) {
            $$$reportNull$$$0(28);
        }
        if (str4 == null) {
            $$$reportNull$$$0(29);
        }
        if (str5 == null) {
            $$$reportNull$$$0(30);
        }
        if (condition == null) {
            $$$reportNull$$$0(31);
        }
        String strTrim2 = (str2 + str + str3).trim();
        if (condition.value(strTrim2)) {
            if (strTrim2 == null) {
                $$$reportNull$$$0(32);
            }
            return strTrim2;
        }
        Matcher matcher = Pattern.compile("(.+?)" + Pattern.quote(str4) + "(\\d{1,9})").matcher(str);
        if (matcher.matches()) {
            String strGroup = matcher.group(1);
            i = 1 + Integer.parseInt(matcher.group(2));
            str = strGroup;
        }
        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(str);
            sb.append(str4);
            int i2 = i + 1;
            sb.append(i);
            sb.append(str5);
            sb.append(str3);
            strTrim = sb.toString().trim();
            if (condition.value(strTrim)) {
                break;
            }
            i = i2;
        }
        if (strTrim == null) {
            $$$reportNull$$$0(33);
        }
        return strTrim;
    }

    public void addExistingName(String str) {
        if (str == null) {
            $$$reportNull$$$0(44);
        }
        this.myExistingNames.add(str);
    }

    public final boolean isUnique(String str) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        return !this.myExistingNames.contains(str);
    }

    public final boolean value(String str) {
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        return isUnique(str);
    }

    public static String generateUniqueName(String str, String str2, String str3, String str4, String str5, Condition<? super String> condition) {
        if (str == null) {
            $$$reportNull$$$0(20);
        }
        if (str2 == null) {
            $$$reportNull$$$0(21);
        }
        if (str3 == null) {
            $$$reportNull$$$0(22);
        }
        if (str4 == null) {
            $$$reportNull$$$0(23);
        }
        if (str5 == null) {
            $$$reportNull$$$0(24);
        }
        if (condition == null) {
            $$$reportNull$$$0(25);
        }
        return generateUniqueName(str, str2, str3, str4, str5, condition, 2);
    }

    public String generateUniqueName(String str, String str2, String str3) {
        if (str == null) {
            $$$reportNull$$$0(34);
        }
        if (str2 == null) {
            $$$reportNull$$$0(35);
        }
        if (str3 == null) {
            $$$reportNull$$$0(36);
        }
        String strGenerateUniqueName = generateUniqueName(str, str2, str3, "", "");
        if (strGenerateUniqueName == null) {
            $$$reportNull$$$0(37);
        }
        return strGenerateUniqueName;
    }

    public String generateUniqueName(String str, String str2, String str3, String str4, String str5) {
        if (str == null) {
            $$$reportNull$$$0(38);
        }
        if (str2 == null) {
            $$$reportNull$$$0(39);
        }
        if (str3 == null) {
            $$$reportNull$$$0(40);
        }
        if (str4 == null) {
            $$$reportNull$$$0(41);
        }
        if (str5 == null) {
            $$$reportNull$$$0(42);
        }
        String strGenerateUniqueName = generateUniqueName(str, str2, str3, str4, str5, this);
        addExistingName(strGenerateUniqueName);
        if (strGenerateUniqueName == null) {
            $$$reportNull$$$0(43);
        }
        return strGenerateUniqueName;
    }

    public String generateUniqueName(String str) {
        if (str == null) {
            $$$reportNull$$$0(45);
        }
        String strGenerateUniqueName = generateUniqueName(str, "", "");
        if (strGenerateUniqueName == null) {
            $$$reportNull$$$0(46);
        }
        return strGenerateUniqueName;
    }
}

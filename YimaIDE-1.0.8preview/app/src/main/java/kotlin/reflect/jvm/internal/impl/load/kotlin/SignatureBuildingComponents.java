package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.compose.compiler.plugins.kotlin.analysis.StabilityExternalClassNameMatchingKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class SignatureBuildingComponents {
    public static final SignatureBuildingComponents INSTANCE = new SignatureBuildingComponents();

    private SignatureBuildingComponents() {
    }

    private final String escapeClassName(String str) {
        if (str.length() <= 1) {
            return str;
        }
        return "L" + str + ';';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence jvmDescriptor$lambda$0(String str) {
        str.getClass();
        return INSTANCE.escapeClassName(str);
    }

    public final String[] constructors(String... strArr) {
        strArr.getClass();
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add("<init>(" + str + ")V");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public final Set<String> inClass(String str, String... strArr) {
        str.getClass();
        strArr.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str2 : strArr) {
            linkedHashSet.add(str + StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR + str2);
        }
        return linkedHashSet;
    }

    public final Set<String> inJavaLang(String str, String... strArr) {
        str.getClass();
        strArr.getClass();
        return inClass(javaLang(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final Set<String> inJavaUtil(String str, String... strArr) {
        str.getClass();
        strArr.getClass();
        return inClass(javaUtil(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final String javaFunction(String str) {
        str.getClass();
        return "java/util/function/" + str;
    }

    public final String javaLang(String str) {
        str.getClass();
        return "java/lang/" + str;
    }

    public final String javaUtil(String str) {
        str.getClass();
        return "java/util/" + str;
    }

    public final String javaUtilConcurrentAtomic(String str) {
        str.getClass();
        return "java/util/concurrent/atomic/" + str;
    }

    public final String jvmDescriptor(String str, List<String> list, String str2) {
        str.getClass();
        list.getClass();
        str2.getClass();
        return str + '(' + CollectionsKt.joinToString$default(list, HttpUrl.FRAGMENT_ENCODE_SET, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents$$Lambda$0
            public Object invoke(Object obj) {
                return SignatureBuildingComponents.jvmDescriptor$lambda$0((String) obj);
            }
        }, 30, (Object) null) + ')' + escapeClassName(str2);
    }

    public final String signature(String str, String str2) {
        str.getClass();
        str2.getClass();
        return str + StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR + str2;
    }
}

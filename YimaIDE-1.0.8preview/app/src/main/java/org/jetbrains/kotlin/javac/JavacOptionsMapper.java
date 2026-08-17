package org.jetbrains.kotlin.javac;

import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.Options;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0014\u0010\u000f\u001a\u00020\u0005*\u00020\u00072\u0006\u0010\u0010\u001a\u00020\nH\u0002R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/javac/JavacOptionsMapper;", "", "<init>", "()V", "map", "", "options", "Lcom/sun/tools/javac/util/Options;", "arguments", "", "", "setUTF8Encoding", "optionPattern", "Ljava/util/regex/Pattern;", JvmProtoBufUtil.PLATFORM_TYPE_ID, "putOption", "option", "org.jetbrains.kotlin:javac-wrapper"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavacOptionsMapper {
    public static final JavacOptionsMapper INSTANCE = new JavacOptionsMapper();
    private static final Pattern optionPattern = Pattern.compile("\\s+");

    private JavacOptionsMapper() {
    }

    private final void putOption(Options options, String str) {
        Pattern pattern = optionPattern;
        pattern.getClass();
        List listSplit$default = StringsKt.split$default(str, pattern, 0, 2, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit$default) {
            if (((String) obj).length() > 0) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        if (size == 1) {
            options.put((String) arrayList.get(0), (String) arrayList.get(0));
        } else {
            if (size != 2) {
                return;
            }
            options.put((String) arrayList.get(0), (String) arrayList.get(1));
        }
    }

    public final void map(Options options, List<String> arguments) {
        options.getClass();
        arguments.getClass();
        Iterator<T> it = arguments.iterator();
        while (it.hasNext()) {
            INSTANCE.putOption(options, (String) it.next());
        }
    }

    public final void setUTF8Encoding(Options options) {
        options.getClass();
        options.put(Option.ENCODING, "UTF8");
    }
}

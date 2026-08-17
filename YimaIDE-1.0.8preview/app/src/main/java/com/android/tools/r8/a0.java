package com.android.tools.r8;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.EnumC3077y2;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class a0 implements ParseFlagInfo {
    public static final /* synthetic */ boolean d = true;
    public final String a;
    public final List b;
    public final List c;

    public a0(String str, List list, List list2) {
        boolean z = d;
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        if (!z && list == null) {
            x1f.a();
            throw null;
        }
        if (!z && list2 == null) {
            x1f.a();
            throw null;
        }
        this.a = str;
        this.b = list;
        this.c = list2;
    }

    public static AbstractC0551Hu a() {
        return AbstractC0551Hu.a(new a0("--force-enable-assertions[:[<class name>|<package name>...]]", Collections.singletonList("--force-ea[:[<class name>|<package name>...]]"), Arrays.asList("Forcefully enable javac generated assertion code.")), new a0("--force-disable-assertions[:[<class name>|<package name>...]]", Collections.singletonList("--force-da[:[<class name>|<package name>...]]"), Arrays.asList("Forcefully disable javac generated assertion code.", "This is the default handling of javac assertion code", "when generating DEX file format.")), new a0("--force-passthrough-assertions[:[<class name>|<package name>...]]", Collections.singletonList("--force-pa[:[<class name>|<package name>...]]"), Arrays.asList("Don't change javac generated assertion code. This", "is the default handling of javac assertion code when", "generating class file format.")), new a0("--force-assertions-handler:<handler method>[:[<class name>|<package name>...]]", Collections.singletonList("--force-ah:<handler method>[:[<class name>|<package name>...]]"), Arrays.asList("Change javac and kotlinc generated assertion code", "to invoke the method <handler method> with each", "assertion error instead of throwing it.", "The <handler method> is specified as a class name", "followed by a dot and the method name.", "The handler method must take a single argument of", "type java.lang.Throwable and have return type void.")));
    }

    public static a0 b(boolean z) {
        return a("--release", Collections.EMPTY_LIST, Arrays.asList("Compile without debugging information" + (z ? " (default)" : XmlPullParser.NO_NAMESPACE) + "."));
    }

    public static a0 c() {
        return a("--help", Collections.EMPTY_LIST, Arrays.asList("Print this message."));
    }

    public static a0 d() {
        return a("--map-diagnostics[:<type>]", AbstractC0551Hu.a("<from-level>", "<to-level>"), Arrays.asList("Map diagnostics of <type> (default any) reported as", "<from-level> to <to-level> where <from-level> and", "<to-level> are one of 'info', 'warning', or 'error'", "and the optional <type> is either the simple or", "fully qualified Java type name of a diagnostic.", "If <type> is unspecified, all diagnostics at ", "<from-level> will be mapped.", "Note that fatal compiler errors cannot be mapped."));
    }

    public static a0 e() {
        return a("--min-api", Collections.singletonList("<number>"), Arrays.asList("Minimum Android API level compatibility (default: " + EnumC3077y2.b().d() + ")."));
    }

    @Override // com.android.tools.r8.ParseFlagInfo
    public final String getFlagFormat() {
        return this.a;
    }

    @Override // com.android.tools.r8.ParseFlagInfo
    public final List getFlagFormatAlternatives() {
        return this.b;
    }

    @Override // com.android.tools.r8.ParseFlagInfo
    public final List getFlagHelp() {
        return this.c;
    }

    public static a0 b() {
        return a("--dex", Collections.EMPTY_LIST, Arrays.asList("Compile program to DEX file format (default)."));
    }

    public static a0 a(String str) {
        return a("--version", Collections.EMPTY_LIST, Arrays.asList("Print the version of " + str + "."));
    }

    public static a0 a(boolean z) {
        return a("--debug", Collections.EMPTY_LIST, Arrays.asList("Compile with debugging information" + (z ? " (default)" : XmlPullParser.NO_NAMESPACE) + "."));
    }

    public static a0 a(String str, List list, List list2) {
        StringBuilder sb = new StringBuilder(str);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            sb.append(" ");
            sb.append(str2);
        }
        return new a0(sb.toString(), Collections.EMPTY_LIST, list2);
    }
}

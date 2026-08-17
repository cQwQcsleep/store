package com.android.tools.r8;

import com.android.tools.r8.AssertionsConfiguration;
import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.hkh;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3309m {
    public static final /* synthetic */ boolean a = true;

    public final boolean a(BaseCompilerCommand.Builder builder, String str, Origin origin) {
        String strSubstring;
        EnumC3308l enumC3308l;
        MethodReference methodReferenceMethodFromDescriptor;
        if (str.startsWith("--force-enable-assertions")) {
            enumC3308l = EnumC3308l.b;
            strSubstring = str.substring(25);
        } else if (str.startsWith("--force-ea")) {
            enumC3308l = EnumC3308l.b;
            strSubstring = str.substring(10);
        } else if (str.startsWith("--force-disable-assertions")) {
            enumC3308l = EnumC3308l.c;
            strSubstring = str.substring(26);
        } else if (str.startsWith("--force-da")) {
            enumC3308l = EnumC3308l.c;
            strSubstring = str.substring(10);
        } else if (str.startsWith("--force-passthrough-assertions")) {
            enumC3308l = EnumC3308l.d;
            strSubstring = str.substring(30);
        } else if (str.startsWith("--force-pa")) {
            enumC3308l = EnumC3308l.d;
            strSubstring = str.substring(10);
        } else if (str.startsWith("--force-assertions-handler")) {
            enumC3308l = EnumC3308l.e;
            strSubstring = str.substring(26);
        } else if (str.startsWith("--force-ah")) {
            enumC3308l = EnumC3308l.e;
            strSubstring = str.substring(10);
        } else {
            strSubstring = null;
            enumC3308l = null;
        }
        if (enumC3308l != EnumC3308l.e) {
            methodReferenceMethodFromDescriptor = null;
        } else {
            if (strSubstring.length() == 0 || (strSubstring.length() == 1 && strSubstring.charAt(0) == ':')) {
                throw builder.fatalError(new StringDiagnostic("Missing required argument <handler method>", origin));
            }
            if (strSubstring.charAt(0) != ':') {
                return false;
            }
            String strSubstring2 = strSubstring.substring(1);
            int iIndexOf = strSubstring2.indexOf(58);
            if (iIndexOf == 0) {
                throw builder.fatalError(new StringDiagnostic("Missing required argument <handler method>", origin));
            }
            String strSubstring3 = iIndexOf > 0 ? strSubstring2.substring(0, iIndexOf) : strSubstring2;
            int iLastIndexOf = strSubstring3.lastIndexOf(46);
            if (strSubstring3.length() < 3 || iLastIndexOf <= 0 || iLastIndexOf == strSubstring3.length() - 1 || !C0929Wj.F(strSubstring3.substring(0, iLastIndexOf))) {
                throw builder.fatalError(new StringDiagnostic("Invalid argument <handler method>: ".concat(strSubstring3), origin));
            }
            methodReferenceMethodFromDescriptor = Reference.methodFromDescriptor(C0929Wj.I(strSubstring3.substring(0, iLastIndexOf)), strSubstring3.substring(iLastIndexOf + 1), "(Ljava/lang/Throwable;)V");
            strSubstring = strSubstring2.substring(strSubstring3.length());
        }
        if (enumC3308l == null) {
            return false;
        }
        if (strSubstring.length() == 0) {
            a(builder, enumC3308l, methodReferenceMethodFromDescriptor, (String) null);
            return true;
        }
        if (strSubstring.length() == 1 && strSubstring.charAt(0) == ':') {
            throw builder.fatalError(new StringDiagnostic("Missing optional argument", origin));
        }
        if (strSubstring.charAt(0) != ':') {
            return false;
        }
        String strSubstring4 = strSubstring.substring(1);
        if (strSubstring4.contains(";") || strSubstring4.contains("[") || strSubstring4.contains("/")) {
            builder.error(new StringDiagnostic("Illegal assertion scope: ".concat(strSubstring4), origin));
        }
        a(builder, enumC3308l, methodReferenceMethodFromDescriptor, strSubstring.substring(1));
        return true;
    }

    public final AssertionsConfiguration b(EnumC3308l enumC3308l, MethodReference methodReference, String str, AssertionsConfiguration.Builder builder) {
        AssertionsConfiguration.Builder compileTimeEnable;
        int iOrdinal = enumC3308l.ordinal();
        if (iOrdinal == 0) {
            compileTimeEnable = builder.setCompileTimeEnable();
        } else if (iOrdinal == 1) {
            compileTimeEnable = builder.setCompileTimeDisable();
        } else if (iOrdinal == 2) {
            compileTimeEnable = builder.setPassthrough();
        } else {
            if (iOrdinal != 3) {
                hkh.a();
                return null;
            }
            compileTimeEnable = builder.setAssertionHandler(methodReference);
        }
        return compileTimeEnable.setScopeClass(str).build();
    }

    public static int b(final BaseCompilerCommand.Builder builder, String str, String[] strArr, int i, Origin origin) {
        Objects.requireNonNull(builder);
        return a(new Consumer() { // from class: nkh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                builder.error((Diagnostic) obj);
            }
        }, builder.b(), str, strArr, i, origin);
    }

    public final AssertionsConfiguration a(EnumC3308l enumC3308l, MethodReference methodReference, AssertionsConfiguration.Builder builder) {
        AssertionsConfiguration.Builder compileTimeEnable;
        int iOrdinal = enumC3308l.ordinal();
        if (iOrdinal == 0) {
            compileTimeEnable = builder.setCompileTimeEnable();
        } else if (iOrdinal == 1) {
            compileTimeEnable = builder.setCompileTimeDisable();
        } else if (iOrdinal == 2) {
            compileTimeEnable = builder.setPassthrough();
        } else if (iOrdinal == 3) {
            compileTimeEnable = builder.setAssertionHandler(methodReference);
        } else {
            hkh.a();
            return null;
        }
        return compileTimeEnable.setScopeAll().build();
    }

    public final AssertionsConfiguration a(EnumC3308l enumC3308l, MethodReference methodReference, String str, AssertionsConfiguration.Builder builder) {
        AssertionsConfiguration.Builder compileTimeEnable;
        int iOrdinal = enumC3308l.ordinal();
        if (iOrdinal == 0) {
            compileTimeEnable = builder.setCompileTimeEnable();
        } else if (iOrdinal == 1) {
            compileTimeEnable = builder.setCompileTimeDisable();
        } else if (iOrdinal == 2) {
            compileTimeEnable = builder.setPassthrough();
        } else if (iOrdinal == 3) {
            compileTimeEnable = builder.setAssertionHandler(methodReference);
        } else {
            hkh.a();
            return null;
        }
        return compileTimeEnable.setScopePackage(str.substring(0, str.length() - 3)).build();
    }

    public final void a(BaseCompilerCommand.Builder builder, final EnumC3308l enumC3308l, final MethodReference methodReference, final String str) {
        if (str == null) {
            builder.addAssertionsConfiguration(new Function() { // from class: kkh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a(enumC3308l, methodReference, (AssertionsConfiguration.Builder) obj);
                }
            });
            return;
        }
        if (!a && str.length() <= 0) {
            x1f.a();
        } else if (str.endsWith("...")) {
            builder.addAssertionsConfiguration(new Function() { // from class: lkh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a(enumC3308l, methodReference, str, (AssertionsConfiguration.Builder) obj);
                }
            });
        } else {
            builder.addAssertionsConfiguration(new Function() { // from class: mkh
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.b(enumC3308l, methodReference, str, (AssertionsConfiguration.Builder) obj);
                }
            });
        }
    }

    public static void a(Consumer consumer, String str, String str2, Origin origin, Consumer consumer2) {
        try {
            int i = Integer.parseInt(str2);
            if (i < 1) {
                consumer.accept(new StringDiagnostic("Invalid argument to " + str + ": " + str2, origin));
                return;
            }
            consumer2.accept(Integer.valueOf(i));
        } catch (NumberFormatException unused) {
            consumer.accept(new StringDiagnostic("Invalid argument to " + str + ": " + str2, origin));
        }
    }

    public static DiagnosticsLevel a(Consumer consumer, String str, Origin origin) {
        if (str.equals("error")) {
            return DiagnosticsLevel.ERROR;
        }
        if (str.equals("warning")) {
            return DiagnosticsLevel.WARNING;
        }
        if (str.equals("info")) {
            return DiagnosticsLevel.INFO;
        }
        if (str.equals("none")) {
            return DiagnosticsLevel.NONE;
        }
        consumer.accept(new StringDiagnostic("Invalid diagnostics level '" + str + "'. Valid levels are 'error', 'warning', 'info' and 'none'.", origin));
        return null;
    }

    public static int a(Consumer consumer, C2742u50 c2742u50, String str, String[] strArr, int i, Origin origin) {
        String strSubstring;
        if (!str.startsWith("--map-diagnostics")) {
            return -1;
        }
        int i2 = i + 2;
        if (strArr.length <= i2) {
            consumer.accept(new StringDiagnostic("Missing argument(s) for " + str + ".", origin));
            return strArr.length - i;
        }
        String strSubstring2 = str.substring(17);
        if (strSubstring2.length() > 0) {
            if (strSubstring2.length() != 1 && strSubstring2.charAt(0) == ':') {
                strSubstring = strSubstring2.substring(1);
            } else {
                consumer.accept(new StringDiagnostic("Invalid diagnostics type specification " + str + ".", origin));
                return 0;
            }
        } else {
            strSubstring = XmlPullParser.NO_NAMESPACE;
        }
        DiagnosticsLevel diagnosticsLevelA = a(consumer, strArr[i + 1], origin);
        DiagnosticsLevel diagnosticsLevelA2 = a(consumer, strArr[i2], origin);
        if (diagnosticsLevelA == null || diagnosticsLevelA2 == null) {
            return 2;
        }
        c2742u50.a(diagnosticsLevelA, diagnosticsLevelA2, strSubstring);
        return 2;
    }

    public static int a(BaseCompilerCommand.Builder builder, String str, String[] strArr, int i, Origin origin) {
        if (!str.equals("--dumpinputtofile") && !str.equals("--dumpinputtodirectory")) {
            return -1;
        }
        int i2 = i + 1;
        if (strArr.length <= i2) {
            builder.error(new StringDiagnostic("Missing argument(s) for " + str + ".", origin));
            return strArr.length - i;
        }
        if (str.equals("--dumpinputtofile")) {
            builder.d(Paths.get(strArr[i2], new String[0]));
            return 1;
        }
        if (a || str.equals("--dumpinputtodirectory")) {
            builder.c(Paths.get(strArr[i2], new String[0]));
            return 1;
        }
        x1f.a();
        return 0;
    }

    public static void a(BaseCommand.Builder builder, Origin origin, String str) {
        Path path = Paths.get(str, new String[0]);
        if ((Files.exists(path.resolve("lib").resolve("jrt-fs.jar"), new LinkOption[0]) || Files.exists(path.resolve("jre").resolve("lib").resolve("rt.jar"), new LinkOption[0])) ? true : Files.exists(path.resolve("lib").resolve("rt.jar"), new LinkOption[0])) {
            try {
                builder.addLibraryResourceProvider(JdkClassFileProvider.fromJdkHome(path));
                return;
            } catch (IOException e) {
                builder.error(new ExceptionDiagnostic(e, origin));
                return;
            }
        }
        builder.addLibraryFiles(path);
    }
}

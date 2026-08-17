package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ufb {
    public static final ufb a = new ufb();
    public static final Set b = SetsKt.setOf(new String[]{"AndroidManifest.xml", "build.gradle"});
    public static final List c = CollectionsKt.toList(new IntRange(26, 36));
    public static final Regex d = new Regex("<string\\s+name=\"app_name\">(.*?)</string>", RegexOption.DOT_MATCHES_ALL);
    public static final ConcurrentHashMap e = new ConcurrentHashMap();
    public static final int f = 8;

    public static final class a {
        public final String a;
        public final String b;
        public final String c;

        public a(String str, String str2, String str3) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public final String a() {
            return this.c;
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c);
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "FileEdit(path=" + this.a + ", oldText=" + this.b + ", newText=" + this.c + ")";
        }
    }

    public enum b {
        BASIC,
        KOTLIN_BASIC,
        COMPOSE,
        SHOWCASE;

        public static final /* synthetic */ EnumEntries g = EnumEntriesKt.enumEntries(b());
    }

    public static final class c {
        public final String a;
        public final String b;
        public final String c;
        public final int d;

        public c(String str, String str2, String str3, int i) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = i;
        }

        public final String a() {
            return this.a;
        }

        public final int b() {
            return this.d;
        }

        public final String c() {
            return this.c;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return Intrinsics.areEqual(this.a, cVar.a) && Intrinsics.areEqual(this.b, cVar.b) && Intrinsics.areEqual(this.c, cVar.c) && this.d == cVar.d;
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Integer.hashCode(this.d);
        }

        public String toString() {
            return "ReleaseMeta(appName=" + this.a + ", packageName=" + this.b + ", versionName=" + this.c + ", versionCode=" + this.d + ")";
        }
    }

    public static final /* synthetic */ class f {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.values().length];
            try {
                iArr[b.BASIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[b.KOTLIN_BASIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[b.COMPOSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[b.SHOWCASE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            a = iArr;
        }
    }

    public static final class h {
        public final File a;
        public final String b;
        public final String c;
        public final vbe.c d;

        public h(File file, String str, String str2, vbe.c cVar) {
            file.getClass();
            str.getClass();
            str2.getClass();
            cVar.getClass();
            this.a = file;
            this.b = str;
            this.c = str2;
            this.d = cVar;
        }

        public final File a() {
            return this.a;
        }

        public final vbe.c b() {
            return this.d;
        }

        public final String c() {
            return this.b;
        }

        public final String d() {
            return this.c;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof h)) {
                return false;
            }
            h hVar = (h) obj;
            return Intrinsics.areEqual(this.a, hVar.a) && Intrinsics.areEqual(this.b, hVar.b) && Intrinsics.areEqual(this.c, hVar.c) && this.d == hVar.d;
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
        }

        public String toString() {
            return "Planned(file=" + this.a + ", path=" + this.b + ", updated=" + this.c + ", mode=" + this.d + ")";
        }
    }

    public static /* synthetic */ boolean M(ufb ufbVar, Context context, String str, String str2, String str3, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = "";
        }
        return ufbVar.L(context, str, str2, str3);
    }

    public static Object a(String str) {
        str.getClass();
        return new Object();
    }

    public static boolean b(String str, String str2, String str3) {
        str3.getClass();
        return Intrinsics.areEqual(str3, str) || StringsKt.startsWith$default(str3, str2, false, 2, (Object) null);
    }

    public static Comparable c(File file) {
        return file.getName();
    }

    public static Object d(Function1 function1, Object obj) {
        return function1.invoke(obj);
    }

    public static boolean e(File file, File file2) {
        file2.getClass();
        return !Intrinsics.areEqual(file2, file);
    }

    public static Comparable f(File file) {
        return Boolean.valueOf(!file.isDirectory());
    }

    public static Comparable g(File file) {
        return Boolean.valueOf(!file.isDirectory());
    }

    public static boolean h(String str, dfb dfbVar) {
        dfbVar.getClass();
        return Intrinsics.areEqual(dfbVar.e(), str);
    }

    public static Comparable i(File file) {
        return file.getName();
    }

    public static /* synthetic */ void x0(ufb ufbVar, Context context, File file, String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            z = false;
        }
        ufbVar.w0(context, file, str, str2, i, z);
    }

    public final void A(Context context, String str) {
        context.getClass();
        str.getClass();
        context.getSharedPreferences("yima_ide_projects", 0).edit().remove("workbench_session_" + str).apply();
    }

    public final boolean A0(Context context, String str, String str2, String str3) {
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        File fileQ0 = q0(context, str, str2);
        boolean z = false;
        if (fileQ0 == null) {
            return false;
        }
        File parentFile = fileQ0.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        synchronized (T(fileQ0)) {
            try {
                FilesKt.writeText$default(fileQ0, str3, (Charset) null, 2, (Object) null);
                long length = fileQ0.length();
                byte[] bytes = str3.getBytes(Charsets.UTF_8);
                bytes.getClass();
                if (length == bytes.length) {
                    z = true;
                }
            } catch (Exception unused) {
            }
        }
        return z;
    }

    public final String B(String str, int i) {
        return "plugins {\n    id 'com.android.application'\n}\n\nandroid {\n    namespace '" + str + "'\n    compileSdk 36\n\n    defaultConfig {\n        applicationId \"" + str + "\"\n        minSdk " + i + "\n        targetSdk 36\n        versionCode 1\n        versionName \"1.0\"\n    }\n}\n\ndependencies {\n    implementation 'com.google.android.material:material:1.12.0'\n    // Jetpack Compose（端上 kotlinc + Compose 编译器插件编译）。\n    implementation 'androidx.activity:activity-compose:1.9.3'\n    implementation 'androidx.compose.ui:ui:1.7.6'\n    implementation 'androidx.compose.ui:ui-tooling-preview:1.7.6'\n    implementation 'androidx.compose.foundation:foundation:1.7.6'\n    implementation 'androidx.compose.material3:material3:1.3.1'\n}\n";
    }

    public final String C(String str) {
        return "package " + str + "\n\nimport android.os.Bundle\nimport androidx.activity.ComponentActivity\nimport androidx.activity.compose.setContent\nimport androidx.activity.enableEdgeToEdge\nimport androidx.compose.foundation.layout.fillMaxSize\nimport androidx.compose.foundation.layout.padding\nimport androidx.compose.material3.Scaffold\nimport androidx.compose.material3.Text\nimport androidx.compose.runtime.Composable\nimport androidx.compose.ui.Modifier\nimport androidx.compose.ui.tooling.preview.Preview\nimport " + str + ".ui.theme.AppTheme\n\nclass MainActivity : ComponentActivity() {\n    override fun onCreate(savedInstanceState: Bundle?) {\n        super.onCreate(savedInstanceState)\n        enableEdgeToEdge()\n        setContent {\n            AppTheme {\n                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->\n                    Greeting(\n                        name = \"Android\",\n                        modifier = Modifier.padding(innerPadding)\n                    )\n                }\n            }\n        }\n    }\n}\n\n@Composable\nfun Greeting(name: String, modifier: Modifier = Modifier) {\n    Text(\n        text = \"Hello $name!\",\n        modifier = modifier\n    )\n}\n\n@Preview(showBackground = true)\n@Composable\nfun GreetingPreview() {\n    AppTheme {\n        Greeting(\"Android\")\n    }\n}\n";
    }

    public final String D(String str) {
        return "package " + str + ".ui.theme\n\nimport androidx.compose.ui.graphics.Color\n\nval Purple80 = Color(0xFFD0BCFF)\nval PurpleGrey80 = Color(0xFFCCC2DC)\nval Pink80 = Color(0xFFEFB8C8)\n\nval Purple40 = Color(0xFF6650a4)\nval PurpleGrey40 = Color(0xFF625b71)\nval Pink40 = Color(0xFF7D5260)\n";
    }

    public final String E(String str) {
        return "package " + str + ".ui.theme\n\nimport android.os.Build\nimport androidx.compose.foundation.isSystemInDarkTheme\nimport androidx.compose.material3.MaterialTheme\nimport androidx.compose.material3.darkColorScheme\nimport androidx.compose.material3.dynamicDarkColorScheme\nimport androidx.compose.material3.dynamicLightColorScheme\nimport androidx.compose.material3.lightColorScheme\nimport androidx.compose.runtime.Composable\nimport androidx.compose.ui.platform.LocalContext\n\nprivate val DarkColorScheme = darkColorScheme(\n    primary = Purple80,\n    secondary = PurpleGrey80,\n    tertiary = Pink80\n)\n\nprivate val LightColorScheme = lightColorScheme(\n    primary = Purple40,\n    secondary = PurpleGrey40,\n    tertiary = Pink40\n)\n\n@Composable\nfun AppTheme(\n    darkTheme: Boolean = isSystemInDarkTheme(),\n    // Android 12+ 动态取色（Material You）。\n    dynamicColor: Boolean = true,\n    content: @Composable () -> Unit\n) {\n    val colorScheme = when {\n        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {\n            val context = LocalContext.current\n            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)\n        }\n        darkTheme -> DarkColorScheme\n        else -> LightColorScheme\n    }\n\n    MaterialTheme(\n        colorScheme = colorScheme,\n        typography = Typography,\n        content = content\n    )\n}\n";
    }

    public final String F() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n    <style name=\"AppTheme\" parent=\"Theme.Material3.DayNight.NoActionBar\" />\n</resources>\n";
    }

    public final String G(String str) {
        return "package " + str + ".ui.theme\n\nimport androidx.compose.material3.Typography\nimport androidx.compose.ui.text.TextStyle\nimport androidx.compose.ui.text.font.FontFamily\nimport androidx.compose.ui.text.font.FontWeight\nimport androidx.compose.ui.unit.sp\n\nval Typography = Typography(\n    bodyLarge = TextStyle(\n        fontFamily = FontFamily.Default,\n        fontWeight = FontWeight.Normal,\n        fontSize = 16.sp,\n        lineHeight = 24.sp,\n        letterSpacing = 0.5.sp\n    )\n)\n";
    }

    public final String H(String str) {
        str.getClass();
        return "package " + str + ";\n\nimport android.app.Activity;\nimport android.os.Bundle;\nimport android.widget.ScrollView;\nimport android.widget.TextView;\n\npublic class CrashActivity extends Activity {\n    @Override\n    protected void onCreate(Bundle savedInstanceState) {\n        super.onCreate(savedInstanceState);\n        String trace = getIntent() != null ? getIntent().getStringExtra(\"trace\") : null;\n        if (trace == null) trace = \"(no stack trace)\";\n        TextView tv = new TextView(this);\n        tv.setText(trace);\n        tv.setTextColor(0xFFFFFFFF);\n        tv.setTextSize(11);\n        tv.setPadding(32, 64, 32, 32);\n        tv.setTextIsSelectable(true);\n        ScrollView sv = new ScrollView(this);\n        sv.setBackgroundColor(0xFFB00020);\n        sv.addView(tv);\n        setContentView(sv);\n    }\n}\n";
    }

    public final String I(String str) {
        str.getClass();
        return "package " + str + ";\n\nimport android.app.Activity;\nimport android.app.Application;\nimport android.content.Context;\nimport android.content.Intent;\nimport android.os.Bundle;\nimport android.widget.Toast;\nimport java.io.File;\nimport java.io.FileInputStream;\n\npublic class CrashApp extends Application {\n\n    // 运行批次号：IDE 编译时把当前 runId 烤进这一行。崩溃回流广播带上此值，\n    // IDE 只接收当前 runId 的崩溃，旧进程残留发的崩溃广播会被拒收，避免旧堆栈污染新控制台。\n    // 占位符 \"INIT\" 由 IDE 在编译前替换为真实 runId。\n    // 增量更新时壳类不重装：优先读 files/code_slot/run_id（由 CodeSlotProvider import 写入）。\n    private static String RUN_ID = \"INIT\";\n    private boolean debugHintShown;\n\n    @Override\n    protected void attachBaseContext(Context base) {\n        super.attachBaseContext(base);\n        // 调试容器代码槽：反射调用，正式包无 CodeSlotLoader 则忽略。\n        try {\n            Class<?> c = Class.forName(getClass().getPackage().getName() + \".CodeSlotLoader\");\n            c.getMethod(\"install\", Context.class).invoke(null, this);\n        } catch (Throwable ignored) {\n            try {\n                Class<?> c = Class.forName(getClass().getPackage().getName() + \".HotSwapLoader\");\n                c.getMethod(\"install\", Context.class).invoke(null, this);\n            } catch (Throwable ignored2) { }\n        }\n        try {\n            Class<?> d = Class.forName(getClass().getPackage().getName() + \".DiagLogger\");\n            d.getMethod(\"init\", Context.class).invoke(null, this);\n        } catch (Throwable ignored) { }\n    }\n\n    @Override\n    public void onCreate() {\n        super.onCreate();\n        try {\n            Class<?> d = Class.forName(getClass().getPackage().getName() + \".DiagLogger\");\n            d.getMethod(\"init\", Context.class).invoke(null, this);\n        } catch (Throwable ignored) { }\n        installCrashHandler();\n        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {\n            @Override public void onActivityCreated(Activity a, Bundle s) {}\n            @Override public void onActivityStarted(Activity a) {}\n            @Override public void onActivityResumed(Activity a) { showDebugHostHintOnce(a); }\n            @Override public void onActivityPaused(Activity a) {}\n            @Override public void onActivityStopped(Activity a) {}\n            @Override public void onActivitySaveInstanceState(Activity a, Bundle o) {}\n            @Override public void onActivityDestroyed(Activity a) {}\n        });\n    }\n\n    /** DEBUG_HOST_HINT：仅调试基座，正式打包已剥离 CrashApp。 */\n    private void showDebugHostHintOnce(Activity activity) {\n        if (debugHintShown || activity == null) return;\n        String n = activity.getClass().getName();\n        if (n.endsWith(\"CrashActivity\")) return;\n        debugHintShown = true;\n        Toast.makeText(\n            activity,\n            \"此为调试基座，无法脱离IDE独立运行，调试无问题后可点击正式打包进行分发。\",\n            Toast.LENGTH_LONG\n        ).show();\n    }\n\n    /** 增量 import 写入的 run_id 优先于烤进壳 dex 的静态值。 */\n    private static String effectiveRunId(Context ctx) {\n        try {\n            if (ctx != null) {\n                File f = new File(ctx.getFilesDir(), \"code_slot/run_id\");\n                if (f.isFile() && f.length() > 0L && f.length() < 4096L) {\n                    byte[] buf = new byte[(int) f.length()];\n                    FileInputStream in = new FileInputStream(f);\n                    try {\n                        int n = in.read(buf);\n                        if (n > 0) {\n                            String s = new String(buf, 0, n, \"UTF-8\").trim();\n                            if (s.length() > 0) return s;\n                        }\n                    } finally { in.close(); }\n                }\n            }\n        } catch (Throwable ignored) { }\n        return RUN_ID;\n    }\n\n    private void installCrashHandler() {\n        final Thread.UncaughtExceptionHandler prev = Thread.getDefaultUncaughtExceptionHandler();\n        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {\n            @Override\n            public void uncaughtException(Thread thread, Throwable ex) {\n                java.io.StringWriter sw = new java.io.StringWriter();\n                ex.printStackTrace(new java.io.PrintWriter(sw));\n                String trace = sw.toString();\n\n                // 把崩溃堆栈回流给 Yima IDE（显式广播，仅当 IDE 装在本机才生效；否则静默忽略）。\n                // 这样「运行→崩溃」能自动出现在 IDE 控制台并交给 AI 排查，无需手动抄。\n                try {\n                    Intent report = new Intent(\"com.yimaide.app.CRASH_REPORT\");\n                    report.setPackage(\"com.yimaide.app\");\n                    report.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);\n                    report.putExtra(\"trace\", trace);\n                    report.putExtra(\"pkg\", getPackageName());\n                    report.putExtra(\"run_id\", effectiveRunId(CrashApp.this));\n                    sendBroadcast(report);\n                } catch (Throwable ignored) { }\n\n                // 本机展示堆栈（独立 :crash 进程，主进程被回收也留得住）。\n                try {\n                    Intent i = new Intent(CrashApp.this, CrashActivity.class);\n                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);\n                    i.putExtra(\"trace\", trace);\n                    startActivity(i);\n                } catch (Throwable ignored) {\n                    if (prev != null) prev.uncaughtException(thread, ex);\n                    return;\n                }\n                // CrashActivity lives in the \":crash\" process, so killing this (main) process is safe.\n                android.os.Process.killProcess(android.os.Process.myPid());\n                System.exit(10);\n            }\n        });\n    }\n}\n";
    }

    public final byte[] J(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, c2c.a);
        if (drawable == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.getClass();
        try {
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                if (!bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)) {
                    CloseableKt.closeFinally(byteArrayOutputStream, (Throwable) null);
                    bitmapCreateBitmap.recycle();
                    return null;
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                CloseableKt.closeFinally(byteArrayOutputStream, (Throwable) null);
                bitmapCreateBitmap.recycle();
                return byteArray;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(byteArrayOutputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            bitmapCreateBitmap.recycle();
            throw th3;
        }
        bitmapCreateBitmap.recycle();
        throw th3;
    }

    public final boolean K(Context context, String str, String str2) {
        File fileQ0;
        context.getClass();
        str.getClass();
        str2.getClass();
        if (StringsKt.isBlank(str2) || (fileQ0 = q0(context, str, str2)) == null || fileQ0.exists()) {
            return false;
        }
        return fileQ0.mkdirs();
    }

    public final boolean L(Context context, String str, String str2, String str3) {
        File fileQ0;
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        boolean z = false;
        if (StringsKt.isBlank(str2) || (fileQ0 = q0(context, str, str2)) == null) {
            return false;
        }
        synchronized (T(fileQ0)) {
            if (!fileQ0.exists()) {
                File parentFile = fileQ0.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                try {
                    FilesKt.writeText$default(fileQ0, str3, (Charset) null, 2, (Object) null);
                    if (fileQ0.exists() && Intrinsics.areEqual(FilesKt.readText$default(fileQ0, (Charset) null, 1, (Object) null), str3)) {
                        z = true;
                    }
                } catch (Exception unused) {
                }
            }
        }
        return z;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x007c A[Catch: all -> 0x0071, TryCatch #0 {all -> 0x0071, blocks: (B:4:0x0048, B:6:0x005e, B:8:0x0064, B:18:0x0085, B:25:0x00ad, B:32:0x01f2, B:40:0x0225, B:56:0x037d, B:42:0x0237, B:43:0x023c, B:44:0x023d, B:46:0x0298, B:47:0x029b, B:59:0x0393, B:55:0x037a, B:27:0x00b7, B:28:0x00bc, B:29:0x00bd, B:30:0x0145, B:31:0x0198, B:17:0x007c), top: B:61:0x0048 }] */
    public final synchronized dfb N(Context context, String str, String str2, int i, String str3, b bVar) throws Throwable {
        List listListOf;
        Context context2;
        ufb ufbVar = this;
        String strA = str2;
        synchronized (this) {
            try {
                context.getClass();
                str.getClass();
                str3.getClass();
                bVar.getClass();
                String strF0 = ufbVar.f0();
                if (strA == null) {
                    strA = jwa.a.a(strF0, str);
                } else {
                    if (StringsKt.isBlank(strA) || !jwa.a.c(strA).a()) {
                        strA = null;
                    }
                    if (strA == null) {
                        strA = jwa.a.a(strF0, str);
                    }
                }
                String str4 = strA;
                String strReplace$default = StringsKt.replace$default(str4, '.', '/', false, 4, (Object) null);
                int[] iArr = f.a;
                int i2 = iArr[bVar.ordinal()];
                if (i2 == 1) {
                    listListOf = CollectionsKt.listOf(new String[]{"build.gradle", "AndroidManifest.xml", "src/" + strReplace$default + "/MainActivity.java", "src/" + strReplace$default + "/CrashApp.java", "src/" + strReplace$default + "/CrashActivity.java", "src/" + strReplace$default + "/DiagLogger.java"});
                } else if (i2 == 2) {
                    listListOf = CollectionsKt.listOf(new String[]{"build.gradle", "AndroidManifest.xml", "src/" + strReplace$default + "/MainActivity.kt", "src/" + strReplace$default + "/CrashApp.java", "src/" + strReplace$default + "/CrashActivity.java", "src/" + strReplace$default + "/DiagLogger.java"});
                } else if (i2 == 3) {
                    listListOf = CollectionsKt.listOf(new String[]{"build.gradle", "AndroidManifest.xml", "src/" + strReplace$default + "/MainActivity.kt", "src/" + strReplace$default + "/ui/theme/Color.kt", "src/" + strReplace$default + "/ui/theme/Theme.kt", "src/" + strReplace$default + "/ui/theme/Type.kt", "src/" + strReplace$default + "/CrashApp.java", "src/" + strReplace$default + "/CrashActivity.java", "src/" + strReplace$default + "/DiagLogger.java"});
                } else {
                    if (i2 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    listListOf = qad.a.z(strReplace$default);
                }
                dfb dfbVar = new dfb(strF0, str, StringsKt.trim(str3).toString(), str4, "android", listListOf);
                File fileV = ufbVar.V(context, strF0);
                fileV.mkdirs();
                int i3 = iArr[bVar.ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        try {
                            ufbVar.w0(context, fileV, str, str4, i, true);
                            ufbVar = this;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    } else if (i3 == 3) {
                        ufbVar.w0(context, fileV, str, str4, i, true);
                        FilesKt.writeText$default(new File(fileV, "build.gradle"), ufbVar.B(str4, i), (Charset) null, 2, (Object) null);
                        FilesKt.writeText$default(new File(fileV, "src/" + strReplace$default + "/MainActivity.kt"), ufbVar.C(str4), (Charset) null, 2, (Object) null);
                        File file = new File(fileV, "src/" + strReplace$default + "/ui/theme/Color.kt");
                        File parentFile = file.getParentFile();
                        if (parentFile != null) {
                            parentFile.mkdirs();
                        }
                        FilesKt.writeText$default(file, ufbVar.D(str4), (Charset) null, 2, (Object) null);
                        FilesKt.writeText$default(new File(fileV, "src/" + strReplace$default + "/ui/theme/Theme.kt"), ufbVar.E(str4), (Charset) null, 2, (Object) null);
                        FilesKt.writeText$default(new File(fileV, "src/" + strReplace$default + "/ui/theme/Type.kt"), ufbVar.G(str4), (Charset) null, 2, (Object) null);
                        new File(fileV, "res/layout/activity_main.xml").delete();
                        new File(fileV, "res/layout").delete();
                        new File(fileV, "res/values/styles.xml").delete();
                        FilesKt.writeText$default(new File(fileV, "res/values/themes.xml"), ufbVar.F(), (Charset) null, 2, (Object) null);
                        new File(fileV, "res/values/colors.xml").delete();
                        new File(fileV, "res/values-night/colors.xml").delete();
                        FilesKt.writeText$default(new File(fileV, "res/values-night/themes.xml"), ufbVar.F(), (Charset) null, 2, (Object) null);
                        new File(fileV, "res/mipmap/ic_launcher.png").delete();
                        new File(fileV, "res/mipmap/ic_launcher_round.png").delete();
                        new File(fileV, "res/mipmap").delete();
                    } else {
                        if (i3 != 4) {
                            throw new NoWhenBranchMatchedException();
                        }
                        qad.a.D(context, fileV, str, str4, i);
                    }
                    context2 = context;
                } else {
                    ufbVar = this;
                    context2 = context;
                    x0(ufbVar, context2, fileV, str, str4, i, false, 32, null);
                }
                ufbVar.S(context2, strF0);
                List mutableList = CollectionsKt.toMutableList(c0(context));
                mutableList.add(0, dfbVar);
                ufbVar.s0(context2, mutableList);
                return dfbVar;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final String O() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!--\n   按需声明云备份/设备迁移的包含/排除规则，例如：\n   <cloud-backup>\n       <include domain=\"sharedpref\" path=\".\"/>\n       <exclude domain=\"sharedpref\" path=\"device.xml\"/>\n   </cloud-backup>\n-->\n<data-extraction-rules>\n    <cloud-backup />\n    <device-transfer />\n</data-extraction-rules>\n";
    }

    public final boolean P(Context context, String str, String str2) throws IOException {
        context.getClass();
        str.getClass();
        str2.getClass();
        File fileQ0 = q0(context, str, str2);
        boolean zDeleteRecursively = false;
        if (fileQ0 == null) {
            return false;
        }
        File canonicalFile = V(context, str).getCanonicalFile();
        if (!Intrinsics.areEqual(fileQ0, canonicalFile)) {
            canonicalFile.getClass();
            if (!Y(canonicalFile, fileQ0)) {
                synchronized (T(fileQ0)) {
                    try {
                        if (fileQ0.exists()) {
                            zDeleteRecursively = fileQ0.isDirectory() ? FilesKt.deleteRecursively(fileQ0) : fileQ0.delete();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return zDeleteRecursively;
            }
        }
        return false;
    }

    public final synchronized boolean Q(Context context, final String str) {
        boolean zDeleteRecursively;
        boolean zRemoveAll;
        try {
            context.getClass();
            str.getClass();
            File fileV = V(context, str);
            zDeleteRecursively = FilesKt.deleteRecursively(fileV);
            ri6.a.d(context, str).delete();
            rbc.a.a(context, str);
            tr1.a.c(context, str);
            tcd.a.f(context, str);
            lh1.a.j(context, str);
            rh1.a.a(context, str);
            cc3.a.x(context, str);
            z(fileV);
            List mutableList = CollectionsKt.toMutableList(c0(context));
            zRemoveAll = CollectionsKt.removeAll(mutableList, new Function1() { // from class: qfb
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ufb.h(str, (dfb) obj));
                }
            });
            if (zRemoveAll) {
                s0(context, mutableList);
            }
            A(context, str);
            if (Intrinsics.areEqual(b0(context), str)) {
                r0(context, null);
            }
            if (zDeleteRecursively != zRemoveAll) {
                Log.w("ProjectStorage", "删除项目状态不一致: projectId=" + str + ", 目录删除=" + zDeleteRecursively + ", 索引移除=" + zRemoveAll);
            }
        } catch (Throwable th) {
            throw th;
        }
        return zDeleteRecursively && zRemoveAll;
    }

    public final String R(String str) {
        str.getClass();
        return "package " + str + ";\n\nimport android.content.Context;\nimport android.content.Intent;\nimport android.util.Log;\nimport java.io.File;\nimport java.io.FileInputStream;\n\n/**\n * 诊断日志回流到 Yima IDE 控制台。AI 排查疑难问题时在代码里埋点用：DiagLogger.d(\"xx\", \"val=\" + v)。\n * 调用后日志会同时进入系统 logcat 和 IDE 控制台，方便 AI 看到运行时真实情况。\n * 正式打包时 IDE 会把本类换成无广播空实现（残留埋点仍可编译，发布包无回流）。\n * 下次调试「运行」时会自动恢复完整实现。\n */\npublic final class DiagLogger {\n\n    private static final String ACTION = \"com.yimaide.app.DIAG_LOG\";\n    private static final String IDE_PKG = \"com.yimaide.app\";\n\n    // 运行批次号：IDE 编译时把当前 runId 烤进这一行。App 运行时发广播带上此值，\n    // IDE 只接收当前 runId 的回流，旧进程残留发的广播会被拒收，避免旧日志污染新控制台。\n    // 占位符 \"INIT\" 由 IDE 在编译前替换为真实 runId；若未替换则发 \"INIT\"，IDE 也会拒收。\n    // 增量更新时优先读 files/code_slot/run_id。\n    private static String RUN_ID = \"INIT\";\n\n    private static volatile Context APP_CTX;\n\n    /** 由 CrashApp.attachBaseContext/onCreate 尽早注入，避免 currentApplication 仍为 null。 */\n    public static void init(Context ctx) {\n        if (ctx != null) APP_CTX = ctx.getApplicationContext();\n    }\n\n    private static String effectiveRunId() {\n        try {\n            Context ctx = APP_CTX;\n            if (ctx == null) {\n                android.app.Application app = currentApp();\n                if (app != null) ctx = app;\n            }\n            if (ctx != null) {\n                File f = new File(ctx.getFilesDir(), \"code_slot/run_id\");\n                if (f.isFile() && f.length() > 0L && f.length() < 4096L) {\n                    byte[] buf = new byte[(int) f.length()];\n                    FileInputStream in = new FileInputStream(f);\n                    try {\n                        int n = in.read(buf);\n                        if (n > 0) {\n                            String s = new String(buf, 0, n, \"UTF-8\").trim();\n                            if (s.length() > 0) return s;\n                        }\n                    } finally { in.close(); }\n                }\n            }\n        } catch (Throwable ignored) { }\n        return RUN_ID;\n    }\n\n    // 限流：达到该行数或该时间间隔(ms)就 flush 一次，避免循环埋点刷爆广播。\n    private static final int FLUSH_LINES = 30;\n    private static final long FLUSH_INTERVAL_MS = 120L;\n\n    private static final Object LOCK = new Object();\n    private static final java.util.ArrayList<String[]> PENDING = new java.util.ArrayList<>();\n    private static long LAST_FLUSH = 0L;\n    private static boolean SCHEDULER_ON = false;\n\n    private DiagLogger() { }\n\n    public static void d(String tag, String msg) { log('D', tag, msg); }\n    public static void i(String tag, String msg) { log('I', tag, msg); }\n    public static void w(String tag, String msg) { log('W', tag, msg); }\n    public static void e(String tag, String msg) { log('E', tag, msg); }\n\n    private static void log(char level, String tag, String msg) {\n        try {\n            // 仍写一份到系统 logcat，adb 也能看到。\n            switch (level) {\n                case 'D': Log.d(tag, msg); break;\n                case 'I': Log.i(tag, msg); break;\n                case 'W': Log.w(tag, msg); break;\n                case 'E': Log.e(tag, msg); break;\n            }\n        } catch (Throwable ignored) { }\n        enqueue(level, tag == null ? \"\" : tag, msg == null ? \"\" : msg);\n    }\n\n    private static void enqueue(char level, String tag, String msg) {\n        synchronized (LOCK) {\n            PENDING.add(new String[]{String.valueOf(level), tag, msg});\n            boolean shouldFlush = PENDING.size() >= FLUSH_LINES\n                    || (System.currentTimeMillis() - LAST_FLUSH) >= FLUSH_INTERVAL_MS;\n            if (shouldFlush) {\n                flushLocked();\n            } else if (!SCHEDULER_ON) {\n                SCHEDULER_ON = true;\n                scheduleFlush();\n            }\n        }\n    }\n\n    private static void scheduleFlush() {\n        new Thread(new Runnable() {\n            @Override public void run() {\n                try { Thread.sleep(FLUSH_INTERVAL_MS); } catch (InterruptedException ignored) { }\n                synchronized (LOCK) {\n                    if (!PENDING.isEmpty()) flushLocked();\n                    SCHEDULER_ON = false;\n                }\n            }\n        }, \"DiagLogger-flush\").start();\n    }\n\n    private static void flushLocked() {\n        final java.util.ArrayList<String[]> snapshot = new java.util.ArrayList<>(PENDING);\n        PENDING.clear();\n        LAST_FLUSH = System.currentTimeMillis();\n        if (snapshot.isEmpty()) return;\n        new Thread(new Runnable() {\n            @Override public void run() {\n                try {\n                    StringBuilder sb = new StringBuilder();\n                    for (String[] it : snapshot) {\n                        sb.append(it[0]).append('/').append(it[1]).append(\": \").append(it[2]).append('\\n');\n                    }\n                    Intent report = new Intent(ACTION);\n                    report.setPackage(IDE_PKG);\n                    report.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);\n                    report.putExtra(\"lines\", sb.toString());\n                    report.putExtra(\"run_id\", effectiveRunId());\n                    Context ctx = APP_CTX;\n                    if (ctx == null) {\n                        android.app.Application app = currentApp();\n                        if (app != null) ctx = app;\n                    }\n                    if (ctx != null) {\n                        report.putExtra(\"pkg\", ctx.getPackageName());\n                        ctx.sendBroadcast(report);\n                    }\n                } catch (Throwable ignored) { }\n            }\n        }, \"DiagLogger-send\").start();\n    }\n\n    private static android.app.Application currentApp() {\n        try {\n            Class<?> at = Class.forName(\"android.app.ActivityThread\");\n            Object current = at.getMethod(\"currentApplication\").invoke(null);\n            return (android.app.Application) current;\n        } catch (Throwable ignored) {\n            return null;\n        }\n    }\n}\n";
    }

    public final void S(Context context, String str) {
        context.getClass();
        str.getClass();
        File fileD = ri6.a.d(context, str);
        if (fileD.exists()) {
            return;
        }
        z0(context, fileD);
    }

    public final Object T(File file) {
        String absolutePath;
        try {
            absolutePath = file.getCanonicalPath();
        } catch (Exception unused) {
            absolutePath = file.getAbsolutePath();
        }
        ConcurrentHashMap concurrentHashMap = e;
        final Function1 function1 = new Function1() { // from class: rfb
            public final Object invoke(Object obj) {
                return ufb.a((String) obj);
            }
        };
        Object objComputeIfAbsent = concurrentHashMap.computeIfAbsent(absolutePath, new Function() { // from class: sfb
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ufb.d(function1, obj);
            }
        });
        objComputeIfAbsent.getClass();
        return objComputeIfAbsent;
    }

    public final Set U() {
        return b;
    }

    public final File V(Context context, String str) {
        context.getClass();
        str.getClass();
        return new File(context.getFilesDir(), "projects/" + str);
    }

    public final List W() {
        return c;
    }

    public final boolean X(String str, String str2) {
        str.getClass();
        str2.getClass();
        return b.contains(str);
    }

    public final boolean Y(File file, File file2) {
        Object obj;
        Set<String> set = b;
        if ((set instanceof Collection) && set.isEmpty()) {
            return false;
        }
        for (String str : set) {
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.constructor-impl(Boolean.valueOf(Intrinsics.areEqual(new File(file, str).getCanonicalFile(), file2)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Boolean bool = Boolean.FALSE;
            if (Result.isFailure-impl(obj)) {
                obj = bool;
            }
            if (((Boolean) obj).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final List Z(Context context, String str) {
        context.getClass();
        str.getClass();
        final File fileV = V(context, str);
        if (!fileV.isDirectory()) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (File file : SequencesKt.filter(FilesKt.walkTopDown(fileV), new Function1() { // from class: lfb
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ufb.e(fileV, (File) obj));
            }
        })) {
            String path = FilesKt.relativeTo(file, fileV).getPath();
            path.getClass();
            String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
            if (file.isDirectory()) {
                arrayList.add(strReplace$default + PsuedoNames.PSEUDONAME_ROOT);
            } else {
                arrayList2.add(strReplace$default);
            }
        }
        return CollectionsKt.plus(CollectionsKt.sorted(arrayList), CollectionsKt.sorted(arrayList2));
    }

    public final kq4 a0(Context context, String str) {
        File[] fileArrListFiles;
        context.getClass();
        str.getClass();
        File fileV = V(context, str);
        kq4 kq4Var = new kq4(str, "", true, null, 8, null);
        if (fileV.isDirectory() && (fileArrListFiles = fileV.listFiles()) != null) {
            for (File file : ArraysKt.sortedWith(fileArrListFiles, ComparisonsKt.compareBy(new Function1[]{new Function1() { // from class: mfb
                public final Object invoke(Object obj) {
                    return ufb.g((File) obj);
                }
            }, new Function1() { // from class: nfb
                public final Object invoke(Object obj) {
                    return ufb.c((File) obj);
                }
            }}))) {
                String name = file.getName();
                if (file.isDirectory()) {
                    List listE = kq4Var.e();
                    name.getClass();
                    listE.add(u0(file, name));
                } else {
                    List listE2 = kq4Var.e();
                    String name2 = file.getName();
                    name2.getClass();
                    name.getClass();
                    listE2.add(new kq4(name2, name, false, null, 8, null));
                }
            }
        }
        return kq4Var;
    }

    public final String b0(Context context) {
        context.getClass();
        return context.getSharedPreferences("yima_ide_projects", 0).getString("last_opened_project_id", null);
    }

    public final synchronized List c0(Context context) {
        context.getClass();
        String string = context.getSharedPreferences("yima_ide_projects", 0).getString("projects_json", null);
        if (string == null) {
            return CollectionsKt.emptyList();
        }
        return g0(string);
    }

    public final g d0(Context context, String str) {
        List listEmptyList;
        List listEmptyList2;
        context.getClass();
        str.getClass();
        String string = context.getSharedPreferences("yima_ide_projects", 0).getString("workbench_session_" + str, null);
        if (string == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("openTabs");
            if (jSONArrayOptJSONArray != null) {
                IntRange intRangeUntil = RangesKt.until(0, jSONArrayOptJSONArray.length());
                listEmptyList = new ArrayList();
                IntIterator it = intRangeUntil.iterator();
                while (it.hasNext()) {
                    String strOptString = jSONArrayOptJSONArray.optString(it.nextInt());
                    if (StringsKt.isBlank(strOptString)) {
                        strOptString = null;
                    }
                    if (strOptString != null) {
                        listEmptyList.add(strOptString);
                    }
                }
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            List list = listEmptyList;
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("expandedDirectories");
            if (jSONArrayOptJSONArray2 != null) {
                IntRange intRangeUntil2 = RangesKt.until(0, jSONArrayOptJSONArray2.length());
                listEmptyList2 = new ArrayList();
                IntIterator it2 = intRangeUntil2.iterator();
                while (it2.hasNext()) {
                    String strOptString2 = jSONArrayOptJSONArray2.optString(it2.nextInt());
                    if (StringsKt.isBlank(strOptString2)) {
                        strOptString2 = null;
                    }
                    if (strOptString2 != null) {
                        listEmptyList2.add(strOptString2);
                    }
                }
            } else {
                listEmptyList2 = CollectionsKt.emptyList();
            }
            List list2 = listEmptyList2;
            String strOptString3 = jSONObject.optString("currentFilePath", "");
            strOptString3.getClass();
            return new g(strOptString3, list, list2, jSONObject.optBoolean("isEditorFullscreen", false), jSONObject.optBoolean("isFileTreeExpanded", true), jSONObject.optBoolean("isEditorExpanded", true));
        } catch (Exception unused) {
            return null;
        }
    }

    public final boolean e0(Context context, String str, String str2, String str3) throws IOException {
        File fileQ0;
        File fileQ1;
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        boolean zRenameTo = false;
        if (StringsKt.isBlank(str2) || StringsKt.isBlank(str3) || (fileQ0 = q0(context, str, str2)) == null || (fileQ1 = q0(context, str, str3)) == null) {
            return false;
        }
        File canonicalFile = V(context, str).getCanonicalFile();
        if (!Intrinsics.areEqual(fileQ0, canonicalFile)) {
            canonicalFile.getClass();
            if (Y(canonicalFile, fileQ0) || Y(canonicalFile, fileQ1)) {
                return false;
            }
            synchronized (T(fileQ0)) {
                try {
                    if (fileQ0.exists() && !fileQ1.exists()) {
                        File parentFile = fileQ1.getParentFile();
                        if (parentFile != null) {
                            parentFile.mkdirs();
                        }
                        zRenameTo = fileQ0.renameTo(fileQ1);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return zRenameTo;
        }
        return false;
    }

    public final String f0() {
        String string = UUID.randomUUID().toString();
        string.getClass();
        return StringsKt.take(StringsKt.replace$default(string, "-", "", false, 4, (Object) null), 12);
    }

    public final List g0(String str) {
        List listListOf;
        JSONArray jSONArray = new JSONArray(str);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString("id");
                strOptString.getClass();
                String string = StringsKt.trim(strOptString).toString();
                String strOptString2 = jSONObjectOptJSONObject.optString("name");
                strOptString2.getClass();
                String string2 = StringsKt.trim(strOptString2).toString();
                if (string.length() > 0 && string2.length() > 0) {
                    String strOptString3 = jSONObjectOptJSONObject.optString("nickname", "");
                    String strOptString4 = jSONObjectOptJSONObject.optString("packageName", "");
                    String strOptString5 = jSONObjectOptJSONObject.optString("type", "android");
                    JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("files");
                    if (jSONArrayOptJSONArray != null) {
                        IntRange intRangeUntil = RangesKt.until(0, jSONArrayOptJSONArray.length());
                        listListOf = new ArrayList();
                        IntIterator it = intRangeUntil.iterator();
                        while (it.hasNext()) {
                            String strOptString6 = jSONArrayOptJSONArray.optString(it.nextInt());
                            if (strOptString6 != null) {
                                listListOf.add(strOptString6);
                            }
                        }
                    } else {
                        listListOf = CollectionsKt.listOf("AndroidManifest.xml");
                    }
                    List list = listListOf;
                    strOptString3.getClass();
                    strOptString4.getClass();
                    strOptString5.getClass();
                    listCreateListBuilder.add(new dfb(string, string2, strOptString3, strOptString4, strOptString5, list));
                }
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public final String h0(File file) {
        MatchResult matchResultFind$default;
        List groupValues;
        String str;
        File file2 = new File(file, "res/values/strings.xml");
        if (!file2.isFile()) {
            file2 = null;
        }
        if (file2 == null || (matchResultFind$default = Regex.find$default(d, FilesKt.readText$default(file2, (Charset) null, 1, (Object) null), 0, 2, (Object) null)) == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = (String) groupValues.get(1)) == null) {
            return null;
        }
        return StringsKt.trim(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, SerializerConstants.ENTITY_AMP, "&", false, 4, (Object) null), SerializerConstants.ENTITY_LT, "<", false, 4, (Object) null), SerializerConstants.ENTITY_QUOT, "\"", false, 4, (Object) null)).toString();
    }

    public final String i0(Context context, dfb dfbVar) {
        context.getClass();
        dfbVar.getClass();
        String strH0 = h0(V(context, dfbVar.e()));
        return strH0 == null ? dfbVar.f() : strH0;
    }

    public final String j() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<vector xmlns:android=\"http://schemas.android.com/apk/res/android\"\n    android:width=\"108dp\"\n    android:height=\"108dp\"\n    android:viewportWidth=\"108\"\n    android:viewportHeight=\"108\">\n    <path android:fillColor=\"#3DDC84\" android:pathData=\"M0,0h108v108h-108z\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M9,0L9,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M19,0L19,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M29,0L29,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M39,0L39,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M49,0L49,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M59,0L59,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M69,0L69,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M79,0L79,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M89,0L89,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M99,0L99,108\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,9L108,9\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,19L108,19\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,29L108,29\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,39L108,39\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,49L108,49\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,59L108,59\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,69L108,69\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,79L108,79\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,89L108,89\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M0,99L108,99\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M19,29L89,29\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M19,39L89,39\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M19,49L89,49\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M19,59L89,59\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M19,69L89,69\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M19,79L89,79\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M29,19L29,89\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M39,19L39,89\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M49,19L49,89\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M59,19L59,89\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M69,19L69,89\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n    <path android:fillColor=\"#00000000\" android:pathData=\"M79,19L79,89\" android:strokeWidth=\"0.8\" android:strokeColor=\"#33FFFFFF\" />\n</vector>\n";
    }

    public final String j0(Context context, String str, String str2) {
        context.getClass();
        str.getClass();
        str2.getClass();
        File fileQ0 = q0(context, str, str2);
        String text$default = null;
        if (fileQ0 == null) {
            return null;
        }
        synchronized (T(fileQ0)) {
            try {
                if (fileQ0.exists() && fileQ0.isFile()) {
                    if (fileQ0.length() > 5242880) {
                        Log.w("ProjectStorage", "readFile 拒绝超大文件（" + fileQ0.length() + " 字节 > 5242880）: " + str2);
                    } else {
                        text$default = FilesKt.readText$default(fileQ0, (Charset) null, 1, (Object) null);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return text$default;
    }

    public final String k() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<vector xmlns:android=\"http://schemas.android.com/apk/res/android\"\n    android:width=\"108dp\"\n    android:height=\"108dp\"\n    android:viewportWidth=\"108\"\n    android:viewportHeight=\"108\">\n    <path\n        android:fillColor=\"#FFFFFF\"\n        android:pathData=\"M65.3,45.828l3.8,-6.6c0.2,-0.4 0.1,-0.9 -0.3,-1.1c-0.4,-0.2 -0.9,-0.1 -1.1,0.3l-3.9,6.7c-6.3,-2.8 -13.4,-2.8 -19.7,0l-3.9,-6.7c-0.2,-0.4 -0.7,-0.5 -1.1,-0.3C38.8,38.328 38.7,38.828 38.9,39.228l3.8,6.6C36.2,49.428 31.7,56.028 31,63.928h46C76.3,56.028 71.8,49.428 65.3,45.828zM43.4,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2c-0.3,-0.7 -0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C45.3,56.528 44.5,57.328 43.4,57.328L43.4,57.328zM64.6,57.328c-0.8,0 -1.5,-0.5 -1.8,-1.2s-0.1,-1.5 0.4,-2.1c0.5,-0.5 1.4,-0.7 2.1,-0.4c0.7,0.3 1.2,1 1.2,1.8C66.5,56.528 65.6,57.328 64.6,57.328L64.6,57.328z\"\n        android:strokeColor=\"#00000000\" />\n</vector>\n";
    }

    public final c k0(Context context, dfb dfbVar) {
        context.getClass();
        dfbVar.getClass();
        return rbc.a.i(context, dfbVar);
    }

    public final String l() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<adaptive-icon xmlns:android=\"http://schemas.android.com/apk/res/android\">\n    <background android:drawable=\"@drawable/ic_launcher_background\" />\n    <foreground android:drawable=\"@drawable/ic_launcher_foreground\" />\n    <monochrome android:drawable=\"@drawable/ic_launcher_foreground\" />\n</adaptive-icon>\n";
    }

    public final boolean l0(Context context, String str, String str2, String str3) throws IOException {
        File fileQ0;
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        boolean zRenameTo = false;
        if (StringsKt.isBlank(str3) || StringsKt.contains$default(str3, '/', false, 2, (Object) null) || StringsKt.contains$default(str3, '\\', false, 2, (Object) null) || Intrinsics.areEqual(str3, Constants.ATTRVAL_THIS) || Intrinsics.areEqual(str3, Constants.ATTRVAL_PARENT) || (fileQ0 = q0(context, str, str2)) == null) {
            return false;
        }
        File canonicalFile = V(context, str).getCanonicalFile();
        if (!Intrinsics.areEqual(fileQ0, canonicalFile)) {
            canonicalFile.getClass();
            if (!Y(canonicalFile, fileQ0)) {
                synchronized (T(fileQ0)) {
                    if (fileQ0.exists()) {
                        File file = new File(fileQ0.getParentFile(), str3);
                        if (!file.exists()) {
                            zRenameTo = fileQ0.renameTo(file);
                        }
                    }
                }
                return zRenameTo;
            }
        }
        return false;
    }

    public final synchronized dfb m(Context context, dfb dfbVar) {
        context.getClass();
        dfbVar.getClass();
        List mutableList = CollectionsKt.toMutableList(c0(context));
        mutableList.add(0, dfbVar);
        s0(context, mutableList);
        S(context, dfbVar.e());
        return dfbVar;
    }

    public final synchronized d m0(Context context, dfb dfbVar, String str) {
        context.getClass();
        dfbVar.getClass();
        str.getClass();
        String string = StringsKt.trim(str).toString();
        if (Intrinsics.areEqual(string, dfbVar.h())) {
            return new d.b(dfbVar);
        }
        File fileV = V(context, dfbVar.e());
        hwa.a aVarK = hwa.a.k(fileV, dfbVar.h(), string);
        if (Intrinsics.areEqual(aVarK, hwa.a.c.a)) {
            return new d.b(dfbVar);
        }
        if (aVarK instanceof hwa.a.C0008a) {
            return new d.a(((hwa.a.C0008a) aVarK).a());
        }
        if (!(aVarK instanceof hwa.a.b)) {
            throw new NoWhenBranchMatchedException();
        }
        String strReplace$default = StringsKt.replace$default(dfbVar.h(), '.', '/', false, 4, (Object) null);
        String strReplace$default2 = StringsKt.replace$default(((hwa.a.b) aVarK).a(), '.', '/', false, 4, (Object) null);
        List<String> listD = dfbVar.d();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listD, 10));
        for (String str2 : listD) {
            if (StringsKt.startsWith$default(str2, "src/" + strReplace$default + PsuedoNames.PSEUDONAME_ROOT, false, 2, (Object) null)) {
                str2 = "src/" + strReplace$default2 + PsuedoNames.PSEUDONAME_ROOT + StringsKt.removePrefix(str2, "src/" + strReplace$default + PsuedoNames.PSEUDONAME_ROOT);
            } else {
                if (Intrinsics.areEqual(str2, "src/" + strReplace$default)) {
                    str2 = "src/" + strReplace$default2;
                }
            }
            arrayList.add(str2);
        }
        dfb dfbVarB = dfb.b(dfbVar, null, null, null, ((hwa.a.b) aVarK).a(), null, arrayList, 23, null);
        List<dfb> listC0 = c0(context);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listC0, 10));
        for (dfb dfbVar2 : listC0) {
            if (Intrinsics.areEqual(dfbVar2.e(), dfbVar.e())) {
                dfbVar2 = dfbVarB;
            }
            arrayList2.add(dfbVar2);
        }
        s0(context, arrayList2);
        FilesKt.deleteRecursively(new File(fileV, "build"));
        cc3.a.x(context, dfbVar.e());
        return new d.b(dfbVarB);
    }

    public final String n() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n    <color name=\"primary\">#60A5FA</color>\n    <color name=\"text\">#F3F4F6</color>\n    <color name=\"bg\">#111827</color>\n</resources>\n";
    }

    public final synchronized dfb n0(Context context, dfb dfbVar, String str) {
        context.getClass();
        dfbVar.getClass();
        str.getClass();
        String string = StringsKt.trim(str).toString();
        if (string.length() == 0) {
            return null;
        }
        if (Intrinsics.areEqual(string, dfbVar.f()) && StringsKt.isBlank(dfbVar.g())) {
            return dfbVar;
        }
        y0(V(context, dfbVar.e()), string);
        dfb dfbVarB = dfb.b(dfbVar, null, string, "", null, null, null, 57, null);
        List<dfb> listC0 = c0(context);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listC0, 10));
        for (dfb dfbVar2 : listC0) {
            if (Intrinsics.areEqual(dfbVar2.e(), dfbVar.e())) {
                dfbVar2 = dfbVarB;
            }
            arrayList.add(dfbVar2);
        }
        s0(context, arrayList);
        return dfbVarB;
    }

    public final String o() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n    <color name=\"primary\">#3B82F6</color>\n    <color name=\"text\">#111827</color>\n    <color name=\"bg\">#ECEFF4</color>\n</resources>\n";
    }

    public final e o0(Context context, String str, String str2, String str3, String str4) {
        e dVar;
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        File fileQ0 = q0(context, str, str2);
        if (fileQ0 == null) {
            return e.b.a;
        }
        synchronized (T(fileQ0)) {
            try {
                if (fileQ0.exists() && fileQ0.isFile()) {
                    vbe.a aVarA = vbe.a.a(FilesKt.readText$default(fileQ0, (Charset) null, 1, (Object) null), str3, str4);
                    if (aVarA instanceof vbe.a.e) {
                        try {
                            FilesKt.writeText$default(fileQ0, ((vbe.a.e) aVarA).b(), (Charset) null, 2, (Object) null);
                            dVar = Intrinsics.areEqual(FilesKt.readText$default(fileQ0, (Charset) null, 1, (Object) null), ((vbe.a.e) aVarA).b()) ? new e.d(((vbe.a.e) aVarA).a()) : e.C0013e.a;
                        } catch (Exception unused) {
                            dVar = e.f.a;
                        }
                    } else if (Intrinsics.areEqual(aVarA, vbe.a.d.a) || Intrinsics.areEqual(aVarA, vbe.a.b.a) || Intrinsics.areEqual(aVarA, vbe.a.c.a)) {
                        dVar = e.c.a;
                    } else {
                        if (!(aVarA instanceof vbe.a.C0014a)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        dVar = new e.a(((vbe.a.C0014a) aVarA).a(), ((vbe.a.C0014a) aVarA).b());
                    }
                } else {
                    dVar = e.b.a;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return dVar;
    }

    public final String p() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<FrameLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n    android:layout_width=\"match_parent\"\n    android:layout_height=\"match_parent\"\n    android:background=\"@color/bg\">\n\n    <TextView\n        android:id=\"@+id/hello\"\n        android:layout_width=\"wrap_content\"\n        android:layout_height=\"wrap_content\"\n        android:layout_gravity=\"center\"\n        android:text=\"@string/hello\"\n        android:textColor=\"@color/text\"\n        android:textSize=\"28sp\"\n        android:textStyle=\"bold\" />\n</FrameLayout>\n";
    }

    public final String p0(Context context, String str, List list) throws IOException {
        int i;
        int i2;
        boolean zAreEqual;
        File fileA;
        context.getClass();
        str.getClass();
        list.getClass();
        if (list.isEmpty()) {
            return "失败：edits 为空";
        }
        if (list.size() > 20) {
            return "失败：单次最多 20 处修改";
        }
        ArrayList arrayList = new ArrayList(list.size());
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        Iterator it = list.iterator();
        char c2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            i3++;
            a aVar = (a) it.next();
            String string = StringsKt.trim(aVar.c()).toString();
            char[] cArr = new char[1];
            cArr[c2] = '/';
            String strReplace$default = StringsKt.replace$default(StringsKt.trim(string, cArr), '\\', '/', false, 4, (Object) null);
            if (strReplace$default.length() == 0) {
                return "失败：第 " + i3 + " 条缺少 path";
            }
            if (aVar.b().length() == 0) {
                return "失败：第 " + i3 + " 条（" + strReplace$default + "）old_text 为空";
            }
            File fileQ0 = q0(context, str, strReplace$default);
            if (fileQ0 == null) {
                return "失败：第 " + i3 + " 条文件不存在 " + strReplace$default;
            }
            if (!fileQ0.isFile()) {
                return "失败：第 " + i3 + " 条不是文件 " + strReplace$default;
            }
            String canonicalPath = fileQ0.getCanonicalPath();
            String str2 = (String) map2.get(canonicalPath);
            if (str2 == null) {
                synchronized (T(fileQ0)) {
                    if (fileQ0.exists() && fileQ0.isFile()) {
                        String text$default = FilesKt.readText$default(fileQ0, (Charset) null, 1, (Object) null);
                        map.put(canonicalPath, text$default);
                        map2.put(canonicalPath, text$default);
                        str2 = text$default;
                    }
                    return "失败：第 " + i3 + " 条文件不存在 " + strReplace$default;
                }
            }
            vbe.a aVarA = vbe.a.a(str2, aVar.b(), aVar.a());
            if (!(aVarA instanceof vbe.a.e)) {
                if (Intrinsics.areEqual(aVarA, vbe.a.d.a) || Intrinsics.areEqual(aVarA, vbe.a.b.a) || Intrinsics.areEqual(aVarA, vbe.a.c.a)) {
                    return "失败：第 " + i3 + " 条（" + strReplace$default + "）未找到唯一 old_text（已尝试精确与空白灵活匹配，请先 read_file）";
                }
                if (!(aVarA instanceof vbe.a.C0014a)) {
                    bu8.a();
                    return null;
                }
                return "失败：第 " + i3 + " 条（" + strReplace$default + "）" + ((vbe.a.C0014a) aVarA).b();
            }
            vbe.a.e eVar = (vbe.a.e) aVarA;
            map2.put(canonicalPath, eVar.b());
            arrayList.add(new h(fileQ0, strReplace$default, eVar.b(), eVar.a()));
            c2 = 0;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it2 = arrayList.iterator();
        it2.getClass();
        while (it2.hasNext()) {
            Object next = it2.next();
            next.getClass();
            h hVar = (h) next;
            String canonicalPath2 = hVar.a().getCanonicalPath();
            File fileA2 = hVar.a();
            String strC = hVar.c();
            Object obj = map2.get(canonicalPath2);
            obj.getClass();
            linkedHashMap.put(canonicalPath2, new h(fileA2, strC, (String) obj, hVar.b()));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            arrayList2.add(((h) it3.next()).b());
        }
        for (Object obj2 : linkedHashMap.values()) {
            obj2.getClass();
            h hVar2 = (h) obj2;
            synchronized (T(hVar2.a())) {
                try {
                    FilesKt.writeText$default(hVar2.a(), hVar2.d(), (Charset) null, 2, (Object) null);
                    zAreEqual = Intrinsics.areEqual(FilesKt.readText$default(hVar2.a(), (Charset) null, 1, (Object) null), hVar2.d());
                } catch (Exception unused) {
                    zAreEqual = false;
                }
            }
            if (!zAreEqual) {
                for (Map.Entry entry : map.entrySet()) {
                    String str3 = (String) entry.getKey();
                    String str4 = (String) entry.getValue();
                    h hVar3 = (h) linkedHashMap.get(str3);
                    if (hVar3 != null && (fileA = hVar3.a()) != null) {
                        try {
                            Result.Companion companion = Result.Companion;
                            synchronized (T(fileA)) {
                                try {
                                    FilesKt.writeText$default(fileA, str4, (Charset) null, 2, (Object) null);
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            Result.constructor-impl(Unit.INSTANCE);
                        } catch (Throwable th2) {
                            Result.Companion companion2 = Result.Companion;
                            Result.constructor-impl(ResultKt.createFailure(th2));
                        }
                    }
                }
                return "失败：写入校验未通过 " + hVar2.c();
            }
        }
        if (arrayList2.isEmpty()) {
            i = 0;
        } else {
            Iterator it4 = arrayList2.iterator();
            i = 0;
            while (it4.hasNext()) {
                if (((vbe.c) it4.next()) == vbe.c.Exact && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (arrayList2.isEmpty()) {
            i2 = 0;
        } else {
            Iterator it5 = arrayList2.iterator();
            int i4 = 0;
            while (it5.hasNext()) {
                if (((vbe.c) it5.next()) == vbe.c.WhitespaceFlexible && (i4 = i4 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
            i2 = i4;
        }
        int size = linkedHashMap.size();
        StringBuilder sb = new StringBuilder();
        sb.append("已修改 " + list.size() + " 处（" + size + " 个文件");
        if (i2 > 0) {
            sb.append("；精确 " + i + " / 空白灵活 " + i2);
        }
        sb.append("）");
        Collection collectionValues = linkedHashMap.values();
        collectionValues.getClass();
        Collection collection = collectionValues;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator it6 = collection.iterator();
        while (it6.hasNext()) {
            arrayList3.add(((h) it6.next()).c());
        }
        List listDistinct = CollectionsKt.distinct(arrayList3);
        if (listDistinct.size() <= 6) {
            sb.append("：");
            sb.append(CollectionsKt.joinToString$default(listDistinct, "、", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        }
        return sb.toString();
    }

    public final String q(String str) {
        return "package " + str + "\n\nimport android.os.Bundle\nimport androidx.appcompat.app.AppCompatActivity\n\n// 端上 Kotlin 编译：MainActivity 用 Kotlin 编写，由 kotlinc 移植版在手机上编译为字节码。\nclass MainActivity : AppCompatActivity() {\n    override fun onCreate(savedInstanceState: Bundle?) {\n        super.onCreate(savedInstanceState)\n        setContentView(R.layout.activity_main)\n    }\n}\n";
    }

    public final File q0(Context context, String str, String str2) {
        context.getClass();
        str.getClass();
        str2.getClass();
        if (StringsKt.isBlank(str2)) {
            return null;
        }
        File fileV = V(context, str);
        try {
            File canonicalFile = fileV.getCanonicalFile();
            File canonicalFile2 = new File(fileV, str2).getCanonicalFile();
            String path = canonicalFile.getPath();
            if (!Intrinsics.areEqual(canonicalFile2.getPath(), path)) {
                String path2 = canonicalFile2.getPath();
                path2.getClass();
                if (!StringsKt.startsWith$default(path2, path + File.separator, false, 2, (Object) null)) {
                    return null;
                }
            }
            return canonicalFile2;
        } catch (Exception unused) {
            return null;
        }
    }

    public final String r(String str) {
        return "package " + str + ";\n\nimport android.os.Bundle;\n\nimport androidx.appcompat.app.AppCompatActivity;\n\n// 端上编译的最小 Hello World：只加载布局，\"Hello World!\" 文字在布局中居中显示。\npublic class MainActivity extends AppCompatActivity {\n\n    @Override\n    protected void onCreate(Bundle savedInstanceState) {\n        super.onCreate(savedInstanceState);\n        setContentView(R.layout.activity_main);\n    }\n}\n";
    }

    public final void r0(Context context, String str) {
        context.getClass();
        context.getSharedPreferences("yima_ide_projects", 0).edit().putString("last_opened_project_id", str).apply();
    }

    public final String s(String str) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n    package=\"" + str + "\">\n\n    <!-- 需要权限时在此声明，例如：\n         <uses-permission android:name=\"android.permission.INTERNET\" />\n         危险权限（相机/定位/麦克风等）还需在代码里运行时申请。 -->\n\n    <application\n        android:name=\".CrashApp\"\n        android:label=\"@string/app_name\"\n        android:icon=\"@mipmap/ic_launcher\"\n        android:roundIcon=\"@mipmap/ic_launcher_round\"\n        android:theme=\"@style/AppTheme\"\n        android:allowBackup=\"true\"\n        android:fullBackupContent=\"@xml/backup_rules\"\n        android:dataExtractionRules=\"@xml/data_extraction_rules\"\n        android:hardwareAccelerated=\"true\"\n        android:resizeableActivity=\"true\">\n\n        <!-- Declare full-screen / tall-screen support. Without this, some ROMs cap the window at\n             the legacy 1.86 aspect ratio and letterbox the app with black bars (the system bars\n             then can't be colored by the app). 2.4 covers up to ~24:9 displays. -->\n        <meta-data android:name=\"android.max_aspect\" android:value=\"2.4\" />\n        <meta-data android:name=\"android.vendor.full_screen\" android:value=\"true\" />\n        <meta-data android:name=\"notch.config\" android:value=\"portrait|landscape\" />\n\n        <activity\n            android:name=\".MainActivity\"\n            android:exported=\"true\">\n            <intent-filter>\n                <action android:name=\"android.intent.action.MAIN\" />\n                <category android:name=\"android.intent.category.LAUNCHER\" />\n            </intent-filter>\n        </activity>\n\n        <!-- Crash viewer: runs in its own process with a plain framework theme so it can render the\n             stack trace even when the AppCompat theme is what blew up. android:name handler lives in\n             CrashApp; this activity only displays the trace passed to it. -->\n        <activity\n            android:name=\".CrashActivity\"\n            android:exported=\"false\"\n            android:process=\":crash\"\n            android:theme=\"@android:style/Theme.Material.Light\" />\n    </application>\n</manifest>\n";
    }

    public final synchronized void s0(Context context, List list) {
        context.getClass();
        list.getClass();
        context.getSharedPreferences("yima_ide_projects", 0).edit().putString("projects_json", v0(list).toString()).apply();
    }

    public final String t(String str) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n    <string name=\"app_name\">" + StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, "&", SerializerConstants.ENTITY_AMP, false, 4, (Object) null), "<", SerializerConstants.ENTITY_LT, false, 4, (Object) null), "\"", SerializerConstants.ENTITY_QUOT, false, 4, (Object) null) + "</string>\n    <string name=\"hello\">Hello World!</string>\n</resources>\n";
    }

    public final void t0(Context context, String str, String str2, List list, List list2, boolean z, boolean z2, boolean z3) throws JSONException {
        context.getClass();
        str.getClass();
        str2.getClass();
        list.getClass();
        list2.getClass();
        SharedPreferences sharedPreferences = context.getSharedPreferences("yima_ide_projects", 0);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("currentFilePath", str2);
        jSONObject.put("openTabs", new JSONArray((Collection) CollectionsKt.distinct(list)));
        jSONObject.put("expandedDirectories", new JSONArray((Collection) CollectionsKt.distinct(list2)));
        jSONObject.put("isEditorFullscreen", z);
        jSONObject.put("isFileTreeExpanded", z2);
        jSONObject.put("isEditorExpanded", z3);
        sharedPreferences.edit().putString("workbench_session_" + str, jSONObject.toString()).apply();
    }

    public final String u() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n    <!-- App theme: descends from Theme.MaterialComponents so MaterialButton / MaterialCardView pick\n         up their default styles (materialButtonStyle, materialCardViewStyle, ...). The parent is\n         resolved by name against the merged Material + appcompat resources (compiled on-device,\n         Milestone 2e-4). colorPrimary/colorAccent are appcompat attrs (NOT android:) tinting widgets\n         app-wide; colorPrimaryVariant/colorSecondary are Material attrs. -->\n    <style name=\"AppTheme\" parent=\"Theme.MaterialComponents.Light.NoActionBar\">\n        <item name=\"colorPrimary\">@color/primary</item>\n        <item name=\"colorPrimaryDark\">@color/primary</item>\n        <item name=\"colorPrimaryVariant\">@color/primary</item>\n        <item name=\"colorAccent\">@color/primary</item>\n        <item name=\"colorSecondary\">@color/primary</item>\n        <!-- The app must own the system-bar backgrounds, and they must NOT be translucent —\n             otherwise the bars render as a black scrim and statusBarColor is ignored entirely. -->\n        <item name=\"android:windowDrawsSystemBarBackgrounds\">true</item>\n        <item name=\"android:windowTranslucentStatus\">false</item>\n        <item name=\"android:windowTranslucentNavigation\">false</item>\n        <!-- Status bar: tinted with the app's primary color, light icons on top. -->\n        <item name=\"android:statusBarColor\">@color/primary</item>\n        <!-- Navigation bar: white background with dark icons (API 27+). -->\n        <item name=\"android:navigationBarColor\">@android:color/white</item>\n        <item name=\"android:windowLightNavigationBar\">true</item>\n    </style>\n</resources>\n";
    }

    public final kq4 u0(File file, String str) {
        String name = file.getName();
        name.getClass();
        kq4 kq4Var = new kq4(name, str, true, null, 8, null);
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : ArraysKt.sortedWith(fileArrListFiles, ComparisonsKt.compareBy(new Function1[]{new Function1() { // from class: ofb
                public final Object invoke(Object obj) {
                    return ufb.f((File) obj);
                }
            }, new Function1() { // from class: pfb
                public final Object invoke(Object obj) {
                    return ufb.i((File) obj);
                }
            }}))) {
                String str2 = str + PsuedoNames.PSEUDONAME_ROOT + file2.getName();
                if (file2.isDirectory()) {
                    kq4Var.e().add(u0(file2, str2));
                } else {
                    List listE = kq4Var.e();
                    String name2 = file2.getName();
                    name2.getClass();
                    listE.add(new kq4(name2, str2, false, null, 8, null));
                }
            }
        }
        return kq4Var;
    }

    public final boolean v(Context context, String str, String str2, String str3) {
        File fileQ0;
        int iIndexOf$default;
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        boolean zContains$default = false;
        if (StringsKt.isBlank(str2) || (fileQ0 = q0(context, str, "build.gradle")) == null) {
            return false;
        }
        synchronized (T(fileQ0)) {
            try {
                String text$default = FilesKt.readText$default(fileQ0, (Charset) null, 1, (Object) null);
                if (StringsKt.contains$default(text$default, "dependencies", false, 2, (Object) null)) {
                    String str4 = "'" + str2 + "'";
                    String str5 = "\"" + str2 + "\"";
                    if (!StringsKt.contains$default(text$default, str4, false, 2, (Object) null) && !StringsKt.contains$default(text$default, str5, false, 2, (Object) null) && (iIndexOf$default = StringsKt.indexOf$default(text$default, "dependencies {", 0, false, 6, (Object) null)) >= 0) {
                        int i = iIndexOf$default + 14;
                        String strSubstring = text$default.substring(0, i);
                        FilesKt.writeText$default(fileQ0, strSubstring + ("\n    " + str3 + " " + str4) + text$default.substring(i), (Charset) null, 2, (Object) null);
                        zContains$default = StringsKt.contains$default(FilesKt.readText$default(fileQ0, (Charset) null, 1, (Object) null), str4, false, 2, (Object) null);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return zContains$default;
    }

    public final JSONArray v0(List list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            dfb dfbVar = (dfb) it.next();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", dfbVar.e());
            jSONObject.put("name", dfbVar.f());
            jSONObject.put("nickname", dfbVar.g());
            jSONObject.put("packageName", dfbVar.h());
            jSONObject.put("type", dfbVar.i());
            jSONObject.put("files", new JSONArray((Collection) dfbVar.d()));
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public final synchronized c w(Context context, dfb dfbVar, String str, String str2, int i) {
        context.getClass();
        dfbVar.getClass();
        str.getClass();
        str2.getClass();
        rbc.a.m(context, dfbVar.e(), str, str2, i);
        return k0(context, dfbVar);
    }

    public final void w0(Context context, File file, String str, String str2, int i, boolean z) {
        FilesKt.writeText$default(new File(file, "build.gradle"), y(str2, i), (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file, "AndroidManifest.xml"), s(str2), (Charset) null, 2, (Object) null);
        File file2 = new File(file, "src/" + StringsKt.replace$default(str2, '.', '/', false, 4, (Object) null));
        file2.mkdirs();
        if (z) {
            FilesKt.writeText$default(new File(file2, "MainActivity.kt"), q(str2), (Charset) null, 2, (Object) null);
        } else {
            FilesKt.writeText$default(new File(file2, "MainActivity.java"), r(str2), (Charset) null, 2, (Object) null);
        }
        FilesKt.writeText$default(new File(file2, "CrashApp.java"), I(str2), (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file2, "CrashActivity.java"), H(str2), (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file2, "DiagLogger.java"), R(str2), (Charset) null, 2, (Object) null);
        File file3 = new File(file, "res/values");
        file3.mkdirs();
        FilesKt.writeText$default(new File(file3, "strings.xml"), t(str), (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file3, "colors.xml"), o(), (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file3, "styles.xml"), u(), (Charset) null, 2, (Object) null);
        File file4 = new File(file, "res/values-night");
        file4.mkdirs();
        FilesKt.writeText$default(new File(file4, "colors.xml"), n(), (Charset) null, 2, (Object) null);
        File file5 = new File(file, "res/layout");
        file5.mkdirs();
        FilesKt.writeText$default(new File(file5, "activity_main.xml"), p(), (Charset) null, 2, (Object) null);
        File file6 = new File(file, "res/drawable");
        file6.mkdirs();
        FilesKt.writeText$default(new File(file6, "ic_launcher_background.xml"), j(), (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file6, "ic_launcher_foreground.xml"), k(), (Charset) null, 2, (Object) null);
        File file7 = new File(file, "res/mipmap-anydpi-v26");
        file7.mkdirs();
        FilesKt.writeText$default(new File(file7, "ic_launcher.xml"), l(), (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file7, "ic_launcher_round.xml"), l(), (Charset) null, 2, (Object) null);
        File file8 = new File(file, "res/mipmap");
        file8.mkdirs();
        z0(context, new File(file8, "ic_launcher.png"));
        z0(context, new File(file8, "ic_launcher_round.png"));
        File file9 = new File(file, "res/xml");
        file9.mkdirs();
        FilesKt.writeText$default(new File(file9, "backup_rules.xml"), x(), (Charset) null, 2, (Object) null);
        FilesKt.writeText$default(new File(file9, "data_extraction_rules.xml"), O(), (Charset) null, 2, (Object) null);
    }

    public final String x() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<!--\n   按需在此声明备份的包含/排除规则，例如：\n   <full-backup-content>\n       <include domain=\"sharedpref\" path=\".\"/>\n       <exclude domain=\"sharedpref\" path=\"device.xml\"/>\n   </full-backup-content>\n-->\n<full-backup-content />\n";
    }

    public final String y(String str, int i) {
        str.getClass();
        return "plugins {\n    id 'com.android.application'\n}\n\nandroid {\n    namespace '" + str + "'\n    compileSdk 36\n\n    defaultConfig {\n        applicationId \"" + str + "\"\n        minSdk " + i + "\n        targetSdk 36\n        versionCode 1\n        versionName \"1.0\"\n    }\n}\n\ndependencies {\n    // material 是 Material Components 全家桶：它本身依赖 appcompat，并再拉一批传递 AAR\n    // （recyclerview / coordinatorlayout / viewpager2 / transition / dynamicanimation ...）。\n    // 每个 AAR 都按包名生成自己的 R 类，Theme.MaterialComponents 主题、Material 组件的\n    // app: 自定义 attr/styleable、颜色选择器、矢量图全部在端上编译合并。\n    // 需要更多依赖时按 'group:artifact:version' 追加即可。\n    implementation 'com.google.android.material:material:1.12.0'\n}\n";
    }

    public final void y0(File file, String str) {
        String strT;
        File file2 = new File(file, "res/values/strings.xml");
        if (file2.isFile()) {
            String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, "&", SerializerConstants.ENTITY_AMP, false, 4, (Object) null), "<", SerializerConstants.ENTITY_LT, false, 4, (Object) null), "\"", SerializerConstants.ENTITY_QUOT, false, 4, (Object) null);
            String text$default = FilesKt.readText$default(file2, (Charset) null, 1, (Object) null);
            Regex regex = d;
            if (regex.containsMatchIn(text$default)) {
                strT = regex.replace(text$default, "<string name=\"app_name\">" + strReplace$default + "</string>");
            } else {
                strT = t(str);
            }
            FilesKt.writeText$default(file2, strT, (Charset) null, 2, (Object) null);
        }
    }

    public final void z(File file) {
        final String absolutePath;
        try {
            absolutePath = file.getCanonicalPath();
        } catch (Exception unused) {
            absolutePath = file.getAbsolutePath();
        }
        final String str = absolutePath + File.separator;
        Set setKeySet = e.keySet();
        setKeySet.getClass();
        CollectionsKt.removeAll(setKeySet, new Function1() { // from class: tfb
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ufb.b(absolutePath, str, (String) obj));
            }
        });
    }

    public final void z0(Context context, File file) {
        context.getClass();
        file.getClass();
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        byte[] bArrJ = J(context);
        if (bArrJ == null) {
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArrJ);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }

    public static abstract class d {

        public static final class a extends d {
            public final String a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str) {
                super(null);
                str.getClass();
                this.a = str;
            }

            public final String a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof a) && Intrinsics.areEqual(this.a, ((a) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Err(message=" + this.a + ")";
            }
        }

        public static final class b extends d {
            public static final int b = dfb.g;
            public final dfb a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(dfb dfbVar) {
                super(null);
                dfbVar.getClass();
                this.a = dfbVar;
            }

            public final dfb a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof b) && Intrinsics.areEqual(this.a, ((b) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Ok(project=" + this.a + ")";
            }
        }

        public /* synthetic */ d(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public d() {
        }
    }

    public static abstract class e {

        public static final class a extends e {
            public final int a;
            public final String b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(int i, String str) {
                super(null);
                str.getClass();
                this.a = i;
                this.b = str;
            }

            public final String a() {
                return this.b;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof a)) {
                    return false;
                }
                a aVar = (a) obj;
                return this.a == aVar.a && Intrinsics.areEqual(this.b, aVar.b);
            }

            public int hashCode() {
                return (Integer.hashCode(this.a) * 31) + this.b.hashCode();
            }

            public String toString() {
                return "Ambiguous(count=" + this.a + ", hint=" + this.b + ")";
            }
        }

        public static final class b extends e {
            public static final b a = new b();

            public b() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof b);
            }

            public int hashCode() {
                return 1617311829;
            }

            public String toString() {
                return "NotFound";
            }
        }

        public static final class c extends e {
            public static final c a = new c();

            public c() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof c);
            }

            public int hashCode() {
                return 974012727;
            }

            public String toString() {
                return "NotMatched";
            }
        }

        public static final class d extends e {
            public final vbe.c a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(vbe.c cVar) {
                super(null);
                cVar.getClass();
                this.a = cVar;
            }

            public final vbe.c a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof d) && this.a == ((d) obj).a;
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Success(mode=" + this.a + ")";
            }
        }

        /* JADX INFO: renamed from: ufb$e$e, reason: collision with other inner class name */
        public static final class C0013e extends e {
            public static final C0013e a = new C0013e();

            public C0013e() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof C0013e);
            }

            public int hashCode() {
                return -1995303172;
            }

            public String toString() {
                return "VerifyFailed";
            }
        }

        public static final class f extends e {
            public static final f a = new f();

            public f() {
                super(null);
            }

            public boolean equals(Object obj) {
                return this == obj || (obj instanceof f);
            }

            public int hashCode() {
                return 435942230;
            }

            public String toString() {
                return "WriteFailed";
            }
        }

        public /* synthetic */ e(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public e() {
        }
    }
}

package defpackage;

import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler;
import org.jetbrains.kotlin.config.Services;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public final class db8 {
    public static final db8 a = new db8();

    public static CharSequence a(File file) {
        file.getClass();
        String absolutePath = file.getAbsolutePath();
        absolutePath.getClass();
        return absolutePath;
    }

    public static /* synthetic */ boolean c(db8 db8Var, List list, List list2, List list3, File file, String str, String str2, List list4, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            str = "17";
        }
        return db8Var.b(list, list2, list3, file, str, (i & 32) != 0 ? "app" : str2, (i & 64) != 0 ? CollectionsKt.emptyList() : list4, function1);
    }

    public final boolean b(List list, List list2, List list3, File file, String str, String str2, List list4, Function1 function1) {
        list.getClass();
        list2.getClass();
        list3.getClass();
        file.getClass();
        str.getClass();
        str2.getClass();
        list4.getClass();
        function1.getClass();
        if (list.isEmpty()) {
            return true;
        }
        file.mkdirs();
        K2JVMCompilerArguments k2JVMCompilerArguments = new K2JVMCompilerArguments();
        List list5 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
        Iterator it = list5.iterator();
        while (it.hasNext()) {
            arrayList.add(((File) it.next()).getAbsolutePath());
        }
        List list6 = list2;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list6, 10));
        Iterator it2 = list6.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((File) it2.next()).getAbsolutePath());
        }
        k2JVMCompilerArguments.setFreeArgs(CollectionsKt.toMutableList(CollectionsKt.plus(arrayList, arrayList2)));
        k2JVMCompilerArguments.setDestination(file.getAbsolutePath());
        String str3 = File.pathSeparator;
        str3.getClass();
        k2JVMCompilerArguments.setClasspath(CollectionsKt.joinToString$default(list3, str3, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new cb8(), 30, (Object) null));
        k2JVMCompilerArguments.setJvmTarget(str);
        k2JVMCompilerArguments.setModuleName(str2);
        k2JVMCompilerArguments.setNoStdlib(true);
        k2JVMCompilerArguments.setNoReflect(true);
        k2JVMCompilerArguments.setNoJdk(true);
        k2JVMCompilerArguments.setSuppressWarnings(false);
        if (!list4.isEmpty()) {
            List list7 = list4;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list7, 10));
            Iterator it3 = list7.iterator();
            while (it3.hasNext()) {
                arrayList3.add(((File) it3.next()).getAbsolutePath());
            }
            k2JVMCompilerArguments.setPluginClasspaths((String[]) arrayList3.toArray(new String[0]));
        }
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        try {
            ExitCode exitCodeExec = new K2JVMCompiler().exec(new a(booleanRef, function1), Services.EMPTY, k2JVMCompilerArguments);
            ExitCode exitCode = ExitCode.OK;
            if (exitCodeExec != exitCode) {
                function1.invoke(t92.c(t92.a, "Kotlin 编译器退出码：" + exitCodeExec, (String) null, 0, 0, 14, (Object) null) + "\n");
            }
            return exitCodeExec == exitCode && !booleanRef.element;
        } catch (Throwable th) {
            Log.e("KotlinCompilerEngine", "Kotlin compilation crashed", th);
            function1.invoke(t92.c(t92.a, "Kotlin 编译器异常：" + th.getMessage(), (String) null, 0, 0, 14, (Object) null) + "\n");
            return false;
        }
    }
}

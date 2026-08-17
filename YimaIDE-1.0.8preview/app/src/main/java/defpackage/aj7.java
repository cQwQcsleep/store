package defpackage;

import android.util.Log;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.text.StringsKt;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class aj7 implements ICompilationUnit {
    public final File b;
    public final File c;

    public aj7(File file, File file2) {
        file.getClass();
        file2.getClass();
        this.b = file;
        this.c = file2;
    }

    public char[] getContents() {
        try {
            char[] charArray = FilesKt.readText$default(this.b, (Charset) null, 1, (Object) null).toCharArray();
            charArray.getClass();
            return charArray;
        } catch (Exception e) {
            Log.e("JavaCompilerEngine", "无法读取源文件 " + this.b.getPath(), e);
            ge7.a.P("[Error] 无法读取源文件 " + this.b.getName() + "：" + e.getMessage() + "\n");
            return new char[0];
        }
    }

    public char[] getFileName() {
        String name = this.b.getName();
        name.getClass();
        char[] charArray = name.toCharArray();
        charArray.getClass();
        return charArray;
    }

    public char[] getMainTypeName() {
        char[] charArray = FilesKt.getNameWithoutExtension(this.b).toCharArray();
        charArray.getClass();
        return charArray;
    }

    public char[][] getPackageName() {
        String path = FilesKt.relativeTo(this.b, this.c).getPath();
        path.getClass();
        String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
        int iLastIndexOf$default = StringsKt.lastIndexOf$default(strReplace$default, '/', 0, false, 6, (Object) null);
        if (iLastIndexOf$default <= 0) {
            return new char[0][];
        }
        List listSplit$default = StringsKt.split$default(strReplace$default.substring(0, iLastIndexOf$default), new char[]{'/'}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            char[] charArray = ((String) it.next()).toCharArray();
            charArray.getClass();
            arrayList.add(charArray);
        }
        return (char[][]) arrayList.toArray(new char[0][]);
    }

    public boolean ignoreOptionalProblems() {
        return false;
    }
}

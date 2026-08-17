package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class yrd implements ICompilationUnit {
    public final String b;
    public final String c;

    public yrd(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.b = str;
        this.c = str2;
    }

    public char[] getContents() {
        char[] charArray = this.c.toCharArray();
        charArray.getClass();
        return charArray;
    }

    public char[] getFileName() {
        char[] charArray = (this.b + ".java").toCharArray();
        charArray.getClass();
        return charArray;
    }

    public char[] getMainTypeName() {
        char[] charArray = StringsKt.substringAfterLast$default(this.b, '.', (String) null, 2, (Object) null).toCharArray();
        charArray.getClass();
        return charArray;
    }

    public char[][] getPackageName() {
        int iLastIndexOf$default = StringsKt.lastIndexOf$default(this.b, '.', 0, false, 6, (Object) null);
        if (iLastIndexOf$default <= 0) {
            return new char[0][];
        }
        List listSplit$default = StringsKt.split$default(this.b.substring(0, iLastIndexOf$default), new char[]{'.'}, false, 0, 6, (Object) null);
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
        return true;
    }
}

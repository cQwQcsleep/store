package org.jetbrains.kotlin.cli.common.messages;

import com.intellij.openapi.util.text.StringUtil;
import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class OutputMessageUtil {

    public static class Output implements Serializable {
        static final long serialVersionUID = 0;
        public final File outputFile;
        public final Collection<File> sourceFiles;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceFiles", "org/jetbrains/kotlin/cli/common/messages/OutputMessageUtil$Output", "<init>"));
        }

        public Output(Collection<File> collection, File file) {
            if (collection == null) {
                $$$reportNull$$$0(0);
            }
            this.sourceFiles = collection;
            this.outputFile = file;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3) ? 2 : 3];
        if (i == 1 || i == 2 || i == 3) {
            objArr[0] = "org/jetbrains/kotlin/cli/common/messages/OutputMessageUtil";
        } else if (i != 4) {
            objArr[0] = "e";
        } else {
            objArr[0] = "message";
        }
        if (i == 1) {
            objArr[1] = "renderException";
        } else if (i == 2 || i == 3) {
            objArr[1] = "formatOutputMessage";
        } else {
            objArr[1] = "org/jetbrains/kotlin/cli/common/messages/OutputMessageUtil";
        }
        if (i != 1 && i != 2 && i != 3) {
            if (i != 4) {
                objArr[2] = "renderException";
            } else {
                objArr[2] = "parseOutputMessage";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static String formatOutputMessage(Collection<File> collection, File file) {
        return "Output:\n" + file.getPath() + "\nSources:\n" + StringUtil.join(collection, "\n");
    }

    public static Output parseOutputMessage(String str) {
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        String[] strArrSplit = str.split("\n");
        if (strArrSplit.length <= 2 || !"Output:".equals(strArrSplit[0])) {
            return null;
        }
        if ("Sources:".equals(strArrSplit[1])) {
            return new Output(parseSourceFiles(strArrSplit, 2), null);
        }
        File file = new File(strArrSplit[1]);
        if ("Sources:".equals(strArrSplit[2])) {
            return new Output(parseSourceFiles(strArrSplit, 3), file);
        }
        return null;
    }

    private static Collection<File> parseSourceFiles(String[] strArr, int i) {
        ArrayList arrayList = new ArrayList();
        while (i < strArr.length) {
            arrayList.add(new File(strArr[i]));
            i++;
        }
        return arrayList;
    }

    public static String renderException(Throwable th) {
        if (th == null) {
            $$$reportNull$$$0(0);
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        if (string == null) {
            $$$reportNull$$$0(1);
        }
        return string;
    }

    public static String formatOutputMessage(Collection<String> collection, String str) {
        return "Output:\n" + str + "\nSources:\n" + StringUtil.join(collection, "\n");
    }
}

package org.jetbrains.kotlin.cli.common.repl;

import com.google.common.base.Throwables;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.LineSeparator;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.repl.ReplUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.repl.ReplEscapeType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u000f\u0010\u0006\u001a\u00070\u0002¢\u0006\u0002\b\u0007*\u00020\u0002\u001a\u000f\u0010\b\u001a\u00070\u0002¢\u0006\u0002\b\u0007*\u00020\u0002\u001a\u0012\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b\u001a\n\u0010\f\u001a\u00020\u0002*\u00020\u0002\u001a\n\u0010\r\u001a\u00020\u0002*\u00020\u0002\u001a\n\u0010\u000e\u001a\u00020\u0002*\u00020\u0002\u001a\n\u0010\u000f\u001a\u00020\u0002*\u00020\u0002\u001a\u000e\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012\u001a\u000e\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015\u001a\u0016\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0001*\u00020\u001cH\u0000\u001a\u0012\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0001*\u00020\u001eH\u0000\u001a*\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H 0\u0001\"\b\b\u0000\u0010 *\u00020!*\b\u0012\u0004\u0012\u0002H 0\u00012\u0006\u0010\"\u001a\u00020\u0002H\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"SOURCE_CHARS", Argument.Delimiters.none, Argument.Delimiters.none, "XML_REPLACEMENTS", "END_LINE", "XML_PREAMBLE", "replUnescapeLineBreaks", "Lorg/jetbrains/annotations/NotNull;", "replEscapeLineBreaks", "replOutputAsXml", "escapeType", "Lorg/jetbrains/kotlin/utils/repl/ReplEscapeType;", "replInputAsXml", "replAddLineBreak", "replRemoveLineBreaksInTheEnd", "replNormalizeLineBreaks", "makeScriptBaseName", "codeLine", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "scriptResultFieldName", "lineNo", Argument.Delimiters.none, "renderReplStackTrace", "cause", Argument.Delimiters.none, "startFromMethodName", "listAllUrlsAsFiles", "Ljava/io/File;", "Ljava/lang/ClassLoader;", "listLocalUrlsAsFiles", "Ljava/net/URLClassLoader;", "ensureNotEmpty", "T", Argument.Delimiters.none, "error", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReplUtilKt {
    private static final String END_LINE;
    private static final List<String> SOURCE_CHARS = CollectionsKt.listOf(new String[]{"\r", "\n", "#"});
    private static final List<String> XML_REPLACEMENTS = CollectionsKt.listOf(new String[]{"#r", "#n", "#diez"});

    static {
        String separatorString = LineSeparator.getSystemLineSeparator().getSeparatorString();
        separatorString.getClass();
        END_LINE = separatorString;
    }

    public static ClassLoader a(ClassLoader classLoader) {
        classLoader.getClass();
        return classLoader.getParent();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> ensureNotEmpty(List<? extends T> list, String str) {
        list.getClass();
        str.getClass();
        if (!list.isEmpty()) {
            return list;
        }
        k2d.a(str);
        return null;
    }

    public static final List<File> listAllUrlsAsFiles(ClassLoader classLoader) {
        classLoader.getClass();
        Sequence sequenceFilterIsInstance = SequencesKt.filterIsInstance(SequencesKt.generateSequence(classLoader, new Function1() { // from class: vec
            public final Object invoke(Object obj) {
                return ReplUtilKt.a((ClassLoader) obj);
            }
        }), URLClassLoader.class);
        List listEmptyList = CollectionsKt.emptyList();
        Iterator it = sequenceFilterIsInstance.iterator();
        while (it.hasNext()) {
            listEmptyList = CollectionsKt.plus(listLocalUrlsAsFiles((URLClassLoader) it.next()), listEmptyList);
        }
        return CollectionsKt.distinct(listEmptyList);
    }

    public static final List<File> listLocalUrlsAsFiles(URLClassLoader uRLClassLoader) {
        uRLClassLoader.getClass();
        URL[] uRLs = uRLClassLoader.getURLs();
        uRLs.getClass();
        ArrayList arrayList = new ArrayList();
        for (URL url : uRLs) {
            String string = url.toString();
            string.getClass();
            String strRemovePrefix = StringsKt.removePrefix(string, "file:");
            if (strRemovePrefix != null) {
                arrayList.add(strRemovePrefix);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new File((String) it.next()));
        }
        return arrayList2;
    }

    public static final String makeScriptBaseName(ReplCodeLine replCodeLine) {
        String str;
        replCodeLine.getClass();
        StringBuilder sb = new StringBuilder("Line_");
        sb.append(replCodeLine.getNo());
        if (replCodeLine.getGeneration() > 1) {
            str = "_gen_" + replCodeLine.getGeneration();
        } else {
            str = Argument.Delimiters.none;
        }
        sb.append(str);
        return sb.toString();
    }

    public static final String renderReplStackTrace(Throwable th, String str) {
        th.getClass();
        str.getClass();
        ArrayList arrayList = new ArrayList();
        StackTraceElement[] stackTrace = th.getStackTrace();
        stackTrace.getClass();
        List listReversed = ArraysKt.reversed(stackTrace);
        int size = listReversed.size();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            StackTraceElement stackTraceElement = (StackTraceElement) listReversed.get(i);
            if (Intrinsics.areEqual(stackTraceElement.getClassName() + '.' + stackTraceElement.getMethodName(), str)) {
                z = false;
            }
            if (!z) {
                arrayList.add(stackTraceElement);
            }
        }
        th.setStackTrace((StackTraceElement[]) CollectionsKt.dropLast(CollectionsKt.reversed(arrayList), 1).toArray(new StackTraceElement[0]));
        String stackTraceAsString = Throwables.getStackTraceAsString(th);
        stackTraceAsString.getClass();
        return StringsKt.trimEnd(stackTraceAsString).toString();
    }

    public static final String replAddLineBreak(String str) {
        str.getClass();
        return str + END_LINE;
    }

    public static final String replEscapeLineBreaks(String str) {
        str.getClass();
        String strReplace = StringUtil.replace(str, SOURCE_CHARS, XML_REPLACEMENTS);
        strReplace.getClass();
        return strReplace;
    }

    public static final String replInputAsXml(String str) {
        str.getClass();
        String strEscapeXmlEntities = StringUtil.escapeXmlEntities(replEscapeLineBreaks(str));
        strEscapeXmlEntities.getClass();
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><input>" + strEscapeXmlEntities + "</input>";
    }

    public static final String replNormalizeLineBreaks(String str) {
        str.getClass();
        return StringsKt.replace$default(str, END_LINE, "\n", false, 4, (Object) null);
    }

    public static final String replOutputAsXml(String str, ReplEscapeType replEscapeType) {
        str.getClass();
        replEscapeType.getClass();
        String strEscapeXmlEntities = StringUtil.escapeXmlEntities(replEscapeLineBreaks(str));
        strEscapeXmlEntities.getClass();
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><output type=\"" + replEscapeType + "\">" + strEscapeXmlEntities + "</output>";
    }

    public static final String replRemoveLineBreaksInTheEnd(String str) {
        str.getClass();
        char[] charArray = END_LINE.toCharArray();
        charArray.getClass();
        return StringsKt.trimEnd(str, Arrays.copyOf(charArray, charArray.length));
    }

    public static final String replUnescapeLineBreaks(String str) {
        str.getClass();
        String strReplace = StringUtil.replace(str, XML_REPLACEMENTS, SOURCE_CHARS);
        strReplace.getClass();
        return strReplace;
    }

    public static final String scriptResultFieldName(int i) {
        return "res" + i;
    }
}

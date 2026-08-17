package kotlin.io;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004b\u0002\b\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0006*\u00020\u00072\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004b\u0002\b\u0005\u001a\"\u0010\b\u001a\u00020\t*\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u000bH\u0086\u0080\u0004\u001a\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e*\u00020\u0002H\u0086\u0080\u0004\u001aG\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\u00020\u00022\u0018\u0010\u0011\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0012\u0012\u0004\u0012\u0002H\u00100\u000bH\u0087\u0088\bb\u0002\b\u0014ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0013\u001a\u0012\u0010\u0015\u001a\u00020\u0016*\u00020\fH\u0087\u0088\u0004b\u0002\b\u0005\u001a\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0012*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0018\u001a\u00020\f*\u00020\u0002H\u0086\u0080\u0004\u001a$\u0010\u0019\u001a\u00020\u001a*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\bb\u0002\b\u0014\u001a\u001c\u0010\u0018\u001a\u00020\f*\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0087\u0088\u0004b\u0002\b\u0005\u001a\u000e\u0010\u001f\u001a\u00020 *\u00020\u001cH\u0086\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006!"}, d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Lkotlin/internal/InlineOnly;", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "forEachLine", "", "action", "Lkotlin/Function1;", "", "readLines", "", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlin/IgnorableReturnValue;", "reader", "Ljava/io/StringReader;", "lineSequence", "readText", "copyTo", "", "out", "Ljava/net/URL;", "charset", "Ljava/nio/charset/Charset;", "readBytes", "", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class TextStreamsKt {
    public static Unit a(ArrayList arrayList, String str) {
        str.getClass();
        arrayList.add(str);
        return Unit.INSTANCE;
    }

    private static final BufferedReader buffered(Reader reader, int i) {
        reader.getClass();
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    public static /* synthetic */ BufferedReader buffered$default(Reader reader, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        reader.getClass();
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    @IgnorableReturnValue
    public static final long copyTo(Reader reader, Writer writer, int i) throws IOException {
        reader.getClass();
        writer.getClass();
        char[] cArr = new char[i];
        int i2 = reader.read(cArr);
        long j = 0;
        while (i2 >= 0) {
            writer.write(cArr, 0, i2);
            j += (long) i2;
            i2 = reader.read(cArr);
        }
        return j;
    }

    public static /* synthetic */ long copyTo$default(Reader reader, Writer writer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(reader, writer, i);
    }

    public static final void forEachLine(Reader reader, Function1<? super String, Unit> function1) {
        reader.getClass();
        function1.getClass();
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192);
        try {
            Iterator it2 = lineSequence(bufferedReader).iterator();
            while (it2.hasNext()) {
                function1.invoke(it2.next());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedReader, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(bufferedReader, th);
                throw th2;
            }
        }
    }

    public static final Sequence<String> lineSequence(BufferedReader bufferedReader) {
        bufferedReader.getClass();
        return SequencesKt.constrainOnce(new LinesSequence(bufferedReader));
    }

    public static final byte[] readBytes(URL url) throws IOException {
        url.getClass();
        InputStream inputStreamOpenStream = url.openStream();
        try {
            inputStreamOpenStream.getClass();
            byte[] bytes = ByteStreamsKt.readBytes(inputStreamOpenStream);
            CloseableKt.closeFinally(inputStreamOpenStream, (Throwable) null);
            return bytes;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStreamOpenStream, th);
                throw th2;
            }
        }
    }

    public static final List<String> readLines(Reader reader) {
        reader.getClass();
        final ArrayList arrayList = new ArrayList();
        forEachLine(reader, new Function1() { // from class: zbe
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextStreamsKt.a(arrayList, (String) obj);
            }
        });
        return arrayList;
    }

    public static final String readText(Reader reader) {
        reader.getClass();
        StringWriter stringWriter = new StringWriter();
        copyTo$default(reader, stringWriter, 0, 2, null);
        String string = stringWriter.toString();
        string.getClass();
        return string;
    }

    public static /* synthetic */ String readText$default(URL url, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        url.getClass();
        charset.getClass();
        return new String(readBytes(url), charset);
    }

    private static final StringReader reader(String str) {
        str.getClass();
        return new StringReader(str);
    }

    @IgnorableReturnValue
    public static final <T> T useLines(Reader reader, Function1<? super Sequence<String>, ? extends T> function1) {
        reader.getClass();
        function1.getClass();
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192);
        try {
            T tInvoke = function1.invoke(lineSequence(bufferedReader));
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(bufferedReader, (Throwable) null);
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(bufferedReader, th);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    private static final BufferedWriter buffered(Writer writer, int i) {
        writer.getClass();
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    public static /* synthetic */ BufferedWriter buffered$default(Writer writer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        writer.getClass();
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    private static final String readText(URL url, Charset charset) {
        url.getClass();
        charset.getClass();
        return new String(readBytes(url), charset);
    }
}

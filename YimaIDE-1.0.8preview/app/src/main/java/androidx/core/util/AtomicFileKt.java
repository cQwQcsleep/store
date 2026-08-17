package androidx.core.util;

import android.util.AtomicFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.text.Charsets;
import org.jline.terminal.TerminalBuilder;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022!\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\bø\u0001\u0000\u001a\u0012\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b\u001a\u001c\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\r\u0010\u0011\u001a\u00020\u000b*\u00020\u0002H\u0086\b\u001a\u0014\u0010\u0012\u001a\u00020\u000e*\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"tryWrite", "", "Landroid/util/AtomicFile;", "block", "Lkotlin/Function1;", "Ljava/io/FileOutputStream;", "Lkotlin/ParameterName;", "name", TerminalBuilder.PROP_OUTPUT_OUT, "writeBytes", "array", "", "writeText", "text", "", "charset", "Ljava/nio/charset/Charset;", "readBytes", "readText", "core"}, k = 2, mv = {2, 1, 0}, xi = OPCode.BACKREFN)
public final class AtomicFileKt {
    public static final byte[] readBytes(AtomicFile atomicFile) throws IOException {
        atomicFile.getClass();
        byte[] fully = atomicFile.readFully();
        fully.getClass();
        return fully;
    }

    public static final String readText(AtomicFile atomicFile, Charset charset) throws IOException {
        atomicFile.getClass();
        charset.getClass();
        byte[] fully = atomicFile.readFully();
        fully.getClass();
        return new String(fully, charset);
    }

    public static /* synthetic */ String readText$default(AtomicFile atomicFile, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readText(atomicFile, charset);
    }

    public static final void tryWrite(AtomicFile atomicFile, Function1<? super FileOutputStream, Unit> function1) throws IOException {
        atomicFile.getClass();
        function1.getClass();
        FileOutputStream fileOutputStreamStartWrite = atomicFile.startWrite();
        try {
            fileOutputStreamStartWrite.getClass();
            function1.invoke(fileOutputStreamStartWrite);
            InlineMarker.finallyStart(1);
            atomicFile.finishWrite(fileOutputStreamStartWrite);
        } finally {
            InlineMarker.finallyStart(1);
            atomicFile.failWrite(fileOutputStreamStartWrite);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void writeBytes(AtomicFile atomicFile, byte[] bArr) throws IOException {
        atomicFile.getClass();
        bArr.getClass();
        FileOutputStream fileOutputStreamStartWrite = atomicFile.startWrite();
        try {
            fileOutputStreamStartWrite.getClass();
            fileOutputStreamStartWrite.write(bArr);
            atomicFile.finishWrite(fileOutputStreamStartWrite);
        } catch (Throwable th) {
            atomicFile.failWrite(fileOutputStreamStartWrite);
            throw th;
        }
    }

    public static final void writeText(AtomicFile atomicFile, String str, Charset charset) throws IOException {
        atomicFile.getClass();
        str.getClass();
        charset.getClass();
        byte[] bytes = str.getBytes(charset);
        bytes.getClass();
        writeBytes(atomicFile, bytes);
    }

    public static /* synthetic */ void writeText$default(AtomicFile atomicFile, String str, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(atomicFile, str, charset);
    }
}

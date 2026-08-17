package kotlin.io;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\rH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0018\u0010\u000e\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\rH\u0087\u0088\u0004b\u0002\b\u0004\u001a\u000e\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004b\u0002\b\u0004\u001a\u0018\u0010\u000f\u001a\u00020\u0010H\u0087\u0080\u0004b\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u001a\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0087\u0080\u0004b\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u001a(\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0087\u0080\u0004b\f\b\u0016\u0012\b\b\u0002\u0012\u0004\b\b(\u0017b\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a¨\u0006\u001b"}, d2 = {"print", "", "message", "", "Lkotlin/internal/InlineOnly;", "", "", "", "", "", "", "", "", "", "println", "readln", "", "Lkotlin/SinceKotlin;", "version", "1.6", "readlnOrNull", "readLine", "Lkotlin/Deprecated;", "Use `readln()` instead of `readLine()!!`, and `readlnOrNull()` instead of `readLine()`.", "Lkotlin/DeprecatedSinceKotlin;", "warningSince", "2.4", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ConsoleKt {
    private static final void print(byte b) {
        System.out.print(Byte.valueOf(b));
    }

    private static final void println(byte b) {
        System.out.println(Byte.valueOf(b));
    }

    @Deprecated(message = "Use `readln()` instead of `readLine()!!`, and `readlnOrNull()` instead of `readLine()`.")
    @DeprecatedSinceKotlin(warningSince = "2.4")
    public static final String readLine() {
        return readlnOrNull();
    }

    public static final String readln() {
        String str = readlnOrNull();
        if (str != null) {
            return str;
        }
        throw new ReadAfterEOFException("EOF has already been reached");
    }

    public static final String readlnOrNull() {
        LineReader lineReader = LineReader.INSTANCE;
        InputStream inputStream = System.in;
        inputStream.getClass();
        Charset charsetDefaultCharset = Charset.defaultCharset();
        charsetDefaultCharset.getClass();
        return lineReader.readLine(inputStream, charsetDefaultCharset);
    }

    private static final void print(int i) {
        System.out.print(i);
    }

    private static final void println(int i) {
        System.out.println(i);
    }

    private static final void print(long j) {
        System.out.print(j);
    }

    private static final void println(long j) {
        System.out.println(j);
    }

    private static final void print(Object obj) {
        System.out.print(obj);
    }

    private static final void println(Object obj) {
        System.out.println(obj);
    }

    private static final void print(short s) {
        System.out.print(Short.valueOf(s));
    }

    private static final void println(short s) {
        System.out.println(Short.valueOf(s));
    }

    private static final void print(char c) {
        System.out.print(c);
    }

    private static final void println(char c) {
        System.out.println(c);
    }

    private static final void print(boolean z) {
        System.out.print(z);
    }

    private static final void println(boolean z) {
        System.out.println(z);
    }

    private static final void print(float f) {
        System.out.print(f);
    }

    private static final void println(float f) {
        System.out.println(f);
    }

    private static final void print(double d) {
        System.out.print(d);
    }

    private static final void println(double d) {
        System.out.println(d);
    }

    private static final void print(char[] cArr) {
        cArr.getClass();
        System.out.print(cArr);
    }

    private static final void println(char[] cArr) {
        cArr.getClass();
        System.out.println(cArr);
    }

    private static final void println() {
        System.out.println();
    }
}

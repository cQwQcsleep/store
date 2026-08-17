package org.jetbrains.kotlin.cli.common.fir;

import java.io.IOException;
import java.io.InputStreamReader;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000eJ\b\u0010\u0016\u001a\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/fir/SequentialPositionFinder;", Argument.Delimiters.none, "reader", "Ljava/io/InputStreamReader;", "<init>", "(Ljava/io/InputStreamReader;)V", "currentLineContent", Argument.Delimiters.none, "buffer", Argument.Delimiters.none, "bufLength", Argument.Delimiters.none, "bufPos", "endOfStream", Argument.Delimiters.none, "skipNextLf", "charsRead", "currentLine", "findNextPosition", "Lorg/jetbrains/kotlin/cli/common/fir/KtSourceFileDiagnosticPos;", "offset", "withLineContents", "readNextLine", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class SequentialPositionFinder {
    private int bufLength;
    private int bufPos;
    private final char[] buffer;
    private int charsRead;
    private int currentLine;
    private String currentLineContent;
    private boolean endOfStream;
    private final InputStreamReader reader;
    private boolean skipNextLf;

    public SequentialPositionFinder(InputStreamReader inputStreamReader) {
        inputStreamReader.getClass();
        this.reader = inputStreamReader;
        this.buffer = new char[255];
        this.bufLength = -1;
    }

    public static /* synthetic */ KtSourceFileDiagnosticPos findNextPosition$default(SequentialPositionFinder sequentialPositionFinder, int i, boolean z, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: findNextPosition");
            return null;
        }
        if ((i2 & 2) != 0) {
            z = true;
        }
        return sequentialPositionFinder.findNextPosition(i, z);
    }

    private static final KtSourceFileDiagnosticPos findNextPosition$posInCurrentLine(int i, SequentialPositionFinder sequentialPositionFinder, boolean z) {
        int i2 = sequentialPositionFinder.charsRead;
        String str = sequentialPositionFinder.currentLineContent;
        str.getClass();
        int length = (i - ((i2 - str.length()) - 1)) + 1;
        String str2 = sequentialPositionFinder.currentLineContent;
        str2.getClass();
        if (length <= str2.length() + 1) {
            return new KtSourceFileDiagnosticPos(sequentialPositionFinder.currentLine, length, z ? sequentialPositionFinder.currentLineContent : null);
        }
        return null;
    }

    private final String readNextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = this.bufPos;
            if (i >= this.bufLength) {
                int i2 = this.reader.read(this.buffer);
                this.bufLength = i2;
                this.bufPos = 0;
                if (i2 < 0) {
                    this.endOfStream = true;
                    this.currentLine++;
                    this.charsRead++;
                    break;
                }
            } else {
                char[] cArr = this.buffer;
                this.bufPos = i + 1;
                char c = cArr[i];
                int i3 = this.charsRead;
                this.charsRead = i3 + 1;
                if (c == '\n' && this.skipNextLf) {
                    this.charsRead = i3;
                    this.skipNextLf = false;
                } else {
                    if (c == '\n' || c == '\r') {
                        this.currentLine++;
                        this.skipNextLf = c == '\r';
                        break;
                    }
                    sb.append(c);
                    this.skipNextLf = false;
                }
            }
        }
        return sb.toString();
    }

    public final KtSourceFileDiagnosticPos findNextPosition(int offset, boolean withLineContents) {
        if (offset < this.charsRead) {
            KtSourceFileDiagnosticPos ktSourceFileDiagnosticPosFindNextPosition$posInCurrentLine = findNextPosition$posInCurrentLine(offset, this, withLineContents);
            ktSourceFileDiagnosticPosFindNextPosition$posInCurrentLine.getClass();
            return ktSourceFileDiagnosticPosFindNextPosition$posInCurrentLine;
        }
        while (true) {
            if (this.currentLineContent == null) {
                this.currentLineContent = readNextLine();
            }
            KtSourceFileDiagnosticPos ktSourceFileDiagnosticPosFindNextPosition$posInCurrentLine2 = findNextPosition$posInCurrentLine(offset, this, withLineContents);
            if (ktSourceFileDiagnosticPosFindNextPosition$posInCurrentLine2 != null) {
                return ktSourceFileDiagnosticPosFindNextPosition$posInCurrentLine2;
            }
            if (this.endOfStream) {
                return new KtSourceFileDiagnosticPos(-1, offset, withLineContents ? this.currentLineContent : null);
            }
            this.currentLineContent = null;
        }
    }
}

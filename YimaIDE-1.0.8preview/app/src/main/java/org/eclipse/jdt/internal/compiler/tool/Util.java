package org.eclipse.jdt.internal.compiler.tool;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import javax.tools.FileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class Util {
    public static String LINE_SEPARATOR = System.getProperty("line.separator");

    public static class EncodingError {
        int length;
        int position;

        public EncodingError(int i, int i2) {
            this.position = i;
            this.length = i2;
        }

        public String getSource(char[] cArr) {
            char c;
            char c2;
            int i = this.position;
            int i2 = (this.length + i) - 1;
            if (i > i2) {
                return "No source available";
            }
            if ((i < 0 && i2 < 0) || cArr.length == 0) {
                return "No source available";
            }
            StringBuilder sb = new StringBuilder("\t");
            int length = cArr.length;
            int i3 = i >= length ? length - 1 : i;
            while (i3 > 0 && (c2 = cArr[i3 - 1]) != '\n' && c2 != '\r') {
                i3--;
            }
            int i4 = i2 >= length ? length - 1 : i2;
            while (true) {
                int i5 = i4 + 1;
                if (i5 >= length || (c = cArr[i5]) == '\r' || c == '\n') {
                    break;
                }
                i4 = i5;
            }
            while (true) {
                char c3 = cArr[i3];
                if (c3 != ' ' && c3 != '\t') {
                    break;
                }
                i3++;
            }
            sb.append(cArr, i3, (i4 - i3) + 1);
            sb.append(Util.LINE_SEPARATOR);
            sb.append("\t");
            while (i3 < i) {
                sb.append(cArr[i3] == '\t' ? '\t' : ' ');
                i3++;
            }
            while (true) {
                if (i > (i2 >= length ? length - 1 : i2)) {
                    return sb.toString();
                }
                sb.append('^');
                i++;
            }
        }
    }

    public static class EncodingErrorCollector {
        String encoding;
        ArrayList<EncodingError> encodingErrors = new ArrayList<>();
        FileObject fileObject;

        public EncodingErrorCollector(FileObject fileObject, String str) {
            this.fileObject = fileObject;
            this.encoding = str;
        }

        public void collect(int i, int i2) {
            this.encodingErrors.add(new EncodingError(i, i2));
        }

        public void reportAllEncodingErrors(String str) {
            char[] charArray = str.toCharArray();
            for (EncodingError encodingError : this.encodingErrors) {
                System.err.println(this.fileObject.getName() + " Unmappable character for encoding " + this.encoding);
                System.err.println(encodingError.getSource(charArray));
            }
        }
    }

    public static CharSequence getCharContents(FileObject fileObject, boolean z, byte[] bArr, String str) throws IOException {
        EncodingErrorCollector encodingErrorCollector = null;
        if (bArr == null) {
            return null;
        }
        try {
            CharsetDecoder charsetDecoderNewDecoder = Charset.forName(str).newDecoder();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length);
            byteBufferAllocate.put(bArr);
            byteBufferAllocate.flip();
            if (z) {
                CodingErrorAction codingErrorAction = CodingErrorAction.REPLACE;
                charsetDecoderNewDecoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
                return charsetDecoderNewDecoder.decode(byteBufferAllocate);
            }
            CodingErrorAction codingErrorAction2 = CodingErrorAction.REPORT;
            charsetDecoderNewDecoder.onMalformedInput(codingErrorAction2).onUnmappableCharacter(codingErrorAction2);
            CharBuffer charBufferAllocate = CharBuffer.allocate(bArr.length);
            String strReplacement = charsetDecoderNewDecoder.replacement();
            int length = strReplacement.length();
            while (true) {
                CoderResult coderResultDecode = charsetDecoderNewDecoder.decode(byteBufferAllocate, charBufferAllocate, true);
                if (!coderResultDecode.isMalformed() && !coderResultDecode.isUnmappable()) {
                    if (!coderResultDecode.isOverflow()) {
                        break;
                    }
                    CharBuffer charBufferAllocate2 = CharBuffer.allocate(charBufferAllocate.capacity() * 2);
                    charBufferAllocate.flip();
                    charBufferAllocate2.put(charBufferAllocate);
                    charBufferAllocate = charBufferAllocate2;
                } else {
                    if (encodingErrorCollector == null) {
                        encodingErrorCollector = new EncodingErrorCollector(fileObject, str);
                    }
                    reportEncodingError(encodingErrorCollector, charBufferAllocate.position(), coderResultDecode.length());
                    if (charBufferAllocate.position() + length >= charBufferAllocate.capacity()) {
                        CharBuffer charBufferAllocate3 = CharBuffer.allocate(charBufferAllocate.capacity() * 2);
                        charBufferAllocate.flip();
                        charBufferAllocate3.put(charBufferAllocate);
                        charBufferAllocate = charBufferAllocate3;
                    }
                    charBufferAllocate.append((CharSequence) strReplacement);
                    byteBufferAllocate.position(byteBufferAllocate.position() + coderResultDecode.length());
                }
            }
            charBufferAllocate.flip();
            if (encodingErrorCollector != null) {
                encodingErrorCollector.reportAllEncodingErrors(charBufferAllocate.toString());
            }
            return charBufferAllocate;
        } catch (IllegalCharsetNameException unused) {
            System.err.println("Illegal charset name : " + str);
            return null;
        } catch (UnsupportedCharsetException unused2) {
            System.err.println("Unsupported charset : " + str);
            return null;
        }
    }

    private static void reportEncodingError(EncodingErrorCollector encodingErrorCollector, int i, int i2) {
        encodingErrorCollector.collect(i, -i2);
    }
}

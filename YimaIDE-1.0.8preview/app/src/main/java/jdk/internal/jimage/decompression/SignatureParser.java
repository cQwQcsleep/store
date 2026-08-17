package jdk.internal.jimage.decompression;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SignatureParser {

    public static class ParseResult {
        public String formatted;
        public final List<String> types;

        private ParseResult() {
            this.types = new ArrayList();
        }
    }

    private SignatureParser() {
    }

    public static ParseResult parseSignatureDescriptor(String str) {
        String strSubstring;
        ParseResult parseResult = new ParseResult();
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = null;
        for (char c : charArray) {
            if (c != 'L') {
                switch (c) {
                    case EditorColorScheme.SIGNATURE_TEXT_NORMAL /* 58 */:
                    case EditorColorScheme.SIGNATURE_TEXT_HIGHLIGHTED_PARAMETER /* 59 */:
                    case EditorColorScheme.SIGNATURE_BACKGROUND /* 60 */:
                        if (sb2 != null) {
                            String string = sb2.toString();
                            int iLastIndexOf = string.lastIndexOf("/");
                            if (iLastIndexOf != -1) {
                                strSubstring = string.substring(0, iLastIndexOf);
                                string = string.substring(iLastIndexOf + 1);
                            } else {
                                strSubstring = "";
                            }
                            parseResult.types.add(strSubstring);
                            parseResult.types.add(string);
                        }
                        sb.append(c);
                        sb2 = null;
                        break;
                    default:
                        if (sb2 == null) {
                            sb.append(c);
                        } else {
                            sb2.append(c);
                        }
                        break;
                }
            } else if (sb2 == null) {
                sb2 = new StringBuilder();
                sb.append(c);
            } else {
                sb2.append(c);
            }
        }
        parseResult.formatted = sb.toString();
        return parseResult;
    }

    public static String reconstruct(String str, List<String> list) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (char c : charArray) {
            sb.append(c);
            if (c == 'L') {
                String str2 = list.get(i);
                if (!str2.isEmpty()) {
                    sb.append(str2);
                    sb.append("/");
                }
                sb.append(list.get(i + 1));
                i += 2;
            }
        }
        return sb.toString();
    }
}

package io.github.rosemoe.sora.util.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RegexBackrefParser {
    private final RegexBackrefGrammar grammar;

    public RegexBackrefParser(RegexBackrefGrammar regexBackrefGrammar) {
        Objects.requireNonNull(regexBackrefGrammar, "grammar can not be null");
        this.grammar = regexBackrefGrammar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v8 */
    public List<RegexBackrefToken> parse(String str, int i) {
        boolean z;
        ?? r10;
        ?? r11;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        boolean z2 = false;
        sb.append((char) 0);
        String string = sb.toString();
        ArrayList arrayList = new ArrayList();
        RegexBackrefGrammar regexBackrefGrammar = this.grammar;
        char c = regexBackrefGrammar.escapeChar;
        char c2 = regexBackrefGrammar.backrefStartChar;
        int length = string.length();
        long j = 0;
        int i2 = 0;
        ?? r12 = 0;
        int i3 = 0;
        while (i2 < length) {
            char cCharAt = string.charAt(i2);
            if (r12 == 0) {
                z = false;
                if (cCharAt == c) {
                    r10 = r12;
                    arrayList.add(new RegexBackrefToken(false, string.substring(i3, i2), -1));
                    r10 = 1;
                } else if (cCharAt == c2) {
                    arrayList.add(new RegexBackrefToken(false, string.substring(i3, i2), -1));
                    r10 = 2;
                }
            } else if (r12 != 1) {
                if (r12 == 2) {
                    if (cCharAt >= '0' && cCharAt <= '9') {
                        j = cCharAt - '0';
                        if (j <= i) {
                            r11 = 3;
                        }
                    }
                    int i4 = i2 - 1;
                    i2--;
                    i3 = i4;
                    r11 = 0;
                } else if (r12 == 3) {
                    if (cCharAt >= '0' && cCharAt <= '9') {
                        long j2 = (10 * j) + ((long) (cCharAt - '0'));
                        if (j2 <= i) {
                            j = j2;
                            r11 = r12;
                        }
                    }
                    arrayList.add(new RegexBackrefToken(true, null, (int) j));
                    i3 = i2;
                    r11 = 0;
                }
                z = false;
                r10 = r11;
            } else {
                if (cCharAt == c || cCharAt == c2) {
                    z = false;
                    arrayList.add(new RegexBackrefToken(false, String.valueOf(cCharAt), -1));
                } else {
                    z = false;
                    arrayList.add(new RegexBackrefToken(false, string.substring(i2 - 1, i2 + 1), -1));
                }
                i3 = i2 + 1;
                r10 = z;
            }
            i2++;
            z2 = z;
            r12 = r10;
        }
        boolean z3 = z2;
        if (r12 == 0) {
            arrayList.add(new RegexBackrefToken(z3, string.substring(i3, length - 1), -1));
            return arrayList;
        }
        w01.a("illegal backref expression");
        return null;
    }
}

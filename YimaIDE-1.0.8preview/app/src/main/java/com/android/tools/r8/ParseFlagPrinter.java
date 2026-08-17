package com.android.tools.r8;

import com.android.tools.r8.internal.Wf0;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ParseFlagPrinter {
    static final /* synthetic */ boolean g = true;
    private final ArrayList a = new ArrayList();
    private String b = "  ";
    private int c = 25;
    private String d = " # ";
    private StringBuilder e = null;
    private int f = -1;

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (b()) {
            if (!g && !b()) {
                x1f.a();
                return;
            } else {
                this.e.append(Wf0.c);
                this.f = -1;
            }
        }
        if (!g && b()) {
            x1f.a();
            return;
        }
        this.f = 0;
        c(this.b);
        c(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (this.f > this.c) {
            if (!g && !b()) {
                x1f.a();
                return;
            } else {
                this.e.append(Wf0.c);
                this.f = -1;
            }
        }
        if (!b()) {
            if (!g && b()) {
                x1f.a();
                return;
            } else {
                this.f = 0;
                c(this.b);
            }
        }
        int i = this.c - this.f;
        if (!g && !b()) {
            x1f.a();
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.e.append(' ');
        }
        this.f += i;
        c(this.d);
        c(str);
        if (g || b()) {
            this.e.append(Wf0.c);
            this.f = -1;
        } else {
            x1f.a();
        }
    }

    private void c(String str) {
        if (!g && !b()) {
            x1f.a();
            return;
        }
        this.e.append(str);
        this.f = str.length() + this.f;
    }

    public static void main(String[] strArr) {
        D8.main(new String[]{"--help"});
    }

    public ParseFlagPrinter addFlags(List<ParseFlagInfo> list) {
        this.a.addAll(list);
        return this;
    }

    public void appendLinesToBuilder(StringBuilder sb) {
        boolean z = g;
        if (!z && this.e != null) {
            x1f.a();
            return;
        }
        if (!z && this.f != -1) {
            x1f.a();
            return;
        }
        this.e = sb;
        a();
        this.e = null;
        this.f = -1;
    }

    public ParseFlagPrinter setHelpColumn(int i) {
        this.c = i;
        return this;
    }

    public ParseFlagPrinter setHelpSeparator(String str) {
        this.d = str;
        return this;
    }

    public ParseFlagPrinter setIndent(int i) {
        return setPrefix(" ".repeat(i));
    }

    public ParseFlagPrinter setPrefix(String str) {
        this.b = str;
        return this;
    }

    private void a() {
        for (ParseFlagInfo parseFlagInfo : this.a) {
            a(parseFlagInfo.getFlagFormat());
            parseFlagInfo.getFlagFormatAlternatives().forEach(new Consumer() { // from class: tya
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((String) obj);
                }
            });
            parseFlagInfo.getFlagHelp().forEach(new Consumer() { // from class: uya
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b((String) obj);
                }
            });
        }
    }

    private boolean b() {
        return this.f >= 0;
    }
}

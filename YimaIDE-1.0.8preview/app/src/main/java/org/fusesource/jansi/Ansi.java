package org.fusesource.jansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.eclipse.jdt.internal.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Ansi implements Appendable {
    private static final char FIRST_ESC_CHAR = 27;
    private static final char SECOND_ESC_CHAR = '[';
    private final ArrayList<Integer> attributeOptions;
    private final StringBuilder builder;
    public static final String DISABLE = Ansi.class.getName().concat(".disable");
    private static Callable<Boolean> detector = new Callable<Boolean>() { // from class: org.fusesource.jansi.Ansi.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            return Boolean.valueOf(!Boolean.getBoolean(Ansi.DISABLE));
        }
    };
    private static final InheritableThreadLocal<Boolean> holder = new InheritableThreadLocal<Boolean>() { // from class: org.fusesource.jansi.Ansi.2
        @Override // java.lang.ThreadLocal
        public Boolean initialValue() {
            return Boolean.valueOf(Ansi.isDetected());
        }
    };

    public enum Attribute {
        RESET(0, "RESET"),
        INTENSITY_BOLD(1, "INTENSITY_BOLD"),
        INTENSITY_FAINT(2, "INTENSITY_FAINT"),
        ITALIC(3, "ITALIC_ON"),
        UNDERLINE(4, "UNDERLINE_ON"),
        BLINK_SLOW(5, "BLINK_SLOW"),
        BLINK_FAST(6, "BLINK_FAST"),
        NEGATIVE_ON(7, "NEGATIVE_ON"),
        CONCEAL_ON(8, "CONCEAL_ON"),
        STRIKETHROUGH_ON(9, "STRIKETHROUGH_ON"),
        UNDERLINE_DOUBLE(21, "UNDERLINE_DOUBLE"),
        INTENSITY_BOLD_OFF(22, "INTENSITY_BOLD_OFF"),
        ITALIC_OFF(23, "ITALIC_OFF"),
        UNDERLINE_OFF(24, "UNDERLINE_OFF"),
        BLINK_OFF(25, "BLINK_OFF"),
        NEGATIVE_OFF(27, "NEGATIVE_OFF"),
        CONCEAL_OFF(28, "CONCEAL_OFF"),
        STRIKETHROUGH_OFF(29, "STRIKETHROUGH_OFF");

        private final String name;
        private final int value;

        Attribute(int i, String str) {
            this.value = i;
            this.name = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }

        public int value() {
            return this.value;
        }
    }

    public enum Color {
        BLACK(0, "BLACK"),
        RED(1, "RED"),
        GREEN(2, "GREEN"),
        YELLOW(3, "YELLOW"),
        BLUE(4, "BLUE"),
        MAGENTA(5, "MAGENTA"),
        CYAN(6, "CYAN"),
        WHITE(7, "WHITE"),
        DEFAULT(9, "DEFAULT");

        private final String name;
        private final int value;

        Color(int i, String str) {
            this.value = i;
            this.name = str;
        }

        public int bg() {
            return this.value + 40;
        }

        public int bgBright() {
            return this.value + 100;
        }

        public int fg() {
            return this.value + 30;
        }

        public int fgBright() {
            return this.value + 90;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }

        public int value() {
            return this.value;
        }
    }

    public interface Consumer {
        void apply(Ansi ansi);
    }

    public enum Erase {
        FORWARD(0, "FORWARD"),
        BACKWARD(1, "BACKWARD"),
        ALL(2, "ALL");

        private final String name;
        private final int value;

        Erase(int i, String str) {
            this.value = i;
            this.name = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }

        public int value() {
            return this.value;
        }
    }

    public Ansi(Ansi ansi) {
        this(new StringBuilder(ansi.builder));
        this.attributeOptions.addAll(ansi.attributeOptions);
    }

    private Ansi _appendEscapeSequence(char c, Object... objArr) {
        this.builder.append(FIRST_ESC_CHAR);
        this.builder.append('[');
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                this.builder.append(';');
            }
            Object obj = objArr[i];
            if (obj != null) {
                this.builder.append(obj);
            }
        }
        this.builder.append(c);
        return this;
    }

    public static Ansi ansi() {
        return isEnabled() ? new Ansi() : new NoAnsi();
    }

    private Ansi appendEscapeSequence(char c, int i) {
        flushAttributes();
        this.builder.append(FIRST_ESC_CHAR);
        this.builder.append('[');
        this.builder.append(i);
        this.builder.append(c);
        return this;
    }

    private void flushAttributes() {
        if (this.attributeOptions.isEmpty()) {
            return;
        }
        if (this.attributeOptions.size() == 1 && this.attributeOptions.get(0).intValue() == 0) {
            this.builder.append(FIRST_ESC_CHAR);
            this.builder.append('[');
            this.builder.append('m');
        } else {
            _appendEscapeSequence('m', this.attributeOptions.toArray());
        }
        this.attributeOptions.clear();
    }

    public static boolean isDetected() {
        try {
            return detector.call().booleanValue();
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean isEnabled() {
        return holder.get().booleanValue();
    }

    public static void setDetector(Callable<Boolean> callable) {
        if (callable != null) {
            detector = callable;
        } else {
            j2d.a();
        }
    }

    public static void setEnabled(boolean z) {
        holder.set(Boolean.valueOf(z));
    }

    public Ansi a(Attribute attribute) {
        this.attributeOptions.add(Integer.valueOf(attribute.value()));
        return this;
    }

    @Override // java.lang.Appendable
    public Ansi append(CharSequence charSequence) {
        this.builder.append(charSequence);
        return this;
    }

    public Ansi apply(Consumer consumer) {
        consumer.apply(this);
        return this;
    }

    public Ansi bg(int i) {
        this.attributeOptions.add(48);
        this.attributeOptions.add(5);
        this.attributeOptions.add(Integer.valueOf(i & 255));
        return this;
    }

    public Ansi bgBright(Color color) {
        this.attributeOptions.add(Integer.valueOf(color.bgBright()));
        return this;
    }

    public Ansi bgBrightCyan() {
        return bgBright(Color.CYAN);
    }

    public Ansi bgBrightDefault() {
        return bgBright(Color.DEFAULT);
    }

    public Ansi bgBrightGreen() {
        return bgBright(Color.GREEN);
    }

    public Ansi bgBrightMagenta() {
        return bgBright(Color.MAGENTA);
    }

    public Ansi bgBrightRed() {
        return bgBright(Color.RED);
    }

    public Ansi bgBrightYellow() {
        return bgBright(Color.YELLOW);
    }

    public Ansi bgCyan() {
        return bg(Color.CYAN);
    }

    public Ansi bgDefault() {
        return bg(Color.DEFAULT);
    }

    public Ansi bgGreen() {
        return bg(Color.GREEN);
    }

    public Ansi bgMagenta() {
        return bg(Color.MAGENTA);
    }

    public Ansi bgRed() {
        return bg(Color.RED);
    }

    public Ansi bgRgb(int i, int i2, int i3) {
        this.attributeOptions.add(48);
        this.attributeOptions.add(2);
        this.attributeOptions.add(Integer.valueOf(i & 255));
        this.attributeOptions.add(Integer.valueOf(i2 & 255));
        this.attributeOptions.add(Integer.valueOf(i3 & 255));
        return this;
    }

    public Ansi bgYellow() {
        return bg(Color.YELLOW);
    }

    public Ansi bold() {
        return a(Attribute.INTENSITY_BOLD);
    }

    public Ansi boldOff() {
        return a(Attribute.INTENSITY_BOLD_OFF);
    }

    public Ansi cursor(int i, int i2) {
        return appendEscapeSequence('H', Integer.valueOf(Math.max(1, i)), Integer.valueOf(Math.max(1, i2)));
    }

    public Ansi cursorDown(int i) {
        if (i > 0) {
            return appendEscapeSequence(Util.C_BYTE, i);
        }
        return i < 0 ? cursorUp(-i) : this;
    }

    public Ansi cursorDownLine(int i) {
        return i < 0 ? cursorUpLine(-i) : appendEscapeSequence('E', i);
    }

    public Ansi cursorLeft(int i) {
        if (i > 0) {
            return appendEscapeSequence(Util.C_DOUBLE, i);
        }
        return i < 0 ? cursorRight(-i) : this;
    }

    public Ansi cursorMove(int i, int i2) {
        return cursorRight(i).cursorDown(i2);
    }

    public Ansi cursorRight(int i) {
        if (i > 0) {
            return appendEscapeSequence(Util.C_CHAR, i);
        }
        return i < 0 ? cursorLeft(-i) : this;
    }

    public Ansi cursorToColumn(int i) {
        return appendEscapeSequence('G', Math.max(1, i));
    }

    public Ansi cursorUp(int i) {
        if (i > 0) {
            return appendEscapeSequence('A', i);
        }
        return i < 0 ? cursorDown(-i) : this;
    }

    public Ansi cursorUpLine(int i) {
        return i < 0 ? cursorDownLine(-i) : appendEscapeSequence(Util.C_FLOAT, i);
    }

    public Ansi eraseLine(Erase erase) {
        return appendEscapeSequence('K', erase.value());
    }

    public Ansi eraseScreen() {
        return appendEscapeSequence(Util.C_LONG, Erase.ALL.value());
    }

    public Ansi fg(int i) {
        this.attributeOptions.add(38);
        this.attributeOptions.add(5);
        this.attributeOptions.add(Integer.valueOf(i & 255));
        return this;
    }

    public Ansi fgBlack() {
        return fg(Color.BLACK);
    }

    public Ansi fgBlue() {
        return fg(Color.BLUE);
    }

    public Ansi fgBright(Color color) {
        this.attributeOptions.add(Integer.valueOf(color.fgBright()));
        return this;
    }

    public Ansi fgBrightBlack() {
        return fgBright(Color.BLACK);
    }

    public Ansi fgBrightBlue() {
        return fgBright(Color.BLUE);
    }

    public Ansi fgBrightCyan() {
        return fgBright(Color.CYAN);
    }

    public Ansi fgBrightDefault() {
        return fgBright(Color.DEFAULT);
    }

    public Ansi fgBrightGreen() {
        return fgBright(Color.GREEN);
    }

    public Ansi fgBrightMagenta() {
        return fgBright(Color.MAGENTA);
    }

    public Ansi fgBrightRed() {
        return fgBright(Color.RED);
    }

    public Ansi fgBrightYellow() {
        return fgBright(Color.YELLOW);
    }

    public Ansi fgCyan() {
        return fg(Color.CYAN);
    }

    public Ansi fgDefault() {
        return fg(Color.DEFAULT);
    }

    public Ansi fgGreen() {
        return fg(Color.GREEN);
    }

    public Ansi fgMagenta() {
        return fg(Color.MAGENTA);
    }

    public Ansi fgRed() {
        return fg(Color.RED);
    }

    public Ansi fgRgb(int i, int i2, int i3) {
        this.attributeOptions.add(38);
        this.attributeOptions.add(2);
        this.attributeOptions.add(Integer.valueOf(i & 255));
        this.attributeOptions.add(Integer.valueOf(i2 & 255));
        this.attributeOptions.add(Integer.valueOf(i3 & 255));
        return this;
    }

    public Ansi fgYellow() {
        return fg(Color.YELLOW);
    }

    public Ansi format(String str, Object... objArr) {
        flushAttributes();
        this.builder.append(String.format(str, objArr));
        return this;
    }

    public Ansi newline() {
        flushAttributes();
        this.builder.append(System.getProperty("line.separator"));
        return this;
    }

    public Ansi render(String str, Object... objArr) {
        a(String.format(AnsiRenderer.render(str), objArr));
        return this;
    }

    public Ansi reset() {
        return a(Attribute.RESET);
    }

    @Deprecated
    public Ansi restorCursorPosition() {
        return appendEscapeSequence('u');
    }

    public Ansi restoreCursorPosition() {
        return appendEscapeSequence('u');
    }

    public Ansi saveCursorPosition() {
        return appendEscapeSequence('s');
    }

    public Ansi scrollDown(int i) {
        if (i > 0) {
            return appendEscapeSequence(Util.C_TYPE_VARIABLE, i);
        }
        return i < 0 ? scrollUp(-i) : this;
    }

    public Ansi scrollUp(int i) {
        if (i > 0) {
            return appendEscapeSequence(Util.C_SHORT, i);
        }
        return i < 0 ? scrollDown(-i) : this;
    }

    public String toString() {
        flushAttributes();
        return this.builder.toString();
    }

    public static class NoAnsi extends Ansi {
        public NoAnsi() {
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi a(Attribute attribute) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi, java.lang.Appendable
        public /* bridge */ /* synthetic */ Appendable append(char c) throws IOException {
            return super.append(c);
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi bg(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi bg(Color color) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi bgBright(Color color) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi bgRgb(int i, int i2, int i3) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursor(int i, int i2) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorDown(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorDownLine() {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorDownLine(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorLeft(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorRight(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorToColumn(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorUp(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorUpLine() {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi cursorUpLine(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi eraseLine() {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi eraseLine(Erase erase) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi eraseScreen() {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi eraseScreen(Erase erase) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi fg(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi fg(Color color) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi fgBright(Color color) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi fgRgb(int i, int i2, int i3) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi reset() {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        @Deprecated
        public Ansi restorCursorPosition() {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi restoreCursorPosition() {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi saveCursorPosition() {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi scrollDown(int i) {
            return this;
        }

        @Override // org.fusesource.jansi.Ansi
        public Ansi scrollUp(int i) {
            return this;
        }

        public NoAnsi(int i) {
            super(i);
        }

        public NoAnsi(StringBuilder sb) {
            super(sb);
        }

        @Override // org.fusesource.jansi.Ansi, java.lang.Appendable
        public /* bridge */ /* synthetic */ Appendable append(CharSequence charSequence) throws IOException {
            return super.append(charSequence);
        }

        @Override // org.fusesource.jansi.Ansi, java.lang.Appendable
        public /* bridge */ /* synthetic */ Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
            return super.append(charSequence, i, i2);
        }
    }

    @Override // java.lang.Appendable
    public Ansi append(CharSequence charSequence, int i, int i2) {
        this.builder.append(charSequence, i, i2);
        return this;
    }

    @Override // java.lang.Appendable
    public Ansi append(char c) {
        this.builder.append(c);
        return this;
    }

    public Ansi eraseLine() {
        return appendEscapeSequence('K');
    }

    public Ansi render(String str) {
        a(AnsiRenderer.render(str));
        return this;
    }

    public Ansi eraseScreen(Erase erase) {
        return appendEscapeSequence(Util.C_LONG, erase.value());
    }

    public Ansi a(String str) {
        flushAttributes();
        this.builder.append(str);
        return this;
    }

    public Ansi cursorDownLine() {
        return appendEscapeSequence('E');
    }

    public Ansi cursorUpLine() {
        return appendEscapeSequence(Util.C_FLOAT);
    }

    public Ansi a(boolean z) {
        flushAttributes();
        this.builder.append(z);
        return this;
    }

    public Ansi() {
        this(new StringBuilder(80));
    }

    public static Ansi ansi(StringBuilder sb) {
        if (isEnabled()) {
            return new Ansi(sb);
        }
        return new NoAnsi(sb);
    }

    public Ansi a(char c) {
        flushAttributes();
        this.builder.append(c);
        return this;
    }

    public Ansi(int i) {
        this(new StringBuilder(i));
    }

    public Ansi(StringBuilder sb) {
        this.attributeOptions = new ArrayList<>(5);
        this.builder = sb;
    }

    public Ansi a(char[] cArr, int i, int i2) {
        flushAttributes();
        this.builder.append(cArr, i, i2);
        return this;
    }

    public static Ansi ansi(int i) {
        if (isEnabled()) {
            return new Ansi(i);
        }
        return new NoAnsi(i);
    }

    public Ansi a(char[] cArr) {
        flushAttributes();
        this.builder.append(cArr);
        return this;
    }

    public Ansi a(CharSequence charSequence, int i, int i2) {
        flushAttributes();
        this.builder.append(charSequence, i, i2);
        return this;
    }

    public Ansi a(CharSequence charSequence) {
        flushAttributes();
        this.builder.append(charSequence);
        return this;
    }

    private Ansi appendEscapeSequence(char c) {
        flushAttributes();
        this.builder.append(FIRST_ESC_CHAR);
        this.builder.append('[');
        this.builder.append(c);
        return this;
    }

    public Ansi a(double d) {
        flushAttributes();
        this.builder.append(d);
        return this;
    }

    public Ansi a(float f) {
        flushAttributes();
        this.builder.append(f);
        return this;
    }

    private Ansi appendEscapeSequence(char c, Object... objArr) {
        flushAttributes();
        return _appendEscapeSequence(c, objArr);
    }

    public Ansi a(int i) {
        flushAttributes();
        this.builder.append(i);
        return this;
    }

    public Ansi bg(Color color) {
        this.attributeOptions.add(Integer.valueOf(color.bg()));
        return this;
    }

    public Ansi fg(Color color) {
        this.attributeOptions.add(Integer.valueOf(color.fg()));
        return this;
    }

    public Ansi a(long j) {
        flushAttributes();
        this.builder.append(j);
        return this;
    }

    public Ansi a(Object obj) {
        flushAttributes();
        this.builder.append(obj);
        return this;
    }

    public Ansi a(StringBuffer stringBuffer) {
        flushAttributes();
        this.builder.append(stringBuffer);
        return this;
    }

    public Ansi bgRgb(int i) {
        return bgRgb(i >> 16, i >> 8, i);
    }

    public Ansi fgRgb(int i) {
        return fgRgb(i >> 16, i >> 8, i);
    }
}

package org.jetbrains.kotlin.js.util;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class TextOutputImpl implements TextOutput {
    private boolean justNewlined;
    private int identLevel = 0;
    private char[][] indents = {new char[0]};
    private int position = 0;
    private int line = 0;
    private int column = 0;
    private final StringBuilder out = new StringBuilder();

    private void movePosition(int i) {
        this.position += i;
        this.column += i;
    }

    private void printAndCount(CharSequence charSequence) {
        this.position += charSequence.length();
        this.column += charSequence.length();
        this.out.append(charSequence);
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public int getColumn() {
        return this.column;
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public int getLine() {
        return this.line;
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public int getPosition() {
        return this.position;
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void indentIn() {
        int i = this.identLevel + 1;
        this.identLevel = i;
        if (i >= this.indents.length) {
            char[] cArr = new char[i * 2];
            Arrays.fill(cArr, ' ');
            char[][] cArr2 = this.indents;
            char[][] cArr3 = new char[cArr2.length + 1][];
            System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
            cArr3[this.identLevel] = cArr;
            this.indents = cArr3;
        }
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void indentOut() {
        this.identLevel--;
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void maybeIndent() {
        if (this.justNewlined) {
            printAndCount(this.indents[this.identLevel]);
            this.justNewlined = false;
        }
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void newline() {
        this.out.append('\n');
        this.position++;
        this.line++;
        this.column = 0;
        this.justNewlined = true;
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void print(double d) {
        maybeIndent();
        int length = this.out.length();
        this.out.append(d);
        movePosition(this.out.length() - length);
    }

    public String toString() {
        return this.out.toString();
    }

    private void printAndCount(char[] cArr) {
        this.position += cArr.length;
        this.column += cArr.length;
        this.out.append(cArr);
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void print(int i) {
        maybeIndent();
        int length = this.out.length();
        this.out.append(i);
        movePosition(this.out.length() - length);
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void print(char c) {
        maybeIndent();
        this.out.append(c);
        movePosition(1);
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void print(char[] cArr) {
        maybeIndent();
        printAndCount(cArr);
    }

    @Override // org.jetbrains.kotlin.js.util.TextOutput
    public void print(CharSequence charSequence) {
        maybeIndent();
        printAndCount(charSequence);
    }
}

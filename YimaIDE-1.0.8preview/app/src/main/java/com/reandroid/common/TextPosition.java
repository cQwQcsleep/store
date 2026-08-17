package com.reandroid.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TextPosition {
    private int columnNumber;
    private String description;
    private int lineNumber;

    public TextPosition(int i, int i2) {
        this.lineNumber = i;
        this.columnNumber = i2;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public void setColumnNumber(int i) {
        this.columnNumber = i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setLineNumber(int i) {
        this.lineNumber = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int lineNumber = getLineNumber();
        if (lineNumber == -1) {
            sb.append("--");
        } else {
            sb.append(" line=");
            sb.append(lineNumber);
        }
        int columnNumber = getColumnNumber();
        if (columnNumber == -1) {
            sb.append("--");
        } else {
            sb.append(" column=");
            sb.append(columnNumber);
        }
        sb.append(']');
        String description = getDescription();
        if (description != null) {
            sb.append(' ');
            sb.append(description);
        }
        return sb.toString();
    }

    public TextPosition() {
        this(-1, -1);
    }
}

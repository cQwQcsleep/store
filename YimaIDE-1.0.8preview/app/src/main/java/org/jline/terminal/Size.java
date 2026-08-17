package org.jline.terminal;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class Size {
    private int cols;
    private int rows;

    public void copy(Size size) {
        setColumns(size.getColumns());
        setRows(size.getRows());
    }

    public int cursorPos(int i, int i2) {
        return (i * (this.cols + 1)) + i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.rows == size.rows && this.cols == size.cols) {
                return true;
            }
        }
        return false;
    }

    public int getColumns() {
        return this.cols;
    }

    public int getRows() {
        return this.rows;
    }

    public int hashCode() {
        return (this.rows * 31) + this.cols;
    }

    public void setColumns(int i) {
        this.cols = (short) i;
    }

    public void setRows(int i) {
        this.rows = (short) i;
    }

    public String toString() {
        return "Size[cols=" + this.cols + ", rows=" + this.rows + ']';
    }
}

package io.github.rosemoe.sora.text;

import io.github.rosemoe.sora.util.IntPair;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class CharPosition {
    public int column;
    public int index;
    public int line;

    public CharPosition(int i, int i2, int i3) {
        this.index = i3;
        this.line = i;
        this.column = i2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CharPosition) {
            CharPosition charPosition = (CharPosition) obj;
            if (charPosition.column == this.column && charPosition.line == this.line && charPosition.index == this.index) {
                return true;
            }
        }
        return false;
    }

    public CharPosition fromThis() {
        CharPosition charPosition = new CharPosition();
        charPosition.set(this);
        return charPosition;
    }

    public int getColumn() {
        return this.column;
    }

    public int getIndex() {
        return this.index;
    }

    public int getLine() {
        return this.line;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.index), Integer.valueOf(this.line), Integer.valueOf(this.column));
    }

    public void set(CharPosition charPosition) {
        this.index = charPosition.index;
        this.line = charPosition.line;
        this.column = charPosition.column;
    }

    public CharPosition toBOF() {
        this.column = 0;
        this.line = 0;
        this.index = 0;
        return this;
    }

    public long toIntPair() {
        return IntPair.pack(this.line, this.column);
    }

    public String toString() {
        return "CharPosition(line = " + this.line + ",column = " + this.column + ",index = " + this.index + ")";
    }

    public CharPosition(int i, int i2) {
        this(i, i2, -1);
    }

    public CharPosition() {
    }
}

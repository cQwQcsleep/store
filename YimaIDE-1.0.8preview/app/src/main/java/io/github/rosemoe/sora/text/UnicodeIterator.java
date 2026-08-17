package io.github.rosemoe.sora.text;

import io.github.rosemoe.sora.util.ObjectPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class UnicodeIterator {
    private static final ObjectPool<UnicodeIterator> sPool = new ObjectPool<UnicodeIterator>() { // from class: io.github.rosemoe.sora.text.UnicodeIterator.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.github.rosemoe.sora.util.ObjectPool
        public UnicodeIterator allocateNew() {
            return new UnicodeIterator();
        }

        @Override // io.github.rosemoe.sora.util.ObjectPool
        public void onRecycleObject(UnicodeIterator unicodeIterator) {
            unicodeIterator.text = null;
        }
    };
    private int codePoint;
    private int end;
    private int limit;
    private int start;
    private CharSequence text;

    public static UnicodeIterator obtain(CharSequence charSequence, int i, int i2) {
        UnicodeIterator unicodeIteratorObtain = sPool.obtain();
        unicodeIteratorObtain.set(charSequence, i, i2);
        return unicodeIteratorObtain;
    }

    public int getCodePoint() {
        return this.codePoint;
    }

    public int getEndIndex() {
        return this.end;
    }

    public int getStartIndex() {
        return this.start;
    }

    public boolean hasNext() {
        return this.end < this.limit;
    }

    public int nextCodePoint() {
        int i;
        int i2 = this.end;
        this.start = i2;
        if (i2 >= this.limit) {
            this.codePoint = 0;
        } else {
            this.end = i2 + 1;
            char cCharAt = this.text.charAt(i2);
            if (!Character.isHighSurrogate(cCharAt) || (i = this.end) >= this.limit) {
                this.codePoint = cCharAt;
            } else {
                this.codePoint = Character.toCodePoint(cCharAt, this.text.charAt(i));
                this.end++;
            }
        }
        return this.codePoint;
    }

    public void recycle() {
        sPool.recycle(this);
    }

    public void set(CharSequence charSequence, int i, int i2) {
        if ((i | i2 | (i2 - i) | (charSequence.length() - i2)) < 0) {
            qc6.a();
            return;
        }
        this.text = charSequence;
        this.end = i;
        this.start = i;
        this.limit = i2;
    }

    private UnicodeIterator() {
    }
}

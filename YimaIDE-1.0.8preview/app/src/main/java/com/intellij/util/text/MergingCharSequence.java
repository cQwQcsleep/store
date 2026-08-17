package com.intellij.util.text;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class MergingCharSequence implements CharSequence {
    private final CharSequence s1;
    private final CharSequence s2;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "s2";
        } else if (i != 2) {
            objArr[0] = "s1";
        } else {
            objArr[0] = "com/intellij/util/text/MergingCharSequence";
        }
        if (i != 2) {
            objArr[1] = "com/intellij/util/text/MergingCharSequence";
        } else {
            objArr[1] = "toString";
        }
        if (i != 2) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    public MergingCharSequence(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null) {
            $$$reportNull$$$0(0);
        }
        if (charSequence2 == null) {
            $$$reportNull$$$0(1);
        }
        this.s1 = charSequence;
        this.s2 = charSequence2;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return i < this.s1.length() ? this.s1.charAt(i) : this.s2.charAt(i - this.s1.length());
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.s1.length() + this.s2.length();
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        if (i == 0 && i2 == length()) {
            return this;
        }
        if (i < this.s1.length() && i2 < this.s1.length()) {
            return this.s1.subSequence(i, i2);
        }
        if (i >= this.s1.length() && i2 >= this.s1.length()) {
            return this.s2.subSequence(i - this.s1.length(), i2 - this.s1.length());
        }
        CharSequence charSequence = this.s1;
        return new MergingCharSequence(charSequence.subSequence(i, charSequence.length()), this.s2.subSequence(0, i2 - this.s1.length()));
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return ((Object) this.s1) + this.s2.toString();
    }
}

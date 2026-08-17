package com.intellij.util.text;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class ImmutableCharSequence implements CharSequence {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 3 ? 3 : 2];
        if (i == 2) {
            objArr[0] = "seq";
        } else if (i != 3) {
            objArr[0] = "cs";
        } else {
            objArr[0] = "com/intellij/util/text/ImmutableCharSequence";
        }
        if (i != 3) {
            objArr[1] = "com/intellij/util/text/ImmutableCharSequence";
        } else {
            objArr[1] = "replace";
        }
        if (i == 1) {
            objArr[2] = "isImmutable";
        } else if (i == 2) {
            objArr[2] = "replace";
        } else if (i != 3) {
            objArr[2] = "asImmutable";
        }
        String str2 = String.format(str, objArr);
        if (i == 3) {
            throw new IllegalStateException(str2);
        }
    }

    public static CharSequence asImmutable(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(0);
        }
        return isImmutable(charSequence) ? charSequence : charSequence.toString();
    }

    private static boolean isImmutable(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(1);
        }
        return (charSequence instanceof ImmutableCharSequence) || ((charSequence instanceof CharSequenceSubSequence) && isImmutable(((CharSequenceSubSequence) charSequence).getBaseSequence()));
    }

    public abstract ImmutableCharSequence delete(int i, int i2);

    public abstract ImmutableCharSequence insert(int i, CharSequence charSequence);

    public ImmutableCharSequence replace(int i, int i2, CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(2);
        }
        ImmutableCharSequence immutableCharSequenceInsert = delete(i, i2).insert(i, charSequence);
        if (immutableCharSequenceInsert == null) {
            $$$reportNull$$$0(3);
        }
        return immutableCharSequenceInsert;
    }

    public abstract ImmutableCharSequence subtext(int i, int i2);

    @Override // java.lang.CharSequence
    public abstract String toString();
}

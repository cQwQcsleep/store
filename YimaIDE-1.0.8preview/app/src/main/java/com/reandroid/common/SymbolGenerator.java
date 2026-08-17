package com.reandroid.common;

import com.intellij.psi.PsiKeyword;
import com.reandroid.utils.collection.CollectionUtil;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SymbolGenerator {
    private final char[] characters;
    private final int lowercaseAndSuffix;
    private final Set<String> reservedNames;
    private final int suffixLength;
    private final int totalCharacters;

    public SymbolGenerator(char[] cArr, int i, int i2, Set<String> set) {
        this.characters = cArr;
        this.totalCharacters = cArr.length;
        this.lowercaseAndSuffix = i + i2;
        this.suffixLength = i2;
        this.reservedNames = set;
    }

    private static Set<String> loadDefaultReservedNames() {
        return CollectionUtil.newHashSet("boolean", "byte", PsiKeyword.CHAR, "double", "float", "int", "long", "short", PsiKeyword.VOID, "it", "by", "class");
    }

    public void addReserved(String str) {
        this.reservedNames.add(str);
    }

    public void clearReserved() {
        this.reservedNames.clear();
    }

    public String generate(int i, boolean z) {
        char[] cArr;
        int i2 = i + 1;
        int i3 = z ? this.totalCharacters : this.lowercaseAndSuffix;
        int i4 = i3 - this.suffixLength;
        int i5 = i2;
        int i6 = 1;
        for (int i7 = i4; i5 > i7; i7 = i3) {
            i5 = (i5 - 1) / i7;
            i6++;
        }
        char[] cArr2 = new char[i6];
        int i8 = this.suffixLength;
        int i9 = i2;
        int i10 = 0;
        while (true) {
            cArr = this.characters;
            if (i9 <= i4) {
                break;
            }
            int i11 = i9 - 1;
            cArr2[i10] = cArr[(i11 % i4) + i8];
            i9 = i11 / i4;
            i4 = i3;
            i8 = 0;
            i10++;
        }
        cArr2[i10] = cArr[(i9 - 1) + i8];
        String str = new String(cArr2);
        return isReserved(str) ? generate(i2, z) : str;
    }

    public String generateLowercase(int i) {
        return generate(i, false);
    }

    public String generateMixedCase(int i) {
        return generate(i, true);
    }

    public boolean isReserved(String str) {
        return this.reservedNames.contains(str);
    }

    public boolean removeReserved(String str) {
        return this.reservedNames.remove(str);
    }

    public SymbolGenerator(char[] cArr, int i, int i2) {
        this(cArr, i, i2, loadDefaultReservedNames());
    }
}

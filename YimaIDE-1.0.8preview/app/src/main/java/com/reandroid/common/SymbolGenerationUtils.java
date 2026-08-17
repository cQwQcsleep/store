package com.reandroid.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SymbolGenerationUtils {
    private static final SymbolGenerator GENERATOR = new SymbolGenerator(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}, 26, 10);

    public static String generate(int i, boolean z) {
        return GENERATOR.generate(i, z);
    }

    public static String generateLowercase(int i) {
        return generate(i, false);
    }

    public static String generateMixedCase(int i) {
        return GENERATOR.generateMixedCase(i);
    }
}

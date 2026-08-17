package org.fusesource.jansi;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum AnsiColors {
    Colors16("16 colors"),
    Colors256("256 colors"),
    TrueColor("24-bit colors");

    private final String description;

    AnsiColors(String str) {
        this.description = str;
    }

    public String getDescription() {
        return this.description;
    }
}

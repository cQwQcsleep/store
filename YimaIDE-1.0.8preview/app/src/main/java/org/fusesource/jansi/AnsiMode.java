package org.fusesource.jansi;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum AnsiMode {
    Strip("Strip all ansi sequences"),
    Default("Print ansi sequences if the stream is a terminal"),
    Force("Always print ansi sequences, even if the stream is redirected");

    private final String description;

    AnsiMode(String str) {
        this.description = str;
    }

    public String getDescription() {
        return this.description;
    }
}

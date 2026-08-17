package org.fusesource.jansi;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum AnsiType {
    Native("Supports ansi sequences natively"),
    Unsupported("Ansi sequences are stripped out"),
    VirtualTerminal("Supported through windows virtual terminal"),
    Emulation("Emulated through using windows API console commands"),
    Redirected("The stream is redirected to a file or a pipe");

    private final String description;

    AnsiType(String str) {
        this.description = str;
    }

    public String getDescription() {
        return this.description;
    }
}

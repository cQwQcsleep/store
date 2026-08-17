package org.antlr.v4.runtime.tree.pattern;

import defpackage.w01;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class TextChunk extends Chunk {
    private final String text;

    public TextChunk(String str) {
        if (str != null) {
            this.text = str;
        } else {
            w01.a("text cannot be null");
            throw null;
        }
    }

    public final String getText() {
        return this.text;
    }

    public String toString() {
        return "'" + this.text + "'";
    }
}

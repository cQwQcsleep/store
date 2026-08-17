package org.antlr.v4.runtime.tree.pattern;

import defpackage.w01;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class TagChunk extends Chunk {
    private final String label;
    private final String tag;

    public TagChunk(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            w01.a("tag cannot be null or empty");
            throw null;
        }
        this.label = str;
        this.tag = str2;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getTag() {
        return this.tag;
    }

    public String toString() {
        if (this.label == null) {
            return this.tag;
        }
        return this.label + ":" + this.tag;
    }

    public TagChunk(String str) {
        this(null, str);
    }
}

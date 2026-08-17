package org.snakeyaml.engine.v2.common;

import java.util.Optional;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public enum ScalarStyle {
    DOUBLE_QUOTED(Optional.of('\"')),
    SINGLE_QUOTED(Optional.of('\'')),
    LITERAL(Optional.of('|')),
    FOLDED(Optional.of('>')),
    JSON_SCALAR_STYLE(Optional.of('J')),
    PLAIN(Optional.empty());

    private final Optional<Character> styleOpt;

    ScalarStyle(Optional optional) {
        this.styleOpt = optional;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.styleOpt.orElse(':'));
    }
}

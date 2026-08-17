package org.snakeyaml.engine.v2.tokens;

import java.util.Objects;
import java.util.Optional;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class TagTuple {
    private final Optional<String> handle;
    private final String suffix;

    public TagTuple(Optional<String> optional, String str) {
        Objects.requireNonNull(optional);
        this.handle = optional;
        Objects.requireNonNull(str);
        this.suffix = str;
    }

    public Optional<String> getHandle() {
        return this.handle;
    }

    public String getSuffix() {
        return this.suffix;
    }
}

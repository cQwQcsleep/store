package org.snakeyaml.engine.v2.env;

import java.util.Optional;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface EnvConfig {
    default Optional<String> getValueFor(String str, String str2, String str3, String str4) {
        return Optional.empty();
    }
}

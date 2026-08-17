package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InputDependencyGraphConsumer {
    void accept(Origin origin, Path path);

    default void acceptProguardApplyMapping(Origin origin, Path path) {
        accept(origin, path);
    }

    default void acceptProguardClassObfuscationDictionary(Origin origin, Path path) {
        accept(origin, path);
    }

    default void acceptProguardInJars(Origin origin, Path path) {
        accept(origin, path);
    }

    default void acceptProguardInclude(Origin origin, Path path) {
        accept(origin, path);
    }

    default void acceptProguardLibraryJars(Origin origin, Path path) {
        accept(origin, path);
    }

    default void acceptProguardObfuscationDictionary(Origin origin, Path path) {
        accept(origin, path);
    }

    default void acceptProguardPackageObfuscationDictionary(Origin origin, Path path) {
        accept(origin, path);
    }

    void finished();
}

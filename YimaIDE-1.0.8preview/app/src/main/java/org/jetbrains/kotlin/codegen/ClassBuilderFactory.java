package org.jetbrains.kotlin.codegen;

import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface ClassBuilderFactory {
    byte[] asBytes(ClassBuilder classBuilder);

    String asText(ClassBuilder classBuilder);

    ClassBuilderMode getClassBuilderMode();

    ClassBuilder newClassBuilder(JvmDeclarationOrigin jvmDeclarationOrigin);
}

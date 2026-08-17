package org.jetbrains.kotlin.backend.common.serialization.proto;

import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface AccessorIdSignatureOrBuilder extends MessageLiteOrBuilder {
    long getAccessorHashId();

    int getDebugInfo();

    long getFlags();

    int getName();

    int getPropertySignature();

    boolean hasAccessorHashId();

    boolean hasDebugInfo();

    boolean hasFlags();

    boolean hasName();

    boolean hasPropertySignature();
}

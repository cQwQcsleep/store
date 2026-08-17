package org.jetbrains.kotlin.backend.common.serialization.proto;

import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface FileLocalIdSignatureOrBuilder extends MessageLiteOrBuilder {
    int getContainer();

    long getLocalId();

    boolean hasContainer();

    boolean hasLocalId();
}

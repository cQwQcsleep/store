package org.jetbrains.kotlin.backend.common.serialization.proto;

import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IrClassReferenceOrBuilder extends MessageLiteOrBuilder {
    long getClassSymbol();

    int getClassType();

    boolean hasClassSymbol();

    boolean hasClassType();
}

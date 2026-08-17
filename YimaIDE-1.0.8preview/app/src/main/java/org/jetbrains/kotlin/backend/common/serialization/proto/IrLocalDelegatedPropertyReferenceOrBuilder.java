package org.jetbrains.kotlin.backend.common.serialization.proto;

import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IrLocalDelegatedPropertyReferenceOrBuilder extends MessageLiteOrBuilder {
    long getDelegate();

    long getGetter();

    int getOriginName();

    long getSetter();

    long getSymbol();

    boolean hasDelegate();

    boolean hasGetter();

    boolean hasOriginName();

    boolean hasSetter();

    boolean hasSymbol();
}

package org.jetbrains.kotlin.backend.common.serialization.proto;

import java.util.List;
import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface LocalSignatureOrBuilder extends MessageLiteOrBuilder {
    int getLocalFqName(int i);

    int getLocalFqNameCount();

    List<Integer> getLocalFqNameList();

    long getLocalHash();

    boolean hasLocalHash();
}

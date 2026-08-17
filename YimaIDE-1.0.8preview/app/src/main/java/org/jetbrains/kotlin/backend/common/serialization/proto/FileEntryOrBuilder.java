package org.jetbrains.kotlin.backend.common.serialization.proto;

import java.util.List;
import org.jetbrains.kotlin.protobuf.ByteString;
import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface FileEntryOrBuilder extends MessageLiteOrBuilder {
    int getFirstRelevantLineIndex();

    int getLineStartOffset(int i);

    int getLineStartOffsetCount();

    int getLineStartOffsetDelta(int i);

    int getLineStartOffsetDeltaCount();

    List<Integer> getLineStartOffsetDeltaList();

    List<Integer> getLineStartOffsetList();

    int getName();

    String getNameOld();

    ByteString getNameOldBytes();

    boolean hasFirstRelevantLineIndex();

    boolean hasName();

    boolean hasNameOld();
}

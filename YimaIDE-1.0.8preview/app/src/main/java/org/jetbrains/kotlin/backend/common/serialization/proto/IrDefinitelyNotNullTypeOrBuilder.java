package org.jetbrains.kotlin.backend.common.serialization.proto;

import java.util.List;
import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IrDefinitelyNotNullTypeOrBuilder extends MessageLiteOrBuilder {
    int getTypes(int i);

    int getTypesCount();

    List<Integer> getTypesList();
}

package org.jetbrains.kotlin.backend.common.serialization.proto;

import java.util.List;
import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IrMultiFieldValueClassRepresentationOrBuilder extends MessageLiteOrBuilder {
    int getUnderlyingPropertyName(int i);

    int getUnderlyingPropertyNameCount();

    List<Integer> getUnderlyingPropertyNameList();

    int getUnderlyingPropertyType(int i);

    int getUnderlyingPropertyTypeCount();

    List<Integer> getUnderlyingPropertyTypeList();
}

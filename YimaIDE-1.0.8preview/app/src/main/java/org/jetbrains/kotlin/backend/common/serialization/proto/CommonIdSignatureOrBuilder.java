package org.jetbrains.kotlin.backend.common.serialization.proto;

import java.util.List;
import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface CommonIdSignatureOrBuilder extends MessageLiteOrBuilder {
    int getDebugInfo();

    int getDeclarationFqName(int i);

    int getDeclarationFqNameCount();

    List<Integer> getDeclarationFqNameList();

    long getFlags();

    long getMemberUniqId();

    long getMemberUniqIdPre240();

    int getPackageFqName(int i);

    int getPackageFqNameCount();

    List<Integer> getPackageFqNameList();

    boolean hasDebugInfo();

    boolean hasFlags();

    boolean hasMemberUniqId();

    boolean hasMemberUniqIdPre240();
}

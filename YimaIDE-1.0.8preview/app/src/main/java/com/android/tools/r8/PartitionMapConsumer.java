package com.android.tools.r8;

import com.android.tools.r8.retrace.MappingPartition;
import com.android.tools.r8.retrace.MappingPartitionMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface PartitionMapConsumer extends I {
    void acceptMappingPartition(MappingPartition mappingPartition);

    void acceptMappingPartitionMetadata(MappingPartitionMetadata mappingPartitionMetadata);
}

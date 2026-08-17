package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.ProguardMapPartitioner;
import com.android.tools.r8.retrace.ProguardMapPartitionerBuilder;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ProguardMapPartitionerBuilder<B extends ProguardMapPartitionerBuilder<B, P>, P extends ProguardMapPartitioner> {
    P build();

    B setAllowEmptyMappedRanges(boolean z);

    B setAllowExperimentalMapping(boolean z);

    B setPartitionConsumer(Consumer<MappingPartition> consumer);

    B setProguardMapProducer(ProguardMapProducer proguardMapProducer);
}

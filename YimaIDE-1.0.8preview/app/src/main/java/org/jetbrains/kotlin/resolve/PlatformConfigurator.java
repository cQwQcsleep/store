package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.container.StorageComponentContainer;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/PlatformConfigurator;", "", "platformSpecificContainer", "Lorg/jetbrains/kotlin/container/StorageComponentContainer;", "getPlatformSpecificContainer", "()Lorg/jetbrains/kotlin/container/StorageComponentContainer;", "configureModuleComponents", "", "container", "configureModuleDependentCheckers", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface PlatformConfigurator {
    void configureModuleComponents(StorageComponentContainer container);

    void configureModuleDependentCheckers(StorageComponentContainer container);

    StorageComponentContainer getPlatformSpecificContainer();
}

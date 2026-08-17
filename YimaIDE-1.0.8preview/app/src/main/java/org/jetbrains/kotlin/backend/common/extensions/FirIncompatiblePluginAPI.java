package org.jetbrains.kotlin.backend.common.extensions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Retention(RetentionPolicy.RUNTIME)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0010\u0012\u000e\b\u0002\u0010\u0002\u001a\u00020\u0003B\u0004\b\b(\u0004R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005Ê\u0001\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/extensions/FirIncompatiblePluginAPI;", "", "hint", "", "", "()Ljava/lang/String;", "org.jetbrains.kotlin:ir.backend.common", "Lkotlin/RequiresOptIn;", "message", "This API is deprecated. It will be removed after the release of K2 compiler"}, k = 1, mv = {2, 4, 0}, xi = 48)
public @interface FirIncompatiblePluginAPI {
    String hint() default "";
}

package org.jetbrains.kotlin.resolve.jvm.extensions;

import kotlin.Metadata;
import org.jetbrains.kotlin.extensions.ProjectExtensionDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/extensions/AnalysisHandlerExtension;", "Lorg/jetbrains/kotlin/resolve/extensions/AnalysisHandlerExtension;", "Companion", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface AnalysisHandlerExtension extends org.jetbrains.kotlin.resolve.extensions.AnalysisHandlerExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/jvm/extensions/AnalysisHandlerExtension$Companion;", "Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "Lorg/jetbrains/kotlin/resolve/extensions/AnalysisHandlerExtension;", "<init>", "()V", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Companion extends ProjectExtensionDescriptor<org.jetbrains.kotlin.resolve.extensions.AnalysisHandlerExtension> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("org.jetbrains.kotlin.analyzeCompleteHandlerExtension", org.jetbrains.kotlin.resolve.extensions.AnalysisHandlerExtension.class);
        }
    }
}

package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"asRenderer", "Lorg/jetbrains/kotlin/diagnostics/rendering/SmartDescriptorRenderer;", "Lorg/jetbrains/kotlin/renderer/DescriptorRenderer;", "org.jetbrains.kotlin:frontend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RenderersKt {
    public static final SmartDescriptorRenderer asRenderer(DescriptorRenderer descriptorRenderer) {
        descriptorRenderer.getClass();
        return new SmartDescriptorRenderer(descriptorRenderer);
    }
}

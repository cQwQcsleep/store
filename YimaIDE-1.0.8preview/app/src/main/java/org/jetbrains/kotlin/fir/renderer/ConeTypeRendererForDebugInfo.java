package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001d\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u001f\b\u0016\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\f\u0010\u0012\u001a\u00020\r*\u00020\u0013H\u0014J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0014H\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRendererForDebugInfo;", "Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "renderCapturedDetails", Argument.Delimiters.none, "coneAttributeRendererForReadability", "Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer;", "<init>", "(ZLorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer;)V", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "(Ljava/lang/StringBuilder;Z)V", "render", Argument.Delimiters.none, "flexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeIntegerLiteralType;", "renderAttributes", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeIntersectionType;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ConeTypeRendererForDebugInfo extends ConeTypeRenderer {
    /* JADX WARN: Illegal instructions before constructor call */
    public ConeTypeRendererForDebugInfo(StringBuilder sb, boolean z) {
        sb.getClass();
        ConeAttributeRenderer coneAttributeRenderer = null;
        this(z, coneAttributeRenderer, 2, (DefaultConstructorMarker) coneAttributeRenderer);
        setBuilder(sb);
        setIdRenderer(new ConeFullyQualifiedIdRenderer());
        getIdRenderer().setBuilder(sb);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public void render(ConeFlexibleType flexibleType) throws UninitializedPropertyAccessException {
        flexibleType.getClass();
        getBuilder().append("(");
        ConeTypeRenderer.render$default(this, flexibleType.getLowerBound(), null, 2, null);
        getBuilder().append("..");
        ConeTypeRenderer.render$default(this, flexibleType.getUpperBound(), null, 2, null);
        getBuilder().append(")");
    }

    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public void renderAttributes(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        renderNonCompilerAttributes(coneKotlinType);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeTypeRendererForDebugInfo(boolean z, ConeAttributeRenderer coneAttributeRenderer) {
        super(coneAttributeRenderer, z);
        coneAttributeRenderer.getClass();
    }

    public /* synthetic */ ConeTypeRendererForDebugInfo(boolean z, ConeAttributeRenderer coneAttributeRenderer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? ConeAttributeRenderer.ForReadability.INSTANCE : coneAttributeRenderer);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ConeTypeRendererForDebugInfo() {
        ConeAttributeRenderer coneAttributeRenderer = null;
        this(false, coneAttributeRenderer, 3, (DefaultConstructorMarker) coneAttributeRenderer);
    }

    public /* synthetic */ ConeTypeRendererForDebugInfo(StringBuilder sb, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sb, (i & 2) != 0 ? false : z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public void render(ConeIntegerLiteralType type) throws UninitializedPropertyAccessException {
        type.getClass();
        ConeTypeRenderer.render$default(this, ConeIntegerLiteralType.getApproximatedType$default(type, null, 1, null), null, 2, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public void render(ConeIntersectionType type) throws UninitializedPropertyAccessException {
        type.getClass();
        int i = 0;
        for (ConeKotlinType coneKotlinType : type.getIntersectedTypes()) {
            int i2 = i + 1;
            if (i > 0) {
                getBuilder().append(" & ");
            }
            ConeTypeRenderer.render$default(this, coneKotlinType, null, 2, null);
            i = i2;
        }
    }
}

package androidx.compose.ui.graphics.colorspace;

import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0080\b¢\u0006\u0004\b\u000b\u0010\f\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\r"}, d2 = {"Connectors", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/graphics/colorspace/Connector;", "getConnectors", "()Landroidx/collection/MutableIntObjectMap;", "connectorKey", "", "src", "dst", "renderIntent", "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "connectorKey-YBCOT_4", "(III)I", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ConnectorKt {
    private static final MutableIntObjectMap<Connector> Connectors;

    static {
        ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
        int id = colorSpaces.getSrgb().getId();
        int id2 = colorSpaces.getSrgb().getId();
        RenderIntent.Companion companion = RenderIntent.INSTANCE;
        DefaultConstructorMarker defaultConstructorMarker = null;
        Connectors = IntObjectMapKt.mutableIntObjectMapOf(id | (id2 << 6) | (companion.m3592getPerceptualuksYyKA() << 12), Connector.INSTANCE.identity$ui_graphics(colorSpaces.getSrgb()), colorSpaces.getSrgb().getId() | (colorSpaces.getOklab().getId() << 6) | (companion.m3592getPerceptualuksYyKA() << 12), new Connector(colorSpaces.getSrgb(), colorSpaces.getOklab(), companion.m3592getPerceptualuksYyKA(), defaultConstructorMarker), colorSpaces.getOklab().getId() | (colorSpaces.getSrgb().getId() << 6) | (companion.m3592getPerceptualuksYyKA() << 12), new Connector(colorSpaces.getOklab(), colorSpaces.getSrgb(), companion.m3592getPerceptualuksYyKA(), defaultConstructorMarker));
    }

    /* JADX INFO: renamed from: connectorKey-YBCOT_4, reason: not valid java name */
    public static final int m3583connectorKeyYBCOT_4(int i, int i2, int i3) {
        return i | (i2 << 6) | (i3 << 12);
    }

    public static final MutableIntObjectMap<Connector> getConnectors() {
        return Connectors;
    }
}

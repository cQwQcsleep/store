package androidx.compose.ui.text.font;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJF\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\tH\u0080\b¢\u0006\u0002\b\u0013J+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\u0016J+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0014\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/text/font/FontMatcher;", "", "<init>", "()V", "matchFont", "", "Landroidx/compose/ui/text/font/Font;", "fontList", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "matchFont-RetOiIg", "(Ljava/util/List;Landroidx/compose/ui/text/font/FontWeight;I)Ljava/util/List;", "filterByClosestWeight", "preferBelow", "", "minSearchRange", "maxSearchRange", "filterByClosestWeight$ui_text", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/font/FontWeight;I)Ljava/util/List;", "Landroidx/compose/ui/text/font/FontListFontFamily;", "(Landroidx/compose/ui/text/font/FontListFontFamily;Landroidx/compose/ui/text/font/FontWeight;I)Ljava/util/List;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FontMatcher {
    public static final int $stable = 0;

    public static /* synthetic */ List filterByClosestWeight$ui_text$default(FontMatcher fontMatcher, List list, FontWeight fontWeight, boolean z, FontWeight fontWeight2, FontWeight fontWeight3, int i, Object obj) {
        FontWeight fontWeight4 = null;
        if ((i & 4) != 0) {
            fontWeight2 = null;
        }
        if ((i & 8) != 0) {
            fontWeight3 = null;
        }
        int size = list.size();
        FontWeight fontWeight5 = null;
        for (int i2 = 0; i2 < size; i2++) {
            FontWeight weight = ((Font) list.get(i2)).getWeight();
            if ((fontWeight2 == null || weight.compareTo(fontWeight2) >= 0) && (fontWeight3 == null || weight.compareTo(fontWeight3) <= 0)) {
                if (weight.compareTo(fontWeight) >= 0) {
                    if (weight.compareTo(fontWeight) <= 0) {
                        fontWeight4 = weight;
                        fontWeight5 = fontWeight4;
                        break;
                    }
                    if (fontWeight5 == null || weight.compareTo(fontWeight5) < 0) {
                        fontWeight5 = weight;
                    }
                } else if (fontWeight4 == null || weight.compareTo(fontWeight4) > 0) {
                    fontWeight4 = weight;
                }
            }
        }
        if (!z ? fontWeight5 != null : fontWeight4 == null) {
            fontWeight4 = fontWeight5;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Object obj2 = list.get(i3);
            if (Intrinsics.areEqual(((Font) obj2).getWeight(), fontWeight4)) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }

    public final List<Font> filterByClosestWeight$ui_text(List<? extends Font> list, FontWeight fontWeight, boolean z, FontWeight fontWeight2, FontWeight fontWeight3) {
        int size = list.size();
        FontWeight fontWeight4 = null;
        FontWeight fontWeight5 = null;
        for (int i = 0; i < size; i++) {
            FontWeight weight = list.get(i).getWeight();
            if ((fontWeight2 == null || weight.compareTo(fontWeight2) >= 0) && (fontWeight3 == null || weight.compareTo(fontWeight3) <= 0)) {
                if (weight.compareTo(fontWeight) >= 0) {
                    if (weight.compareTo(fontWeight) <= 0) {
                        fontWeight4 = weight;
                        fontWeight5 = fontWeight4;
                        break;
                    }
                    if (fontWeight5 == null || weight.compareTo(fontWeight5) < 0) {
                        fontWeight5 = weight;
                    }
                } else if (fontWeight4 == null || weight.compareTo(fontWeight4) > 0) {
                    fontWeight4 = weight;
                }
            }
        }
        if (!z ? fontWeight5 != null : fontWeight4 == null) {
            fontWeight4 = fontWeight5;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size2 = list.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Font font = list.get(i2);
            if (Intrinsics.areEqual(font.getWeight(), fontWeight4)) {
                arrayList.add(font);
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: matchFont-RetOiIg, reason: not valid java name */
    public final List<Font> m5587matchFontRetOiIg(List<? extends Font> fontList, FontWeight fontWeight, int fontStyle) {
        ArrayList arrayList = new ArrayList(fontList.size());
        List<? extends Font> list = fontList;
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Font font = fontList.get(i2);
            Font font2 = font;
            if (Intrinsics.areEqual(font2.getWeight(), fontWeight) && FontStyle.m5591equalsimpl0(font2.getStyle(), fontStyle)) {
                arrayList.add(font);
            }
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(fontList.size());
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            Font font3 = fontList.get(i3);
            if (FontStyle.m5591equalsimpl0(font3.getStyle(), fontStyle)) {
                arrayList2.add(font3);
            }
        }
        if (!arrayList2.isEmpty()) {
            fontList = arrayList2;
        }
        List<? extends Font> list2 = fontList;
        FontWeight.Companion companion = FontWeight.INSTANCE;
        FontWeight fontWeight2 = null;
        if (fontWeight.compareTo(companion.getW400()) < 0) {
            List<? extends Font> list3 = list2;
            int size3 = list3.size();
            FontWeight fontWeight3 = null;
            for (int i4 = 0; i4 < size3; i4++) {
                FontWeight weight = list2.get(i4).getWeight();
                if (weight.compareTo(fontWeight) >= 0) {
                    if (weight.compareTo(fontWeight) <= 0) {
                        fontWeight2 = weight;
                        fontWeight3 = fontWeight2;
                        break;
                    }
                    if (fontWeight3 == null || weight.compareTo(fontWeight3) < 0) {
                        fontWeight3 = weight;
                    }
                } else if (fontWeight2 == null || weight.compareTo(fontWeight2) > 0) {
                    fontWeight2 = weight;
                }
            }
            if (fontWeight2 == null) {
                fontWeight2 = fontWeight3;
            }
            ArrayList arrayList3 = new ArrayList(list2.size());
            int size4 = list3.size();
            while (i < size4) {
                Font font4 = list2.get(i);
                if (Intrinsics.areEqual(font4.getWeight(), fontWeight2)) {
                    arrayList3.add(font4);
                }
                i++;
            }
            return arrayList3;
        }
        if (fontWeight.compareTo(companion.getW500()) > 0) {
            List<? extends Font> list4 = list2;
            int size5 = list4.size();
            FontWeight fontWeight4 = null;
            for (int i5 = 0; i5 < size5; i5++) {
                FontWeight weight2 = list2.get(i5).getWeight();
                if (weight2.compareTo(fontWeight) >= 0) {
                    if (weight2.compareTo(fontWeight) <= 0) {
                        fontWeight2 = weight2;
                        fontWeight4 = fontWeight2;
                        break;
                    }
                    if (fontWeight4 == null || weight2.compareTo(fontWeight4) < 0) {
                        fontWeight4 = weight2;
                    }
                } else if (fontWeight2 == null || weight2.compareTo(fontWeight2) > 0) {
                    fontWeight2 = weight2;
                }
            }
            if (fontWeight4 != null) {
                fontWeight2 = fontWeight4;
            }
            ArrayList arrayList4 = new ArrayList(list2.size());
            int size6 = list4.size();
            while (i < size6) {
                Font font5 = list2.get(i);
                if (Intrinsics.areEqual(font5.getWeight(), fontWeight2)) {
                    arrayList4.add(font5);
                }
                i++;
            }
            return arrayList4;
        }
        FontWeight w500 = companion.getW500();
        List<? extends Font> list5 = list2;
        int size7 = list5.size();
        FontWeight fontWeight5 = null;
        FontWeight fontWeight6 = null;
        for (int i6 = 0; i6 < size7; i6++) {
            FontWeight weight3 = list2.get(i6).getWeight();
            if (w500 == null || weight3.compareTo(w500) <= 0) {
                if (weight3.compareTo(fontWeight) >= 0) {
                    if (weight3.compareTo(fontWeight) <= 0) {
                        fontWeight5 = weight3;
                        fontWeight6 = fontWeight5;
                        break;
                    }
                    if (fontWeight6 == null || weight3.compareTo(fontWeight6) < 0) {
                        fontWeight6 = weight3;
                    }
                } else if (fontWeight5 == null || weight3.compareTo(fontWeight5) > 0) {
                    fontWeight5 = weight3;
                }
            }
        }
        if (fontWeight6 != null) {
            fontWeight5 = fontWeight6;
        }
        ArrayList arrayList5 = new ArrayList(list2.size());
        int size8 = list5.size();
        for (int i7 = 0; i7 < size8; i7++) {
            Font font6 = list2.get(i7);
            if (Intrinsics.areEqual(font6.getWeight(), fontWeight5)) {
                arrayList5.add(font6);
            }
        }
        if (arrayList5.isEmpty()) {
            FontWeight w501 = FontWeight.INSTANCE.getW500();
            int size9 = list5.size();
            FontWeight fontWeight7 = null;
            for (int i8 = 0; i8 < size9; i8++) {
                FontWeight weight4 = list2.get(i8).getWeight();
                if (w501 == null || weight4.compareTo(w501) >= 0) {
                    if (weight4.compareTo(fontWeight) >= 0) {
                        if (weight4.compareTo(fontWeight) <= 0) {
                            fontWeight2 = weight4;
                            fontWeight7 = fontWeight2;
                            break;
                        }
                        if (fontWeight7 == null || weight4.compareTo(fontWeight7) < 0) {
                            fontWeight7 = weight4;
                        }
                    } else if (fontWeight2 == null || weight4.compareTo(fontWeight2) > 0) {
                        fontWeight2 = weight4;
                    }
                }
            }
            if (fontWeight7 != null) {
                fontWeight2 = fontWeight7;
            }
            arrayList5 = new ArrayList(list2.size());
            int size10 = list5.size();
            while (i < size10) {
                Font font7 = list2.get(i);
                if (Intrinsics.areEqual(font7.getWeight(), fontWeight2)) {
                    arrayList5.add(font7);
                }
                i++;
            }
        }
        return arrayList5;
    }

    /* JADX INFO: renamed from: matchFont-RetOiIg, reason: not valid java name */
    public final List<Font> m5586matchFontRetOiIg(FontListFontFamily fontFamily, FontWeight fontWeight, int fontStyle) {
        return m5587matchFontRetOiIg(fontFamily.getFonts(), fontWeight, fontStyle);
    }

    /* JADX INFO: renamed from: matchFont-RetOiIg, reason: not valid java name */
    public final List<Font> m5585matchFontRetOiIg(FontFamily fontFamily, FontWeight fontWeight, int fontStyle) {
        if (fontFamily instanceof FontListFontFamily) {
            return m5586matchFontRetOiIg((FontListFontFamily) fontFamily, fontWeight, fontStyle);
        }
        w01.a("Only FontFamily instances that presents a list of Fonts can be used");
        return null;
    }
}

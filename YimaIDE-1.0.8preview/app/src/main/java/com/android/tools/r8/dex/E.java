package com.android.tools.r8.dex;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class E {
    public static final /* synthetic */ boolean d = true;
    public final int a;
    public final int b;
    public final int c;

    public E(int i, int i2, int i3, int i4) {
        this.a = i;
        if (!d && i2 != 0) {
            x1f.a();
            throw null;
        }
        this.b = i3;
        this.c = i4;
    }

    public static String b(int i) {
        switch (i) {
            case 0:
                return "Header";
            case 1:
                return "Strings";
            case 2:
                return "Types";
            case XmlPullParser.END_TAG /* 3 */:
                return "Protos";
            case 4:
                return "Fields";
            case XmlPullParser.CDSECT /* 5 */:
                return "Methods";
            case XmlPullParser.ENTITY_REF /* 6 */:
                return "ClassDefs";
            default:
                switch (i) {
                    case 4096:
                        return "Maps";
                    case 4097:
                        return "TypeLists";
                    case 4098:
                        return "AnnotationSetRefs";
                    case 4099:
                        return "AnnotationSets";
                    default:
                        switch (i) {
                            case 8192:
                                return "ClassData";
                            case 8193:
                                return "Code";
                            case 8194:
                                return "StringData";
                            case 8195:
                                return "DebugInfo";
                            case 8196:
                                return "Annotation";
                            case 8197:
                                return "EncodedArrays";
                            case 8198:
                                return "AnnotationsDirectory";
                            default:
                                return "Unknown";
                        }
                }
        }
    }

    public final void a(int i) {
    }

    public final String toString() {
        return b(this.a) + " @" + this.c + " " + this.b;
    }
}

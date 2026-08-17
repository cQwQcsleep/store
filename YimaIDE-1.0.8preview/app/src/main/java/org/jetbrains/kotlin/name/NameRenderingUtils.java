package org.jetbrains.kotlin.name;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.renderer.KeywordStringsGenerated;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0007\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\b\u001a\u0016\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"render", "", "Lorg/jetbrains/kotlin/name/Name;", "stipSpecialMarkers", "", "shouldBeEscaped", "string", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "Lorg/jetbrains/kotlin/name/FqName;", "renderFqName", "pathSegments", "", "org.jetbrains.kotlin:names"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class NameRenderingUtils {
    public static final String render(Name name, boolean z) {
        name.getClass();
        String strAsStringStripSpecialMarkers = z ? name.asStringStripSpecialMarkers() : name.asString();
        strAsStringStripSpecialMarkers.getClass();
        if ((z && name.isSpecial()) || !shouldBeEscaped(strAsStringStripSpecialMarkers)) {
            return strAsStringStripSpecialMarkers;
        }
        return ("`" + strAsStringStripSpecialMarkers).concat("`");
    }

    public static /* synthetic */ String render$default(Name name, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return render(name, z);
    }

    private static final String renderFqName(List<Name> list) {
        StringBuilder sb = new StringBuilder();
        for (Name name : list) {
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(render$default(name, false, 1, null));
        }
        return sb.toString();
    }

    private static final boolean shouldBeEscaped(String str) {
        if (KeywordStringsGenerated.KEYWORDS.contains(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!Character.isLetterOrDigit(cCharAt) && cCharAt != '_') {
                return true;
            }
        }
        return str.length() == 0 || !Character.isJavaIdentifierStart(str.codePointAt(0));
    }

    public static final String render(FqNameUnsafe fqNameUnsafe) {
        fqNameUnsafe.getClass();
        return renderFqName(fqNameUnsafe.pathSegments());
    }

    public static final String render(FqName fqName) {
        fqName.getClass();
        return renderFqName(fqName.pathSegments());
    }
}

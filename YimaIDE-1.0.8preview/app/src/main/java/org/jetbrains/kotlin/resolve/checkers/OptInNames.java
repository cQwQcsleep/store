package org.jetbrains.kotlin.resolve.checkers;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\b\b\u0002\u0010\"\u001a\u00020#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u001fJ(\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u001f2\b\u0010'\u001a\u0004\u0018\u00010\u001f2\u0006\u0010(\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001b¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/OptInNames;", "", "<init>", "()V", "REQUIRES_OPT_IN_FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "getREQUIRES_OPT_IN_FQ_NAME", "()Lorg/jetbrains/kotlin/name/FqName;", "REQUIRES_OPT_IN_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getREQUIRES_OPT_IN_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "OPT_IN_FQ_NAME", "getOPT_IN_FQ_NAME", "OPT_IN_CLASS_ID", "getOPT_IN_CLASS_ID", "SUBCLASS_OPT_IN_REQUIRED_FQ_NAME", "getSUBCLASS_OPT_IN_REQUIRED_FQ_NAME", "SUBCLASS_OPT_IN_REQUIRED_CLASS_ID", "getSUBCLASS_OPT_IN_REQUIRED_CLASS_ID", "WAS_EXPERIMENTAL_FQ_NAME", "getWAS_EXPERIMENTAL_FQ_NAME", "WAS_EXPERIMENTAL_CLASS_ID", "getWAS_EXPERIMENTAL_CLASS_ID", "OPT_IN_ANNOTATION_CLASS", "Lorg/jetbrains/kotlin/name/Name;", "getOPT_IN_ANNOTATION_CLASS", "()Lorg/jetbrains/kotlin/name/Name;", "WAS_EXPERIMENTAL_ANNOTATION_CLASS", "getWAS_EXPERIMENTAL_ANNOTATION_CLASS", "buildDefaultDiagnosticMessage", "", "prefix", "markerName", "isSubclassOptInApplicable", "", "postfix", "buildOverrideMessage", "supertypeName", "markerMessage", "verb", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class OptInNames {
    public static final OptInNames INSTANCE = new OptInNames();
    private static final Name OPT_IN_ANNOTATION_CLASS;
    private static final ClassId OPT_IN_CLASS_ID;
    private static final FqName OPT_IN_FQ_NAME;
    private static final ClassId REQUIRES_OPT_IN_CLASS_ID;
    private static final FqName REQUIRES_OPT_IN_FQ_NAME;
    private static final ClassId SUBCLASS_OPT_IN_REQUIRED_CLASS_ID;
    private static final FqName SUBCLASS_OPT_IN_REQUIRED_FQ_NAME;
    private static final Name WAS_EXPERIMENTAL_ANNOTATION_CLASS;
    private static final ClassId WAS_EXPERIMENTAL_CLASS_ID;
    private static final FqName WAS_EXPERIMENTAL_FQ_NAME;

    static {
        FqName fqName = new FqName("kotlin.RequiresOptIn");
        REQUIRES_OPT_IN_FQ_NAME = fqName;
        ClassId.Companion companion = ClassId.Companion;
        REQUIRES_OPT_IN_CLASS_ID = companion.topLevel(fqName);
        FqName fqName2 = new FqName("kotlin.OptIn");
        OPT_IN_FQ_NAME = fqName2;
        OPT_IN_CLASS_ID = companion.topLevel(fqName2);
        FqName fqName3 = new FqName("kotlin.SubclassOptInRequired");
        SUBCLASS_OPT_IN_REQUIRED_FQ_NAME = fqName3;
        SUBCLASS_OPT_IN_REQUIRED_CLASS_ID = companion.topLevel(fqName3);
        FqName fqName4 = new FqName("kotlin.WasExperimental");
        WAS_EXPERIMENTAL_FQ_NAME = fqName4;
        WAS_EXPERIMENTAL_CLASS_ID = companion.topLevel(fqName4);
        Name nameIdentifier = Name.identifier("markerClass");
        nameIdentifier.getClass();
        OPT_IN_ANNOTATION_CLASS = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("markerClass");
        nameIdentifier2.getClass();
        WAS_EXPERIMENTAL_ANNOTATION_CLASS = nameIdentifier2;
    }

    private OptInNames() {
    }

    public static /* synthetic */ String buildDefaultDiagnosticMessage$default(OptInNames optInNames, String str, String str2, boolean z, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            str3 = null;
        }
        return optInNames.buildDefaultDiagnosticMessage(str, str2, z, str3);
    }

    public final String buildDefaultDiagnosticMessage(String prefix, String markerName, boolean isSubclassOptInApplicable, String postfix) {
        prefix.getClass();
        markerName.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        if (isSubclassOptInApplicable) {
            sb.append(" with '@" + markerName + "', '@OptIn(" + markerName + "::class)' or '@SubclassOptInRequired(" + markerName + "::class)'");
        } else {
            sb.append(" with '@" + markerName + "' or '@OptIn(" + markerName + "::class)'");
        }
        if (postfix != null) {
            sb.append(". ");
            sb.append(postfix);
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x003d  */
    public final String buildOverrideMessage(String supertypeName, String markerMessage, String verb, String markerName) {
        String strConcat;
        supertypeName.getClass();
        verb.getClass();
        markerName.getClass();
        String str = "Base declaration of supertype '" + supertypeName + "' needs opt-in. ";
        if (markerMessage == null) {
            strConcat = "";
        } else {
            if (StringsKt.isBlank(markerMessage)) {
                markerMessage = null;
            }
            if (markerMessage != null) {
                strConcat = markerMessage.concat(StringsKt.endsWith$default(markerMessage, ".", false, 2, (Object) null) ? " " : ". ");
            } else {
                strConcat = "";
            }
        }
        return str + strConcat + buildDefaultDiagnosticMessage$default(this, "The declaration override " + verb + " be annotated", markerName, false, null, 12, null);
    }

    public final Name getOPT_IN_ANNOTATION_CLASS() {
        return OPT_IN_ANNOTATION_CLASS;
    }

    public final ClassId getOPT_IN_CLASS_ID() {
        return OPT_IN_CLASS_ID;
    }

    public final FqName getOPT_IN_FQ_NAME() {
        return OPT_IN_FQ_NAME;
    }

    public final ClassId getREQUIRES_OPT_IN_CLASS_ID() {
        return REQUIRES_OPT_IN_CLASS_ID;
    }

    public final FqName getREQUIRES_OPT_IN_FQ_NAME() {
        return REQUIRES_OPT_IN_FQ_NAME;
    }

    public final ClassId getSUBCLASS_OPT_IN_REQUIRED_CLASS_ID() {
        return SUBCLASS_OPT_IN_REQUIRED_CLASS_ID;
    }

    public final FqName getSUBCLASS_OPT_IN_REQUIRED_FQ_NAME() {
        return SUBCLASS_OPT_IN_REQUIRED_FQ_NAME;
    }

    public final Name getWAS_EXPERIMENTAL_ANNOTATION_CLASS() {
        return WAS_EXPERIMENTAL_ANNOTATION_CLASS;
    }

    public final ClassId getWAS_EXPERIMENTAL_CLASS_ID() {
        return WAS_EXPERIMENTAL_CLASS_ID;
    }

    public final FqName getWAS_EXPERIMENTAL_FQ_NAME() {
        return WAS_EXPERIMENTAL_FQ_NAME;
    }
}

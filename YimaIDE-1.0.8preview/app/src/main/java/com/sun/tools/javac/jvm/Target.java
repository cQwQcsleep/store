package com.sun.tools.javac.jvm;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbConst;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Options;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.sun.tools.javac.jvm.Target, still in use, count: 1, list:
  (r0v1 com.sun.tools.javac.jvm.Target) from 0x01a3: SPUT (r0v1 com.sun.tools.javac.jvm.Target) com.sun.tools.javac.jvm.Target.MIN com.sun.tools.javac.jvm.Target
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Target {
    JDK1_1(SerializerConstants.XMLVERSION11, 45, 3),
    JDK1_2("1.2", 46, 0),
    JDK1_3("1.3", 47, 0),
    JDK1_4("1.4", 48, 0),
    JDK1_5(TlbConst.TYPELIB_MINOR_VERSION_OFFICE, 49, 0),
    JDK1_6("6", 50, 0),
    JDK1_7("7", 51, 0),
    JDK1_8(TlbConst.TYPELIB_MAJOR_VERSION_WORD, 52, 0),
    JDK1_9("9", 53, 0),
    JDK1_10("10", 54, 0),
    JDK1_11("11", 55, 0),
    JDK1_12("12", 56, 0),
    JDK1_13("13", 57, 0),
    JDK1_14("14", 58, 0),
    JDK1_15("15", 59, 0),
    JDK1_16("16", 60, 0),
    JDK1_17("17", 61, 0),
    JDK1_18("18", 62, 0),
    JDK1_19("19", 63, 0),
    JDK1_20("20", 64, 0),
    JDK1_21("21", 65, 0),
    JDK1_22("22", 66, 0),
    JDK1_23("23", 67, 0),
    JDK1_24("24", 68, 0),
    JDK1_25("25", 69, 0),
    JDK1_26("26", 70, 0);

    public static final Target DEFAULT;
    private static final Target MAX;
    public static final Target MIN = new Target(TlbConst.TYPELIB_MAJOR_VERSION_WORD, 52, 0);
    private static final Map<String, Target> tab;
    public final int majorVersion;
    public final int minorVersion;
    public final String name;
    private static final Context.Key<Target> targetKey = new Context.Key<>();

    static {
        Target target = values()[values().length - 1];
        MAX = target;
        DEFAULT = target;
        tab = new HashMap();
        for (Target target2 : values()) {
            tab.put(target2.name, target2);
        }
        Map<String, Target> map = tab;
        map.put("1.5", JDK1_5);
        map.put("1.6", JDK1_6);
        map.put("1.7", JDK1_7);
        map.put("1.8", JDK1_8);
        map.put("1.9", JDK1_9);
        map.put("1.10", JDK1_10);
    }

    private Target(String str, int i, int i2) {
        super(str, i);
        this.name = str;
        this.majorVersion = i;
        this.minorVersion = i2;
    }

    public static Target instance(Context context) {
        Context.Key<Target> key = targetKey;
        Target targetLookup = (Target) context.get(key);
        if (targetLookup == null) {
            String str = Options.instance(context).get(Option.TARGET);
            if (str != null) {
                targetLookup = lookup(str);
            }
            if (targetLookup == null) {
                targetLookup = DEFAULT;
            }
            context.put(key, targetLookup);
        }
        return targetLookup;
    }

    public static Target lookup(String str) {
        return tab.get(str);
    }

    public static Target valueOf(String str) {
        return (Target) Enum.valueOf(Target.class, str);
    }

    public static Target[] values() {
        return (Target[]) $VALUES.clone();
    }

    public boolean allApiModulesAreRoots() {
        return compareTo(JDK1_11) >= 0;
    }

    public boolean hasMethodParameters() {
        return compareTo(JDK1_8) >= 0;
    }

    public boolean hasNestmateAccess() {
        return compareTo(JDK1_11) >= 0;
    }

    public boolean hasSealedClasses() {
        return compareTo(JDK1_15) >= 0;
    }

    public boolean hasStringConcatFactory() {
        return compareTo(JDK1_9) >= 0;
    }

    public boolean hasVirtualPrivateInvoke() {
        return compareTo(JDK1_11) >= 0;
    }

    public boolean isSupported() {
        return compareTo(MIN) >= 0;
    }

    public String multiReleaseValue() {
        return Integer.toString((ordinal() - JDK1_1.ordinal()) + 1);
    }

    public boolean nullCheckOuterThisByDefault() {
        return compareTo(JDK1_25) >= 0;
    }

    public boolean obsoleteAccStrict() {
        return compareTo(JDK1_17) >= 0;
    }

    public boolean optimizeOuterThis() {
        return compareTo(JDK1_18) >= 0;
    }

    public boolean runtimeUseNestAccess() {
        return compareTo(JDK1_15) >= 0;
    }

    public boolean switchBootstrapOnlyAllowsReferenceTypesAsCaseLabels() {
        return compareTo(JDK1_23) < 0;
    }

    public char syntheticNameChar() {
        return '$';
    }

    public boolean usesReferenceOnlySelectorTypes() {
        return compareTo(JDK1_23) < 0;
    }
}

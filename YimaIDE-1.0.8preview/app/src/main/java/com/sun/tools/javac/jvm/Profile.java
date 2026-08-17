package com.sun.tools.javac.jvm;

import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Options;
import java.util.EnumSet;
import java.util.Set;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'COMPACT1' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Profile {
    private static final /* synthetic */ Profile[] $VALUES;
    public static final Profile COMPACT1;
    public static final Profile COMPACT2;
    public static final Profile COMPACT3;
    public static final Profile DEFAULT;
    private static final Context.Key<Profile> profileKey;
    public final String name;
    final Set<Target> targets;
    public final int value;

    private static /* synthetic */ Profile[] $values() {
        return new Profile[]{COMPACT1, COMPACT2, COMPACT3, DEFAULT};
    }

    static {
        Target target = Target.JDK1_8;
        COMPACT1 = new Profile("COMPACT1", 0, "compact1", 1, target, new Target[0]);
        COMPACT2 = new Profile("COMPACT2", 1, "compact2", 2, target, new Target[0]);
        COMPACT3 = new Profile("COMPACT3", 2, "compact3", 3, target, new Target[0]);
        DEFAULT = new Profile("DEFAULT", 3) { // from class: com.sun.tools.javac.jvm.Profile.1
            @Override // com.sun.tools.javac.jvm.Profile
            public boolean isValid(Target target2) {
                return true;
            }
        };
        $VALUES = $values();
        profileKey = new Context.Key<>();
    }

    private Profile(String str, int i) {
        super(str, i);
        this.name = null;
        this.value = Integer.MAX_VALUE;
        this.targets = null;
    }

    public static Profile instance(Context context) {
        Context.Key<Profile> key = profileKey;
        Profile profileLookup = (Profile) context.get(key);
        if (profileLookup == null) {
            String str = Options.instance(context).get(Option.PROFILE);
            if (str != null) {
                profileLookup = lookup(str);
            }
            if (profileLookup == null) {
                profileLookup = DEFAULT;
            }
            context.put(key, profileLookup);
        }
        return profileLookup;
    }

    public static Profile lookup(String str) {
        for (Profile profile : values()) {
            if (str.equals(profile.name)) {
                return profile;
            }
        }
        return null;
    }

    public static Profile valueOf(String str) {
        return (Profile) Enum.valueOf(Profile.class, str);
    }

    public static Profile[] values() {
        return (Profile[]) $VALUES.clone();
    }

    public boolean isValid(Target target) {
        return this.targets.contains(target);
    }

    private Profile(String str, int i, String str2, int i2, Target target, Target... targetArr) {
        super(str, i);
        this.name = str2;
        this.value = i2;
        this.targets = EnumSet.of(target, targetArr);
    }

    public static Profile lookup(int i) {
        for (Profile profile : values()) {
            if (i == profile.value) {
                return profile;
            }
        }
        return null;
    }
}

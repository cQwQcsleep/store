package org.jetbrains.kotlin.descriptors;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\t\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\r\u0010\u000bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities;", Argument.Delimiters.none, "<init>", "()V", "ORDERED_VISIBILITIES", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/Visibility;", Argument.Delimiters.none, "compare", "first", "second", "(Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Visibility;)Ljava/lang/Integer;", "compareLocal", "compareLocal$org_jetbrains_kotlin_compiler_common", "isPrivate", Argument.Delimiters.none, "visibility", "DEFAULT_VISIBILITY", "Lorg/jetbrains/kotlin/descriptors/Visibilities$Public;", "getDEFAULT_VISIBILITY", "()Lorg/jetbrains/kotlin/descriptors/Visibilities$Public;", "Private", "PrivateToThis", "Protected", "Internal", "Public", "Local", "Inherited", "InvisibleFake", "Unknown", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Visibilities {
    private static final Public DEFAULT_VISIBILITY;
    public static final Visibilities INSTANCE = new Visibilities();
    private static final Map<Visibility, Integer> ORDERED_VISIBILITIES;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$Inherited;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Inherited extends Visibility {
        public static final Inherited INSTANCE = new Inherited();

        private Inherited() {
            super("inherited", false);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            throw new IllegalStateException("This method shouldn't be invoked for INHERITED visibility");
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$Internal;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Internal extends Visibility {
        public static final Internal INSTANCE = new Internal();

        private Internal() {
            super("internal", false);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$InvisibleFake;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "externalDisplayName", Argument.Delimiters.none, "getExternalDisplayName", "()Ljava/lang/String;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InvisibleFake extends Visibility {
        public static final InvisibleFake INSTANCE = new InvisibleFake();

        private InvisibleFake() {
            super("invisible_fake", false);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public String getExternalDisplayName() {
            return "invisible (private in a supertype)";
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$Local;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Local extends Visibility {
        public static final Local INSTANCE = new Local();

        private Local() {
            super("local", false);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$Private;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Private extends Visibility {
        public static final Private INSTANCE = new Private();

        private Private() {
            super("private", false);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$PrivateToThis;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "internalDisplayName", Argument.Delimiters.none, "getInternalDisplayName", "()Ljava/lang/String;", "mustCheckInImports", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PrivateToThis extends Visibility {
        public static final PrivateToThis INSTANCE = new PrivateToThis();

        private PrivateToThis() {
            super("private_to_this", false);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        /* JADX INFO: renamed from: getInternalDisplayName */
        public String getName() {
            return "private/*private to this*/";
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$Protected;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Protected extends Visibility {
        public static final Protected INSTANCE = new Protected();

        private Protected() {
            super("protected", true);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$Public;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Public extends Visibility {
        public static final Public INSTANCE = new Public();

        private Public() {
            super("public", true);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibilities$Unknown;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Unknown extends Visibility {
        public static final Unknown INSTANCE = new Unknown();

        private Unknown() {
            super("unknown", false);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            throw new IllegalStateException("This method shouldn't be invoked for UNKNOWN visibility");
        }
    }

    static {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        mapCreateMapBuilder.put(PrivateToThis.INSTANCE, 0);
        mapCreateMapBuilder.put(Private.INSTANCE, 0);
        mapCreateMapBuilder.put(Internal.INSTANCE, 1);
        mapCreateMapBuilder.put(Protected.INSTANCE, 1);
        Public r1 = Public.INSTANCE;
        mapCreateMapBuilder.put(r1, 2);
        ORDERED_VISIBILITIES = MapsKt.build(mapCreateMapBuilder);
        DEFAULT_VISIBILITY = r1;
    }

    private Visibilities() {
    }

    public final Integer compare(Visibility first, Visibility second) {
        first.getClass();
        second.getClass();
        Integer numCompareTo = first.compareTo(second);
        if (numCompareTo != null) {
            return numCompareTo;
        }
        Integer numCompareTo2 = second.compareTo(first);
        if (numCompareTo2 != null) {
            return Integer.valueOf(-numCompareTo2.intValue());
        }
        return null;
    }

    public final Integer compareLocal$org_jetbrains_kotlin_compiler_common(Visibility first, Visibility second) {
        first.getClass();
        second.getClass();
        if (first == second) {
            return 0;
        }
        Map<Visibility, Integer> map = ORDERED_VISIBILITIES;
        Integer num = map.get(first);
        Integer num2 = map.get(second);
        if (num == null || num2 == null || Intrinsics.areEqual(num, num2)) {
            return null;
        }
        return Integer.valueOf(num.intValue() - num2.intValue());
    }

    public final Public getDEFAULT_VISIBILITY() {
        return DEFAULT_VISIBILITY;
    }

    public final boolean isPrivate(Visibility visibility) {
        visibility.getClass();
        return visibility == Private.INSTANCE || visibility == PrivateToThis.INSTANCE;
    }
}

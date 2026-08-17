package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\r\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#B%\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u000e\u001a\u00020\u0003H\u0096\u0080\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\u0018\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u0082\u0001\n$%&'()*+,-¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "publicApi", Argument.Delimiters.none, "privateApi", "<init>", "(Ljava/lang/String;ZZ)V", "getName", "()Ljava/lang/String;", "getPublicApi", "()Z", "getPrivateApi", "toString", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "lowerBound", "PrivateInClass", "Local", "Unknown", "PrivateInFile", "Public", "InternalOrPackage", "Internal", "PackagePrivate", "Protected", "ProtectedBound", "InternalProtected", "InternalProtectedBound", "Permissiveness", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$InternalOrPackage;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$InternalProtected;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$InternalProtectedBound;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Local;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$PrivateInClass;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$PrivateInFile;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Protected;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$ProtectedBound;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Public;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Unknown;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class EffectiveVisibility {
    private final String name;
    private final boolean privateApi;
    private final boolean publicApi;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Internal;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$InternalOrPackage;", "<init>", "()V", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Internal extends InternalOrPackage {
        public static final Internal INSTANCE = new Internal();

        private Internal() {
            super(true, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Internal.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$InternalProtected;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "containerTypeConstructor", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "<init>", "(Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;)V", "getContainerTypeConstructor", "()Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "lowerBound", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InternalProtected extends EffectiveVisibility {
        private final TypeConstructorMarker containerTypeConstructor;

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Permissiveness.values().length];
                try {
                    iArr[Permissiveness.SAME.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Permissiveness.LESS.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Permissiveness.UNKNOWN.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Permissiveness.MORE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public InternalProtected(TypeConstructorMarker typeConstructorMarker) {
            super("internal & protected", false, false, 4, null);
            this.containerTypeConstructor = typeConstructorMarker;
        }

        public boolean equals(Object other) {
            return (other instanceof InternalProtected) && Intrinsics.areEqual(this.containerTypeConstructor, ((InternalProtected) other).containerTypeConstructor);
        }

        public final TypeConstructorMarker getContainerTypeConstructor() {
            return this.containerTypeConstructor;
        }

        public int hashCode() {
            TypeConstructorMarker typeConstructorMarker = this.containerTypeConstructor;
            if (typeConstructorMarker != null) {
                return typeConstructorMarker.hashCode();
            }
            return 0;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public EffectiveVisibility lowerBound(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, Public.INSTANCE) || (other instanceof InternalOrPackage)) {
                return this;
            }
            if (!Intrinsics.areEqual(other, PrivateInClass.INSTANCE) && !Intrinsics.areEqual(other, PrivateInFile.INSTANCE)) {
                Local local = Local.INSTANCE;
                if (!Intrinsics.areEqual(other, local)) {
                    InternalProtectedBound internalProtectedBound = InternalProtectedBound.INSTANCE;
                    if (!Intrinsics.areEqual(other, internalProtectedBound)) {
                        if ((other instanceof Protected) || (other instanceof InternalProtected)) {
                            int i = WhenMappings.$EnumSwitchMapping$0[relation(other, typeCheckerContextProvider).ordinal()];
                            if (i != 1 && i != 2) {
                                if (i != 3) {
                                    if (i != 4) {
                                        bu8.a();
                                        return null;
                                    }
                                }
                            }
                            return this;
                        }
                        if (!Intrinsics.areEqual(other, ProtectedBound.INSTANCE)) {
                            if (Intrinsics.areEqual(other, Unknown.INSTANCE)) {
                                return local;
                            }
                            bu8.a();
                            return null;
                        }
                        return internalProtectedBound;
                    }
                }
            }
            return other;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, Public.INSTANCE) || (other instanceof InternalOrPackage)) {
                return Permissiveness.LESS;
            }
            if (Intrinsics.areEqual(other, PrivateInClass.INSTANCE) || Intrinsics.areEqual(other, PrivateInFile.INSTANCE) || Intrinsics.areEqual(other, Local.INSTANCE) || Intrinsics.areEqual(other, InternalProtectedBound.INSTANCE)) {
                return Permissiveness.MORE;
            }
            if (other instanceof InternalProtected) {
                return EffectiveVisibilityKt.containerRelation(this.containerTypeConstructor, ((InternalProtected) other).containerTypeConstructor, typeCheckerContextProvider);
            }
            if (!(other instanceof Protected)) {
                if (Intrinsics.areEqual(other, ProtectedBound.INSTANCE) || Intrinsics.areEqual(other, Unknown.INSTANCE)) {
                    return Permissiveness.UNKNOWN;
                }
                bu8.a();
                return null;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[EffectiveVisibilityKt.containerRelation(this.containerTypeConstructor, ((Protected) other).getContainerTypeConstructor(), typeCheckerContextProvider).ordinal()];
            if (i == 1 || i == 2) {
                return Permissiveness.LESS;
            }
            if (i == 3 || i == 4) {
                return Permissiveness.UNKNOWN;
            }
            bu8.a();
            return null;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" (in ");
            Character ch = this.containerTypeConstructor;
            if (ch == null) {
                ch = '?';
            }
            sb.append(ch);
            sb.append(')');
            return sb.toString();
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Private.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$InternalProtectedBound;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "()V", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InternalProtectedBound extends EffectiveVisibility {
        public static final InternalProtectedBound INSTANCE = new InternalProtectedBound();

        private InternalProtectedBound() {
            super("internal & protected (in different classes)", false, false, 6, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, Public.INSTANCE) || (other instanceof Protected) || (other instanceof InternalProtected) || Intrinsics.areEqual(other, ProtectedBound.INSTANCE) || (other instanceof InternalOrPackage)) {
                return Permissiveness.LESS;
            }
            if (Intrinsics.areEqual(other, PrivateInClass.INSTANCE) || Intrinsics.areEqual(other, PrivateInFile.INSTANCE) || Intrinsics.areEqual(other, Local.INSTANCE)) {
                return Permissiveness.MORE;
            }
            if (Intrinsics.areEqual(other, INSTANCE)) {
                return Permissiveness.SAME;
            }
            if (Intrinsics.areEqual(other, Unknown.INSTANCE)) {
                return Permissiveness.UNKNOWN;
            }
            bu8.a();
            return null;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Private.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Local;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "()V", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Local extends EffectiveVisibility {
        public static final Local INSTANCE = new Local();

        private Local() {
            super("local", false, false, 6, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            return (Intrinsics.areEqual(this, other) || Intrinsics.areEqual(PrivateInClass.INSTANCE, other)) ? Permissiveness.SAME : Permissiveness.LESS;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Local.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$PackagePrivate;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$InternalOrPackage;", "<init>", "()V", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PackagePrivate extends InternalOrPackage {
        public static final PackagePrivate INSTANCE = new PackagePrivate();

        private PackagePrivate() {
            super(false, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Private.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "LESS", "SAME", "MORE", "UNKNOWN", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Permissiveness {
        LESS,
        SAME,
        MORE,
        UNKNOWN;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Permissiveness> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$PrivateInClass;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "()V", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PrivateInClass extends EffectiveVisibility {
        public static final PrivateInClass INSTANCE = new PrivateInClass();

        private PrivateInClass() {
            super("private-in-class", false, true, 2, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            return (Intrinsics.areEqual(this, other) || Intrinsics.areEqual(Local.INSTANCE, other)) ? Permissiveness.SAME : Permissiveness.LESS;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Private.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$PrivateInFile;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "()V", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PrivateInFile extends EffectiveVisibility {
        public static final PrivateInFile INSTANCE = new PrivateInFile();

        private PrivateInFile() {
            super("private-in-file", false, true, 2, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, this)) {
                return Permissiveness.SAME;
            }
            return (Intrinsics.areEqual(other, PrivateInClass.INSTANCE) || Intrinsics.areEqual(other, Local.INSTANCE)) ? Permissiveness.MORE : Permissiveness.LESS;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Private.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Protected;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "containerTypeConstructor", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "<init>", "(Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;)V", "getContainerTypeConstructor", "()Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "lowerBound", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Protected extends EffectiveVisibility {
        private final TypeConstructorMarker containerTypeConstructor;

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Permissiveness.values().length];
                try {
                    iArr[Permissiveness.SAME.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Permissiveness.MORE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Permissiveness.UNKNOWN.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Permissiveness.LESS.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Protected(TypeConstructorMarker typeConstructorMarker) {
            super("protected", true, false, 4, null);
            this.containerTypeConstructor = typeConstructorMarker;
        }

        public boolean equals(Object other) {
            return (other instanceof Protected) && Intrinsics.areEqual(this.containerTypeConstructor, ((Protected) other).containerTypeConstructor);
        }

        public final TypeConstructorMarker getContainerTypeConstructor() {
            return this.containerTypeConstructor;
        }

        public int hashCode() {
            TypeConstructorMarker typeConstructorMarker = this.containerTypeConstructor;
            if (typeConstructorMarker != null) {
                return typeConstructorMarker.hashCode();
            }
            return 0;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public EffectiveVisibility lowerBound(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (!Intrinsics.areEqual(other, Public.INSTANCE)) {
                if (!Intrinsics.areEqual(other, PrivateInClass.INSTANCE) && !Intrinsics.areEqual(other, PrivateInFile.INSTANCE)) {
                    Local local = Local.INSTANCE;
                    if (!Intrinsics.areEqual(other, local)) {
                        ProtectedBound protectedBound = ProtectedBound.INSTANCE;
                        if (!Intrinsics.areEqual(other, protectedBound)) {
                            InternalProtectedBound internalProtectedBound = InternalProtectedBound.INSTANCE;
                            if (!Intrinsics.areEqual(other, internalProtectedBound)) {
                                if (other instanceof Protected) {
                                    int i = WhenMappings.$EnumSwitchMapping$0[relation(other, typeCheckerContextProvider).ordinal()];
                                    if (i != 1) {
                                        if (i != 2) {
                                            if (i == 3) {
                                                return protectedBound;
                                            }
                                            if (i != 4) {
                                                bu8.a();
                                                return null;
                                            }
                                        }
                                    }
                                } else {
                                    if (!(other instanceof InternalProtected)) {
                                        if (other instanceof InternalOrPackage) {
                                            return new InternalProtected(this.containerTypeConstructor);
                                        }
                                        if (other instanceof Unknown) {
                                            return local;
                                        }
                                        bu8.a();
                                        return null;
                                    }
                                    if (WhenMappings.$EnumSwitchMapping$0[relation(other, typeCheckerContextProvider).ordinal()] != 2) {
                                        return internalProtectedBound;
                                    }
                                }
                            }
                        }
                    }
                }
                return other;
            }
            return this;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, Public.INSTANCE)) {
                return Permissiveness.LESS;
            }
            if (Intrinsics.areEqual(other, PrivateInClass.INSTANCE) || Intrinsics.areEqual(other, PrivateInFile.INSTANCE) || Intrinsics.areEqual(other, Local.INSTANCE) || Intrinsics.areEqual(other, ProtectedBound.INSTANCE) || Intrinsics.areEqual(other, InternalProtectedBound.INSTANCE)) {
                return Permissiveness.MORE;
            }
            if (other instanceof Protected) {
                return EffectiveVisibilityKt.containerRelation(this.containerTypeConstructor, ((Protected) other).containerTypeConstructor, typeCheckerContextProvider);
            }
            if (!(other instanceof InternalProtected)) {
                if ((other instanceof InternalOrPackage) || (other instanceof Unknown)) {
                    return Permissiveness.UNKNOWN;
                }
                bu8.a();
                return null;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[EffectiveVisibilityKt.containerRelation(this.containerTypeConstructor, ((InternalProtected) other).getContainerTypeConstructor(), typeCheckerContextProvider).ordinal()];
            if (i == 1 || i == 2) {
                return Permissiveness.MORE;
            }
            if (i == 3 || i == 4) {
                return Permissiveness.UNKNOWN;
            }
            bu8.a();
            return null;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" (in ");
            Character ch = this.containerTypeConstructor;
            if (ch == null) {
                ch = '?';
            }
            sb.append(ch);
            sb.append(')');
            return sb.toString();
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Protected.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$ProtectedBound;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "()V", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "lowerBound", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ProtectedBound extends EffectiveVisibility {
        public static final ProtectedBound INSTANCE = new ProtectedBound();

        private ProtectedBound() {
            super("protected (in different classes)", true, false, 4, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public EffectiveVisibility lowerBound(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, Public.INSTANCE) || (other instanceof Protected)) {
                return this;
            }
            if (!Intrinsics.areEqual(other, PrivateInClass.INSTANCE) && !Intrinsics.areEqual(other, PrivateInFile.INSTANCE)) {
                Local local = Local.INSTANCE;
                if (!Intrinsics.areEqual(other, local) && !Intrinsics.areEqual(other, INSTANCE)) {
                    InternalProtectedBound internalProtectedBound = InternalProtectedBound.INSTANCE;
                    if (!Intrinsics.areEqual(other, internalProtectedBound)) {
                        if ((other instanceof InternalOrPackage) || (other instanceof InternalProtected)) {
                            return internalProtectedBound;
                        }
                        if (other instanceof Unknown) {
                            return local;
                        }
                        bu8.a();
                        return null;
                    }
                }
            }
            return other;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, Public.INSTANCE) || (other instanceof Protected)) {
                return Permissiveness.LESS;
            }
            if (Intrinsics.areEqual(other, PrivateInClass.INSTANCE) || Intrinsics.areEqual(other, PrivateInFile.INSTANCE) || Intrinsics.areEqual(other, Local.INSTANCE) || Intrinsics.areEqual(other, InternalProtectedBound.INSTANCE)) {
                return Permissiveness.MORE;
            }
            if (Intrinsics.areEqual(other, INSTANCE)) {
                return Permissiveness.SAME;
            }
            if ((other instanceof InternalOrPackage) || (other instanceof InternalProtected) || (other instanceof Unknown)) {
                return Permissiveness.UNKNOWN;
            }
            bu8.a();
            return null;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Protected.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Public;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "()V", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Public extends EffectiveVisibility {
        public static final Public INSTANCE = new Public();

        private Public() {
            super("public", true, false, 4, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, this)) {
                return Permissiveness.SAME;
            }
            return Intrinsics.areEqual(other, Unknown.INSTANCE) ? Permissiveness.UNKNOWN : Permissiveness.MORE;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Public.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Unknown;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "()V", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "toVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Unknown extends EffectiveVisibility {
        public static final Unknown INSTANCE = new Unknown();

        private Unknown() {
            super("unknown", false, false, 6, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            return Intrinsics.areEqual(other, INSTANCE) ? Permissiveness.SAME : Permissiveness.UNKNOWN;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Visibility toVisibility() {
            return Visibilities.Unknown.INSTANCE;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Permissiveness.values().length];
            try {
                iArr[Permissiveness.SAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Permissiveness.LESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Permissiveness.MORE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Permissiveness.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ EffectiveVisibility(String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2, null);
    }

    public final String getName() {
        return this.name;
    }

    public final boolean getPrivateApi() {
        return this.privateApi;
    }

    public final boolean getPublicApi() {
        return this.publicApi;
    }

    public EffectiveVisibility lowerBound(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
        other.getClass();
        typeCheckerContextProvider.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[relation(other, typeCheckerContextProvider).ordinal()];
        if (i == 1 || i == 2) {
            return this;
        }
        if (i == 3) {
            return other;
        }
        if (i == 4) {
            return PrivateInClass.INSTANCE;
        }
        bu8.a();
        return null;
    }

    public abstract Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider);

    public String toString() {
        return this.name;
    }

    public abstract Visibility toVisibility();

    private EffectiveVisibility(String str, boolean z, boolean z2) {
        this.name = str;
        this.publicApi = z;
        this.privateApi = z2;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0016\u0082\u0001\u0002\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$InternalOrPackage;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "internal", Argument.Delimiters.none, "<init>", "(Z)V", "relation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "other", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "lowerBound", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Internal;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$PackagePrivate;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class InternalOrPackage extends EffectiveVisibility {
        private InternalOrPackage(boolean z) {
            super(z ? "internal" : "public/*package*/", false, false, 6, null);
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public EffectiveVisibility lowerBound(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, Public.INSTANCE)) {
                return this;
            }
            if (!Intrinsics.areEqual(other, PrivateInClass.INSTANCE) && !Intrinsics.areEqual(other, PrivateInFile.INSTANCE)) {
                Local local = Local.INSTANCE;
                if (!Intrinsics.areEqual(other, local)) {
                    InternalProtectedBound internalProtectedBound = InternalProtectedBound.INSTANCE;
                    if (Intrinsics.areEqual(other, internalProtectedBound) || (other instanceof InternalOrPackage) || (other instanceof InternalProtected)) {
                        return other;
                    }
                    if (other instanceof Protected) {
                        return new InternalProtected(((Protected) other).getContainerTypeConstructor());
                    }
                    if (other instanceof Unknown) {
                        return local;
                    }
                    if (Intrinsics.areEqual(other, ProtectedBound.INSTANCE)) {
                        return internalProtectedBound;
                    }
                    bu8.a();
                    return null;
                }
            }
            return other;
        }

        @Override // org.jetbrains.kotlin.descriptors.EffectiveVisibility
        public Permissiveness relation(EffectiveVisibility other, TypeCheckerProviderContext typeCheckerContextProvider) {
            other.getClass();
            typeCheckerContextProvider.getClass();
            if (Intrinsics.areEqual(other, Public.INSTANCE)) {
                return Permissiveness.LESS;
            }
            if (Intrinsics.areEqual(other, PrivateInClass.INSTANCE) || Intrinsics.areEqual(other, PrivateInFile.INSTANCE) || Intrinsics.areEqual(other, Local.INSTANCE) || Intrinsics.areEqual(other, InternalProtectedBound.INSTANCE) || (other instanceof InternalProtected)) {
                return Permissiveness.MORE;
            }
            if (Intrinsics.areEqual(other, this)) {
                return Permissiveness.SAME;
            }
            if ((other instanceof InternalOrPackage) || Intrinsics.areEqual(other, ProtectedBound.INSTANCE) || (other instanceof Protected) || Intrinsics.areEqual(other, Unknown.INSTANCE)) {
                return Permissiveness.UNKNOWN;
            }
            bu8.a();
            return null;
        }

        public /* synthetic */ InternalOrPackage(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(z);
        }
    }

    public /* synthetic */ EffectiveVisibility(String str, boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, z2);
    }
}

package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.utils.FirScriptCustomizationKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0014\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%B=\b\u0004\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0011\u001a\u00020\u0003H\u0096\u0080\u0004R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\f\u0082\u0001\u0014&'()*+,-./0123456789¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", Argument.Delimiters.none, "displayName", Argument.Delimiters.none, "fromSupertypes", Argument.Delimiters.none, "generated", "fromSource", "generatedAnyMethod", "<init>", "(Ljava/lang/String;ZZZZ)V", "getFromSupertypes", "()Z", "getGenerated", "getFromSource", "getGeneratedAnyMethod", "isBuiltIns", "toString", "Source", "Library", "Precompiled", "BuiltIns", "BuiltInsFallback", "Java", "Synthetic", "DynamicScope", "SamConstructor", "Enhancement", "ImportedFromObjectOrStatic", "SubstitutionOverride", "IntersectionOverride", "Delegated", "RenamedForOverride", "WrappedIntegerOperator", "ScriptCustomization", "FromOtherReplSnippet", "ForeignValue", "Plugin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$BuiltIns;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$BuiltInsFallback;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Delegated;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$DynamicScope;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Enhancement;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ForeignValue;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$FromOtherReplSnippet;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ImportedFromObjectOrStatic;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$IntersectionOverride;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Library;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Plugin;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Precompiled;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$RenamedForOverride;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SamConstructor;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Source;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$WrappedIntegerOperator;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDeclarationOrigin {
    private final String displayName;
    private final boolean fromSource;
    private final boolean fromSupertypes;
    private final boolean generated;
    private final boolean generatedAnyMethod;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$BuiltIns;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BuiltIns extends FirDeclarationOrigin {
        public static final BuiltIns INSTANCE = new BuiltIns();

        private BuiltIns() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$BuiltInsFallback;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BuiltInsFallback extends FirDeclarationOrigin {
        public static final BuiltInsFallback INSTANCE = new BuiltInsFallback();

        private BuiltInsFallback() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Delegated;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Delegated extends FirDeclarationOrigin {
        public static final Delegated INSTANCE = new Delegated();

        private Delegated() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$DynamicScope;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DynamicScope extends FirDeclarationOrigin {
        public static final DynamicScope INSTANCE = new DynamicScope();

        private DynamicScope() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Enhancement;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Enhancement extends FirDeclarationOrigin {
        public static final Enhancement INSTANCE = new Enhancement();

        private Enhancement() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ForeignValue;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForeignValue extends FirDeclarationOrigin {
        public static final ForeignValue INSTANCE = new ForeignValue();

        private ForeignValue() {
            super(null, false, false, false, false, 23, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$FromOtherReplSnippet;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FromOtherReplSnippet extends FirDeclarationOrigin {
        public static final FromOtherReplSnippet INSTANCE = new FromOtherReplSnippet();

        private FromOtherReplSnippet() {
            super(null, false, false, false, false, 23, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ImportedFromObjectOrStatic;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ImportedFromObjectOrStatic extends FirDeclarationOrigin {
        public static final ImportedFromObjectOrStatic INSTANCE = new ImportedFromObjectOrStatic();

        private ImportedFromObjectOrStatic() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$IntersectionOverride;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IntersectionOverride extends FirDeclarationOrigin {
        public static final IntersectionOverride INSTANCE = new IntersectionOverride();

        private IntersectionOverride() {
            super(null, true, false, false, false, 29, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Library;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Library extends FirDeclarationOrigin {
        public static final Library INSTANCE = new Library();

        private Library() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Plugin;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "<init>", "(Lorg/jetbrains/kotlin/GeneratedDeclarationKey;)V", "getKey", "()Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Plugin extends FirDeclarationOrigin {
        private final GeneratedDeclarationKey key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Plugin(GeneratedDeclarationKey generatedDeclarationKey) {
            super("Plugin[" + generatedDeclarationKey + ']', false, true, false, false, 26, null);
            generatedDeclarationKey.getClass();
            this.key = generatedDeclarationKey;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Plugin) {
                return Intrinsics.areEqual(this.key, ((Plugin) other).key);
            }
            return false;
        }

        public final GeneratedDeclarationKey getKey() {
            return this.key;
        }

        public int hashCode() {
            return this.key.hashCode();
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Precompiled;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Precompiled extends FirDeclarationOrigin {
        public static final Precompiled INSTANCE = new Precompiled();

        private Precompiled() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$RenamedForOverride;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class RenamedForOverride extends FirDeclarationOrigin {
        public static final RenamedForOverride INSTANCE = new RenamedForOverride();

        private RenamedForOverride() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SamConstructor;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SamConstructor extends FirDeclarationOrigin {
        public static final SamConstructor INSTANCE = new SamConstructor();

        private SamConstructor() {
            super(null, false, false, false, false, 31, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Source;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Source extends FirDeclarationOrigin {
        public static final Source INSTANCE = new Source();

        private Source() {
            super(null, false, false, true, false, 23, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$WrappedIntegerOperator;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class WrappedIntegerOperator extends FirDeclarationOrigin {
        public static final WrappedIntegerOperator INSTANCE = new WrappedIntegerOperator();

        private WrappedIntegerOperator() {
            super(null, false, false, false, false, 31, null);
        }
    }

    public /* synthetic */ FirDeclarationOrigin(String str, boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? false : z3, (i & 16) != 0 ? false : z4, null);
    }

    public final boolean getFromSource() {
        return this.fromSource;
    }

    public final boolean getFromSupertypes() {
        return this.fromSupertypes;
    }

    public final boolean getGenerated() {
        return this.generated;
    }

    public final boolean getGeneratedAnyMethod() {
        return this.generatedAnyMethod;
    }

    public final boolean isBuiltIns() {
        return Intrinsics.areEqual(this, BuiltIns.INSTANCE) || Intrinsics.areEqual(this, BuiltInsFallback.INSTANCE);
    }

    public String toString() {
        String str = this.displayName;
        if (str != null) {
            return str;
        }
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        simpleName.getClass();
        return simpleName;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\b\tB\u001b\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "displayName", Argument.Delimiters.none, "fromSource", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Z)V", "Source", "Library", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java$Library;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java$Source;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class Java extends FirDeclarationOrigin {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java$Library;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Library extends Java {
            public static final Library INSTANCE = new Library();

            private Library() {
                super("Java(Library)", false, 2, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java$Source;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Source extends Java {
            public static final Source INSTANCE = new Source();

            private Source() {
                super("Java(Source)", true, null);
            }
        }

        private Java(String str, boolean z) {
            super(str, false, false, z, false, 22, null);
        }

        public /* synthetic */ Java(String str, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, z);
        }

        public /* synthetic */ Java(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? false : z, null);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "displayName", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "DeclarationSite", "CallSite", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride$CallSite;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride$DeclarationSite;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class SubstitutionOverride extends FirDeclarationOrigin {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride$CallSite;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class CallSite extends SubstitutionOverride {
            public static final CallSite INSTANCE = new CallSite();

            private CallSite() {
                super("SubstitutionOverride(CallSite)", null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride$DeclarationSite;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class DeclarationSite extends SubstitutionOverride {
            public static final DeclarationSite INSTANCE = new DeclarationSite();

            private DeclarationSite() {
                super("SubstitutionOverride(DeclarationSite)", null);
            }
        }

        private SubstitutionOverride(String str) {
            super(str, true, false, false, false, 28, null);
        }

        public /* synthetic */ SubstitutionOverride(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000f\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\u0013\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0001\u000f\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "generatedAnyMethod", Argument.Delimiters.none, "<init>", "(Z)V", "DataClassMember", "ValueClassMember", "JavaProperty", "DelegateField", "PluginFile", "Builtins", "Error", "TypeAliasConstructor", "FakeFunction", "ForwardDeclaration", "ScriptTopLevelDestructuringDeclarationContainer", "FakeHiddenInPreparationForNewJdk", "ImplicitWhenSubject", "ReplContainerClass", "ReplEvalFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$Builtins;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$DataClassMember;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$DelegateField;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$Error;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$FakeFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$FakeHiddenInPreparationForNewJdk;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ForwardDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ImplicitWhenSubject;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$JavaProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$PluginFile;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ReplContainerClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ReplEvalFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ScriptTopLevelDestructuringDeclarationContainer;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$TypeAliasConstructor;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ValueClassMember;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class Synthetic extends FirDeclarationOrigin {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$Builtins;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Builtins extends Synthetic {
            public static final Builtins INSTANCE = new Builtins();

            private Builtins() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$DataClassMember;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class DataClassMember extends Synthetic {
            public static final DataClassMember INSTANCE = new DataClassMember();

            private DataClassMember() {
                super(true, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$DelegateField;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class DelegateField extends Synthetic {
            public static final DelegateField INSTANCE = new DelegateField();

            private DelegateField() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$Error;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Error extends Synthetic {
            public static final Error INSTANCE = new Error();

            private Error() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$FakeFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class FakeFunction extends Synthetic {
            public static final FakeFunction INSTANCE = new FakeFunction();

            private FakeFunction() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$FakeHiddenInPreparationForNewJdk;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class FakeHiddenInPreparationForNewJdk extends Synthetic {
            public static final FakeHiddenInPreparationForNewJdk INSTANCE = new FakeHiddenInPreparationForNewJdk();

            private FakeHiddenInPreparationForNewJdk() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ForwardDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ForwardDeclaration extends Synthetic {
            public static final ForwardDeclaration INSTANCE = new ForwardDeclaration();

            private ForwardDeclaration() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ImplicitWhenSubject;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ImplicitWhenSubject extends Synthetic {
            public static final ImplicitWhenSubject INSTANCE = new ImplicitWhenSubject();

            private ImplicitWhenSubject() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$JavaProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class JavaProperty extends Synthetic {
            public static final JavaProperty INSTANCE = new JavaProperty();

            private JavaProperty() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$PluginFile;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class PluginFile extends Synthetic {
            public static final PluginFile INSTANCE = new PluginFile();

            private PluginFile() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ReplContainerClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ReplContainerClass extends Synthetic {
            public static final ReplContainerClass INSTANCE = new ReplContainerClass();

            private ReplContainerClass() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ReplEvalFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ReplEvalFunction extends Synthetic {
            public static final ReplEvalFunction INSTANCE = new ReplEvalFunction();

            private ReplEvalFunction() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ScriptTopLevelDestructuringDeclarationContainer;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ScriptTopLevelDestructuringDeclarationContainer extends Synthetic {
            public static final ScriptTopLevelDestructuringDeclarationContainer INSTANCE = new ScriptTopLevelDestructuringDeclarationContainer();

            private ScriptTopLevelDestructuringDeclarationContainer() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$TypeAliasConstructor;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class TypeAliasConstructor extends Synthetic {
            public static final TypeAliasConstructor INSTANCE = new TypeAliasConstructor();

            private TypeAliasConstructor() {
                super(false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic$ValueClassMember;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Synthetic;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ValueClassMember extends Synthetic {
            public static final ValueClassMember INSTANCE = new ValueClassMember();

            private ValueClassMember() {
                super(true, null);
            }
        }

        private Synthetic(boolean z) {
            super(null, false, false, false, z, 15, null);
        }

        public /* synthetic */ Synthetic(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(z);
        }

        public /* synthetic */ Synthetic(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, null);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\b\t\n\u000bB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0004\f\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "kind", "Lorg/jetbrains/kotlin/fir/declarations/utils/FirScriptCustomizationKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/utils/FirScriptCustomizationKind;)V", "getKind", "()Lorg/jetbrains/kotlin/fir/declarations/utils/FirScriptCustomizationKind;", "Default", "ResultProperty", "Parameter", "ParameterFromBaseClass", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization$Default;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization$Parameter;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization$ParameterFromBaseClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization$ResultProperty;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ScriptCustomization extends FirDeclarationOrigin {
        private final FirScriptCustomizationKind kind;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization$Default;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Default extends ScriptCustomization {
            public static final Default INSTANCE = new Default();

            private Default() {
                super(FirScriptCustomizationKind.DEFAULT, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization$Parameter;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Parameter extends ScriptCustomization {
            public static final Parameter INSTANCE = new Parameter();

            private Parameter() {
                super(FirScriptCustomizationKind.PARAMETER, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization$ParameterFromBaseClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ParameterFromBaseClass extends ScriptCustomization {
            public static final ParameterFromBaseClass INSTANCE = new ParameterFromBaseClass();

            private ParameterFromBaseClass() {
                super(FirScriptCustomizationKind.PARAMETER_FROM_BASE_CLASS, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization$ResultProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$ScriptCustomization;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ResultProperty extends ScriptCustomization {
            public static final ResultProperty INSTANCE = new ResultProperty();

            private ResultProperty() {
                super(FirScriptCustomizationKind.RESULT_PROPERTY, null);
            }
        }

        private ScriptCustomization(FirScriptCustomizationKind firScriptCustomizationKind) {
            super(null, false, false, false, false, 31, null);
            this.kind = firScriptCustomizationKind;
        }

        public final FirScriptCustomizationKind getKind() {
            return this.kind;
        }

        public /* synthetic */ ScriptCustomization(FirScriptCustomizationKind firScriptCustomizationKind, DefaultConstructorMarker defaultConstructorMarker) {
            this(firScriptCustomizationKind);
        }
    }

    private FirDeclarationOrigin(String str, boolean z, boolean z2, boolean z3, boolean z4) {
        this.displayName = str;
        this.fromSupertypes = z;
        this.generated = z2;
        this.fromSource = z3;
        this.generatedAnyMethod = z4;
    }

    public /* synthetic */ FirDeclarationOrigin(String str, boolean z, boolean z2, boolean z3, boolean z4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, z2, z3, z4);
    }
}

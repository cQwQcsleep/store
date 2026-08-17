package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.util.ConeTypeRegistry;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.ComponentArrayOwner;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \u001f2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001f B\u0015\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0002\b\u0007¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0007b\u0002\b\u0017J\u001c\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0002H\u0007b\u0002\b\u0017J,\u0010\u0012\u001a\u00020\u0013\"\u0010\b\u0000\u0010\u001a\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u001a0\u001b2\u0006\u0010\u0016\u001a\u0002H\u001aH\u0087\bb\u0002\b\u0017¢\u0006\u0002\u0010\u001cJ9\u0010\u0012\u001a\u00020\u0013\"\u000e\b\u0000\u0010\u001a*\b\u0012\u0004\u0012\u0002H\u001a0\u001b2\u000e\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u001a0\u00152\u0006\u0010\u0016\u001a\u0002H\u001aH\u0007b\u0002\b\u0017¢\u0006\u0002\u0010\u001dJ\n\u0010\u001e\u001a\u00020\u0019H\u0096\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000fX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/util/ComponentArrayOwner;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "kind", "Lorg/jetbrains/kotlin/fir/FirSession$Kind;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession$Kind;)V", "Lorg/jetbrains/kotlin/fir/PrivateSessionConstructor;", "getKind", "()Lorg/jetbrains/kotlin/fir/FirSession$Kind;", "builtinTypes", "Lorg/jetbrains/kotlin/fir/BuiltinTypes;", "getBuiltinTypes", "()Lorg/jetbrains/kotlin/fir/BuiltinTypes;", "typeRegistry", "Lorg/jetbrains/kotlin/util/TypeRegistry;", "getTypeRegistry", "()Lorg/jetbrains/kotlin/util/TypeRegistry;", "register", Argument.Delimiters.none, "tClass", "Lkotlin/reflect/KClass;", "value", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "keyQualifiedName", Argument.Delimiters.none, "T", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "(Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;)V", "(Lkotlin/reflect/KClass;Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;)V", "toString", "Companion", "Kind", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSession extends ComponentArrayOwner<FirSessionComponent, FirSessionComponent> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BuiltinTypes builtinTypes;
    private final Kind kind;
    private final TypeRegistry<FirSessionComponent, FirSessionComponent> typeRegistry;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirSession$Kind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "Source", "Library", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Kind {
        Source,
        Library;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Kind.values().length];
            try {
                iArr[Kind.Source.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Kind.Library.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @PrivateSessionConstructor
    public FirSession(Kind kind) {
        kind.getClass();
        this.kind = kind;
        this.builtinTypes = new BuiltinTypes();
        this.typeRegistry = INSTANCE;
    }

    public BuiltinTypes getBuiltinTypes() {
        return this.builtinTypes;
    }

    public final Kind getKind() {
        return this.kind;
    }

    public final TypeRegistry<FirSessionComponent, FirSessionComponent> getTypeRegistry() {
        return this.typeRegistry;
    }

    @SessionConfiguration
    public final <T extends FirComposableSessionComponent<T>> void register(KClass<? extends T> tClass, T value) {
        tClass.getClass();
        value.getClass();
        FirComposableSessionComponent firComposableSessionComponent = (FirComposableSessionComponent) getOrNull(tClass);
        if (firComposableSessionComponent != null) {
            value = (T) firComposableSessionComponent.compose(value);
        }
        registerComponent(tClass, value);
    }

    public String toString() {
        String str;
        FirModuleData nullableModuleData = FirModuleDataKt.getNullableModuleData(this);
        if (nullableModuleData == null) {
            return "Libraries session";
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.kind.ordinal()];
        if (i == 1) {
            str = "Source";
        } else {
            if (i != 2) {
                bu8.a();
                return null;
            }
            str = "Library";
        }
        return str + " session for module " + nullableModuleData.getName();
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J'\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u0002H\u0086\bJ9\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00022\u000b\u0010\t\u001a\u0007H\u0007¢\u0006\u0002\b\nH\u0086\b¢\u0006\u0002\u0010\u000bJ/\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0086\bJ'\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00070\u000f\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u0002H\u0086\b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirSession$Companion;", "Lorg/jetbrains/kotlin/fir/util/ConeTypeRegistry;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "sessionComponentAccessor", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "T", "sessionComponentAccessorWithDefault", "defaultImplementation", "Lkotlin/internal/NoInfer;", "(Lorg/jetbrains/kotlin/fir/FirSessionComponent;)Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "id", Argument.Delimiters.none, "nullableSessionComponentAccessor", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ConeTypeRegistry<FirSessionComponent, FirSessionComponent> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final /* synthetic */ <T extends FirSessionComponent> NullableArrayMapAccessor<FirSessionComponent, FirSessionComponent, T> nullableSessionComponentAccessor() {
            Intrinsics.reifiedOperationMarker(4, "T");
            return generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirSessionComponent.class));
        }

        public final /* synthetic */ <T extends FirSessionComponent> ArrayMapAccessor<FirSessionComponent, FirSessionComponent, T> sessionComponentAccessor() {
            Intrinsics.reifiedOperationMarker(4, "T");
            return TypeRegistry.generateAccessor$default(this, Reflection.getOrCreateKotlinClass(FirSessionComponent.class), (Object) null, 2, (Object) null);
        }

        public final /* synthetic */ <T extends FirSessionComponent> ArrayMapAccessor<FirSessionComponent, FirSessionComponent, T> sessionComponentAccessorWithDefault(T defaultImplementation) {
            defaultImplementation.getClass();
            Intrinsics.reifiedOperationMarker(4, "T");
            return generateAccessor(Reflection.getOrCreateKotlinClass(FirSessionComponent.class), defaultImplementation);
        }

        private Companion() {
        }

        public final /* synthetic */ <T extends FirSessionComponent> ArrayMapAccessor<FirSessionComponent, FirSessionComponent, T> sessionComponentAccessor(String id) {
            id.getClass();
            return TypeRegistry.generateAccessor$default(this, id, (Object) null, 2, (Object) null);
        }
    }

    @SessionConfiguration
    public final void register(String keyQualifiedName, FirSessionComponent value) {
        keyQualifiedName.getClass();
        value.getClass();
        registerComponent(keyQualifiedName, value);
    }

    @SessionConfiguration
    public final /* synthetic */ <T extends FirComposableSessionComponent<T>> void register(T value) {
        value.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        register((KClass) Reflection.getOrCreateKotlinClass(FirComposableSessionComponent.class), (FirComposableSessionComponent) value);
    }

    @SessionConfiguration
    public final void register(KClass<? extends FirSessionComponent> tClass, FirSessionComponent value) {
        tClass.getClass();
        value.getClass();
        registerComponent(tClass, value);
    }
}

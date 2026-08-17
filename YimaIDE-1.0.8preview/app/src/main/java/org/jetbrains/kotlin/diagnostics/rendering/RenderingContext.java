package org.jetbrains.kotlin.diagnostics.rendering;

import java.util.Collection;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \f2\u00020\u0001:\u0004\t\n\u000b\fB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H¦\u0002¢\u0006\u0002\u0010\b\u0082\u0001\u0002\r\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", Argument.Delimiters.none, "<init>", "()V", "get", "T", "key", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;", "(Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;)Ljava/lang/Object;", "Key", "Impl", "Empty", "Companion", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Empty;", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Impl;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class RenderingContext {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0096\u0002¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Empty;", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "<init>", "()V", "get", "T", "key", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;", "(Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;)Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Empty extends RenderingContext {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.diagnostics.rendering.RenderingContext
        public <T> T get(Key<? extends T> key) {
            key.getClass();
            return key.compute(CollectionsKt.emptyList(), DiagnosticContext.Default.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J%\u0010\t\u001a\u00028\u00002\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;", "T", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "compute", "objectsToRender", Argument.Delimiters.none, "diagnosticContext", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "(Ljava/util/Collection;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class Key<T> {
        private final String name;

        public Key(String str) {
            str.getClass();
            this.name = str;
        }

        public abstract T compute(Collection<? extends Object> objectsToRender, DiagnosticBaseContext diagnosticContext);

        public final String getName() {
            return this.name;
        }
    }

    public /* synthetic */ RenderingContext(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    @LegacyRenderingContextApi
    public static final RenderingContext of(Object... objArr) {
        return INSTANCE.of(objArr);
    }

    public abstract <T> T get(Key<? extends T> key);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0007\"\u0004\u0018\u00010\u0001H\u0007b\u0002\b\tb\u0002\b\n¢\u0006\u0002\u0010\bJ+\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0016\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0007\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Companion;", Argument.Delimiters.none, "<init>", "()V", "of", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "objectsToRender", Argument.Delimiters.none, "([Ljava/lang/Object;)Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "Lkotlin/jvm/JvmStatic;", "Lorg/jetbrains/kotlin/diagnostics/rendering/LegacyRenderingContextApi;", "diagnosticContext", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;[Ljava/lang/Object;)Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RenderingContext of(DiagnosticBaseContext diagnosticContext, Object... objectsToRender) {
            diagnosticContext.getClass();
            objectsToRender.getClass();
            return new Impl(ArraysKt.toList(objectsToRender), diagnosticContext);
        }

        private Companion() {
        }

        @JvmStatic
        @LegacyRenderingContextApi
        public final RenderingContext of(Object... objectsToRender) {
            objectsToRender.getClass();
            return new Impl(ArraysKt.toList(objectsToRender));
        }
    }

    private RenderingContext() {
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001f\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u001d\b\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u001a\u0002\b\n¢\u0006\u0004\b\u0007\u0010\tJ\"\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\rH\u0096\u0002¢\u0006\u0002\u0010\u0012R\u0016\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u000b\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0006\u0012\u0004\u0018\u00010\u00040\fj\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Impl;", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "objectsToRender", Argument.Delimiters.none, Argument.Delimiters.none, "diagnosticContext", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "<init>", "(Ljava/util/Collection;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)V", "(Ljava/util/Collection;)V", "Lorg/jetbrains/kotlin/diagnostics/rendering/LegacyRenderingContextApi;", "data", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;", "Lkotlin/collections/LinkedHashMap;", "get", "T", "key", "(Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext$Key;)Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Impl extends RenderingContext {
        private final LinkedHashMap<Key<?>, Object> data;
        private final DiagnosticBaseContext diagnosticContext;
        private final Collection<Object> objectsToRender;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Impl(Collection<? extends Object> collection, DiagnosticBaseContext diagnosticBaseContext) {
            super(null);
            collection.getClass();
            diagnosticBaseContext.getClass();
            this.objectsToRender = collection;
            this.diagnosticContext = diagnosticBaseContext;
            this.data = new LinkedHashMap<>();
        }

        @Override // org.jetbrains.kotlin.diagnostics.rendering.RenderingContext
        public <T> T get(Key<? extends T> key) {
            key.getClass();
            T t = (T) this.data.get(key);
            if (t == null) {
                t = null;
            }
            if (t != null) {
                return t;
            }
            T tCompute = key.compute(this.objectsToRender, this.diagnosticContext);
            this.data.put(key, tCompute);
            return tCompute;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @LegacyRenderingContextApi
        public Impl(Collection<? extends Object> collection) {
            this(collection, DiagnosticContext.Default.INSTANCE);
            collection.getClass();
        }
    }
}

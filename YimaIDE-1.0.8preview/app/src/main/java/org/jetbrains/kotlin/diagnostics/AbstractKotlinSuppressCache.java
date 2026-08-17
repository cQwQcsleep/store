package org.jetbrains.kotlin.diagnostics;

import com.google.common.collect.ImmutableSet;
import com.intellij.util.containers.ContainerUtil;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\t\b&\u0018\u0000 $*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0007$%&'()*B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J+\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0016\u0010\f\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0014J'\u0010\u0017\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\rH$¢\u0006\u0002\u0010\u0019J5\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u001dH\u0014¢\u0006\u0002\u0010\u001eJ\u001b\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\u001b\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010 J\u001b\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\"2\u0006\u0010\u001b\u001a\u00028\u0000H$¢\u0006\u0002\u0010#RD\u0010\u0005\u001a/\u0012\u0010\u0012\u000e\b\u00028\u0000¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b0\u0006¢\u0006\u0002\b\u0007X\u0084\u0004ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b9¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache;", "Element", Argument.Delimiters.none, "<init>", "()V", "suppressors", "Ljava/util/concurrent/ConcurrentMap;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$Suppressor;", "getSuppressors", "()Ljava/util/concurrent/ConcurrentMap;", "isSuppressed", Argument.Delimiters.none, "element", "rootElement", "suppressionKey", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;)Z", "request", "Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$SuppressRequest;", "getClosestAnnotatedAncestorElement", "excludeSelf", "(Ljava/lang/Object;Ljava/lang/Object;Z)Ljava/lang/Object;", "isSuppressedByAnnotated", "annotated", "debugDepth", Argument.Delimiters.none, "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;Ljava/lang/Object;Ljava/lang/Object;I)Z", "getOrCreateSuppressor", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$Suppressor;", "getSuppressingStrings", Argument.Delimiters.none, "(Ljava/lang/Object;)Ljava/util/Set;", "Companion", "Suppressor", "EmptySuppressor", "SingularSuppressor", "MultiSuppressor", "SuppressRequest", "StringSuppressRequest", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractKotlinSuppressCache<Element> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ConcurrentMap<Element, Suppressor<Element>> suppressors;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$EmptySuppressor;", "Element", "Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$Suppressor;", "annotated", "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "isSuppressed", Argument.Delimiters.none, "suppressionKey", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "dominates", "other", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class EmptySuppressor<Element> extends Suppressor<Element> {
        public EmptySuppressor(Element element) {
            super(element);
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.Suppressor
        public boolean dominates(Suppressor<Element> other) {
            other.getClass();
            return other instanceof EmptySuppressor;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.Suppressor
        public boolean isSuppressed(String suppressionKey, Severity severity) {
            suppressionKey.getClass();
            severity.getClass();
            return false;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0016J\u0016\u0010\u000e\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$MultiSuppressor;", "Element", "Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$Suppressor;", "annotated", "strings", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Ljava/lang/Object;Ljava/util/Set;)V", "isSuppressed", Argument.Delimiters.none, "suppressionKey", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "dominates", "other", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class MultiSuppressor<Element> extends Suppressor<Element> {
        private final Set<String> strings;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MultiSuppressor(Element element, Set<String> set) {
            super(element);
            set.getClass();
            this.strings = set;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.Suppressor
        public boolean dominates(Suppressor<Element> other) {
            other.getClass();
            return other instanceof EmptySuppressor;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.Suppressor
        public boolean isSuppressed(String suppressionKey, Severity severity) {
            suppressionKey.getClass();
            severity.getClass();
            return AbstractKotlinSuppressCache.INSTANCE.isSuppressedByStrings(suppressionKey, this.strings, severity);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\r\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$SingularSuppressor;", "Element", "Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$Suppressor;", "annotated", "string", Argument.Delimiters.none, "<init>", "(Ljava/lang/Object;Ljava/lang/String;)V", "isSuppressed", Argument.Delimiters.none, "suppressionKey", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "dominates", "other", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SingularSuppressor<Element> extends Suppressor<Element> {
        private final String string;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SingularSuppressor(Element element, String str) {
            super(element);
            str.getClass();
            this.string = str;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.Suppressor
        public boolean dominates(Suppressor<Element> other) {
            other.getClass();
            if (other instanceof EmptySuppressor) {
                return true;
            }
            return (other instanceof SingularSuppressor) && Intrinsics.areEqual(((SingularSuppressor) other).string, this.string);
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.Suppressor
        public boolean isSuppressed(String suppressionKey, Severity severity) {
            suppressionKey.getClass();
            severity.getClass();
            Companion companion = AbstractKotlinSuppressCache.INSTANCE;
            ImmutableSet immutableSetOf = ImmutableSet.of(this.string);
            immutableSetOf.getClass();
            return companion.isSuppressedByStrings(suppressionKey, immutableSetOf, severity);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0016\u0010\u0003\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$StringSuppressRequest;", "Element", "Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$SuppressRequest;", "element", "rootElement", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "suppressKey", Argument.Delimiters.none, "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/Severity;Ljava/lang/String;)V", "getElement", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getRootElement", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "getSuppressKey", "()Ljava/lang/String;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class StringSuppressRequest<Element> implements SuppressRequest<Element> {
        private final Element element;
        private final Element rootElement;
        private final Severity severity;
        private final String suppressKey;

        public StringSuppressRequest(Element element, Element element2, Severity severity, String str) {
            severity.getClass();
            str.getClass();
            this.element = element;
            this.rootElement = element2;
            this.severity = severity;
            this.suppressKey = str;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.SuppressRequest
        public Element getElement() {
            return this.element;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.SuppressRequest
        public Element getRootElement() {
            return this.rootElement;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.SuppressRequest
        public Severity getSeverity() {
            return this.severity;
        }

        @Override // org.jetbrains.kotlin.diagnostics.AbstractKotlinSuppressCache.SuppressRequest
        public String getSuppressKey() {
            return this.suppressKey;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bd\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002R\u0012\u0010\u0003\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$SuppressRequest;", "Element", Argument.Delimiters.none, "element", "getElement", "()Ljava/lang/Object;", "rootElement", "getRootElement", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "suppressKey", Argument.Delimiters.none, "getSuppressKey", "()Ljava/lang/String;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface SuppressRequest<Element> {
        Element getElement();

        Element getRootElement();

        Severity getSeverity();

        String getSuppressKey();
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b$\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u0016\u0010\u000f\u001a\u00020\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000H&R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$Suppressor;", "Element", Argument.Delimiters.none, "annotatedElement", "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getAnnotatedElement", "()Ljava/lang/Object;", "Ljava/lang/Object;", "isSuppressed", Argument.Delimiters.none, "suppressionKey", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "dominates", "other", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class Suppressor<Element> {
        private final Element annotatedElement;

        public Suppressor(Element element) {
            this.annotatedElement = element;
        }

        public abstract boolean dominates(Suppressor<Element> other);

        public final Element getAnnotatedElement() {
            return this.annotatedElement;
        }

        public abstract boolean isSuppressed(String suppressionKey, Severity severity);
    }

    public AbstractKotlinSuppressCache() {
        ConcurrentMap<Element, Suppressor<Element>> concurrentMapCreateConcurrentWeakValueMap = ContainerUtil.createConcurrentWeakValueMap();
        concurrentMapCreateConcurrentWeakValueMap.getClass();
        this.suppressors = concurrentMapCreateConcurrentWeakValueMap;
    }

    public abstract Element getClosestAnnotatedAncestorElement(Element element, Element rootElement, boolean excludeSelf);

    public final Suppressor<Element> getOrCreateSuppressor(Element annotated) {
        ConcurrentMap<Element, Suppressor<Element>> concurrentMap = this.suppressors;
        Suppressor<Element> emptySuppressor = concurrentMap.get(annotated);
        if (emptySuppressor == null) {
            Set<String> suppressingStrings = getSuppressingStrings(annotated);
            int size = suppressingStrings.size();
            if (size != 0) {
                emptySuppressor = size != 1 ? new MultiSuppressor<>(annotated, suppressingStrings) : new SingularSuppressor<>(annotated, (String) CollectionsKt.first(suppressingStrings));
            } else {
                emptySuppressor = new EmptySuppressor(annotated);
            }
            concurrentMap.put(annotated, emptySuppressor);
        }
        return emptySuppressor;
    }

    public abstract Set<String> getSuppressingStrings(Element annotated);

    public final ConcurrentMap<Element, Suppressor<Element>> getSuppressors() {
        return this.suppressors;
    }

    public boolean isSuppressed(SuppressRequest<Element> request) {
        request.getClass();
        Element closestAnnotatedAncestorElement = getClosestAnnotatedAncestorElement(request.getElement(), request.getRootElement(), false);
        if (closestAnnotatedAncestorElement == null) {
            return false;
        }
        return isSuppressedByAnnotated(request.getSuppressKey(), request.getSeverity(), closestAnnotatedAncestorElement, request.getRootElement(), 0);
    }

    public boolean isSuppressedByAnnotated(String suppressionKey, Severity severity, Element annotated, Element rootElement, int debugDepth) {
        suppressionKey.getClass();
        severity.getClass();
        Suppressor<Element> orCreateSuppressor = getOrCreateSuppressor(annotated);
        if (orCreateSuppressor.isSuppressed(suppressionKey, severity)) {
            return true;
        }
        Element closestAnnotatedAncestorElement = getClosestAnnotatedAncestorElement(orCreateSuppressor.getAnnotatedElement(), rootElement, true);
        if (closestAnnotatedAncestorElement == null) {
            return false;
        }
        boolean zIsSuppressedByAnnotated = isSuppressedByAnnotated(suppressionKey, severity, closestAnnotatedAncestorElement, rootElement, debugDepth + 1);
        Suppressor<Element> suppressor = this.suppressors.get(closestAnnotatedAncestorElement);
        if (suppressor != null && suppressor.dominates(orCreateSuppressor)) {
            this.suppressors.put(annotated, suppressor);
        }
        return zIsSuppressedByAnnotated;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKotlinSuppressCache$Companion;", Argument.Delimiters.none, "<init>", "()V", "isSuppressedByStrings", Argument.Delimiters.none, "key", Argument.Delimiters.none, "strings", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSuppressedByStrings(String key, Set<String> strings, Severity severity) {
            if (severity == Severity.WARNING && strings.contains(AbstractDiagnosticCollector.SUPPRESS_ALL_WARNINGS)) {
                return true;
            }
            String lowerCase = key.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            return strings.contains(lowerCase);
        }

        private Companion() {
        }
    }

    public final boolean isSuppressed(Element element, Element rootElement, String suppressionKey, Severity severity) {
        suppressionKey.getClass();
        severity.getClass();
        String lowerCase = suppressionKey.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return isSuppressed(new StringSuppressRequest(element, rootElement, severity, lowerCase));
    }
}

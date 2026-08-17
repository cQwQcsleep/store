package org.jetbrains.kotlin.fir.extensions;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate;
import org.jetbrains.kotlin.fir.extensions.predicate.DeclarationPredicate;
import org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate;
import org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\"#B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H\u0017b\u0002\b\u0019J\u001c\u0010\u001a\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H\u0002J\u001c\u0010\u001c\u001a\u00020\u00102\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u001d2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001e\u001a\f\u0012\u0004\u0012\u00020 0\u001fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f\u0012\u0004\u0012\u00020\u000e0\u001fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProviderImpl;", "Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "registeredPluginAnnotations", "Lorg/jetbrains/kotlin/fir/extensions/FirRegisteredPluginAnnotations;", "cache", "Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProviderImpl$Cache;", "getSymbolsByPredicate", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "predicate", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "fileHasPluginAnnotations", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "registerAnnotatedDeclaration", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "owners", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionApiInternals;", "getOwnersOfDeclaration", "registerOwnersDeclarations", "matches", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "declarationPredicateMatcher", "Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProviderImpl$Matcher;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate;", "lookupPredicateMatcher", "Matcher", "Cache", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPredicateBasedProviderImpl extends FirPredicateBasedProvider {
    private final Cache cache;
    private final Matcher<DeclarationPredicate> declarationPredicateMatcher;
    private final Matcher<LookupPredicate> lookupPredicateMatcher;
    private final FirRegisteredPluginAnnotations registeredPluginAnnotations;
    private final FirSession session;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0004\u001a\u0012\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u00060\u0006j\u0002`\u00070\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR!\u0010\u000f\u001a\u0012\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR!\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u00060\u0006j\u0002`\u00070\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR!\u0010\u0013\u001a\u0012\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR!\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR!\u0010\u0017\u001a\u0012\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\nR!\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR#\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProviderImpl$Cache;", Argument.Delimiters.none, "<init>", "()V", "declarationByAnnotation", "Lcom/google/common/collect/Multimap;", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getDeclarationByAnnotation", "()Lcom/google/common/collect/Multimap;", "annotationsOfDeclaration", "Lcom/google/common/collect/LinkedHashMultimap;", "getAnnotationsOfDeclaration", "()Lcom/google/common/collect/LinkedHashMultimap;", "declarationsUnderAnnotated", "getDeclarationsUnderAnnotated", "annotationsOfUnderAnnotated", "getAnnotationsOfUnderAnnotated", "declarationsParentAnnotated", "getDeclarationsParentAnnotated", "annotationsOfParentAnnotated", "getAnnotationsOfParentAnnotated", "declarationsHasAnnotated", "getDeclarationsHasAnnotated", "annotationsOfHasAnnotated", "getAnnotationsOfHasAnnotated", "ownersForDeclaration", Argument.Delimiters.none, "Lkotlinx/collections/immutable/PersistentList;", "getOwnersForDeclaration", "()Ljava/util/Map;", "filesWithPluginAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesWithPluginAnnotations", "()Ljava/util/Set;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Cache {
        private final LinkedHashMultimap<FirDeclaration, FqName> annotationsOfDeclaration;
        private final Multimap<FirDeclaration, FqName> annotationsOfHasAnnotated;
        private final Multimap<FirDeclaration, FqName> annotationsOfParentAnnotated;
        private final LinkedHashMultimap<FirDeclaration, FqName> annotationsOfUnderAnnotated;
        private final Multimap<FqName, FirDeclaration> declarationByAnnotation;
        private final Multimap<FqName, FirDeclaration> declarationsHasAnnotated;
        private final Multimap<FqName, FirDeclaration> declarationsParentAnnotated;
        private final Multimap<FqName, FirDeclaration> declarationsUnderAnnotated;
        private final Set<FirFile> filesWithPluginAnnotations;
        private final Map<FirDeclaration, PersistentList<FirDeclaration>> ownersForDeclaration;

        public Cache() {
            LinkedHashMultimap linkedHashMultimapCreate = LinkedHashMultimap.create();
            linkedHashMultimapCreate.getClass();
            this.declarationByAnnotation = linkedHashMultimapCreate;
            LinkedHashMultimap<FirDeclaration, FqName> linkedHashMultimapCreate2 = LinkedHashMultimap.create();
            linkedHashMultimapCreate2.getClass();
            this.annotationsOfDeclaration = linkedHashMultimapCreate2;
            LinkedHashMultimap linkedHashMultimapCreate3 = LinkedHashMultimap.create();
            linkedHashMultimapCreate3.getClass();
            this.declarationsUnderAnnotated = linkedHashMultimapCreate3;
            LinkedHashMultimap<FirDeclaration, FqName> linkedHashMultimapCreate4 = LinkedHashMultimap.create();
            linkedHashMultimapCreate4.getClass();
            this.annotationsOfUnderAnnotated = linkedHashMultimapCreate4;
            LinkedHashMultimap linkedHashMultimapCreate5 = LinkedHashMultimap.create();
            linkedHashMultimapCreate5.getClass();
            this.declarationsParentAnnotated = linkedHashMultimapCreate5;
            LinkedHashMultimap linkedHashMultimapCreate6 = LinkedHashMultimap.create();
            linkedHashMultimapCreate6.getClass();
            this.annotationsOfParentAnnotated = linkedHashMultimapCreate6;
            LinkedHashMultimap linkedHashMultimapCreate7 = LinkedHashMultimap.create();
            linkedHashMultimapCreate7.getClass();
            this.declarationsHasAnnotated = linkedHashMultimapCreate7;
            LinkedHashMultimap linkedHashMultimapCreate8 = LinkedHashMultimap.create();
            linkedHashMultimapCreate8.getClass();
            this.annotationsOfHasAnnotated = linkedHashMultimapCreate8;
            this.ownersForDeclaration = new LinkedHashMap();
            this.filesWithPluginAnnotations = new LinkedHashSet();
        }

        public final LinkedHashMultimap<FirDeclaration, FqName> getAnnotationsOfDeclaration() {
            return this.annotationsOfDeclaration;
        }

        public final Multimap<FirDeclaration, FqName> getAnnotationsOfHasAnnotated() {
            return this.annotationsOfHasAnnotated;
        }

        public final Multimap<FirDeclaration, FqName> getAnnotationsOfParentAnnotated() {
            return this.annotationsOfParentAnnotated;
        }

        public final LinkedHashMultimap<FirDeclaration, FqName> getAnnotationsOfUnderAnnotated() {
            return this.annotationsOfUnderAnnotated;
        }

        public final Multimap<FqName, FirDeclaration> getDeclarationByAnnotation() {
            return this.declarationByAnnotation;
        }

        public final Multimap<FqName, FirDeclaration> getDeclarationsHasAnnotated() {
            return this.declarationsHasAnnotated;
        }

        public final Multimap<FqName, FirDeclaration> getDeclarationsParentAnnotated() {
            return this.declarationsParentAnnotated;
        }

        public final Multimap<FqName, FirDeclaration> getDeclarationsUnderAnnotated() {
            return this.declarationsUnderAnnotated;
        }

        public final Set<FirFile> getFilesWithPluginAnnotations() {
            return this.filesWithPluginAnnotations;
        }

        public final Map<FirDeclaration, PersistentList<FirDeclaration>> getOwnersForDeclaration() {
            return this.ownersForDeclaration;
        }
    }

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\b\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u000eJ#\u0010\u000f\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u0011J#\u0010\u0012\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u0014J#\u0010\u0015\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u001aJ#\u0010\u001b\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c2\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u001dJ#\u0010\u001e\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010\n\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010 J\"\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00052\u0010\u0010#\u001a\f\u0012\b\u0012\u00060%j\u0002`&0$H\u0002J\"\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00052\u0010\u0010#\u001a\f\u0012\b\u0012\u00060%j\u0002`&0$H\u0002J\"\u0010(\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00052\u0010\u0010#\u001a\f\u0012\b\u0012\u00060%j\u0002`&0$H\u0002J\"\u0010)\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00052\u0010\u0010#\u001a\f\u0012\b\u0012\u00060%j\u0002`&0$H\u0002J\"\u0010*\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00052\u0010\u0010#\u001a\f\u0012\b\u0012\u00060%j\u0002`&0$H\u0002¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProviderImpl$Matcher;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProviderImpl;)V", "visitPredicate", "predicate", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "visitAnd", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$And;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$And;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "visitOr", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Or;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Or;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "visitAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AnnotatedWith;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "visitAncestorAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AncestorAnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AncestorAnnotatedWith;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "visitParentAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$ParentAnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$ParentAnnotatedWith;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "visitHasAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$HasAnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$HasAnnotatedWith;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "visitMetaAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$MetaAnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$MetaAnnotatedWith;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "matchWith", "declaration", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "matchNonIndexedDeclaration", "matchUnder", "matchParentWith", "matchHasAnnotatedWith", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class Matcher<P extends AbstractPredicate<P>> extends PredicateVisitor<P, Boolean, FirDeclaration> {
        public Matcher() {
        }

        private final boolean matchHasAnnotatedWith(FirDeclaration declaration, Set<FqName> annotations) {
            Collection collection = FirPredicateBasedProviderImpl.this.cache.getAnnotationsOfHasAnnotated().get(declaration);
            collection.getClass();
            Collection collection2 = collection;
            if (collection2.isEmpty()) {
                return false;
            }
            Iterator it = collection2.iterator();
            while (it.hasNext()) {
                if (annotations.contains((FqName) it.next())) {
                    return true;
                }
            }
            return false;
        }

        private final boolean matchNonIndexedDeclaration(FirDeclaration declaration, Set<FqName> annotations) {
            List<FirAnnotation> annotations2 = declaration.getAnnotations();
            FirPredicateBasedProviderImpl firPredicateBasedProviderImpl = FirPredicateBasedProviderImpl.this;
            if ((annotations2 instanceof Collection) && annotations2.isEmpty()) {
                return false;
            }
            Iterator<T> it = annotations2.iterator();
            while (it.hasNext()) {
                if (CollectionsKt.contains(annotations, CallableIdUtilsKt.fqName((FirAnnotation) it.next(), firPredicateBasedProviderImpl.session))) {
                    return true;
                }
            }
            return false;
        }

        private final boolean matchParentWith(FirDeclaration declaration, Set<FqName> annotations) {
            Collection collection = FirPredicateBasedProviderImpl.this.cache.getAnnotationsOfParentAnnotated().get(declaration);
            collection.getClass();
            Collection collection2 = collection;
            if (collection2.isEmpty()) {
                return false;
            }
            Iterator it = collection2.iterator();
            while (it.hasNext()) {
                if (annotations.contains((FqName) it.next())) {
                    return true;
                }
            }
            return false;
        }

        private final boolean matchUnder(FirDeclaration declaration, Set<FqName> annotations) {
            Set set = FirPredicateBasedProviderImpl.this.cache.getAnnotationsOfUnderAnnotated().get(declaration);
            set.getClass();
            Set set2 = set;
            if ((set2 instanceof Collection) && set2.isEmpty()) {
                return false;
            }
            Iterator it = set2.iterator();
            while (it.hasNext()) {
                if (annotations.contains((FqName) it.next())) {
                    return true;
                }
            }
            return false;
        }

        private final boolean matchWith(FirDeclaration declaration, Set<FqName> annotations) {
            FirDeclarationOrigin origin = declaration.getOrigin();
            if (Intrinsics.areEqual(origin, FirDeclarationOrigin.Library.INSTANCE) || (origin instanceof FirDeclarationOrigin.Java)) {
                return matchNonIndexedDeclaration(declaration, annotations);
            }
            boolean z = (declaration instanceof FirClass) && ((FirClass) declaration).getIsLocal();
            if (z) {
                return matchNonIndexedDeclaration(declaration, annotations);
            }
            if (z) {
                bu8.a();
                return false;
            }
            Set set = FirPredicateBasedProviderImpl.this.cache.getAnnotationsOfDeclaration().get(declaration);
            set.getClass();
            Set set2 = set;
            if ((set2 instanceof Collection) && set2.isEmpty()) {
                return false;
            }
            Iterator it = set2.iterator();
            while (it.hasNext()) {
                if (annotations.contains((FqName) it.next())) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor
        public Boolean visitAncestorAnnotatedWith(AbstractPredicate.AncestorAnnotatedWith<P> predicate, FirDeclaration data) {
            predicate.getClass();
            data.getClass();
            return Boolean.valueOf(matchUnder(data, predicate.getAnnotations()));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor
        public Boolean visitAnd(AbstractPredicate.And<P> predicate, FirDeclaration data) {
            predicate.getClass();
            data.getClass();
            return Boolean.valueOf(((Boolean) predicate.getA().accept(this, data)).booleanValue() && ((Boolean) predicate.getB().accept(this, data)).booleanValue());
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor
        public Boolean visitAnnotatedWith(AbstractPredicate.AnnotatedWith<P> predicate, FirDeclaration data) {
            predicate.getClass();
            data.getClass();
            return Boolean.valueOf(matchWith(data, predicate.getAnnotations()));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor
        public Boolean visitHasAnnotatedWith(AbstractPredicate.HasAnnotatedWith<P> predicate, FirDeclaration data) {
            predicate.getClass();
            data.getClass();
            return Boolean.valueOf(matchHasAnnotatedWith(data, predicate.getAnnotations()));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor
        public Boolean visitMetaAnnotatedWith(AbstractPredicate.MetaAnnotatedWith<P> predicate, FirDeclaration data) {
            predicate.getClass();
            data.getClass();
            List<FirAnnotation> annotations = data.getAnnotations();
            FirPredicateBasedProviderImpl firPredicateBasedProviderImpl = FirPredicateBasedProviderImpl.this;
            boolean z = false;
            if (!(annotations instanceof Collection) || !annotations.isEmpty()) {
                Iterator<T> it = annotations.iterator();
                while (it.hasNext()) {
                    if (FirPredicateBasedProviderImplKt.markedWithMetaAnnotation((FirAnnotation) it.next(), firPredicateBasedProviderImpl.session, data, predicate.getMetaAnnotations(), predicate.getIncludeItself())) {
                        z = true;
                        break;
                    }
                }
            }
            return Boolean.valueOf(z);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor
        public Boolean visitOr(AbstractPredicate.Or<P> predicate, FirDeclaration data) {
            predicate.getClass();
            data.getClass();
            return Boolean.valueOf(((Boolean) predicate.getA().accept(this, data)).booleanValue() || ((Boolean) predicate.getB().accept(this, data)).booleanValue());
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor
        public Boolean visitParentAnnotatedWith(AbstractPredicate.ParentAnnotatedWith<P> predicate, FirDeclaration data) {
            predicate.getClass();
            data.getClass();
            return Boolean.valueOf(matchParentWith(data, predicate.getAnnotations()));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.PredicateVisitor
        public Boolean visitPredicate(AbstractPredicate<P> predicate, FirDeclaration data) {
            predicate.getClass();
            data.getClass();
            throw new IllegalStateException("Should not be there");
        }
    }

    public FirPredicateBasedProviderImpl(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.registeredPluginAnnotations = FirRegisteredPluginAnnotationsKt.getRegisteredPluginAnnotations(firSession);
        this.cache = new Cache();
        this.declarationPredicateMatcher = new Matcher<>();
        this.lookupPredicateMatcher = new Matcher<>();
    }

    private final void registerOwnersDeclarations(FirDeclaration declaration, PersistentList<? extends FirDeclaration> owners) {
        FirDeclaration firDeclaration = (FirDeclaration) CollectionsKt.lastOrNull(owners);
        if (firDeclaration == null) {
            return;
        }
        Set set = this.cache.getAnnotationsOfDeclaration().get(firDeclaration);
        Set set2 = this.cache.getAnnotationsOfUnderAnnotated().get(firDeclaration);
        set.getClass();
        Set set3 = set;
        Iterator it = set3.iterator();
        while (it.hasNext()) {
            this.cache.getDeclarationsParentAnnotated().put((FqName) it.next(), declaration);
        }
        this.cache.getAnnotationsOfParentAnnotated().putAll(declaration, set3);
        set2.getClass();
        Set setPlus = SetsKt.plus(set, set2);
        Iterator it2 = setPlus.iterator();
        while (it2.hasNext()) {
            this.cache.getDeclarationsUnderAnnotated().put((FqName) it2.next(), declaration);
        }
        this.cache.getAnnotationsOfUnderAnnotated().putAll(declaration, setPlus);
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    public boolean fileHasPluginAnnotations(FirFile file) {
        file.getClass();
        return this.cache.getFilesWithPluginAnnotations().contains(file);
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    public List<FirBasedSymbol<?>> getOwnersOfDeclaration(FirDeclaration declaration) {
        declaration.getClass();
        PersistentList<FirDeclaration> persistentList = this.cache.getOwnersForDeclaration().get(declaration);
        if (persistentList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(persistentList, 10));
        Iterator it = persistentList.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirDeclaration) it.next()).getSymbol());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    public List<FirBasedSymbol<?>> getSymbolsByPredicate(LookupPredicate predicate) {
        predicate.getClass();
        Set<FqName> annotations = predicate.getAnnotations();
        if (annotations.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (FqName fqName : annotations) {
            Collection collection = this.cache.getDeclarationByAnnotation().get(fqName);
            collection.getClass();
            Collection collection2 = this.cache.getDeclarationsUnderAnnotated().get(fqName);
            collection2.getClass();
            CollectionsKt.addAll(linkedHashSet, CollectionsKt.plus(collection, collection2));
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : linkedHashSet) {
            FirDeclaration firDeclaration = (FirDeclaration) obj;
            firDeclaration.getClass();
            if (matches(predicate, firDeclaration)) {
                arrayList.add(obj);
            }
        }
        ArrayList<FirBasedSymbol<?>> arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((FirDeclaration) it.next()).getSymbol());
        }
        for (FirBasedSymbol<?> firBasedSymbol : arrayList2) {
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(this.session);
            if (lookupTracker != null) {
                lookupTracker.recordDirtyDeclaration(firBasedSymbol);
            }
        }
        return arrayList2;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    public boolean matches(AbstractPredicate<?> predicate, FirDeclaration declaration) {
        predicate.getClass();
        declaration.getClass();
        FirSession session = declaration.getModuleData().getSession();
        if (session.getKind() == FirSession.Kind.Source && session != this.session) {
            return FirPredicateBasedProviderKt.getPredicateBasedProvider(session).matches(predicate, declaration);
        }
        if (predicate instanceof DeclarationPredicate) {
            return ((Boolean) ((DeclarationPredicate) predicate).accept(this.declarationPredicateMatcher, declaration)).booleanValue();
        }
        if (predicate instanceof LookupPredicate) {
            return ((Boolean) ((LookupPredicate) predicate).accept(this.lookupPredicateMatcher, declaration)).booleanValue();
        }
        bu8.a();
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider
    @FirExtensionApiInternals
    public void registerAnnotatedDeclaration(FirDeclaration declaration, PersistentList<? extends FirDeclaration> owners) {
        declaration.getClass();
        owners.getClass();
        this.cache.getOwnersForDeclaration().put(declaration, owners);
        registerOwnersDeclarations(declaration, owners);
        if (declaration.getAnnotations().isEmpty()) {
            return;
        }
        List<FirAnnotation> annotations = declaration.getAnnotations();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = annotations.iterator();
        while (it.hasNext()) {
            FqName fqName = CallableIdUtilsKt.fqName((FirAnnotation) it.next(), this.session);
            if (fqName != null) {
                arrayList.add(fqName);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (this.registeredPluginAnnotations.getAnnotations().contains((FqName) obj)) {
                arrayList2.add(obj);
            }
        }
        if (arrayList2.isEmpty()) {
            arrayList2 = null;
        }
        if (arrayList2 == null) {
            return;
        }
        FirDeclaration firDeclaration = (FirDeclaration) CollectionsKt.lastOrNull(owners);
        if (firDeclaration != null) {
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                this.cache.getDeclarationsHasAnnotated().put((FqName) it2.next(), firDeclaration);
            }
            this.cache.getAnnotationsOfHasAnnotated().putAll(firDeclaration, arrayList2);
        }
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            this.cache.getDeclarationByAnnotation().put((FqName) it3.next(), declaration);
        }
        this.cache.getAnnotationsOfDeclaration().putAll(declaration, arrayList2);
        Object objFirst = CollectionsKt.first(owners);
        objFirst.getClass();
        this.cache.getFilesWithPluginAnnotations().add((FirFile) objFirst);
    }
}

package org.jetbrains.kotlin.fir.types;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0007\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\b0\u0007j\u0002`\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\n\u001a\u001e\u0012\u0014\u0012\u0012\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\b0\u0007j\u0002`\t\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR-\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0014\u0012\u0012\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\b0\u0007j\u0002`\t0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes;", "", "<init>", "()V", "compilerAttributeByClassId", "", "Lorg/jetbrains/kotlin/name/ClassId;", "Lkotlin/reflect/KClass;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributeKey;", "classIdByCompilerAttributeKey", "getClassIdByCompilerAttributeKey", "()Ljava/util/Map;", "compilerAttributeKeyByFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getCompilerAttributeKeyByFqName", "Exact", "NoInfer", "EnhancedNullability", "ExtensionFunctionType", "RawType", "ContextFunctionTypeParams", "UnsafeVariance", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CompilerConeAttributes {
    public static final CompilerConeAttributes INSTANCE = new CompilerConeAttributes();
    private static final Map<KClass<? extends ConeAttribute<?>>, ClassId> classIdByCompilerAttributeKey;
    private static final Map<ClassId, KClass<? extends ConeAttribute<?>>> compilerAttributeByClassId;
    private static final Map<FqName, KClass<? extends ConeAttribute<?>>> compilerAttributeKeyByFqName;

    static {
        Exact exact = Exact.INSTANCE;
        Pair pair = TuplesKt.to(exact.getANNOTATION_CLASS_ID(), exact.getKey());
        NoInfer noInfer = NoInfer.INSTANCE;
        Pair pair2 = TuplesKt.to(noInfer.getANNOTATION_CLASS_ID(), noInfer.getKey());
        EnhancedNullability enhancedNullability = EnhancedNullability.INSTANCE;
        Pair pair3 = TuplesKt.to(enhancedNullability.getANNOTATION_CLASS_ID(), enhancedNullability.getKey());
        ExtensionFunctionType extensionFunctionType = ExtensionFunctionType.INSTANCE;
        Pair pair4 = TuplesKt.to(extensionFunctionType.getANNOTATION_CLASS_ID(), extensionFunctionType.getKey());
        UnsafeVariance unsafeVariance = UnsafeVariance.INSTANCE;
        Map<ClassId, KClass<? extends ConeAttribute<?>>> mapMapOf = MapsKt.mapOf(new Pair[]{pair, pair2, pair3, pair4, TuplesKt.to(unsafeVariance.getANNOTATION_CLASS_ID(), unsafeVariance.getKey()), TuplesKt.to(ContextFunctionTypeParams.INSTANCE.getANNOTATION_CLASS_ID(), Reflection.getOrCreateKotlinClass(ContextFunctionTypeParams.class))});
        compilerAttributeByClassId = mapMapOf;
        Set<Map.Entry<ClassId, KClass<? extends ConeAttribute<?>>>> setEntrySet = mapMapOf.entrySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10)), 16));
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put((KClass) entry.getValue(), (ClassId) entry.getKey());
        }
        classIdByCompilerAttributeKey = linkedHashMap;
        Map<ClassId, KClass<? extends ConeAttribute<?>>> map = compilerAttributeByClassId;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        Iterator<T> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            linkedHashMap2.put(((ClassId) entry2.getKey()).asSingleFqName(), entry2.getValue());
        }
        compilerAttributeKeyByFqName = linkedHashMap2;
    }

    private CompilerConeAttributes() {
    }

    public final Map<KClass<? extends ConeAttribute<?>>, ClassId> getClassIdByCompilerAttributeKey() {
        return classIdByCompilerAttributeKey;
    }

    public final Map<FqName, KClass<? extends ConeAttribute<?>>> getCompilerAttributeKeyByFqName() {
        return compilerAttributeKeyByFqName;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\n\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ContextFunctionTypeParams;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "contextParameterNumber", "", "<init>", "(I)V", "getContextParameterNumber", "()I", "union", "other", "intersect", "add", "isSubtypeOf", "", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "toString", "", "Companion", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ContextFunctionTypeParams extends ConeAttribute<ContextFunctionTypeParams> {
        private final int contextParameterNumber;
        private final KClass<? extends ContextFunctionTypeParams> key = Reflection.getOrCreateKotlinClass(ContextFunctionTypeParams.class);

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final ClassId ANNOTATION_CLASS_ID = ClassId.INSTANCE.topLevel(StandardNames.FqNames.contextFunctionTypeParams);

        public ContextFunctionTypeParams(int i) {
            this.contextParameterNumber = i;
        }

        public final int getContextParameterNumber() {
            return this.contextParameterNumber;
        }

        public boolean getKeepInInferredDeclarationType() {
            return true;
        }

        public KClass<? extends ContextFunctionTypeParams> getKey() {
            return this.key;
        }

        public String toString() {
            return "@" + StandardNames.FqNames.contextFunctionTypeParams.shortName().asString() + '(' + this.contextParameterNumber + ')';
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ContextFunctionTypeParams$Companion;", "", "<init>", "()V", "ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final ClassId getANNOTATION_CLASS_ID() {
                return ContextFunctionTypeParams.ANNOTATION_CLASS_ID;
            }

            private Companion() {
            }
        }

        public ContextFunctionTypeParams add(ContextFunctionTypeParams other) {
            return this;
        }

        public ContextFunctionTypeParams intersect(ContextFunctionTypeParams other) {
            return this;
        }

        public boolean isSubtypeOf(ContextFunctionTypeParams other) {
            return true;
        }

        public ContextFunctionTypeParams union(ContextFunctionTypeParams other) {
            return other;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\n\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$EnhancedNullability;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "<init>", "()V", "ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "union", "other", "intersect", "add", "isSubtypeOf", "", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "implementsEquality", "getImplementsEquality", "toString", "", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class EnhancedNullability extends ConeAttribute<EnhancedNullability> {
        public static final EnhancedNullability INSTANCE = new EnhancedNullability();
        private static final ClassId ANNOTATION_CLASS_ID = StandardClassIds.Annotations.INSTANCE.getEnhancedNullability();
        private static final KClass<? extends EnhancedNullability> key = Reflection.getOrCreateKotlinClass(EnhancedNullability.class);

        private EnhancedNullability() {
        }

        public final ClassId getANNOTATION_CLASS_ID() {
            return ANNOTATION_CLASS_ID;
        }

        public boolean getImplementsEquality() {
            return true;
        }

        public boolean getKeepInInferredDeclarationType() {
            return true;
        }

        public KClass<? extends EnhancedNullability> getKey() {
            return key;
        }

        public String toString() {
            return "@EnhancedNullability";
        }

        public EnhancedNullability add(EnhancedNullability other) {
            return this;
        }

        public EnhancedNullability intersect(EnhancedNullability other) {
            return this;
        }

        public boolean isSubtypeOf(EnhancedNullability other) {
            return true;
        }

        public EnhancedNullability union(EnhancedNullability other) {
            return other;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\n\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$Exact;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "<init>", "()V", "ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "union", "other", "intersect", "add", "isSubtypeOf", "", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "toString", "", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Exact extends ConeAttribute<Exact> {
        private static final ClassId ANNOTATION_CLASS_ID;
        public static final Exact INSTANCE = new Exact();
        private static final KClass<? extends Exact> key;

        static {
            FqName fqName = new FqName("kotlin.internal");
            Name nameIdentifier = Name.identifier("Exact");
            nameIdentifier.getClass();
            ANNOTATION_CLASS_ID = new ClassId(fqName, nameIdentifier);
            key = Reflection.getOrCreateKotlinClass(Exact.class);
        }

        private Exact() {
        }

        public final ClassId getANNOTATION_CLASS_ID() {
            return ANNOTATION_CLASS_ID;
        }

        public boolean getKeepInInferredDeclarationType() {
            return false;
        }

        public KClass<? extends Exact> getKey() {
            return key;
        }

        public String toString() {
            return "@Exact";
        }

        public Exact add(Exact other) {
            return this;
        }

        public Exact intersect(Exact other) {
            return null;
        }

        public boolean isSubtypeOf(Exact other) {
            return true;
        }

        public Exact union(Exact other) {
            return null;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\n\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ExtensionFunctionType;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "<init>", "()V", "ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "union", "other", "intersect", "add", "isSubtypeOf", "", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "toString", "", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ExtensionFunctionType extends ConeAttribute<ExtensionFunctionType> {
        private static final ClassId ANNOTATION_CLASS_ID;
        public static final ExtensionFunctionType INSTANCE = new ExtensionFunctionType();
        private static final KClass<? extends ExtensionFunctionType> key;

        static {
            FqName fqName = new FqName("kotlin");
            Name nameIdentifier = Name.identifier("ExtensionFunctionType");
            nameIdentifier.getClass();
            ANNOTATION_CLASS_ID = new ClassId(fqName, nameIdentifier);
            key = Reflection.getOrCreateKotlinClass(ExtensionFunctionType.class);
        }

        private ExtensionFunctionType() {
        }

        public final ClassId getANNOTATION_CLASS_ID() {
            return ANNOTATION_CLASS_ID;
        }

        public boolean getKeepInInferredDeclarationType() {
            return true;
        }

        public KClass<? extends ExtensionFunctionType> getKey() {
            return key;
        }

        public String toString() {
            return "@ExtensionFunctionType";
        }

        public ExtensionFunctionType add(ExtensionFunctionType other) {
            return this;
        }

        public ExtensionFunctionType intersect(ExtensionFunctionType other) {
            return this;
        }

        public boolean isSubtypeOf(ExtensionFunctionType other) {
            return true;
        }

        public ExtensionFunctionType union(ExtensionFunctionType other) {
            return other;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\n\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$NoInfer;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "<init>", "()V", "ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "union", "other", "intersect", "add", "isSubtypeOf", "", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "toString", "", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class NoInfer extends ConeAttribute<NoInfer> {
        public static final NoInfer INSTANCE = new NoInfer();
        private static final ClassId ANNOTATION_CLASS_ID = StandardClassIds.Annotations.INSTANCE.getNoInfer();
        private static final KClass<? extends NoInfer> key = Reflection.getOrCreateKotlinClass(NoInfer.class);

        private NoInfer() {
        }

        public final ClassId getANNOTATION_CLASS_ID() {
            return ANNOTATION_CLASS_ID;
        }

        public boolean getKeepInInferredDeclarationType() {
            return false;
        }

        public KClass<? extends NoInfer> getKey() {
            return key;
        }

        public String toString() {
            return "@NoInfer";
        }

        public NoInfer add(NoInfer other) {
            return this;
        }

        public NoInfer intersect(NoInfer other) {
            return null;
        }

        public boolean isSubtypeOf(NoInfer other) {
            return true;
        }

        public NoInfer union(NoInfer other) {
            return null;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u0007\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\b\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u001c\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$RawType;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "<init>", "()V", "union", "other", "intersect", "add", "isSubtypeOf", "", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "toString", "", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class RawType extends ConeAttribute<RawType> {
        public static final RawType INSTANCE = new RawType();
        private static final KClass<? extends RawType> key = Reflection.getOrCreateKotlinClass(RawType.class);

        private RawType() {
        }

        public boolean getKeepInInferredDeclarationType() {
            return true;
        }

        public KClass<? extends RawType> getKey() {
            return key;
        }

        public String toString() {
            return "Raw type";
        }

        public RawType add(RawType other) {
            return this;
        }

        public RawType intersect(RawType other) {
            return other;
        }

        public boolean isSubtypeOf(RawType other) {
            return true;
        }

        public RawType union(RawType other) {
            return other;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\n\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$UnsafeVariance;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "<init>", "()V", "ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "union", "other", "intersect", "add", "isSubtypeOf", "", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "toString", "", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class UnsafeVariance extends ConeAttribute<UnsafeVariance> {
        private static final ClassId ANNOTATION_CLASS_ID;
        public static final UnsafeVariance INSTANCE = new UnsafeVariance();
        private static final KClass<? extends UnsafeVariance> key;

        static {
            FqName fqName = new FqName("kotlin");
            Name nameIdentifier = Name.identifier("UnsafeVariance");
            nameIdentifier.getClass();
            ANNOTATION_CLASS_ID = new ClassId(fqName, nameIdentifier);
            key = Reflection.getOrCreateKotlinClass(UnsafeVariance.class);
        }

        private UnsafeVariance() {
        }

        public final ClassId getANNOTATION_CLASS_ID() {
            return ANNOTATION_CLASS_ID;
        }

        public boolean getKeepInInferredDeclarationType() {
            return false;
        }

        public KClass<? extends UnsafeVariance> getKey() {
            return key;
        }

        public String toString() {
            return "@UnsafeVariance";
        }

        public UnsafeVariance add(UnsafeVariance other) {
            return this;
        }

        public UnsafeVariance intersect(UnsafeVariance other) {
            return null;
        }

        public boolean isSubtypeOf(UnsafeVariance other) {
            return true;
        }

        public UnsafeVariance union(UnsafeVariance other) {
            return null;
        }
    }
}

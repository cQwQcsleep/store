package org.jetbrains.kotlin.name;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b?\n\u0002\u0010$\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003UVWB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007R\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000eR\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000eR\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0013R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0013R\u0011\u0010(\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0013R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0013R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0011\u00100\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0007R\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0010\u00104\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010H\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010I\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010K\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0L8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010P\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Q\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010S\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0L8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lorg/jetbrains/kotlin/name/JvmStandardClassIds;", Argument.Delimiters.none, "<init>", "()V", "BASE_JVM_PACKAGE", "Lorg/jetbrains/kotlin/name/FqName;", "getBASE_JVM_PACKAGE", "()Lorg/jetbrains/kotlin/name/FqName;", "JVM_NAME", "JVM_NAME_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "JVM_NAME_SHORT", Argument.Delimiters.none, "getJVM_NAME_SHORT", "()Ljava/lang/String;", "JVM_EXPOSE_BOXED_ANNOTATION_FQ_NAME", "getJVM_EXPOSE_BOXED_ANNOTATION_FQ_NAME", "JVM_EXPOSE_BOXED_ANNOTATION_CLASS_ID", "getJVM_EXPOSE_BOXED_ANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "JVM_MULTIFILE_CLASS", "getJVM_MULTIFILE_CLASS", "JVM_MULTIFILE_CLASS_ID", "getJVM_MULTIFILE_CLASS_ID", "JVM_MULTIFILE_CLASS_SHORT", "getJVM_MULTIFILE_CLASS_SHORT", "JVM_PACKAGE_NAME", "getJVM_PACKAGE_NAME", "JVM_PACKAGE_NAME_SHORT", "getJVM_PACKAGE_NAME_SHORT", "JVM_DEFAULT_FQ_NAME", "getJVM_DEFAULT_FQ_NAME", "JVM_DEFAULT_CLASS_ID", "getJVM_DEFAULT_CLASS_ID", "JVM_DEFAULT_WITHOUT_COMPATIBILITY_FQ_NAME", "getJVM_DEFAULT_WITHOUT_COMPATIBILITY_FQ_NAME", "JVM_DEFAULT_WITH_COMPATIBILITY_FQ_NAME", "getJVM_DEFAULT_WITH_COMPATIBILITY_FQ_NAME", "JVM_DEFAULT_WITHOUT_COMPATIBILITY_CLASS_ID", "getJVM_DEFAULT_WITHOUT_COMPATIBILITY_CLASS_ID", "JVM_DEFAULT_WITH_COMPATIBILITY_CLASS_ID", "getJVM_DEFAULT_WITH_COMPATIBILITY_CLASS_ID", "JVM_OVERLOADS_FQ_NAME", "getJVM_OVERLOADS_FQ_NAME", "JVM_OVERLOADS_CLASS_ID", "getJVM_OVERLOADS_CLASS_ID", "JVM_STATIC_FQ_NAME", "getJVM_STATIC_FQ_NAME", "JVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME", "getJVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME", "JVM_WILDCARD_ANNOTATION_FQ_NAME", "getJVM_WILDCARD_ANNOTATION_FQ_NAME", "JVM_SERIALIZABLE_LAMBDA_ANNOTATION_FQ_NAME", "JVM_SYNTHETIC_ANNOTATION_FQ_NAME", "JVM_SYNTHETIC_ANNOTATION_CLASS_ID", "JVM_RECORD_ANNOTATION_FQ_NAME", "JVM_RECORD_ANNOTATION_CLASS_ID", "SYNCHRONIZED_ANNOTATION_FQ_NAME", "SYNCHRONIZED_ANNOTATION_CLASS_ID", "THROWS_ANNOTATION_FQ_NAME", "THROWS_ANNOTATION_CLASS_ID", "STRICTFP_ANNOTATION_FQ_NAME", "STRICTFP_ANNOTATION_CLASS_ID", "VOLATILE_ANNOTATION_FQ_NAME", "VOLATILE_ANNOTATION_CLASS_ID", "TRANSIENT_ANNOTATION_FQ_NAME", "TRANSIENT_ANNOTATION_CLASS_ID", "ATOMIC_BOOLEAN_FQ_NAME", "ATOMIC_BOOLEAN_CLASS_ID", "ATOMIC_INTEGER_FQ_NAME", "ATOMIC_INTEGER_CLASS_ID", "ATOMIC_LONG_FQ_NAME", "ATOMIC_LONG_CLASS_ID", "ATOMIC_REFERENCE_FQ_NAME", "ATOMIC_REFERENCE_CLASS_ID", "atomicByPrimitive", Argument.Delimiters.none, "ATOMIC_REFERENCE_ARRAY_FQ_NAME", "ATOMIC_REFERENCE_ARRAY_CLASS_ID", "ATOMIC_INTEGER_ARRAY_FQ_NAME", "ATOMIC_INTEGER_ARRAY_CLASS_ID", "ATOMIC_LONG_ARRAY_FQ_NAME", "ATOMIC_LONG_ARRAY_CLASS_ID", "atomicArrayByPrimitive", "MULTIFILE_PART_NAME_DELIMITER", "Annotations", "Java", "Callables", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmStandardClassIds {
    public static final ClassId ATOMIC_BOOLEAN_CLASS_ID;
    public static final FqName ATOMIC_BOOLEAN_FQ_NAME;
    public static final ClassId ATOMIC_INTEGER_ARRAY_CLASS_ID;
    public static final FqName ATOMIC_INTEGER_ARRAY_FQ_NAME;
    public static final ClassId ATOMIC_INTEGER_CLASS_ID;
    public static final FqName ATOMIC_INTEGER_FQ_NAME;
    public static final ClassId ATOMIC_LONG_ARRAY_CLASS_ID;
    public static final FqName ATOMIC_LONG_ARRAY_FQ_NAME;
    public static final ClassId ATOMIC_LONG_CLASS_ID;
    public static final FqName ATOMIC_LONG_FQ_NAME;
    public static final ClassId ATOMIC_REFERENCE_ARRAY_CLASS_ID;
    public static final FqName ATOMIC_REFERENCE_ARRAY_FQ_NAME;
    public static final ClassId ATOMIC_REFERENCE_CLASS_ID;
    public static final FqName ATOMIC_REFERENCE_FQ_NAME;
    private static final FqName BASE_JVM_PACKAGE;
    public static final JvmStandardClassIds INSTANCE = new JvmStandardClassIds();
    private static final ClassId JVM_DEFAULT_CLASS_ID;
    private static final FqName JVM_DEFAULT_FQ_NAME;
    private static final ClassId JVM_DEFAULT_WITHOUT_COMPATIBILITY_CLASS_ID;
    private static final FqName JVM_DEFAULT_WITHOUT_COMPATIBILITY_FQ_NAME;
    private static final ClassId JVM_DEFAULT_WITH_COMPATIBILITY_CLASS_ID;
    private static final FqName JVM_DEFAULT_WITH_COMPATIBILITY_FQ_NAME;
    private static final ClassId JVM_EXPOSE_BOXED_ANNOTATION_CLASS_ID;
    private static final FqName JVM_EXPOSE_BOXED_ANNOTATION_FQ_NAME;
    private static final FqName JVM_MULTIFILE_CLASS;
    private static final ClassId JVM_MULTIFILE_CLASS_ID;
    private static final String JVM_MULTIFILE_CLASS_SHORT;
    public static final FqName JVM_NAME;
    public static final ClassId JVM_NAME_CLASS_ID;
    private static final String JVM_NAME_SHORT;
    private static final ClassId JVM_OVERLOADS_CLASS_ID;
    private static final FqName JVM_OVERLOADS_FQ_NAME;
    private static final FqName JVM_PACKAGE_NAME;
    private static final String JVM_PACKAGE_NAME_SHORT;
    public static final ClassId JVM_RECORD_ANNOTATION_CLASS_ID;
    public static final FqName JVM_RECORD_ANNOTATION_FQ_NAME;
    public static final FqName JVM_SERIALIZABLE_LAMBDA_ANNOTATION_FQ_NAME;
    private static final FqName JVM_STATIC_FQ_NAME;
    private static final FqName JVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME;
    public static final ClassId JVM_SYNTHETIC_ANNOTATION_CLASS_ID;
    public static final FqName JVM_SYNTHETIC_ANNOTATION_FQ_NAME;
    private static final FqName JVM_WILDCARD_ANNOTATION_FQ_NAME;
    public static final String MULTIFILE_PART_NAME_DELIMITER = "__";
    public static final ClassId STRICTFP_ANNOTATION_CLASS_ID;
    public static final FqName STRICTFP_ANNOTATION_FQ_NAME;
    public static final ClassId SYNCHRONIZED_ANNOTATION_CLASS_ID;
    public static final FqName SYNCHRONIZED_ANNOTATION_FQ_NAME;
    public static final ClassId THROWS_ANNOTATION_CLASS_ID;
    public static final FqName THROWS_ANNOTATION_FQ_NAME;
    public static final ClassId TRANSIENT_ANNOTATION_CLASS_ID;
    public static final FqName TRANSIENT_ANNOTATION_FQ_NAME;
    public static final ClassId VOLATILE_ANNOTATION_CLASS_ID;
    public static final FqName VOLATILE_ANNOTATION_FQ_NAME;
    public static final Map<ClassId, ClassId> atomicArrayByPrimitive;
    public static final Map<ClassId, ClassId> atomicByPrimitive;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b'\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/name/JvmStandardClassIds$Callables;", Argument.Delimiters.none, "<init>", "()V", "JavaClass", "Lorg/jetbrains/kotlin/name/CallableId;", "getJavaClass", "()Lorg/jetbrains/kotlin/name/CallableId;", "atomicReferenceCompareAndSet", "getAtomicReferenceCompareAndSet", "atomicReferenceWeakCompareAndSet", "getAtomicReferenceWeakCompareAndSet", "atomicReferenceWeakCompareAndSetAcquire", "getAtomicReferenceWeakCompareAndSetAcquire", "atomicReferenceWeakCompareAndSetRelease", "getAtomicReferenceWeakCompareAndSetRelease", "atomicReferenceWeakCompareAndSetPlain", "getAtomicReferenceWeakCompareAndSetPlain", "atomicReferenceWeakCompareAndSetVolatile", "getAtomicReferenceWeakCompareAndSetVolatile", "atomicReferenceCompareAndExchange", "getAtomicReferenceCompareAndExchange", "atomicReferenceCompareAndExchangeAcquire", "getAtomicReferenceCompareAndExchangeAcquire", "atomicReferenceCompareAndExchangeRelease", "getAtomicReferenceCompareAndExchangeRelease", "atomicReferenceArrayCompareAndSet", "getAtomicReferenceArrayCompareAndSet", "atomicReferenceArrayWeakCompareAndSet", "getAtomicReferenceArrayWeakCompareAndSet", "atomicReferenceArrayWeakCompareAndSetAcquire", "getAtomicReferenceArrayWeakCompareAndSetAcquire", "atomicReferenceArrayWeakCompareAndSetRelease", "getAtomicReferenceArrayWeakCompareAndSetRelease", "atomicReferenceArrayWeakCompareAndSetPlain", "getAtomicReferenceArrayWeakCompareAndSetPlain", "atomicReferenceArrayWeakCompareAndSetVolatile", "getAtomicReferenceArrayWeakCompareAndSetVolatile", "atomicReferenceArrayCompareAndExchange", "getAtomicReferenceArrayCompareAndExchange", "atomicReferenceArrayCompareAndExchangeAcquire", "getAtomicReferenceArrayCompareAndExchangeAcquire", "atomicReferenceArrayCompareAndExchangeRelease", "getAtomicReferenceArrayCompareAndExchangeRelease", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Callables {
        public static final Callables INSTANCE = new Callables();
        private static final CallableId JavaClass;
        private static final CallableId atomicReferenceArrayCompareAndExchange;
        private static final CallableId atomicReferenceArrayCompareAndExchangeAcquire;
        private static final CallableId atomicReferenceArrayCompareAndExchangeRelease;
        private static final CallableId atomicReferenceArrayCompareAndSet;
        private static final CallableId atomicReferenceArrayWeakCompareAndSet;
        private static final CallableId atomicReferenceArrayWeakCompareAndSetAcquire;
        private static final CallableId atomicReferenceArrayWeakCompareAndSetPlain;
        private static final CallableId atomicReferenceArrayWeakCompareAndSetRelease;
        private static final CallableId atomicReferenceArrayWeakCompareAndSetVolatile;
        private static final CallableId atomicReferenceCompareAndExchange;
        private static final CallableId atomicReferenceCompareAndExchangeAcquire;
        private static final CallableId atomicReferenceCompareAndExchangeRelease;
        private static final CallableId atomicReferenceCompareAndSet;
        private static final CallableId atomicReferenceWeakCompareAndSet;
        private static final CallableId atomicReferenceWeakCompareAndSetAcquire;
        private static final CallableId atomicReferenceWeakCompareAndSetPlain;
        private static final CallableId atomicReferenceWeakCompareAndSetRelease;
        private static final CallableId atomicReferenceWeakCompareAndSetVolatile;

        static {
            FqName base_jvm_package = JvmStandardClassIds.INSTANCE.getBASE_JVM_PACKAGE();
            Name nameIdentifier = Name.identifier("javaClass");
            nameIdentifier.getClass();
            JavaClass = new CallableId(base_jvm_package, nameIdentifier);
            ClassId classId = JvmStandardClassIds.ATOMIC_REFERENCE_CLASS_ID;
            Name nameIdentifier2 = Name.identifier("compareAndSet");
            nameIdentifier2.getClass();
            atomicReferenceCompareAndSet = new CallableId(classId, nameIdentifier2);
            Name nameIdentifier3 = Name.identifier("weakCompareAndSet");
            nameIdentifier3.getClass();
            atomicReferenceWeakCompareAndSet = new CallableId(classId, nameIdentifier3);
            Name nameIdentifier4 = Name.identifier("weakCompareAndSetAcquire");
            nameIdentifier4.getClass();
            atomicReferenceWeakCompareAndSetAcquire = new CallableId(classId, nameIdentifier4);
            Name nameIdentifier5 = Name.identifier("weakCompareAndSetRelease");
            nameIdentifier5.getClass();
            atomicReferenceWeakCompareAndSetRelease = new CallableId(classId, nameIdentifier5);
            Name nameIdentifier6 = Name.identifier("weakCompareAndSetPlain");
            nameIdentifier6.getClass();
            atomicReferenceWeakCompareAndSetPlain = new CallableId(classId, nameIdentifier6);
            Name nameIdentifier7 = Name.identifier("weakCompareAndSetVolatile");
            nameIdentifier7.getClass();
            atomicReferenceWeakCompareAndSetVolatile = new CallableId(classId, nameIdentifier7);
            Name nameIdentifier8 = Name.identifier("compareAndExchange");
            nameIdentifier8.getClass();
            atomicReferenceCompareAndExchange = new CallableId(classId, nameIdentifier8);
            Name nameIdentifier9 = Name.identifier("compareAndExchangeAcquire");
            nameIdentifier9.getClass();
            atomicReferenceCompareAndExchangeAcquire = new CallableId(classId, nameIdentifier9);
            Name nameIdentifier10 = Name.identifier("compareAndExchangeRelease");
            nameIdentifier10.getClass();
            atomicReferenceCompareAndExchangeRelease = new CallableId(classId, nameIdentifier10);
            ClassId classId2 = JvmStandardClassIds.ATOMIC_REFERENCE_ARRAY_CLASS_ID;
            Name nameIdentifier11 = Name.identifier("compareAndSet");
            nameIdentifier11.getClass();
            atomicReferenceArrayCompareAndSet = new CallableId(classId2, nameIdentifier11);
            Name nameIdentifier12 = Name.identifier("weakCompareAndSet");
            nameIdentifier12.getClass();
            atomicReferenceArrayWeakCompareAndSet = new CallableId(classId2, nameIdentifier12);
            Name nameIdentifier13 = Name.identifier("weakCompareAndSetAcquire");
            nameIdentifier13.getClass();
            atomicReferenceArrayWeakCompareAndSetAcquire = new CallableId(classId2, nameIdentifier13);
            Name nameIdentifier14 = Name.identifier("weakCompareAndSetRelease");
            nameIdentifier14.getClass();
            atomicReferenceArrayWeakCompareAndSetRelease = new CallableId(classId2, nameIdentifier14);
            Name nameIdentifier15 = Name.identifier("weakCompareAndSetPlain");
            nameIdentifier15.getClass();
            atomicReferenceArrayWeakCompareAndSetPlain = new CallableId(classId2, nameIdentifier15);
            Name nameIdentifier16 = Name.identifier("weakCompareAndSetVolatile");
            nameIdentifier16.getClass();
            atomicReferenceArrayWeakCompareAndSetVolatile = new CallableId(classId2, nameIdentifier16);
            Name nameIdentifier17 = Name.identifier("compareAndExchange");
            nameIdentifier17.getClass();
            atomicReferenceArrayCompareAndExchange = new CallableId(classId2, nameIdentifier17);
            Name nameIdentifier18 = Name.identifier("compareAndExchangeAcquire");
            nameIdentifier18.getClass();
            atomicReferenceArrayCompareAndExchangeAcquire = new CallableId(classId2, nameIdentifier18);
            Name nameIdentifier19 = Name.identifier("compareAndExchangeRelease");
            nameIdentifier19.getClass();
            atomicReferenceArrayCompareAndExchangeRelease = new CallableId(classId2, nameIdentifier19);
        }

        private Callables() {
        }

        public final CallableId getAtomicReferenceArrayCompareAndExchange() {
            return atomicReferenceArrayCompareAndExchange;
        }

        public final CallableId getAtomicReferenceArrayCompareAndExchangeAcquire() {
            return atomicReferenceArrayCompareAndExchangeAcquire;
        }

        public final CallableId getAtomicReferenceArrayCompareAndExchangeRelease() {
            return atomicReferenceArrayCompareAndExchangeRelease;
        }

        public final CallableId getAtomicReferenceArrayCompareAndSet() {
            return atomicReferenceArrayCompareAndSet;
        }

        public final CallableId getAtomicReferenceArrayWeakCompareAndSet() {
            return atomicReferenceArrayWeakCompareAndSet;
        }

        public final CallableId getAtomicReferenceArrayWeakCompareAndSetAcquire() {
            return atomicReferenceArrayWeakCompareAndSetAcquire;
        }

        public final CallableId getAtomicReferenceArrayWeakCompareAndSetPlain() {
            return atomicReferenceArrayWeakCompareAndSetPlain;
        }

        public final CallableId getAtomicReferenceArrayWeakCompareAndSetRelease() {
            return atomicReferenceArrayWeakCompareAndSetRelease;
        }

        public final CallableId getAtomicReferenceArrayWeakCompareAndSetVolatile() {
            return atomicReferenceArrayWeakCompareAndSetVolatile;
        }

        public final CallableId getAtomicReferenceCompareAndExchange() {
            return atomicReferenceCompareAndExchange;
        }

        public final CallableId getAtomicReferenceCompareAndExchangeAcquire() {
            return atomicReferenceCompareAndExchangeAcquire;
        }

        public final CallableId getAtomicReferenceCompareAndExchangeRelease() {
            return atomicReferenceCompareAndExchangeRelease;
        }

        public final CallableId getAtomicReferenceCompareAndSet() {
            return atomicReferenceCompareAndSet;
        }

        public final CallableId getAtomicReferenceWeakCompareAndSet() {
            return atomicReferenceWeakCompareAndSet;
        }

        public final CallableId getAtomicReferenceWeakCompareAndSetAcquire() {
            return atomicReferenceWeakCompareAndSetAcquire;
        }

        public final CallableId getAtomicReferenceWeakCompareAndSetPlain() {
            return atomicReferenceWeakCompareAndSetPlain;
        }

        public final CallableId getAtomicReferenceWeakCompareAndSetRelease() {
            return atomicReferenceWeakCompareAndSetRelease;
        }

        public final CallableId getAtomicReferenceWeakCompareAndSetVolatile() {
            return atomicReferenceWeakCompareAndSetVolatile;
        }

        public final CallableId getJavaClass() {
            return JavaClass;
        }
    }

    static {
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        FqName base_kotlin_package = standardClassIds.getBASE_KOTLIN_PACKAGE();
        Name nameIdentifier = Name.identifier("jvm");
        nameIdentifier.getClass();
        BASE_JVM_PACKAGE = base_kotlin_package.child(nameIdentifier);
        FqName fqName = new FqName("kotlin.jvm.JvmName");
        JVM_NAME = fqName;
        ClassId.Companion companion = ClassId.Companion;
        JVM_NAME_CLASS_ID = companion.topLevel(fqName);
        String strAsString = fqName.shortName().asString();
        strAsString.getClass();
        JVM_NAME_SHORT = strAsString;
        FqName fqName2 = new FqName("kotlin.jvm.JvmExposeBoxed");
        JVM_EXPOSE_BOXED_ANNOTATION_FQ_NAME = fqName2;
        JVM_EXPOSE_BOXED_ANNOTATION_CLASS_ID = companion.topLevel(fqName2);
        FqName fqName3 = new FqName("kotlin.jvm.JvmMultifileClass");
        JVM_MULTIFILE_CLASS = fqName3;
        JVM_MULTIFILE_CLASS_ID = companion.topLevel(fqName3);
        String strAsString2 = fqName3.shortName().asString();
        strAsString2.getClass();
        JVM_MULTIFILE_CLASS_SHORT = strAsString2;
        FqName fqName4 = new FqName("kotlin.jvm.JvmPackageName");
        JVM_PACKAGE_NAME = fqName4;
        String strAsString3 = fqName4.shortName().asString();
        strAsString3.getClass();
        JVM_PACKAGE_NAME_SHORT = strAsString3;
        FqName fqName5 = new FqName("kotlin.jvm.JvmDefault");
        JVM_DEFAULT_FQ_NAME = fqName5;
        JVM_DEFAULT_CLASS_ID = companion.topLevel(fqName5);
        FqName fqName6 = new FqName("kotlin.jvm.JvmDefaultWithoutCompatibility");
        JVM_DEFAULT_WITHOUT_COMPATIBILITY_FQ_NAME = fqName6;
        FqName fqName7 = new FqName("kotlin.jvm.JvmDefaultWithCompatibility");
        JVM_DEFAULT_WITH_COMPATIBILITY_FQ_NAME = fqName7;
        JVM_DEFAULT_WITHOUT_COMPATIBILITY_CLASS_ID = companion.topLevel(fqName6);
        JVM_DEFAULT_WITH_COMPATIBILITY_CLASS_ID = companion.topLevel(fqName7);
        FqName fqName8 = new FqName("kotlin.jvm.JvmOverloads");
        JVM_OVERLOADS_FQ_NAME = fqName8;
        JVM_OVERLOADS_CLASS_ID = companion.topLevel(fqName8);
        JVM_STATIC_FQ_NAME = new FqName("kotlin.jvm.JvmStatic");
        JVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME = new FqName("kotlin.jvm.JvmSuppressWildcards");
        JVM_WILDCARD_ANNOTATION_FQ_NAME = new FqName("kotlin.jvm.JvmWildcard");
        JVM_SERIALIZABLE_LAMBDA_ANNOTATION_FQ_NAME = new FqName("kotlin.jvm.JvmSerializableLambda");
        FqName fqName9 = new FqName("kotlin.jvm.JvmSynthetic");
        JVM_SYNTHETIC_ANNOTATION_FQ_NAME = fqName9;
        JVM_SYNTHETIC_ANNOTATION_CLASS_ID = companion.topLevel(fqName9);
        FqName fqName10 = new FqName("kotlin.jvm.JvmRecord");
        JVM_RECORD_ANNOTATION_FQ_NAME = fqName10;
        JVM_RECORD_ANNOTATION_CLASS_ID = companion.topLevel(fqName10);
        FqName fqName11 = new FqName("kotlin.jvm.Synchronized");
        SYNCHRONIZED_ANNOTATION_FQ_NAME = fqName11;
        SYNCHRONIZED_ANNOTATION_CLASS_ID = companion.topLevel(fqName11);
        FqName fqName12 = new FqName("kotlin.jvm.Throws");
        THROWS_ANNOTATION_FQ_NAME = fqName12;
        THROWS_ANNOTATION_CLASS_ID = companion.topLevel(fqName12);
        FqName fqName13 = new FqName("kotlin.jvm.Strictfp");
        STRICTFP_ANNOTATION_FQ_NAME = fqName13;
        STRICTFP_ANNOTATION_CLASS_ID = companion.topLevel(fqName13);
        FqName fqName14 = new FqName("kotlin.jvm.Volatile");
        VOLATILE_ANNOTATION_FQ_NAME = fqName14;
        VOLATILE_ANNOTATION_CLASS_ID = companion.topLevel(fqName14);
        FqName fqName15 = new FqName("kotlin.jvm.Transient");
        TRANSIENT_ANNOTATION_FQ_NAME = fqName15;
        TRANSIENT_ANNOTATION_CLASS_ID = companion.topLevel(fqName15);
        FqName fqName16 = new FqName("java.util.concurrent.atomic.AtomicBoolean");
        ATOMIC_BOOLEAN_FQ_NAME = fqName16;
        ClassId classId = companion.topLevel(fqName16);
        ATOMIC_BOOLEAN_CLASS_ID = classId;
        FqName fqName17 = new FqName("java.util.concurrent.atomic.AtomicInteger");
        ATOMIC_INTEGER_FQ_NAME = fqName17;
        ClassId classId2 = companion.topLevel(fqName17);
        ATOMIC_INTEGER_CLASS_ID = classId2;
        FqName fqName18 = new FqName("java.util.concurrent.atomic.AtomicLong");
        ATOMIC_LONG_FQ_NAME = fqName18;
        ClassId classId3 = companion.topLevel(fqName18);
        ATOMIC_LONG_CLASS_ID = classId3;
        FqName fqName19 = new FqName("java.util.concurrent.atomic.AtomicReference");
        ATOMIC_REFERENCE_FQ_NAME = fqName19;
        ATOMIC_REFERENCE_CLASS_ID = companion.topLevel(fqName19);
        atomicByPrimitive = MapsKt.mapOf(new Pair[]{TuplesKt.to(standardClassIds.getBoolean(), classId), TuplesKt.to(standardClassIds.getInt(), classId2), TuplesKt.to(standardClassIds.getLong(), classId3)});
        FqName fqName20 = new FqName("java.util.concurrent.atomic.AtomicReferenceArray");
        ATOMIC_REFERENCE_ARRAY_FQ_NAME = fqName20;
        ATOMIC_REFERENCE_ARRAY_CLASS_ID = companion.topLevel(fqName20);
        FqName fqName21 = new FqName("java.util.concurrent.atomic.AtomicIntegerArray");
        ATOMIC_INTEGER_ARRAY_FQ_NAME = fqName21;
        ClassId classId4 = companion.topLevel(fqName21);
        ATOMIC_INTEGER_ARRAY_CLASS_ID = classId4;
        FqName fqName22 = new FqName("java.util.concurrent.atomic.AtomicLongArray");
        ATOMIC_LONG_ARRAY_FQ_NAME = fqName22;
        ClassId classId5 = companion.topLevel(fqName22);
        ATOMIC_LONG_ARRAY_CLASS_ID = classId5;
        atomicArrayByPrimitive = MapsKt.mapOf(new Pair[]{TuplesKt.to(standardClassIds.getInt(), classId4), TuplesKt.to(standardClassIds.getLong(), classId5)});
    }

    private JvmStandardClassIds() {
    }

    public final FqName getBASE_JVM_PACKAGE() {
        return BASE_JVM_PACKAGE;
    }

    public final ClassId getJVM_DEFAULT_CLASS_ID() {
        return JVM_DEFAULT_CLASS_ID;
    }

    public final FqName getJVM_DEFAULT_FQ_NAME() {
        return JVM_DEFAULT_FQ_NAME;
    }

    public final ClassId getJVM_DEFAULT_WITHOUT_COMPATIBILITY_CLASS_ID() {
        return JVM_DEFAULT_WITHOUT_COMPATIBILITY_CLASS_ID;
    }

    public final FqName getJVM_DEFAULT_WITHOUT_COMPATIBILITY_FQ_NAME() {
        return JVM_DEFAULT_WITHOUT_COMPATIBILITY_FQ_NAME;
    }

    public final ClassId getJVM_DEFAULT_WITH_COMPATIBILITY_CLASS_ID() {
        return JVM_DEFAULT_WITH_COMPATIBILITY_CLASS_ID;
    }

    public final FqName getJVM_DEFAULT_WITH_COMPATIBILITY_FQ_NAME() {
        return JVM_DEFAULT_WITH_COMPATIBILITY_FQ_NAME;
    }

    public final ClassId getJVM_EXPOSE_BOXED_ANNOTATION_CLASS_ID() {
        return JVM_EXPOSE_BOXED_ANNOTATION_CLASS_ID;
    }

    public final FqName getJVM_EXPOSE_BOXED_ANNOTATION_FQ_NAME() {
        return JVM_EXPOSE_BOXED_ANNOTATION_FQ_NAME;
    }

    public final FqName getJVM_MULTIFILE_CLASS() {
        return JVM_MULTIFILE_CLASS;
    }

    public final ClassId getJVM_MULTIFILE_CLASS_ID() {
        return JVM_MULTIFILE_CLASS_ID;
    }

    public final String getJVM_MULTIFILE_CLASS_SHORT() {
        return JVM_MULTIFILE_CLASS_SHORT;
    }

    public final String getJVM_NAME_SHORT() {
        return JVM_NAME_SHORT;
    }

    public final ClassId getJVM_OVERLOADS_CLASS_ID() {
        return JVM_OVERLOADS_CLASS_ID;
    }

    public final FqName getJVM_OVERLOADS_FQ_NAME() {
        return JVM_OVERLOADS_FQ_NAME;
    }

    public final FqName getJVM_PACKAGE_NAME() {
        return JVM_PACKAGE_NAME;
    }

    public final String getJVM_PACKAGE_NAME_SHORT() {
        return JVM_PACKAGE_NAME_SHORT;
    }

    public final FqName getJVM_STATIC_FQ_NAME() {
        return JVM_STATIC_FQ_NAME;
    }

    public final FqName getJVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME() {
        return JVM_SUPPRESS_WILDCARDS_ANNOTATION_FQ_NAME;
    }

    public final FqName getJVM_WILDCARD_ANNOTATION_FQ_NAME() {
        return JVM_WILDCARD_ANNOTATION_FQ_NAME;
    }
}

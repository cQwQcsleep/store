package org.jetbrains.kotlin.incremental;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.backend.common.UtilsKt;
import org.jetbrains.kotlin.backend.jvm.metadata.MetadataSerializer;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.backend.jvm.FirMetadataSerializer;
import org.jetbrains.kotlin.fir.backend.jvm.FirMetadataSerializerKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.serialization.JvmStringTable;
import org.jetbrains.kotlin.modules.TargetId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000g\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001J6\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\nH\u0086\bø\u0001\u0000J\u001e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006#"}, d2 = {"org/jetbrains/kotlin/incremental/IncrementalFirCacheUtilsKt$collectNewDirtySources$visitFirFiles$1", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/jvm/metadata/MetadataSerializer;", "withMetadataSerializer", "metadata", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "data", "body", "Lkotlin/Function1;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visitFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IncrementalFirCacheUtilsKt$collectNewDirtySources$visitFirFiles$1 extends FirVisitor<Unit, List<MetadataSerializer>> {
    final /* synthetic */ SingleModuleFrontendOutput $analyzedOutput;
    final /* synthetic */ IncrementalJvmCachesManager $caches;
    final /* synthetic */ ChangesCollector $changesCollector;
    final /* synthetic */ CompilerConfiguration $configuration;
    final /* synthetic */ FirFile $file;
    final /* synthetic */ JvmSerializationBindings $globalSerializationBindings;
    final /* synthetic */ TargetId $targetId;

    public IncrementalFirCacheUtilsKt$collectNewDirtySources$visitFirFiles$1(SingleModuleFrontendOutput singleModuleFrontendOutput, JvmSerializationBindings jvmSerializationBindings, TargetId targetId, CompilerConfiguration compilerConfiguration, FirFile firFile, IncrementalJvmCachesManager incrementalJvmCachesManager, ChangesCollector changesCollector) {
        this.$analyzedOutput = singleModuleFrontendOutput;
        this.$globalSerializationBindings = jvmSerializationBindings;
        this.$targetId = targetId;
        this.$configuration = compilerConfiguration;
        this.$file = firFile;
        this.$caches = incrementalJvmCachesManager;
        this.$changesCollector = changesCollector;
    }

    /* JADX INFO: renamed from: visitAnonymousObject, reason: avoid collision after fix types in other method */
    public void visitAnonymousObject2(FirAnonymousObject anonymousObject, List<MetadataSerializer> data) {
        anonymousObject.getClass();
        data.getClass();
        visitClass2((FirClass) anonymousObject, data);
    }

    /* JADX INFO: renamed from: visitClass, reason: avoid collision after fix types in other method */
    public void visitClass2(FirClass klass, List<MetadataSerializer> data) {
        klass.getClass();
        data.getClass();
        FirMetadataSource.Class r0 = new FirMetadataSource.Class(klass);
        FirFile firFile = this.$file;
        IncrementalJvmCachesManager incrementalJvmCachesManager = this.$caches;
        ChangesCollector changesCollector = this.$changesCollector;
        FirMetadataSerializer firMetadataSerializerMakeLocalFirMetadataSerializerForMetadataSource = FirMetadataSerializerKt.makeLocalFirMetadataSerializerForMetadataSource(r0, this.$analyzedOutput.getSession(), this.$analyzedOutput.getScopeSession(), this.$globalSerializationBindings, (MetadataSerializer) CollectionsKt.lastOrNull(data), this.$targetId, this.$configuration, null);
        UtilsKt.push(data, firMetadataSerializerMakeLocalFirMetadataSerializerForMetadataSource);
        klass.acceptChildren(this, data);
        Pair pairSerialize = firMetadataSerializerMakeLocalFirMetadataSerializerForMetadataSource.serialize(r0, new FirMetadataSource.File(firFile));
        if (pairSerialize != null) {
            ProtoBuf.Class r1 = (MessageLite) pairSerialize.component1();
            JvmStringTable jvmStringTable = (JvmStringTable) pairSerialize.component2();
            IncrementalJvmCache platformCache = incrementalJvmCachesManager.getPlatformCache();
            ClassId classId = FirDeclarationUtilKt.getClassId(klass);
            r1.getClass();
            platformCache.saveFrontendClassToCache(classId, r1, jvmStringTable, (List) null, changesCollector);
        }
        UtilsKt.pop(data);
    }

    /* JADX INFO: renamed from: visitConstructor, reason: avoid collision after fix types in other method */
    public void visitConstructor2(FirConstructor constructor, List<MetadataSerializer> data) {
        constructor.getClass();
        data.getClass();
        super.visitConstructor(constructor, data);
        ((MetadataSerializer) CollectionsKt.first(data)).bindMethodMetadata(new FirMetadataSource.Function(constructor), new Method(SpecialNames.INIT.asString(), SignatureUtilsKt.computeJvmDescriptor$default(constructor, Argument.Delimiters.none, false, null, 6, null)));
    }

    /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
    public void visitElement2(FirElement element, List<MetadataSerializer> data) {
        element.getClass();
        data.getClass();
        element.acceptChildren(this, data);
    }

    /* JADX INFO: renamed from: visitFile, reason: avoid collision after fix types in other method */
    public void visitFile2(FirFile file, List<MetadataSerializer> data) {
        file.getClass();
        data.getClass();
        UtilsKt.push(data, FirMetadataSerializerKt.makeLocalFirMetadataSerializerForMetadataSource(new FirMetadataSource.File(file), this.$analyzedOutput.getSession(), this.$analyzedOutput.getScopeSession(), this.$globalSerializationBindings, (MetadataSerializer) CollectionsKt.lastOrNull(data), this.$targetId, this.$configuration, null));
        file.acceptChildren(this, data);
        UtilsKt.pop(data);
    }

    /* JADX INFO: renamed from: visitNamedFunction, reason: avoid collision after fix types in other method */
    public void visitNamedFunction2(FirNamedFunction namedFunction, List<MetadataSerializer> data) {
        namedFunction.getClass();
        data.getClass();
        MetadataSerializer metadataSerializer = (MetadataSerializer) CollectionsKt.firstOrNull(data);
        if (metadataSerializer != null) {
            super.visitFunction(namedFunction, data);
            metadataSerializer.bindMethodMetadata(new FirMetadataSource.Function(namedFunction), new Method(namedFunction.getName().asString(), SignatureUtilsKt.computeJvmDescriptor$default(namedFunction, null, false, null, 7, null)));
        }
    }

    /* JADX INFO: renamed from: visitProperty, reason: avoid collision after fix types in other method */
    public void visitProperty2(FirProperty property, List<MetadataSerializer> data) {
        property.getClass();
        data.getClass();
        property.acceptChildren(this, data);
    }

    /* JADX INFO: renamed from: visitRegularClass, reason: avoid collision after fix types in other method */
    public void visitRegularClass2(FirRegularClass regularClass, List<MetadataSerializer> data) {
        regularClass.getClass();
        data.getClass();
        visitClass2((FirClass) regularClass, data);
    }

    public final void withMetadataSerializer(FirMetadataSource metadata, List<MetadataSerializer> data, Function1<? super MetadataSerializer, Unit> body) {
        metadata.getClass();
        data.getClass();
        body.getClass();
        FirMetadataSerializer firMetadataSerializerMakeLocalFirMetadataSerializerForMetadataSource = FirMetadataSerializerKt.makeLocalFirMetadataSerializerForMetadataSource(metadata, this.$analyzedOutput.getSession(), this.$analyzedOutput.getScopeSession(), this.$globalSerializationBindings, (MetadataSerializer) CollectionsKt.lastOrNull(data), this.$targetId, this.$configuration, null);
        UtilsKt.push(data, firMetadataSerializerMakeLocalFirMetadataSerializerForMetadataSource);
        body.invoke(firMetadataSerializerMakeLocalFirMetadataSerializerForMetadataSource);
        UtilsKt.pop(data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitAnonymousObject(FirAnonymousObject firAnonymousObject, List<MetadataSerializer> list) {
        visitAnonymousObject2(firAnonymousObject, list);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, List<MetadataSerializer> list) {
        visitElement2(firElement, list);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitProperty(FirProperty firProperty, List<MetadataSerializer> list) {
        visitProperty2(firProperty, list);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitRegularClass(FirRegularClass firRegularClass, List<MetadataSerializer> list) {
        visitRegularClass2(firRegularClass, list);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitConstructor(FirConstructor firConstructor, List<MetadataSerializer> list) {
        visitConstructor2(firConstructor, list);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitNamedFunction(FirNamedFunction firNamedFunction, List<MetadataSerializer> list) {
        visitNamedFunction2(firNamedFunction, list);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitFile(FirFile firFile, List<MetadataSerializer> list) {
        visitFile2(firFile, list);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitClass(FirClass firClass, List<MetadataSerializer> list) {
        visitClass2(firClass, list);
        return Unit.INSTANCE;
    }
}

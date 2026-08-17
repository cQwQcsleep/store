package org.jetbrains.kotlin.fir.descriptors;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.ModuleCapability;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageViewDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 C2\u00020\u0001:\u0001CB!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J*\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001c2\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00110\u001eH\u0016J#\u0010,\u001a\u0004\u0018\u0001H-\"\u0004\b\u0000\u0010-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H-0/H\u0016¢\u0006\u0002\u00100J\b\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020\u001fH\u0016J\u001e\u0010;\u001a\u0002042\u0014\u0010<\u001a\u0010\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020>\u0018\u00010=H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R \u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010!X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010#R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00010)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u00101\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00108\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0014\u0010?\u001a\u00020@8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010B¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getBuiltIns", "()Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "shouldSeeInternalsOf", Argument.Delimiters.none, "targetModule", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPackage", "Lorg/jetbrains/kotlin/descriptors/PackageViewDescriptor;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getSubPackagesOf", Argument.Delimiters.none, "nameFilter", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/name/Name;", "allDependencyModules", Argument.Delimiters.none, "getAllDependencyModules", "()Ljava/util/List;", "setAllDependencyModules", "(Ljava/util/List;)V", "expectedByModules", "getExpectedByModules", "allExpectedByModules", Argument.Delimiters.none, "getAllExpectedByModules", "()Ljava/util/Set;", "getCapability", "T", "capability", "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "(Lorg/jetbrains/kotlin/descriptors/ModuleCapability;)Ljava/lang/Object;", "isValid", "()Z", "assertValid", Argument.Delimiters.none, "getOriginal", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "getName", "stableName", "getStableName", "()Lorg/jetbrains/kotlin/name/Name;", "acceptVoid", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "Ljava/lang/Void;", "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "getAnnotations", "()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "Companion", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirModuleDescriptor implements ModuleDescriptor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private List<? extends ModuleDescriptor> allDependencyModules;
    private final KotlinBuiltIns builtIns;
    private final FirModuleData moduleData;
    private final FirSession session;

    private FirModuleDescriptor(FirSession firSession, FirModuleData firModuleData, KotlinBuiltIns kotlinBuiltIns) {
        this.session = firSession;
        this.moduleData = firModuleData;
        this.builtIns = kotlinBuiltIns;
        this.allDependencyModules = CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor, org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public /* bridge */ <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return (R) ModuleDescriptor.DefaultImpls.accept(this, declarationDescriptorVisitor, d);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public void acceptVoid(DeclarationDescriptorVisitor<Void, Void> visitor) throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public void assertValid() {
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public List<ModuleDescriptor> getAllDependencyModules() {
        return this.allDependencyModules;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public Set<ModuleDescriptor> getAllExpectedByModules() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return Annotations.INSTANCE.getEMPTY();
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public KotlinBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public <T> T getCapability(ModuleCapability<T> capability) {
        capability.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor, org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public /* bridge */ DeclarationDescriptor getContainingDeclaration() {
        return ModuleDescriptor.DefaultImpls.getContainingDeclaration(this);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public List<ModuleDescriptor> getExpectedByModules() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    public final FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.descriptors.Named
    public Name getName() {
        return this.moduleData.getName();
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    /* JADX INFO: renamed from: getOriginal */
    public DeclarationDescriptor m84getOriginal() {
        return this;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public PackageViewDescriptor getPackage(FqName fqName) {
        fqName.getClass();
        if (FirSymbolProviderKt.getSymbolProvider(this.session).hasPackage(fqName)) {
            return new FirPackageViewDescriptor(fqName, this);
        }
        s0g.a("Module ", this.moduleData, " doesn't contain package ", fqName);
        return null;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public TargetPlatform getPlatform() {
        return this.moduleData.getPlatform();
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public Name getStableName() {
        return getName();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public Collection<FqName> getSubPackagesOf(FqName fqName, Function1<? super Name, Boolean> nameFilter) throws KotlinNothingValueException {
        fqName.getClass();
        nameFilter.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    /* JADX INFO: renamed from: isValid */
    public boolean getIsValid() {
        return true;
    }

    public void setAllDependencyModules(List<? extends ModuleDescriptor> list) {
        list.getClass();
        this.allDependencyModules = list;
    }

    @Override // org.jetbrains.kotlin.descriptors.ModuleDescriptor
    public boolean shouldSeeInternalsOf(ModuleDescriptor targetModule) {
        targetModule.getClass();
        if (targetModule instanceof FirModuleDescriptor) {
            return FirModuleDataKt.canSeeInternalsOf(this.moduleData, ((FirModuleDescriptor) targetModule).moduleData);
        }
        return false;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\t¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor$Companion;", Argument.Delimiters.none, "<init>", "()V", "createSourceModuleDescriptor", "Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "createDependencyModuleDescriptor", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirModuleDescriptor createDependencyModuleDescriptor(FirModuleData moduleData, KotlinBuiltIns builtIns) {
            moduleData.getClass();
            builtIns.getClass();
            return new FirModuleDescriptor(moduleData.getSession(), moduleData, builtIns, null);
        }

        public final FirModuleDescriptor createSourceModuleDescriptor(FirSession session, KotlinBuiltIns builtIns) {
            session.getClass();
            builtIns.getClass();
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (session.getKind() == FirSession.Kind.Source) {
                return new FirModuleDescriptor(session, FirModuleDataKt.getModuleData(session), builtIns, defaultConstructorMarker);
            }
            w01.a("Failed requirement.");
            return null;
        }

        private Companion() {
        }
    }

    public /* synthetic */ FirModuleDescriptor(FirSession firSession, FirModuleData firModuleData, KotlinBuiltIns kotlinBuiltIns, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firModuleData, kotlinBuiltIns);
    }
}

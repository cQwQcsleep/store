package org.jetbrains.kotlin.psi.stubs;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind;", "", "WithPackage", "Invalid", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$Invalid;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KotlinFileStubKind {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005Ê\u0001\u0010\b\u0007\u0012\f\b\b\u0012\b\b\fJ\u0004\b\t0\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$Invalid;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind;", "errorMessage", "", "getErrorMessage", "()Ljava/lang/String;", "org.jetbrains.kotlin:psi-api", "Lkotlin/SubclassOptInRequired;", "markerClass", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public interface Invalid extends KotlinFileStubKind {
        String getErrorMessage();
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0003\t\n\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "File", "Script", "Facade", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Facade;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$File;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Script;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public interface WithPackage extends KotlinFileStubKind {

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0006\u0007R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\b\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Facade;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage;", "facadeFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getFacadeFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "Simple", "MultifileClass", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Facade$MultifileClass;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Facade$Simple;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public interface Facade extends WithPackage {

            @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006Ê\u0001\u0010\b\b\u0012\f\b\t\u0012\b\b\fJ\u0004\b\t0\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Facade$MultifileClass;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Facade;", "facadePartSimpleNames", "", "", "getFacadePartSimpleNames", "()Ljava/util/List;", "org.jetbrains.kotlin:psi-api", "Lkotlin/SubclassOptInRequired;", "markerClass", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
            public interface MultifileClass extends Facade {
                List<String> getFacadePartSimpleNames();
            }

            @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005Ê\u0001\u0010\b\u0007\u0012\f\b\b\u0012\b\b\fJ\u0004\b\t0\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Facade$Simple;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Facade;", "partSimpleName", "", "getPartSimpleName", "()Ljava/lang/String;", "org.jetbrains.kotlin:psi-api", "Lkotlin/SubclassOptInRequired;", "markerClass", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
            public interface Simple extends Facade {
                String getPartSimpleName();
            }

            FqName getFacadeFqName();
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001Ê\u0001\u0010\b\u0003\u0012\f\b\u0004\u0012\b\b\fJ\u0004\b\t0\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$File;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage;", "org.jetbrains.kotlin:psi-api", "Lkotlin/SubclassOptInRequired;", "markerClass", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public interface File extends WithPackage {
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001Ê\u0001\u0010\b\u0003\u0012\f\b\u0004\u0012\b\b\fJ\u0004\b\t0\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage$Script;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFileStubKind$WithPackage;", "org.jetbrains.kotlin:psi-api", "Lkotlin/SubclassOptInRequired;", "markerClass", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public interface Script extends WithPackage {
        }

        FqName getPackageFqName();
    }
}

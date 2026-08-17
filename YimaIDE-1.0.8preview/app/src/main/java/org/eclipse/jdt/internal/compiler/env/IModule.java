package org.eclipse.jdt.internal.compiler.env;

import java.util.jar.Manifest;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IModule {
    public static final String MODULE_INFO = "module-info";
    public static final String MODULE_INFO_CLASS = "module-info.class";
    public static final String MODULE_INFO_JAVA = "module-info.java";
    public static final IModuleReference[] NO_MODULE_REFS = new IModuleReference[0];
    public static final IPackageExport[] NO_EXPORTS = new IPackageExport[0];
    public static final char[][] NO_USES = new char[0][];
    public static final IService[] NO_PROVIDES = new IService[0];
    public static final IModule[] NO_MODULES = new IModule[0];
    public static final IPackageExport[] NO_OPENS = new IPackageExport[0];

    public interface IModuleReference {
        int getModifiers();

        default boolean isStatic() {
            return (getModifiers() & 64) != 0;
        }

        default boolean isTransitive() {
            return (getModifiers() & 32) != 0;
        }

        char[] name();
    }

    public interface IPackageExport {
        default boolean isQualified() {
            char[][] cArrTargets = targets();
            return cArrTargets != null && cArrTargets.length > 0;
        }

        char[] name();

        char[][] targets();
    }

    public interface IService {
        char[] name();

        char[][] with();
    }

    static IModule createAutomatic(String str, boolean z, Manifest manifest) {
        char[] cArrDetermineAutomaticModuleNameFromManifest = AutomaticModuleNaming.determineAutomaticModuleNameFromManifest(manifest);
        boolean z2 = true;
        if (cArrDetermineAutomaticModuleNameFromManifest == null) {
            cArrDetermineAutomaticModuleNameFromManifest = AutomaticModuleNaming.determineAutomaticModuleNameFromFileName(str, true, z);
            z2 = false;
        }
        return createAutomatic(cArrDetermineAutomaticModuleNameFromManifest, z2);
    }

    IPackageExport[] exports();

    default boolean isAutoNameFromManifest() {
        return false;
    }

    default boolean isAutomatic() {
        return false;
    }

    boolean isOpen();

    char[] name();

    IPackageExport[] opens();

    IService[] provides();

    IModuleReference[] requires();

    char[][] uses();

    static IModule createAutomatic(char[] cArr, boolean z) {
        return new IModule(cArr, z) { // from class: org.eclipse.jdt.internal.compiler.env.IModule.1AutoModule
            char[] name;
            boolean nameFromManifest;

            {
                this.name = cArr;
                this.nameFromManifest = z;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public IPackageExport[] exports() {
                return IModule.NO_EXPORTS;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public boolean isAutoNameFromManifest() {
                return this.nameFromManifest;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public boolean isAutomatic() {
                return true;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public boolean isOpen() {
                return false;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public char[] name() {
                return this.name;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public IPackageExport[] opens() {
                return IModule.NO_OPENS;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public IService[] provides() {
                return IModule.NO_PROVIDES;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public IModuleReference[] requires() {
                return IModule.NO_MODULE_REFS;
            }

            @Override // org.eclipse.jdt.internal.compiler.env.IModule
            public char[][] uses() {
                return IModule.NO_USES;
            }
        };
    }
}

package com.sun.tools.classfile;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Dependency {

    public interface Filter {
        boolean accepts(Dependency dependency);
    }

    public interface Finder {
        Iterable<? extends Dependency> findDependencies(ClassFile classFile);
    }

    public interface Location {
        String getClassName();

        String getName();

        String getPackageName();
    }

    Location getOrigin();

    Location getTarget();
}

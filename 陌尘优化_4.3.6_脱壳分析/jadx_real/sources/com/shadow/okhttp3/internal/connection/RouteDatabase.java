package com.shadow.okhttp3.internal.connection;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.Route;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class RouteDatabase {
    private final Set<Route> failedRoutes = new LinkedHashSet();

    public final synchronized void connected(Route route) {
        CloseableKt.checkNotNullParameter(route, "route");
        this.failedRoutes.remove(route);
    }

    public final synchronized void failed(Route route) {
        CloseableKt.checkNotNullParameter(route, "failedRoute");
        this.failedRoutes.add(route);
    }

    public final synchronized boolean shouldPostpone(Route route) {
        CloseableKt.checkNotNullParameter(route, "route");
        return this.failedRoutes.contains(route);
    }
}

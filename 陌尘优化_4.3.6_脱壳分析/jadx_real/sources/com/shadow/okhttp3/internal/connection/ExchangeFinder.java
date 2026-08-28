package com.shadow.okhttp3.internal.connection;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.Address;
import com.shadow.okhttp3.EventListener;
import com.shadow.okhttp3.HttpUrl;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.Route;
import com.shadow.okhttp3.internal.Util;
import com.shadow.okhttp3.internal.connection.RouteSelector;
import com.shadow.okhttp3.internal.http.ExchangeCodec;
import com.shadow.okhttp3.internal.http.RealInterceptorChain;
import com.shadow.okhttp3.internal.http2.ConnectionShutdownException;
import com.shadow.okhttp3.internal.http2.ErrorCode;
import com.shadow.okhttp3.internal.http2.StreamResetException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ExchangeFinder {
    private final Address address;
    private final RealCall call;
    private final RealConnectionPool connectionPool;
    private int connectionShutdownCount;
    private final EventListener eventListener;
    private Route nextRouteToTry;
    private int otherFailureCount;
    private int refusedStreamCount;
    private RouteSelector.Selection routeSelection;
    private RouteSelector routeSelector;

    public ExchangeFinder(RealConnectionPool realConnectionPool, Address address, RealCall realCall, EventListener eventListener) {
        CloseableKt.checkNotNullParameter(realConnectionPool, "connectionPool");
        CloseableKt.checkNotNullParameter(address, "address");
        CloseableKt.checkNotNullParameter(realCall, "call");
        CloseableKt.checkNotNullParameter(eventListener, "eventListener");
        this.connectionPool = realConnectionPool;
        this.address = address;
        this.call = realCall;
        this.eventListener = eventListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final RealConnection findConnection(int i, int i2, int i3, int i4, boolean z) throws IOException {
        List<Route> routes;
        Socket socketReleaseConnectionNoEvents$okhttp;
        if (this.call.isCanceled()) {
            throw new IOException("Canceled");
        }
        RealConnection connection = this.call.getConnection();
        if (connection != null) {
            synchronized (connection) {
                try {
                    socketReleaseConnectionNoEvents$okhttp = (connection.getNoNewExchanges() || !sameHostAndPort(connection.route().address().url())) ? this.call.releaseConnectionNoEvents$okhttp() : null;
                } finally {
                }
            }
            if (this.call.getConnection() != null) {
                if (socketReleaseConnectionNoEvents$okhttp == null) {
                    return connection;
                }
                throw new IllegalStateException("Check failed.");
            }
            if (socketReleaseConnectionNoEvents$okhttp != null) {
                Util.closeQuietly(socketReleaseConnectionNoEvents$okhttp);
            }
            this.eventListener.connectionReleased(this.call, connection);
        }
        this.refusedStreamCount = 0;
        this.connectionShutdownCount = 0;
        this.otherFailureCount = 0;
        if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, null, false)) {
            RealConnection connection2 = this.call.getConnection();
            CloseableKt.checkNotNull(connection2);
            this.eventListener.connectionAcquired(this.call, connection2);
            return connection2;
        }
        Route next = this.nextRouteToTry;
        try {
            if (next == null) {
                RouteSelector.Selection selection = this.routeSelection;
                if (selection != null) {
                    CloseableKt.checkNotNull(selection);
                    if (selection.hasNext()) {
                        RouteSelector.Selection selection2 = this.routeSelection;
                        CloseableKt.checkNotNull(selection2);
                        next = selection2.next();
                    }
                }
                RouteSelector routeSelector = this.routeSelector;
                if (routeSelector == null) {
                    routeSelector = new RouteSelector(this.address, this.call.getClient().getRouteDatabase(), this.call, this.eventListener);
                    this.routeSelector = routeSelector;
                }
                RouteSelector.Selection next2 = routeSelector.next();
                this.routeSelection = next2;
                routes = next2.getRoutes();
                if (this.call.isCanceled()) {
                    throw new IOException("Canceled");
                }
                if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, false)) {
                    RealConnection connection3 = this.call.getConnection();
                    CloseableKt.checkNotNull(connection3);
                    this.eventListener.connectionAcquired(this.call, connection3);
                    return connection3;
                }
                next = next2.next();
                RealConnection realConnection = new RealConnection(this.connectionPool, next);
                this.call.setConnectionToCancel(realConnection);
                realConnection.connect(i, i2, i3, i4, z, this.call, this.eventListener);
                this.call.setConnectionToCancel(null);
                this.call.getClient().getRouteDatabase().connected(realConnection.route());
                if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
                    synchronized (realConnection) {
                        this.connectionPool.put(realConnection);
                        this.call.acquireConnectionNoEvents(realConnection);
                    }
                    this.eventListener.connectionAcquired(this.call, realConnection);
                    return realConnection;
                }
                RealConnection connection4 = this.call.getConnection();
                CloseableKt.checkNotNull(connection4);
                this.nextRouteToTry = next;
                Util.closeQuietly(realConnection.socket());
                this.eventListener.connectionAcquired(this.call, connection4);
                return connection4;
            }
            CloseableKt.checkNotNull(next);
            this.nextRouteToTry = null;
            realConnection.connect(i, i2, i3, i4, z, this.call, this.eventListener);
            this.call.setConnectionToCancel(null);
            this.call.getClient().getRouteDatabase().connected(realConnection.route());
            if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
            }
        } catch (Throwable th) {
            this.call.setConnectionToCancel(null);
            throw th;
        }
        routes = null;
        RealConnection realConnection2 = new RealConnection(this.connectionPool, next);
        this.call.setConnectionToCancel(realConnection2);
    }

    private final RealConnection findHealthyConnection(int i, int i2, int i3, int i4, boolean z, boolean z2) throws IOException {
        while (true) {
            RealConnection realConnectionFindConnection = findConnection(i, i2, i3, i4, z);
            if (realConnectionFindConnection.isHealthy(z2)) {
                return realConnectionFindConnection;
            }
            realConnectionFindConnection.noNewExchanges$okhttp();
            if (this.nextRouteToTry == null) {
                RouteSelector.Selection selection = this.routeSelection;
                if (selection != null ? selection.hasNext() : true) {
                    continue;
                } else {
                    RouteSelector routeSelector = this.routeSelector;
                    if (!(routeSelector != null ? routeSelector.hasNext() : true)) {
                        throw new IOException("exhausted all routes");
                    }
                }
            }
        }
    }

    private final Route retryRoute() {
        RealConnection connection;
        if (this.refusedStreamCount > 1 || this.connectionShutdownCount > 1 || this.otherFailureCount > 0 || (connection = this.call.getConnection()) == null) {
            return null;
        }
        synchronized (connection) {
            if (connection.getRouteFailureCount$okhttp() != 0) {
                return null;
            }
            if (Util.canReuseConnectionFor(connection.route().address().url(), this.address.url())) {
                return connection.route();
            }
            return null;
        }
    }

    public final ExchangeCodec find(OkHttpClient okHttpClient, RealInterceptorChain realInterceptorChain) {
        CloseableKt.checkNotNullParameter(okHttpClient, "client");
        CloseableKt.checkNotNullParameter(realInterceptorChain, "chain");
        try {
            return findHealthyConnection(realInterceptorChain.getConnectTimeoutMillis$okhttp(), realInterceptorChain.getReadTimeoutMillis$okhttp(), realInterceptorChain.getWriteTimeoutMillis$okhttp(), okHttpClient.pingIntervalMillis(), okHttpClient.retryOnConnectionFailure(), !CloseableKt.areEqual(realInterceptorChain.getRequest$okhttp().method(), "GET")).newCodec$okhttp(okHttpClient, realInterceptorChain);
        } catch (RouteException e) {
            trackFailure(e.getLastConnectException());
            throw e;
        } catch (IOException e2) {
            trackFailure(e2);
            throw new RouteException(e2);
        }
    }

    public final Address getAddress$okhttp() {
        return this.address;
    }

    public final boolean retryAfterFailure() {
        RouteSelector routeSelector;
        if (this.refusedStreamCount == 0 && this.connectionShutdownCount == 0 && this.otherFailureCount == 0) {
            return false;
        }
        if (this.nextRouteToTry != null) {
            return true;
        }
        Route routeRetryRoute = retryRoute();
        if (routeRetryRoute != null) {
            this.nextRouteToTry = routeRetryRoute;
            return true;
        }
        RouteSelector.Selection selection = this.routeSelection;
        if ((selection == null || !selection.hasNext()) && (routeSelector = this.routeSelector) != null) {
            return routeSelector.hasNext();
        }
        return true;
    }

    public final boolean sameHostAndPort(HttpUrl httpUrl) {
        CloseableKt.checkNotNullParameter(httpUrl, "url");
        HttpUrl httpUrlUrl = this.address.url();
        return httpUrl.port() == httpUrlUrl.port() && CloseableKt.areEqual(httpUrl.host(), httpUrlUrl.host());
    }

    public final void trackFailure(IOException iOException) {
        CloseableKt.checkNotNullParameter(iOException, "e");
        this.nextRouteToTry = null;
        if ((iOException instanceof StreamResetException) && ((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
            this.refusedStreamCount++;
        } else if (iOException instanceof ConnectionShutdownException) {
            this.connectionShutdownCount++;
        } else {
            this.otherFailureCount++;
        }
    }
}

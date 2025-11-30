package com.example.geofence.util;

import com.example.geofence.model.GeoPoint;

import java.util.List;

public class GeoUtils {

    public static boolean pointInPolygon(GeoPoint point, List<GeoPoint> polygon) {
        if (polygon == null || polygon.size() < 3) return false;
        boolean inside = false;
        int n = polygon.size();
        double x = point.getLon();
        double y = point.getLat();
        for (int i = 0, j = n - 1; i < n; j = i++) {
            double xi = polygon.get(i).getLon(), yi = polygon.get(i).getLat();
            double xj = polygon.get(j).getLon(), yj = polygon.get(j).getLat();

            if (pointOnSegment(x, y, xi, yi, xj, yj)) {
                return true;
            }

            boolean intersect = ((yi > y) != (yj > y)) &&
                    (x < (xj - xi) * (y - yi) / (yj - yi + 0.0) + xi);
            if (intersect) inside = !inside;
        }
        return inside;
    }

    private static boolean pointOnSegment(double x, double y, double x1, double y1, double x2, double y2) {
        double area = (x2 - x1) * (y - y1) - (y2 - y1) * (x - x1);
        if (Math.abs(area) > 1e-9) return false;
        double minX = Math.min(x1, x2) - 1e-9, maxX = Math.max(x1, x2) + 1e-9;
        double minY = Math.min(y1, y2) - 1e-9, maxY = Math.max(y1, y2) + 1e-9;
        return (x >= minX && x <= maxX && y >= minY && y <= maxY);
    }
}

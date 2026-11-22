package utils;

public class CoordinateTransform {

    /**
     * Converts world coordinates to screen coordinates.
     * screenX = (worldX - offsetX) * scaleX
     * screenY = (worldY - offsetY) * scaleY
     */
    public static Vector worldToScreen(Vector world, Vector offset, Vector scale) {
        return new Vector(
            (world.x - offset.x) * scale.x,
            (world.y - offset.y) * scale.y
        );
    }

    /**
     * Converts screen coordinates to world coordinates.
     * worldX = (screenX / scaleX) + offsetX
     * worldY = (screenY / scaleY) + offsetY
     */
    public static Vector screenToWorld(Vector screen, Vector offset, Vector scale) {
        return new Vector(
            (screen.x / scale.x) + offset.x,
            (screen.y / scale.y) + offset.y
        );
    }
}

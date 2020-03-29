package technologicalmayhem.magicalstorage.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class ShapeRotationBuilder {

    public VoxelShape up;
    public VoxelShape down;
    public VoxelShape north;
    public VoxelShape east;
    public VoxelShape south;
    public VoxelShape west;

    @SuppressWarnings("SuspiciousNameCombination")
    public ShapeRotationBuilder(double xMin, double yMin, double zMin, double xMax, double yMax, double zMax) {
        up = Block.createCuboidShape(xMin, yMin, zMin, xMax, yMax, zMax);
        down = mirrorShape(up);
        south = Block.createCuboidShape(zMin, xMin, yMin, zMax, xMax, yMax);
        north = mirrorShape(south);
        east = Block.createCuboidShape(yMin, zMin, xMin, yMax, zMax, xMax);
        west = mirrorShape(east);
    }

    private static VoxelShape mirrorShape(VoxelShape shape) {
        Box b = shape.getBoundingBox();
        return VoxelShapes.cuboid(
                1 - b.x1, 1 - b.y1, 1 - b.z1,
                1 - b.x2, 1 - b.y2, 1 - b.z2
        );
    }
}

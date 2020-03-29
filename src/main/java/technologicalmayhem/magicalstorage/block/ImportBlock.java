package technologicalmayhem.magicalstorage.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import technologicalmayhem.magicalstorage.block.blockentity.ImportBlockEntity;
import technologicalmayhem.magicalstorage.util.ShapeRotationBuilder;

public class ImportBlock extends FacingBlock implements BlockEntityProvider {

    private static VoxelShape UP;
    private static VoxelShape DOWN;
    private static VoxelShape NORTH;
    private static VoxelShape EAST;
    private static VoxelShape SOUTH;
    private static VoxelShape WEST;

    private BooleanProperty ONLINE = BooleanProperty.of("online");

    public ImportBlock() {
        super(FabricBlockSettings.of(Material.STONE).build());
        setDefaultState(this.stateManager.getDefaultState().with(Properties.FACING, Direction.UP).with(ONLINE, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getSide().getOpposite()).with(ONLINE, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.FACING).add(BooleanProperty.of("online"));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(ONLINE))
        {
            world.setBlockState(pos, state.with(ONLINE, false));
        }
        else
        {
            world.setBlockState(pos, state.with(ONLINE, true));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        switch (state.get(FACING)) {
            case DOWN:
                return DOWN;
            case UP:
                return UP;
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            case EAST:
                return EAST;
            default:
                throw new IllegalStateException("Unexpected value: " + state.get(FACING));
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new ImportBlockEntity();
    }

    static {
        ShapeRotationBuilder upperPlate = new ShapeRotationBuilder(0, 13, 0, 16, 16, 16);
        ShapeRotationBuilder lowerPlate = new ShapeRotationBuilder(2, 11, 2, 14, 14, 14);
        ShapeRotationBuilder rod = new ShapeRotationBuilder(5, 0, 5, 11, 11, 11);

        UP = VoxelShapes.union(upperPlate.up, lowerPlate.up, rod.up);
        DOWN = VoxelShapes.union(upperPlate.down, lowerPlate.down, rod.down);
        NORTH = VoxelShapes.union(upperPlate.north, lowerPlate.north, rod.north);
        SOUTH = VoxelShapes.union(upperPlate.south, lowerPlate.south, rod.south);
        WEST = VoxelShapes.union(upperPlate.west, lowerPlate.west, rod.west);
        EAST = VoxelShapes.union(upperPlate.east, lowerPlate.east, rod.east);
    }
}

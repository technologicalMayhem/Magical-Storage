package technologicalmayhem.magicalstorage.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.loader.metadata.ModMetadataV1;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import technologicalmayhem.magicalstorage.block.blockentity.StorageBlockEntity;

public class StorageBlock extends Block implements BlockEntityProvider {

    public StorageBlock() {
        super(FabricBlockSettings
                .of(Material.STONE)
                .sounds(BlockSoundGroup.STONE)
                .build()
        );
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        StorageBlockEntity entity = (StorageBlockEntity) world.getBlockEntity(pos);
        if (entity != null && !world.isClient) {
            entity.SayStuff();
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new StorageBlockEntity();
    }
}

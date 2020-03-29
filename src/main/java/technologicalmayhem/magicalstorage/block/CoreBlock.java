package technologicalmayhem.magicalstorage.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class CoreBlock extends Block {

    public CoreBlock() {
        super(FabricBlockSettings.of(Material.STONE).build());
    }
}

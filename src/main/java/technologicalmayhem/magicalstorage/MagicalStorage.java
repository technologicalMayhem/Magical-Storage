package technologicalmayhem.magicalstorage;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import technologicalmayhem.magicalstorage.block.ImportBlock;
import technologicalmayhem.magicalstorage.block.StorageBlock;
import technologicalmayhem.magicalstorage.block.blockentity.ImportBlockEntity;
import technologicalmayhem.magicalstorage.block.blockentity.StorageBlockEntity;

public class MagicalStorage implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "magicalstorage";
    public static final String MOD_NAME = "Magical Storage";

    //Blocks
    public static final Block STORAGE_BLOCK = new StorageBlock();
    public static final Block IMPORT_BLOCK = new ImportBlock();

    //BlockItems
    public static final BlockItem STORAGE_BLOCK_ITEM = new BlockItem(STORAGE_BLOCK, new Item.Settings());
    public static final BlockItem IMPORT_BLOCK_ITEM = new BlockItem(IMPORT_BLOCK, new Item.Settings());

    //EntityBlocks
    public static BlockEntityType<StorageBlockEntity> STORAGE_BLOCK_ENTITY;
    public static BlockEntityType<ImportBlockEntity> IMPORT_BLOCK_ENTITY;

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        //Blocks
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "storage"), STORAGE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "import"), IMPORT_BLOCK);
        //BlockItems
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "storage"), STORAGE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "import"), IMPORT_BLOCK_ITEM);
        //EntityBlocks
        STORAGE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "storage"), BlockEntityType.Builder.create(StorageBlockEntity::new, STORAGE_BLOCK).build(null));
        IMPORT_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "import"), BlockEntityType.Builder.create(ImportBlockEntity::new, STORAGE_BLOCK).build(null));

        FabricItemGroupBuilder.create(new Identifier(MOD_ID, "general"))
                .icon(() -> new ItemStack(STORAGE_BLOCK_ITEM))
                .appendItems(itemStacks -> {
                    //Block Items
                    itemStacks.add(new ItemStack(STORAGE_BLOCK_ITEM));
                    itemStacks.add(new ItemStack(IMPORT_BLOCK_ITEM));
                })
                .build();
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}
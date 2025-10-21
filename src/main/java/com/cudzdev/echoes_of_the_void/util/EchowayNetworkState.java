package com.cudzdev.echoes_of_the_void.util;

import com.cudzdev.echoes_of_the_void.Main;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EchowayNetworkState extends PersistentState {
    public final Map<UUID, EchowayInfo> gates = new HashMap<>();

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        System.out.println("========================================");
        System.out.println("SAVING GATE NETWORK STATE...");
        System.out.println("Number of gates to save: " + gates.size());

        NbtList gatesNbt = new NbtList();
        for (Map.Entry<UUID, EchowayInfo> entry : gates.entrySet()) {
            NbtCompound gateNbt = new NbtCompound();
            gateNbt.putUuid("id", entry.getKey());
            gateNbt.put("info", EchowayInfo.toNbt(entry.getValue(), registryLookup));
            gatesNbt.add(gateNbt);
            System.out.println(" > Saved gate with ID: " + entry.getKey());
        }
        nbt.put("gates", gatesNbt);
        System.out.println("SAVE COMPLETE.");
        System.out.println("========================================");
        return nbt;
    }

    public static EchowayNetworkState createFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        System.out.println("========================================");
        System.out.println("LOADING GATE NETWORK STATE...");
        EchowayNetworkState state = new EchowayNetworkState();

        NbtList gatesNbt = nbt.getList("gates", NbtCompound.COMPOUND_TYPE);
        System.out.println("Found " + gatesNbt.size() + " gates in save file.");
        for (int i = 0; i < gatesNbt.size(); i++) {
            NbtCompound gateNbt = gatesNbt.getCompound(i);
            UUID id = gateNbt.getUuid("id");
            EchowayInfo info = EchowayInfo.fromNbt(gateNbt.getCompound("info"), registryLookup);
            state.gates.put(id, info);
            System.out.println(" > Loaded gate with ID: " + id);
        }
        System.out.println("LOAD COMPLETE.");
        System.out.println("========================================");
        return state;
    }

    public static EchowayNetworkState getServerState(ServerWorld world) {
        PersistentStateManager persistentStateManager = world.getServer().getOverworld().getPersistentStateManager();
        return persistentStateManager.getOrCreate(
                new Type<>(EchowayNetworkState::new, EchowayNetworkState::createFromNbt, null),
                Main.MOD_ID + "_gate_network"
        );
    }

    public record EchowayInfo(DefaultedList<ItemStack> address, BlockPos pos, RegistryKey<World> dimension) {
        public static NbtCompound toNbt(EchowayInfo info, RegistryWrapper.WrapperLookup registryLookup) {
            NbtCompound nbt = new NbtCompound();
            nbt.put("address", Inventories.writeNbt(new NbtCompound(), info.address, registryLookup));
            nbt.putLong("pos", info.pos.asLong());
            nbt.putString("dimension", info.dimension.getValue().toString());
            return nbt;
        }

        public static EchowayInfo fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
            DefaultedList<ItemStack> address = DefaultedList.ofSize(5, ItemStack.EMPTY);
            Inventories.readNbt(nbt.getCompound("address"), address, registryLookup);
            BlockPos pos = BlockPos.fromLong(nbt.getLong("pos"));
            RegistryKey<World> dimension = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(nbt.getString("dimension")));
            return new EchowayInfo(address, pos, dimension);
        }
    }
}

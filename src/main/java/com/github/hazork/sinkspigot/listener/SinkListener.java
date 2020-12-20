package com.github.hazork.sinkspigot.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.server.ServiceRegisterEvent;
import org.bukkit.event.server.ServiceUnregisterEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.SpawnChangeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import com.github.hazork.sinkspigot.SinkHelper;
import com.github.hazork.sinkspigot.interfaces.Registrable;

public abstract class SinkListener implements Listener, Registrable, SinkHelper {

    @Override
    public void register() {
	Bukkit.getPluginManager().registerEvents(this, getPlugin());
    }

    public void onEvent(AsyncPlayerChatEvent event) {}

    public void onEvent(AsyncPlayerPreLoginEvent event) {}

    public void onEvent(BlockBreakEvent event) {}

    public void onEvent(BlockBurnEvent event) {}

    public void onEvent(BlockCanBuildEvent event) {}

    public void onEvent(BlockDamageEvent event) {}

    public void onEvent(BlockDispenseEvent event) {}

    public void onEvent(BlockFadeEvent event) {}

    public void onEvent(BlockFormEvent event) {}

    public void onEvent(BlockFromToEvent event) {}

    public void onEvent(BlockGrowEvent event) {}

    public void onEvent(BlockIgniteEvent event) {}

    public void onEvent(BlockPhysicsEvent event) {}

    public void onEvent(BlockPistonExtendEvent event) {}

    public void onEvent(BlockPistonRetractEvent event) {}

    public void onEvent(BlockPlaceEvent event) {}

    public void onEvent(BlockRedstoneEvent event) {}

    public void onEvent(BlockSpreadEvent event) {}

    public void onEvent(BrewEvent event) {}

    public void onEvent(ChunkLoadEvent event) {}

    public void onEvent(ChunkPopulateEvent event) {}

    public void onEvent(ChunkUnloadEvent event) {}

    public void onEvent(CraftItemEvent event) {}

    public void onEvent(CreatureSpawnEvent event) {}

    public void onEvent(CreeperPowerEvent event) {}

    public void onEvent(EnchantItemEvent event) {}

    public void onEvent(EntityBlockFormEvent event) {}

    public void onEvent(EntityBreakDoorEvent event) {}

    public void onEvent(EntityChangeBlockEvent event) {}

    public void onEvent(EntityCombustByBlockEvent event) {}

    public void onEvent(EntityCombustByEntityEvent event) {}

    public void onEvent(EntityCombustEvent event) {}

    public void onEvent(EntityCreatePortalEvent event) {}

    public void onEvent(EntityDamageByBlockEvent event) {}

    public void onEvent(EntityDamageByEntityEvent event) {}

    public void onEvent(EntityDamageEvent event) {}

    public void onEvent(EntityDeathEvent event) {}

    public void onEvent(EntityExplodeEvent event) {}

    public void onEvent(EntityInteractEvent event) {}

    public void onEvent(EntityPortalEnterEvent event) {}

    public void onEvent(EntityRegainHealthEvent event) {}

    public void onEvent(EntityShootBowEvent event) {}

    public void onEvent(EntityTameEvent event) {}

    public void onEvent(EntityTargetEvent event) {}

    public void onEvent(EntityTargetLivingEntityEvent event) {}

    public void onEvent(EntityTeleportEvent event) {}

    public void onEvent(ExpBottleEvent event) {}

    public void onEvent(ExplosionPrimeEvent event) {}

    public void onEvent(FoodLevelChangeEvent event) {}

    public void onEvent(FurnaceBurnEvent event) {}

    public void onEvent(FurnaceSmeltEvent event) {}

    public void onEvent(InventoryClickEvent event) {}

    public void onEvent(InventoryCloseEvent event) {}

    public void onEvent(InventoryEvent event) {}

    public void onEvent(InventoryOpenEvent event) {}

    public void onEvent(ItemDespawnEvent event) {}

    public void onEvent(LeavesDecayEvent event) {}

    public void onEvent(LightningStrikeEvent event) {}

    public void onEvent(MapInitializeEvent event) {}

    public void onEvent(NotePlayEvent event) {}

    public void onEvent(PigZapEvent event) {}

    public void onEvent(PlayerAnimationEvent event) {}

    public void onEvent(PlayerBedEnterEvent event) {}

    public void onEvent(PlayerBedLeaveEvent event) {}

    public void onEvent(PlayerBucketEmptyEvent event) {}

    public void onEvent(PlayerBucketFillEvent event) {}

    public void onEvent(PlayerChatTabCompleteEvent event) {}

    public void onEvent(PlayerCommandPreprocessEvent event) {}

    public void onEvent(PlayerDeathEvent event) {}

    public void onEvent(PlayerDropItemEvent event) {}

    public void onEvent(PlayerEggThrowEvent event) {}

    public void onEvent(PlayerExpChangeEvent event) {}

    public void onEvent(PlayerFishEvent event) {}

    public void onEvent(PlayerGameModeChangeEvent event) {}

    public void onEvent(PlayerInteractEntityEvent event) {}

    public void onEvent(PlayerInteractEvent event) {}

    public void onEvent(PlayerItemBreakEvent event) {}

    public void onEvent(PlayerItemHeldEvent event) {}

    public void onEvent(PlayerJoinEvent event) {}

    public void onEvent(PlayerKickEvent event) {}

    public void onEvent(PlayerLevelChangeEvent event) {}

    public void onEvent(PlayerLoginEvent event) {}

    public void onEvent(PlayerMoveEvent event) {}

    public void onEvent(PlayerPickupItemEvent event) {}

    public void onEvent(PlayerPortalEvent event) {}

    public void onEvent(PlayerQuitEvent event) {}

    public void onEvent(PlayerRegisterChannelEvent event) {}

    public void onEvent(PlayerRespawnEvent event) {}

    public void onEvent(PlayerShearEntityEvent event) {}

    public void onEvent(PlayerTeleportEvent event) {}

    public void onEvent(PlayerToggleFlightEvent event) {}

    public void onEvent(PlayerToggleSneakEvent event) {}

    public void onEvent(PlayerToggleSprintEvent event) {}

    public void onEvent(PlayerUnregisterChannelEvent event) {}

    public void onEvent(PlayerVelocityEvent event) {}

    public void onEvent(PluginDisableEvent event) {}

    public void onEvent(PluginEnableEvent event) {}

    public void onEvent(PortalCreateEvent event) {}

    public void onEvent(PotionSplashEvent event) {}

    public void onEvent(PrepareItemEnchantEvent event) {}

    public void onEvent(RemoteServerCommandEvent event) {}

    public void onEvent(ServerCommandEvent event) {}

    public void onEvent(ServerListPingEvent event) {}

    public void onEvent(ServiceRegisterEvent event) {}

    public void onEvent(ServiceUnregisterEvent event) {}

    public void onEvent(SheepDyeWoolEvent event) {}

    public void onEvent(SheepRegrowWoolEvent event) {}

    public void onEvent(SignChangeEvent event) {}

    public void onEvent(SlimeSplitEvent event) {}

    public void onEvent(SpawnChangeEvent event) {}

    public void onEvent(StructureGrowEvent event) {}

    public void onEvent(ThunderChangeEvent event) {}

    public void onEvent(VehicleBlockCollisionEvent event) {}

    public void onEvent(VehicleCreateEvent event) {}

    public void onEvent(VehicleDamageEvent event) {}

    public void onEvent(VehicleDestroyEvent event) {}

    public void onEvent(VehicleEnterEvent event) {}

    public void onEvent(VehicleEntityCollisionEvent event) {}

    public void onEvent(VehicleExitEvent event) {}

    public void onEvent(VehicleMoveEvent event) {}

    public void onEvent(VehicleUpdateEvent event) {}

    public void onEvent(WeatherChangeEvent event) {}

    public void onEvent(WorldInitEvent event) {}

    public void onEvent(WorldLoadEvent event) {}

    public void onEvent(WorldSaveEvent event) {}

    public void onEvent(WorldUnloadEvent event) {}

}
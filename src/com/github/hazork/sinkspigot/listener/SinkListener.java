package com.github.hazork.sinkspigot.listener;

import javax.annotation.Nonnull;

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

    public void onEvent(@Nonnull AsyncPlayerChatEvent event) {}

    public void onEvent(@Nonnull AsyncPlayerPreLoginEvent event) {}

    public void onEvent(@Nonnull BlockBreakEvent event) {}

    public void onEvent(@Nonnull BlockBurnEvent event) {}

    public void onEvent(@Nonnull BlockCanBuildEvent event) {}

    public void onEvent(@Nonnull BlockDamageEvent event) {}

    public void onEvent(@Nonnull BlockDispenseEvent event) {}

    public void onEvent(@Nonnull BlockFadeEvent event) {}

    public void onEvent(@Nonnull BlockFormEvent event) {}

    public void onEvent(@Nonnull BlockFromToEvent event) {}

    public void onEvent(@Nonnull BlockGrowEvent event) {}

    public void onEvent(@Nonnull BlockIgniteEvent event) {}

    public void onEvent(@Nonnull BlockPhysicsEvent event) {}

    public void onEvent(@Nonnull BlockPistonExtendEvent event) {}

    public void onEvent(@Nonnull BlockPistonRetractEvent event) {}

    public void onEvent(@Nonnull BlockPlaceEvent event) {}

    public void onEvent(@Nonnull BlockRedstoneEvent event) {}

    public void onEvent(@Nonnull BlockSpreadEvent event) {}

    public void onEvent(@Nonnull BrewEvent event) {}

    public void onEvent(@Nonnull ChunkLoadEvent event) {}

    public void onEvent(@Nonnull ChunkPopulateEvent event) {}

    public void onEvent(@Nonnull ChunkUnloadEvent event) {}

    public void onEvent(@Nonnull CraftItemEvent event) {}

    public void onEvent(@Nonnull CreatureSpawnEvent event) {}

    public void onEvent(@Nonnull CreeperPowerEvent event) {}

    public void onEvent(@Nonnull EnchantItemEvent event) {}

    public void onEvent(@Nonnull EntityBlockFormEvent event) {}

    public void onEvent(@Nonnull EntityBreakDoorEvent event) {}

    public void onEvent(@Nonnull EntityChangeBlockEvent event) {}

    public void onEvent(@Nonnull EntityCombustByBlockEvent event) {}

    public void onEvent(@Nonnull EntityCombustByEntityEvent event) {}

    public void onEvent(@Nonnull EntityCombustEvent event) {}

    public void onEvent(@Nonnull EntityCreatePortalEvent event) {}

    public void onEvent(@Nonnull EntityDamageByBlockEvent event) {}

    public void onEvent(@Nonnull EntityDamageByEntityEvent event) {}

    public void onEvent(@Nonnull EntityDamageEvent event) {}

    public void onEvent(@Nonnull EntityDeathEvent event) {}

    public void onEvent(@Nonnull EntityExplodeEvent event) {}

    public void onEvent(@Nonnull EntityInteractEvent event) {}

    public void onEvent(@Nonnull EntityPortalEnterEvent event) {}

    public void onEvent(@Nonnull EntityRegainHealthEvent event) {}

    public void onEvent(@Nonnull EntityShootBowEvent event) {}

    public void onEvent(@Nonnull EntityTameEvent event) {}

    public void onEvent(@Nonnull EntityTargetEvent event) {}

    public void onEvent(@Nonnull EntityTargetLivingEntityEvent event) {}

    public void onEvent(@Nonnull EntityTeleportEvent event) {}

    public void onEvent(@Nonnull ExpBottleEvent event) {}

    public void onEvent(@Nonnull ExplosionPrimeEvent event) {}

    public void onEvent(@Nonnull FoodLevelChangeEvent event) {}

    public void onEvent(@Nonnull FurnaceBurnEvent event) {}

    public void onEvent(@Nonnull FurnaceSmeltEvent event) {}

    public void onEvent(@Nonnull InventoryClickEvent event) {}

    public void onEvent(@Nonnull InventoryCloseEvent event) {}

    public void onEvent(@Nonnull InventoryEvent event) {}

    public void onEvent(@Nonnull InventoryOpenEvent event) {}

    public void onEvent(@Nonnull ItemDespawnEvent event) {}

    public void onEvent(@Nonnull LeavesDecayEvent event) {}

    public void onEvent(@Nonnull LightningStrikeEvent event) {}

    public void onEvent(@Nonnull MapInitializeEvent event) {}

    public void onEvent(@Nonnull NotePlayEvent event) {}

    public void onEvent(@Nonnull PigZapEvent event) {}

    public void onEvent(@Nonnull PlayerAnimationEvent event) {}

    public void onEvent(@Nonnull PlayerBedEnterEvent event) {}

    public void onEvent(@Nonnull PlayerBedLeaveEvent event) {}

    public void onEvent(@Nonnull PlayerBucketEmptyEvent event) {}

    public void onEvent(@Nonnull PlayerBucketFillEvent event) {}

    public void onEvent(@Nonnull PlayerChatTabCompleteEvent event) {}

    public void onEvent(@Nonnull PlayerCommandPreprocessEvent event) {}

    public void onEvent(@Nonnull PlayerDeathEvent event) {}

    public void onEvent(@Nonnull PlayerDropItemEvent event) {}

    public void onEvent(@Nonnull PlayerEggThrowEvent event) {}

    public void onEvent(@Nonnull PlayerExpChangeEvent event) {}

    public void onEvent(@Nonnull PlayerFishEvent event) {}

    public void onEvent(@Nonnull PlayerGameModeChangeEvent event) {}

    public void onEvent(@Nonnull PlayerInteractEntityEvent event) {}

    public void onEvent(@Nonnull PlayerInteractEvent event) {}

    public void onEvent(@Nonnull PlayerItemBreakEvent event) {}

    public void onEvent(@Nonnull PlayerItemHeldEvent event) {}

    public void onEvent(@Nonnull PlayerJoinEvent event) {}

    public void onEvent(@Nonnull PlayerKickEvent event) {}

    public void onEvent(@Nonnull PlayerLevelChangeEvent event) {}

    public void onEvent(@Nonnull PlayerLoginEvent event) {}

    public void onEvent(@Nonnull PlayerMoveEvent event) {}

    public void onEvent(@Nonnull PlayerPickupItemEvent event) {}

    public void onEvent(@Nonnull PlayerPortalEvent event) {}

    public void onEvent(@Nonnull PlayerQuitEvent event) {}

    public void onEvent(@Nonnull PlayerRegisterChannelEvent event) {}

    public void onEvent(@Nonnull PlayerRespawnEvent event) {}

    public void onEvent(@Nonnull PlayerShearEntityEvent event) {}

    public void onEvent(@Nonnull PlayerTeleportEvent event) {}

    public void onEvent(@Nonnull PlayerToggleFlightEvent event) {}

    public void onEvent(@Nonnull PlayerToggleSneakEvent event) {}

    public void onEvent(@Nonnull PlayerToggleSprintEvent event) {}

    public void onEvent(@Nonnull PlayerUnregisterChannelEvent event) {}

    public void onEvent(@Nonnull PlayerVelocityEvent event) {}

    public void onEvent(@Nonnull PluginDisableEvent event) {}

    public void onEvent(@Nonnull PluginEnableEvent event) {}

    public void onEvent(@Nonnull PortalCreateEvent event) {}

    public void onEvent(@Nonnull PotionSplashEvent event) {}

    public void onEvent(@Nonnull PrepareItemEnchantEvent event) {}

    public void onEvent(@Nonnull RemoteServerCommandEvent event) {}

    public void onEvent(@Nonnull ServerCommandEvent event) {}

    public void onEvent(@Nonnull ServerListPingEvent event) {}

    public void onEvent(@Nonnull ServiceRegisterEvent event) {}

    public void onEvent(@Nonnull ServiceUnregisterEvent event) {}

    public void onEvent(@Nonnull SheepDyeWoolEvent event) {}

    public void onEvent(@Nonnull SheepRegrowWoolEvent event) {}

    public void onEvent(@Nonnull SignChangeEvent event) {}

    public void onEvent(@Nonnull SlimeSplitEvent event) {}

    public void onEvent(@Nonnull SpawnChangeEvent event) {}

    public void onEvent(@Nonnull StructureGrowEvent event) {}

    public void onEvent(@Nonnull ThunderChangeEvent event) {}

    public void onEvent(@Nonnull VehicleBlockCollisionEvent event) {}

    public void onEvent(@Nonnull VehicleCreateEvent event) {}

    public void onEvent(@Nonnull VehicleDamageEvent event) {}

    public void onEvent(@Nonnull VehicleDestroyEvent event) {}

    public void onEvent(@Nonnull VehicleEnterEvent event) {}

    public void onEvent(@Nonnull VehicleEntityCollisionEvent event) {}

    public void onEvent(@Nonnull VehicleExitEvent event) {}

    public void onEvent(@Nonnull VehicleMoveEvent event) {}

    public void onEvent(@Nonnull VehicleUpdateEvent event) {}

    public void onEvent(@Nonnull WeatherChangeEvent event) {}

    public void onEvent(@Nonnull WorldInitEvent event) {}

    public void onEvent(@Nonnull WorldLoadEvent event) {}

    public void onEvent(@Nonnull WorldSaveEvent event) {}

    public void onEvent(@Nonnull WorldUnloadEvent event) {}

}
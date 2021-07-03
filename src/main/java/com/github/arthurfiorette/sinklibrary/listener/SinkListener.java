package com.github.arthurfiorette.sinklibrary.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
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

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;

/**
 * A abstract Spigot listener implementation with the most methods implements.
 * Override and use {@link org.bukkit.event.EventHandler} to make the method be
 * a listener. This listener will work when register is called.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public abstract class SinkListener implements Listener, BaseService {

  protected final BasePlugin plugin;

  /**
   * Constructs a new SinkListener
   *
   * @param plugin the plugin owner;
   */
  public SinkListener(final BasePlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void enable() {
    Bukkit.getPluginManager().registerEvents(this, this.getBasePlugin());
  }

  @Override
  public void disable() {
    HandlerList.unregisterAll(this);
  }

  @Override
  public BasePlugin getBasePlugin() {
    return this.plugin;
  }

  public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {}

  public void onAsyncPlayerPreLogin(final AsyncPlayerPreLoginEvent event) {}

  public void onBlockBreak(final BlockBreakEvent event) {}

  public void onBlockBurn(final BlockBurnEvent event) {}

  public void onBlockCanBuild(final BlockCanBuildEvent event) {}

  public void onBlockDamage(final BlockDamageEvent event) {}

  public void onSinkListener(final BlockDispenseEvent event) {}

  public void onBlockFade(final BlockFadeEvent event) {}

  public void onBlockForm(final BlockFormEvent event) {}

  public void onBlockFromTo(final BlockFromToEvent event) {}

  public void onBlockGrow(final BlockGrowEvent event) {}

  public void onBlockIgnite(final BlockIgniteEvent event) {}

  public void onBlockPhysics(final BlockPhysicsEvent event) {}

  public void onBlockPistonExtend(final BlockPistonExtendEvent event) {}

  public void onBlockPistonRetract(final BlockPistonRetractEvent event) {}

  public void onBlockPlace(final BlockPlaceEvent event) {}

  public void onBlockRedstone(final BlockRedstoneEvent event) {}

  public void onBlockSpread(final BlockSpreadEvent event) {}

  public void onBrewEvent(final BrewEvent event) {}

  public void onChunkLoadEvent(final ChunkLoadEvent event) {}

  public void onChunkPopulateEvent(final ChunkPopulateEvent event) {}

  public void onChunkUnload(final ChunkUnloadEvent event) {}

  public void onCraftItem(final CraftItemEvent event) {}

  public void onCreatureSpawn(final CreatureSpawnEvent event) {}

  public void onSinkListener(final CreeperPowerEvent event) {}

  public void onEnchantItem(final EnchantItemEvent event) {}

  public void onEntityBlockForm(final EntityBlockFormEvent event) {}

  public void onEntityBreakDoor(final EntityBreakDoorEvent event) {}

  public void onEntityChangeBlock(final EntityChangeBlockEvent event) {}

  public void onEntityCombustByBlock(final EntityCombustByBlockEvent event) {}

  public void onEntityCombustByEntity(final EntityCombustByEntityEvent event) {}

  public void onEntityCombust(final EntityCombustEvent event) {}

  public void onEntityCreatePortal(final EntityCreatePortalEvent event) {}

  public void onEntityDamageByBlock(final EntityDamageByBlockEvent event) {}

  public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {}

  public void onEntityDamage(final EntityDamageEvent event) {}

  public void onEntityDeath(final EntityDeathEvent event) {}

  public void onEntityExplode(final EntityExplodeEvent event) {}

  public void onEntityInteract(final EntityInteractEvent event) {}

  public void onEntityPortalEnter(final EntityPortalEnterEvent event) {}

  public void onEntityRegainHealth(final EntityRegainHealthEvent event) {}

  public void onEntityShootBow(final EntityShootBowEvent event) {}

  public void onEntityTame(final EntityTameEvent event) {}

  public void onEntityTarget(final EntityTargetEvent event) {}

  public void onEntityTargetLivingEntity(final EntityTargetLivingEntityEvent event) {}

  public void onEntityTeleport(final EntityTeleportEvent event) {}

  public void onExpBottle(final ExpBottleEvent event) {}

  public void onExplosionPrime(final ExplosionPrimeEvent event) {}

  public void onFoodLevelChange(final FoodLevelChangeEvent event) {}

  public void onFurnaceBurnEvent(final FurnaceBurnEvent event) {}

  public void onFurnaceSmelt(final FurnaceSmeltEvent event) {}

  public void onInventoryClick(final InventoryClickEvent event) {}

  public void onInventoryClose(final InventoryCloseEvent event) {}

  public void onInventoryOpen(final InventoryOpenEvent event) {}

  public void onItemDespawn(final ItemDespawnEvent event) {}

  public void onLeavesDecay(final LeavesDecayEvent event) {}

  public void onLightningStrike(final LightningStrikeEvent event) {}

  public void onMapInitializeEvent(final MapInitializeEvent event) {}

  public void onNotePlay(final NotePlayEvent event) {}

  public void onPigZap(final PigZapEvent event) {}

  public void onPlayerAnimation(final PlayerAnimationEvent event) {}

  public void onPlayerBedEnter(final PlayerBedEnterEvent event) {}

  public void onPlayerBedLeave(final PlayerBedLeaveEvent event) {}

  public void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event) {}

  public void onPlayerBucketFill(final PlayerBucketFillEvent event) {}

  public void onPlayerChatTabComplete(final PlayerChatTabCompleteEvent event) {}

  public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {}

  public void onPlayerDeath(final PlayerDeathEvent event) {}

  public void onPlayerDropItem(final PlayerDropItemEvent event) {}

  public void onPlayerEggThrow(final PlayerEggThrowEvent event) {}

  public void onPlayerExpChange(final PlayerExpChangeEvent event) {}

  public void onPlayerFish(final PlayerFishEvent event) {}

  public void onPlayerGameModeChange(final PlayerGameModeChangeEvent event) {}

  public void onPlayerInteractEntity(final PlayerInteractEntityEvent event) {}

  public void onPlayerInteract(final PlayerInteractEvent event) {}

  public void onPlayerItemBreak(final PlayerItemBreakEvent event) {}

  public void onPlayerItemHeld(final PlayerItemHeldEvent event) {}

  public void onPlayerJoin(final PlayerJoinEvent event) {}

  public void onPlayerKick(final PlayerKickEvent event) {}

  public void onPlayerLevelChange(final PlayerLevelChangeEvent event) {}

  public void onPlayerLogin(final PlayerLoginEvent event) {}

  public void onPlayerMove(final PlayerMoveEvent event) {}

  public void onPlayerPickupItem(final PlayerPickupItemEvent event) {}

  public void onPlayerPortal(final PlayerPortalEvent event) {}

  public void onPlayerQuit(final PlayerQuitEvent event) {}

  public void onPlayerRegisterChannel(final PlayerRegisterChannelEvent event) {}

  public void onPlayerRespawn(final PlayerRespawnEvent event) {}

  public void onPlayerShearEntity(final PlayerShearEntityEvent event) {}

  public void onPlayerTeleport(final PlayerTeleportEvent event) {}

  public void onPlayerToggleFlight(final PlayerToggleFlightEvent event) {}

  public void onPlayerToggleSneak(final PlayerToggleSneakEvent event) {}

  public void onPlayerToggleSprint(final PlayerToggleSprintEvent event) {}

  public void onPlayerUnregisterChannel(final PlayerUnregisterChannelEvent event) {}

  public void onPlayerVelocity(final PlayerVelocityEvent event) {}

  public void onPluginDisable(final PluginDisableEvent event) {}

  public void onPluginEnable(final PluginEnableEvent event) {}

  public void onPortalCreate(final PortalCreateEvent event) {}

  public void onPotionSplash(final PotionSplashEvent event) {}

  public void onPrepareItemEnchant(final PrepareItemEnchantEvent event) {}

  public void onRemoteServerCommand(final RemoteServerCommandEvent event) {}

  public void onServerCommand(final ServerCommandEvent event) {}

  public void onServerListPing(final ServerListPingEvent event) {}

  public void onServiceRegister(final ServiceRegisterEvent event) {}

  public void onServiceUnregister(final ServiceUnregisterEvent event) {}

  public void onSheepDyeWoolEvent(final SheepDyeWoolEvent event) {}

  public void onSheepRegrowWoolEvent(final SheepRegrowWoolEvent event) {}

  public void onSignChange(final SignChangeEvent event) {}

  public void onSlimeSplit(final SlimeSplitEvent event) {}

  public void onSpawnChange(final SpawnChangeEvent event) {}

  public void onStructureGrow(final StructureGrowEvent event) {}

  public void onThunderChange(final ThunderChangeEvent event) {}

  public void onVehicleBlockCollision(final VehicleBlockCollisionEvent event) {}

  public void onVehicleCreate(final VehicleCreateEvent event) {}

  public void onVehicleDamage(final VehicleDamageEvent event) {}

  public void onVehicleDestroy(final VehicleDestroyEvent event) {}

  public void onVehicleEnter(final VehicleEnterEvent event) {}

  public void onVehicleEntityCollision(final VehicleEntityCollisionEvent event) {}

  public void onVehicleExit(final VehicleExitEvent event) {}

  public void onVehicleMove(final VehicleMoveEvent event) {}

  public void onVehicleUpdate(final VehicleUpdateEvent event) {}

  public void onWeatherChange(final WeatherChangeEvent event) {}

  public void onWorldInit(final WorldInitEvent event) {}

  public void onWorldLoad(final WorldLoadEvent event) {}

  public void onWorldSave(final WorldSaveEvent event) {}

  public void onWorldUnload(final WorldUnloadEvent event) {}
}

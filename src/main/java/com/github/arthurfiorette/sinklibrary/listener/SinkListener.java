package com.github.arthurfiorette.sinklibrary.listener;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
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

/**
 * A abstract Spigot listener implementation with the most methods implements.
 * Override and use {@link org.bukkit.event.EventHandler} to make the method be
 * a listener. This listener will work when register is called.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public abstract class SinkListener implements Listener, BaseService {

  private final BasePlugin owner;

  /**
   * Constructs a new SinkListener
   *
   * @param owner the plugin owner;
   */
  public SinkListener(BasePlugin owner) {
    this.owner = owner;
  }

  @Override
  public void enable() {
    Bukkit.getPluginManager().registerEvents(this, this.getPlugin());
  }

  @Override
  public void disable() {
    HandlerList.unregisterAll(this);
  }

  @Override
  public BasePlugin getPlugin() {
    return this.owner;
  }

  public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {}

  public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {}

  public void onBlockBreak(BlockBreakEvent event) {}

  public void onBlockBurn(BlockBurnEvent event) {}

  public void onBlockCanBuild(BlockCanBuildEvent event) {}

  public void onBlockDamage(BlockDamageEvent event) {}

  public void onSinkListener(BlockDispenseEvent event) {}

  public void onBlockFade(BlockFadeEvent event) {}

  public void onBlockForm(BlockFormEvent event) {}

  public void onBlockFromTo(BlockFromToEvent event) {}

  public void onBlockGrow(BlockGrowEvent event) {}

  public void onBlockIgnite(BlockIgniteEvent event) {}

  public void onBlockPhysics(BlockPhysicsEvent event) {}

  public void onBlockPistonExtend(BlockPistonExtendEvent event) {}

  public void onBlockPistonRetract(BlockPistonRetractEvent event) {}

  public void onBlockPlace(BlockPlaceEvent event) {}

  public void onBlockRedstone(BlockRedstoneEvent event) {}

  public void onBlockSpread(BlockSpreadEvent event) {}

  public void onBrewEvent(BrewEvent event) {}

  public void onChunkLoadEvent(ChunkLoadEvent event) {}

  public void onChunkPopulateEvent(ChunkPopulateEvent event) {}

  public void onChunkUnload(ChunkUnloadEvent event) {}

  public void onCraftItem(CraftItemEvent event) {}

  public void onCreatureSpawn(CreatureSpawnEvent event) {}

  public void onSinkListener(CreeperPowerEvent event) {}

  public void onEnchantItem(EnchantItemEvent event) {}

  public void onEntityBlockForm(EntityBlockFormEvent event) {}

  public void onEntityBreakDoor(EntityBreakDoorEvent event) {}

  public void onEntityChangeBlock(EntityChangeBlockEvent event) {}

  public void onEntityCombustByBlock(EntityCombustByBlockEvent event) {}

  public void onEntityCombustByEntity(EntityCombustByEntityEvent event) {}

  public void onEntityCombust(EntityCombustEvent event) {}

  public void onEntityCreatePortal(EntityCreatePortalEvent event) {}

  public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {}

  public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {}

  public void onEntityDamage(EntityDamageEvent event) {}

  public void onEntityDeath(EntityDeathEvent event) {}

  public void onEntityExplode(EntityExplodeEvent event) {}

  public void onEntityInteract(EntityInteractEvent event) {}

  public void onEntityPortalEnter(EntityPortalEnterEvent event) {}

  public void onEntityRegainHealth(EntityRegainHealthEvent event) {}

  public void onEntityShootBow(EntityShootBowEvent event) {}

  public void onEntityTame(EntityTameEvent event) {}

  public void onEntityTarget(EntityTargetEvent event) {}

  public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {}

  public void onEntityTeleport(EntityTeleportEvent event) {}

  public void onExpBottle(ExpBottleEvent event) {}

  public void onExplosionPrime(ExplosionPrimeEvent event) {}

  public void onFoodLevelChange(FoodLevelChangeEvent event) {}

  public void onFurnaceBurnEvent(FurnaceBurnEvent event) {}

  public void onFurnaceSmelt(FurnaceSmeltEvent event) {}

  public void onInventoryClick(InventoryClickEvent event) {}

  public void onInventoryClose(InventoryCloseEvent event) {}

  public void onInventoryOpen(InventoryOpenEvent event) {}

  public void onItemDespawn(ItemDespawnEvent event) {}

  public void onLeavesDecay(LeavesDecayEvent event) {}

  public void onLightningStrike(LightningStrikeEvent event) {}

  public void onMapInitializeEvent(MapInitializeEvent event) {}

  public void onNotePlay(NotePlayEvent event) {}

  public void onPigZap(PigZapEvent event) {}

  public void onPlayerAnimation(PlayerAnimationEvent event) {}

  public void onPlayerBedEnter(PlayerBedEnterEvent event) {}

  public void onPlayerBedLeave(PlayerBedLeaveEvent event) {}

  public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {}

  public void onPlayerBucketFill(PlayerBucketFillEvent event) {}

  public void onPlayerChatTabComplete(PlayerChatTabCompleteEvent event) {}

  public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {}

  public void onPlayerDeath(PlayerDeathEvent event) {}

  public void onPlayerDropItem(PlayerDropItemEvent event) {}

  public void onPlayerEggThrow(PlayerEggThrowEvent event) {}

  public void onPlayerExpChange(PlayerExpChangeEvent event) {}

  public void onPlayerFish(PlayerFishEvent event) {}

  public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {}

  public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {}

  public void onPlayerInteract(PlayerInteractEvent event) {}

  public void onPlayerItemBreak(PlayerItemBreakEvent event) {}

  public void onPlayerItemHeld(PlayerItemHeldEvent event) {}

  public void onPlayerJoin(PlayerJoinEvent event) {}

  public void onPlayerKick(PlayerKickEvent event) {}

  public void onPlayerLevelChange(PlayerLevelChangeEvent event) {}

  public void onPlayerLogin(PlayerLoginEvent event) {}

  public void onPlayerMove(PlayerMoveEvent event) {}

  public void onPlayerPickupItem(PlayerPickupItemEvent event) {}

  public void onPlayerPortal(PlayerPortalEvent event) {}

  public void onPlayerQuit(PlayerQuitEvent event) {}

  public void onPlayerRegisterChannel(PlayerRegisterChannelEvent event) {}

  public void onPlayerRespawn(PlayerRespawnEvent event) {}

  public void onPlayerShearEntity(PlayerShearEntityEvent event) {}

  public void onPlayerTeleport(PlayerTeleportEvent event) {}

  public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {}

  public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {}

  public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {}

  public void onPlayerUnregisterChannel(PlayerUnregisterChannelEvent event) {}

  public void onPlayerVelocity(PlayerVelocityEvent event) {}

  public void onPluginDisable(PluginDisableEvent event) {}

  public void onPluginEnable(PluginEnableEvent event) {}

  public void onPortalCreate(PortalCreateEvent event) {}

  public void onPotionSplash(PotionSplashEvent event) {}

  public void onPrepareItemEnchant(PrepareItemEnchantEvent event) {}

  public void onRemoteServerCommand(RemoteServerCommandEvent event) {}

  public void onServerCommand(ServerCommandEvent event) {}

  public void onServerListPing(ServerListPingEvent event) {}

  public void onServiceRegister(ServiceRegisterEvent event) {}

  public void onServiceUnregister(ServiceUnregisterEvent event) {}

  public void onSheepDyeWoolEvent(SheepDyeWoolEvent event) {}

  public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent event) {}

  public void onSignChange(SignChangeEvent event) {}

  public void onSlimeSplit(SlimeSplitEvent event) {}

  public void onSpawnChange(SpawnChangeEvent event) {}

  public void onStructureGrow(StructureGrowEvent event) {}

  public void onThunderChange(ThunderChangeEvent event) {}

  public void onVehicleBlockCollision(VehicleBlockCollisionEvent event) {}

  public void onVehicleCreate(VehicleCreateEvent event) {}

  public void onVehicleDamage(VehicleDamageEvent event) {}

  public void onVehicleDestroy(VehicleDestroyEvent event) {}

  public void onVehicleEnter(VehicleEnterEvent event) {}

  public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {}

  public void onVehicleExit(VehicleExitEvent event) {}

  public void onVehicleMove(VehicleMoveEvent event) {}

  public void onVehicleUpdate(VehicleUpdateEvent event) {}

  public void onWeatherChange(WeatherChangeEvent event) {}

  public void onWorldInit(WorldInitEvent event) {}

  public void onWorldLoad(WorldLoadEvent event) {}

  public void onWorldSave(WorldSaveEvent event) {}

  public void onWorldUnload(WorldUnloadEvent event) {}
}

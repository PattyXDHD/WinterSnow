package de.pattyxdhd.snow;

import de.pattyxdhd.snow.utils.FileWriter;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Project: WinterSnow
 * Created on 10.12.2018
 * Copyright (c) 2018 PattyXDHD. All rights reserved.
 */

public class WinterSnow extends JavaPlugin {

    @Getter
    private static WinterSnow instance;

    @Getter
    private final static String prefix = "§8[§bWinterSnow§8] §7";

    @Getter
    private static FileWriter fileWriter;

    private Integer particleAmount = 60;
    private Integer ticks = 1;

    @Override
    public void onEnable() {
        instance = this;
        fileWriter = new FileWriter(WinterSnow.getInstance().getDataFolder().getPath(), "config.yml");

        loadFile();
        startParticle();

        log("§aPlugin geladen.");
    }

    @Override
    public void onDisable() {
        log("§cPlugin entladen.");
    }

    private void loadFile() {
        fileWriter.setDefaultValue("ParticleAmount", particleAmount);
        fileWriter.setDefaultValue("Ticks", ticks);
        particleAmount = fileWriter.getInt("ParticleAmount");
        ticks = fileWriter.getInt("Ticks");
    }

    private void startParticle(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            Bukkit.getOnlinePlayers().forEach(target -> {
                sendParticle(target, target.getLocation(), 0.0F, particleAmount);
            });
        }, ticks, ticks);
    }

    public void sendParticle(final Player p, final Location loc, final Float speed, final Integer amount) {
        EnumParticle enumParticle = EnumParticle.FIREWORKS_SPARK;
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(enumParticle, true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), 20.0F, 20.0F, 20.0F, speed.floatValue(), amount.intValue(), null);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    private void log(final String message) {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + message);
    }

}

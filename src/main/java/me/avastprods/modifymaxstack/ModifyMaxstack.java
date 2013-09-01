package main.java.me.avastprods.modifymaxstack;

import java.lang.reflect.Field;

import net.minecraft.server.v1_6_R2.Item;

import org.bukkit.plugin.java.JavaPlugin;

public class ModifyMaxstack extends JavaPlugin {

	public void onEnable() {
		saveDefaultConfig();

		for (String str : getConfig().getStringList("itemstack")) {
			String[] split = str.split(",");
			Item item = Item.byId[Integer.parseInt(split[0])];
			int size = 0;

			try {
				size = Integer.valueOf(split[1]);
			} catch (NumberFormatException ex) {
				System.out.println("An error occured while parsing integer. at:" + split[1]);
			}

			modifyMaxStack(item, size);
		}
	}

	public void modifyMaxStack(Item item, int amount) {
		try {
			Field f = Item.class.getDeclaredField("maxStackSize");
			f.setAccessible(true);
			f.setInt(item, amount);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}

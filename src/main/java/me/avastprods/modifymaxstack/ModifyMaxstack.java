package main.java.me.avastprods.modifymaxstack;

import java.lang.reflect.Field;

import net.minecraft.server.v1_6_R3.Item;

import org.bukkit.plugin.java.JavaPlugin;

public class ModifyMaxstack extends JavaPlugin {

	public void onEnable() {
		saveDefaultConfig();

		for (String str : getConfig().getStringList("itemstack")) {
			String[] split = str.split(":");

			Item item = null;
			int size = 0;

			try {
				item = Item.byId[Integer.valueOf(split[0])];
			} catch (NumberFormatException a) {
				System.out.println("An error occured while parsing item id.\nat: " + split[0]);
			}

			try {
				size = Integer.valueOf(split[1]);
			} catch (NumberFormatException b) {
				System.out.println("An error occured while parsing item amount.\nat: " + split[1]);
				return;
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

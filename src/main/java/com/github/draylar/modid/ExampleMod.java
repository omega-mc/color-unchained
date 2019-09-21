package com.github.draylar.modid;

import com.github.draylar.modid.common.Blocks;
import com.github.draylar.modid.common.Items;
import com.github.draylar.modid.common.Entities;
import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer
{
	public static final String MODID = "modid";

	@Override
	public void onInitialize()
	{
		Blocks.init();
		Items.init();
		Entities.init();
	}
}

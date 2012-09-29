/** TheWalls2: The Walls 2 plugin.
  * Copyright (C) 2012  Andrew Stevanus (Hoot215) <hoot893@gmail.com>
  * 
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * 
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */

package me.Hoot215.TheWalls2.api;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public class AddonLoader {
	private Set<Addon> addons = new HashSet<Addon>();
	
	public Set<Addon> getAddons() {
		return addons;
	}
	
	public void loadAddons(File addonDir) {
		if (!addonDir.exists()) {
			addonDir.mkdirs();
			return;
		}
		
		File[] addonJars = addonDir.listFiles();
		if (addons != null && addonJars.length > 0) {
			for (File file : addonJars) {
				try {
					Addon addon = this.loadAddon(file);
					addons.add(addon);
					addon.load();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Addon loadAddon(File file) throws Throwable {
		ClassLoader child = URLClassLoader.newInstance(new URL[]
				{file.toURI().toURL()}, this.getClass().getClassLoader());
		Class<?> jarClass = Class.forName("Main", true, child);
		Class<? extends Addon> addon = jarClass.asSubclass(Addon.class);
		Constructor<? extends Addon> constructor = addon.getConstructor();
		return constructor.newInstance();
	}
	
	public void unloadAddons() {
		for (Addon addon : addons) {
			addon.unload();
		}
	}
}
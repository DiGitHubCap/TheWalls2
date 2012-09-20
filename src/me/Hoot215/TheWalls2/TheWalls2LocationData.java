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

package me.Hoot215.TheWalls2;

import java.util.HashSet;
import java.util.Set;

import me.Hoot215.TheWalls2.util.Cuboid;

import org.bukkit.Location;
import org.bukkit.World;

public class TheWalls2LocationData {
	private Location lobby = new Location(null, -793.5, 97.0, -56.5);
	private Set<Location> slots = new HashSet<Location>();
	private Location team1Slot0 = new Location(null, -864.5, 71.0, -135.5);
	private Location team1Slot1 = new Location(null, -864.5, 71.0, -132.5);
	private Location team1Slot2 = new Location(null, -864.5, 71.0, -129.5);
	private Location team2Slot0 = new Location(null, -864.5, 71.0, -215.5);
	private Location team2Slot1 = new Location(null, -864.5, 71.0, -212.5);
	private Location team2Slot2 = new Location(null, -864.5, 71.0, -209.5);
	private Location team3Slot0 = new Location(null, -722.5, 71.0, -209.5);
	private Location team3Slot1 = new Location(null, -722.5, 71.0, -212.5);
	private Location team3Slot2 = new Location(null, -722.5, 71.0, -215.5);
	private Location team4Slot0 = new Location(null, -722.5, 71.0, -129.5);
	private Location team4Slot1 = new Location(null, -722.5, 71.0, -132.5);
	private Location team4Slot2 = new Location(null, -722.5, 71.0, -135.5);
	private Set<Cuboid> walls = new HashSet<Cuboid>();
	private Cuboid worldCube = new Cuboid(new Location(null, -917, 5, -245),
			new Location(null, -671, 112, -47));
	
	public TheWalls2LocationData(World world) {
		lobby.setWorld(world);
		team1Slot0.setWorld(world);
		team1Slot1.setWorld(world);
		team1Slot2.setWorld(world);
		team2Slot0.setWorld(world);
		team2Slot1.setWorld(world);
		team2Slot2.setWorld(world);
		team3Slot0.setWorld(world);
		team3Slot1.setWorld(world);
		team3Slot2.setWorld(world);
		team4Slot0.setWorld(world);
		team4Slot1.setWorld(world);
		team4Slot2.setWorld(world);
		slots.add(team1Slot0);
		slots.add(team1Slot1);
		slots.add(team1Slot2);
		slots.add(team2Slot0);
		slots.add(team2Slot1);
		slots.add(team2Slot2);
		slots.add(team3Slot0);
		slots.add(team3Slot1);
		slots.add(team3Slot2);
		slots.add(team4Slot0);
		slots.add(team4Slot1);
		slots.add(team4Slot2);
		walls.add(new Cuboid(new Location(null, -864, 68, -164),
				new Location(null, -804, 97, -164)));
		walls.add(new Cuboid(new Location(null, -803, 68, -103),
				new Location(null, -803, 97, -163)));
		walls.add(new Cuboid(new Location(null, -785, 68, -164),
				new Location(null, -785, 97, -103)));
		walls.add(new Cuboid(new Location(null, -724, 68, -164),
				new Location(null, -784, 97, -164)));
		walls.add(new Cuboid(new Location(null, -724, 68, -182),
				new Location(null, -784, 97, -182)));
		walls.add(new Cuboid(new Location(null, -785, 68, -243),
				new Location(null, -785, 97, -183)));
		walls.add(new Cuboid(new Location(null, -803, 68, -243),
				new Location(null, -803, 97, -183)));
		walls.add(new Cuboid(new Location(null, -864, 68, -182),
				new Location(null, -804, 97, -182)));
	}
	
	public Location getLobby() {
		return lobby;
	}
	
	public void setLobby(Location loc) {
		lobby = loc;
	}
	
	public Location getSlot(int team, int slot) {
		switch (team) {
			case 1:
				switch (slot) {
					case 0:
						return team1Slot0;
					case 1:
						return team1Slot1;
					case 2:
						return team1Slot2;
				}
				return null;
			case 2:
				switch (slot) {
					case 0:
						return team2Slot0;
					case 1:
						return team2Slot1;
					case 2:
						return team2Slot2;
				}
				return null;
			case 3:
				switch (slot) {
					case 0:
						return team3Slot0;
					case 1:
						return team3Slot1;
					case 2:
						return team3Slot2;
				}
				return null;
			case 4:
				switch (slot) {
					case 0:
						return team4Slot0;
					case 1:
						return team4Slot1;
					case 2:
						return team4Slot2;
				}
				return null;
		}
		return null;
	}
	
	public Set<Location> getSlots() {
		return slots;
	}
	
	public boolean isPartOfWall(Location loc) {
		for (Cuboid c : walls) {
			if (c.isIn(loc))
				return true;
		}
		return false;
	}
	
	public Cuboid getWorldCube() {
		return worldCube;
	}
}
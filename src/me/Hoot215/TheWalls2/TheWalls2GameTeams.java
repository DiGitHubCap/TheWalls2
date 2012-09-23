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

public class TheWalls2GameTeams {
	private TheWalls2PlayerQueue queue;
	private Set<String> team1;
	private Set<String> team2;
	private Set<String> team3;
	private Set<String> team4;
	
	public TheWalls2GameTeams(TheWalls2PlayerQueue queueInstance) {
		queue = queueInstance;
		team1 = new HashSet<String>();
		team2 = new HashSet<String>();
		team3 = new HashSet<String>();
		team4 = new HashSet<String>();
	}
	
	public boolean addPlayerToTeam(int i, String playerName) {
		switch (i) {
			case 1:
				team1.add(playerName);
				return true;
			case 2:
				team2.add(playerName);
				return true;
			case 3:
				team3.add(playerName);
				return true;
			case 4:
				team4.add(playerName);
				return true;
			default:
				return false;
		}
	}
	
	public void removePlayer(String playerName) {
		team1.remove(playerName);
		team2.remove(playerName);
		team3.remove(playerName);
		team4.remove(playerName);
	}
	
	public boolean compareTeams(String player1, String player2) {
		if (getPlayerTeam(player1) == getPlayerTeam(player2))
			return true;
		return false;
	}
	
	public int getPlayerTeam(String playerName) {
		if (team1.contains(playerName))
			return 1;
		if (team2.contains(playerName))
			return 2;
		if (team3.contains(playerName))
			return 3;
		if (team4.contains(playerName))
			return 4;
		return 0;
	}
	
	public boolean isInTeam(String playerName) {
		if (!team1.contains(playerName)
				&& !team2.contains(playerName)
				&& !team3.contains(playerName)
				&& !team4.contains(playerName))
			return false;
		return true;
	}
	
	public boolean isTeamFull(int i) {
		switch (i) {
			case 1:
				if (team1.size() < 3)
					return false;
				return true;
			case 2:
				if (team2.size() < 3)
					return false;
				return true;
			case 3:
				if (team3.size() < 3)
					return false;
				return true;
			case 4:
				if (team4.size() < 3)
					return false;
				return true;
			default:
				return true;
		}
	}
	
	public void cleanup() {
		for (String s : queue.getList()) {
			String playerName = s.split(":")[0];
			if (!isInTeam(playerName)) {
				insertIntoTeam(playerName);
			}
		}
	}
	
	public void insertIntoTeam(String playerName) {
		if (team1.isEmpty()) {
			team1.add(playerName);
		}
		else if (team2.isEmpty()) {
			team2.add(playerName);
		}
		else if (team3.isEmpty()) {
			team3.add(playerName);
		}
		else if (team4.isEmpty()) {
			team4.add(playerName);
		}
		else if (team1.size() == 1) {
			team1.add(playerName);
		}
		else if (team2.size() == 1) {
			team2.add(playerName);
		}
		else if (team3.size() == 1) {
			team3.add(playerName);
		}
		else if (team4.size() == 1) {
			team4.add(playerName);
		}
		else if (team1.size() == 2) {
			team1.add(playerName);
		}
		else if (team2.size() == 2) {
			team2.add(playerName);
		}
		else if (team3.size() == 2) {
			team3.add(playerName);
		}
		else if (team4.size() == 2) {
			team4.add(playerName);
		}
	}
	
	public Set<String> getTeam(int i) {
		switch (i) {
			case 1:
				return team1;
			case 2:
				return team2;
			case 3:
				return team3;
			case 4:
				return team4;
			default:
				return null;
		}
	}
	
	public int getEmptyTeamCount() {
		int i = 0;
		
		if (team1.isEmpty())
			i++;
		if (team2.isEmpty())
			i++;
		if (team3.isEmpty())
			i++;
		if (team4.isEmpty())
			i++;
		
		return i;
	}
	
	public void reset() {
		team1 = new HashSet<String>();
		team2 = new HashSet<String>();
		team3 = new HashSet<String>();
		team4 = new HashSet<String>();
	}
}
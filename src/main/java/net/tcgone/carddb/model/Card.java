/*
Copyright 2018 axpendix@hotmail.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package net.tcgone.carddb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {
	/**
	 * Experimental id: 101004
	 */
	@Id
	public String id;
	/**
	 * Pio id: base1-4
	 */
	public String pioId;
	/**
	 * Our id: CHARIZARD_4:BASE_SET
	 */
	public String enumId;
	/**
	 *
	 */
	public Set set;
	/**
	 * Card name: Charizard
	 */
	public String name;
	/**
	 * 4
	 */
	public Integer nationalPokedexNumber;
	/**
	 * 4
	 */
	public String number;
	/**
	 * Img url: https://tcgone.net/scans/m/base_set/004.jpg
	 */
	@JsonIgnore
	public String imageUrl;
	/**
	 * Img url: https://tcgone.net/scans/l/base_set/004.jpg
	 */
	@JsonIgnore
	public String imageUrlHiRes;
	/**
	 * Array of types: ["R"]
	 */
	public List<String> types;
	/**
	 * Either Pokémon, Trainer or Energy
	 */
	public String superType;
	/**
	 * Stage 2
	 */
	public List<String> subTypes;
	/**
	 * Has additional types like OWNERS_POKEMON, TEAM_PLASMA, etc. []
	 */
	public List<String> cardFlags;
	/**
	 * Charmeleon
	 */
	public String evolvesFrom;
	/**
	 *
	 */
	public List<String> evolvesTo;
	/**
	 * 120
	 */
	public Integer hp;
	/**
	 * 3
	 */
	public Integer retreatCost;
	/**
	 * [ {
	 "type": "Pokémon Power",
	 "name": "Energy Burn",
	 "text": "As often as you like during your turn (before your attack), you may turn all Energy attached to Charizard into [R] for the rest of the turn. This power can't be used if Charizard is Asleep, Confused, or Paralyzed."
	 } ]
	 */
	public List<Ability> abilities;
	/**
	 * [ {
	 "cost": ["R","R","R","R"],
	 "name": "Fire Spin",
	 "text": "Discard 2 Energy cards attached to Charizard in order to use this attack.",
	 "damage": "100",
	 "convertedEnergyCost": 4
	 } ]
	 */
	public List<Move> moves;
	/**
	 * [ {
	 "type": "W",
	 "value": "×2"
	 } ]
	 */
	public List<WeaknessResistance> weaknesses;
	/**
	 * [ {
	 "type": "F",
	 "value": "-30"
	 } ]
	 */
	public List<WeaknessResistance> resistances;
	/**
	 * Charizard (BS 4)
	 */
	public String fullName;
	/**
	 * charizard-bs-4
	 */
	public String seoName;
	/**
	 * Rare Holo
	 */
	public String rarity;
	/**
	 * Epic
	 */
	public String careerClass;
	/**
	 * Trainer/Energy text/Pokemon ruling text. Each entry is a line.
	 */
	public List<String> text;
	/**
	 * Energy types
	 */
	public List<List<String>> energy;
	/**
	 * Mitsuhiro Arita
	 */
	public String artist;
	/**
	 * Spits fire that is hot enough to melt boulders. Known to unintentionally cause forest fires.
	 */
	public String flavorText;
	/**
	 * (null)
	 */
	public String implNotes;
	/**
	 *
		pokemonPower {
			def set = [] as Set
			def eff1, eff2
			onActivate {
				if(eff1) eff1.unregister()
				if(eff2) eff2.unregister()
				eff1 = delayed {
					before BETWEEN_TURNS, {
						set.clear()
					}
				}
				eff2 = getter GET_ENERGY_TYPES, { holder->
					if(set.contains(holder.effect.card)) {
						int count = holder.object.size()
						holder.object = [(1..count).collect{[FIRE] as Set}]
					}
				}
			}
			actionA {
				assert !(self.specialConditions) : "$self is affected by a special condition"
				def newSet = [] as Set
				newSet.addAll(self.cards.filterByType(ENERGY))
				if(newSet != set){
					powerUsed()
					set.clear()
					set.addAll(newSet)
				} else {
					wcu "Nothing to burn more"
				}
			}
		}
		move {
			onAttack {
				damage 100
				discardSelfEnergy(C) // one energy card
				discardSelfEnergy(C) // one energy card
			}
		}
	 */
	public String script;

	public String copyOfId;

//	public String similarToId;
//	// reprint-exact, reprint-changed, variation
//	public String similarityType;

	/**
	 * true when this has been merged with pio, so the definition is finalized.
	 * merged cards won't be attempted to be merged again, so the process can be
	 * restarted when failed.
	 */
	public Boolean merged;
	/**
	 * Sort order (respective to its set)
	 */
	public Integer order;

	// public String stage;

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
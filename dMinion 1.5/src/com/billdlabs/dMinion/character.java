package com.billdlabs.dMinion;

public class character implements Comparable<character>{

	//public vars
	public String charName;
	public int initModifier;
	public int initRoll;
	public int secondInt;
	public int hp;
	public int tempHp;
	public int ac;
	public int reflex;
	public int fortitude;
	public int will;
	public int str;
	public int con;
	public int dex;
	public int wis;
	public int cha;
	public int speed;
	public int pInisght;
	public int pPerception;
	public int deathSaves;
	public boolean dead;
	public boolean poisoned;
	public int poisonDamage;
	public boolean burning;
	public int fireDamage;
	public boolean freezing;
	public int coldDamage;
	public boolean slowed;
	public boolean dazed;
	public boolean unconscious;
	public boolean blinded;
	public boolean deafened;
	public boolean dominated;
	public String dominagedBy;
	public boolean helpless;
	public boolean immobilized;
	public boolean marked;
	public String markedBy;
	public boolean petrified;
	public boolean prone;
	public boolean restrained;
	public boolean stunned;
	public boolean surprised;
	public boolean weakened;
	public int totalInit = initModifier + initRoll;
	
	
	//this one calculates the total init. The logic could be put in the 'add' class, but all the numbers involved need to be stored to determine initiative order.
	public int getTotalInit() {
		return totalInit;
	}
	
	//getters and setters
	
	public String getCharName() {
		return charName;
	}
	public int getSecondInt() {
		return secondInt;
	}
	public void setSecondInt(int secondInt) {
		this.secondInt = secondInt;
	}
	public void setCharName(String charName) {
		this.charName = charName;
	}
	public int getInitModifier() {
		return initModifier;
	}
	public void setInitModifier(int initModifier) {
		this.initModifier = initModifier;
	}
	public int getInitRoll() {
		return initRoll;
	}
	public void setInitRoll(int initRoll) {
		this.initRoll = initRoll;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getTempHp() {
		return tempHp;
	}

	public void setTempHp(int tempHp) {
		this.tempHp = tempHp;
	}

	public int getDeathSaves() {
		return deathSaves;
	}
	public void setDeathSaves(int deathSaves) {
		this.deathSaves = deathSaves;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public boolean isPoisoned() {
		return poisoned;
	}
	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}
	public int getPoisonDamage() {
		return poisonDamage;
	}
	public void setPoisonDamage(int poisonDamage) {
		this.poisonDamage = poisonDamage;
	}
	public boolean isBurning() {
		return burning;
	}
	public void setBurning(boolean burning) {
		this.burning = burning;
	}
	public int getFireDamage() {
		return fireDamage;
	}
	public void setFireDamage(int fireDamage) {
		this.fireDamage = fireDamage;
	}
	public boolean isFreezing() {
		return freezing;
	}
	public void setFreezing(boolean freezing) {
		this.freezing = freezing;
	}
	public int getColdDamage() {
		return coldDamage;
	}
	public void setColdDamage(int coldDamage) {
		this.coldDamage = coldDamage;
	}
	public boolean isSlowed() {
		return slowed;
	}
	public void setSlowed(boolean slowed) {
		this.slowed = slowed;
	}
	public boolean isDazed() {
		return dazed;
	}
	public void setDazed(boolean dazed) {
		this.dazed = dazed;
	}
	public boolean isUnconscious() {
		return unconscious;
	}
	public void setUnconscious(boolean unconscious) {
		this.unconscious = unconscious;
	}
	public boolean isBlinded() {
		return blinded;
	}
	public void setBlinded(boolean blinded) {
		this.blinded = blinded;
	}
	public boolean isDeafened() {
		return deafened;
	}
	public void setDeafened(boolean deafened) {
		this.deafened = deafened;
	}
	public boolean isDominated() {
		return dominated;
	}
	public void setDominated(boolean dominated) {
		this.dominated = dominated;
	}
	public String getDominagedBy() {
		return dominagedBy;
	}
	public void setDominagedBy(String dominagedBy) {
		this.dominagedBy = dominagedBy;
	}
	public boolean isHelpless() {
		return helpless;
	}
	public void setHelpless(boolean helpless) {
		this.helpless = helpless;
	}
	public boolean isImmobilized() {
		return immobilized;
	}
	public void setImmobilized(boolean immobilized) {
		this.immobilized = immobilized;
	}
	public boolean isMarked() {
		return marked;
	}
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	public String getMarkedBy() {
		return markedBy;
	}
	public void setMarkedBy(String markedBy) {
		this.markedBy = markedBy;
	}
	public boolean isPetrified() {
		return petrified;
	}
	public void setPetrified(boolean petrified) {
		this.petrified = petrified;
	}
	public boolean isProne() {
		return prone;
	}
	public void setProne(boolean prone) {
		this.prone = prone;
	}
	public boolean isRestrained() {
		return restrained;
	}
	public void setRestrained(boolean restrained) {
		this.restrained = restrained;
	}
	public boolean isStunned() {
		return stunned;
	}
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	public boolean isSurprised() {
		return surprised;
	}
	public void setSurprised(boolean surprised) {
		this.surprised = surprised;
	}
	public boolean isWeakened() {
		return weakened;
	}
	public void setWeakened(boolean weakened) {
		this.weakened = weakened;
	}
	public int getAc() {
		return ac;
	}
	public void setAc(int ac) {
		this.ac = ac;
	}
	public int getReflex() {
		return reflex;
	}
	public void setReflex(int reflex) {
		this.reflex = reflex;
	}
	public int getFortitude() {
		return fortitude;
	}
	public void setFortitude(int fortitude) {
		this.fortitude = fortitude;
	}
	public int getWill() {
		return will;
	}
	public void setWill(int will) {
		this.will = will;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getCon() {
		return con;
	}
	public void setCon(int con) {
		this.con = con;
	}
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public int getWis() {
		return wis;
	}
	public void setWis(int wis) {
		this.wis = wis;
	}
	public int getCha() {
		return cha;
	}
	public void setCha(int cha) {
		this.cha = cha;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getpInisght() {
		return pInisght;
	}
	public void setpInisght(int pInisght) {
		this.pInisght = pInisght;
	}
	public int getpPerception() {
		return pPerception;
	}
	public void setpPerception(int pPerception) {
		this.pPerception = pPerception;
	}

	@Override
	public int compareTo(character another) {
		int compareInit = ((character) another).getTotalInit();
		
		return this.totalInit - compareInit; 
	}
}

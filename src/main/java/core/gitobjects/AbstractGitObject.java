package core.gitobjects;

import java.util.List;

import core.GitProperty;
import core.IRepository;

public abstract class AbstractGitObject implements IGitObject{
	private GitObjectType type;
	private String rawData;
	private List<GitProperty<?>> properties;
	private String hash;
	private IRepository repository;
	private IGitObject parent;
	
	protected AbstractGitObject(String hash, String rawData){
		this.hash = hash;
		this.setRawData(rawData);
	}
	/**
	 * Retourne le type de l'objet
	 * @return le type de l'objet
	 */
	public  GitObjectType getType(){
		return this.type;
	}
	
	/**
	 * Retourne la liste de toutes les propriétés de l'objets
	 * @return la liste de toutes les propriétés de l'objets
	 */
	public List<GitProperty<?>> getProperties(){
		return this.properties;
	}
	
	/**
	 * Retourne les données brutes d'un objet git.
	 * @return les données brutes d'un objet git.
	 */
	public String getRawData(){
		return this.rawData;
	}
	
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
	
	public void setType(GitObjectType type) {
		this.type = type;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void  fillData(){
		return;
	}
	
	public IRepository getRepository() {
		return repository;
	}
	
	public void setRepository(IRepository repository) {
		this.repository = repository;
	}
	
	public void setParent(IGitObject parent){
		this.parent = parent;
	}
}
